package project.server.submit;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Id;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Subclass;
import com.googlecode.objectify.annotation.Unindexed;

@Unindexed
@Subclass
public class EntryPointPersist extends PersistObject{	

	
	private ArrayList<Key<EntryMethodPersist>> methods=new ArrayList<Key<EntryMethodPersist>>();
	
	
	private String story;//from parent
	
	/*public EntryPointPersist(){
		methods=new ArrayList<Key<EntryMethodPersist>>();
	}*/
	
	public void addMethod(EntryMethodPersist method){
		Objectify o=ObjectifyService.begin();
		methods.add(o.put(method));
	}
	
	public EntryMethodPersist getMethod(int index){
		Objectify o=ObjectifyService.begin();
		return o.get(methods.get(index));
	}
	public void removeMethod(int index){
		Objectify o=ObjectifyService.begin();
		o.delete(methods.remove(index));
	}
	public int getNumMethods(){
		if(methods!=null)
			return methods.size();
		return -1;
	}

	
	public Collection<EntryMethodPersist> getAllMethods(){
		Objectify o=ObjectifyService.begin();
		return o.get(methods).values();
	}
	
	//from parent
	
	public void setStory(String s){
		this.story=s;
	}
	public String getStory(){
		return story;
	}
	
	//for testing
	public String info(){
		String a="Story is: "+story;
		for(int x=0; x<methods.size(); x++)
			a+="\nDescription is: "+ObjectifyService.begin().get(methods.get(x)).getDescription();
		return a;
	}
}
