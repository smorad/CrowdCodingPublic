package project.client.tests;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;

/*Pressing add test case creates a new testcase panel
 * 
 */
public class TestCasePanel extends HorizontalPanel{
	private TextBox textBox;

	public TestCasePanel(){
		DOM.setStyleAttribute(getElement(), "width", "750px");  //fixes size error on firefox
		DOM.setStyleAttribute(getElement(), "height", "20px");
		
		textBox = new TextBox();
		textBox.setText("Write test here");
		add(textBox);
		textBox.setWidth("500px");
		textBox.setFocus(true);
		
		final Button delete = new Button();
		delete.setText("Delete");
		delete.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {	
				try{//This monster line deletes the testcase panel and removes that panel from the list
					//if you get errors here when changing something, you may need to add/remove a getParent()
				((TestCaseWidget)delete.getParent().getParent().getParent().getParent().getParent().getParent()).removeFromList(((TestCasePanel)delete.getParent()));
			} catch (Exception e){
				e.printStackTrace();
			}
				}
		});
		add(delete);
	}
	
	public String getTest(){
		return textBox.getText();
	}
}
