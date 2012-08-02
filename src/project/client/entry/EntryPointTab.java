package project.client.entry;

import java.util.ArrayList;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
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
	private ArrayList<TextBox> paramType = new ArrayList<TextBox>();
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
		DOM.setStyleAttribute(methodDesc.getElement(), "width", "600px");
		
		methodName=new TextBox();
		methodName.setText("Method name goes here.");
		panel.add(methodName);
		panel.setWidgetLeftWidth(methodName, 0.0, Unit.PX, 385.0, Unit.PX);
		panel.setWidgetTopHeight(methodName, labelHeight*3+5, Unit.PX, 25.0, Unit.PX);
		
		
		HorizontalPanel h=new HorizontalPanel();
		h.setSpacing(5);
		add(h);
/*		TextBox type = new TextBox();
		type.setText("(type) int");
		panel.setWidgetLeftWidth(type, 0.0, Unit.PX, 685.0, Unit.PX);
		panel.setWidgetTopHeight(type, 404.0, Unit.PX, 165.0, Unit.PX);
		panel.add(type);
		type.setSize("400px", "28px");*/
		
		Button addParButton=new Button("Add Parameter");
		addParButton.setSize("150px", "30px");
		addParButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				parameters.add(new TextBox());
				paramType.add(new TextBox());
				TextBox t=parameters.get(parameters.size()-1);
				TextBox type = paramType.get(paramType.size()-1);
				type = new TextBox();
				t.setText("(Parameter) maxCount");
				type.setText("(type) int");
				type.setSize("400px", "28px");
				panel.add(t);
				panel.add(type);
				panel.setWidgetLeftWidth(t, 0.0, Unit.PX, labelWidth, Unit.PX);
				panel.setWidgetTopHeight(t, labelHeight*(parameters.size()+3)+5, Unit.PX, labelHeight, Unit.PX);
				panel.setWidgetLeftWidth(type, 404.0, Unit.PX, 165.0, Unit.PX);
				panel.setWidgetTopHeight(type, labelHeight*(parameters.size()+3)+5, Unit.PX, labelHeight, Unit.PX);
			}
		});
		h.add(addParButton);
		
		Button removeParButton=new Button("Remove last Parameter");
		removeParButton.setSize("200px", "30px");
		removeParButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				if(!parameters.isEmpty())
					panel.remove(parameters.remove(parameters.size()-1));
				if(!paramType.isEmpty())
					panel.remove(paramType.remove(parameters.size()-1));
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
	public ArrayList<String> getParamType(){
		ArrayList<String> result = new ArrayList<String>();
		for(int x=0; x<paramType.size(); x++)
			result.add(paramType.get(x).getText());
		return result;
	}
}
