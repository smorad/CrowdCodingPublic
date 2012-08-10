package project.client.screen;

import java.util.List;

import project.shared.JSIssue;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SubmitServiceAsync {

	void sendCode(String string, Long points, AsyncCallback<String> callback);
	void doCheck(String s, AsyncCallback<List<JSIssue>> c);
	void instantiate(AsyncCallback c);
}