package com.zdsoft.finance.businesssetting.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.businesssetting.entity.AuthGrade;
import com.zdsoft.finance.businesssetting.entity.InstitutionGrade;
import com.zdsoft.finance.businesssetting.entity.OrgAuthConn;
import com.zdsoft.finance.businesssetting.entity.OrgIntermediate;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title InstitutionGradeService.java
 * @className InstitutionGradeService
 * @description 机构评级service
 * @author LiaoGuoWei
 * @create 2017/3/3 15:03
 * @version V1.0
 **/
public interface InstitutionGradeService extends BaseService<InstitutionGrade> {

    /**
     * @Title: findById
     * @Description: 按照ID查找机构评级
     * @author liaoguowei
     * @param id 机构评级ID
     * @return com.zdsoft.finance.businesssetting.entity.InstitutionGrade
     * @throws BusinessException
     */
    public InstitutionGrade findById(String id) throws BusinessException;

    /**
     * @Title: findByCondition
     * @Description: 按照条件查找
     * @author liaoguowei
     * @param institutionCode 机构ID
     * @return java.util.List<com.zdsoft.finance.businesssetting.entity.InstitutionGrade>
     * @throws Exception
     */
    public List<InstitutionGrade> findByCondition(String institutionCode) throws Exception;

    /**
     * @Title: saveInstitution
     * @Description: 保存机构评级
     * @author liaoguowei
     * @param institutionGrade 机构ID
     * @return java.util.List<com.zdsoft.finance.businesssetting.entity.InstitutionGrade>
     * @throws Exception
     */
    public List<InstitutionGrade> saveInstitution(InstitutionGrade institutionGrade) throws Exception;

    /**
     * @Title: saveWithOrgAuthGrade
     * @Description: 通过关系表保存历史数据
     * @author liaoguowei
     * @param orgAuthConn 关系表
     * @return java.util.List<com.zdsoft.finance.businesssetting.entity.InstitutionGrade>
     * @throws Exception
     */
    public List<InstitutionGrade> saveWithOrgAuthGrade(OrgAuthConn orgAuthConn) throws Exception;


    /**
     * @Title: updateInstitution
     * @Description: 更新机构评级
     * @author liaoguowei
     * @param institutionGrade 机构评级
     * @return java.util.List<com.zdsoft.finance.businesssetting.entity.InstitutionGrade>
     * @throws
     */
    public List<InstitutionGrade> updateInstitution(InstitutionGrade institutionGrade) throws Exception;


    /**
     * @Title: saveOrUpdateInstitution
     * @Description: 更新或保存
     * @author liaoguowei
     * @param institutionGrade 机构评级
     * @return java.util.List<com.zdsoft.finance.businesssetting.entity.InstitutionGrade>
     * @throws
     */
    public List<InstitutionGrade> saveOrUpdateInstitution(InstitutionGrade institutionGrade) throws Exception;

    /**
     * @Title: findPageByCondition
     * @Description: 分页查询
     * @author liaoguowei
     * @param pageable 分页参数
     * @param institutionCode 机构ID
     * @return com.zdsoft.framework.core.common.page.Page<java.util.Map<java.lang.String,java.lang.Object>>
     * @throws Exception
     */
    public Page<Map<String,Object>> findPageByCondition(Pageable pageable, String institutionCode) throws Exception;


    /**
     * @Title: findListByCondition
     * @Description: 按照条件查找集合
     * @author liaoguowei
     * @param institutionCode 机构ID
     * @return java.util.List<com.zdsoft.finance.businesssetting.entity.InstitutionGrade>
     * @throws BusinessException
     */
    public List<InstitutionGrade> findListByCondition(String institutionCode) throws BusinessException;

    /**
     * @Title: writeDataToOrg
     * @Description: 写入数据到机构中间表
     * @author liaoguowei
     * @param
     * @return void
     * @throws Exception
     */
    public void writeDataToOrg() throws Exception;


    /**
     * @Title: findPageList
     * @Description: 查找分页数据
     * @author liaoguowei
     * @param pageable 分页参数
     * @param institutionCode 机构ID
     * @return com.zdsoft.framework.core.common.page.Page<com.zdsoft.finance.businesssetting.entity.InstitutionGrade>
     * @throws  BusinessException
     */
    public Page<InstitutionGrade> findPageList(Pageable pageable, String institutionCode) throws BusinessException;

    /**
     * @Title: findAllOrg
     * @Description: 查找机构
     * @author liaoguowei
     * @param
     * @return java.util.List<com.zdsoft.finance.businesssetting.entity.OrgIntermediate>
     * @throws
     */
    List<OrgIntermediate> findAllOrg();

    /**
     * @Title: findInstitutionAuthPage
     * @Description: 机构授权列表分页查询
     * @author liaoguowei
     * @param pageable 分页参数
     * @param authGrade 机构评级
     * @return com.zdsoft.framework.core.common.page.Page<com.zdsoft.finance.businesssetting.entity.AuthGrade>
     * @throws
     */
    public Page<AuthGrade> findInstitutionAuthPage(Pageable pageable, AuthGrade authGrade) throws Exception;


    /**
     * @Title: findAllHander
     * @Description: 查找所有的有效的机构评级
     * @author liaoguowei
     * @param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @throws Exception
     */
    public List<Map<String,Object>> findAllHander() throws Exception;

}
