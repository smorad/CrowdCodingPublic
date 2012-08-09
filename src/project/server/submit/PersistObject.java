package project.server.submit;


import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Indexed;
@Entity
public class PersistObject {
	@Id
	private Long id;
	
	@Indexed
	private boolean isDone;
	
	@Indexed
	private boolean checkedOut;
	
	
	void setDone(boolean b){isDone=b;}	
	public boolean isDone(){return isDone;}
	
	public void setCheckedOut(boolean b){checkedOut=b;}
	public boolean isCheckedOut(){return checkedOut;}
	
	public Long getId(){return id;}
	
	//for testing
	public String info() {return"";}
}
