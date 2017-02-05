package com.zdsoft.finance.casemanage.material.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseMaterialListAttaLog.java
 * @Package:com.zdsoft.finance.casemanage.atta.entity
 * @Description::案件资料清单附件日志
 * @author: gonght
 * @date:2017年1月15日 下午12:29:50
 * @version:v1.0
 */
@Entity
@Table(name = "case_material_list_atta_log")
public class CaseMaterialListAttaLog extends BaseEntity {

    private static final long serialVersionUID = -1629220876075995446L;

    /**
     * 关联案件资料清单附件
     */
    @ManyToOne
    @JoinColumn(name = "caseMaterialListAttaId")
    private CaseMaterialListAtta caseMaterialListAtta;

    /**
     * 日志类型(目前暂用于下载)
     */
    @Column(length = 32)
    private String logType;

    /**
     * 操作人Code
     */
    @Column(length = 32)
    private String operatorCd;

    /**
     * 操作人名称
     */
    @Column(length = 128)
    private String operatorNm;

    /**
     * 操作时间
     */
    @Column
    private Long operatorTime;

    public CaseMaterialListAtta getCaseMaterialListAtta() {
        return caseMaterialListAtta;
    }

    public void setCaseMaterialListAtta(CaseMaterialListAtta caseMaterialListAtta) {
        this.caseMaterialListAtta = caseMaterialListAtta;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getOperatorCd() {
        return operatorCd;
    }

    public void setOperatorCd(String operatorCd) {
        this.operatorCd = operatorCd;
    }

    public String getOperatorNm() {
        return operatorNm;
    }

    public void setOperatorNm(String operatorNm) {
        this.operatorNm = operatorNm;
    }

    public Long getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Long operatorTime) {
        this.operatorTime = operatorTime;
    }
}
