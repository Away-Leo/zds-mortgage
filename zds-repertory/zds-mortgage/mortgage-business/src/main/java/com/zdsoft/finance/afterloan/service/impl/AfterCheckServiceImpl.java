package com.zdsoft.finance.afterloan.service.impl;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.afterloan.entity.AfterCheck;
import com.zdsoft.finance.afterloan.entity.AfterLoanReview;
import com.zdsoft.finance.afterloan.repository.AfterCheckRepository;
import com.zdsoft.finance.afterloan.repository.AfterLoanReviewRepository;
import com.zdsoft.finance.afterloan.service.AfterCheckService;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.AfterContact;
import com.zdsoft.finance.customer.repository.AfterContactRepository;
import com.zdsoft.finance.risk.entity.HuifaRequest;
import com.zdsoft.finance.risk.entity.HuifaResultInfo;
import com.zdsoft.finance.risk.huifa.service.HuifaDetailService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterCheckServiceImpl.java 
 * @ClassName: AfterCheckServiceImpl 
 * @Description: 贷后检查服务类
 * @author zhoushichao 
 * @date 2017年2月8日 下午5:03:40 
 * @version V1.0 
 */ 
@Service("afterCheckService")
public class AfterCheckServiceImpl extends BaseServiceImpl<AfterCheck, CustomRepository<AfterCheck, String>>
		implements AfterCheckService {

	@Autowired
	AfterCheckRepository afterCheckRepository;

	@Autowired
	private AfterContactRepository afterContactRepository;

	@Autowired
	private AfterLoanReviewRepository afterLoanReviewRepository;

	@Autowired
	private HuifaDetailService huifaDetailService;

	@Autowired
	CED CED;

	@Override
	public AfterCheck saveOrUpdateAfterCheck(AfterCheck afterCheck) throws Exception {
		AfterCheck afterChecks = new AfterCheck();
		if (ObjectHelper.isNotEmpty(afterCheck.getId())) {
			afterChecks = this.findOne(afterCheck.getId());
		}
		BeanUtils.copyProperties(afterCheck, afterChecks,new String[] {"id","createTime"});
		return this.saveOrUpdateEntity(afterChecks);
	}
	@Override
	public Page<Map<String, Object>> findPageAfterCheck(Pageable pageable, List<QueryObj> queryObjs) throws Exception {
		Page<Map<String, Object>> page = afterCheckRepository.findPageAfterCheck(pageable, queryObjs);
		if(ObjectHelper.isNotEmpty(page)){
			List<Map<String, Object>> records = page.getRecords();
			if(ObjectHelper.isNotEmpty(records)){
				for (Map<String, Object> map : records) {
					//根据案件号查询最近一次检查
					String caseApplyId = (String) map.get("ID");
					List<AfterCheck> afterCheck = afterCheckRepository.findByCaseApplyIdOrderByCreateTimeDesc(caseApplyId);
					if(ObjectHelper.isNotEmpty(afterCheck)){
						map.put("ACTIONSNAME", CED.loadSimpleCodeNameByFullCode(afterCheck.get(0).getActions()));
						map.put("CHECKEDDATE", afterCheck.get(0).getCheckedDate());
						map.put("TRACKERNAME", afterCheck.get(0).getTrackerName());
					}
					//案件状态
					String stage = "";
					if(ObjectHelper.isNotEmpty(map.get("STAGE"))){
						stage=CED.loadSimpleCodeNameByFullCode(String.valueOf(map.get("STAGE")));
					}
					map.put("STAGE",stage);
				}
			}
		}
		return page;
	}

	@Override
	public Page<AfterLoanReview> findAfterLoanReviewByCondition(Pageable pageable, AfterLoanReview afterLoanReview) throws Exception {
		if(ObjectHelper.isNotEmpty(pageable)&&ObjectHelper.isNotEmpty(afterLoanReview)){
			Page<AfterLoanReview> sourceData=this.afterLoanReviewRepository.findAfterLoanReviewByCondition(pageable, afterLoanReview);
			if(ObjectHelper.isNotEmpty(sourceData)&&ObjectHelper.isNotEmpty(sourceData.getRecords())&&sourceData.getRecords().size()>0){
				for(AfterLoanReview temp:sourceData.getRecords()){
					//查询主借人联系方式
					String customerId=temp.getCustomerId();
					List<AfterContact> contacts = afterContactRepository.queryContactList(customerId);
					temp.setPhoneNumber(ObjectHelper.isNotEmpty(contacts)&&ObjectHelper.isNotEmpty(contacts.get(0).getPhoneNumber())?contacts.get(0).getPhoneNumber():"");
					//拼装押品地址
					if(ObjectHelper.isNotEmpty(temp.getProvince())&&ObjectHelper.isNotEmpty(temp.getCity())&&ObjectHelper.isNotEmpty(temp.getDistrict())){
						try{
							String mailingAddress=CED.loadSimpleCodeNameByFullCode(temp.getProvince())+"/"+CED.loadSimpleCodeNameByFullCode(temp.getCity())+"/"+CED.loadSimpleCodeNameByFullCode(temp.getDistrict())+"/"+temp.getMailingAddress();
							temp.setCollateralAddress(mailingAddress);
						}catch (Exception e){
							logger.error("查询贷后监控查询拼接押品地址出错",e);
							e.printStackTrace();
						}
					}
					//汇法
					//客户类型
					Integer customerType=0;
					if(ObjectHelper.isNotEmpty(temp.getCustomerType())&&temp.getCustomerType().equalsIgnoreCase("PERS")){
						customerType= HuifaRequest.STYPE_PERSONAL;
					}else if(ObjectHelper.isNotEmpty(temp.getCustomerType())&&!temp.getCustomerType().equalsIgnoreCase("PERS")){
						customerType=HuifaRequest.STYPE_COMPANY;
					}
					List<HuifaResultInfo> huifaResultInfos=this.huifaDetailService.findSingleHuifaDetail(customerType,temp.getCredentialNo(),HuifaResultInfo.TYPE_CRIMINAL);
					//工商
					//房产评估
				}
				return sourceData;
			}else{
				throw new BusinessException("10010002","根据参数未找相应数据，分页查询贷后监控查询出错");
			}
		}else{
			throw new BusinessException("10010004","未传入相关参数，分页查询贷后监控查询出错");
		}
	}

	@Override
	public Page<AfterCheck> findByCaseApplyId(String caseApplyId, Pageable pageable) throws Exception {
		if(ObjectHelper.isEmpty(caseApplyId) || ObjectHelper.isEmpty(pageable)){
			logger.error("参数不能为空");
			return null;
		}
		return afterCheckRepository.findByCaseApplyId(caseApplyId, pageable);
	}
}
