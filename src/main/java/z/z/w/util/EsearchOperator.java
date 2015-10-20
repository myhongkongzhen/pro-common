/**********************************************************************************************************************
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package z.z.w.util;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/*********************************************************************************************
 * <pre>
 *     FileName: com.zy.redis.EsearchOperator
 *         Desc:
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-10-16 17:14
 *   LastChange: 2015-10-16 17:14
 *      History:
 * </pre>
 *********************************************************************************************/
public class EsearchOperator
{

	public Client getClient()
	{
		return ESClient.INSTANCE.getClient();
	}

	static enum ESClient
	{

		INSTANCE;

		private Client client = null;

		private ESClient()
		{

			Settings settings = ImmutableSettings.settingsBuilder()
					//指定集群名称
					.put( "cluster.name", "smsbiz" ).put( "client.transport.ping_timeout", "20s" )
							//探测集群中机器状态
					.put( "client.transport.sniff", true ).build();

			client = new TransportClient( settings ).addTransportAddress( new InetSocketTransportAddress( "113.31.87.154", 9300 ) );

		}

		public Client getClient()
		{
			return client;
		}
	}
}
