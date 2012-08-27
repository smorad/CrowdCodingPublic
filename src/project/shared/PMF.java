package project.shared;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

//Not sure if we still need this, remnant from when we used JDO
//But Objectify is based on JDO so I'm not sure
//Chances are we don't, but I'm being safe
public final class PMF {
	private static final PersistenceManagerFactory pmfInstance = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");

	private PMF() {
	}

	public static PersistenceManagerFactory get() {
		return pmfInstance;
	}
}