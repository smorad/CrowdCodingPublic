package project.client.editor;

import com.google.gwt.user.client.rpc.IsSerializable;


public class AceEditorInfo implements IsSerializable{
	private boolean isDone;
	private String code;
		
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