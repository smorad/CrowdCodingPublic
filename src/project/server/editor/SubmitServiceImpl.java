package project.server.editor;

import project.client.editor.SubmitService;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
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
	public String sendCode(String string) {
		// TODO Auto-generated method stub
		try{
			UserService userService = UserServiceFactory.getUserService();
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Key user = KeyFactory.createKey("user", userService.getCurrentUser().getEmail()); //userid will be the actual userid from login instead of this string
			Entity code = datastore.get(user);//new Entity(user);
			
			code.setProperty("code", new Text(string));//may store up to 1mb of text
			code.setProperty("methodName", "method name goes here");
			code.setProperty("pointGoal", 50000);
			
			
			datastore.put(code);	//puts code in datastore
			
			System.out.println(user);  //prints the userid
			System.out.println(code.getKey());  //key, not quite sure what this is used for
			System.out.println(((Text)code.getProperty("code")).getValue());  //this is where the code is stored	
			System.out.println(code.getProperty("methodName"));
			System.out.println(code.getProperty("pointGoal"));
			//Query query = new Query("user","userid").addSort("code", Query.SortDirection.Descending)
		}catch(Exception e){
			e.printStackTrace();
		}

		return string;
	}

}