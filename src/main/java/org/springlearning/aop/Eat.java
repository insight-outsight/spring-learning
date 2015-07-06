package org.springlearning.aop;

import org.springframework.stereotype.Component;

@Component
public class Eat {

	public void doIt(Integer i){
		System.out.println("eatting,"+i);
	}
}
