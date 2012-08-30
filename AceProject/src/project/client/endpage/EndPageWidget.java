package project.client.endpage;

import com.google.gwt.user.client.ui.Label;

import project.client.login.LoginInfo;
import project.client.screen.ScreenWidget;

//This is shown when no microtasks are available
public class EndPageWidget extends ScreenWidget {

	public EndPageWidget() {
		setSize("1150", "768px");
		UI();
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
