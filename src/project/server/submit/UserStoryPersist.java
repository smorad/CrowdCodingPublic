package project.server.submit;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class UserStoryPersist {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent
	private String story;
	
	@Persistent
	private String name;
	
	@Persistent
	private EntryPointPersist childInfo;//child
	
	@Persistent
	private boolean isDone;
		
	public void setStory(String story){
		this.story=story;
	}
	
	public Key getKey(){
		return key;
	}
	
	public String getStory(){
		return story;
	}
	
	public void createEntryChild(){
		childInfo=new EntryPointPersist();
	}
	public void deleteChild(){
		childInfo=null;
	}
	
	public EntryPointPersist getChild(){
		return childInfo;
	}
	public boolean hasChild(){
		return childInfo==null;
	}
	public void setChild(EntryPointPersist c){
		childInfo=c;
	}
	
	public void setKey(Key key){
		this.key=key;
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
	
	
	
	
}
