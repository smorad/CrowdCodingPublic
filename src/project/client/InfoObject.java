package project.client;

public interface InfoObject {
	void setDone(boolean b);
	boolean isDone();
	
	void setKeyString(Long s);
	Long getKeyString();
	
	//for testing
	String info();
}
