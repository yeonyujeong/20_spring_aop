package com.spring.aop;

public class ClassEmployee implements IPosition {

	@Override
	public void work() {
		System.out.println("직원의 일을 한다.");
		
	}

	@Override
	public void getWorkTime() {
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void normal(String title , int salary) {	// AfterReturning Advice 예시
		System.out.println("직급 : " + title);
		System.out.println("급여 : " + salary);

	}

	@Override
	public void mistake() {			// AfterThrowing Advice 예시
		System.out.println(3/0);

	}


}
