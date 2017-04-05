package com.zdsoft.finance.businesssetting.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.VoUtil;
import com.zdsoft.finance.businesssetting.entity.MortOwner;
import com.zdsoft.framework.core.common.util.DateHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title MortOwnerVo.java
 * @className MortOwnerVo
 * @description 抵押权人VO
 * @author LiaoGuoWei
 * @create 2017/3/3 15:23
 * @version V1.0
 **/
public class MortOwnerVo extends BaseVo<MortOwner> {
    /**
     * 所属机构编号
     */
    private String beOrgCode;

    /**
     * 所属机构名称
     */
    private String beOrgName;

    /**
     * 类别编号
     */
    private String ownerTypeCode;

    /**
     * 类别名称
     */
    private String ownerTypeName;

    private String ownerTypeCodeName;


    /**
     * 名称
     */
    private String ownerName;

    /**
     * 状态
     */
    private String status;

    /**
     * 添加时间字符串
     */
    private String createTimeStr;

    public String getOwnerTypeCodeName() {
        return ownerTypeCodeName;
    }

    public void setOwnerTypeCodeName(String ownerTypeCodeName) {
        this.ownerTypeCodeName = ownerTypeCodeName;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getBeOrgName() {
        return beOrgName;
    }

    public void setBeOrgName(String beOrgName) {
        this.beOrgName = beOrgName;
    }

    public String getOwnerTypeName() {
        return ownerTypeName;
    }

    public void setOwnerTypeName(String ownerTypeName) {
        this.ownerTypeName = ownerTypeName;
    }

    public String getBeOrgCode() {
        return beOrgCode;
    }

    public void setBeOrgCode(String beOrgCode) {
        this.beOrgCode = beOrgCode;
    }

    public String getOwnerTypeCode() {
        return ownerTypeCode;
    }

    public void setOwnerTypeCode(String ownerTypeCode) {
        this.ownerTypeCode = ownerTypeCode;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MortOwnerVo(){}

    public MortOwnerVo(MortOwner mortOwner){
        VoUtil.copyPoperties(mortOwner,this,false,null,new String[]{"ownerTypeCode"});
        this.setCreateTimeStr(DateHelper.dateToString(mortOwner.getCreateTime(),DateHelper.DATE_WITHSECOND_FORMAT));
    }
}
