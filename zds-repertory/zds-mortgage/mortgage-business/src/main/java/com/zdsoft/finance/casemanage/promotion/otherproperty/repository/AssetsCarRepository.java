package com.zdsoft.finance.casemanage.promotion.otherproperty.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.AssetsCar;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AssetsCarRepository.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.repository
 * @Description:其他资产中的汽车信息实现类
 * @author: xiongpan
 * @date:2017年2月17日 上午10:32:25
 * @version:v1.0
 */
public interface AssetsCarRepository extends CustomRepository<AssetsCar, String>{

	/**
	 * 
	 * @Title: findPageAssetsCar 
	 * @Description: 根据案件id查询出该案件的所有其他资产中的汽车信息的分页信息
	 * @author xiongpan
	 * @param pageable 分页信息
	 * @param caseApplyId 案件id
	 * @return
	 * @throws Exception
	 */
	public default Page<Map<String, Object>> findPageAssetsCar(PageRequest pageable, String caseApplyId) throws Exception {
		StringBuffer sql = new StringBuffer(" select c.id as id, ");
		sql.append(  "  c.carType as carType, ");
        sql.append(  "  c.useYear as useYear, " );
        sql.append(  "  c.worth as worth, " );
        sql.append(  "  c.owner as owner, " );
        sql.append(  "  c.isPledge as isPledge, " );
        sql.append(  "  c.pledgePerson as pledgePerson, " );
        sql.append(  "  c.pledgeAmount as pledgeAmount, " );
        sql.append(  "  c.pledgeDeadLine as pledgeDeadLine, " );
        sql.append(  "  c.pledgeDeadLineUnit as pledgeDeadLineUnit, " );
        sql.append(  "  c.caseApplyId as caseApplyId " );
        
        sql.append(   " from case_other_assets_car c " );
        sql.append(  " where c.isDeleted='F' and c.caseApplyId='"+caseApplyId+"'");
        StringBuffer extendSql = new StringBuffer(" order by c.updateTime desc ");
        
        List<QueryObj> params = new ArrayList<QueryObj>();
        return this.getListObjectBySql(pageable, params, sql, extendSql);
		
	}

	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件id查询出该案件的所有其他资产中的汽车信息的列表信息
	 * @author xiongpan
	 * @param caseApplyId 案件id
	 * @return
	 */
	@Query(" FROM AssetsCar a where a.caseApplyId=:caseApplyId and a.isDeleted ='F' order by a.updateTime desc ")
	public List<AssetsCar> findByCaseApplyId(@Param(value="caseApplyId")String caseApplyId);

}
