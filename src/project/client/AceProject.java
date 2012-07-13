package project.client;


import java.util.ArrayList;

import project.client.entry.EntryPointTab;
import project.client.entry.EntryPointWidget;
import project.client.login.LoginInfo;
import project.client.login.LoginService;
import project.client.login.LoginServiceAsync;
import project.client.login.LoginWidget;
import project.client.screen.ScreenWidget;
import project.client.submission.SubmitService;
import project.client.submission.SubmitServiceAsync;
import project.client.tests.TestCaseWidget;
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
		instantiateRandomly(loginInfo);
		//editor=new project.client.editor.AceEditorWidget(loginInfo);
		RootLayoutPanel.get().add(new ScrollPanel(editor));
		if(editor instanceof EditorContainer)
			((EditorContainer)editor).buildEditor();
	}
	
	private static void instantiateRandomly(LoginInfo loginInfo){
		ArrayList<ScreenWidget> list = new ArrayList<ScreenWidget>();
		UserStoryWidget story = new UserStoryWidget(loginInfo, storyInfo);
		if(!story.getInfo().isDone()){
			list.add(story);  //add story
			if(!story.getInfo().getChild().isDone()){
				EntryPointWidget ePoint = new EntryPointWidget(loginInfo, story.getInfo().getChild(), story.getInfo().getStory());  //add entry point
				list.add(ePoint);
				for(int i= 0; i<story.getInfo().getChild().getNumMethods(); i++ )
					if(!story.getInfo().getChild().getMethod(i).isDone()){	
						if(!story.getInfo().getChild().getMethod(i).getTest().isDone()){  //adding testcaseinfo
							TestCaseWidget tCase = new TestCaseWidget(loginInfo, story.getInfo().getChild().getMethod(i).getTest());
							list.add(tCase);
							if(!tCase.getInfo().getTestInfo(i).isDone()){
								UnitTestWidget uTest = new UnitTestWidget(loginInfo, tCase.getInfo().getTestInfo(i));
								list.add(uTest);
							}
						

						
						
					}
						
					}
					}
			}
		
		
		int a=(int)(Math.random()*list.size());
		editor=list.get(a);
	}
	
	@SuppressWarnings("rawtypes")
	public static void submit(){
		editor.submit();
		service.submit(storyInfo, new AsyncCallback(){
			public void onFailure(Throwable t){
				Window.alert("failure");
			}
			public void onSuccess(Object result){
				Window.alert("success");
			}
		});
	}

	
	
}