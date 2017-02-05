package com.zdsoft.finance.app;

import com.zdsoft.finance.commom.dto.BaseEntityDto;
 
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:LoginInformationDto.java
 * @Package:com.zdsoft.finance.app
 * @Description:app登录记录dto
 * @author: jingjy
 * @date:2017年1月11日 下午6:07:08
 * @version:v1.0
 */
public class LoginInformationDto extends BaseEntityDto {

      /**
      * ID
      */
    private String id;
    /**
     * 登录账户
     */
    private String username ;
    
    /**
     * 登录手机号
     */
    private String mobile ;
    
    /**
     * 登录设备名称
     */
    private String device ;
    
    /**
     * 登录时间
     */
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
