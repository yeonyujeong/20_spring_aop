package com.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/*

	# AOP( Aspect-Oriented Programming ) 관점 지향 프로그래밍
	
	- 프로젝트 개발 과정에서 핵심 기능 외에 추가적이고, 다양한 부가(공통) 기능이 필요하다. (로깅,보안,트랜젝션,테스트)
	- 이 부가(공통)기능들은 프로젝트에 굉장히 중요한 역할을 하며 이 부가(공통)기능이 코드마다 반복적(중복)으로 나타나는 부분이 존재한다. 
	- 코드에서 비즈니스 핵심 로직과 부가기능을 분리하여 부가 로직을 따로 관리(모듈화)한다.
	- 종단(비즈니스 로직) 기능 , 횡단(관심,Aspect) 기능 
	- 부가 기능이 비즈니스 로직(핵심 기능)을 담은 클래스의 코드에 전혀 영향을 주지 않으면서 부가기능의 구현을 용이하게 할 수 있는 구조를 제공한다.
	- AOP는 OOP를 대체하는 새로운 개념이 아니라 OOP를 돕는 보조적 기술 중에 하나 이다.
	- 스프링 DI  : 의존성(new)주입
	  스프링 AOP  : 로직(code) 주입
	
	[ 용어 정리 ]
	
	Around (Advice)			: 대상 객체의 메서드 실행 전,후 및 예외 발생 모두 실행한다.
	Before (Advice)			: 대상 객체의 메서드 메서드 호출전에 수행한다.
	After (Advice)			: 대상 객체의 메서드 실행도중 예외 발생 여부와 상관없이 메서드 실행 후 실행한다.
	AfterReturning (Advice) : 대상 객체의 메서드가 실행 도중 예외없이 실행 성공한 경우에 실행한다.
	AfterThrowing (Advice)  : 대상 객체의 메서드가 실행 도중 예외가 발생한 경우에 실행한다.
	
	
	Aspect	   : 관점
	Advisor    : advice + Pointcut
	Advice	   : 핵심기능에 부여되는 부가기능 ( 위치 메서드에 적용될 부가 기능 )
	Pointcut   : Aspect 적용 위치 지정자      ( advice를 어디에 적용할지를 결정  )
	Joinpoint  : Aspect가 적용한 지점
	
	( 구현 예시 )
	
	1) pom.xml 파일에 AOP 관련 dependency 추가
	2) AOP설정 xml 파일에 aop autoproxy 지시
	3) target메소드에 추가 할 Aspect 생성
	4) 구현

*/



@Aspect	// 스프링 AOP에서 Aspect어노테이션을 사용해주어야 AOP기능이 동작한다.
public class MyAspect {
	
	@Pointcut("execution(* work())")	// 중복되는 메서드를 기술
	private void pointcut() {
		// 메서드의 내용은 의미가 없다.
	}
	
	
	// 메서드 호출 전
	//@Before("execution(public void com.spring.aop.ClassBoss.work())") // 특정 패키지.특정클래스.특정메서드 지정
	//@Before("execution(* work())")
	@Before("pointcut()")
	public void before() {
		System.out.println("===================================");
		System.out.println("AOP Before 메서트 호출 : 출근한다.");
	}
	
	// 메서드 호출 후
	//@After("execution(* work())")
	@After("pointcut()")
	public void after() {
		System.out.println("AOP After 메서트 호출 : 퇴근한다.");
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// 메서드 호출 전후
	@Around("execution(* getWorkTime())")
	public void around(ProceedingJoinPoint pjp) {
		
		// 메서드 호출 전
		System.out.println("=============================");
		long startTime = System.currentTimeMillis();
		// 메서드 호출 
		try {
			pjp.proceed();	// ProceedingJoinPoint의 proceed메서드를 호출하여 사용
		} catch (Throwable e) {
			e.printStackTrace();
		} 
		
		// 메서드 호출 후
		long endTime = System.currentTimeMillis();
		System.out.println("업무 소요 시간 : " + (endTime - startTime) );
		System.out.println("=============================\n");
	}
	
	
	
	
}
