package com.zdsoft.finance.contract.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.contract.entity.ConCaseContract;
import com.zdsoft.finance.contract.entity.ConCaseContractSeal;
import com.zdsoft.finance.contract.entity.ConCaseContractSealDetail;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

import java.util.List;
import java.util.Map;

/**
 *
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConCaseContractSealService.java
 * @ClassName: ConCaseContractSealService
 * @Description: 合同盖章
 * @author denglw
 * @date 2017年3月25日 下午5:37:35
 * @version V1.0
 */
public interface ConCaseContractSealService extends BaseService<ConCaseContractSeal> {
    /**
     *
     * @Title: getContractList
     * @Description: 合同盖章列表
     * @author denglw
     * @param pageable
     * @param queryObjs
     * @return
     * @throws Exception
     */
    public Page<Map<String, Object>> getContractList(PageRequest pageable, List<QueryObj> queryObjs) throws Exception;

    /**
     * @Title: saveOrUpdateEntity
     * @Description: 保存或更新实体
     * @author denglw
     * @param contractSeal
     * @param sealDetails
     * @param fileUuid
     * @return
     * @throws Exception
     */
    public ConCaseContractSeal saveOrUpdateEntity(ConCaseContractSeal contractSeal, List<ConCaseContractSealDetail> sealDetails, String fileUuid) throws Exception;

    /**
     * @Title: saveOrUpdateContractSeal
     * @Description: 保存盖章
     * @author denglw
     * @param contractSeal 申请盖章信息
     * @param sealDetails  盖章明细
     * @param submitStatus 提交状态
     * @throws Exception
     *
     */
    public ConCaseContractSeal saveOrUpdateBill(ConCaseContractSeal contractSeal, List<ConCaseContractSealDetail> sealDetails, boolean submitStatus, String fileUuid) throws Exception;

    /**
     * @Title: saveTrackingNoSend
     * @Description: 保存驻点寄出快递单号
     * @author denglw
     * @param id
     * @param trackingNoSend
     * @return
     * @throws Exception
     */
    public ConCaseContractSeal saveTrackingNoSend(String id, String trackingNoSend) throws Exception;

    /**
     * @Title: saveTrackingNoReceive
     * @Description: 保存退回信托快递单号
     * @author denglw
     * @param id
     * @param trackingNoReceive
     * @return
     * @throws Exception
     */
    public ConCaseContractSeal saveTrackingNoReceive(String id, String trackingNoReceive) throws Exception;

    /**
     *
     * @Title: importContractFile
     * @Description: 导入合同生成压缩包后上传
     * @author denglw
     * @param contractDetailIds 合同明细
     * @param businessId 业务ID
     * @param caseApplyId 案件ID
     * @param linkCode
     * @param productId 产品
     * @throws Exception
     */
    public  void importContractFile(String contractDetailIds, String businessId, String caseApplyId, String linkCode, String productId) throws Exception;
}
