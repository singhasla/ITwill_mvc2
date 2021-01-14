package com.spring.ex02;


//로그인을 위해 입력한 아이디와 이름을 임시로 저장 해 놓는 VO클래스 
public class LoginVO {

		private String userID;
		private String userName;
		
		//getter,setter메소드 
		public String getUserID() {
			return userID;
		}
		public void setUserID(String userID) {
			this.userID = userID;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
	
}
