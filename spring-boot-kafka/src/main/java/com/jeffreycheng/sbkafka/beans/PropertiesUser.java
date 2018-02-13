package com.jeffreycheng.sbkafka.beans;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


@SessionScope
@Component
public class PropertiesUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long timeSlot = System.currentTimeMillis();
	private String accessKey = null; 
	
	private boolean isLogin;
	    
	@Autowired
	public PropertiesUser(
			@Value("${member.login.isLogin}") boolean isLogin

	){
		this.isLogin = isLogin;
	}
	
	public Long getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(Long timeSlot) {
		this.timeSlot = timeSlot;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public boolean getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	                                                
}
