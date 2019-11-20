package com.study.apphcpzj.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.study.apphcpzj.R;
import com.study.apphcpzj.base.BaseActivity;
import com.study.apphcpzj.base.bmob.HcpHcp;
import com.study.apphcpzj.base.bmob.HcpJpCc;
import com.study.apphcpzj.base.bmob.HcpUser;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * @author ztt : 2019-03-26
 */
public class HcpGmActivity extends BaseActivity {

	private TextView mEdGpr;
	private Button mBtnGm;
	private TextView mTvHcp;
	private EditText mEdCpId;
	private HcpUser user;
	private HcpHcp hcp;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_gmcp);

		setTitle("车票录入");
		mEdGpr = findViewById(R.id.edGpr);
		mBtnGm = findViewById(R.id.btn_gm);
		mTvHcp = findViewById(R.id.tvHcp);
		mEdCpId = findViewById(R.id.edCpId);


		mTvHcp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (items2.size() == 0) {
					makeToast("请先录入车次信息");
					return;
				}

				//条件选择器
				OptionsPickerView<String> pvOptions = new OptionsPickerBuilder(mActivity, new
						OnOptionsSelectListener() {
							@Override
							public void onOptionsSelect(int options1, int option2, int options3, View v) {
								//返回的分别是三个级别的选中位置
								mTvHcp.setText(datas2.get(options1).getJpcc());
							}
						}).build();
				pvOptions.setPicker(items2, null, null);
				pvOptions.show();
			}
		});

		mEdGpr.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (items.size() == 0) {
					makeToast("用户列表为空，请重试或录入用户信息");
					return;
				}
				//条件选择器
				OptionsPickerView<String> pvOptions = new OptionsPickerBuilder(mActivity, new
						OnOptionsSelectListener() {
							@Override
							public void onOptionsSelect(int options1, int option2, int options3, View v) {
								//返回的分别是三个级别的选中位置
								mEdGpr.setText(items.get(options1));
								user = datas.get(options1);
							}
						}).build();
				pvOptions.setPicker(items, null, null);
				pvOptions.show();
			}
		});
		mBtnGm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 车次
				String cc = mTvHcp.getText().toString();
				// 车票id
				String id = mEdCpId.getText().toString();
				String sfz = mEdGpr.getText().toString();

				if ("请选择车次".equals(cc)) {
					makeToast("请选择车次");
				} else if ("购票人".equals(sfz)) {
					makeToast("请选择购票人");
				} else if (TextUtils.isEmpty(id)) {
					makeToast("车票id不能为空");
				} else {
					hcp = new HcpHcp();
					hcp.setName(user.getName());
					hcp.setSfz(user.getSfzh());
					hcp.setCpNum(id);
					hcp.setLccc(cc);


					queryCl(hcp.getSfz());
				}
			}
		});

		getList();
		getCpList();
	}


	private List<HcpUser> datas = new ArrayList<>();
	private List<String> items = new ArrayList<>();

	private void getList() {
		BmobQuery<HcpUser> query = new BmobQuery<>();
		query.findObjects(new FindListener<HcpUser>() {

			@Override
			public void done(List<HcpUser> object, BmobException e) {
				if (e == null) {
					datas.addAll(object);
					for (HcpUser user : datas) {
						items.add(user.getName() + " - " + user.getSfzh());
					}
				} else {
					makeToast("列表查询失败");
				}
			}
		});
	}

	private List<HcpJpCc> datas2 = new ArrayList<>();
	private List<String> items2 = new ArrayList<>();

	private void getCpList() {
		BmobQuery<HcpJpCc> query = new BmobQuery<>();
		query.findObjects(new FindListener<HcpJpCc>() {

			@Override
			public void done(List<HcpJpCc> object, BmobException e) {
				if (e == null) {
					datas2.addAll(object);
					for (HcpJpCc user : datas2) {
						items2.add(user.getJpcc());
					}
				} else {
					makeToast("列表查询失败");
				}
			}
		});
	}


	/**
	 * 查询车票信息
	 */
	private void queryCl(String rlSfz) {
		BmobQuery<HcpHcp> query = new BmobQuery<>();
		query.addWhereEqualTo("sfz", rlSfz);
		query.addWhereEqualTo("isJp", false);
		query.findObjects(new FindListener<HcpHcp>() {

			@Override
			public void done(List<HcpHcp> object, BmobException e) {
				if (e == null || object == null || object.size() > 0) {
					hcp.save(new SaveListener<String>() {
						@Override
						public void done(String s, BmobException e) {
							if (e == null) {
								makeToast("录入成功");
							} else {
								makeToast("录入失败，id重复");
							}
						}
					});
				} else {
					makeToast("此车票已售出");
				}
			}
		});
	}
}
