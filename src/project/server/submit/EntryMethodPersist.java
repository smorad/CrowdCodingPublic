package project.server.submit;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Id;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Unindexed;

@Unindexed
public class EntryMethodPersist {

	@Id
	private Long id;
	private String methodDescription;
	private String methodName;
	private ArrayList<String> parameters;
	private Key<TestCasePersist> test;// child
	private Key<AceEditorPersist> code; // child
	private boolean isDone;
	private String returnType;

	public void setMethodDescription(String description) {
		methodDescription = description;
		if (test != null) {
			Logger.getLogger("NameOfYourLogger").log(Level.SEVERE,
					"desc is: " + description);
			TestCasePersist t = getTest();
			t.setDescription(description);
			ObjectifyService.begin().put(t);
			Logger.getLogger("NameOfYourLogger").log(Level.SEVERE,
					getTest().getDescription());
		}
	}

	public void setMethodName(String name) {
		methodName = name;
	}

	public void addParameter(String parameter) {
		if (parameters == null) {
			parameters = new ArrayList<String>();
		}
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

	public void newTest() {
		Objectify o = ObjectifyService.begin();
		if (test != null)
			o.delete(test);
		TestCasePersist t = new TestCasePersist();
		t.setDescription(methodDescription);
		test = o.put(t);
		o.get(test);

	}

	public void newCode() { // WORK HERE!
		Objectify o = ObjectifyService.begin();
		if (code != null)
			o.delete(code);
		AceEditorPersist a = new AceEditorPersist();
		a.setDescription(methodDescription);
		a.setMethodName(methodName);
		a.setReturnType(returnType);
		if (parameters != null)
			a.setParameters(parameters);
		else {
			addParameter("");
			a.addParameter(getParameter(0));
		}
		a.setReturnType(returnType);
		code = o.put(a);
		o.get(code);
	}

	public AceEditorPersist getCode() {
		Objectify o = ObjectifyService.begin();
		return o.get(code);
	}

	public TestCasePersist getTest() {
		Objectify o = ObjectifyService.begin();
		return o.get(test);
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean bool) {
		isDone = bool;
	}

	public Long getId() {
		return id;
	}

	// for testing
	public String info() {
		return "methodDescription is: " + methodDescription
				+ "\nmethodName is: " + methodName;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType2) {
		returnType = returnType2;
	}

}
