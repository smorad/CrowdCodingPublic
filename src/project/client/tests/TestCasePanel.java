package project.client.tests;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;


public class TestCasePanel extends HorizontalPanel{
	private TextBox textBox;

	public TestCasePanel(){
		setSpacing(10);
		
		textBox = new TextBox();
		textBox.setText("Write test here");
		add(textBox);
		textBox.setWidth("500px");
		
		final Button delete = new Button();
		delete.setText("Delete");
		delete.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {				
				((TestCaseWidget)delete.getParent().getParent().getParent().getParent()).removeFromList(((TestCasePanel)delete.getParent()));
			}
		});
		add(delete);
	}
	
	public String getTest(){
		return textBox.getText();
	}
}
