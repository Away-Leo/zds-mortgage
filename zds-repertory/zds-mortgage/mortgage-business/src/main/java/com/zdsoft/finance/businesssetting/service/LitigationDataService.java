package com.zdsoft.finance.businesssetting.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.businesssetting.entity.LitigationData;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title LitigationDataService.java
 * @className LitigationDataService
 * @description 诉讼资料配置service
 * @author LiaoGuoWei
 * @create 2017/2/15 9:59
 * @version V1.0
 **/
public interface LitigationDataService extends BaseService<LitigationData>{

	/**
	 * @Title: deleteLitigationData
	 * @Description: 逻辑删除诉讼资料配置
	 * @author liaoguowei
	 * @param id 诉讼资料ID
	 * @return void
	 * @throws
	 */
	public void deleteLitigationData(String id);


	/**
	 * @Title: findById
	 * @Description: 按照ID查找数据
	 * @author liaoguowei
	 * @param id 诉讼资料ID
	 * @return com.zdsoft.finance.businesssetting.entity.LitigationData
	 * @throws BusinessException
	 */
	public LitigationData findById(String id) throws BusinessException;


	/**
	 * @Title: saveLitigationData
	 * @Description: 保存诉讼资料配置
	 * @author liaoguowei
	 * @param litiData 诉讼资料
	 * @return com.zdsoft.finance.businesssetting.entity.LitigationData
	 * @throws Exception
	 */
	public LitigationData saveLitigationData(LitigationData litiData) throws Exception;


	/**
	 * @Title: updateLitigationData
	 * @Description: 更新诉讼资料配置
	 * @author liaoguowei
	 * @param litiData 诉讼资料
	 * @return com.zdsoft.finance.businesssetting.entity.LitigationData
	 * @throws Exception
	 */
	public LitigationData updateLitigationData(LitigationData litiData) throws Exception;

	/**
	 * @Title: saveOrUpdateLitiData
	 * @Description: 更新或保存诉讼资料配置
	 * @author liaoguowei
	 * @param litiData 诉讼资料
	 * @return com.zdsoft.finance.businesssetting.entity.LitigationData
	 * @throws Exception
	 */
	public LitigationData saveOrUpdateLitiData(LitigationData litiData) throws Exception;

	/**
	 * @Title: findLitigationDataByCondition
	 * @Description: 按照条件查找
	 * @author liaoguowei
	 * @param litigationData 查询条件
	 * @param page 分页参数
	 * @return com.zdsoft.framework.core.common.page.Page<com.zdsoft.finance.businesssetting.entity.LitigationData>
	 * @throws BusinessException
	 */
	public Page<LitigationData> findLitigationDataByCondition(Pageable page,LitigationData litigationData) throws BusinessException;


}
