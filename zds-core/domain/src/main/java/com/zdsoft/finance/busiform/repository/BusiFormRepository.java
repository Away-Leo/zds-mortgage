package com.zdsoft.finance.busiform.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;

import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.MyDraft;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 审批单操作仓库
 * 
 * @author liuwei
 * @date 2016/10/12
 * @version 1.0
 */
public interface BusiFormRepository extends CustomRepository<BusiForm, String> {

	/**
	 * 根据关联业务表单id查找审批单
	 * 
	 * @param businessEntityId
	 *            关联业务表单id
	 * @param businessEntitym
	 *            关联业务表单域对象名字
	 * @return 审批单
	 */
	public BusiForm findByBusinessEntityIdAndBusinessEntityName(String businessEntityId, String businessEntityName);

	/**
	 * 根据关联业务表单数据id查找审批单信息
	 * 
	 * @param businessEntityId
	 *            关联业务表单数据id
	 * @return 审批单
	 */
	public BusiForm findByBusinessEntityId(String businessEntityId);
	
	/**
	 * 我的草稿查询分页列表
	 * @param myDraft
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	public Page<MyDraft> findByPage(MyDraft myDraft,Pageable pageable) throws BusinessException;
	/**
	 * 
	 * @Title: findByComponentsEntityIdOrderByCreateTimeDesc 
	 * @Description:  通过案件id查询业务表单
	 * @author xj 
	 * @param caseApplyId
	 * @return
	 */
	public List<BusiForm> findByComponentsEntityIdOrderByCreateTimeDesc(String caseApplyId);
	
	/**
	 * 
	 * @Title: findBusiFormByHql 
	 * @Description: 多条件查询业务表单
	 * @author yangjia 
	 * @param componentsEntityId
	 * @param businessEntityId
	 * @param processInstanceKey
	 * @return
	 */
	@Query("from BusiForm where componentsEntityId=?1 and businessEntityId=?2 and processInstanceKey=?3 and isDeleted='F' ")
	public default List<BusiForm> findBusiFormByHql(String componentsEntityId,String businessEntityId,String processInstanceKey){
		
		 StringBuffer hql=new StringBuffer(" from BusiForm where isDeleted='F' ");
	        Map<String,Object> conditions=new HashMap<String,Object>();
	       
	        if(ObjectHelper.isNotEmpty(componentsEntityId)){
	            hql.append(" and componentsEntityId = :componentsEntityId ");
	            conditions.put("componentsEntityId",componentsEntityId);
	        }
	        if(ObjectHelper.isNotEmpty(businessEntityId)){
	            hql.append(" and businessEntityId = :businessEntityId ");
	            conditions.put("businessEntityId",businessEntityId);
	        }
	        if(ObjectHelper.isNotEmpty(processInstanceKey)){
	            hql.append(" and processInstanceKey = :processInstanceKey ");
	            conditions.put("processInstanceKey",processInstanceKey);
	        }
	       
	        List<BusiForm> list = this.findByHql(hql.toString(), conditions);
	        return list;
	}
	
	

}
