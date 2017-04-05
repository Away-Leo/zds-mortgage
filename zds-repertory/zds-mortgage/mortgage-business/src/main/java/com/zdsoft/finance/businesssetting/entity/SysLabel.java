package com.zdsoft.finance.businesssetting.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title SysLabel.java
 * @className SysLabel
 * @description 标签设置域对象
 * @author LiaoGuoWei
 * @create 2017/2/27 20:34
 * @version V1.0
 **/
@Entity
@Table(name="buss_labelset")
public class SysLabel extends BaseEntity{
	private static final long serialVersionUID = -6075266942139101516L;
	/**
	 * 标签所属分类
	 */
	@Column(length = 20)
	private String labelType;

	/**
	 * 标签名称
	 */
	@Column(length = 64)
	private String labelName;

	/**
	 * 取值方式
	 */
	@Column(length = 20)
	private String valueMethod;

	/**
	 * 最大显示字数
	 */
	@Column
	private Integer maxDisplayNum;

	/**
	 * 状态
	 */
	@Column(length = 1)
	private String labelStatus;

	/**
	 * 取值sql内容
	 */
	@Column(length = 2000)
	private String valueSQL;

	/**
	 * 应用场景
	 */
	@Column(length = 1)
	private String sceneType;

	public String getSceneType() {
		return sceneType;
	}

	public void setSceneType(String sceneType) {
		this.sceneType = sceneType;
	}

	public String getLabelType() {
		return labelType;
	}

	public void setLabelType(String labelType) {
		this.labelType = labelType;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getValueMethod() {
		return valueMethod;
	}

	public void setValueMethod(String valueMethod) {
		this.valueMethod = valueMethod;
	}

	public Integer getMaxDisplayNum() {
		return maxDisplayNum;
	}

	public void setMaxDisplayNum(Integer maxDisplayNum) {
		this.maxDisplayNum = maxDisplayNum;
	}

	public String getLabelStatus() {
		return labelStatus;
	}

	public void setLabelStatus(String labelStatus) {
		this.labelStatus = labelStatus;
	}

	public String getValueSQL() {
		return valueSQL;
	}

	public void setValueSQL(String valueSQL) {
		this.valueSQL = valueSQL;
	}
}
