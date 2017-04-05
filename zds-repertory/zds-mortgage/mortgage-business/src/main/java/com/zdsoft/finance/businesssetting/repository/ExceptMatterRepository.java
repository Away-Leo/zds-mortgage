package com.zdsoft.finance.businesssetting.repository;

import com.zdsoft.finance.businesssetting.entity.ExceptMatter;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title ExceptMatterRepository.java
 * @className ExceptMatterRepository
 * @description 特批事项操作库
 * @author LiaoGuoWei
 * @create 2017/3/3 14:28
 * @version V1.0
 **/
public interface ExceptMatterRepository extends CustomRepository<ExceptMatter, String>{
	
	/** 
	 * @Title: findByExceptMatterType 
	 * @Description: 通过特批事项类型查询风险特批事项
	 * @author wangrongwei
	 * @param exceptMatterType 特批事项类型
	 * @return  
	 */ 
	public List<ExceptMatter> findByExceptMatterType(String exceptMatterType);
	
	/** 
	 * @Title: findByExceptMattercode 
	 * @Description: 通过事项编码查询风险特批事项
	 * @author wangrongwei
	 * @param exceptMattercode 特批事项编号
	 * @return  
	 */ 
	public ExceptMatter findByExceptMattercode(String exceptMattercode);

	/**
	 * @Title: findMaxCode
	 * @Description: 查找最大特批事项编号
	 * @author liaoguowei
	 * @param
	 * @return java.lang.String
	 * @throws
	 */
	@Query(" select max(e.exceptMattercode) from ExceptMatter e where e.isDeleted = false ")
	public String findMaxCode();


	/**
	 * @Title: findByAllTypeAndName
	 * @Description: 按照特批事项类和名称查找
	 * @author liaoguowei
	 * @param exceptMatterType 特批事项类型
	 * @param exceptMatterName 特批事项名称
	 * @return java.util.List<com.zdsoft.finance.businesssetting.entity.ExceptMatter>
	 * @throws
	 */
	@Query(" from ExceptMatter e where e.isDeleted = false and e.exceptMatterType = :exceptMatterType and e.exceptMatterName = :exceptMatterName ")
	public List<ExceptMatter> findByTypeAndName(@Param("exceptMatterType")String exceptMatterType,@Param("exceptMatterName")String exceptMatterName);

	/**
	 * @Title: findExceptMatterByCondition
	 * @Description: 按照条件查找
	 * @author liaoguowei
	 * @param pageable 分页参数
	 * @param exceptMatter 查询条件
	 * @return com.zdsoft.framework.core.common.page.Page<com.zdsoft.finance.businesssetting.entity.ExceptMatter>
	 * @throws
	 */
	public default Page<ExceptMatter> findExceptMatterByCondition(Pageable pageable,ExceptMatter exceptMatter){
		Map<String,Object> qryCondition=new HashMap<String,Object>();
		StringBuffer hql=new StringBuffer(" from ExceptMatter e where e.isDeleted = false ");
		//类型
		if(ObjectHelper.isNotEmpty(exceptMatter.getExceptMatterType())){
			hql.append(" and e.exceptMatterType = :exceptMatterType ");
			qryCondition.put("exceptMatterType",exceptMatter.getExceptMatterType());
		}
		//特批事项
		if(ObjectHelper.isNotEmpty(exceptMatter.getExceptMatterName())){
			hql.append(" and e.exceptMatterName like :exceptMatterName escape'/' ");
			qryCondition.put("exceptMatterName","%"+exceptMatter.getExceptMatterName().replaceAll("%","/%").replaceAll("_","/_")+"%");
		}
		//状态
		if(ObjectHelper.isNotEmpty(exceptMatter.getStatus())){
			hql.append(" and e.status = :status ");
			qryCondition.put("status",exceptMatter.getStatus());
		}
		hql.append(" order by e.exceptMattercode ");
		return this.findByHqlPage(pageable,hql.toString(),qryCondition);
	}

}
