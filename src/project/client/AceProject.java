package project.client;


import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import edu.ycp.cs.dh.acegwt.client.ace.AceAnnotationType;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorCallback;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorTheme;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AceProject implements EntryPoint {
	static AceEditor editor1;
	private InlineLabel rowColLabel;
	private SubmitServiceAsync submitService;
	
	private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label("Please sign in to your Google Account to access the StockWatcher application.");
	private Anchor signInLink = new Anchor("Sign In");
	private Anchor signOutLink = new Anchor("Sign Out");

	private static final String JAVA_TEXT ="//This is text.";

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// Check login status using login service.
	    LoginServiceAsync loginService = GWT.create(LoginService.class);
	    loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
	      public void onFailure(Throwable error) {
	      }

	      public void onSuccess(LoginInfo result) {
	        loginInfo = result;
	        if(loginInfo.isLoggedIn()) {
	        	loadEditorAndService();
	        } else {
	          loadLogin();
	        }
	      }
	    });
		

		
	}
	
	private void loadLogin() {
	    // Assemble login panel.
	    signInLink.setHref(loginInfo.getLoginUrl());
	    loginPanel.add(loginLabel);
	    loginPanel.add(signInLink);
	    RootPanel.get("ace").add(loginPanel);//see the html file for id
	  }

	private void loadEditorAndService() {
		// Set up sign out hyperlink.
		System.out.println(loginInfo.getLogoutUrl());
	    signOutLink.setHref(loginInfo.getLogoutUrl());
		
		if(submitService==null)
			submitService=(SubmitServiceAsync)GWT.create(SubmitService.class);
		
		buildEditor();
	}

	/**
	 * This method builds the UI.
	 * It creates UI widgets that exercise most/all of the AceEditor methods,
	 * so it's a bit of a kitchen sink.
	 */
	private void buildUI() {
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setWidth("100%");

		mainPanel.add(new Label("Label above!"));

		mainPanel.add(editor1);

		// Label to display current row/column
		rowColLabel = new InlineLabel("");
		mainPanel.add(rowColLabel);
		
		mainPanel.add(signOutLink);

		// Create some buttons for testing various editor APIs
		HorizontalPanel buttonPanel = new HorizontalPanel();

		// Add button to insert text at current cursor position
		Button insertTextButton = new Button("Insert");
		insertTextButton.addClickHandler(new ClickHandler() {
			@Override
			/**
			 * Code to be overridden
			 */
			public void onClick(ClickEvent event) {
				//Window.alert("Cursor at: " + editor1.getCursorPosition());
				editor1.insertAtCursor("inserted text!");
			}
		});
		buttonPanel.add(insertTextButton);

		// Add check box to enable/disable soft tabs
		final CheckBox softTabsBox = new CheckBox("Soft tabs");
		softTabsBox.setValue(true); // I think soft tabs is the default
		softTabsBox.addClickHandler(new ClickHandler() {
			@Override
			/**
			 * Code to be overridden
			 */
			public void onClick(ClickEvent event) {
				editor1.setUseSoftTabs(softTabsBox.getValue());
			}
		});
		buttonPanel.add(softTabsBox);

		// add text box and button to set tab size
		final TextBox tabSizeTextBox = new TextBox();
		tabSizeTextBox.setWidth("4em");
		Button setTabSizeButton = new Button("Set tab size");
		setTabSizeButton.addClickHandler(new ClickHandler() {
			@Override
			/**
			 * Code to be overridden
			 */
			public void onClick(ClickEvent event) {
				editor1.setTabSize(Integer.parseInt(tabSizeTextBox.getText()));
			}
		});
		buttonPanel.add(new InlineLabel("Tab size: "));
		buttonPanel.add(tabSizeTextBox);
		buttonPanel.add(setTabSizeButton);

		// add text box and button to go to a given line
		final TextBox gotoLineTextBox = new TextBox();
		gotoLineTextBox.setWidth("4em");
		Button gotoLineButton = new Button("Go to line");
		gotoLineButton.addClickHandler(new ClickHandler() {
			@Override
			/**
			 * Code to be overridden
			 */
			public void onClick(ClickEvent event) {
				editor1.gotoLine(Integer.parseInt(gotoLineTextBox.getText()));
			}
		});
		buttonPanel.add(new InlineLabel("Go to line: "));
		buttonPanel.add(gotoLineTextBox);
		buttonPanel.add(gotoLineButton);

		// checkbox to set whether or not horizontal scrollbar is always visible
		final CheckBox hScrollBarAlwaysVisibleBox = new CheckBox("H scrollbar: ");
		hScrollBarAlwaysVisibleBox.setValue(true);
		hScrollBarAlwaysVisibleBox.addClickHandler(new ClickHandler() {
			@Override
			/**
			 * Code to be overridden
			 */
			public void onClick(ClickEvent event) {
				editor1.setHScrollBarAlwaysVisible(hScrollBarAlwaysVisibleBox.getValue());
			}
		});
		buttonPanel.add(hScrollBarAlwaysVisibleBox);

		// checkbox to show/hide gutter
		final CheckBox showGutterBox = new CheckBox("Show gutter: ");
		showGutterBox.setValue(true);
		showGutterBox.addClickHandler(new ClickHandler() {
			@Override
			/**
			 * Code to be overridden
			 */
			public void onClick(ClickEvent event) {
				editor1.setShowGutter(showGutterBox.getValue());
			}
		});
		buttonPanel.add(showGutterBox);

		// checkbox to set/unset readonly mode
		final CheckBox readOnlyBox = new CheckBox("Read only: ");
		readOnlyBox.setValue(false);
		readOnlyBox.addClickHandler(new ClickHandler() {
			@Override
			/**
			 * Code to be overridden
			 */
			public void onClick(ClickEvent event) {
				editor1.setReadOnly(readOnlyBox.getValue());
			}
		});
		buttonPanel.add(readOnlyBox);

		// checkbox to show/hide print margin
		final CheckBox showPrintMarginBox = new CheckBox("Show print margin: ");
		showPrintMarginBox.setValue(true);
		showPrintMarginBox.addClickHandler(new ClickHandler() {
			@Override
			/**
			 * Code to be overridden
			 */
			public void onClick(ClickEvent event) {
				editor1.setShowPrintMargin(showPrintMarginBox.getValue());
			}
		});
		buttonPanel.add(showPrintMarginBox);

		mainPanel.add(buttonPanel);

		mainPanel.add(new Label(""));
		
		mainPanel.add(makeButton());
		
		mainPanel.add(new Label("Editor by daveho@Github"));

		RootPanel.get().add(mainPanel);
	}

	private void updateEditor1CursorPosition() {
		rowColLabel.setText(editor1.getCursorPosition().toString());
	}
	
	public String sendText(){
		return editor1.getText();
	}
	
	private Button makeButton(){
		Button button = new Button("Submit Code");
		button.addClickHandler(new ClickHandler() {
			@Override
			/**
			 * Code to be overridden
			 */
			public void onClick(ClickEvent event) {
				try{
					callSubmitService();
				}
			
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		}});
		return button;
		
	}
	
	private void buildEditor(){
		// create first AceEditor widget
		editor1 = new AceEditor(true);
		editor1.setWidth("800px");
		editor1.setHeight("300px");



		// build the UI
		buildUI();

		// start the first editor and set its theme and mode
		editor1.startEditor(); // must be called before calling setTheme/setMode/etc.
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
		editor1.setText(JAVA_TEXT);

		// add some annotations
		editor1.addAnnotation(0, 1, "What's up?", AceAnnotationType.WARNING);
		editor1.addAnnotation(2, 1, "This code is lame", AceAnnotationType.ERROR);
		editor1.setAnnotations();
	}
	
	//Method used to call service
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void callSubmitService(){
		submitService.sendCode(sendText(), new AsyncCallback<String>() {
		      public void onFailure(Throwable caught) {
		    	 //System.out.println(caught.toString());
		    	 Window.alert("Failure");
		      }
		      public void onSuccess(String result) {
		    	  Window.alert("Success");
		      }
		});
	}
}