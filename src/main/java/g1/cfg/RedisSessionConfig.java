package g1.cfg;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.util.StringUtils;


/*

查看最终的session的timeout方法为”request.getSession().getMaxInactiveInterval()"  根据  SpringBoot中Session超时原理说明 2016-06-30 http://blog.csdn.net/gaodebao1/article/details/51789188 

spring boot + redis 实现session共享 2016-05-23 http://www.cnblogs.com/mengmeng89012/p/5519698.html
    但是 这样还有问题，redis没有利用server.session.timeout，那边的session的timeout固定是1800sec。解决办法如下，参见 http://stackoverflow.com/questions/37303967/enableredishttpsession-spring-boot-ignoring-server-session-timeout-on-applica
    另外，注意到在pom.xml中是加的如下代码。是没有经过spring boot整合的，所以才有上面的问题。
	<dependency>  
        	<groupId>org.springframework.session</groupId>  
        	<artifactId>spring-session-data-redis</artifactId>
        </dependency>



 */

@Configuration  
@EnableRedisHttpSession  
public class RedisSessionConfig {


	@Value("${server.session.timeout}")
	private int maxInactiveIntervalInSeconds;


	@Autowired
	private RedisOperationsSessionRepository sessionRepository;

	@PostConstruct
    private void afterPropertiesSet() {
		if (maxInactiveIntervalInSeconds > 0){
			sessionRepository.setDefaultMaxInactiveInterval(maxInactiveIntervalInSeconds);
		}
    }
	
	
	
	
	
	
	
	
}
