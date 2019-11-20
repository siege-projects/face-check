package com.study.apphcpzj.base.bmob;

import cn.bmob.v3.BmobObject;

/**
 * @author ztt : 2019-03-26
 */
public class HcpHcp extends BmobObject {

	private String sfz;
	private String name;
	// 车票编号
	private String cpNum;
	// 列车车次
	private String lccc;

	private boolean isJp = false;

	public boolean isJp() {
		return isJp;
	}

	public void setJp(boolean jp) {
		isJp = jp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSfz() {
		return sfz;
	}

	public void setSfz(String sfz) {
		this.sfz = sfz;
	}


	public String getCpNum() {
		return cpNum;
	}

	public void setCpNum(String cpNum) {
		this.cpNum = cpNum;
	}

	public String getLccc() {
		return lccc;
	}

	public void setLccc(String lccc) {
		this.lccc = lccc;
	}
}
