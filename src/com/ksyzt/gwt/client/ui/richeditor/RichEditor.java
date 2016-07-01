package com.ksyzt.gwt.client.ui.richeditor;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

public class RichEditor extends VerticalPanel {

	@UiConstructor
	public RichEditor() {
		super();
		m_editor=new RichTextArea();
		toolbar=new RichTextToolbar();
		toolbar.setRichTextArea(m_editor);
		this.add(toolbar);
		this.add(m_editor);
		m_editor.setWidth("100%");
	}

	private RichTextToolbar toolbar;
	private  RichTextArea    m_editor;


	public void setHTML(String html)
	{
		m_editor.setHTML(html);
	}

	public String getHTML()
	{
		return m_editor.getHTML();
	}

	@Override
	protected void onDetach()
	{
		super.onDetach();
		toolbar.hidePopup();
	}
	
	

}
