package com.ksyzt.gwt.client.event;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasMessageHandlers extends HasHandlers {
	HandlerRegistration addMessageHandler(MessageHandler handler);
}
