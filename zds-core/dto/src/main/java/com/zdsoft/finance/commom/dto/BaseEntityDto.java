package com.zdsoft.finance.commom.dto;

import java.util.Date;

/**
 * 基本基类（BaseEntity）DTO
 * @author LiaoGuoWei
 * @create 2016-11-14 15:32
 **/
public class BaseEntityDto {

//  DTO中不传输id   只传输如code之类的标识性 By maple
//    /**
//     * ID
//     */
//    private String id;
    /**
     * 版本
     */
    private Integer version;
    /**
     * 是否删除
     */
    private Boolean isDeleted = Boolean.FALSE;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除时间
     */
    private Date deleteTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建人部门
     */
    private String createOrgCd;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新人所属部门
     */
    private String updateOrgCd;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateOrgCd() {
        return createOrgCd;
    }

    public void setCreateOrgCd(String createOrgCd) {
        this.createOrgCd = createOrgCd;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateOrgCd() {
        return updateOrgCd;
    }

    public void setUpdateOrgCd(String updateOrgCd) {
        this.updateOrgCd = updateOrgCd;
    }
}
