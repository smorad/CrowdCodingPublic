package project.client.profile;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.gen2.picker.client.SliderBar;
import com.google.gwt.user.client.ui.Label;

import project.client.login.LoginInfo;
import project.client.screen.ScreenWidget;

public class ProfileWidget extends ScreenWidget {
	private SliderWidget main = new SliderWidget("User Story");
	private SliderWidget ePoint = new SliderWidget("Entry Point");
	private SliderWidget sketch = new SliderWidget("Sketch/Impl");
	private SliderWidget testCase = new SliderWidget("Test Case");
	private SliderWidget unit = new SliderWidget("Unit Test");
	
	public ProfileWidget(LoginInfo loginInfo) {
		super(loginInfo);
		setSize("1150px", "768px");
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
		

		mainPanel.setWidgetTopHeight(main, 100, Unit.PX, 100, Unit.PX);
		
		mainPanel.setWidgetTopHeight(ePoint, 200, Unit.PX, 100, Unit.PX); 
		
		mainPanel.setWidgetTopHeight(sketch, 300, Unit.PX, 100, Unit.PX); 
		
		mainPanel.setWidgetTopHeight(testCase, 400, Unit.PX, 100, Unit.PX); 
		
		mainPanel.setWidgetTopHeight(unit, 500, Unit.PX, 100, Unit.PX); 
		
	}
	
	
		

	@Override
	public void submit() {
		if(main==null)
			System.out.println("null1");
		else System.out.println(main.getCurrentValue());
		if(ePoint==null)
			System.out.println("null2");
		else System.out.println(ePoint.getCurrentValue());
		if(sketch==null)
			System.out.println("null3");
		else System.out.println(sketch.getCurrentValue());
		if(testCase==null)
			System.out.println("null4");
		else System.out.println(testCase.getCurrentValue());
		if(unit==null)
			System.out.println("null5");
		else System.out.println(unit.getCurrentValue());
		System.out.println();
	}
	

}
