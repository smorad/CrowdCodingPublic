package project.client;


import project.client.editor.AceEditorWidget;
import project.client.editor.EditorScreenWidget;
import project.client.login.LoginInfo;
import project.client.login.LoginService;
import project.client.login.LoginServiceAsync;
import project.client.login.LoginWidget;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AceProject implements EntryPoint {
	
	
	private LoginInfo loginInfo = null;
	private LoginWidget loginPanel; 
	private EditorScreenWidget editor; 
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
	    	loginPanel=new LoginWidget(loginInfo);
	    RootLayoutPanel.get().add(new ScrollPanel(loginPanel));//see the html file for id
  }

	private void loadEditorAndService() {
		if(editor==null)
			editor=new EditorScreenWidget(loginInfo);
		RootLayoutPanel.get().add((editor));
		editor.startEditor();
		System.out.println("Success");
	}

	
	
}