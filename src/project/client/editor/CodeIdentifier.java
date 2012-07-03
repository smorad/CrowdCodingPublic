package project.client.editor;

import project.client.AceProject;

import com.google.gwt.user.client.ui.LayoutPanel;

import edu.ycp.cs.dh.acegwt.client.ace.AceEditor;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.ValuePicker;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.cell.client.TextCell;


public class CodeIdentifier extends LayoutPanel {
	public static AceEditor editor;
	public static int row = 0;
	public static String type = "Real Code";
	public CodeIdentifier() {
		setSize("1024px", "768px");
		 editor = new AceEditor(true);
		this.add(editor);
		setWidgetLeftWidth(editor, 0.0, Unit.PX, 700.0, Unit.PX);
		setWidgetTopHeight(editor, 150.0, Unit.PX, 450.0, Unit.PX);
		editor.setSize("700px", "450px");
		
		TextArea txtrInstructionsHere = new TextArea();
		txtrInstructionsHere.setText("Instructions here");
		add(txtrInstructionsHere);
		setWidgetLeftWidth(txtrInstructionsHere, 0.0, Unit.PX, 700.0, Unit.PX);
		setWidgetTopHeight(txtrInstructionsHere, 0.0, Unit.PX, 144.0, Unit.PX);
		
		final ListBox comboBox = new ListBox();
		comboBox.addItem("Real Code");
		comboBox.addItem("Pseudocode");
		add(comboBox);
		setWidgetLeftWidth(comboBox, 0.0, Unit.PX, 177.0, Unit.PX);
		setWidgetTopHeight(comboBox, 604.0, Unit.PX, 24.0, Unit.PX);
		
		comboBox.addChangeHandler(new ChangeHandler(){
			public void onChange(ChangeEvent e){
				type = comboBox.getValue(comboBox.getSelectedIndex());
				String position = editor.getCursorPosition().toString();
				int subPos = position.indexOf(':');
				String pos = position.substring(0, subPos);
				row = Integer.parseInt(pos);
				EditorScreenWidget.a.callCodeIdentifierService();
				
			}
		});
		
	}
	public void onChange(ChangeEvent e){
		
	}
}
