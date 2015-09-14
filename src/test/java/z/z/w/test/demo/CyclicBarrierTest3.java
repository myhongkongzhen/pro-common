package z.z.w.test.demo ;

import java.util.Random ;
import java.util.concurrent.Callable ;
import java.util.concurrent.CompletionService ;
import java.util.concurrent.CyclicBarrier ;
import java.util.concurrent.ExecutorCompletionService ;
import java.util.concurrent.ExecutorService ;
import java.util.concurrent.Executors ;
import java.util.concurrent.Future ;

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
public class CyclicBarrierTest3
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
		CyclicBarrier barrier = new CyclicBarrier( size, new MainWorker4( comp ) ) ;
		for ( int i = 0 ; i < size ; i++ )
		{
			comp.submit( new Worker3( barrier ) ) ;
		}
		
		getExec.shutdown() ;
		System.out.println( "FINISH========" + Thread.currentThread().getName() ) ;
	}
}

class MainWorker4 implements Runnable
{
	private CompletionService< Long >	comp	= null ;
	
//	private static ExecutorService		getExec	= Executors.newFixedThreadPool( 4 );
	
	public MainWorker4( CompletionService< Long > comp )
	{
		super() ;
		this.comp = comp ;
	}
	
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
//			Thread.sleep( ll * 10 );
			
			for ( int i = 0 ; i < 4 ; i++ )
			{
				try
				{
					System.out.println( "--------------" ) ;
					final Future< Long > future = comp.poll() ;
					System.out.println( "=======FUTURE--->" + future ) ;
//					getExec.submit( new Callable< Long >()
//					{
//						@Override
//						public Long call() throws Exception
//						{
//							Thread.sleep( ( long ) ( Math.random() * 10000 ) );
//					System.out.println( future.get() + "========" + Thread.currentThread().getName() );
//							return future.get();
//						}
//						
//					} );
				}
				catch ( Exception e )
				{
					e.printStackTrace() ;
				}
			}
		}
		finally
		{
			System.out.println( "===Doing sth use :" + ll + Thread.currentThread().getName() ) ;
		}
	}
}

class Worker3 implements Callable< Long >
{
	private CyclicBarrier	cyclicBarrier ;
	
	public Worker3( CyclicBarrier cyclicBarrier )
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
