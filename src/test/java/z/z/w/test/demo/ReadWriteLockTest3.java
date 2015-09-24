package z.z.w.test.demo ;

import java.util.concurrent.locks.ReadWriteLock ;
import java.util.concurrent.locks.ReentrantReadWriteLock ;

class Data3
{
	private int				data ;									// 共享数据
	private ReadWriteLock	rwl	= new ReentrantReadWriteLock() ;
	
	public void get()
	{
		rwl.readLock().lock() ;// 取到读锁
		try
		{
			System.out.println( Thread.currentThread().getName() + "准备读取数据++++++++++++++" ) ;
			try
			{
				Thread.sleep( ( long ) ( Math.random() * 1000 ) ) ;
			}
			catch ( InterruptedException e )
			{
				e.printStackTrace() ;
			}
			System.out.println( Thread.currentThread().getName() + "读取" + data ) ;
		}
		finally
		{
			rwl.readLock().unlock() ;// 释放读锁
		}
	}
	
	public void set( int data )
	{
		rwl.writeLock().lock() ;// 取到写锁
		try
		{
			System.out.println( Thread.currentThread().getName() + "准备写入数据................" ) ;
			try
			{
				Thread.sleep( ( long ) ( Math.random() * 1000 ) ) ;
			}
			catch ( InterruptedException e )
			{
				e.printStackTrace() ;
			}
			this.data = data ;
			System.out.println( Thread.currentThread().getName() + "写入" + this.data ) ;
		}
		finally
		{
			rwl.writeLock().unlock() ;// 释放写锁
		}
	}
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
public class ReadWriteLockTest3
{
	
	/**
	 * Create by : 2015年8月4日 下午2:39:56
	 * 
	 * @param args
	 */
	public static void main( String[] args )
	{
		System.out.println( Thread.currentThread().getName() + "准备=====================" ) ;
		for ( int i = 0 ; i < 4 ; i++ )
			new Thread( new Runnable()
			{
				
				@Override
				public void run()
				{
					System.out.println( Thread.currentThread().getName() + "准备" ) ;
					final Data data = new Data() ;
					for ( int i = 0 ; i < 3 ; i++ )
						new Thread( new Runnable()
						{
							@Override
							public void run()
							{
								for ( int j = 0 ; j < 5 ; j++ )
									data.set( ( int ) ( Math.random() * 10000 ) ) ;
							}
						} ).start() ;
					for ( int i = 0 ; i < 3 ; i++ )
						new Thread( new Runnable()
						{
							@Override
							public void run()
							{
								for ( int j = 0 ; j < 5 ; j++ )
									data.get() ;
							}
						} ).start() ;
					System.out.println( Thread.currentThread().getName() + "結束" ) ;
				}
			} ).start() ;
		System.out.println( Thread.currentThread().getName() + "結束======================" ) ;
	}
}
