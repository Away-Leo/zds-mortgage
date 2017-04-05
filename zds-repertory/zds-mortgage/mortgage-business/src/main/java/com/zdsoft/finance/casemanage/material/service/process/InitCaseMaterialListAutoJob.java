package com.zdsoft.finance.casemanage.material.service.process;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.finance.casemanage.material.entity.CaseMaterialList;
import com.zdsoft.finance.casemanage.material.service.CaseMaterialListService;
import com.zdsoft.finance.marketing.entity.BeforehandApply;
import com.zdsoft.finance.marketing.service.BeforehandApplyService;
import com.zdsoft.finance.product.entity.MaterialList;
import com.zdsoft.finance.product.service.MateriaListService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.service.JavaDelegate;
import com.zdsoft.framework.core.common.util.ObjectHelper;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:InitCaseMaterialListAutoJob.java
 * @Package:com.zdsoft.finance.casemanage.material.service.process
 * @Description:初始化产品资料清单
 * @author: xj
 * @date:2017年1月17日 下午8:26:20
 * @version:v1.0
 */
@Service
@AutoJob(label = "案件资料清单初始化", resource = "com.zdsoft.finance.projectatta.service.process.initCaseMaterialListAuto.autoJob")
@Lazy(false)
public class InitCaseMaterialListAutoJob implements JavaDelegate {
	@Autowired
	private CaseMaterialListService caseMaterialListService;
	@Autowired
	private BeforehandApplyService beforehandApplyService;
	@Autowired
	private MateriaListService materiaListService;
	@Log
	private Logger log;
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		log.info("开始初始化资料清单");
		String caseApplyId = (String)execution.getVariable("projectId");
		log.info("caseApplyId:"+caseApplyId);
		//开始查询产品资料清单
		//获取产品
		BeforehandApply beforehandApply = beforehandApplyService.findOne(caseApplyId);
		//产品id
		String productId = beforehandApply.getProductSubtypeId();
		List<MaterialList> materialLists = materiaListService.findByProductId(productId);
		if(ObjectHelper.isNotEmpty(materialLists)){
			for (MaterialList materialList : materialLists) {
				CaseMaterialList caseMaterialList = new CaseMaterialList();
				//案件id
				caseMaterialList.setCaseApplyId(caseApplyId);
				//资料所属分类
				caseMaterialList.setMateriaTypeCode(materialList.getMateriaTypeCode());
				//资料所属分类名称
				caseMaterialList.setMateriaTypeName(materialList.getMateriaTypeName());
				//资料类别code
				caseMaterialList.setMateriaCode(materialList.getMateriaCode());
				//资料类型名称
				caseMaterialList.setMateriaName(materialList.getMateriaName());
				
				caseMaterialList.setMaterialListId(materialList.getId());
				caseMaterialListService.saveCredit(caseMaterialList);
			}
		}
		log.info("开始初始化资料清单结束");
		
	}

}
