package com.ksyzt.gwt.client.ui.richeditor;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * 文本编辑器初始化参数
 * 
 * @author Administrator
 * 
 */
public class EditOption extends JavaScriptObject {

	public final static EditOption getSimpleOption() {
		EditOption option = EditOption.create();
		option.tools(TS_SIMPLE);
		return option;
	}

	public final static String TS_FULL = "full";
	public final static String TS_MFULL = "mfull";
	public final static String TS_SIMPLE = "simple";
	public final static String TS_MINI = "mini";

	public final static String SKIN_O2007_SILVER = "o2007silver";
	public final static String SKIN_VISTA = "vista";
	public final static String SKIN_O2007_BLUE = "o2007blue";
	public final static String SKIN_NOSTYLE = "o2007blue";

	public final static String LINK_EXT = "txt,rar,zip,jpg,jpeg,gif,png,swf,wmv,avi,wma,mp3,mid,pdf,doc,docx";

	protected EditOption() {
		
	}

	public static native EditOption create()/*-{
		return new Object();
	}-*/;

	public final native String width()/*-{
		return (this.width === undefined) ? "0" : this.width + "";
	}-*/;

	public final native void width(String width)/*-{
		this.width = width;
	}-*/;

	public final native String height()/*-{
		return (this.height === undefined) ? "0" : this.height + "";
	}-*/;

	public final native void height(String height)/*-{
		this.height = height;
	}-*/;

	public final native String tools()/*-{
		return this.tools;
	}-*/;

	public final native void tools(String toolsStyle)/*-{
		this.tools = toolsStyle;
	}-*/;

	public final native void setSkin(String skinname)/*-{
		this.skin = skinname;
	}-*/;

	public final native void setUploadImagePath(String path)/*-{
		this.upImgUrl = path;
	}-*/;

	public final native void setLinkExt(String ext)/*-{
		this.upLinkExt = ext;
	}-*/;

	public final native void setUploadLinkExt(String path)/*-{
		this.upLinkUrl = path;
	}-*/;

	public final native void setEditorRoot(String path)/*-{
		this.editorRoot = path;
	}-*/;

	public final native void setUrlBase(String base)/*-{
		this.urlBase = base;
	}-*/;

	public final native void enableHtml5Upload(boolean b)/*-{
		this.html5Upload = false;
	}-*/;
}
