
package com.zdsoft.finance.contract.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.contract.entity.BussPrintTplProduct;
import com.zdsoft.finance.contract.repository.BussPrintTplProductRepository;
import com.zdsoft.finance.contract.service.BussPrintTplProductService;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:BussPrintTplProductServiceImpl.java
 * @Package:com.zdsoft.finance.contract.service.impl
 * @Description:用一句话描述该文件做什么
 * @author: huangdongkui
 * @date:Mar 6, 2017 10:58:43 AM
 * @version:v1.0
 */
@Service("BussPrintTplProductService")
public class BussPrintTplProductServiceImpl extends BaseServiceImpl<BussPrintTplProduct, BussPrintTplProductRepository>
        implements BussPrintTplProductService {

    @Autowired
    BussPrintTplProductRepository bussPrintTplProductRepository;

    @Override
    public void logicDeleteBy(String printTemplateId) {
        // TODO Auto-generated method stub
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("printTemplateId", printTemplateId);
        List<BussPrintTplProduct> lists = bussPrintTplProductRepository
                .findByHql(" from  BussPrintTplProduct where printTemplateId=:printTemplateId", condition);
        if (lists != null) {
            bussPrintTplProductRepository.deleteInBatch(lists);
        }
    }

    @Override
    public List<BussPrintTplProduct> findByPrintTemplateId(String printTemplateId) {
        // TODO Auto-generated method stub
        List<BussPrintTplProduct> results = null;
        try {

            Map<String, Object> condition = new HashMap<String, Object>();
            condition.put("printTemplateId", printTemplateId);
            results = bussPrintTplProductRepository
                    .findByHql(" from  BussPrintTplProduct where printTemplateId=:printTemplateId", condition);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return results;
    }

}
