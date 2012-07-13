package project.client.editor;


public class AceEditorInfo{
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