package com.javaex.vo;

public class GuestbookVO {
	
	private int no;
	private String name;
	private String pw;
	private String content;
	private String reg_date;
	
	
	
	
	@Override
	public String toString() {
		return "GuestbookVO [no=" + no + ", name=" + name + ", pw=" + pw + ", content=" + content + ", reg_date="
				+ reg_date + "]";
	}
	


	



	public GuestbookVO(int no, String pw) {
		this.no = no;
		this.pw = pw;
	}







	public GuestbookVO(String name, String pw, String content) {
		this.name = name;
		this.pw = pw;
		this.content = content;
	}


	public GuestbookVO() {
	}

	public GuestbookVO(int no, String name, String pw, String content, String reg_date) {
		this.no = no;
		this.name = name;
		this.pw = pw;
		this.content = content;
		this.reg_date = reg_date;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}




	
	

	
}
