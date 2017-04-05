
package com.zdsoft.finance.contract.service.impl;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.deser.ValueInstantiators.Base;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.businesssetting.entity.SysLabel;
import com.zdsoft.finance.businesssetting.service.SysLabelService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.contract.entity.BussPrintTplPageField;
import com.zdsoft.finance.contract.repository.BussPrintTplPageFieldRepository;
import com.zdsoft.finance.contract.service.BussPrintTplPageFieldService;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:BussPrintTplPageFieldServiceImpl.java
 * @Package:com.zdsoft.finance.contract.service.impl
 * @Description:用一句话描述该文件做什么
 * @author: huangdongkui
 * @date:Mar 1, 2017 6:11:15 PM
 * @version:v1.0
 */
@Service("BussPrintTplPageFieldService")
public class BussPrintTplPageFieldServiceImpl extends BaseServiceImpl<BussPrintTplPageField, BussPrintTplPageFieldRepository>
        implements BussPrintTplPageFieldService {

    @Autowired
    BussPrintTplPageFieldRepository bussPrintTplPageFieldRepository;

    @Autowired
    private SysLabelService sysLabelService;

    /**
     * 保存书签先删除后添加
     */
    @Transactional
    @Override
    public void saveListWithDelete(List<BussPrintTplPageField> listb) {
        // TODO 保存书签先删除后添加
        try {
            if (listb.size() > 0) {
                // TODO 删除本页的所有书签
                bussPrintTplPageFieldRepository.deleteBypageid(listb.get(0).getPageid());
                // TODO 添加书签
                bussPrintTplPageFieldRepository.batchSave(listb);
            }
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }

    }

    @Override
    /**
     * Get label by pageid
     */
    public List<BussPrintTplPageField> findBypageid(String pageid, String actionname) {
        // TODO Auto-generated method stub
        List<BussPrintTplPageField> listBussPrintTplPageField = bussPrintTplPageFieldRepository.findBypageid(pageid);
        if (actionname.equals("view")) {

            try {
                for (BussPrintTplPageField iterable_element : listBussPrintTplPageField) {

                    SysLabel findOne = sysLabelService.findOne(iterable_element.getLabelid());
                    List<Map<String, Object>> lmLabel = bussPrintTplPageFieldRepository.findListMapByCondition(findOne.getValueSQL(), null);

                    iterable_element.setLabelname(lmLabel.get(0).get("LABEL").toString());
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return listBussPrintTplPageField;
    }

}
