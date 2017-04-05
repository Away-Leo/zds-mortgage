package com.zdsoft.finance.marketing.vo;

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
 * @Title: MarketingBeforehandVo.java 
 * @ClassName: MarketingBeforehandVo 
 * @Description: 营销申请Vo封装
 * @author zhoushichao 
 * @date 2017年3月14日 下午7:49:41 
 * @version V1.0
 */
public class MarketingBeforehandVo {

	/**
	 * 案件预申请Vo
	 */
	private BeforehandApplyVo beforehandApplyVo;
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
	/**
	 * 房产Vo
	 */
	private HousePropertyVo housePropertyVo;
	/**
	 * 抵押情况VoList
	 */
	private List<PledgeInfoVo> pledgeInfoVoList;
	/**
	 * 产权人VoList
	 */
	private List<PropertyOwnerVo> propertyOwnerVoList;
	
	/**
	 * 终端进件审批意见Vo
	 */
	private TerminalCaseApprovalOpinionVo terminalCaseApprovalOpinionVo;
	
	/**
	 * 征信附件id，逗号隔开
	 */
	private String creditAttachmentIds;
	//设置工作单位
	@SuppressWarnings("unchecked")
	public void setWorkUnitInfos(String workUnitInfos){
		if(ObjectHelper.isNotEmpty(workUnitInfos)){
			JSONArray receivablePlanArray = JSONArray.fromObject(workUnitInfos);

			this.beforeWorkUnitVos = JSONArray.toList(receivablePlanArray, new BeforeWorkUnitVo(),
					new JsonConfig());
		}
	}
	//设置联系方式
	@SuppressWarnings("unchecked")
	public void setContactWayInfos(String contactWayInfos){
		if(ObjectHelper.isNotEmpty(contactWayInfos)){
			JSONArray receivablePlanArray = JSONArray.fromObject(contactWayInfos);

			this.beforeContactVos = JSONArray.toList(receivablePlanArray, new BeforeContactVo(),new JsonConfig());
		}
	}
	
	//设置抵押情况
	@SuppressWarnings("unchecked")
	public void setPledgeInfos(String pledgeInfos) {
		if (ObjectHelper.isNotEmpty(pledgeInfos)) {
			JSONArray pledgeInfoArray =JSONArray.fromObject(pledgeInfos);
			this.pledgeInfoVoList = JSONArray.toList(pledgeInfoArray, new PledgeInfoVo(), new JsonConfig());
		}
	}
	//设置产权人情况
	@SuppressWarnings("unchecked")
	public void setPropertyInfos(String propertyInfos) {
		if (ObjectHelper.isNotEmpty(propertyInfos)) {
			JSONArray propertyInfoArray =JSONArray.fromObject(propertyInfos);
			this.propertyOwnerVoList = JSONArray.toList(propertyInfoArray, new PropertyOwnerVo(), new JsonConfig());
		}
	}
	public BeforehandApplyVo getBeforehandApplyVo() {
		return beforehandApplyVo;
	}
	public void setBeforehandApplyVo(BeforehandApplyVo beforehandApplyVo) {
		this.beforehandApplyVo = beforehandApplyVo;
	}
	public HousePropertyVo getHousePropertyVo() {
		return housePropertyVo;
	}
	public void setHousePropertyVo(HousePropertyVo housePropertyVo) {
		this.housePropertyVo = housePropertyVo;
	}
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
	public List<PledgeInfoVo> getPledgeInfoVoList() {
		return pledgeInfoVoList;
	}
	public void setPledgeInfoVoList(List<PledgeInfoVo> pledgeInfoVoList) {
		this.pledgeInfoVoList = pledgeInfoVoList;
	}
	public List<PropertyOwnerVo> getPropertyOwnerVoList() {
		return propertyOwnerVoList;
	}
	public void setPropertyOwnerVoList(List<PropertyOwnerVo> propertyOwnerVoList) {
		this.propertyOwnerVoList = propertyOwnerVoList;
	}
	public String getCreditAttachmentIds() {
		return creditAttachmentIds;
	}
	public void setCreditAttachmentIds(String creditAttachmentIds) {
		this.creditAttachmentIds = creditAttachmentIds;
	}
	public TerminalCaseApprovalOpinionVo getTerminalCaseApprovalOpinionVo() {
		return terminalCaseApprovalOpinionVo;
	}
	public void setTerminalCaseApprovalOpinionVo(TerminalCaseApprovalOpinionVo terminalCaseApprovalOpinionVo) {
		this.terminalCaseApprovalOpinionVo = terminalCaseApprovalOpinionVo;
	}
	
	
	
}
