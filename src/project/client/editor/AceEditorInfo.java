package project.client.editor;

import java.util.ArrayList;

import project.client.InfoObject;
import project.client.tests.TestCaseInfo;

import com.google.gwt.user.client.rpc.IsSerializable;


public class AceEditorInfo implements IsSerializable, InfoObject{
	private boolean isDone;
	private Long key;
	private String code;
	private String description; //from parent
	private String returnType;
	private ArrayList<String> parameters = new ArrayList<String>();
	private String methodName;
	private TestCaseInfo child; 
	private boolean stubCreated;
	
	
	public boolean getStubCreated(){
		return stubCreated;
	}
	public void setStubCreated(boolean b){
		stubCreated=b;
	}
	
	
	@Override
	public void setDone(boolean b) {
		isDone=b;	
	}
	
	public String getCode(){
		return code;
	}
	public void setCode(String c){
		code=c;
	}

	@Override
	public boolean isDone() {
	return isDone;
	}

	@Override
	public void setKeyString(Long s) {
		key=s;
	}

	@Override
	public Long getKeyString() {
		return key;
	}

	@Override
	public String info() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public ArrayList<String> getParameters() {
		return parameters;
	}
	public String getParameter(int index){
		return parameters.get(index);
	}

	public String getMethodName() {
		return methodName;
	}

	public String getReturnType() {
		if(returnType==null)
			return "void";
		return returnType;
	}
	public void setDescription(String s){
		description = s;
	}
	public void setParameters(ArrayList<String> s){
		parameters=s;
	}
	public void setMethodName(String s){
		methodName = s;
	}
	public void setReturnType(String s){
		if(s!=null)
			returnType = s;
		else{s="void";}
	}

}