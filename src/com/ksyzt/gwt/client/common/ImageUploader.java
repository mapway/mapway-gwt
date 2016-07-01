package com.ksyzt.gwt.client.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.ksyzt.gwt.client.event.MessageEvent;

public class ImageUploader extends MessageComposite {

	private static ImageUploaderUiBinder uiBinder = GWT
			.create(ImageUploaderUiBinder.class);

	interface ImageUploaderUiBinder extends UiBinder<Widget, ImageUploader> {
	}

	@UiField
	AbsolutePanel root;

	@UiField
	Button btnCancel;

	private ClickHandler m_image_click = new ClickHandler() {

		public void onClick(ClickEvent event) {
			panelLoader.setVisible(true);
		}
	};
	ClickHandler m_cancel_click = new ClickHandler() {

		public void onClick(ClickEvent event) {
			panelLoader.setVisible(false);

		}
	};

	@UiField
	Label info;
	private MouseOverHandler m_mouse_over = new MouseOverHandler() {

		public void onMouseOver(MouseOverEvent event) {

			if (!panelLoader.isVisible()) {
				info.setVisible(true);
			}
		}
	};

	MouseOutHandler m_mouse_out = new MouseOutHandler() {

		public void onMouseOut(MouseOutEvent event) {
			info.setVisible(false);
		}
	};

	@UiConstructor
	public ImageUploader() {
		initWidget(uiBinder.createAndBindUi(this));
		html.addClickHandler(m_image_click);

		btnCancel.addClickHandler(m_cancel_click);

		uploader.setName("fileset");
		uploader.addChangeHandler(m_file_change);

		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);
		form.setAction(GWT.getModuleBaseURL() + "../gwtfileuploader");
		form.addSubmitHandler(m_submit_handler);
		form.addSubmitCompleteHandler(m_submit_complete_handler);
		panelLoader.setVisible(false);

		html.addMouseOverHandler(m_mouse_over);

		html.addMouseOutHandler(m_mouse_out);

	}

	public boolean canUpload(String ft) {
		boolean b = false;

		if (ft.compareToIgnoreCase("zip") == 0
				|| ft.compareToIgnoreCase("png") == 0
				|| ft.compareToIgnoreCase("bmp") == 0
				|| ft.compareToIgnoreCase("jpg") == 0
				|| ft.compareToIgnoreCase("jpeg") == 0
				|| ft.compareToIgnoreCase("swf") == 0
				|| ft.compareToIgnoreCase("doc") == 0
				|| ft.compareToIgnoreCase("docx") == 0
				|| ft.compareToIgnoreCase("gif") == 0
				|| ft.compareToIgnoreCase("txt") == 0)
			b = true;
		return b;
	}

	private ChangeHandler m_file_change = new ChangeHandler() {

		public void onChange(ChangeEvent event) {
			String fn = uploader.getFilename();
			if (uploader.getFilename().equals("")) {

			} else {
				int lastdot = fn.lastIndexOf('.');
				if (lastdot > 0) {
					String filetype = fn.substring(lastdot + 1);
					if (canUpload(filetype)) {
						form.submit();
					} else {
						message("不能上传此文件格式");
					}
				}
			}
		}
	};

	public void message(String msg) {
		MessageEvent ev = new MessageEvent(MessageEvent.MESSAGE, msg);
		fireEvent(ev);
	}

	
	private SubmitHandler m_submit_handler = new SubmitHandler() {

		public void onSubmit(SubmitEvent event) {
			
		}
	};

	@UiField
	HTML html;
	private SubmitCompleteHandler m_submit_complete_handler = new SubmitCompleteHandler() {

		public void onSubmitComplete(SubmitCompleteEvent event) {

			String jsonstr = event.getResults();
			JSONValue rvalue = JSONParser.parse(jsonstr);
			JSONObject robj = rvalue.isObject();

			JSONString msg = (JSONString) robj.get("err").isString();
			if (msg.stringValue().equals("")) {
				JSONObject data = robj.get("data").isObject();

				String path = data.get("path").isString().stringValue();
				String url = data.get("url").isString().stringValue();
				mUrl = url;
				int w = root.getOffsetWidth() - 6;
				int h = root.getOffsetHeight() - 6;

				String imgdata = "<img src='" + url + "' width=" + w
						+ " height=" + h + " />";
				html.setHTML(imgdata);
				MessageEvent ev = new MessageEvent(MessageEvent.SUCCESS, path);
				fireEvent(ev);
				message("上传成功");
			} else {
				message(msg.stringValue());
			}
			panelLoader.setVisible(false);
		}
	};
	@UiField
	FlowPanel panelLoader;

	@UiField
	FileUpload uploader;

	@UiField
	Hidden thumbWidth;
	@UiField
	Hidden thumbHeight;

	int m_height = 334;
	int m_width = 228;

	public void setThumbSize(int w, int h) {
		if (w > 0) {
			m_width = w;
		}
		if (h > 0) {
			m_height = h;
		}
	}

	@UiField
	FormPanel form;

	/**
	 * 设置初始化图像
	 * @param url
	 */
	public void setImage(String url) {
		if (url == null || url.length() == 0) {

			int w = this.getOffsetWidth() - 6;
			int h = this.getOffsetHeight() - 6;
			url = SysResource.INSTANCE.upload().getSafeUri().asString();
			String imgdata = "<img src='" + url + "' width=" + w + " height="
					+ h + " />";
			html.setHTML(imgdata);

		} else {
			int w = this.getOffsetWidth() - 6;
			int h = this.getOffsetHeight() - 6;
			String imgdata = "<img src='" + url + "' width=" + w + " height="
					+ h + " />";
			html.setHTML(imgdata);
		}
		panelLoader.setVisible(false);
	}

	@UiField
	Button btnRemovePicture;

	/**
	 * 删除新闻图片
	 * 
	 * @param e
	 */
	@UiHandler("btnRemovePicture")
	void onbtnRemovePicture(ClickEvent e) {
		MessageEvent ev = new MessageEvent(MessageEvent.DELETE, 0);
		fireEvent(ev);
	}

	public void reset() {
		form.reset();
		thumbHeight.setValue(m_height + "");
		thumbWidth.setValue(m_width + "");
	}

	private String mUrl = "";

	/**
	 * 获取图像Url
	 * 
	 * @return
	 */
	public String getImageRelativeUrl() {
		return mUrl;
	}
}
