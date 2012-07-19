package project.server.submit;

import java.util.ArrayList;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import project.client.InfoObject;
import project.client.entry.EntryMethodInfo;
import project.client.entry.EntryPointInfo;
import project.client.submission.SubmitService;
import project.client.tests.TestCaseInfo;
import project.client.tests.UnitTestInfo;
import project.client.userstory.UserStoryInfo;
import project.shared.PMF;

public class SubmitServiceImpl extends RemoteServiceServlet implements SubmitService {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InfoObject retrieve(String name){
		PersistenceManager pm=PMF.get().getPersistenceManager();
		try{
			Key key=KeyFactory.createKey(UserStoryPersist.class.getSimpleName(), name);
			UserStoryPersist story=pm.getObjectById(UserStoryPersist.class, key);
			
			PersistObject pInfo=story;//chooseRandomly(story);
			
			if(pInfo instanceof UserStoryPersist)
				return transferToClient((UserStoryPersist)pInfo);
			if(pInfo instanceof EntryPointPersist)
				return transferToClient((EntryPointPersist)pInfo);
			if(pInfo instanceof TestCasePersist)
				return transferToClient((TestCasePersist)pInfo);
			if(pInfo instanceof UnitTestPersist)
				return transferToClient((UnitTestPersist)pInfo);
			return null;
			
		}
		finally{
			pm.close();
		}
	}
	
	private PersistObject chooseRandomly(UserStoryPersist story){
		ArrayList<PersistObject> list = new ArrayList<PersistObject>();
		if(!story.isDone())
			list.add(story);//add story
		
		EntryPointPersist ePoint=story.getChild();
		if(!ePoint.isDone())
			list.add(ePoint);  //add entry point
		
		for(int i= 0; i<ePoint.getNumMethods(); i++){
			EntryMethodPersist e=ePoint.getMethod(i);
			TestCasePersist t=e.getTest();
			if(!t.isDone())
				list.add(t);
			for(int x=0; x<t.getNumTests(); x++){
				UnitTestPersist u=t.getTestInfo(x);
				if(!u.isDone())
					list.add(u);
			}
		}
		int a=(int)(Math.random()*list.size());
		PersistObject p=list.get(a);
		return p;
	}
	
	private UserStoryInfo transferToClient(UserStoryPersist pInfo){
		UserStoryInfo result=new UserStoryInfo();
		result.setStory(pInfo.getStory());
		result.setName(pInfo.getName());
		result.setKeyString(KeyFactory.keyToString(pInfo.getKey()));
		result.setDone(pInfo.isDone());
		return result;
	}
	private EntryPointInfo transferToClient(EntryPointPersist pInfo){
		EntryPointInfo result=new EntryPointInfo();
		result.setStory(pInfo.getStory());
		for(int x=0; x<pInfo.getNumMethods();x++){
			EntryMethodInfo e=new EntryMethodInfo();
			EntryMethodPersist p=pInfo.getMethod(x);
			e.setMethodDescription(p.getDescription());
			e.setMethodName(p.getName());
			for(int i=0; i<p.getNumParameters(); i++)
				e.addParameter(p.getParameter(i));
			result.addMethod(e);
		}
		result.setKeyString(KeyFactory.keyToString(pInfo.getKey()));
		result.setDone(pInfo.isDone());
		return result;
	}
	private TestCaseInfo transferToClient(TestCasePersist pInfo){
		TestCaseInfo result=new TestCaseInfo();
		result.setDescription(pInfo.getDescription());
		for(int x=0; x<pInfo.getNumTests(); x++){
			result.addTest(pInfo.getTest(x));
		}
		result.setKeyString(KeyFactory.keyToString(pInfo.getKey()));
		result.setDone(pInfo.isDone());
		return result;
	}
	private UnitTestInfo transferToClient(UnitTestPersist pInfo){
		UnitTestInfo result=new UnitTestInfo();
		result.setCode(pInfo.getCode());
		result.setMethodDesc(pInfo.getMethodDesc());
		result.setTestDesc(pInfo.getMethodDesc());
		result.setKeyString(KeyFactory.keyToString(pInfo.getKey()));
		result.setDone(pInfo.isDone());
		return result;
	}
	

	public void submit(InfoObject info) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{			
			Key key=KeyFactory.stringToKey(info.getKeyString());
			//PersistObject pInfo=(PersistObject)pm.getUserObject(key);
			
			if(info instanceof UserStoryInfo)
				transferToServer(pm,  key, (UserStoryInfo)info);
			else if(info instanceof EntryPointInfo)
				transferToServer(pm, key,(EntryPointInfo)info);
			else if(info instanceof TestCaseInfo)
				transferToServer(pm, key, (TestCaseInfo)info);
			else if(info instanceof UnitTestInfo)
				transferToServer(pm, key, (UnitTestInfo)info);
		}
		finally{
			pm.close();
		}
	}
	
	private void transferToServer(PersistenceManager pm,Key key, UserStoryInfo info){
		UserStoryPersist pInfo=pm.getObjectById(UserStoryPersist.class, key);
		pInfo.setStory(info.getStory());
		pInfo.getChild().setStory(info.getStory());
		pInfo.setDone(info.isDone());
	}
	private void transferToServer(PersistenceManager pm,Key key, EntryPointInfo info){
		EntryPointPersist pInfo=pm.getObjectById(EntryPointPersist.class, key);
		pInfo.setStory(info.getStory());
		
		for(int x=pInfo.getNumMethods()-1; x>=0; x--)
			pInfo.removeMethod(x);
		for(int x=0; x<info.getNumMethods(); x++){
			EntryMethodPersist e=new EntryMethodPersist();
			EntryMethodInfo p=info.getMethod(x);
			e.setMethodDescription(p.getDescription());
			e.setMethodName(p.getName());
			for(int i=0; i<p.getNumParameters(); i++)
				e.addParameter(p.getParameter(i));
			pInfo.addMethod(e);
		}
		pInfo.setDone(info.isDone());
	}
	private void transferToServer(PersistenceManager pm,Key key, TestCaseInfo info){
		TestCasePersist pInfo=pm.getObjectById(TestCasePersist.class, key);
		pInfo.setDescription(info.getDescription());
		
		for(int x=pInfo.getNumTests()-1; x>=0; x--)
			pInfo.removeTest(pInfo.getTest(x));
		for(int x=0; x<info.getNumTests(); x++){
			pInfo.addTest(info.getTest(x));
		}
		pInfo.setDone(info.isDone());
	}
	private void transferToServer(PersistenceManager pm,Key key, UnitTestInfo info){
		UnitTestPersist pInfo=pm.getObjectById(UnitTestPersist.class, key);
		pInfo.setCode(info.getCode());
		pInfo.setMethodDesc(info.getMethodDesc());
		pInfo.setTestDesc(info.getMethodDesc());
		pInfo.setDone(info.isDone());
	}
	
	public UserStoryInfo create(UserStoryInfo info){
		PersistenceManager pm=PMF.get().getPersistenceManager();
		try{
			UserStoryPersist pInfo=new UserStoryPersist();
			pInfo.setName(info.getName());
			pInfo.setStory(info.getStory());
			Key key=KeyFactory.createKey(UserStoryPersist.class.getSimpleName(), info.getName());
			pInfo.setKey(key);
			pm.makePersistent(pInfo);
			info.setKeyString(KeyFactory.keyToString(pInfo.getKey()));
			return info;
		}
		finally{
			pm.close();
		}
		
	}

}
