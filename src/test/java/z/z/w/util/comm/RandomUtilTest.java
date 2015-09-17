package z.z.w.util.comm ;

import org.junit.Test ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.util.comm.RandomUtilTest.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年9月17日 下午4:24:18 
 *   LastChange: 2015年9月17日 下午4:24:18 
 *      History:
 * </pre>
 **************************************************************************/
public class RandomUtilTest
{
	
	/**
	 * Test method for {@link z.z.w.util.comm.RandomUtil#generateString(int)}.
	 */
	@Test
	public void testGenerate()
	{
		System.out.println( "返回一个定长的随机字符串(只包含大小写字母、数字):" + RandomUtil.INSTANCE.generateString( 10 ) ) ;
		System.out.println( "返回一个定长的随机纯字母字符串(只包含大小写字母):" + RandomUtil.INSTANCE.generateMixString( 10 ) ) ;
		System.out.println( "返回一个定长的随机纯大写字母字符串(只包含大小写字母):" + RandomUtil.INSTANCE.generateLowerString( 10 ) ) ;
		System.out.println( "返回一个定长的随机纯小写字母字符串(只包含大小写字母):" + RandomUtil.INSTANCE.generateUpperString( 10 ) ) ;
		System.out.println( "生成一个定长的纯0字符串:" + RandomUtil.INSTANCE.generateZeroString( 10 ) ) ;
		System.out.println( "根据数字生成一个定长的字符串，长度不够前面补0:" + RandomUtil.INSTANCE.toFixdLengthString( 123, 10 ) ) ;
		int[] in = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 } ;
		System.out.println( "每次生成的len位数都不相同:" + RandomUtil.INSTANCE.getNotSimple( in, 3 ) ) ;
	}
	
}
