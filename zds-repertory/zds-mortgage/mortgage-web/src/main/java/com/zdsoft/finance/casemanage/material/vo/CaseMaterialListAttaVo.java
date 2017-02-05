package com.zdsoft.finance.casemanage.material.vo;

import com.zdsoft.finance.casemanage.material.entity.CaseMaterialListAtta;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.VoUtil;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseMaterialListAttaVo.java
 * @Package:com.zdsoft.finance.casemanage.material.vo
 * @Description:案件资料清单附件Vo
 * @author: gonght
 * @date:2017年1月15日 下午4:59:07
 * @version:v1.0
 */
public class CaseMaterialListAttaVo extends BaseVo<CaseMaterialListAtta> {
	
	public static void main(String args[]) { 
		String fString  = "rhzysfz+身份证附件测试.jpg";
		String[] fl = fString.split("[+]");
		System.out.println(fl.length);
	}

	private static final long serialVersionUID = -7918694153552306090L;

	/**
	 * 关联案件资料清单
	 */
	private String caseMaterialListId;

	/**
	 * 是否定位
	 */
	private String position;

	/**
	 * 定位地址
	 */
	private String positionPath;

	/**
	 * 来源
	 */
	private String source;

	/**
	 * 附件.Id
	 */
	private String attachmentId;

	/**
	 * 附件.文件名(冗余)
	 */
	private String attachmentName;

	/**
	 * 附件.上传人Code(冗余)
	 */
	private String operatorCd;

	/**
	 * 附件.上传人名称(冗余)
	 */
	private String operatorNm;

	/**
	 * 附件.上传时间(冗余)
	 */
	private Long operatorTime;

	/**
	 * 下载汇总数
	 */
	private Long downCount;

	/**
	 * 下载最近一次时间
	 */
	private Long lastDownTime;

	/**
	 * 业务表单id
	 */
	private String businessId;

	/**
	 * 附件状态(非正式、正式)
	 */
	private String state;

	/**
	 * 资料类别code
	 */
	private String materiaCode;

	/**
	 * 资料类型名称
	 */
	private String materiaName;

	/**
	 * 产品子类Id
	 */
	private String caseApplyId;

	public String getCaseMaterialListId() {
		return caseMaterialListId;
	}

	public void setCaseMaterialListId(String caseMaterialListId) {
		this.caseMaterialListId = caseMaterialListId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPositionPath() {
		return positionPath;
	}

	public void setPositionPath(String positionPath) {
		this.positionPath = positionPath;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getOperatorCd() {
		return operatorCd;
	}

	public void setOperatorCd(String operatorCd) {
		this.operatorCd = operatorCd;
	}

	public String getOperatorNm() {
		return operatorNm;
	}

	public void setOperatorNm(String operatorNm) {
		this.operatorNm = operatorNm;
	}

	public Long getOperatorTime() {
		return operatorTime;
	}

	public void setOperatorTime(Long operatorTime) {
		this.operatorTime = operatorTime;
	}

	public Long getDownCount() {
		return downCount;
	}

	public void setDownCount(Long downCount) {
		this.downCount = downCount;
	}

	public Long getLastDownTime() {
		return lastDownTime;
	}

	public void setLastDownTime(Long lastDownTime) {
		this.lastDownTime = lastDownTime;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMateriaCode() {
		return materiaCode;
	}

	public void setMateriaCode(String materiaCode) {
		this.materiaCode = materiaCode;
	}

	public String getMateriaName() {
		return materiaName;
	}

	public void setMateriaName(String materiaName) {
		this.materiaName = materiaName;
	}

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public CaseMaterialListAttaVo() {

	}

	public CaseMaterialListAttaVo(CaseMaterialListAtta atta, String[] args, String[] simpleArgs) throws Exception {
		VoUtil.copyPoperties(atta, this, false, args, simpleArgs);
	}

	public CaseMaterialListAtta toPo() throws Exception {
		CaseMaterialListAtta t = new CaseMaterialListAtta();
		VoUtil.copyPoperties(this, t, true);
		return t;
	}
}
