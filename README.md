�� note.txt ������ȫ��ͬ�������� note.txt Ϊ׼��
<br/>��Ϊ�������������֧��markdown��ʽ����ҳ�ϵ��Ű�ͱȽ��ң���һ��ͬ�����¡�

<br/>��������Ǵ� https://github.com/zlywq/springdemo ������spring boot��Ŀ���Ǵﵽ���Ų�����ʵ�á�
<br/>�ؼ���: spring boot 1.5.2 + spring mvc + spring security + mybatis + session in redis , maven
<br/>����������spring-tool-suite-3.8.3.RELEASE-e4.6.2-win32-x86_64, java1.8
<br/>Ŀǰ����δ��maven��ģ�����֯��ʽ������һ����Ŀ��

<br/>mybatis ʹ���� Druid ���ӳأ�����Ҫ�Ĳ������Զ���ģ�ͨ��@ConfigurationProperties���뵽 DruidProperties���档
<br/>&emsp;    ע���и� PostController.checkTran ��ר�ż������ġ���Ϊ������ǰ�ȿӣ���2��������������á�
<br/>&emsp;    ��������id�ǲ��õ�Snowflake�ļ򻯰档

<br/>spring mvc �̳���spring boot�Ĵ󲿷��Զ����á�ֻ�Ƕ�ContentNegotiatingViewResolver����һ���Զ��壬����ͨ������url����չ����������Ӧ��ʽ������(��.json��.xml)������������xml�д��Ľ���
<br/>&emsp;    ��Ȼ��Ҳ����ͨ��@ResponseBody������json��
<br/>spring security���������Զ���ģ�������ǰ��xml���á�
<br/>&emsp;    ����֧�ֵ�¼ʱ����json���ݡ�
<br/>web session�л���redis�洢�Ƚϼ򵥣������и�bug������session.timeout�ģ��Ѿ����ˡ�bug��Ե��������δ��spring boot���ϣ��Ӷ��޷�����spring boot�е�properties��
<br/>spring boot����tomcat������Ҳ������https��http��
<br/>&emsp;    Ŀǰ�и���������ҳ��¼ʱ��������http��ת��https��������ת����������Ϊhttps��session������httpʹ�á���������Ӱ��app��curl����Ϊ��������ʹ���Ǹ�sessionId�����������������ô���塣
<br/>�����spring mvc����Ҳ��error����һ�㴦��������ʾĬ��ҳ������ʾ������Ϣ��
<br/>����ʹ����Thymeleaf������jsp(jstl)����Ⱦweb��ͼ��
<br/>&emsp;    ������������ http://www.cnblogs.com/chry/p/5912870.html������ʹ��jsp(jstl)��
<br/>&emsp;    ����Thymeleafģ������ʾ���˶����Զ��庯���ĵ��á�

<br/>���⣬Ҳ�򵥼��˵�actuator�����Ե�¼��ҳ�鿴��