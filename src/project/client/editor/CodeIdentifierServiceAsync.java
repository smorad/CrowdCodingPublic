package project.client.editor;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CodeIdentifierServiceAsync {

	void storeType(int row, String type, AsyncCallback<Void> callback);

}
