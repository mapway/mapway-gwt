package com.ksyzt.gwt.client.common;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Image;

public class DeleteButton extends Image {

	public DeleteButton(int value) {
		super(SysResource.INSTANCE.delete());
		DOM.setElementPropertyInt(this.getElement(), "v", value);
		DOM.setStyleAttribute(this.getElement(), "cursor", "hand");
	}

	@UiConstructor
	public DeleteButton() {
		super(SysResource.INSTANCE.delete());
		DOM.setElementPropertyInt(this.getElement(), "v", 0);
		DOM.setStyleAttribute(this.getElement(), "cursor", "hand");
	}

	public int getValue() {
		return DOM.getElementPropertyInt(this.getElement(), "v");
	}
}
