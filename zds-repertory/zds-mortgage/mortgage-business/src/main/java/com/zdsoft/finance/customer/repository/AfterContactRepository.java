package com.zdsoft.finance.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.AfterContact;

/**
 * 贷后联系方式
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterContactRepository.java 
 * @ClassName: AfterContactRepository 
 * @Description: 
 * @author zhangchao 
 * @date 2017年2月6日 上午10:17:31 
 * @version V1.0
 */
public interface AfterContactRepository extends CustomRepository<AfterContact, String>{
	
	/**
	 * 
	 * @Title: queryContactList 
	 * @Description: 通过客户id查询客户联系方式、创建时间升序排序
	 * @author xj 
	 * @param customerId
	 * @return
	 */
	@Query("from AfterContact where customerId = :customerId  order by createTime,updateTime")
	public List<AfterContact> queryContactList(@Param("customerId")String customerId);
	
	/**
	 * @Title: findByCustomerId 
	 * @Description: 根据用户id查找联系方式
	 * @author huangwei 
	 * @param customerId
	 * @return
	 */
	public List<AfterContact> findByCustomerId(String customerId);
}
