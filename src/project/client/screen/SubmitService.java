package project.client.screen;

import java.util.List;

import project.client.login.LoginInfo;
import project.shared.JSIssue;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("AceProjectPath")
public interface SubmitService extends RemoteService {
	String sendCode(String string, Long points);

	List<JSIssue> doCheck(String s);

	void instantiate();

	String setProfile(LoginInfo info);
}
