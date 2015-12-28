/**********************************************************************************************************************
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package z.z.w.test;

import z.z.w.test.entry.User;

import java.util.ArrayList;
import java.util.List;

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

		List<User> list = new ArrayList<User>();
		User       u    = new User();
		u.setAge( 1 );
		u.setUserName( "1" );
		list.add( u );

		u = new User();
		u.setAge( 2 );
		u.setUserName( "2" );
		list.add( u );

		u = new User();
		u.setAge( 3 );
		u.setUserName( "3" );
		list.add( u );

		u = new User();
		u.setAge( 4 );
		u.setUserName( "4" );
		list.add( u );

		u = new User();
		u.setAge( 5 );
		u.setUserName( "5" );
		list.add( u );

		List<User> subList = list.subList( 1, 3 );

		for ( User i : list )
		{
			System.out.println( i.toString() );
		}

		System.out.println( "========" );

		for ( User i : subList )
		{
			System.out.println( i );
			i.setUserName( i.getUserName() + " == " + i.getAge() );
		}
		System.out.println( "========" );

		for ( User i : list )
		{
			System.out.println( i.toString() );
		}
//
//		Integer[] des = new Integer[ 4 ];
//		Integer[] src = { 6 , 7 , 8 , 9 , 10 , 11 , 12 , 13 };
//
//		// 把 src 數組的元素從 第 2 號位置開始拷貝,拷貝3個元素
//		// 到 des 數組中，在des中的 1 號位置開始放置數據
//		System.arraycopy( src, 2, des, 1, 3 );
//
//		for ( Integer i : des )
//		{
//			System.out.println( "desc==" + i );
//		}
//		System.out.println( "======================" );
//		for ( Integer i : src )
//		{
//			System.out.println( i );
//		}

//		Object[] obj = {};
//		System.out.println(obj.length);
//
//		obj[0]=2; // ArrayIndexOutOfBoundsException
//		System.out.println(obj.length);

//		int i = 0;
//		int j = i >> 1;
//		System.out.println( i );
//		System.out.println( j );
	}
}
