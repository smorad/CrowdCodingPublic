package project.client.tests;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;


public class TestCasePanel extends HorizontalPanel{
	private TextBox textBox;

	public TestCasePanel(){
		DOM.setStyleAttribute(getElement(), "width", "750px");  //fixes size error on firefox
		DOM.setStyleAttribute(getElement(), "height", "20px");
		//setSpacing(10);
		
		textBox = new TextBox();
		textBox.setText("Write test here");
		add(textBox);
		textBox.setWidth("500px");
		
		final Button delete = new Button();
		delete.setText("Delete");
		delete.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {	
				System.out.println(delete.getParent().getClass());
				System.out.println(delete.getParent().getParent().getClass());
				System.out.println(delete.getParent().getParent().getParent().getClass());
				System.out.println(delete.getParent().getParent().getParent().getParent().getClass());
				System.out.println(delete.getParent().getParent().getParent().getParent().getParent().getClass());
				System.out.println(delete.getParent().getParent().getParent().getParent().getParent().getParent().getClass());
				System.out.println(delete.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getClass());
				try{
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
