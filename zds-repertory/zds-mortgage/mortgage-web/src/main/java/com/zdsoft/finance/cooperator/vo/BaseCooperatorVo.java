package com.zdsoft.finance.cooperator.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.VoUtil;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: BaseCooperatorVo.java 	
* @Package com.zdsoft.finance.cooperator.vo 	
* @Description: 公共父类 合作方的Vo	
* @author liuhuan 	
* @date 2017年1月20日 下午10:01:10 	
* @version V1.0 	
*/
public class BaseCooperatorVo<T extends BaseEntity> extends BaseVo<T>{

	private static final long serialVersionUID = 2856464940796618471L;
	
	/**
	 * 合作方名称
	 */
	private String cooperatorName;
	/**
	 * 合作方简称
	 */
	private String cooperatorShortName;
	/**
	 * 上级名称
	 */
	private String parentName;
	/**
	 * 合作方类型
	 */
	private String cooperatorType;
	/**
	 * 合作方地址
	 */
	private String cooperatorAddress;
	/**
	 * 地区CODE
	 */
	private String regionCode;
	/**
	 * 成立时间
	 */
	private Long foundDate;
	/**
	 * 法人
	 */
	private String legalPerson;
	/**
	 * 税号
	 */
	private String dutyParagraph;
	/**
	 * 行业
	 */
	private String industry;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否停用
	 */
	private String isStop;
	/**
	 * 网站
	 */
	private String website;
	public String getCooperatorName() {
		return cooperatorName;
	}
	public void setCooperatorName(String cooperatorName) {
		this.cooperatorName = cooperatorName;
	}
	public String getCooperatorShortName() {
		return cooperatorShortName;
	}
	public void setCooperatorShortName(String cooperatorShortName) {
		this.cooperatorShortName = cooperatorShortName;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getCooperatorType() {
		return cooperatorType;
	}
	public void setCooperatorType(String cooperatorType) {
		this.cooperatorType = cooperatorType;
	}
	public String getCooperatorAddress() {
		return cooperatorAddress;
	}
	public void setCooperatorAddress(String cooperatorAddress) {
		this.cooperatorAddress = cooperatorAddress;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public Long getFoundDate() {
		return foundDate;
	}
	public void setFoundDate(Long foundDate) {
		this.foundDate = foundDate;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getDutyParagraph() {
		return dutyParagraph;
	}
	public void setDutyParagraph(String dutyParagraph) {
		this.dutyParagraph = dutyParagraph;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsStop() {
		return isStop;
	}
	public void setIsStop(String isStop) {
		this.isStop = isStop;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public BaseCooperatorVo() {
		super();
	}
	
	/**
	 * 构造器
	 * 
	 * @param t
	 *            对象
	 * @param args
	 *            不复制的参数
	 * @param simpleArgs
	 *            需要转换simpleCode的属性
	 */
	public BaseCooperatorVo(T t, String[] args, String[] simpleArgs) {
		VoUtil.copyPoperties(t, this, false, args, simpleArgs);
	}

	/**
	 * 构造器
	 * 
	 * @param t
	 *            对象
	 * @param args
	 *            不复制的参数
	 * @param simpleArgs
	 *            需要转换simpleCode的属性
	 */
	public BaseCooperatorVo(T t, String[] args, String[] simpleArgs, String[] dateArgs) {
		VoUtil.copyPoperties(t, this, false, args, simpleArgs, dateArgs);
	}

	/**
	 * 构造器
	 * 
	 * @param t
	 *            对象
	 * @param args
	 *            不复制的参数
	 */
	public BaseCooperatorVo(T t, String[] args) {
		VoUtil.copyPoperties(t, this, false, args);
	}

	/**
	 * 构造器
	 * 
	 * @param t
	 *            对象
	 */
	public BaseCooperatorVo(T t) {
		VoUtil.copyPoperties(t, this, false);
	}
	
	
}
