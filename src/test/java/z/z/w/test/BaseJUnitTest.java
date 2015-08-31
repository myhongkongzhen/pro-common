package z.z.w.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.BaseJUnitTest.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年8月27日 上午11:44:23 
 *   LastChange: 2015年8月27日 上午11:44:23 
 *      History:
 * </pre>
 **************************************************************************/
public class BaseJUnitTest
{
	private static Logger	logger	= LoggerFactory.getLogger( BaseJUnitTest.class );
	
	@Test
	public void test()
	{
		logger.info( "This is test." );
	}
	
}
