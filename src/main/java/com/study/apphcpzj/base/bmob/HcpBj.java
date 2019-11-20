package com.study.apphcpzj.base.bmob;

import cn.bmob.v3.BmobObject;

/**
 * @author ztt : 2019-03-25
 */
public class HcpBj extends BmobObject {

	private String phone;
	private String title;
	private long time;
	private long edTime;
	private String content;
	private boolean isSc;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isSc() {
		return isSc;
	}

	public void setSc(boolean sc) {
		isSc = sc;
	}

	public long getEdTime() {
		return edTime;
	}

	public void setEdTime(long edTime) {
		this.edTime = edTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
