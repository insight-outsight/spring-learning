package org.springlearning.converters;

import org.springframework.core.convert.converter.Converter;
import org.springlearning.model.UserLevelEnum;

public class UserLevelEnumConverter implements Converter<String, UserLevelEnum> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fasterxml.jackson.databind.util.Converter#convert(java.lang.Object)
	 * 
	 * @author: ShangJianguo 2014-6-12 下午4:56:30
	 */
	@Override
	public UserLevelEnum convert(String sourceValue) {
		String value = sourceValue.trim();
		try {
			int valueInt = Integer.parseInt(value);
			return UserLevelEnum.valueOf(valueInt);
		} catch (Exception e) {
			return null;
		}
	}

}
