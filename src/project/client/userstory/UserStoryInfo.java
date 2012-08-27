package project.client.userstory;

import project.client.InfoObject;
import project.client.entry.EntryPointInfo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class UserStoryInfo implements IsSerializable, InfoObject {

	private boolean isDone;
	private String story;
	private String name;
	private EntryPointInfo childInfo;// child
	private Long keyString;

	public UserStoryInfo() {
		story = "story";
		name = "name";
		childInfo = new EntryPointInfo();
	}

	public void setStory(String story) {
		this.story = story;
		childInfo.setStory(story);
	}

	public String getStory() {
		return story;
	}

	public void deleteChild() {
		childInfo = new EntryPointInfo();
	}

	public EntryPointInfo getChild() {
		return childInfo;
	}

	public void setChild(EntryPointInfo c) {
		childInfo = c;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean isDone() {
		return isDone;
	}

	@Override
	public void setDone(boolean bool) {
		isDone = bool;
	}

	@Override
	public void setKeyString(Long s) {
		keyString = s;
	}

	@Override
	public Long getKeyString() {
		return keyString;
	}

	// for testing
	@Override
	public String info() {
		return "name is: " + name + "\nstory is: " + story;
	}
}
