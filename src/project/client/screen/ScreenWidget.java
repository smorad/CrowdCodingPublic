package project.client.screen;

import java.util.List;

import project.client.AceProject;
import project.client.editor.AceEditorWidget;
import project.client.login.LoginInfo;
import project.client.points.PointUpdateService;
import project.client.points.PointUpdateServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

public abstract class ScreenWidget extends VerticalPanel{
	
	protected LayoutPanel mainPanel = new LayoutPanel();
	private Label userPoints=new Label("0");
	private VerticalPanel pointRank=new VerticalPanel();
	private Anchor signOutLink = new Anchor("Sign Out");
	private PointUpdateServiceAsync pointUpdater;
	protected SubmitServiceAsync submitService;	//temporary
	private LoginInfo loginInfo;
	private Long points=1L;
	private VerticalPanel userPointsPanel;
	private HorizontalPanel hPanel = new HorizontalPanel();	
	private Label spacer = new Label();
	private Label spacer1 = new Label();
	public AceEditorWidget a;
	
	protected Button button;
	protected HandlerRegistration remover;
	
	public ScreenWidget(LoginInfo loginInfo){
		setSize(Window.getClientHeight() + "px",Window.getClientWidth()+"px");
		this.loginInfo=loginInfo;
		buildButtonUI();
		buildUI();
		buildPointDisplays();
		
		if(false){
			startService();
			RootPanel.get("ace").getElement().setAttribute("align", "center");
			DOM.setStyleAttribute(RootPanel.get("ace").getElement(), "marginLeft", "auto");  //removes border
			DOM.setStyleAttribute(RootPanel.get("ace").getElement(), "marginRight", "auto");  //removes border
		}

		spacer.setSize("1px", Window.getClientHeight()/4+"px");
	}
	


	private void buildUI() {

								
		//hPanel.add(new Label());			
		userPointsPanel = new VerticalPanel();
		hPanel.add(userPointsPanel);
		userPointsPanel.setSize("250px", "40px");
		
		spacer.setSize("1px", Window.getClientHeight()/4+"px");
		spacer1.setSize("1px", Window.getClientHeight()/4+"px");
		Label lab=new Label(loginInfo.getNickname());
		Label lab1 = new Label("Your Points:");
		userPointsPanel.add(spacer);
		userPointsPanel.add(lab);
		userPointsPanel.add(lab1);
		lab.setStyleName("gwt-DialogBox");
		lab.setSize("150px", "80px");
		
		final VerticalPanel verticalPanel_2 = new VerticalPanel();
		hPanel.add(verticalPanel_2);
		hPanel.setCellHorizontalAlignment(verticalPanel_2, ALIGN_RIGHT);
		hPanel.setCellHorizontalAlignment(userPointsPanel, ALIGN_LEFT);
		verticalPanel_2.setSize("750", "750");
		
		Label lblCrowdcoding = new Label("Crowd Coding");
		add(lblCrowdcoding);
		lblCrowdcoding.setStyleName("gwt-Title");
		lblCrowdcoding.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		verticalPanel_2.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel_2.add(mainPanel);
		mainPanel.setSize("750px", "650px");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel_2.add(verticalPanel);
		verticalPanel.setSize("750px", "160px");
		add(hPanel);	
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		horizontalPanel.setSize("100%", "40px");
		button = new Button("Submit");
		remover=button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				try {
					callSubmitService();
					AceProject.submit();
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		});
		
		horizontalPanel.add(button);
		button.setWidth("100px");
		
		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		verticalPanel.add(horizontalPanel_1);
		horizontalPanel_1.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel_1.setSpacing(5);
		horizontalPanel_1.setSize("750px", "40px");
		
				
				
		Label label = new Label("Editor by daveho@Github");
		horizontalPanel_1.add(label);
		label.setSize("180px", "23px");
		
		Label lblPreferencesWillGo = new Label("Preferences will go here");
		horizontalPanel_1.add(lblPreferencesWillGo);
		lblPreferencesWillGo.setSize("170px", "20px");
		horizontalPanel_1.add(signOutLink);
		
		//Current user
		if(loginInfo!=null){
			Label lblYouAreSigned = new Label("You are signed in as "+loginInfo.getNickname());
			horizontalPanel_1.add(lblYouAreSigned);
		}
		setInitialSize(); //causes window to center without resizing
							  
	}

	private void setInitialSize() {
		setHeight(Window.getClientHeight()+"px");
		setWidth(Window.getClientWidth()+"px");
		 userPointsPanel.setWidth(Window.getClientWidth()/5 + "px");
		System.out.println("Client width is: "+ Window.getClientWidth());
		Window.resizeTo(Window.getClientWidth()-1,Window.getClientHeight()-1);
		System.out.println("initial resize");
		
		
	}



	private void startService(){

		if (submitService == null)
			submitService = (SubmitServiceAsync) GWT
					.create(SubmitService.class);
		
		submitService.instantiate(new AsyncCallback(){
			public void onFailure(Throwable t){
				t.printStackTrace();
			}
			public void onSuccess(Object o){
				
			}
		});
		
		if(pointUpdater==null)
			pointUpdater=(PointUpdateServiceAsync) GWT
					.create(PointUpdateService.class);
		callRankUpdateService();
		callPointUpdateService();
		// Set up sign out hyperlink.
		signOutLink.setHref(loginInfo.getLogoutUrl());
	}
	
	private void buildPointDisplays(){  //displays player points
		pointRank.setStyleName("gwt-DialogBox");
		if(pointRank!=null){
			VerticalPanel pointRankPanel = new VerticalPanel();
			pointRankPanel.add(spacer1);
			pointRankPanel.add(pointRank);
			hPanel.add(pointRankPanel);
		}
		pointRank.setSize("500px", "28px");
		userPoints.setStyleName("gwt-DialogBox");
		
		userPointsPanel.add(userPoints);
		userPoints.setSize("150px", "20px");
		
	}
	
	private void callRankUpdateService(){  //leaderboard update
		pointUpdater.updatedList( new AsyncCallback<List<String>>(){
			public void onFailure(Throwable caught){
			}
			
			public void onSuccess(List<String> result){
				pointRank.clear();
				for(int x=0; x<result.size(); x++){
					System.out.println(result.get(x));
					pointRank.add(new Label(result.get(x)));
				}
			}
		});
	}
	
	private void callPointUpdateService(){
		pointUpdater.updatedPoints(new AsyncCallback<Long>(){
			public void onFailure(Throwable caught){
			}
			
			public void onSuccess(Long result){
				userPoints.setText(result.toString());
			}
		});
	}
	// Method used to call service
	public void callSubmitService() {
		submitService.sendCode("Success", points, new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				Window.alert("Failure");
			}

			public void onSuccess(String result) {
				//Window.alert("Success");
				updatePoints();
			}
		});
	}
	protected void updatePoints(){
		callRankUpdateService();
		callPointUpdateService();
	}
	
	private void buildButtonUI(){
	}
	

	public abstract void UI();
	
	public abstract void submit();
}
