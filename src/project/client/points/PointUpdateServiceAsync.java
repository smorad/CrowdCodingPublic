package project.client.points;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PointUpdateServiceAsync {
	void updatedList( AsyncCallback<List<String>> a);
	void updatedPoints(AsyncCallback<Long> a);
}
