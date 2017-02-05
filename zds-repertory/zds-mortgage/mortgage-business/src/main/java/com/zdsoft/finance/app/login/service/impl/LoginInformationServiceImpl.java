package com.zdsoft.finance.app.login.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.app.LoginInformationDto;
import com.zdsoft.finance.app.login.entity.LoginInformation;
import com.zdsoft.finance.app.login.repository.LoginInformationRepository;
import com.zdsoft.finance.app.login.service.LoginInformationService;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.exception.AppException;
import com.zdsoft.framework.core.common.exception.InvalidArgumentException;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:LoginInformationServiceImpl.java
 * @Package:com.zdsoft.finance.app.login.service.impl
 * @Description:app登录记录接口实现
 * @author: jingjy
 * @date:2017年1月14日 下午1:30:20
 * @version:v1.0
 */
@Service
public class LoginInformationServiceImpl extends BaseServiceImpl<LoginInformation, CustomRepository<LoginInformation, String>> 
                implements LoginInformationService {

    @Log
    private Logger log;
    
    @Resource
    private LoginInformationRepository loginInformationRepository ;
    
    @Override
    public LoginInformationDto findById(String id) throws AppException {
        log.info("进入根据id查询登录记录信息！");
        if (ObjectHelper.isEmpty(id)) {
            throw new InvalidArgumentException("登录记录id不能为空！");
        }
        log.debug("查询登录记录id："+id);
        LoginInformation entity = loginInformationRepository.findOne(id);
        LoginInformationDto dto = new LoginInformationDto() ;
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    @Override
    public LoginInformationDto findLoginInformationByUserName(String userName) throws AppException {
        log.info("根据登录账户查询登录记录！");
        if (ObjectHelper.isEmpty(userName)) {
           throw new InvalidArgumentException("登录账户不能为空！");
        }
        log.info("登录的账户为："+userName);
        List<LoginInformation> list = loginInformationRepository.findLoginInformationByUserName(userName);
        LoginInformationDto dto = new LoginInformationDto() ;
        LoginInformation loginInformation = new LoginInformation() ;
        if (ObjectHelper.isNotEmpty(list)) {
           if (list.size()==1) {
               loginInformation = list.get(0);
           }
           if (list.size()>1) {
               loginInformation = list.get(1);
           }
           BeanUtils.copyProperties(loginInformation, dto);
        }
        return dto;
    }

    @Override
    @Transactional
    public LoginInformationDto saveOrUpdate(LoginInformationDto dto) throws AppException {
        log.info("进入登录记录保存！");
        LoginInformation entity = null ;
        if (ObjectHelper.isNotEmpty(dto.getId())) {
            entity = loginInformationRepository.findOne(dto.getId());
        }else {
            entity = new LoginInformation() ;
        }
        BeanUtils.copyProperties(dto, entity,new String[]{"id"});
        entity.setLoginDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
        if(ObjectHelper.isEmpty(entity.getId())){
            entity = loginInformationRepository.updateEntity(entity);
        }else{
            entity = loginInformationRepository.saveEntity(entity);
        }
        dto.setId(entity.getId());
        dto.setLoginDate(entity.getLoginDate());
        return dto;
    }

}
