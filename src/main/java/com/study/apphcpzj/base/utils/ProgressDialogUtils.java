package com.study.apphcpzj.base.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by ztt on 2017/9/21.
 * <p>
 * 加载框
 */

public class ProgressDialogUtils {

	private static ProgressDialog progressDialog = null;

	public static void showProgress(Context c, String str, boolean touchCancel) {

		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
			progressDialog = null;
		}

		progressDialog = new ProgressDialog(c);
		progressDialog.setMessage(str);
		progressDialog.setCancelable(true);
		progressDialog.setCanceledOnTouchOutside(touchCancel);
		try {
			progressDialog.show();
		} catch (Exception e) {
			dismissProgress();
		}
	}

	public static void showProgress(Context c, String str) {
		showProgress(c, str, true);
	}

	public static void dismissProgress() {
		try {
			if (progressDialog != null) {
				progressDialog.dismiss();
				progressDialog = null;
			}
		} catch (Exception e) {
			progressDialog = null;
		}
	}
}
