package com.zdsoft.finance.product.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.MateriaListAuth;

/**
 * 资料清单节点权限VO
 * @author LiaoGuoWei
 * @create 2016-12-26 16:23
 **/
public class MateriaListAuthVo extends BaseVo<MateriaListAuth> {

    /**
     * 所属产品
     */
    private String productCode;

    /**
     * 流程编号
     */
    private String processCode;

    /**
     * 流程名称
     */
    private String processName;

    /**
     * 节点编号
     */
    private String processNodeCode;

    /**
     * 节点名称
     */
    private String processNodeName;


    public String getProductCd() {
        return productCode;
    }

    public void setProductCd(String productCode) {
        this.productCode = productCode;
    }

    public String getProcessCd() {
        return processCode;
    }

    public void setProcessCd(String processCode) {
        this.processCode = processCode;
    }

    public String getProcessNm() {
        return processName;
    }

    public void setProcessNm(String processName) {
        this.processName = processName;
    }

    public String getProcessNodeCd() {
        return processNodeCode;
    }

    public void setProcessNodeCd(String processNodeCode) {
        this.processNodeCode = processNodeCode;
    }

    public String getProcessNodeNm() {
        return processNodeName;
    }

    public void setProcessNodeNm(String processNodeName) {
        this.processNodeName = processNodeName;
    }


    public MateriaListAuthVo(){}
    public MateriaListAuthVo(MateriaListAuth materiaListAuth){
        super(materiaListAuth);
    }
}
