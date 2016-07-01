package com.ksyzt.gwt.client.mainframe;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.CustomTab;
import com.ksyzt.gwt.client.common.MessageComposite;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

public class AppTopbar extends MessageComposite {

	private static AppTopbarUiBinder uiBinder = GWT
			.create(AppTopbarUiBinder.class);

	interface AppTopbarUiBinder extends UiBinder<Widget, AppTopbar> {
	}

	private MessageHandler m_tabs_handler = new MessageHandler() {

		@Override
		public void onMessage(Object sender, Integer message, Object value) {
			if (message == MessageEvent.MESSAGE) {
				message((String) value);
			} else {
				MessageEvent ev = new MessageEvent(message, value);
				fireEvent(ev);
			}
		}
	};

	@UiHandler("linkQuit")
	void onExit(ClickEvent e) {
		MessageEvent ev = new MessageEvent(MessageEvent.QUIT, 0);
		fireEvent(ev);
	}

	@UiConstructor
	public AppTopbar() {
		initWidget(uiBinder.createAndBindUi(this));
		tabs.addMessageHandler(m_tabs_handler);
		tabs.setTabAlign(HasHorizontalAlignment.ALIGN_RIGHT);
	}

	@UiField
	CustomTab tabs;

	@UiField
	Label lbUserName;
	int m_uid;

	public void initTop(String username, int uid) {
		lbUserName.setText(username);
		m_uid = uid;
	}

	public void addTab(String caption, Integer id) {
		tabs.addItem(caption, id);
	}

	public void addTab(ModuleData d) {
		tabs.addItem(d.getCaption(), d);
	}

	public void setSelected(int index) {
		tabs.setSelectIndex(index);
	}
}
