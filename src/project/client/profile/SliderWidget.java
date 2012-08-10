package project.client.profile;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.gen2.picker.client.SliderBar;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;

public class SliderWidget extends LayoutPanel{
	private SliderBar slider;
	private Label label;
	
	private final int height=50;
	private final int sliderWidth=300;
	private final int labelWidth=200;
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
		  SliderBar slider = new SliderBar(0.0, 100.0){
			  protected String formatLabel(double val){
				  //System.out.println(super.formatLabel(val)+"\n");
				  /*if(val==0)
					  return "I hate it";
				  if(val==100)
					  return "*/
				  return super.formatLabel(val);
			  }
		  };
		  slider.setStepSize(2.0);
		  slider.setCurrentValue(50.0);
		  slider.setNumTicks(0);
		  slider.setNumLabels(2);
		  slider.setHeight(height+"px");
		  slider.setWidth(sliderWidth+"px");
		  return slider;
	}
	
	public double getCurrentValue(){
		return slider.getCurrentValue();
	}
}
