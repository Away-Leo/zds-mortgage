package com.zdsoft.finance.businesssetting.service.impl;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.businesssetting.entity.Bank;
import com.zdsoft.finance.businesssetting.repository.BankRepository;
import com.zdsoft.finance.businesssetting.service.BankService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.ObjectProperUtil;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title BankServiceImpl.java
 * @className BankServiceImpl
 * @description 银行代码设置自定义操作库
 * @author LiaoGuoWei
 * @create 2017/2/15 15:48
 * @version V1.0
 **/
@Service("BankService")
public class BankServiceImpl extends BaseServiceImpl<Bank, CustomRepository<Bank, String>>
implements BankService {
	@Autowired
	private BankRepository bankRepository;

	@Autowired
	private CED CED;
	@Override
	@Transactional
	public void deleteBank(String id) throws BusinessException{
		Bank bank=this.findBankById(id);
		bankRepository.logicDelete(bank);
	}
	@Override
	public Bank findByCode(String code) throws BusinessException{
		if(ObjectHelper.isNotEmpty(code)){
			Bank sourceData=this.bankRepository.findByBankCode(code);
			return sourceData;
		}else{
			throw new BusinessException("10010004","未传入相关参数");
		}

	}

	@Override
	public Bank findBankById(String id) throws BusinessException {
		if(ObjectHelper.isNotEmpty(id)){
			Bank bank=this.bankRepository.findOne(id);
			if(ObjectHelper.isNotEmpty(bank)){
				return bank;
			}else{
				throw new BusinessException("10010002","根据参数未找到相应数据");
			}
		}else{
			throw new BusinessException("10010004","未传入相关参数");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Bank saveBank(Bank bank) throws Exception {
		if(ObjectHelper.isNotEmpty(bank)){
			if(ObjectHelper.isEmpty(bank.getId())){
				bank.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				bank.setCreateBy(CED.getLoginUser().getEmpCd());
				return this.bankRepository.saveEntity(bank);
			}else{
				throw new BusinessException("10010003","传入参数有误");
			}
		}else{
			throw new BusinessException("10010004","未传入相关参数");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Bank updateBank(Bank bank) throws Exception {
		if(ObjectHelper.isNotEmpty(bank)){
			if(ObjectHelper.isNotEmpty(bank.getId())){
				//查找旧数据
				Bank oldData=this.findBankById(bank.getId());
				//将更改的数据进行赋值
				oldData=(Bank) ObjectProperUtil.compareAndValue(bank,oldData,false,null);
				oldData.setUpdateBy(CED.getLoginUser().getEmpCd());
				oldData.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
				//执行更新操作
				oldData=this.bankRepository.updateEntity(oldData);
				return oldData;
			}else{
				throw new BusinessException("10010003","传入参数有误");
			}
		}else{
			throw new BusinessException("10010004","未传入相关参数");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Bank saveOrUpdateBank(Bank bank) throws Exception {
		if(ObjectHelper.isNotEmpty(bank)){
			if(ObjectHelper.isNotEmpty(bank.getId())){
				return this.updateBank(bank);
			}else{
				return this.saveBank(bank);
			}
		}else{
			throw new BusinessException("10010004","未传入相关参数");
		}
	}

	@Override
	public List<Bank> findBankWithNameByLike(String bankName) throws BusinessException {
		if(ObjectHelper.isNotEmpty(bankName)){
			List<Bank> bankList=this.bankRepository.findByBankNameLike(bankName);
			if(ObjectHelper.isNotEmpty(bankList)&&bankList.size()>0){
				return bankList;
			}else{
				throw new BusinessException("10010002","根据参数未找到相关数据");
			}
		}else{
			throw new BusinessException("10010004","未传入相关参数");
		}
	}
	
	@Override
	public List<Bank> findAllBank() {
		return bankRepository.findAllBank();
	}

	@Override
	public Bank findByCodeAndName(Bank bank) throws BusinessException{
		if(ObjectHelper.isNotEmpty(bank)&&ObjectHelper.isNotEmpty(bank.getBankCode())&&ObjectHelper.isNotEmpty(bank.getBankName())){
			return this.bankRepository.findByNameAndCode(bank.getBankName(),bank.getBankCode());
		}else{
			throw new BusinessException("10010004","未传入相关参数，按照名称和编号查找银行出错");
		}
	}
}
