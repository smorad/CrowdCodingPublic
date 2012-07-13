package project.client.userstory;

import project.client.login.LoginInfo;
import project.client.screen.ScreenWidget;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.dom.client.Style.Unit;

public class UserStoryWidget extends ScreenWidget{
	
	private TextArea area;
	private UserStoryInfo uInfo;
	
	public UserStoryWidget(LoginInfo info, UserStoryInfo uInfo){
		super(info);
		setSize("1150px", "768px");
		this.uInfo=uInfo;
	}
	
	public void UI(){
	
		Label l=new Label("User Story");
		mainPanel.add(l);
		mainPanel.setWidgetLeftWidth(l, 290.0, Unit.PX, 73.0, Unit.PX);
		mainPanel.setWidgetTopHeight(l, 29.0, Unit.PX, 54.0, Unit.PX);
		
		area=new TextArea();
		mainPanel.add(area);
		mainPanel.setWidgetLeftWidth(area, 49.0, Unit.PX, 630.0, Unit.PX);
		mainPanel.setWidgetTopHeight(area, 165.0, Unit.PX, 347.0, Unit.PX);
		area.setText("Insert story here.");
	}
	public void submit(){
		uInfo.setStory(area.getText());
		uInfo.createEntryChild();
	}
	
	public UserStoryInfo getInfo(){
		return uInfo;
	}
}
