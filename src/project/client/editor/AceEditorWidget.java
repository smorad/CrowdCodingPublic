package project.client.editor;

import java.util.ArrayList;

import project.client.EditorContainer;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

/* This widget is the Sketch/Impl widget/phase.
 * It contains AceEditor, which is a wonky addon,
 * if you find it not working, it's because you must add the editor
 * and all its parent panels to the root panel
 * before calling editor.startEditor().
 * Also, annotations can be added, but cannot be removed due to
 * a bug from the developer
 */
public class AceEditorWidget extends EditorContainer {

	private final String methodDescription;
	private final ArrayList<String> parameters;
	private final String methodName, methodType;
	private final AceEditorInfo aInfo;
	private final VerticalPanel panel = new VerticalPanel();
	private TextArea lint;
	private final Timer timer;
	private final HTML h = new HTML();

	public AceEditorWidget(AceEditorInfo aInfo) {
		this.aInfo = aInfo;
		this.methodDescription = aInfo.getDescription();
		this.methodName = aInfo.getMethodName();
		this.methodType = aInfo.getReturnType();
		this.parameters = aInfo.getParameters();
		UI();
		timer = new Timer() {
			@Override
			public void run() {
				checkSyntax();
			}

			private void checkSyntax() {
				lint.setText("JSLint checker output: \n" + lintData);
			}
		};
		timer.scheduleRepeating(5000);
	}

	@Override
	public void UI() {
		h.setStyleName("h1");
		h.setHTML("This is the sketch phase. Write the method that takes the parameters given."
				+ " and returns what the description asks for"
				+ "use the pound symbol '#' to denote a line of psuedocode, comment with //. "
				+ "If your method is not done, make sure one of your lines starts with # so it is not flagged as complete!.");
		instructions.add(h);
		setSize("1150px", "768px");
		mainPanel.add(panel);
		// create AceEditor widget

		aceEditor.setWidth("750px");
		aceEditor.setHeight("300px");
		TextArea textArea = new TextArea();
		lint = new TextArea(); // This is the JsLint checker output area
		textArea.setText("Method signature:\n" + "method name: "
				+ aInfo.getMethodName() + "\n" + "parameters: "
				+ aInfo.getParameters() + "\n" + "method return type: "
				+ aInfo.getReturnType()); // these lines make method signature

		DOM.setStyleAttribute(textArea.getElement(), "border", "1px"); // removes
																		// border
		DOM.setStyleAttribute(textArea.getElement(), "minHeight", "100px");
		DOM.setStyleAttribute(textArea.getElement(), "width", "652px"); // fixes
																		// size
																		// error
																		// on
																		// firefox
		DOM.setStyleAttribute(textArea.getElement(), "height", "80px");

		DOM.setStyleAttribute(textArea.getElement(), "resize", "none"); // resize
																		// is
																		// not
																		// allowed

		DOM.setStyleAttribute(lint.getElement(), "border", "1px"); // removes
																	// border
		DOM.setStyleAttribute(lint.getElement(), "minHeight", "100px");
		DOM.setStyleAttribute(lint.getElement(), "width", "652px"); // fixes
																	// size
																	// error on
																	// firefox
		DOM.setStyleAttribute(lint.getElement(), "height", "80px");
		DOM.setStyleAttribute(lint.getElement(), "resize", "none");
		textArea.setReadOnly(true);
		panel.add(textArea);
		panel.add(aceEditor);
		panel.add(lint);
		lint.setReadOnly(true);
	}

	@Override
	public void buildEditor() {
		super.buildEditor();
		aceEditor.setText(aInfo.getCode());
	}

	@Deprecated
	public void buildEditorAndStub() {
		super.buildEditor();
		aceEditor.setText(aInfo.getCode());
		if (!aInfo.getStubCreated()) {
			aceEditor.setText(method()); // autogenerates method stub in Editor.
											// Currently not in use,
			aInfo.setStubCreated(true); // since method signature already
										// generated in textArea
		}
	}

	@Deprecated
	private String method() { // used to autogenerate method stub
		String s = "public " + methodType + " " + methodName + "(";
		for (int x = 0; x < parameters.size(); x++) {
			s += parameters.get(x);
			if (parameters.size() > 1)
				s += ", ";
		}
		s = s.trim() + "){\n\treturn";
		if (methodType.equalsIgnoreCase("void"))
			;
		else if (methodType.equals("int") || methodType.equals("short")
				|| methodType.equals("long") || methodType.equals("byte"))
			s += " -1";
		else if (methodType.equals("double") || methodType.equals("float"))
			s += " -1.0";
		else if (methodType.equals("char"))
			s += " a";
		else if (methodType.equals("String"))
			s += " \"\"";
		else
			s += " null";
		return s + ";\n}";
	}

	@Override
	public void submit() {
		boolean tempIsDone = true;
		String text = aceEditor.getText();
		aInfo.setCode(text); // save text in aInfo object
		String lines[] = text.split("[\\r\\n]+"); // split text on newlines
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].trim().startsWith("#")) { // if line starts with # (not
													// counting whitespace)
				aInfo.setDone(false); // psuedocode exists, phase not complete
				return;
			}
			aInfo.setDone(tempIsDone); // phase complete
		}
	}
}
