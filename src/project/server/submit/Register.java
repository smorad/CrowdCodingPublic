package project.server.submit;

import com.googlecode.objectify.ObjectifyService;
/*Registers classes with objectify
 * If adding a new info/persist class, register the persist class here
 */
public class Register {
	static{
		//temporary solution for testing purposes
				ObjectifyService.register(EntryMethodPersist.class);
				ObjectifyService.register(EntryPointPersist.class);
				ObjectifyService.register(TestCasePersist.class);
				ObjectifyService.register(UnitTestPersist.class);
				ObjectifyService.register(UserStoryPersist.class);
				ObjectifyService.register(AceEditorPersist.class);
				ObjectifyService.register(PersistObject.class);			
	}
}
