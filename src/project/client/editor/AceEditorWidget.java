package project.client.editor;

import java.util.ArrayList;

import project.client.EditorContainer;
import project.client.login.LoginInfo;
import project.client.screen.ScreenWidget;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorTheme;

import com.google.gwt.dom.client.Style.Unit;
import com.googlecode.jslint4java.JSLint;
import com.googlecode.jslint4java.JSLintBuilder;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class AceEditorWidget  extends EditorContainer{
	
	private String methodDescription;
	private  ArrayList<String> parameters;
	private String methodName, methodType;
	private TextArea description;
	private AceEditorInfo aInfo;
	
	//JSLint j=new JSLintBuilder().fromDefault();
	

	
	public AceEditorWidget(LoginInfo info, AceEditorInfo aInfo ){
		super(info);
		this.aInfo = aInfo;
		this.methodDescription = aInfo.getDescription();
		this.methodName = aInfo.getMethodName();
		this.methodType = aInfo.getReturnType();
		this.parameters = aInfo.getParameters();
		UI();
	}

	
	
	public void UI(){
		setSize("1150px", "768px");
		Label title=new Label("Sketch and Implement the Method");
		mainPanel.add(title);
		mainPanel.setWidgetLeftWidth(title, 229.0, Unit.PX, 213.0, Unit.PX);
		mainPanel.setWidgetTopHeight(title, 23.0, Unit.PX, 38.0, Unit.PX);
		
		// create first AceEditor widget
		
		aceEditor.setWidth("652px");
		aceEditor.setHeight("300px");
		mainPanel.add(aceEditor);
		mainPanel.setWidgetLeftWidth(aceEditor, 21.0, Unit.PX, 652.0, Unit.PX);
		mainPanel.setWidgetTopHeight(aceEditor, 274.0, Unit.PX, 300.0, Unit.PX);
						
		description=new TextArea();
		//description.setReadOnly(true);
		description.setText(methodDescription);
		description.setEnabled(false);
		//System.out.println("adding txtbox with text: "+ methodDescription);
		mainPanel.add(description);
		mainPanel.setWidgetLeftWidth(description, 21.0, Unit.PX, 652.0, Unit.PX);
		mainPanel.setWidgetTopHeight(description, 80.0, Unit.PX, 162.0, Unit.PX);
		
		Button b=new Button("button");
		b.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//System.out.println(j.report(aceEditor.getText()));
				
				/*submitService.doCheck(aceEditor.getText(), new AsyncCallback(){
					public void onFailure(Throwable t){
						System.out.println("no check");
					}
					public void onSuccess(Object r){
						
					}
				});*/
				//setAnnotations();
			}
		});
		mainPanel.add(b);
		mainPanel.setWidgetLeftWidth(b, 609.0, Unit.PX, 86.0, Unit.PX);
		mainPanel.setWidgetTopHeight(b, 612.0, Unit.PX, 38.0, Unit.PX);
	}


	public void buildEditor(){
		super.buildEditor();
		aceEditor.setText(aInfo.getCode());
		if(!aInfo.getStubCreated()){
			aceEditor.setText(method());  //autogenerates method stub
			aInfo.setStubCreated(true);
		}
	}
	
	private String method(){  //used to autogenerate method stub
		String s="public "+methodType+" "+methodName+"(";
		for(int x=0; x<parameters.size(); x++){
			s+=parameters.get(x);
		if(parameters.size()>1)
			s+=", ";
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
				aceEditor.getText();
		aInfo.setCode(text);*/
		String text = aceEditor.getText();
		aInfo.setCode(text);
		//System.out.println("text in submit is "+ text);
		//System.out.println("aInfo code in submit is " + aInfo.getCode());
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
