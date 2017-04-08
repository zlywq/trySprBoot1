package g1.cfg;



import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SocketUtils;



@Configuration
//@EnableConfigurationProperties(TomcatExtProperties.class)
//@ConditionalOnProperty(prefix = "tomcatext", name = "httpPort")
public class TomcatExtConfig {
	
	@Autowired
	private TomcatExtProperties tomcatExtProperties;
	
	@Bean
	public Integer port() {
		int port = tomcatExtProperties.getHttpPort();
		if (port == 0){
			port = SocketUtils.findAvailableTcpPort();
		}
		return port;
	}
	
	@Value("${server.port}")
	private int serverPortAsHttps;

	/*
目前还有问题，http跳转到https登录后，再访问http是不行的，没有session。
	 */
	
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory(){
			 @Override
	            protected void postProcessContext(Context context) {
	                SecurityConstraint constraint = new SecurityConstraint();
	                constraint.setUserConstraint("CONFIDENTIAL");
	                SecurityCollection collection = new SecurityCollection();
	                //collection.addPattern("/*");//ok
	                //collection.addPattern("/logreg/*");//ok
	                
	                //collection.addPattern("/logreg/login*");//fail
	                //collection.addPattern("/logreg/reg*");
	                
	                collection.addPattern("/logreg/login");//ok 目前是可以跳转到https
	                collection.addPattern("/logreg/reg");
	                
	                constraint.addCollection(collection);
	                context.addConstraint(constraint);
	            }
		};
		
		//ServerProperties s;
		
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setPort(port());
		connector.setScheme("http");
        connector.setSecure(false);
        connector.setRedirectPort(serverPortAsHttps);
        
		tomcat.addAdditionalTomcatConnectors(connector);
		return tomcat;
	}

}
