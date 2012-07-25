package project.server.submit;


public class AceEditorPersist implements PersistObject{
	private String code;
	
	private boolean isDone;
		
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
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String info() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}