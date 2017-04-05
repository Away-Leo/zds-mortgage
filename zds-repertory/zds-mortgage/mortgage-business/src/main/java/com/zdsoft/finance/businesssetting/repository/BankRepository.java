package com.zdsoft.finance.businesssetting.repository;

import com.zdsoft.finance.businesssetting.entity.Bank;
import com.zdsoft.finance.common.base.CustomRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title BankRepository.java
 * @className BankRepository
 * @description 银行代码设置 自定义操作库
 * @author LiaoGuoWei
 * @create 2017/2/16 11:28
 * @version V1.0
 **/
public interface BankRepository extends CustomRepository<Bank, String>{

	/**
	  * @Title: findByBankCode
	  * @Description: 通过银行编码查找
	  * @author liaoguowei
	  * @param bankCode 银行编号
	  * @return List<Bank>
	  * @throws
	*/
	public Bank findByBankCode(String bankCode);

	/**
	  * @Title: findByBankNameLike
	  * @Description: 通过银行名称模糊匹配
	  * @author liaoguowei
	  * @param bankName 银行名称
	  * @return List<Bank>
	  * @throws
	*/
	@Query(" from Bank t where t.bankName like CONCAT('%',:bankName,'%') and t.isDeleted = false")
	public List<Bank> findByBankNameLike(@Param("bankName") String bankName);

	/**
	  * @Title: findByIsDeleted
	  * @Description: 按照是否删除进行查找
	  * @author liaoguowei
	  * @param isDeleted 是否删除
	  * @return List<Bank>
	  * @throws
	*/
	public List<Bank> findByIsDeleted(boolean isDeleted);
	
	
	/**
	 * 
	 * @Title: findAllBank
	 * @Description: 查询所有有效的银行信息
	 * @author liuwei
	 * @return 银行信息集合
	 */
	@Query(" from Bank where isDeleted = false ")
	public List<Bank> findAllBank();

	/**
	 * @Title: findByNameAndCode
	 * @Description: 按照名称和编号查找
	 * @author liaoguowei
	 * @param bankName 银行名称
	 * @param bankCode 银行编号
	 * @return com.zdsoft.finance.businesssetting.entity.Bank
	 * @throws
	 */
	@Query(" from Bank b where b.isDeleted = false and b.bankName = :bankName and b.bankCode = :bankCode ")
	public Bank findByNameAndCode(@Param("bankName")String bankName,@Param("bankCode")String bankCode);

}
