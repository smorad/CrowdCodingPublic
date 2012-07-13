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
	
	public void addMethod(EntryMethodPersist method){
		methods.add(method);
	}
	
	public EntryMethodPersist getMethod(int index){
		return methods.get(index);
	}
	public int getNumMethods(){
		return methods.size();
	}
}