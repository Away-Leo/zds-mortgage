package com.zdsoft.finance.product.service.impl;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.MateriaListLimits;
import com.zdsoft.finance.product.repository.MateriaListLimitsRepository;
import com.zdsoft.finance.product.service.MateriaListLimitsService;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MateriaListLimitsServiceImpl.java 
 * @ClassName: MateriaListLimitsServiceImpl 
 * @Description: 资料清单权限
 * @author gufeng 
 * @date 2017年3月2日 下午4:45:43 
 * @version V1.0
 */
@Service("materiaListLimitsService")
public class MateriaListLimitsServiceImpl extends BaseServiceImpl<MateriaListLimits, CustomRepository<MateriaListLimits,String>> 
	implements MateriaListLimitsService{

	@Autowired
	private MateriaListLimitsRepository materiaListLimitsRepository;

	@Override
	public List<MateriaListLimits> getLimits(String productId, String materiaTypeCode) {
		if(ObjectHelper.isEmpty(productId) || ObjectHelper.isEmpty(materiaTypeCode)){
			return null;
		}
		return materiaListLimitsRepository.findByProductIdAndMateriaTypeCode(productId,materiaTypeCode);
	}

	@Override
	public void saveLimits(String productId, String[] materiaTypeCode, String[] materiaLimit,EmpDto emp) throws BusinessException {
		if(ObjectHelper.isEmpty(productId)){
			throw new BusinessException("1000000001","产品id为空，不能保存");
		}
		if(ObjectHelper.isEmpty(materiaTypeCode) || ObjectHelper.isEmpty(materiaLimit)){
			throw new BusinessException("100000002","资料类型或权限为空");
		}
		if(ObjectHelper.isEmpty(emp)){
			throw new BusinessException("100000004","当前登录人未获取到");
		}
		List<MateriaListLimits> limits = materiaListLimitsRepository.findByProductId(productId);
		if(ObjectHelper.isNotEmpty(limits)){
			for (MateriaListLimits materiaListLimits : limits) {
				materiaListLimitsRepository.delete(materiaListLimits);
			}
		}
		for (int i = 0; i < materiaTypeCode.length; i++) {
			String typeCode = materiaTypeCode[i];
			String[] li = materiaLimit[i].split(",");
			if(materiaTypeCode.length == 1){
				li = materiaLimit;
			}
			for (String limit : li) {
				if(ObjectHelper.isEmpty(limit)){
					continue;
				}
				MateriaListLimits bean = new MateriaListLimits();
				bean.setProductId(productId);
				bean.setMateriaTypeCode(typeCode);
				bean.setMateriaLimit(limit);
				bean.setCreateBy(emp.getEmpCd());
				bean.setCreateOrgCd(emp.getOrgCd());
				materiaListLimitsRepository.saveEntity(bean);
			}
		}
	}

	@Override
	public MateriaListLimits findWithMateriaLimitLike(String productId, String materiaTypeCode, String materiaLimitLike) throws BusinessException {
		if(ObjectHelper.isEmpty(productId)){
			throw new BusinessException("10010004","未传入相关参数，按照全条件查找资料清单权限未传入productId");
		}
		if(ObjectHelper.isEmpty(materiaTypeCode)){
			throw new BusinessException("10010004","未传入相关参数，按照全条件查找资料清单权限未传入materiaTypeCode");
		}
		if(ObjectHelper.isEmpty(materiaLimitLike)){
			throw new BusinessException("10010004","未传入相关参数，按照全条件查找资料清单权限未传入materiaLimitLike");
		}
		logger.debug("paras:productId="+productId+"      materiaTypeCode="+materiaTypeCode+"     materiaLimitLike:"+materiaLimitLike);
		List<MateriaListLimits> materiaListLimits=this.materiaListLimitsRepository.findByProductIdAndMateriaTypeCode(productId,materiaTypeCode);
		MateriaListLimits returnData=null;
		if(ObjectHelper.isNotEmpty(materiaListLimits)){
			for(MateriaListLimits temp:materiaListLimits){
				if(temp.getMateriaLimit().substring(temp.getMateriaLimit().length()-2,temp.getMateriaLimit().length()).equalsIgnoreCase(materiaLimitLike)){
					returnData=temp;
					break;
				}
			}
		}else{
			throw new BusinessException("10010002","根据参数未找到相应数据，根据全条件未找到相应权限数据");
		}
		if(ObjectHelper.isNotEmpty(returnData)){
			return returnData;
		}else{
			throw new BusinessException("10010002","根据参数未找到相应数据，根据案件状态未找到");
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public List<Map<String, Object>> findLimitByProductId(String productId) throws BusinessException {
		if(ObjectHelper.isEmpty(productId)){
			throw new BusinessException("1000000001","产品id为空");
		}
		Map<String, Object> condition = new HashMap<>();
		condition.put("productId", productId);
		try {
			return materiaListLimitsRepository.findListMapByCondition(materiaListLimitsRepository.limitSql, condition);
		} catch (Exception e) {
			throw new BusinessException("查询问题",e.getMessage());
		}
	}
}
