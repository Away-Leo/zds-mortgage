package com.zdsoft.finance.businesssetting.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.businesssetting.entity.AuthGrade;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

import java.math.BigDecimal;
import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title AuthGradeService.java
 * @className AuthGradeService
 * @description 授权等级设定service
 * @author LiaoGuoWei
 * @create 2017/3/3 14:51
 * @version V1.0
 **/
public interface AuthGradeService extends BaseService<AuthGrade> {

    /**
      * @Title: findByCondition
      * @Description: 按照查询条件查询
      * @author liaoguowei
      * @param pageable 分页参数
      * @param productParentId 父产品ID
      * @param productChildId 子产品ID
      * @param gradeCode 等级编号
      * @return Page<AuthGrade>
      * @throws
    */
    public Page<AuthGrade> findByCondition(Pageable pageable, String productParentId, String productChildId, String gradeCode);

    /**
      * @Title: saveAuthGrade
      * @Description: 保存
      * @author liaoguowei 
      * @param authGrade 授权等级
      * @return AuthGrade 授权等级
      * @throws BusinessException
    */
    public AuthGrade saveAuthGrade(AuthGrade authGrade) throws BusinessException;

    /**
      * @Title: updateAuthGrade
      * @Description: 更新
      * @author liaoguowei
      * @param authGrade 授权等级
      * @return AuthGrade 授权等级
      * @throws BusinessException
    */
    public AuthGrade updateAuthGrade(AuthGrade authGrade) throws BusinessException;

    /**
     * @Title: saveOrUpdateAuthGrade
     * @Description: 保存或更新
     * @author liaoguowei
     * @param authGrade 授权等级
     * @return AuthGrade 授权等级
     * @throws BusinessException
     */
    public AuthGrade saveOrUpdateAuthGrade(AuthGrade authGrade) throws BusinessException;


    /**
      * @Title: findById
      * @Description: 按照ID查找数据
      * @author liaoguowei
      * @param id 授权等级Id
      * @return AuthGrade 授权等级
      * @throws BusinessException
    */
    public AuthGrade findById(String id) throws BusinessException;

    /**
      * @Title: findByGradeCode
      * @Description: 按照等级编号查找
      * @author liaoguowei 
      * @param gradeCode 等级编号
      * @return List<AuthGrade> 授权等级LIST
      * @throws BusinessException
    */
    public List<AuthGrade> findByGradeCode(String gradeCode) throws BusinessException;
    
    /** 
     * @Title: findByCaseApplyId 
     * @Description: 通过案件ID查询案件产品所属机构评级 金额
     * @author wangrongwei
     * @param caseApplyId 案件ID
     * @return  
     * @throws Exception 
     */ 
    public BigDecimal findByCaseApplyId(String caseApplyId) throws Exception;

    /**
     * @Title: findByProductChildIdAndGradeCode
     * @Description: 按照产品ID和等级编号查找
     * @author liaoguowei
     * @param authGrade 查询条件
     * @return com.zdsoft.finance.businesssetting.entity.AuthGrade
     * @throws BusinessException
     */
    public AuthGrade findByProductChildIdAndGradeCode(AuthGrade authGrade) throws BusinessException;

}
