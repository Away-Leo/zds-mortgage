package com.zdsoft.finance.houseassessment.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/** 
* 版权所有：重庆正大华日软件有限公司
* @Title: HouseEvaluateQueryVo.java 
* @Package com.zdsoft.finance.houseassessment.vo 
* @Description: 房产评估查询Vo
* @author jingyh 
* @date 2017年3月10日 下午5:47:09 
* @version V1.0 
*/
public class HouseEvaluateQueryVo implements Serializable {

	private static final long serialVersionUID = -4598127758269325329L;

	/**
	 * 房产地址
	 */
	private String houseAddress;
	
	/**
	 * 评估总价
	 */
	private BigDecimal sumPrice;
	
	/**
	 * 评估价
	 */
	private BigDecimal evaluatePrice;
	
	/**
	 * 评估价千分位
	 */
	private String evaluatePriceStr;
	
	/**
	 * 请求时间
	 */
	private Date createTime;
	
	/**
	 * 请求时间字符串
	 */
	private String createTimeStr;
	
	/**
	 * 评估公司名称
	 */
	private String evaluateCompany;
	
	/**
	 * 返回时间
	 */
	private Date resultTime;
	
	/**
	 * 返回时间字符串
	 */
	private String resultTimeStr;
	
	/**
	 * 处理标示：0：人工；1：自动
	 */
	private Long onlineFlg;
	
	/**
	 * 评估机构Id
	 */
	private String evaluateId;

	public String getHouseAddress() {
		return houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	public BigDecimal getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(BigDecimal sumPrice) {
		this.sumPrice = sumPrice;
	}

	public BigDecimal getEvaluatePrice() {
		return evaluatePrice;
	}

	public void setEvaluatePrice(BigDecimal evaluatePrice) {
		this.evaluatePrice = evaluatePrice;
	}

	public String getEvaluatePriceStr() {
		return evaluatePriceStr;
	}

	public void setEvaluatePriceStr(String evaluatePriceStr) {
		this.evaluatePriceStr = evaluatePriceStr;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateTimeStr() {
		if (ObjectHelper.isEmpty(this.createTimeStr) && ObjectHelper.isNotEmpty(this.createTime)) {
			return DateHelper.dateToString(this.createTime, DateHelper.DATE_WITHSECOND_CHN_FORMAT);
		}
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getEvaluateCompany() {
		return evaluateCompany;
	}

	public void setEvaluateCompany(String evaluateCompany) {
		this.evaluateCompany = evaluateCompany;
	}

	public Date getResultTime() {
		return resultTime;
	}

	public void setResultTime(Date resultTime) {
		this.resultTime = resultTime;
	}

	public String getResultTimeStr() {
		if (ObjectHelper.isEmpty(this.resultTimeStr) && ObjectHelper.isNotEmpty(this.resultTime)) {
			return DateHelper.dateToString(this.resultTime, DateHelper.DATE_WITHSECOND_CHN_FORMAT);
		}
		return resultTimeStr;
	}

	public void setResultTimeStr(String resultTimeStr) {
		this.resultTimeStr = resultTimeStr;
	}

	public Long getOnlineFlg() {
		return onlineFlg;
	}

	public void setOnlineFlg(Long onlineFlg) {
		this.onlineFlg = onlineFlg;
	}

	public String getEvaluateId() {
		return evaluateId;
	}

	public void setEvaluateId(String evaluateId) {
		this.evaluateId = evaluateId;
	}
}
