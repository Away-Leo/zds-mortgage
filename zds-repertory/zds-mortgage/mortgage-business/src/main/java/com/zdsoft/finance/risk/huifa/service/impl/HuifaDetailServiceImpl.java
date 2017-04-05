package com.zdsoft.finance.risk.huifa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.ConstantParameter;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.risk.entity.HuifaRequest;
import com.zdsoft.finance.risk.entity.HuifaResult;
import com.zdsoft.finance.risk.entity.HuifaResultInfo;
import com.zdsoft.finance.risk.huifa.repository.HuifaRequestRepository;
import com.zdsoft.finance.risk.huifa.repository.HuifaResultInfoRepository;
import com.zdsoft.finance.risk.huifa.repository.HuifaResultRepository;
import com.zdsoft.finance.risk.huifa.service.HuifaDetailService;
import com.zdsoft.framework.core.common.util.HttpClientHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import net.sf.json.JSONObject;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HuifaDetailServiceImpl.java 
 * @ClassName: HuifaDetailServiceImpl 
 * @Description: 汇法网详情服务类
 * @author panshm 
 * @date 2017年2月18日 下午2:01:13 
 * @version V1.0
 */
@Service("huifaDetailService")
public class HuifaDetailServiceImpl extends BaseServiceImpl<HuifaResultInfo, HuifaResultInfoRepository>
		implements HuifaDetailService {
	
	@Autowired
	private HuifaRequestRepository huifaRequestRepository;
	
	@Autowired
	private HuifaResultRepository huifaResultRepository;

	@Override
	public List<HuifaResultInfo> findSingleHuifaDetail(String type, String resultId) {
		logger.debug("Service called. findByTypeAndResultId({}, {})", type, resultId);
		return this.customReposity.findByTypenAndResultId(type, resultId);
	}

	@Override
	public Boolean callHuifaInterface(String type, String name, String code, String businessId)
			throws BusinessException {
		Boolean success = false;
		if (StringUtils.isEmpty(type)) {
			throw new BusinessException("10010004", "类型不可为空！");
		}
		if (StringUtils.isEmpty(name)) {
			throw new BusinessException("10010004", "个人/企业名称不可为空！");
		}
		if (StringUtils.isEmpty(businessId)) {
			throw new BusinessException("10010004", "业务ID不可为空！");
		}
		if ("1".equals(type) && StringUtils.isEmpty(code)) {
			throw new BusinessException("10010004", "个人用户必须传入身份证信息！");
		}

		String url = ConstantParameter.getHuifaUrl();
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", type);
		map.put("name", name);
		map.put("code", code);
		map.put("biz_id", businessId);
		try {
			String data = HttpClientHelper.receivePostData(url, map);
			if (ObjectHelper.isNotEmpty(data)) {
				JSONObject jasonObject = JSONObject.fromObject(data);
				@SuppressWarnings("unchecked")
				Map<String, Object> jsonMap = (Map<String, Object>) jasonObject;
				if ("s".equals(jsonMap.get("success"))) {
					success = true;
				} else {
					success = false;
				}
			} else {
				success = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("调用汇法网接口出错.", e.getMessage());
			success = false;
		}
		return success;
	}

	@Override
	public List<HuifaResultInfo> findSingleHuifaDetail(int stype, String code, String infoType) {
		List<HuifaResultInfo> list = new ArrayList<HuifaResultInfo>();
		HuifaRequest request = null;
		if (HuifaRequest.STYPE_PERSONAL.equals(stype)) {
			request = huifaRequestRepository.findByCodeAndStype(code, stype);
		} else if (HuifaRequest.STYPE_COMPANY.equals(stype)) {
			request = huifaRequestRepository.findByNameAndStype(code, stype);
		}
		if (null == request) {
			return list;
		}
		Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("requestId", request.getId());
        String hql = "from HuifaResult where requestId=:requestId";
		List<HuifaResult> resultList = huifaResultRepository.findByHql(hql, condition);
		if (null != resultList && resultList.size() == 1) {
			String resultId = resultList.get(0).getId();
			list = this.customReposity.findByTypenAndResultId(infoType, resultId);
		}
		return list;
	}

}
