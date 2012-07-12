package project.client.info;

import java.util.ArrayList;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class StoryInfo {
	@Persistent
	private ArrayList<MethodInfo> methodInfos;
	@Persistent
	private String storyInfo="";
	
	public StoryInfo(){
		methodInfos=new ArrayList<MethodInfo>();
	}
	
	public void addMethodInfo(){
		methodInfos.add(new MethodInfo());
	}
	public MethodInfo getMethodInfo(int index){
		return methodInfos.get(index);
	}
	public void removeMethodInfo(){
		if(!methodInfos.isEmpty())
			methodInfos.remove(methodInfos.size()-1);
	}
	public int getNumMethods(){
		return methodInfos.size();
	}
	public String getStoryInfo(){
		return storyInfo;
	}
	public void setStoryInfo(String info){
		storyInfo=info;
	}
}
