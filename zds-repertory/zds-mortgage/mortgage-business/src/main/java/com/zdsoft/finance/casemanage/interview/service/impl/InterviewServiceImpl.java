package com.zdsoft.finance.casemanage.interview.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.busiform.service.BusiFormService;
import com.zdsoft.finance.casemanage.interview.entity.Interview;
import com.zdsoft.finance.casemanage.interview.repository.InterviewRepository;
import com.zdsoft.finance.casemanage.interview.service.InterviewService;
import com.zdsoft.finance.casemanage.receivablePlan.entity.BankAccount;
import com.zdsoft.finance.casemanage.receivablePlan.service.BankAccountService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.enums.busiform.ApplyModelTypeEnum;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import net.sf.json.JSONArray;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: InterviewServiceImpl.java 
 * @ClassName: InterviewServiceImpl 
 * @Description: 案件面签信息服务接口的实现
 * @author dengyy 
 * @date 2017年2月15日 上午10:12:24 
 * @version V1.0
 */
@Service("interviewService")
public class InterviewServiceImpl extends BaseServiceImpl<Interview, InterviewRepository>implements InterviewService {

    @Resource
    private BankAccountService bankAccountService ;
    
    @Resource
    private CaseApplyService caseApplyService ;
    
    @Resource
    private BusiFormService busiFormService ;
    
    @Resource
    private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService ;
    
    @Resource
    private HousePropertyService housePropertyService ;
    
    @Resource
    private CED CED;
    
    @Override
    public Interview findByCaseApplyId(String caseApplyId) throws BusinessException {
        if(ObjectHelper.isEmpty(caseApplyId)){
            throw new BusinessException("10010004", "传入参数为空");
        }
        return this.customReposity.findByCaseApplyId(caseApplyId);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Interview saveOrUpdateInterview(Interview interview, BankAccount loanAccount, BankAccount recAccount) throws Exception {
        Interview entity = null ;
        EmpDto empDto = CED.getLoginUser();
        //1、先保存面签信息
        if(ObjectHelper.isNotEmpty(interview.getId())){
            entity = this.customReposity.findOne(interview.getId());
            entity.setUpdateBy(empDto.getEmpCd());
            entity.setUpdateOrgCd(empDto.getOrgCd());
        }else{
            entity = new Interview();
            entity.setCreateBy(empDto.getEmpCd());
            entity.setCreateOrgCd(empDto.getOrgCd());
        }
        BeanUtils.copyProperties(interview, entity,new String[]{"id","createTime","isDeleted","createBy","createOrgCd","version","busiForm"});
        entity.setInterviewStatus(Interview.PROCEESSCODE_TWO);
        //面签时间
        entity.setActualInterviewDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT));
        //保存面签信息
        entity = this.saveOrUpdateEntity(entity);
        
        //获取案件信息
        CaseApply caseApply = caseApplyService.findOne(entity.getCaseApplyId());
        caseApply.setInterview(entity);
        caseApply.setInterviewStatus(Interview.PROCEESSCODE_TWO);
        //修改案件信息
        caseApply = caseApplyService.saveOrUpdateEntity(caseApply);
        
        //获取案件的主借人
        List<BeforeCustomer> caseApplyIdAndJoinType = caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(caseApply.getId(), CaseApplyBeforeCustomer.MAIN_BORROW);
        
        //2、保存BusiForm信息 草稿获取启动流程
        BusiForm busiForm = entity.getBusiForm();
        if(ObjectHelper.isEmpty(busiForm)){
            busiForm = new BusiForm();
        }
        busiForm.setBusinessEntityId(entity.getId());
        busiForm.setBusinessEntityName(Interview.class.getSimpleName());
        busiForm.setComponentsEntityId(entity.getCaseApplyId());
        busiForm.setComponentsEntityName(CaseApply.class.getSimpleName());
        busiForm.setFormStatus(BusiFormStatus.DRAFT.value);
        //面签申请：标题 案件号+主借人+产品      业务编号：案件号
        busiForm.setApplyTitle(caseApply.getCaseApplyCode()+caseApplyIdAndJoinType.get(0).getCustomerName()+caseApply.getProductSubtypeName());
        busiForm.setBusinessCode(caseApply.getCaseApplyCode());
        //所属模块
        busiForm.setModelType(ApplyModelTypeEnum.CASE_INTERVIEW.value);
        busiForm = busiFormService.saveOrUpdateBusiForm(busiForm);
        entity.setBusiForm(busiForm);
        entity = this.saveOrUpdateEntity(entity);
        
        //3、修改案件的收款银行和还款（代扣）银行
        //获取已有的银行信息
        List<BankAccount> caseBankAccount = caseApply.getCaseBankAccount();
        if(ObjectHelper.isNotEmpty(caseBankAccount)){
            for(BankAccount bank : caseBankAccount){
                //收款账户
                if(bank.getAccountType()==0){
                    BankAccount loanAccount1 = bank;
                    //BeanUtils.copyProperties(loanAccount, loanAccount1);
                    // 银行代码
                    loanAccount1.setBankCode(loanAccount.getBankCode());
                    // 银行卡号
                    loanAccount1.setBankNo(loanAccount.getBankNo());
                    // 开户银行
                    loanAccount1.setBankAccount(loanAccount.getBankAccount());
                    //账户名
                    loanAccount1.setCardholderName(loanAccount.getCardholderName());
                    bankAccountService.saveOrUpdateEntity(loanAccount1);
                }
                //还款账户
                if(bank.getAccountType()==1){
                    BankAccount recAccount1 = bank;
                    //BeanUtils.copyProperties(recAccount, recAccount1);
                    // 银行代码
                    recAccount1.setBankCode(recAccount.getBankCode());
                    // 银行卡号
                    recAccount1.setBankNo(recAccount.getBankNo());
                    // 开户银行
                    recAccount1.setBankAccount(recAccount.getBankAccount());
                    //账户名
                    recAccount1.setCardholderName(recAccount.getCardholderName());
                    bankAccountService.saveOrUpdateEntity(recAccount1);
                }
            }
        }else{
            //没有案件银行信息 就新建保存信息
            //收款
            loanAccount.setAccountType(BankAccount.RECEIVABLES);
            loanAccount.setCaseApplyId(entity.getCaseApplyId());
            bankAccountService.saveOrUpdateEntity(loanAccount);
            //还款
            recAccount.setAccountType(BankAccount.REPAYMENT);
            recAccount.setCaseApplyId(entity.getCaseApplyId());
            bankAccountService.saveOrUpdateEntity(recAccount);
        }
        
        //4、 解析案件房产信息  json解析
        JSONArray jsonArray = JSONArray.fromObject(interview.getHouseData());  
        @SuppressWarnings({ "unchecked", "rawtypes" })
        List<Map<String,Object>> mapListJson = (List)jsonArray;  
        for (int i = 0; i < mapListJson.size(); i++) {  
            Map<String,Object> obj=mapListJson.get(i);
            //获取房产信息
            HouseProperty property = housePropertyService.findOne(obj.get("HOUSEID").toString());
            property.setHouseNo(obj.get("HOUSENO").toString());
            property.setExpectedDate(DateHelper.stringDateToLong(obj.get("MORTGAGEDATE").toString(), DateHelper.DATE_SHORT_SIMPLE_FORMAT, DateHelper.DATE_SHORT_SIMPLE_FORMAT));
            if(ObjectHelper.isNotEmpty(obj.get("MORTGAGEEID"))){
                property.setMortgageeName(obj.get("MORTGAGEEIDText").toString());
                property.setMortgageeId(obj.get("MORTGAGEEID").toString());
            }
            housePropertyService.saveOrUpdateEntity(property);
        }  
        //判断是否启动流程
        if(interview.getIsSubmit()){
            //功能代码
            busiForm.setFunctionCode(Interview.proceessCode);
            //产品id
            busiForm.setProductId(caseApply.getProductSubtypeId());
            //启动流程
            busiForm = busiFormService.startProcess(busiForm, null, null);
            //下一处理人
            entity.setCurrentDealEmpNm(busiForm.getCurrentDealEmpNm());
        }
        return entity;
    }

    @Override
    public List<Map<String,String>>findPageInterview(PageRequest pageable, List<QueryObj> queryObjs,DataOperPermDto dataOperPermDto) throws Exception {
        StringBuffer dataAuth = this.developmentManagerDataAuth(dataOperPermDto, "c");
        Page<Map<String,Object>> page = this.customReposity.findPageInterview(pageable, queryObjs,dataAuth);
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        if(ObjectHelper.isNotEmpty(page.getRecords())){
            List<Map<String,Object>> records = page.getRecords();
            for (Map<String, Object> map : records) {
                Map<String,String> mapRtn = new HashMap<String,String>();
                String province = map.get("PROVINCE").toString();
                String city = map.get("CITY").toString();
                String district = map.get("DISTRICT").toString();
                List<String> arg0 = new ArrayList<String>();
                arg0.add(province);
                arg0.add(city);
                arg0.add(district);
                //转换省市区 地址
                Map<String, String> fullCodes = CED.loadSimpleCodeNameByFullCodes(arg0);
                mapRtn.put("address", fullCodes.get(province)+fullCodes.get(city)+fullCodes.get(district)+map.get("MAILINGADDRESS").toString());
                mapRtn.put("caseApplyId", map.get("CASEAPPLYID").toString());
                mapRtn.put("caseApplyCode", map.get("CASEAPPLYCODE").toString());
                //联系电话
                if(ObjectHelper.isNotEmpty(map.get("PHONENUMBER"))){
                    mapRtn.put("phoneNumber", map.get("PHONENUMBER").toString());
                }else{
                    mapRtn.put("phoneNumber", "");
                }
                if(ObjectHelper.isNotEmpty(map.get("SYNTHESIZEPRICE"))){
                    mapRtn.put("synthesizePrice", map.get("SYNTHESIZEPRICE").toString());
                }else{
                    mapRtn.put("synthesizePrice", "");
                }
                if(ObjectHelper.isNotEmpty(map.get("SYNTHESIZEPRICE"))){
                    mapRtn.put("synthesizePrice", map.get("SYNTHESIZEPRICE").toString());
                }else{
                    mapRtn.put("synthesizePrice", "");
                }
                if(ObjectHelper.isNotEmpty(map.get("COMMUNITYNAME"))){
                    mapRtn.put("communityName", map.get("COMMUNITYNAME").toString());
                }else{
                    mapRtn.put("communityName", "");
                }
                if(ObjectHelper.isNotEmpty(map.get("RECIPIENTS"))){
                    mapRtn.put("recipients", map.get("RECIPIENTS").toString());
                }else{
                    mapRtn.put("recipients", "");
                }
                list.add(mapRtn);
            }
        }
        return list;
    }
	
}
