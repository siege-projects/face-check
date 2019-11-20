package com.study.apphcpzj.net.okgo;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.study.apphcpzj.base.utils.LogUtil;
import com.study.apphcpzj.net.ICallBack;
import com.study.apphcpzj.net.IClient;
import com.study.apphcpzj.net.Params;


/**
 * Created by ztt on 2017/9/21.
 * <p>
 * OKGO请求
 * <p>
 * 需要一个ICallBack接口的返回处理，
 * <p>
 * 可以直接使用CallBack
 */

public class OkGoClient implements IClient {


	private static OkGoClient instance = new OkGoClient();

	public static OkGoClient getInstance() {
		return instance;
	}


	public void getget(Params params, ICallBack callBack) {
		GetRequest<String> getRequest = OkGo.get(params.getUrl());
		if (!params.getHeaders().isEmpty()) {
			for (String header : params.getHeaders().keySet()) {
				getRequest.headers(header, params.getHeaders().get(header));
			}
		}

		/*
		 * 全部为String
		 */
		if (!params.getParames().isEmpty()) {
			for (String key : params.getParames().keySet()) {
				Object value = params.getParames().get(key);
				if (value instanceof String) {
					getRequest.params(key, (String) value);
				}
			}
		}
		LogUtil.e("callBack：" + callBack);
		LogUtil.e("url：" + params.getUrl());
		getRequest.tag(params.getUrl()).execute(new OkGoCallBack(callBack, params));
	}

	@Override
	public void get(Params params, ICallBack callBack) {
		PostRequest<String> getRequest = OkGo.post(params.getUrl());
		if (!params.getHeaders().isEmpty()) {
			for (String header : params.getHeaders().keySet()) {
				getRequest.headers(header, params.getHeaders().get(header));
			}
		}

		/*
		 * 全部为String
		 */
		if (!params.getParames().isEmpty()) {
			for (String key : params.getParames().keySet()) {
				Object value = params.getParames().get(key);
				if (value instanceof String) {
					getRequest.params(key, (String) value);
				}
			}
		}
		getRequest.tag(params.getUrl()).execute(new OkGoCallBack(callBack, params));
	}

	@Override
	public void post(Params params, ICallBack callBack) {
		PostRequest<String> postRequest = OkGo.post(params.getUrl());
		if (!params.getHeaders().isEmpty()) {
			for (String header : params.getHeaders().keySet()) {
				postRequest.headers(header, params.getHeaders().get(header));
			}
		}

		/*
		 * 全部为String
		 */
		if (!params.getParames().isEmpty()) {

			for (String key : params.getParames().keySet()) {
				Object value = params.getParames().get(key);
				if (value instanceof String) {
					postRequest.params(key, (String) value);
				}
			}
		}

		postRequest.tag(params.getUrl()).execute(new OkGoCallBack(callBack, params));

	}

	@Override
	public void cancelQueue(String tag) {
		OkGo.getInstance().cancelTag(tag);
	}
}
