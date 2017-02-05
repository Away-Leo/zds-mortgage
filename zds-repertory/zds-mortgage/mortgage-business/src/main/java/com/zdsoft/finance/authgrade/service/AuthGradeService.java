package com.zdsoft.finance.authgrade.service;

import com.zdsoft.finance.authgrade.entity.AuthGrade;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

import java.util.List;

/**
 * 授权等级设定service
 * @author LiaoGuoWei
 * @create 2017-01-04 16:41
 **/
public interface AuthGradeService extends BaseService<AuthGrade> {

    /**
     * 按照查询条件查询
     * @param pageable
     * @param productParentId
     * @param productChildId
     * @param gradeCode
     * @return
     */
    public Page<AuthGrade> findByCondition(Pageable pageable,String productParentId,String productChildId,String gradeCode);

    /**
     * 保存
     * @param authGrade
     * @return
     * @throws BusinessException
     */
    public AuthGrade saveAuthGrade(AuthGrade authGrade) throws BusinessException;

    /**
     * 更新
     * @param authGrade
     * @return
     * @throws BusinessException
     */
    public AuthGrade updateAuthGrade(AuthGrade authGrade) throws BusinessException;

    /**
     * 保存或更新
     * @param authGrade
     * @return
     * @throws BusinessException
     */
    public AuthGrade saveOrUpdateAuthGrade(AuthGrade authGrade) throws BusinessException;


    /**
     * 按照ID查找数据
     * @param id
     * @return
     * @throws BusinessException
     */
    public AuthGrade findById(String id) throws BusinessException;

    /**
     * 按照等级编号查找
     * @param gradeCode
     * @return
     * @throws BusinessException
     */
    public List<AuthGrade> findByGradeCode(String gradeCode) throws BusinessException;
}
