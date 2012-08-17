package project.client.profile;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import project.client.screen.ScreenWidget;

public class ProfileWidget extends ScreenWidget {
	private ProfilePanel s;


	private Button cancelButton;

	private ScreenWidget other;

	

	public ProfileWidget(ScreenWidget other) {
		System.out.println("Printing");
		this.other=other;
		mainPanel.setSpacing(0);
		setSize("1150px", "768px");
		override();
		UI();
		
	}

	@Override
	public void UI() {
		s=new ProfilePanel(loginInfo);
		mainPanel.add(s);

		s.addSlider("User Story", loginInfo.getUserStory());
		s.addSlider("EntryPoint", loginInfo.getePoint());
		s.addSlider("Sketch/Impl", loginInfo.getSketch());
		s.addSlider("Test Case", loginInfo.getTestCase());
		s.addSlider("Unit Test", loginInfo.getUnit());
		
		cancelButton=new Button("Cancel");
		horizontalPanel.add(cancelButton);
		cancelButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent c){
				transfer();
			}
		});
	}




	@Override
	public void submit() {
		savePrefs();
		submitService.setProfile(loginInfo, new AsyncCallback<String>(){
			public void onFailure(Throwable s){
				System.out.println("no change");
			}
			public void onSuccess(String s){
				
				updatePoints();
				transfer();

			}
		});
		
	}
	
	private void savePrefs(){
		loginInfo.setNickname(s.getNickname());
		
		loginInfo.setUserStory(s.getSlider(0).getCurrentValue());
		loginInfo.setePoint(s.getSlider(1).getCurrentValue());
		loginInfo.setSketch(s.getSlider(2).getCurrentValue());
		loginInfo.setTestCase(s.getSlider(3).getCurrentValue());
		loginInfo.setUnit(s.getSlider(4).getCurrentValue());
	}
	

	private void override(){
		button.setText("Save");
		remover.removeHandler();
		remover=button.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent c){
				submit();
				
			}
		});
		prefs.removeFromParent();
	}

	private void transfer(){
		RootPanel.get("ace").clear();

		RootPanel.get("ace").add(other);
	}


}