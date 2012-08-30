package project.client.tests;

import project.client.InfoObject;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UnitTestInfo implements IsSerializable, InfoObject {

	private boolean isDone;
	private String code;
	private Long keyString;
	private String methodDesc, testDesc;// from parent

	public UnitTestInfo() {
		code = "code";
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	@Override
	public boolean isDone() {
		return isDone;
	}

	@Override
	public void setDone(boolean bool) {
		isDone = bool;
	}

	@Override
	public void setKeyString(Long s) {
		keyString = s;
	}

	@Override
	public Long getKeyString() {
		return keyString;
	}

	// from parent
	public void setMethodDesc(String s) {
		methodDesc = s;
	}

	public void setTestDesc(String s) {
		testDesc = s;
	}

	public String getMethodDesc() {
		return methodDesc;
	}

	public String getTestDesc() {
		return testDesc;
	}

	// for testing
	@Override
	public String info() {
		return "methodDesc is: " + methodDesc + "\ntestDesc is: " + testDesc
				+ "\ncode is: " + code;
	}
}
