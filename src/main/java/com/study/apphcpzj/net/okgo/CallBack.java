package com.study.apphcpzj.net.okgo;


import com.study.apphcpzj.R;
import com.study.apphcpzj.base.BaseActivity;
import com.study.apphcpzj.base.utils.LogUtil;
import com.study.apphcpzj.net.GsonUtil;
import com.study.apphcpzj.net.ICallBack;

import java.lang.reflect.ParameterizedType;
import java.net.SocketTimeoutException;


/**
 * Created by ztt on 2017/9/21.
 * <p>
 * isShowProgressDialog 控制是否需要显示dialog
 * <p>
 * <p>
 * onError 可以重写，自定义
 * success 可以重写自定义
 */
public abstract class CallBack<T extends ResultBean> implements ICallBack {

	protected BaseActivity activity;
	protected int type = 0;
	protected boolean isShowProgressDialog;

	public CallBack(BaseActivity mActivity) {
		this(mActivity, false);
	}

	public CallBack(BaseActivity activity, boolean isShowProgressDialog) {
		this.activity = activity;
		this.isShowProgressDialog = isShowProgressDialog;
	}


	@Override
	public void start() {
		if (isShowProgressDialog) {
			activity.showProgressDialog();
		}
	}

	@Override
	public void finished() {
		if (isShowProgressDialog) {
			activity.dismissProgressDialog();
		}
	}

	@Override
	public void error(Throwable e, int code, String msg) {
		String error = "";
		if (-1 == code) {
			if (e instanceof SocketTimeoutException) {
//				activity.makeToast(R.string.net_fault_timeout);
				error = activity.getString(R.string.net_fault_timeout);
			} else {
//				activity.makeToast(R.string.net_base_error);
				error = activity.getString(R.string.net_base_error);
			}
		} else {
//			activity.makeToast("网络请求错误，错误码：" + code);
			error = "网络请求错误，错误码：" + code;
		}

		onError(error);
	}

	@Override
	public void success(String result) {
		LogUtil.e("result:" + result);
		T bean = null;
		try {
			bean = GsonUtil.GsonToBean(result, (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
		} catch (Exception e) {
			LogUtil.e(e.getMessage());
		}

		if (activity.isFinishing()) {
			LogUtil.e("界面已经关闭");
			return;
		}
		if (bean == null) {
//			activity.makeToast(R.string.net_base_error_empty);
			onError(activity.getString(R.string.net_base_error_empty));
		} else {
			onSuccess(bean);
		}
	}

	/**
	 * 返回值，不为null
	 *
	 * @param resultBean 不为null
	 */
	public abstract void onSuccess(T resultBean);

	/**
	 * 返回值，不为null
	 */
	public void onError(String error) {
		activity.makeToast(error);
	}


}
