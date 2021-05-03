package com.spring.aop;

public class ClassBoss implements IPosition {

	@Override
	public void work() {
		System.out.println("사장의 일을 한다.");

	}

	@Override
	public void getWorkTime() {
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void normal() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mistake() {
		// TODO Auto-generated method stub

	}

}
