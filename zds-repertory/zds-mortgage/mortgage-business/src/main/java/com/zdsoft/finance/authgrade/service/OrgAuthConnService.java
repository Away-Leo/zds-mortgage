package com.zdsoft.finance.authgrade.service;

import com.zdsoft.finance.authgrade.entity.OrgAuthConn;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;

/**
 * 机构与机构评级关系service
 * @author LiaoGuoWei
 * @create 2017-01-22 20:33
 **/
public interface OrgAuthConnService extends BaseService<OrgAuthConn> {

    /**
     * 按照ID查找
     * @param id
     * @return
     * @throws BusinessException
     */
    public OrgAuthConn findById(String id) throws BusinessException;

    /**
     * 保存
     * @param orgAuthConn
     * @return
     * @throws BusinessException
     */
    public OrgAuthConn saveOrgAuthConn(OrgAuthConn orgAuthConn) throws Exception;

    /**
     * 更新
     * @param orgAuthConn
     * @return
     * @throws BusinessException
     */
    public OrgAuthConn updateOrgAuthConn(OrgAuthConn orgAuthConn) throws Exception;


    /**
     * 跟新或保存
     * @param orgAuthConn
     * @return
     * @throws BusinessException
     */
    public OrgAuthConn saveOrUpdateOrgAuthConn(OrgAuthConn orgAuthConn) throws Exception;

    /**
     * 按照机构查询
     * @param orgCode
     * @return
     * @throws Exception
     */
    public OrgAuthConn findByOrgCode(String orgCode) throws Exception;
}
