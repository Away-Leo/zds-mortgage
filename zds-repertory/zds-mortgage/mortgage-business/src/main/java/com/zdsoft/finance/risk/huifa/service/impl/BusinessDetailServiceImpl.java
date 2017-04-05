package com.zdsoft.finance.risk.huifa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.common.base.ConstantParameter;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.risk.entity.DaasBasic;
import com.zdsoft.finance.risk.huifa.repository.DaasAlterRepository;
import com.zdsoft.finance.risk.huifa.repository.DaasBasicRepository;
import com.zdsoft.finance.risk.huifa.repository.DaasCaseInfoRepository;
import com.zdsoft.finance.risk.huifa.repository.DaasEntinvRepository;
import com.zdsoft.finance.risk.huifa.repository.DaasFiliationRepository;
import com.zdsoft.finance.risk.huifa.repository.DaasFrinvRepository;
import com.zdsoft.finance.risk.huifa.repository.DaasFrpositionRepository;
import com.zdsoft.finance.risk.huifa.repository.DaasMordetailRepository;
import com.zdsoft.finance.risk.huifa.repository.DaasMorguaInfoRepository;
import com.zdsoft.finance.risk.huifa.repository.DaasPersonRepository;
import com.zdsoft.finance.risk.huifa.repository.DaasPunishBreakRepository;
import com.zdsoft.finance.risk.huifa.repository.DaasPunishedRepository;
import com.zdsoft.finance.risk.huifa.repository.DaasShareHolderRepository;
import com.zdsoft.finance.risk.huifa.repository.DaasSharesFrostRepository;
import com.zdsoft.finance.risk.huifa.repository.DaasSharesImpawnRepository;
import com.zdsoft.finance.risk.huifa.service.BusinessDetailService;
import com.zdsoft.finance.risk.huifa.service.bo.BusinessDetailBo;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.util.HttpClientHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import net.sf.json.JSONObject;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BusinessDetailServiceImpl.java 
 * @ClassName: BusinessDetailServiceImpl 
 * @Description: 工商信息详情服务类
 * @author panshm 
 * @date 2017年2月20日 下午3:01:43 
 * @version V1.0
 */
@Service("businessDetailService")
public class BusinessDetailServiceImpl implements BusinessDetailService {
	
	@Autowired
	private DaasAlterRepository alterRepo;
	
	@Autowired
	private DaasBasicRepository basicRepo;
	
	@Autowired
	private DaasCaseInfoRepository caseInfoRepo;
	
	@Autowired
	private DaasEntinvRepository entinvRepo;
	
	@Autowired
	private DaasFiliationRepository filiationRepo;
	
	@Autowired
	private DaasFrinvRepository frinvRepo;
	
	@Autowired
	private DaasFrpositionRepository frpositionRepo;
	
	@Autowired
	private DaasMordetailRepository mordetailRepo;
	
	@Autowired
	private DaasMorguaInfoRepository morguaInfoRepo;
	
	@Autowired
	private DaasPersonRepository personRepo;
	
	@Autowired
	private DaasPunishBreakRepository punishBreakRepo;
	
	@Autowired
	private DaasPunishedRepository punishedRepo;
	
	@Autowired
	private DaasShareHolderRepository shareHolderRepo;
	
	@Autowired
	private DaasSharesFrostRepository sharesFrostRepo;
	
	@Autowired
	private DaasSharesImpawnRepository sharesImpawnRepo;

	@Log
	public Logger logger;

	@Override
	public BusinessDetailBo findByOrderId(String orderId) {
		// 初始化查询条件
		BusinessDetailBo bo = new BusinessDetailBo();
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("orderId", orderId);
        String hql = "";
        // 开始构建BusinessObject数据
        hql = "from DaasAlter where orderId=:orderId";
        bo.setAlterList(alterRepo.findByHql(hql, condition));
        
        hql = "from DaasBasic where orderId=:orderId";
        bo.setBasicList(basicRepo.findByHql(hql, condition));
        
        hql = "from DaasCaseInfo where orderId=:orderId";
        bo.setCaseInfoList(caseInfoRepo.findByHql(hql, condition));
        
        hql = "from DaasEntinv where orderId=:orderId";
        bo.setEntinvList(entinvRepo.findByHql(hql, condition));
        
        hql = "from DaasFiliation where orderId=:orderId";
        bo.setFiliationList(filiationRepo.findByHql(hql, condition));
        
        hql = "from DaasFrinv where orderId=:orderId";
        bo.setFrinvList(frinvRepo.findByHql(hql, condition));
        
        hql = "from DaasFrposition where orderId=:orderId";
        bo.setFrpositionList(frpositionRepo.findByHql(hql, condition));
        
        hql = "from DaasMordetail where orderId=:orderId";
        bo.setMordetailList(mordetailRepo.findByHql(hql, condition));
        
        hql = "from DaasMorguaInfo where orderId=:orderId";
        bo.setMorguaInfoList(morguaInfoRepo.findByHql(hql, condition));
        
        hql = "from DaasPerson where orderId=:orderId";
        bo.setPersonList(personRepo.findByHql(hql, condition));
        
        hql = "from DaasPunishBreak where orderId=:orderId";
        bo.setPunishBreakList(punishBreakRepo.findByHql(hql, condition));
        
        hql = "from DaasPunished where orderId=:orderId";
        bo.setPunishedList(punishedRepo.findByHql(hql, condition));
        
        hql = "from DaasShareHolder where orderId=:orderId";
        bo.setShareHolderList(shareHolderRepo.findByHql(hql, condition));
        
        hql = "from DaasSharesFrost where orderId=:orderId";
        bo.setSharesFrostList(sharesFrostRepo.findByHql(hql, condition));
        
        hql = "from DaasSharesImpawn where orderId=:orderId";
        bo.setSharesImpawnList(sharesImpawnRepo.findByHql(hql, condition));
        
		return bo;
	}

	@Override
	public List<DaasBasic> findByCompanyNames(String[] companyNameList) {
		Map<String, Object> condition = new HashMap<String, Object>();
		String hql = "";
		hql = "from DaasBasic where 1=0";
		for (int i=0;i<companyNameList.length;i++) {
			String companyName = companyNameList[i];
			if (StringUtils.isNotEmpty(companyName)) {				
				hql += " or entname=:companyName"+i;
				condition.put("companyName"+i, companyName);
			}
		}
		List<DaasBasic> list = basicRepo.findByHql(hql, condition);
		return list;
	}

	@Override
	public Boolean callBusinessInterface(String companyName, String businessId, String orgName, String operator)
			throws BusinessException {
		Boolean success = false;
		if (StringUtils.isEmpty(companyName)) {
			throw new BusinessException("10010004", "公司名称不可为空！");
		}
		if (StringUtils.isEmpty(businessId)) {
			throw new BusinessException("10010004", "业务ID不可为空！");
		}

		String url = ConstantParameter.getBusinessUrl();
		Map<String, String> map = new HashMap<String, String>();
		map.put("companyName", companyName);
		map.put("biz_id", businessId);
		map.put("orgName", orgName);
		map.put("operator", operator);
		try {
			String data = HttpClientHelper.receivePostData(url, map);
			if (ObjectHelper.isNotEmpty(data)) {
				JSONObject jasonObject = JSONObject.fromObject(data);
				@SuppressWarnings("unchecked")
				Map<String, Object> jsonMap = (Map<String, Object>) jasonObject;
				if ("s".equals(jsonMap.get("success"))) {
					success = true;
				} else {
					String content = (String) jsonMap.get("content");
					JSONObject contentObject = JSONObject.fromObject(content);
					logger.error("调用工商接口返回错误信息：", contentObject.get("message"));
					success = false;
				}
			} else {
				success = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("调用工商接口出错.", e.getMessage());
			success = false;
		}
		return success;
	}

	@Override
	public BusinessDetailBo findAllBusinessInfo(String companyName) {
		Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("companyName", companyName);
        String hql = "";
		hql = "from DaasBasic where entname=:companyName";
		List<DaasBasic> list = basicRepo.findByHql(hql, condition);
		if (ObjectHelper.isNotEmpty(list)) {
			DaasBasic basic = list.get(0);
			if (StringUtils.isEmpty(basic.getOrderId())) {
				return null;
			}
			return this.findByOrderId(basic.getOrderId());
		} else {			
			return null;
		}
	}
	
	@Override
	public boolean hasDaasInfos(String companyName) {
		if (ObjectHelper.isNotEmpty(companyName)) {
			String sql = "select tdb.order_id from T_DAAS_BASIC tdb where tdb.entname = :companyName";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("companyName", companyName);
			Long result = this.basicRepo.countAllSql(sql, params);
			if (ObjectHelper.isNotEmpty(result) && result > 0) {
				return true;
			}
		}
		return false;
	}

}
