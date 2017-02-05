package com.zdsoft.finance.marketing.service;

import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.marketing.entity.BeforehandApply;
import com.zdsoft.finance.marketing.entity.HouseProperty;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BeforehandApplyService.java
 * @Package:com.zdsoft.finance.marketing.service
 * @Description:案件预申请服务类
 * @author: zhoushichao
 * @date:2017年1月10日 下午9:49:27
 * @version:v1.0
 */
public interface BeforehandApplyService extends BaseService<BeforehandApply>{
	/**
	 * 案件征信小类类型
	 */
	public static String CREDIT_CLASS = "maclass_warrant5";

	/**
	 * 保存营销登记
	 * @author zhoushichao
	 * @param beforehandApply 案件预申请
	 * @param mainCustomer 主借人
	 * @param houseProperty 房产
	 * @param creditAttachmentIds 征信附件ids
	 * @param submitStatus 提交状态
	 * @return
	 * @throws Exception 
	 */
	public Map<String,Object> saveOrUpdateBeforehandApply(BeforehandApply beforehandApply, BeforePersonalCustomer mainCustomer,
			 HouseProperty houseProperty,String creditAttachmentIds,Boolean submitStatus) throws Exception;
	/**
	 * 
	 * 通过案件id查询案件欲申请
	 *
	 * @author xj
	 * @param caseApplyId
	 * @return
	 */
	public BeforehandApply findByCaseApplyId(String caseApplyId);

}
