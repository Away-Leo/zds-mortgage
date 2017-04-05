package com.zdsoft.finance.credit.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.credit.entity.MyCreditCard;
import com.zdsoft.framework.core.common.exception.BusinessException;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MyCreditCardService.java 
 * @ClassName: MyCreditCardService 
 * @Description: 本人信用卡信息 Service 
 * @author dengyy 
 * @date 2017年2月23日 上午9:32:31 
 * @version V1.0 
 */
public interface MyCreditCardService extends BaseService<MyCreditCard> {

    /**
     * 
     * @Title: findByCreditSituationId 
     * @Description: 根据综合授信id获取信用卡信息 
     * @author dengyy 
     * @param creditSituationId 综合授信id
     * @return
     * @throws BusinessException
     */
    public List<MyCreditCard> findByCreditSituationId(String creditSituationId) throws BusinessException ;
    
    
}
