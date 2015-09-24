package z.z.w.test.demo.server ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.server.FirstServiceLoaderTestImpl.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年8月6日 下午12:14:17 
 *   LastChange: 2015年8月6日 下午12:14:17 
 *      History:
 * </pre>
 **************************************************************************/
public class FirstServiceLoaderTestImpl implements IServiceLoaderTest
{
	
	/*
	 * (non-Javadoc)
	 * @see z.z.w.test.server.IServiceLoaderTest#destroy()
	 */
	@Override
	public void destroy()
	{
		System.out.println( "FirstServiceLoaderTestImpl destroy....." ) ;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see z.z.w.test.server.IServiceLoaderTest#loadService()
	 */
	@Override
	public void loadService()
	{
		System.out.println( "FirstServiceLoaderTestImpl loadService....." ) ;
	}
	
}
