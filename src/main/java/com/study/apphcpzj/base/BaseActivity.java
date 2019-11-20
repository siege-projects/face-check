package com.study.apphcpzj.base;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.study.apphcpzj.Constants;
import com.study.apphcpzj.base.utils.ProgressDialogUtils;
import com.study.apphcpzj.base.utils.ToastUtils;
import com.study.apphcpzj.net.NetProxy;
import com.study.apphcpzj.net.Params;
import com.study.apphcpzj.net.okgo.CallBack;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * @author ztt : 2019-01-09
 */
public class BaseActivity extends AppCompatActivity {

	protected BaseActivity mActivity;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mActivity = this;
		addBackListener();
	}

	protected void addBackListener() {
		android.support.v7.app.ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}


	public void makeToast(String msg) {
		ToastUtils.makeLongToast(mActivity, msg);
	}

	public void makeToast(int msgRes) {
		ToastUtils.makeLongToast(mActivity, msgRes);
	}

	public void showProgressDialog() {
		ProgressDialogUtils.showProgress(mActivity, "加载中");
	}

	public void dismissProgressDialog() {
		ProgressDialogUtils.dismissProgress();
	}

	public void showDatePickerDialog(final Button btn) {
		String time = btn.getText().toString();
		int year = Integer.valueOf(time.substring(0, 4));
		int monthOfYear = Integer.valueOf(time.substring(5, 7));
		int dayOfMonth = Integer.valueOf(time.substring(8));
		DatePickerDialog datePickerDialog = new DatePickerDialog(mActivity,
				new DatePickerDialog.OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
					                      int monthOfYear, int dayOfMonth) {
						String strMonth = ++monthOfYear > 9 ? String
								.valueOf(monthOfYear) : "0" + monthOfYear;
						String strDay = dayOfMonth > 9 ? String
								.valueOf(dayOfMonth) : "0" + dayOfMonth;
						btn.setText(year + "-" + strMonth + "-" + strDay);
					}
				}, year, --monthOfYear, dayOfMonth);
		datePickerDialog.show();
		// 禁止弹出日期选择框之后点击输入日期输入
		datePickerDialog.getDatePicker().setDescendantFocusability(
				DatePicker.FOCUS_BLOCK_DESCENDANTS);
		// 弹出Dialog的时候会弹出输入法，将弹出的输入法关闭。必须在show()方法之后调用
		datePickerDialog.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
						| WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
	}

	public void startAct(Class c) {
		startActivity(new Intent(mActivity, c));
	}


	public void showDateMD(final TextView textView) {
		//时间选择器
		TimePickerView pvTime = new TimePickerBuilder(mActivity, new OnTimeSelectListener
				() {
			@Override
			public void onTimeSelect(Date date, View v) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM", Locale.getDefault());
				textView.setText(format.format(date));
			}
		})
				.setType(new boolean[]{true, true, false, false, false, false})
				.build();

		pvTime.show();
	}




	/**
	 * @param title            标题
	 * @param content             内容
	 * @param positive         确定
	 * @param negative         取消
	 * @param positiveListener 确定
	 * @param negativeListener 取消
	 * @return dia
	 */
	public AlertDialog showAlertDialog(String title, String content
			, String positive
			, String negative
			, DialogInterface.OnClickListener positiveListener
			, DialogInterface.OnClickListener negativeListener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setMessage(content);
		if (positiveListener != null) {
			builder.setPositiveButton(positive, positiveListener);
		}
		if (negativeListener != null) {
			builder.setNegativeButton(negative, negativeListener);
		}
		AlertDialog dialog = builder.create();
		dialog.show();
//		dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#2F88F0"));
//		dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#2F88F0"));
		return dialog;
	}


	public void requestPost(Params params, CallBack callBack) {
		NetProxy.getClient().post(params, callBack);
	}
}
