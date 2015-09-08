package z.z.w.util ;

import org.springframework.beans.BeansException ;
import org.springframework.beans.factory.BeanFactoryUtils ;
import org.springframework.context.ApplicationContext ;
import org.springframework.context.ApplicationContextAware ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.util.SpringContextUtil.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年9月7日 下午2:19:17 
 *   LastChange: 2015年9月7日 下午2:19:17 
 *      History:
 * </pre>
 **************************************************************************/
public class SpringContextUtil implements ApplicationContextAware
{
	private static transient ApplicationContext	SPRING_CONTEXT ;
	
	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext
	 * )
	 */
	public void setApplicationContext( ApplicationContext applicationContext ) throws BeansException
	{
		SPRING_CONTEXT = applicationContext ;
	}
	
	@SuppressWarnings( "unchecked" )
	public static < T > T getBean( Class< T > type, String name )
	{
		
		if ( getSpringContext().containsBean( name ) )
		{
			Object bean = getSpringContext().getBean( name ) ;
			if ( type.isInstance( bean ) ) { return ( T ) bean ; }
		}
		return null ;
	}
	
	/**
	 * Create by : 2015年9月7日 下午2:20:15
	 */
	public static ApplicationContext getSpringContext()
	{
		return SPRING_CONTEXT ;
	}
	
	public static < T > T getBean( final Class< T > clazz )
	{
		return clazz.cast( BeanFactoryUtils.beanOfTypeIncludingAncestors( SPRING_CONTEXT, clazz ) ) ;
	}
	
}
