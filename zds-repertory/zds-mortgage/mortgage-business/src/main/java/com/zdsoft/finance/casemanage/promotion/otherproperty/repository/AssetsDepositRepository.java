package com.zdsoft.finance.casemanage.promotion.otherproperty.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.AssetsDeposit;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AssetsDepositRepository.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.repository
 * @Description:其他资产中的存款信息实现类
 * @author: xiongpan
 * @date:2017年2月21日 下午2:29:27
 * @version:v1.0
 */
public interface AssetsDepositRepository extends CustomRepository<AssetsDeposit, String>{

	/**
	 * 
	 * @Title: findPageAssetsDeposit 
	 * @Description: 根据案件id查询出该案件的所有其他资产中的存款信息的分页列表
	 * @author xiongpan
	 * @param pageable 分页信息
	 * @param caseApplyId 案件id
	 * @return
	 */
	public default Page<Map<String, Object>> findPageAssetsDeposit(PageRequest pageable, String caseApplyId){
		StringBuffer sql = new StringBuffer(" select c.id as id, ");
		sql.append(  "  c.depositType as depositType, ");
        sql.append(  "  c.depositDeadLine as depositDeadLine, " );
        sql.append(  "  c.depositDeadLineUnit as depositDeadLineUnit, " );
        sql.append(  "  c.depositAmount as depositAmount, " );
        sql.append(  "  c.caseApplyId as caseApplyId " );
        
        sql.append(   " from case_other_assets_deposit c " );
        sql.append(  " where c.isDeleted='F' and c.caseApplyId='"+caseApplyId+"'");
        StringBuffer extendSql = new StringBuffer(" order by c.createTime desc ");
        
        List<QueryObj> params = new ArrayList<QueryObj>();
        return this.getListObjectBySql(pageable, params, sql, extendSql);
	};
	
	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件id查询出该案件的所有其他资产中的存款信息的列表信息
	 * @author xiongpan
	 * @param caseApplyId 案件id
	 * @return
	 */
	@Query(" FROM AssetsDeposit a where a.caseApplyId=:caseApplyId and a.isDeleted ='F' order by a.updateTime desc ")
	List<AssetsDeposit> findByCaseApplyId(@Param(value="caseApplyId")String caseApplyId);


}
