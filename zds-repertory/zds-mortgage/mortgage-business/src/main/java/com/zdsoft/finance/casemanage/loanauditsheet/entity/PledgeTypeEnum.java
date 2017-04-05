package com.zdsoft.finance.casemanage.loanauditsheet.entity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: PledgeTypeEnum.java
 * @Package com.zdsoft.finance.casemanage.loanauditsheet.entity
 * @Description: 抵押类型（抵押顺位）枚举类
 * @author Liyb
 * @date 2017年1月15日 下午12:07:59
 * @version V1.0
 */
public enum PledgeTypeEnum {

    FIRST_MORTGAGE("YWDM0051011", "一抵"),
    SECOND_MORTGAGE("YWDM0051012", "二抵"),
	THERE_MORTGAGE("YWDM0051013", "三抵"),
	OTHER_MORTGAGE("YWDM0051013", "其他");
    private String name;
    private String key;

    private PledgeTypeEnum(String key, String name) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
