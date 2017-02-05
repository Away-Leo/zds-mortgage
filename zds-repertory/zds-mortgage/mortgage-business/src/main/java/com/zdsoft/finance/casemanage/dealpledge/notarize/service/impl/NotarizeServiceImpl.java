package com.zdsoft.finance.casemanage.dealpledge.notarize.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.dealpledge.notarize.entity.Notarize;
import com.zdsoft.finance.casemanage.dealpledge.notarize.repository.NotarizeRepository;
import com.zdsoft.finance.casemanage.dealpledge.notarize.service.NotarizeService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

@Service("NotarizeService")
public class NotarizeServiceImpl extends BaseServiceImpl<Notarize, CustomRepository<Notarize,String>> 
		implements NotarizeService{
	
	@Autowired
	private NotarizeRepository notarizeRepository;

//    @Override
//    public void deleteNotarize(String id) throws Exception {
//        StringBuffer hql = new StringBuffer(" from Notarize t where 1=1");
//        hql.append(" and t.caseApplyId :=caseApplyId");
//        hql.append(" and t.caseApplyId :=caseApplyId");
//        hql.append(" and t.caseApplyId :=caseApplyId");
//        
//        
//        Map<String, Object> map =new HashMap<>() ;
//        map.put("caseApplyId", id);
//        map.put("caseApplyId", id);
//        map.put("caseApplyId", id);
//        
//        if(){
//            hql.append(" order by c,c,d");
//        }
//        
//        
//       
//        
//        notarizeRepository.findByHqlPage(Pageable, hql, map);
//        
//    }

    @Override
    public Page<Notarize> queryNotarizeBycaseApplyId(Pageable pageable,String caseApplyId) throws BusinessException {
        
        StringBuilder hql = new StringBuilder("from Notarize n where 1 = 1");
        Map<String, Object> map = new HashMap<String, Object>();
        caseApplyId ="cxk";
        hql.append(" and n.isDeleted =:isDeleted");
        if(ObjectHelper.isNotEmpty(caseApplyId)){
            hql.append(" and n.caseApplyId =:caseApplyId");
        }else{
            throw new BusinessException("10010004", "传入参数为空");
        }
        map.put("isDeleted", !BaseEntity.DELETED);
        map.put("caseApplyId", caseApplyId);
        
        hql.append(" Order by n.notarizeDate");
        Page<Notarize> page = notarizeRepository.findByHqlPage(pageable, hql.toString(), map);
        
        return page;
    }

	

}
