

package com.zdsoft.finance.contract.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * <p>版权所有：重庆正大华日软件有限公司</p>
 
 * <p>Title: 泛华信贷系统</p>
 *
 * <p>Description: ZF_PRINT_TEMPLATE_INNERPAGE entity</p>
 *
 * @author wolfhuang
 * @version 1.0 
 */
 
@Entity
@Table(name = "ZF_PRINT_TEMPLATE_INNERPAGE")
public class ZfPrintTemplateInnerpage extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = -3307895529384707655L;
	

    
	/** 套打模板内页Id */

	private String pid;
    
    
	/** 套打模板Id */

	private String tid;
    
    
	/** 页名 */

	private String pagename;
    
    
	/** 页码 */

	private Long pageno;
    
    
	/** 左边距 */

	private Long paddingleft;
    
    
	/** 上边距 */

	private Long paddingtop;
    
    
	/** 页地址 */

	private String pagepath;
    
    
	/** 附件 */

	private String filename;
    
    
	/** 正文内容 */

	private String pagecontent;
    

	/**
	 * @return 返回 套打模板内页Id。
	 */
	public String getPid() {
		return pid;
	}

	/**
	 * @param pid 要设置的 套打模板内页Id。
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * @return 返回 套打模板Id。
	 */
	public String getTid() {
		return tid;
	}

	/**
	 * @param tid 要设置的 套打模板Id。
	 */
	public void setTid(String tid) {
		this.tid = tid;
	}
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
	public Long getPageno() {
		return pageno;
	}

	/**
	 * @param pageno 要设置的 页码。
	 */
	public void setPageno(Long pageno) {
		this.pageno = pageno;
	}
	/**
	 * @return 返回 左边距。
	 */
	public Long getPaddingleft() {
		return paddingleft;
	}

	/**
	 * @param paddingleft 要设置的 左边距。
	 */
	public void setPaddingleft(Long paddingleft) {
		this.paddingleft = paddingleft;
	}
	/**
	 * @return 返回 上边距。
	 */
	public Long getPaddingtop() {
		return paddingtop;
	}

	/**
	 * @param paddingtop 要设置的 上边距。
	 */
	public void setPaddingtop(Long paddingtop) {
		this.paddingtop = paddingtop;
	}
	/**
	 * @return 返回 页地址。
	 */
	public String getPagepath() {
		return pagepath;
	}

	/**
	 * @param pagepath 要设置的 页地址。
	 */
	public void setPagepath(String pagepath) {
		this.pagepath = pagepath;
	}
	/**
	 * @return 返回 附件。
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename 要设置的 附件。
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * @return 返回 正文内容。
	 */
	public String getPagecontent() {
		return pagecontent;
	}

	/**
	 * @param pagecontent 要设置的 正文内容。
	 */
	public void setPagecontent(String pagecontent) {
		this.pagecontent = pagecontent;
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
