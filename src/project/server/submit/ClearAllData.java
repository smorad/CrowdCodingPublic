package project.server.submit;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class ClearAllData {
	public ClearAllData() {
		Objectify o = ObjectifyService.begin();
		o.delete(o.query(UserStoryPersist.class).fetchKeys());
		o.delete(o.query(UnitTestPersist.class).fetchKeys());
		o.delete(o.query(TestCasePersist.class).fetchKeys());
		o.delete(o.query(EntryPointPersist.class).fetchKeys());
		o.delete(o.query(EntryMethodPersist.class).fetchKeys());
		o.delete(o.query(AceEditorPersist.class).fetchKeys());
	}

}
