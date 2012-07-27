package project.server.submit;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Id;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Subclass;
import com.googlecode.objectify.annotation.Unindexed;

@Unindexed
@Subclass
public class UserStoryPersist extends PersistObject{

	private String story;
	@Indexed
	private String name;
	
	@Indexed
	private Key<EntryPointPersist> childInfo;//child
	
	public void setStory(String story){
		this.story=story;
		Objectify o=ObjectifyService.begin();
		EntryPointPersist e=o.get(childInfo);
		e.setStory(story);
		o.put(e);
	}
	
	public String getStory(){
		return story;
	}
	public EntryPointPersist getChild(){
		Objectify o=ObjectifyService.begin();
		return o.find(childInfo);
	}
	public void setChild(EntryPointPersist c){
		Objectify o=ObjectifyService.begin();
		childInfo=o.put(c);
	}
	public Key<EntryPointPersist> newChild(){
		Objectify o=ObjectifyService.begin();
		if(childInfo!=null)
			o.delete(childInfo);
		childInfo=o.put(new EntryPointPersist());
		return childInfo;
	}
	
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	
	
	//for testing
		public String info(){
			return "name is: "+name
					+"\nstory is: "+story;
		}
	
}
