package z.z.w.test.demo.zy ;

import java.util.Random ;
import java.util.concurrent.Callable ;
import java.util.concurrent.CompletionService ;
import java.util.concurrent.ExecutionException ;
import java.util.concurrent.ExecutorCompletionService ;
import java.util.concurrent.ExecutorService ;
import java.util.concurrent.Executors ;
import java.util.concurrent.Future ;
import java.util.concurrent.TimeUnit ;

/**************************************************************************
 * <pre>
 *     FileName: com.zy.demo.CallableTest.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年7月28日 下午3:14:29 
 *   LastChange: 2015年7月28日 下午3:14:29 
 *      History:
 * </pre>
 **************************************************************************/
public class CallableTest
{
	
	/**
	 * @param args
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public static void main( String[] args ) throws InterruptedException , ExecutionException
	{
		ExecutorService executor = Executors.newFixedThreadPool( 200 ) ;
		CompletionService< Param > comp = new ExecutorCompletionService< Param >( executor ) ;
		
		for ( int i = 0 ; i < 500 ; i++ )
			comp.submit( new Callable< Param >()
			{
				@Override
				public Param call() throws Exception
				{
					Param p = new Param() ;
					Random rand = new Random() ;
					TimeUnit.SECONDS.sleep( rand.nextInt( 7 ) ) ;
					System.out.println( Thread.currentThread().getName() + "====" ) ;
					p.setI( rand.nextInt() ) ;
					p.setN( rand.nextInt() + "=This is test!!!!!" ) ;
					return p ;
				}
			} ) ;
		executor.shutdown() ;
		
		int count = 0 , index = 1 ;
		while ( count < 500 )
		{
			Future< Param > f = comp.poll() ;
			if ( f == null ) System.out.println( index + " : " + Thread.currentThread().getName() + " 没发现有完成的任务" ) ;
			else
			{
				System.out.println( index + " : " + Thread.currentThread().getName() + "产生了一个随机数: " + f.get() ) ;
				count++ ;
			}
			index++ ;
//			TimeUnit.MILLISECONDS.sleep( 500 );
		}
		
	}
	
}

class Param
{
	private int		i ;
	private String	n ;
	
	public Param()
	{
		super() ;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj ) return true ;
		if ( obj == null ) return false ;
		if ( getClass() != obj.getClass() ) return false ;
		Param other = ( Param ) obj ;
		if ( i != other.i ) return false ;
		if ( n == null )
		{
			if ( other.n != null ) return false ;
		}
		else if ( !n.equals( other.n ) ) return false ;
		return true ;
	}
	
	/**
	 * @return the i
	 */
	public int getI()
	{
		return i ;
	}
	
	/**
	 * @return the n
	 */
	public String getN()
	{
		return n ;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31 ;
		int result = 1 ;
		result = ( prime * result ) + i ;
		result = ( prime * result ) + ( ( n == null ) ? 0 : n.hashCode() ) ;
		return result ;
	}
	
	/**
	 * @param i
	 *            the i to set
	 */
	public void setI( int i )
	{
		this.i = i ;
	}
	
	/**
	 * @param n
	 *            the n to set
	 */
	public void setN( String n )
	{
		this.n = n ;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Param [i=" + i + ", n=" + n + "]" ;
	}
	
}
