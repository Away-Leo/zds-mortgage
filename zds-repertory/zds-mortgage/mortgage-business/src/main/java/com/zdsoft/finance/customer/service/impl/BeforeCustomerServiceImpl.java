package com.zdsoft.finance.customer.service.impl;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.impl.CustomCommon;
import com.zdsoft.finance.customer.dto.CaseRelationCustomerDto;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.customer.repository.BeforeCustomerRepository;
import com.zdsoft.finance.customer.service.BeforeCustomerService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BeforeCustomerServiceImpl.java 
 * @ClassName: BeforeCustomerServiceImpl 
 * @Description: 客户Service实现
 * @author jingyh 
 * @date 2017年2月24日 下午2:16:28 
 * @version V1.0
 */
@Service("beforeCustomerService")
public class BeforeCustomerServiceImpl extends BaseServiceImpl<BeforeCustomer, BeforeCustomerRepository> 
			implements BeforeCustomerService {
	
	@Autowired
	private CED CED;
	/**
	 * 
	 * @Title: findByCredentialTypeAndCredentialNo 
	 * @Description: 根据证件类型和证件号查询客户对象
	 * @author jingyh 
	 * @param credentialType
	 * @param credentialNo
	 * @param caseApplyId
	 * @return
	 */
	@Override
	public BeforeCustomer findByCredentialTypeAndCredentialNo(String credentialType,String credentialNo,String caseApplyId){
		return this.customReposity.findByCredentialTypeAndCredentialNo(credentialType, credentialNo,caseApplyId);
	}
	
	@Override
	public List<CaseRelationCustomerDto> findRelationCustomerByCaseApplyId(String caseApplyId) throws Exception {
		List<Map<String, Object>> queryResult = this.customReposity.findRelationCustomerByCaseApplyId(caseApplyId);
		List<CaseRelationCustomerDto> result = null;
		if (ObjectHelper.isNotEmpty(queryResult)) {
			result = new ArrayList<CaseRelationCustomerDto>();
			List<Object> list = CustomCommon.convert(CaseRelationCustomerDto.class, queryResult);
			for (Object mapObj : list) {
				// 查询配偶参与类型
				result.add((CaseRelationCustomerDto)mapObj);
			}
			// 循环处理，获得配偶参与人类型
			for (CaseRelationCustomerDto dto : result) {
				if(ObjectHelper.isNotEmpty(dto.getActualUsePerson())){
					dto.setActualUsePersonName(CED.loadSimpleCodeNameByFullCode(dto.getActualUsePerson()));
				}else{
					dto.setActualUsePersonName("否");
				}
				if(ObjectHelper.isNotEmpty(dto.getCredentialType())){
					dto.setCredentialTypeName(CED.loadSimpleCodeNameByFullCode(dto.getCredentialType()));
				}
				if(ObjectHelper.isNotEmpty(dto.getJoinType())){
					dto.setJoinTypeName(CED.loadSimpleCodeNameByFullCode(dto.getJoinType()));
				}
				if (ObjectHelper.isNotEmpty(dto.getSpouseCustomerId())) {
					for (CaseRelationCustomerDto temp: result) {
						if (dto.getSpouseCustomerId().equals(temp.getCustomerId())) {
							dto.setSpouseJoinType(temp.getJoinType());
							dto.setSpouseJoinTypeName(CED.loadSimpleCodeNameByFullCode(temp.getJoinType())+"配偶");
							break;
						}
					}
				}
			}
		}
		return result;
	}
	
}
