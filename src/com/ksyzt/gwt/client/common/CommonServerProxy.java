package com.ksyzt.gwt.client.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.ksyzt.gwt.client.service.ISiteManager;
import com.ksyzt.gwt.client.service.ISiteManagerAsync;

@RemoteServiceRelativePath("../ksyzt_common_server")
public interface CommonServerProxy {
	public static final ISiteManagerAsync SITE_MANAGER = GWT
			.create(ISiteManager.class);
}
