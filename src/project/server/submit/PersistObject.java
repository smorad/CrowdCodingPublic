package project.server.submit;

public interface PersistObject {
	void setDone(boolean b);
	boolean isDone();
	
	Long getId();
	
	//for testing
	String info();
}
