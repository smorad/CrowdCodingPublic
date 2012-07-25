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
	private static String name="cDemo";//started using Obj at bDemo10
	/**
	 * This is the entry point method.
	 * @wbp.parser.entryPoint
	 */
	@SuppressWarnings("rawtypes")
	public void onModuleLoad() {
		// Check login status using login service.
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
		      public void onFailure(Throwable error) {
		      }

		      public void onSuccess(final LoginInfo result) {
		    	  service.register(new AsyncCallback(){
					public void onSuccess(Object t){
						loginInfo = result;
				        if(loginInfo.isLoggedIn()) {
				        	loadEditorAndService();
				        } else {
				          loadLogin();
				        }
				        
				        //Window.alert("Worked");	
					}
					public void onFailure(Throwable t){
						Window.alert("Didn't work");
					}
		    	  });

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
		callCreate();
	}
	private static void callCreate(){
		service.create((UserStoryInfo)storyInfo, new AsyncCallback<UserStoryInfo>(){
			public void onFailure(Throwable t){
				Window.alert("bad luck");
			}
			public void onSuccess(UserStoryInfo info){
				storyInfo=info;
				editor=new UserStoryWidget(loginInfo, (UserStoryInfo)storyInfo);
				RootLayoutPanel.get().add(new ScrollPanel(editor));
				System.out.println("Success");
			}
		});
	}
	
	

	public static void instantiateRandomly(final LoginInfo loginInfo){
		RootLayoutPanel.get().clear();		
		
		service.retrieve(name, new AsyncCallback<InfoObject>(){
			public void onFailure(Throwable t){
				Window.alert("Nope");
			}
			public void onSuccess(InfoObject info){
				storyInfo=info;
				if(info==null){
					Window.alert("No more");
					return;
				}				
				
				
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
		System.out.println(storyInfo.isDone());
		callSubmit();
		
	}
	
	
	@SuppressWarnings("rawtypes")
	private static void callSubmit(){
		service.submit(storyInfo, new AsyncCallback(){
			public void onFailure(Throwable t){
				Window.alert("failure");
			}
			public void onSuccess(Object result){
				//Window.alert("success");
				instantiateRandomly(loginInfo);
			}
		});
	}

	
	
}