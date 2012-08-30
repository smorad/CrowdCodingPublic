package project.client.tests;

import java.util.ArrayList;

import project.client.InfoObject;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TestCaseInfo implements IsSerializable, InfoObject {

	private boolean isDone;
	private ArrayList<String> tests = new ArrayList<String>();
	private ArrayList<UnitTestInfo> testInfos = new ArrayList<UnitTestInfo>();
	private Long keyString;
	private String description;// from parent
	private String testDescription;

	public TestCaseInfo() {
		testInfos = new ArrayList<UnitTestInfo>();
		tests = new ArrayList<String>();
	}

	public void setTestDescription(String s) {
		testDescription = s;
	}

	public String getTestDescription() {
		return testDescription;
	}

	public void addTest(String test) {
		tests.add(test);
		UnitTestInfo u = new UnitTestInfo();
		u.setMethodDesc(getDescription());
		u.setTestDesc(test);
		testInfos.add(u);
	}

	public void removeTest(String test) {
		int i = tests.indexOf(test);
		tests.remove(test);
		testInfos.remove(i);
	}

	public void setTests(ArrayList<String> tests) {
		this.tests = tests;
		testInfos = new ArrayList<UnitTestInfo>();
		for (int x = 0; x < tests.size(); x++)
			testInfos.add(new UnitTestInfo());
	}

	public String getTest(int index) {
		return tests.get(index);
	}

	public UnitTestInfo getTestInfo(int index) {
		return testInfos.get(index);
	}

	public int getNumTests() {
		return tests.size();
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

	// from parent
	public void setDescription(String s) {
		description = s;
	}

	public String getDescription() {
		return description;
	}

	// for testing
	@Override
	public String info() {
		return "testDescription is: " + description;
	}

}
