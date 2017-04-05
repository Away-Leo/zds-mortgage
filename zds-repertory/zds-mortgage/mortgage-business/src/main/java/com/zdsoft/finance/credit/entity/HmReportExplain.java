package com.zdsoft.finance.credit.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HmReportExplain.java
 * @ClassName: HmReportExplain
 * @Description: 报告说明
 * @author gufeng
 * @date 2017年2月23日 上午9:43:55
 * @version V1.0
 */
@Entity
@Table(name = "hm_Report_Explain")
public class HmReportExplain extends BaseEntity {

	private static final long serialVersionUID = 1526110875990420503L;
	/**
	 * 查询表ID
	 */
	@Column(name = "query_Id")
	private String queryId;
	/**
	 * 身份证号码
	 */
	@Column(name = "id_card")
	private String idCard;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Timestamp createTime;
	/**
	 * 顺序
	 */
	@Column(name = "no")
	private Integer no;
	/**
	 * 声明内容
	 */
	@Column(name = "hm_explain", length = 3000)
	private String explain;

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

}
