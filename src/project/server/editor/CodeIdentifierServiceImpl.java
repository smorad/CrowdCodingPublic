package project.server.editor;

import project.client.identifier.CodeIdentifierService;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CodeIdentifierServiceImpl extends RemoteServiceServlet implements CodeIdentifierService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void storeType(int row, String type) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Key c = KeyFactory.createKey("ProjectName", "Project name goes here");
		Entity code = new Entity(c);
		code.setProperty("row "+ row, type);	
		datastore.put(code);	//puts code in datastore
		System.out.println(code.getProperty("row " + row));
		
	}

}
