package g1.cfg;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;

import g1.sprsec.*;

/*

 */

@Configuration
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Bean
	MyUserDetailsService myUserDetailsService(){
		return new MyUserDetailsService(); 
	}
	
	@Bean
	MyAuthenticationProvider myAuthenticationProvider(){
		return new MyAuthenticationProvider(); 
	}
	
	@Bean
	MyAuthenticationSuccessHandler authSuccessHandler(){
		return new MyAuthenticationSuccessHandler(); 
	}
	@Bean
	MyAuthenticationFailureHandler authFailureHandler(){
		MyAuthenticationFailureHandler bean = new MyAuthenticationFailureHandler(); 
		bean.setDefaultFailureUrl("/logreg/login?error=true");
		return bean;
	}
	@Bean
	MyLogoutSuccessHandler myLogoutSuccessHandler(){
		MyLogoutSuccessHandler bean = new MyLogoutSuccessHandler(); 
		bean.setDefaultTargetUrl("/logreg/login");
		return bean;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService()); 
		auth.authenticationProvider(myAuthenticationProvider());
	}

//org.springframework.session.web.http.ExpiringSessionHttpSession<S extends ExpiringSession> implements HttpSession

	@Override
	protected void configure(HttpSecurity http) throws Exception {

			AuthenticationEntryPoint entryPoint = entryPoint();
			//原本在xml配置上的http上有个entryPoint，看来是对应这里。如<http use-expressions="false" entry-point-ref="AuthenticationEntryPoint1">
			http.exceptionHandling().authenticationEntryPoint(entryPoint);
			
			http.formLogin().loginPage("/logreg/login").failureUrl("/logreg/login?error=true")
				.failureHandler(authFailureHandler()).successHandler(authSuccessHandler())
				.permitAll();
			http.logout().logoutUrl("/logreg/logout")
				.logoutSuccessHandler(myLogoutSuccessHandler())
				.permitAll()
				.invalidateHttpSession(true);  
			http.authorizeRequests().antMatchers("/nologin/**","/tmptest/**","/logreg/login*","/logreg/reg*").permitAll();
			http.authorizeRequests().anyRequest().authenticated(); //anyRequest() 似乎等于 antMatchers("/**")
			
			
			
			//<remember-me data-source-ref="dataSource" token-validity-seconds="1209600" /> 其中的data-source-ref目前发现用不着了，或者可能被下面的代码替换了
			http.rememberMe().tokenValiditySeconds(1209600) //在网页上试验要注意勾选对应的checkbox。
			//.tokenRepository(persistentTokenRepository()) //TODO 检查应该需要，应该是对应 persistent_logins 表的
				.userDetailsService(myUserDetailsService());//...
			
			
			http.csrf().disable();//TODO 待研究调.json时如何支持
			
			//http.httpBasic();//目前看来用不着这个了。以前在xml配置有这项，没有拿掉它，原因不记得。
			

	}
	
	//AbstractAuthenticationProcessingFilter a;
	//ApplicationFilterChain.doFilter
	//FilterChainProxy doFilter
//	RememberMeAuthenticationFilter r;
//	SessionManagementFilter s;
	
//	@Bean//发现用不着 TODO 检查应该需要，应该是对应 persistent_logins 表的
//	public PersistentTokenRepository persistentTokenRepository() {
//		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
//		db.setDataSource(dataSource);
//		return db;
//	}
	

	

	private AuthenticationEntryPoint entryPoint() {
		MyAuthenticationEntryPoint MyAuthenticationEntryPoint1 = new MyAuthenticationEntryPoint("/logreg/login");
		return MyAuthenticationEntryPoint1;
		
	}

	
	
	
	
	
	
	
	
	
	
	
}
