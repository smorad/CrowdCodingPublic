package project.client;
//A wrapper class
public interface InfoObject {
	void setDone(boolean b);
	boolean isDone();
	
	void setKeyString(Long s);
	Long getKeyString();
	
	//for testing
	String info();
}
