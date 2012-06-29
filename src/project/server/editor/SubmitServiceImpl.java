package project.server.editor;

import java.util.List;

import project.client.editor.SubmitService;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Text;
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
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity user=null;
		try{
			Key k = KeyFactory.createKey("user", userService.getCurrentUser().getEmail()); //userid will be the actual userid from login instead of this string
			user = datastore.get(k);//new Entity(user);
		}
		catch(EntityNotFoundException e){
			user=new Entity("AceProjectUser");
		}
		
		if(!user.hasProperty("points"))
			user.setProperty("points",0);
		else
			user.setProperty("points", ((Integer)user.getProperty("points"))+points);			
		datastore.put(user);	//puts code in datastore
		Query q=new Query("AceProjectUser").addSort("points", Query.SortDirection.DESCENDING);	
		List<Entity> le=datastore.prepare(q).asList(FetchOptions.Builder.withLimit(5));
		return string;
	}

}