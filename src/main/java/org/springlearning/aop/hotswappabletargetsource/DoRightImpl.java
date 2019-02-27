package org.springlearning.aop.hotswappabletargetsource;

public class DoRightImpl implements IDoable {

	@Override
	public void doSomething() {
		System.out.println("do something right.");
	}

}
