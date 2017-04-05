package com.zdsoft.finance.credit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.credit.entity.MyCreditCard;
import com.zdsoft.finance.credit.repository.MyCreditCardRepository;
import com.zdsoft.finance.credit.service.MyCreditCardService;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MyCreditCardServiceImpl.java 
 * @ClassName: MyCreditCardServiceImpl 
 * @Description: 本人信用卡征信
 * @author dengyy 
 * @date 2017年2月23日 上午9:43:34 
 * @version V1.0 
 */
@Service("myCreditCardService")
public class MyCreditCardServiceImpl extends BaseServiceImpl<MyCreditCard, MyCreditCardRepository> implements MyCreditCardService {

    @Override
    public List<MyCreditCard> findByCreditSituationId(String creditSituationId) throws BusinessException {
        if(ObjectHelper.isEmpty(creditSituationId)){
            throw new BusinessException("10010004", "综合征信id不能为空！");
        }
        return this.customReposity.findByCreditSituationIdAndIsDeletedFalse(creditSituationId);
    }

}
