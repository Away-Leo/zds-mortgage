package com.zdsoft.finance.customer.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drools.inparams.HandleVerifyInParam;
import com.drools.outparams.HandleVerifyOutParam;
import com.drools.taskname.TaskName;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.ConstantParameter;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BlanckList;
import com.zdsoft.finance.customer.repository.BlanckListRepository;
import com.zdsoft.finance.customer.service.BlanckListService;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import demo.cnfhrule.BeforeCustomer;
import demo.cnfhrule.BeforeCustomerList;
import demo.cnfhrule.RuleInfo;
import sense.vitality.client.SenseClient;
import sense.vitality.protocol.Stuff;
import sense.vitality.text.Result;

@Service
public class BlanckListServiceImpl extends BaseServiceImpl<BlanckList, BlanckListRepository> 
	implements BlanckListService {
	
	@Autowired
	private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService ;
	
    @Override
    public List<BlanckList> findByALL() throws BusinessException {
        StringBuffer hql = new StringBuffer(" from BlanckList t where 1=1");
        hql.append(" and t.isDeleted =:isDeleted");
        Map<String, Object> condition = new HashMap<>();
        condition.put("isDeleted", !BaseEntity.DELETED);
        List<BlanckList> list = this.customReposity.findByHql(hql.toString(), condition);
        return list;
    }

	@Override
	public Boolean checkBlankList(String caseApplyId) throws BusinessException {
		if(ObjectHelper.isEmpty(caseApplyId)){
			throw new BusinessException("10010004", "案件id不能为空！");
		}
		boolean falg = false ;
		//调用规则,调用地址和端口号建议做到配置文件里，以应对运行环境的切换和服务器迁移
        SenseClient sc = SenseClient.instance(ConstantParameter.getRuleEngineUrl(),Integer.parseInt(ConstantParameter.getRuleEnginePort()));
        //设置任务名（目前只有黑名单验证规则）
        Stuff req = sc.instanceRequest(TaskName.CALL_DROOLS_TASK);
        //设置入参（ 黑名单规则入参包括，黑名单List，客户，规则信息（组ID，构件ID，版本号））
        HandleVerifyInParam inparam = new HandleVerifyInParam();
        //客户关系
        List<CaseApplyBeforeCustomer> customerList = caseApplyBeforeCustomerService.queryByCaseApplyId(caseApplyId);
        //设置客户
        List<BeforeCustomer> beforeCustomerlist= new ArrayList<BeforeCustomer>();
        for (CaseApplyBeforeCustomer caseApplyBeforeCustomer : customerList) {
        	 com.zdsoft.finance.customer.entity.BeforeCustomer beforeCustomer = caseApplyBeforeCustomer.getBeforeCustomer();
        	 BeforeCustomer beCus = new BeforeCustomer();
             //BeanUtils.copyProperties(beforeCustomer, beCus);
        	 beCus.setCredentialNo(beforeCustomer.getCredentialNo());
        	 beCus.setCustomerName(beforeCustomer.getCustomerName());
             beforeCustomerlist.add(beCus);
		}
        BeforeCustomerList beforeCustomers = new BeforeCustomerList();
        beforeCustomers.setBeforeCustomers(beforeCustomerlist);
        inparam.setBeforeCustomers(beforeCustomers);
        //设置规则组ID，构件ID，版本号 通过配置
        RuleInfo ruleinfo = new RuleInfo();
        ruleinfo.setGroupId(ConstantParameter.getRuleEngineGroupId());
        ruleinfo.setArtifactId(ConstantParameter.getRuleEngineArtifactId());
        ruleinfo.setVersionCode(ConstantParameter.getRuleEngineVersionCode());
        inparam.setRuleInfo(ruleinfo);
        //将设置好的入参对象inparam，set到请求对象req中
        req.setTargetObject(inparam);
        //发送请求并接收返回信息
        Stuff resp = sc.send(req);
        //固定写法，解析返回的结果对象
        Result result = resp.getResult();
        if(result.isSuccess()){
            HandleVerifyOutParam outparams = resp.getTargetObject(HandleVerifyOutParam.class);
            if("Y".equals(outparams.getVrResult())){
            	falg = true ;
            }
        }else{
        	throw new BusinessException("请求规则引擎数据异常");
        }
        return falg ;
	}
}
