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
	public void normal() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mistake() {
		// TODO Auto-generated method stub

	}

}
