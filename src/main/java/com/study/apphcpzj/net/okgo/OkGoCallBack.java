package com.study.apphcpzj.net.okgo;


import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.study.apphcpzj.net.ICallBack;
import com.study.apphcpzj.net.Params;

/**
 * Created by ztt on 2017/9/21.
 * <p>
 * OkGo请求返回
 */

public class OkGoCallBack extends StringCallback {

	private ICallBack callBack;
	private Params params;
	private String url = "";
	StringBuilder builder1 = new StringBuilder();

	public OkGoCallBack(ICallBack callBack) {
		this.callBack = callBack;
	}

	public OkGoCallBack(ICallBack callBack, Params params) {
		this.callBack = callBack;
		this.params = params;
	}


	@Override
	public void onStart(Request<String, ? extends Request> request) {
		super.onStart(request);
		callBack.start();
	}

	@Override
	public void onSuccess(Response<String> response) {
		callBack.success(response.body());
	}

	@Override
	public void onError(Response<String> response) {
		super.onError(response);
		callBack.error(response.getException(), response.code(), response.message());
	}

	@Override
	public void onFinish() {
		super.onFinish();
		callBack.finished();
	}
}
