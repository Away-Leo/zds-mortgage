package com.zdsoft.finance.casemanage.interview.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.interview.entity.Interview;
import com.zdsoft.finance.casemanage.receivablePlan.entity.BankAccount;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: InterviewService.java 
 * @ClassName: InterviewService 
 * @Description: 案件面签信息服务接口
 * @author dengyy 
 * @date 2017年2月15日 上午10:11:37 
 * @version V1.0
 */
public interface InterviewService extends BaseService<Interview>{

    /**
     * 
     * @Title: findByCaseApplyId 
     * @Description: 根据案件id获取面签信息
     * @author dengyy 
     * @param caseApplyId 案件id
     * @return
     * @throws BusinessException 
     */
    public Interview findByCaseApplyId(String caseApplyId)throws BusinessException ;

    /** 
     * @Title: saveOrUpdateInterview 
     * @Description: 保存面签信息  和更新案件收款、还款（代扣）银行账号信息
     * @author dengyy 
     * @param interview 面签信息
     * @param loanAccount 收款银行信息
     * @param recAccount 还款（代扣）银行信息
     * @return  
     * @throws Exception 
     */ 
    public Interview saveOrUpdateInterview(Interview interview, BankAccount loanAccount, BankAccount recAccount) throws Exception;
    
    /**
     * 
     * @Title: findPageInterview 
     * @Description: app 获取面签信息列表
     * @author dengyy 
     * @param pageable 分页信息
     * @param queryObjs 查询条件
     * @param dataOperPermDto 数据权限
     * @return
     * @throws Exception 
     */
    public List<Map<String,String>> findPageInterview(PageRequest pageable, List<QueryObj> queryObjs,DataOperPermDto dataOperPermDto)throws  Exception ;
}
