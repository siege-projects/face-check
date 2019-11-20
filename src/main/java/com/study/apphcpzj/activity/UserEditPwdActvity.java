package com.study.apphcpzj.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.study.apphcpzj.Constants;
import com.study.apphcpzj.R;
import com.study.apphcpzj.base.BaseActivity;

/**
 * @author ztt : 2019-03-15
 */
public class UserEditPwdActvity extends BaseActivity {

	private EditText mEdPwd0;
	private EditText mEdPwd;
	private EditText mEdPwd1;
	private Button mBtnXg;
	private String mPwd;
	private SharedPreferences sp;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_ed_pwd);

		setTitle("修改密码");
		mEdPwd0 = findViewById(R.id.ed_pwd0);
		mEdPwd = findViewById(R.id.ed_pwd);
		mEdPwd1 = findViewById(R.id.ed_pwd1);
		mBtnXg = findViewById(R.id.btn_xg);

		mBtnXg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clickRegister();
			}
		});


		sp = getSharedPreferences("mPwd", MODE_PRIVATE);
		mPwd = sp.getString("mPwd", "");
		if (TextUtils.isEmpty(mPwd)) {
			mPwd = Constants.pwd;
		}
	}

	/**
	 * 点击注册
	 */
	private void clickRegister() {
		String pwd0 = mEdPwd0.getText().toString();
		String pwd = mEdPwd.getText().toString();
		String pwd1 = mEdPwd1.getText().toString();

		if (TextUtils.isEmpty(pwd0)) {
			makeToast("原不能为空");
		} else if (!pwd0.equals(mPwd)) {
			makeToast("原密码错误");
		} else if (TextUtils.isEmpty(pwd)) {
			makeToast("密码不能为空");
		} else if (TextUtils.isEmpty(pwd1)) {
			makeToast("重复密码不能为空");
		} else if (!pwd1.equals(pwd)) {
			makeToast("密码两次输入不同");
		} else {
			sp.edit().putString("mPwd", pwd).apply();
			makeToast("密码修改完成");
			finish();
		}
	}
}
