package project.client.login;


import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;

public class LoginWidget extends LayoutPanel{
	
	private Label loginLabel = new Label("Please sign in to your Google account");
	//private VerticalPanel loginPanel=this;
	private Anchor signInLink = new Anchor("Sign in");
	private LoginInfo loginInfo;
	private int width, height;
	private AbsolutePanel absolutePanel;
	
	public LoginWidget(final LoginInfo loginInfo){
		//setSize("1024px","768px");
		width=Window.getClientWidth();
		height=Window.getClientHeight();
		setPixelSize(width, height);
		this.loginInfo=loginInfo;
		createLogin();
		
	}
	public void onResize(){
		setPixelSize(Window.getClientWidth(), Window.getClientHeight());
		clear();
		createLogin();
		
		
	}

	
	private void createLogin(){
		absolutePanel = new AbsolutePanel();
		add(absolutePanel);
	    setWidgetLeftWidth(absolutePanel, Window.getClientWidth()/3, Unit.PX, 408.0, Unit.PX);
	    setWidgetTopHeight(absolutePanel, Window.getClientHeight()/3, Unit.PX, 126.0, Unit.PX);
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
