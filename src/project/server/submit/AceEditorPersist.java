package project.server.submit;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class AceEditorPersist{
	@Persistent
	private String code;
	
	@PrimaryKey
    private String key;
	
	public void setCode(String code){
		this.code=code;
	}
	public String getCode(){
		return code;
	}
	
}