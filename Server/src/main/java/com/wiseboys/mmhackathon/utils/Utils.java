/**
 * 
 */
package com.wiseboys.mmhackathon.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * All util functions 
 * @author Irudaya Raj
 * Nov 11, 2016 2016
 */
public class Utils {
	
	public static String encodeToUtf8(String rawString){
		try {
			return URLEncoder.encode(rawString,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return rawString;
	}
}
