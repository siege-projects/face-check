package com.study.apphcpzj.net.okgo;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

/**
 * Created by ztt on 2017/9/21.
 * <p>
 * <p>
 * okgo配置
 */

public class OkGoConfig {

	public static void initOkGo(Application application) {
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		//全局的读取超时时间
		builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
		//全局的写入超时时间
		builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
		//全局的连接超时时间
		builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);

		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");

		//log打印级别，决定了log显示的详细程度
//		if (Constants.IS_DEBUG) {
			loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
//		} else {
//			loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.NONE);
//		}
		//log颜色级别，决定了log在控制台显示的颜色
		loggingInterceptor.setColorLevel(Level.INFO);
		builder.addInterceptor(loggingInterceptor);

		OkGo.getInstance().init(application)                       //必须调用初始化
				.setOkHttpClient(builder.build())               //必须设置OkHttpClient
//                    .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
//                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
				.setRetryCount(0);                              //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
//                    .addCommonHeaders(headers)                      //全局公共头
//                    .addCommonParams(params);
	}
}
