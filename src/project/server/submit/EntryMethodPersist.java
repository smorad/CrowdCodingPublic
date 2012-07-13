package project.server.submit;

import java.util.ArrayList;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable
public class EntryMethodPersist {
	@PrimaryKey
    private String key;
	
	@Persistent
	private String methodDescription;
	
	@Persistent
	private String methodName;
	
	@Persistent
	private ArrayList<String> parameters=new ArrayList<String>();
	
	@Persistent
	private TestCasePersist test;//child
	
	@Persistent
	private boolean isDone;
	
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
	
	
	
	
}
