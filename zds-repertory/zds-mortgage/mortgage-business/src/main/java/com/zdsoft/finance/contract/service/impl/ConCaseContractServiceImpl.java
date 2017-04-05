package com.zdsoft.finance.contract.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.contract.entity.ConCaseContractDetail;
import com.zdsoft.finance.contract.entity.ConFormatContractDetail;
import com.zdsoft.finance.contract.entity.ConFormatContractMaterial;
import com.zdsoft.finance.contract.repository.ConFormatContractDetailRepository;
import com.zdsoft.finance.contract.repository.ConFormatContractMaterialRepository;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.contract.entity.ConCaseContract;
import com.zdsoft.finance.contract.repository.ConCaseContractRepository;
import com.zdsoft.finance.contract.service.ConCaseContractService;

/**
 * 版权所有：重庆正大华日软件有限公司
 *
 * @author wangnengduo
 * @version V1.0
 * @Title: ConCaseContractServiceImpl.java
 * @ClassName: ConCaseContractServiceImpl
 * @Description: 案件合同
 * @date 2017年2月28日 上午11:03:46
 */

@Service("conCaseContractService")
public class ConCaseContractServiceImpl extends BaseServiceImpl<ConCaseContract, ConCaseContractRepository> implements ConCaseContractService {

    @Autowired
    private ConFormatContractMaterialRepository contractMaterialRepository;
    @Autowired
    private ConFormatContractDetailRepository conFormatContractDetailRepository;

    @Override
    public ConCaseContract findByCaseApplyId(String caseApplyId) throws Exception {
        return this.customReposity.findByCaseApplyId(caseApplyId);
    }

    /*
     * 根据案件ID获取案件基本信息(合同套打)
     */
    @Override
    public List<Map<String, Object>> getConCaseContractById(String caseApplyId) throws Exception {
        // TODO 自动生成的方法存根
        StringBuffer sql = new StringBuffer(" select cc.CONTRACTAMOUNT,cc.CONTRACTSTARTDATE,cc.CONTRACTENDDATE,cc.contractNo,c.caseApplyCode,cus.customerName,c.applyDate,c.mechanismCode,c.mechanismName,h.province, h.city,h.district, h.mailingAddress as Address ");

        sql.append(" from mkt_case_apply c ");
        sql.append(" LEFT JOIN mkt_collateral co on co.caseApplyId=c.id ");
        sql.append(" LEFT JOIN con_case_contract cc on co.caseApplyId=cc.caseApplyId ");
        sql.append(" LEFT JOIN mkt_house_property h on h.id=co.id ");
        sql.append(" LEFT JOIN case_before_customer cbc on cbc.caseApplyId=c.id ");
        sql.append(" LEFT JOIN cust_before_customer cus on cus.id=cbc.customerId ");
        sql.append(" WHERE 1=1 and c.id='" + caseApplyId + "'");
        //StringBuffer _extendSql = new StringBuffer(" order by c.id");
        String _sql = sql.toString();
        //_sql="select * from con_case_contract";
        return this.customReposity.findListMapByCondition(_sql, new HashMap<String, Object>());

    }

    /*
     * 
     * 根据案件ID获取合同生成中的所有相关合同
     */
    @Override
    public List<Map<String, Object>> getContractDetailByCaseContractId(String caseContractId) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append(" select ccc.CASEAPPLYID,cd.ID,cd.CASECONTRACTID,cd.contractname,cct.attachmentid,cd.contracttype from CON_CASE_CONTRACT_DETAIL cd ");
        sql.append(" LEFT JOIN con_case_contract ccc on ccc.id=cd.CASECONTRACTID ");
        sql.append(" LEFT JOIN mkt_case_apply c on ccc.CASEAPPLYID=c.id ");
        sql.append(" left join prd_product_contract ppc on ppc.productid=c.ProductSubtypeid ");
        sql.append(" left join con_contract_tpl cct on cct.id=ppc.contractid ");
        sql.append(" where ccc.CASEAPPLYID='" + caseContractId + "' and cd.isdeleted='F' ");
 
        return this.customReposity.findListMapByCondition(sql.toString(), new HashMap<String, Object>());
    }

    @Override
    public List<Map<String, Object>> getContractPrintTplByCaseApplyId(String caseApplyId) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append(" select c.id,b.id bid,b.TEMPLATENAME from BUSS_PRINT_TPL_SET b  ");
        sql.append(" left join MKT_CASE_APPLY c on (b.beloworgcode=c.mechanismCode  and b.fundsource=c.capitalSource)  ");
        sql.append(" left join buss_print_tpl_product bpt on (bpt.productcode=c.productsubtypeid and b.id=bpt.printtemplateid) ");
        sql.append(" where c.id='" + caseApplyId + "' and b.isdeleted='F' ");
        return this.customReposity.findListMapByCondition(sql.toString(), new HashMap<String, Object>());
    }

    /**
     * 获取案件实体状态情况统计
     *
     * @return
     * @throws Exception
     */
    @Override
    public Page<Map<String, Object>> getContractCancelReport(PageRequest pageable, List<QueryObj> queryObjs) throws Exception {
        return this.customReposity.getContractCancelReport(pageable, queryObjs);
    }

    /**
     * 查询需要修改的实体状态数据ID
     *
     * @param capitaIdC
     * @param contractTypeC
     * @param contractNameC
     * @param fileNo
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> getMaterialCountByCondition(String capitaIdC, String contractTypeC, String contractNameC, String fileNo) {
        try {
            return this.customReposity.getMaterialCountByCondition(capitaIdC, contractTypeC, contractNameC, fileNo);
        } catch (Exception e) {
            logger.error("合同作废查询修改实体失败", e);
            return null;
        }
    }

    @Override
    public void procContractCancel(List<Map<String, Object>> maps) {
        int modiNum = 0;
        String detailId = "";
        for (Map<String, Object> map : maps) {
            String materialId = map.get("MATERIALID").toString();
            ConFormatContractMaterial conMaterial = contractMaterialRepository.findOne(materialId);
            detailId = conMaterial.getFormatContractDetailId();
            if ("1".equals(conMaterial.getFileStatus())) {
                modiNum++;
            }
            conMaterial.setFileStatus("2");
            contractMaterialRepository.updateEntity(conMaterial);
        }
        //修改格式化合同明细清单使用和作废份数
        ConFormatContractDetail conDetail = conFormatContractDetailRepository.findOne(detailId);
        if (conDetail != null) {
            conDetail.setUseNum((conDetail.getUseNum() == null ? 0 : conDetail.getUseNum()) - modiNum);
            conDetail.setCancellationNum((conDetail.getCancellationNum() == null ? 0 : conDetail.getCancellationNum()) + maps.size());
            conFormatContractDetailRepository.updateEntity(conDetail);
        }
    }

    @Override
    public List<Map<String, Object>> getContractDetailByContractId(String contractId) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("select id,\n" +
                "       createby,\n" +
                "       createorgcd,\n" +
                "       createtime,\n" +
                "       deletetime,\n" +
                "       isdeleted,\n" +
                "       updateby,\n" +
                "       updateorgcd,\n" +
                "       updatetime,\n" +
                "       version,\n" +
                "       attachmentid,\n" +
                "       casecontractid,\n" +
                "       contractname,\n" +
                "       contracttype,\n" +
                "       formatcontractmaterialid\n" +
                "  from con_case_contract_detail\n" +
                "where casecontractid = '" + contractId + "'");
        return this.customReposity.findListMapByCondition(sql.toString(), new HashMap<String, Object>());
    }

    @Override
    public Map<String, Object> getConCaseContractDetailById(String detailId) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("select id,\n" +
                "       createby,\n" +
                "       createorgcd,\n" +
                "       createtime,\n" +
                "       deletetime,\n" +
                "       isdeleted,\n" +
                "       updateby,\n" +
                "       updateorgcd,\n" +
                "       updatetime,\n" +
                "       version,\n" +
                "       attachmentid,\n" +
                "       casecontractid,\n" +
                "       contractname,\n" +
                "       contracttype,\n" +
                "       formatcontractmaterialid\n" +
                "  from con_case_contract_detail\n" +
                "where id = '" + detailId + "'");
        List<Map<String, Object>> ls = this.customReposity.findListMapByCondition(sql.toString(), new HashMap<String, Object>());
        return ls.size() > 0 ? ls.get(0) : new HashedMap();
    }

}