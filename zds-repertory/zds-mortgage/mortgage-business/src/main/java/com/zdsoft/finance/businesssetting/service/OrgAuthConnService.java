package com.zdsoft.finance.businesssetting.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.businesssetting.entity.OrgAuthConn;
import com.zdsoft.finance.common.exception.BusinessException;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title OrgAuthConnService.java
 * @className OrgAuthConnService
 * @description 机构与机构评级关系service
 * @author LiaoGuoWei
 * @create 2017/3/3 15:11
 * @version V1.0
 **/
public interface OrgAuthConnService extends BaseService<OrgAuthConn> {

    /**
     * @Title: findById
     * @Description: 按照ID查找
     * @author liaoguowei
     * @param id 关系表ID
     * @return com.zdsoft.finance.businesssetting.entity.OrgAuthConn
     * @throws BusinessException
     */
    public OrgAuthConn findById(String id) throws BusinessException;


    /**
     * @Title: saveOrgAuthConn
     * @Description: 保存
     * @author liaoguowei
     * @param orgAuthConn 关系表
     * @return com.zdsoft.finance.businesssetting.entity.OrgAuthConn
     * @throws Exception
     */
    public OrgAuthConn saveOrgAuthConn(OrgAuthConn orgAuthConn) throws Exception;


    /**
     * @Title: updateOrgAuthConn
     * @Description: 更新
     * @author liaoguowei
     * @param orgAuthConn 关系表
     * @return com.zdsoft.finance.businesssetting.entity.OrgAuthConn
     * @throws Exception
     */
    public OrgAuthConn updateOrgAuthConn(OrgAuthConn orgAuthConn) throws Exception;

    /**
     * @Title: saveOrUpdateOrgAuthConn
     * @Description: 跟新或保存
     * @author liaoguowei
     * @param orgAuthConn 关系表
     * @return com.zdsoft.finance.businesssetting.entity.OrgAuthConn
     * @throws Exception
     */
    public OrgAuthConn saveOrUpdateOrgAuthConn(OrgAuthConn orgAuthConn) throws Exception;


    /**
     * @Title: findByOrgCode
     * @Description: 按照机构查询
     * @author liaoguowei
     * @param orgCode 机构ID
     * @return com.zdsoft.finance.businesssetting.entity.OrgAuthConn
     * @throws Exception
     */
    public OrgAuthConn findByOrgCode(String orgCode) throws Exception;


    /**
     * @Title: deleteByOrgCd
     * @Description: 按照机构编号删除原有的关系数据
     * @author liaoguowei
     * @param orgCd
     * @return void
     * @throws Exception
     */
    public void deleteByOrgCd(String orgCd) throws Exception;

}
