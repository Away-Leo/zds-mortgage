package com.zdsoft.finance.casemanage.material.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseMaterialList.java
 * @Package:com.zdsoft.finance.casemanage.atta.entity
 * @Description:案件资料清单
 * @author: gonght
 * @date:2017年1月15日 下午12:12:03
 * @version:v1.0
 */
@Entity
@Table(name = "case_material_list")
public class CaseMaterialList extends BaseEntity {

    private static final long serialVersionUID = 2514618278231777633L;

    /**
     * 关联案件
     */
    @ManyToOne
    @JoinColumn(name = "caseApplyId")
    private CaseApply caseApply;

    /**
     * 案件id，非持久化字段
     */
    @Transient
    private String caseApplyId;

    /**
     * 资料所属分类Code
     */
    @Column(length = 32)
    private String materiaTypeCode;

    /**
     * 资料所属分类名称
     */
    @Column(length = 256)
    private String materiaTypeName;

    /**
     * 资料类别code
     */
    @Column(length = 32)
    private String materiaCode;

    /**
     * 资料类型名称
     */
    @Column(length = 256)
    private String materiaName;

    /**
     * 对应产品资料清单id(与prd_materialList弱关联),暂时不用
     */
    @Column(length = 32)
    private String materialListId;

    /**
     * 案件资料清单附件集合
     */
    @OneToMany(mappedBy = "caseMaterialList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CaseMaterialListAtta> caseMaterialListAttas = new ArrayList<CaseMaterialListAtta>();

    public CaseApply getCaseApply() {
        return caseApply;
    }

    public void setCaseApply(CaseApply caseApply) {
        this.caseApply = caseApply;
    }

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

    public String getMateriaCode() {
        return materiaCode;
    }

    public void setMateriaCode(String materiaCode) {
        this.materiaCode = materiaCode;
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

    public List<CaseMaterialListAtta> getCaseMaterialListAttas() {
        return caseMaterialListAttas;
    }

    public void setCaseMaterialListAttas(List<CaseMaterialListAtta> caseMaterialListAttas) {
        this.caseMaterialListAttas = caseMaterialListAttas;
    }

}