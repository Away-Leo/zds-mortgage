package com.zdsoft.finance.casemanage.material.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.material.entity.CaseMaterialList;
import com.zdsoft.finance.casemanage.material.repository.CaseMaterialListRepository;
import com.zdsoft.finance.casemanage.material.service.CaseMaterialListService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseMaterialListServiceImpl.java
 * @Package:com.zdsoft.finance.casemanage.material.service.impl
 * @Description:案件资料清单服务接口实现
 * @author: gonght
 * @date:2017年1月15日 下午2:29:37
 * @version:v1.0
 */
@Service("caseMaterialListService")
public class CaseMaterialListServiceImpl extends BaseServiceImpl<CaseMaterialList, CaseMaterialListRepository> implements CaseMaterialListService {
    
    @Log
    private Logger logger;
    
    @Autowired
    private CED CED;
    
    @Autowired
    private CaseApplyService caseApplyService;

    @Override
    public Page<Map<String, Object>> findCaseMaterialList(PageRequest pageRequest, List<QueryObj> li) throws Exception {
        StringBuffer _sql = new StringBuffer();
        _sql.append("SELECT cml.id cmlid,cml.materiaTypeName,cml.materiaName,cmla.id,cmla.attachmentId,cmla.attachmentName,cmla.operatorNm,cmla.operatorTime,cmla.position,cmla.source FROM case_material_list cml ")
        .append(" LEFT JOIN case_material_list_atta cmla ON cml.id = cmla.caseMaterialListId ")
        .append(" WHERE 1=1 ");
        StringBuffer _extendSql = new StringBuffer(" order by cml.id,cmla.createTime desc ");
        return this.customReposity.getListObjectBySql(pageRequest, li, _sql, _extendSql);
    }

    @Override
    @Transactional
    public CaseMaterialList saveCredit(CaseMaterialList entity) throws Exception {
        
        if (ObjectHelper.isEmpty(entity.getCaseApplyId())) {
            throw new BusinessException("10010004", "案件Id为空");
        }

        if (ObjectHelper.isEmpty(entity.getMaterialListId())) {
            throw new BusinessException("10010004", "案件产品资料清单Id为空");
        }

        // 当前操作人
        EmpDto emp = CED.getLoginUser();
        // 当前操作时间
        Long currDateTimeLong = DateHelper.dateToLong(DateHelper.getCurrentDateTime(), DateHelper.DATE_LONG_SIMPLE_FORMAT);
        logger.info(emp.getEmpNm() + "在" + currDateTimeLong + ",初始化了" + entity.getCaseApplyId() + "案件下的" + entity.getMaterialListId() + "产品资料清单");
        
        if (ObjectHelper.isNotEmpty(entity.getId())) {
            CaseMaterialList oldCaseMaterialList = this.findOne(entity.getId());
            
            //案件id和产品资料清单id不允许修改
            //资料所属分类Code materiaTypeCode;
            
            //资料所属分类名称 materiaTypeName;

            //资料类别code materiaCode;

            //资料类型名称 materiaName;

            //对应产品资料清单id(与prct_materialList弱关联),暂时不用 materialListId;

            // 添加修改人、修改人机构，用于数据权限查询
            oldCaseMaterialList.setUpdateBy(emp.getEmpCd());
            oldCaseMaterialList.setUpdateOrgCd(emp.getOrgCd());
            entity = this.updateEntity(oldCaseMaterialList);
        } else {

            //关联案件 caseApply;
            CaseApply caseApply = caseApplyService.findOne(entity.getCaseApplyId());
            if(ObjectHelper.isEmpty(caseApply)) {
                throw new BusinessException("10010004", "指定的案件不存在");
            }            
            entity.setCaseApply(caseApply);

            //资料所属分类Code materiaTypeCode;

            //资料所属分类名称 materiaTypeName;

            //资料类别code materiaCode;

            //资料类型名称 materiaName;

            //对应产品资料清单id(与prct_materialList弱关联),暂时不用 materialListId;

            // 添加创建人、添加机构，用于数据权限查询
            entity.setCreateBy(emp.getEmpCd());
            entity.setCreateOrgCd(emp.getOrgCd());
            entity = this.saveEntity(entity);
        }
        return entity;
    }

	@Override
	public List<CaseMaterialList> findByMateriaCode(String materiaCode) throws Exception {
		
		if(ObjectHelper.isEmpty(materiaCode)) {
            throw new BusinessException("10010004", "案件资料清单小类代码为空");
        }
		
		return this.customReposity.findByMateriaCode(materiaCode);
	}

	@Override
	public List<CaseMaterialList> findByMaterialListId(String materialListId) throws Exception {
		
		if(ObjectHelper.isEmpty(materialListId)) {
            throw new BusinessException("10010004", "产品资料清单Id为空");
        }
		
		return this.customReposity.findByMaterialListId(materialListId);
	}

}
