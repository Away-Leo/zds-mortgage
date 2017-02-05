package com.zdsoft.finance.casemanage.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.loan.entity.ReviewInformation;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: ReviewInformation.java 	
* @Package com.zdsoft.finance.loan.entity 	
* @Description: 案件管理：财务  合规复核中的复核信息	
* @author liuhuan 	
* @date 2017年1月17日 下午10:12:52 	
* @version V1.0 	
*/
public class ReviewInformationVo extends BaseVo<ReviewInformation>{

	private static final long serialVersionUID = 2095803214467357824L;

	/**
	 * 一级标示
	 */
	private String firstMark;
	
	/**
	 * 二级标示
	 */
	private String secondMark;
	
	/**
	 * 三级标示
	 */
	private String thirdMark;
	
	/**
	 * 责任人
	 */
	private String personLiable;

	public ReviewInformationVo() {
		super();
	}
	
	public ReviewInformationVo(ReviewInformation po) {
		super(po);
	}
	
	public ReviewInformation toPo(){
		ReviewInformation po = new ReviewInformation();
		return super.toPo(this, po);
	}
	
	public String getFirstMark() {
		return firstMark;
	}

	public void setFirstMark(String firstMark) {
		this.firstMark = firstMark;
	}

	public String getSecondMark() {
		return secondMark;
	}

	public void setSecondMark(String secondMark) {
		this.secondMark = secondMark;
	}

	public String getThirdMark() {
		return thirdMark;
	}

	public void setThirdMark(String thirdMark) {
		this.thirdMark = thirdMark;
	}

	public String getPersonLiable() {
		return personLiable;
	}

	public void setPersonLiable(String personLiable) {
		this.personLiable = personLiable;
	}
	
}

