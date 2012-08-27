package project.client.submission;

import project.client.InfoObject;
import project.client.login.LoginInfo;
import project.client.userstory.UserStoryInfo;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SubmitServiceAsync {
	void submit(InfoObject info,
			@SuppressWarnings("rawtypes") AsyncCallback callback);

	void retrieve(LoginInfo info, AsyncCallback<InfoObject> callback);

	void create(UserStoryInfo info, AsyncCallback<UserStoryInfo> callback);

	void register(@SuppressWarnings("rawtypes") AsyncCallback call);
}
