package project.server.submit;

import java.util.ArrayList;
import java.util.Collection;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Subclass;
import com.googlecode.objectify.annotation.Unindexed;

@Unindexed
@Subclass
public class TestCasePersist extends PersistObject {

	private ArrayList<String> tests = new ArrayList<String>();
	private ArrayList<Key<UnitTestPersist>> testInfos = new ArrayList<Key<UnitTestPersist>>(); // child
	private String description;// from parent
	private String caseDescription;

	public void addTest(String test) {
		tests.add(test);
		UnitTestPersist u = new UnitTestPersist();
		u.setMethodDesc(getDescription());
		u.setTestDesc(test);
		Objectify o = ObjectifyService.begin();
		testInfos.add(o.put(u));
		o.get(testInfos.get(testInfos.size() - 1));
	}

	public void removeTest(String test) {
		int i = tests.indexOf(test);
		tests.remove(test);
		Objectify o = ObjectifyService.begin();
		o.delete(testInfos.remove(i));
	}

	public void setTests(ArrayList<String> tests) {
		this.tests = tests;
		Objectify o = ObjectifyService.begin();
		o.delete(testInfos);
		testInfos = new ArrayList<Key<UnitTestPersist>>();
		for (int x = 0; x < tests.size(); x++) {
			testInfos.add(o.put(new UnitTestPersist()));
			o.get(testInfos.get(x));
		}
	}

	public String getTest(int index) {
		return tests.get(index);
	}

	public UnitTestPersist getTestInfo(int index) {
		Objectify o = ObjectifyService.begin();
		return o.get(testInfos.get(index));
	}

	public int getNumTests() {
		return tests.size();
	}

	public Collection<UnitTestPersist> getAllUnitTests() {
		Objectify o = ObjectifyService.begin();
		return o.get(testInfos).values();
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
		return "description is: " + description;
	}

}