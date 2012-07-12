package project.client.identifier;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CodeIdentifierServiceAsync {

	void storeType(int row, String type, @SuppressWarnings("rawtypes") AsyncCallback callback);

}
