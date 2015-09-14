package z.z.w.test.demo.server;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.server.SecondServiceLoaderTestImpl.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年8月6日 下午12:14:56 
 *   LastChange: 2015年8月6日 下午12:14:56 
 *      History:
 * </pre>
 **************************************************************************/
public class SecondServiceLoaderTestImpl implements IServiceLoaderTest
{
	
	/*
	 * (non-Javadoc)
	 * @see z.z.w.test.server.IServiceLoaderTest#loadService()
	 */
	@Override
	public void loadService()
	{
		System.out.println( "SecondServiceLoaderTestImpl loadService....." );
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see z.z.w.test.server.IServiceLoaderTest#destroy()
	 */
	@Override
	public void destroy()
	{
		System.out.println( "SecondServiceLoaderTestImpl destroy....." );
		
	}
	
}
