package project.client.userstory;

import project.client.login.LoginInfo;
import project.client.screen.ScreenWidget;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class UserStoryWidget extends ScreenWidget{
	
	private TextArea area;
	private UserStoryInfo uInfo;
	
	public UserStoryWidget(LoginInfo info, UserStoryInfo uInfo){
		super(info);
		setSize("1150px", "768px");
		this.uInfo=uInfo;
		
		UI();
	}
	
	public void UI(){
	
		Label l=new Label("User Story");
		l.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		mainPanel.add(l);
		mainPanel.setWidgetLeftWidth(l, 0.0, Unit.PX, 750.0, Unit.PX);
		mainPanel.setWidgetTopHeight(l, 29.0, Unit.PX, 21.0, Unit.PX);
		
		area=new TextArea();
		mainPanel.add(area);
		mainPanel.setWidgetLeftWidth(area, 49.0, Unit.PX, 630.0, Unit.PX);
		mainPanel.setWidgetTopHeight(area, 165.0, Unit.PX, 347.0, Unit.PX);
		area.setText(uInfo.getStory());
	}
	public void submit(){
		uInfo.setStory(area.getText());
		uInfo.setDone(true);
	}
	
	public UserStoryInfo getInfo(){
		return uInfo;
	}
}
