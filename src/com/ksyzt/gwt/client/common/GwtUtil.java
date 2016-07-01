package com.ksyzt.gwt.client.common;

import java.sql.Timestamp;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.URL;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;

public class GwtUtil {

	private static DateTimeFormat g_mf;

	public static DateTimeFormat getDateFormat() {
		if (g_mf == null) {
			g_mf = DateTimeFormat.getFormat("yyyy年MM月dd日");
		}

		return g_mf;
	}

	public static native void redirect(String url)/*-{
													$wnd.location = url;
													}-*/;

	public static native void open(String url)/*-{
												$wnd.open(url);
												}-*/;

	public static native void nocopy(Element e)/*-{
												e.onselectstart = function() {
												return false;
												}
												e.oncopy = function() {
												return false;
												}
												e.ondoubleclick = function() {
												return false;
												}
												}-*/;

	public static native void refresh()/*-{
										$wnd.location.reload();
										}-*/;

	public static String getCurrentPageURL() {
		return Window.Location.getHref();
	}

	public static String getEncodeURL(String url) {
		if (url == null) {
			return "";
		} else {
			return URL.encode(url);
		}
	}

	public static String getEncodeQueryString(String uri) {
		if (uri == null) {
			return "";
		} else {
			return URL.encodeQueryString(uri);
		}
	}

	public static String getDncodeQueryString(String uri) {
		if (uri == null) {
			return "";
		} else {
			return URL.decodeQueryString(uri);
		}
	}

	public static String getDecodeURL(String url) {
		if (url == null) {
			return "";
		} else {
			return URL.decode(url);
		}
	}

	public static String getCurrentPageLoginURL() {
		String base = GWT.getModuleBaseURL();
		String current_page = GwtUtil.getCurrentPageURL();

		String encodePath = URL.encodeQueryString(current_page);
		String url = base + "../user/login?url=" + encodePath;
		return url;
	}

	public final static String int2path(int v) {
		String id = v + "";
		String str = "";
		for (int i = 0; i < id.length(); i++) {
			str += id.charAt(i) + "/";
		}
		return str;
	}

	public static String getDebug() {
		String v = Window.Location.getParameter("gwt.codesvr");
		if (v != null && v.length() > 0) {
			return "&gwt.codesvr=" + v;
		}
		return "";
	}

	public static String getImageUrl(String path, int id) {
		String picurl = GWT.getModuleBaseURL() + "../" + path + "/"
				+ GwtUtil.int2path(id) + "64.jpg?t=" + Random.nextDouble();
		return picurl;
	}

	public static String getUserIconURL(int uid, int size) {
		return GWT.getModuleBaseURL() + "../images/user/"
				+ GwtUtil.int2path(uid) + size + ".jpg";
	}

	public static String chars = "abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String RandString(int i) {

		String str = "";
		for (int count = 0; count < i; count++) {
			str += chars.charAt(Random.nextInt(chars.length()));
		}
		return str;
	}

	static DateTimeFormat m_dfshort = null;
	static DateTimeFormat m_dflong = null;

	public static DateTimeFormat getLongFormat() {
		if (m_dflong == null) {
			m_dflong = DateTimeFormat.getFormat("yyyy年MM月dd日 HH:mm:ss");
		}
		return m_dflong;
	}

	public static DateTimeFormat getShortFormat() {
		if (m_dfshort == null) {
			m_dfshort = DateTimeFormat.getFormat("yyyy年MM月dd日");
		}
		return m_dfshort;
	}

	public static String formatTimestamp(Timestamp time, boolean longformat) {
		if (time == null) {
			return "----";
		} else {
			if (longformat == true) {
				return getLongFormat().format(new Date(time.getTime()));
			} else {
				return getShortFormat().format(new Date(time.getTime()));
			}
		}
	}

	public static String getHome() {
		String base = GWT.getModuleBaseURL();
		String url = base + "../?" + getDebug();
		return url;
	}

	public static Timestamp getTimestamp(Date value) {

		return new Timestamp(value.getTime());
	}

	public static Date getNow() {
		return new Date();
	}

	public static Date getDate(Timestamp establishdate) {
		if (establishdate == null)
			return new Date();
		else {
			return new Date(establishdate.getTime());
		}
	}

	public static String[] parsePostImageResult(String json) {
		JSONValue jv = JSONParser.parse(json);
		JSONObject jo = (JSONObject) (jv);
		JSONString strobj;
		strobj = (JSONString) jo.get("returncode");
		String rcode = strobj.stringValue();
		strobj = (JSONString) jo.get("msg");
		String msg = strobj.stringValue();
		String[] r = new String[2];
		r[0] = rcode;
		r[1] = msg;
		return r;
	}
}
