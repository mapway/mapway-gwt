package com.ksyzt.gwt.client.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HasAutoHorizontalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.event.MessageEvent;

public class CustomTab extends MessageComposite {

	private static CustomTabUiBinder uiBinder = GWT
			.create(CustomTabUiBinder.class);

	interface CustomTabUiBinder extends UiBinder<Widget, CustomTab> {
	}

	@UiConstructor
	public CustomTab() {
		initWidget(uiBinder.createAndBindUi(this));
		this.setStylePrimaryName("KSYZT");
		setTabAlign(HasHorizontalAlignment.ALIGN_LEFT);
	}

	@Override
	public void setStylePrimaryName(String name) {
		super.setStylePrimaryName(name);
		this.setStyleName(this.getStylePrimaryName() + "-TAB");
		lbCaption.setStyleName(this.getStylePrimaryName() + "-CAPTION");
		for (int index = 0; index < btns.getWidgetCount(); index++) {
			Widget a1 = (Widget) btns.getWidget(index);
			a1.setStyleName(this.getStylePrimaryName() + "-Button");
		}
	}

	public void setTabAlign(HorizontalAlignmentConstant align) {
		if (HasHorizontalAlignment.ALIGN_RIGHT.getTextAlignString().equals(
				align.getTextAlignString())) {
			box.remove(lbCaption);
			box.insert(lbCaption, 0);
			box.setCellHorizontalAlignment(btns,
					HasHorizontalAlignment.ALIGN_RIGHT);
			box.setCellHorizontalAlignment(lbCaption,
					HasHorizontalAlignment.ALIGN_LEFT);
		} else {
			box.remove(lbCaption);
			box.insert(lbCaption, 1);
			box.setCellHorizontalAlignment(btns,
					HasHorizontalAlignment.ALIGN_LEFT);
			box.setCellHorizontalAlignment(lbCaption,
					HasHorizontalAlignment.ALIGN_RIGHT);
		}
	}

	@UiField
	HorizontalPanel box;
	@UiField
	HorizontalPanel btns;
	@UiField
	Label lbCaption;

	public void setCaption(String text) {
		lbCaption.setText(text);
	}

	public void setSelectIndex(int index) {
		setSelectIndex(index, false);
	}

	public void setSelectIndex(Object data) {
		setSelectIndex(data, false);
	}

	public void setSelectIndex(int index, boolean fireEvent) {
		if (index >= 0 && index < btns.getWidgetCount()) {
			Widget a = (Widget) btns.getWidget(index);
			makeAnchorSelected(a, fireEvent);
		}
	}

	public void setSelectIndex(Object data, boolean fireEvent) {
		for (int i = 0; i < btns.getWidgetCount(); i++) {
			Widget a = (Widget) btns.getWidget(i);
			if (a.getElement().getPropertyObject("v").equals(data)) {
				makeAnchorSelected(a, fireEvent);
				break;
			}
		}
	}

	private void makeAnchorSelected(Widget a, boolean fireEvent) {
		if (!a.getStyleName().equals(
				this.getStylePrimaryName() + "-BUTTON-SELECED")) {
			for (int index = 0; index < btns.getWidgetCount(); index++) {
				Widget a1 = (Widget) btns.getWidget(index);
				if (a1.equals(a)) {
					a1.setStyleName(this.getStylePrimaryName()
							+ "-BUTTON-SELECTED");
				} else {
					a1.setStyleName(this.getStylePrimaryName() + "-BUTTON");
				}
			}
		}
		if (fireEvent) {
			Object v = a.getElement().getPropertyObject("v");
			MessageEvent ev = new MessageEvent(MessageEvent.ITEMCLICK, v);
			fireEvent(ev);
		}
	}

	public void addItem(String text, Object value) {
		Label a = new Label(text);
		a.addClickHandler(m_click);
		a.setStyleName(this.getStylePrimaryName() + "-BUTTON");
		a.getElement().setPropertyObject("v", value);
		a.setAutoHorizontalAlignment(HasAutoHorizontalAlignment.ALIGN_CENTER);
		btns.add(a);
	}

	private ClickHandler m_click = new ClickHandler() {
		public void onClick(ClickEvent event) {
			Widget a = (Widget) event.getSource();
			makeAnchorSelected(a, true);
		}
	};
}
