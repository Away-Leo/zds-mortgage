package com.zdsoft.finance.houseassessment.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.houseassessment.entity.HouseEvaluateDetail;
import com.zdsoft.finance.houseassessment.repository.HouseEvaluateDetailRepository;
import com.zdsoft.finance.houseassessment.service.HouseEvaluateDetailService;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HouseEvaluateDetailServiceImpl.java
 * @ClassName: HouseEvaluateDetailServiceImpl
 * @Description: 房产评估详情服务类
 * @author yangjia
 * @date 2017年2月20日 上午10:34:38
 * @version V1.0
 */

@Service("houseEvaluateDetailService")
public class HouseEvaluateDetailServiceImpl
		extends
		BaseServiceImpl<HouseEvaluateDetail, CustomRepository<HouseEvaluateDetail, String>>
		implements HouseEvaluateDetailService {

	@Autowired
	HouseEvaluateDetailRepository houseEvaluateDetailRepository;
	
	@Override
	public List<Map<String, Object>> queryHouseEvaluateInfo(Map<String, Object> param) throws Exception {
		return houseEvaluateDetailRepository.queryHouseEvaluateInfo(param);
	}

	/**
	 * 
	 * <p>
	 * Title: getPriceForCompany
	 * </p>
	 * <p>
	 * Description:查询评估信息
	 * </p>
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 * @see com.zdsoft.finance.houseassessment.service.HouseEvaluateDetailService#getPriceForCompany(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> getPriceForCompany(Map<String, Object> map)
			throws Exception {
		List<Map<String, Object>> list = houseEvaluateDetailRepository
				.getPriceForCompany(map);

		return list;
	}
    
	/**
	 * 
	 * <p>Title: getPriceByBizId</p>   
	 * <p>Description: 根据押品ID查询评估信息</p>   
	 * @param bizid
	 * @return
	 * @throws Exception   
	 * @see com.zdsoft.finance.houseassessment.service.HouseEvaluateDetailService#getPriceByBizId(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> getPriceByBizId(String bizid)
			throws Exception {

		List<Map<String, Object>> list = houseEvaluateDetailRepository
				.getPriceByBizId(bizid);

		return list;
	}

	@Override
	public List<HouseEvaluateDetail> getHouseEvaluateDetailByHouseEvaluateId(String houseEvaluateId) throws Exception {
		
		return houseEvaluateDetailRepository.findByHouseEvaluateId(houseEvaluateId);
	}

}
