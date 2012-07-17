package project.client.entry;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

import project.client.tests.TestCaseInfo;
import project.server.submit.TestCasePersist;


public class EntryMethodInfo implements IsSerializable{

	private boolean isDone;
	private String methodDescription;
	
	private String methodName;
	
	private ArrayList<String> parameters;
	
	private TestCaseInfo test;//child
	
	public EntryMethodInfo(){
		methodDescription="description";
		methodName="name";
		parameters=new ArrayList<String>();
		test=new TestCaseInfo();
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
		test=new TestCaseInfo();
	}
	public void removeTest(){
		test=null;
	}
	
	public TestCaseInfo getTest(){
		return test;
	}
	public boolean isDone(){
		return isDone;
	}
	public void setDone(boolean bool){
		isDone=bool;
	}
	
	
	
	
	
}
