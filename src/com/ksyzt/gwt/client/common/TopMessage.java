package com.ksyzt.gwt.client.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class TopMessage extends PopupPanel {

	private static TopMessageUiBinder uiBinder = GWT
			.create(TopMessageUiBinder.class);

	interface TopMessageUiBinder extends UiBinder<Widget, TopMessage> {
	}

	public TopMessage() {
		setWidget(uiBinder.createAndBindUi(this));
		this.setAnimationEnabled(true);
		this.setAutoHideEnabled(false);
		this.setGlassEnabled(false);
		this.setModal(false);
		this.setStyleName("");
	}

	@UiField
	Label lbMessage;
	private PositionCallback m_callback = new PositionCallback() {

		public void setPosition(int offsetWidth, int offsetHeight) {
			int width = Window.getClientWidth();
			int left = (width - offsetWidth) / 2;
			int top = 0;
			TopMessage.this.setPopupPosition(left, top);
		}

	};

	public void showMesasge(String text) {
		if (text == null || text.length() == 0) {
			this.hide();
		} else {
			lbMessage.setText(text);
			if (!this.isShowing()) {
				this.setPopupPositionAndShow(m_callback);
			}
			new Timer() {

				@Override
				public void run() {
					TopMessage.this.hide(true);
				}
			}.schedule(5 * 1000);
		}
	}

}
