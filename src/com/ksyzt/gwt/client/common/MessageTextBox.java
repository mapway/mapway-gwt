package com.ksyzt.gwt.client.common;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.TextBox;
import com.ksyzt.gwt.client.event.HasMessageHandlers;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

public class MessageTextBox extends TextBox implements HasMessageHandlers {

	public HandlerRegistration addMessageHandler(MessageHandler handler) {
		if (m_register != null) {
			m_register.removeHandler();
		}
		m_register = addHandler(handler, MessageEvent.TYPE);
		return m_register;
	}
	HandlerRegistration m_register = null;
	private KeyDownHandler m_key_down_handler =new KeyDownHandler() {
		
	
		public void onKeyDown(KeyDownEvent event) {
			if(event.getNativeKeyCode()==KeyCodes.KEY_ENTER)
			{
				MessageEvent ev=new MessageEvent(MessageEvent.RETURN,0);
				fireEvent(ev);
			}
			
		}
	};
	private FocusHandler m_focus_handler =new FocusHandler() {
		
	
		public void onFocus(FocusEvent event) {
			
			
		}
	};
	private BlurHandler m_blur_handler =new BlurHandler() {
		
	
		public void onBlur(BlurEvent event) {
			MessageEvent ev=new MessageEvent(MessageEvent.CANCEL,0);
			fireEvent(ev);
			
		}
	};
	
	@UiConstructor
	public MessageTextBox()
	{
		super();
		this.addKeyDownHandler(m_key_down_handler);
		this.addFocusHandler(m_focus_handler);
		this.addBlurHandler(m_blur_handler );
	}

}
