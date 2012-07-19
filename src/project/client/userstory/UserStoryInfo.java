package project.client.userstory;

import com.google.gwt.user.client.rpc.IsSerializable;

import project.client.InfoObject;
import project.client.entry.EntryPointInfo;

public class UserStoryInfo implements IsSerializable, InfoObject{

	private boolean isDone;
	private String story;
	private String name;

	private EntryPointInfo childInfo;//child
	private String keyString;
	
	public UserStoryInfo(){
		story="story";
		name="name";
		childInfo=new EntryPointInfo();
	}
		
	public void setStory(String story){
		this.story=story;
		childInfo.setStory(story);
	}
	
	public String getStory(){
		return story;
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
	public void setKeyString(String s){
		keyString=s;
	}
	public String getKeyString(){
		return keyString;
	}
	
	
	
}
