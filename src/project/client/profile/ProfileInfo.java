package project.client.profile;

import project.client.InfoObject;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ProfileInfo implements IsSerializable, InfoObject {
	private int userStory;
	private int ePoint;
	private int sketch;
	private int testCase;
	private int unit;
	private boolean isDone;
	private Long key;
	
	
	@Override
	public boolean isDone() {
	return isDone;
	}

	@Override
	public void setKeyString(Long s) {
		key=s;
	}

	@Override
	public Long getKeyString() {
		return key;
	}
	public int getUserStory() {
		return userStory;
	}
	public void setUserStory(int userStory) {
		this.userStory = userStory;
	}
	public int getePoint() {
		return ePoint;
	}
	public void setePoint(int ePoint) {
		this.ePoint = ePoint;
	}
	public int getSketch() {
		return sketch;
	}
	public void setSketch(int sketch) {
		this.sketch = sketch;
	}
	public int getTestCase() {
		return testCase;
	}
	public void setTestCase(int testCase) {
		this.testCase = testCase;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}

	@Override
	public void setDone(boolean b) {
		isDone = b;
		
	}

	@Override
	public String info() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
