package project.server.submit;

import java.util.ArrayList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.client.rpc.IsSerializable;


@PersistenceCapable
public class EntryMethodPersist implements IsSerializable, PersistObject{
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent
	private String methodDescription;
	
	@Persistent
	private String methodName;
	
	@Persistent
	private ArrayList<String> parameters;
	
	@Persistent
	private TestCasePersist test;//child
	
	@Persistent
	private boolean isDone;
	
	public EntryMethodPersist(){
		methodDescription="description";
		methodName="name";
		parameters=new ArrayList<String>();
		test=new TestCasePersist();
	}
	
	public void setMethodDescription(String description){
		methodDescription=description;
	}
	public void setMethodName(String name){
		methodName=name;
	}
	public void addParameter(String parameter){
		parameters.add(parameter);
	}
	public void setParameters(ArrayList<String> parameters){
		this.parameters=parameters;
	}
	public void removeParameter(String parameter){
		parameters.remove(parameter);
	}
	public void removeParameter(int index){
		parameters.remove(index);
	}
	
	public String getDescription(){
		return methodDescription;
	}
	public String getName(){
		return methodName;
	}
	public String getParameter(int index){
		return parameters.get(index);
	}
	public int getNumParameters(){
		return parameters.size();
	}
	
	public void addTest(){
		test=new TestCasePersist();
	}
	public void removeTest(){
		test=null;
	}
	public TestCasePersist getTest(){
		return test;
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
	
	
	
	
}
