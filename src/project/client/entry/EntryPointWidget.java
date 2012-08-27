package project.client.entry;
/*This widget is the entry point widget. When users create a new method
 * they create a new entrypointtab, where you can add parameters
 */
import java.util.Iterator;

import project.client.login.LoginInfo;
import project.client.screen.ScreenWidget;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
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
	private HTML h = new HTML();
	
	public EntryPointWidget(EntryPointInfo eInfo){
		setSize("1150px", "768px");
		this.story=eInfo.getStory();
		this.eInfo=eInfo;
		UI();
	}
	
	public void UI(){
		h.setStyleName("h1");
		h.setHTML("This is the entry point phase. Designate \"entry points\" by reading the user story thoroughly" +
				" and identifying all the method calls to successfully create the program. Each method description should contain " +
				"a detailed description of what the method does, and what is returned. " +
				"Don't forget to give your methods names and types!");
		instructions.add(h);
		text=new TextArea(); //this should probably be changed to HTML in the future
		text.setText(story);
		text.setReadOnly(true);
		text.setSize("600px", "80px");
		text.setStyleName("dialogVPanel");  //makes the font black instead of grey
		DOM.setStyleAttribute(text.getElement(), "border", "1px");  //removes border
		DOM.setStyleAttribute(text.getElement(), "minHeight","100px");
		DOM.setStyleAttribute(text.getElement(), "width", "600px");  //fixes size error on firefox
		DOM.setStyleAttribute(text.getElement(), "height", "80px");
		mainPanel.add(text);
		
		tabs=new TabPanel();
		tabs.setSize("450px", "320px");
		tabs.setAnimationEnabled(true);
		mainPanel.add(tabs);
		
		Button addMethodButton=new Button("Add Method");
		mainPanel.add(addMethodButton);
		addMethodButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				addTab();
			}
		});
		
		Button delMethodButton = new Button("Delete Method");
		mainPanel.add(delMethodButton);
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
		for(int x=0; x<numTabs; x++){ //gets info from entrypointtab and saves it in einfo object
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
