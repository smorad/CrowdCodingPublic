package project.client.tests;

import java.util.ArrayList;

import project.client.login.LoginInfo;
import project.client.screen.ScreenWidget;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class TestCaseWidget extends ScreenWidget {
	private ArrayList<TestCasePanel> testCase;
	private TestCaseInfo tInfo;
	private String methodDescription;
	VerticalPanel testContainer = new VerticalPanel();
	Button btnNewTestCase;

	public TestCaseWidget(TestCaseInfo tInfo) {
		setSize("1150px", "768px");
		this.tInfo=tInfo;
		this.methodDescription=tInfo.getDescription();	
		UI();
	}

	
	
	public void UI(){
		instructions.setText("This is the test case phase. Write some single line test cases for the given description. " +
				"These test cases will be used to create unit tests, so be descriptive! Try to think of some errors that" +
				"the given function may have trouble with, such as having null variables.");
		testCase = new ArrayList<TestCasePanel>();
		Label lblDescribeTestCases = new Label("Describe test cases");
		mainPanel.add(lblDescribeTestCases);
		//mainPanel.setWidgetLeftWidth(lblDescribeTestCases, 336.0, Unit.PX, 125.0, Unit.PX);
		//mainPanel.setWidgetTopHeight(lblDescribeTestCases, 13.0, Unit.PX, 42.0, Unit.PX);
		
		TextArea methodBox=new TextArea();
		methodBox.setText(methodDescription);
		methodBox.setReadOnly(true);
		mainPanel.add(methodBox);
		//mainPanel.setWidgetLeftWidth(methodBox, 79.0, Unit.PX, 604.0, Unit.PX);
		//mainPanel.setWidgetTopHeight(methodBox, 44.0, Unit.PX, 189.0, Unit.PX);
		DOM.setStyleAttribute(methodBox.getElement(), "border", "1px");  //removes border
		DOM.setStyleAttribute(methodBox.getElement(), "width", "750px");  //fixes size error on firefox
		DOM.setStyleAttribute(methodBox.getElement(), "height", "80px");

		//mainPanel.setWidgetLeftWidth(lblTestCaseDescription, 336.0, Unit.PX, 134.0,
				//Unit.PX);
		//mainPanel.setWidgetTopHeight(lblTestCaseDescription, 239.0, Unit.PX, 42.0,
				//Unit.PX);
								
		btnNewTestCase = new Button("New test case");
		//mainPanel.setWidgetLeftWidth(btnNewTestCase, 492.0, Unit.PX, 81.0, Unit.PX);
		//mainPanel.setWidgetTopHeight(btnNewTestCase, 249.0, Unit.PX, 32.0, Unit.PX);
		btnNewTestCase.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				createTextBox();
			}
		});
		btnNewTestCase.setText("Add test");
		createTextBox();
		//mainPanel.add(btnNewTestCase);
	}
	
	private void createTextBox() {
		testCase.add(new TestCasePanel());
		TestCasePanel currentPanel = (TestCasePanel) testCase.get(testCase.size() - 1);
		currentPanel.setVerticalAlignment(ALIGN_TOP);
		testContainer.add(currentPanel);
		mainPanel.add(testContainer);
		mainPanel.add(btnNewTestCase);
		//mainPanel.setWidgetLeftWidth(currentPanel, 100.0, Unit.PX, 650.0, Unit.PX);
		//mainPanel.setWidgetTopHeight(currentPanel, 287+35*(testCase.size()-1), Unit.PX, 44, Unit.PX);
	}
	
	public void removeFromList(TestCasePanel panel){
		testCase.remove(panel);
		testContainer.remove(panel);
		for(int x=0; x<testCase.size(); x++)
			testContainer.remove(testCase.get(x));
		for(int x=0; x<testCase.size(); x++){
			TestCasePanel currentPanel=testCase.get(x);
			testContainer.add(currentPanel);
		//	mainPanel.setWidgetLeftWidth(currentPanel, 100.0, Unit.PX, 650.0, Unit.PX);
		//	mainPanel.setWidgetTopHeight(currentPanel, 287+(x*35), Unit.PX, 44, Unit.PX);
		}
	}
	
	public TestCaseInfo getInfo(){
		return tInfo;
	}
	
	public void submit(){
		for(int x=0; x<testCase.size(); x++)
			tInfo.addTest(testCase.get(x).getTest());
		tInfo.setDone(true);
	}
}
