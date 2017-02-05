package com.zdsoft.finance.authgrade.service.impl;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.authgrade.entity.AuthGrade;
import com.zdsoft.finance.authgrade.entity.OrgAuthConn;
import com.zdsoft.finance.authgrade.repository.OrgAuthConnRepository;
import com.zdsoft.finance.authgrade.service.AuthGradeService;
import com.zdsoft.finance.authgrade.service.InstitutionGradeService;
import com.zdsoft.finance.authgrade.service.OrgAuthConnService;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 机构和授权等级设定service实现类
 * @author LiaoGuoWei
 * @create 2017-01-22 20:44
 **/
@Service("orgAuthConnService")
public class OrgAuthConnServiceImpl extends BaseServiceImpl<OrgAuthConn,OrgAuthConnRepository> implements OrgAuthConnService {

    @Autowired
    private OrgAuthConnRepository orgAuthConnRepository;

    @Autowired
    private CED CED;

    @Autowired
    private InstitutionGradeService institutionGradeService;

    @Autowired
    private AuthGradeService authGradeService;

    @Override
    public OrgAuthConn findById(String id) throws BusinessException {
        if(ObjectHelper.isNotEmpty(id)){
            OrgAuthConn sourceData=this.orgAuthConnRepository.findOne(id);
            if(ObjectHelper.isNotEmpty(sourceData)){
                return sourceData;
            }else{
                throw new BusinessException("10010002","根据参数未找到相关数据");
            }
        }else{
            throw new BusinessException("10010004","未传入相关参数");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrgAuthConn saveOrgAuthConn(OrgAuthConn orgAuthConn) throws Exception {
        if(ObjectHelper.isNotEmpty(orgAuthConn)&&ObjectHelper.isNotEmpty(orgAuthConn.getOrgIntermediateId())){
            //查找机构授权等级中等级为终评的数据
            List<AuthGrade> sourceAuthGrade=this.authGradeService.findByGradeCode(orgAuthConn.getAuthGradeCode());
            if(ObjectHelper.isNotEmpty(sourceAuthGrade)&&sourceAuthGrade.size()>0){
                orgAuthConn.setCreateBy(CED.getLoginUser().getEmpCd());
                orgAuthConn.setCreateTime(new Date());
                orgAuthConn.setHandelerCode(CED.getLoginUser().getEmpCd());
                orgAuthConn.setHandelerName(CED.getLoginUser().getEmpNm());
                orgAuthConn.setHandelTime(DateHelper.dateToString(new Date(),DateHelper.DATE_WITHMINUTE_FORMAT));
                //保存操作历史
                this.institutionGradeService.saveWithOrgAuthGrade(orgAuthConn);
                return this.orgAuthConnRepository.saveEntity(orgAuthConn);
            }else{
                throw new BusinessException("10010002","根据参数未找到相关数据,未配置授权等级设定");
            }
        }else{
            throw new BusinessException("10010004","未传入相关参数");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrgAuthConn updateOrgAuthConn(OrgAuthConn orgAuthConn) throws Exception {
        if(ObjectHelper.isNotEmpty(orgAuthConn)&&ObjectHelper.isNotEmpty(orgAuthConn.getId())&&ObjectHelper.isNotEmpty(orgAuthConn.getOrgIntermediateId())){
            //查找机构授权等级中等级为终评的数据
            List<AuthGrade> sourceAuthGrade=this.authGradeService.findByGradeCode(orgAuthConn.getAuthGradeCode());
            if(ObjectHelper.isNotEmpty(sourceAuthGrade)&&sourceAuthGrade.size()>0){
                OrgAuthConn oldData=this.orgAuthConnRepository.findByOrgIntermediateId(orgAuthConn.getOrgIntermediateId());
                oldData.setHandelerCode(CED.getLoginUser().getEmpCd());
                oldData.setHandelerName(CED.getLoginUser().getEmpNm());
                oldData.setHandelTime(DateHelper.dateToString(new Date(),DateHelper.DATE_WITHMINUTE_FORMAT));
                oldData.setOriginalGradeCode(oldData.getAuthGradeCode());
                oldData.setOriginalGradeName(oldData.getAuthGradeName());
                oldData.setAuthGradeCode(orgAuthConn.getAuthGradeCode());
                oldData.setAuthGradeName(orgAuthConn.getAuthGradeName());
                oldData.setUpdateBy(CED.getLoginUser().getEmpCd());
                oldData.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
                oldData=this.orgAuthConnRepository.updateEntity(oldData);
                //保存操作历史
                this.institutionGradeService.saveWithOrgAuthGrade(oldData);
                return oldData;
            }else{
                throw new BusinessException("10010002","根据参数未找到相应数据,未配置授权等级设定");
            }
        }else{
            throw new BusinessException("10010004","未传入相关参数");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrgAuthConn saveOrUpdateOrgAuthConn(OrgAuthConn orgAuthConn) throws Exception {
        if(ObjectHelper.isNotEmpty(orgAuthConn)){
            if(ObjectHelper.isNotEmpty(orgAuthConn.getId())){
                return this.updateOrgAuthConn(orgAuthConn);
            }else {
                return this.saveOrgAuthConn(orgAuthConn);
            }
        }else{
            throw new BusinessException("10010004","未传入相关参数");
        }
    }

    @Override
    public OrgAuthConn findByOrgCode(String orgCode) throws Exception {
        if(ObjectHelper.isNotEmpty(orgCode)){
            OrgAuthConn sourceData=this.orgAuthConnRepository.findByOrgIntermediateId(orgCode);
            if(ObjectHelper.isNotEmpty(sourceData)){
                return sourceData;
            }else{
                throw new BusinessException("10010002","根据参数未找到相关数据");
            }
        }else{
            throw new BusinessException("10010004","未传入相关数据");
        }
    }
}
