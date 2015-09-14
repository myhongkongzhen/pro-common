package z.z.w.test.demo ;

import java.util.Random ;
import java.util.concurrent.Callable ;
import java.util.concurrent.CompletionService ;
import java.util.concurrent.CyclicBarrier ;
import java.util.concurrent.ExecutorCompletionService ;
import java.util.concurrent.ExecutorService ;
import java.util.concurrent.Executors ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.CyclicBarrierTest2.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年7月31日 下午3:40:51 
 *   LastChange: 2015年7月31日 下午3:40:51 
 *      History:
 * </pre>
 **************************************************************************/
public class CyclicBarrierTest2
{
//	private static ExecutorService	getExec	= new ThreadPoolExecutor( 2, 6, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue< Runnable >() );
	public static final int					size	= 4 ;
	private static ExecutorService			getExec	= Executors.newFixedThreadPool( size ) ;
	public static CompletionService< Long >	comp	= new ExecutorCompletionService< Long >( getExec ) ;
	
	/**
	 * Create by : 2015年7月31日 下午3:40:51
	 * 
	 * @param args
	 */
	public static void main( String[] args )
	{
		System.out.println( "Start========" + Thread.currentThread().getName() ) ;
		CyclicBarrier barrier = new CyclicBarrier( size, new MainWorker() ) ;
		for ( int i = 0 ; i < size ; i++ )
		{
			comp.submit( new Worker( barrier ) ) ;
		}
		
//		for ( int i = 0; i < size; i++ )
//		{
//			try
//			{
//				final Future< Long > future = comp.take();
//				getExec.submit( new Callable< Long >()
//				{
//					@Override
//					public Long call() throws Exception
//					{
////						Thread.sleep( ( long ) ( Math.random() * 10000 ) );
//						System.out.println( future.get() + "========" + Thread.currentThread().getName() );
//						return future.get();
//					}
//					
//				} );
//			}
//			catch ( InterruptedException e )
//			{
//				e.printStackTrace();
//			}
//		}
		
		getExec.shutdown() ;
		System.out.println( "FINISH========" + Thread.currentThread().getName() ) ;
	}
}

class MainWorker implements Runnable
{
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		System.out.println( "All the working has been completed.......then I am going to do sth...." + Thread.currentThread().getName() ) ;
		long ll = -4000 ;
		try
		{
			ll = Math.abs( new Random( 100 ).nextInt() ) % 100 ;
			Thread.sleep( ll * 10 ) ;
		}
		catch ( InterruptedException e )
		{}
		finally
		{
			System.out.println( "===Doing sth use :" + ll + Thread.currentThread().getName() ) ;
		}
	}
}

class Worker implements Callable< Long >
{
	private CyclicBarrier	cyclicBarrier ;
	
	public Worker( CyclicBarrier cyclicBarrier )
	{
		super() ;
		this.cyclicBarrier = cyclicBarrier ;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Long call() throws Exception
	{
		try
		{
			System.out.println( Thread.currentThread().getName() + " will do the work!!!" ) ;
			Thread.sleep( ( long ) ( Math.random() * 10000 ) ) ;
			System.out.println( Thread.currentThread().getName() + " complete the work!!!" ) ;
			cyclicBarrier.await() ;
			return ( long ) ( Math.random() * 10000 ) ;
		}
		catch ( Exception e )
		{
			e.printStackTrace() ;
		}
		return null ;
		
	}
	
}
