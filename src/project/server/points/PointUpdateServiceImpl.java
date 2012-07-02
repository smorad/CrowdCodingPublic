package project.server.points;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import project.client.points.PointUpdateService;

public class PointUpdateServiceImpl extends RemoteServiceServlet implements PointUpdateService{
	public List<String> updatePoints(){
		ArrayList<String> strings=new ArrayList<String>();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q=new Query("AceProjectUser").addSort("points", Query.SortDirection.DESCENDING);	
		List<Entity> allUsers=datastore.prepare(q).asList(FetchOptions.Builder.withLimit(5));

		for(int x=0; x<10; x++){
			if(x>=allUsers.size())
				break;
			Entity e=allUsers.get(x);
			strings.add((x+1)+": "+(String)e.getProperty("nickname"));
			strings.add((String)e.getProperty("points"));
			strings.add("");
		}
		strings.add("Person A");
		strings.add("100");
		strings.add("");
		return strings;
	}

}	
