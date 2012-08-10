package project.client.entry;

import java.util.Iterator;

import project.client.login.LoginInfo;
import project.client.screen.ScreenWidget;

import com.google.gwt.user.client.DOM;
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
	
	public EntryPointWidget(LoginInfo info, EntryPointInfo eInfo){
		super(info);
		setSize("1150px", "768px");
		
		this.story=eInfo.getStory();
		this.eInfo=eInfo;

		UI();
	}
	
	public void UI(){
		text=new TextArea();
		
		text.setText(story);
		text.setReadOnly(true);
		text.setSize("600px", "80px");
		DOM.setStyleAttribute(text.getElement(), "border", "1px");  //removes border
		DOM.setStyleAttribute(text.getElement(), "minHeight","100px");
		DOM.setStyleAttribute(text.getElement(), "width", "600px");  //fixes size error on firefox
		DOM.setStyleAttribute(text.getElement(), "height", "80px");
		//text.setEnabled(false);
		mainPanel.add(text);
		//mainPanel.setWidgetLeftWidth(text, 76.0, Unit.PX, 597.0, Unit.PX);
		//mainPanel.setWidgetTopHeight(text, 77.0, Unit.PX, 82.0, Unit.PX);

		Label title=new Label("Identify the entry point necessary for this user story");
		mainPanel.add(title);
		//mainPanel.setWidgetLeftWidth(title, 207.0, Unit.PX, 311.0, Unit.PX);
		//mainPanel.setWidgetTopHeight(title, 0.0, Unit.PX, 49.0, Unit.PX);
		
		tabs=new TabPanel();
		tabs.setSize("450px", "320px");
		tabs.setAnimationEnabled(true);
		mainPanel.add(tabs);
		//mainPanel.setWidgetLeftWidth(tabs, 40.0, Unit.PX, 685.0, Unit.PX);
		//mainPanel.setWidgetTopHeight(tabs, 190.0, Unit.PX, 320.0, Unit.PX);
		
		Button addMethodButton=new Button("Add Method");
		mainPanel.add(addMethodButton);
		//mainPanel.setWidgetLeftWidth(addMethodButton, 76.0, Unit.PX, 119.0, Unit.PX);
		//mainPanel.setWidgetTopHeight(addMethodButton, 611.0, Unit.PX, 28.0, Unit.PX);
		addMethodButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				addTab();
			}
		});
		
		Button delMethodButton = new Button("Delete Method");
		mainPanel.add(delMethodButton);
		//mainPanel.setWidgetLeftWidth(delMethodButton, 207.0, Unit.PX, 119.0, Unit.PX);
		//mainPanel.setWidgetTopHeight(delMethodButton, 611.0, Unit.PX, 28.0, Unit.PX);
		delMethodButton.setSize("119px", "28px");
		delMethodButton.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				delTab();
			}
		});
		
		tabs.add(new EntryPointTab(), "Method 1");
		tabs.selectTab(0);
		numTabs++;
	}
	
	public void delTab() {
		int curTab = tabs.getTabBar().getSelectedTab();
		tabs.remove(curTab);
		tabs.selectTab(curTab-1);
		numTabs--;
	}

	public void submit(){
		Iterator<Widget> i=tabs.iterator();
		
		for(int x=0; x<numTabs; x++){
			EntryPointTab info=(EntryPointTab)i.next();
			EntryMethodInfo e=new EntryMethodInfo();
			e.setMethodName(info.getName());
			e.setMethodDescription(info.getDesc());
			e.setParameters(info.getParameters());
			e.addTest();
			eInfo.addMethod(e);
			
		}
		eInfo.setDone(true);
	}
	public void addTab(){
		tabs.add(new EntryPointTab(), "Method " + (tabs.getWidgetCount()+1));
		tabs.selectTab(numTabs);
		numTabs++;	
	}
	public EntryPointInfo getInfo(){
		return eInfo;
	}


}
