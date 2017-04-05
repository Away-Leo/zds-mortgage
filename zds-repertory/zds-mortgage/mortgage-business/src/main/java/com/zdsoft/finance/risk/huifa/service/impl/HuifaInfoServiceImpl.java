package com.zdsoft.finance.risk.huifa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.risk.entity.HuifaRequest;
import com.zdsoft.finance.risk.huifa.repository.HuifaRequestRepository;
import com.zdsoft.finance.risk.huifa.service.HuifaInfoService;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HuifaInfoServiceImpl.java 
 * @ClassName: HuifaInfoServiceImpl 
 * @Description: 汇法网基本信息服务类
 * @author panshm 
 * @date 2017年2月18日 下午2:01:13 
 * @version V1.0
 */
@Service("huifaInfoService")
public class HuifaInfoServiceImpl extends BaseServiceImpl<HuifaRequest, HuifaRequestRepository>
		implements HuifaInfoService {
	
	@Autowired
	private HuifaRequestRepository huifaRequestRepository;

	@Override
	public List<HuifaRequest> findByCodeAndStype(String[] codes, Integer stype) {
		logger.debug("Service called. findByCodeAndStype({}, {})", codes, stype);
		String hql = "";
		hql = "from HuifaRequest where stype=:stype and (1=0 ";
        Map<String, Object> condition = new HashMap<String, Object>();
        
        for (int i=0;i<codes.length;i++) {
			String code = codes[i];
			if (StringUtils.isNotEmpty(code)) {				
				hql += " or code=:code"+i;
				condition.put("code"+i, code);
			}
		}
		hql += " ) ";
		condition.put("stype", stype);
		List<HuifaRequest> list = huifaRequestRepository.findByHql(hql, condition);
		return list;
	}

	@Override
	public List<HuifaRequest> findByNameAndStype(String[] names, Integer stype) {
		logger.debug("Service called. findByNameAndStype({}, {})", names, stype);
		String hql = "";
		hql = "from HuifaRequest where stype=:stype and (1=0 ";
		
		Map<String, Object> condition = new HashMap<String, Object>();
		for (int i=0;i<names.length;i++) {
			String name = names[i];
			if (StringUtils.isNotEmpty(name)) {				
				hql += " or name=:name"+i;
				condition.put("name"+i, name);
			}
		}
		hql += " ) ";
		condition.put("stype", stype);
		List<HuifaRequest> list = huifaRequestRepository.findByHql(hql, condition);
		return list;
	}

}
