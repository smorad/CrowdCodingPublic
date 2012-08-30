package project.client.tests;

import java.util.ArrayList;

import project.client.screen.ScreenWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

//Most of the magic happens in testcasepanel
public class TestCaseWidget extends ScreenWidget {
	private ArrayList<TestCasePanel> testCase;
	private final TestCaseInfo tInfo;
	private final String methodDescription;
	private final HTML h = new HTML();
	VerticalPanel testContainer = new VerticalPanel();
	Button btnNewTestCase;

	public TestCaseWidget(TestCaseInfo tInfo) {
		setSize("1150px", "768px");
		this.tInfo = tInfo;
		this.methodDescription = tInfo.getDescription();
		UI();
	}

	@Override
	public void UI() {
		h.setHTML("This is the test case phase. Write some single line test cases for the given description. "
				+ "These test cases will be used to create unit tests, so be descriptive! Try to think of some errors that "
				+ "the given function may have trouble with.");
		h.setStyleName("h1");
		instructions.add(h);
		testCase = new ArrayList<TestCasePanel>();

		TextArea methodBox = new TextArea(); // this should probably be changed
												// to HTML in the future
		methodBox.setText(methodDescription);
		methodBox.setReadOnly(true);
		methodBox.setStyleName("dialogVPanel"); // makes the font black instead
												// of grey
		mainPanel.add(methodBox);

		DOM.setStyleAttribute(methodBox.getElement(), "border", "1px"); // removes
																		// border
		DOM.setStyleAttribute(methodBox.getElement(), "width", "750px"); // fixes
																			// size
																			// error
																			// on
																			// firefox
		DOM.setStyleAttribute(methodBox.getElement(), "height", "80px");

		btnNewTestCase = new Button("New test case");
		btnNewTestCase.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				createTextBox();
			}
		});
		btnNewTestCase.setText("Add test");
		createTextBox();
	}

	private void createTextBox() { // when the user clicks addtestcase
		testCase.add(new TestCasePanel());
		TestCasePanel currentPanel = testCase.get(testCase.size() - 1);
		currentPanel.setVerticalAlignment(ALIGN_TOP);
		testContainer.add(currentPanel);
		mainPanel.add(testContainer);
		mainPanel.add(btnNewTestCase);
	}

	public void removeFromList(TestCasePanel panel) { // when the user clicks
														// deleted on selected
														// testcase
		testCase.remove(panel);
		testContainer.remove(panel);
		for (int x = 0; x < testCase.size(); x++)
			testContainer.remove(testCase.get(x));
		for (int x = 0; x < testCase.size(); x++) {
			TestCasePanel currentPanel = testCase.get(x);
			testContainer.add(currentPanel);
		}
	}

	public TestCaseInfo getInfo() {
		return tInfo;
	}

	@Override
	public void submit() {
		for (int x = 0; x < testCase.size(); x++)
			tInfo.addTest(testCase.get(x).getTest());
		tInfo.setDone(true);
	}
}
