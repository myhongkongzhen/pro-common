package z.z.w.test.demo.zy ;

import java.security.MessageDigest ;
import java.security.NoSuchAlgorithmException ;
import java.util.HashMap ;
import java.util.Map ;

public class SmsApi
{
	private static final String	HEX_CHARS	= "0123456789abcdef" ;
	
	public static MessageDigest getDigest()
	{
		try
		{
			return MessageDigest.getInstance( "MD5" ) ;
		}
		catch ( NoSuchAlgorithmException e )
		{
			throw new RuntimeException( e ) ;
		}
	}
	
	public static void main( String[] args ) throws Exception
	{
		for ( int i = 0 ; i < 2 ; i++ )
		{
			try
			{
				Thread.sleep( 1 ) ;
			}
			catch ( Exception e )
			{}
			
			new Thread( new Runnable()
			{
				
				@Override
				public void run()
				{
					try
					{
						
						spend() ;
					}
					catch ( Exception e )
					{
						e.printStackTrace() ;
					}
				}
			} ).start() ;
		}
	}
	
	public static byte[] md5( byte[] data )
	{
		return getDigest().digest( data ) ;
	}
	
	public static byte[] md5( String data )
	{
		return md5( data.getBytes() ) ;
	}
	
	public static String md5Hex( String data )
	{
		return toHexString( md5( data ) ) ;
	}
	
	/**
	 * Create by : 2015年8月5日 上午11:41:11
	 * 
	 * @throws Exception
	 */
	protected static void spend() throws Exception
	{
		// https://sms.zhiyan.net/sms/sms/single/03b02f314c284434aed7de3d540360c7/7hj812H9j1Q9/ADSF23ASDFQW
		// 时间戳
		long timestamp = System.currentTimeMillis() ;
		// 智验APPKEY
		String appKey = "eb1c8b631eae4e81ab897aa1d6534819" ;
		// 应用token值
		String token = "13Ckd436H1j7" ;
		// 应用绑定模板ID
		String templateId = "UASDFQUSJSUS" ;
		// 手机号
		String mobile = "13686802157" ;
		// 验证码
		String code = "0913" ;
		// MD5加密处理
		String sign = md5Hex( appKey + token + templateId + mobile + code + "" + timestamp ) ;
		
		// https://sms.zhiyan.net/sms/sms/single/61ee6f6831d746ed917e80befba155c4/A398F9MpBp0f/ADSF23A4DFAS
//		      http://localhost:8888/sms/sms/single/61ee6f6831d746ed917e80befba155c4/A398F9MpBp0f/ADSF23A4DFAS?timestamp=1437615728039&sign=3b4872b829e295998c652c2e739f4c23
		// -----------------------------------appkey---------------------------token--------templateid
		// -timestamp--------------sign--
		
		String url = "http://101.200.188.159:9091/sms/sms/single/" + appKey + "/" + token + "/" + templateId + "?timestamp=" + timestamp + "&sign=" + sign ;
//		String url = "http://101.200.188.159:9091/sms/sms/single/" + appKey + "/" + token + "/" + templateId + "?timestamp=" + timestamp + "&sign=" + sign;
		System.out.println( url ) ;
		Map< String, String > params = new HashMap< String, String >() ;
		Map< String, String > map = new HashMap< String, String >() ;
		map.put( "mobile" , mobile ) ;
		map.put( "param" , code ) ;
		map.put( "extend" , "" ) ;
//		params.put( "data", JSONObject.fromObject( map ).toString() ) ;
		System.out.println( "==>" + params ) ;
		
//				url = "https://rest.nexmo.com/sms/json?api_key=97fa83ab&api_secret=e08c0e99&from=NEXMO&to=8615098648522&text=Welcome+to+Nexmo";
		
		HttpClient hc = new HttpClient( url , 5000 , 5000 ) ;
		int status = hc.send( params , "UTF-8" ) ;
		System.out.println( status ) ;
		String rsp = hc.getResult() ;
		if ( status != 200 ) System.out.println( "【短信系统】请求响应码不为200,为:" + status + ",请求URL为：" + url ) ;
		else System.out.println( "---->" + rsp ) ;
		
	}
	
	public static String toHexString( byte[] b )
	{
		StringBuffer sb = new StringBuffer() ;
		for ( int i = 0 ; i < b.length ; i++ )
		{
			sb.append( HEX_CHARS.charAt( ( b[ i ] >>> 4 ) & 0x0F ) ) ;
			sb.append( HEX_CHARS.charAt( b[ i ] & 0x0F ) ) ;
		}
		return sb.toString() ;
	}
}
