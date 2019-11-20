package com.study.apphcpzj.net;

/**
 * Created by ztt on 2017/9/21.
 * <p>
 * 网络请求客户端
 */

public interface IClient {

	void get(Params params, ICallBack callBack);

	void post(Params params, ICallBack callBack);

	void cancelQueue(String tag);
}
