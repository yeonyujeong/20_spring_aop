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
	public void normal() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mistake() {
		// TODO Auto-generated method stub

	}

}
