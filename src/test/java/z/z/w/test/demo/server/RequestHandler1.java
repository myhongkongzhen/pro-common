package z.z.w.test.demo.server ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.server.d.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年8月6日 下午5:23:13 
 *   LastChange: 2015年8月6日 下午5:23:13 
 *      History:
 * </pre>
 **************************************************************************/
public class RequestHandler1 implements Handler
{
	@Override
	public boolean accept( String txt )
	{
		return true ;
	}
	
	@Override
	public String execute()
	{
		return "11111111111111" ;
	}
}
