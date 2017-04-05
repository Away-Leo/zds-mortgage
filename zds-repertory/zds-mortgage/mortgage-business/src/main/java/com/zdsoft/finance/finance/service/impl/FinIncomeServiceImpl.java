package com.zdsoft.finance.finance.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.datasurvey.entity.FeeInfomation;
import com.zdsoft.finance.casemanage.datasurvey.service.FeeInfomationService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.BigDecimalCalculateTwo;
import com.zdsoft.finance.finance.entity.FinIncome;
import com.zdsoft.finance.finance.entity.FinIncomeDetail;
import com.zdsoft.finance.finance.repository.FinIncomeRepository;
import com.zdsoft.finance.finance.service.FinIncomeDetailService;
import com.zdsoft.finance.finance.service.FinIncomeService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyAfterCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: FinIncomeServiceImpl.java
 * @ClassName: FinIncomeServiceImpl
 * @Description: 费用收费管理接口实现
 * @author longwei
 * @date 2017年2月15日 上午9:50:44
 * @version V1.0
 */
@Service("finIncomeService")
public class FinIncomeServiceImpl extends BaseServiceImpl<FinIncome, FinIncomeRepository> implements FinIncomeService {

	@Autowired
	private FinIncomeDetailService finIncomeDetailService;

	@Autowired
	private com.zdsoft.essential.client.service.CED CED;
	
	@Autowired
	private FeeInfomationService feeInfomationService;
	
	@Autowired
	private CaseApplyService caseApplyService;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FinIncome saveOrUpdateFinIncome(FinIncome entity, List<FinIncomeDetail> finIncomeDetails) throws Exception {
		BigDecimal payerAmount = BigDecimal.ZERO;
		EmpDto emp=CED.getLoginUser();
		
		if(ObjectHelper.isEmpty(entity.getId())){
			entity.setCreateTime(new Date());
			entity.setCreateBy(emp.getEmpCd());
			entity.setCreateOrgCd(emp.getOrgCd());
			entity.setOrgId(emp.getOrgId());
			entity.setOrgName(emp.getOrgNm());
			entity.setStatus("0");
			this.saveEntity(entity);
		}else{
			FinIncome old_entity=this.findOne(entity.getId());
			BeanUtils.copyProperties(entity, old_entity,new String[]{"createBy","createOrgCd","createTime","status","orgId","orgName","busiForm","caseApplyId","caseApplyCode"});
			old_entity.setUpdateBy(emp.getEmpCd());
			old_entity.setUpdateOrgCd(emp.getOrgCd());
			old_entity.setUpdateTime(new Date());
			entity=this.updateEntity(old_entity);
			//删除以前的费用项目
			List<FinIncomeDetail> detailList=finIncomeDetailService.findByFinIncomeId(entity.getId());
			for(FinIncomeDetail detail:detailList){
				finIncomeDetailService.deleteByFinIncomeId(detail.getId());
			}
		}
		
		for (FinIncomeDetail finIncomeDetail : finIncomeDetails) {
			payerAmount = BigDecimalCalculateTwo.add(payerAmount, finIncomeDetail.getPaidAmount());
			finIncomeDetail.setFinIncomeId(entity.getId());
			if(ObjectHelper.isEmpty(finIncomeDetail.getFeeObjectId())){
				finIncomeDetail.setFeeObjectType(FeeInfomationService.JOIN_TYPE_OTHER_FULLCODE);
				finIncomeDetail.setFeeObjectId(emp.getEmpCd());
				finIncomeDetail.setFeeeObjectName(emp.getEmpNm());
			}
			if(ObjectHelper.isEmpty(finIncomeDetail.getPayObjectId())){
				finIncomeDetail.setPayObjectType(FeeInfomationService.JOIN_TYPE_OTHER_FULLCODE);
				finIncomeDetail.setPayObjectId(emp.getEmpCd());
				finIncomeDetail.setPayObjectName(emp.getEmpNm());
			}
			finIncomeDetailService.saveEntity(finIncomeDetail);
		}
		if(entity.getIsSubmit()){
			entity.setStatus("1");
		}
		entity.setPayerAmount(payerAmount);
		this.updateEntity(entity);
		return entity;
	}

	
	@Override
	public List<Map<String, Object>> findAllSimpCode() throws Exception {
		return this.customReposity.findAllSimpCode();
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateFinIncomeStatus(String businessKey, String status) throws Exception {
		String[] ids=businessKey.split(",");
		for(String id:ids){
			FinIncome old_entity=this.findOne(id);
			old_entity.setStatus(status);
			this.updateEntity(old_entity);
			if("2".equals(status)){
				List<FinIncomeDetail> detailList = finIncomeDetailService.findByFinIncomeId(businessKey);
				for(FinIncomeDetail detail:detailList){
					if(ObjectHelper.isNotEmpty(detail.getFeeId())){
						FeeInfomation fee=feeInfomationService.findOne(detail.getFeeId());
						if(ObjectHelper.isNotEmpty(fee)){
							fee.setReceivedAmount(detail.getPaidAmount().add(ObjectHelper.isNotEmpty(fee.getReceivedAmount())?fee.getReceivedAmount():BigDecimal.ZERO));//累计实收
							fee.setBalanceAmount(fee.getReceivedAmount().subtract(ObjectHelper.isNotEmpty(fee.getPaidAmount())?fee.getPaidAmount():BigDecimal.ZERO));//实收-实付=结余
							feeInfomationService.updateEntity(fee);
						}
					}else{
						//把FinIncomeDetail中存在，FeeInfomation不存在的数据 保存到FeeInfomation
						CaseApply apply = caseApplyService.findOne(old_entity.getCaseApplyId());//案件信息
						FeeInfomation info = new FeeInfomation();
						BeanUtils.copyProperties(detail, info,new String[]{"id"});
						info.setCaseApply(apply);
						info.setExpectedAmount(detail.getPaidAmount());
						info.setExpectedPayableAmount(detail.getPaidAmount());
						info.setReceivedAmount(detail.getPaidAmount());
						info.setBalanceAmount(BigDecimal.ZERO);
						feeInfomationService.saveEntity(info);
						detail.setFeeId(info.getId());
						finIncomeDetailService.updateEntity(detail);
					}
				}
			}
		}
	}


	@Override
	public Page<Map<String, Object>> getCaseApplyFinIncomeList(PageRequest pageRequest, List<QueryObj> li,DataOperPermDto dataOperPermDto) throws BusinessException {
		StringBuffer _sql=new StringBuffer();
		_sql.append(" select  ");
		_sql.append("  mca.id,fin.payerAmount,cus.customerName, ");
		_sql.append("  mca.caseApplyCode,mca.productSubtypeName,mca.productTypeName, mca.applyAmount, mca.developmentDepartmentName ");
		_sql.append("  from mkt_case_apply mca   ");
		_sql.append("  INNER JOIN ( SELECT caseApplyId,SUM (payerAmount) payerAmount  FROM fin_income  where status='2' GROUP BY caseApplyId )fin  on fin.caseApplyId=mca.ID   ");
		_sql.append(" LEFT JOIN case_before_customer cbc on cbc.caseApplyId=mca.id  and cbc.joinType='"+ CaseApplyAfterCustomer.MAIN_BORROW + "' ");
		_sql.append(" LEFT JOIN cust_before_customer cus on cus.id=cbc.customerId ");
		_sql.append("  where 1=1 ");
		
		if(ObjectHelper.isNotEmpty(dataOperPermDto)){
			StringBuffer data_auth=this.developmentManagerDataAuth(dataOperPermDto, "mca");
			_sql.append(data_auth);
		}
		
		StringBuffer _extendSql=new StringBuffer();
		_extendSql.append(" order by mca.applyDate desc ");
		return this.customReposity.getListObjectBySql(pageRequest, li, _sql, _extendSql);
	}


	@Override
	public Page<Map<String, Object>> getFinIncomeCaseApplyList(PageRequest page, List<QueryObj> li,DataOperPermDto dataOperPermDto) throws BusinessException {
		StringBuffer _sql=new StringBuffer();
		_sql.append(" select  ");
		_sql.append("  fin.id,fin.payerAmount,fin.caseApplyId,fin.status,fin.billCode,fin.createBy,fin.receiptsDate, ");
		_sql.append("  cus.customerName, ");
		_sql.append("  mca.caseApplyCode,mca.productSubtypeName,mca.productTypeName, mca.applyAmount, mca.developmentDepartmentName,mca.mechanismName ");
		_sql.append("  from fin_income fin   ");
		_sql.append("  INNER JOIN mkt_case_apply mca  on mca.id=fin.caseApplyId  ");
		_sql.append("  LEFT JOIN case_before_customer cbc on cbc.caseApplyId=mca.id  and cbc.joinType='"+ CaseApplyAfterCustomer.MAIN_BORROW + "' ");
		_sql.append("  LEFT JOIN cust_before_customer cus on cus.id=cbc.customerId ");
		_sql.append("  where 1=1 ");
		
		if(ObjectHelper.isNotEmpty(dataOperPermDto)){
			StringBuffer data_auth=this.developmentManagerDataAuth(dataOperPermDto, "mca");
			_sql.append(data_auth);
		}
		
		StringBuffer _extendSql=new StringBuffer();
		_extendSql.append(" order by fin.createTime desc ");
		return this.customReposity.getListObjectBySql(page, li, _sql, _extendSql);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void destoryFinIncome(String finIncomeId) throws Exception{
		FinIncome fin=this.findOne(finIncomeId);
		fin.setStatus("3");
		this.updateEntity(fin);
	}


	@Override
	public XSSFWorkbook exportFinIncomeExcel(String finIncomes,DataOperPermDto dataOperPermDto) throws Exception {
		XSSFWorkbook wb = new XSSFWorkbook();    
	       XSSFSheet sheet = wb.createSheet();
	       XSSFRow row_ = sheet.createRow(0);    
	       row_.createCell(0).setCellValue("机构");
	       row_.createCell(1).setCellValue("案件号");
	       row_.createCell(2).setCellValue("主借人");
	       row_.createCell(3).setCellValue("单据号");
	       row_.createCell(4).setCellValue("收款金额");
	       row_.createCell(5).setCellValue("收款日期");
	       row_.createCell(6).setCellValue("业务部门");
	       row_.createCell(7).setCellValue("状态");
	       XSSFCellStyle style = wb.createCellStyle();    
	       style.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
	       
	       StringBuffer _sql=new StringBuffer();
	         _sql.append(" select  ");
			_sql.append("  fin.id,fin.payerAmount,fin.caseApplyId,fin.status,fin.billCode,fin.createBy,fin.receiptsDate, ");
			_sql.append("  cus.customerName, ");
			_sql.append("  mca.caseApplyCode,mca.productSubtypeName,mca.productTypeName, mca.applyAmount, mca.developmentDepartmentName,mca.mechanismName ");
			_sql.append("  from fin_income fin   ");
			_sql.append("  INNER JOIN mkt_case_apply mca  on mca.id=fin.caseApplyId  ");
			_sql.append("  LEFT JOIN case_before_customer cbc on cbc.caseApplyId=mca.id  and cbc.joinType='"+ CaseApplyAfterCustomer.MAIN_BORROW + "' ");
			_sql.append("  LEFT JOIN cust_before_customer cus on cus.id=cbc.customerId ");
			_sql.append("  where 1=1  ");
//			if(ObjectHelper.isNotEmpty(dataOperPermDto)){
//				StringBuffer data_auth=this.developmentManagerDataAuth(dataOperPermDto, "mca");
//				_sql.append(data_auth);
//			}
			_sql.append(" order by fin.createTime desc ");
			
			List<Map<String,Object>> mapList=this.customReposity.findListMapByCondition(_sql.toString(), new HashMap<String, Object>());
			
		   for(int i=0;i<mapList.size();i++){
			    Map<String,Object> map=mapList.get(i);
			    XSSFRow row = sheet.createRow(1+i);    
				row.createCell(0).setCellValue(this.transToString(map.get("MECHANISMNAME")));
	            row.createCell(1).setCellValue(this.transToString(map.get("CASEAPPLYCODE")));
	            row.createCell(2).setCellValue(this.transToString(map.get("CUSTOMERNAME"))); 
	            row.createCell(3).setCellValue(this.transToString(map.get("BILLCODE"))); 
	            row.createCell(4).setCellValue(this.transToString(map.get("PAYERAMOUNT"))); 
	            row.createCell(5).setCellValue(this.transToString(map.get("RECEIPTSDATE"))); 
	            row.createCell(6).setCellValue(this.transToString(map.get("DEVELOPMENTDEPARTMENTNAME"))); 
	            Object obj=map.get("STATUS");
	            String status_Str="草稿";
	            if(ObjectHelper.isNotEmpty(obj)){
	            	if("1".equals(obj.toString())){
	            		status_Str="待确认";
	            	}
	            	if("2".equals(obj.toString())){
	            		status_Str="已确认";
	            	}
	            	if("3".equals(obj.toString())){
	            		status_Str="已废弃";
	            	}
	            	if("4".equals(obj.toString())){
	            		status_Str="已退回";
	            	}
	            }
	            
	            row.createCell(7).setCellValue(status_Str);
			}
		return wb;
	}
	
	private String transToString(Object obj){
		if(ObjectHelper.isEmpty(obj)){
			return "";
		}
		return obj.toString();
	}
}
