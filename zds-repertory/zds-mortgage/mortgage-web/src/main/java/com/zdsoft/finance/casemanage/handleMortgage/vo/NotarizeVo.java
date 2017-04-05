package com.zdsoft.finance.casemanage.handleMortgage.vo;

import com.zdsoft.finance.casemanage.handleMortgage.entity.Notarize;
import com.zdsoft.finance.common.base.BaseVo;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: NotarizeVo.java 
 * @ClassName: NotarizeVo 
 * @Description: 公证Vo
 * @author zhoushichao 
 * @date 2017年2月21日 下午4:33:45 
 * @version V1.0 
 */ 
public class NotarizeVo extends BaseVo<Notarize> {

	private static final long serialVersionUID = 1L;

	/**
	 * 案件Id
	 * 
	 */
	private String caseApplyId;
	
	/**
	 * 公证类型code
	 */
	private String notarizeType;
	
   /**
     * 公证类型Name
     */
    private String notarizeTypeName;
    
	/**
	 * 公证机关
	 */
	private String notarizeOffice;
	
	/**
	 * 办理公证时间
	 */
	
	private Long notarizeDate;
	
	/**
	 * 预计公证书出具时间
	 */
	
	private Long notarizeProvideDate;
	
	/**
	 * 备注
	 */
	private String remark;


	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getNotarizeType() {
		return notarizeType;
	}

	public void setNotarizeType(String notarizeType) {
		this.notarizeType = notarizeType;
	}

	public String getNotarizeOffice() {
		return notarizeOffice;
	}

	public void setNotarizeOffice(String notarizeOffice) {
		this.notarizeOffice = notarizeOffice;
	}

	public Long getNotarizeDate() {
		return notarizeDate;
	}

	public void setNotarizeDate(Long notarizeDate) {
		this.notarizeDate = notarizeDate;
	}

	public Long getNotarizeProvideDate() {
		return notarizeProvideDate;
	}

	public void setNotarizeProvideDate(Long notarizeProvideDate) {
		this.notarizeProvideDate = notarizeProvideDate;
	}
	public String getNotarizeTypeName() {
        return notarizeTypeName;
    }

    public void setNotarizeTypeName(String notarizeTypeName) {
        this.notarizeTypeName = notarizeTypeName;
    }

    public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public NotarizeVo() {
		super();
	}
	public NotarizeVo(Notarize notarize) {
		super(notarize,null,new String[]{"notarizeType"});
	}
	public Notarize toPo() {
		Notarize notarize = new Notarize();
		return (Notarize)super.toPo(this, notarize);
	}
}