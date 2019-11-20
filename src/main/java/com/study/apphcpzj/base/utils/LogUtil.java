package com.study.apphcpzj.base.utils;

import android.text.TextUtils;
import android.util.Log;


/**
 * Created by ztt on 16/7/26.
 * <p>
 * log工具类
 */

public class LogUtil {

	private static boolean IS_DEBUG = true;
	private static String TAG = "TAG";

	public static void setTAG(String TAG) {
		LogUtil.TAG = TAG;
	}

	public static void v(String msg) {
		if (IS_DEBUG) {
			if (TextUtils.isEmpty(msg)) {
				msg = "log为空";
			}
			Log.v(TAG, msg);
		}
	}

	public static void d(String msg) {
		if (IS_DEBUG) {
			if (TextUtils.isEmpty(msg)) {
				msg = "log为空";
			}
			Log.d(TAG, msg);
		}
	}

	public static void i(String msg) {
		if (IS_DEBUG) {
			if (TextUtils.isEmpty(msg)) {
				msg = "log为空";
			}
			Log.i(TAG, msg);
		}
	}

	public static void w(String msg) {
		if (IS_DEBUG) {
			if (TextUtils.isEmpty(msg)) {
				msg = "log为空";
			}
			Log.w(TAG, msg);
		}
	}

	public static void e(String msg) {
		if (IS_DEBUG) {
			if (TextUtils.isEmpty(msg)) {
				msg = "log为空";
			}
			Log.e(TAG, msg);
		}
	}

	public static void e(String tag, String msg) {
		if (IS_DEBUG) {
			if (TextUtils.isEmpty(msg)) {
				msg = "log为空";
			}
			Log.e(tag, msg);
		}
	}

}
