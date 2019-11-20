package com.study.apphcpzj.baidu.bean;

import com.study.apphcpzj.net.okgo.ResultBean;

/**
 * @author ztt : 2019-03-26
 */
public class BaseBean extends ResultBean {


	/**
	 * error_code : 223101
	 * log_id : 815967402
	 * error_msg :  group is already exist
	 */

	private int error_code;
	private long log_id;
	private String error_msg;

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	public long getLog_id() {
		return log_id;
	}

	public void setLog_id(long log_id) {
		this.log_id = log_id;
	}

	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}
}
