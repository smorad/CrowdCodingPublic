package project.client.profile;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.gen2.picker.client.SliderBar;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import project.client.login.LoginInfo;
import project.client.screen.ScreenWidget;

public class ProfileWidget extends ScreenWidget {
	private SliderWidget main = new SliderWidget("User Story");
	private SliderWidget ePoint = new SliderWidget("Entry Point");
	private SliderWidget sketch = new SliderWidget("Sketch/Impl");
	private SliderWidget testCase = new SliderWidget("Test Case");
	private SliderWidget unit = new SliderWidget("Unit Test");
	
	private TextBox nameBox;
	
	private final int height=SliderWidget.getSliderHeight()+10;
	public ProfileWidget(LoginInfo loginInfo) {
		super(loginInfo);
		setSize("1150px", "768px");
		override();
		UI();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void UI() {
		mainPanel.add(main);
		mainPanel.add(ePoint);
		mainPanel.add(sketch);
		mainPanel.add(testCase);
		mainPanel.add(unit);
		

		mainPanel.setWidgetTopHeight(main, height, Unit.PX, height, Unit.PX);
		
		mainPanel.setWidgetTopHeight(ePoint, height*2, Unit.PX, height, Unit.PX); 
		
		mainPanel.setWidgetTopHeight(sketch, height*3, Unit.PX, height, Unit.PX); 
		
		mainPanel.setWidgetTopHeight(testCase, height*4, Unit.PX, height, Unit.PX); 
		
		mainPanel.setWidgetTopHeight(unit, height*5, Unit.PX, height, Unit.PX); 
		
		
		Label l=new Label("Nickname is: ");
		mainPanel.add(l);
		mainPanel.setWidgetLeftWidth(l, 74.0, Unit.PX, 92.0, Unit.PX);
		mainPanel.setWidgetTopHeight(l, 498.0, Unit.PX, 21.0, Unit.PX); 
		
		nameBox=new TextBox();
		nameBox.setText("1");
		mainPanel.add(nameBox);
		mainPanel.setWidgetLeftWidth(nameBox, 184.0, Unit.PX, 334.0, Unit.PX);
		mainPanel.setWidgetTopHeight(nameBox, 492.0, Unit.PX, 43.0, Unit.PX); 
	}
	
	
		

	@Override
	public void submit() {
		submitService.setNickname(nameBox.getText(), new AsyncCallback<String>(){
			public void onFailure(Throwable s){
				System.out.println("no change");
			}
			public void onSuccess(String s){
				updatePoints();
				System.out.println(s);
				
			}
		});
	}
	
	private void override(){
		remover.removeHandler();
		remover=button.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent c){
				submit();
			}
		});
	}
	

}
