package com.ksyzt.gwt.client.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window.Navigator;
import com.google.gwt.user.client.ui.RootPanel;

public class PostImageProxy extends JavaScriptObject {

	protected PostImageProxy() {

	}

	public final void showPicture(String path, Integer id) {

		String url = GWT.getModuleBaseURL();

		String ua = Navigator.getUserAgent().toLowerCase();
		JavaScriptObject who = this;
		if (ua.indexOf("mozilla") < 0) {
			who = this.getFirstChild();
		}
		if (id != null) {

			String str = PostImageProxy.int2path(id);
			String p = url + "../" + path + "/" + str + "128.jpg";

			setup(who, url + "../postimage", id, path, p);
		} else {

			setup(who, url + "../postimage", -1, path, "");
		}
	}

	public final static PostImageProxy getPostImageProxy(String id) {
		return getById(id);
	}

	private final static native PostImageProxy getById(String id)
	/*-{
		var t = $doc.getElementById(id);
		return t;
	}-*/;

	private final native JavaScriptObject getFirstChild()
	/*-{
		var t = this.childNodes.length;
		var tag = "embed";
		for ( var i = 0; i < t; i++) {
			var tname = this.childNodes.item(i).nodeName;
			tname = tname.toLowerCase();
			if (tag == tname) {
				return this.childNodes.item(i);
			}
		}
		return null;
	}-*/;

	private final static native PostImageProxy getByName(String id)
	/*-{
		var t = $doc.getElementsByName(id);
		if (t != null && t.length > 0) {
			return t[0];
		} else {
			return null;
		}
	}-*/;

	public final native void setup(JavaScriptObject who, String server, int id,
			String loc, String initsrc)
	/*-{
		var _this = this;
		if (who != null) {
			_this = who;
		}
		var pf = function() {
			if (_this.setupParameter) {
				initsrc += "?" + Math.random();
				_this.setupParameter(server, id, loc, initsrc);
			} else {
				$wnd.setTimeout(pf, 500);
			}
		};
		$wnd.setTimeout(pf, 10);

	}-*/;

	public static void createPostImageProxy(String flashid) throws Exception {

		try {
			Element wrapper = DOM.createDiv();
			wrapper.setInnerHTML(getFlashString(flashid));

			Element body = RootPanel.getBodyElement();
			body.appendChild(wrapper);

			Node t = wrapper.getChild(0);
			wrapper.removeChild(t);

			body.appendChild(t);
			body.removeChild(wrapper);

		} catch (Exception e) {
			throw e;
		}

	}

	public final static String getFlashString(String flashid) {
		String base = GWT.getModuleBaseURL();
		String movie = base + "../images/PostImage.swf";

		String flash = SysResource.INSTANCE.flashSource().getText();
		flash = flash.replace("{movie}", movie);
		flash = flash.replace("{id}", flashid);
		return flash;
	}

	public final static String int2path(int v) {
		String id = v + "";
		String str = "";
		for (int i = 0; i < id.length(); i++) {
			str += id.charAt(i) + "/";
		}
		return str;
	}

	public final void hide() {
		Element ele = this.cast();
		ele.getStyle().setVisibility(Visibility.HIDDEN);
	}

	public final void show() {
		Element ele = this.cast();
		ele.getStyle().setVisibility(Visibility.VISIBLE);
	}

	public final native void moveTo(int left, int top)
	/*-{
		this.style.left = left + "px";
		this.style.top = top + "px";
	}-*/;

	public final native void setSize(int w, int h)
	/*-{
		this.style.width = w + "px";
		this.style.height = h + "px";
	}-*/;
}
