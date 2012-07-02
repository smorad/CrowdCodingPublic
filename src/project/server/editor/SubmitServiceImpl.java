package project.server.editor;

import project.client.editor.SubmitService;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class SubmitServiceImpl extends RemoteServiceServlet implements SubmitService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String sendCode(String string, int points) {
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
		}
		
		if(!user.hasProperty("points"))
			user.setProperty("points",points);
		else
			user.setProperty("points", ((Integer)user.getProperty("points"))+points);	
		user.setProperty("nickname", currentUser.getNickname());
		datastore.put(user);	//puts code in datastore
		return string;
	}
	
	

}