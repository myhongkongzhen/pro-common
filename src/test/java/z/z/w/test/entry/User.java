/**********************************************************************************************************************
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package z.z.w.test.entry;

import java.io.Serializable;
import java.util.Objects;

/*********************************************************************************************
 * <pre>
 *     FileName: z.z.w.test.entry.User
 *         Desc:
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-12-09 16:30
 *   LastChange: 2015-12-09 16:30
 *      History:
 * </pre>
 *********************************************************************************************/
public class User implements Serializable
{

	private String userName;
	private int    age;

	public User()
	{

	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName( String userName )
	{
		this.userName = userName;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge( int age )
	{
		this.age = age;
	}

	@Override
	public boolean equals( Object o )
	{

		if ( this == o ) return true;
		if ( !( o instanceof User ) ) return false;
		User user = ( User ) o;
		return age == user.age && Objects.equals( userName, user.userName );
	}

	@Override
	public int hashCode()
	{
		return Objects.hash( userName, age );
	}

	@Override
	public String toString()
	{
		final StringBuilder sb = new StringBuilder( "User{" );
		sb.append( "age=" ).append( age );
		sb.append( ", userName='" ).append( userName ).append( '\'' );
		sb.append( ", super=" ).append( super.toString() );
		sb.append( '}' );
		return sb.toString();
	}
}
