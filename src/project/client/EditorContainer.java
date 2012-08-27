package project.client;

import java.util.List;

import project.client.screen.ScreenWidget;
import project.shared.JSIssue;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorMode;
import edu.ycp.cs.dh.acegwt.client.ace.AceEditorTheme;

/*This is the superclass for all objects which use an AceEditor
 * This is because AceEditor will not function correctly and throw exceptions
 * Unless starting the editor is the very last thing you do
 * So we made a class that will start the editor at the very end
 * It also contains the JsLint checker output
 */
public abstract class EditorContainer extends ScreenWidget {

	protected AceEditor aceEditor = new AceEditor(true);
	private final Timer timer;
	protected String lintData;

	public EditorContainer() {
		timer = new Timer() {
			@Override
			public void run() {
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

	public AceEditor getEditor() {
		return aceEditor;
	}

	public void setAnnotations() { // used to set annotations, once we realized
									// annotations don't work
									// we changed this to print JsLint to a
									// textbox
		aceEditor.clearAnnotations();
		submitService.doCheck(aceEditor.getText(),
				new AsyncCallback<List<JSIssue>>() {
					@Override
					public void onFailure(Throwable t) {
						t.printStackTrace();
					}

					@Override
					public void onSuccess(List<JSIssue> t) {
						for (JSIssue issue : t) {
							String reason = issue.getReason();
							lintData = issue.getReason() + " at line"
									+ issue.getLine() + " char"
									+ issue.getCharacter();
						}
						aceEditor.setAnnotations();
					}
				});

	}

	public void clearTimer() { // Timer cancels to save resources
		timer.cancel();
	}

}
