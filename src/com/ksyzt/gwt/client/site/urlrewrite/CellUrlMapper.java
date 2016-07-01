package com.ksyzt.gwt.client.site.urlrewrite;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.common.MessageComposite;
import com.ksyzt.gwt.client.event.MessageEvent;

public class CellUrlMapper extends MessageComposite {

	private static CellUrlMapperUiBinder uiBinder = GWT
			.create(CellUrlMapperUiBinder.class);

	interface CellUrlMapperUiBinder extends UiBinder<Widget, CellUrlMapper> {
	}

	public CellUrlMapper() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	TextBox txtDescription;

	@UiField
	TextBox txtFrom;

	@UiField
	TextBox txtTo;

	public void renderCell(RewriteData d) {
		txtDescription.setText(d.desc);
		txtFrom.setText(d.from);
		txtTo.setText(d.to);
	}

	@UiHandler("btnModify")
	void onModify(ClickEvent e) {
		RewriteData d = new RewriteData();
		d.desc = txtDescription.getValue();
		d.from = txtFrom.getValue();
		d.to = txtTo.getValue();
		MessageEvent ev = new MessageEvent(MessageEvent.EDIT, d);
		fireEvent(ev);
	}

	@UiHandler("btnDelete")
	void onDelete(ClickEvent e) {
		RewriteData d = new RewriteData();
		d.desc = txtDescription.getValue();
		d.from = txtFrom.getValue();
		d.to = txtTo.getValue();
		MessageEvent ev = new MessageEvent(MessageEvent.DELETE, d);
		fireEvent(ev);
	}
}
