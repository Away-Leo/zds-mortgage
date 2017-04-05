package com.zdsoft.finance.businesssetting.repository;

import com.zdsoft.finance.businesssetting.entity.InComeBody;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title InComeBodyRepository.java
 * @className InComeBodyRepository
 * @description 收款主体操作库
 * @author LiaoGuoWei
 * @create 2017/3/3 14:37
 * @version V1.0
 **/
public interface InComeBodyRepository extends CustomRepository<InComeBody, String>{

    /**
     * @Title: findIncomBodyByCondition
     * @Description: 按照条件查找
     * @author liaoguowei
     * @param pageable 分页参数
     * @param inComeBody 查询条件
     * @return com.zdsoft.framework.core.common.page.Page<com.zdsoft.finance.businesssetting.entity.InComeBody>
     * @throws
     */
    public default Page<InComeBody> findIncomBodyByCondition(Pageable pageable,InComeBody inComeBody){
        Map<String,Object> qryCondition=new HashMap<String,Object>();
        StringBuffer hql=new StringBuffer(" from InComeBody i where i.isDeleted = false ");

        //收款主体
        if(ObjectHelper.isNotEmpty(inComeBody.getInBody())){
            hql.append(" and i.inBody like :inBody escape '/' ");
            qryCondition.put("inBody","%"+inComeBody.getInBody().replaceAll("%","/%").replaceAll("_","/_")+"%");
        }
        //机构
        if(ObjectHelper.isNotEmpty(inComeBody.getOrgIds())){
            hql.append(" and i.orgId in (:orgIds) ");
            qryCondition.put("orgIds", Arrays.asList(inComeBody.getOrgIds().split(",")));
        }
        hql.append(" order by i.createTime desc,i.updateTime desc ");
        return this.findByHqlPage(pageable,hql.toString(),qryCondition);
    }
    
    /**
	 * @Title: findByOrgId 
	 * @Description: 查询当前机构收款主体
	 * @author jincheng 
	 * @param orgId
	 * @return
	 */
    @Query(" from InComeBody where orgId=?1 and isDeleted='F' ")
	List<InComeBody> findByOrgId(String orgId);
}
