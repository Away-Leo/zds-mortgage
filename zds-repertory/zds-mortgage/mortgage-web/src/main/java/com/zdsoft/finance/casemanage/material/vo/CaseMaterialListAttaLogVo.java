package com.zdsoft.finance.casemanage.material.vo;

import com.zdsoft.finance.casemanage.material.entity.CaseMaterialListAttaLog;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.VoUtil;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseMaterialListAttaLogVo.java
 * @Package:com.zdsoft.finance.casemanage.material.vo
 * @Description:案件资料清单附件日志Vo
 * @author: gonght
 * @date:2017年1月15日 下午4:58:22
 * @version:v1.0
 */
public class CaseMaterialListAttaLogVo extends BaseVo<CaseMaterialListAttaLog> {

    private static final long serialVersionUID = 4795098572464470121L;

    /**
     * 关联案件资料清单附件
     */
    private String caseMaterialListAttaId;

    /**
     * 日志类型(目前暂用于下载)
     */
    private String logType;

    /**
     * 操作人Code
     */
    private String operatorCd;

    /**
     * 操作人名称
     */
    private String operatorNm;

    /**
     * 操作时间
     */
    private Long operatorTime;

    public String getCaseMaterialListAttaId() {
        return caseMaterialListAttaId;
    }

    public void setCaseMaterialListAttaId(String caseMaterialListAttaId) {
        this.caseMaterialListAttaId = caseMaterialListAttaId;
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

    public CaseMaterialListAttaLogVo() {

    }

    public CaseMaterialListAttaLogVo(CaseMaterialListAttaLog log, String[] args, String[] simpleArgs) throws Exception {
        VoUtil.copyPoperties(log, this, false, args, simpleArgs);
    }

    public CaseMaterialListAttaLog toPo() throws Exception {
        CaseMaterialListAttaLog t = new CaseMaterialListAttaLog();
        VoUtil.copyPoperties(this, t, true);
        return t;
    }
}