package com.ksyzt.gwt.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.ksyzt.gwt.client.site.urlrewrite.RewriteData;
import com.ksyzt.gwt.shared.exception.AdminLoginException;
import com.ksyzt.gwt.shared.module.AdminUser;
import com.ksyzt.gwt.shared.module.SiteInformation;

@RemoteServiceRelativePath("../ksyzt_site_manager")
public interface ISiteManager extends RemoteService {

	/***************************** site infomation *****************************/
	public SiteInformation updateSiteInformation(SiteInformation info)
			throws Exception;

	public SiteInformation getSiteInformation() throws Exception;

	/*************************** url rewrite ************************************/
	public List<RewriteData> getRewriteData() throws Exception;

	public List<RewriteData> updateRewriteData(RewriteData data)
			throws Exception;

	public List<RewriteData> deleteRewriteData(RewriteData data)
			throws Exception;

	/************************* site configure user login ************************/
	public AdminUser adminLogin(String username, String pwd)
			throws AdminLoginException;

	public AdminUser adminUpdate(String username, String pwd)
			throws AdminLoginException;

	public AdminUser checkAdminLogin() throws AdminLoginException;

	public void quit();

}
