package project.client.tests;

import java.util.ArrayList;

import project.server.submit.UnitTestPersist;

import com.google.gwt.user.client.rpc.IsSerializable;


public class TestCaseInfo implements IsSerializable{
	private boolean isDone;
	private ArrayList<String> tests=new ArrayList<String>();
	private ArrayList<UnitTestInfo> testInfos=new ArrayList<UnitTestInfo>();
	
	public TestCaseInfo(){
		testInfos=new ArrayList<UnitTestInfo>();
		tests=new ArrayList<String>();
	}
	
	public void addTest(String test){
		tests.add(test);
		testInfos.add(new UnitTestInfo());
	}
	public void removeTest(String test){
		int i=tests.indexOf(test);
		tests.remove(test);
		testInfos.remove(i);
	}
	public void setTests(ArrayList<String> tests){
		this.tests=tests;
		testInfos=new ArrayList<UnitTestInfo>();
		for(int x=0; x<tests.size(); x++)
			testInfos.add(new UnitTestInfo());
	}
	
	public String getTest(int index){
		return tests.get(index);
	}
	public UnitTestInfo getTestInfo(int index){
		return testInfos.get(index);
	}
	public int getNumTests(){
		return tests.size();
	}
	public boolean isDone(){
		return isDone;
	}
	public void setDone(boolean bool){
		isDone=bool;
	}
	
	
}
