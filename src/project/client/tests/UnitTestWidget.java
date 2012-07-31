package project.client.tests;

import project.client.EditorContainer;
import project.client.login.LoginInfo;
import project.client.screen.ScreenWidget;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.TextArea;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorTheme;

public class UnitTestWidget extends  EditorContainer{
	private UnitTestInfo uInfo;
	private String description, testCase;
	
	public UnitTestWidget(LoginInfo info, UnitTestInfo uInfo) {
		super(info);
		this.uInfo=uInfo;
		this.description=uInfo.getMethodDesc();
		this.testCase=uInfo.getTestDesc();
		
		UI();
	}
	
	public void UI(){
		setSize("1150px", "768px");
		Label lblWriteAUnit = new Label("Write a Unit Test");
		mainPanel.add(lblWriteAUnit);
		mainPanel.setWidgetLeftWidth(lblWriteAUnit, 324.0, Unit.PX, 319.0, Unit.PX);
		mainPanel.setWidgetTopHeight(lblWriteAUnit, 26.0, Unit.PX, 58.0, Unit.PX);
		
		TextArea txtrMethodDescription = new TextArea();
		txtrMethodDescription.setEnabled(false);
		txtrMethodDescription.setText(description);
		mainPanel.add(txtrMethodDescription);
		mainPanel.setWidgetLeftWidth(txtrMethodDescription, 86.0, Unit.PX, 631.0, Unit.PX);
		mainPanel.setWidgetTopHeight(txtrMethodDescription, 90.0, Unit.PX, 106.0, Unit.PX);
		
		TextArea txtbxTestCase = new TextArea();
		txtbxTestCase.setText(testCase);
		txtbxTestCase.setEnabled(false);
		mainPanel.add(txtbxTestCase);
		mainPanel.setWidgetLeftWidth(txtbxTestCase, 86.0, Unit.PX, 631.0, Unit.PX);
		mainPanel.setWidgetTopHeight(txtbxTestCase, 202.0, Unit.PX, 32.0, Unit.PX);
		
		aceEditor.setWidth("652px");
		aceEditor.setHeight("300px");
		mainPanel.add(aceEditor);
		mainPanel.setWidgetLeftWidth(aceEditor, 86.0, Unit.PX, 631.0, Unit.PX);
		mainPanel.setWidgetTopHeight(aceEditor, 240.0, Unit.PX, 343.0, Unit.PX);
	}

	
	public void submit(){
		uInfo.setCode(aceEditor.getText());
		uInfo.setDone(true);
	}

}
