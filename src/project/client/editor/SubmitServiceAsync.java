package project.client.editor;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SubmitServiceAsync {

	void sendCode(String string, AsyncCallback<String> callback);
}