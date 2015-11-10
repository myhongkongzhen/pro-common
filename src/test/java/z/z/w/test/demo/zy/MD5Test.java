package z.z.w.test.demo.zy;

import z.z.w.util.comm.MD5FANDXUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
	private static final String HEX_CHARS = "0123456789abcdef";

	public static MessageDigest getDigest()
	{
		try
		{
			return MessageDigest.getInstance( "MD5" );
		}
		catch ( NoSuchAlgorithmException e )
		{
			throw new RuntimeException( e );
		}
	}

	/**
	 * Create by : 2015年7月30日 下午3:45:14
	 *
	 * @param args
	 */
	public static void main( String[] args )
	{
//		String dateTime = "2015-11-06 14:43:44";//DateFormatUtils
//		.format( new Date(), DateFormatUtils.ISO_DATE_FORMAT.getPattern() + " " + DateFormatUtils.ISO_TIME_NO_T_FORMAT.getPattern() );
//		System.out.println( dateTime );
//		String source = "10040001" + dateTime + "1000" + "您好：您的注册码是" + ( ( long ) ( Math.random() * 10000 ) ) + "【技AHDX】" +
//						"15098648522BhpetXlwaldEMDKsoGFaEngKnqhJUXvU";

		String source
				= "Captcha=4523&Content=您好：您的注册码是22222【验证码】www.google.com【技FX】&Product=智验&Sendto=15098648522&Serialno=0240cb931b1b47419d00be19e3c2f122&TplId=0&UserID=32^7f9747265610f511202efcfbb7f81445";
		System.out.println( "Source : " + source );
		String sign = md5Hex( source );
		System.out.println( "Generate sign : " + sign );

		sign = MD5Util.md5Hex( source );
		System.out.println( "MD5Util sign : " + sign );

		sign = MD5FANDXUtil.calcMD5( source );
		System.out.println( "MD5FANDXUtil sign : " + sign );

	}

	public static byte[] md5( byte[] data )
	{
		return getDigest().digest( data );
	}

	public static byte[] md5( String data )
	{
		byte[] b = md5( data.getBytes() );
		System.out.println( "{byte[]}=md5(data)==>" + b );
		for ( byte y : b )
			System.out.print( y + ";" );
		System.out.println( "" );
		return b;
	}

	private static String md5Hex( String data )
	{
		return toHexString( md5( data ) );
	}

	public static String toHexString( byte[] b )
	{
		StringBuffer sb = new StringBuffer();
		for ( int i = 0 ; i < b.length ; i++ )
		{
			System.out.print( b[ i ] + "   " );
			sb.append( HEX_CHARS.charAt( ( b[ i ] >>> 4 ) & 0x0F ) );
			sb.append( HEX_CHARS.charAt( b[ i ] & 0x0F ) );
		}
		System.out.println( "" );
		return sb.toString();
	}
}
