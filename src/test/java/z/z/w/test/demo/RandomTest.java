package z.z.w.test.demo;

import java.util.Random;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.Random.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年8月3日 上午10:04:29 
 *   LastChange: 2015年8月3日 上午10:04:29 
 *      History:
 * </pre>
 **************************************************************************/
public class RandomTest
{
	
	/**
	 * Create by : 2015年8月3日 上午10:04:29
	 * 
	 * @param args
	 */
	public static void main( String[ ] args )
	{
		Random random = new Random( 100 );
//		for ( int i = 0; i < 10; i++ )
//		{
		System.out.println( Math.abs( random.nextInt() ) % 100 );
//		}
		
	}
	
}
