package com.zdsoft.finance.businesssetting.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.businesssetting.entity.Bank;
import com.zdsoft.finance.common.exception.BusinessException;

import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title BankService.java
 * @className BankService
 * @description 银行代码设置service
 * @author LiaoGuoWei
 * @create 2017/2/15 15:41
 * @version V1.0
 **/
public interface BankService extends BaseService<Bank>{

	/**
	  * @Title: deleteBank
	  * @Description: 逻辑删除银行代码设置
	  * @author liaoguowei
	  * @param id 银行代码id
	  * @return void
	  * @throws BusinessException
	*/
	public void deleteBank(String id) throws BusinessException;

	/**
	 * @Title: findByCode
	 * @Description: 通过code查询
	 * @author liaoguowei
	 * @param code 银行编号
	 * @return com.zdsoft.finance.businesssetting.entity.Bank
	 * @throws BusinessException
	 */
	public Bank findByCode(String code) throws BusinessException;

	/**
	 * @Title: findBankById
	 * @Description: 按照ID查找
	 * @author liaoguowei
	 * @param id 银行ID
	 * @return com.zdsoft.finance.businesssetting.entity.Bank
	 * @throws BusinessException
	 */
	public Bank findBankById(String id) throws BusinessException;


	/**
	 * @Title: saveBank
	 * @Description: 保存银行代码设置
	 * @author liaoguowei
	 * @param bank 银行
	 * @return com.zdsoft.finance.businesssetting.entity.Bank
	 * @throws Exception
	 */
	public Bank saveBank(Bank bank) throws Exception;

	/**
	 * @Title: updateBank
	 * @Description: 更新银行代码设置
	 * @author liaoguowei
	 * @param bank 银行
	 * @return com.zdsoft.finance.businesssetting.entity.Bank
	 * @throws Exception
	 */
	public Bank updateBank(Bank bank) throws Exception;

	/**
	 * @Title: saveOrUpdateBank
	 * @Description: 更新或保存银行代码设置
	 * @author liaoguowei
	 * @param bank 银行
	 * @return com.zdsoft.finance.businesssetting.entity.Bank
	 * @throws BusinessException
	 */
	public Bank saveOrUpdateBank(Bank bank) throws Exception;


	/**
	 * @Title: findBankWithNameByLike
	 * @Description: 按照银行名称查找模糊查询银行
	 * @author liaoguowei
	 * @param bankName 银行名称
	 * @return java.util.List<com.zdsoft.finance.businesssetting.entity.Bank>
	 * @throws
	 */
	public List<Bank> findBankWithNameByLike(String bankName) throws BusinessException;

	/**
	 * 
	 * @Title: findAllBank
	 * @Description: 获取所有银行信息
	 * @author liuwei
	 * @return 银行集合
	 */
	public List<Bank> findAllBank();

	/**
	 * @Title: findByCodeAndName
	 * @Description: 按照名称和编号查找
	 * @author liaoguowei
	 * @param bank 查询条件
	 * @return com.zdsoft.finance.businesssetting.entity.Bank
	 * @throws BusinessException
	 */
	public Bank findByCodeAndName(Bank bank) throws BusinessException;

}
