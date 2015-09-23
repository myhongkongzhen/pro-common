package z.z.w.util ;

import java.lang.management.ManagementFactory ;
import java.math.BigDecimal ;
import java.util.concurrent.BlockingQueue ;
import java.util.concurrent.LinkedBlockingQueue ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.util.SimplePoolSizeCaculatorImplTest.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年9月23日 下午2:02:12 
 *   LastChange: 2015年9月23日 下午2:02:12 
 *      History:
 * </pre>
 **************************************************************************/
public class SimplePoolSizeCaculatorImplTest extends PoolSizeCalculator
{
	
	public static void main( String[] args )
	{
		PoolSizeCalculator poolSizeCalculator = new SimplePoolSizeCaculatorImplTest() ;
		
//		指定期望CPU利用率为1.0，任务队列总大小不超过100,000字节
		poolSizeCalculator.calculateBoundaries( new BigDecimal( 1 ) , new BigDecimal( 100000 ) ) ;
		
		/**
		 * Target queue memory usage (bytes): 10000
		 * createTask() produced z.z.w.util.AsyncIOTask which took 40 bytes in a queue
		 * Formula: 10000 / 40
		 * Recommended queue capacity (bytes): 250
		 * Number of CPU: 4
		 * Target utilization: 0.59999999999999997779553950749686919152736663818359375
		 * Elapsed time (nanos): 3000000000
		 * Compute time (nanos): 78000500
		 * Wait time (nanos): 2921999500
		 * Formula: 4 * 0.59999999999999997779553950749686919152736663818359375 * (1 + 2921999500 / 78000500)
		 * Optimal thread count: 91.19999999999999662492200513952411711215972900390625000
		 */
//		ThreadPoolExecutor pool = new ThreadPoolExecutor( 91 , 91 , 0L , TimeUnit.MILLISECONDS , new LinkedBlockingQueue< Runnable >( 250 ) ) ;
//		ThreadPoolExecutor pool = new ThreadPoolExecutor( 204 , 204 , 0L , TimeUnit.MILLISECONDS , new LinkedBlockingQueue< Runnable >( 2500 ) ) ;
	}
	
	/*
	 * (non-Javadoc)
	 * @see z.z.w.util.PoolSizeCalculator#creatTask()
	 */
	@Override
	protected Runnable creatTask()
	{
		return new AsyncIOTask() ;
	}
	
	/*
	 * (non-Javadoc)
	 * @see z.z.w.util.PoolSizeCalculator#createWorkQueue()
	 */
	@Override
	protected BlockingQueue< Runnable > createWorkQueue()
	{
		return new LinkedBlockingQueue< Runnable >( 1000 ) ;
	}
	
	/*
	 * (non-Javadoc)
	 * @see z.z.w.util.PoolSizeCalculator#getCurrentThreadCPUTime()
	 */
	@Override
	protected long getCurrentThreadCPUTime()
	{
		return ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime() ;
	}
	
}
