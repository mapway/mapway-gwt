package com.ksyzt.gwt.client.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class SimpleMessage extends Composite {

	interface SimpleMessageUiBinder extends UiBinder<Widget, SimpleMessage> {
	}

	private static SimpleMessageUiBinder uiBinder = GWT
			.create(SimpleMessageUiBinder.class);

	public void setMessage(String t, String msg) {
		title.setText(t);
		html.setHTML(msg);
	}

	public SimpleMessage() {
		initWidget(uiBinder.createAndBindUi(this));
		title.setText("");
		html.setHTML("");
	}

	public SimpleMessage(String text) {
		initWidget(uiBinder.createAndBindUi(this));
		title.setText("");
		html.setHTML(text);
	}

	public void setMessage(String text) {
		title.setText("");
		html.setHTML(text);
	}

	@UiField
	HTML html;
	@UiField
	Label title;
}
