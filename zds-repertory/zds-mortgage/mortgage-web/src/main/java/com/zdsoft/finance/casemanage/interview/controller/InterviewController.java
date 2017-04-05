package com.zdsoft.finance.casemanage.interview.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.bpm.dto.TaskOpinionDto;
import com.zdsoft.bpm.service.client.BPM;
import com.zdsoft.essential.client.aop.annotation.DataAuthResource;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.businesssetting.entity.Bank;
import com.zdsoft.finance.businesssetting.entity.MortOwner;
import com.zdsoft.finance.businesssetting.service.BankService;
import com.zdsoft.finance.businesssetting.service.MortOwnerService;
import com.zdsoft.finance.casemanage.interview.entity.Interview;
import com.zdsoft.finance.casemanage.interview.service.InterviewService;
import com.zdsoft.finance.casemanage.interview.vo.InterviewVo;
import com.zdsoft.finance.casemanage.receivablePlan.entity.BankAccount;
import com.zdsoft.finance.casemanage.receivablePlan.vo.BankAccountVo;
import com.zdsoft.finance.casemanage.receivablePlan.vo.LoanAccountVo;
import com.zdsoft.finance.casemanage.receivablePlan.vo.ReceivableAccountVo;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.exception.AppException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.common.util.StoreHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: InterviewController.java 
 * @ClassName: InterviewController 
 * @Description: 案件面签管理控制器
 * @author dengyy 
 * @date 2017年2月17日 下午1:34:46 
 * @version V1.0
 */
@Controller
@RequestMapping("interview")
public class InterviewController extends BaseController{
	
	@Autowired
	private InterviewService interviewService;
	
	@Autowired
	private CaseApplyService caseApplyService;
	
	@Autowired
	private BeforePersonalCustomerService beforePersonalCustomerService;
	
	@Autowired
	private MortOwnerService mortOwnerService ;
	
	@Autowired
	private BankService bankService;
	
	@Autowired
	private CED CED ;
	
	@Autowired
	private BPM BPM ;
	
	/**
	 * 
	 * @Title: interviewList 
	 * @Description:  案件面签管理入口
	 * @author dengyy 
	 * @return
	 */
	@RequestMapping("/interviewList")
	@UriKey(key = "com.cnfh.rms.casemanage.interview.interviewList")
	@Menu(resource = "com.cnfh.rms.casemanage.interview.interviewList", label = "面签管理", group = "project", order = 3)
	@DataAuthResource(resource="com.cnfh.rms.casemanage.interview.interviewList.dataAuth",label="面签管理",group="com.cnfh.rms.casemanage.interview.interviewList")
	public ModelAndView interviewList() {
		return new ModelAndView("/casemanage/interview/case_interview_list");
	}
	
	/**
	 * 
	 * @Title: getInterviewList 
	 * @Description:  面签管理分页查询列表
	 * @author dengyy 
	 * @param request 请求信息
	 * @param pageable 分页信息
	 * @return
	 */
	@RequestMapping("/findInterviewList")
	@UriKey(key = "com.cnfh.rms.casemanage.interview.findInterviewList")
	@ResponseBody
	public ResponseMsg findInterviewList(HttpServletRequest request, PageRequest pageable,Boolean flag){
	    logger.info("进入获取案件面签列表信息！");
	    ResponseMsg msg = new ResponseMsg();
	    try {
    	    // 获取查询参数
    		@SuppressWarnings("unchecked")
    		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
    		if(ObjectHelper.isEmpty(flag)){
                QueryObj qu = new QueryObj();
                qu.setElement("interviewStatus");
                qu.setObj("c");
                qu.setOperator("=");
                qu.setValue(Interview.SIMPLECODE_ONE);
                queryObjs.add(qu);
            }
    		DataOperPermDto dataOperPermDto = CED.findDataPerms(StoreHelper.getApplication(), "com.cnfh.rms.casemanage.interview.interviewList.dataAuth");
    		// 获取案件面签信息
    		Page<Map<String, Object>> interviews = caseApplyService.findPageInterview(pageable, queryObjs,dataOperPermDto);
            msg.setMsg("列表查询成功");
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setTotal(interviews.getTotalRows());
            msg.setRows(interviews.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
            msg.setResultStatus(ResponseMsg.SYS_ERROR);
            msg.setMsg("查询信息失败！");
            logger.error("查询面签信息失败 :",e);
        }
		return msg;
	}
	
	/**
	 * 
	 * @Title: interviewEdit 
	 * @Description: 面签页面编辑页面
	 * @author dengyy 
	 * @param id 案件id
	 * @return
	 */
	@RequestMapping("/interviewEdit")
	@UriKey(key="com.cnfh.rms.casemanage.interview.interviewEdit")
	public ModelAndView interviewEdit(String id){
	    logger.info("进入案件面签的编辑页面！");
	    logger.debug("案件id：{}"+id);
		Map<String,Object> interviewModel = new HashMap<String,Object>();
		interviewModel.put("caseApplyId", id);
        try {
            //获取案件信息
            CaseApply caseApply = caseApplyService.findOne(id);
            //获取案件的收款银行和还款（代扣）银行
            List<BankAccount> caseBankAccount = caseApply.getCaseBankAccount();
            BankAccountVo bankAccountVo = new BankAccountVo();
            for(BankAccount bank : caseBankAccount){
                //收款账户
                if(bank.getAccountType()==0){
                    BankAccount loanAccount = bank;
                    LoanAccountVo loanAccountVo = new LoanAccountVo();
                    BeanUtils.copyProperties(loanAccount, loanAccountVo);
                    bankAccountVo.setLoanAccountVo(loanAccountVo);
                }
                //还款账户
                if(bank.getAccountType()==1){
                    BankAccount recAccount = bank;
                    ReceivableAccountVo recAccountVo = new ReceivableAccountVo();
                    BeanUtils.copyProperties(recAccount, recAccountVo);
                    bankAccountVo.setRecAccountVo(recAccountVo);
                }
            }
            //面签信息
            Interview interview = interviewService.findByCaseApplyId(id);
            if(ObjectHelper.isNotEmpty(interview)){
                InterviewVo interviewVo = new InterviewVo(interview);
                interviewModel.put("interviewVo", interviewVo);
            }
            interviewModel.put("bankAccountVo", bankAccountVo);
            
            String mortgageHolder = this.findMortgageHolder(id);
            interviewModel.put("mortgageHolder", mortgageHolder);
            
        } catch (BusinessException e) {
            logger.error("面签的编辑页面！",e);
            e.printStackTrace();
        }
		return new ModelAndView("/casemanage/interview/case_interview_edit", interviewModel);
	}
	
	/**
	 * 
	 * @Title: interviewTotelEdit 
	 * @Description: 案件面签总页面
	 * @author dengyy 
	 * @param projectId 案件id
	 * @return
	 */
	@RequestMapping("/interviewTotelEdit")
	@UriKey(key="com.cnfh.rms.casemanage.interview.interviewTotelEdit")
	public ModelAndView interviewTotelEdit(String projectId){
	    logger.info("进入案件面签的编辑总页面！");
	    logger.debug("案件id：{}"+projectId);
        ModelMap map = new ModelMap() ;
        map.put("caseApplyId", projectId);
        try {
            CaseApply caseApply = caseApplyService.findOne(projectId);
            map.put("productId", caseApply.getProductSubtypeId());
        } catch (BusinessException e) {
            logger.error("获取案件信息失败！",e);
            e.printStackTrace();
        }
        return new ModelAndView("/casemanage/interview/case_interview_base_edit", map);
	}
	
	/**
	 * 
	 * @Title: interviewTotelView 
	 * @Description: 案件面签查看总页面
	 * @author dengyy 
	 * @param projectId 案件id
	 * @return
	 */
	@RequestMapping("/interviewTotelView")
    @UriKey(key="com.cnfh.rms.casemanage.interview.interviewTotelView")
    public ModelAndView interviewTotelView(String projectId){
        logger.info("进入案件面签的编辑总页面！");
        logger.debug("案件id：{}"+projectId);
        ModelMap map = new ModelMap() ;
        map.put("caseApplyId", projectId);
        try {
            CaseApply caseApply = caseApplyService.findOne(projectId);
            map.put("productId", caseApply.getProductSubtypeId());
        } catch (BusinessException e) {
            logger.error("获取案件信息失败！",e);
            e.printStackTrace();
        }
        return new ModelAndView("/casemanage/interview/case_interview_base_view", map);
    }
	
	/**
	 * 
	 * @Title: saveInterview 
	 * @Description: 保存修改面签信息
	 * @author dengyy 
	 * @param interviewVo 面签信息
	 * @param bankAccountVo 案件银行信息
	 * @return
	 */
	@RequestMapping("/saveInterview")
	@UriKey(key = "com.cnfh.rms.casemanage.interview.saveInterview")
	@ResponseBody
	public ResponseMsg saveInterview(InterviewVo interviewVo,BankAccountVo bankAccountVo){
	    logger.info("进入案件的面签信息保存！");
		ResponseMsg msg = new ResponseMsg();
        try {
            //面签信息转换
            Interview interview = interviewVo.toPo();
            //收款
            LoanAccountVo loanAccountVo = bankAccountVo.getLoanAccountVo();
            //还款
            ReceivableAccountVo recAccountVo = bankAccountVo.getRecAccountVo();
            //收款账户
            BankAccount loanAccount =  loanAccountVo.toPo(loanAccountVo, new BankAccount());
            //还款(代扣)账户
            BankAccount recAccount =  loanAccountVo.toPo(recAccountVo, new BankAccount());
            logger.info("收款账户！{}"+ObjectHelper.objectToJson(loanAccount));
            logger.info("还款(代扣)账户！{}"+ObjectHelper.objectToJson(recAccount));
            interview = interviewService.saveOrUpdateInterview(interview,loanAccount,recAccount);
            msg.setId(interview.getId());
            if(interviewVo.getIsSubmit()){
                msg.setMsg("保存成功！下一处理人："+interview.getCurrentDealEmpNm());
            }else{
                msg.setMsg("保存成功！");
            }
            msg.setResultStatus(ResponseMsg.SUCCESS);
        } catch (Exception e) {
            logger.error("保存面签信息失败！",e);
            msg.setResultStatus(ResponseMsg.SYS_ERROR);
            msg.setMsg("保存面签信息失败！");
            e.printStackTrace();
        }
		return msg;
	}
	
	/**
	 * 
	 * @Title: findHousePropertyInfo 
	 * @Description: 获取案件的押品信息（房产信息）
	 * @author dengyy 
	 * @param caseApplyId 案件id
	 * @return
	 */
	@RequestMapping("/findHousePropertyInfo")
	@UriKey(key = "com.cnfh.rms.casemanage.interview.findHousePropertyInfo")
	@ResponseBody
	public ResponseMsg findHousePropertyInfo(String caseApplyId){
	    logger.info("获取案件的押品（房产）信息");
	    logger.debug("案件id：{}"+caseApplyId);
		ResponseMsg msg = new ResponseMsg();
        try {
            List<Map<String, Object>> houseInfos = caseApplyService.queryHouseInfo(caseApplyId);
            msg.setMsg("列表查询成功");
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setRows(houseInfos);
        } catch (Exception e) {
            logger.error("获取案件的押品失败！",e);
            e.printStackTrace();
        }
	
		return msg;
	}
	
	/**
	 * 
	 * @Title: findThirdPartyRepayAccreditName 
	 * @Description: 获取参与方人员(除主借人外) 
	 * @author dengyy 
	 * @param caseApplyId 案件id
	 * @return
	 */
	@RequestMapping("/findThirdPartyRepayAccreditName")
	@UriKey(key = "com.cnfh.rms.casemanage.interview.findThirdPartyRepayAccreditName")
	@ResponseBody
	public String findThirdPartyRepayAccreditName(String caseApplyId){
	    logger.info("获取案件参与方人员(除主借人外)信息！");
	    logger.debug("案件id：{}"+caseApplyId);
	    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		List<BeforePersonalCustomer> beforePersonCustomers = beforePersonalCustomerService.queryByCaseApplyId(caseApplyId);
		for (BeforePersonalCustomer beforePersonalCustomer : beforePersonCustomers) {
		    Map<String, String> map = new HashMap<String, String>();
		    map.put("fullCode", beforePersonalCustomer.getId());
		    map.put("name", beforePersonalCustomer.getCustomerName());
		    list.add(map);
        }
		return ObjectHelper.objectToJson(list);
	}
	
	/**
	 * 
	 * @Title: findMortgageHolder 
	 * @Description: 获取案件机构的抵押权证人
	 * @author dengyy 
	 * @param caseApplyId 案件id
	 * @return
	 */
	@RequestMapping("/findMortgageHolder")
	@UriKey(key = "com.cnfh.rms.casemanage.interview.findMortgageHolder")
    @ResponseBody
	public String findMortgageHolder(String caseApplyId){
	    logger.info("获取案件机构的抵押权证人");
	    logger.debug("案件id："+caseApplyId);
	    List<Map<String, String>> map = new ArrayList<Map<String, String>>();
	    try {
	        //获取案件信息
            CaseApply caseApply= caseApplyService.findOne(caseApplyId);
            if(ObjectHelper.isNotEmpty(caseApply)){
                //案件对应公司的抵押权证人信息
                //TODO 后期修改为常量类中维护
                List<MortOwner> list = mortOwnerService.findByOrgCode(caseApply.getMechanismCode(),"YWDM0012202");
                //转换json数据
                for (MortOwner mortOwner : list) {
                    Map<String, String> maps = new HashMap<String, String>();
                    maps.put("fullcode", mortOwner.getId());
                    maps.put("name", mortOwner.getOwnerName());
                    map.add(maps);
                }
            }else{
                throw new BusinessException();
            }
        } catch (BusinessException e) {
            logger.error("获取抵押权证人信息失败！",e);
            e.printStackTrace();
        }catch (Exception e) {
            logger.error("获取抵押权证人信息失败！",e);
            e.printStackTrace();
        }
	    return ObjectHelper.objectToJson(map) ;
	}
	
	/**
	 * 
	 * @Title: findBankByLikeName 
	 * @Description: 自动匹配 银行信息
	 * @author dengyy 
	 * @param id 银行名称（页面输入的值）
	 * @return
	 */
	@RequestMapping("/findBankByLikeName")
	@UriKey(key="com.cnfh.rms.casemanage.interview.findBankByLikeName")
	@ResponseBody
	public String findBankByLikeName(String id){
	    logger.info("进入银行信息模糊查询！");
	    List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
	    Map<String, Object> mapListRt = new HashMap<String, Object>();
	    if(ObjectHelper.isEmpty(id)){
            id = "" ;
        }
        List<Bank> list = null ;
        try{
             list = bankService.findBankWithNameByLike(id);
        }catch (BusinessException e) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("bankCode", "");
            map.put("bankName", "");
            mapList.add(map);
            return ObjectHelper.objectToJson(mapListRt);
        }
        if(ObjectHelper.isNotEmpty(list)){
            for (Bank bank : list) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("bankCode", bank.getBankCode());
                map.put("bankName", bank.getBankName());
                mapList.add(map);
            }
        }
        mapListRt.put("data", mapList);
        return ObjectHelper.objectToJson(mapListRt);
	    
	}
	
	/**
	 * 
	 * @Title: approvalOpinion 
	 * @Description: 获取案件的审批意见
	 * @author dengyy 
	 * @param request
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping("/approvalOpinion")
    @UriKey(key="com.cnfh.rms.casemanage.interview.approvalOpinion")
	public ModelAndView approvalOpinion(HttpServletRequest request,String caseApplyId){
	    logger.info("进入获取案件的审批意见信息");
	    logger.info("案件id:"+caseApplyId);
	    ModelMap map = new ModelMap();
	    try {
            CaseApply caseApply = caseApplyService.findOne(caseApplyId);
            BusiForm busiForm = caseApply.getBusiForm();
            List<TaskOpinionDto> opinions = BPM.findTaskOpinions(busiForm.getProcessInstanceKey(), busiForm.getBusinessEntityId());
            map.put("opinions", opinions);
	    } catch (BusinessException e) {
            logger.error("获取案件信息失败！",e);
            e.printStackTrace();
        }catch (AppException e) {
            logger.error("获取案件审批意见失败！",e);
            e.printStackTrace();
        }
        return new ModelAndView("/casemanage/interview/case_approvalOpinion_view", map);
	}

	/**
	 * 
	 * @Title: viewInterviewDetails 
	 * @Description: 面签详情页面
	 * @author dengyy 
	 * @param caseApplyId 案件id
	 * @return
	 */
	@RequestMapping("/viewInterviewDetails")
	@UriKey(key="com.cnfh.rms.casemanage.interview.viewInterviewDetails")
	public ModelAndView viewInterviewDetails(String caseApplyId) {
	    logger.info("初始化案件面签查看页面！");
	    logger.debug("案件id：{}"+caseApplyId);
		Map<String,Object> interviewModel = new HashMap<String,Object>();
        try {
            //案件信息
            CaseApply caseApply = caseApplyService.findOne(caseApplyId);
            //案件银行信息
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
    		//案件面签信息
    		Interview interview = interviewService.findByCaseApplyId(caseApplyId);
    		InterviewVo interviewVo = new InterviewVo(interview);
    		
    		interviewModel.put("interviewVo", interviewVo);
    		interviewModel.put("caseApplyId", caseApplyId);
    		interviewModel.put("bankAccountVo", bankAccountVo);
        } catch (BusinessException e) {
            logger.error("初始化面签查看页面信息失败！",e);
            e.printStackTrace();
        }
		return new ModelAndView("/casemanage/interview/case_interview_view", interviewModel);
	}
}
