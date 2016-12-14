package com.gy.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * *************************************************************************
 * @文件名称: DateUtils.java
 *
 * @包路径  : com.zhe800.task.tradepush.utils
 *				 
 * @版权所有: 团博百众（北京）科技有限公司 (C) 2014
 *
 * @类描述:	日期工具类  
 * 
 * @创建人:   liujinxin  
 *
 * @创建时间: 2015年1月16日 - 下午1:15:32 
 *
 * @修改记录:
   -----------------------------------------------------------------------------------------------
             时间						|		修改人		|		修改的方法		|		修改描述                                                                
   -----------------------------------------------------------------------------------------------
							|					|					|                                       
   ----------------------------------------------------------------------------------------------- 	
 
 **************************************************************************
 */
public final class DateUtils {

	private DateUtils(){}
	
	public static final Long DAY_MILLIS = 24*60*60*1000L;
	public static final Long HOUR_MILLIS = 60*60*1000L;
	public static final Long MINUTE_MILLIS = 60*1000L;
	public static final Long SECOND_MILLIS = 1000L;
	

	public static final String ss = "yyyy-MM-dd HH:mm:ss";
	public static final String SSS = "yyyy-MM-dd HH:mm:ss.SSSZ";
	
	/**
	 * 	方法描述: 字符串（yyyy-MM-dd HH:mm:ss）转换为 DateTime
	 *   
	 *  @author  liujinxin  创建时间 2015年1月16日 下午1:15:28
	 */
	public static DateTime str2DateTimeWithss(String str){
		return DateTimeFormat.forPattern(ss).parseDateTime(str);  
	}
	
	/**
	 * 	方法描述: 将时间戳改为DateTime
	 *   
	 *  @author  hongliangjun  创建时间 2015年3月18日 下午4:46:22
	 */
	public static DateTime millis2DateTimeWithss(String str){
		
		return new DateTime(Long.parseLong(str));
	}
	
	/**
	 * 	方法描述: DateTime 转换为 字符串（yyyy-MM-dd HH:mm:ss）
	 * 			DateTime = null 的时候 return null
	 *   
	 *  @author  liujinxin  创建时间 2015年1月16日 下午1:17:55
	 */
	public static String dateTime2StringWithss(DateTime dateTime){
		if(dateTime == null){
			return null;
		}else{
			return dateTime.toString(ss);
		}
	}

	public static void main(String[] args){
	    long startTime = System.currentTimeMillis();
	    System.out.println("main 方法开始");



	    System.out.println("main 方法结束 耗时:" + (System.currentTimeMillis() - startTime) +" ms!");
	}
}
