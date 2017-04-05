package com.zdsoft.finance.businesssetting.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.businesssetting.entity.AuthGrade;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @title AuthGradeRepository.java
 * @className AuthGradeRepository
 * @description 授权等级设定操作库
 * @author LiaoGuoWei
 * @create 2017/3/3 14:24
 * @version V1.0
 **/
public interface AuthGradeRepository extends CustomRepository<AuthGrade, String> {

	/**
	 * @Title: qryConditions @Description: 自定义分页查询 @author liaoguowei @param
	 * pageable 分页参数 @param productParentId 父产品ID @param productChildId
	 * 子产品ID @param gradeCode 等级编号 @return
	 * com.zdsoft.framework.core.common.page.Page<com.zdsoft.finance.businesssetting.entity.AuthGrade> @throws
	 */
	public default Page<AuthGrade> qryConditions(Pageable pageable, String productParentId, String productChildId,
			String gradeCode) {
		StringBuffer hql = new StringBuffer(" from AuthGrade g where g.isDeleted = :isDeleted ");
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("isDeleted", !BaseEntity.DELETED);
		if (ObjectHelper.isNotEmpty(productParentId)) {
			hql.append(" and g.productParentId = :productParentId ");
			conditions.put("productParentId", productParentId);
		}
		if (ObjectHelper.isNotEmpty(productChildId)) {
			hql.append(" and g.productChildId = :productChildId ");
			conditions.put("productChildId", productChildId);
		}
		if (ObjectHelper.isNotEmpty(gradeCode)) {
			hql.append(" and g.gradeCode = :gradeCode ");
			conditions.put("gradeCode", gradeCode);
		}
		hql.append(" order by g.createTime desc,g.productParentId  ");
		Page<AuthGrade> returnData = this.findByHqlPage(pageable, hql.toString(), conditions);
		return returnData;
	}

	/**
	 * @Title: findByGradeCode @Description: 按照等级编号查找 @author liaoguowei @param
	 * gradeCode 等级编号 @return List<AuthGrade> @throws
	 */
	@Query(" select a from AuthGrade a where a.isDeleted=false and a.gradeCode=:gradeCode")
	public List<AuthGrade> findByGradeCode(@Param("gradeCode") String gradeCode);

	/**
	 * @Title: findInstitutionAuth @Description: 机构授权查询 @author
	 * liaoguowei @param pageable 分页参数 @param authGrade 授权等级 @return
	 * com.zdsoft.framework.core.common.page.Page<com.zdsoft.finance.businesssetting.entity.AuthGrade> @throws
	 */
	public default Page<AuthGrade> findInstitutionAuth(Pageable pageable, AuthGrade authGrade) throws Exception {
		Map<String, Object> qryCondition = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer(" SELECT C.AUTHGRADENAME     AS \"finalGradeName\", ");
										sql.append(" C.ORIGINALGRADENAME AS \"originalGradeName\", ");
										sql.append(" C.HANDELERNAME      AS \"handerName\", ");
										sql.append(" C.HANDELTIME        AS \"handelTime\", ");
										sql.append(" A.AUTHAMOUNT        AS \"authAmount\", ");
										sql.append(" A.PRODUCTPARENTID   AS \"productParentId\", ");
										sql.append(" A.PRODUCTPARENTNAME AS \"productParentName\", ");
										sql.append(" A.PRODUCTCHILDID    AS \"productChildId\", ");
										sql.append(" A.PRODUCTCHILDNAME  AS \"productChildName\", ");
										sql.append(" O.ORGCD             AS \"institutionCode\", ");
										sql.append(" O.ORGNM             AS \"institutionName\" ");
								sql.append(" FROM BUSS_ORGINTERMEDIATE O ");
								sql.append(" LEFT JOIN BUSS_ORGAUTHCONN C ON C.ORGINTERMEDIATEID = O.ORGCD ");
								sql.append(" LEFT JOIN BUSS_AUTHGRADE A ON A.GRADECODE = C.AUTHGRADECODE AND A.ISDELETED = 'F' ");
								sql.append(" WHERE 1 = 1 ");
								sql.append(" AND A.ID IS NOT NULL ");
		//机构
		if(ObjectHelper.isNotEmpty(authGrade.getInstitutionCode())){
			sql.append(" AND O.ORGCD = :institutionCode ");
			qryCondition.put("institutionCode",authGrade.getInstitutionCode());
		}
		//终评
		if(ObjectHelper.isNotEmpty(authGrade.getFinalGradeCode())){
			sql.append(" AND C.AUTHGRADECODE = :finalGradeCode ");
			qryCondition.put("finalGradeCode",authGrade.getFinalGradeCode());
		}
		//产品大类
		if(ObjectHelper.isNotEmpty(authGrade.getProductParentId())){
			sql.append(" AND A.PRODUCTPARENTID = :productParentId ");
			qryCondition.put("productParentId",authGrade.getProductParentId());
		}
		//产品小类
		if(ObjectHelper.isNotEmpty(authGrade.getProductChildId())){
			sql.append(" AND A.PRODUCTCHILDID = :productChildId ");
			qryCondition.put("productChildId",authGrade.getProductChildId());
		}
		//评级人
		if(ObjectHelper.isNotEmpty(authGrade.getHanderName())){
			sql.append(" AND C.HANDELERNAME LIKE :handerName ");
			qryCondition.put("handerName","%"+authGrade.getHanderName()+"%");
		}
		sql.append(" ORDER BY C.CREATETIME DESC ");

		Page<AuthGrade> authGrades = this.findBySqlEntityPage(pageable, sql.toString(), qryCondition, AuthGrade.class);
		return authGrades;
	}

	/**
	 * @Title: findByCaseApplyId
	 * @Description: 通过案件ID查询案件产品所属机构评级 金额
	 * @author wangrongwei
	 * @param caseApplyId
	 *            案件ID
	 * @return Page<Map<String, Object>>
	 * @throws Exception
	 */
	public default Page<Map<String, Object>> findByCaseApplyId(String caseApplyId) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select auth.* from mkt_case_apply m ");
		sql.append(" left join buss_orgintermediate b ");
		sql.append(" on b.orgcd = m.mechanismcode ");
		sql.append(" left join buss_orgauthconn conn ");
		sql.append(" on conn.orgintermediateid = b.orgcd ");
		sql.append(" left join buss_authgrade auth ");
		sql.append(" on conn.authgradecode = auth.gradecode where m.id='" + caseApplyId + "' ");
		sql.append(" and auth.productchildid= m.productsubtypeid ");
		return this.findBySqlMapPage(new PageRequest(), sql.toString(), new HashMap<>());
	}

	/**
	 * @Title: findByProductChildIdAndGradeCode
	 * @Description: 按照产品子类和等级编号查询数据
	 * @author liaoguowei
	 * @param productChildId 子产品ID
	 * @param gradeCode 等级编号
	 * @return com.zdsoft.finance.businesssetting.entity.AuthGrade
	 * @throws
	 */
	@Query(" from AuthGrade a where a.isDeleted = false and a.productChildId = :productChildId and a.gradeCode = :gradeCode ")
	public AuthGrade findByProductChildIdAndGradeCode(@Param("productChildId")String productChildId,@Param("gradeCode")String gradeCode);

}
