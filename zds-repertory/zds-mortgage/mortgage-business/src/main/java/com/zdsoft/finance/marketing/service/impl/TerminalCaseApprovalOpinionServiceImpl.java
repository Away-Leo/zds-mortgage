package com.zdsoft.finance.marketing.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.busiform.service.BusiFormService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.enums.busiform.ApplyModelTypeEnum;
import com.zdsoft.finance.customer.entity.BeforeContact;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.service.BeforeContactService;
import com.zdsoft.finance.customer.service.BeforeCustomerService;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.marketing.entity.BeforehandApply;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnumSimpleCodeEnum;
import com.zdsoft.finance.marketing.entity.ExportTerminalCaseVo;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.entity.PledgeInfo;
import com.zdsoft.finance.marketing.entity.TerminalCaseApprovalOpinion;
import com.zdsoft.finance.marketing.repository.CaseApplyRepository;
import com.zdsoft.finance.marketing.repository.TerminalCaseApprovalOpinionRepository;
import com.zdsoft.finance.marketing.service.BeforehandApplyService;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.service.PledgeInfoService;
import com.zdsoft.finance.marketing.service.TerminalCaseApprovalOpinionService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: TerminalCaseApprovalOpinionServiceImpl.java
 * @ClassName: TerminalCaseApprovalOpinionServiceImpl
 * @Description: 通过案件id来查询终端进件审批意见服务实现
 * @author xiongpan
 * @date 2017年3月4日 上午11:17:33
 * @version V1.0
 */
@Service
public class TerminalCaseApprovalOpinionServiceImpl
		extends BaseServiceImpl<TerminalCaseApprovalOpinion, CustomRepository<TerminalCaseApprovalOpinion, String>>
		implements TerminalCaseApprovalOpinionService {
	@Autowired
	private TerminalCaseApprovalOpinionRepository terminalCaseApprovalOpinionRepository;
	@Autowired
	private CaseApplyRepository caseApplyRepository;
	@Autowired
	private BeforehandApplyService beforehandApplyService;
	@Autowired
	private CaseApplyService caseApplyService;
	@Autowired
	private BeforeCustomerService beforeCustomerService;
	@Autowired
	private BeforePersonalCustomerService beforePersonalCustomerService;
	@Autowired
	private HousePropertyService housePropertyService;
	@Autowired
	private PledgeInfoService pledgeInfoService;
	@Autowired
	private BeforeContactService beforeContactService;
	@Autowired
	private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;
	@Autowired
	private CED CED;
	@Autowired
	private BusiFormService busiFormService;
	

	/**
	 * 
	 * @Title: findByCaseApplyId
	 * @Description: 通过案件id来查询终端进件审批意见
	 * @author xiongpan
	 */
	@Override
	public TerminalCaseApprovalOpinion findByCaseApplyId(String caseApplyId) {
		return terminalCaseApprovalOpinionRepository.findByCaseApplyId(caseApplyId);
	}

	/**
	 * 
	 * @Title: saveTerminalCaseApprovalOpinion
	 * @Description: 批量保存终端进件审批意见
	 * @author xiongpan
	 * @param id
	 *            需要保存的所有案件的id字符串
	 * @param terminalCaseApprovalOpinion
	 *            需要保存的审批意见信息
	 */
	@Override
	@Transactional
	public void saveTerminalCaseApprovalOpinion(String ids, TerminalCaseApprovalOpinion terminalCaseApprovalOpinion) {
		String[] caseApplyIds = null;
		if (StringUtils.isNotEmpty(ids)) {
			ids = ids.substring(0, ids.length() - 1);
			caseApplyIds = ids.split(",");
			for (String caseApplyId : caseApplyIds) {
				CaseApply caseApply = caseApplyRepository.findOne(caseApplyId);
				try {
						TerminalCaseApprovalOpinion terminalCaseApprovalOpinionHas = terminalCaseApprovalOpinionRepository
								.findByCaseApplyId(caseApplyId);
						if (ObjectHelper.isNotEmpty(terminalCaseApprovalOpinionHas)) {
							terminalCaseApprovalOpinionHas.setApprovalResult(terminalCaseApprovalOpinion.getApprovalResult());
							terminalCaseApprovalOpinionHas.setRefuseReason(terminalCaseApprovalOpinion.getRefuseReason());
							terminalCaseApprovalOpinionHas.setOrganizationCd(terminalCaseApprovalOpinion.getOrganizationCd());
							terminalCaseApprovalOpinionHas
							.setOrganizationName(terminalCaseApprovalOpinion.getOrganizationName());
							terminalCaseApprovalOpinionHas.setChannelManagerCd(terminalCaseApprovalOpinion.getChannelManagerCd());
							try {
								String empNm = CED.loadEmployeeByCode(terminalCaseApprovalOpinion.getChannelManagerCd()).getEmpNm();
								terminalCaseApprovalOpinionHas.setChannelManagerName(empNm);
								caseApply.setDevelopmentDepartmentName(empNm);
							} catch (Exception e) {
								logger.error("根据员工code未找到员工姓名", e);
								e.printStackTrace();
							}
							terminalCaseApprovalOpinionHas.setMo(terminalCaseApprovalOpinion.getMo());
							terminalCaseApprovalOpinionHas.setCaseApplyId(caseApplyId);
							terminalCaseApprovalOpinionRepository.updateEntity(terminalCaseApprovalOpinionHas);
							caseApply.setDevelopmentManagerCode(terminalCaseApprovalOpinion.getChannelManagerCd());
							

						} else {
							TerminalCaseApprovalOpinion terminalCaseApprovalOpinionNO = new TerminalCaseApprovalOpinion();
							terminalCaseApprovalOpinionNO.setApprovalResult(terminalCaseApprovalOpinion.getApprovalResult());
							terminalCaseApprovalOpinionNO.setRefuseReason(terminalCaseApprovalOpinion.getRefuseReason());
							terminalCaseApprovalOpinionNO.setOrganizationCd(terminalCaseApprovalOpinion.getOrganizationCd());
							terminalCaseApprovalOpinionNO
							.setOrganizationName(terminalCaseApprovalOpinion.getOrganizationName());
							terminalCaseApprovalOpinionNO.setChannelManagerCd(terminalCaseApprovalOpinion.getChannelManagerCd());
							try {
								String empNm = CED.loadEmployeeByCode(terminalCaseApprovalOpinion.getChannelManagerCd()).getEmpNm();
								terminalCaseApprovalOpinionNO.setChannelManagerName(empNm);
								caseApply.setDevelopmentDepartmentName(empNm);
							} catch (Exception e) {
								logger.error("根据员工code未找到员工姓名", e);
								e.printStackTrace();
							}
							terminalCaseApprovalOpinionNO.setMo(terminalCaseApprovalOpinion.getMo());
							terminalCaseApprovalOpinionNO.setCaseApplyId(caseApplyId);
							terminalCaseApprovalOpinionRepository.saveEntity(terminalCaseApprovalOpinionNO);
							caseApply.setDevelopmentManagerCode(terminalCaseApprovalOpinion.getChannelManagerCd());

						}
						
						caseApplyRepository.updateEntity(caseApply);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}
	}

	/**
	 * 
	 * @Title: submitTerminalCaseApprovalOpinion
	 * @Description: 批量提交终端进件审批意见
	 * @author xiongpan
	 * @param id
	 *            需要保存的所有案件的id字符串
	 * @param terminalCaseApprovalOpinion
	 *            需要保存的审批意见信息
	 */
	@Override
	@Transactional
	public void submitTerminalCaseApprovalOpinion(String ids, TerminalCaseApprovalOpinion terminalCaseApprovalOpinion) {
		String[] caseApplyIds = null;
		if (StringUtils.isNotEmpty(ids)) {
			ids = ids.substring(0, ids.length() - 1);
			caseApplyIds = ids.split(",");
			for (String caseApplyId : caseApplyIds) {
				CaseApply caseApply = caseApplyRepository.findOne(caseApplyId);
				try {
						TerminalCaseApprovalOpinion terminalCaseApprovalOpinionHas = terminalCaseApprovalOpinionRepository
								.findByCaseApplyId(caseApplyId);
						if (ObjectHelper.isNotEmpty(terminalCaseApprovalOpinionHas)) {
							terminalCaseApprovalOpinionHas.setApprovalResult(terminalCaseApprovalOpinion.getApprovalResult());
							terminalCaseApprovalOpinionHas.setRefuseReason(terminalCaseApprovalOpinion.getRefuseReason());
							terminalCaseApprovalOpinionHas.setOrganizationCd(terminalCaseApprovalOpinion.getOrganizationCd());
							terminalCaseApprovalOpinionHas
							.setOrganizationName(terminalCaseApprovalOpinion.getOrganizationName());
							terminalCaseApprovalOpinionHas.setChannelManagerCd(terminalCaseApprovalOpinion.getChannelManagerCd());
							try {
								String empNm = CED.loadEmployeeByCode(terminalCaseApprovalOpinion.getChannelManagerCd()).getEmpNm();
								terminalCaseApprovalOpinionHas.setChannelManagerName(empNm);
								caseApply.setDevelopmentDepartmentName(empNm);
							} catch (Exception e) {
								logger.error("根据员工code未找到员工姓名", e);
								e.printStackTrace();
							}
							terminalCaseApprovalOpinionHas.setMo(terminalCaseApprovalOpinion.getMo());
							terminalCaseApprovalOpinionHas.setCaseApplyId(caseApplyId);
							terminalCaseApprovalOpinionRepository.updateEntity(terminalCaseApprovalOpinionHas);
							if ("YWDM007002".equals(terminalCaseApprovalOpinionHas.getApprovalResult())) {
								caseApply.setStage(CaseApplyStageEnumSimpleCodeEnum.VETO.value);
							}
							if ("YWDM007001".equals(terminalCaseApprovalOpinionHas.getApprovalResult())) {
								caseApply.setStage(CaseApplyStageEnumSimpleCodeEnum.ACCEPTANCE.value);
								caseApply.setDevelopmentManagerCode(terminalCaseApprovalOpinion.getChannelManagerCd());
								
								this.doPublicBusi(caseApply);
								
							}

						} else {
							TerminalCaseApprovalOpinion terminalCaseApprovalOpinionNO = new TerminalCaseApprovalOpinion();
							terminalCaseApprovalOpinionNO.setApprovalResult(terminalCaseApprovalOpinion.getApprovalResult());
							terminalCaseApprovalOpinionNO.setRefuseReason(terminalCaseApprovalOpinion.getRefuseReason());
							terminalCaseApprovalOpinionNO.setOrganizationCd(terminalCaseApprovalOpinion.getOrganizationCd());
							terminalCaseApprovalOpinionNO
							.setOrganizationName(terminalCaseApprovalOpinion.getOrganizationName());
							terminalCaseApprovalOpinionNO.setChannelManagerCd(terminalCaseApprovalOpinion.getChannelManagerCd());
							try {
								String empNm = CED.loadEmployeeByCode(terminalCaseApprovalOpinion.getChannelManagerCd()).getEmpNm();
								terminalCaseApprovalOpinionNO.setChannelManagerName(empNm);
								caseApply.setDevelopmentDepartmentName(empNm);
							} catch (Exception e) {
								logger.error("根据员工code未找到员工姓名", e);
								e.printStackTrace();
							}
							terminalCaseApprovalOpinionNO.setMo(terminalCaseApprovalOpinion.getMo());
							terminalCaseApprovalOpinionNO.setCaseApplyId(caseApplyId);
							terminalCaseApprovalOpinionRepository.saveEntity(terminalCaseApprovalOpinionNO);
							if ("YWDM007002".equals(terminalCaseApprovalOpinionNO.getApprovalResult())) {
								caseApply.setStage(CaseApplyStageEnumSimpleCodeEnum.VETO.value);
							}
							if ("YWDM007001".equals(terminalCaseApprovalOpinionNO.getApprovalResult())) {
								caseApply.setStage(CaseApplyStageEnumSimpleCodeEnum.ACCEPTANCE.value);
								caseApply.setDevelopmentManagerCode(terminalCaseApprovalOpinion.getChannelManagerCd());
								
								this.doPublicBusi(caseApply);
								
							}

						}
						
						caseApplyRepository.updateEntity(caseApply);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}
	}

	/**
	 * 
	 * @Title: doPublicBusi 
	 * @Description: 提取公共代码共有
	 * @author xiongpan
	 * @param caseApply 案件
	 * @throws Exception 
	 */
	private void doPublicBusi(CaseApply caseApply) throws Exception {
		EmpDto loginUser = CED.getLoginUser();
		//审批通过是设置业务表单
		if(ObjectHelper.isEmpty(caseApply.getBusiForm())){
			BusiForm busiForm = new BusiForm();
			//设置状态
			busiForm.setFormStatus(BusiFormStatus.DRAFT.value);
			//发起人编号
			busiForm.setLaunchEmpCode(loginUser.getEmpCd());
			//发起人名称
			busiForm.setLaunchEmpName(loginUser.getEmpNm());
			//申请时间
			busiForm.setApplyDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			//关联业务表单id
			busiForm.setBusinessEntityId(caseApply.getId());
			//业务编号
			busiForm.setBusinessCode(caseApply.getCaseApplyCode());
			//申请类型
			busiForm.setModelType(ApplyModelTypeEnum.CASE_APPLY.value);
			//关联表单域对象类名 例如表单为Project时 此字段存入的值为Project
			busiForm.setBusinessEntityName(CaseApply.class.getSimpleName());
			//关联组件数据ID 例如 项目表ID 合同ID
			busiForm.setComponentsEntityId(caseApply.getId());
			//关联组件域对象类名 例如表单为Project时 此字段存入的值为Project
			busiForm.setComponentsEntityName(CaseApply.class.getSimpleName());
			busiFormService.saveEntity(busiForm);
			caseApply.setBusiForm(busiForm);
		}
		if(ObjectHelper.isEmpty(caseApply.getCaseApplyCode())){
			try {
				 // 生成案件案号
				String caseApplyCode = CED.resolveExpression("YW000000000003", null);
				logger.debug("新生成的案件号为："+caseApplyCode);
				 caseApply.setCaseApplyCode(caseApplyCode);
			} catch (Exception e) {
				logger.error("平台异常，未解析表达式");
				e.printStackTrace();
			}
		}
		if(ObjectHelper.isEmpty(caseApply.getCaseApplyStatus())){
			caseApply.setCaseApplyStatus(CaseApply.NORMAL);
		}
 		if(ObjectHelper.isEmpty(caseApply.getExaminationStatus())){
 			caseApply.setExaminationStatus(CaseApply.FIRST_INSTANCE);
 		}
		
	}

	/**
	 * 
	 * @Title: terminalCaseDataMap
	 * @Description: 将需要导出的终端进件数据封装
	 * @author xiongpan
	 * @param content
	 *            需要导出的终端进件数据
	 * @return
	 */
	@Override
	@Transactional
	public List<Map<String, Object>> terminalCaseDataMap(String content) {
		List<ExportTerminalCaseVo> ExportTerminalCaseVos = JSON.parseArray(content, ExportTerminalCaseVo.class);
		List<Map<String, Object>> paras = new ArrayList<Map<String, Object>>();

		if (ObjectHelper.isNotEmpty(ExportTerminalCaseVos)) {
			String[] title = new String[] { "主借人", "申请额度", "产品分类", "子产品", "所在楼层", "押品地址", "评估价(元)", "终端", "机构",
					"案件状态" };// 标题
			Map<String, Object> titleMap = new HashMap<String, Object>();
			for (int i = 0; i < title.length; i++) {
				titleMap.put("title" + i, title[i]);
			}
			paras.add(titleMap);

			for (int j = 0; j < ExportTerminalCaseVos.size(); j++) {
				ExportTerminalCaseVo exportTerminalCaseVo = ExportTerminalCaseVos.get(j);

				Map<String, Object> needMap = new HashMap<String, Object>();
				if (ObjectHelper.isNotEmpty(exportTerminalCaseVo.getCUSTOMERNAME())) {
					needMap.put("CUSTOMERNAME", exportTerminalCaseVo.getCUSTOMERNAME());
				} else {
					needMap.put("CUSTOMERNAME", "");
				}
				if (ObjectHelper.isNotEmpty(exportTerminalCaseVo.getAPPLYAMOUNT())) {
					needMap.put("APPLYAMOUNT", exportTerminalCaseVo.getAPPLYAMOUNT());
				} else {
					needMap.put("APPLYAMOUNT", "");
				}
				if (ObjectHelper.isNotEmpty(exportTerminalCaseVo.getPRODUCTTYPENAME())) {
					needMap.put("PRODUCTTYPENAME", exportTerminalCaseVo.getPRODUCTTYPENAME());
				} else {
					needMap.put("PRODUCTTYPENAME", "");
				}
				if (ObjectHelper.isNotEmpty(exportTerminalCaseVo.getPRODUCTSUBTYPENAME())) {
					needMap.put("PRODUCTSUBTYPENAME", exportTerminalCaseVo.getPRODUCTSUBTYPENAME());
				} else {
					needMap.put("PRODUCTSUBTYPENAME", "");
				}
				if (ObjectHelper.isNotEmpty(exportTerminalCaseVo.getPLACEFLOOR())) {
					needMap.put("PLACEFLOOR", exportTerminalCaseVo.getPLACEFLOOR());
				} else {
					needMap.put("PLACEFLOOR", "");
				}
				if (ObjectHelper.isNotEmpty(exportTerminalCaseVo.getHOUSEPROPERTYADDRESS())) {
					needMap.put("HOUSEPROPERTYADDRESS", exportTerminalCaseVo.getHOUSEPROPERTYADDRESS());
				} else {
					needMap.put("HOUSEPROPERTYADDRESS", "");
				}
				if (ObjectHelper.isNotEmpty(exportTerminalCaseVo.getSYNTHESIZEPRICE())) {
					needMap.put("SYNTHESIZEPRICE", exportTerminalCaseVo.getSYNTHESIZEPRICE());
				} else {
					needMap.put("SYNTHESIZEPRICE", "");
				}
				if (ObjectHelper.isNotEmpty(exportTerminalCaseVo.getTERMINALFULLNAME())) {
					needMap.put("TERMINALFULLNAME", exportTerminalCaseVo.getTERMINALFULLNAME());
				} else {
					needMap.put("TERMINALFULLNAME", "");
				}
				if (ObjectHelper.isNotEmpty(exportTerminalCaseVo.getMECHANISMNAME())) {
					needMap.put("MECHANISMNAME", exportTerminalCaseVo.getMECHANISMNAME());
				} else {
					needMap.put("MECHANISMNAME", "");
				}
				if (ObjectHelper.isNotEmpty(exportTerminalCaseVo.getSTAGENM())) {
					needMap.put("STAGENM", exportTerminalCaseVo.getSTAGENM());
				} else {
					needMap.put("STAGENM", "");
				}
				paras.add(needMap);
			}

		}
		return paras;
	}

	/**
	 * 
	 * @Title: getCellValue
	 * @Description: 转换单元格样式
	 * @author xiongpan
	 * @param cell
	 *            单元格
	 * @return
	 * @throws Exception
	 */
	private String getCellValue(Cell cell) throws Exception {
		String cellvalue = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case XSSFCell.CELL_TYPE_BOOLEAN:
				cellvalue = String.valueOf(cell.getBooleanCellValue());
				break;
			case XSSFCell.CELL_TYPE_NUMERIC:
				cellvalue = new BigDecimal(cell.getNumericCellValue()).toPlainString();
				break;
			case XSSFCell.CELL_TYPE_STRING:
				cellvalue = cell.getStringCellValue();
				break;
			case XSSFCell.CELL_TYPE_BLANK:
				break;
			case XSSFCell.CELL_TYPE_ERROR:
				break;
			case XSSFCell.CELL_TYPE_FORMULA:
				break;
			}
		}
		return cellvalue;
	}

	
	/**
	 * 
	 * @Title: importTerminalCases 
	 * @Description: 终端进件导入
	 * @author xiongpan
	 * @param file 导入的Excel文件
	 * @param msg 响应的消息
	 */
	@Override
	@Transactional
	public void importTerminalCases(CommonsMultipartFile file, ResponseMsg msg) {
		//根据指定的文件输入流导入Excel从而产生Workbook对象  
		Workbook wb0 = null;
		try {
			String name = file.getFileItem().getName();
			if(ObjectHelper.isNotEmpty(name)){
				String[] fm = name.split("\\.");
				String nameType = "."+fm[fm.length-1];
				if(".xlsx".equals(nameType)){
					wb0 = new XSSFWorkbook(file.getInputStream());
				}else if(".xls".equals(nameType)){
					wb0 = new HSSFWorkbook(file.getInputStream());
				}else{
					msg.setMsg("文件格式不对,必须是.xlsx或.xls");
					return;
				}
			}
			//获取Excel文档中的第一个表单  
			Sheet sheet1 = wb0.getSheetAt(0);
			int totalRows = sheet1.getPhysicalNumberOfRows();// --获取sheet总行数
			// 从第四行,第2列开始获取数据
			for (int i = 3; i < totalRows; i++) {
				Row row = sheet1.getRow(i);
				if (ObjectHelper.isEmpty(row.getCell(0))) {
					continue;
				}
				int[] cellArray = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
						22, 23, 24, 25, 26, 27, 28, 29, 30 };
				CaseApply caseApply = new CaseApply();
				BeforehandApply beforehandApply = new BeforehandApply();
				BeforePersonalCustomer beforePersonalCustomer = new BeforePersonalCustomer();
				BeforeContact beforeContact = new BeforeContact();
				HouseProperty houseProperty = new HouseProperty();
				CaseApplyBeforeCustomer caseApplyBeforeCustomer = new CaseApplyBeforeCustomer();
				
				if(ObjectHelper.isNotEmpty(this.getCellValue(row.getCell(cellArray[0])))){
					beforehandApply.setApplyDate(Long.valueOf(this.getCellValue(row.getCell(cellArray[0])).replaceAll("/", "")));// Excel中的派单日期相当于申请日期
					caseApply.setApplyDate(beforehandApply.getApplyDate());
				}
				if(ObjectHelper.isNotEmpty(this.getCellValue(row.getCell(cellArray[1])))){
					beforehandApply.setDevelopmentManagerCode(this.getCellValue(row.getCell(cellArray[1])));// Excel中的渠道标识相当于拓展经理代码
					caseApply.setDevelopmentManagerCode(beforehandApply.getDevelopmentManagerCode());
				}
				if(ObjectHelper.isNotEmpty(this.getCellValue(row.getCell(cellArray[21])))){
					beforehandApply.setApplyAmount(BigDecimal.valueOf(Long.valueOf(this.getCellValue(row.getCell(cellArray[21])))));//客户额度需求
					caseApply.setApplyAmount(beforehandApply.getApplyAmount());
				}
				if(ObjectHelper.isNotEmpty(this.getCellValue(row.getCell(cellArray[22])))){
					beforehandApply.setApplyTerm(Integer.valueOf(this.getCellValue(row.getCell(cellArray[22]))));//融资期限
					caseApply.setApplyTerm(beforehandApply.getApplyTerm());
				}
				if("经营".equals(this.getCellValue(row.getCell(cellArray[23])))){
					beforehandApply.setCapitalUseFor("YWDM0051045");
					caseApply.setCapitalUseFor(beforehandApply.getCapitalUseFor());
				}
				if("消费".equals(this.getCellValue(row.getCell(cellArray[23])))){
					beforehandApply.setCapitalUseFor("YWDM0051046");
					caseApply.setCapitalUseFor(beforehandApply.getCapitalUseFor());
				}
				caseApply.setIsTerminalCase(CaseApply.YESTERMINALCASE);
				caseApply.setStage("YWDM009203");
				if(ObjectHelper.isNotEmpty(this.getCellValue(row.getCell(cellArray[2])))){
					beforePersonalCustomer.setCustomerName(this.getCellValue(row.getCell(cellArray[2])));
				}
				if(ObjectHelper.isNotEmpty(this.getCellValue(row.getCell(cellArray[3])))){
					beforePersonalCustomer.setCredentialType("YWDM002501");// 证件类型:身份证
					beforePersonalCustomer.setCredentialNo(this.getCellValue(row.getCell(cellArray[3])));// 身份证号码
				}
				if ("男".equals(this.getCellValue(row.getCell(cellArray[5])))) {
					beforePersonalCustomer.setGender("303001");
				}
				if ("女".equals(this.getCellValue(row.getCell(cellArray[5])))) {
					beforePersonalCustomer.setGender("303002");
				}
				if ("未婚".equals(this.getCellValue(row.getCell(cellArray[7])))
						|| "未".equals(this.getCellValue(row.getCell(cellArray[5])))
						|| "否".equals(this.getCellValue(row.getCell(cellArray[5])))) {
					beforePersonalCustomer.setMaritalStatus("303001");
				}
				if ("已婚".equals(this.getCellValue(row.getCell(cellArray[7])))
						|| "已".equals(this.getCellValue(row.getCell(cellArray[7])))
						|| "是".equals(this.getCellValue(row.getCell(cellArray[7])))) {
					beforePersonalCustomer.setMaritalStatus("303002");
				}
				beforePersonalCustomerService.saveEntity(beforePersonalCustomer);
				caseApplyService.saveEntity(caseApply);
				beforehandApply.setCaseApplyId(caseApply.getId());
				beforehandApplyService.saveEntity(beforehandApply);
				
				caseApplyBeforeCustomer.setCaseApply(caseApply);
				caseApplyBeforeCustomer.setJoinType(CaseApplyBeforeCustomer.MAIN_BORROW);
				BeforeCustomer beforeCustomer = beforeCustomerService.findOne(beforePersonalCustomer.getId());
				caseApplyBeforeCustomer.setBeforeCustomer(beforeCustomer);
				caseApplyBeforeCustomerService.saveEntity(caseApplyBeforeCustomer);
				// 省市区的处理(在Excel中直接查看省市区Excel复制fullCode,后台这里直接存fullCode)
				if(ObjectHelper.isNotEmpty(this.getCellValue(row.getCell(cellArray[9])))){
					houseProperty.setProvince(this.getCellValue(row.getCell(cellArray[9])));
				}
				if(ObjectHelper.isNotEmpty(this.getCellValue(row.getCell(cellArray[10])))){
					houseProperty.setCity(this.getCellValue(row.getCell(cellArray[10])));
				}
				if(ObjectHelper.isNotEmpty(this.getCellValue(row.getCell(cellArray[11])))){
					houseProperty.setDistrict(this.getCellValue(row.getCell(cellArray[11])));
				}
				if(ObjectHelper.isNotEmpty(this.getCellValue(row.getCell(cellArray[12])))){
					houseProperty.setMailingAddress(this.getCellValue(row.getCell(cellArray[12])));
				}
				if("是".equals(this.getCellValue(row.getCell(cellArray[27])))){
					houseProperty.setEstateOwnership("YWDM0051055");
				}
				if("否".equals(this.getCellValue(row.getCell(cellArray[27])))){
					houseProperty.setEstateOwnership("YWDM0051054");
				}
				houseProperty.setCaseApply(caseApply);
				housePropertyService.saveEntity(houseProperty);
				
				// 房贷情况
				if (ObjectHelper.isNotEmpty(this.getCellValue(row.getCell(cellArray[14])))
						|| ObjectHelper.isNotEmpty(this.getCellValue(row.getCell(cellArray[16])))) {
					PledgeInfo pledgeInfo = new PledgeInfo();
					if(ObjectHelper.isNotEmpty(this.getCellValue(row.getCell(cellArray[14])))){
						pledgeInfo.setLoanBalance(
								BigDecimal.valueOf(Long.valueOf(this.getCellValue(row.getCell(cellArray[14])))));// 抵押房产贷款余额
					}
					if(ObjectHelper.isNotEmpty(this.getCellValue(row.getCell(cellArray[16])))){
						pledgeInfo.setDeadline(Integer.valueOf(this.getCellValue(row.getCell(cellArray[16]))));// 房贷期限
					}
					// 红线情况
					if (ObjectHelper.isNotEmpty(this.getCellValue(row.getCell(cellArray[19])))) {
						pledgeInfo.setPledgeType(PledgeInfo.PLEDGETYPE_ONE);
					}
					pledgeInfo.setPledgeType(PledgeInfo.PLEDGETYPE_ONE);
					pledgeInfo.setHousePropertyId(houseProperty.getId());
					pledgeInfoService.saveEntity(pledgeInfo);
					
				}
				if ("一押".equals(this.getCellValue(row.getCell(cellArray[20])))
						|| "一抵".equals(this.getCellValue(row.getCell(cellArray[20])))
						|| "一".equals(this.getCellValue(row.getCell(cellArray[20])))) {
					PledgeInfo pledgeInfo = new PledgeInfo();
					pledgeInfo.setPledgeType(PledgeInfo.PLEDGETYPE_ONE);
					pledgeInfo.setHousePropertyId(houseProperty.getId());
					pledgeInfoService.saveEntity(pledgeInfo);
					
				}
				if ("二押".equals(this.getCellValue(row.getCell(cellArray[20])))
						|| "二抵".equals(this.getCellValue(row.getCell(cellArray[20])))
						|| "二".equals(this.getCellValue(row.getCell(cellArray[20])))) {
					PledgeInfo pledgeInfo = new PledgeInfo();
					pledgeInfo.setPledgeType(PledgeInfo.PLEDGETYPE_TWO);
					pledgeInfo.setHousePropertyId(houseProperty.getId());
					pledgeInfoService.saveEntity(pledgeInfo);
				}
				
				if(ObjectHelper.isNotEmpty(this.getCellValue(row.getCell(cellArray[4])))){
					if(ObjectHelper.isNotEmpty(this.getCellValue(row.getCell(cellArray[2])))){
						beforeContact.setCustomerId(beforePersonalCustomer.getId());
						beforeContact.setCustomerName(this.getCellValue(row.getCell(cellArray[2])));
					}
					beforeContact.setContactType(BeforeContact.MOBILE_NUMBER);// 电话类型:手机
					beforeContact.setPhoneNumber(this.getCellValue(row.getCell(cellArray[4])));// 电话号码
				}
				beforeContact.setCustomerId(beforePersonalCustomer.getId());
				beforeContactService.saveEntity(beforeContact);
			}
			
			if(msg.getResultStatus() == ResponseMsg.SUCCESS){
				msg.setMsg("导入成功");
			}
			
		} catch (Exception e) {
			msg.setMsg("导入失败");
			e.printStackTrace();
		}
		
	}

}
