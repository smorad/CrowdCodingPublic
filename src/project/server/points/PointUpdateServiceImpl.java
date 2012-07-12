package project.server.points;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import project.client.points.PointUpdateService;

@SuppressWarnings("serial")
public class PointUpdateServiceImpl extends RemoteServiceServlet implements PointUpdateService{
	public List<String> updatedList(){
		ArrayList<String> strings=new ArrayList<String>();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q=new Query("AceProjectUser").addSort("points", Query.SortDirection.DESCENDING);	
		List<Entity> allUsers=datastore.prepare(q).asList(FetchOptions.Builder.withLimit(5));

		for(int x=0; x<10; x++){
			if(x>=allUsers.size())
				break;
			Entity e=allUsers.get(x);
			strings.add((x+1)+": "+(String)e.getProperty("nickname"));
			Object a=e.getProperty("points");
			long b=(Long)a;
			strings.add(" "+Long.toString(b)+" pts.");
			strings.add("");
		}
		return strings;
	}
	
	public Long updatedPoints(){
		//ArrayList<String> strings=new ArrayList<String>();
		UserService userService = UserServiceFactory.getUserService();
		User currentUser=userService.getCurrentUser();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity user=null;
		Key k = KeyFactory.createKey("AceProjectUser", currentUser.getEmail()); //userid will be the actual userid from login instead of this string
		try{
			user = datastore.get(k);//new Entity(user);
		}
		catch(EntityNotFoundException e){
			user=new Entity(k);
			user.setProperty("points",0);
			user.setProperty("nickname", currentUser.getNickname());
			datastore.put(user);
		}
		return (Long)user.getProperty("points");
		//strings.add((String)user.getProperty("points"));
		//return strings;
	}

}	
