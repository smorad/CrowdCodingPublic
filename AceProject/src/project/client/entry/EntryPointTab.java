package project.client.entry;

import java.util.ArrayList;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/*These are the individual tabs for each method
 */
public class EntryPointTab extends VerticalPanel {
	private static double labelWidth = 680.0, labelHeight = 25.0;
	private final ArrayList<TextBox> parameters = new ArrayList<TextBox>();
	private final ArrayList<TextBox> paramType = new ArrayList<TextBox>();
	private final TextBox returnType;
	private final TextArea methodDesc;
	private final TextBox methodName;

	public EntryPointTab() {
		setSize("685px", "260px");
		ScrollPanel scroll = new ScrollPanel();
		scroll.setSize("685px", "220px");
		add(scroll); // allows scrolling

		final LayoutPanel panel = new LayoutPanel();
		panel.setSize("685px", "200px");
		scroll.add(panel);

		methodDesc = new TextArea();
		methodDesc.setText("Method description goes here.");
		panel.add(methodDesc);
		panel.setWidgetLeftWidth(methodDesc, 0.0, Unit.PX, 685.0, Unit.PX);
		panel.setWidgetTopHeight(methodDesc, 0.0, Unit.PX, 75.0, Unit.PX);
		DOM.setStyleAttribute(methodDesc.getElement(), "width", "600px"); // firefox
																			// doesn't
																			// recognize
																			// setSize()

		methodName = new TextBox();
		methodName.setText("Method name goes here.");
		methodName.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				EntryPointWidget.updateTitle(methodName.getText());
			}

		});
		panel.add(methodName);
		panel.setWidgetLeftWidth(methodName, 0.0, Unit.PX, 275, Unit.PX);
		panel.setWidgetTopHeight(methodName, labelHeight * 3 + 5, Unit.PX,
				25.0, Unit.PX);

		returnType = new TextBox();
		returnType.setText("Return type goes here");
		panel.add(returnType);
		panel.setWidgetLeftWidth(returnType, 300, Unit.PX, 275, Unit.PX);
		panel.setWidgetTopHeight(returnType, labelHeight * 3 + 5, Unit.PX,
				25.0, Unit.PX);

		HorizontalPanel h = new HorizontalPanel();
		h.setSpacing(5);
		add(h);

		Button addParButton = new Button("Add Parameter");
		addParButton.setSize("150px", "30px");
		addParButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				parameters.add(new TextBox());
				paramType.add(new TextBox());
				TextBox t = parameters.get(parameters.size() - 1);
				TextBox type = paramType.get(paramType.size() - 1);
				t.setText("varName"); // example variable name
				type.setText("@String"); // example parameter
				panel.add(t);
				panel.add(type);
				panel.setWidgetLeftWidth(t, 0.0, Unit.PX, 200, Unit.PX);
				panel.setWidgetTopHeight(t, labelHeight
						* (parameters.size() + 3) + 5, Unit.PX, labelHeight,
						Unit.PX);
				panel.setWidgetLeftWidth(type, 200, Unit.PX, 200, Unit.PX);
				panel.setWidgetTopHeight(type, labelHeight
						* (parameters.size() + 3) + 5, Unit.PX, labelHeight,
						Unit.PX);
			}
		});
		h.add(addParButton);
		Button removeParButton = new Button("Remove last Parameter");
		removeParButton.setSize("200px", "30px");
		removeParButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (!parameters.isEmpty())
					panel.remove(parameters.remove(parameters.size() - 1)); // remove
																			// last
																			// parameter
				if (!paramType.isEmpty())
					panel.remove(paramType.remove(paramType.size() - 1)); // remote
																			// last
																			// parameter
																			// type
			}
		});
		h.add(removeParButton);
	}

	public String getName() {
		return methodName.getText();
	}

	public String getDesc() {
		return methodDesc.getText();
	}

	public String getReturnType() {
		return returnType.getText();
	}

	public ArrayList<String> getParameters() {
		ArrayList<String> result = new ArrayList<String>();
		for (int x = 0; x < parameters.size(); x++)
			result.add(paramType.get(x).getText() + " "
					+ parameters.get(x).getText());
		return result;
	}
}
