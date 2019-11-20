package com.study.apphcpzj.base.utils;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.study.apphcpzj.R;
import com.study.apphcpzj.base.bmob.HcpJpCc;

import java.util.List;

/**
 * @author ztt : 2019-02-28
 */
public class TvCcAdapter extends BaseQuickAdapter<HcpJpCc, BaseViewHolder> {

	public TvCcAdapter(@Nullable List<HcpJpCc> data) {
		super(R.layout.base_item_del, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, HcpJpCc item) {

		helper.setText(R.id.item_tv, item.getJpcc());
		if (item.isSfjp()) {
			helper.setText(R.id.item_del, "停止检票");
		} else {
			helper.setText(R.id.item_del, "开始检票");
		}
		helper.addOnClickListener(R.id.item_del);

	}
}
