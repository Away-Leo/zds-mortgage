package com.zdsoft.finance.casemanage.promotion.otherproperty.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.CustomerHouse;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CustomerHouseRepository.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.repository
 * @Description:其他资产中的房产信息实现类
 * @author: xiongpan
 * @date:2017年2月14日 下午3:57:20
 * @version:v1.0
 */
public interface CustomerHouseRepository extends CustomRepository<CustomerHouse, String>{

	/**
	 * 
	 * @Title: findPageCustomerHouse 
	 * @Description: 根据案件id查询出该案件的所有其他资产中的房产信息的分页列表
	 * @author xiongpan
	 * @param pageable 分页信息
	 * @param caseApplyId 对应的案件id
	 * @return
	 * @throws Exception
	 */
	public default Page<Map<String, Object>> findPageCustomerHouse(PageRequest pageable, String caseApplyId) throws Exception{
		StringBuffer sql = new StringBuffer(" select c.id as id, ");
		sql.append(  "  c.owner as owner, ");
        sql.append(  "  c.houseProperty as houseProperty, " );
        sql.append(  "  c.isPledge as isPledge, " );
        sql.append(  "  c.pledgePerson as pledgePerson, " );
        sql.append(  "  c.pledgeAmount as pledgeAmount, " );
        sql.append(  "  c.pledgeDeadLine as pledgeDeadLine, " );
        sql.append(  "  c.pledgeDeadLineUnit as pledgeDeadLineUnit, " );
        sql.append(  "  c.measureArea as measureArea, " );
        sql.append(  "  c.worth as worth, " );
        sql.append(  "  c.province as province, " );
        sql.append(  "  c.city as city, " );
        sql.append(  "  c.district as district, " );
        sql.append(  "  c.address as address " );
        
        sql.append(   " from case_house c " );
        sql.append(  " where c.isDeleted='F' and c.caseApplyId='"+caseApplyId+"'");
        StringBuffer extendSql = new StringBuffer(" order by c.createTime desc ");
        
        List<QueryObj> params = new ArrayList<QueryObj>();
        return this.getListObjectBySql(pageable, params, sql, extendSql);
        
	}

	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件id查询出该案件的所有其他资产中的房产信息的列表信息
	 * @author xiongpan
	 * @param caseApplyId 对应的案件id
	 * @return
	 */
	@Query(" FROM CustomerHouse c where c.caseApplyId=:caseApplyId and c.isDeleted ='F' order by c.updateTime desc ")
	public List<CustomerHouse> findByCaseApplyId(@Param(value="caseApplyId")String caseApplyId);

}
