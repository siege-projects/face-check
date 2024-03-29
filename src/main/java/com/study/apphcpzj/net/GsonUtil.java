package com.study.apphcpzj.net;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * Created by ztt on 2017/9/22.
 * <p>
 * Gson封装类
 */

public class GsonUtil {
	private static Gson gson = null;

	static {
		gson = new Gson();}

	private GsonUtil() {
	}

//	public static <T> ResultBean<List<T>> fromJsonArray(String reader, Class<T> clazz) {
//		if (gson == null) {
//			gson = new Gson();
//		}
//		Type type = TypeBuilder
//				.newInstance(ResultBean.class)
//				.beginSubType(List.class)
//				.addTypeParam(clazz)
//				.endSubType()
//				.build();
//		return gson.fromJson(reader, type);
//	}
//
//	public static <T> ResultBean<T> fromJsonObject(String reader, Class<T> clazz) {
//		if (gson == null) {
//			gson = new Gson();
//		}
//		Type type = TypeBuilder
//				.newInstance(ResultBean.class)
//				.addTypeParam(clazz)
//				.build();
//		return gson.fromJson(reader, type);
//	}

	/**
	 * 转成json
	 *
	 * @param object
	 * @return
	 */
	public static String GsonString(Object object) {
		String gsonString = null;
		if (gson != null) {
			gsonString = gson.toJson(object);
		}
		return gsonString;
	}

	/**
	 * 转成bean
	 *
	 * @param gsonString
	 * @param cls
	 * @return
	 */
	public static <T> T GsonToBean(String gsonString, Class<T> cls) {
		T t = null;
		if (gson != null) {
			t = gson.fromJson(gsonString, cls);
		}
		return t;
	}

	/**
	 * 转成list
	 *
	 * @param gsonString
	 * @param cls
	 * @return
	 */
	public static <T> List<T> GsonToList(String gsonString, Class<T> cls) {
		List<T> list = null;
		if (gson != null) {
			list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
			}.getType());
		}
		return list;
	}

	/**
	 * 转成list中有map的
	 *
	 * @param gsonString
	 * @return
	 */
	public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
		List<Map<String, T>> list = null;
		if (gson != null) {
			list = gson.fromJson(gsonString,
					new TypeToken<List<Map<String, T>>>() {
					}.getType());
		}
		return list;
	}

	/**
	 * 转成map的
	 *
	 * @param gsonString
	 * @return
	 */
	public static <T> Map<String, T> GsonToMaps(String gsonString) {
		Map<String, T> map = null;
		if (gson != null) {
			map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
			}.getType());
		}
		return map;
	}
}
