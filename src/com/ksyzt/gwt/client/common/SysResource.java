package com.ksyzt.gwt.client.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

public interface SysResource extends ClientBundle {
	public final static SysResource INSTANCE = GWT.create(SysResource.class);

	@Source("flash.txt")
	TextResource flashSource();

	@Source("style.css")
	@CssResource.NotStrict
	CssStyle getCss();

	@Source("images/logo.png")
	ImageResource logo();

	@Source("images/64back.png")
	ImageResource back64();

	@Source("images/upload.png")
	ImageResource upload();

	@Source("images/delete.png")
	ImageResource delete();

	@Source("images/edit.png")
	ImageResource edit();

	@Source("images/operator.png")
	ImageResource imgOperator();

	@Source("images/system.png")
	ImageResource imgSystem();

	@Source("images/mall.png")
	ImageResource imgMall();

	@Source("images/btn_exit.png")
	ImageResource img_btn_exit();

	@Source("images/btn_exit_over.png")
	ImageResource img_btn_exit_over();

	@Source("images/topbar_back.png")
	@ImageOptions(repeatStyle = RepeatStyle.Horizontal, height = 30, width = 41)
	ImageResource topbar_back();

	@Source("images/frameback.png")
	@ImageOptions(repeatStyle = RepeatStyle.Both, height = 150, width = 150)
	ImageResource frameback();

	@Source("images/item_selected.png")
	@ImageOptions(repeatStyle = RepeatStyle.Horizontal, height = 66, width = 170)
	ImageResource item_selected_back();

	@Source("images/col_catalog.png")
	ImageResource col_catalog();

	@Source("images/col_list.png")
	ImageResource col_list();

	@Source("images/col_picture.png")
	@ImageOptions(height = 16, width = 16)
	ImageResource col_picture();

	@Source("images/col_video.png")
	ImageResource col_video();

	@Source("images/col_article.png")
	ImageResource col_article();

	@Source("images/lightness.png")
	ImageResource lightness();
	
	@Source("images/switch_on.png")
	ImageResource switchon();
	
	@Source("images/switch_off.png")
	ImageResource switchoff();
	
	@Source("images/new.gif")
	ImageResource newicon();
	
	@Source("images/uptop.png")
	ImageResource uptop();

	@Source("images/hueSaturation.png")
	ImageResource hueSaturation();
	
	@Source("images/right_shadow.png")
	@ImageOptions(repeatStyle = RepeatStyle.Vertical, height = 10, width = 13)
	ImageResource right_shadow();
}
