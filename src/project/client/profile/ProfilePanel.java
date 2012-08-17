package project.client.profile;

import java.util.ArrayList;

import project.client.login.LoginInfo;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.TextBox;

public class ProfilePanel extends LayoutPanel{

	private HorizontalPanel h=new HorizontalPanel();

	private ArrayList<SliderWidget> sliders=new ArrayList<SliderWidget>();
	private TextBox nameBox;
	
	private static final int height=SliderWidget.getSliderHeight()+10;
	public ProfilePanel(LoginInfo info){
		add(h);
		Label l=new Label("Nickname is: ");
		h.add(l);
		nameBox=new TextBox();
		h.add(nameBox);	
		if(info!=null)
			nameBox.setText(info.getNickname());
	}
	
	public void addSlider(String name, double value){
		sliders.add(new SliderWidget(name));
		int pos=sliders.size()-1;
		sliders.get(pos).setCurrentValue(value);
		add(sliders.get(pos));
		setWidgetTopHeight(sliders.get(pos), height*pos, Unit.PX, height, Unit.PX);
		setWidgetTopHeight(h, height*(pos+1), Unit.PX, height, Unit.PX);
	}
	
	public SliderWidget getSlider(int pos){
		return sliders.get(pos);
	}
	public String getNickname(){
		return nameBox.getText();
	}
	
	
	
}
