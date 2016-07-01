/******************************************************************************
<pre>

           =============================================================
           -   ____ _  _ ____ _  _ ____  _ _ ____ _  _ ____ _  _ ____  -
           -    __] |__| |__| |\ | | __  | | |__| |\ | [__  |__| |___  -
           -   [___ |  | |  | | \| |__| _| | |  | | \| ___] |  | |___  -
           -           http://hi.baidu.com/zhangjianshe                -
           =============================================================

</pre>
 *******************************************************************************/
package com.ksyzt.gwt.client.common;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * 信息显示对话框
 * 
 * @author zhangjianshe@navinfo.com
 * 
 */
public class InformationDialog extends PopupPanel {

	public static InformationDialog DIALOG;
	HTMLPanel panel;
	HTML mHTML;

	/**
	 * 获取弹出消息框
	 * 
	 * @return
	 */
	public static InformationDialog getDialog() {
		if (DIALOG == null) {
			DIALOG = new InformationDialog();
		}
		return DIALOG;
	}

	/**
	 * 
	 */
	public InformationDialog() {
		this.setAnimationEnabled(false);
		this.setAutoHideEnabled(false);
		this.setStylePrimaryName("information-dialog");

		mHTML = new HTML();
		this.add(mHTML);
		this.setGlassEnabled(false);
		this.setModal(true);
	}

	/**
	 * 设置图标
	 * 
	 * @param url
	 */
	public void setIcon(String url) {
		mIcon = url;
	}

	private String mIcon;

	public void setInformation(String info, boolean loadding) {
		String html = "";
		if (loadding) {
			html += "<table><tr><td><img src='" + mIcon + "'/></td>";
			html += "<td>" + info + "</td>";
			html += "</tr></table>";
		} else {
			html += info;
		}
		mHTML.setHTML(html);
	}

	public void setInformation(String info) {
		setInformation(info, true);
	}
}
