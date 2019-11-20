package com.study.apphcpzj.base;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.AlbumLoader;

/**
 * @author ztt : 2019-03-08
 */
public class MediaLoader implements AlbumLoader {

	@Override
	public void load(ImageView imageView, AlbumFile albumFile) {
		load(imageView, albumFile.getPath());
	}

	@Override
	public void load(ImageView imageView, String url) {
		Glide.with(imageView.getContext())
				.load(url)
				.crossFade()
				.into(imageView);
	}
}