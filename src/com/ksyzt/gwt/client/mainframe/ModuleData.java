package com.ksyzt.gwt.client.mainframe;

import java.util.Properties;

import com.google.gwt.user.client.ui.Widget;

public class ModuleData {
	static int g_index = 0;
	IModuleFactory m_mf;
	IAppModule module;
	Integer thisIndex;
	AppData app;
	ModuleProperties m_props;

	@SuppressWarnings("unused")
	private ModuleData() {
	}

	public Widget getWidget() {
		if (module == null) {
			return null;
		} else {
			return module.getRootWidget();
		}
	}

	public Integer getIndex() {
		return this.thisIndex;
	}

	public String getCaption() {
		return m_mf.getModuleCaption();
	}

	public Widget createWidget() {
		if (module == null) {
			module = m_mf.createModule();
			module.onInitialize(app, m_props);
		}
		return module.getRootWidget();
	}

	public ModuleData(AppData application, IModuleFactory mf,
			ModuleProperties props) {
		m_mf = mf;
		app = application;
		module = null;
		thisIndex = g_index++;
		m_props = props;
	}
}
