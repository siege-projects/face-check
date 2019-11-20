package com.study.apphcpzj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.study.apphcpzj.activity.UserEditPwdActvity;
import com.study.apphcpzj.activity.UserLoginActvity;
import com.study.apphcpzj.activity.UserRegisterActvity;
import com.study.apphcpzj.activity.ZjJcActivity;
import com.study.apphcpzj.baidu.bean.BaseBean;
import com.study.apphcpzj.baidu.bean.TokenBean;
import com.study.apphcpzj.base.BaseActivity;
import com.study.apphcpzj.base.utils.ItemBean;
import com.study.apphcpzj.base.utils.TvAdapter;
import com.study.apphcpzj.net.Params;
import com.study.apphcpzj.net.okgo.CallBack;
import com.study.apphcpzj.net.okgo.ResultBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

	private List<ItemBean> beans = new ArrayList<>();

	private RecyclerView recy;
	private TvAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		recy = findViewById(R.id.recy);
		recy.setLayoutManager(new LinearLayoutManager(mActivity));


		beans.add(new ItemBean(1, "检票"));
		beans.add(new ItemBean(3, "用户注册"));
		beans.add(new ItemBean(2, "票务管理"));
		adapter = new TvAdapter(beans);
		recy.setAdapter(adapter);
		adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
				int p = beans.get(position).getId();
				switch (p) {
					case 1:
						startAct(ZjJcActivity.class);
						break;
					case 2:
						startAct(UserLoginActvity.class);
						break;
					case 3:
						startAct(UserRegisterActvity.class);
						break;
					default:
						makeToast("");
				}
			}
		});


		requestToken();
	}

	/**
	 * 请求token
	 */
	private void requestToken() {
		Params params = Params.getInstance("https://aip.baidubce.com/oauth/2.0/token?");
		params.addParames("grant_type", "client_credentials");
		params.addParames("client_id", "MQS7fe4u2wxDAKYKG4AtSjzE");
		params.addParames("client_secret", "LFZNGE52zSE0eYT8xqzyZNSq1DFGEA8V");
		requestPost(params, new CallBack<TokenBean>(mActivity) {
			@Override
			public void onSuccess(TokenBean resultBean) {
				Constants.access_token = resultBean.getAccess_token();
//				makeToast("token获取成功");
//				createUserGroup();
			}
		});
	}


	/**
	 * 请求token
	 */
	private void createUserGroup() {
		Params params = Params.getInstance("https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/add");
		params.addParames("access_token", Constants.access_token);
		params.addParames("group_id", Constants.USER_GROUP);
		requestPost(params, new CallBack<BaseBean>(mActivity) {
			@Override
			public void onSuccess(BaseBean resultBean) {
				if (0 == resultBean.getLog_id()) {
					makeToast("用户组创建成功");
				} else if (223101 != resultBean.getLog_id()) {
					makeToast(resultBean.getError_msg() + "-" + resultBean.getError_code());
				}
			}
		});
	}
}
