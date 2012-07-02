package project.client.editor;

import java.util.List;

import project.client.login.LoginInfo;
import project.client.points.PointUpdateService;
import project.client.points.PointUpdateServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
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
	private PointUpdateServiceAsync pointUpdater;
	private LoginInfo loginInfo;
	private Anchor signOutLink = new Anchor("Sign Out");
	private int points=0;
	private static VerticalPanel pointRank=new VerticalPanel();
	
	private String METHOD_DESCRIPTION="This is a description.\n"
									+"Parameters:\n"
									+"\ta-some int\n"
									+"\tb-some int\n"
									;
	
	
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
		if(pointUpdater==null)
			pointUpdater=(PointUpdateServiceAsync) GWT
					.create(PointUpdateService.class);
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
		
		final TextArea description=new TextArea();
		description.setReadOnly(true);
		description.setEnabled(false);
		description.setText(METHOD_DESCRIPTION);
		add(description);
		setWidgetLeftWidth(description, 145.0, Unit.PX, 800.0, Unit.PX);
		setWidgetTopHeight(description, 16.0, Unit.PX, 162.0, Unit.PX);
		
		
		add(pointRank);
		setWidgetLeftWidth(pointRank, 18.0, Unit.PX, 119.0, Unit.PX);
		setWidgetTopHeight(pointRank, 53.0, Unit.PX, 500.0, Unit.PX);
		callPointUpdateService();

		LayoutPanel layoutPanel = new LayoutPanel();
		add(layoutPanel);
		setWidgetLeftWidth(layoutPanel, 147.0, Unit.PX, 808.0, Unit.PX);
		setWidgetTopHeight(layoutPanel, 490.0, Unit.PX, 113.0, Unit.PX);
		Button button = new Button("Submit Code");
		layoutPanel.add(button);
		button.setWidth("100px");
		layoutPanel.setWidgetLeftWidth(button, 0.0, Unit.PX, 119.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(button, 14.0, Unit.PX, 28.0, Unit.PX);
		layoutPanel.add(signOutLink);
		layoutPanel
				.setWidgetLeftWidth(signOutLink, 153.0, Unit.PX, 100.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(signOutLink, 14.0, Unit.PX, 32.0,
				Unit.PX);

		// Set up sign out hyperlink.
		signOutLink.setHref(loginInfo.getLogoutUrl());
		
		Label label = new Label("Editor by daveho@Github");
		layoutPanel.add(label);
		layoutPanel.setWidgetLeftWidth(label, 0.0, Unit.PX, 154.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(label, 82.0, Unit.PX, 25.0, Unit.PX);
		
		
				
		button.addClickHandler(new ClickHandler() {
			@Override
			/**
			 * Code to be overridden
			 */
			public void onClick(ClickEvent event) {
				try {
					callSubmitService();
					callPointUpdateService();
				}

				catch (Exception e) {
					System.out.println(e.getMessage());
				}

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
		submitService.sendCode(sendText(), points, new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				// System.out.println(caught.toString());
				Window.alert("Failure");
			}

			public void onSuccess(String result) {
				Window.alert("Success");
			}
		});
	}
	
	public void callPointUpdateService(){
		pointUpdater.updatePoints( new AsyncCallback<List<String>>(){
			public void onFailure(Throwable caught){
				Window.alert("Failed to update");
			}
			
			public void onSuccess(List<String> result){
				pointRank.clear();
				for(int x=0; x<result.size(); x++)
					pointRank.add(new Label(result.get(x)));
				Window.alert("Successful update");
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
