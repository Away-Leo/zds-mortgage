package com.zdsoft.finance.contract.service.impl;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.contract.entity.ConCaseContractSealDetail;
import com.zdsoft.finance.contract.repository.ConCaseContractSealDetailRepository;
import com.zdsoft.finance.contract.service.ConCaseContractSealDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConCaseContractSealDetailServiceImpl.java
 * @ClassName: ConCaseContractSealDetailServiceImpl
 * @Description: 案件合同盖章明细
 * @author denglw
 * @date 2017年3月16日 下午4:03:06
 * @version V1.0
 */
@Service("conCaseContractSealDetailService")
public class ConCaseContractSealDetailServiceImpl extends BaseServiceImpl<ConCaseContractSealDetail, ConCaseContractSealDetailRepository>
        implements ConCaseContractSealDetailService {
    @Override
    public List<ConCaseContractSealDetail> findByCaseContractSealId(String contractSealId) {
        return customReposity.findByCaseContractSealIdOrderByMaterialCode(contractSealId);
    }
}
