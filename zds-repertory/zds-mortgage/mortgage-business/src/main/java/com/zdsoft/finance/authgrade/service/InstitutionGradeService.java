package com.zdsoft.finance.authgrade.service;

import com.zdsoft.finance.authgrade.entity.AuthGrade;
import com.zdsoft.finance.authgrade.entity.InstitutionGrade;
import com.zdsoft.finance.authgrade.entity.OrgAuthConn;
import com.zdsoft.finance.authgrade.entity.OrgIntermediate;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 机构评级service
 * @author LiaoGuoWei
 * @create 2017-01-06 15:18
 **/
public interface InstitutionGradeService extends BaseService<InstitutionGrade> {

    /**
     * 按照ID查找机构评级
     * @param id
     * @return
     * @throws BusinessException
     */
    public InstitutionGrade findById(String id) throws BusinessException;

    /**
     * 按照条件查找
     * @param institutionCode
     * @return
     * @throws BusinessException
     */
    public List<InstitutionGrade> findByCondition(String institutionCode) throws Exception;

    /**
     * 保存机构评级
     * @param institutionGrade
     * @return
     * @throws BusinessException
     */
    public List<InstitutionGrade> saveInstitution(InstitutionGrade institutionGrade) throws Exception;
    /**
     * 通过关系表保存历史数据
     * @param orgAuthConn
     * @return
     * @throws BusinessException
     */
    public List<InstitutionGrade> saveWithOrgAuthGrade(OrgAuthConn orgAuthConn) throws Exception;

    /**
     * 更新机构评级
     * @param institutionGrade
     * @return
     * @throws BusinessException
     */
    public List<InstitutionGrade> updateInstitution(InstitutionGrade institutionGrade) throws Exception;

    /**
     * 更新或保存
     * @param institutionGrade
     * @return
     * @throws BusinessException
     */
    public List<InstitutionGrade> saveOrUpdateInstitution(InstitutionGrade institutionGrade) throws Exception;

    /**
     * 分页查询
     * @param institutionCode
     * @return
     * @throws BusinessException
     */
    public Page<Map<String,Object>> findPageByCondition(Pageable pageable, String institutionCode) throws Exception;

    /**
     * 按照条件查找集合
     * @param institutionCode
     * @return
     * @throws BusinessException
     */
    public List<InstitutionGrade> findListByCondition(String institutionCode) throws BusinessException;

    /**
     * 写入数据到机构中间表
     * @throws BusinessException
     */
    public void writeDataToOrg() throws Exception;

    /**
     * 查找分页数据
     * @param institutionCode
     * @return
     * @throws BusinessException
     */
    public Page<InstitutionGrade> findPageList(Pageable pageable, String institutionCode) throws BusinessException;

    /**
     * 查找机构
     * @return
     */
    List<OrgIntermediate> findAllOrg();

    /**
     * 机构授权列表分页查询
     * @param authGrade
     * @return
     */
    public Page<AuthGrade> findInstitutionAuthPage(Pageable pageable,AuthGrade authGrade) throws Exception;

    /**
     * 查找所有的有效的机构评级
     * @return
     */
    public List<Map<String,Object>> findAllHander() throws Exception;
}
