package com.zdsoft.finance.businesssetting.service.impl;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.businesssetting.entity.LitigationData;
import com.zdsoft.finance.businesssetting.repository.LitigationDataRepository;
import com.zdsoft.finance.businesssetting.service.LitigationDataService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.ObjectProperUtil;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title LitigationDataServiceImpl.java
 * @className LitigationDataServiceImpl
 * @description 诉讼资料配置service
 * @author LiaoGuoWei
 * @create 2017/3/3 14:49
 * @version V1.0
 **/
@Service("litigationDataService")
public class LitigationDataServiceImpl extends BaseServiceImpl<LitigationData, CustomRepository<LitigationData, String>>
implements LitigationDataService {
	@Autowired
	private LitigationDataRepository litigationDataRepository;

	@Autowired
	private CED CED;


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteLitigationData(String id) {
		LitigationData litigationData=this.litigationDataRepository.findOne(id);
		litigationDataRepository.logicDelete(litigationData);
	}

	@Override
	public LitigationData findById(String id) throws BusinessException {
		if(ObjectHelper.isNotEmpty(id)){
			LitigationData litigationData=this.litigationDataRepository.findOne(id);
			if(ObjectHelper.isNotEmpty(litigationData)){
				return litigationData;
			}else{
				throw new BusinessException("10010002","根据参数未找到相应数据");
			}
		}else{
			throw new BusinessException("10010004","未传入相关参数");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public LitigationData saveLitigationData(LitigationData litiData) throws Exception {
		if(ObjectHelper.isNotEmpty(litiData)){
			if(ObjectHelper.isEmpty(litiData.getId())){
				litiData.setCreateBy(CED.getLoginUser().getEmpCd());
				litiData.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				return this.litigationDataRepository.saveEntity(litiData);
			}else{
				throw new BusinessException("10010003","传入参数有误");
			}
		}else{
			throw new BusinessException("10010004","未传入相关参数");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public LitigationData updateLitigationData(LitigationData litiData) throws Exception {
		if(ObjectHelper.isNotEmpty(litiData)){
			if(ObjectHelper.isNotEmpty(litiData.getId())){
				LitigationData oldData=this.findById(litiData.getId());
				//进行比对并对更改过的数据进行赋值
				oldData=(LitigationData) ObjectProperUtil.compareAndValue(litiData,oldData,false,new String[]{"remark"});
				oldData.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
				oldData.setUpdateBy(CED.getLoginUser().getEmpCd());
				//执行更新操作
				oldData=this.litigationDataRepository.updateEntity(oldData);
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
	public LitigationData saveOrUpdateLitiData(LitigationData litiData) throws Exception {
		if(ObjectHelper.isNotEmpty(litiData)){
			if(ObjectHelper.isEmpty(litiData.getId())){
				return this.saveLitigationData(litiData);
			}else{
				return this.updateLitigationData(litiData);
			}
		}else{
			throw new BusinessException("10010004","未传入相关参数");
		}
	}

	@Override
	public Page<LitigationData> findLitigationDataByCondition(Pageable pageable,LitigationData litigationData) throws BusinessException {
		if(ObjectHelper.isNotEmpty(pageable)&&ObjectHelper.isNotEmpty(litigationData)){
			return this.litigationDataRepository.findLitigationDataByCondition(pageable, litigationData);
		}else{
			throw new BusinessException("10010004","未传入相关参数");
		}
	}
}
