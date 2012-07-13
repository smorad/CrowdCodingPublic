package project.client.userstory;

import project.client.entry.EntryPointInfo;
import project.server.submit.EntryPointPersist;

public class UserStoryInfo {

	
	private String story;
	private String name;

	private EntryPointInfo childInfo;//child
		
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
		childInfo=null;
	}
	
	public EntryPointInfo getChild(){
		return childInfo;
	}
	public boolean hasChild(){
		return childInfo==null;
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
	
	
	
	
}
