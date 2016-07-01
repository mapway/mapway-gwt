package com.ksyzt.gwt.client.common;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.ksyzt.gwt.client.event.HasMessageHandlers;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

/**
 * 能够处理消息的界面UI
 * 
 * @author zhangjianshe@navinfo.com
 * 
 */
public abstract class MessageComposite extends Composite implements
		HasMessageHandlers {

	public MessageComposite() {
		super();
	}

	public HandlerRegistration addMessageHandler(MessageHandler handler) {
		return addHandler(handler, MessageEvent.TYPE);
	}

	public void info(String text) {
		info(text, true);
	}

	public void info(String text, boolean loadding) {
		InformationDialog.DIALOG.show();
		InformationDialog.DIALOG.center();
		InformationDialog.DIALOG.setInformation(text, loadding);
	}

	public void closeInfo() {
		InformationDialog.DIALOG.hide();
	}

	/**
	 * 发送消息
	 * 
	 * @param message
	 */
	public void message(String message) {
		onMessage(this, MessageEvent.MESSAGE, message);
	}

	/**
	 * 缺省的消息处理函数
	 * 
	 * @param sender
	 * @param message
	 * @param data
	 */
	public void onMessage(Object sender, Integer message, Object data) {
		MessageEvent ev = new MessageEvent(message, data);
		fireEvent(ev);
	}

	/**
	 * 辅助处理消息
	 * 
	 * @param sender
	 * @param message
	 * @param data
	 */
	public void handlerMessaage(Object sender, Integer message, Object value) {
		if (message == MessageEvent.MESSAGE) {
			message((String) value);
		}
	}

	/**
	 * 辅助函数，激发一个消息
	 * 
	 * @param message
	 *            消息类型
	 * @param data
	 *            消息数据
	 */
	public void fireMessage(Integer message, Object data) {
		MessageEvent ev = new MessageEvent(message, data);
		fireEvent(ev);
	}

}
