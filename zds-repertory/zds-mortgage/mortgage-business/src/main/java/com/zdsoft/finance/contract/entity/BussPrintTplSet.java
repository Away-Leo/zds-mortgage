
package com.zdsoft.finance.contract.entity;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * <版权所有：重庆正大华日软件有限公司
 * 
 * Title: 泛华信贷系统
 *
 * Description: BUSS_PRINT_TPL_SET entity
 *
 * @author wolfhuang
 * @version 1.0
 */

@Entity
@Table(name = "BUSS_PRINT_TPL_SET")
public class BussPrintTplSet extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = -3307895529384707655L;

	/** 模版名称 */
	@Column(length = 32)
	private String templatename;

	/** 所属机构 */
	@Column(length = 32)
	private String beloworgcode;



	/** 资金来源  */
	@Column(length = 32)
	private String fundsource;

	/** 是否停用 */
	@Column(length = 1)
	private String isdisable;



	/**
	 * @return 返回 。
	 */

	public String getTemplatename() {

		return templatename;
	}

	/**
	 * @param templatename
	 *            要设置的 。
	 */
	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}

	/**
	 * @return 返回 。
	 */

	public String getBeloworgcode() {
		return beloworgcode;
	}

	/**
	 * @param beloworgcode
	 *            要设置的 。
	 */
	public void setBeloworgcode(String beloworgcode) {
		this.beloworgcode = beloworgcode;
	}



	/**
	 * @return 返回 。
	 */

	public String getFundsource() {
		return fundsource;
	}

	/**
	 * @param fundsource
	 *            要设置的 。
	 */
	public void setFundsource(String fundsource) {
		this.fundsource = fundsource;
	}

	/**
	 * @return 返回 。
	 */

	public String getIsdisable() {
		return isdisable;
	}

	/**
	 * @param isdisable
	 *            要设置的 。
	 */
	public void setIsdisable(String isdisable) {
		this.isdisable = isdisable;
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
