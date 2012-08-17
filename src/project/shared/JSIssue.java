package project.shared;

import java.io.Serializable;
//This is used because JsLint cannot be used clientside, only serverside
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
