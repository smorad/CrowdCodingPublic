package project.server.editor;

import java.util.ArrayList;
import java.util.List;

import project.client.login.LoginInfo;
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

/*Despite the name,  this isn't really for submission
 * It is for points and JsLint checking
 * This is probably one of the oldest files, so I'm not quite sure what it does exactly
 */
public class SubmitServiceImpl extends RemoteServiceServlet implements
		SubmitService {

	private static final long serialVersionUID = 1L;
	private static JSLint j;

	@Override
	public String sendCode(String string, Long points) {
		submitPoints(points);
		return string;
	}

	private void submitPoints(Long points) {

		UserService userService = UserServiceFactory.getUserService();
		User currentUser = userService.getCurrentUser();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Entity user = null;
		Key k = KeyFactory.createKey("AceProjectUser", currentUser.getEmail()); // creates
																				// user
																				// in
																				// datastore

		try {
			user = datastore.get(k);

		} catch (EntityNotFoundException e) {
			user = new Entity(k);
			user.setProperty("nickname", currentUser.getNickname());

		}

		if (!user.hasProperty("points"))
			user.setProperty("points", points);
		else
			user.setProperty("points", ((Long) user.getProperty("points"))
					+ points);

		datastore.put(user); // puts code in datastore

	}

	@Override
	public List<JSIssue> doCheck(String s) {

		ArrayList<JSIssue> issues = new ArrayList<JSIssue>();

		JSLintResult result = j.lint(null, s);
		for (Issue i : result.getIssues()) {
			JSIssue jsIssue = new JSIssue(i.getLine(), i.getCharacter(),
					i.getReason());
			issues.add(jsIssue);
		}
		return issues;
	}

	@Override
	public void instantiate() {
		if (j == null)
			j = new JSLintBuilder().fromDefault();

	}

	@Override
	@SuppressWarnings("finally")
	public String setProfile(LoginInfo info) {
		UserService userService = UserServiceFactory.getUserService();
		User currentUser = userService.getCurrentUser();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Entity user = null;
		Key k = KeyFactory.createKey("AceProjectUser", currentUser.getEmail());
		try {
			user = datastore.get(k);
		} catch (EntityNotFoundException e) {
			user = new Entity(k);
		}

		setProperties(user, info);
		datastore.put(user);

		try {
			datastore.get(k);
		} finally {
			return info.getNickname();
		}
	}

	private void setProperties(Entity u, LoginInfo info) {
		u.setProperty("nickname", info.getNickname());
		u.setProperty("userStory", info.getUserStory());
		u.setProperty("ePoint", info.getePoint());
		u.setProperty("sketch", info.getSketch());
		u.setProperty("testCase", info.getTestCase());
		u.setProperty("unit", info.getUnit());
	}
}