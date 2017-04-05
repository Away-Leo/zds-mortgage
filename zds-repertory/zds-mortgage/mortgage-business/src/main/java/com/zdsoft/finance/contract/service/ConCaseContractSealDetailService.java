package com.zdsoft.finance.contract.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.contract.entity.ConCaseContractSealDetail;

import java.util.List;

/**
 *
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConCaseContractSealDetailService.java
 * @ClassName: ConCaseContractSealDetailService
 * @Description: 合同盖章明细
 * @author denglw
 * @date 2017年3月16日 下午4:01:30
 * @version V1.0
 */
public interface ConCaseContractSealDetailService  extends BaseService<ConCaseContractSealDetail> {
    /**
     *
     * @Title: findByCaseContractSealId
     * @Description: 合同盖章明细查询
     * @author denglw
     * @param contractSealId 合同盖章ID
     * @return
     */
    public List<ConCaseContractSealDetail> findByCaseContractSealId(String contractSealId);
}
