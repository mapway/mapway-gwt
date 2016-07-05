package com.ksyzt.gwt.server.servlet;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ksyzt.gwt.client.service.ISiteManager;
import com.ksyzt.gwt.client.site.urlrewrite.RewriteData;
import com.ksyzt.gwt.server.common.SiteUtil;
import com.ksyzt.gwt.server.common.Utility;
import com.ksyzt.gwt.shared.exception.AdminLoginException;
import com.ksyzt.gwt.shared.module.AdminUser;
import com.ksyzt.gwt.shared.module.SiteInformation;
import com.ksyzt.gwt.shared.module.SystemConst;

// TODO: Auto-generated Javadoc
/**
 * The Class SiteManagerServlet.
 */
public class SiteManagerServlet extends RemoteServiceServlet implements
		ISiteManager {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see com.ksyzt.gwt.client.service.ISiteManager#updateSiteInformation(com.ksyzt.gwt.shared.module.SiteInformation)
	 */
	@Override
	public SiteInformation updateSiteInformation(SiteInformation info)
			throws Exception {

		SiteUtil.writeSiteInfo(this.getServletContext(), info);
		return info;
	}

	/* (non-Javadoc)
	 * @see com.ksyzt.gwt.client.service.ISiteManager#getSiteInformation()
	 */
	@Override
	public SiteInformation getSiteInformation() throws Exception {

		return SiteUtil.getFromMemory(this.getServletContext(), false);
	}

	/**
	 * *********************************** site rewrite **********************.
	 *
	 * @return the rewrite data
	 * @throws Exception the exception
	 */
	@Override
	public List<RewriteData> getRewriteData() throws Exception {
		return SiteUtil.getRewriteDataFromMemory(getServletContext());
	}

	/* (non-Javadoc)
	 * @see com.ksyzt.gwt.client.service.ISiteManager#updateRewriteData(com.ksyzt.gwt.client.site.urlrewrite.RewriteData)
	 */
	@Override
	public List<RewriteData> updateRewriteData(RewriteData data)
			throws Exception {
		List<RewriteData> list = SiteUtil
				.getRewriteDataFromMemory(getServletContext());
		boolean handler = false;
		for (RewriteData d : list) {
			if (d.from.equals(data.from)) {
				d.from = data.from;
				d.to = data.to;
				d.desc = data.desc;
				handler = true;
				break;
			}
		}
		if (handler == false) {
			list.add(data);
		}
		SiteUtil.writeRewriteData(getServletContext(), list);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.ksyzt.gwt.client.service.ISiteManager#deleteRewriteData(com.ksyzt.gwt.client.site.urlrewrite.RewriteData)
	 */
	@Override
	public List<RewriteData> deleteRewriteData(RewriteData data)
			throws Exception {
		List<RewriteData> list = SiteUtil
				.getRewriteDataFromMemory(getServletContext());

		for (RewriteData d : list) {
			if (d.from.equals(data.from)) {
				list.remove(d);
				break;
			}
		}
		SiteUtil.writeRewriteData(getServletContext(), list);
		return list;
	}

	/**
	 * ***************************** admin login *****************************.
	 *
	 * @param username the username
	 * @param pwd the pwd
	 * @return the admin user
	 * @throws AdminLoginException the admin login exception
	 */
	@Override
	public AdminUser adminLogin(String username, String pwd)
			throws AdminLoginException {
		if (username == null || username.length() == 0) {
			throw new AdminLoginException(AdminLoginException.AE_MSG, "用户名不能为空");
		} else if (pwd == null || pwd.length() == 0) {
			throw new AdminLoginException(AdminLoginException.AE_MSG, "密码不能为空");
		}
		AdminUser u = SiteUtil.confirmAdminUser(getServletContext());
		if (u != null) {
			if (!username.equals(u.getUserName())) {
				throw new AdminLoginException(AdminLoginException.AE_MSG,
						"没有此用户");
			}
			if (!Utility.encrypt(pwd).equals(u.getPassword())) {
				throw new AdminLoginException(AdminLoginException.AE_MSG,
						"密码错误");
			}
			HttpSession session = perThreadRequest.get().getSession();
			session.setAttribute(SystemConst.SESSION_ADMIN_USER, u);
		}
		return u;
	}

	/* (non-Javadoc)
	 * @see com.ksyzt.gwt.client.service.ISiteManager#adminUpdate(java.lang.String, java.lang.String)
	 */
	@Override
	public AdminUser adminUpdate(String username, String pwd)
			throws AdminLoginException {
		if (username == null || username.length() == 0) {
			throw new AdminLoginException(AdminLoginException.AE_MSG,
					SystemConst.MSG_USERNAME_NOTNULL);
		} else if (pwd == null || pwd.length() == 0) {
			throw new AdminLoginException(AdminLoginException.AE_MSG,
					SystemConst.MSG_PWD_NOTNULL);
		}
		AdminUser u = new AdminUser();
		u.setPassword(com.ksyzt.gwt.server.common.Utility.encrypt(pwd));
		u.setUserName(username);
		SiteUtil.writeAdminUser(getServletContext(), u);
		HttpSession session = perThreadRequest.get().getSession();
		session.setAttribute(SystemConst.SESSION_ADMIN_USER, u);
		return u;
	}

	/* (non-Javadoc)
	 * @see com.ksyzt.gwt.client.service.ISiteManager#checkAdminLogin()
	 */
	@Override
	public AdminUser checkAdminLogin() throws AdminLoginException {

		HttpSession session = perThreadRequest.get().getSession();
		AdminUser u = (AdminUser) session
				.getAttribute(SystemConst.SESSION_ADMIN_USER);
		if (u == null) {

			u = SiteUtil.confirmAdminUser(getServletContext());
			
			return null;

		} else {
			return u;
		}

	}

	/* (non-Javadoc)
	 * @see com.ksyzt.gwt.client.service.ISiteManager#quit()
	 */
	@Override
	public void quit() {
		HttpSession session = perThreadRequest.get().getSession();
		session.setAttribute(SystemConst.SESSION_ADMIN_USER, null);
	}
}
