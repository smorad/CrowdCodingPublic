package project.client.points;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;

public interface PointUpdateServiceAsync {
	void updatePoints( AsyncCallback<List<String>> a);
}
