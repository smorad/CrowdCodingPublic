package project.server.submit;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Unindexed;
@Unindexed
public class UnitTestPersist implements PersistObject{
	@Id 
	private Long id;
	
	private String code;
	
	private boolean isDone;
	
	private String methodDesc;
	
	private String testDesc;//from parent
	
	public UnitTestPersist(){
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
	public Long getId(){
		return id;
	}
	
	
	//from parent
	public String getMethodDesc(){
		return methodDesc;
	}
	public String getTestDesc(){
		return testDesc;
	}

	public void setMethodDesc(String s){
		methodDesc=s;
	}
	public void setTestDesc(String s){
		testDesc=s;
	}
	
	//for testing
		public String info(){
			return "methodDesc is: "+methodDesc
					+"\ntestDesc is: "+testDesc
					+"\ncode is: "+code;
		}
}
