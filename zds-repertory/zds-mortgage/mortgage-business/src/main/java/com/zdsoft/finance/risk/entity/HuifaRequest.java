package com.zdsoft.finance.risk.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HuifaRequest.java
 * @Package com.zdsoft.finance.risk.entity
 * @Description: 汇法网请求数据
 * @author panshm
 */
@Entity
@Table(name = "T_HUIFA_REQUEST")
public class HuifaRequest extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 汇法请求类型：1：个人
	 */
	public static final Integer STYPE_PERSONAL = 1;

	/**
	 * 汇法请求类型：2：企业
	 */
	public static final Integer STYPE_COMPANY = 2;

	/**
	 * 类型：1:个人/2:企业
	 */
	@Column
	private Integer stype;

	/**
	 * 姓名/企业名称
	 */
	@Column(length=100)
    private String name;

	/**
	 * 身份证/组织机构代码
	 */
	@Column(length=100)
    private String code;

	/**
	 * 导航参数
	 */
	@Column(name="proper_value", length=15)
    private String properValue;

	/**
	 * 请求次数
	 */
	@Column(name="req_times")
    private Integer reqTimes;

	/**
	 * 删除标识：N:未删除/Y:已删除
	 */
    @Column
    private String deleted;

	/**
	 * 创建人ID
	 */
	@Column(name="creator_id", length=36)
    private String creatorId;

	/**
	 * 创建时间
	 */
	@Column(name="create_time")
	@Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

	/**
	 * 修改人ID
	 */
	@Column(name="updater_id", length=36)
    private String updaterId;

	/**
	 * 修改时间
	 */
	@Column(name="update_time")
	@Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

	/**
	 * IP地址
	 */
	@Column(length=20)
    private String ip;

	/**
	 * 业务ID
	 */
	@Column(name="biz_id", length=36)
	private String bizId;

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

}
