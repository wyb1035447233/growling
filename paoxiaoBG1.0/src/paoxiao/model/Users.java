package paoxiao.model;

import java.sql.Date;

public class Users {

	private int userId;
	private String userName;
	private String userHead;
	private String userPassword;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUserHead() {
		return userHead;
	}
	
	public void setUserHead(String userHead) {
		this.userHead = userHead;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}