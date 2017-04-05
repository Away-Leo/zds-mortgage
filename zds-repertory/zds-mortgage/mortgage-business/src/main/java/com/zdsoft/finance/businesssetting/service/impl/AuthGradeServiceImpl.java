package com.zdsoft.finance.businesssetting.service.impl;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.businesssetting.entity.AuthGrade;
import com.zdsoft.finance.businesssetting.repository.AuthGradeRepository;
import com.zdsoft.finance.businesssetting.service.AuthGradeService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.ObjectProperUtil;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title AuthGradeServiceImpl.java
 * @className AuthGradeServiceImpl
 * @description 授权评级设定service实现
 * @author LiaoGuoWei
 * @create 2017/3/3 14:48
 * @version V1.0
 **/
@Service("authGradeService")
public class AuthGradeServiceImpl extends BaseServiceImpl<AuthGrade,AuthGradeRepository> implements AuthGradeService {

    @Autowired
    private AuthGradeRepository authGradeRepository;

    @Override
    public Page<AuthGrade> findByCondition(Pageable pageable, String productParentId, String productChildId, String gradeCode) {
        return authGradeRepository.qryConditions(pageable, productParentId, productChildId, gradeCode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AuthGrade saveAuthGrade(AuthGrade authGrade) throws BusinessException {
        if(ObjectHelper.isNotEmpty(authGrade)){
            if(ObjectHelper.isNotEmpty(authGrade.getUpdateBy())&&ObjectHelper.isNotEmpty(authGrade.getUpdateByNm())){
                authGrade.setCreateBy(authGrade.getUpdateBy());
                authGrade.setCreateByNm(authGrade.getUpdateByNm());
                authGrade.setUpdateTime(new Date());
            }
            AuthGrade savedAuthGrade=this.authGradeRepository.saveEntity(authGrade);
            if(ObjectHelper.isNotEmpty(savedAuthGrade)&&ObjectHelper.isNotEmpty(savedAuthGrade.getId())){
                return savedAuthGrade;
            }else{
                throw new BusinessException("10010010","保存数据出错");
            }
        }else{
            throw new BusinessException("10010004","未传入相关参数");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AuthGrade updateAuthGrade(AuthGrade authGrade) throws BusinessException {
        if(ObjectHelper.isNotEmpty(authGrade)){
            if(ObjectHelper.isNotEmpty(authGrade.getId())){
                AuthGrade oldAuthGrade=this.findById(authGrade.getId());
                oldAuthGrade=(AuthGrade) ObjectProperUtil.compareAndValue(authGrade,oldAuthGrade,false,null);
                oldAuthGrade=this.authGradeRepository.updateEntity(oldAuthGrade);
                return oldAuthGrade;
            }else{
                throw new BusinessException("10010003","传入参数有错误");
            }
        }else{
            throw new BusinessException("10010004","未传入相关参数");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AuthGrade saveOrUpdateAuthGrade(AuthGrade authGrade) throws BusinessException {
        if(ObjectHelper.isNotEmpty(authGrade)){
            if(ObjectHelper.isNotEmpty(authGrade.getId())){
                return this.updateAuthGrade(authGrade);
            }else{
                return this.saveAuthGrade(authGrade);
            }
        }else{
            throw new BusinessException("10010004","未传入相关参数");
        }
    }

    @Override
    public AuthGrade findById(String id) throws BusinessException {
        if(ObjectHelper.isNotEmpty(id)){
            AuthGrade authGrade=this.authGradeRepository.findOne(id);
            if(ObjectHelper.isNotEmpty(authGrade)&&ObjectHelper.isNotEmpty(authGrade.getId())){
                return authGrade;
            }else{
                throw new BusinessException("10010002","根据相应参数未找到相应数据");
            }
        }else{
            throw new BusinessException("10010004","未传入相关参数");
        }
    }

    @Override
    public List<AuthGrade> findByGradeCode(String gradeCode) throws BusinessException {
        if(ObjectHelper.isNotEmpty(gradeCode)){
            List<AuthGrade> sourceData=this.authGradeRepository.findByGradeCode(gradeCode);
            if(ObjectHelper.isNotEmpty(sourceData)&&sourceData.size()>0){
                return sourceData;
            }else{
                throw new BusinessException("10010002","根据参数未找到相应配置,未配置相应授权等级设定");
            }
        }else{
            throw new BusinessException("10010004","未传入相关参数");
        }
    }

	@Override
	public BigDecimal findByCaseApplyId(String caseApplyId) throws Exception {
		List<Map<String, Object>> list = authGradeRepository.findByCaseApplyId(caseApplyId).getRecords();
		if(ObjectHelper.isNotEmpty(list)&&list.size()>0){
			return (BigDecimal) list.get(0).get("AUTHAMOUNT");
		}
		return null;
	}

    @Override
    public AuthGrade findByProductChildIdAndGradeCode(AuthGrade authGrade) throws BusinessException {
        if(ObjectHelper.isNotEmpty(authGrade)&&ObjectHelper.isNotEmpty(authGrade.getProductChildId())&&ObjectHelper.isNotEmpty(authGrade.getGradeCode())){
            return this.authGradeRepository.findByProductChildIdAndGradeCode(authGrade.getProductChildId(),authGrade.getGradeCode());
        }else{
            throw new BusinessException("10010004","未传入相关参数，用过产品ID和等级编号查找授权等级设定出错");
        }
    }
}
