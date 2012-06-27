package project.client.login;


import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class LoginWidget extends VerticalPanel{
	
	private Label loginLabel = new Label("Please sign in to your Google account");
	private VerticalPanel loginPanel=this;
	private Anchor signInLink = new Anchor("Sign in");
	
	public LoginWidget(LoginInfo loginInfo){
		createLogin(loginInfo);
	}
	
	private void createLogin(final LoginInfo loginInfo){
	    
	    AbsolutePanel absolutePanel = new AbsolutePanel();
	    loginPanel.add(absolutePanel);
	    absolutePanel.setSize("408px", "126px");
	    loginLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	    absolutePanel.add(loginLabel, 0, 0);
	    loginLabel.setStyleName("dialogVPanel");
	    loginLabel.setSize("441px", "70px");
	    
	    signInLink.setHref(loginInfo.getLoginUrl());
	    absolutePanel.add(signInLink, 188, 47);
	    signInLink.setSize("58px", "33px");
	}
}
