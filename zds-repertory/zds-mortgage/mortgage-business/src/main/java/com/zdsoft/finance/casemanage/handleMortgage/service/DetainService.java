package com.zdsoft.finance.casemanage.handleMortgage.service;

import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.handleMortgage.entity.Detain;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.HouseProperty;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: DetainService.java 
 * @ClassName: DetainService 
 * @Description: 查册入押Service
 * @author zhoushichao 
 * @date 2017年2月16日 下午6:34:16 
 * @version V1.0 
 */ 
public interface DetainService extends BaseService<Detain>{

    /**
     * 
     * @Title: saveDetainAllInfo 
     * @Description: 查册入押页面所有信息保存
     * @author zhoushichao 
     * @param detain 查册入押
     * @param houseProperty 房产信息
     * @throws Exception
     */
    public Map<String,Object> saveDetainAllInfo(Detain detain,HouseProperty houseProperty) throws Exception;
    
    /**
     * 
     * @Title: findByHousePropertyId 
     * @Description: 根据房产ID查找查册入押信息
     * @author zhoushichao 
     * @param housePropertyId 房产ID
     * @return
     * @throws BusinessException
     */
    public Detain findByHousePropertyId(String housePropertyId) throws BusinessException;
    
    /**
     * 
     * @Title: findByAppInChargeHousePropertyId 
     * @Description: 根据房产ID查找app房产查册入押信息
     * @author zhoushichao 
     * @param housePropertyId 房产ID
     * @return
     * @throws Exception
     */
    public Map<String,Object> findByAppInChargeHousePropertyId(String housePropertyId) throws Exception;

}
