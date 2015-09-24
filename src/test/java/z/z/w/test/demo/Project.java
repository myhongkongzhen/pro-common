package z.z.w.test.demo ;

import java.util.Random ;
import java.util.concurrent.CountDownLatch ;
import java.util.concurrent.ExecutorService ;
import java.util.concurrent.Executors ;
import java.util.concurrent.TimeUnit ;

class Controller implements Runnable
{
	private CountDownLatch	latch ;
	
	public Controller( CountDownLatch latch )
	{
		super() ;
		this.latch = latch ;
	}
	
	@Override
	public void run()
	{
		try
		{
			latch.await() ;
			System.out.print( "所有模块都完成，任务完成  " ) ;
			System.out.println( "Controller currentThread==>" + Thread.currentThread().getName() ) ;
		}
		catch ( InterruptedException e )
		{
			e.printStackTrace() ;
		}
		
	}
	
}

class Module implements Runnable
{
	private CountDownLatch	latch ;
	private String			moduleName ;
	private int				time ;			// 用时
											
	public Module( CountDownLatch latch , String moduleName , int time )
	{
		super() ;
		this.latch = latch ;
		this.moduleName = moduleName ;
		this.time = time ;
	}
	
	@Override
	public void run()
	{
		try
		{
			work() ;
			latch.countDown() ;
		}
		catch ( InterruptedException e )
		{
			e.printStackTrace() ;
		}
		
	}
	
	private void work() throws InterruptedException
	{
		TimeUnit.MILLISECONDS.sleep( time ) ;
		System.out.print( moduleName + " 完成，耗时:" + time ) ;
		System.out.println( "   Module currentThread==>" + Thread.currentThread().getName() ) ;
	}
}

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.Project.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年7月31日 下午12:22:24 
 *   LastChange: 2015年7月31日 下午12:22:24 
 *      History:
 * </pre>
 **************************************************************************/
public class Project
{
	static final int	SIZE	= 20 ;
	
	public static void main( String[] args )
	{
		CountDownLatch latch = new CountDownLatch( SIZE ) ;
		Random r = new Random() ;
		ExecutorService exec = Executors.newCachedThreadPool() ;
		Controller controller = new Controller( latch ) ;
		exec.execute( controller ) ;
		for ( int i = 0 ; i < SIZE ; i++ )
			exec.execute( new Module( latch , "模块" + ( i + 1 ) , r.nextInt( 2000 ) ) ) ;
		
		exec.shutdown() ;
		System.out.println( "Project currentThread==>" + Thread.currentThread().getName() ) ;
		
	}
}
