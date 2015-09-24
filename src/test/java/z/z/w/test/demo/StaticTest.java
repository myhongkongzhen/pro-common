package z.z.w.test.demo ;

import z.z.w.test.demo.server.Manager ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.StaticTest.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年8月6日 下午5:09:48 
 *   LastChange: 2015年8月6日 下午5:09:48 
 *      History:
 * </pre>
 **************************************************************************/
public class StaticTest
{
	
	/**
	 * Create by : 2015年8月6日 下午5:09:48
	 * 
	 * @param args
	 */
	public static void main( String[] args )
	{
		for ( int i = 0 ; i < 10 ; i++ )
			try
			{
				Thread.sleep( ( long ) ( Math.random() * 1000 ) ) ;
				new Thread( new TestRunner( i ) ).start() ;
			}
			catch ( InterruptedException e )
			{
				e.printStackTrace() ;
			}
	}
}

class TestRunner implements Runnable
{
	
	private int	i	= 0 ;
	
//	static
//	{
//		System.out.println( "This is static block." );
//	}
	
	public TestRunner( int i )
	{
		super() ;
		this.i = i ;
	}
	
	@Override
	public void run()
	{
		try
		{
			Manager.process( String.valueOf( i ) ) ;
			Thread.sleep( ( long ) ( Math.random() * 1000 ) ) ;
//			System.out.println( "====" + Math.random() * 1000 );
		}
		catch ( InterruptedException e )
		{
			e.printStackTrace() ;
		}
	}
	
}
