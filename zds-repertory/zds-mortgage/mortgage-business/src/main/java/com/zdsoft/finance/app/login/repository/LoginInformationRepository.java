package com.zdsoft.finance.app.login.repository;

import java.util.List;

import com.zdsoft.finance.app.login.entity.LoginInformation;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:LoginInformationRepository.java
 * @Package:com.zdsoft.finance.app.login.repository
 * @Description:用一句话描述该文件做什么
 * @author: jingjy
 * @date:2017年1月11日 下午8:02:03
 * @version:v1.0
 */
public interface LoginInformationRepository extends CustomRepository<LoginInformation, String> {
    /**
     * 根据姓名查询登录信息
     *
     * @author jingjy
     * @param userName
     * @return
     */
    public List<LoginInformation> findLoginInformationByUserName(String username);
    
    
}
