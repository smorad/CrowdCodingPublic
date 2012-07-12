package project.client.tests;

import java.util.ArrayList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class TestCaseInfo {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent
	private ArrayList<String> tests;
	
	public void addTest(String test){
		tests.add(test);
	}
	public void removeTest(String test){
		tests.remove(test);
	}
	public void setTests(ArrayList<String> tests){
		this.tests=tests;
	}
	
	public String getTest(int index){
		return tests.get(index);
	}
	public int getNumTests(){
		return tests.size();
	}
	
	
	
}
