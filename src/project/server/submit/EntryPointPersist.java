package project.server.submit;

import java.util.ArrayList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.client.rpc.IsSerializable;

@PersistenceCapable
public class EntryPointPersist implements IsSerializable, PersistObject{
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent
	private ArrayList<EntryMethodPersist> methods;//children
	
	@Persistent
	private boolean isDone;
	
	@Persistent
	private String story;//from parent
	
	public EntryPointPersist(){
		methods=new ArrayList<EntryMethodPersist>();
	}
	
	public void addMethod(EntryMethodPersist method){
		methods.add(method);
	}
	
	public EntryMethodPersist getMethod(int index){
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
	
	public Key getKey(){
		return key;
	}
	public String getKeyString(){
		return KeyFactory.keyToString(key);
	}
	
	//from parent
	
		public void setStory(String s){
			this.story=s;
		}
		public String getStory(){
			return story;
		}
}
