package project.client.entry;

import java.util.ArrayList;

import project.client.InfoObject;
import project.client.editor.AceEditorInfo;
import project.client.tests.TestCaseInfo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class EntryMethodInfo implements IsSerializable, InfoObject {

	private boolean isDone;
	private String methodDescription;
	private String methodName;
	private Long keyString;
	private String returnType;
	private ArrayList<String> parameters;
	private TestCaseInfo test; // child
	private AceEditorInfo code; // child

	public EntryMethodInfo() {
		methodDescription = "description";
		methodName = "name";
		returnType = "type";
		parameters = new ArrayList<String>();
		test = new TestCaseInfo();
		code = new AceEditorInfo();
	}

	public void setMethodDescription(String description) {
		methodDescription = description;
		test.setDescription(description);
		code.setDescription(description);
		System.out.println("editor object created with desc: " + description);
	}

	public void setMethodName(String name) {
		methodName = name;
	}

	public void addParameter(String parameter) {
		parameters.add(parameter);
	}

	public void setParameters(ArrayList<String> parameters) {
		this.parameters = parameters;
	}

	public void removeParameter(String parameter) {
		parameters.remove(parameter);
	}

	public void removeParameter(int index) {
		parameters.remove(index);
	}

	public String getDescription() {
		return methodDescription;
	}

	public String getName() {
		return methodName;
	}

	public String getParameter(int index) {
		return parameters.get(index);
	}

	public int getNumParameters() {
		return parameters.size();
	}

	public void addTest() {
		test = new TestCaseInfo();
	}

	public void removeTest() {
		test = null;
	}

	public TestCaseInfo getTest() {
		return test;
	}

	@Override
	public boolean isDone() {
		return isDone;
	}

	@Override
	public void setDone(boolean bool) {
		isDone = bool;
	}

	@Override
	public void setKeyString(Long s) {
		keyString = s;
	}

	@Override
	public Long getKeyString() {
		return keyString;
	}

	// for testing, this prints out debug info
	@Override
	public String info() {
		return "methodDescription is: " + methodDescription
				+ "\nmethodName is: " + methodName;
	}

	public String[] getParametersAsArray() {
		return (String[]) parameters.toArray();
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String r) {
		returnType = r;
		code.setReturnType(r); // changed
	}

	public ArrayList<String> getParameters() {
		return parameters;
	}
}
