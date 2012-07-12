package project.client.identifier;

import project.client.EditorContainer;
import project.client.login.LoginInfo;
import project.client.screen.ScreenWidget;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorTheme;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;


public class CodeIdentifier extends ScreenWidget implements EditorContainer{
	public AceEditor editor;
	public static int row = 0;
	public static String type = "Real Code";
	private static CodeIdentifierServiceAsync codeService;
	public CodeIdentifier(LoginInfo info) {
		super(info);
		setSize("1150px", "768px");
	}
	
	public void UI(){
		startIdentifyService();
		
		editor = new AceEditor(true);
		mainPanel.add(editor);
		mainPanel.setWidgetLeftWidth(editor, 21.0, Unit.PX, 700.0, Unit.PX);
		mainPanel.setWidgetTopHeight(editor, 150.0, Unit.PX, 450.0, Unit.PX);
		editor.setSize("700px", "450px");
		
		TextArea txtrInstructionsHere = new TextArea();
		txtrInstructionsHere.setEnabled(false);
		txtrInstructionsHere.setText("Instructions here");
		mainPanel.add(txtrInstructionsHere);
		mainPanel.setWidgetLeftWidth(txtrInstructionsHere, 21.0, Unit.PX, 700.0, Unit.PX);
		mainPanel.setWidgetTopHeight(txtrInstructionsHere, 0.0, Unit.PX, 144.0, Unit.PX);
		
		final ListBox comboBox = new ListBox();
		comboBox.addItem("Real Code");
		comboBox.addItem("Pseudocode");
		mainPanel.add(comboBox);
		mainPanel.setWidgetLeftWidth(comboBox, 21.0, Unit.PX, 177.0, Unit.PX);
		mainPanel.setWidgetTopHeight(comboBox, 606.0, Unit.PX, 24.0, Unit.PX);
		
		comboBox.addChangeHandler(new ChangeHandler(){
			public void onChange(ChangeEvent e){
				type = comboBox.getValue(comboBox.getSelectedIndex());  //pseudo or real
				String position = editor.getCursorPosition().toString(); 
				int subPos = position.indexOf(':');
				String pos = position.substring(0, subPos);
				row = Integer.parseInt(pos);  //this and above parses row:col to get row number
				callCodeIdentifierService();  //calls service at AceEditorWidget
				
			}
		});
	}

	@Override
	public void buildEditor() {
			// start the first editor and set its theme and mode
			editor.startEditor(); // must be called before calling
									// setTheme/setMode/etc.
			editor.setTheme(AceEditorTheme.ECLIPSE);
			editor.setMode(AceEditorMode.JAVA);	
	}
	
	@SuppressWarnings("rawtypes")
	private void callCodeIdentifierService(){
		codeService.storeType(CodeIdentifier.row, CodeIdentifier.type, new AsyncCallback() {
			   public void onFailure(Throwable caught) {
			    Window.alert("Failure");
			   }

			   public void onSuccess(Object result) {
			    Window.alert("Success");
			   }
			  });
	}
	private void startIdentifyService(){
		if (codeService== null)
			codeService= (CodeIdentifierServiceAsync) GWT
					.create(CodeIdentifierService.class);
	}
	
	public void submit(){
		/*
		 * put code here
		 * later
		 */
	}
}
