package com.zdsoft.finance.businesssetting.vo;

import com.zdsoft.finance.businesssetting.entity.CostItem;
import com.zdsoft.finance.common.base.BaseVo;
import org.springframework.beans.BeanUtils;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @title CostItemVo.java
 * @className CostItemVo
 * @description 费用项VO
 * @author LiaoGuoWei
 * @create 2017/3/15 20:25
 * @version V1.0
 **/
public class CostItemVo extends BaseVo<CostItem> {

    private static final long serialVersionUID = -6633388025048400162L;
    /**
     * 费用项编码
     */
    private String costItemCode;
    /**
     * 费用项名称
     */
    private String costItemName;
    /**
     * 备注
     */
    private String remark;

    /**
     * 操作人名称
     */
    private String empName;

    public String getCostItemCode() {
        return costItemCode;
    }

    public void setCostItemCode(String costItemCode) {
        this.costItemCode = costItemCode;
    }

    public String getCostItemName() {
        return costItemName;
    }

    public void setCostItemName(String costItemName) {
        this.costItemName = costItemName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public CostItemVo(){}

    /**
     * @Title: CostItemVo
     * @Description: 带有域对象的构造方法
     * @author liaoguowei
     * @param costItem
     * @return
     * @throws
     */
    public CostItemVo(CostItem costItem){
        BeanUtils.copyProperties(costItem,this);
    }

    /**
     * @Title: toPo
     * @Description: VO转域对象
     * @author liaoguowei
     * @param
     * @return com.zdsoft.finance.businesssetting.entity.CostItem
     * @throws
     */
    public CostItem toPo(){
        CostItem costItem=new CostItem();
        BeanUtils.copyProperties(this,costItem);
        return costItem;
    }

}
