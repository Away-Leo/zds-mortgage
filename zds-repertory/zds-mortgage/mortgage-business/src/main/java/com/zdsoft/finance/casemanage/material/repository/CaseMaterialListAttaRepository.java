package com.zdsoft.finance.casemanage.material.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.casemanage.material.entity.CaseMaterialListAtta;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseMaterialListAttaRepository.java
 * @Package:com.zdsoft.finance.casemanage.material.repository
 * @Description:案件资料清单附件dao
 * @author: gonght
 * @date:2017年1月15日 下午2:46:04
 * @version:v1.0
 */
public interface CaseMaterialListAttaRepository extends CustomRepository<CaseMaterialListAtta, String> {

	/**
	 * 通过案件资料清单类型id，删除其下的附件内容
	 * 
	 * @param caseMaterialListId
	 *            案件资料清单类别id
	 * @return
	 */
	@Modifying
	@Transactional
	@Query("delete from CaseMaterialListAtta cmla where cmla.caseMaterialList.id = ?1")
	int deleteByCaseMaterialListId(String caseMaterialListId);

	/**
	 * 通过案件资料清单类型id+附件对应的业务表单id，删除其下的附件内容
	 * 
	 * @param caseMaterialListId
	 *            案件资料清单类别id
	 * @param businessId
	 *            业务表单id
	 * @return
	 */
	@Modifying
	@Transactional
	@Query("delete from CaseMaterialListAtta cmla where cmla.caseMaterialList.id = ?1 and cmla.businessId=?2")
	int deleteByCaseMaterialListIdAndBusinessId(String caseMaterialListId, String businessId);
}
