package com.zdsoft.finance.credit.repository;


import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.credit.entity.HmQuery;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HmQueryRepository.java 
 * @ClassName: HmQueryRepository 
 * @Description: 征信查询表
 * @author gufeng 
 * @date 2017年2月20日 下午8:40:51 
 * @version V1.0
 */
public interface HmQueryRepository extends CustomRepository<HmQuery, String> {
	
	/**
	 * @Title: findByQueryCredNumAndObjectIdLike 
	 * @Description: 证件号和业务号查询
	 * @author gufeng 
	 * @param queryCredNum 证件号
	 * @param objectId 业务id
	 * @return 查询结果集
	 */
	public List<HmQuery> findByQueryCredNumAndObjectIdLike(String queryCredNum,String objectId);

	/**
	 * @Title: findByQueryCredNumAndObjectId 
	 * @Description: 证件号和业务号查询
	 * @author gufeng 
	 * @param queryCredNum 证件号
	 * @param objectId 业务id
	 * @return 查询结果集
	 */
	public List<HmQuery> findByQueryCredNumAndObjectId(String queryCredNum, String objectId);
	
	/**
	 * @Title: findByQueryCredNumAndQueryCertTypeOrderByCreateTimeDesc 
	 * @Description: 证件号和证件类型查询并排序
	 * @author gufeng 
	 * @param cardNum 证件号
	 * @param cardType 证件类型
	 * @return 查询出具
	 */
	public List<HmQuery> findByQueryCredNumAndQueryCertTypeOrderByCreateTimeDesc(String queryCredNum, String queryCertType);

	/**
	 * @Title: findByObjectIdLikeOrderByObjectId 
	 * @Description: ObjectId模糊查询
	 * @author gufeng 
	 * @param objectId 编号
	 * @return 数据
	 */
	public List<HmQuery> findByObjectIdLikeOrderByObjectIdDesc(String objectId);

	
}
