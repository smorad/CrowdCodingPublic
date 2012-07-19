package project.client;


import project.client.entry.EntryPointInfo;
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
		
	private static LoginInfo loginInfo = null;
	private LoginWidget loginPanel; 
	private static ScreenWidget editor; 
	private static InfoObject storyInfo;
	private static SubmitServiceAsync service=(SubmitServiceAsync)GWT.create(SubmitService.class);
	private static String name="Demo4";
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
		RootLayoutPanel.get().clear();
		storyInfo=new UserStoryInfo();
		((UserStoryInfo)storyInfo).setName(name);
		editor=new UserStoryWidget(loginInfo, (UserStoryInfo)storyInfo);
		RootLayoutPanel.get().add(new ScrollPanel(editor));
		System.out.println("Success");
	}
	

	public static void instantiateRandomly(final LoginInfo loginInfo){
		RootLayoutPanel.get().clear();		
		
		service.retrieve(name, new AsyncCallback<InfoObject>(){
			public void onFailure(Throwable t){
				Window.alert("Nope");
			}
			public void onSuccess(InfoObject info){
				System.out.println("Key is: "+info.getKeyString());
				System.out.println("isDone is: "+info.isDone());
				if(info instanceof EntryPointInfo)
					editor=new EntryPointWidget(loginInfo, (EntryPointInfo)info);
				else if(info instanceof TestCaseInfo)
					editor=new TestCaseWidget(loginInfo, (TestCaseInfo)info);
				else if(info instanceof UnitTestInfo)
					editor=new UnitTestWidget(loginInfo, (UnitTestInfo)info);
				else if(info instanceof UserStoryInfo)
					editor=new UserStoryWidget(loginInfo, (UserStoryInfo)info);
				
				RootLayoutPanel.get().add(new ScrollPanel(editor));
				if(editor instanceof EditorContainer)
					((EditorContainer)editor).buildEditor();
			}
		});
		
	}
	
	public static void submit(){
		editor.submit();
		if(storyInfo.getKeyString()==null)
			callCreate();
		else
			callSubmit();
		instantiateRandomly(loginInfo);
	}
	
	private static void callCreate(){
		System.out.println("Null again");
		service.create((UserStoryInfo)storyInfo, new AsyncCallback<UserStoryInfo>(){
			public void onFailure(Throwable t){
				Window.alert("bad luck");
			}
			public void onSuccess(UserStoryInfo info){
				storyInfo=info;
				System.out.println("Name is: "+info.getName());
				System.out.println("Key is: "+storyInfo.getKeyString());
			}
		});
	}
	@SuppressWarnings("rawtypes")
	private static void callSubmit(){
		System.out.println("Doesnt seem to be null this time.");
		service.submit(storyInfo, new AsyncCallback(){
			public void onFailure(Throwable t){
				Window.alert("failure");
			}
			public void onSuccess(Object result){
				//Window.alert("success");
			}
		});
	}

	
	
}