package z.z.w.test.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.CountDownLatchTest.java
 *         Desc: 模拟了100米赛跑，10名选手已经准备就绪，只等裁判一声令下。当所有人都到达终点时，比赛结束。
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年7月31日 上午11:50:59 
 *   LastChange: 2015年7月31日 上午11:50:59 
 *      History:
 * </pre>
 **************************************************************************/
public class CountDownLatchTest
{
	
	public static void main( String[ ] args ) throws InterruptedException
	{
		// 开始的倒数锁
		final CountDownLatch begin = new CountDownLatch( 1 );
		
		// 结束的倒数锁
		final CountDownLatch end = new CountDownLatch( 10 );
		
		// 十名选手
		final ExecutorService exec = Executors.newFixedThreadPool( 10 );
		
		for ( int index = 0; index < 10; index++ )
		{
			final int NO = index + 1;
			Runnable run = new Runnable()
			{
				public void run()
				{
					try
					{
						// 如果当前计数为零，则此方法立即返回。
						// 等待
						begin.await();
						Thread.sleep( ( long ) ( Math.random() * 10000 ) );
						System.out.println( "No." + NO + " arrived" );
					}
					catch ( InterruptedException e )
					{}
					finally
					{
						// 每个选手到达终点时，end就减一
						end.countDown();
					}
				}
			};
			exec.submit( run );
		}
		System.out.println( "Game Start" );
		// begin减一，开始游戏
		begin.countDown();
		// 等待end变为0，即所有选手到达终点
		end.await();
		System.out.println( "Game Over" );
		exec.shutdown();
	}
}
