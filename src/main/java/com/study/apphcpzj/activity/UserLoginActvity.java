package com.study.apphcpzj.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.study.apphcpzj.AdminMenuActivity;
import com.study.apphcpzj.Constants;
import com.study.apphcpzj.R;
import com.study.apphcpzj.base.BaseActivity;

/**
 * @author ztt : 2019-03-15
 */
public class UserLoginActvity extends BaseActivity {

	private EditText mEdSj;
	private EditText mEdPwd;
	private Button mBtnDl;
	private Button mBtnZc;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_act_login);
		mEdSj = findViewById(R.id.ed_sj);
		mEdPwd = findViewById(R.id.ed_pwd);
		mBtnDl = findViewById(R.id.btn_dl);
		mBtnZc = findViewById(R.id.btn_zc);
		setTitle("登录");

		mBtnDl.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clickLogin();
			}
		});
	}
	private String mPwd;
	private SharedPreferences sp;

	/**
	 * 点击注册
	 */
	private void clickLogin() {
		String sjh = mEdSj.getText().toString();
		String pwd = mEdPwd.getText().toString();

		if (TextUtils.isEmpty(sjh)) {
			makeToast("账号不能为空");
		} else if (TextUtils.isEmpty(pwd)) {
			makeToast("密码不能为空");
		} else {
			sp = getSharedPreferences("mPwd", MODE_PRIVATE);
			mPwd = sp.getString("mPwd", "");
			if (TextUtils.isEmpty(mPwd)) {
				mPwd = Constants.pwd;
			}

			if (Constants.admin.equals(sjh) && pwd.equals(mPwd)) {
				startAct(AdminMenuActivity.class);
				finish();
			} else {
				makeToast("账号或密码错误");
			}
		}
	}
}
