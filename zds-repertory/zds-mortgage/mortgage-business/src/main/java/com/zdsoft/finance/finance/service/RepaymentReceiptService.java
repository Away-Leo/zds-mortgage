package com.zdsoft.finance.finance.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.finance.entity.RepaymentReceipt;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RepaymentReceiptService.java 
 * @ClassName: RepaymentReceiptService 
 * @Description: 还款-收款单接口
 * @author jincheng 
 * @date 2017年2月17日 下午5:04:20 
 * @version V1.0
 */
public interface RepaymentReceiptService extends BaseService<RepaymentReceipt>{
	
	/**
	 * @Title: saveOrUpdateRepaymentReceipt 
	 * @Description: 新增或修改还款
	 * @author jincheng 
	 * @param feceipt
	 * @param receiptList
	 * @return
	 */
	RepaymentReceipt saveOrUpdateRepaymentReceipt(RepaymentReceipt feceipt, String receiptList)throws Exception;

	/**
	 * 
	 * @Title: repaymentApplyList 
	 * @Description: 收据列表详细
	 * @author xiangjx 
	 * @param pageRequest
	 * @param li
	 * @return
	 */
	public Page<Map<String, Object>> repaymentApplyList(PageRequest pageRequest, List<QueryObj> li,DataOperPermDto dataOperPermDto)throws BusinessException;

	/**
	 * @Title: deleteRepaymentReceipt 
	 * @Description: 删除收款单明细
	 * @author jincheng 
	 * @param receiptId
	 */
	void deleteRepaymentReceipt(String receiptId)throws Exception;

	/**
	 * @Title: updateStatus 
	 * @Description: 修改收款单状态
	 * @author xiangjx 
	 * @param id
	 * @param status
	 */
	void updateStatus(String id, Integer status)throws Exception;

	/**
	 * @Title: exportReceivableMonthToExcel 
	 * @Description: 导出本月应还
	 * @author jincheng 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public XSSFWorkbook exportReceivableMonthToExcel(String path)throws Exception;

	/**
	 * @Title: findByCaseApplyIdAndReceiptTypeAndStatus 
	 * @Description: 根据案件id获取预收款
	 * @author jincheng 
	 * @param caseApplyId
	 * @param receiptType
	 * @param status
	 * @return
	 */
	List<RepaymentReceipt> findByCaseApplyIdAndReceiptTypeAndStatus(String caseApplyId, Integer receiptType, Integer status);

	/**
	 * @Title: exportRepaymentReceiptExcel 
	 * @Description: 导出收款复核
	 * @author jincheng 
	 * @param receipts
	 * @return
	 */
	XSSFWorkbook exportRepaymentReceiptExcel(String receipts)throws Exception;
}
