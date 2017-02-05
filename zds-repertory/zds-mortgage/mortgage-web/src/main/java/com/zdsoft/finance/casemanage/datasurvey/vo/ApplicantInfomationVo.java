package com.zdsoft.finance.casemanage.datasurvey.vo;

import java.util.List;

import com.zdsoft.finance.customer.vo.BeforeAddressVo;
import com.zdsoft.finance.customer.vo.BeforeContactVo;
import com.zdsoft.finance.customer.vo.BeforePersonalCustomerVo;
import com.zdsoft.finance.customer.vo.BeforeWorkUnitVo;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:ApplicantInfomationVo.java
 * @Package:com.zdsoft.finance.casemanage.datasurvey.vo
 * @Description:客户信息VO
 * @author: laijun
 * @date:2017年1月16日 上午10:26:44
 * @version:v1.0
 */
public class ApplicantInfomationVo {
	/**
	 * 案件Id
	 */
	private String caseApplyId;
	/**
	 * 客户Vo
	 */
	private BeforePersonalCustomerVo persCustomerVo;
	/**
	 * 户籍地址Vo
	 */
	private BeforeAddressVo beforeAddressVo;
	/**
	 * 家庭地址Vo
	 */
	private BeforeAddressVo homeAddressVo;
	/**
	 * 联系方式Vos
	 */
	private List<BeforeContactVo> beforeContactVos;
	/**
	 * 工作单位Vos
	 */
	private List<BeforeWorkUnitVo> beforeWorkUnitVos;

	public BeforePersonalCustomerVo getPersCustomerVo() {
		return persCustomerVo;
	}

	public void setPersCustomerVo(BeforePersonalCustomerVo persCustomerVo) {
		this.persCustomerVo = persCustomerVo;
	}

	public BeforeAddressVo getBeforeAddressVo() {
		return beforeAddressVo;
	}

	public void setBeforeAddressVo(BeforeAddressVo beforeAddressVo) {
		this.beforeAddressVo = beforeAddressVo;
	}

	public BeforeAddressVo getHomeAddressVo() {
		return homeAddressVo;
	}

	public void setHomeAddressVo(BeforeAddressVo homeAddressVo) {
		this.homeAddressVo = homeAddressVo;
	}

	public List<BeforeContactVo> getBeforeContactVos() {
		return beforeContactVos;
	}

	public void setBeforeContactVos(List<BeforeContactVo> beforeContactVos) {
		this.beforeContactVos = beforeContactVos;
	}

	public List<BeforeWorkUnitVo> getBeforeWorkUnitVos() {
		return beforeWorkUnitVos;
	}

	public void setBeforeWorkUnitVos(List<BeforeWorkUnitVo> beforeWorkUnitVos) {
		this.beforeWorkUnitVos = beforeWorkUnitVos;
	}

	// 设置工作单位
	@SuppressWarnings("unchecked")
	public void setWorkUnitInfos(String workUnitInfos) {
		if (ObjectHelper.isNotEmpty(workUnitInfos)) {
			JSONArray workUnitInfoArray = JSONArray.fromObject(workUnitInfos);
			this.beforeWorkUnitVos = JSONArray.toList(workUnitInfoArray, new BeforeWorkUnitVo(), new JsonConfig());
		}
	}

	// 设置联系方式
	@SuppressWarnings("unchecked")
	public void setContactWayInfos(String contactWayInfos) {
		if (ObjectHelper.isNotEmpty(contactWayInfos)) {
			JSONArray contactWayInfoArray = JSONArray.fromObject(contactWayInfos);

			this.beforeContactVos = JSONArray.toList(contactWayInfoArray, new BeforeContactVo(), new JsonConfig());
		}
	}

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

}
