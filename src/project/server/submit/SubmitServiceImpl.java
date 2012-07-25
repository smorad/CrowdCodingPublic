package project.server.submit;

import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

import project.client.InfoObject;
import project.client.entry.EntryMethodInfo;
import project.client.entry.EntryPointInfo;
import project.client.submission.SubmitService;
import project.client.tests.TestCaseInfo;
import project.client.tests.UnitTestInfo;
import project.client.userstory.UserStoryInfo;

@SuppressWarnings("serial")
public class SubmitServiceImpl extends RemoteServiceServlet implements SubmitService {	
	/**
	 * 
	 */
	
	private Logger logger = Logger.getLogger("NameOfYourLogger");
	//private DAO d=new DAO();

	public InfoObject retrieve(String name){
		Objectify o=ObjectifyService.begin();		
		try{
			
			UserStoryPersist story=o.query(UserStoryPersist.class).filter("name", name).get();
			logger.log(Level.SEVERE, ""+story.isDone());
			
			PersistObject pInfo=chooseRandomly(story);
			if(pInfo instanceof UserStoryPersist)
				return transferToClient((UserStoryPersist)pInfo);
			if(pInfo instanceof EntryPointPersist)
				return transferToClient((EntryPointPersist)pInfo);
			if(pInfo instanceof TestCasePersist)
				return transferToClient((TestCasePersist)pInfo);
			if(pInfo instanceof UnitTestPersist)
				return transferToClient((UnitTestPersist)pInfo);
			//o.getTxn().commit();
			return null;
		}
		finally{
			/*if(o.getTxn().isActive()){
				o.getTxn().rollback();
				logger.log(Level.SEVERE, "Put failed, rolling back");
			}*/
			
		}
	}
	
	private PersistObject chooseRandomly(UserStoryPersist story){
		ArrayList<PersistObject> list = new ArrayList<PersistObject>();
		if(!story.isDone())
			list.add(story);//add story
		
		EntryPointPersist ePoint=story.getChild();
		if(!ePoint.isDone())
			list.add(ePoint);  //add entry point
		
		for(EntryMethodPersist e:ePoint.getAllMethods()){
			TestCasePersist t=e.getTest();
			if(!t.isDone())
				list.add(t);
			
			for(UnitTestPersist u:t.getAllUnitTests()){
				if(!u.isDone())
					list.add(u);
			}
		}
		
		if(list.size()==0)
			return null;
		int a=(int)(Math.random()*list.size());
		PersistObject p=list.get(a);
		return p;
	}
	
	private UserStoryInfo transferToClient(UserStoryPersist pInfo){
		UserStoryInfo result=new UserStoryInfo();
		result.setStory(pInfo.getStory());
		result.setName(pInfo.getName());
		result.setKeyString(pInfo.getId());
		result.setDone(pInfo.isDone());
		return result;
	}
	private EntryPointInfo transferToClient(EntryPointPersist pInfo){
		EntryPointInfo result=new EntryPointInfo();
		result.setStory(pInfo.getStory());
		for(EntryMethodPersist p:pInfo.getAllMethods()){
			EntryMethodInfo e=new EntryMethodInfo();
			e.setMethodDescription(p.getDescription());
			e.setMethodName(p.getName());
			for(int i=0; i<p.getNumParameters(); i++)
				e.addParameter(p.getParameter(i));
			result.addMethod(e);
		}
		result.setKeyString(pInfo.getId());
		result.setDone(pInfo.isDone());
		return result;
	}
	private TestCaseInfo transferToClient(TestCasePersist pInfo){
		TestCaseInfo result=new TestCaseInfo();
		result.setDescription(pInfo.getDescription());
		for(int x=0; x<pInfo.getNumTests(); x++){
			result.addTest(pInfo.getTest(x));
		}
		result.setKeyString(pInfo.getId());
		result.setDone(pInfo.isDone());
		return result;
	}
	private UnitTestInfo transferToClient(UnitTestPersist pInfo){
		UnitTestInfo result=new UnitTestInfo();
		result.setCode(pInfo.getCode());
		result.setMethodDesc(pInfo.getMethodDesc());
		result.setTestDesc(pInfo.getMethodDesc());
		result.setKeyString(pInfo.getId());
		result.setDone(pInfo.isDone());
		return result;
	}
	

	public void submit(InfoObject info) {
		Objectify o = ObjectifyService.begin();
		try{					
			if(info instanceof UserStoryInfo)
				transferToServer(o, (UserStoryInfo)info);
			else if(info instanceof EntryPointInfo)
				transferToServer(o,(EntryPointInfo)info);
			else if(info instanceof TestCaseInfo)
				transferToServer(o, (TestCaseInfo)info);
			else if(info instanceof UnitTestInfo)
				transferToServer(o, (UnitTestInfo)info);
			//o.getTxn().commit();
		}
		finally{
			/*if(o.getTxn().isActive()){
				o.getTxn().rollback();
				logger.log(Level.SEVERE, "Commit failed, rolling back");
			}*/
		}
	}
	
	private void transferToServer(Objectify o, UserStoryInfo info){
		UserStoryPersist pInfo=o.get(new Key<UserStoryPersist>(UserStoryPersist.class, info.getKeyString()));

		pInfo.setStory(info.getStory());
		pInfo.getChild().setStory(info.getStory());

		pInfo.setDone(info.isDone());
		o.put(pInfo);

	}
	private void transferToServer(Objectify o, EntryPointInfo info){
		EntryPointPersist pInfo=o.get(new Key<EntryPointPersist>(EntryPointPersist.class, info.getKeyString()));
		pInfo.setStory(info.getStory());
		
		for(int x=pInfo.getNumMethods()-1; x>=0; x--)
			pInfo.removeMethod(x);
		
		for(int x=0; x<info.getNumMethods(); x++){
			EntryMethodPersist e=new EntryMethodPersist();
			EntryMethodInfo p=info.getMethod(x);
			e.newTest();
			e.setMethodDescription(p.getDescription());
			e.setMethodName(p.getName());
			for(int i=0; i<p.getNumParameters(); i++)
				e.addParameter(p.getParameter(i));
			pInfo.addMethod(e);

		}
		pInfo.setDone(info.isDone());
		o.put(pInfo);

	}
	private void transferToServer(Objectify o, TestCaseInfo info){
		TestCasePersist pInfo=o.get(new Key<TestCasePersist>(TestCasePersist.class, info.getKeyString()));
		pInfo.setDescription(info.getDescription());
		
		for(int x=pInfo.getNumTests()-1; x>=0; x--)
			pInfo.removeTest(pInfo.getTest(x));
		for(int x=0; x<info.getNumTests(); x++){
			pInfo.addTest(info.getTest(x));
		}
		pInfo.setDone(info.isDone());
		o.put(pInfo);
	}
	private void transferToServer(Objectify o, UnitTestInfo info){
		UnitTestPersist pInfo=o.get(new Key<UnitTestPersist>(UnitTestPersist.class, info.getKeyString()));
		pInfo.setCode(info.getCode());
		pInfo.setMethodDesc(info.getMethodDesc());
		pInfo.setTestDesc(info.getMethodDesc());
		pInfo.setDone(info.isDone());
		o.put(pInfo);
	}
	
	public UserStoryInfo create(UserStoryInfo info){
		Objectify o=ObjectifyService.begin();
		try{
			UserStoryPersist pInfo=new UserStoryPersist();					
			pInfo.newChild();
			pInfo.setStory(info.getStory());
			pInfo.setName(info.getName());
			o.put(pInfo);
			info.setKeyString(pInfo.getId());
			//o.getTxn().commit();
			return info;
		} finally{
			//if(o.getTxn().isActive()){
				//o.getTxn().rollback();
				//logger.log(Level.SEVERE, "Transmission still active, rolling back");
			//}
		}
		
	}
	
	public void register(){
		Register a=new Register();
		//clears memory
		Objectify o=ObjectifyService.begin();
		o.delete(o.query(UserStoryPersist.class).fetchKeys());
		o.delete(o.query(UnitTestPersist.class).fetchKeys());
		o.delete(o.query(TestCasePersist.class).fetchKeys());
		o.delete(o.query(EntryPointPersist.class).fetchKeys());
		o.delete(o.query(EntryMethodPersist.class).fetchKeys());
	}

}
