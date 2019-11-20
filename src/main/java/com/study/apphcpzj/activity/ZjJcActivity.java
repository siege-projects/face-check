package com.study.apphcpzj.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.study.apphcpzj.Constants;
import com.study.apphcpzj.R;
import com.study.apphcpzj.baidu.bean.RlcxBean;
import com.study.apphcpzj.baidu.bean.SfzBean;
import com.study.apphcpzj.base.BaseActivity;
import com.study.apphcpzj.base.bmob.HcpHcp;
import com.study.apphcpzj.base.bmob.HcpJpCc;
import com.study.apphcpzj.net.Params;
import com.study.apphcpzj.net.okgo.CallBack;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * @author ztt : 2019-03-26
 */
public class ZjJcActivity extends BaseActivity {

	private Button btnJy;
	private EditText edCpid;
	private ImageView imgRl;
	private Button btnSfz;
	private Button btnRl;
	private ImageView imgSfz;
	String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/image/";

	String pn = "com.study.apphcpzj.fileprovider";
	private File rlFile, sfzFile;
	private String rlBase;
	private String sfzBase;

	private TextView jcjd;
	// 人脸身份证
	private String rlSfz;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zjjc);
		btnJy = findViewById(R.id.btn_jy);
		edCpid = findViewById(R.id.ed_cpid);
		imgRl = findViewById(R.id.img_rl);
		btnSfz = findViewById(R.id.btn_sfz);
		btnRl = findViewById(R.id.btn_rl);
		imgSfz = findViewById(R.id.img_sfz);
		jcjd = findViewById(R.id.tvJcjd);

		edCpid.setVisibility(View.GONE);
		File file = new File(dirPath);
		if (!file.exists()) {
			file.mkdirs();
		}

		btnRl.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//用于保存调用相机拍照后所生成的文件
				rlFile = new File(dirPath, System.currentTimeMillis() + ".png");
				//跳转到调用系统相机
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				//判断版本
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
					intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
					Uri contentUri = FileProvider.getUriForFile(mActivity, pn, rlFile);
					intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
				} else {    //否则使用Uri.fromFile(file)方法获取Uri
					intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(rlFile));
				}
				startActivityForResult(intent, 0);
			}
		});


		btnSfz.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//用于保存调用相机拍照后所生成的文件
				sfzFile = new File(dirPath, System.currentTimeMillis() + ".png");
				//跳转到调用系统相机
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				//判断版本
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
					intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
					Uri contentUri = FileProvider.getUriForFile(mActivity,
							pn,
							sfzFile);
					intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
				} else {    //否则使用Uri.fromFile(file)方法获取Uri
					intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(rlFile));
				}
				startActivityForResult(intent, 1);
			}
		});


		btnJy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				String cpid = edCpid.getText().toString();
				if (rlBase == null) {
					makeToast("请拍摄人脸");
				} else if (sfzBase == null) {
					makeToast("请拍摄身份证");
				} else {

					jcjd.setText("开始检测：\n");
//					queryCl(cpid);
					rlCx();
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.e("TAG", "系统相机拍照完成，resultCode=" + resultCode);
		if (resultCode != RESULT_OK) {
			return;
		}
		if (requestCode == 0) {
			File file = new File(dirPath);
			if (!file.exists()) {
				file.mkdirs();
			}

			Luban.with(this)
					.load(rlFile)
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
							String rlPath = file.getPath();
							Glide.with(mActivity).load(rlPath).asBitmap()//签到整体 背景
									.into(new SimpleTarget<Bitmap>() {        //设置宽高
										@Override
										public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

											ByteArrayOutputStream baos = new ByteArrayOutputStream();
											//读取图片到ByteArrayOutputStream
											resource.compress(Bitmap.CompressFormat.PNG, 100, baos);
											byte[] bytes = baos.toByteArray();
											rlBase = Base64.encodeToString(bytes, Base64.DEFAULT);

											imgRl.setImageBitmap(resource);
										}
									});

						}

						@Override
						public void onError(Throwable e) {
						}
					}).launch();
		} else if (requestCode == 1) {
//			Glide.with(mActivity).load(sfzFile.getAbsolutePath()).into(imgSfz);


			File file = new File(dirPath);
			if (!file.exists()) {
				file.mkdirs();
			}

			Luban.with(this)
					.load(sfzFile)
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
							String rlPath = file.getPath();
							Glide.with(mActivity).load(rlPath).asBitmap()//签到整体 背景
									.into(new SimpleTarget<Bitmap>() {        //设置宽高
										@Override
										public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

											ByteArrayOutputStream baos = new ByteArrayOutputStream();
											//读取图片到ByteArrayOutputStream
											resource.compress(Bitmap.CompressFormat.PNG, 100, baos);
											byte[] bytes = baos.toByteArray();
											sfzBase = Base64.encodeToString(bytes, Base64.DEFAULT);

											imgSfz.setImageBitmap(resource);
										}
									});

						}

						@Override
						public void onError(Throwable e) {
						}
					}).launch();
		}

	}


	private String mCpId = "";
	private HcpHcp hcp;

	/**
	 * 查询车票信息
	 */
	private void queryCl() {
		BmobQuery<HcpHcp> query = new BmobQuery<>();
		query.addWhereEqualTo("sfz", rlSfz);
		query.addWhereEqualTo("isJp", false);
		query.findObjects(new FindListener<HcpHcp>() {

			@Override
			public void done(List<HcpHcp> object, BmobException e) {
				if (e == null) {
					if (object.size() > 0) {
						hcp = object.get(0);
//						makeToast("车票查询成功");
						jcjd.append("车票查询成功：" + hcp.getCpNum() + "\n");
						queryCc();
					} else {
						jcjd.append("没有查询到此身份证未检票车票信息\n");
//						makeToast("没有查询到此编号的车票信息");
					}
				} else {
					jcjd.append("火车票查询失败\n");
//					makeToast("列表查询失败");
				}
			}
		});
	}

	/**
	 * 查询车次
	 */
	private void queryCc() {
		BmobQuery<HcpJpCc> query = new BmobQuery<>();
		query.addWhereEqualTo("jpcc", hcp.getLccc());
		query.findObjects(new FindListener<HcpJpCc>() {

			@Override
			public void done(List<HcpJpCc> object, BmobException e) {
				if (e == null) {
					if (object.size() > 0) {
//						hcp = object.get(0);
//						makeToast("车票查询成功");
						jcjd.append("检票车次查询成功\n");
						if (object.get(0).isSfjp()) {
							jcjd.append("车次正在检票\n");
							gxCp();



						} else {
							jcjd.append("车次未开始检票，需开启检票才能通过\n");
						}
					} else {
						jcjd.append("没有查询到检票车次信息\n");
						makeToast("没有查询到检票车次信息");
					}
				} else {
					jcjd.append("检票车次查询失败\n");
					makeToast("检票车次查询失败");
				}
			}
		});
	}


	/**
	 * 人脸查询
	 */
	private void rlCx() {

		Params params = Params.getInstance("https://aip.baidubce.com/rest/2.0/face/v3/search");
		params.addParames("access_token", Constants.access_token);
		params.addParames("group_id_list", Constants.USER_GROUP);
		params.addParames("image", rlBase);
		params.addParames("image_type", "BASE64");
		params.addParames("quality_control", "NORMAL");
		params.addParames("liveness_control", "NONE");
		requestPost(params, new CallBack<RlcxBean>(mActivity) {
			@Override
			public void onSuccess(RlcxBean resultBean) {
				if (0 == resultBean.getError_code()) {
					jcjd.append("人脸查询成功\n");
					makeToast("人脸查询成功");
					double score = resultBean.getResult().getUser_list().get(0).getScore();
					rlSfz = resultBean.getResult().getUser_list().get(0).getUser_id();
					String name = resultBean.getResult().getUser_list().get(0).getUser_info();
					if (score > 80) {
						jcjd.append("人脸对比成功，相似度：" + score + "%\n");
						sfzCx();
					} else {
						jcjd.append("人脸对比失败，相似度：" + score + "%\n");
					}
					jcjd.append("人脸身份证：" + rlSfz);
				} else {
					makeToast(resultBean.getError_msg() + "-" + resultBean.getError_code());
					jcjd.append(resultBean.getError_msg() + "  人脸查询失败\n");
				}
			}

			@Override
			public void onError(String error) {
				super.onError(error);
				makeToast("人脸查询失败");
				jcjd.append(error + "  人脸查询失败\n");
			}
		});
	}


	/**
	 * 身份证查询
	 */
	private void sfzCx() {

		Params params = Params.getInstance("https://aip.baidubce.com/rest/2.0/ocr/v1/idcard");
		params.addParames("access_token", Constants.access_token);
		params.addParames("image", sfzBase);
		params.addParames("id_card_side", "front");
		params.addParames("detect_direction", "true");
		params.addParames("detect_risk", "false");
		requestPost(params, new CallBack<SfzBean>(mActivity) {
			@Override
			public void onSuccess(SfzBean resultBean) {
				if (0 == resultBean.getError_code()) {
					jcjd.append("身份证查询成功\n");
					makeToast("身份证查询成功");

					String gmsfz = resultBean.getWords_result().get公民身份号码().getWords();
					if (rlSfz.equals(gmsfz)) {
						jcjd.append("身份证对比成功，身份证：" + gmsfz + "\n");
						queryCl();
//						jcjd.append("通过");

					} else {
						jcjd.append("没有查询到匹配的身份证信息\n");
						jcjd.append("身份证对比失败，身份证：" + gmsfz + "\n");
					}
				} else {
					makeToast(resultBean.getError_msg() + "-" + resultBean.getError_code());
					jcjd.append(resultBean.getError_msg() + "  身份证查询失败\n");
				}
			}

			@Override
			public void onError(String error) {
				super.onError(error);
				makeToast("身份证查询失败");
				jcjd.append(error + "  身份证查询失败\n");
			}
		});
	}


	/**
	 * 更新车票
	 */
	private void gxCp() {
		jcjd.append("开始更新车票信息\n");
		hcp.setJp(true);
		hcp.update(new UpdateListener() {
			@Override
			public void done(BmobException e) {
				if (e == null) {
					jcjd.append("车票更新成功\n");
					jcjd.append("通过\n");
					showAlertDialog("温馨提示", "车票信息，人脸信息，身份证信息检测通过", "确定", null, new
							DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									finish();
								}
							}, null);
				} else {
					makeToast("车票程序失败");
				}
			}
		});
	}
}
