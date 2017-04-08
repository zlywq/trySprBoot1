package g1.cfg;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "tomcatext") 
public class TomcatExtProperties {

	
	private int     httpPort;
	
	

    public int getHttpPort() {
        return httpPort;
    }
    public void setHttpPort(int httpPort) {
        this.httpPort = httpPort;
    }

	
	
	
	
	
	
	
	
	
}
