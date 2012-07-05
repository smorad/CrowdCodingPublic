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
	private AceEditor editor1;
	private SubmitServiceAsync submitService;	
	private static CodeIdentifierServiceAsync codeService;
	private Long points;
	private String methodDescription
									;
	private  String[] parameters;
	private String methodName, methodType;
	private TextArea description;

	public AceEditorWidget(long points, String methodDescription, String[] parameters,
							String methodName, String methodType) {
		this.points=points;
		this.methodDescription=methodDescription;
		this.parameters=parameters;
		this.methodName=methodName;
		this.methodType=methodType;
		//create the editor stuff
		startServices();
		// build the UI
		buildUI();
	}

	private void startServices() {
		if (submitService == null)
			submitService = (SubmitServiceAsync) GWT
					.create(SubmitService.class);
	}

	/**
	 * This method builds the UI. It creates UI widgets that exercise most/all
	 * of the AceEditor methods, so it's a bit of a kitchen sink.
	 */
	private void buildUI() {
		setSize("730px","505px");
		buildEditorUI();
		
	
	}
	
	private void buildEditorUI(){
		// create first AceEditor widget
		editor1 = new AceEditor(true);
		editor1.setWidth("652px");
		editor1.setHeight("300px");
		add(editor1);
		setWidgetLeftWidth(editor1, 21.0, Unit.PX, 652.0, Unit.PX);
		setWidgetTopHeight(editor1, 195.0, Unit.PX, 300.0, Unit.PX);
				
		description=new TextArea();
		description.setReadOnly(true);
		description.setEnabled(false);
		description.setText(methodDescription);
		add(description);
		setWidgetLeftWidth(description, 21.0, Unit.PX, 652.0, Unit.PX);
		setWidgetTopHeight(description, 10.0, Unit.PX, 162.0, Unit.PX);
	}
	
	

	private void updateEditor1CursorPosition() {  //not sure why this is here
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
		
		editor1.setText(method());  //autogenerates method stub
	}

	// Method used to call service
	public void callSubmitService() {
		submitService.sendCode(getCode(), points, new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				Window.alert("Failure");
			}

			public void onSuccess(String result) {
				Window.alert("Success");
			}
		});
	}
	
	public static void callCodeIdentifierService(){  //Identify if code is real or pseudo
		  codeService.storeType(CodeIdentifier.row, CodeIdentifier.type, new AsyncCallback() {
		   public void onFailure(Throwable caught) {
		    Window.alert("Failure");
		   }

		   public void onSuccess(Object result) {
		    Window.alert("Success");
		   }
		  });
		 }
	
	
	private String method(){  //used to autogenerate method stub
		String s="public "+methodType+" "+methodName+"(";
		for(int x=0; x<parameters.length; x++)
			s+=parameters[x]+", ";
		s=s.trim()+"){\n\treturn";
		if(methodType.equals("void"))
			;
		else if(methodType.equals("int")||methodType.equals("short")
				||methodType.equals("long")||methodType.equals("byte"))
			s+=" -1";
		else if(methodType.equals("double")||methodType.equals("float"))
			s+=" -1.0";
		else if(methodType.equals("char"))
			s+=" a";
		else if(methodType.equals("String"))
			s+=" \"\"";
		else
			s+=" null";
		return s+";\n}";
	}
	
	public String getCode() {  //Recieves code in editor
		return editor1.getText();
	}
	public Long getPoints(){
		return points;
	}
	public String getMethodDescription(){
		return methodDescription;
	}
	public String getMethodName(){
		return methodName;
	}
	public String[] getParameters(){
		return parameters;
	}
	public String getMethodType(){
		return methodType;
	}	
}
