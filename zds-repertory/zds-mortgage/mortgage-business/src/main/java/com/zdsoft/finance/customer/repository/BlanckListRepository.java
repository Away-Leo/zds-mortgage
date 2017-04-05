package com.zdsoft.finance.customer.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.BlanckList;

/**
 * 黑名单
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BlanckListRepository.java 
 * @ClassName: BlanckListRepository 
 * @Description: 
 * @author zhangchao 
 * @date 2017年2月6日 上午10:17:17 
 * @version V1.0
 */
public interface BlanckListRepository extends CustomRepository<BlanckList, String> {

	/**
	 * @Title: findByCredentialNo 
	 * @Description: 根据证件号获取黑名单
	 * @author jincheng 
	 * @param credentialNo
	 * @return
	 */
	BlanckList findByCredentialNo(String credentialNo);
	
	/**
	 * 
	 * @Title: findByCredentiaTypeAndCredentialNo 
	 * @Description: 根据证件类型与证件号获取黑名单
	 * @author yangjia 
	 * @param credentiaType
	 * @param credentialNo
	 * @return
	 */
	public List<BlanckList> findByCredentiaTypeAndCredentialNoAndIsDeleted(String credentiaType,String credentialNo,Boolean isDeleted);

}
