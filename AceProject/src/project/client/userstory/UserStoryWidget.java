package project.client.userstory;

import project.client.screen.ScreenWidget;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextArea;

//Simple and self explanatory
public class UserStoryWidget extends ScreenWidget {

	private TextArea area;
	private final UserStoryInfo uInfo;
	private final HTML h = new HTML();

	public UserStoryWidget(UserStoryInfo uInfo) {
		setSize("1150px", "768px");
		this.uInfo = uInfo;
		UI();
	}

	@Override
	public void UI() {
		h.setStyleName("h1");
		h.setHTML("<b>This is the user story phase. Have a program you would like to create in mind, and "
				+ "write the specifications here. Please be as descriptive and thorough as possible, as this is all the information"
				+ "all the other users will have to create the program.");
		instructions.add(h);

		area = new TextArea();
		DOM.setStyleAttribute(area.getElement(), "width", "750px");
		DOM.setStyleAttribute(area.getElement(), "height", "450px");

		mainPanel.add(area);
		area.setText(uInfo.getStory()); // this should usually be null, unless
										// someone else is editing user story
	}

	@Override
	public void submit() {
		uInfo.setStory(area.getText());
		uInfo.setDone(true);
	}

	public UserStoryInfo getInfo() {
		return uInfo;
	}
}
