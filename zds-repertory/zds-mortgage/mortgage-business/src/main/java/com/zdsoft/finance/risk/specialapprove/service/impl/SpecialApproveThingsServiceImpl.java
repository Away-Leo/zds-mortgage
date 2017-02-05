package com.zdsoft.finance.risk.specialapprove.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.risk.entity.SpecialApproveThings;
import com.zdsoft.finance.risk.specialapprove.service.SpecialApproveThingsService;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: SpecialApproveThingsServiceImpl.java
 * @Package com.zdsoft.finance.risk.specialapprove.service.impl
 * @Description: 风险特批service实现
 * @author Liyb
 * @date 2017年1月15日 下午4:21:09
 * @version V1.0
 */
@Service("specialApproveThingsService")
public class SpecialApproveThingsServiceImpl implements SpecialApproveThingsService {

    @Override
    public List<SpecialApproveThings> querySpecialApproveThingsByCaseApplyId(String caseApplyId) {
        // 模拟数据
        List<SpecialApproveThings> specialApproveThingsList = new ArrayList<>();
        SpecialApproveThings specialApproveThings = new SpecialApproveThings();
        specialApproveThings.setItemCode("R0010001");
        specialApproveThings.setItemName("第一主借人年龄不能小于18岁。");
        specialApproveThings.setItemType("年龄不符");
        specialApproveThingsList.add(specialApproveThings);
        return specialApproveThingsList;
    }

}
