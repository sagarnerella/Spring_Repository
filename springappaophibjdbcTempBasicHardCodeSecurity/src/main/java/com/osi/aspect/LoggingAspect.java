package com.osi.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class LoggingAspect {

	
	public Object logMethodProcess(ProceedingJoinPoint proceedingJoinPoint){
		
		Object object=null;
		try {
			System.out.println("before execute  "+proceedingJoinPoint.getSignature().getName()+"() method");
			object = proceedingJoinPoint.proceed();
			System.out.println("after execute "+proceedingJoinPoint.getSignature().getName()+"() method");
			System.out.println("result "+object.toString());
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
	
	public Object printError(IllegalArgumentException ex){

		 System.out.println("There has been an exception: " + ex.toString());   
		return null;
	}
}
