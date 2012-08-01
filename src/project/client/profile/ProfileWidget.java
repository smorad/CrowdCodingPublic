package project.client.profile;

import com.google.gwt.event.dom.client.DragEvent;
import com.google.gwt.event.dom.client.DragHandler;
import com.google.gwt.user.client.ui.Button;
import com.kiouri.sliderbar.client.solution.simplehorizontal.SliderBarSimpleHorizontal;
import com.kiouri.sliderbar.client.view.SliderBar;
import com.kiouri.sliderbar.client.view.SliderBarHorizontal;

import project.client.login.LoginInfo;
import project.client.screen.ScreenWidget;

public class ProfileWidget extends ScreenWidget {
	private int pos;

	public ProfileWidget(LoginInfo loginInfo) {
		super(loginInfo);
		setSize("1150px", "768px");
		UI();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void UI() {
		//final SliderBarSimpleHorizontal slide = new SliderBarSimpleHorizontal(100, "200px", true);
		/*slide.setHeight("50px");
		slide.setWidth("300px");
		slide.setMaxValue(100);
		slide.addDragHandler(new DragHandler(){
			@Override
			public void onDrag(DragEvent event) {
				pos = slide.getValue();			
			}
			
		});*/
		//mainPanel.add(slide);
		
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		
	}

}
