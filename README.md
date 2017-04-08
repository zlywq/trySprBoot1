与 note.txt 内容完全相同，并且以 note.txt 为准。
<br/>因为这里如果不特意支持markdown格式，网页上的排版就比较乱，不一定同步更新。

<br/>这个例子是从 https://github.com/zlywq/springdemo 升级到spring boot。目标是达到入门并可以实用。
<br/>关键词: spring boot 1.5.2 + spring mvc + spring security + mybatis + session in redis , maven
<br/>开发环境是spring-tool-suite-3.8.3.RELEASE-e4.6.2-win32-x86_64, java1.8
<br/>目前暂且未按maven的模块的组织形式，都在一个项目中

<br/>mybatis 使用了 Druid 连接池，其需要的参数是自定义的，通过@ConfigurationProperties读入到 DruidProperties里面。
<br/>&emsp;    注意有个 PostController.checkTran 是专门检查事务的。因为根据以前踩坑，有2种情况事务不起作用。
<br/>&emsp;    其中生成id是采用的Snowflake的简化版。

<br/>spring mvc 继承了spring boot的大部分自动设置。只是对ContentNegotiatingViewResolver做了一点自定义，加了通过区分url的扩展名来返回相应格式的数据(如.json和.xml)。不过，返回xml尚待改进。
<br/>&emsp;    当然，也可以通过@ResponseBody来返回json。
<br/>spring security的配置是自定义的，根据以前的xml配置。
<br/>&emsp;    其中支持登录时返回json数据。
<br/>web session切换到redis存储比较简单，但是有个bug，关于session.timeout的，已经改了。bug的缘起是由于未经spring boot整合，从而无法利用spring boot中的properties。
<br/>spring boot内置tomcat，这里也配置了https和http。
<br/>&emsp;    目前有个问题是网页登录时，设置了http跳转到https，但是跳转不回来，因为https的session不能由http使用。不过，不影响app和curl，因为可以自行使用那个sessionId，而不是像浏览器那么死板。
<br/>这里的spring mvc里面也对error做了一点处理，避免显示默认页，以显示更多信息。
<br/>本例使用了Thymeleaf而不是jsp(jstl)来渲染web视图。
<br/>&emsp;    根据网上文章 http://www.cnblogs.com/chry/p/5912870.html，放弃使用jsp(jstl)。
<br/>&emsp;    其中Thymeleaf模版里面示例了对于自定义函数的调用。

<br/>另外，也简单加了点actuator，可以登录首页查看。