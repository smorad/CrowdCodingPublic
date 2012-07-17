package project.server.submit;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.rpc.IsSerializable;

@PersistenceCapable
public class AceEditorPersist implements IsSerializable{
	@Persistent
	private String code;
	
	@Persistent
	private boolean isDone;
	
	@PrimaryKey
    private String key;
	
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