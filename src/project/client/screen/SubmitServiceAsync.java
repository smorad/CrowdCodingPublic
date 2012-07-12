package project.client.screen;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SubmitServiceAsync {

	void sendCode(String string, Long points, AsyncCallback<String> callback);
}