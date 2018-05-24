package com.amaiaindustries.rw.Utils;

/**
 * @author Krystal Amaia
 */
public class AIUtilities {
	public static String convertShortToString(Short s){
		return s.toString();
	}

	public static String byteToString(byte inByte) {
		byte[] b = {inByte};
		return new String(b);
	}
}
