package com.zdsoft.finance.app.login.vo;

import com.zdsoft.finance.app.login.entity.LoginInformation;
import com.zdsoft.finance.common.base.BaseVo;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:PersonalVo.java
 * @Package:com.zdsoft.finance.app.login.vo
 * @Description:app 个人中心主页信息vo
 * @author: jingjy
 * @date:2017年1月11日 下午7:31:44
 * @version:v1.0
 */
public class PersonalVo extends BaseVo<LoginInformation> {

    
    /**
     * 用一句话描述这个变量表示什么
     */
    private static final long serialVersionUID = 1L;

    /**
     * 登录者Id
     */
    private String clientId ;
    
    /**
     * 登录者姓名
     */
    private String name ;
    
    /**
     * 性别(男：303001，女：303002)
     */
    private String gender ;

    /**
     * 上次登录时间
     */
    private String latestLogDate ;
    
    /**
     * 上次登录设备
     */
    private String latestDevice ;
    
    /**
     * 手机号码
     */
    private String phone ;

    /**
     * 电子邮箱（可选）
     */
    private String email ;
    
    /**
     * QQ号码（可选）
     */
    private String qq ;


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getLatestLogDate() {
        return latestLogDate;
    }

    public void setLatestLogDate(String latestLogDate) {
        this.latestLogDate = latestLogDate;
    }

    public String getLatestDevice() {
        return latestDevice;
    }

    public void setLatestDevice(String latestDevice) {
        this.latestDevice = latestDevice;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
    
}
