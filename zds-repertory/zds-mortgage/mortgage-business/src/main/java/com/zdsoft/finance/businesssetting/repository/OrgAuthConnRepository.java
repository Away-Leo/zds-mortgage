package com.zdsoft.finance.businesssetting.repository;

import com.zdsoft.finance.businesssetting.entity.OrgAuthConn;
import com.zdsoft.finance.common.base.CustomRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title OrgAuthConnRepository.java
 * @className OrgAuthConnRepository
 * @description 机构与授权等级设定关系表操作库
 * @author LiaoGuoWei
 * @create 2017/3/3 14:42
 * @version V1.0
 **/
public interface OrgAuthConnRepository extends CustomRepository<OrgAuthConn,String> {

    /**
     * @Title: findByOrgIntermediateId
     * @Description: 按照机构ID查找
     * @author liaoguowei
     * @param orgIntermediateId
     * @return com.zdsoft.finance.businesssetting.entity.OrgAuthConn
     * @throws
     */
    public OrgAuthConn findByOrgIntermediateId(String orgIntermediateId);


    /**
     * @Title: deleteByOrg
     * @Description: 删除关系表中原有机构数据
     * @author liaoguowei
     * @param orgCd
     * @return void
     * @throws
     */
    @Query(" delete OrgAuthConn o where o.orgIntermediateId =:orgCd ")
    @Modifying
    public void deleteByOrg(@Param("orgCd") String orgCd);

}
