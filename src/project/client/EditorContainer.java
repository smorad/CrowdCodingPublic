package project.client;

import java.util.List;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.ycp.cs.dh.acegwt.client.ace.AceAnnotationType;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorTheme;
import project.client.login.LoginInfo;
import project.client.screen.ScreenWidget;
import project.shared.JSIssue;

public abstract class EditorContainer extends ScreenWidget{
	protected AceEditor aceEditor = new AceEditor(true);
	private Timer timer;
	protected String lintData;
	
	public EditorContainer(){
		timer=new Timer(){
			public void run(){
				setAnnotations();
			}
		};
		
		timer.scheduleRepeating(5000);
	}
	
	public void buildEditor() {
		// start the first editor and set its theme and mode
		aceEditor.startEditor(); // must be called before calling
								// setTheme/setMode/etc.
		aceEditor.setTheme(AceEditorTheme.ECLIPSE);
		aceEditor.setMode(AceEditorMode.JAVASCRIPT);

		
	}
	
	public AceEditor getEditor(){
		return aceEditor;
	}
	
	public void setAnnotations(){
		aceEditor.clearAnnotations();
		submitService.doCheck(aceEditor.getText(), new AsyncCallback<List<JSIssue>>(){
			public void onFailure(Throwable t){
				t.printStackTrace();
			}
			public void onSuccess(List<JSIssue> t){
				for(JSIssue issue:t){
					//int line=issue.getLine();
					//int character=issue.getCharacter();
					String reason= issue.getReason();
					//aceEditor.addAnnotation(line, character, reason, AceAnnotationType.ERROR);
					lintData = issue.getReason() + " at line" + issue.getLine() + " char" + issue.getCharacter();
				}
				aceEditor.setAnnotations();
				//Window.alert("Yes");
			}
		});
		
	}
	
	public void clearTimer(){
		timer.cancel();
	}
	
}
