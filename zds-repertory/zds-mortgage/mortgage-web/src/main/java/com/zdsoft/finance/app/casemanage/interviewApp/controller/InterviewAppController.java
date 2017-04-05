package com.zdsoft.finance.app.casemanage.interviewApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.casemanage.interview.service.InterviewService;
import com.zdsoft.finance.casemanage.receivablePlan.entity.BankAccount;
import com.zdsoft.finance.casemanage.receivablePlan.service.BankAccountService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.utils.AppStatus;
import com.zdsoft.finance.common.utils.app.AppServerUtil;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.common.util.StoreHelper;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: InterviewAppController.java
 * @ClassName: InterviewAppController
 * @Description: app 面签控制
 * @author dengyy
 * @date 2017年2月27日 上午10:28:30
 * @version V1.0
 */
@Controller
@RequestMapping("server/casemanage/interviewApp")
public class InterviewAppController extends BaseController {

    @Autowired
    private InterviewService interviewService;

    @Autowired
    private CaseApplyService caseApplyService;

    @Autowired
    private BankAccountService bankAccountService;
    
    @Autowired
    private CED CED ;

    /**
     * @Title: findInterviewList
     * @Description: app 面签列表
     * @author dengyy
     * @param request
     *            请求信息
     * @param pageable
     *            分页信息
     * @return
     */
    @RequestMapping(value="/findInterviewList",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String findInterviewList(HttpServletRequest request, PageRequest pageable) {
        logger.info("进入获取案件面签列表信息！");
        try { // 分页 信息
            Integer pageIndex = AppServerUtil.DefaultPageIndex;
            Integer pageSize = AppServerUtil.DefaultPageSize;
            if (ObjectHelper.isNotEmpty(request.getParameter("pageIndex"))) {
                pageIndex = new Integer(request.getParameter("pageIndex"));
            }
            if (ObjectHelper.isNotEmpty(request.getParameter("pageSize"))) {
                pageSize = new Integer(request.getParameter("pageSize"));
            }
            pageable = new PageRequest(pageIndex, pageSize);

            // 输入类型 1:案件号 2：申请人
            String type = request.getParameter("type");
            // 输入值
            String keyword = request.getParameter("keyword");
            // 获取查询参数
            List<QueryObj> queryObjs = new ArrayList<QueryObj>();
            if (ObjectHelper.isNotEmpty(type)) {
                if ("1".equals(type)) {
                    QueryObj qu = new QueryObj();
                    qu.setElement("caseApplyCode");
                    qu.setOperator("like");
                    qu.setValue("%" + keyword + "%");
                    qu.setObj("c");
                    queryObjs.add(qu);
                }
                if ("2".equals(type)) {
                    QueryObj qu = new QueryObj();
                    qu.setElement("recipients");
                    qu.setOperator("like");
                    qu.setValue("%" + keyword + "%");
                    qu.setObj("a");
                    queryObjs.add(qu);
                }
            }
            //数据权限
            DataOperPermDto dataOperPermDto = CED.findDataPerms(StoreHelper.getApplication(), "com.cnfh.rms.casemanage.interview.interviewList.dataAuth");
            // 获取案件面签信息
            List<Map<String, String>> pageInterview = interviewService.findPageInterview(pageable, queryObjs,dataOperPermDto);
            return AppServerUtil.buildJsonList(pageInterview);
        } catch (Exception e) {
            logger.error("获取面签列表信息失败！");
            e.printStackTrace();
            return AppServerUtil.buildError(AppStatus.SystemError);
        }
    }

    /**
     * @Title: findCaseAPPlyView
     * @Description: 案件基础信息
     * @author dengyy
     * @param request
     *            请求信息
     * @return
     */
    @RequestMapping(value="/findCaseAPPlyView",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String findCaseAPPlyView(HttpServletRequest request) {
        // 获取案件id
        String caseApplyId = request.getParameter("caseApplyId");
        if (ObjectHelper.isEmpty(caseApplyId)) {
            return AppServerUtil.buildError(AppStatus.ArgsError);
        }
        try {
            // 查询案件的基础信息
            Map<String, String> condition = caseApplyService.findCaseApplyByCondition(caseApplyId);
            return AppServerUtil.buildJsonObject(condition);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取案件基础信息失败！");
            return AppServerUtil.buildError(AppStatus.SystemError);
        }
    }

    /**
     * @Title: bankView
     * @Description: 案件银行信息初始化
     * @author dengyy
     * @param request
     *            请求信息
     * @return
     */
    @RequestMapping(value="/bankView",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String bankView(HttpServletRequest request) {
        String caseApplyId = request.getParameter("caseApplyId");
        if (ObjectHelper.isEmpty(caseApplyId)) {
            return AppServerUtil.buildError(AppStatus.ArgsError);
        }
        try {
            // 收款账户
            BankAccount bankAccount = bankAccountService.findByCaseApplyIdAndAccountType(caseApplyId, BankAccount.RECEIVABLES);
            // 还款账户
            BankAccount bankAccount2 = bankAccountService.findByCaseApplyIdAndAccountType(caseApplyId, BankAccount.REPAYMENT);
            Map<String, String> map = new HashMap<String, String>();
            map.put("caseApplyId", caseApplyId);
            // 收款账户
            if (ObjectHelper.isNotEmpty(bankAccount)) {
                map.put("paymentBankNo", bankAccount.getBankNo());
                map.put("paymentBankAccount", bankAccount.getBankAccount());
                map.put("paymentCardholderName", bankAccount.getCardholderName());
                map.put("paymentId", bankAccount.getId());
            } else {
                map.put("paymentBankNo", "");
                map.put("paymentBankAccount", "");
                map.put("paymentCardholderName", "");
                map.put("paymentId", "");
            }
            // 还款账户
            if (ObjectHelper.isNotEmpty(bankAccount2)) {
                map.put("repaymentBankNo", bankAccount2.getBankNo());
                map.put("repaymentBankAccount", bankAccount2.getBankAccount());
                map.put("repaymentCardholderName", bankAccount2.getCardholderName());
                map.put("repaymentId", bankAccount2.getId());
            } else {
                map.put("repaymentBankNo", "");
                map.put("repaymentBankAccount", "");
                map.put("repaymentCardholderName", "");
                map.put("repaymentId", "");
            }
            return AppServerUtil.buildJsonObject(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取信息失败");
            return AppServerUtil.buildError(AppStatus.SystemError);
        }
    }

    /**
     * 
     * @Title: saveOrUpdatebank 
     * @Description: 保存面签时银行卡信息
     * @author dengyy 
     * @param request 请求信息
     * @return
     */
    @RequestMapping(value="/saveOrUpdateBank")
    @ResponseBody
    public String saveOrUpdatebank(HttpServletRequest request) {
        String caseApplyId = request.getParameter("caseApplyId");
        String paymentBankNo = request.getParameter("paymentBankNo");
        String paymentBankAccount = request.getParameter("paymentBankAccount");
        String paymentCardholderName = request.getParameter("paymentCardholderName");
        String paymentId = request.getParameter("paymentId");
        String repaymentBankNo = request.getParameter("repaymentBankNo");
        String repaymentBankAccount = request.getParameter("repaymentBankAccount");
        String repaymentCardholderName = request.getParameter("repaymentCardholderName");
        String repaymentId = request.getParameter("repaymentId");
        if (ObjectHelper.isEmpty(caseApplyId) || ObjectHelper.isEmpty(paymentBankNo) || ObjectHelper.isEmpty(paymentBankAccount)
                || ObjectHelper.isEmpty(paymentCardholderName) || ObjectHelper.isEmpty(repaymentBankNo) || ObjectHelper.isEmpty(repaymentBankAccount)
                || ObjectHelper.isEmpty(repaymentCardholderName)) {
            return AppServerUtil.buildError(AppStatus.ArgsError);
        }
        try {
            // 收款账户
            BankAccount bankAccount =null;
            // 还款账户
            BankAccount bankAccount2 = null;
            EmpDto empDto = CED.getLoginUser();
            //收款账户
            if(ObjectHelper.isEmpty(paymentId)){
                bankAccount = new BankAccount();
                bankAccount.setCreateBy(empDto.getEmpCd());
                bankAccount.setCreateOrgCd(empDto.getOrgCd());
            }else{
                bankAccount = bankAccountService.findOne(paymentId);
                bankAccount.setUpdateBy(empDto.getEmpCd());
                bankAccount.setUpdateOrgCd(empDto.getOrgCd());
            }
            
            // 还款账户
            if(ObjectHelper.isEmpty(repaymentId)){
                bankAccount2 = new BankAccount();
                bankAccount2.setCreateBy(empDto.getEmpCd());
                bankAccount2.setCreateOrgCd(empDto.getOrgCd());
            }else{
                bankAccount2 = bankAccountService.findOne(repaymentId);
                bankAccount2.setUpdateBy(empDto.getEmpCd());
                bankAccount2.setUpdateOrgCd(empDto.getOrgCd());
            }
            //设置收款账号信息
            bankAccount.setAccountType(BankAccount.RECEIVABLES);
            bankAccount.setBankNo(paymentBankNo);
            bankAccount.setBankAccount(paymentBankAccount);
            bankAccount.setCardholderName(paymentCardholderName);
            bankAccount.setId(paymentId);
            bankAccount.setCaseApplyId(caseApplyId);
            //设置还款账号信息
            bankAccount2.setAccountType(BankAccount.REPAYMENT);
            bankAccount2.setBankNo(repaymentBankNo);
            bankAccount2.setBankAccount(repaymentBankAccount);
            bankAccount2.setCardholderName(repaymentCardholderName);
            bankAccount2.setId(repaymentId);
            bankAccount2.setCaseApplyId(caseApplyId);
            //保存修改银行账号信息
            List<BankAccount> accounts = new ArrayList<BankAccount>();
            accounts.add(bankAccount);
            accounts.add(bankAccount2);
            List<BankAccount> list = bankAccountService.saveOrUpdateBankAccounts(accounts);
            Map<String, String> map = new HashMap<String, String>();
            for (BankAccount bankAccount3 : list) {
                if(BankAccount.RECEIVABLES.equals(bankAccount3.getAccountType())){
                    map.put("paymentId", bankAccount3.getId());
                }
                if(BankAccount.RECEIVABLES.equals(bankAccount3.getAccountType())){
                    map.put("repaymentId", bankAccount3.getId());
                }
            }
            map.put("caseApplyId", caseApplyId);
            return AppServerUtil.buildJsonObject(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取信息失败");
            return AppServerUtil.buildError(AppStatus.SystemError);
        }
    }
    
    /**
     * 
     * @Title: submitInterview 
     * @Description: 面签信息提交
     * @author dengyy 
     * @param request 请求信息
     * @return
     */
    @RequestMapping(value="/submitInterview",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String submitInterview(HttpServletRequest request){
        return AppServerUtil.buildJsonMessage(AppStatus.Succeed);
    }
    
}