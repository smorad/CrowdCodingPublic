package project.server.submit;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class UnitTestPersist{
	@PrimaryKey
    private String key;
	
	@Persistent
	private String code;
	
	public void setCode(String code){
		this.code=code;
	}
	public String getCode(){
		return code;
	}
}
