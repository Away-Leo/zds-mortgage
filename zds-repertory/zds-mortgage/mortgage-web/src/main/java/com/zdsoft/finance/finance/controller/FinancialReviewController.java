
package com.zdsoft.finance.finance.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.bpm.annotation.client.FinishJob;
import com.zdsoft.bpm.annotation.client.ManualJob;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.businesssetting.entity.Bank;
import com.zdsoft.finance.businesssetting.service.BankService;
import com.zdsoft.finance.capital.entity.CreditEntrust;
import com.zdsoft.finance.capital.service.CreditEntrustService;
import com.zdsoft.finance.capital.vo.CreditEntrustVo;
import com.zdsoft.finance.casemanage.datasurvey.entity.MatterModuleValidate;
import com.zdsoft.finance.casemanage.datasurvey.entity.MatterNameEnum;
import com.zdsoft.finance.casemanage.datasurvey.service.MatterModuleValidateService;
import com.zdsoft.finance.casemanage.limitApply.entity.CaseLimitApply;
import com.zdsoft.finance.casemanage.limitApply.service.CaseLimitApplyService;
import com.zdsoft.finance.casemanage.receivablePlan.entity.BankAccount;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivableInfoService;
import com.zdsoft.finance.casemanage.receivablePlan.vo.BankAccountVo;
import com.zdsoft.finance.casemanage.receivablePlan.vo.LoanAccountVo;
import com.zdsoft.finance.casemanage.receivablePlan.vo.ReceivableAccountVo;
import com.zdsoft.finance.casemanage.receivablePlan.vo.ReceivableInfoVo;
import com.zdsoft.finance.casemanage.vo.MatterModuleValidateVo;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.finance.entity.ReviewOfArchives;
import com.zdsoft.finance.finance.service.ReviewOfArchivesService;
import com.zdsoft.finance.finance.vo.ReviewOfArchivesVo;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FinancialReviewController.java 
 * @ClassName: FinancialReviewController 
 * @Description: 财务复核  Controller
 * @author dengyy 
 * @date 2017年2月20日 下午1:58:05 
 * @version V1.0 
 */
@Controller
@RequestMapping("/financialReview")
public class FinancialReviewController extends BaseController {
    
    @Autowired
    private ReceivableInfoService receivableInfoService ;
    
    @Autowired
    private CaseApplyService caseApplyService ;
    
    @Autowired
    private CaseLimitApplyService caseLimitApplyService ;
    
    @Autowired
    private CreditEntrustService creditEntrustService ;

    @Autowired
    private BankService bankService ;
    
    @Autowired
    private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService ;
    
    @Autowired
    private ReviewOfArchivesService reviewOfArchivesService ;
    
    @Autowired
    private CED CED ;
    
    @Autowired
    private MatterModuleValidateService matterModuleValidateService ;
    
    /**
     * 
     * @Title: financialReviewEdit 
     * @Description: 财务复核事项
     * @author dengyy  
     * @param projectId 案件id 
     * @param processInstanceId 流程id
     * @param businessKey 业务id
     * @return
     */
    @RequestMapping("/financialReviewEdit")
    @UriKey(key = "com.cnfh.rms.finance.financialReview.financialReviewEdit")
    @ManualJob(resource = "com.cnfh.rms.finance.financialReview.financialReviewEdit", label = "财务复核") 
    public ModelAndView financialReviewEdit(String projectId, String processInstanceId, String businessKey){
        logger.info("----进入财务复核信息页面----");
        ModelMap model = new ModelMap();
        model.put("caseApplyId", projectId);
        model.put("processInstanceId", processInstanceId);
        model.put("businessKey", businessKey);
        try {
            //获取案件信息
            CaseApply caseApply = caseApplyService.findOne(projectId);
            String matterName = MatterNameEnum.FINANCIAL_REVIEW.value;
            List<MatterModuleValidate> validateList = matterModuleValidateService.findByBusinessKeyAndMatterName(projectId, matterName);
            List<MatterModuleValidateVo> validateVoList = new ArrayList<MatterModuleValidateVo>();
            for (MatterModuleValidate matterModuleValidate : validateList) {
                MatterModuleValidateVo validateVo = new MatterModuleValidateVo();
                validateVo.setTabName(matterModuleValidate.getTabName());
                validateVo.setExecuteTag(matterModuleValidate.getExecuteTag());
                validateVoList.add(validateVo);
            }
            String validateVoJson = ObjectHelper.objectToJson(validateVoList);
            model.put("validateVoJson", validateVoJson);
            model.put("productId", caseApply.getProductSubtypeId());
            model.put("CaseApplyCode", caseApply.getCaseApplyCode());
        } catch (BusinessException e) {
            logger.error("获取案件信息出错！",e);
            e.printStackTrace();
        }catch (Exception e) {
            logger.error("获取案件信息出错！",e);
            e.printStackTrace();
        }
        return new ModelAndView("/finance/financialReview/financialReview_total_process_edit", model);
    }
    
    /**
     * 
     * @Title: saveOrUpdateFinancialReview 
     * @Description: 保存财务复核信息
     * @author dengyy 
     * @param reviewOfArchivesVo 
     * @param caseApplyId 案件id
     * @param processInstanceId 流程id
     * @param businessKey 业务id
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateFinancialReview")
    @UriKey(key = "com.cnfh.rms.finance.financialReview.saveOrUpdateFinancialReview")
    @ResponseBody
    public ResponseMsg saveOrUpdateFinancialReview(ReviewOfArchivesVo reviewOfArchivesVo,String caseApplyId, String processInstanceId, String businessKey){
        ResponseMsg msg = new ResponseMsg();
        try {
            EmpDto empDto = CED.getLoginUser();
            ReviewOfArchives archives = reviewOfArchivesService.findByCaseApplyId(caseApplyId);
            if(ObjectHelper.isNotEmpty(archives)){
                archives.setIsAllCollectInformation(reviewOfArchivesVo.getIsAllCollectInformation());
                archives.setRemark(reviewOfArchivesVo.getRemark());
                archives.setUpdateBy(empDto.getEmpCd());
                archives.setUpdateBy(empDto.getOrgCd());
            }else{
                archives = reviewOfArchivesVo.toPo() ;
                archives.setCreateBy(empDto.getEmpCd());
                archives.setCreateOrgCd(empDto.getOrgCd());
            }
            archives = reviewOfArchivesService.saveOrUpdateEntity(archives);
            msg.setId(archives.getId());
            msg.setMsg("保存成功！");
            msg.setResultStatus(ResponseMsg.SUCCESS);
        } catch (BusinessException e) {
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg("保存数据失败");
            logger.error("保存数据失败！",e);
            e.printStackTrace();
        }catch (Exception e) {
            msg.setResultStatus(ResponseMsg.SYS_ERROR);
            logger.error("保存数据失败！",e);
            msg.setMsg("保存数据失败");
            e.printStackTrace();
        }
        return msg;
    }
    
    /**
     * 
     * @Title: saveOrUpdateFinancial 
     * @Description: 提交流程节点
     * @author dengyy 
     * @param caseApplyId 案件id
     * @param processInstanceId 流程id
     * @param businessKey 业务id
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateFinancial")
    @UriKey(key = "com.cnfh.rms.finance.financialReview.saveOrUpdateFinancial")
    @FinishJob(resource = "com.cnfh.rms.finance.financialReview.financialReviewEdit", businessId = "businessKey", projectId = "caseApplyId")
    @ResponseBody
    public ResponseMsg saveOrUpdateFinancial(String caseApplyId, String processInstanceId, String businessKey){
        ResponseMsg msg = new ResponseMsg();
        try {
            msg.setMsg("保存成功！");
            msg.setResultStatus(ResponseMsg.SUCCESS);
        } catch (Exception e) {
            msg.setResultStatus(ResponseMsg.SYS_ERROR);
            logger.error("保存数据失败！",e);
            msg.setMsg("保存数据失败");
            e.printStackTrace();
        }
        return msg;
    }
    
    /**
     * 
     * @Title: caseApplyView 
     * @Description: 案件基础信息
     * @author dengyy 
     * @param caseApplyId 案件id
     * @return
     */
    @RequestMapping("/caseApplyView")
    @UriKey(key="com.cnfh.rms.finance.financialReview.caseApplyView")
    public ModelAndView caseApplyView(String caseApplyId){
        logger.info("进入财务复核--案件基础信息!");
        logger.debug("案件id："+caseApplyId);
        ModelMap model = new ModelMap();
        try {
            //计划基础信息
            ReceivableInfo receivableInfo = receivableInfoService.findByCaseApplyId(caseApplyId);
            //案件信息
            CaseApply caseApply = caseApplyService.findOne(caseApplyId);
            //银行账号
            List<BankAccount> caseBankAccount = caseApply.getCaseBankAccount();
            BankAccountVo bankAccountVo = new BankAccountVo();
            for(BankAccount bank : caseBankAccount){
                //收款银行
                if(bank.getAccountType()==0){
                    BankAccount loanAccount = bank;
                    LoanAccountVo loanAccountVo = new LoanAccountVo();
                    BeanUtils.copyProperties(loanAccount, loanAccountVo);
                    bankAccountVo.setLoanAccountVo(loanAccountVo);
                }
                //还款银行 
                if(bank.getAccountType()==1){
                    BankAccount recAccount = bank;
                    ReceivableAccountVo recAccountVo = new ReceivableAccountVo();
                    BeanUtils.copyProperties(recAccount, recAccountVo);
                    bankAccountVo.setRecAccountVo(recAccountVo);
                }
            }
            //银行账号
            if(ObjectHelper.isNotEmpty(bankAccountVo)){
                model.put("bankAccountVo", bankAccountVo);
            }
            //案件信息
            model.put("caseApplyVo", new CaseApplyVo(caseApply));
            //计划基础信息
            if(ObjectHelper.isNotEmpty(receivableInfo)){
                ReceivableInfoVo receivableInfoVo = new ReceivableInfoVo(receivableInfo);
                model.put("receivableInfoVo", receivableInfoVo);
            }
            //获取额度信息
            List<CaseLimitApply> list = caseLimitApplyService.findByCaseApplyIdAndEffectiveStatus(caseApplyId, CaseLimitApply.ALLOCATED_FUNDS);
            if(ObjectHelper.isNotEmpty(list)){
                CaseLimitApply caseLimitApply = list.get(0);
                //获取资金计划
                CreditEntrust creditEntrust = creditEntrustService.findOne(caseLimitApply.getFundPlanId());
                model.put("creditEntrustVo", new CreditEntrustVo(creditEntrust));
                //获取银行代码
                List<Bank> nameByLike = bankService.findBankWithNameByLike(creditEntrust.getSpareAccountBank());
                if(ObjectHelper.isNotEmpty(nameByLike)){
                    Bank bank = nameByLike.get(0);
                    model.put("bankCode", bank.getBankCode()); 
                }
            }
            //获取案件的主借人
            List<BeforeCustomer> joinType = caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(caseApplyId, CaseApplyBeforeCustomer.MAIN_BORROW);
            if(ObjectHelper.isNotEmpty(joinType)){
                model.put("customerName", joinType.get(0).getCustomerName()); 
            }
        } catch (BusinessException e) {
            logger.error("案件基础信息出错！",e);
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("案件基础信息出错！",e);
            e.printStackTrace();
        }
        
        return new ModelAndView("/finance/financialReview/financialReview_caseApply_view", model);
    }
    
    /**
     * 
     * @Title: archivesView 
     * @Description: 财务复核--档案信息
     * @author dengyy 
     * @param caseApplyId 案件id
     * @return
     */
    @RequestMapping("/archivesView")
    @UriKey(key="com.cnfh.rms.finance.financialReview.archivesView")
    public ModelAndView archivesView(String caseApplyId){
        logger.info("进入财务复核--档案信息");
        logger.debug("案件id："+caseApplyId);
        ModelMap model = new ModelMap();
        model.put("caseApplyId", caseApplyId);
        try {
            ReviewOfArchives archives = reviewOfArchivesService.findByCaseApplyId(caseApplyId);
            if(ObjectHelper.isNotEmpty(archives)){
                ReviewOfArchivesVo reviewOfArchivesVo = new ReviewOfArchivesVo(archives);
                model.put("reviewOfArchivesVo", reviewOfArchivesVo);
            }
        } catch (BusinessException e) {
            logger.error("获取信息失败！",e);
            e.printStackTrace();
        }catch (Exception e) {
            logger.error("档案信息出错！",e);
            e.printStackTrace();
        }
        return new ModelAndView("/finance/financialReview/financialReview_archives_edit", model);
    }
    
    /**
     * 
     * @Title: archivesListData 
     * @Description: 获取财务复核--档案信息列表
     * @author dengyy 
     * @param caseApplyId 案件id
     * @return
     */
    @RequestMapping("/archivesListData")
    @UriKey(key="com.cnfh.rms.finance.financialReview.archivesListData")
    @ResponseBody
    public ResponseMsg archivesListData(String caseApplyId){
        ResponseMsg msg = new ResponseMsg() ;
        return msg ;        
    }
}
