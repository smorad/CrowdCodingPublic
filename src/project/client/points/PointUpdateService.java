package project.client.points;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.ui.VerticalPanel;

@RemoteServiceRelativePath("points")
public interface PointUpdateService extends RemoteService {
	List<String> updatePoints();
}
