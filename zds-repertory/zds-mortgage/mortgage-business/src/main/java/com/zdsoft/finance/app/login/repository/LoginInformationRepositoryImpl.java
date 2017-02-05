package com.zdsoft.finance.app.login.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.zdsoft.finance.app.login.entity.LoginInformation;
import com.zdsoft.framework.core.common.util.ObjectHelper;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:LoginInformationRepositoryImpl.java
 * @Package:com.zdsoft.finance.app.login.repository
 * @Description:app登录记录实现
 * @author: jingjy
 * @date:2017年1月14日 下午1:30:29
 * @version:v1.0
 */
public class LoginInformationRepositoryImpl {
    
    @PersistenceContext
    private EntityManager em;
    
    
    /**
     * 根据姓名查询信息
     * @return
     */
    public List<LoginInformation> findLoginInformationByUserName(String username){
        StringBuffer hql = new StringBuffer("from LoginInformation where username =:username ");
        Query query = em.createQuery(hql.toString());
        query.setParameter("username" ,username);
        @SuppressWarnings("unchecked")
        List<LoginInformation> list = query.getResultList();
        if(ObjectHelper.isNotEmpty(list) && list.size() > 0){
            return list;
        }else{
            return null;
        }
    }
    

}
