package project.client.entry;

import java.util.ArrayList;


public class EntryPointInfo {
	private boolean isDone;
	private ArrayList<EntryMethodInfo> methods;//children
	
	public void addMethod(EntryMethodInfo method){
		methods.add(method);
	}
	
	public EntryMethodInfo getMethod(int index){
		return methods.get(index);
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
}
