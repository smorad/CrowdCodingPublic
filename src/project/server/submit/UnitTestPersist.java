package project.server.submit;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.client.rpc.IsSerializable;

@PersistenceCapable
public class UnitTestPersist implements IsSerializable, PersistObject{
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent
	private String code;
	
	@Persistent
	private boolean isDone;
	
	@Persistent
	private String methodDesc;
	
	@Persistent
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
	
	public Key getKey(){
		return key;
	}
	public String getKeyString(){
		return KeyFactory.keyToString(key);
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
}
