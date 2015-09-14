package z.z.w.test.demo ;

import java.util.HashMap ;
import java.util.Map ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.MapTest.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年8月5日 下午3:21:31 
 *   LastChange: 2015年8月5日 下午3:21:31 
 *      History:
 * </pre>
 **************************************************************************/
public class MapTest
{
	
	/**
	 * Create by : 2015年8月5日 下午3:21:31
	 * 
	 * @param args
	 */
	public static void main( String[] args )
	{
		String result = "14386746486091|13718249651|0|2015-03-19 05:50:00^14386746487031|13839399408|0|2015-03-19 05:50:00^14386746487031|13339399808|0|2015-03-19 05:50:00^14386746487031|13839394808|0|2015-03-19 05:50:00" ;
		String array[] = result.split( "\\^", -1 ) ;
		Map< String, String > params = new HashMap< String, String >() ;
		for ( String aay : array )
		{
			try
			{
				final String smsStatus = aay ;
				String ay[] = smsStatus.split( "\\|", -1 ) ;
				REQParam pv = new REQParam() ;// updateParseData( sv );
				pv.setUid( ay[ 0 ] ) ;
				pv.setMerSmsChanSig( ay[ 2 ] ) ;
				pv.setMobile( ay[ 1 ] ) ;
				
//				params.put( "data", JSONObject.fromObject( pv ).toString() );
				String s = pars( pv ) ;
				System.out.println( "---------------" + s ) ;
				if ( params.containsKey( pv.getUid() ) )
				{
					String tmp = params.get( pv.getUid() ) ;
					StringBuffer sb = new StringBuffer( tmp ) ;
					sb.append( "," ) ;
					sb.append( s ) ;
					s = sb.toString() ;
					System.out.println( "=====>" + tmp + "===>" + sb.toString() ) ;
				}
				params.put( pv.getUid(), s ) ;
				
			}
			catch ( Exception e )
			{
				e.printStackTrace() ;
			}
			System.out.println( "====>" + params ) ;
		}
	}
	
	/**
	 * Create by : 2015年8月5日 下午3:37:32
	 * 
	 * @param pv
	 * @return
	 */
	private static String pars( REQParam pv )
	{
		return "{uid:\"" + pv.getUid() + "\",data:\"" + pv.getMerSmsChanSig() + "\",mobile:\"" + pv.getMobile() + "\"}" ;
	}
}

class REQParam
{
	/**
	 * {
	 * "mobile":"13333333333",
	 * "uid":”fdsfds48789fdslkljff8”
	 * "datetime":"2014-01-12 09:12:12",
	 * "status":"0",
	 * "desc":""
	 * }
	 */
	// 接受手机号, 用户接收时间, 接收状态, 接收失败原因，对应的发送短信的UID
	private String	mobile ;
	private String	uid ;
	private String	merSmsChanSig ;	// 內部ID，用於唯一標示符
									
	public REQParam()
	{
		super() ;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "REQParam [mobile=" + mobile + ", uid=" + uid + ", merSmsChanSig=" + merSmsChanSig + "]" ;
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
		result = prime * result + ( ( merSmsChanSig == null ) ? 0 : merSmsChanSig.hashCode() ) ;
		result = prime * result + ( ( mobile == null ) ? 0 : mobile.hashCode() ) ;
		result = prime * result + ( ( uid == null ) ? 0 : uid.hashCode() ) ;
		return result ;
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
		REQParam other = ( REQParam ) obj ;
		if ( merSmsChanSig == null )
		{
			if ( other.merSmsChanSig != null ) return false ;
		}
		else if ( !merSmsChanSig.equals( other.merSmsChanSig ) ) return false ;
		if ( mobile == null )
		{
			if ( other.mobile != null ) return false ;
		}
		else if ( !mobile.equals( other.mobile ) ) return false ;
		if ( uid == null )
		{
			if ( other.uid != null ) return false ;
		}
		else if ( !uid.equals( other.uid ) ) return false ;
		return true ;
	}
	
	/**
	 * @return the mobile
	 */
	public String getMobile()
	{
		return mobile ;
	}
	
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile( String mobile )
	{
		this.mobile = mobile ;
	}
	
	/**
	 * @return the uid
	 */
	public String getUid()
	{
		return uid ;
	}
	
	/**
	 * @param uid the uid to set
	 */
	public void setUid( String uid )
	{
		this.uid = uid ;
	}
	
	/**
	 * @return the merSmsChanSig
	 */
	public String getMerSmsChanSig()
	{
		return merSmsChanSig ;
	}
	
	/**
	 * @param merSmsChanSig the merSmsChanSig to set
	 */
	public void setMerSmsChanSig( String merSmsChanSig )
	{
		this.merSmsChanSig = merSmsChanSig ;
	}
	
}
