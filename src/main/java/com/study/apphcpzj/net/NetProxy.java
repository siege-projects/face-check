package com.study.apphcpzj.net;

import android.app.Application;

import com.study.apphcpzj.net.okgo.OkGoClient;
import com.study.apphcpzj.net.okgo.OkGoConfig;


/**
 * Created by ztt on 2017/9/21.
 *
 * 网络请求代理
 */

public class NetProxy {

	public static void initNetClient(Application application) {
		OkGoConfig.initOkGo(application);
	}

	public static IClient getClient() {
		return OkGoClient.getInstance();
	}

}
