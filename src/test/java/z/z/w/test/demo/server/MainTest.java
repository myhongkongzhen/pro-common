package z.z.w.test.demo.server ;

import java.util.ArrayList ;
import java.util.Iterator ;
import java.util.List ;
import java.util.ServiceLoader ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.server.MainTest.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年8月6日 下午12:15:46 
 *   LastChange: 2015年8月6日 下午12:15:46 
 *      History:
 * </pre>
 **************************************************************************/
public class MainTest
{
	
	private static List< Handler >	list	= null ;
	
	static
	{
		list = new ArrayList< Handler >() ;
		ServiceLoader< Handler > loader = ServiceLoader.load( Handler.class ) ;
		Iterator< Handler > it = loader.iterator() ;
		while ( it.hasNext() )
			list.add( it.next() ) ;
	}
	
	/**
	 * Create by : 2015年8月6日 下午12:15:46
	 * 
	 * @param args
	 */
	public static void main( String[] args )
	{
		ServiceLoader< IServiceLoaderTest > serviceLoader = ServiceLoader.load( IServiceLoaderTest.class ) ;
		for ( IServiceLoaderTest service : serviceLoader )
		{
			service.loadService() ;
			service.destroy() ;
			System.out.println( "============================" ) ;
		}
	}
	
	public static void process( String txt )
	{
		for ( Handler handler : list )
			if ( handler.accept( txt ) ) handler.execute() ;
	}
}
