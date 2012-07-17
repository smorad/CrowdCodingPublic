package project.client.entry;

import java.util.ArrayList;

import project.server.submit.EntryMethodPersist;

import com.google.gwt.user.client.rpc.IsSerializable;


public class EntryPointInfo implements IsSerializable{
	private boolean isDone;
	private ArrayList<EntryMethodInfo> methods;//children
	
	public EntryPointInfo(){
		methods=new ArrayList<EntryMethodInfo>();
	}
	
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
