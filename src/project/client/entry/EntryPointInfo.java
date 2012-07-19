package project.client.entry;

import java.util.ArrayList;

import project.client.InfoObject;
import com.google.gwt.user.client.rpc.IsSerializable;


public class EntryPointInfo implements IsSerializable, InfoObject{
	private boolean isDone;
	private ArrayList<EntryMethodInfo> methods;//children
	private String keyString;
	private String story;//from parent
	
	public EntryPointInfo(){
		methods=new ArrayList<EntryMethodInfo>();
	}
	
	public void addMethod(EntryMethodInfo method){
		methods.add(method);
	}
	
	public EntryMethodInfo getMethod(int index){
		return methods.get(index);
	}
	public void removeMethod(int index){
		methods.remove(index);
	}
	public int getNumMethods(){
		return methods.size();
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
	
	//from parent
	
	public void setStory(String s){
		this.story=s;
	}
	public String getStory(){
		return story;
	}
}
