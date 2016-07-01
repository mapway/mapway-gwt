package com.ksyzt.gwt.server.listener;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ksyzt.gwt.client.site.urlrewrite.RewriteData;
import com.ksyzt.gwt.server.common.Logger;
import com.ksyzt.gwt.server.common.SiteUtil;
import com.ksyzt.gwt.server.common.ZipUtility;
import com.ksyzt.gwt.shared.module.SiteInformation;

public class KsyztServer implements ServletContextListener {

	public static List<RewriteData> REWRITEDATA = null;

	private Timer m_timer;

	private void info(String info) {
		Logger.LOG.info(tag, info);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		m_timer.cancel();
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {

		info("=====Server started (v1.0)==========");
		ServletContext context = event.getServletContext();
		long reloadtime = 10 * 60 * 60 * 1000;
		String v = event.getServletContext().getInitParameter(
				"site-configure-reload-time");
		if (v != null && v.length() > 0) {
			reloadtime = Long.valueOf(v);
		}
		m_timer = new Timer(true);

		String siteFile = SiteUtil.getSiteInfoFile(context);
		System.out.println("Site File:" + siteFile);
		File f = new File(siteFile);
		if (!f.exists()) {
			info(" X site configure file doesnt exist");
			info("create site configure @" + f.getAbsolutePath());
			SITEINFO = new SiteInformation();
			SITEINFO.setName("站点名称");
			SiteUtil.writeSiteInfo(context, SITEINFO);
		}

		info("extract console resource....");
		try {
			extractConsoleResource(context.getRealPath("/"));
			info("extract console resource ok");
		} catch (IOException e) {
			info("error to extract console resourceX");
		}

	}

	public static SiteInformation SITEINFO;

	private static String tag = "SiteServer";

	private void extractConsoleResource(String path) throws IOException {
		String pathjar;
		pathjar = ZipUtility.getClassFile(getClass());

		try {
			ZipUtility.decompress(pathjar, path, "war/", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
