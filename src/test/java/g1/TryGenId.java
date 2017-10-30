package g1;


import g1.service.IdGeneratorLikeSnowflakeByMinuteTool;
import org.junit.Test;

public class TryGenId {



    @Test
    public void test1(){
        IdGeneratorLikeSnowflakeByMinuteTool tool = new IdGeneratorLikeSnowflakeByMinuteTool();
        System.out.println("IdGeneratorLikeSnowflakeByMinuteTool.sequenceMax="+IdGeneratorLikeSnowflakeByMinuteTool.sequenceMax+
                " IdGeneratorLikeSnowflakeByMinuteTool.sequenceBits="+IdGeneratorLikeSnowflakeByMinuteTool.sequenceBits);

        long n = 1000000*2000;
        long idPrev = 0;
        for(long i=0; i<n; i++){
            long id = tool.generateId();
            if (id <= idPrev){
                throw new RuntimeException("id <= idPrev, id="+id+" idPrev="+idPrev);
            }
            if (id % 2000000 == 0){
                System.out.println("id="+id+", i="+i);
            }
            idPrev = id;
        }

    }





}
