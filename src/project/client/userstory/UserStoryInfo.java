package project.client.userstory;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import project.client.entry.EntryPointInfo;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class UserStoryInfo {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent
	private String story;
	
	@Persistent
	private EntryPointInfo childInfo;
	
	public void setStory(String story){
		this.story=story;
	}
	
	public String getStory(){
		return story;
	}
	
	
}
