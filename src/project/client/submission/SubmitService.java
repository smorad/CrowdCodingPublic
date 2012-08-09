package project.client.submission;

import project.client.InfoObject;
import project.client.userstory.UserStoryInfo;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("submit")
public interface SubmitService extends RemoteService{
	void submit(InfoObject info);
	InfoObject retrieve(String name);
	UserStoryInfo create(UserStoryInfo info);
	void register();
}
