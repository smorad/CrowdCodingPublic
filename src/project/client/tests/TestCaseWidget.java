package project.client.tests;

import java.util.ArrayList;

import project.client.login.LoginInfo;
import project.client.screen.ScreenWidget;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class TestCaseWidget extends ScreenWidget {
	private ArrayList<TestCasePanel> testCase;
	private TestCaseInfo tInfo;
	private String methodDescription;

	public TestCaseWidget(LoginInfo info, TestCaseInfo tInfo, String methodDescription) {
		super(info);
		setSize("1150px", "768px");
		this.tInfo=tInfo;
		this.methodDescription=methodDescription;
		
		UI();
	}

	
	
	public void UI(){
		testCase = new ArrayList<TestCasePanel>();
		Label lblDescribeTestCases = new Label("Describe test cases");
		mainPanel.add(lblDescribeTestCases);
		mainPanel.setWidgetLeftWidth(lblDescribeTestCases, 336.0, Unit.PX, 125.0, Unit.PX);
		mainPanel.setWidgetTopHeight(lblDescribeTestCases, 13.0, Unit.PX, 42.0, Unit.PX);
		
		TextArea methodBox=new TextArea();
		methodBox.setText(methodDescription);
		methodBox.setEnabled(false);
		mainPanel.add(methodBox);
		mainPanel.setWidgetLeftWidth(methodBox, 79.0, Unit.PX, 604.0, Unit.PX);
		mainPanel.setWidgetTopHeight(methodBox, 44.0, Unit.PX, 189.0, Unit.PX);


		Label lblTestCaseDescription = new Label("Test case description");
		mainPanel.add(lblTestCaseDescription);
		mainPanel.setWidgetLeftWidth(lblTestCaseDescription, 336.0, Unit.PX, 134.0,
				Unit.PX);
		mainPanel.setWidgetTopHeight(lblTestCaseDescription, 239.0, Unit.PX, 42.0,
				Unit.PX);

		Button btnDone = new Button("Done");
		mainPanel.add(btnDone);
		mainPanel.setWidgetLeftWidth(btnDone, 124.0, Unit.PX, 81.0, Unit.PX);
		mainPanel.setWidgetTopHeight(btnDone, 730.0, Unit.PX, 28.0, Unit.PX);
								
		Button btnNewTestCase = new Button("New test case");
		mainPanel.add(btnNewTestCase);
		mainPanel.setWidgetLeftWidth(btnNewTestCase, 492.0, Unit.PX, 81.0, Unit.PX);
		mainPanel.setWidgetTopHeight(btnNewTestCase, 249.0, Unit.PX, 32.0, Unit.PX);
		btnNewTestCase.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				createTextBox();
			}
		});
		btnNewTestCase.setText("Add test");
		
		createTextBox();
	}
	
	private void createTextBox() {
		testCase.add(new TestCasePanel());
		TestCasePanel currentPanel = (TestCasePanel) testCase.get(testCase.size() - 1);
		mainPanel.add(currentPanel);
		mainPanel.setWidgetLeftWidth(currentPanel, 100.0, Unit.PX, 650.0, Unit.PX);
		mainPanel.setWidgetTopHeight(currentPanel, 287+35*(testCase.size()-1), Unit.PX, 44, Unit.PX);
	}
	
	public void removeFromList(TestCasePanel panel){
		testCase.remove(panel);
		mainPanel.remove(panel);
		for(int x=0; x<testCase.size(); x++)
			mainPanel.remove(testCase.get(x));
		for(int x=0; x<testCase.size(); x++){
			TestCasePanel currentPanel=testCase.get(x);
			mainPanel.add(currentPanel);
			mainPanel.setWidgetLeftWidth(currentPanel, 100.0, Unit.PX, 650.0, Unit.PX);
			mainPanel.setWidgetTopHeight(currentPanel, 287+(x*35), Unit.PX, 44, Unit.PX);
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
