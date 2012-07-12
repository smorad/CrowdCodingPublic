package project.client.info;

import java.util.ArrayList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class MethodInfo {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private String methodDesc;
	@Persistent
	private String methodName;
	@Persistent
	private ArrayList<String> parameters;
	@Persistent
	private ArrayList<UnitTestInfo> testInfos;
	
	public MethodInfo(){
		parameters=new ArrayList<String>();
		testInfos=new ArrayList<UnitTestInfo>();
	}
	
	
	public void clearAll(){
		methodDesc=new String();
		methodName=new String();
		parameters=new ArrayList<String>();
	}
	public void clearTests(){
		testInfos=new ArrayList<UnitTestInfo>();
	}
	
	public void setMethodDesc(String desc){
		methodDesc=desc;
	}
	public void setMethodName(String name){
		methodName=name;
	}
	public void addParameter(String parameter){
		parameters.add(parameter);
	}
	public void removeParameter(){
		if(!parameters.isEmpty())
			parameters.remove(parameters.size()-1);
	}
	public void addTestInfo(){
		testInfos.add(new UnitTestInfo());
	}
	public void removeTestInfo(int index){
		if(!testInfos.isEmpty())
			testInfos.remove(index);
	}
	
	public String getMethodDesc(){
		return methodDesc;
	}
	public String getMethodName(){
		return methodName;
	}
	public String getParameter(int index){
		return parameters.get(index);
	}
	public int getNumParameters(){
		return parameters.size();
	}
	public UnitTestInfo getUnitTestInfo(int index){
		return testInfos.get(index);
	}
	public int getNumUnitTests(){
		return testInfos.size();
	}
	
	public void setKey(Key key) {
        this.key = key;
    }
	 public Key getKey() {
	        return key;
	    }
}
