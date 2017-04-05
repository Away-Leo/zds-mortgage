package com.zdsoft.finance.marketing.service;

import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.marketing.entity.BeforehandApply;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.entity.TerminalCaseApprovalOpinion;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BeforehandApplyService.java 
 * @ClassName: BeforehandApplyService 
 * @Description: 案件预申请服务类
 * @author zhoushichao 
 * @date 2017年3月14日 下午7:33:18 
 * @version V1.0
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
	 * @Title: saveOrUpdateAppBeforehandApply 
	 * @Description: 保存App接口营销申请
	 * @author zhoushichao 
	 * @param beforehandApply 营销申请
	 * @return
	 * @throws Exception
	 */
	public BeforehandApply saveOrUpdateAppBeforehandApply(BeforehandApply beforehandApply) throws Exception;
	/**
	 * 
	 * 通过案件id查询案件欲申请
	 *
	 * @author xj
	 * @param caseApplyId
	 * @return
	 */
	public BeforehandApply findByCaseApplyId(String caseApplyId);
	
	/**
	 * 
	 * @Title: saveOrUpdateTerminalCaseApply 
	 * @Description: 保存终端进件编辑信息
	 * @author xiongpan
	 * @param beforehandApply 终端进件的预申请
	 * @param mainCustomer 主借人
	 * @param houseProperty 房产
	 * @param terminalCaseApprovalOpinion 终端进件的审批意见
	 * @param submitStatus 提交状态
	 * @return
	 */
	public Map<String, Object> saveOrUpdateTerminalCaseApply(BeforehandApply beforehandApply,
			BeforePersonalCustomer mainCustomer, HouseProperty houseProperty,
			TerminalCaseApprovalOpinion terminalCaseApprovalOpinion, Boolean submitStatus) throws Exception;
	
	/**
	 * 
	 * @Title: checkLoanTerm 
	 * @Description: 检查贷款期限是否在产品期限范围内
	 * @author yangjia 
	 * @param productSubtypeId子产品id
	 * @param applyTerm贷款期限
	 * @return
	 */
	public Long checkLoanTerm(String productSubtypeId,int applyTerm);
	
	/**
	 * 
	 * @Title: firstApprovalDetermine 
	 * @Description: 一审规则校验
	 * @author jingyh 
	 * @param caseApply
	 * @param busiForm
	 * @return
	 * @throws Exception
	 */
	public Boolean firstApprovalDetermine(CaseApply caseApply,BusiForm busiForm) throws Exception;

}
