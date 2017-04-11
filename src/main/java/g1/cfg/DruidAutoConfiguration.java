package g1.cfg;

import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.support.ibatis.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import cmn2.util.Util1;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


//@Configuration
//@EnableConfigurationProperties({DruidProperties1.class, DruidProperties2.class})
////@ConditionalOnClass(DruidDataSource.class)
////@ConditionalOnProperty(prefix = "druid1", name = "url")
////@AutoconfigureOrder(Ordered.HIGHEST_PRECEDENCE)
////@AutoConfigureBefore({DataSourceAutoConfiguration.class,MyWebSecurityConfigurerAdapter.class})
////@AutoConfigureBefore(DataSourceAutoConfiguration.class)
////@AutoConfigureBefore(MyWebSecurityConfigurerAdapter.class)
//@MapperScan(basePackages = "g1.ibatisMapper")
public class DruidAutoConfiguration {
	
//	private final Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Autowired
//    private DruidProperties1 properties1;
//    
//    @Autowired
//    private DruidProperties2 properties2;
//
//    @Bean//(name="primaryDataSource") 
//    public DataSource primaryDataSource()  
//    {
//    	DruidProperties properties = properties1;
////        DruidDataSource dataSource = new DruidDataSource();
////        dataSource.setDriverClassName(properties.getDriverClass());
////        dataSource.setUrl(properties.getUrl());
////        dataSource.setUsername(properties.getUsername());
////        dataSource.setPassword(properties.getPassword());
////        if (properties.getInitialSize() > 0) {
////            dataSource.setInitialSize(properties.getInitialSize());
////        }
////        if (properties.getMinIdle() > 0) {
////            dataSource.setMinIdle(properties.getMinIdle());
////        }
////        if (properties.getMaxActive() > 0) {
////            dataSource.setMaxActive(properties.getMaxActive());
////        }
////        dataSource.setTestOnBorrow(properties.isTestOnBorrow());
////        try {
////            dataSource.init();
////        } catch (SQLException e) {
////            throw new RuntimeException(e);
////        }
////        return dataSource;
//        
//        
//        
//        Properties props = new Properties();
//        props.put("driverClassName", properties.getDriverClass());
//        props.put("url", properties.getUrl());
//        props.put("username", properties.getUsername());
//        props.put("password", properties.getPassword());
//        
//        try {
//        	return DruidDataSourceFactory.createDataSource(props);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        
//        
//    }
//    
//    @Bean//(name="secondaryDataSource")
//    public DataSource secondaryDataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(properties2.getDriverClass());
//        dataSource.setUrl(properties2.getUrl());
//        dataSource.setUsername(properties2.getUsername());
//        dataSource.setPassword(properties2.getPassword());
//        if (properties2.getInitialSize() > 0) {
//            dataSource.setInitialSize(properties2.getInitialSize());
//        }
//        if (properties2.getMinIdle() > 0) {
//            dataSource.setMinIdle(properties2.getMinIdle());
//        }
//        if (properties2.getMaxActive() > 0) {
//            dataSource.setMaxActive(properties2.getMaxActive());
//        }
//        dataSource.setTestOnBorrow(properties2.isTestOnBorrow());
//        try {
//            dataSource.init();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return dataSource;
//    }
//    
//    
//    @Bean
//    @Primary
//    public DynamicDataSource dataSource
//    ()
//	//(@Qualifier("primaryDataSource") DataSource primaryDataSource,@Qualifier("secondaryDataSource") DataSource secondaryDataSource)
//    {
//    	DataSource primaryDataSource = primaryDataSource() ;
//    	DataSource secondaryDataSource = secondaryDataSource() ;
//    	
//        Map<Object, Object> targetDataSources = new HashMap<>();
//        targetDataSources.put(DatabaseType.primary, primaryDataSource);
//        targetDataSources.put(DatabaseType.secondary, secondaryDataSource);
//
//        DynamicDataSource dataSource = new DynamicDataSource();
//        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
//        dataSource.setDefaultTargetDataSource(primaryDataSource);// 默认的datasource设置为primaryDataSource
//        return dataSource;
//    }
//    
//    
//    @Autowired
//    private Environment env;
//    /**
//     * http://www.cnblogs.com/java-zhao/p/5413845.html
//     * 根据数据源创建SqlSessionFactory
//     */
//    @Bean
//    public SqlSessionFactory sqlSessionFactory
//    //()
//    (DynamicDataSource ds) 
//    		throws Exception {
//    	//DynamicDataSource ds = dataSource();
//    	
//        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
//        fb.setDataSource(ds);// 指定数据源(这个必须有，否则报错)
//        
//        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
//        String mybatis_typeAliasesPackage = env.getProperty("mybatis.typeAliasesPackage"); //"g1.ibatisMapper"
//        logger.info("mybatis_typeAliasesPackage="+mybatis_typeAliasesPackage);
//        if (!Util1.isStringEmpty(mybatis_typeAliasesPackage)){
//        	fb.setTypeAliasesPackage(mybatis_typeAliasesPackage);// 指定基包
//        }
//        String mybatis_mapperLocations = env.getProperty("mybatis.mapperLocations");
//        logger.info("mybatis_mapperLocations="+mybatis_mapperLocations);
//        if (!Util1.isStringEmpty(mybatis_mapperLocations)){
//        	fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mybatis_mapperLocations));//
//        }
//        
//        return fb.getObject();
//    }
//
//    /**
//     * 配置事务管理器
//     */
//    @Bean
//    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//
////The dependencies of some of the beans in the application context form a cycle:
////
////   bbsPostService (field private g1.ibatisMapper.BbsPostMapper g1.service.BbsPostService.postMapper)
////      ↓
////   bbsPostMapper defined in file [D:\zly2\work\githubmy\trySprBoot1\target\classes\g1\ibatisMapper\BbsPostMapper.class]
////      ↓
////   sqlSessionFactory defined in class path resource [g1/cfg/DruidAutoConfiguration.class]
////┌─────┐
////|  dataSource defined in class path resource [g1/cfg/DruidAutoConfiguration.class]
////↑     ↓
////|  primaryDataSource defined in class path resource [g1/cfg/DruidAutoConfiguration.class]
////↑     ↓
////|  dataSourceInitializer
////└─────┘
////    
    
    
}
