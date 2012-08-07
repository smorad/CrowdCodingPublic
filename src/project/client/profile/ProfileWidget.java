package project.client.profile;

import gwtquery.plugins.ui.Ui;
import gwtquery.plugins.ui.widgets.Slider;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.DragEvent;
import com.google.gwt.event.dom.client.DragHandler;
import com.google.gwt.gen2.picker.client.SliderBar;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


import static com.google.gwt.query.client.GQuery.$;
import static gwtquery.plugins.ui.Ui.Ui;





import project.client.login.LoginInfo;
import project.client.screen.ScreenWidget;

public class ProfileWidget extends ScreenWidget {
	private SliderBar main = createSlider("User Story");
	private SliderBar ePoint = createSlider("Entry Point");
	private SliderBar sketch = createSlider("Sketch/Impl");
	private SliderBar testCase = createSlider("Test Case");
	private SliderBar unit = createSlider("Unit Test");
	private VerticalPanel panel = new VerticalPanel();
	
	private int pos;
	public ProfileWidget(LoginInfo loginInfo) {
		super(loginInfo);
		setSize("1150px", "768px");
		UI();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void UI() {
		 //add((IsWidget) GQuery.$("slider").as(Ui).slider());
		 //add((IsWidget) ((Ui) GQuery.$("#slider")).slider());
		/* $("#slider").as(Ui).slider();
		 $(".slider").as(Enhance).slider(0, 2);
		 ((Ui) $("#slider")).slider();*/
		/*	
		  SliderBar slider = new SliderBar(0.0, 100.0);
		  slider.setStepSize(2.0);
		  slider.setCurrentValue(50.0);
		  slider.setNumTicks(0);
		  slider.setNumLabels(0);
		  slider.setTitle("Current value: " + slider.getValue());
		  mainPanel.add(slider);*/

		
		mainPanel.add(main);
		mainPanel.add(ePoint);
		mainPanel.add(sketch);
		mainPanel.add(testCase);
		mainPanel.add(unit);
		//mainPanel.setWidgetLeftWidth(main, 100, Unit.PX, 100, Unit.PX); 
		mainPanel.setWidgetTopHeight(main, 100, Unit.PX, 100, Unit.PX); 
		
		//mainPanel.setWidgetLeftWidth(ePoint, 100, Unit.PX, 100, Unit.PX); 
		mainPanel.setWidgetTopHeight(ePoint, 200, Unit.PX, 100, Unit.PX); 
		
		//mainPanel.setWidgetLeftWidth(sketch, 100, Unit.PX, 100, Unit.PX); 
		mainPanel.setWidgetTopHeight(sketch, 300, Unit.PX, 100, Unit.PX); 
		
		//mainPanel.setWidgetLeftWidth(testCase, 100, Unit.PX, 100, Unit.PX); 
		mainPanel.setWidgetTopHeight(testCase, 400, Unit.PX, 100, Unit.PX); 
		
		//mainPanel.setWidgetLeftWidth(unit, 100, Unit.PX, 100, Unit.PX); 
		mainPanel.setWidgetTopHeight(unit, 500, Unit.PX, 100, Unit.PX); 
		}
	
	public SliderBar createSlider(String name){
		  SliderBar slider = new SliderBar(0.0, 100.0);
		  slider.setStepSize(2.0);
		  slider.setCurrentValue(50.0);
		  slider.setNumTicks(0);
		  slider.setNumLabels(2);
		  slider.setHeight("50px");
		  return slider;
	}
		

	@Override
	public void submit() {
		main.getCurrentValue();
		ePoint.getCurrentValue();
		sketch.getCurrentValue();
		testCase.getCurrentValue();
		unit.getCurrentValue();
		
	}
	

}
