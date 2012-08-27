package project.client.login;

import java.io.Serializable;

public class LoginInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean loggedIn = false;
	private String loginUrl;
	private String logoutUrl;
	private String emailAddress;
	private String nickname;
	private double userStory;
	private double ePoint;
	private double sketch;
	private double testCase;
	private double unit;

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public double getUserStory() {
		return userStory;
	}

	public void setUserStory(double userStory) {
		this.userStory = userStory;
	}

	public double getePoint() {
		return ePoint;
	}

	public void setePoint(double ePoint) {
		this.ePoint = ePoint;
	}

	public double getSketch() {
		return sketch;
	}

	public void setSketch(double sketch) {
		this.sketch = sketch;
	}

	public double getTestCase() {
		return testCase;
	}

	public void setTestCase(double testCase) {
		this.testCase = testCase;
	}

	public double getUnit() {
		return unit;
	}

	public void setUnit(double unit) {
		this.unit = unit;
	}
}