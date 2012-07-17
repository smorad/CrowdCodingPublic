package project.client;


import java.util.ArrayList;

import project.client.entry.EntryMethodInfo;
import project.client.entry.EntryPointTab;
import project.client.entry.EntryPointWidget;
import project.client.login.LoginInfo;
import project.client.login.LoginService;
import project.client.login.LoginServiceAsync;
import project.client.login.LoginWidget;
import project.client.screen.ScreenWidget;
import project.client.submission.SubmitService;
import project.client.submission.SubmitServiceAsync;
import project.client.tests.TestCaseInfo;
import project.client.tests.TestCaseWidget;
import project.client.tests.UnitTestInfo;
import project.client.tests.UnitTestWidget;
import project.client.userstory.UserStoryInfo;
import project.client.userstory.UserStoryWidget;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AceProject implements EntryPoint {
	
	//private static somethinginfo;
	//private static othersomthinginfo;
	
	private static LoginInfo loginInfo = null;
	private LoginWidget loginPanel; 
	private static ScreenWidget editor; 
	private static UserStoryInfo storyInfo;
	private static SubmitServiceAsync service=(SubmitServiceAsync)GWT.create(SubmitService.class);
	/**
	 * This is the entry point method.
	 * @wbp.parser.entryPoint
	 */
	public void onModuleLoad() {
		// Check login status using login service.
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
		      public void onFailure(Throwable error) {
		      }

		      public void onSuccess(LoginInfo result) {
		        loginInfo = result;
		        if(loginInfo.isLoggedIn()) {
		        	loadEditorAndService();
		        } else {
		          loadLogin();
		        }
		      }
		    });		
	}
	
	private void loadLogin() {
	    if(loginPanel==null)
	    	loginPanel=new LoginWidget(loginInfo);  //make sure only 1 service is running
	    RootLayoutPanel.get().add(new ScrollPanel(loginPanel));//see the html file for id
  }

	private void loadEditorAndService() {
		loadRandomly();
		System.out.println("Success");
	}
	
	private static void loadRandomly(){
		RootLayoutPanel.get().clear();
		storyInfo=new UserStoryInfo();
		storyInfo.setName("Demo");
		storyInfo.setDone(false);
		service.retrieve(storyInfo, new AsyncCallback(){
			public void onFailure(Throwable t){
				Window.alert("Nope");
			}
			public void onSuccess(Object r){
				//Window.alert("Yep");
			}
		});
		//instantiateRandomly(loginInfo);
		editor=new UserStoryWidget(loginInfo, storyInfo);
		RootLayoutPanel.get().add(new ScrollPanel(editor));
		
	}
	
	public static void instantiateRandomly(LoginInfo loginInfo){
		RootLayoutPanel.get().clear();
		System.out.println(storyInfo.isDone());
		
		
		ArrayList<ScreenWidget> list = new ArrayList<ScreenWidget>();
		UserStoryWidget story = new UserStoryWidget(loginInfo, storyInfo);
		System.out.println(story.getInfo().getStory());
		System.out.println(story.getInfo().getName());
		if(!story.getInfo().isDone())
			list.add(story);//add story
		
		EntryPointWidget ePoint = new EntryPointWidget(loginInfo, story.getInfo().getChild(), story.getInfo().getStory());
		System.out.println(ePoint.getInfo().isDone());
		if(!ePoint.getInfo().isDone())
			list.add(ePoint);  //add entry point
		
		//System.out.println(ePoint.getInfo().getNumMethods());
		for(int i= 0; i<ePoint.getInfo().getNumMethods(); i++){
			EntryMethodInfo e=ePoint.getInfo().getMethod(i);
			TestCaseInfo t=e.getTest();
			if(!t.isDone())
				list.add(new TestCaseWidget(loginInfo, t, e.getDescription()));
			for(int x=0; x<t.getNumTests(); x++){
				UnitTestInfo u=t.getTestInfo(x);
				if(!u.isDone())
					list.add(new UnitTestWidget(loginInfo, u, e.getDescription(), t.getTest(x)));
			}
		}
			
		if(list.isEmpty()){
			Window.alert("empty");
			return;
		}
		int a=(int)(Math.random()*list.size());
		editor=list.get(a);
		RootLayoutPanel.get().add(new ScrollPanel(editor));
		if(editor instanceof EditorContainer)
			((EditorContainer)editor).buildEditor();
	}
	
	@SuppressWarnings("rawtypes")
	public static void submit(){
		editor.submit();
		System.out.println("a6");
		service.submit(storyInfo, new AsyncCallback(){
			public void onFailure(Throwable t){
				Window.alert("failure");
			}
			public void onSuccess(Object result){
				//Window.alert("success");
			}
		});
		instantiateRandomly(loginInfo);
	}

	
	
}