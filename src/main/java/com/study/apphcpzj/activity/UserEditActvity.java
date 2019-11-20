package com.study.apphcpzj.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.study.apphcpzj.Constants;
import com.study.apphcpzj.R;
import com.study.apphcpzj.baidu.bean.FaceAddBean;
import com.study.apphcpzj.base.BaseActivity;
import com.study.apphcpzj.base.bmob.HcpUser;
import com.study.apphcpzj.base.utils.LogUtil;
import com.study.apphcpzj.net.Params;
import com.study.apphcpzj.net.okgo.CallBack;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * @author ztt : 2019-03-15
 */
public class UserEditActvity extends BaseActivity {



	private EditText mEdSj;
	private EditText mEdNc;
	private ImageView mImgTx;
	private Button mBtnZc;

	public static HcpUser user;
	String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/image/";
	private String path;
	private String strbm;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_act_register);
		setTitle("修改人脸图片");
		mEdSj = findViewById(R.id.ed_sj);
		mEdNc = findViewById(R.id.ed_nc);
		mImgTx = findViewById(R.id.img_tx);
		mBtnZc = findViewById(R.id.btn_zc);
		mBtnZc.setText("修改");

		mEdSj.setText(user.getSfzh());
		mEdNc.setText(user.getName());
		mEdSj.setFocusable(false);
		mEdNc.setFocusable(false);

		mBtnZc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clickRegister();
			}
		});

		mImgTx.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Album.image(mActivity) // Image selection.
						.multipleChoice()
						.camera(true)
						.columnCount(2)
						.selectCount(1)
						.onResult(new Action<ArrayList<AlbumFile>>() {
							@Override
							public void onAction(@NonNull ArrayList<AlbumFile> result) {
								LogUtil.e("图片：" + result.get(0).getPath());


								ysImg(result.get(0).getPath());
							}
						})
						.onCancel(new Action<String>() {
							@Override
							public void onAction(@NonNull String result) {
							}
						})
						.start();
			}
		});
	}


	/**
	 * 压缩图片
	 */
	private void ysImg(String img) {

		File file = new File(dirPath);
		if (!file.exists()) {
			file.mkdirs();
		}

		Luban.with(this)
				.load(img)
				.ignoreBy(1000)
				.setTargetDir(dirPath)
				.filter(new CompressionPredicate() {
					@Override
					public boolean apply(String path) {
						return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
					}
				})
				.setCompressListener(new OnCompressListener() {
					@Override
					public void onStart() {

					}

					@Override
					public void onSuccess(File file) {
						path = file.getPath();
						Glide.with(mActivity).load(path).asBitmap()//签到整体 背景
								.into(new SimpleTarget<Bitmap>() {        //设置宽高
									@Override
									public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

										ByteArrayOutputStream baos = new ByteArrayOutputStream();
										//读取图片到ByteArrayOutputStream
										resource.compress(Bitmap.CompressFormat.PNG, 100, baos);
										byte[] bytes = baos.toByteArray();
										strbm = Base64.encodeToString(bytes,Base64.DEFAULT);

										mImgTx.setImageBitmap(resource);
									}
								});

					}

					@Override
					public void onError(Throwable e) {
					}
				}).launch();
	}

	/**
	 * 点击注册
	 */
	private void clickRegister() {
		String sjh = mEdSj.getText().toString();
		String nc = mEdNc.getText().toString();

		if (TextUtils.isEmpty(sjh)) {
			makeToast("身份证号不能为空");
		} else if (TextUtils.isEmpty(nc)) {
			makeToast("姓名不能为空");
		} else {
			user = new HcpUser();
			user.setSfzh(sjh);
			user.setName(nc);

			zcRl();
		}
	}

	/**
	 * 更新人脸
	 */
	private void zcRl() {

		Params params = Params.getInstance("https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/update");
		params.addParames("access_token", Constants.access_token);
		params.addParames("group_id", Constants.USER_GROUP);
		params.addParames("image", strbm);
		params.addParames("image_type", "BASE64");
		params.addParames("quality_control", "NORMAL");
		params.addParames("liveness_control", "NONE");
		params.addParames("user_id", user.getSfzh());
		params.addParames("user_info", user.getName());
		requestPost(params, new CallBack<FaceAddBean>(mActivity) {
			@Override
			public void onSuccess(FaceAddBean resultBean) {
				if (0 == resultBean.getError_code()) {
					makeToast("人脸更新成功");
				} else {
					makeToast(resultBean.getError_msg() + "-" + resultBean.getError_code());
				}
			}

			@Override
			public void onError(String error) {
				super.onError(error);
				makeToast("人脸更新失败");
			}
		});

	}
}
