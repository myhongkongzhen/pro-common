package z.z.w.test.demo ;

import java.io.BufferedReader ;
import java.io.IOException ;
import java.io.InputStream ;
import java.io.InputStreamReader ;
import java.net.HttpURLConnection ;
import java.net.URL ;
import java.net.URLEncoder ;
import java.util.HashMap ;
import java.util.Map ;

import z.z.w.test.demo.zy.HttpClient ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.SendMessage.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年7月22日 下午7:09:27 
 *   LastChange: 2015年7月22日 下午7:09:27 
 *      History:
 * </pre>
 **************************************************************************/
public class SendMessage
{
	/**
	 * @param myurl
	 * @return
	 * @throws IOException
	 */
	private static String getHttp( String myurl ) throws IOException
	{
		URL url = new URL( myurl ) ;
		HttpURLConnection connection = ( HttpURLConnection ) url.openConnection() ;
		connection.setRequestProperty( "user-agent" , "mozilla/4.0 (compatible; msie 6.0; windows 2000)" ) ;
		connection.setConnectTimeout( 15000 ) ;
		connection.setReadTimeout( 30000 ) ;
		connection.connect() ;
		System.out.println( "--debug myurl:" + myurl + "    ,length:" + connection.getContentLength() + " , status:" + connection.getResponseCode() ) ;
		StringBuffer sb = new StringBuffer() ;
		if ( connection.getResponseCode() == 200 )
		{
			InputStream in = connection.getInputStream() ;
			BufferedReader breader = new BufferedReader( new InputStreamReader( in , "gbk" ) ) ;
			String str = breader.readLine() ;
			while ( str != null )
			{
				sb.append( str ) ;
				str = breader.readLine() ;
			}
			in.close() ;
		}
		connection.disconnect() ;
		connection = null ;
		url = null ;
		return sb.toString() ;
		
	}
	
	protected static String getReqParam()
	{
		/**
		 * {
		 * "mobile":"13333333333",
		 * "uid":”fdsfds48789fdslkljff8”
		 * "datetime":"2014-01-12 09:12:12",
		 * "status":"0",
		 * "desc":""
		 * }
		 * 内容：接受手机号, 用户接收时间, 接收状态, 接收失败原因，对应的发送短信的UID
		 */
		StringBuffer sb = new StringBuffer( "{" ) ;
		
		sb.append( "\"mobile\":\"13425648562\"," ) ;
		sb.append( "\"datetime\":\"2015-04-04 09:09:34\"," ) ;
		sb.append( "\"status\":\"1\"," ) ;
		sb.append( "\"desc\":\"\"," ) ;
		sb.append( "\"uid\":\"fjkdei974hweog-90348sdifd35wq;dcou0\"" ) ;
		sb.append( "}" ) ;
		
		return sb.toString() ;
	}
	
	public static void main( String[] args ) throws Exception
	{
		String content = "您的注册码是2015072219【智驗科技】" ;
		StringBuffer sb = new StringBuffer() ;
		sb.append( "http://sms.weiyingjia.cn:8080/dog3/httpUTF8SMS.jsp" ) ;
		sb.append( "http://sms.weiyingjia.cn:8080/dog3/httpUTF8Report.jsp" ) ;
		sb.append( "?username=" + "tjyk" ) ;// 用户名
		sb.append( "&pwd=" + "y7u8r5e3" ) ;// 密码
		sb.append( "&msg=" + URLEncoder.encode( content , "utf-8" ) ) ;
		sb.append( "&mobile=15098648522" ) ;// 手机号码
		sb.append( "&gwid=" + "2" ) ;// 不动
		
		sb.append( "https://rest.nexmo.com/sms/json?api_key=97fa83ab&api_secret=e08c0e99&from=NEXMO&to=8615098648522&text=Welcome+to+Nexmo" ) ;
		sb.append( "http://202.105.136.108:19998/zyccreport.do" ) ;
		sb.append( "?data=" ).append( getReqParam() ) ;
		String result = SendMessage.getHttp( sb.toString() ) ;
		System.out.println( result ) ;
		
		System.out.println( "==============================" ) ;
		Map< String, String > params = new HashMap< String, String >() ;
		/**
		 * private String mobile;
		 * private String datetime;
		 * private String status;
		 * private String desc;
		 * private String uid;
		 */
//		params.put( "mobile", "13427853654" );
//		params.put( "datetime", "2015-03-02 23:11:11" );
//		params.put( "status", "1" );
//		params.put( "desc", "" );
//		params.put( "uid", "vkele46n5763kduiz3jsnfsl" );
		
		params.put( "data" , getReqParam() ) ;
		sb = new StringBuffer() ;
		sb.append( "http://202.105.136.108:19998/zyccreport.do" ) ;
		
		HttpClient hc = new HttpClient( sb.toString() , 5000 , 5000 ) ;
		int status = hc.send( params , "UTF-8" ) ;
		System.out.println( status ) ;
		String rsp = hc.getResult() ;
		System.out.println( rsp ) ;
		
	}
}
