package project.client.endpage;

import com.google.gwt.user.client.ui.Label;

import project.client.login.LoginInfo;
import project.client.screen.ScreenWidget;

public class EndPageWidget extends ScreenWidget {

	public EndPageWidget() {
		setSize("1150", "768px");
		UI();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void UI() {
		mainPanel.add(new Label("There are no more microtasks at this time."));
		
		
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		
	}

}
