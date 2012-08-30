package project.client.login;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("login")
// this is NEEDED for adding new RPC's the string "login" is referenced in the
// .gwt.xml file
public interface LoginService extends RemoteService {
	public LoginInfo login(String requestUri);
}