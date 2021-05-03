package com.spring.aop;

public class SampleClass {
	
	//AOP 프로그래밍 이해 예시1
	public void sample1() {
		
		// Boss , Employee , Manager 공통
		System.out.println("출근한다.");
		
		// Boss , Employee , Manager마다 개별 업무
		System.out.println("사장의 일을 한다");
		
		System.out.println("관리자의 일을 한다");
		
		System.out.println("직원의 일을 한다");

		// Boss , Employee , Manager 공통
		System.out.println("퇴근한다.");
	}
	
	//AOP 프로그래밍 이해 예시2
	public void sample2() {
	
		try {
			
			// DB Connection 객체 생성 (insert , select , delete, update 공통)
			// insert , update, delete, select 마다 개별 로직
			
		} catch (Exception e) {
			// 예외처리 (insert , select , delete, update 공통)
		}
	
		
	}
	
	//AOP 프로그래밍 이해 예시3
		public void sample3() {
	
			// 로깅, 응답속도 테스트
			
			// 핵심 로직(비즈니스 로직)
			
			// 로깅, 응답속도 테스트
			
		}	
			
	
}
