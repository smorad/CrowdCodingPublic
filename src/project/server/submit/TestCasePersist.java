package project.server.submit;

import java.util.ArrayList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.gwt.user.client.rpc.IsSerializable;

import project.client.tests.UnitTestInfo;

@PersistenceCapable
public class TestCasePersist implements IsSerializable{
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent
	private ArrayList<String> tests;
	
	@Persistent
	private ArrayList<UnitTestPersist> testInfos;
	
	@Persistent
	private boolean isDone;
	
	public TestCasePersist(){
		testInfos=new ArrayList<UnitTestPersist>();
		tests=new ArrayList<String>();
	}

	
	public void addTest(String test){
		tests.add(test);
		testInfos.add(new UnitTestPersist());
	}
	public void removeTest(String test){
		int i=tests.indexOf(test);
		tests.remove(test);
		testInfos.remove(i);
	}
	public void setTests(ArrayList<String> tests){
		this.tests=tests;
		testInfos=new ArrayList<UnitTestPersist>();
		for(int x=0; x<tests.size(); x++)
			testInfos.add(new UnitTestPersist());
	}
	
	public String getTest(int index){
		return tests.get(index);
	}
	public UnitTestPersist getTestInfo(int index){
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