package project.client.entry;

import java.util.Iterator;

import project.client.login.LoginInfo;
import project.client.screen.ScreenWidget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class EntryPointWidget extends ScreenWidget{
	private TextArea text;
	private TabPanel tabs;
	private int numTabs;
	private String story;
	private EntryPointInfo eInfo;
	
	public EntryPointWidget(LoginInfo info, EntryPointInfo eInfo, String story){
		super(new LoginInfo());
		setSize("1150px", "768px");
		this.story=story;
		this.eInfo=eInfo;

	}
	
	public void UI(){
		text=new TextArea();
		text.setEnabled(false);
		text.setText(story);
		mainPanel.add(text);
		mainPanel.setWidgetLeftWidth(text, 76.0, Unit.PX, 597.0, Unit.PX);
		mainPanel.setWidgetTopHeight(text, 77.0, Unit.PX, 82.0, Unit.PX);
		
		Label title=new Label("Identify the entry point necessary for this user story");
		mainPanel.add(title);
		mainPanel.setWidgetLeftWidth(title, 207.0, Unit.PX, 311.0, Unit.PX);
		mainPanel.setWidgetTopHeight(title, 0.0, Unit.PX, 49.0, Unit.PX);
		
		Button nextButton=new Button("Done");
		nextButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				;
			}
		});
		mainPanel.add(nextButton);
		mainPanel.setWidgetLeftWidth(nextButton, 227.0, Unit.PX, 77.0, Unit.PX);
		mainPanel.setWidgetTopHeight(nextButton, 611.0, Unit.PX, 28.0, Unit.PX);
		
		tabs=new TabPanel();
		tabs.setSize("450px", "320px");
		mainPanel.add(tabs);
		mainPanel.setWidgetLeftWidth(tabs, 40.0, Unit.PX, 685.0, Unit.PX);
		mainPanel.setWidgetTopHeight(tabs, 190.0, Unit.PX, 320.0, Unit.PX);
		
		Button addMethodButton=new Button("Add Method");
		mainPanel.add(addMethodButton);
		mainPanel.setWidgetLeftWidth(addMethodButton, 76.0, Unit.PX, 119.0, Unit.PX);
		mainPanel.setWidgetTopHeight(addMethodButton, 611.0, Unit.PX, 28.0, Unit.PX);
		addMethodButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				tabs.add(new EntryPointTab(), "Nothing more");
				tabs.selectTab(numTabs);
				numTabs++;
			}
		});
		
		tabs.add(new EntryPointTab(), "Nothing");
		tabs.selectTab(0);
		numTabs++;
	}
	
	public void submit(){
		Iterator<Widget> i=tabs.iterator();
		EntryPointTab info=(EntryPointTab)i.next();
		for(; i.hasNext();info=(EntryPointTab)i.next()){
			EntryMethodInfo e=new EntryMethodInfo();
			e.setMethodName(info.getName());
			e.setMethodDescription(info.getDesc());
			e.setParameters(info.getParameters());
		}
			
	}


}
