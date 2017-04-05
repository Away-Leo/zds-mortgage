package com.zdsoft.finance.risk.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HuifaRequestVo.java
 * @Package com.zdsoft.finance.risk.vo
 * @Description: 汇法网请求数据
 * @author panshm
 */
public class HuifaRequestVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 类型：1:个人/2:企业
	 */
	private Integer stype;

	/**
	 * 姓名/企业名称
	 */
    private String name;

	/**
	 * 身份证/组织机构代码
	 */
    private String code;

	/**
	 * 导航参数
	 */
    private String properValue;

	/**
	 * 请求次数
	 */
    private Integer reqTimes;

	/**
	 * 删除标识：N:未删除/Y:已删除
	 */
    private String deleted;

	/**
	 * 创建人ID
	 */
    private String creatorId;

	/**
	 * 创建时间
	 */
    private Date createTime;

	/**
	 * 修改人ID
	 */
    private String updaterId;

	/**
	 * 修改时间
	 */
    private Date updateTime;

	/**
	 * IP地址
	 */
    private String ip;
	
	/**
	 * 请求结果数据
	 */
	private String bizId;
	
	/**
	 * 成功状态;s:成功
	 */
	private String success;

	/**
	 * 是否有诉讼
	 */
	private String hasLawsuit;

	/**
	 * 接口调用状态
	 */
	private String statusStr;

	/**
	 * 涉诉信息条数
	 */
	private Integer totalNumber;
	
	/**
	 * 申请人（企业汇法数据时本字段必须有值）
	 */
	private String applicant;
	
	/**
	 * 对应的结果ID
	 */
	private String resultId;

	public Integer getStype() {
		return stype;
	}

	public void setStype(Integer stype) {
		this.stype = stype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProperValue() {
		return properValue;
	}

	public void setProperValue(String properValue) {
		this.properValue = properValue;
	}

	public Integer getReqTimes() {
		return reqTimes;
	}

	public void setReqTimes(Integer reqTimes) {
		this.reqTimes = reqTimes;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(String updaterId) {
		this.updaterId = updaterId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getHasLawsuit() {
		if (totalNumber != null && totalNumber > 0) {
			hasLawsuit = "有";
		} else {
			hasLawsuit = "无";
		}
		return hasLawsuit;
	}

	public void setHasLawsuit(String hasLawsuit) {
		this.hasLawsuit = hasLawsuit;
	}

	public String getStatusStr() {
		if ("s".equals(success)) {
			statusStr = "成功";
		} else {
			statusStr = "未成功";
		}
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

}
