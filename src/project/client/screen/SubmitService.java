package project.client.screen;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("AceProjectPath")
	public interface SubmitService extends RemoteService {
		String sendCode(String string, Long points);
	}

