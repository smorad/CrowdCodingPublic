package project.server.submit;

import com.google.appengine.api.datastore.Key;

public interface PersistObject {
	void setDone(boolean b);
	boolean isDone();
	
	Key getKey();
}
