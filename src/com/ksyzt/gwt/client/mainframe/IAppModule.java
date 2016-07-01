package com.ksyzt.gwt.client.mainframe;

import com.google.gwt.user.client.ui.Widget;

/**
 * 系统模块必须实现此接口
 * @author Administrator
 *
 */
public interface IAppModule {
	/**
	 * 模块初始化
	 * @param application
	 */
	public void onInitialize(AppData application,ModuleProperties pros);

	/**
	 * 模块卸载
	 */
	public void onUnInitialize();

	/**
	 * 得到模块 的根元素
	 * @return
	 */
	public Widget getRootWidget();
}
