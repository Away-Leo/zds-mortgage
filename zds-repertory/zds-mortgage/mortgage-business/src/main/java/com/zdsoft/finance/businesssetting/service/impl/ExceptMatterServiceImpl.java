package com.zdsoft.finance.businesssetting.service.impl;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.businesssetting.entity.ExceptMatter;
import com.zdsoft.finance.businesssetting.repository.ExceptMatterRepository;
import com.zdsoft.finance.businesssetting.service.ExceptMatterService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.ObjectProperUtil;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 *
 * @author LiaoGuoWei
 * @version V1.0
 * @title ExceptMatterServiceImpl.java
 * @className ExceptMatterServiceImpl
 * @description
 * @create 2017/2/16 17:32
 **/
@Service("exceptMatterService")
public class ExceptMatterServiceImpl extends BaseServiceImpl<ExceptMatter, CustomRepository<ExceptMatter, String>>
        implements ExceptMatterService {

    @Autowired
    private ExceptMatterRepository exceptMatterRepository;

    @Autowired
    private CED CED;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteExceptMatter(String id) throws BusinessException {
        ExceptMatter exceptMatter = this.findById(id);
        exceptMatterRepository.logicDelete(exceptMatter);
    }

    @Override
    public ExceptMatter findById(String id) throws BusinessException {
        if (ObjectHelper.isNotEmpty(id)) {
            ExceptMatter exceptMatter = this.exceptMatterRepository.findOne(id);
            if (ObjectHelper.isNotEmpty(exceptMatter)) {
                return exceptMatter;
            } else {
                throw new BusinessException("10010002", "根据参数未找到相应配置");
            }
        } else {
            throw new BusinessException("10010004", "未传入相关参数");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExceptMatter saveExceptMatter(ExceptMatter exceptMatter) throws Exception {
        if (ObjectHelper.isNotEmpty(exceptMatter)) {
            if (ObjectHelper.isEmpty(exceptMatter.getId())) {
                //将code的值赋值给ID
                exceptMatter.setCreateBy(CED.getLoginUser().getEmpCd());
                exceptMatter.setCreateOrgCd(CED.getLoginUser().getOrgCd());
                exceptMatter.setUpdateBy(CED.getLoginUser().getEmpCd());
                exceptMatter.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
                exceptMatter.setUpdateTime(new Date());
                return this.exceptMatterRepository.saveEntity(exceptMatter);
            } else {
                throw new BusinessException("10010003", "传入参数有误");
            }
        } else {
            throw new BusinessException("10010004", "未传入相关参数");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExceptMatter updateExceptMatter(ExceptMatter exceptMatter) throws Exception {
        if (ObjectHelper.isNotEmpty(exceptMatter)) {
            if (ObjectHelper.isNotEmpty(exceptMatter.getId())) {
                ExceptMatter oldData = this.findById(exceptMatter.getId());
                //将更改后的值进行赋值
                oldData = (ExceptMatter) ObjectProperUtil.compareAndValue(exceptMatter, oldData, false, new String[]{"remark"});
                oldData.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
                oldData.setUpdateBy(CED.getLoginUser().getEmpCd());
                return this.exceptMatterRepository.updateEntity(oldData);
            } else {
                throw new BusinessException("10010003", "传入参数有误");
            }
        } else {
            throw new BusinessException("10010004", "未传入相关参数");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExceptMatter saveOrUpdateExceptMatter(ExceptMatter exceptMatter) throws Exception {
        if (ObjectHelper.isNotEmpty(exceptMatter)) {
            if (ObjectHelper.isNotEmpty(exceptMatter.getId())) {
                return this.updateExceptMatter(exceptMatter);
            } else {
                return this.saveExceptMatter(exceptMatter);
            }
        } else {
            throw new BusinessException("10010004", "未传入相关参数");
        }
    }

    @Override
    public ExceptMatter findByExceptMattercode(String exceptMattercode) {
        return exceptMatterRepository.findByExceptMattercode(exceptMattercode);
    }

    @Override
    public List<ExceptMatter> findByExceptMatterType(String exceptMatterType) {
        return exceptMatterRepository.findByExceptMatterType(exceptMatterType);
    }

    @Override
    public String buildingCode() throws Exception {
        String code = this.exceptMatterRepository.findMaxCode();
        if (ObjectHelper.isNotEmpty(code)) {
            String numStr = code.substring(4);
            Integer numInt = Integer.parseInt(numStr);
            Integer returnInt = numInt + 1;
            if (returnInt.toString().length() < 7) {
                String oriReturnData = "TPDM000000";
                String returnData = oriReturnData.substring(0, oriReturnData.length() - returnInt.toString().length()) + returnInt.toString();
                return returnData;
            } else {
                return "TPDM" + returnInt.toString();
            }
        } else {
            throw new BusinessException("10010004", "未找到相应数据，查找特批事项最大编号出错");
        }
    }

    @Override
    public List<ExceptMatter> findByTypeAndName(ExceptMatter exceptMatter) throws BusinessException{
        if(ObjectHelper.isNotEmpty(exceptMatter)&&ObjectHelper.isNotEmpty(exceptMatter.getExceptMatterType())&&ObjectHelper.isNotEmpty(exceptMatter.getExceptMatterName())){
            List<ExceptMatter> sourceData=this.exceptMatterRepository.findByTypeAndName(exceptMatter.getExceptMatterType().trim(),exceptMatter.getExceptMatterName().trim());
            if(ObjectHelper.isNotEmpty(sourceData)){
                return sourceData;
            }else{
                return null;
            }
        }else{
            throw new BusinessException("10010004","未传入相关参数，根据类型和名称查找特批事项出错");
        }
    }

    @Override
    public Page<ExceptMatter> findExceptMatterByCondition(Pageable page, ExceptMatter exceptMatter) throws BusinessException {
        if(ObjectHelper.isNotEmpty(page)&&ObjectHelper.isNotEmpty(exceptMatter)){
            return this.exceptMatterRepository.findExceptMatterByCondition(page,exceptMatter);
        }else{
            throw new BusinessException("10010004","未传入相关参数");
        }
    }
}