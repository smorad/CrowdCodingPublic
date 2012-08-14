package project.server.login;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import project.client.login.LoginInfo;
import project.client.login.LoginService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LoginServiceImpl extends RemoteServiceServlet implements
    LoginService {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginInfo login(String requestUri) {
	    UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    LoginInfo loginInfo = new LoginInfo();
	
	    if (user != null) {
	    	//made without datastore
		    loginInfo.setLoggedIn(true);
		    loginInfo.setEmailAddress(user.getEmail());
		    loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
		  
		    //made with datastore
		    Entity u=createUser(user);
		    loginInfo.setNickname((String)u.getProperty("nickname"));
		    setPrefs(u, loginInfo);
		} else {
		    loginInfo.setLoggedIn(false);
		    loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
		}
	    return loginInfo;
	}

	@SuppressWarnings("finally")
	public Entity createUser(User user){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key k = KeyFactory.createKey("AceProjectUser", user.getEmail());
		Entity u;
		try{
			u = datastore.get(k);//new Entity(user);
			if(!u.hasProperty("userStory")){
				setDefaultPrefs(u);
				datastore.put(u);
			}
		}
		catch(EntityNotFoundException e){
			u=new Entity(k);
			u.setProperty("nickname", user.getNickname());
			setDefaultPrefs(u);
			datastore.put(u);
		}
		try{
			datastore.get(k);
		}
		finally{
			return u;
		}
	}
	
	private void setDefaultPrefs(Entity u){
		u.setProperty("userStory", 50.0);
		u.setProperty("ePoint", 50.0);
		u.setProperty("sketch", 50.0);
		u.setProperty("testCase", 50.0);
		u.setProperty("unit", 50.0);
	}
	private void setPrefs(Entity u, LoginInfo info){
		info.setUserStory((Double)u.getProperty("userStory"));
		info.setePoint((Double)u.getProperty("ePoint"));
		info.setSketch((Double)u.getProperty("sketch"));
		info.setTestCase((Double)u.getProperty("testCase"));
		info.setUnit((Double)u.getProperty("unit"));
	}
	

}