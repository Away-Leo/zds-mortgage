package com.zdsoft.finance.contract.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * <版权所有：重庆正大华日软件有限公司
 
 * Title: 泛华信贷系统
 *
 * Description: BUSS_PRINT_TPL_PAGE entity
 *
 * @author wolfhuang
 * @version 1.0 
 */
 
@Entity
@Table(name = "BUSS_PRINT_TPL_PAGE")
public class BussPrintTplPage extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = -3307895529384707655L;
	

    
	/** 页名 */
    @Column(length=64)
	private String pagename;
    
    
	/** 页码 */
    @Column
	private Long pagenum;
    
    
	/** 左边距 */
    @Column
	private Long leftmargin;
    
    
	/** 上边距 */
    @Column
	private Long topmargin;
    
    
	/** 页参照定位附件Id */
    @Column(length=32)
	private String attachmentid;
    
    
	/** 所属模板Id */
    @Column(length=32)
	private String printtemplateid;
    

	/**
	 * @return 返回 页名。
	 */

	public String getPagename() {
		return pagename;
	}

	/**
	 * @param pagename 要设置的 页名。
	 */
	public void setPagename(String pagename) {
		this.pagename = pagename;
	}
	/**
	 * @return 返回 页码。
	 */

	public Long getPagenum() {
		return pagenum;
	}

	/**
	 * @param pagenum 要设置的 页码。
	 */
	public void setPagenum(Long pagenum) {
		this.pagenum = pagenum;
	}
	/**
	 * @return 返回 左边距。
	 */

	public Long getLeftmargin() {
		return leftmargin;
	}

	/**
	 * @param leftmargin 要设置的 左边距。
	 */
	public void setLeftmargin(Long leftmargin) {
		this.leftmargin = leftmargin;
	}
	/**
	 * @return 返回 上边距。
	 */

	public Long getTopmargin() {
		return topmargin;
	}

	/**
	 * @param topmargin 要设置的 上边距。
	 */
	public void setTopmargin(Long topmargin) {
		this.topmargin = topmargin;
	}
	/**
	 * @return 返回 页参照定位附件Id。
	 */

	public String getAttachmentid() {
		return attachmentid;
	}

	/**
	 * @param attachmentid 要设置的 页参照定位附件Id。
	 */
	public void setAttachmentid(String attachmentid) {
		this.attachmentid = attachmentid;
	}
	/**
	 * @return 返回 所属模板Id。
	 */

	public String getPrinttemplateid() {
		return printtemplateid;
	}

	/**
	 * @param printtemplateid 要设置的 所属模板Id。
	 */
	public void setPrinttemplateid(String printtemplateid) {
		this.printtemplateid = printtemplateid;
	}
	
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
}
