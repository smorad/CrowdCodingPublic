package project.client.login;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginWidget extends VerticalPanel{
	
	private Label loginLabel = new Label("Please sign in to your Google Account to access the Crowdsource application.");
	private Anchor signInLink = new Anchor("Sign In");
	private VerticalPanel loginPanel=this;
	
	public LoginWidget(LoginInfo loginInfo){
		createLogin(loginInfo);
	}
	
	private void createLogin(LoginInfo loginInfo){
		// Assemble login panel.
	    signInLink.setHref(loginInfo.getLoginUrl());
	    loginPanel.add(loginLabel);
	    loginPanel.add(signInLink);
	}
}
