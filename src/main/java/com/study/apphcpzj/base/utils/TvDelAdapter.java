package com.study.apphcpzj.base.utils;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.study.apphcpzj.R;

import java.util.List;

/**
 * @author ztt : 2019-02-28
 */
public class TvDelAdapter extends BaseQuickAdapter<ItemBean, BaseViewHolder> {

	public TvDelAdapter(@Nullable List<ItemBean> data) {
		super(R.layout.base_item_del, data);
	}

	@Override
	protected void convert(BaseViewHolder helper, ItemBean item) {

		helper.setText(R.id.item_tv, item.getTitle());
		helper.addOnClickListener(R.id.item_del);

	}
}
