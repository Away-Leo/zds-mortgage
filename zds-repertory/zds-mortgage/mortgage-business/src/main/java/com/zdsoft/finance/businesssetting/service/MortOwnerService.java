package com.zdsoft.finance.businesssetting.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.businesssetting.entity.MortOwner;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title MortOwnerService.java
 * @className MortOwnerService
 * @description 抵押权人service
 * @author LiaoGuoWei
 * @create 2017/3/3 15:09
 * @version V1.0
 **/
public interface MortOwnerService extends BaseService<MortOwner> {


    /**
     * @Title: findById
     * @Description: 按照ID查找
     * @author liaoguowei
     * @param id 抵押权人ID
     * @return com.zdsoft.finance.businesssetting.entity.MortOwner
     * @throws BusinessException
     */
    public MortOwner findById(String id) throws BusinessException;


    /**
     * @Title: findByCondition
     * @Description: 按照条件查询分页数据
     * @author liaoguowei
     * @param ownerName 抵押权人名称
     * @param beOrgCode 所属机构
     * @param pageable 分页参数
     * @return com.zdsoft.framework.core.common.page.Page<com.zdsoft.finance.businesssetting.entity.MortOwner>
     * @throws
     */
    public Page<MortOwner> findByCondition(String ownerName, String beOrgCode, Pageable pageable);


    /**
     * @Title: saveMortOwner
     * @Description: 保存抵押权人
     * @author liaoguowei
     * @param mortOwner 抵押权人
     * @return com.zdsoft.finance.businesssetting.entity.MortOwner
     * @throws BusinessException
     */
    public MortOwner saveMortOwner(MortOwner mortOwner) throws BusinessException;


    /**
     * @Title: updateMortOwner
     * @Description: 更新抵押权人
     * @author liaoguowei
     * @param mortOwner 抵押权人
     * @return com.zdsoft.finance.businesssetting.entity.MortOwner
     * @throws BusinessException
     */
    public MortOwner updateMortOwner(MortOwner mortOwner) throws BusinessException;


    /**
     * @Title: saveOrUpdateMortOwner
     * @Description: 更新或保存抵押权人
     * @author liaoguowei
     * @param mortOwner 抵押权人
     * @return com.zdsoft.finance.businesssetting.entity.MortOwner
     * @throws  BusinessException
     */
    public MortOwner saveOrUpdateMortOwner(MortOwner mortOwner) throws BusinessException;


    /**
     * @Title: deleteMortOwner
     * @Description: 逻辑删除抵押权人
     * @author liaoguowei
     * @param id 抵押权人ID
     * @return com.zdsoft.finance.businesssetting.entity.MortOwner
     * @throws BusinessException
     */
    public MortOwner deleteMortOwner(String id) throws BusinessException;


    /**
     * @Title: findByOrgCode
     * @Description: 根据机构编号查询已启用的抵押权人
     * @author liaoguowei
     * @param orgCode 所属机构ID
     * @param ownerTypeCode 抵押权人编号
     * @return java.util.List<com.zdsoft.finance.businesssetting.entity.MortOwner>
     * @throws BusinessException
     */
    public List<MortOwner> findByOrgCode(String orgCode, String ownerTypeCode) throws BusinessException;

    /**
     * @Title: findBeOrgCodeAndOwnerTypeCodeAndOwnerName
     * @Description: 通过所属机构、类别、名称查找
     * @author liaoguowei
     * @param mortOwner 查询条件
     * @return java.util.List<com.zdsoft.finance.businesssetting.entity.MortOwner>
     * @throws BusinessException 业务异常
     */
    public List<MortOwner> findBeOrgCodeAndOwnerTypeCodeAndOwnerName(MortOwner mortOwner) throws BusinessException;

 }

