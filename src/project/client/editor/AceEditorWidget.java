package project.client.editor;

import project.client.login.LoginInfo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import edu.ycp.cs.dh.acegwt.client.ace.AceAnnotationType;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorCallback;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorTheme;

import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.dom.client.Style.Unit;

public class AceEditorWidget extends LayoutPanel {
	static AceEditor editor1;
	private SubmitServiceAsync submitService;
	private LoginInfo loginInfo;
	private Anchor signOutLink = new Anchor("Sign Out");
	
	private String METHOD_DESCRIPTION="This is a description.\n"
									+"Paremeters:\n"
									+"\ta-some int\n"
									+"\tb-some int\n";
	
	
	private  String[] parameters={"int a", "int b"};
	private String methodName="helloWorld", methodType="void";

	public AceEditorWidget(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
		//create the editor stuff
		createEditor();
		// build the UI
		buildUI();
	}

	private void createEditor() {
		if (submitService == null)
			submitService = (SubmitServiceAsync) GWT
					.create(SubmitService.class);
		// create first AceEditor widget
		editor1 = new AceEditor(true);
		editor1.setWidth("798px");
		editor1.setHeight("300px");

	}

	/**
	 * This method builds the UI. It creates UI widgets that exercise most/all
	 * of the AceEditor methods, so it's a bit of a kitchen sink.
	 */
	private void buildUI() {
		setSize("1024px","768px");
		
		add(editor1);
		setWidgetLeftWidth(editor1, 147.0, Unit.PX, 835.0, Unit.PX);
		setWidgetTopHeight(editor1, 184.0, Unit.PX, 300.0, Unit.PX);
		
		final Label description=new Label(METHOD_DESCRIPTION);
		add(description);
		setWidgetLeftWidth(description, 145.0, Unit.PX, 800.0, Unit.PX);
		setWidgetTopHeight(description, 16.0, Unit.PX, 162.0, Unit.PX);

		LayoutPanel layoutPanel = new LayoutPanel();
		add(layoutPanel);
		setWidgetLeftWidth(layoutPanel, 147.0, Unit.PX, 808.0, Unit.PX);
		setWidgetTopHeight(layoutPanel, 490.0, Unit.PX, 214.0, Unit.PX);
		
		
		

		// checkbox to show/hide gutter
		final CheckBox showGutterBox = new CheckBox("Show gutter: ");
		layoutPanel.add(showGutterBox);
		showGutterBox.setSize("100px", "30px");
		layoutPanel.setWidgetLeftWidth(showGutterBox, 4.0, Unit.PX, 100.0,
				Unit.PX);
		layoutPanel.setWidgetTopHeight(showGutterBox, 4.0, Unit.PX, 48.0,
				Unit.PX);
		showGutterBox.setValue(true);
		Button setTabSizeButton = new Button("Set tab size");
		layoutPanel.add(setTabSizeButton);
		layoutPanel.setWidgetLeftWidth(setTabSizeButton, 91.0, Unit.PX, 100.0,
				Unit.PX);
		layoutPanel.setWidgetTopHeight(setTabSizeButton, 48.0, Unit.PX, 28.0,
				Unit.PX);
		setTabSizeButton.setSize("100px", "");

		// add text box and button to set tab size
		final TextBox tabSizeTextBox = new TextBox();
		layoutPanel.add(tabSizeTextBox);
		layoutPanel.setWidgetLeftWidth(tabSizeTextBox, 4.0, Unit.PX, 62.0,
				Unit.PX);
		layoutPanel.setWidgetTopHeight(tabSizeTextBox, 48.0, Unit.PX, 32.0,
				Unit.PX);
		tabSizeTextBox.setWidth("4em");

		// add text box and button to go to a given line
		final TextBox gotoLineTextBox = new TextBox();
		layoutPanel.add(gotoLineTextBox);
		layoutPanel.setWidgetLeftWidth(gotoLineTextBox, 348.0, Unit.PX, 62.0,
				Unit.PX);
		layoutPanel.setWidgetTopHeight(gotoLineTextBox, 48.0, Unit.PX, 32.0,
				Unit.PX);
		gotoLineTextBox.setWidth("4em");
		Button gotoLineButton = new Button("Go to line");
		layoutPanel.add(gotoLineButton);
		layoutPanel.setWidgetLeftWidth(gotoLineButton, 431.0, Unit.PX, 100.0,
				Unit.PX);
		layoutPanel.setWidgetTopHeight(gotoLineButton, 48.0, Unit.PX, 28.0,
				Unit.PX);
		gotoLineButton.setWidth("100px");

		// checkbox to set/unset readonly mode
		final CheckBox readOnlyBox = new CheckBox("Read only: ");
		layoutPanel.add(readOnlyBox);
		readOnlyBox.setSize("100px", "30px");
		layoutPanel.setWidgetLeftWidth(readOnlyBox, 559.0, Unit.PX, 82.0,
				Unit.PX);
		layoutPanel.setWidgetTopHeight(readOnlyBox, 4.0, Unit.PX, 32.0,
				Unit.PX);
		readOnlyBox.setValue(false);

		// checkbox to show/hide print margin
		final CheckBox showPrintMarginBox = new CheckBox("Show print margin: ");
		layoutPanel.add(showPrintMarginBox);
		showPrintMarginBox.setSize("150", "30");
		layoutPanel.setWidgetLeftWidth(showPrintMarginBox, 237.0, Unit.PX, 128.0,
				Unit.PX);
		layoutPanel.setWidgetTopHeight(showPrintMarginBox, 4.0, Unit.PX, 30.0,
				Unit.PX);
		showPrintMarginBox.setValue(true);

		// Add check box to enable/disable soft tabs
		final CheckBox softTabsBox = new CheckBox("Soft tabs");
		layoutPanel.add(softTabsBox);
		layoutPanel
				.setWidgetLeftWidth(softTabsBox, 380.0, Unit.PX, 72.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(softTabsBox, 4.0, Unit.PX, 16.0,
				Unit.PX);
		softTabsBox.setSize("100px", "30px");
		softTabsBox.setValue(true); // I think soft tabs is the default

		// checkbox to set whether or not horizontal scrollbar is always visible
		final CheckBox hScrollBarAlwaysVisibleBox = new CheckBox(
				"H scrollbar: ");
		layoutPanel.add(hScrollBarAlwaysVisibleBox);
		hScrollBarAlwaysVisibleBox.setSize("100px", "30px");
		layoutPanel.setWidgetLeftWidth(hScrollBarAlwaysVisibleBox, 125.0,
				Unit.PX, 100.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(hScrollBarAlwaysVisibleBox, 4.0,
				Unit.PX, 28.0, Unit.PX);
		hScrollBarAlwaysVisibleBox.setValue(true);
		Button button = new Button("Submit Code");
		layoutPanel.add(button);
		button.setWidth("100px");
		layoutPanel.setWidgetLeftWidth(button, 4.0, Unit.PX, 119.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(button, 106.0, Unit.PX, 28.0, Unit.PX);
		layoutPanel.add(signOutLink);
		layoutPanel
				.setWidgetLeftWidth(signOutLink, 125.0, Unit.PX, 100.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(signOutLink, 106.0, Unit.PX, 32.0,
				Unit.PX);

		// Set up sign out hyperlink.
		signOutLink.setHref(loginInfo.getLogoutUrl());
		
		Label label = new Label("Editor by daveho@Github");
		layoutPanel.add(label);
		layoutPanel.setWidgetLeftWidth(label, 251.0, Unit.PX, 154.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(label, 106.0, Unit.PX, 25.0, Unit.PX);
		
		button.addClickHandler(new ClickHandler() {
			@Override
			/**
			 * Code to be overridden
			 */
			public void onClick(ClickEvent event) {
				try {
					callSubmitService();
				}

				catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}
		});
		hScrollBarAlwaysVisibleBox.addClickHandler(new ClickHandler() {
			@Override
			/**
			 * Code to be overridden
			 */
			public void onClick(ClickEvent event) {
				editor1.setHScrollBarAlwaysVisible(hScrollBarAlwaysVisibleBox
						.getValue());
			}
		});
		softTabsBox.addClickHandler(new ClickHandler() {
			@Override
			/**
			 * Code to be overridden
			 */
			public void onClick(ClickEvent event) {
				editor1.setUseSoftTabs(softTabsBox.getValue());
			}
		});
		showPrintMarginBox.addClickHandler(new ClickHandler() {
			@Override
			/**
			 * Code to be overridden
			 */
			public void onClick(ClickEvent event) {
				editor1.setShowPrintMargin(showPrintMarginBox.getValue());
			}
		});
		readOnlyBox.addClickHandler(new ClickHandler() {
			@Override
			/**
			 * Code to be overridden
			 */
			public void onClick(ClickEvent event) {
				editor1.setReadOnly(readOnlyBox.getValue());
			}
		});
		gotoLineButton.addClickHandler(new ClickHandler() {
			@Override
			/**
			 * Code to be overridden
			 */
			public void onClick(ClickEvent event) {
				editor1.gotoLine(Integer.parseInt(gotoLineTextBox.getText()));
			}
		});
		setTabSizeButton.addClickHandler(new ClickHandler() {
			@Override
			/**
			 * Code to be overridden
			 */
			public void onClick(ClickEvent event) {
				editor1.setTabSize(Integer.parseInt(tabSizeTextBox.getText()));
			}
		});
		showGutterBox.addClickHandler(new ClickHandler() {
			@Override
			/**
			 * Code to be overridden
			 */
			public void onClick(ClickEvent event) {
				editor1.setShowGutter(showGutterBox.getValue());
			}
		});

		// RootPanel.get("ace").add(mainPanel);
	}

	private void updateEditor1CursorPosition() {
	}

	public String sendText() {
		return editor1.getText();
	}


	public void buildEditor() {
		// start the first editor and set its theme and mode
		editor1.startEditor(); // must be called before calling
								// setTheme/setMode/etc.
		editor1.setTheme(AceEditorTheme.ECLIPSE);
		editor1.setMode(AceEditorMode.JAVA);
		// use cursor position change events to keep a label updated
		// with the current row/col
		editor1.addOnCursorPositionChangeHandler(new AceEditorCallback() {
			@Override
			public void invokeAceCallback(JavaScriptObject obj) {
				updateEditor1CursorPosition();
			}
		});
		updateEditor1CursorPosition(); // initial update
		// set some initial text in editor 1
		
		editor1.setText(method());
		// add some annotations
		editor1.addAnnotation(0, 1, "What's up?", AceAnnotationType.WARNING);
		editor1.addAnnotation(2, 1, "This code is lame",
				AceAnnotationType.ERROR);
		editor1.setAnnotations();
	}

	// Method used to call service
	public void callSubmitService() {
		submitService.sendCode(sendText(), new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				// System.out.println(caught.toString());
				Window.alert("Failure");
			}

			public void onSuccess(String result) {
				Window.alert("Success");
			}
		});
	}
	
	public String method(){
		String s="public "+methodType+" "+methodName+"(";
		for(int x=0; x<parameters.length; x++)
			s+=parameters[x]+", ";
		s=s.trim()+"){\n\treturn";
		if(methodType.equals("void"))
			;
		else if(methodType.equals("int")||methodType.equals("short")
				||methodType.equals("long")||methodType.equals("byte"))
			s+="-1";
		else if(methodType.equals("double")||methodType.equals("float"))
			s+="-1.0";
		else if(methodType.equals("char"))
			s+='a';
		else if(methodType.equals("String"))
			s+="\"\"";
		else
			s+="null";
		return s+";\n}";
	}
	
	
	
}
