package com.zdsoft.finance.casemanage.limitApply.service;

import java.math.BigDecimal;
import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.limitApply.entity.CaseLimitApply;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseLimitApplyService.java 
 * @ClassName: CaseLimitApplyService 
 * @Description: 案件额度申请服务接口
 * @author xj 
 * @date 2017年2月17日 下午2:56:50 
 * @version V1.0
 */
public interface CaseLimitApplyService extends BaseService<CaseLimitApply> {

	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 通过案件id查询额度申请列表（根据创建时间升序）
	 * @author xj 
	 * @param id
	 * @return
	 */
	public List<CaseLimitApply> findByCaseApplyId(String caseApplyId);
	
	/**
	 * 
	 * @Title: findByCaseApplyIdAndEffectiveStatus 
	 * @Description: 通过案件id和额度状态查询
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @param effectiveStatus 状态
	 * @return
	 */
	public List<CaseLimitApply> findByCaseApplyIdAndEffectiveStatus(String caseApplyId,String effectiveStatus);

	/**
	 * 通过信托计划id查询所有额度分配信息
	 * 
	 * @param creditEntrustId
	 *            信托计划id
	 * @param state
	 *            生效状态
	 * @return 额度分配集合
	 * 
	 * @author liuwei
	 */
	public List<CaseLimitApply> findByCreditEntrustId(String creditEntrustId, String state);
	
	/**
	 * 
	 * @Title: saveCaseLimitApply 
	 * @Description: 保存额度申请信息
	 * @author xj 
	 * @return
	 * @throws Exception 
	 */
	public CaseLimitApply saveCaseLimitApply(CaseLimitApply caseLimitApply) throws Exception;
	
	/**
	 * 
	 * @Title: allotCaseLimitApply 
	 * @Description: 分配额度
	 * @author xj 
	 * @param caseLimitApply 
	 * @return
	 * @throws Exception
	 */
	public CaseLimitApply allotCaseLimitApply(CaseLimitApply caseLimitApply) throws Exception;
	
	/**
	 * 
	 * @Title: cancelCaseLimitApply 
	 * @Description: 取消额度
	 * @author xj 
	 * @param caseLimitApplyId 额度id
	 * @throws Exception 
	 */
	public void cancelCaseLimitApply(String caseLimitApplyId) throws Exception;
	
	/**
	 * 
	 * @Title: applyLimit 
	 * @Description: 申请额度 不进行资金计算
	 * @author xj 
	 * @param caseLimitApplyId 申请额度id 用于修改
	 * @param creditEntrustId 资金计划id
	 * @param caseApplyId 案件id
	 * @param applyLimitAmount 申请金额
	 * @return
	 * @throws Exception
	 */
	public CaseLimitApply applyLimit(String caseLimitApplyId,String creditEntrustId,String caseApplyId,BigDecimal applyLimitAmount) throws Exception;
	
	/**
	 * 
	 * @Title: matchLimit 
	 * @Description: 匹配额度 不进行资金计算
	 * @author xj 
	 * @param caseLimitApplyId
	 * @throws Exception
	 */
	public void matchLimit(String caseLimitApplyId) throws Exception;
	
	/**
	 * 
	 * @Title: cancelApplyLimit 
	 * @Description: 取消申请 不进行资金计算
	 * @author xj 
	 * @param caseLimitApplyId  额度申请id
	 * @throws Exception
	 */
	public void cancelApplyLimit(String caseLimitApplyId) throws Exception; 
	
	/**
	 * 
	 * @Title: cancelMatchLimit 
	 * @Description: 取消匹配 不进行资金计算
	 * @author xj 
	 * @param caseLimitApplyId 额度申请id 
	 * @throws Exception
	 */
	public void cancelMatchLimit(String caseLimitApplyId) throws Exception; 
}
