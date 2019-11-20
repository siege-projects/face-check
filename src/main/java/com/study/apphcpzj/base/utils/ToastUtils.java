package com.study.apphcpzj.base.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ztt on 16/7/7.
 * <p>
 * Toast工具类。新的Toast会将上一个关闭
 */
public class ToastUtils {

	private static Toast toast;

	public static void makeShortToast(Context context, String content) {
		makeToast(context.getApplicationContext(), content, Toast.LENGTH_SHORT);
	}


	public static void makeShortToast(Context context, int id) {
		if (context != null) {
			makeShortToast(context, context.getResources().getString(id));
		}
	}


	public static void makeLongToast(Context context, int id) {
		makeLongToast(context.getApplicationContext(), context.getResources().getString(id));
	}

	public static void makeLongToast(Context context, String content) {
		makeToast(context.getApplicationContext(), content, Toast.LENGTH_LONG);
	}

	private static void makeToast(Context context, String content, int duration) {
//		cancleToast();
		toast = Toast.makeText(context, content, duration);
		toast.setText(content);
		toast.show();
	}

	private static void cancleToast() {
		if (toast != null) {
			toast.cancel();
			toast = null;
		}
	}
}
