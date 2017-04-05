package com.zdsoft.finance.common.enums;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title ENUM_USING_STATUS.java
 * @className ENUM_USING_STATUS
 * @description 启用状态枚举
 * @author LiaoGuoWei
 * @create 2017/2/15 16:02
 * @version V1.0
 **/
public enum ENUM_USING_STATUS {

    /**
     * 启用
     */
    USING("0"),
    /**
     * 停用
     */
    STOPED("1"),
    /**
     * 草稿
     */
    DRAFT("2"),;

    public final String value;

    private ENUM_USING_STATUS(String value){
        this.value=value;
    }
}
