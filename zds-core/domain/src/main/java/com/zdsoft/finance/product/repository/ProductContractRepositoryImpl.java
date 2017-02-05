package com.zdsoft.finance.product.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;


/**
 * 产品合同模板关系
 * @createTime 2017年1月10日 下午3:51:39
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
@SuppressWarnings("deprecation")
public class ProductContractRepositoryImpl{

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * 合同集合
	 * @param productId 产品id
	 * @param isAdd 是否已添加
	 * @return 合同集合数据
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectContract(String productId, boolean isAdd){
		StringBuffer sql = new StringBuffer("select id,contractNm from prct_temp_contract where isDeleted = 0");
		if(isAdd){
			sql.append(" and id in(select contractId from prct_product_contract where isDeleted = 0 and productId = :productId)");
		}else{
			sql.append(" and id not in(select contractId from prct_product_contract where isDeleted = 0 and productId = :productId)");
		}
		Query query = (Query) em.createNativeQuery(sql.toString());
		query.setParameter("productId", productId);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.getResultList();
	}
	
}
