package project.server.submit;

import java.util.ArrayList;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class EntryPointPersist {
	@PrimaryKey
    private String key;
	
	@Persistent
	private ArrayList<EntryMethodPersist> methods;//children
	
	@Persistent
	private boolean isDone;
	
	public void addMethod(EntryMethodPersist method){
		methods.add(method);
	}
	
	public EntryMethodPersist getMethod(int index){
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
