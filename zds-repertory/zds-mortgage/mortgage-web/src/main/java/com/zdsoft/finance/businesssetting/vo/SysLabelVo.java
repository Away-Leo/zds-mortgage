package com.zdsoft.finance.businesssetting.vo;

import com.zdsoft.finance.businesssetting.entity.SysLabel;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.VoUtil;
import org.springframework.beans.BeanUtils;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title SysLabelVo.java
 * @className SysLabelVo
 * @description 标签设置VO
 * @author LiaoGuoWei
 * @create 2017/2/27 20:34
 * @version V1.0
 **/
public class SysLabelVo extends BaseVo<SysLabel>{
    private static final long serialVersionUID = -2999277907836979829L;
    /**
     * 标签所属分类
     */
    private String labelType;

    /**
     * 标签所属分类名称
     */
    private String labelTypeName;

    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 取值方式
     */
    private String valueMethod;
    /**
     * 取值方式名称
     */
    private String valueMethodName;

    /**
     * 最大显示字数
     */
    private Integer maxDisplayNum;

    /**
     * 状态
     */
    private String labelStatus;

    /**
     * 取值sql内容
     */
    private String valueSQL;
    /**
     * 应用场景
     */
    private String sceneType;

    public String getSceneType() {
        return sceneType;
    }

    public void setSceneType(String sceneType) {
        this.sceneType = sceneType;
    }

    public String getLabelTypeName() {
        return labelTypeName;
    }

    public void setLabelTypeName(String labelTypeName) {
        this.labelTypeName = labelTypeName;
    }

    public String getValueMethodName() {
        return valueMethodName;
    }

    public void setValueMethodName(String valueMethodName) {
        this.valueMethodName = valueMethodName;
    }

    public String getLabelType() {
        return labelType;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getValueMethod() {
        return valueMethod;
    }

    public void setValueMethod(String valueMethod) {
        this.valueMethod = valueMethod;
    }

    public Integer getMaxDisplayNum() {
        return maxDisplayNum;
    }

    public void setMaxDisplayNum(Integer maxDisplayNum) {
        this.maxDisplayNum = maxDisplayNum;
    }

    public String getLabelStatus() {
        return labelStatus;
    }

    public void setLabelStatus(String labelStatus) {
        this.labelStatus = labelStatus;
    }

    public String getValueSQL() {
        return valueSQL;
    }

    public void setValueSQL(String valueSQL) {
        this.valueSQL = valueSQL;
    }

    public SysLabelVo(){}
    public SysLabelVo(SysLabel sysLabel){
        VoUtil.copyPoperties(sysLabel,this,false,null,new String[]{"labelType","valueMethod"});
    }

    public SysLabel toPo(){
        SysLabel sysLabel=new SysLabel();
        BeanUtils.copyProperties(this,sysLabel);
        return sysLabel;
    }
}
