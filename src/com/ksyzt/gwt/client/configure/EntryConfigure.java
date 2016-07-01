package com.ksyzt.gwt.client.configure;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.ksyzt.gwt.client.common.CommonServerProxy;
import com.ksyzt.gwt.client.common.SysResource;
import com.ksyzt.gwt.client.event.ICheckValue;
import com.ksyzt.gwt.client.event.MessageEvent;
import com.ksyzt.gwt.client.event.MessageHandler;
import com.ksyzt.gwt.client.site.AdminUserConfigure;
import com.ksyzt.gwt.client.site.SiteFrame;
import com.ksyzt.gwt.shared.exception.AdminLoginException;
import com.ksyzt.gwt.shared.module.AdminUser;
import com.ksyzt.gwt.shared.module.SystemConst;

public class EntryConfigure implements EntryPoint {

	private ICheckValue m_check_value = new ICheckValue() {

		@Override
		public boolean check(Object obj) {
			AdminUser u = (AdminUser) obj;
			if (u.getUserName().length() == 0) {
				Window.alert(SystemConst.MSG_USERNAME_NOTNULL);
				return false;
			}
			if (u.getPassword().length() == 0) {
				Window.alert(SystemConst.MSG_PWD_NOTNULL);
				return false;
			}
			return true;
		}
	};

	private MessageHandler m_set_info = new MessageHandler() {

		@Override
		public void onMessage(Object sender, Integer message, Object value) {
			AdminUser u = (AdminUser) value;
			CommonServerProxy.SITE_MANAGER.adminUpdate(u.getUserName(),
					u.getPassword(), check_login_handler);
			dlg.hide();

		}
	};

	DialogBox dlg;
	/**
	 * 检查是否登录
	 */
	private AsyncCallback<AdminUser> check_login_handler = new AsyncCallback<AdminUser>() {

		@Override
		public void onSuccess(AdminUser result) {
			RootLayoutPanel.get().clear();
			if (result == null) {
				showLogin("请登录");
			} else {
				showFrame();
			}

		}

		@Override
		public void onFailure(Throwable caught) {
			RootPanel.get().clear();
			AdminLoginException exe = (AdminLoginException) caught;
			if (exe == null) {
				Window.alert(caught.getMessage());
			} else {
				switch (exe.getType()) {
				case AdminLoginException.AE_MSG: {
					showLogin(exe.getMessage());
					break;
				}
				case AdminLoginException.AE_FILEERROR: {
					showLogin(exe.getMessage());
					break;
				}
				case AdminLoginException.AE_NOFILE: {
					// 系统第一次使用 需要设置用户名和密码
					sureDialog();
					dlg.setModal(true);
					AdminUserConfigure configure = new AdminUserConfigure();
					configure.setMesage(caught.getMessage());
					configure.onCheckValue = m_check_value;
					configure.addMessageHandler(m_set_info);
					dlg.add(configure);
					dlg.show();
					dlg.center();
				}
				}

			}
		}
	};

	private MessageHandler dlg_login_handler = new MessageHandler() {

		@Override
		public void onMessage(Object sender, Integer message, Object value) {
			AdminUser u = (AdminUser) value;
			CommonServerProxy.SITE_MANAGER.adminLogin(u.getUserName(),
					u.getPassword(), check_login_handler);
			dlg.hide();
		}
	};

	private AsyncCallback<Void> m_on_qiut = new AsyncCallback<Void>() {

		@Override
		public void onSuccess(Void result) {
			CommonServerProxy.SITE_MANAGER.checkAdminLogin(check_login_handler);

		}

		@Override
		public void onFailure(Throwable caught) {
			com.google.gwt.user.client.Window.alert(caught.getMessage());
		}
	};

	private MessageHandler m_frame_quit = new MessageHandler() {

		@Override
		public void onMessage(Object sender, Integer message, Object value) {
			// TODO Auto-generated method stub
			if (MessageEvent.QUIT == message) {
				CommonServerProxy.SITE_MANAGER.quit(m_on_qiut);
			}
		}
	};

	@Override
	public void onModuleLoad() {

		SysResource.INSTANCE.getCss().ensureInjected();
		CommonServerProxy.SITE_MANAGER.checkAdminLogin(check_login_handler);
	}
	SiteFrame frame;
	protected void showFrame() {

		if(frame==null)
			{
				frame = new SiteFrame();
				frame.addMessageHandler(m_frame_quit);
			}
		RootLayoutPanel.get().add(frame);
	}

	public void showLogin(String msg) {
		sureDialog();
		dlg.setModal(true);
		AdminUserConfigure configure = new AdminUserConfigure();
		configure.setMesage(msg);
		configure.onCheckValue = m_check_value;
		configure.addMessageHandler(dlg_login_handler);
		dlg.add(configure);
		dlg.show();
		dlg.center();
	}

	private void sureDialog() {
		dlg = new DialogBox();

	}
}
