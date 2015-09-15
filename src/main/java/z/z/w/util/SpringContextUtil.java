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
	private static transient ApplicationContext	applicationContext ;
	
	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext
	 * )
	 */
	public void setApplicationContext( ApplicationContext applicationContext ) throws BeansException
	{
		SpringContextUtil.applicationContext = applicationContext ;
	}
	
	@SuppressWarnings( "unchecked" )
	public static < T > T getBean( Class< T > type, String name )
	{
		
		if ( getApplicationContext().containsBean( name ) )
		{
			Object bean = getApplicationContext().getBean( name ) ;
			if ( type.isInstance( bean ) ) { return ( T ) bean ; }
		}
		return null ;
	}
	
	public static < T > T getBean( final Class< T > clazz )
	{
		return clazz.cast( BeanFactoryUtils.beanOfTypeIncludingAncestors( SpringContextUtil.applicationContext, clazz ) ) ;
	}
	
	/**
	 * @return the applicationContext
	 */
	public static ApplicationContext getApplicationContext()
	{
		return applicationContext ;
	}
	
}
