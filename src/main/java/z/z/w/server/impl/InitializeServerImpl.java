package z.z.w.server.impl ;

import java.util.Enumeration ;
import java.util.Properties ;

import org.apache.commons.lang3.StringUtils ;
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;

import z.z.w.server.IServiceLoader ;
import z.z.w.util.SpringContextUtil ;
import z.z.w.util.comm.PropertiesUtils ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.server.impl.InitializeServerImpl.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年9月22日 下午4:47:34 
 *   LastChange: 2015年9月22日 下午4:47:34 
 *      History:
 * </pre>
 **************************************************************************/
public class InitializeServerImpl implements IServiceLoader
{
	final static Logger	logger			= LoggerFactory.getLogger( InitializeServerImpl.class ) ;
	final static String	RELATIVE_PATH	= "pro/services.properties" ;
	
	/*
	 * (non-Javadoc)
	 * @see z.z.w.server.IServiceLoader#destroy()
	 */
	@Override
	public void destroy()
	{
		// TODO 2015年9月22日 下午4:47:34 
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see z.z.w.server.IServiceLoader#loadService()
	 */
	@Override
	public void loadService()
	{
		logger.info( "Starting initialize beans service....." ) ;
		try
		{
			Properties serviceProps = PropertiesUtils.INSTANCE.getProperties( RELATIVE_PATH ) ;
			
			if ( serviceProps.isEmpty() )
			{
				logger.warn( "沒有要執行的服務。。。。" ) ;
				return ;
			}
			
			Enumeration< Object > keys = serviceProps.keys() ;
			while ( keys.hasMoreElements() )
				try
				{
					String serviceId = keys.nextElement().toString() ;
					String serviceName = serviceProps.getProperty( serviceId ) ;
					
					if ( StringUtils.isBlank( serviceName ) ) continue ;
					
					Runnable iServer = SpringContextUtil.getBean( Runnable.class , serviceName ) ;
					logger.info( "服務[{}-{}:{}]開始執行。。。。" , serviceId , serviceName , iServer ) ;
					
					new Thread( iServer ).start() ;
				}
				catch ( Exception e )
				{
					logger.error( "Loading initialize beans error : {}." , e.getMessage() , e ) ;
				}
		}
		catch ( Exception e )
		{
			logger.error( "Loading initialize beans error : {}." , e.getMessage() , e ) ;
		}
		logger.info( "Loaded initialize beans service." ) ;
		
	}
	
}
