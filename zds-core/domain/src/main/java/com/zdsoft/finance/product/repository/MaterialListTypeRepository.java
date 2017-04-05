package com.zdsoft.finance.product.repository;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.product.entity.MaterialListType;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MaterialListTypeRepository.java 
 * @ClassName: MaterialListTypeRepository 
 * @Description: 资料类型
 * @author gufeng 
 * @date 2017年3月16日 下午2:28:55 
 * @version V1.0
 */
public interface MaterialListTypeRepository extends CustomRepository<MaterialListType,String>{
	
	/**
	 * @Title: findByIsDeleted 
	 * @Description: 类型查询
	 * @author gufeng 
	 * @param isDeleted 是否删除
	 * @return 有效资料类型
	 */
	public List<MaterialListType> findByIsDeletedOrderByIdAsc(Boolean isDeleted);

	/**
	 * @Title: findParentMateria
	 * @Description: 查找父级资料
	 * @author liaoguowei
	 * @param
	 * @return java.util.List<com.zdsoft.finance.product.entity.MaterialListType>
	 * @throws
	 */
	@Query(" from MaterialListType m where m.isDeleted = false and m.pId is null ")
	public List<MaterialListType> findParentMateria();

	
}
