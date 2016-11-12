/**
 * 
 */
package com.wiseboys.mmhackathon.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Getting random string as user login token
 * @author Irudaya Raj Nov 10, 2016 2016
 */
public class SessionIdentifierGenerator {

	private static SecureRandom random = new SecureRandom();

	public static String nextSessionId() {
		return new BigInteger(130, random).toString(32);
	}
}
