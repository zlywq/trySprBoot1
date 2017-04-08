package g1.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import g1.domain.BbsPost;
import g1.ibatisMapper.BbsPostMapper;
import g1.util.MyBaseException;

@Service
public class CheckTranService {

	

	@Autowired
	IdGeneratorLikeSnowflake idGeneratorLikeSnowflake;
	

		
	@Autowired
	private BbsPostService bbsPostService;
	
	
	
	@Autowired
	UtilService utilService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public void checkTran(){
		try{
			bbsPostService.checkTran1noTran();
		}catch(MyBaseException ex){
			Map<String,Object> mpData = (Map<String,Object>)ex.getData();
			long id1 = (Long)mpData.get("id1");
			long id2 = (Long)mpData.get("id2");
			BbsPost post1 = bbsPostService.getById(id1);
			BbsPost post2 = bbsPostService.getById(id2);
			if (post1 == null || post2 == null){
				throw new RuntimeException("checkTran1noTran, insert 2 should succeed but not");
			}
		}
		
		try{
			bbsPostService.checkTran1haveTran();
		}catch(MyBaseException ex){
			Map<String,Object> mpData = (Map<String,Object>)ex.getData();
			long id1 = (Long)mpData.get("id1");
			long id2 = (Long)mpData.get("id2");
			BbsPost post1 = bbsPostService.getById(id1);
			BbsPost post2 = bbsPostService.getById(id2);
			if (post1 != null || post2 != null){
				throw new RuntimeException("checkTran1haveTran, insert 2 should fail but not");
			}
		}
		
	}
	
	
	
	
	
	
}
