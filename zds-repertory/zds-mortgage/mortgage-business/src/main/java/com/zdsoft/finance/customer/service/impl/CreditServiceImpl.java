package com.zdsoft.finance.customer.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.material.entity.CaseMaterialListAtta;
import com.zdsoft.finance.casemanage.material.service.CaseMaterialListAttaService;
import com.zdsoft.finance.customer.entity.Credit;
import com.zdsoft.finance.customer.repository.CreditRepository;
import com.zdsoft.finance.customer.service.CreditService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CreditServiceImpl.java
 * @Package:com.zdsoft.finance.customer.service.impl
 * @Description:客户征信记录 服务接口实现
 * @author: gonght
 * @date:2017年1月16日 下午3:06:44
 * @version:v1.0
 */
@Service("creditService")
public class CreditServiceImpl extends BaseServiceImpl<Credit, CreditRepository> implements CreditService {

    @Log
    private Logger logger;

    @Autowired
    private CED CED;

    @Autowired
    private CaseApplyService caseApplyService;
    
    @Autowired
    private CaseMaterialListAttaService caseMaterialListAttaService;

    @Override
    @Transactional
    public Credit saveCredit(Credit entity) throws Exception {

        if (ObjectHelper.isEmpty(entity.getCaseApplyId())) {
            throw new BusinessException("10010004", "案件Id为空");
        }

        if (ObjectHelper.isEmpty(entity.getCustomerId())) {
            throw new BusinessException("10010004", "客户Id为空");
        }

        // 当前操作人
        EmpDto emp = CED.getLoginUser();
        // 当前操作时间
        Long currDateTimeLong = DateHelper.dateToLong(DateHelper.getCurrentDateTime(),
                DateHelper.DATE_LONG_SIMPLE_FORMAT);
        logger.info(emp.getEmpNm() + "在" + currDateTimeLong + ",添加了" + entity.getCaseApplyId() + "案件下的"
                + entity.getCustomerId() + "客户征信");

        if (ObjectHelper.isNotEmpty(entity.getId())) {
            Credit oldCredit = this.findOne(entity.getId());

            // 案件id和客户id不允许修改

            // 添加修改人、修改人机构，用于数据权限查询
            oldCredit.setUpdateBy(emp.getEmpCd());
            oldCredit.setUpdateOrgCd(emp.getOrgCd());
            entity = this.updateEntity(oldCredit);
        } else {
            // 案件ID caseApplyId;
            // 关联案件
            CaseApply caseApply = caseApplyService.findOne(entity.getCaseApplyId());
            if (ObjectHelper.isEmpty(caseApply)) {
                throw new BusinessException("10010004", "指定的案件不存在");
            }
            entity.setCaseApply(caseApply);

            // 客户ID customerId;

            // 客户名称(冗余,由于客户逻辑问题) customerName;

            // 客户证件类型code(冗余,由于客户逻辑问题) credentialType;

            // 客户证件类型名称(冗余,由于客户逻辑问题) credentialTypeName;

            // 证件号码(冗余,由于客户逻辑问题) credentialNo;

            // 参与类型code(冗余,由于客户逻辑问题) joinType;

            // 参与类型名字(冗余,由于客户逻辑问题) joinTypeName;

            // 是否实际用款人code(冗余,由于客户逻辑问题) actualUsePerson;

            // 是否实际用款人中文(冗余,由于客户逻辑问题) actualUsePersonName;

            // 征信状态 creditStatus;
            if (ObjectHelper.isEmpty(entity.getCreditStatus())) {
                entity.setCreditStatus("01");// 为空时，初始化状态
            }

            // 征信录入时间 recordDate;

            // 添加创建人、添加机构，用于数据权限查询
            entity.setCreateBy(emp.getEmpCd());
            entity.setCreateOrgCd(emp.getOrgCd());
            entity = this.saveEntity(entity);
        }
        return entity;
    }

    @Override
    public Credit findByCustomerId(String customerId) {
        String hql = "from Credit where customerId=:customerId";
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("customerId", customerId);
        List<Credit> list = this.customReposity.findByHql(hql, condition);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

	@Override
	@Transactional
	public void saveCustomerCreditAtta(CaseMaterialListAtta atta) throws Exception {
		
		Credit credit = null;
		if(ObjectHelper.isNotEmpty(atta.getBusinessId())){
			credit = this.findOne(atta.getBusinessId());
		} else {
			throw new BusinessException("10010004", "客户征信记录找不到");
		}
		
		//保存附件
		caseMaterialListAttaService.saveCustomerCreditAtta(atta);
		
		//判断附件类别
		if(atta.getMateriaCode().equals("")) {
			credit.setCreditStatus("1");
		} else if(atta.getMateriaCode().equals("")) {
			credit.setCreditStatus("2");
		}		
		//根据类型，更新客户征信状态
		
		this.updateEntity(credit);
	}

}
