package project.client.tests;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UnitTestInfo implements IsSerializable{
	private boolean isDone;
	private String code;
	
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
}
