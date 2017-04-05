package com.zdsoft.finance.customer.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BlanckList;

/**
 * 黑名单接口
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BlanckListService.java 
 * @ClassName: BlanckListService 
 * @Description: 
 * @author zhangchao 
 * @date 2017年2月6日 上午10:22:33 
 * @version V1.0
 */
public interface BlanckListService extends BaseService<BlanckList>{

    /**
     * @Title: findByCredentialNo 
     * @Description: 查询所有的黑名单
     * @author dengyy 
     * @throws BusinessException
     * @return
     */
    public List<BlanckList> findByALL() throws BusinessException ;
    
    /**
     * 
     * 判断案件中是否有黑名单客户
     * 
     * @param caseApplyId 案件id  
     * @return 黑名单 true 否则false
     * @throws BusinessException
     * @throws Exception 
     */
    public Boolean checkBlankList(String caseApplyId) throws BusinessException, Exception ;

    /**
     * @Title: findByCredentialNo 
     * @Description: 根据证件号获取黑名单
     * @author jincheng 
     * @param credentialNo
     * @return
     */
	public BlanckList findByCredentialNo(String credentialNo);
	
	/**
	 * 
	 * @Title: findByCredentiaTypeAndCredentialNo 
	 * @Description: 根据证件类型与证件号获取黑名单
	 * @author yangjia 
	 * @param credentiaType
	 * @param credentialNo
	 * @return
	 */
	public List<BlanckList> findByCredentiaTypeAndCredentialNo(String credentiaType,String credentialNo);
}
