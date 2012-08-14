package project.client.tests;

import project.client.EditorContainer;
import project.client.login.LoginInfo;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.TextArea;

public class UnitTestWidget extends  EditorContainer{
	private UnitTestInfo uInfo;
	private String description, testCase;
	
	public UnitTestWidget(UnitTestInfo uInfo) {
		this.uInfo=uInfo;
		this.description=uInfo.getMethodDesc();
		this.testCase=uInfo.getTestDesc();
		
		UI();
	}
	
	public void UI(){
		instructions.setText("This is the unit test phase. Using the test case given by another user, " +
				"write a JsUnit test. Try to write as much as possible, don't worry about processing cycles or storage limits!");
		setSize("1150px", "768px");
		Label lblWriteAUnit = new Label("Write a Unit Test");
		mainPanel.add(lblWriteAUnit);
		//mainPanel.setWidgetLeftWidth(lblWriteAUnit, 324.0, Unit.PX, 319.0, Unit.PX);
		//mainPanel.setWidgetTopHeight(lblWriteAUnit, 26.0, Unit.PX, 58.0, Unit.PX);
		
		TextArea txtrMethodDescription = new TextArea();
		txtrMethodDescription.setReadOnly(true);
		txtrMethodDescription.setText(description);
		mainPanel.add(txtrMethodDescription);
		//mainPanel.setWidgetLeftWidth(txtrMethodDescription, 86.0, Unit.PX, 631.0, Unit.PX);
		//mainPanel.setWidgetTopHeight(txtrMethodDescription, 90.0, Unit.PX, 106.0, Unit.PX);
		DOM.setStyleAttribute(txtrMethodDescription.getElement(), "border", "1px");  //removes border
		DOM.setStyleAttribute(txtrMethodDescription.getElement(), "width", "600px");  //fixes size error on firefox
		DOM.setStyleAttribute(txtrMethodDescription.getElement(), "height", "80px");
		
		TextArea txtbxTestCase = new TextArea();
		txtbxTestCase.setText(testCase);
		txtbxTestCase.setReadOnly(true);
		mainPanel.add(txtbxTestCase);
		//mainPanel.setWidgetLeftWidth(txtbxTestCase, 86.0, Unit.PX, 631.0, Unit.PX);
		//mainPanel.setWidgetTopHeight(txtbxTestCase, 202.0, Unit.PX, 32.0, Unit.PX);
		
		aceEditor.setWidth("652px");
		aceEditor.setHeight("300px");
		mainPanel.add(aceEditor);
		//mainPanel.setWidgetLeftWidth(aceEditor, 86.0, Unit.PX, 631.0, Unit.PX);
		//mainPanel.setWidgetTopHeight(aceEditor, 240.0, Unit.PX, 343.0, Unit.PX);
	}

	
	public void submit(){
		uInfo.setCode(aceEditor.getText());
		uInfo.setDone(true);
	}

}
