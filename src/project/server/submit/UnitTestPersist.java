package project.server.submit;

import com.googlecode.objectify.annotation.Subclass;
import com.googlecode.objectify.annotation.Unindexed;
@Unindexed
@Subclass
public class UnitTestPersist extends PersistObject{
	private String code;
	private String methodDesc;
	private String testDesc;//from parent
	
	public void setCode(String code){
		this.code=code;
	}
	public String getCode(){
		return code;
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
