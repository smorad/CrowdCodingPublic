package project.client.profile;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.widgetideas.client.SliderBar;

public class SliderWidget extends LayoutPanel{
	private SliderBar slider;
	private Label label;
	
	private static final int height=50;
	private static final int sliderWidth=300;
	private static final int labelWidth=200;
	public SliderWidget(String microtask){
		
		label=new Label("Preference for "+microtask+" tasks");
		slider=createSlider();
		
		
		add(label);
		setWidgetTopHeight(label, height/3, Unit.PX, height, Unit.PX);
		setWidgetLeftWidth(label, 0, Unit.PX, labelWidth, Unit.PX);
		
		add(slider);
		setWidgetTopHeight(slider, 0, Unit.PX, height, Unit.PX);
		setWidgetLeftWidth(slider, 300, Unit.PX, sliderWidth, Unit.PX);
		
	}
	
	private SliderBar createSlider(){
		  SliderBar slider = new SliderBar(0.0, 100.0);
		  slider.setStepSize(2.0);
		  //slider.setCurrentValue(50.0);
		  slider.setNumTicks(0);
		  slider.setNumLabels(2);
		  slider.setHeight(height+"px");
		  slider.setWidth(sliderWidth+"px");
		  return slider;
	}
	
	public double getCurrentValue(){
		return slider.getCurrentValue();
	}
	public void setCurrentValue(double value){
		slider.setCurrentValue(value);
	}
	
	public static int getSliderHeight(){
		return height;
	}
}
