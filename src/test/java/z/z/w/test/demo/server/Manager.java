package z.z.w.test.demo.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.server.Manager.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年8月6日 下午5:14:47 
 *   LastChange: 2015年8月6日 下午5:14:47 
 *      History:
 * </pre>
 **************************************************************************/
public class Manager
{
	private static List< Handler >	list;
	static
	{
		list = new ArrayList< Handler >();
		
		ServiceLoader< Handler > loader = ServiceLoader.load( Handler.class );
		Iterator< Handler > it = loader.iterator();
		while ( it.hasNext() )
		{
			list.add( it.next() );
		}
	}
	
	public static void process( String txt )
	{
		for ( Handler handler : list )
		{
			if ( handler.accept( txt ) )
			{
				handler.execute();
			}
		}
	}
}
