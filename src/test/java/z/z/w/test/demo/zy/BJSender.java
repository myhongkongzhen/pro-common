package z.z.w.test.demo.zy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**************************************************************************
 * <pre>
 *     FileName: com.zy.demo.BJSender.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年7月27日 下午2:35:28 
 *   LastChange: 2015年7月27日 下午2:35:28 
 *      History:
 * </pre>
 **************************************************************************/
public class BJSender
{
	
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main( String[ ] args ) throws IOException
	{
		/**
		 * http://sms.weiyingjia.cn:8080/dog3/httpUTF8SMS.jsp?username=xxx&pwd=yyy&mobile=1392055xxxx&msg=验证码:xxx【阿里云】
		 * 返回样例 success:13691791254:1426763299800^success:13691791254:14267632998001
		 * 英文^号分割小段，英文：号分割内容。
		 * http://sms.weiyingjia.cn:8080/dog3/httpUTF8SMS.jsp?username=tjyk&pwd=tjyk&msg=This+is+a+test+of+the+sms%3B&mobile=
		 * 15098648522&gwid=2
		 */
		StringBuffer sb = new StringBuffer();
		
		String content = "李永刚先生,您好：您的注册码是2014-10-02【隆畅达科技】";
		sb.append( "http://sms.weiyingjia.cn:8080/dog3/httpUTF8SMS.jsp" );
		sb.append( "?username=" + "tjyk" );// 用户名
		sb.append( "&pwd=" + "y7u8r5e3" );// 密码
		sb.append( "&msg=" + URLEncoder.encode( content, "utf-8" ) );
		sb.append( "&mobile=15098648522" );// 手机号码
		sb.append( "&gwid=" + "2" );// 不动
		
		System.out.println( sb.toString() );
		
		sb = new StringBuffer();
		sb.append( "http://sms.weiyingjia.cn:8080/dog3/httpUTF8SMS.jsp?username=tjyk&pwd=y7u8r5e3&msg=This+is+a+test+of+the+sms%3B&mobile=15098648522&gwid=2" );
		String result = BJSender.getHttp( sb.toString() );
		System.out.println( result );
		
	}
	
	/**
	 * @param myurl
	 * @return
	 * @throws IOException
	 */
	private static String getHttp( String myurl ) throws IOException
	{
		URL url = new URL( myurl );
		HttpURLConnection connection = ( HttpURLConnection ) url.openConnection();
		connection.setRequestProperty( "user-agent", "mozilla/4.0 (compatible; msie 6.0; windows 2000)" );
		connection.setConnectTimeout( 15000 );
		connection.setReadTimeout( 30000 );
		connection.connect();
		System.out.println( "--debug myurl:" + myurl + ",length:" + connection.getContentLength() + ",status:" + connection.getResponseCode() );
		StringBuffer sb = new StringBuffer();
		if ( connection.getResponseCode() == 200 )
		{
			InputStream in = connection.getInputStream();
			BufferedReader breader = new BufferedReader( new InputStreamReader( in, "gbk" ) );
			String str = breader.readLine();
			while ( str != null )
			{
				sb.append( str );
				str = breader.readLine();
			}
			in.close();
		}
		connection.disconnect();
		connection = null;
		url = null;
		return sb.toString();
		
	}
	
}
