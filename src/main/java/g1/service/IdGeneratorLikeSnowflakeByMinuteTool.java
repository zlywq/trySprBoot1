package g1.service;


import cmn2.util.ErrCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;


/*
 * 这里在IdGeneratorLikeSnowflake的基础上，使递增序号对应的时间为分钟。
 * 这样做有几个好处。
 * 一是使得取mod时更均匀，因为如果是按以前的millisecond，很可能导致序号为0的值过多。
 * 二是可以对抗系统时间的调整。比如由于闰秒的调整。更多的是由于系统时间同步校准的调整。
 * 三是小批量聚合成大批量，可以有效应对需求洪峰，虽然之前的小批量也应该足够。
 * 四，时间粒度到分钟，也应该对实际应用不产生太大的妨碍。时间粒度到毫秒，其实没必要。
 * 这样做的缺点。
 * 一，浪费了大约10%的序号空间。因为60000 到 65536 之间的一段序号被浪费了。但是可以接受，毕竟总量足够大。
//41+10+12=63  , 1min = 1000*60ms , 1024*64=2^16 , 41-16+1=26, 12+16-1=27 , 2^26/60/24/365=127year , 2^27=134217728rec/min ~-> 2236962rec/sec
 *
 */

public class IdGeneratorLikeSnowflakeByMinuteTool {

	private static final Logger logger = LoggerFactory.getLogger(IdGeneratorLikeSnowflakeByMinuteTool.class);

	//把datacenterIdBits缩减2位，把timestampBits增加2位，这样 =2^43/(3600*1000*24*365) =~ 278year，够用了，也不用去靠twepoch来增加几十年了
	
//	private static final long hhepoch = 1300000000000L; //13 Mar 2011 07:06:40 GMT
	private static final long hhepoch = 1439526000000L;//14 Aug 2015 04:20:00 GMT
	//之所以采用这个值hhepoch，是省一点刻度下来，以后可以往上升。比如某一段id觉得用过了，可以废弃掉这一段，此时把hhepoch减少一定数值（对应若干天或若干月）即可。
	//之所以担心id用过了，是由于微信支付那边只有正式环境，测试时用过一个id就不能在下次测试或正式环境中使用这个id了。
	private static final long twepoch = hhepoch /1000/60 ; //0L; //1288834974657L; // 4 Nov 2010 01:42:54 GMT

    //41+10+12=63  , 1min = 1000*60ms , 1024*64=2^16 , 41-16+1=26, 12+16-1=27 , 2^26/60/24/365=127year , 2^27=134217728rec/min ~-> 2236962rec/sec
    private static final long datacenterIdBits = 10L;//10L;
    private static final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    public static final long UpLimitDatacenterId = maxDatacenterId + 1;
    private static final long timestampBits = 26L; //41L;
    public static final long sequenceBits = 27L;

    private static final long datacenterIdShift = sequenceBits; //64L-datacenterIdBits;
    private static final long timestampLeftShift = sequenceBits + datacenterIdBits; // 64L-datacenterIdBits-timestampBits;
    public static final long sequenceMax =  134217728L; //X 2L^sequenceBits; // 2^27=134217728rec/min //4096;

    
//    private long datacenterId = -1;    
//    private volatile long sequence = 0L;    
    private HashMap<String, Long> hmSequence = new HashMap<String, Long>();
    //private volatile long lastTimestamp = -1L;
    private HashMap<String, Long> hmLastTimestamp = new HashMap<String, Long>();

   
    private long getSequence(String tableKey){
    	long sequence = 0;
    	Long objSequence = hmSequence.get(tableKey);
    	if (objSequence != null){
    		sequence = objSequence;
    	}
    	return sequence;
    }
    private void setSequence(String tableKey, long sequence){
    	hmSequence.put(tableKey, sequence);
    }

    private long getLastTimestamp(String tableKey){
    	long lastTimestamp = 0;
    	Long objLastTimestamp = hmLastTimestamp.get(tableKey);
    	if (objLastTimestamp != null){
    		lastTimestamp = objLastTimestamp;
    	}
    	return lastTimestamp;
    }
    private void setLastTimestamp(String tableKey, long lastTimestamp){
    	hmLastTimestamp.put(tableKey, lastTimestamp);
    }
    
//    private long tilNextMillis(long lastTimestamp){
//        long timestamp = System.currentTimeMillis();
//        while (timestamp <= lastTimestamp) {
//            timestamp = System.currentTimeMillis();
//        }
//        return timestamp;
//    }
    private long tilNextMinute(long lastTimestamp){
        long timestampInMilliSec = System.currentTimeMillis();
        long timestamp = timestampInMilliSec/1000/60 ;
        if (timestamp > lastTimestamp){
            return timestamp;
        }else{
            while (timestamp <= lastTimestamp) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    //do nothing
                }
                timestampInMilliSec = System.currentTimeMillis();
                timestamp = timestampInMilliSec/1000/60 ;
            }
            return timestamp;
        }
    }


    private long getDatacenterId(){
    	return 111;//TODO
    }
    
//    public synchronized long generateId(Object domainPojo){
//    	return generateId(domainPojo.getClass().getSimpleName());
//    }

    private synchronized long generateId(@SuppressWarnings("rawtypes") Class objClass){
    	return generateId(objClass.getSimpleName());
    }

    public synchronized long generateId()  {
        return generateId("default");//不用担心生成的id不够用的问题，没必要分table了，这样是id超全局唯一，即任何一条记录的id都不同
    }
    
    private synchronized long generateId(String tableKey)  {
        long timestampInMilliSec = System.currentTimeMillis();
        long timestamp = timestampInMilliSec/1000/60 ;
        long lastTimestamp = getLastTimestamp(tableKey);
        if(timestamp<lastTimestamp){
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id for "+ (lastTimestamp - timestamp) +" milliseconds."+tableKey);
        }
        
        long sequence = getSequence(tableKey);
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) % sequenceMax;
            if (sequence == 0) {
                timestamp = tilNextMinute(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        setSequence(tableKey,sequence);
        
        lastTimestamp = timestamp;
        setLastTimestamp(tableKey,lastTimestamp);
        long datacenterId = getDatacenterId();
        long id = ((timestamp - twepoch) << timestampLeftShift) |
                  (datacenterId << datacenterIdShift) |
                  sequence;
//        utilService.log(logger, "in generateId, tableKey="+tableKey+", sequence="+sequence+", lastTimestamp="+lastTimestamp);
        return id;
    }

    
}
