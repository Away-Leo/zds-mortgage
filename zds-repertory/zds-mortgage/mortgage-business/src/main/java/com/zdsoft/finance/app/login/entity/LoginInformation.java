package com.zdsoft.finance.app.login.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:LoginInformation.java
 * @Package:com.zdsoft.guarantee.domain.app
 * @Description:app登录信息
 * @author: dengyy
 * @date:2016年7月27日 下午2:11:25
 * @version:v1.0
 */
@Entity
@Table(name="zf_LoginInformation")
public class LoginInformation extends BaseEntity{
    
    /**
     * 用一句话描述这个变量表示什么
     */
    private static final long serialVersionUID = 1L;

    /**
     * 登录账户
     */
    @Column(length=64)
    private String username ;
    
    /**
     * 登录手机号
     */
    @Column(length=64)
    private String mobile ;
    
    /**
     * 登录设备名称
     */
    @Column(length=64)
    private String device ;
    
    /**
     * 登录时间
     */
    @Column(length=20)
    private long loginDate ;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public long getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(long loginDate) {
        this.loginDate = loginDate;
    }
    
}
