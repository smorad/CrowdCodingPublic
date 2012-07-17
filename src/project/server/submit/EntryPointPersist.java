package project.server.submit;

import java.util.ArrayList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.gwt.user.client.rpc.IsSerializable;

@PersistenceCapable
public class EntryPointPersist implements IsSerializable{
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent
	private ArrayList<EntryMethodPersist> methods;//children
	
	@Persistent
	private boolean isDone;
	
	public EntryPointPersist(){
		methods=new ArrayList<EntryMethodPersist>();
	}
	
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
