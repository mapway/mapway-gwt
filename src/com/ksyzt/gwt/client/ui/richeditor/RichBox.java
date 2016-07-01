package com.ksyzt.gwt.client.ui.richeditor;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

public class RichBox extends JavaScriptObject {

	protected RichBox() {

	}

	/**
	 * 初始化文本编辑器
	 * 
	 * @param id
	 *            textarea 控件ID
	 * @return
	 */
	public final static native RichBox initialize(String id, EditOption options)/*-{
		var t = $("#" + id).xheditor(options);
		return t;
	}-*/;

	/**
	 * 初始化文本编辑器
	 * 
	 * @param id
	 *            textarea 控件ID
	 * @return
	 */
	public final static native RichBox initialize(Element e, EditOption options)/*-{
		var w=$wnd.$;
		var t = w(e).xheditor(options);
		return t;
	}-*/;

	public final native void unLoad()/*-{
		this.xheditor(false);
	}-*/;

	public final native void focus()/*-{
		this.focus();
	}-*/;

	public final native String html()/*-{
		return this.getSource();
	}-*/;

	public final native void html(String html)/*-{
		this.setSource(html);
	}-*/;

}
