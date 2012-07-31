package project.server.editor;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import project.client.screen.SubmitService;
import project.shared.JSIssue;

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
import com.googlecode.jslint4java.Issue;
import com.googlecode.jslint4java.JSLint;
import com.googlecode.jslint4java.JSLintBuilder;
import com.googlecode.jslint4java.JSLintResult;

public class SubmitServiceImpl extends RemoteServiceServlet implements SubmitService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JSLint j;

	@Override
	public String sendCode(String string, Long points) {
		submitPoints(points);
		return string;
	}
	
	private void submitPoints(Long points){
		
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
			user.setProperty("nickname", currentUser.getNickname());

		}
		
		if(!user.hasProperty("points"))
			user.setProperty("points",points);
		else
			user.setProperty("points", ((Long)user.getProperty("points"))+points);	

		datastore.put(user);	//puts code in datastore
		
	}
	
	public List<JSIssue> doCheck(String s){
		
		ArrayList<JSIssue> issues=new ArrayList<JSIssue>();
		
		JSLintResult result=j.lint(null,s);
		for (Issue i : result.getIssues()) {
			JSIssue jsIssue=new JSIssue(i.getLine(), i.getCharacter(), i.getReason());
			issues.add(jsIssue);
	    }
		return issues;
	}
	
	public void instantiate(){
		if(j==null)
			j=new JSLintBuilder().fromDefault();
			
	}

	
	

}