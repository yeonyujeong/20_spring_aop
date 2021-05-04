package com.spring.aop;

public class ClassManager implements IPosition {

	@Override
	public void work() {
		System.out.println("관리자의 일을 한다.");

	}

	@Override
	public void getWorkTime() {
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void normal(String title , int salary) {
		System.out.println("직급 : " + title);
		System.out.println("급여 : " + salary);
	}

	@Override
	public void mistake() {
		// TODO Auto-generated method stub

	}




}
