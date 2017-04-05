

package com.zdsoft.finance.contract.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.contract.entity.BussPrintTplPage;
import com.zdsoft.finance.contract.repository.BussPrintTplPageRepository;
import com.zdsoft.finance.contract.service.BussPrintTplPageService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;


/**
 * <版权所有：重庆正大华日软件有限公司
 
 * Title: 泛华信贷系统
 *
 * Description: BUSS_PRINT_TPL_PAGE ServiceImpl
 *
 * @author wolfhuang
 * @version 1.0 
 **/
 
 
@Service("BussPrintTplPageService")
public class BussPrintTplPageServiceImpl
extends BaseServiceImpl<BussPrintTplPage, BussPrintTplPageRepository> implements BussPrintTplPageService  {

    @Autowired
    BussPrintTplPageRepository bussPrintTplPageRepository;
    
    @Override
    public List<BussPrintTplPage> findByTempId(String printTemplateId) {
        // TODO Auto-generated method stub

        BussPrintTplPage temp=new BussPrintTplPage();
        temp.setPrinttemplateid(printTemplateId);
        Example<BussPrintTplPage> conExample=Example.of(temp);
        
        return bussPrintTplPageRepository.findAll(conExample);
    }
    

    @Override
    public Page<BussPrintTplPage> findByPageByCondition(PageRequest pageable, String printTemplateId) {
        // TODO Auto-generated method stub
        Map<String, Object> condition=new HashMap<String, Object>();
        condition.put("printtemplateid",printTemplateId);
        return  bussPrintTplPageRepository.findByHqlPage(pageable, " from BussPrintTplPage where printtemplateid=:printtemplateid and isDeleted='F'", condition);
    }

}





 

