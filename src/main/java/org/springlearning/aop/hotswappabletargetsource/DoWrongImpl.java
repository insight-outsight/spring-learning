package org.springlearning.aop.hotswappabletargetsource;

public class DoWrongImpl implements IDoable {

	@Override
	public void doSomething() {
		System.out.println("do something wrong.");
	}

}
