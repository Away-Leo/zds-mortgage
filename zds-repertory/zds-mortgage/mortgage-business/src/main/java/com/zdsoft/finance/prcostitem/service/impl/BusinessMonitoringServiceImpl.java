package com.zdsoft.finance.prcostitem.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.prcostitem.entity.BusinessMonitoring;
import com.zdsoft.finance.prcostitem.repository.BusinessMonitoringRepository;
import com.zdsoft.finance.prcostitem.service.BusinessMonitoringService;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BusinessMonitoringServiceImpl.java 
 * @ClassName: BusinessMonitoringServiceImpl 
 * @Description: 业务监控
 * @author gufeng 
 * @date 2017年3月13日 下午5:03:49 
 * @version V1.0
 */
@Service
public class BusinessMonitoringServiceImpl extends BaseServiceImpl<BusinessMonitoring, CustomRepository<BusinessMonitoring, String>> 
	implements BusinessMonitoringService {
	
	@Autowired
	private BusinessMonitoringRepository businessMonitoringRepository;

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public void monitoring() throws BusinessException {
		try{
			//特批案件总条数
			Long specialCount = businessMonitoringRepository.specialCount();
			if(ObjectHelper.isNotEmpty(specialCount) && specialCount > 0){
				//特批案件逾期月总条数
				this.saveBusinessMonitoring(specialCount, BusinessMonitoring.OVERDUE_TYPE_MONTH);
				//特批案件逾期季总条数
				this.saveBusinessMonitoring(specialCount, BusinessMonitoring.OVERDUE_TYPE_QUARTER);
				//特批案件逾期半年总条数
				this.saveBusinessMonitoring(specialCount, BusinessMonitoring.OVERDUE_TYPE_SEMESTER);
				//特批案件逾期年条数
				this.saveBusinessMonitoring(specialCount, BusinessMonitoring.OVERDUE_TYPE_YEAR);
			}
		}catch(Exception e){
			throw new BusinessException("检查出错",e.getMessage());
		}
	}
	
	/**
	 * 特批按键逾期
	 * @param specialCount 特批案件总条数
	 * @param overdueType 逾期类型
	 */
	private void saveBusinessMonitoring(Long specialCount,String overdueType){
		Long overdueCount = this.spcialOverdueCount(overdueType);
		if(ObjectHelper.isNotEmpty(overdueType) && ((double) overdueCount/specialCount) > 0.06){
			double rate = (double)overdueCount / specialCount;
			if(rate > 0.03){
				BusinessMonitoring bm = new BusinessMonitoring();
				bm.setCreateBy("System");
				bm.setCreateTime(new Date());
				bm.setMonitoringTime(DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT));
				bm.setOverdueTotal(overdueCount);
				bm.setSpecialTotal(specialCount);
				bm.setOverdueType(overdueType);
				bm.setOverdueRate(rate);
				businessMonitoringRepository.saveEntity(bm);
			}
			
		}
	}
	
	/**
	 * 特批案件逾期条数
	 * @param overdueType 逾期类型
	 * @return 条数
	 */
	private Long spcialOverdueCount(String overdueType){
		Long count = 0l;
		if(ObjectHelper.isEmpty(overdueType)){
			return count;
		}
		switch (overdueType) {
		case BusinessMonitoring.OVERDUE_TYPE_MONTH:
			count = businessMonitoringRepository.overdueSpecialCount(30);
			break;
		case BusinessMonitoring.OVERDUE_TYPE_QUARTER:
			count = businessMonitoringRepository.overdueSpecialCount(90);
			break;
		case BusinessMonitoring.OVERDUE_TYPE_SEMESTER:
			count = businessMonitoringRepository.overdueSpecialCount(180);
			break;
		case BusinessMonitoring.OVERDUE_TYPE_YEAR:
			count = businessMonitoringRepository.overdueSpecialCount(365);
			break;
		default:
			break;
		}
		return count;
	}
	
}
