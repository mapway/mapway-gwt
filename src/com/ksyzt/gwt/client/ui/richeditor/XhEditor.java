package com.ksyzt.gwt.client.ui.richeditor;

import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.TextArea;

public class XhEditor extends TextArea {

	@UiConstructor
	public XhEditor() {

	}

	public XhEditor(Element element) {
		super(element);
	}

	private RichBox proxy;

	public void initControl(EditOption option) {
		if (option == null) {
			option = EditOption.getSimpleOption();
		}
		proxy = RichBox.initialize(getElement(), option);
	}

	/**
	 * 设置HTML内容
	 * 
	 * @param html
	 */
	public void setSource(String html) {
		proxy.html(html);
	}

	/**
	 * 获取HTML内容
	 * 
	 * @return
	 */
	public String getSource() {
		return proxy.html();
	}

	/**
	 * 卸载组件
	 */
	public void unload() {
		proxy.unLoad();
	}

	public void focus() {
		proxy.focus();
	}

	/**
	 * 获取HTML
	 */
	public String getValue() {
		return this.getSource();
	}

	/**
	 * 设置HTML
	 */
	public void setValue(String html) {
		this.setSource(html);
	}
}
