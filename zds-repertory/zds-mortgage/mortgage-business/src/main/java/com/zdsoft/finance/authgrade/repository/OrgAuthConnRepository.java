package com.zdsoft.finance.authgrade.repository;

import com.zdsoft.finance.authgrade.entity.OrgAuthConn;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 机构与授权等级设定关系表操作库
 * @author LiaoGuoWei
 * @create 2017-01-22 20:31
 **/
public interface OrgAuthConnRepository extends CustomRepository<OrgAuthConn,String> {

    /**
     * 按照机构ID查找
     * @param orgIntermediateId
     * @return
     */
    public OrgAuthConn findByOrgIntermediateId(String orgIntermediateId);
}
