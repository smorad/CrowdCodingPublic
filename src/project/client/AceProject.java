package project.client;


import project.client.login.LoginInfo;
import project.client.login.LoginService;
import project.client.login.LoginServiceAsync;
import project.client.login.LoginWidget;
import project.client.screen.ScreenWidget;
import project.client.submission.SubmitService;
import project.client.submission.SubmitServiceAsync;
import project.client.userstory.UserStoryInfo;
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
		instantiateRandomly();
		//editor=new project.client.editor.AceEditorWidget(loginInfo);
		RootLayoutPanel.get().add(new ScrollPanel(editor));
		if(editor instanceof EditorContainer)
			((EditorContainer)editor).buildEditor();
	}
	
	private static void instantiateRandomly(){
		int a=(int)(Math.random()*5);
		switch(a){
			case 0: //editor=new project.client.editor.AceEditorWidget(loginInfo);
					break;
			case 1: //editor=new project.client.userstory.UserStoryWidget(loginInfo);
					break;	
			case 2: //editor=new project.client.entry.EntryPointWidget(loginInfo, null, "Blah");
					break;
			case 3: //editor=new project.client.tests.TestCaseWidget(loginInfo);
					break;
			case 4: //editor=new project.client.tests.UnitTestWidget(loginInfo);
					break;	
			case 5: //editor=new project.client.identifier.CodeIdentifier(loginInfo);
					break;
		}
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