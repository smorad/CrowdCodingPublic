package project.server.submit;

import java.util.ArrayList;

import com.googlecode.objectify.annotation.Subclass;
import com.googlecode.objectify.annotation.Unindexed;

@Unindexed
@Subclass
public class AceEditorPersist extends PersistObject {

	private String code;
	private String description; // from parent
	private String returnType;
	private ArrayList<String> parameters = new ArrayList<String>();
	private String methodName;
	private boolean stubCreated;

	public boolean getStubCreated() {
		return stubCreated;
	}

	public void setStubCreated(boolean b) {
		stubCreated = b;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String c) {
		code = c;
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

	public String getParameter(int index) {
		return parameters.get(index);
	}

	public String getMethodName() {
		return methodName;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setDescription(String s) {
		description = s;
	}

	public void setParameters(ArrayList<String> s) {
		parameters = s;
	}

	public void setMethodName(String s) {
		methodName = s;
	}

	public void setReturnType(String s) {
		returnType = s;
	}

	public void addParameter(String parameter) {
		parameters.add(parameter);

	}

}