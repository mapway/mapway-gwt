package com.ksyzt.gwt.client.common;

import java.io.Serializable;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.ksyzt.gwt.client.event.HasMessageHandlers;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;

/**
 * 网页的导航条 首页 上一页 [3/10] 下一页 尾页 【】页GO
 * 
 * @author 张建设
 * 
 */
public class PageNaviBar extends HorizontalPanel implements HasMessageHandlers {

	public class NaviData implements Serializable {
		public int page;
		public int size;

		public NaviData(int page, int size) {
			this.page = page;
			this.size = size;
		}
	}

	Anchor first;
	Anchor prev;
	Anchor next;
	Anchor last;
	TextBox txtPage;
	Button btnGo;
	Label lbInfo;
	private ClickHandler m_go_click = new ClickHandler() {

		public void onClick(ClickEvent event) {

			Integer num = Integer.valueOf(txtPage.getValue());
			if (num >= 1 && num <= m_pages + 1) {
				firePage(num - 1);
			}

		}
	};

	private void firePage(int page) {
		NaviData data = new NaviData(page , m_pagesize);
		MessageEvent ev = new MessageEvent(MessageEvent.PAGE, data);
		fireEvent(ev);
		renderState(page, m_pages);
	}

	private ClickHandler m_first_click = new ClickHandler() {

		public void onClick(ClickEvent event) {

			firePage(0);
		}
	};

	private ClickHandler m_prev_click = new ClickHandler() {

		public void onClick(ClickEvent event) {
			m_currentpage--;
			firePage(m_currentpage);
		}
	};
	private ClickHandler m_next_click = new ClickHandler() {

		public void onClick(ClickEvent event) {
			m_currentpage++;
			firePage(m_currentpage);
		}
	};
	private ClickHandler m_last_click = new ClickHandler() {

		public void onClick(ClickEvent event) {
			m_currentpage = m_pages - 1;
			firePage(m_currentpage);
		}
	};

	@UiConstructor
	public PageNaviBar() {
		super();
		this.setSpacing(3);
		init();
	}

	private void init() {
		first = new Anchor("首页");
		first.addClickHandler(m_first_click);
		this.add(first);

		prev = new Anchor("上一页");
		prev.addClickHandler(m_prev_click);
		this.add(prev);

		lbInfo = new Label("/");
		lbInfo.setWidth("80px");
		this.add(lbInfo);
		this.setCellHorizontalAlignment(lbInfo,
				HasHorizontalAlignment.ALIGN_CENTER);

		next = new Anchor("下一页");
		next.addClickHandler(m_next_click);
		this.add(next);

		last = new Anchor("尾页");
		last.addClickHandler(m_last_click);
		this.add(last);

		txtPage = new TextBox();
		txtPage.setWidth("40px");
		this.add(txtPage);
		btnGo = new Button("Go");
		btnGo.addClickHandler(m_go_click);
		this.add(btnGo);

		first.setEnabled(false);
		next.setEnabled(false);
		prev.setEnabled(false);
		last.setEnabled(false);
		btnGo.setEnabled(false);

	}

	public HandlerRegistration addMessageHandler(MessageHandler handler) {
		return addHandler(handler, MessageEvent.TYPE);
	}

	int m_currentpage;
	int m_pages;
	int m_pagesize;

	public void setData(int currentPage, int total, int pagesize) {
		m_pagesize = pagesize;
		m_currentpage = currentPage;
		int pages = 0;

		pages = total / pagesize;
		m_pages = pages;

		renderState(currentPage, pages);
	}

	private void renderState(int currentPage, int pages) {
		if (currentPage >= 0 && currentPage <= pages) {
			txtPage.setText((currentPage + 1) + "");
			lbInfo.setText((currentPage + 1) + "/" + (pages + 1));
			btnGo.setEnabled(true);

			if (currentPage == 0) {
				first.setEnabled(false);
			} else {
				first.setEnabled(true);
			}

			if (currentPage == pages) {
				last.setEnabled(false);
			} else {
				last.setEnabled(true);
			}

			if (currentPage < pages) {
				next.setEnabled(true);
			} else {
				next.setEnabled(false);
			}

			if (currentPage > 0) {
				prev.setEnabled(true);
			} else {
				prev.setEnabled(false);
			}

		} else {
			first.setEnabled(false);
			next.setEnabled(false);
			prev.setEnabled(false);
			last.setEnabled(false);
			btnGo.setEnabled(false);
		}
	}
}
