package com.zdsoft.finance.product.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.MateriaListAuth;
import com.zdsoft.finance.product.entity.MaterialList;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 资料清单VO
 * @author LiaoGuoWei
 * @create 2016-12-26 16:01
 **/
public class MaterialListVo extends BaseVo<MaterialList> {

    /**
     * 资料类型编号-客户资料，担保人资料，押品资料 等的代码
     */
    private String materiaTypeCode;

    /**
     * 资料类型名称
     */
    private String materiaTypeName;

    /**
     * 所属产品编号
     */
    private String productCode;
    /**
     * 所属产品名称
     */
    private String productName;

    /**
     * 显示顺序
     */
    private Integer showOrder;

    /**
     * 资料编号
     */
    private String materiaCode;
    /**
     * 资料名称
     */
    private String materiaName;

    /**
     * 助记码
     */
    private String rememberCode;

    /**
     * 数字助记码
     */
    private Long rememberNo;

    /**
     * 资料证明
     */
    private String materiaIdentify;
    /**
     * 资料证明名称
     */
    private String materiaIdentifyName;

    /**
     * 拥有的流程权限节点
     */
    private List<MateriaListAuthVo> materiaListAuth;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
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

    public String getRememberCode() {
        return rememberCode;
    }

    public void setRememberCode(String rememberCode) {
        this.rememberCode = rememberCode;
    }

    public Long getRememberNo() {
        return rememberNo;
    }

    public void setRememberNo(Long rememberNo) {
        this.rememberNo = rememberNo;
    }

    public String getMateriaIdentify() {
        return materiaIdentify;
    }

    public void setMateriaIdentify(String materiaIdentify) {
        this.materiaIdentify = materiaIdentify;
    }

    public String getMateriaIdentifyName() {
        return materiaIdentifyName;
    }

    public void setMateriaIdentifyName(String materiaIdentifyName) {
        this.materiaIdentifyName = materiaIdentifyName;
    }

    public List<MateriaListAuthVo> getMateriaListAuth() {
        return materiaListAuth;
    }

    public void setMateriaListAuth(List<MateriaListAuthVo> materiaListAuth) {
        this.materiaListAuth = materiaListAuth;
    }

    public MaterialListVo(){}
    public MaterialListVo(MaterialList materialList){
        super(materialList,new String[]{"materiaListAuth"});
        if(ObjectHelper.isNotEmpty(materialList.getMateriaListAuth())&&materialList.getMateriaListAuth().size()>0){
            List<MateriaListAuthVo> listAuthVoList=new ArrayList<MateriaListAuthVo>();
            for(MateriaListAuth temp:materialList.getMateriaListAuth()){
                MateriaListAuthVo authVo=new MateriaListAuthVo(temp);
                listAuthVoList.add(authVo);
            }
            this.setMateriaListAuth(listAuthVoList);
        }
    }

    public MaterialList toPo(){
        MaterialList materialList=new MaterialList();
        BeanUtils.copyProperties(this,materialList);
        return materialList;
    }
}
