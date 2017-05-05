package com.hansheng.studynote.sqlite.Multile.model;

public class Tag {

	int id;
	String tag_name;

	// constructors
	public Tag() {

	}

	public Tag(String tag_name) {
		this.tag_name = tag_name;
	}

	public Tag(int id, String tag_name) {
		this.id = id;
		this.tag_name = tag_name;
	}

	// setter
	public void setId(int id) {
		this.id = id;
	}

	public void setTagName(String tag_name) {
		this.tag_name = tag_name;
	}

	// getter
	public int getId() {
		return this.id;
	}

	public String getTagName() {
		return this.tag_name;
	}
}
