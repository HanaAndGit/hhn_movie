package com.yi.model;


public class Movie {
	private int no;
	private String title;
	private String content;
	private String file;
	private String time;
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Movie(int no, String title, String content, String file, String time) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.file = file;
		this.time = time;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Movie [no=" + no + ", title=" + title + ", content=" + content + ", file=" + file + ", time=" + time
				+ "]";
	}
	
	
	
	
}
