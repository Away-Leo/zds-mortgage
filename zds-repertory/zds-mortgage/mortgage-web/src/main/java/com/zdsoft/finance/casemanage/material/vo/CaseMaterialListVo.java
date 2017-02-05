package com.zdsoft.finance.casemanage.material.vo;

import com.zdsoft.finance.casemanage.material.entity.CaseMaterialList;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.VoUtil;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseMaterialListVo.java
 * @Package:com.zdsoft.finance.casemanage.material.vo
 * @Description:案件资料清单vo
 * @author: gonght
 * @date:2017年1月15日 下午4:56:08
 * @version:v1.0
 */
public class CaseMaterialListVo extends BaseVo<CaseMaterialList> {

    private static final long serialVersionUID = 9168626454762668357L;

    /**
     * 关联案件
     */
    private String caseApplyId;

    /**
     * 资料所属分类Code
     */
    // TODO:

    /**
     * 资料所属分类名称
     */
    // TODO:

    /**
     * 资料类别code
     */
    private String materiaTypeCode;

    /**
     * 资料类型名称
     */
    private String materiaTypeName;

    /**
     * 资料名称
     */
    private String materiaName;

    /**
     * 对应产品资料清单id(与prct_materialList弱关联),暂时不用
     */
    private String materialListId;
    
    public String getCaseApplyId() {
        return caseApplyId;
    }

    public void setCaseApplyId(String caseApplyId) {
        this.caseApplyId = caseApplyId;
    }

    public String getMateriaTypeCode() {
        return materiaTypeCode;
    }

    public void setMateriaTypeCode(String materiaTypeCode) {
        this.materiaTypeCode = materiaTypeCode;
    }

    public String getMateriaTypeName() {
        return materiaTypeName;
    }

    public void setMateriaTypeName(String materiaTypeName) {
        this.materiaTypeName = materiaTypeName;
    }

    public String getMateriaName() {
        return materiaName;
    }

    public void setMateriaName(String materiaName) {
        this.materiaName = materiaName;
    }

    public String getMaterialListId() {
        return materialListId;
    }

    public void setMaterialListId(String materialListId) {
        this.materialListId = materialListId;
    }

    public CaseMaterialListVo() {

    }

    public CaseMaterialListVo(CaseMaterialList caseMaterialList, String[] args, String[] simpleArgs) throws Exception {
        VoUtil.copyPoperties(caseMaterialList, this, false, args, simpleArgs);
    }

    public CaseMaterialList toPo() throws Exception {
        CaseMaterialList t = new CaseMaterialList();
        VoUtil.copyPoperties(this, t, true);
        return t;
    }
}
