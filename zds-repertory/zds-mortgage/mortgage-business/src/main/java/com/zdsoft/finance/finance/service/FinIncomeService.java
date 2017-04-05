package com.zdsoft.finance.finance.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.finance.entity.FinIncome;
import com.zdsoft.finance.finance.entity.FinIncomeDetail;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FinIncomeService.java 
 * @ClassName: FinIncomeService 
 * @Description: 费用收费管理接口
 * @author longwei 
 * @date 2017年2月15日 上午9:49:35 
 * @version V1.0
 */
public interface FinIncomeService extends BaseService<FinIncome>{
	
	/**
	 * 
	 * @Title: findAllSimpCode 
	 * @Description: 查询所有的终端 评估公司  其他合作方 
	 * simpcode 下拉数据
	 * @author xiangjx 
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> findAllSimpCode() throws Exception;
	
	/**
	 * 保存或修改收款单和收款明细 、启动流程
	 * @param finIncome
	 * @param finIncomeDetails
	 * @return
	 * @throws Exception
	 */
	public FinIncome saveOrUpdateFinIncome(FinIncome finIncome, List<FinIncomeDetail> finIncomeDetails) throws Exception;

	/**
	 * @Title: updateFinIncomeStatus 
	 * @Description: 修改收费状态
	 * @author jincheng 
	 * @param businessKey
	 * @param status
	 */
	public void updateFinIncomeStatus(String businessKey, String status)throws Exception;

	/**
	 * @Title: getCaseApplyFinIncomeList 
	 * @Description: 获取案件收费明细列表数据
	 * @author jincheng 
	 * @param pageable
	 * @param queryObjs
	 * @return
	 */
	public Page<Map<String, Object>> getCaseApplyFinIncomeList(PageRequest pageable, List<QueryObj> queryObjs,DataOperPermDto dataOperPermDto) throws BusinessException;

	/**
	 * @Title: getFinIncomeCaseApplyList 
	 * @Description: 获取收费明细列表数据
	 * @author jincheng 
	 * @param page
	 * @param li
	 * @param dataOperPermDto
	 * @return
	 */
	public Page<Map<String, Object>> getFinIncomeCaseApplyList(PageRequest page, List<QueryObj> li,DataOperPermDto dataOperPermDto)throws BusinessException;

	/**
	 * @Title: destoryFinIncome 
	 * @Description: 废弃费用收款
	 * @author jincheng 
	 * @param finIncomeId
	 */
	public void destoryFinIncome(String finIncomeId)throws Exception;

	/**
	 * @Title: exportFinIncomeExcel 
	 * @Description: 导出费用收费
	 * @author jincheng 
	 * @param finIncomes
	 * @return
	 */
	public XSSFWorkbook exportFinIncomeExcel(String finIncomes,DataOperPermDto dataOperPermDto)throws Exception;

}
