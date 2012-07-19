package project.client.tests;

import project.client.InfoObject;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UnitTestInfo implements IsSerializable, InfoObject{
	private boolean isDone;
	private String code;
	private String keyString;
	private String methodDesc, testDesc;//from parent
	
	public UnitTestInfo(){
		code="code";
	}
	
	public void setCode(String code){
		this.code=code;
	}
	public String getCode(){
		return code;
	}
	public boolean isDone(){
		return isDone;
	}
	public void setDone(boolean bool){
		isDone=bool;
	}
	
	public void setKeyString(String s){
		keyString=s;
	}
	public String getKeyString(){
		return keyString;
	}
	
	
	//from parent
	public void setMethodDesc(String s){
		methodDesc=s;
	}
	public void setTestDesc(String s){
		testDesc=s;
	}
	
	
	public String getMethodDesc(){
		return methodDesc;
	}
	public String getTestDesc(){
		return testDesc;
	}
}
