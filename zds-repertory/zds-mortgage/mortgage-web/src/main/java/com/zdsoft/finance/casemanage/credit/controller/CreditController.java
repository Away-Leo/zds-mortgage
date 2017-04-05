package com.zdsoft.finance.casemanage.credit.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.customer.service.CreditService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditController.java 
 * @ClassName: CreditController 
 * @Description: 征信controller
 * @author xj 
 * @date 2017年2月23日 下午3:50:32 
 * @version V1.0
 */
@Controller
@RequestMapping("/casemanage/credit")
public class CreditController extends BaseController {
	@Autowired
	private CreditService creditService;
	@Autowired
	private CED CED;
	
	/**
	 * 
	 * @Title: initCreditList 
	 * @Description: 案件征信录入菜单
	 * @author xj 
	 * @return
	 */
	@RequestMapping("/initCreditList")
	@UriKey(key = "com.cnfh.rms.casemanage.credit.initCreditList")
	@Menu(resource = "com.cnfh.rms.casemanage.credit.initCreditList", label = "案件征信录入", group = "project", order = 25)
	public String initCreditList(){
		return "/casemanage/credit/credit_list";
	}
	
	/**
	 * 
	 * @Title: attachmentUpload 
	 * @Description: 跳转到附件上传页面
	 * @author xj 
	 * @return
	 */
	@RequestMapping("/attachmentUpload")
	@UriKey(key = "com.cnfh.rms.casemanage.credit.attachmentUpload")
	public String attachmentUpload(){
		return "/casemanage/credit/attachment_upload";
	}
	
	/**
	 * 
	 * @Title: getCreditList 
	 * @Description: 征信列表
	 * @author xj 
	 * @param request
	 * @param pageable
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/getCreditList")
	@UriKey(key = "com.cnfh.rms.casemanage.credit.getCreditList")
	@ResponseBody
	public String getCreditList(HttpServletRequest request, PageRequest pageable,String  inputStatus, String jsoncallback){
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功！");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		try {
			@SuppressWarnings("unchecked")
			List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
			EmpDto loginUser = CED.getLoginUser();
			String companyCd = loginUser.getCompanyCd();
			Page<Map<String, Object>> page = creditService.findPageCredit(pageable, queryObjs,inputStatus,companyCd);
			List<Map<String, Object>> records = page.getRecords();
			//数据处理
			if(ObjectHelper.isNotEmpty(records)){
				for (Map<String, Object> map : records) {
					//贷款期限转换为月
					String applyDeadlineUnit = (String) map.get("applyTermUnit");
					String creditLinkCode = (String) map.get("creditLinkCode");
					//录入事项
					String creditLinkName = "";
					if(ObjectHelper.isNotEmpty(creditLinkCode)){
						creditLinkName = CED.loadSimpleCodeNameByFullCode(creditLinkCode);
					}
					map.put("creditLinkName", creditLinkName);
					
					int applyDeadline = 0;
					if(ObjectHelper.isNotEmpty(map.get("applyTerm"))){
						 applyDeadline = Integer.parseInt(map.get("applyTerm").toString());
					}
					//年转换为月
					if("0931001".equals(applyDeadlineUnit)){
						applyDeadline = applyDeadline*12;
					}else if("0931003".equals(applyDeadlineUnit)){//日转换为月
						applyDeadline = applyDeadline/30;
					}
					map.put("applyTermName", applyDeadline);
					//end
					//判断参与类型
					String jointype = (String) map.get("jointype");
					if(ObjectHelper.isNotEmpty(jointype)){
						map.put("jointypeName", CED.loadSimpleCodeNameByFullCode(jointype));
					}
				}
			}
			
			msg.setTotal(page.getTotalRows());
			msg.setRows(page.getRecords());
			
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("查询失败，请联系管理员！");
			e.printStackTrace();
			logger.error("征信列表:", e);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
		
	}
}
