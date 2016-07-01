package com.ksyzt.gwt.client.mainframe;

import java.util.HashMap;

/**
 * 模块初始化参数
 * 
 * @author Administrator
 * 
 */
public class ModuleProperties extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	public int getInt(String key, int defaultvalue) {
		if (this.containsKey(key)) {
			Integer i = (Integer) this.get(key);
			return i;
		} else {
			return defaultvalue;
		}
	}

	public Boolean getBoolean(String key, Boolean defaultvalue) {
		if (this.containsKey(key)) {
			Boolean i = (Boolean) this.get(key);
			return i;
		} else {
			return defaultvalue;
		}

	}

	public Object getObject(String key, Object defaultvalue) {
		if (this.containsKey(key)) {
			return this.get(key);
		} else {
			return defaultvalue;
		}

	}

	public String getString(String key, String defaultvalue) {
		if (this.containsKey(key)) {
			return (String) this.get(key);
		} else {
			return defaultvalue;
		}

	}

}
