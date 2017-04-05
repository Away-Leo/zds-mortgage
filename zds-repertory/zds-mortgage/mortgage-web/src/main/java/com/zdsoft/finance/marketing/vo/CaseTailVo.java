package com.zdsoft.finance.marketing.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.marketing.entity.CaseTail;
import com.zdsoft.framework.core.common.util.DateHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseTailVo.java 
 * @ClassName: CaseTailVo 
 * @Description: 营销跟踪Vo
 * @author caixiekang 
 * @date 2017年3月4日 上午9:34:27 
 * @version V1.0
 */
public class CaseTailVo extends BaseVo<CaseTail> {
	
	/**   
	 * @Fields serialVersionUID  
	 */ 
	private static final long serialVersionUID = 8529416719743288241L;

	/**
	 * 案件id
	 */
	private String caseApplyId;

	/**
	 * 营销人ID
	 */
	private String marketingPersonId;
	
	/**
	 * 营销人姓名 added by caixiekang
	 */
	private String marketingPersonName;
	
	/**
	 * 跟踪时间 added by caixiekang
	 */
	private Long tailDate;
	/**
	 * 跟踪时间(后台转格式回显示用)
	 */
	@SuppressWarnings("unused")
	private String tailDateView;
	/**
	 * 跟踪内容
	 */
	private String tailContent;
	
	public String getCaseApplyId() {
		return caseApplyId;
	}
	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	public String getMarketingPersonId() {
		return marketingPersonId;
	}
	public void setMarketingPersonId(String marketingPersonId) {
		this.marketingPersonId = marketingPersonId;
	}
	public String getMarketingPersonName() {
		return marketingPersonName;
	}
	public void setMarketingPersonName(String marketingPersonName) {
		this.marketingPersonName = marketingPersonName;
	}
	public Long getTailDate() {
		return tailDate;
	}
	public void setTailDate(Long tailDate) {
		this.tailDate = tailDate;
	}
	public String getTailDateView() {
		//后台转换日期格式
		String tailDateView = DateHelper.longToDate(this.getTailDate(), DateHelper.DATE_SHORT_FORMAT);
		return tailDateView;
		//return tailDateView;
	}
	public void setTailDateView(String tailDateView) {
		this.tailDateView = tailDateView;
	}
	public String getTailContent() {
		return tailContent;
	}
	public void setTailContent(String tailContent) {
		this.tailContent = tailContent;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public CaseTailVo(){
		super();
	}
	
	public CaseTailVo(CaseTail caseTail){
		super(caseTail, null, null);
	}
	
	public CaseTail toPo(){
		CaseTail caseTail = new CaseTail();
		return super.toPo(this, caseTail, new String[]{"tailDateView"});
			
	}
}
