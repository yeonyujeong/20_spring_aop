package com.spring.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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


/*
 *  # execution 명시자 
 *  
 *  - execution(수식어패턴 리턴타입패턴 클래스이름패턴?메서드이름패턴(파라미터패턴)) 
 *  - 각 패턴은 *을 이용하여 모든값을 표현할 수 있다.
 *  
 *  
 *  [패키지]
 *  com.spring.aop	  > com.spring.aop패키지를 타겟
 *  com.spring.aop..  > com.spring.aop로 시작하는 하위의 모든 패키지를 타겟
 *  
 *  [리턴타입]
 *  *		> 모든 리턴 타입 타겟
 *  void	> 리턴 타입이 void인 메서드만 타겟
 *  !void	> 리턴 타입이 void가 아닌 메서드만 타겟 
 *  
 *  [매개 변수 지정]
 *  (..)		>  0개 이상의 모든 파라미터 타겟
 *  (*)			> 1개의 파라미터만 타겟
 *  (*,*)		> 2개의 파라미터만 타겟
 *  (String,*)	> 2개의 파라미터중 첫번째 파라미터가 String타입만 타겟
 *  
 *  
 *  샘플 예시
 *  
 *  execution(public void set*(..)) 					> 리턴 타입이 void이고 메서드 이름이 set으로 시작하고 파라미터가 0개 이상인 메서드 타겟
 *  execution(* abc.*.*()) 								> abc패키지에 속한 파라미터가 없는 모든 메서드 타겟
 *  execution(* abc..*.*(..)) 							> abc패키지 및 하위 패키지에 있는 파라미터가 0개 이상인 메서드 타겟
 *  execution(Long com.spring.aop.ClassBoss.work(..))   > 리턴 타입인 Long인 com.spring.aop 패키지 안의 ClassBoss클래스의 work 메서드 타겟
 *  execution (* get*(*)) 								> 이름이 get으로 시작하고 파라미터가 한 개인 메서드 타겟
 *  execution(* get*(*,*)) 								> 이름이 get으로 시작하고 파라미터가 2개인 메서드 타겟
 *  execution(* read*(Integer,..)) 						> 메서드 이름이 read 로시작하고, 첫번째 파라미터 타입이 Integer이며 한개 이상의 파라미터를 갖는 메서드 타겟
 *  
 * */



@Aspect	// 스프링 AOP에서 Aspect어노테이션을 사용해주어야 AOP기능이 동작한다.
public class MyAspect {
	
	private static Logger logger = LoggerFactory.getLogger(MyAspect.class);
	
	
	
	@Pointcut("execution(* work())")	// 중복되는 메서드를 기술
	private void pointcut() {
		// 메서드의 내용은 의미가 없다.
	}
	
	
	// 메서드 호출 전
	//@Before("execution(public void com.spring.aop.ClassBoss.work())") // 특정 패키지.특정클래스.특정메서드 지정
	//@Before("execution(* work())")
	@Before("pointcut()")
	public void before() {
		//System.out.println("AOP Before 메서트 호출 : 출근한다.");
		logger.info("AOP Before 메서트 호출 : 출근한다.");
	}
	
	// 메서드 호출 후
	//@After("execution(* work())")
	@After("pointcut()")
	public void after() {
		//System.out.println("AOP After 메서트 호출 : 퇴근한다.");
		logger.info("AOP After 메서트 호출 : 퇴근한다.");
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	
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
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	// 호출된 메서드가 성공적으로 실행된 후
	@AfterReturning("execution(* normal(..))")
	public void afterReturning(JoinPoint jp) {		 // JoinPoint를 통하여 메서드의 파라미터를 전달받을 수 있다.
		
		System.out.println("1:" + Arrays.toString(jp.getArgs()));	// 메서드의 파라미터를 확인
		System.out.println("2:" + jp.getKind());					// 메서드의 종류
		System.out.println("3:" + jp.getSignature().getName());		// 어드바이즈메서드에 대한 설명(descrption)이 반환
		System.out.println("4:" + jp.getTarget().toString());		// 대상 객체를 반환
		System.out.println("5:" + jp.getThis().toString());			// 프록시 객체를 반환	
		System.out.println("AOP AfterReturning메서트 호출 \n\n");
		
	}
	
	
	// 호출된 메서드에서 예외가 발생한 후
	@AfterThrowing("execution(public void com.spring.aop.ClassEmployee.mistake())")
	public void afterThrowing() {
		System.out.println("AOP AfterThrowing 메서드 호출 \n");
	}
	
	
	
	
	
}
