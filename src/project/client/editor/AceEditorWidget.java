package project.client.editor;

import project.client.EditorContainer;
import project.client.login.LoginInfo;
import project.client.screen.ScreenWidget;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.TextArea;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorCallback;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorTheme;

import com.google.gwt.dom.client.Style.Unit;

public class AceEditorWidget extends ScreenWidget implements EditorContainer{
	private AceEditor editor1;
	private String methodDescription
									;
	private  String[] parameters;
	private String methodName, methodType;
	private TextArea description;
	private AceEditorInfo eInfo;

	public AceEditorWidget(LoginInfo info,  AceEditorInfo eInfo, String methodDescription, String[] parameters,
							String methodName, String methodType) {
		super(info);
		this.eInfo=eInfo;
		this.methodDescription=methodDescription;
		this.parameters=parameters;
		this.methodName=methodName;
		this.methodType=methodType;
		
	}
	
	/**
	 * @wbp.parser.constructor
	 */

	public AceEditorWidget(LoginInfo info){
		this(info, new AceEditorInfo(), "Description", new String[0], "methodName", "type");
	}
	
	public void UI(){
		setSize("1150px", "768px");
		// create first AceEditor widget
		editor1 = new AceEditor(true);
		editor1.setWidth("652px");
		editor1.setHeight("300px");
		mainPanel.add(editor1);
		mainPanel.setWidgetLeftWidth(editor1, 21.0, Unit.PX, 652.0, Unit.PX);
		mainPanel.setWidgetTopHeight(editor1, 195.0, Unit.PX, 300.0, Unit.PX);
						
		description=new TextArea();
		description.setReadOnly(true);
		description.setEnabled(false);
		description.setText(methodDescription);
		mainPanel.add(description);
		mainPanel.setWidgetLeftWidth(description, 21.0, Unit.PX, 652.0, Unit.PX);
		mainPanel.setWidgetTopHeight(description, 10.0, Unit.PX, 162.0, Unit.PX);
	}


	public void buildEditor() {
		// start the first editor and set its theme and mode
		editor1.startEditor(); // must be called before calling
								// setTheme/setMode/etc.
		editor1.setTheme(AceEditorTheme.ECLIPSE);
		editor1.setMode(AceEditorMode.JAVA);
		
		editor1.setText(method());  //autogenerates method stub
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
	
	public void submit(){
		eInfo.setCode(editor1.getText());
	}
}
