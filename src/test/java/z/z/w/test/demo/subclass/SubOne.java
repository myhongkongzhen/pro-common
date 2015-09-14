package z.z.w.test.demo.subclass;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.subclass.SubOne.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年8月21日 上午11:29:25 
 *   LastChange: 2015年8月21日 上午11:29:25 
 *      History:
 * </pre>
 **************************************************************************/
public class SubOne extends Parent
{
	/*
	 * (non-Javadoc)
	 * @see z.z.w.test.subclass.Parent#print()
	 */
	@Override
	public void print()
	{
		System.out.println( "---SubOne----" );
	}
	
	/*
	 * (non-Javadoc)
	 * @see z.z.w.test.subclass.Parent#test()
	 */
	@Override
	public void test()
	{
		System.out.println( "---SubOne-test---" );
	}
}
