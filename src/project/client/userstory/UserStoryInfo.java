package project.client.userstory;

import com.google.gwt.user.client.rpc.IsSerializable;

import project.client.entry.EntryPointInfo;
import project.server.submit.EntryPointPersist;

public class UserStoryInfo implements IsSerializable{

	private boolean isDone;
	private String story;
	private String name;

	private EntryPointInfo childInfo;//child
	
	public UserStoryInfo(){
		story="story";
		name="name";
		childInfo=new EntryPointInfo();
	}
		
	public void setStory(String story){
		this.story=story;
	}
	
	public String getStory(){
		return story;
	}
	
	public void createEntryChild(){
		childInfo=new EntryPointInfo();
	}
	public void deleteChild(){
		childInfo=new EntryPointInfo();
	}
	
	public EntryPointInfo getChild(){
		return childInfo;
	}
	public void setChild(EntryPointInfo c){
		childInfo=c;
	}
	
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public boolean isDone(){
		return isDone;
	}
	public void setDone(boolean bool){
		isDone=bool;
	}
	
	
	
}
