package project.server.submit;

import java.util.ArrayList;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import project.client.entry.EntryMethodInfo;
import project.client.entry.EntryPointInfo;
import project.client.submission.SubmitService;
import project.client.tests.TestCaseInfo;
import project.client.tests.UnitTestInfo;
import project.client.userstory.UserStoryInfo;
import project.shared.PMF;

public class SubmitServiceImpl extends RemoteServiceServlet implements SubmitService {

	public void retrieve(UserStoryInfo storyInfo) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Key key = KeyFactory.createKey(UserStoryPersist.class.getSimpleName(), storyInfo.getName());

			// retrieve userstory
			//UserStoryPersist pInfo = pm.getObjectById(UserStoryPersist.class,
			//		key);
			UserStoryPersist pInfo=new UserStoryPersist();
			pInfo.setStory("sdifuhsf");//temporary testing demo thing
			
			
			
			storyInfo.setStory(pInfo.getStory());
			storyInfo.setChild(new EntryPointInfo());
			

			// retrieve entrypoint
			EntryPointPersist pChild1 = pInfo.getChild();
			if (pChild1==null||pChild1.getNumMethods() == 0) {
				pm.close();
				return;
			}
			EntryPointInfo child1 = storyInfo.getChild();
			for (int x = 0; x < pChild1.getNumMethods(); x++)
				child1.addMethod(new EntryMethodInfo());

			// retrieve entrymethods
			for (int x = 0; x < pChild1.getNumMethods(); x++) {
				EntryMethodInfo child2 = child1.getMethod(x);
				EntryMethodPersist pChild2 = pChild1.getMethod(x);
				child2.setMethodDescription(pChild2.getDescription());
				child2.setMethodName(pChild2.getName());
				for (int y = 0; y < pChild2.getNumParameters(); y++)
					child2.addParameter(pChild2.getParameter(y));
				if (pChild2.getTest() != null)
					child2.addTest();
			}

			// retrieve unittests
			ArrayList<TestCaseInfo> children3 = new ArrayList<TestCaseInfo>();
			ArrayList<TestCasePersist> pChildren3 = new ArrayList<TestCasePersist>();
			for (int x = 0; x < pChild1.getNumMethods(); x++) {
				EntryMethodPersist pChild2 = pChild1.getMethod(x);
				if (pChild2.getTest() == null)
					continue;
				TestCasePersist pChild3 = pChild2.getTest();
				TestCaseInfo child3 = child1.getMethod(x).getTest();
				pChildren3.add(pChild3);
				for (int i = 0; i < pChild3.getNumTests(); i++)
					child3.addTest(pChild3.getTest(i));
			}

			// retrieve unittests
			for (int x = 0; x < pChildren3.size(); x++) {
				for (int y = 0; y < pChildren3.get(x).getNumTests(); y++) {
					UnitTestInfo child4 = children3.get(x).getTestInfo(y);
					UnitTestPersist pChild4 = pChildren3.get(x).getTestInfo(y);
					child4.setCode(pChild4.getCode());
				}
			}
			pm.close();
		} finally {

		}
	}

	public void submit(UserStoryInfo info) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Key key = KeyFactory.createKey(UserStoryPersist.class.getSimpleName(), info.getName());

			// record userstory
			UserStoryPersist story = new UserStoryPersist();
			story.setKey(key);
			pm.makePersistent(story);
			story=pm.getObjectById(UserStoryPersist.class,
					key);
			
			story.setStory(info.getStory());
			story.setName(info.getName());
			story.setChild(new EntryPointPersist());
			

			// record entrypoint
			EntryPointInfo child1 = info.getChild();
			if (child1.getNumMethods() == 0) {
				pm.close();
				return;
			}
			EntryPointPersist pChild1 = story.getChild();
			for (int x = 0; x < child1.getNumMethods(); x++)
				pChild1.addMethod(new EntryMethodPersist());

			// record entrymethods
			for (int x = 0; x < child1.getNumMethods(); x++) {
				EntryMethodInfo child2 = child1.getMethod(x);
				EntryMethodPersist pChild2 = pChild1.getMethod(x);
				pChild2.setMethodDescription(child2.getDescription());
				pChild2.setMethodName(child2.getName());
				for (int y = 0; y < child2.getNumParameters(); y++)
					pChild2.addParameter(child2.getParameter(y));
				if (child2.getTest() != null)
					pChild2.addTest();
			}

			ArrayList<TestCaseInfo> children3 = new ArrayList<TestCaseInfo>();
			ArrayList<TestCasePersist> pChildren3 = new ArrayList<TestCasePersist>();
			// record testcases
			for (int x = 0; x < child1.getNumMethods(); x++) {
				EntryMethodInfo child2 = child1.getMethod(x);
				if (child2.getTest() == null)
					continue;
				TestCaseInfo child3 = child2.getTest();
				children3.add(child3);
				TestCasePersist pChild3 = pChild1.getMethod(x).getTest();
				pChildren3.add(pChild3);
				for (int i = 0; i < child3.getNumTests(); i++)
					pChild3.addTest(child3.getTest(i));
			}

			// record unittests
			for (int x = 0; x < children3.size(); x++) {
				for (int y = 0; y < children3.get(x).getNumTests(); y++) {
					UnitTestInfo child4 = children3.get(x).getTestInfo(y);
					UnitTestPersist pChild4 = pChildren3.get(x).getTestInfo(y);
					pChild4.setCode(child4.getCode());
				}
			}
			pm.close();

			// 
		} finally {
			//pm.close();
		}
	}

}
