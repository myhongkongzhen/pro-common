package z.z.w.test.demo.thread;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.thread.MapData.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年8月17日 上午10:03:53 
 *   LastChange: 2015年8月17日 上午10:03:53 
 *      History:
 * </pre>
 **************************************************************************/
public class MapData implements Runnable
{
	private ConcurrentHashMap< String, String >	smsparams	= new ConcurrentHashMap< String, String >();
	
	public static void main( String[ ] args )
	{
		new Thread( new MapData() ).start();
		new Thread( new MapData() ).start();
		new Thread( new MapData() ).start();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		smsparams.put( "1", smsparams.get( "4" ) + "1" );
		smsparams.put( "2", smsparams.get( "3" ) + "2" );
		smsparams.put( "3", smsparams.get( "2" ) + "3" );
		smsparams.put( "4", smsparams.get( "1" ) + "4" );
		try
		{
			long ll = ( long ) ( Math.random() * 1000 );
			System.out.println( ll + "===" );
			Thread.sleep( ll );
		}
		catch ( InterruptedException e )
		{}
		System.out.println( smsparams.size() );
		Set< String > set = smsparams.keySet();
		Iterator< String > it = set.iterator();
		while ( it.hasNext() )
		{
			String key = it.next();
			System.out.println( Thread.currentThread().getName() + "==" + key + "--->>>" + smsparams.get( key ) );
			smsparams.remove( key, smsparams.get( key ) );
			System.out.println( Thread.currentThread().getName() + "------------" + key + "--->>>" + smsparams.get( key ) );
		}
		System.out.println( "=====================================================" );
		System.out.println( smsparams.size() );
	}
}
