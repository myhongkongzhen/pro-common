package z.z.w.test.demo.zy ;

import java.security.MessageDigest ;
import java.security.NoSuchAlgorithmException ;

/**************************************************************************
 * <pre>
 *     FileName: com.zy.demo.MD5Test.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年7月30日 下午3:45:14 
 *   LastChange: 2015年7月30日 下午3:45:14 
 *      History:
 * </pre>
 **************************************************************************/
public class MD5Test
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
	
	/**
	 * Create by : 2015年7月30日 下午3:45:14
	 * 
	 * @param args
	 */
	public static void main( String[] args )
	{
		String source = "This is md5 test." ;
		System.out.println( "Source : " + source ) ;
		String sign = md5Hex( source ) ;
		System.out.println( "Generate sign : " + sign ) ;
	}
	
	public static byte[] md5( byte[] data )
	{
		return getDigest().digest( data ) ;
	}
	
	public static byte[] md5( String data )
	{
		byte[] b = md5( data.getBytes() ) ;
		System.out.println( "{byte[]}=md5(data)==>" + b ) ;
		for ( byte y : b )
			System.out.print( y + ";" ) ;
		System.out.println( "" ) ;
		return b ;
	}
	
	private static String md5Hex( String data )
	{
		return toHexString( md5( data ) ) ;
	}
	
	public static String toHexString( byte[] b )
	{
		StringBuffer sb = new StringBuffer() ;
		for ( int i = 0 ; i < b.length ; i++ )
		{
			System.out.print( b[ i ] + "   " ) ;
			sb.append( HEX_CHARS.charAt( ( b[ i ] >>> 4 ) & 0x0F ) ) ;
			sb.append( HEX_CHARS.charAt( b[ i ] & 0x0F ) ) ;
		}
		System.out.println( "" ) ;
		return sb.toString() ;
	}
}
