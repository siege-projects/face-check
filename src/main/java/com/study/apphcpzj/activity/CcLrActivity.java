package com.study.apphcpzj.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.study.apphcpzj.R;
import com.study.apphcpzj.base.BaseActivity;
import com.study.apphcpzj.base.bmob.HcpJpCc;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * @author ztt : 2019-03-26
 */
public class CcLrActivity extends BaseActivity {

	private Button mBtnGm;
	private EditText mEdCpId;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_cclr);

		setTitle("车次录入");
		mBtnGm = findViewById(R.id.btn_gm);
		mEdCpId = findViewById(R.id.edCpId);

		mBtnGm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String id = mEdCpId.getText().toString();
				if (TextUtils.isEmpty(id)) {
					makeToast("车次不能为空");
				} else {

					HcpJpCc jpCc = new HcpJpCc();
					jpCc.setJpcc(id);
					jpCc.save(new SaveListener<String>() {
						@Override
						public void done(String s, BmobException e) {
							if (e == null) {
								makeToast("录入成功");
							} else {
								makeToast("录入失败，id重复");
							}
						}
					});
				}
			}
		});
	}
}
