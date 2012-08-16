package project.client.screen;

import java.util.List;

import project.client.AceProject;
import project.client.editor.AceEditorWidget;
import project.client.login.LoginInfo;
import project.client.points.PointUpdateService;
import project.client.points.PointUpdateServiceAsync;
import project.client.profile.ProfileWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

public abstract class ScreenWidget extends VerticalPanel{
	
	protected VerticalPanel mainPanel = new VerticalPanel();
	private Label userPoints=new Label("0");
	private VerticalPanel pointRank=new VerticalPanel();
	private Anchor signOutLink = new Anchor("Sign Out");
	private  PointUpdateServiceAsync pointUpdater;
	protected SubmitServiceAsync submitService;	//temporary
	
	private Label lblYouAreSigned;
	private Label lab;
	
	private Long points=1L;
	private VerticalPanel userPointsPanel;
	private HorizontalPanel hPanel = new HorizontalPanel();	
	private Label spacer = new Label();
	private Label spacer1 = new Label();
	private Label spacer2 = new Label();
	private Label userPointSpacer = new Label();
	public AceEditorWidget a;
	protected SimplePanel instructions = new SimplePanel();
	protected HorizontalPanel horizontalPanel;
	protected Anchor prefs;
	protected Button button;
	protected HandlerRegistration remover;
	private Label lab1;
	
	protected static LoginInfo loginInfo;
	
	boolean temporaryForTestingPurposes=true;
	
	public ScreenWidget(){
		buildButtonUI();
		buildUI();
		buildPointDisplays();
		if(temporaryForTestingPurposes){
			startService();
			RootPanel.get("ace").getElement().setAttribute("align", "center");
			DOM.setStyleAttribute(RootPanel.get("ace").getElement(), "marginLeft", "auto");  //removes border
			DOM.setStyleAttribute(RootPanel.get("ace").getElement(), "marginRight", "auto");  //removes border
			
			DOM.setStyleAttribute(instructions.getElement(), "height", "200px");  //removes border
			DOM.setStyleAttribute(instructions.getElement(), "width", "750px");  //removes border
		}

	}
	


	private void buildUI() {
								
		//hPanel.add(new Label());			
		userPointsPanel = new VerticalPanel();
		hPanel.add(userPointsPanel);
		userPointsPanel.setSize("250px", "40px");
		
		spacer.setSize("1px", Window.getClientHeight()/4+"px");
		spacer1.setSize("1px", Window.getClientHeight()/4+"px");
		
		if(loginInfo!=null)
			lab = new Label(loginInfo.getNickname());
		else
			lab = new Label();
		lab1 = new Label("Your Points:");
		lab1.setHorizontalAlignment(ALIGN_CENTER);
		lab.setHorizontalAlignment(ALIGN_CENTER);
		userPointsPanel.add(spacer);
		userPointsPanel.add(lab);
		userPointsPanel.add(userPointSpacer);
		//userPointSpacer.setSize("1px", )
		userPointsPanel.add(lab1);
		lab1.setHeight("40px");
		lab.setStyleName("gwt-DialogBox");
		lab.setSize("150px", "80px");
		
		final VerticalPanel contentPanel = new VerticalPanel();
		hPanel.add(contentPanel);
		hPanel.setCellHorizontalAlignment(userPointsPanel, ALIGN_RIGHT);
		contentPanel.setSize("750px", "750px");
		
		spacer2.setSize("75px", "1px");
		hPanel.add(spacer2);
		
		Label lblCrowdcoding = new Label("Crowd Coding");
		//titleSpacer.setText("This is a title lalalalalala");;
		lblCrowdcoding.setHeight("30px");
		add(lblCrowdcoding);
		//titleSpacer1.setText("weeeeeeeeeeeeeeeeeeeeeeeeee ");
		lblCrowdcoding.setStyleName("gwt-Title");
		lblCrowdcoding.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		contentPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		//contentPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		contentPanel.add(instructions);
		contentPanel.add(mainPanel);
		mainPanel.setSize("750px", "650px");
		
		VerticalPanel footerPanel = new VerticalPanel();
		contentPanel.add(footerPanel);
		footerPanel.setSize("750px", "160px");
		add(hPanel);	
		
		horizontalPanel = new HorizontalPanel();
		footerPanel.add(horizontalPanel);
		horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		horizontalPanel.setSize("750px", "40px");
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
		
		HorizontalPanel bottomFooterPanel = new HorizontalPanel();
		footerPanel.add(bottomFooterPanel);
		bottomFooterPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		bottomFooterPanel.setSpacing(5);
		bottomFooterPanel.setSize("750px", "40px");
		
				
				
		Label label = new Label("Editor by daveho@Github");
		bottomFooterPanel.add(label);
		label.setSize("180px", "23px");

		bottomFooterPanel.add(signOutLink);
		prefs=new Anchor("Set Preferences");
		bottomFooterPanel.add(prefs);
		final ScreenWidget t=this;
		prefs.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent e){
				RootPanel.get("ace").clear();
				RootPanel.get("ace").add(new ProfileWidget(t));

			}
		});

		//Current user
		String text;
		if(loginInfo!=null)
			text=loginInfo.getNickname();
		else
			 text="null";
		lblYouAreSigned=new Label("You are signed in as "+text);
		bottomFooterPanel.add(lblYouAreSigned);
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
			//hPanel.setCellHorizontalAlignment(pointRank, ALIGN_CENTER);
			//pointRankPanel.setCellHorizontalAlignment(true, ALIGN_CENTER);
		}
		pointRank.setSize("250px", "40px");
		//pointRank.setHorizontalAlignment(ALIGN_CENTER);
		userPoints.setStyleName("gwt-DialogBox");
		
		userPointsPanel.add(userPoints);
		userPoints.setHorizontalAlignment(ALIGN_CENTER);
		userPointsPanel.setCellHorizontalAlignment(userPoints, ALIGN_CENTER);
		userPointsPanel.setCellHorizontalAlignment(lab, ALIGN_CENTER);
		userPointsPanel.setCellHorizontalAlignment(lab1, ALIGN_CENTER);
		userPoints.setSize("250px", "20px");
		userPoints.setStyleName("userPoints");
		
	}
	
	private void callRankUpdateService(){  //leaderboard update
		pointUpdater.updatedList( new AsyncCallback<List<String>>(){
			public void onFailure(Throwable caught){
			}
			
			public void onSuccess(List<String> result){
				pointRank.clear();
				for(int x=0; x<result.size(); x++){
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
		lab.setText(loginInfo.getNickname());
		lblYouAreSigned.setText("You are signed in as "+loginInfo.getNickname());	
	}
	
	private void buildButtonUI(){
	}
	
	protected void onLoad(){
		super.onLoad();
		if(temporaryForTestingPurposes)
		updatePoints();
	}
	

	public abstract void UI();
	
	public abstract void submit();
	
	public static void setLoginInfo(LoginInfo l){
		loginInfo=l;
	}
	public static LoginInfo getLoginInfo(){
		return loginInfo;
	}
	
}
