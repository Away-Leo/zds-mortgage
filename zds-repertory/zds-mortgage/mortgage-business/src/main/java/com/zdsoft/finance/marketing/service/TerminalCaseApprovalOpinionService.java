package com.zdsoft.finance.marketing.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.marketing.entity.TerminalCaseApprovalOpinion;
import com.zdsoft.framework.core.common.dto.ResponseMsg;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: TerminalCaseApprovalOpinionService.java 
 * @ClassName: TerminalCaseApprovalOpinionService 
 * @Description: TODO
 * @author 终端进件审批意见服务
 * @date 2017年3月4日 上午11:16:12 
 * @version V1.0 
 */ 
public interface TerminalCaseApprovalOpinionService extends BaseService<TerminalCaseApprovalOpinion>{

	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 通过案件id来查询终端进件审批意见服务
	 * @author xiongpan
	 * @param caseApplyId 案件id
	 * @return
	 */
	public TerminalCaseApprovalOpinion findByCaseApplyId(String caseApplyId);

	/**
	 * 
	 * @Title: saveTerminalCaseApprovalOpinion 
	 * @Description: 批量保存终端进件审批意见
	 * @author xiongpan
	 * @param id 需要保存的所有案件的id字符串
	 * @param terminalCaseApprovalOpinion 需要保存的审批意见信息
	 */
	public void saveTerminalCaseApprovalOpinion(String ids, TerminalCaseApprovalOpinion terminalCaseApprovalOpinion);

	/**
	 * 
	 * @Title: submitTerminalCaseApprovalOpinion 
	 * @Description: 批量提交终端进件审批意见
	 * @author xiongpan
	 * @param id 需要保存的所有案件的id字符串
	 * @param terminalCaseApprovalOpinion 需要保存的审批意见信息
	 */
	public void submitTerminalCaseApprovalOpinion(String ids, TerminalCaseApprovalOpinion terminalCaseApprovalOpinion);


	/**
	 * 
	 * @Title: terminalCaseDataMap 
	 * @Description: 将需要导出的终端进件数据封装
	 * @author xiongpan
	 * @param content 需要导出的终端进件数据
	 * @return
	 */
	public List<Map<String, Object>> terminalCaseDataMap(String content);


	/**
	 * 
	 * @Title: importTerminalCases 
	 * @Description: 终端进件导入
	 * @author xiongpan
	 * @param file 导入的Excel文件
	 * @param msg 响应的消息
	 */
	public void importTerminalCases(CommonsMultipartFile file, ResponseMsg msg);
	

}
