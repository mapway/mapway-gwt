package com.ksyzt.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface MessageHandler extends EventHandler {
	void onMessage(Object sender,Integer message, Object value);
}
