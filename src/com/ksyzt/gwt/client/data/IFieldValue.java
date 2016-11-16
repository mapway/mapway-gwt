package com.ksyzt.gwt.client.data;

/**
 * 通过名称获取字段值
 * 
 * @author zhangjianshe
 *
 */
public interface IFieldValue {

	/**
	 * 获取字段值
	 * 
	 * @param fieldName
	 * @return
	 */
	public Object getFieldValue(String fieldName);
}
