package project.client.editor;

import java.util.ArrayList;

import project.client.EditorContainer;
import project.client.login.LoginInfo;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

public class AceEditorWidget  extends EditorContainer{
	
	private String methodDescription;
	private  ArrayList<String> parameters;
	private String methodName, methodType;
	private TextArea description;
	private AceEditorInfo aInfo;
	private VerticalPanel panel = new VerticalPanel();
	private TextArea lint;
	private Timer timer;
	
	//JSLint j=new JSLintBuilder().fromDefault();
	

	
	public AceEditorWidget(AceEditorInfo aInfo ){
		this.aInfo = aInfo;
		this.methodDescription = aInfo.getDescription();
		this.methodName = aInfo.getMethodName();
		this.methodType = aInfo.getReturnType();
		this.parameters = aInfo.getParameters();
		UI();
		timer=new Timer(){
			public void run(){
				checkSyntax();
			}

			private void checkSyntax() {
				lint.setText("JSLint checker output: \n" +lintData);
			}
		};
		
		timer.scheduleRepeating(5000);
	}
	
	

	
	
	public void UI(){
		instructions.setText("This is the sketch phase. Write the method that takes the parameters given." +
				" and returns what the description asks for" +
				"use the pound symbol '#' to denote a line of psuedocode, comment with //. " +
				"If your method is not done, make sure one of your lines starts with # so it is not flagged as complete!.");
		setSize("1150px", "768px");
		Label title=new Label("Sketch and Implement the Method. Use # to denote a line of pseudocode");
		title.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		/*mainPanel.add(title);
		mainPanel.setWidgetLeftWidth(title, 21.0, Unit.PX, 652.0, Unit.PX);
		mainPanel.setWidgetTopHeight(title, 23.0, Unit.PX, 38.0, Unit.PX);*/
		mainPanel.add(panel);
	
		
		// create first AceEditor widget
		
		aceEditor.setWidth("652px");
		aceEditor.setHeight("300px");
		/*mainPanel.add(aceEditor);
		mainPanel.setWidgetLeftWidth(aceEditor, 21.0, Unit.PX, 652.0, Unit.PX);
		mainPanel.setWidgetTopHeight(aceEditor, 274.0, Unit.PX, 300.0, Unit.PX);*/
						
		description=new TextArea();
		//description.setReadOnly(true);
		description.setText(methodDescription);
		description.setReadOnly(true);
		description.setStyleName("dialogVPanel");  //makes the font black instead of grey
		//System.out.println("adding txtbox with text: "+ methodDescription);
	/*	mainPanel.add(description);
		mainPanel.setWidgetLeftWidth(description, 21.0, Unit.PX, 652.0, Unit.PX);
		mainPanel.setWidgetTopHeight(description, 80.0, Unit.PX, 162.0, Unit.PX);*/
		
		TextArea textArea = new TextArea();
		lint = new TextArea();
	/*	mainPanel.add(textArea);
		mainPanel.setWidgetLeftWidth(textArea, 21.0, Unit.PX, 652.0, Unit.PX);
		mainPanel.setWidgetTopHeight(textArea, 248.0, Unit.PX, 86.0, Unit.PX);*/
		textArea.setText("Method signature:\n" + "method name: " + aInfo.getMethodName()+ "\n" +  "parameters: " + aInfo.getParameters() + "\n" + "method return type: " + aInfo.getReturnType());
		DOM.setStyleAttribute(description.getElement(), "border", "1px");  //removes border
		DOM.setStyleAttribute(description.getElement(), "minHeight","100px");
		DOM.setStyleAttribute(description.getElement(), "width", "652px");  //fixes size error on firefox
		DOM.setStyleAttribute(description.getElement(), "height", "80px");
		
		DOM.setStyleAttribute(textArea.getElement(), "border", "1px");  //removes border
		DOM.setStyleAttribute(textArea.getElement(), "minHeight","100px");
		DOM.setStyleAttribute(textArea.getElement(), "width", "652px");  //fixes size error on firefox
		DOM.setStyleAttribute(textArea.getElement(), "height", "80px");
		
		DOM.setStyleAttribute(textArea.getElement(), "resize", "none");
		DOM.setStyleAttribute(description.getElement(), "resize", "none");
		
		DOM.setStyleAttribute(lint.getElement(), "border", "1px");  //removes border
		DOM.setStyleAttribute(lint.getElement(), "minHeight","100px");
		DOM.setStyleAttribute(lint.getElement(), "width", "652px");  //fixes size error on firefox
		DOM.setStyleAttribute(lint.getElement(), "height", "80px");
		DOM.setStyleAttribute(lint.getElement(), "resize", "none");
		textArea.setReadOnly(true);
		panel.add(title);
		panel.add(description);
		panel.add(textArea);
		panel.add(aceEditor);
		panel.add(lint);
		lint.setReadOnly(true);
	}


	public void buildEditor(){
		super.buildEditor();
		aceEditor.setText(aInfo.getCode());
/*		if(!aInfo.getStubCreated()){
			aceEditor.setText(method());  //autogenerates method stub
			aInfo.setStubCreated(true);
		}*/
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
			if(lines[i].trim().startsWith("#")){
				aInfo.setDone(false);
				return;
			}
			aInfo.setDone(tempIsDone);
	}
}
}
