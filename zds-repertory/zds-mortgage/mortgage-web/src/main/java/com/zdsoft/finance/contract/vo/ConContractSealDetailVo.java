package com.zdsoft.finance.contract.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.contract.entity.ConCaseContractSealDetail;

/**
 *
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConContractSealDetailVo.java
 * @ClassName: ConContractSealDetailVo
 * @Description: 合同盖章明细VO
 * @author denglw
 * @date 2017年3月18日 上午11:45:24
 * @version V1.0
 */
public class ConContractSealDetailVo extends BaseVo<ConCaseContractSealDetail> {
    /**
     * 资料类型Code
     */
    private String materialCode;

    /**
     * 资料类型名称
     */
    private String materialName;

    /**
     * 原件（份数）
     */
    private Integer originalNum;

    /**
     * 复印件（份数）
     */
    private Integer copyNum;

    /**
     * 申请公章
     */
    private String applySeal;

    /**
     * 其它说明
     */
    private String otherExplain;

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Integer getOriginalNum() {
        return originalNum;
    }

    public void setOriginalNum(Integer originalNum) {
        this.originalNum = originalNum;
    }

    public Integer getCopyNum() {
        return copyNum;
    }

    public void setCopyNum(Integer copyNum) {
        this.copyNum = copyNum;
    }

    public String getApplySeal() {
        return applySeal;
    }

    public void setApplySeal(String applySeal) {
        this.applySeal = applySeal;
    }

    public String getOtherExplain() {
        return otherExplain;
    }

    public void setOtherExplain(String otherExplain) {
        this.otherExplain = otherExplain;
    }
}
