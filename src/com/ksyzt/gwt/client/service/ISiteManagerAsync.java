package com.ksyzt.gwt.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.ksyzt.gwt.client.site.urlrewrite.RewriteData;
import com.ksyzt.gwt.shared.module.AdminUser;
import com.ksyzt.gwt.shared.module.SiteInformation;

public interface ISiteManagerAsync {

	void getSiteInformation(AsyncCallback<SiteInformation> callback);

	void updateSiteInformation(SiteInformation info,
			AsyncCallback<SiteInformation> callback);
	void getRewriteData(AsyncCallback<List<RewriteData>> callback);

	void updateRewriteData(RewriteData data,
			AsyncCallback<List<RewriteData>> callback);

	void deleteRewriteData(RewriteData data,
			AsyncCallback<List<RewriteData>> callback);

	void adminLogin(String username, String pwd,
			AsyncCallback<AdminUser> callback);

	void adminUpdate(String username, String pwd,
			AsyncCallback<AdminUser> callback);

	void checkAdminLogin(AsyncCallback<AdminUser> callback);

	void quit(AsyncCallback<Void> callback);

}
