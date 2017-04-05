package com.zdsoft.finance.busiform.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.MyDraft;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 审批单业务逻辑接口类
 *
 * @author liuwei
 * @version 1.0
 * @date 2016/10/12
 */
public interface BusiFormService extends BaseService<BusiForm>{

	/**
	 * 按照ID查询
	 *
	 * @param id
	 *            ID
	 * @return
	 * @throws BusinessException
	 */
	public BusiForm findById(String id) throws BusinessException;

	/**
	 * 更改状态
	 *
	 * @param busiform
	 *            需要更改的busiform
	 * @param status
	 * @return
	 * @throws BusinessException
	 */
	public BusiForm updateBusiFormStatus(BusiForm busiform, Integer status) throws BusinessException;

	/**
	 * 根据关联业务id以及关联业务类型进行查询
	 *
	 * @param businessEntityId
	 *            关联业务id
	 * @param businessEntityNm
	 *            关联业务类型
	 * @return 审批单
	 */
	BusiForm findByBusinessEntityIdAndBusinessEntityNm(String businessEntityId, String businessEntityNm);

	/**
	 * 保存审批单
	 *
	 * @param busiForm
	 *            审批单
	 * @return 审批单
	 */
	public BusiForm saveBusiForm(BusiForm busiForm) throws Exception;

	/**
	 * 修改审批单
	 *
	 * @param busiForm
	 *            审批单
	 * @return 审批单
	 */
	public BusiForm updateBusiForm(BusiForm busiForm) throws BusinessException;

	/**
	 * 逻辑删除审批单
	 *
	 * @param businessId
	 *            关联业务标识id
	 * @param businessType
	 *            关联业务类型
	 */
	public BusiForm logicDelete(String businessId, String businessType);

	/**
	 * 多条件查询
	 * 
	 * @param condition
	 *            查询条件
	 * @return
	 */
	public List<BusiForm> findByCondition(Map<String, Object> condition);

	/**
	 * 更新或保存busiForm
	 * 
	 * @param busiForm
	 *            流程业务表单
	 * @return 流程业务表单
	 * @throws BusinessException
	 *             业务异常
	 */
	public BusiForm saveOrUpdateBusiForm(BusiForm busiForm) throws Exception;

	/**
	 * 根据关联业务表单数据id查找审批单信息
	 * 
	 * @param businessEntityId
	 *            关联业务表单数据id
	 * @return 审批单
	 * @throws BusinessException
	 */
	public BusiForm findByBusinessEntityId(String businessEntityId) throws BusinessException;
	
	/**
	 * 我的草稿查询分页列表
	 * @param myDraft
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	public Page<MyDraft> findByPage(MyDraft myDraft,Pageable pageable) throws BusinessException;
	
	/**
	 * 
	 * @Title: startProcess 
	 * @Description: 启动流程
	 * @author jingyh 
	 * @date 2017年02月16日 下午5:51:01
	 * @param busiForm
	 * 		流程表单信息
	 * 			businessEntityId,businessEntityNm,componentsEntityId,componentsEntityNm:不可为空
	 * 			businessCode,modelType,applyTitle:不可为空
	 * 			默认以processKey启动流程，若为空，则根据functionCode和productId查询配置产品配置
	 * @param businessVarible
	 * 		业务变量
	 * @param engineVars
	 * 		引擎变量
	 * @return
	 * @throws Exception 
	 * BusiForm 
	 * @throws
	 */
	public BusiForm startProcess(BusiForm busiForm, Map<String, String> businessVarible, Map<String, String> engineVars)
			throws Exception;

	/**
	 * @Title: findMyApplyInfos 
	 * @Description: 分页查询我的申请信息
	 * @author jingyh 
	 * @param pageable
	 * @param queryObj
	 * @return
	 * @throws Exception
	 */
	public Page<BusiForm> findMyApplyInfos(Pageable pageable, List<QueryObj> queryObj) throws Exception;
	
	/**
	 * @Title: wasteApplyInfo 
	 * @Description: 废弃申请
	 * @author jingyh 
	 * @param id
	 * @throws BusinessException
	 */
	public void wasteApplyInfo(String id) throws BusinessException;
	
	/**
	 * 
	 * @Title: findBusiFormByHql 
	 * @Description: 多条件查询业务表单
	 * @author yangjia 
	 * @param componentsEntityId
	 * @param businessEntityId
	 * @param processInstanceKey
	 * @return
	 */
	public List<BusiForm> findBusiFormByHql(String componentsEntityId,String businessEntityId,String processInstanceKey);
}
