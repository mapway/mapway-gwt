package com.ksyzt.gwt.server.servlet;

public class UploadReturn {
	public String msg;
	public String err;
	public ImageLocationInfo data;
	public UploadReturn() {
		data=new ImageLocationInfo();
	}
}
