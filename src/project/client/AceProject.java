package project.client;

import project.client.editor.AceEditorInfo;
import project.client.editor.AceEditorWidget;
import project.client.endpage.EndPageWidget;
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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

/* This is here to start all the services and serve as an entrypoint
 * Depending on which info type the server serves, this will load the appropriate widget
 * with the info served
 * If you are adding a new service/RPC, initialize it here
 */
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AceProject implements EntryPoint {

	private static LoginInfo loginInfo = null;
	private LoginWidget loginPanel;
	private static ScreenWidget editor;
	private static InfoObject storyInfo;
	private static SubmitServiceAsync service = (SubmitServiceAsync) GWT
			.create(SubmitService.class);
	private static String name = "cDemo";// started using Obj at bDemo10

	/**
	 * This is the entry point method.
	 * 
	 * @wbp.parser.entryPoint
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public void onModuleLoad() {
		// Check login status using login service.
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL(),
				new AsyncCallback<LoginInfo>() {

					@Override
					public void onFailure(Throwable error) {
					}

					@Override
					public void onSuccess(final LoginInfo result) {
						ScreenWidget.setLoginInfo(result);

						service.register(new AsyncCallback() {
							@Override
							public void onSuccess(Object t) {
								loginInfo = result;
								if (loginInfo.isLoggedIn()) {
									loadEditorAndService();
								} else {
									loadLogin();
								}

							}

							@Override
							public void onFailure(Throwable t) {
								Window.alert("Didn't work");
							}
						});
					}
				});
	}

	private void loadLogin() {
		if (loginPanel == null)
			loginPanel = new LoginWidget(loginInfo); // make sure only 1 service
														// is running
		RootPanel.get("ace").add(new ScrollPanel(loginPanel));// see the html
																// file for id
	}

	private void loadEditorAndService() {
		RootPanel.get("ace").clear();
		storyInfo = new UserStoryInfo();
		((UserStoryInfo) storyInfo).setName(name);
		callCreate();
	}

	private static void callCreate() {
		service.create((UserStoryInfo) storyInfo,
				new AsyncCallback<UserStoryInfo>() {

					@Override
					public void onFailure(Throwable t) {
						t.printStackTrace();
					}

					@Override
					public void onSuccess(UserStoryInfo info) {
						storyInfo = info;
						editor = new UserStoryWidget((UserStoryInfo) storyInfo);
						RootPanel.get("ace").add(new ScrollPanel(editor));
						System.out.println("Success");
					}
				});
	}

	public static void instantiateRandomly(final LoginInfo loginInfo) {
		RootPanel.get("ace").clear();

		service.retrieve(ScreenWidget.getLoginInfo(),
				new AsyncCallback<InfoObject>() {
					@Override
					public void onFailure(Throwable t) {
						t.printStackTrace();
					}

					@Override
					public void onSuccess(InfoObject info) {
						storyInfo = info;
						if (info == null) {
							RootPanel.get("ace").clear();
							RootPanel.get("ace").add(new EndPageWidget());
							return;
						}
						if (info instanceof EntryPointInfo)
							editor = new EntryPointWidget((EntryPointInfo) info);
						else if (info instanceof TestCaseInfo)
							editor = new TestCaseWidget((TestCaseInfo) info);
						else if (info instanceof UnitTestInfo)
							editor = new UnitTestWidget((UnitTestInfo) info);
						else if (info instanceof UserStoryInfo)
							editor = new UserStoryWidget((UserStoryInfo) info);
						else if (info instanceof AceEditorInfo)
							editor = new AceEditorWidget((AceEditorInfo) info);

						RootPanel.get("ace").add(new ScrollPanel(editor));
						if (editor instanceof EditorContainer)
							((EditorContainer) editor).buildEditor();
					}
				});
	}

	public static void submit() {
		editor.submit();
		if (editor instanceof EditorContainer)
			((EditorContainer) editor).clearTimer();
		RootPanel.get("ace").clear();
		callSubmit();
	}

	@SuppressWarnings("rawtypes")
	private static void callSubmit() {
		service.submit(storyInfo, new AsyncCallback() {
			@Override
			public void onFailure(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onSuccess(Object result) {
				instantiateRandomly(loginInfo);
			}
		});
	}
}