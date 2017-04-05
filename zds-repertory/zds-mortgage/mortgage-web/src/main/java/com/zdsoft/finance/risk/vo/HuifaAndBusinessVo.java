package com.zdsoft.finance.risk.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HuifaAndBusinessVo.java 
 * @ClassName: HuifaAndBusinessVo 
 * @Description: 汇法网基础数据Vo
 * @author panshm 
 * @date 2017年2月18日 下午2:48:44 
 * @version V1.0
 */
public class HuifaAndBusinessVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private HuifaRequestVo companyHuifaInfo;
	
	private HuifaRequestVo personalHuifaInfo;
	
	private List<BusinessInfoVo> businessInfoVoList;

	public HuifaRequestVo getCompanyHuifaInfo() {
		return companyHuifaInfo;
	}

	public void setCompanyHuifaInfo(HuifaRequestVo companyHuifaInfo) {
		this.companyHuifaInfo = companyHuifaInfo;
	}

	public HuifaRequestVo getPersonalHuifaInfo() {
		return personalHuifaInfo;
	}

	public void setPersonalHuifaInfo(HuifaRequestVo personalHuifaInfo) {
		this.personalHuifaInfo = personalHuifaInfo;
	}

	public List<BusinessInfoVo> getBusinessInfoVoList() {
		return businessInfoVoList;
	}

	public void setBusinessInfoVoList(List<BusinessInfoVo> businessInfoVoList) {
		this.businessInfoVoList = businessInfoVoList;
	}

	public HuifaAndBusinessVo(){}
	
//	public HuifaInfoVo(QuestionSceneQuery query){
//		BeanUtils.copyProperties(query, this);
//	}
//	
//	public QuestionSceneQuery toPo(){
//		QuestionSceneQuery questionSceneQuery=new QuestionSceneQuery();
//		BeanUtils.copyProperties(this, questionSceneQuery);
//		return questionSceneQuery;
//	}
}
