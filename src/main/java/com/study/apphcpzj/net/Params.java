package com.study.apphcpzj.net;

import java.util.HashMap;

/**
 * Created by ztt on 2017/9/21.
 *
 *
 * 网络请求参数
 */

public class Params {

	private String url;
	private HashMap<String, Object> parames = new HashMap<>();
	private HashMap<String, String> headers = new HashMap<>();

	private Params(String url) {
		this.url = url;
	}

	public static Params getInstance(String url) {
		return new Params(url);
	}

	public Params addParames(String key, Object value) {
		parames.put(key, value);
		return this;
	}

	public Params addHeader(String key, String value) {
		headers.put(key, value);
		return this;
	}

	public String getUrl() {
		return url;
	}

	public HashMap<String, Object> getParames() {
		return parames;
	}

	public HashMap<String, String> getHeaders() {
		return headers;
	}
}
