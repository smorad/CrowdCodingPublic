package project.client.editor;

import java.util.ArrayList;

import project.client.EditorContainer;
import project.client.login.LoginInfo;
import project.client.screen.ScreenWidget;

import com.google.gwt.user.client.ui.TextArea;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorTheme;

import com.google.gwt.dom.client.Style.Unit;

public class AceEditorWidget extends ScreenWidget implements EditorContainer{
	private AceEditor editor1;
	private String methodDescription
									;
	private  ArrayList<String> parameters;
	private String methodName, methodType;
	private TextArea description;
	private AceEditorInfo aInfo;
	private static boolean stubCreated = false;

	
	public AceEditorWidget(LoginInfo info, AceEditorInfo aInfo ){
		super(info);
		this.aInfo = aInfo;
		this.methodDescription = aInfo.getDescription();
		this.methodName = aInfo.getMethodName();
		this.methodType = aInfo.getReturnType();
		this.parameters = aInfo.getParameters();
		UI();
	}
	/*public AceEditorWidget(LoginInfo info,  AceEditorInfo aInfo, String methodDescription, ArrayList<String> parameters,
							String methodName, String methodType) {
		super(info);
		this.aInfo=aInfo;
		this.methodDescription=methodDescription;
		this.parameters=parameters;
		this.methodName=methodName;
		this.methodType=methodType;
		
		UI();
		buildEditor();
	}*/
	
	/**
	 * @wbp.parser.constructor
	 */

	/*public AceEditorWidget(LoginInfo info){
		this(info, new AceEditorInfo(), "Description", new ArrayList<String>(), "methodName", "type");
	}*/
	
	public void UI(){
		setSize("1150px", "768px");
		// create first AceEditor widget
		editor1 = new AceEditor(true);
		editor1.setWidth("652px");
		editor1.setHeight("300px");
		mainPanel.add(editor1);
		mainPanel.setWidgetLeftWidth(editor1, 21.0, Unit.PX, 652.0, Unit.PX);
		mainPanel.setWidgetTopHeight(editor1, 334.0, Unit.PX, 300.0, Unit.PX);
						
		description=new TextArea();
		//description.setReadOnly(true);
		description.setText(methodDescription);
		description.setEnabled(false);
		System.out.println("adding txtbox with text: "+ methodDescription);
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
		editor1.setText(aInfo.getCode());
		System.out.println("aInfo code is: " +aInfo.getCode());
		
		if(!stubCreated){
		editor1.setText(method());  //autogenerates method stub
		stubCreated=true;
		}
	}
	
	private String method(){  //used to autogenerate method stub
		String s="public "+methodType+" "+methodName+"(";
		for(int x=0; x<parameters.size(); x++){
			s+=parameters.get(x);
			if(paramaters.size()>1)
				s+=", "
		}
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
	
	
	
	public void submit(){
		boolean tempIsDone = true;
	/*	String text = ">> denote pseudo code with the '>>' notation \n" +
				editor1.getText();
		aInfo.setCode(text);*/
		String text = editor1.getText();
		aInfo.setCode(text);
		System.out.println("text in submit is "+ text);
		System.out.println("aInfo code in submit is " + aInfo.getCode());
		String lines[] = text.split("[\\r\\n]+");
		for(int i=0; i<lines.length; i++ ){
			if(lines[i].contains("#")){
				aInfo.setDone(false);
				return;
			}
			aInfo.setDone(tempIsDone);
	}
}
}
