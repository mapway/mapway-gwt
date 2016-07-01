package com.ksyzt.gwt.client.common;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.shared.module.SystemConst;

public abstract class FlashMessageComposite extends MessageComposite {

	public FlashMessageComposite() {
	}

	// //////////////////////////////图片编辑区

	private native void init_script()
	/*-{
		var __this = this;

		$wnd.uploadresult = function(data) {
			__this.@com.ksyzt.gwt.client.common.FlashMessageComposite::onSaveImage(Ljava/lang/String;)(data);
		};
	}-*/;

	protected abstract void onSaveImage(String json);

	PostImageProxy m_proxy;
	boolean m_needmove = false;

	public void movefalsh() {
		if (m_needmove == true) {
			showflash();
			m_needmove = false;
		}
	}

	@Override
	public void onLoad() {
		super.onLoad();
		init_script();
		m_needmove = true;
		m_proxy = PostImageProxy.getPostImageProxy(SystemConst.FLASHID);
	}

	/**
	 * 隐藏FLASH控件
	 */
	public void hideflash() {
		m_proxy.hide();
		m_needmove = true;
	}

	protected abstract void onShowFlash();

	public void showflash() {
		m_proxy.show();
		onShowFlash();
	}

	public void showPicture(String path, Integer id) {
		m_proxy.showPicture(path, id);
	}

	/**
	 * 移动Flash控件到指定位置
	 * 
	 * @param left
	 * @param top
	 */
	public void moveFlashTo(int left, int top) {
		m_proxy.moveTo(left, top);
	}

	public PostImageProxy getPostImageProxy() {
		return m_proxy;
	}

	/**
	 * 插入节点
	 * 
	 * @param w
	 */
	public void insertTo(Widget w) {
		Element p = w.getElement();
		Element ele = m_proxy.cast();
		if (!ele.getParentElement().equals(p)) {
			ele.removeFromParent();
			p.appendChild(ele);
		}
	}

	@Override
	public void onUnload() {
		super.onUnload();
		hideflash();
	}
}
