package com.zdsoft.finance.casemanage.handleMortgage.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.handleMortgage.entity.MaterialPromise;
import com.zdsoft.finance.casemanage.handleMortgage.repository.MaterialPromiseRepository;
import com.zdsoft.finance.casemanage.handleMortgage.service.MaterialPromiseService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MaterialPromiseServiceImpl.java 
 * @ClassName: MaterialPromiseServiceImpl 
 * @Description: 后补资料承诺ServiceImpl
 * @author zhoushichao 
 * @date 2017年2月18日 下午2:17:29 
 * @version V1.0 
 */ 
@Service("materialPromiseService")
public class MaterialPromiseServiceImpl extends BaseServiceImpl<MaterialPromise, MaterialPromiseRepository>
			implements MaterialPromiseService{

	@Override
	public List<MaterialPromise> findByCaseApplyId(String caseApplyId) throws Exception {
		return this.customReposity.findByCaseApplyId(caseApplyId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMaterialPromiseList(List<MaterialPromise> materialPromiseList) throws Exception {
		for (MaterialPromise materialPromise : materialPromiseList) {
			MaterialPromise material = new MaterialPromise();
			if (ObjectHelper.isNotEmpty(materialPromise.getId())) {
				material = this.customReposity.findOne(materialPromise.getId());
			}
			material.setCaseApplyId(materialPromise.getCaseApplyId());
			material.setMaterialTypeCode(materialPromise.getMaterialTypeCode());
			material.setMaterialTypeName(materialPromise.getMaterialTypeName());
			material.setpMaterialTypeCode(materialPromise.getpMaterialTypeCode());
			material.setpMaterialTypeName(materialPromise.getpMaterialTypeName());
			material.setPredictDate(materialPromise.getPredictDate());
			material.setOperatorCode(materialPromise.getOperatorCode());
			material.setOperatorName(materialPromise.getOperatorName());
			material.setPromiseDate(materialPromise.getPromiseDate());
			material.setPromiseRemark(materialPromise.getPromiseRemark());
			material.setUploadDate(materialPromise.getUploadDate());
			material.setUploadPersonCode(materialPromise.getUploadPersonCode());
			material.setUploadPersonName(materialPromise.getUploadPersonName());
			material.setAttachmentId(materialPromise.getAttachmentId());
			if (ObjectHelper.isEmpty(material.getIsUpload())) {
				material.setIsUpload(MaterialPromise.NOT_COMPLETE);
			}
			this.saveOrUpdateEntity(material);
		}
	}

	@Override
	public List<MaterialPromise> queryByCaseApplyIdAndMaterialTypeCode(String caseApplyId, List<String> codes)
			throws Exception {
		return this.customReposity.queryByCaseApplyIdAndMaterialTypeCode(caseApplyId, codes);
	}

	@Override
	public MaterialPromise findByCaseApplyIdAndMaterialTypeCode(String caseApplyId, String materialTypeCode)
			throws Exception {
		return  this.customReposity.findByCaseApplyIdAndMaterialTypeCode(caseApplyId, materialTypeCode);
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public void deleteById(String id) {
		 this.customReposity.delete(id);
		
	}

}
