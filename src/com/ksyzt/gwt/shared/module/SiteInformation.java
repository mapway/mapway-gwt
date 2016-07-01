package com.ksyzt.gwt.shared.module;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述网站信息
 * 
 * @author Administrator
 * 
 */

public class SiteInformation implements Serializable {

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary
	 *            the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the when
	 */
	public Date getWhen() {
		return when;
	}

	/**
	 * @param when
	 *            the when to set
	 */
	public void setWhen(Date when) {
		this.when = when;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return the icp
	 */
	public String getIcp() {
		return icp;
	}

	/**
	 * @param icp
	 *            the icp to set
	 */
	public void setIcp(String icp) {
		this.icp = icp;
	}

	/**
	 * @return the secno
	 */
	public String getSecno() {
		return secno;
	}

	/**
	 * @param secno
	 *            the secno to set
	 */
	public void setSecno(String secno) {
		this.secno = secno;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the web
	 */
	public String getWeb() {
		return web;
	}

	/**
	 * @param web
	 *            the web to set
	 */
	public void setWeb(String web) {
		this.web = web;
	}

	/**
	 * @return the open
	 */
	public boolean getOpen() {
		return open;
	}

	/**
	 * @param open
	 *            the open to set
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}

	/**
	 * @return the allowreg
	 */
	public boolean isAllowreg() {
		return allowreg;
	}

	/**
	 * @param allowreg
	 *            the allowreg to set
	 */
	public void setAllowreg(boolean allowreg) {
		this.allowreg = allowreg;
	}

	/**
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords
	 *            the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * @return the siteowner
	 */
	public String getSiteowner() {
		return siteowner;
	}

	/**
	 * @param siteowner
	 *            the siteowner to set
	 */
	public void setSiteowner(String siteowner) {
		this.siteowner = siteowner;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 5990058217383767521L;

	
	private String name;
	
	private String summary;
	
	private Date when;

	private int version;
	
	private String icp;
	
	private String secno;
	
	private String email;
	
	private String web;
	
	private boolean open;
	
	private boolean allowreg;
	
	private String keywords;
	
	private String siteowner;
	
	private String tel;

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
}
