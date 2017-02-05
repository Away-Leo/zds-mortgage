package com.zdsoft.finance.app.login.service;

import com.zdsoft.finance.app.LoginInformationDto;
import com.zdsoft.framework.core.common.exception.AppException;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:LoginInformationServer.java
 * @Package:com.zdsoft.guarantee.service.app.login
 * @Description:app 登录记录接口
 * @author: dengyy
 * @date:2016年7月27日 下午2:54:25
 * @version:v1.0
 */
public interface LoginInformationService {

    /**
     * 
     * 根据id获取登录记录
     *
     * @author dengyy
     * @param id 登录记录id
     * @return
     * @throws AppException
     */
    public LoginInformationDto findById(String id) throws AppException ;
    
    /**
     * 
     * 根据登录账户获取上一次的登录信息
     *
     * @author dengyy
     * @param userName 登录账户
     * @return
     * @throws AppException
     */
    public LoginInformationDto findLoginInformationByUserName(String userName) throws AppException ;
    
    /**
     * 
     * 保存登录记录
     *
     * @author dengyy
     * @param dto 登录记录
     * @return
     * @throws AppException
     */
    public LoginInformationDto saveOrUpdate(LoginInformationDto dto) throws AppException ;
}
