package com.zdsoft.finance.casemanage.dealpledge.notarize.vo;

import com.zdsoft.finance.casemanage.dealpledge.notarize.entity.Notarize;
import com.zdsoft.finance.common.base.BaseVo;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:NotarizeVo.java
 * @Package:com.zdsoft.finance.casemanage.dealpledge.notarize.vo
 * @Description:公证VO对象
 * @author: caixiekang
 * @date:2017年1月15日 下午5:08:20
 * @version:v1.0
 */
public class NotarizeVo extends BaseVo<Notarize> {

	/**
	 * 案件  多对一 
	 * 
	 */
	private String caseApplyId;
	
	/**
	 * 公证类型
	 */
	private String notarizeType;
	
   /**
     * 公证类型
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
	private String mo;


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

	public String getMo() {
		return mo;
	}

	public void setMo(String mo) {
		this.mo = mo;
	}
	
	public String getNotarizeTypeName() {
        return notarizeTypeName;
    }

    public void setNotarizeTypeName(String notarizeTypeName) {
        this.notarizeTypeName = notarizeTypeName;
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