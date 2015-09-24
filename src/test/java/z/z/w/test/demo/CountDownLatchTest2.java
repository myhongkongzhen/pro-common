package z.z.w.test.demo ;

import java.util.ArrayList ;
import java.util.List ;
import java.util.concurrent.CountDownLatch ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.CountDownLatchTest2.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年7月31日 上午11:53:43 
 *   LastChange: 2015年7月31日 上午11:53:43 
 *      History:
 * </pre>
 **************************************************************************/
public class CountDownLatchTest2
{
	// 启动多个线程，多线程汇总二维数组每行的结果，然后汇总所有结果
	private static int[][]			data	= new int[][] { { 1 , 3 , 5 , 7 , 9 } , { 2 , 4 , 6 , 8 , 10 } } ;
	private static CountDownLatch	countLatch ;
	
	/**
	 * Create by : 2015年7月31日 上午11:53:43
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main( String[] args ) throws InterruptedException
	{
		// 汇总数
		int total = 0 ;
		List< MyCaculate > threadList = new ArrayList< MyCaculate >() ;
		// 启动二维数组行数个线程
		int threadCount = data.length ;
		countLatch = new CountDownLatch( threadCount ) ;
		for ( int i = 0 ; i < threadCount ; i++ )
		{
			MyCaculate caculate = new MyCaculate( data[ i ] , countLatch ) ;
			caculate.setName( "caculate-thread-" + i ) ;
			caculate.start() ;
			// System.out.println(caculate.isDone());
			threadList.add( caculate ) ;
		}
		// 阻塞所有线程计算完成
		countLatch.await() ;
		//
		for ( MyCaculate c : threadList )
			/*
			 * while(true){
			 * if(c.isDone()){
			 * total+=c.getSum();
			 * break;
			 * }
			 * }
			 */
			// 因为上面await会阻塞到所有线程完成，所以，不用判断isDone
			total += c.getSum() ;
		System.out.println( "--------------------------------------" ) ;
		System.out.println( "各行汇总结果操作完成，汇总结果：" + total ) ;
		
	}
	
}

class MyCaculate extends Thread
{
	private CountDownLatch	countLatch ;
	private int[]			row ;
	private int				sum ;
	private boolean			isDone	= false ;
	
	public MyCaculate( int[] row , CountDownLatch countLatch )
	{
		this.row = row ;
		this.countLatch = countLatch ;
	}
	
	public int getSum()
	{
		return sum ;
	}
	
	public boolean isDone()
	{
		return isDone ;
	}
	
	@Override
	public void run()
	{
		
		try
		{
			System.out.println( Thread.currentThread().getName() + " 开始计算" ) ;
			for ( int i : row )
				sum += i ;
		}
		finally
		{
			// 做完，计数器减1
			countLatch.countDown() ;
		}
		isDone = true ;
	}
}
