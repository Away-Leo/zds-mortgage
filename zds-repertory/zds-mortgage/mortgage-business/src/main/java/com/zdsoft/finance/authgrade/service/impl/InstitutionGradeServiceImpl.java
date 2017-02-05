package com.zdsoft.finance.authgrade.service.impl;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.org.OrgDto;
import com.zdsoft.finance.authgrade.entity.AuthGrade;
import com.zdsoft.finance.authgrade.entity.InstitutionGrade;
import com.zdsoft.finance.authgrade.entity.OrgAuthConn;
import com.zdsoft.finance.authgrade.entity.OrgIntermediate;
import com.zdsoft.finance.authgrade.repository.AuthGradeRepository;
import com.zdsoft.finance.authgrade.repository.InstitutionGradeRepository;
import com.zdsoft.finance.authgrade.repository.OrgIntermediateRepository;
import com.zdsoft.finance.authgrade.service.AuthGradeService;
import com.zdsoft.finance.authgrade.service.InstitutionGradeService;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.apache.commons.beanutils.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 机构评级service实现
 *
 * @author LiaoGuoWei
 * @create 2017-01-06 16:47
 **/
@Service("institutionGradeService")
public class InstitutionGradeServiceImpl extends BaseServiceImpl<InstitutionGrade, InstitutionGradeRepository> implements InstitutionGradeService {

    @Autowired
    private CED CED;
    @Autowired
    private OrgIntermediateRepository orgIntermediateRepository;
    @Autowired
    private InstitutionGradeRepository institutionGradeRepository;
    @Autowired
    private AuthGradeService authGradeService;
    @Autowired
    private AuthGradeRepository authGradeRepository;

    @Override
    public InstitutionGrade findById(String id) throws BusinessException {
        return null;
    }

    @Override
    public List<InstitutionGrade> findByCondition(String institutionCode) throws Exception {
        //将机构数据写入临时表
        this.writeDataToOrg();
        List<InstitutionGrade> sourceData = this.institutionGradeRepository.queryWithCondition(institutionCode);
        return sourceData;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<InstitutionGrade> saveInstitution(InstitutionGrade institutionGrade) throws Exception {
        List<InstitutionGrade> returnData=new ArrayList<InstitutionGrade>();
        if (ObjectHelper.isNotEmpty(institutionGrade)&&ObjectHelper.isNotEmpty(institutionGrade.getInstitutionCode())&&ObjectHelper.isNotEmpty(institutionGrade.getFinalGradeCode())) {
            //查找机构授权等级中等级为终评的数据
            List<AuthGrade> sourceAuthGrade=this.authGradeService.findByGradeCode(institutionGrade.getFinalGradeCode());
            //保存临时数据
            List<InstitutionGrade> saveTempList=new ArrayList<InstitutionGrade>();
            if(ObjectHelper.isNotEmpty(sourceAuthGrade)&&sourceAuthGrade.size()>0){
                for(AuthGrade temp:sourceAuthGrade){
                    InstitutionGrade tempInstitution=new InstitutionGrade();
                    tempInstitution.setInstitutionCode(institutionGrade.getInstitutionCode());
                    tempInstitution.setInstitutionName(institutionGrade.getInstitutionName());
                    tempInstitution.setFinalGradeCode(institutionGrade.getFinalGradeCode());
                    tempInstitution.setFinalGradeName(institutionGrade.getFinalGradeName());
                    tempInstitution.setUpdateTime(new Date());
                    tempInstitution.setAuthGrade(temp);
                    tempInstitution.setHandelTime(DateHelper.dateToString(new Date(),DateHelper.DATE_WITHMINUTE_FORMAT));
                    tempInstitution.setHanderCode(CED.getLoginUser().getEmpCd());
                    tempInstitution.setHanderName(CED.getLoginUser().getEmpNm());
                    tempInstitution.setCreateBy(CED.getLoginUser().getEmpCd());
                    tempInstitution.setCreateOrgCd(CED.getLoginUser().getOrgCd());
                    saveTempList.add(tempInstitution);
                }
                saveTempList=this.institutionGradeRepository.batchSave(saveTempList);
                if(ObjectHelper.isNotEmpty(saveTempList)&&saveTempList.size()>0){
                    returnData=saveTempList;
                }else{
                    throw new BusinessException("10010010","保存数据出错");
                }
            }else{
                throw new BusinessException("10010002","根据参数未找到相应数据");
            }
        } else {
            throw new BusinessException("10010004", "未传入相关参数");
        }
        return returnData;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<InstitutionGrade> saveWithOrgAuthGrade(OrgAuthConn orgAuthConn) throws Exception {
        InstitutionGrade institutionGrade=new InstitutionGrade();
        institutionGrade.setFinalGradeCode(ObjectHelper.isNotEmpty(orgAuthConn.getAuthGradeCode())?orgAuthConn.getAuthGradeCode():"");
        institutionGrade.setFinalGradeName(ObjectHelper.isNotEmpty(orgAuthConn.getAuthGradeName())?orgAuthConn.getAuthGradeName():"");
        institutionGrade.setOriginalGradeCode(ObjectHelper.isNotEmpty(orgAuthConn.getOriginalGradeCode())?orgAuthConn.getOriginalGradeCode():"");
        institutionGrade.setOriginalGradeName(ObjectHelper.isNotEmpty(orgAuthConn.getOriginalGradeName()) ? orgAuthConn.getOriginalGradeName() : "");
        institutionGrade.setHandelTime(ObjectHelper.isNotEmpty(orgAuthConn.getHandelTime())?orgAuthConn.getHandelTime():"");
        institutionGrade.setHanderCode(ObjectHelper.isNotEmpty(orgAuthConn.getHandelerCode())?orgAuthConn.getHandelerCode():"");
        institutionGrade.setHanderName(ObjectHelper.isNotEmpty(orgAuthConn.getHandelerName())?orgAuthConn.getHandelerName():"");
        institutionGrade.setInstitutionCode(orgAuthConn.getOrgIntermediateId());
        institutionGrade.setInstitutionName(orgAuthConn.getOrgIntermediateName());
        return this.saveInstitution(institutionGrade);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<InstitutionGrade> updateInstitution(InstitutionGrade institutionGrade) throws Exception {
        if(ObjectHelper.isNotEmpty(institutionGrade)&&ObjectHelper.isNotEmpty(institutionGrade.getInstitutionCode())&&ObjectHelper.isNotEmpty(institutionGrade.getFinalGradeCode())){
            List<InstitutionGrade> oldDatas=this.institutionGradeRepository.queryWithCondition(institutionGrade.getInstitutionCode());
            for(InstitutionGrade temp:oldDatas){
                temp.setOriginalGradeCode(temp.getFinalGradeCode());
                temp.setOriginalGradeName(temp.getFinalGradeName());
                temp.setFinalGradeCode(institutionGrade.getFinalGradeCode());
                temp.setFinalGradeName(institutionGrade.getFinalGradeName());
                temp.setHandelTime(DateHelper.dateToString(new Date(),DateHelper.DATE_WITHMINUTE_FORMAT));
                temp.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
                temp.setUpdateBy(CED.getLoginUser().getEmpCd());
            }
            oldDatas=this.institutionGradeRepository.batchUpdate(oldDatas);
            return oldDatas;
        }else{
            throw new BusinessException("10010004","未传入相关参数");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<InstitutionGrade> saveOrUpdateInstitution(InstitutionGrade institutionGrade) throws Exception {
        if(ObjectHelper.isNotEmpty(institutionGrade)){
            if(ObjectHelper.isNotEmpty(institutionGrade.getId())){
                return this.updateInstitution(institutionGrade);
            }else{
                return this.saveInstitution(institutionGrade);
            }
        }else{
            throw new BusinessException("10010004","未传入相关参数");
        }
    }

    @Override
    public Page<Map<String, Object>> findPageByCondition(Pageable pageable, String institutionCode) throws Exception {
        Page<Map<String, Object>> sourceData = this.institutionGradeRepository.findPageWithCondition(pageable, institutionCode);
        List<Map<String, Object>> returnListData = new ArrayList<Map<String, Object>>();
        if (ObjectHelper.isNotEmpty(sourceData) && ObjectHelper.isNotEmpty(sourceData.getRecords()) && sourceData.getRecords().size() > 0) {
            for (Map<String, Object> temp : sourceData.getRecords()) {
                if (ObjectHelper.isNotEmpty(temp.get("authGradeId"))) {
                    String authGradeId = temp.get("authGradeId").toString();
                    AuthGrade authGrade = this.authGradeService.findById(authGradeId);
                    authGrade.setInstitutionGradeId(ObjectHelper.isNotEmpty(temp.get("id")) ? temp.get("id").toString() : "");
                    authGrade.setInstitutionCode(ObjectHelper.isNotEmpty(temp.get("institutionCode")) ? temp.get("institutionCode").toString() : "");
                    authGrade.setInstitutionName(ObjectHelper.isNotEmpty(temp.get("institutionName")) ? temp.get("institutionName").toString() : "");
                    authGrade.setInsGradeCode(ObjectHelper.isNotEmpty(temp.get("insGradeCode")) ? temp.get("insGradeCode").toString() : "");
                    authGrade.setInsGradeName(ObjectHelper.isNotEmpty(temp.get("insGradeName")) ? temp.get("insGradeName").toString() : "");
                    authGrade.setHanderCode(ObjectHelper.isNotEmpty(temp.get("handerCode")) ? temp.get("handerCode").toString() : "");
                    authGrade.setHanderName(ObjectHelper.isNotEmpty(temp.get("handerName")) ? temp.get("handerName").toString() : "");
                    authGrade.setHandelTime(ObjectHelper.isNotEmpty(temp.get("handelTime")) ? temp.get("handelTime").toString() : "");
                    authGrade.setOriginalGradeCode(ObjectHelper.isNotEmpty(temp.get("originalGradeCode")) ? temp.get("originalGradeCode").toString() : "");
                    authGrade.setOriginalGradeName(ObjectHelper.isNotEmpty(temp.get("originalGradeName")) ? temp.get("originalGradeName").toString() : "");
                    authGrade.setFinalGradeCode(ObjectHelper.isNotEmpty(temp.get("finalGradeCode")) ? temp.get("finalGradeCode").toString() : "");
                    authGrade.setFinalGradeName(ObjectHelper.isNotEmpty(temp.get("finalGradeName")) ? temp.get("finalGradeName").toString() : "");
                    returnListData.add(new BeanMap(authGrade));
                } else {
                    returnListData.add(temp);
                }
            }
        }
        sourceData.setRecords(returnListData);
        return sourceData;
    }

    @Override
    public List<InstitutionGrade> findListByCondition(String institutionCode) throws BusinessException {
        List<InstitutionGrade> sourceData = this.institutionGradeRepository.queryWithCondition(institutionCode);
        if (ObjectHelper.isNotEmpty(sourceData) && sourceData.size() > 0) {
            return sourceData;
        } else {
            throw new BusinessException("10010002", "根据参数未找到相应数据");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized void writeDataToOrg() throws Exception {
        List<OrgDto> orgSource = CED.queryAllCompany();
        if (ObjectHelper.isNotEmpty(orgSource) && orgSource.size() > 0) {
            orgIntermediateRepository.deleteAll();
            for (OrgDto temp : orgSource) {
                if (ObjectHelper.isNotEmpty(temp.getOrgType()) && temp.getOrgType().equals("1")||temp.getOrgType().equals("2")) {
                    OrgIntermediate orgIntermediate = new OrgIntermediate();
                    orgIntermediate.setOrgCd(ObjectHelper.isNotEmpty(temp.getOrgCd()) ? temp.getOrgCd() : "");
                    orgIntermediate.setOrgCdRule(ObjectHelper.isNotEmpty(temp.getOrgCdRule()) ? temp.getOrgCdRule() : "");
                    orgIntermediate.setOrgCdRuleNm(ObjectHelper.isNotEmpty(temp.getOrgCdRuleNm()) ? temp.getOrgCdRuleNm() : "");
                    orgIntermediate.setOrgId(ObjectHelper.isNotEmpty(temp.getOrgId()) ? temp.getOrgId() : "");
                    orgIntermediate.setOrgNm(ObjectHelper.isNotEmpty(temp.getOrgNm()) ? temp.getOrgNm() : "");
                    orgIntermediate.setOrgType(ObjectHelper.isNotEmpty(temp.getOrgType()) ? temp.getOrgType() : "");
                    orgIntermediate.setOrgTypeNm(ObjectHelper.isNotEmpty(temp.getOrgTypeNm()) ? temp.getOrgTypeNm() : "");
                    orgIntermediate.setShortNm(ObjectHelper.isNotEmpty(temp.getShortNm()) ? temp.getShortNm() : "");
                    orgIntermediate.setRegion(ObjectHelper.isNotEmpty(temp.getRegion()) ? temp.getRegion() : "");
                    orgIntermediate.setRemark(ObjectHelper.isNotEmpty(temp.getRemark()) ? temp.getRemark() : "");

                    this.orgIntermediateRepository.saveEntity(orgIntermediate);
                }
            }
        }
    }

    @Override
    public Page<InstitutionGrade> findPageList(Pageable pageable,String institutionCode) throws BusinessException {
        return this.institutionGradeRepository.queryPageWithCondition(pageable,institutionCode);
    }

    @Override
    public List<OrgIntermediate> findAllOrg() {
        return this.orgIntermediateRepository.findAll();
    }

    @Override
    public Page<AuthGrade> findInstitutionAuthPage(Pageable pageable,AuthGrade authGrade) throws Exception{
        return this.authGradeRepository.findInstitutionAuth(pageable,authGrade);
    }

    @Override
    public List<Map<String,Object>> findAllHander() throws Exception{
        List<Map<String,Object>> sourceData=new ArrayList<Map<String,Object>>();
        sourceData=this.institutionGradeRepository.findHander();
        if(ObjectHelper.isNotEmpty(sourceData)&&sourceData.size()>0){
            return sourceData;
        }else{
            throw new BusinessException("10010002","根据参数未找到相应数据");
        }
    }
}
