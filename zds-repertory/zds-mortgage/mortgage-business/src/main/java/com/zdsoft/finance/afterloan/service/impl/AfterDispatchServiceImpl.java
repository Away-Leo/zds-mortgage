package com.zdsoft.finance.afterloan.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.afterloan.entity.AfterDispatch;
import com.zdsoft.finance.afterloan.entity.AfterSupervise;
import com.zdsoft.finance.afterloan.repository.AfterDispatchRepository;
import com.zdsoft.finance.afterloan.service.AfterDispatchService;
import com.zdsoft.finance.afterloan.service.AfterSuperviseService;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterSuperviseServiceImpl.java 
 * @ClassName: AfterSuperviseServiceImpl 
 * @Description: 派工service实现
 * @author xj 
 * @date 2017年3月7日 下午1:14:07 
 * @version V1.0
 */
@Service("afterDispatchService")
public class AfterDispatchServiceImpl extends BaseServiceImpl<AfterDispatch, AfterDispatchRepository> implements AfterDispatchService {
	
	@Autowired
	private CED CED;
	@Autowired
	private AfterSuperviseService afterSuperviseService;
	
	@Override
	public List<AfterDispatch> findByAfterSuperviseId(String afterSuperviseId) {
		return this.customReposity.findByAfterSuperviseId(afterSuperviseId);
	}

	@Override
	public AfterDispatch findByAfterSuperviseIdAndDispatchCode(String afterSuperviseId, String dispatchCode) {
		return this.customReposity.findByAfterSuperviseIdAndDispatchCode(afterSuperviseId, dispatchCode);
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public AfterDispatch saveAfterDispatch(String afterSuperviseId, String dispatchCode) throws Exception {
		EmpDto loginUser = CED.getLoginUser();
		String superviserCode = loginUser.getEmpCd();
		String superviserName = loginUser.getEmpNm();
		String superviserOrgCd = loginUser.getOrgCd();
		AfterDispatch afterDispatch = this.customReposity.findByAfterSuperviseIdAndSuperviserCode(afterSuperviseId, superviserCode);
		if(ObjectHelper.isEmpty(afterDispatch)){
			afterDispatch = new AfterDispatch();
			afterDispatch.setCreateOrgCd(superviserOrgCd);
			afterDispatch.setCreateBy(superviserCode);
		}
		AfterSupervise afterSupervise = afterSuperviseService.findOne(afterSuperviseId);
		//督办人code
		afterDispatch.setSuperviserCode(superviserCode);
		//督办人name
		afterDispatch.setSuperviserName(superviserName);
		//派工人员
		EmpDto empDto = CED.findEmployeesByCodes(new String[]{dispatchCode}).get(0);
		//派工人姓名
		String dispatchName = empDto.getEmpNm();
		//派工所属部门code
		String dispatchDepartmentCode = empDto.getDepartmentCd();
		//派工所属部门name
		String dispatchDepartmentName = empDto.getDepartmentName();
		afterDispatch.setDispatchCode(dispatchCode);
		afterDispatch.setDispatchName(dispatchName);
		afterDispatch.setDispatchDepartmentCode(dispatchDepartmentCode);
		afterDispatch.setDispatchDepartmentName(dispatchDepartmentName);
		afterDispatch.setAfterSupervise(afterSupervise);
		return this.saveOrUpdateEntity(afterDispatch);
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public AfterDispatch updateAfterDispatch(String afterSuperviseId, String feedbackRresults) throws Exception {
		EmpDto loginUser = CED.getLoginUser();
		//派工人
		String dispatchCode = loginUser.getEmpCd();
		AfterDispatch afterDispatch = this.findByAfterSuperviseIdAndDispatchCode(afterSuperviseId, dispatchCode);
		//反馈时间
		afterDispatch.setFeedbackDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT));
		//反馈结果
		afterDispatch.setFeedbackRresults(feedbackRresults);
		AfterSupervise afterSupervise = afterDispatch.getAfterSupervise();
		//最后一次反馈时间
		afterSupervise.setFeedbackDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT));
		//最后一次反馈结果
		afterSupervise.setFeedbackRresults(feedbackRresults);
		afterSuperviseService.updateEntity(afterSupervise);
		return this.updateEntity(afterDispatch);
	}

	@Override
	public AfterDispatch findByAfterSuperviseIdAndSuperviserCode(String afterSuperviseId, String superviserCode) {
		return this.customReposity.findByAfterSuperviseIdAndSuperviserCode(afterSuperviseId, superviserCode);
	}
	
}
