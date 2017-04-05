package com.zdsoft.finance.afterloan.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.afterloan.entity.AfterMonitorRecord;
import com.zdsoft.finance.afterloan.service.AfterMonitorRecordService;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.customer.entity.AfterContact;
import com.zdsoft.finance.customer.service.AfterContactService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterMonitorRecordServiceImpl.java 
 * @ClassName: AfterMonitorRecordServiceImpl 
 * @Description: 贷中贷后监控service
 * @author xj 
 * @date 2017年2月23日 下午2:19:16 
 * @version V1.0
 */
@Service("afterMonitorRecordService")
public class AfterMonitorRecordServiceImpl extends BaseServiceImpl<AfterMonitorRecord, CustomRepository<AfterMonitorRecord, String>> implements AfterMonitorRecordService {
	@Autowired
	private CaseApplyService caseApplyService;
	@Autowired
	private AfterContactService afterContactService;
	@Autowired
	private CED CED;
	@Override
	public Page<Map<String, Object>> findPageMonitorRecord(PageRequest pageable, List<QueryObj> queryObjs,String controlType)
			throws Exception {
		if(AfterMonitorRecord.MIDDLE_MONITOR_RECORD.equals(controlType)){
			controlType = AfterMonitorRecord.MIDDLE_MONITOR_RECORD;
		}else{
			controlType = AfterMonitorRecord.AFTER_MONITOR_RECORD;
		}
		Page<Map<String, Object>> page = caseApplyService.findPageMonitorRecord(pageable, queryObjs,controlType);
		//查询主借人的联系方式
		if(ObjectHelper.isNotEmpty(page)){
			List<Map<String, Object>> records = page.getRecords();
			if(ObjectHelper.isNotEmpty(records)){
				for (Map<String, Object> map : records) {
					String customerId = (String) map.get("customerId");
					List<AfterContact> contacts = afterContactService.queryContactList(customerId);
					if(ObjectHelper.isNotEmpty(contacts) && contacts.size()>0){
						map.put("phoneNumber", contacts.get(0).getPhoneNumber());
					}
				}
			}
		}
		return page;
	}
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void saveOrsubmitConnectLand(String[] caseApplyIds) throws Exception {
		for (String caseApplyId : caseApplyIds) {
			AfterMonitorRecord afterMonitorRecord = new AfterMonitorRecord();
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			//案件
			afterMonitorRecord.setCaseApply(caseApply);
			//监控时间
			afterMonitorRecord.setMonitorDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT));
			//接口类型(工商、汇法、房产评估)-汇法
			afterMonitorRecord.setInterfaceType(AfterMonitorRecord.INTERFACE_CONNECTLAND);
			//监控类型(贷中、贷后)
			afterMonitorRecord.setControlType(AfterMonitorRecord.MIDDLE_MONITOR_RECORD);
			EmpDto loginUser = CED.getLoginUser();
			//创建人代码
			String empCd = loginUser.getEmpCd();
			//机构
			String orgCd = loginUser.getOrgCd();
			afterMonitorRecord.setCreateBy(empCd);
			afterMonitorRecord.setCreateOrgCd(orgCd);
			//TODO 调用接口
			this.saveEntity(afterMonitorRecord);
		}
	}

	@Override
	public Map<String, Object> findMonitorRecordByCaseApplyId(String caseApplyId, String controlType) {
		return caseApplyService.findMonitorRecordByCaseApplyId(caseApplyId, controlType);
	}

}
