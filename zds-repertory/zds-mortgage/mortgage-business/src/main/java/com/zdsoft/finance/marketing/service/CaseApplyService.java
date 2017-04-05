package com.zdsoft.finance.marketing.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseApplyService.java 
 * @ClassName: CaseApplyService 
 * @Description: 案件服务类
 * @author zhoushichao 
 * @date 2017年3月14日 下午7:33:58 
 * @version V1.0
 */
public interface CaseApplyService extends BaseService<CaseApply> {
	
	/** 案件特批管理列表
	 * @param pageable
	 * @param queryObjs
	 * @return
	 */
	public Page<Map<String, Object>> findPageCaseSpecialApprove(Pageable pageable, List<QueryObj> queryObjs) throws Exception;

	/**
	 * 
	 * @Title: saveOrUpdateCaseApply 
	 * @Description: 保存案件信息
	 * @author zhoushichao 
	 * @param caseApply  案件信息
	 * @return
	 * @throws Exception
	 */
	public CaseApply saveOrUpdateCaseApply(CaseApply caseApply) throws Exception;

	/**
	 * 
	 * @Title: findPageBeforehandApply 
	 * @Description: 分页 查询营销登记信息
	 * @author zhoushichao 
	 * @param pageable 分页信息
	 * @param queryObjs  参数信息
	 * @return
	 * @throws Exception
	 */
    public Page<Map<String,Object>> findPageBeforehandApply(Pageable pageable, List<QueryObj> queryObjs) throws Exception;
    
    /**
     * 
     * @Title: findPageCaseApplyAndCommission 
     * @Description: 支佣案件数据列表
     * @author xiangjx 
     * @param pageable
     * @param queryObjs
     * @return
     * @throws Exception
     */
    public Page<Map<String,Object>> findPageCaseApplyAndCommission(Pageable pageable, List<QueryObj> queryObjs) throws Exception;
    /**
     * 
     * @Title: findPageCaseApplyAndDetail 
     * @Description: 用于财务管理》》收款单和收费明细查询
     * @author xiangjx 
     * @param pageable
     * @param queryObjs
     * @return
     * @throws Exception
     */
    public Page<Map<String,Object>> findPageCaseApplyAndDetail(Pageable pageable, List<QueryObj> queryObjs) throws Exception;
    /**
     * 
     * @Title: findPageCaseApply 
     * @Description: 分页案件查询信息
     * @author zhoushichao 
     * @param pageable 分页信息
	 * @param queryObjs  参数信息
	 * @param dtOperPermDto  查看权限
     * @return
     * @throws Exception
     */
	public Page<Map<String, Object>> findPageCaseApply(Pageable pageable, List<QueryObj> queryObjs,DataOperPermDto dtOperPermDto) throws Exception;
	
	/**
	 * 案件额度申请分页查询信息
	 * @param pageable	分页信息
	 * @param queryObjs	查询条件
	 * @return	Page<Map> 信息
	 * @throws BusinessException 
	 */

	public Page<Map<String, Object>> findPageCaseLimitApply(Pageable pageable, List<QueryObj> queryObjs, DataOperPermDto dtOperPermDto) throws BusinessException;
	
	/**
	 * 案件预约分页查询信息
	 * @param pageable
	 * @param queryObjs
	 * @return
	 * @throws Exception 
	 */
	public Page<Map<String, Object>> findPageAppointment(PageRequest pageable, List<QueryObj> queryObjs, DataOperPermDto dtOperPermDto) throws Exception;

	/**
	 * 获取案件预约客户(即案件的主借人,所有担保人)
	 * @return
	 */
	public List<Map<String, Object>> appointmentClient(String id);

	/**
	 * 案件面签分页查询信息
	 * @param pageable
	 * @param queryObjs
	 * @return
	 * @throws Exception 
	 */
	public Page<Map<String, Object>> findPageInterview(PageRequest pageable, List<QueryObj> queryObjs,DataOperPermDto dataOperPermDto ) throws Exception;

	/**
	 * 查询押品相关信息
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public List<Map<String, Object>> queryHouseInfo(String id) throws Exception;

	/**
	 * 
	 * @Title: findPageTerminalCase 
	 * @Description: 终端进件分页查询信息
	 * @author xiongpan
	 * @param pageable 分页信息
	 * @param queryObjs 查询条件
	 * @return
	 * @throws Exception
	 */
	public Page<Map<String, Object>> findPageTerminalCase(PageRequest pageable, List<QueryObj> queryObjs) throws Exception;
	
	/**
	 * 
	 * @Title: findPageCaseApplyBasicInforList 主借人信息先寻找贷后客户，如果没有查找贷前客户
	 * @Description: 分页查询案件基本信息（案件号，主借人，证件号码，产品分类，子产品，申请金额，当前处理人，当前节点，案件状态，拓展经理，资信员，申请时间）
	 * @author xj 
	 * @param pageable
	 * @param queryObjs
	 * @param dtOperPermDto
	 * @return
	 * @throws Exception
	 */
	public Page<Map<String, Object>> findPageCaseApplyBasicInforList(PageRequest pageable, List<QueryObj> queryObjs, DataOperPermDto dtOperPermDto) throws Exception;

	/**
	 * 
	 * @Title: findRelationCaseInfos 
	 * @Description: 查询指定客户关联的案件信息
	 * @author jingyh 
	 * @param pageable
	 * @param customerId
	 * 				客户Id
	 * @param exceptCaseIds
	 * 				排除的案件Id
	 * @return
	 * @throws Exception 
	 */
	public Page<Map<String, Object>> findRelationCaseInfos(Pageable pageable,String customerId,List<String> exceptCaseIds) throws Exception;

	/**
	  * @Title: findByCaseApplyCode
	  * @Description: 通过案件编号查询案件
	  * @author liaoguowei
	  * @param caseApplyCode
	  * @return CaseApply
	  * @throws BusinessException
	*/
	public CaseApply findByCaseApplyCode(String caseApplyCode) throws BusinessException;
	
	
	/**
     * 
     * @Title: findCaseApplyByCondition 
     * @Description: 查询案件的基础信息 app使用公共接口
     * @author dengyy 
     * @param caseApplyId 案件id
     * @return
     * @throws Exception
     */
    public  Map<String,String> findCaseApplyByCondition(String caseApplyId) throws Exception ;

    /**
     * @Title: findByStage 
     * @Description: 根据案件状态获取案件
     * @author jincheng 
     * @param stage
     * @return
     */
	public List<CaseApply> findByStage(String stage);
	/**
	 * 废弃贷款申请
	 * @Title: discardApply 
	 * @author jingjiyan 
	 * @param caseApplyId
	 * 			案件id
	 */
	public void discardApply(String caseApplyId);
	
	/**
	 * 
	 * @Title: changeCaseStage 
	 * @Description: 根据案件Id改变案件状态
	 * @author caixiekang 
	 * @param caseApplyId 案件id
	 * @param stage 案件状态(枚举类维护CaseApplyStageSimpleCodeEnum)
	 * @return
	 * @throws Exception
	 */
	public CaseApply changeCaseStage(String caseApplyId, String stage) throws Exception;

	/**
	 * 
	 * @Title: findPageMonitorRecord 
	 * @Description:  条件查询案件列表（贷中监控）,放款之后的
	 * @author xj 
	 * @param pageable
	 * @param queryObjs
	 * @param controlType 监控类型
	 * @return
	 * @throws Exception
	 */
	public Page<Map<String, Object>> findPageMonitorRecord(PageRequest pageable, List<QueryObj> queryObjs,
			String controlType) throws Exception;
	
	/**
	 * 
	 * @Title: findMonitorRecordByCaseApplyId 
	 * @Description: 根据案件id查询监控需要的信息
	 * @author xj 
	 * @param caseApplyId 案件ID
	 * @param controlType 监控类型
	 * @return
	 */
	public Map<String, Object> findMonitorRecordByCaseApplyId(String caseApplyId, String controlType);
	
	/**
	 * @Title: getCaseApplyCustomerReceivableList 
	 * @Description: 获取案件信息（包括逾期信息）
	 * @author jincheng 
	 * @param pageable
	 * @param queryObjs
	 * @return
	 */
	public Page<Map<String, Object>> getCaseApplyCustomerReceivableList(PageRequest pageable, List<QueryObj> queryObjs,DataOperPermDto dataOperPermDto)throws BusinessException;
}
