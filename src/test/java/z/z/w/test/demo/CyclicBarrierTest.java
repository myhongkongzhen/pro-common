package z.z.w.test.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.CyclicBarrierTest.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年7月31日 下午3:18:01 
 *   LastChange: 2015年7月31日 下午3:18:01 
 *      History:
 * </pre>
 **************************************************************************/
public class CyclicBarrierTest
{
	
	/**
	 * Create by : 2015年7月31日 下午3:18:01
	 * 
	 * @param args
	 */
	public static void main( String[ ] args )
	{
		int N = 4;
		CyclicBarrier barrier = new CyclicBarrier( N, new Runnable()
		{
			
			@Override
			public void run()
			{
				System.out.println( "线程" + Thread.currentThread().getName() + " |||所有線程都完成了。。。我開始執行任務咯。。。。。。。。。。。。。。。。。" );
				
			}
		} );
		
		for ( int i = 0; i < 4; i++ )
		{
			new Writer( barrier ).start();
		}
		
		try
		{
			Thread.sleep( 25000 );
		}
		catch ( InterruptedException e )
		{}
		
		System.out.println( "CyclicBarrier重用" );
		
		for ( int i = 0; i < 4; i++ )
		{
			new Writer( barrier ).start();
		}
	}
	
	static class Writer extends Thread
	{
		private CyclicBarrier	cyclicBarrier;
		
		public Writer( CyclicBarrier cyclicBarrier )
		{
			this.cyclicBarrier = cyclicBarrier;
		}
		
		@Override
		public void run()
		{
			System.out.println( "线程" + Thread.currentThread().getName() + "正在写入数据..." );
			try
			{
				Thread.sleep( ( long ) ( Math.random() * 10000 ) ); // 以睡眠来模拟写入数据操作
				System.out.println( "线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕" );
				
				cyclicBarrier.await();
			}
			catch ( InterruptedException e )
			{
				e.printStackTrace();
			}
			catch ( BrokenBarrierException e )
			{
				e.printStackTrace();
			}
			System.out.println( Thread.currentThread().getName() + "线程写入完毕..." );
		}
	}
}
