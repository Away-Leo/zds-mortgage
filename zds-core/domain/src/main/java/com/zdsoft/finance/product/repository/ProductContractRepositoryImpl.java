package com.zdsoft.finance.product.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;



/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProductContractRepositoryImpl.java 
 * @ClassName: ProductContractRepositoryImpl 
 * @Description: 产品合同模板关系
 * @author gufeng 
 * @date 2017年3月13日 下午4:47:53 
 * @version V1.0
 */
@SuppressWarnings("deprecation")
public class ProductContractRepositoryImpl{

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectContract(String productId, boolean isAdd){
		StringBuffer sql = new StringBuffer("select id,contractName from con_contract_tpl where isDeleted = 'F' and contractTplState='Enable' ");
		sql.append(" and (orgCantractApplyId is null or orgCantractApplyId=''");
		sql.append(" or orgCantractApplyId in(");
		sql.append(" select id from con_agency_contract_tpl where isDeleted='F' and applyOrgCode in(select c.code from prd_company_product cp left join prd_company c on cp.company_id = c.id where cp.isDeleted='F' and c.isDeleted = 'F' and cp.product_id=:productId)");
		sql.append(")) and capitalId in(select capitalist_id from prd_product where isDeleted = 'F' and id=:productId)");
		if(isAdd){
			sql.append(" and id in(select contractId from prd_product_contract where isDeleted = 'F' and productId = :productId)");
		}else{
			sql.append(" and id not in(select contractId from prd_product_contract where isDeleted = 'F' and productId = :productId)");
		}
		Query query = (Query) em.createNativeQuery(sql.toString());
		query.setParameter("productId", productId);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.getResultList();
	}
	
}
