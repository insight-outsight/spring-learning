package org.springlearning.aop.hotswappabletargetsource;

public class TestUtils {

	public static void printClassName(Object targetObject) {
		System.out.println(targetObject.getClass().getName());
	}

	public static void assertNotSame(Object targetObject0, Object targetObject1) {
		System.out.println(targetObject0!=targetObject1);
	}
	
	public static void assertSame(Object targetObject0, Object targetObject1) {
		System.out.println(targetObject0==targetObject1);
	}
	
}
