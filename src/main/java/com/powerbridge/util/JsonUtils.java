package com.powerbridge.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

/**
 * json处理
 * 
 * @author ZhongYF
 * 
 */
public final class JsonUtils {
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> parseJSON2List(String jsonStr) {
		JSONArray jsonArr = JSONArray.fromObject(jsonStr);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Iterator<JSONObject> it = jsonArr.iterator();
		while (it.hasNext()) {
			JSONObject json2 = it.next();
			list.add(parseJSON2Map(json2.toString()));
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> parseJSON2Map(String jsonStr) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		// 最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			// 如果内层还是数组的话，继续解析
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<JSONObject> it = ((JSONArray) v).iterator();
				while (it.hasNext()) {
					JSONObject json2 = it.next();
					list.add(parseJSON2Map(json2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}

	public static List<Map<String, Object>> getListByUrl(String url) {
		InputStream in = null;
		BufferedReader reader = null;
		try {
			// 通过HTTP获取JSON数据
			in = new URL(url).openStream();
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return parseJSON2List(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				//
			}
		}

		return null;
	}

	public static Map<String, Object> getMapByUrl(String url) {
		InputStream in = null;
		BufferedReader reader = null;
		try {
			// 通过HTTP获取JSON数据
			in = new URL(url).openStream();
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return parseJSON2Map(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				//
			}
		}
		return null;
	}

	public static Object fromObject(Object str) {
		switch (getJSONType(str)) {
		case JSON_TYPE_ARRAY:
			return JSONArray.fromObject(str);
		case JSON_TYPE_ERROR:
			return null;
		case JSON_TYPE_OBJECT:
			return JSONObject.fromObject(str);
		default:
			return null;
		}
	}

	public static void main(String[] args) {
		String ss = "[{birth=\"2016-05-01\", sex=\"男\", order=\"1\", name=\"孩子次1\", relation=\"亲儿子\"}]";
		System.out.println(parseJSON2List(ss));
		/*
		 * List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		 * for(int i=0;i<5;i++){ Map<String,Object> map = new
		 * HashMap<String,Object>(); map.put("AA", "ss"); map.put("BB", "dd");
		 * map.put("CC", "cc"); list.add(map);
		 * System.out.println(fromObject(map)); }
		 * System.out.println(fromObject(list));
		 */
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static JSON_TYPE getJSONType(Object str) {
		if (str == null || "".equals(str))
			return JSON_TYPE.JSON_TYPE_ERROR;
		final char[] strChar = str.toString().substring(0, 1).toCharArray();
		final char firstChar = strChar[0];
		if (firstChar == '{') {
			return JSON_TYPE.JSON_TYPE_OBJECT;
		} else if (firstChar == '[') {
			return JSON_TYPE.JSON_TYPE_ARRAY;
		} else {
			return JSON_TYPE.JSON_TYPE_ERROR;
		}
	}

	public enum JSON_TYPE {
		/** JSONObject */
		JSON_TYPE_OBJECT,
		/** JSONArray */
		JSON_TYPE_ARRAY,
		/** 不是JSON格式的字符串 */
		JSON_TYPE_ERROR
	}

}
