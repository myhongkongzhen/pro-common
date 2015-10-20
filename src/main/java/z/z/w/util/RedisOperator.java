/**********************************************************************************************************************
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package z.z.w.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;

/*********************************************************************************************
 * <pre>
 *     FileName: z.z.w.util.RedisOperator
 *         Desc: Redis操作
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-10-09 09:41
 *   LastChange: 2015-10-09 09:41
 *      History:
 * </pre>
 *********************************************************************************************/
public class RedisOperator
{
	protected final static Logger logger = LoggerFactory.getLogger( RedisOperator.class );

	private ShardedJedisPool shardedJedisPool;

	public ShardedJedisPool getShardedJedisPool()
	{
		return shardedJedisPool;
	}

	@Resource public void setShardedJedisPool( ShardedJedisPool shardedJedisPool )
	{
		this.shardedJedisPool = shardedJedisPool;
	}

	public ShardedJedis getRedisClient()
	{
		try
		{
			ShardedJedis shardJedis = shardedJedisPool.getResource();
			return shardJedis;
		}
		catch ( Exception e )
		{
			logger.error( "Get Redis Clent error", e );
		}
		return null;
	}

	public void returnResource( ShardedJedis shardedJedis )
	{
		shardedJedisPool.returnResource( shardedJedis );
	}

	public void returnBrokenResource( ShardedJedis shardedJedis )
	{
		shardedJedisPool.returnBrokenResource( shardedJedis );
	}

	public String set( String key, String value )
	{
		String result = null;

		ShardedJedis shardedJedis = getRedisClient();
		if ( shardedJedis == null ) { return result; }
		try
		{
			result = shardedJedis.set( key, value );
		}
		catch ( Exception e )
		{
			logger.error( "Set redis cache data error : {}.", e.getMessage(), e );
			returnResource( shardedJedis );
		}
		finally
		{
			returnBrokenResource( shardedJedis );
		}

		return result;
	}

	public String get( String key )
	{
		String result = null;

		ShardedJedis shardedJedis = getRedisClient();
		if ( shardedJedis == null ) { return result; }
		try
		{
			result = shardedJedis.get( key );
		}
		catch ( Exception e )
		{
			logger.error( "Set redis cache data error : {}.", e.getMessage(), e );
			returnResource( shardedJedis );
		}
		finally
		{
			returnBrokenResource( shardedJedis );
		}

		return result;
	}

	public String setex( String key, int seconds, String value )
	{
		String result = null;

		ShardedJedis shardedJedis = getRedisClient();
		if ( shardedJedis == null ) { return result; }
		try
		{
			result = shardedJedis.setex( key, seconds, value );
		}
		catch ( Exception e )
		{
			logger.error( "Set redis cache data error : {}.", e.getMessage(), e );
			returnResource( shardedJedis );
		}
		finally
		{
			returnBrokenResource( shardedJedis );
		}

		return result;
	}

	public Long del( String key )
	{
		Long result = null;

		ShardedJedis shardedJedis = getRedisClient();
		if ( shardedJedis == null ) { return result; }
		try
		{
			result = shardedJedis.del( key );
		}
		catch ( Exception e )
		{
			logger.error( "Set redis cache data error : {}.", e.getMessage(), e );
			returnResource( shardedJedis );
		}
		finally
		{
			returnBrokenResource( shardedJedis );
		}

		return result;
	}
}
