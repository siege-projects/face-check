package com.study.apphcpzj;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.study.apphcpzj.activity.CcListActivity;
import com.study.apphcpzj.activity.CcLrActivity;
import com.study.apphcpzj.activity.HcpGmActivity;
import com.study.apphcpzj.activity.HcpListActivity;
import com.study.apphcpzj.activity.UserEditPwdActvity;
import com.study.apphcpzj.activity.UserListActivity;
import com.study.apphcpzj.activity.UserRegisterActvity;
import com.study.apphcpzj.base.BaseActivity;
import com.study.apphcpzj.base.utils.ItemBean;
import com.study.apphcpzj.base.utils.TvAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdminMenuActivity extends BaseActivity {

	private List<ItemBean> beans = new ArrayList<>();

	private RecyclerView recy;
	private TvAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		recy = findViewById(R.id.recy);
		recy.setLayoutManager(new LinearLayoutManager(mActivity));


//		beans.add(new ItemBean(1, "用户录入"));
		beans.add(new ItemBean(2, "窗口售票"));
		beans.add(new ItemBean(6, "车次录入"));
		beans.add(new ItemBean(3, "用户管理"));
		beans.add(new ItemBean(5, "车次管理"));
		beans.add(new ItemBean(4, "车票管理"));
		beans.add(new ItemBean(11, "修改密码"));
		adapter = new TvAdapter(beans);
		recy.setAdapter(adapter);
		adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
				int p = beans.get(position).getId();
				switch (p) {
					case 11:
						startAct(UserEditPwdActvity.class);
						break;
					case 1:
						startAct(UserRegisterActvity.class);
						break;
					case 2:
						startAct(HcpGmActivity.class);
						break;
					case 3:
						startAct(UserListActivity.class);
						break;
					case 4:
						startAct(HcpListActivity.class);
						break;
					case 5:
						startAct(CcListActivity.class);
						break;
					case 6:
						startAct(CcLrActivity.class);
						break;
					default:
						makeToast("功能开发中");
				}
			}
		});
	}
}
