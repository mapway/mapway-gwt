package com.ksyzt.gwt.client.ui.button;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Image;
import com.ksyzt.gwt.client.common.SysResource;
import com.ksyzt.gwt.client.event.HasMessageHandlers;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

public class Switch extends Image implements HasMessageHandlers {

	boolean b = true;
	private ClickHandler m_click_handler = new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			toggle();
		}
	};

	@UiConstructor
	public Switch() {
		super();
		on();
		this.addClickHandler(m_click_handler);
	}

	public void on() {
		b = true;
		this.setResource(SysResource.INSTANCE.switchon());
	}

	public void off() {
		b = false;
		this.setResource(SysResource.INSTANCE.switchoff());
	}

	public boolean toggle() {
		b = !b;
		setValue(b,true);
		return b;
	}

	public void setValue(boolean b) {
		setValue(b,false);
	}
	public void setValue(boolean b,boolean fire) {
		this.b = b;
		if (b == true) {
			this.setResource(SysResource.INSTANCE.switchon());
		} else {
			this.setResource(SysResource.INSTANCE.switchoff());
		}
		if(fire==true)
		{
			MessageEvent e=new MessageEvent(MessageEvent.VALUECHANGE, b);
			fireEvent(e);
		}
	}

	public boolean getValue() {
		return b;
	}

	@Override
	public HandlerRegistration addMessageHandler(MessageHandler handler) {
		return addHandler(handler, MessageEvent.TYPE);
	}
}
