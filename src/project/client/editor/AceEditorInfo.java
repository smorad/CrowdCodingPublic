package project.client.editor;

import project.client.InfoObject;

import com.google.gwt.user.client.rpc.IsSerializable;


public class AceEditorInfo implements IsSerializable, InfoObject{
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
	@Override
	public void setKeyString(String s) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getKeyString() {
		// TODO Auto-generated method stub
		return null;
	}
}