package com.zdsoft.finance.casemanage.receivablePlan.service.impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.receivablePlan.constant.RepaymentPlanTypeEnum;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanTemp;
import com.zdsoft.finance.casemanage.receivablePlan.repository.ReceivablePlanTempRepository;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanTempService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ReceivablePlanTempServiceImpl.java 
 * @ClassName: ReceivablePlanTempServiceImpl 
 * @Description: 还款计划(临时)接口实现
 * @author jincheng 
 * @date 2017年2月16日 上午11:28:02 
 * @version V1.0
 */
@Service("receivablePlanTempService")
public class ReceivablePlanTempServiceImpl extends BaseServiceImpl< ReceivablePlanTemp,  ReceivablePlanTempRepository>implements  ReceivablePlanTempService {

	@Autowired   
	private com.zdsoft.essential.client.service.CED CED ;
	
	@Override
	public List<ReceivablePlanTemp> findReceivablePlanTempByCaseApplyId(String caseApplyId) {
		return this.customReposity.findReceivablePlanTempByCaseApplyIdOrderByCreateTimeAsc(caseApplyId);
	}
	
	@Override
	public List<ReceivablePlanTemp> findReceivablePlanTempByReceivableInfoId(String receivableInfoId) {
		return this.customReposity.findReceivablePlanTempByReceivableInfoIdOrderByCreateTimeAsc(receivableInfoId);
	}
	
	@Override 
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void saveReceivablePlanTemp(String caseApplyId, String receivableInfoId, String planList)throws Exception {
			List<ReceivablePlanTemp> planTempList=JSONObject.parseArray(planList, ReceivablePlanTemp.class);
			List<ReceivablePlanTemp> old_planTempList = customReposity.findReceivablePlanTempByReceivableInfoIdOrderByCreateTimeAsc(receivableInfoId);
			this.customReposity.delete(old_planTempList);
			for(ReceivablePlanTemp plan:planTempList){
				plan.setCaseApplyId(caseApplyId);
				plan.setReceivableInfoId(receivableInfoId);
				plan.setPlanType(RepaymentPlanTypeEnum.DRAFT.getValue());
				EmpDto emp=CED.getLoginUser();
				plan.setCreateTime(new Date());
				plan.setCreateBy(emp.getEmpCd());
				plan.setCreateOrgCd(emp.getOrgCd());
				this.customReposity.saveEntity(plan);
			}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void deleteReceivablePlanTemp(String caseApplyId, Integer planType) throws Exception {
		List<ReceivablePlanTemp> old_tempList=this.findReceivablePlanTempByCaseApplyId(caseApplyId);	
		for(ReceivablePlanTemp temp:old_tempList){
			this.customReposity.delete(temp);
		}
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void deleteReceivablePlanTempByReceivableInfoId(String receivableInfoId) throws Exception {
		List<ReceivablePlanTemp> old_tempList=this.customReposity.findReceivablePlanTempByReceivableInfoIdOrderByCreateTimeAsc(receivableInfoId);
		for(ReceivablePlanTemp temp:old_tempList){
			this.customReposity.delete(temp);
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void importPlanExcelFile(String caseApplyId,String fileID) throws Exception {
		AttachmentDto  attachment = CED.findAttachmentById(fileID);
		
		String filePath=attachment.getFilePath();
		String filetype ="xlsx";
		if(ObjectHelper.isNotEmpty(filetype)){
			   filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase();
		}
	     
        InputStream stream = new FileInputStream(filePath);  
        Workbook wb = null;  
        if (filetype.equals("xls")) {  
            wb = new HSSFWorkbook(stream);  
        }else if (filetype.equals("xlsx")) {  
            wb = new XSSFWorkbook(stream);  
        }  
        
        Sheet sheet1 = wb.getSheetAt(0); //获取第一个sheet 
        int totalRows = sheet1.getPhysicalNumberOfRows();// --获取sheet总行数  
        
        //设计可从第几行开始,获取那几列
        for (int i=1;i<totalRows;i++) { 
        	Row row=sheet1.getRow(i);//获取行数默认从第二行开始
        	if(ObjectHelper.isEmpty(this.getCellValue(row.getCell(0)))){
        		continue;
        	}
        	int[] cellArray=new int[]{0,1,2,3,4,5,6,7,8,9};//表格位置,定义为cell数组,可通过配置(按照顺序1为客户名称对应表格的位置)
        	ReceivablePlanTemp record=new ReceivablePlanTemp();
        	record.setPlanReayDateStr(this.getCellValue(row.getCell(cellArray[0])));//应还款时间
        	record.setPlanPrincipalAmount(BigDecimal.valueOf(Long.valueOf(this.getCellValue(row.getCell(cellArray[1])))));//应还本金
        	record.setPlanInterestAmount(BigDecimal.valueOf(Long.valueOf(this.getCellValue(row.getCell(cellArray[2])))));//应还利息
        	record.setPlanServiceFee(BigDecimal.valueOf(Long.valueOf(this.getCellValue(row.getCell(cellArray[3])))));//应还服务费
        	record.setRemainPrincipal(BigDecimal.valueOf(Long.valueOf(this.getCellValue(row.getCell(cellArray[4])))));//剩余本金
        	record.setCaseApplyId(caseApplyId);
        	record.setPlanType(RepaymentPlanTypeEnum.DRAFT.getValue());
        	EmpDto emp=CED.getLoginUser();
        	record.setCreateTime(new Date());
        	record.setCreateBy(emp.getEmpCd());
        	record.setCreateOrgCd(emp.getOrgCd());
        	this.saveEntity(record);
        }  
	}
	
	/**
	 * 转换单元格的样式
	 * @param cell
	 * @return
	 * @throws Exception
	 */
	private String getCellValue(Cell cell)throws Exception{  
	       String cellvalue = "";  
	       if (cell!=null) {  
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
}
