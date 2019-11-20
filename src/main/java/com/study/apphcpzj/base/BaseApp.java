package com.study.apphcpzj.base;

import android.app.Application;
import android.app.Notification;
import android.util.DisplayMetrics;

import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import cn.bmob.v3.Bmob;

/**
 * @author ztt : 2019-01-12
 */
public class BaseApp extends Application {

	public static int screenWidth = 0;
	public static int screenHeight = 0;

	private Notification notification = null;

	@Override
	public void onCreate() {
		super.onCreate();
		getScreenSize();
//		SDKInitializer.initialize(this);
//		MultiDex.install(this);
		Bmob.initialize(this, "bbe56efaeb9ab6e6d753b6a3e8c3d8f6");
		Album.initialize(AlbumConfig.newBuilder(this)
				.setAlbumLoader(new MediaLoader())
				.build());
//		Constants.init();

//		if (getApplicationInfo().packageName.equals(getMyProcessName())){
//			BmobIM.init(this);
//			BmobIM.registerDefaultMessageHandler(new DemoMessageHandler());
//		}
	}

	/**
	 * 获取当前运行的进程名
	 * @return
	 */
	public static String getMyProcessName() {
		try {
			File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
			BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
			String processName = mBufferedReader.readLine().trim();
			mBufferedReader.close();
			return processName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 获取屏幕尺寸
	 */
	private void getScreenSize() {
		DisplayMetrics dm = getResources().getDisplayMetrics();
		screenHeight = dm.heightPixels;
		screenWidth = dm.widthPixels;
	}
}
