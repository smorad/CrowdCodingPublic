package project.server.submit;

import javax.jdo.PersistenceManager;

import project.client.submission.SubmitService;
import project.client.userstory.UserStoryInfo;
import project.shared.PMF;

public class SubmitServiceImpl implements SubmitService {


	public void submit(UserStoryInfo info) {
		PersistenceManager pm=PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(info);
		}finally{
			pm.close();
		}
	}

}
