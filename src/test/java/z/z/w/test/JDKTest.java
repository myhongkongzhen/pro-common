/**********************************************************************************************************************
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package z.z.w.test;

/*********************************************************************************************
 * <pre>
 *     FileName: z.z.w.test.JDKTest
 *         Desc:
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-12-09 11:54
 *   LastChange: 2015-12-09 11:54
 *      History:
 * </pre>
 *********************************************************************************************/
public class JDKTest
{
	public static void main( String[] args )
	{

		Object[] obj = {};
		System.out.println(obj.length);

		obj[0]=2; // ArrayIndexOutOfBoundsException
		System.out.println(obj.length);

//		int i = 10;
//		int j = i >> 1;
//		System.out.println( i );
//		System.out.println( j );
	}
}
