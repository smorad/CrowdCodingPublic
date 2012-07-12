package project.client.submission;

import project.client.userstory.UserStoryInfo;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SubmitServiceAsync {
	void submit(UserStoryInfo info, AsyncCallback callback);
}
