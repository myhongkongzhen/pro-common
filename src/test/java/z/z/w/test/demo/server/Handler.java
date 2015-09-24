package z.z.w.test.demo.server ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.server.dd.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年8月6日 下午5:22:17 
 *   LastChange: 2015年8月6日 下午5:22:17 
 *      History:
 * </pre>
 **************************************************************************/
public interface Handler
{
	public boolean accept( String txt ) ;
	
	public String execute() ;
}
