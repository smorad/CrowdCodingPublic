package project.shared;

import java.io.Serializable;

public class JSIssue implements Serializable{
	
	private int line;
	private int character;
	private String reason;
	
	public JSIssue(){
		
	}
	
	public JSIssue(int line, int character, String reason){
		this.line=line;
		this.character=character;
		this.reason=reason;
	}
	
	public int getLine(){
		return line;
	}
	public int getCharacter(){
		return character;
	}
	public String getReason(){
		return reason;
	}

}
