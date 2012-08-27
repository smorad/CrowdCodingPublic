package project.client.tests;

import project.client.EditorContainer;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextArea;

//This one is pretty simple and self explanatory
public class UnitTestWidget extends EditorContainer {
	private final UnitTestInfo uInfo;
	private final String description, testCase;
	private final HTML h = new HTML();

	public UnitTestWidget(UnitTestInfo uInfo) {
		this.uInfo = uInfo;
		this.description = uInfo.getMethodDesc();
		this.testCase = uInfo.getTestDesc();

		UI();
	}

	@Override
	public void UI() {
		h.setStyleName("h1");
		h.setHTML("<b>This is the unit test phase. Using the test case given by another user, "
				+ "write a JsUnit test. Try to write as much as possible, don't worry about processing cycles or storage limits!");
		instructions.add(h);
		setSize("1150px", "768px");

		TextArea txtrMethodDescription = new TextArea(); // this should probably
															// be changed to
															// HTML in the
															// future
		txtrMethodDescription.setReadOnly(true);
		txtrMethodDescription.setText(description);
		mainPanel.add(txtrMethodDescription);
		txtrMethodDescription.setStyleName("dialogVPanel"); // makes the font
															// black instead of
															// grey
		DOM.setStyleAttribute(txtrMethodDescription.getElement(), "border",
				"1px"); // removes border
		DOM.setStyleAttribute(txtrMethodDescription.getElement(), "width",
				"600px"); // fixes size error on firefox
		DOM.setStyleAttribute(txtrMethodDescription.getElement(), "height",
				"80px");

		TextArea txtbxTestCase = new TextArea();
		txtbxTestCase.setText(testCase);
		txtbxTestCase.setReadOnly(true);
		mainPanel.add(txtbxTestCase);

		aceEditor.setWidth("652px");
		aceEditor.setHeight("300px");
		mainPanel.add(aceEditor);
	}

	@Override
	public void submit() {
		uInfo.setCode(aceEditor.getText());
		uInfo.setDone(true);
	}

}
