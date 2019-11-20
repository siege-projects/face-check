package com.study.apphcpzj.base.bmob;

import cn.bmob.v3.BmobObject;

/**
 * @author ztt : 2019-03-26
 *
 * 检票车次
 */
public class HcpJpCc extends BmobObject {

	// 检票车次
	private String jpcc;
	// 是否检票
	private boolean sfjp;

	public String getJpcc() {
		return jpcc;
	}

	public void setJpcc(String jpcc) {
		this.jpcc = jpcc;
	}

	public boolean isSfjp() {
		return sfjp;
	}

	public void setSfjp(boolean sfjp) {
		this.sfjp = sfjp;
	}
}
