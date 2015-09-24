package z.z.w.test.demo ;

import java.util.concurrent.locks.ReadWriteLock ;
import java.util.concurrent.locks.ReentrantReadWriteLock ;

class Data2
{
	private int				data	= ( int ) ( Math.random() * 1000 ) ;	// 共享数据
	private ReadWriteLock	rwl		= new ReentrantReadWriteLock() ;
	
	public void compareAndSetNewValue( int data )
	{
		rwl.readLock().lock() ;// 取到读锁
		try
		{
			System.out.println( Thread.currentThread().getName() + "准备读取数据++++++++++++++" + this.data + "========" + data ) ;
			try
			{
				Thread.sleep( ( long ) ( Math.random() * 1000 ) ) ;
			}
			catch ( InterruptedException e )
			{
				e.printStackTrace() ;
			}
			this.data = data ;
			System.out.println( Thread.currentThread().getName() + "读取___________________" + this.data ) ;
		}
		finally
		{
			rwl.readLock().unlock() ;// 释放读锁
		}
		
		rwl.writeLock().lock() ;// 取到写锁
		try
		{
			System.out.println( Thread.currentThread().getName() + "准备写入数据................" + this.data ) ;
			try
			{
				Thread.sleep( ( long ) ( Math.random() * 1000 ) ) ;
			}
			catch ( InterruptedException e )
			{
				e.printStackTrace() ;
			}
			this.data = data ;
			System.out.println( Thread.currentThread().getName() + "写入--------------------" + this.data ) ;
		}
		finally
		{
			rwl.writeLock().unlock() ;// 释放写锁
		}
	}
	
	public void getV()
	{
		System.out.println( Thread.currentThread().getName() + "========================>>>>>>>>>>>>>" + data ) ;
	}
	
//	public void set( int data )
//	{
//		rwl.writeLock().lock();// 取到写锁
//		try
//		{
//			System.out.println( Thread.currentThread().getName() + "准备写入数据................" );
//			try
//			{
//				Thread.sleep( ( long ) ( Math.random() * 1000 ) );
//			}
//			catch ( InterruptedException e )
//			{
//				e.printStackTrace();
//			}
//			this.data = data;
//			System.out.println( Thread.currentThread().getName() + "写入" + this.data );
//		}
//		finally
//		{
//			rwl.writeLock().unlock();// 释放写锁
//		}
//	}
//	
//	public void get()
//	{
//		rwl.readLock().lock();// 取到读锁
//		try
//		{
//			System.out.println( Thread.currentThread().getName() + "准备读取数据++++++++++++++" );
//			try
//			{
//				Thread.sleep( ( long ) ( Math.random() * 1000 ) );
//			}
//			catch ( InterruptedException e )
//			{
//				e.printStackTrace();
//			}
//			System.out.println( Thread.currentThread().getName() + "读取" + this.data );
//		}
//		finally
//		{
//			rwl.readLock().unlock();// 释放读锁
//		}
//	}
}

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.ReadWriteLockTest.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年8月4日 下午2:39:56 
 *   LastChange: 2015年8月4日 下午2:39:56 
 *      History:
 * </pre>
 **************************************************************************/
public class ReadWriteLockTest2
{
	
	/**
	 * Create by : 2015年8月4日 下午2:39:56
	 * 
	 * @param args
	 */
	public static void main( String[] args )
	{
		System.out.println( Thread.currentThread().getName() + "准备" ) ;
		final Data2 data = new Data2() ;
		for ( int i = 0 ; i < 3 ; i++ )
			new Thread( new Runnable()
			{
				@Override
				public void run()
				{
					for ( int j = 0 ; j < 5 ; j++ )
						data.compareAndSetNewValue( ( int ) ( Math.random() * 10000 ) ) ;
				}
			} ).start() ;
//		for ( int i = 0; i < 3; i++ )
//		{
//			new Thread( new Runnable()
//			{
//				public void run()
//				{
//					for ( int j = 0; j < 5; j++ )
//					{
//						data.getV();
//					}
//				}
//			} ).start();
//		}
		System.out.println( Thread.currentThread().getName() + "結束" ) ;
	}
	
}
