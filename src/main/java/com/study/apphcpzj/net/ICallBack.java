package com.study.apphcpzj.net;

/**
 * Created by ztt on 2017/9/21.
 * <p>
 * 网络请求返回接口
 */

public interface ICallBack {

	void start();

	void finished();

	void success(String result);

	void error(Throwable e, int code, String msg);
}
