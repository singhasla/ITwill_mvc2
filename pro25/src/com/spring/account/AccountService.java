package com.spring.account;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//서비스 클래스의 메소드는 단위 기능을 수행하므로 
//@Transactional 어노테이션기호를 서비스클래스에 적용해서 메소드별로 트랜잭션을 적용합니다.


//스프링이 제공하는 @Transactional 기호를 사용하면 트랜잭션 범위를 매우 쉽게 지정할수 있다.
//트랜잭션 범위에서 실행하고 싶은 메소드명 위에 붙여도 되고 ,
//트랜잭션 범위에서 실행하고 싶은 모든 메소드들이 존재하는 클래스명 위에 붙여도 된다.

//스프링은 @Transactional 기호가 붙은 클래스에 속해 있는 setAccDAO메소드, sendMoney메소드를 동일한 트랜잭션 범위에서 실행한다.
//따라서 sendMoney()메소드 내부에 있는 accDAO.updateBalance1(); accDAO.updateBalance2()메소드에서 실행하는 UPDATE구문 실행 작업은
//한 트랜잭션범위로 묶인다.

@Transactional(propagation=Propagation.REQUIRED)//@Transactional을 이용해 AccountService클래스의 모든 메소드에 트랜잭션을 적용합니다
public class AccountService {

	
	private AccountDAO accDAO;
	
	//action-service.xml파일에서 <property>태그를 이용해서 호출되는 setter메소드임
	//AccountDAO객체를 매개변수로 전달 받아 위 accDAO변수에 주입(저장)하기 위한 setter메소드임
	public void setAccDAO(AccountDAO accDAO){
		
		this.accDAO = accDAO;
	}
	
	
	//AccountController에서 호출하는 메소드로 계좌이체 요청 ~
	public void sendMoney() throws Exception{
		//account.xml파일의 두개의 UPDATE구문을 실행하기 위해  AccountDAO객체의 두개의 메소드를 호출함
		accDAO.updateBalance1(); //-> 홍길동이 김유신에게 500만원 이체 함으로써 홍길동의 1000만원에서 500백만원을 차감하기 위해 update구문 실행 명령 
		accDAO.updateBalance2(); //-> 김유신은 홍길동으로 부터 500만원을 이체 받았으므로  김유신의 계좌 잔액은 1000만원에서 1500만원이 되게 update구문 실행 명령
		
	}
		
}
