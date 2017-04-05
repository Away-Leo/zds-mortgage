package com.zdsoft.finance.contract.repository;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.contract.entity.ConCaseContractSealDetail;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

import java.util.List;
import java.util.Map;

/**
 *
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConCaseContractSealDetailRepository.java
 * @ClassName: ConCaseContractSealDetailRepository
 * @Description: 合同盖章明细
 * @author denglw
 * @date 2017年3月16日 下午4:00:50
 * @version V1.0
 */
public interface ConCaseContractSealDetailRepository extends CustomRepository<ConCaseContractSealDetail,String> {
    /**
     *
     * @Title: findByCaseContractSealId
     * @Description: 查询盖章明细
     * @author denglw
     * @param contractSealId 合同盖章主表ID
     * @return
     */
    public List<ConCaseContractSealDetail> findByCaseContractSealIdOrderByMaterialCode(String contractSealId);

    /**
     *
     * @Title: deleteByCaseContractSealId
     * @Description: 根据盖章ID删除明细
     * @author denglw
     * @param contractSealId 盖章主表ID
     * @return
     */
    public int deleteByCaseContractSealId(String contractSealId);
}
