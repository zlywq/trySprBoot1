package g1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@EnableWebMvc
@SpringBootApplication
@MapperScan(basePackages = "g1.ibatisMapper")
public class TrySprBoot1Application {

	public static void main(String[] args) {
		SpringApplication.run(TrySprBoot1Application.class, args);
	}
}
