package com.zdsoft.finance.marketing.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.utils.DateUtil;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.repository.CaseApplyRepository;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseApplySerivceImpl.java
 * @Package:com.zdsoft.finance.marketing.service.impl
 * @Description:案件服务类
 * @author: zhoushichao
 * @date:2017年1月10日 下午9:51:00
 * @version:v1.0
 */
@Service("caseApplySerivce")
public class CaseApplyServiceImpl extends BaseServiceImpl<CaseApply, CustomRepository<CaseApply, String>>
		implements CaseApplyService {

	@Autowired
	CaseApplyRepository caseApplyRepository;
	@Autowired
	private CED CED;
	
	@Override
	@Transactional
	public CaseApply saveOrUpdateCaseApply(CaseApply caseApply) throws Exception {
		if(ObjectHelper.isEmpty(caseApply.getCaseApplyCode())){
			try {
				 // 生成案件案号
				String caseApplyCode = CED.resolveExpression("1000000000012", null);
				logger.debug("新生成的案件号为："+caseApplyCode);
				 caseApply.setCaseApplyCode(caseApplyCode);
			} catch (Exception e) {
				logger.error("平台异常，未解析表达式");
				e.printStackTrace();
			}
		}
		//初始案件状态设置为正常
 		if(ObjectHelper.isEmpty(caseApply.getCaseApplyStatus())){
 			caseApply.setCaseApplyStatus(CaseApply.NORMAL);
		}
		//初始保存案件审批状态设置为一审状态
 		if(ObjectHelper.isEmpty(caseApply.getExaminationStatus())){
 			caseApply.setExaminationStatus(CaseApply.FIRST_INSTANCE);
 		}
		//设置申请时间
		if(ObjectHelper.isEmpty(caseApply.getApplyDate())){
			caseApply.setApplyDate(DateUtil.getCurrentDate());
		}
		return this.saveOrUpdateEntity(caseApply);
	}
	
	@Override
	public Page<Map<String, Object>> findPageBeforehandApply(Pageable pageable, List<QueryObj> queryObjs)
			throws Exception {
		return caseApplyRepository.findPageBeforehandApply(pageable, queryObjs);
	}

	@Override
	public Page<Map<String, Object>> findPageCaseApply(Pageable pageable, List<QueryObj> queryObjs) {
		return caseApplyRepository.findPageCaseApply(pageable, queryObjs);
	}

	/**
	 * 	案件额度申请分页查询信息
	 *  @author xingpan
	 */
	@Override
	public Page<Map<String, Object>> findPageCaseLimitApply(Pageable pageable, List<QueryObj> queryObjs) {
		Page<Map<String,Object>> page = caseApplyRepository.findPageCaseLimitApply(pageable,queryObjs);
		for (Map<String,Object> map : page.getRecords()) {
			if(ObjectHelper.isNotEmpty(map.get("actualLimitStatus"))){
				try {
					String code = CED.loadSimpleCodeNameByFullCode(map.get("actualLimitStatus").toString());
					map.put("actualLimitStatus", code);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return page;
	}

	/**
	 * 案件预约分页查询信息
	 * @throws Exception 
	 */
	@Override
	public Page<Map<String, Object>> findPageAppointment(PageRequest pageable, List<QueryObj> queryObjs) throws Exception {
		Page<Map<String, Object>> page = caseApplyRepository.findPageAppointment(pageable,queryObjs);
		for(Map<String, Object> map : page.getRecords()){
			if(ObjectHelper.isNotEmpty(map.get("appointmentType"))){
				String code = CED.loadSimpleCodeNameByFullCode(map.get("appointmentType").toString());
				map.put("appointmentType", code);
			}
		}
		return page;
	}

	/**
	 * 获取案件预约客户(即案件的主借人,所有担保人)
	 */
	@Override
	public List<Map<String, Object>> appointmentClient(String id) {
		List<Map<String, Object>> list = caseApplyRepository.appointmentClient(id);
		if(ObjectHelper.isNotEmpty(list)){
			for(Map<String, Object> map : list){
				if(ObjectHelper.isNotEmpty(map.get("joinType"))){
					try {
						String code = CED.loadSimpleCodeNameByFullCode(map.get("joinType").toString());
						map.put("joinType", code);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if(ObjectHelper.isNotEmpty(map.get("contactType"))){
					try {
						String code = CED.loadSimpleCodeNameByFullCode(map.get("contactType").toString());
						map.put("contactType", code);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			return list;
		}
		return null;
	}

}
