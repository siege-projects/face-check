package com.study.apphcpzj.base.bmob;

import cn.bmob.v3.BmobObject;

/**
 * @author ztt : 2019-03-25
 */
public class HcpUser extends BmobObject {

	private String sfzh;
	private String name;
	private String face_token;

	public String getFace_token() {
		return face_token;
	}

	public void setFace_token(String face_token) {
		this.face_token = face_token;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
