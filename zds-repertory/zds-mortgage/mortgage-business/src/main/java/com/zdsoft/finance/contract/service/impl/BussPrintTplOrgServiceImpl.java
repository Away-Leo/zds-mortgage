
package com.zdsoft.finance.contract.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.contract.entity.BussPrintTplOrg;
import com.zdsoft.finance.contract.entity.BussPrintTplProduct;
import com.zdsoft.finance.contract.repository.BussPrintTplOrgRepository;
import com.zdsoft.finance.contract.service.BussPrintTplOrgService;

/**
 * <版权所有：重庆正大华日软件有限公司 Title: 泛华信贷系统 Description: BUSS_PRINT_TPL_ORG ServiceImpl
 *
 * @author wolfhuang
 * @version 1.0
 **/

@Service("BussPrintTplOrgService")
public class BussPrintTplOrgServiceImpl extends BaseServiceImpl<BussPrintTplOrg, BussPrintTplOrgRepository> implements BussPrintTplOrgService {

    @Autowired
    private BussPrintTplOrgRepository bussPrintTplOrgRepository;

    @Override
    public List<BussPrintTplOrg> findByPrintTemplateId(String printTemplateId) {
        return bussPrintTplOrgRepository.findByPrintTemplateId(printTemplateId);
    }

    @Override
    public void logicDeleteBy(String printTemplateId) {
        
        // TODO 批量删除
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("printTemplateId", printTemplateId);
        List<BussPrintTplOrg> lists = bussPrintTplOrgRepository
                .findByHql(" from  BussPrintTplOrg where printTemplateId=:printTemplateId", condition);
        if (lists != null) {
            bussPrintTplOrgRepository.deleteInBatch(lists);
        }
    }
}
