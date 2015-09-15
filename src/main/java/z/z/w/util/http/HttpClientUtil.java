package z.z.w.util.http ;

import java.io.IOException ;
import java.io.InterruptedIOException ;
import java.io.UnsupportedEncodingException ;
import java.net.MalformedURLException ;
import java.net.URI ;
import java.net.URISyntaxException ;
import java.net.URL ;
import java.net.UnknownHostException ;
import java.nio.charset.CodingErrorAction ;
import java.util.LinkedList ;
import java.util.List ;
import java.util.Map ;

import javax.net.ssl.SSLException ;

import org.apache.commons.lang3.StringUtils ;
import org.apache.http.Consts ;
import org.apache.http.HttpEntity ;
import org.apache.http.HttpEntityEnclosingRequest ;
import org.apache.http.HttpHost ;
import org.apache.http.HttpRequest ;
import org.apache.http.NameValuePair ;
import org.apache.http.ParseException ;
import org.apache.http.client.ClientProtocolException ;
import org.apache.http.client.HttpRequestRetryHandler ;
import org.apache.http.client.config.RequestConfig ;
import org.apache.http.client.entity.UrlEncodedFormEntity ;
import org.apache.http.client.methods.CloseableHttpResponse ;
import org.apache.http.client.methods.HttpPost ;
import org.apache.http.client.protocol.HttpClientContext ;
import org.apache.http.config.ConnectionConfig ;
import org.apache.http.config.MessageConstraints ;
import org.apache.http.config.SocketConfig ;
import org.apache.http.conn.ConnectTimeoutException ;
import org.apache.http.conn.routing.HttpRoute ;
import org.apache.http.entity.StringEntity ;
import org.apache.http.impl.client.CloseableHttpClient ;
import org.apache.http.impl.client.HttpClients ;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager ;
import org.apache.http.message.BasicNameValuePair ;
import org.apache.http.protocol.HttpContext ;
import org.apache.http.util.EntityUtils ;
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.util.http.HttpClientUtil.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年8月27日 上午11:46:17 
 *   LastChange: 2015年8月27日 上午11:46:17 
 *      History:
 * </pre>
 **************************************************************************/
public class HttpClientUtil
{
	private static final Logger			logger					= LoggerFactory.getLogger( HttpClientUtil.class ) ;
	private static final int			MAX_TOTAL_CONNECTIONS	= 200 ;
	private static final int			MAX_ROUTE_CONNECTIONS	= 50 ;
	private static final HttpHost		DEFAULT_TARGETHOST		= new HttpHost( "http://localhost", 8888 ) ;
	private static final int			CONNECT_TIMEOUT			= 61000 ;
	private static final int			SOCKET_TIMEOUT			= 61000 ;
	private static final int			CONN_MANAGER_TIMEOUT	= 61000 ;
	
	private static final RequestConfig	config					= RequestConfig.custom()
																				.setSocketTimeout( SOCKET_TIMEOUT )
																				.setConnectTimeout( CONNECT_TIMEOUT )
																				.setConnectionRequestTimeout( CONN_MANAGER_TIMEOUT )
																				.build() ;
	
	private static CloseableHttpClient	httpClient				= null ;
	
	static
	{
		try
		{
			PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager() ;
			SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay( true ).build() ;
			connManager.setDefaultSocketConfig( socketConfig ) ;
			MessageConstraints messageConstraints = MessageConstraints.custom().setMaxHeaderCount( 200 ).setMaxLineLength( 2000 ).build() ;
			ConnectionConfig connectionConfig = ConnectionConfig.custom()
																.setMalformedInputAction( CodingErrorAction.IGNORE )
																.setUnmappableInputAction( CodingErrorAction.IGNORE )
																.setCharset( Consts.UTF_8 )
																.setMessageConstraints( messageConstraints )
																.build() ;
			connManager.setDefaultConnectionConfig( connectionConfig ) ;
			connManager.setMaxTotal( MAX_TOTAL_CONNECTIONS ) ;
			connManager.setDefaultMaxPerRoute( MAX_ROUTE_CONNECTIONS ) ;
			connManager.setMaxPerRoute( new HttpRoute( DEFAULT_TARGETHOST ), 50 ) ;
			
			HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler()
			{
				public boolean retryRequest( IOException exception, int executionCount, HttpContext context )
				{
					if ( executionCount >= 5 ) return false ;
					if ( exception instanceof InterruptedIOException ) return false ;
					if ( exception instanceof UnknownHostException ) return false ;
					if ( exception instanceof ConnectTimeoutException ) return false ;
					if ( exception instanceof SSLException ) return false ;
					HttpClientContext clientContext = HttpClientContext.adapt( context ) ;
					HttpRequest request = clientContext.getRequest() ;
					boolean idempotent = !( request instanceof HttpEntityEnclosingRequest ) ;
					if ( idempotent ) return true ;
					return false ;
				}
			} ;
			
			httpClient = HttpClients.custom().setConnectionManager( connManager ).setRetryHandler( retryHandler ).build() ;
		}
		catch ( Exception e )
		{
			logger.error( "HttpClientUtil error : [{}].", e.getMessage(), e ) ;
		}
	}
	
	/**
	 * @return the httpClient
	 */
	public static CloseableHttpClient getHttpClient()
	{
		return httpClient ;
	}
	
	/**
	 * 解决BODY中文乱码问题
	 * Create by : 2015年9月2日 下午2:37:29
	 * 
	 * @throws Exception
	 */
	public static String httpPost( String url, String msg ) throws Exception
	{
		try
		{
			StringEntity stringEntity = new StringEntity( msg, "utf-8" ) ;// 解决中文乱码问题
			HttpPost httpPost = new HttpPost( url ) ;
			httpPost.setEntity( stringEntity ) ;
			
			return doPost( httpPost ) ;
		}
		catch ( Exception e )
		{
			logger.error( "HttpClientUtil error : [{}].", e.getMessage(), e ) ;
			throw e ;
		}
	}
	
	/**
	 * 解決參數&xxx=xxx中文編碼，UTF-8
	 * Create by : 2015年9月14日 上午11:00:56
	 */
	public static String httpPost( String url, Map< String, String > paramMap ) throws Exception
	{
		try
		{
			HttpPost httpPost = new HttpPost( url ) ;
			setParam( httpPost, paramMap ) ;
			
			return doPost( httpPost ) ;
		}
		catch ( Exception e )
		{
			logger.error( "HttpClientUtil error : [{}].", e.getMessage(), e ) ;
			throw e ;
		}
	}
	
	/**
	 * URL參數map
	 * 自定義header
	 * Create by : 2015年9月14日 上午11:02:05
	 */
	public static String httpPost( String httpUrl, Map< String, String > paramMap, Map< String, String > headerMap ) throws Exception
	{
		try
		{
			HttpPost httpPost = new HttpPost( httpUrl ) ;
			setParam( httpPost, paramMap ) ;
			setHeader( httpPost, headerMap ) ;
			
			return doPost( httpPost ) ;
		}
		catch ( Exception e )
		{
			logger.error( "HttpClientUtil error : [{}].", e.getMessage(), e ) ;
			throw e ;
		}
	}
	
	/**
	 * 請求URL中包含特殊字符,使用此API發送請求
	 * http://XXX.XXX.XXX:XXX/SingleSMS?json={"SingleSMSRequest":{"UserID":"1000001"}}
	 * Create by : 2015年9月14日 下午2:31:26
	 */
	public static String httpPostParticular( String httpUrl, Map< String, String > paramMap, Map< String, String > headerMap ) throws Exception
	{
		try
		{
			URL url = new URL( httpUrl ) ;
			URI uri = new URI( url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null ) ;
			
			HttpPost httpPost = new HttpPost( uri ) ;
			setParam( httpPost, paramMap ) ;
			setHeader( httpPost, headerMap ) ;
			
			return doPost( httpPost ) ;
		}
		catch ( Exception e )
		{
			logger.error( "HttpClientUtil error : [{}].", e.getMessage(), e ) ;
			throw e ;
		}
	}
	
	/**
	 * Create by : 2015年9月14日 上午11:04:22
	 * 
	 * @throws UnsupportedEncodingException
	 */
	private static void setParam( HttpPost httpPost, Map< String, String > paramMap ) throws UnsupportedEncodingException
	{
		if ( null != paramMap && !paramMap.isEmpty() )
		{
			List< NameValuePair > params = new LinkedList< NameValuePair >() ;
			
			for ( Map.Entry< String, String > entry : paramMap.entrySet() )
				params.add( new BasicNameValuePair( StringUtils.trimToEmpty( entry.getKey() ), StringUtils.trimToEmpty( entry.getValue() ) ) ) ;
			
			if ( null != params && !params.isEmpty() ) httpPost.setEntity( new UrlEncodedFormEntity( params, "UTF-8" ) ) ;
		}
		
	}
	
	/**
	 * Create by : 2015年9月14日 上午11:03:15
	 */
	private static void setHeader( HttpPost httpPost, Map< String, String > headerMap )
	{
		if ( null != headerMap && headerMap.size() > 0 )
		{
			for ( Map.Entry< String, String > entry : headerMap.entrySet() )
				httpPost.setHeader( StringUtils.trimToEmpty( entry.getKey() ), StringUtils.trimToEmpty( entry.getValue() ) ) ;
		}
		
	}
	
	/**
	 * 解决BODY中文乱码问题
	 * 自定義header
	 * Create by : 2015年9月2日 下午2:52:24
	 */
	public static String httpPost( String url, String msg, Map< String, String > headerMap ) throws Exception
	{
		try
		{
			StringEntity stringEntity = new StringEntity( msg, "utf-8" ) ;// 解决中文乱码问题
			
			HttpPost httpPost = new HttpPost( url ) ;
			httpPost.setEntity( stringEntity ) ;
			
			setHeader( httpPost, headerMap ) ;
			
			return doPost( httpPost ) ;
		}
		catch ( Exception e )
		{
			logger.error( "HttpClientUtil error : [{}].", e.getMessage(), e ) ;
			throw e ;
		}
	}
	
	/**
	 * 請求URL中包含特殊字符,使用此API發送請求
	 * http://XXX.XXX.XXX:XXX/SingleSMS?json={"SingleSMSRequest":{"UserID":"1000001"}}
	 * Create by : 2015年8月27日 下午4:45:32
	 */
	public static String httpPostParticular( String httpUrl ) throws Exception
	{
		try
		{
			URL url = new URL( httpUrl ) ;
			URI uri = new URI( url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null ) ;
			HttpPost httpPost = new HttpPost( uri ) ;
			return doPost( httpPost ) ;
		}
		catch ( MalformedURLException e )
		{
			logger.error( "HttpClientUtil error : [{}].", e.getMessage(), e ) ;
			throw e ;
		}
		catch ( URISyntaxException e )
		{
			logger.error( "HttpClientUtil error : [{}].", e.getMessage(), e ) ;
			throw e ;
		}
		catch ( Exception e )
		{
			logger.error( "HttpClientUtil error : [{}].", e.getMessage(), e ) ;
			throw e ;
		}
	}
	
	/**
	 * header由調用者set
	 * Create by : 2015年9月14日 上午11:54:59
	 */
	private static String doPost( HttpPost httpPost ) throws Exception
	{
		try
		{
			httpPost.setConfig( config ) ;
			/**
			 * setHeader(name, value)：如果Header中没有定义则添加，如果已定义则用新的value覆盖原用value值。
			 * addHeader(name, value)：如果Header中没有定义则添加，如果已定义则保持原有value不改变。
			 */
//			httpPost.addHeader( HTTP.CONTENT_TYPE, "application/json" ) ;
//			httpPost.addHeader( "Accept-Charset", "gbk,GB2312,utf-8;q=0.7,*;q=0.7" ) ;
//			httpPost.addHeader( "Accept-Language", "zh-cn,zh;q=0.5" ) ;
//			httpPost.addHeader( HTTP.CONN_DIRECTIVE, "keep-alive" ) ;
//			httpPost.addHeader( "refer", "localhost" ) ;
//			httpPost.addHeader( HTTP.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2" ) ;
			
			CloseableHttpResponse response = httpClient.execute( httpPost, HttpClientContext.create() ) ;
			try
			{
				HttpEntity entity = response.getEntity() ;
				logger.debug( "{}", entity ) ;
//				return EntityUtils.toString( entity, "GBK" ) ;
				return EntityUtils.toString( entity, "utf-8" ) ;
			}
			finally
			{
				if ( null != response ) response.close() ;
			}
		}
		catch ( ClientProtocolException e )
		{
			logger.error( "HttpClientUtil error : [{}].", e.getMessage(), e ) ;
			throw e ;
		}
		catch ( ParseException e )
		{
			logger.error( "HttpClientUtil error : [{}].", e.getMessage(), e ) ;
			throw e ;
		}
		catch ( IOException e )
		{
			logger.error( "HttpClientUtil error : [{}].", e.getMessage(), e ) ;
			throw e ;
		}
		catch ( Exception e )
		{
			logger.error( "HttpClientUtil error : [{}].", e.getMessage(), e ) ;
			throw e ;
		}
	}
	
	public static String httpPost( String url ) throws Exception
	{
		try
		{
			HttpPost httpPost = new HttpPost( url ) ;
			return doPost( httpPost ) ;
		}
		catch ( Exception e )
		{
			logger.error( "HttpClientUtil error : [{}].", e.getMessage(), e ) ;
			throw e ;
		}
		
	}
	
	private HttpClientUtil()
	{
		super() ;
	}
	
}
