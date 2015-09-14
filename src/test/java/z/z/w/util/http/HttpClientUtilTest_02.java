package z.z.w.util.http ;

import java.util.HashMap ;
import java.util.Map ;
import java.util.UUID ;

import org.apache.commons.lang3.StringUtils ;
import org.junit.Ignore ;
import org.junit.Test ;
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.util.http.HttpClientUtilTest_02.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年9月14日 上午11:10:09 
 *   LastChange: 2015年9月14日 上午11:10:09 
 *      History:
 * </pre>
 **************************************************************************/
public class HttpClientUtilTest_02
{
	private static Logger	logger	= LoggerFactory.getLogger( HttpClientUtilTest_02.class ) ;
	
	@Test
	@Ignore
	public void testZS_P2P_CHANNEL()
	{
		String url = "http://120.25.231.14:9999/sms.aspx" ;
		try
		{
			logger.info( "-->{}", url ) ;
			Map< String, String > param = new HashMap< String, String >() ;
			
			/**
			 * data.append( "?action=send" ) ;
			 * data.append( "&userid=602" ) ;
			 * data.append( "&account=szzykj" ) ;
			 * data.append( "&password=zhiyan20150724" ) ;
			 * data.append( "&mobile=15098648522" ) ;
			 * data.append( "&content=" + "您好：您的注册码是" + ( ( long ) ( Math.random() * 10000 ) ) + "【验证码】【技ZSP2P】" ) ;
			 * data.append( "&sendTime=&taskName=智验科技" ) ;
			 * data.append( "&checkcontent=0" ) ;
			 * data.append( "&mobilenumber=1" ) ;
			 * data.append( "&countnumber=0" ) ;
			 * data.append( "&telephonenumber=0" ) ;
			 */
			param.put( "action", "send" ) ;
			param.put( "userid", "602" ) ;
			param.put( "account", "szzykj" ) ;
			param.put( "password", "zhiyan20150724" ) ;
			param.put( "mobile", "15098648522" ) ;
			param.put( "content", "验证码7662，   欢迎使用3G产品！ 【网络ZSP2P2】" ) ;
			param.put( "sendTime", "" ) ;
			param.put( "taskName", "智验科技" ) ;
			param.put( "checkcontent", "0" ) ;
			param.put( "mobilenumber", "1" ) ;
			param.put( "telephonenumber", "0" ) ;
			
			logger.info( "-->{}", HttpClientUtil_Test.convertUnicode( HttpClientUtil.httpPost( url, param, null ) ) ) ; ;
		}
		catch ( Exception e )
		{
			logger.error( "HttpClientUtil error : [{}].", e.getMessage(), e ) ;
		}
	}
	
	@Test
	@Ignore
	public void testTJ_COMM_CHANNE()
	{
		// TODO -- 2015-9-1 16:23:52 天津三網測試發送xml文件
		String url = "http://211.137.171.46:9191/adc_posthandler_template" ;// + sendSmsContentTJSW() ;
		try
		{
			logger.info( "-->{}", HttpClientUtil.httpPost( url ) ) ;
		}
		catch ( Exception e )
		{
			logger.error( "HttpClientUtil error : [{}].", e.getMessage(), e ) ;
		}
	}
	
	@Test
	@Ignore
	public void testNativeNsqApi()
	{
		String msg = "這是一個使用原生NSQ API做的發送測試" ;
		String url = "http://101.200.188.159:4151/pub" ;
		try
		{
			logger.info( "-->{}", ( HttpClientUtil.httpPost( url, msg ) ) ) ;
		}
		catch ( Exception e )
		{
			logger.error( "HttpClientUtil error : [{}].", e.getMessage(), e ) ;
		}
	}
	
	@Test
	@Ignore
	public void testHZ_YD_CHANNEL()
	{
		// TODO -- 2015-9-1 16:23:52 天津三网通杭州移动单网(天津星际池州)測試發送xml文件
		String url = "http://211.137.171.46:9191/adc_posthandler_new" ;
		try
		{
			logger.info( "-->{}", url ) ;
			Map< String, String > param = new HashMap< String, String >() ;
			
			/**
			 * data.append( "?action=send" ) ;
			 * data.append( "&userid=602" ) ;
			 * data.append( "&account=szzykj" ) ;
			 * data.append( "&password=zhiyan20150724" ) ;
			 * data.append( "&mobile=15098648522" ) ;
			 * data.append( "&content=" + "您好：您的注册码是" + ( ( long ) ( Math.random() * 10000 ) ) + "【验证码】【技ZSP2P】" ) ;
			 * data.append( "&sendTime=&taskName=智验科技" ) ;
			 * data.append( "&checkcontent=0" ) ;
			 * data.append( "&mobilenumber=1" ) ;
			 * data.append( "&countnumber=0" ) ;
			 * data.append( "&telephonenumber=0" ) ;
			 */
			
			param.put( "TplId", "0" ) ;
			param.put( "Product", "" ) ;
			param.put( "Captcha", "123" ) ;
			param.put( "Content", "您好：您的注册码是" + ( ( long ) ( Math.random() * 10000 ) ) + "【技DG】" ) ;
			param.put( "Sendto", "15098648522" ) ;
			param.put( "Serialno", StringUtils.substring( UUID.randomUUID().toString().replace( "-", "" ), 1, 20 ) ) ;
			param.put( "UserID", "32" ) ;
			param.put( "Sign", StringUtils.substring( UUID.randomUUID().toString().replace( "-", "" ), 1, 20 ) ) ;
			
			logger.info( "-->{}", HttpClientUtil_Test.convertUnicode( HttpClientUtil.httpPost( url, param, null ) ) ) ;
		}
		catch ( Exception e )
		{
			logger.error( "HttpClientUtil error : [{}].", e.getMessage(), e ) ;
		}
	}
	
	@Test
	@Ignore
	public void testFX_YD_CHANNEL()
	{
		String url = "http://feixin.91yunma.cn/openapi/applyfeixinsms.html" ;
		try
		{
			logger.info( "-->{}", url ) ;
			Map< String, String > param = new HashMap< String, String >() ;
			
			param.put( "TplId", "0" ) ;
			param.put( "Product", "" ) ;
			param.put( "Captcha", "123" ) ;
			param.put( "Content", "您好：您的注册码是" + ( ( long ) ( Math.random() * 10000 ) ) + "【技DG】" ) ;
			param.put( "Sendto", "15098648522" ) ;
			param.put( "Serialno", StringUtils.substring( UUID.randomUUID().toString().replace( "-", "" ), 1, 20 ) ) ;
			param.put( "UserID", "32" ) ;
			param.put( "Sign", StringUtils.substring( UUID.randomUUID().toString().replace( "-", "" ), 1, 20 ) ) ;
			
			logger.info( "-->{}", HttpClientUtil_Test.convertUnicode( HttpClientUtil.httpPost( url, param, null ) ) ) ; ;
		}
		catch ( Exception e )
		{
			logger.error( "HttpClientUtil error : [{}].", e.getMessage(), e ) ;
		}
	}
	
	@Test
	@Ignore
	public void testDG_UNICOM_CHANNEL()
	{
		String url = "http://gd.ums86.com:8893/sms/Api/Send.do" ;
		try
		{
			logger.info( "-->{}", url ) ;
			Map< String, String > param = new HashMap< String, String >() ;
			
			/**
			 * GBK編碼
			 * data.append( "?SpCode=221550" ) ;
			 * data.append( "&LoginName=admin3" ) ;
			 * data.append( "&Password=zhiyan213" ) ;
			 * data.append( "&UserNumber=15098648522" ) ;
			 * data.append( "&MessageContent=您好：您的注册码是" + ( ( long ) ( Math.random() * 10000 ) ) + "【技DG】" ) ;
			 * data.append( "&SerialNumber=" ).append( StringUtils.substring( UUID.randomUUID().toString().replace( "-", "" ),
			 * 1, 20 ) ) ;
			 * data.append( "&ScheduleTime=" ) ;
			 * data.append( "&ExtendAccessNum=" ) ;
			 * data.append( "&f=1" ) ;
			 */
			param.put( "SpCode", "221550" ) ;
			param.put( "LoginName", "admin3" ) ;
			param.put( "Password", "zhiyan213" ) ;
			param.put( "ScheduleTime", "" ) ;
			param.put( "ExtendAccessNum", "" ) ;
			param.put( "f", "1" ) ;
			param.put( "UserNumber", "15098648522" ) ;
			param.put( "MessageContent", "您好：您的注册码是" + ( ( long ) ( Math.random() * 10000 ) ) + "【技DG】" ) ;
			param.put( "SerialNumber", StringUtils.substring( UUID.randomUUID().toString().replace( "-", "" ), 1, 20 ) ) ;
			
			logger.info( "-->{}", HttpClientUtil.httpPost( url, param, null ) ) ; ;
		}
		catch ( Exception e )
		{
			logger.error( "HttpClientUtil error : [{}].", e.getMessage(), e ) ;
		}
	}
	
	@Test
	@Ignore
	public void testBJ_JTD_YD_CHANNEL()
	{
		String url = "http://sms.weiyingjia.cn:8080/dog3/httpUTF8SMS2Key.jsp" ;
		try
		{
			logger.info( "-->{}", url ) ;
			Map< String, String > param = new HashMap< String, String >() ;
			
			/**
			 * username=tjyk&pwd=y7u8r5e3&msg=【預警：北京聚通达移动三网通道故障告警！！！】&mobile=15098648522&gwid=2&key="
			 * + StringUtils.substring( UUID.randomUUID()
			 * .toString()
			 * .replace( "-",
			 * "" ),
			 * 1,
			 * 20 ) ;
			 */
			param.put( "username", "tjyk" ) ;
			param.put( "pwd", "y7u8r5e3" ) ;
			param.put( "msg", "【預警：北京聚通达移动三网通道故障告警！！！】" ) ;
			param.put( "mobile", "15098648522" ) ;
			param.put( "gwid", "2" ) ;
			param.put( "key", StringUtils.substring( UUID.randomUUID().toString().replace( "-", "" ), 1, 20 ) ) ;
			
			logger.info( "-->{}", HttpClientUtil.httpPost( url, param, null ) ) ; ;
		}
		catch ( Exception e )
		{
			logger.error( "HttpClientUtil error : [{}].", e.getMessage(), e ) ;
		}
	}
	
	@Test
	@Ignore
	public void testBJ_CT_CHANNEL()
	{
		String url = "http://xtx.telhk.cn:8080/sms.aspx" ;
		try
		{
			logger.info( "-->{}", url ) ;
			Map< String, String > param = new HashMap< String, String >() ;
			
			/**
			 * data.append( "action=send" ) ;
			 * data.append( "&userid=5310" ) ;
			 * data.append( "&account=a10116" ) ;
			 * data.append( "&password=452136" ) ;
			 * data.append( "&mobile=15098648522" ) ;
			 * data.append( "&content=验证码7662，欢迎使用3G产品！ 【出众网络】" ) ;
			 * data.append( "&sendTime=&taskName=智验科技" ) ;
			 * data.append( "&checkcontent=0" ) ;
			 * data.append( "&mobilenumber=1" ) ;
			 * data.append( "&countnumber=0" ) ;
			 * data.append( "&telephonenumber=0" ) ;
			 */
			param.put( "action", "send" ) ;
			param.put( "userid", "5310" ) ;
			param.put( "account", "a10116" ) ;
			param.put( "password", "452136" ) ;
			param.put( "mobile", "15098648522" ) ;
			param.put( "content", "验证码7662，   欢迎使用3G产品！ 【出众网络】" ) ;
			param.put( "sendTime", "" ) ;
			param.put( "taskName", "智验科技" ) ;
			param.put( "checkcontent", "0" ) ;
			param.put( "mobilenumber", "1" ) ;
			param.put( "telephonenumber", "0" ) ;
			
			logger.info( "-->{}", HttpClientUtil.httpPost( url, param, null ) ) ; ;
		}
		catch ( Exception e )
		{
			logger.error( "HttpClientUtil error : [{}].", e.getMessage(), e ) ;
		}
	}
	
	@Test
	@Ignore
	public void testAH_UNICOM_CHANNEL()
	{
		String url = "http://ah.vnet.cn/SmsGW/SMS/SingleSMS" ;
		try
		{
			logger.info( "-->{}", url ) ;
			Map< String, String > param = new HashMap< String, String >() ;
			param.put( "json", sendSmsJson() ) ;
			
			logger.info( "-->{}", HttpClientUtil.httpPostParticular( url, param, null ) ) ; ;
		}
		catch ( Exception e )
		{
			logger.error( "HttpClientUtil error : [{}].", e.getMessage(), e ) ;
		}
	}
	
	/**
	 * Create by : 2015年9月14日 上午11:11:49
	 */
	private String sendSmsJson()
	{
		StringBuffer data = new StringBuffer() ;
		data.append( "{" ) ;
		data.append( "\"SingleSMSRequest\":{" ) ;
		data.append( "\"UserID\":\"10040001\", " ) ;
		data.append( "\"TPLID\":\"1000\", " ) ;
		data.append( "\"MSMSID\":\"100000022015061212310697\"," ) ;
		data.append( "\"StampTime\":\"2015-06-12 12:31:06\"," ) ;
		data.append( "\"SMSText\":\"您好 ： 您的注册码是" + ( ( long ) ( Math.random() * 10000 ) ) + "【技AHDX】\", " ) ;
		data.append( "\"Sign\":\"1a7dbaa9f9a2e1166f2d62cf87b6bb3b\"," ) ;
		data.append( "\"ObjMobile\":\"15098648522\"" ) ;
		data.append( "}" ) ;
		data.append( "}" ) ;
		return data.toString() ;
	}
	
}
