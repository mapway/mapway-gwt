package com.ksyzt.gwt.client.common;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Image;

public class Image64 extends Image {


	@UiConstructor
	public Image64()
	{
		super();
		setStyleName("IMAGE64");
		setFrame(SysResource.INSTANCE.back64());
	}

	public Image64(String src)
	{
		super();
		setStyleName("IMAGE64");
		setFrame(SysResource.INSTANCE.back64());
		setSrc(src);
	}
	public void setSrc(String src)
	{
		String imgurl="url("+src+")";
		this.getElement().getStyle().setBackgroundImage(imgurl);
	}

	public void setFrame(ImageResource ir)
	{
		this.setUrl(ir.getSafeUri());
	}
	public void setFrame(String url)
	{
		this.setUrl(url);
	}
}
