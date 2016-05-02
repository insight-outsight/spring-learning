package org.springlearning.utils;

import java.util.zip.CRC32;

public class CRC32Utils {

	public static long getCRC32Value(String str){
		CRC32 crc32 = new CRC32();
		crc32.update(str.getBytes());
		return crc32.getValue();
	}
	
}
