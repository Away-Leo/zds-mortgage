

package com.zdsoft.finance.contract.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.zdsoft.framework.core.common.domain.BaseEntity;


 
/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BussPrintTplProduct.java
 * @Package:com.zdsoft.finance.contract.entity
 * @Description:套打模板适用产品分类实体
 * @author: huangdongkui
 * @date:Mar 6, 2017 10:52:30 AM
 * @version:v1.0
 */
@Entity
@Table(name = "BUSS_PRINT_TPL_PRODUCT")
public class BussPrintTplProduct extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = -3307895529384707655L;
	

    
	/** 产品Code */
    @Column(length=32)
	private String productcode;
    
    
	/** 模板ID */
    @Column(length=32)
	private String printtemplateid;
    

	/**
	 * @return 返回 产品Code。
	 */

	public String getProductcode() {
		return productcode;
	}

	/**
	 * @param productcode 要设置的 产品Code。
	 */
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	/**
	 * @return 返回 模板ID。
	 */

	public String getPrinttemplateid() {
		return printtemplateid;
	}

	/**
	 * @param printtemplateid 要设置的 模板ID。
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
