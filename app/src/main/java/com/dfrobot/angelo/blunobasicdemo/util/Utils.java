/**  
 * Project Name:Android_Car_Example  
 * File Name:Utils.java  
 * Package Name:com.amap.api.car.example  
 * Date:2015年4月7日下午3:43:05  
 *  
*/  
  
package com.dfrobot.angelo.blunobasicdemo.util;


/**  
 * ClassName:Utils <br/>
 * Date:     2015年4月7日 下午3:43:05 <br/>  
 * @author   yiyi.qi  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public class Utils {



	/**
	 * 异或操作
	 * @param datas
	 * @return
	 */
	public static byte getXor(byte[] datas) {
		byte temp = datas[0];
		for (int i = 1; i < datas.length; i++) {
			temp ^= datas[i];
		}
		return temp;
	}
	
}
  
