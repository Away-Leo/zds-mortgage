package com.zdsoft.finance.casemanage.material.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.material.entity.CaseMaterialList;
import com.zdsoft.finance.casemanage.material.entity.CaseMaterialListAtta;
import com.zdsoft.finance.casemanage.material.repository.CaseMaterialListAttaRepository;
import com.zdsoft.finance.casemanage.material.service.CaseMaterialListAttaService;
import com.zdsoft.finance.casemanage.material.service.CaseMaterialListService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.product.entity.MaterialList;
import com.zdsoft.finance.product.service.MateriaListService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseMaterialListAttaServiceImpl.java
 * @Package:com.zdsoft.finance.casemanage.material.service.impl
 * @Description:案件资料清单附件服务接口实现
 * @author: gonght
 * @date:2017年1月15日 下午2:51:51
 * @version:v1.0
 */
@Service("caseMaterialListAttaService")
public class CaseMaterialListAttaServiceImpl extends
		BaseServiceImpl<CaseMaterialListAtta, CaseMaterialListAttaRepository> implements CaseMaterialListAttaService {

	@Log
	private Logger logger;

	@Autowired
	private CED CED;

	@Autowired
	private CaseMaterialListService caseMaterialListService;
	
	@Autowired
	private CaseApplyService caseApplyService;
	
	@Autowired
	private MateriaListService materiaListService;

	@Override
	@Transactional
	public void save(CaseMaterialListAtta caseMaterialListAtta) throws Exception {
		//附件名称(解析后对应资料清单),附件
		logger.info("上传的附件ids={}", caseMaterialListAtta.getAttachmentId());
		logger.info("上传的附件数量共={}", caseMaterialListAtta.getAttachmentId().split(",").length);
		logger.info("关联资料清单id={}", caseMaterialListAtta.getCaseMaterialListId());
		
		logger.info("本次上传资料清单关联的案件Id是",caseMaterialListAtta.getCaseApplyId());
		CaseApply caseApply = null;
		if (ObjectHelper.isNotEmpty(caseMaterialListAtta.getCaseApplyId())) {
			caseApply = caseApplyService.findOne(caseMaterialListAtta.getCaseApplyId());
			if (ObjectHelper.isEmpty(caseApply)) {
				throw new BusinessException("10010004", "附件对应的案件对象找不到");
			}
		} else {
			throw new BusinessException("10010004", "附件对应的案件Id为空");
		}
		
		//案件对应的产品id+（包括资料中文名称、助记码、数字助记码）进行查找，资料清单对象
		String productSubtypeId = caseApply.getProductSubtypeId();//案件产品id

		//当前操作人和时间
		EmpDto empDto = CED.getLoginUser();		
		Long currentDateTime = DateHelper.dateToLong(DateHelper.getCurrentDateTime(), DateHelper.DATE_LONG_SIMPLE_FORMAT);
		// 获取对应的附件集合
		String[] attachmentIds = caseMaterialListAtta.getAttachmentId().split(",");
		List<AttachmentDto> attachmentDtos = CED.findAttachmentByIds(Arrays.asList(attachmentIds));
		for (AttachmentDto attachmentDto : attachmentDtos) {
			logger.info("附件名称={}", attachmentDto.getFileLabel());
			// 通过名字解析对应的资料清单 助记码+文件名
			String[] fileName = attachmentDto.getFileLabel().split("[+]");
			
			CaseMaterialListAtta entity = new CaseMaterialListAtta();
			
			if(fileName.length==2){
				/**
				 * 附件.文件名(冗余)
				 */
				entity.setAttachmentName(fileName[1]);
				
				//通过接口获取id,按照产品类型和唯一编码（包括资料中文名称、助记码、数字助记码）进行查找
				//TODO:后面考虑用sql方式查询
//				MaterialList materialList = materiaListService.findByProductAndClass(productSubtypeId, fileName[0]);
				//
//				if(ObjectHelper.isNotEmpty(materialList)){
//					List<CaseMaterialList> caseMaterialLists = caseMaterialListService.findByMaterialListId(materialList.getId());
//					if(ObjectHelper.isNotEmpty(caseMaterialLists)) {
//						/**
//						 * 关联案件资料清单
//						 */
//						entity.setCaseMaterialList(caseMaterialLists.get(0));
//					}
//				} else {
//					//throw new BusinessException("10010004", "附件名称解析后没有匹配的资料类型");
//				}
			}
			
			/**
			 * 是否定位
			 */
			entity.setPosition(caseMaterialListAtta.getPosition());
			/**
			 * 定位地址
			 */
			entity.setPositionPath(caseMaterialListAtta.getPositionPath());
			/**
			 * 来源
			 */
			entity.setSource(caseMaterialListAtta.getSource());

			/**
			 * 附件.Id
			 */
			entity.setAttachmentId(attachmentDto.getId());

			//附件名称
			if(ObjectHelper.isEmpty(entity.getAttachmentName())){
				entity.setAttachmentName(attachmentDto.getFileLabel());
			}

			/**
			 * 附件.上传人Code(冗余)
			 */
			entity.setOperatorCd(empDto.getEmpCd());

			/**
			 * 附件.上传人名称(冗余)
			 */
			entity.setOperatorNm(empDto.getEmpNm());

			/**
			 * 附件.上传时间(冗余)
			 */
			entity.setOperatorTime(currentDateTime);
			/**
			 * 业务表单id
			 */
			entity.setBusinessId(caseMaterialListAtta.getBusinessId());

			/**
			 * 附件状态(非正式、正式)
			 */
			entity.setState(caseMaterialListAtta.getState());
			
			this.saveOrUpdateEntity(entity);
			
			
			logger.info("拥有者Code={}", attachmentDto.getOwner());
			logger.info("", empDto.getEmpNm());
		}
		logger.info("操作时间={}",currentDateTime);
	}

	@Override
	@Transactional
	public void saveCustomerCreditAtta(CaseMaterialListAtta caseMaterialListAtta) throws Exception {
		
		//文件类型(对应资料清单)，附件
		logger.info("上传的附件ids={}", caseMaterialListAtta.getAttachmentId());
		logger.info("上传的附件数量共={}", caseMaterialListAtta.getAttachmentId().split(",").length);
		logger.info("关联资料清单 小类code={}", caseMaterialListAtta.getMateriaCode());
		logger.info("资料清单附件对应的业务表单id={}", caseMaterialListAtta.getBusinessId());

		CaseMaterialList caseMaterialList = null;
//		if (ObjectHelper.isNotEmpty(caseMaterialListAtta.getCaseMaterialListId())) {
//			caseMaterialList = caseMaterialListService.findOne(caseMaterialListAtta.getCaseMaterialListId());
//			if (ObjectHelper.isEmpty(caseMaterialList)) {
//				throw new BusinessException("10010004", "附件对应的案件资料清单类型对象找不到");
//			}
//		} else {
//			throw new BusinessException("10010004", "附件对应的案件资料清单类型Id为空");
//		}
		
		//通过资料清单小类类别code，获取对应的案件资料清单类型对象
		if (ObjectHelper.isNotEmpty(caseMaterialListAtta.getMateriaCode())) {
			List<CaseMaterialList> caseMaterialLists = caseMaterialListService.findByMateriaCode(caseMaterialListAtta.getMateriaCode());
			if (ObjectHelper.isEmpty(caseMaterialLists)) {
				throw new BusinessException("10010004", "附件对应的案件资料清单类型对象找不到");
			} else {
				caseMaterialList = caseMaterialLists.get(0);
			}
		} else {
			throw new BusinessException("10010004", "附件对应的案件资料清单类型Id为空");
		}
		
		//清除原有数据，添加新数据
		this.customReposity.deleteByCaseMaterialListIdAndBusinessId(caseMaterialListAtta.getCaseMaterialListId(), caseMaterialListAtta.getBusinessId());

		//当前操作人和时间
		EmpDto empDto = CED.getLoginUser();		
		Long currentDateTime = DateHelper.dateToLong(DateHelper.getCurrentDateTime(), DateHelper.DATE_LONG_SIMPLE_FORMAT);
		// 获取对应的附件集合，遍历写入到资料清单附件表中去
		String[] attachmentIds = caseMaterialListAtta.getAttachmentId().split(",");
		List<AttachmentDto> attachmentDtos = CED.findAttachmentByIds(Arrays.asList(attachmentIds));
		for (AttachmentDto attachmentDto : attachmentDtos) {

			CaseMaterialListAtta entity = new CaseMaterialListAtta();
			
			/**
			 * 关联案件资料清单
			 */
			entity.setCaseMaterialList(caseMaterialList);
			
			/**
			 * 是否定位
			 */
			entity.setPosition(caseMaterialListAtta.getPosition());
			/**
			 * 定位地址
			 */
			entity.setPositionPath(caseMaterialListAtta.getPositionPath());
			/**
			 * 来源
			 */
			entity.setSource(caseMaterialListAtta.getSource());

			/**
			 * 附件.Id
			 */
			entity.setAttachmentId(attachmentDto.getId());

			/**
			 * 附件.文件名(冗余)
			 */
			entity.setAttachmentName(attachmentDto.getFileLabel());

			/**
			 * 附件.上传人Code(冗余)
			 */
			entity.setOperatorCd(empDto.getEmpCd());

			/**
			 * 附件.上传人名称(冗余)
			 */
			entity.setOperatorNm(empDto.getEmpNm());

			/**
			 * 附件.上传时间(冗余)
			 */
			entity.setOperatorTime(currentDateTime);
			/**
			 * 业务表单id
			 */
			entity.setBusinessId(caseMaterialListAtta.getBusinessId());

			/**
			 * 附件状态(非正式、正式)
			 */
			entity.setState(caseMaterialListAtta.getState());
			
			this.saveOrUpdateEntity(entity);
		}
		
		logger.info("操作时间={}",currentDateTime);
	}

	@Override
	@Transactional
	public void saveCaseApplyCreditAtta(String caseApplyId, String businessId, String productId, String materiaCode,
			String[] attachmentIds) throws Exception {
		
	}

	@Override
	public List<CaseMaterialListAtta> findByBusinessIdAndMateriaCode(String businessId, String materiaCode) throws Exception {
		
		
		return null;
	}
}
