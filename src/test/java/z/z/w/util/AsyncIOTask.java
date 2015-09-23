package z.z.w.util ;

import java.io.BufferedReader ;
import java.io.IOException ;
import java.io.InputStreamReader ;
import java.net.HttpURLConnection ;
import java.net.URL ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.util.AsyncIOTask.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年9月23日 下午2:02:59 
 *   LastChange: 2015年9月23日 下午2:02:59 
 *      History:
 * </pre>
 **************************************************************************/
public class AsyncIOTask implements Runnable
{
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		HttpURLConnection connection = null ;
		BufferedReader reader = null ;
		try
		{
			String getURL = "http://baidu.com" ;
			URL getUrl = new URL( getURL ) ;
			
			connection = ( HttpURLConnection ) getUrl.openConnection() ;
			connection.connect() ;
			reader = new BufferedReader( new InputStreamReader( connection.getInputStream() ) ) ;
			
			String line = null ;
			while ( ( line = reader.readLine() ) != null )
			{
//				 empty loop
			}
		}
		
		catch ( IOException e )
		{
//			e.printStackTrace() ;
		}
		finally
		{
			if ( reader != null )
			{
				try
				{
					reader.close() ;
				}
				catch ( Exception e )
				{	
					
				}
			}
			connection.disconnect() ;
		}
		
	}
}
