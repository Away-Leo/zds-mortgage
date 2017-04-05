package com.zdsoft.finance.businesssetting.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.VoUtil;
import com.zdsoft.finance.businesssetting.entity.ExceptMatter;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.beans.BeanUtils;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title ExceptMatterVo.java
 * @className ExceptMatterVo
 * @description 特批事项VO
 * @author LiaoGuoWei
 * @create 2017/3/3 15:23
 * @version V1.0
 **/
public class ExceptMatterVo extends BaseVo<ExceptMatter> {

    /**
     * 特批事项编码
     */
    private String exceptMattercode;
    /**
     * 特批事项类型
     */
    private String exceptMatterType;
    /**
     * 特批事项类型名称
     */
    private String exceptMatterTypeName;
    /**
     * 特批事项名称
     */
    private String exceptMatterName;
    /**
     * 状态
     */
    private String status;
    /**
     * 备注
     */
    private String remark;

    /**
     * 操作人
     */
    private String handelrName;

    /**
     * 操作时间
     */
    private String handelTime;

    public String getExceptMatterTypeName() {
        return exceptMatterTypeName;
    }

    public void setExceptMatterTypeName(String exceptMatterTypeName) {
        this.exceptMatterTypeName = exceptMatterTypeName;
    }

    public String getHandelTime() {
        return handelTime;
    }

    public void setHandelTime(String handelTime) {
        this.handelTime = handelTime;
    }

    public String getHandelrName() {
        return handelrName;
    }

    public void setHandelrName(String handelrName) {
        this.handelrName = handelrName;
    }

    public String getExceptMattercode() {
        return exceptMattercode;
    }

    public void setExceptMattercode(String exceptMattercode) {
        this.exceptMattercode = exceptMattercode;
    }

    public String getExceptMatterType() {
        return exceptMatterType;
    }

    public void setExceptMatterType(String exceptMatterType) {
        this.exceptMatterType = exceptMatterType;
    }

    public String getExceptMatterName() {
        return exceptMatterName;
    }

    public void setExceptMatterName(String exceptMatterName) {
        this.exceptMatterName = exceptMatterName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ExceptMatterVo(){}
    public ExceptMatterVo(ExceptMatter exceptMatter){
        VoUtil.copyPoperties(exceptMatter,this,false,null,new String[]{"exceptMatterType"});
        if(ObjectHelper.isNotEmpty(exceptMatter.getUpdateTime())){
            this.setHandelTime(DateHelper.dateToString(exceptMatter.getUpdateTime(),DateHelper.DATE_WITHMINUTE_FORMAT));
        }
    }

    public ExceptMatter toPo(){
        ExceptMatter exceptMatter=new ExceptMatter();
        BeanUtils.copyProperties(this,exceptMatter);
        return exceptMatter;
    }

}
