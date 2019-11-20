package com.study.apphcpzj.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.study.apphcpzj.R;
import com.study.apphcpzj.base.BaseActivity;
import com.study.apphcpzj.base.bmob.HcpJpCc;
import com.study.apphcpzj.base.utils.TvCcAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * @author ztt : 2019-03-26
 */
public class CcListActivity extends BaseActivity {

	private List<HcpJpCc> datas = new ArrayList<>();

	private RecyclerView recy;
	private TvCcAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recy);
		setTitle("车次管理");
		recy = findViewById(R.id.recy);
		recy.setLayoutManager(new LinearLayoutManager(mActivity));
		adapter = new TvCcAdapter(datas);
		recy.setAdapter(adapter);

		adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
			@Override
			public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
				HcpJpCc user = datas.get(position);
				user.setSfjp(!user.isSfjp());
				user.update(new UpdateListener() {
					@Override
					public void done(BmobException e) {
						if (e == null) {
							makeToast("修改成功");
							adapter.notifyItemChanged(position);
						} else {
							makeToast("删除失败");
						}
					}
				});
			}
		});
		getList();
	}


	private void getList() {
		BmobQuery<HcpJpCc> query = new BmobQuery<>();
//		query.addWhereEqualTo("sjh", Constants.user.getSjh());
		query.findObjects(new FindListener<HcpJpCc>() {

			@Override
			public void done(List<HcpJpCc> object, BmobException e) {
				if (e == null) {
					datas.addAll(object);
					adapter.notifyDataSetChanged();
				} else {
					makeToast("列表查询失败");
				}
			}
		});
	}

}
