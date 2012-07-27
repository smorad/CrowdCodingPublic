package project.client.screen;

import java.util.List;

import project.client.AceProject;
import project.client.editor.AceEditorWidget;
import project.client.login.LoginInfo;
import project.client.points.PointUpdateService;
import project.client.points.PointUpdateServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class ScreenWidget extends LayoutPanel{
	protected LayoutPanel mainPanel = new LayoutPanel();
	private Label userPoints=new Label("0");
	private VerticalPanel pointRank=new VerticalPanel();
	private Anchor signOutLink = new Anchor("Sign Out");
	private PointUpdateServiceAsync pointUpdater;
	private SubmitServiceAsync submitService;	
	private LoginInfo loginInfo;
	private Long points=0L;
	public AceEditorWidget a;
	
	public ScreenWidget(LoginInfo loginInfo){
		this.loginInfo=loginInfo;
		setSize("1150px", "768px");
		
		buildPointDisplays();
		buildButtonUI();
		startService();
		
		add(mainPanel);
		setWidgetTopHeight(mainPanel, 28.0, Unit.PX, 650.0, Unit.PX);
		setWidgetLeftWidth(mainPanel, 224.0, Unit.PX, 750.0, Unit.PX);
		
		//UI();
	}
	
	private void startService(){
		if (submitService == null)
			submitService = (SubmitServiceAsync) GWT
					.create(SubmitService.class);
		
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
		add(pointRank);
		setWidgetLeftWidth(pointRank, 18.0, Unit.PX, 119.0+30, Unit.PX);
		setWidgetTopHeight(pointRank, 53.0, Unit.PX, 500.0, Unit.PX);
		
		
		Label lab=new Label("Your current total points:");
		lab.setStyleName("gwt-DialogBox");
		add(lab);
		setWidgetLeftWidth(lab, 1001.0, Unit.PX, 147.0, Unit.PX);
		setWidgetTopHeight(lab, 53.0, Unit.PX, 18.0, Unit.PX);
		userPoints.setStyleName("gwt-DialogBox");
		
		add(userPoints);
		setWidgetLeftWidth(userPoints, 1001.0, Unit.PX, 119.0+50, Unit.PX);
		setWidgetTopHeight(userPoints, 79.0, Unit.PX, 18.0, Unit.PX);
		
	}
	
	private void callRankUpdateService(){  //leaderboard update
		pointUpdater.updatedList( new AsyncCallback<List<String>>(){
			public void onFailure(Throwable caught){
			}
			
			public void onSuccess(List<String> result){
				pointRank.clear();
				for(int x=0; x<result.size(); x++)
					pointRank.add(new Label(result.get(x)));
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
			}
		});
	}
	
	private void buildButtonUI(){
		Button button = new Button("Submit");
		add(button);
		button.setWidth("100px");
		setWidgetLeftWidth(button, 1001.0, Unit.PX, 119.0, Unit.PX);
		setWidgetTopHeight(button, 136.0, Unit.PX, 28.0, Unit.PX);
		add(signOutLink);
		
		setWidgetLeftWidth(signOutLink, 1001.0, Unit.PX, 100.0, Unit.PX);
		setWidgetTopHeight(signOutLink, 194.0, Unit.PX, 32.0,
				Unit.PX);

		
		
		Label label = new Label("Editor by daveho@Github");
		add(label);
		setWidgetLeftWidth(label, 1001.0, Unit.PX, 154.0, Unit.PX);
		setWidgetTopHeight(label, 743.0, Unit.PX, 25.0, Unit.PX);
	
		button.addClickHandler(new ClickHandler() {
			@Override
			/**
			 * Code to be overridden
			 */
			public void onClick(ClickEvent event) {
				try {
					callSubmitService();
					callRankUpdateService();
					callPointUpdateService();
					AceProject.submit();
				}

				catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}
		});
	}
	

	public abstract void UI();
	
	public abstract void submit();


	
	/*private void addInitialEditor(){
		add(panel);
		setWidgetTopHeight(panel, 27.0, Unit.PX, 650.0, Unit.PX);
		setWidgetLeftWidth(panel, 227.0, Unit.PX, 750.0, Unit.PX);
			
		String methodDescription="This is a description.\n"
				+"Parameters:\n"
				+"\ta-some int\n"
				+"\tb-some int\n"
				;
		String[] parameters={"int a", "int b"};
		String methodName="helloWorld", methodType="String";
		AceEditorWidget a=new AceEditorWidget(1L, methodDescription, parameters, methodName, methodType);
		a.setSize("697px", "496px");
		panel.add(a, methodName, false);
		panel.selectTab(0);
	}
	
	
	public void addMethod(long points, String methodDescription, String[] parameters, 
							String methodName, String methodType){
		addMethod(new AceEditorWidget(loginInfo, points, methodDescription, parameters,
					methodName, methodType));
	}
	public void addMethod(AceEditorWidget a){
		panel.add(a, a.getMethodName(), false);
		numTabs++;
	}
	
	public void startEditor(){
		Iterator<Widget> i=panel.iterator();
		while(i.hasNext())
			((AceEditorWidget)i.next()).buildEditor();
	}
	
	public void onTabSelected(SourcesTabEvents s, int tabIndex){
		currentIndex=tabIndex;
	}*/


}
