package g1.service;


import cmn2.util.ErrCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;



@Scope("singleton")
@Service
public class IdGeneratorLikeSnowflakeByMinute {
	

	@Autowired
	UtilService utilService;
	
	//private final Logger logger = LoggerFactory.getLogger(getClass());


    IdGeneratorLikeSnowflakeByMinuteTool IdGeneratorLikeSnowflakeByMinuteToolObj = new IdGeneratorLikeSnowflakeByMinuteTool();




    public synchronized long generateId()  {
        return IdGeneratorLikeSnowflakeByMinuteToolObj.generateId();
    }

    
}
