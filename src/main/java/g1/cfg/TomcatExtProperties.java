package g1.cfg;

import java.util.ArrayList;
import java.util.List;

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

	
    private List<String>     urlsNeedSecurity = new ArrayList<>();
	
    public List<String> getUrlsNeedSecurity() {
        return urlsNeedSecurity;
    }
    public void setHttpPort(List<String> urlsNeedSecurity) {
        this.urlsNeedSecurity = urlsNeedSecurity;
    }
	
	
	
	
	
	
	
}
