package com.zdsoft.finance.casemanage.promotion.otherproperty.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.AssetsSecurities;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AssetsSecuritiesRepository.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.repository
 * @Description:其他资产中的有价证券信息实现类
 * @author: xiongpan
 * @date:2017年2月20日 上午11:59:02
 * @version:v1.0
 */
public interface AssetsSecuritiesRepository extends CustomRepository<AssetsSecurities, String>{

	/**
	 * 
	 * @Title: findPageAssetsSecurities 
	 * @Description: 根据对应的案件id查询出所有其他资产之有价证券分页信息
	 * @author xiongpan
	 * @param pageable 分页信息
	 * @param caseApplyId 对应的案件id
	 * @return
	 */
	public default Page<Map<String, Object>> findPageAssetsSecurities(PageRequest pageable, String caseApplyId){
		StringBuffer sql = new StringBuffer(" select c.id as id, ");
		sql.append(  "  c.securitiesName as securitiesName, ");
        sql.append(  "  c.worth as worth, " );
        sql.append(  "  c.owner as owner, " );
        sql.append(  "  c.caseApplyId as caseApplyId " );
        
        sql.append(   " from case_other_assets_securities c " );
        sql.append(  " where c.isDeleted='F' and c.caseApplyId='"+caseApplyId+"'");
        StringBuffer extendSql = new StringBuffer(" order by c.createTime desc ");
        
        List<QueryObj> params = new ArrayList<QueryObj>();
        return this.getListObjectBySql(pageable, params, sql, extendSql);
	}

	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据对应的案件id查询出所有其他资产之有价证券列表信息
	 * @author xiongpan
	 * @param caseApplyId 对应的案件id
	 * @return
	 */
	@Query(" FROM AssetsSecurities a where a.caseApplyId=:caseApplyId and a.isDeleted ='F' order by a.updateTime desc ")
	public List<AssetsSecurities> findByCaseApplyId(@Param(value="caseApplyId")String caseApplyId);

}
