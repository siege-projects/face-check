package com.study.apphcpzj.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.study.apphcpzj.R;
import com.study.apphcpzj.base.BaseActivity;
import com.study.apphcpzj.base.bmob.HcpHcp;
import com.study.apphcpzj.base.utils.ItemBean;
import com.study.apphcpzj.base.utils.TvDelAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * @author ztt : 2019-03-26
 */
public class HcpListActivity extends BaseActivity {

	private List<ItemBean> beans = new ArrayList<>();
	private List<HcpHcp> datas = new ArrayList<>();

	private RecyclerView recy;
	private TvDelAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recy);
		setTitle("车票管理");
		recy = findViewById(R.id.recy);
		recy.setLayoutManager(new LinearLayoutManager(mActivity));
		adapter = new TvDelAdapter(beans);
		recy.setAdapter(adapter);
		adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
			}
		});

		adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
			@Override
			public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
				HcpHcp user = datas.get(position);
				user.delete(new UpdateListener() {
					@Override
					public void done(BmobException e) {
						if (e == null) {
							makeToast("删除成功");
							adapter.remove(position);
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
		beans.clear();
		adapter.notifyDataSetChanged();
		BmobQuery<HcpHcp> query = new BmobQuery<>();
//		query.addWhereEqualTo("sjh", Constants.user.getSjh());
		query.findObjects(new FindListener<HcpHcp>() {

			@Override
			public void done(List<HcpHcp> object, BmobException e) {
				if (e == null) {
					datas.addAll(object);
					for (HcpHcp score : datas) {
						beans.add(new ItemBean(0,
								"车次：" + score.getLccc()
										+ "\n" + "编号：" + score.getCpNum()
						));
					}

					adapter.notifyDataSetChanged();
				} else {
					makeToast("列表查询失败");
				}
			}
		});
	}

}
