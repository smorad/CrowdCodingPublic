package project.client.editor;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("CodeIdentifierPath")
public interface CodeIdentifierService extends RemoteService {
	void storeType(int row, String type);

}
