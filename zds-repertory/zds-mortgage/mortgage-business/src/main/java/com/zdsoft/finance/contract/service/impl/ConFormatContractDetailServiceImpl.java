package com.zdsoft.finance.contract.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.contract.entity.ConFormatContractDetail;
import com.zdsoft.finance.contract.repository.ConFormatContractDetailRepository;
import com.zdsoft.finance.contract.service.ConFormatContractDetailService;
import com.zdsoft.framework.core.common.util.ObjectHelper;
/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConFormatContractDetailServiceImpl.java 
 * @ClassName: ConFormatContractDetailServiceImpl 
 * @Description: 格式化合同明细清单接口实现
 * @author zhongyong 
 * @date 2017年3月7日 下午5:12:45 
 * @version V1.0
 */
@Service("conFormatContractDetailService")
public class ConFormatContractDetailServiceImpl
		extends BaseServiceImpl<ConFormatContractDetail, ConFormatContractDetailRepository>
		implements ConFormatContractDetailService {
	
	@Autowired
	private CED CED;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ConFormatContractDetail saveOrUpdateFormatContractDetail(ConFormatContractDetail detail) throws Exception {
		ConFormatContractDetail entity = null ;
        EmpDto empDto = CED.getLoginUser();
        if(ObjectHelper.isNotEmpty(detail.getId())){
            entity = this.customReposity.findOne(detail.getId());
            entity.setUpdateBy(empDto.getEmpCd());
            entity.setUpdateOrgCd(empDto.getOrgCd());
        }else{
            entity = new ConFormatContractDetail();
            entity.setCreateBy(empDto.getEmpCd());
            entity.setCreateOrgCd(empDto.getOrgCd());
        }
        BeanUtils.copyProperties(detail, entity,new String[]{"id","createTime","isDeleted","createBy","createOrgCd","version"});
        entity = this.saveOrUpdateEntity(entity);
        return entity;
	}
	
	@Override
	public List<ConFormatContractDetail> findByFormatContractApplyId(String applyId) {
		return this.customReposity.findByFormatContractApplyIdAndIsDeletedFalse(applyId);
	}

}
