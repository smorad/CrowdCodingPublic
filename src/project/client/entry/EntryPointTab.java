package project.client.entry;

import java.util.ArrayList;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EntryPointTab extends VerticalPanel{
	private static double labelWidth=680.0, labelHeight=25.0;
	private ArrayList<TextBox> parameters=new ArrayList<TextBox>();
	private TextArea methodDesc;
	private TextBox methodName;
	public EntryPointTab(){
		setSize("685px", "260px");
		ScrollPanel scroll=new ScrollPanel();
		scroll.setSize("685px", "220px");
		add(scroll);
		
		final LayoutPanel panel=new LayoutPanel();
		panel.setSize("685px", "200px");
		scroll.add(panel);
		
		methodDesc=new TextArea();
		methodDesc.setText("Method description goes here.");
		panel.add(methodDesc);
		panel.setWidgetLeftWidth(methodDesc, 0.0, Unit.PX, 685.0, Unit.PX);
		panel.setWidgetTopHeight(methodDesc, 0.0, Unit.PX, 75.0, Unit.PX);
		
		methodName=new TextBox();
		methodName.setText("Method name goes here.");
		panel.add(methodName);
		panel.setWidgetLeftWidth(methodName, 0.0, Unit.PX, labelWidth, Unit.PX);
		panel.setWidgetTopHeight(methodName, labelHeight*3+5, Unit.PX, labelHeight, Unit.PX);
		
		HorizontalPanel h=new HorizontalPanel();
		h.setSpacing(5);
		add(h);
		
		Button addParButton=new Button("Add Parameter");
		addParButton.setSize("150px", "30px");
		addParButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				parameters.add(new TextBox());
				TextBox t=parameters.get(parameters.size()-1);
				t.setText("Parameter goes here");
				panel.add(t);
				panel.setWidgetLeftWidth(t, 0.0, Unit.PX, labelWidth, Unit.PX);
				panel.setWidgetTopHeight(t, labelHeight*(parameters.size()+3)+5, Unit.PX, labelHeight, Unit.PX);
			}
		});
		h.add(addParButton);
		
		Button removeParButton=new Button("Remove last Parameter");
		removeParButton.setSize("200px", "30px");
		removeParButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				if(!parameters.isEmpty())
					panel.remove(parameters.remove(parameters.size()-1));
			}
		});
		h.add(removeParButton);
	}
	
	public String getName(){
		return methodName.getText();
	}
	public String getDesc(){
		return methodDesc.getText();
	}
	public ArrayList<String> getParameters(){
		ArrayList<String> result=new ArrayList<String>();
		for(int x=0; x<parameters.size(); x++)
			result.add(parameters.get(x).getText());
		return result;
	}
}
