package com.zdsoft.finance.casemanage.vo;

import com.zdsoft.finance.casemanage.record.entity.CaseApplyRecord;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.VoUtil;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CaseApplyRecordVo.java
 * @Package com.zdsoft.finance.casemanage.vo
 * @Description: 案件信息录入VO
 * @author Liyb
 * @date 2017年1月16日 下午9:07:49
 * @version V1.0
 */
public class CaseApplyRecordVo extends BaseVo<CaseApplyRecord> {

    /**
     * 
     */
    private static final long serialVersionUID = 961022715114197745L;

    /**
     * 录入类型
     */
    private String recordType;

    /**
     * 录入类型名称
     */
    private String recordTypeName;

    /**
     * 录入状态
     */
    private String recordStatus;

    /**
     * 录入状态名称
     */
    private String recordStatusName;

    /**
     * 录入时间
     */
    private Long recordDate;

    /**
     * 操作人ID
     */
    private String operatorId;

    /**
     * 案件主借款人
     */
    private String mainBorrower;

    private CaseApplyVo caseApply;

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public Long getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Long recordDate) {
        this.recordDate = recordDate;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getRecordTypeName() {
        return recordTypeName;
    }

    public void setRecordTypeName(String recordTypeName) {
        this.recordTypeName = recordTypeName;
    }

    public String getRecordStatusName() {
        return recordStatusName;
    }

    public void setRecordStatusName(String recordStatusName) {
        this.recordStatusName = recordStatusName;
    }

    public String getMainBorrower() {
        return mainBorrower;
    }

    public void setMainBorrower(String mainBorrower) {
        this.mainBorrower = mainBorrower;
    }

    public CaseApplyVo getCaseApply() {
        return caseApply;
    }

    public void setCaseApply(CaseApplyVo caseApply) {
        this.caseApply = caseApply;
    }

    public CaseApplyRecordVo() {

    }

    public CaseApplyRecordVo(CaseApplyRecord po) throws Exception {
        VoUtil.copyPoperties(po, this, false);
    }

    public CaseApplyRecordVo(CaseApplyRecord po, String[] args, String[] simpleArgs) throws Exception {
        VoUtil.copyPoperties(po, this, false, args, simpleArgs);
        if (ObjectHelper.isNotEmpty(po.getCaseApply())) {
            this.setCaseApply(new CaseApplyVo(po.getCaseApply()));
        }
    }

    public CaseApplyRecord toPo() throws Exception {
        CaseApplyRecord t = new CaseApplyRecord();
        VoUtil.copyPoperties(this, t, true);
        return t;
    }
}
