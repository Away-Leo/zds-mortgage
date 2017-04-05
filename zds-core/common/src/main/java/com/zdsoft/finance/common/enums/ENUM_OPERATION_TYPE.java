package com.zdsoft.finance.common.enums;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title ENUM_OPERATION_TYPE.java
 * @className ENUM_OPERATION_TYPE
 * @description 操作类型枚举
 * @author LiaoGuoWei
 * @create 2017/3/30 17:06
 * @version V1.0
 **/
public enum ENUM_OPERATION_TYPE {

    /**
     * 查看
     */
    VIEW("0","view"),

    /**
     * 编辑
     */
    EDIT("1","edit");

    public final String value;

    public final String name;

    private ENUM_OPERATION_TYPE(String value,String name){
        this.value=value;
        this.name=name;
    }
}
