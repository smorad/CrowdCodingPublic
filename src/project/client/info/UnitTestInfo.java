package project.client.info;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class UnitTestInfo {
	@Persistent
	private String testDesc;
	@Persistent
	private String code;
	
	public void setDesc(String desc){
		testDesc=desc;
	}
	public void setCode(String code){
		this.code=code;
	}
	public String getTestDesc(){
		return testDesc;
	}
	public String getCode(){
		return code;
	}
}
