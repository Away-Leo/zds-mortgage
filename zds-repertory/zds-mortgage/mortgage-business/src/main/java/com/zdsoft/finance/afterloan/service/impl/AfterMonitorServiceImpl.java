package com.zdsoft.finance.afterloan.service.impl;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.afterloan.entity.AfterMonitor;
import com.zdsoft.finance.afterloan.repository.AfterMonitorRepository;
import com.zdsoft.finance.afterloan.service.AfterMonitorService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.AfterContact;
import com.zdsoft.finance.customer.repository.AfterContactRepository;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.risk.huifa.service.BusinessDetailService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title AfterMonitorServiceImpl.java
 * @className AfterMonitorServiceImpl
 * @description 贷后监控service
 * @author LiaoGuoWei
 * @create 2017/3/6 21:24
 * @version V1.0
 **/
@Service("afterMonitorService")
public class AfterMonitorServiceImpl implements AfterMonitorService {

    @Autowired
    private AfterMonitorRepository afterMonitorRepository;

    @Autowired
    private AfterContactRepository afterContactRepository;

    @Autowired
    private BusinessDetailService businessDetailService;

    @Autowired
    private CaseApplyService caseApplyService;

    @Autowired
    private CED CED;

    @Log
    private Logger logger;


    @Override
    public Page<AfterMonitor> findMonitorByCondition(PageRequest page, AfterMonitor after) throws Exception {
        if(ObjectHelper.isNotEmpty(page)&&ObjectHelper.isNotEmpty(after)){
            Page<AfterMonitor> sourceData=this.afterMonitorRepository.findMonitorByPage(page,after);
            if(ObjectHelper.isNotEmpty(sourceData)&&ObjectHelper.isNotEmpty(sourceData.getRecords())){
                for (AfterMonitor temp : sourceData.getRecords()) {
                    String customerId = temp.getCustomerId();
                    List<AfterContact> contacts = afterContactRepository.queryContactList(customerId);
                    if(ObjectHelper.isNotEmpty(contacts) && contacts.size()>0){
                        temp.setPhoneNumber(contacts.get(0).getPhoneNumber());
                    }
                }
            }
            return sourceData;
        }else{
            throw new BusinessException("10010004","未传入相关参数，条件查找贷后监控出错");
        }
    }

    @Override
    public List<AfterMonitor> findMonitorInitiActiveList(AfterMonitor afterMonitor) throws Exception {
        if(ObjectHelper.isNotEmpty(afterMonitor)){
            if(ObjectHelper.isNotEmpty(afterMonitor.getId())){
//                afterMonitor.setId(afterMonitor.getId().replace(",","','"));
                List<AfterMonitor> sourceData=this.afterMonitorRepository.findMonitorInitiActiveList(afterMonitor);
                if(ObjectHelper.isNotEmpty(sourceData)&&sourceData.size()>0){
                    for (AfterMonitor temp : sourceData) {
                        String customerId = temp.getCustomerId();
                        List<AfterContact> contacts = afterContactRepository.queryContactList(customerId);
                        if(ObjectHelper.isNotEmpty(contacts) && contacts.size()>0){
                            temp.setPhoneNumber(contacts.get(0).getPhoneNumber());
                        }
                    }
                }
                return sourceData;
            }else{
                throw new BusinessException("10010003","传入参数有误，贷后监控主动查询列表查询出错");
            }
        }else{
            throw new BusinessException("10010004","未传入相关参数，贷后监控主动查询列表查询出错");
        }
    }

    @Override
    public void doInitiActive(String ids,String impls) throws Exception {
        if(ObjectHelper.isNotEmpty(ids)){
            String[] idArray=ids.split(",");
            for(int i=0;i<idArray.length;i++){
                CaseApply caseApply=this.caseApplyService.findOne(idArray[i]);
                if(ObjectHelper.isNotEmpty(caseApply)){
                    //工商信息 即时接口
                    if(impls.contains("0")){
                        //汇法网 TODO 24小时接口
                    }
                    if(impls.contains("1")){
                        //房产评估 TODO 及时接口
                    }
                    if(impls.contains("2")){
                        try{
                            Boolean result=this.businessDetailService.callBusinessInterface(caseApply.getMechanismName(),caseApply.getId(),caseApply.getDevelopmentDepartmentName(),CED.getLoginUser().getEmpNm());
                        }catch (BusinessException e){
                            logger.error("贷后监控主动查询工商信息出错",e);
                            e.printStackTrace();
                        }
                    }
                }
            }
        }else{
            throw new BusinessException("10010004","未传入相关参数！贷后监控主动查询出错");
        }
    }
}
