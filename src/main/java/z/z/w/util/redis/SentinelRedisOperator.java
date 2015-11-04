/**********************************************************************************************************************
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package z.z.w.util.redis;

import org.slf4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import javax.annotation.Resource;
import java.util.Map;

/*********************************************************************************************
 * <pre>
 *     FileName: z.z.w.util.redis.ShardRedisOperator
 *         Desc:
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-11-04 15:02
 *   LastChange: 2015-11-04 15:02
 *      History:
 * </pre>
 *********************************************************************************************/
public class SentinelRedisOperator
{

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger( SentinelRedisOperator.class );

	private JedisSentinelPool jedisSentinelPool = null;

	public String set( String key, Map<String, String> map )
	{

		Jedis jedis = jedisSentinelPool.getResource();
		if ( jedis == null ) return null;

		try
		{
			Pipeline pipelined = jedis.pipelined();
			Response<String> hmset = pipelined.hmset( key, map );
			pipelined.syncAndReturnAll();
			return hmset.get();
		}
		catch ( Exception e )
		{
			logger.error( "hmset exception : {}.", e.getMessage(), e );
		}
		finally
		{
			jedis.close();
		}

		return null;
	}

	public JedisSentinelPool getJedisSentinelPool()
	{
		return jedisSentinelPool;
	}

	@Resource
	public void setJedisSentinelPool( JedisSentinelPool jedisSentinelPool )
	{
		this.jedisSentinelPool = jedisSentinelPool;
	}
}
