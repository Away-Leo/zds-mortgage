package com.zdsoft.finance.product.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.MateriaListAuth;
import com.zdsoft.finance.product.entity.MaterialList;
import com.zdsoft.finance.product.repository.MateriaListAuthRepository;
import com.zdsoft.finance.product.service.MateriaListAuthService;
import com.zdsoft.finance.product.service.MateriaListService;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 资料清单节点权限service
 *
 * @author LiaoGuoWei
 * @create 2016-12-26 15:34
 **/
@Service("materiaListAuthService")
public class MateriaListAuthServiceImpl extends BaseServiceImpl<MateriaListAuth, MateriaListAuthRepository> implements MateriaListAuthService {


    @Autowired
    private MateriaListAuthRepository materiaListAuthRepository;
    @Autowired
    private MateriaListService materiaListService;
    @Autowired
    private CED CED;

    @Override
    public List<MateriaListAuth> findByProductCdAndMaterialListId(String productCode, String materialListId) throws BusinessException {
        if (ObjectHelper.isNotEmpty(productCode) && ObjectHelper.isNotEmpty(materialListId)) {
            MaterialList materialList = this.materiaListService.findByMaterialListById(materialListId);
            List<MateriaListAuth> sourceData = this.materiaListAuthRepository.findByProductCodeAndMaterialList(productCode, materialList);
            if (ObjectHelper.isNotEmpty(sourceData) && sourceData.size() > 0) {
                return sourceData;
            } else {
                throw new BusinessException("10010002", "根据参数未找到相应数据");
            }
        } else {
            throw new BusinessException("10010004", "未传入相关参数");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<MateriaListAuth> saveMateriaAuth(String dataJsonStr, String productId, String materiaListId) throws Exception {
        if (ObjectHelper.isNotEmpty(dataJsonStr) && ObjectHelper.isNotEmpty(productId) && ObjectHelper.isNotEmpty(materiaListId)) {
            JSONArray jsonArray = JSONObject.parseArray(dataJsonStr);
            MaterialList materialList = this.materiaListService.findByMaterialListById(materiaListId);
            List<MateriaListAuth> materiaListAuthList = new ArrayList<MateriaListAuth>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject rowData = (JSONObject) jsonArray.get(i);
                String nodeId = rowData.getString("field");
                String nodeName = rowData.getString("name");
                String processid = rowData.getString("processid");
                String processname = rowData.getString("processname");
                MateriaListAuth materiaListAuth = new MateriaListAuth();
                materiaListAuth.setProductCode(productId);
                materiaListAuth.setMaterialList(materialList);
                materiaListAuth.setProcessCode(processid);
                materiaListAuth.setProcessName(processname);
                materiaListAuth.setProcessNodeCode(nodeId);
                materiaListAuth.setProcessNodeName(nodeName);
                materiaListAuth.setCreateOrgCd(CED.getLoginUser().getOrgCd());
                materiaListAuth.setCreateBy(CED.getLoginUser().getEmpCd());
                materiaListAuthList.add(materiaListAuth);
            }
            materiaListAuthList = this.materiaListAuthRepository.batchSave(materiaListAuthList);
            if(ObjectHelper.isEmpty(materiaListAuthList)||materiaListAuthList.size()==0){
                throw new BusinessException("10010010","保存数据出错");
            }
            return materiaListAuthList;
        } else {
            throw new BusinessException("10010004", "未传入相关参数");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<MateriaListAuth> updateMateriaAuth(String dataJsonStr, String productId, String materiaListId) throws Exception {
        if(ObjectHelper.isNotEmpty(dataJsonStr)&&ObjectHelper.isNotEmpty(productId)&&ObjectHelper.isNotEmpty(materiaListId)){
            //将json字符串转换为jsonArray
            JSONArray jsonArray = JSONObject.parseArray(dataJsonStr);
            //查找与之关联的资料清单
            MaterialList materialList = this.materiaListService.findByMaterialListById(materiaListId);
            //定义流程ID
            String processid="";
            List<MateriaListAuth> materiaListAuthList = new ArrayList<MateriaListAuth>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject rowData = (JSONObject) jsonArray.get(i);
                String nodeId = rowData.getString("field");
                String nodeName = rowData.getString("name");
                processid = rowData.getString("processid");
                String processname = rowData.getString("processname");
                MateriaListAuth materiaListAuth = new MateriaListAuth();
                materiaListAuth.setProductCode(productId);
                materiaListAuth.setMaterialList(materialList);
                materiaListAuth.setProcessCode(processid);
                materiaListAuth.setProcessName(processname);
                materiaListAuth.setProcessNodeCode(nodeId);
                materiaListAuth.setProcessNodeName(nodeName);
                materiaListAuth.setCreateOrgCd(CED.getLoginUser().getOrgCd());
                materiaListAuth.setCreateBy(CED.getLoginUser().getEmpCd());
                materiaListAuth.setUpdateBy(CED.getLoginUser().getEmpCd());
                materiaListAuth.setUpdateTime(new Date());
                materiaListAuth.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
                materiaListAuthList.add(materiaListAuth);
            }
            materiaListAuthList = this.materiaListAuthRepository.batchSave(materiaListAuthList);
            if(ObjectHelper.isNotEmpty(materiaListAuthList)&&materiaListAuthList.size()>0){
                //删除旧数据
                this.materiaListAuthRepository.deleteByCondition(productId, materiaListId, processid);
            }
            if(ObjectHelper.isEmpty(materiaListAuthList)||materiaListAuthList.size()==0){
                throw new BusinessException("10010011","更新数据出错");
            }
            return materiaListAuthList;
        }else{
            throw new BusinessException("10010004","未传入相关参数");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<MateriaListAuth> saveOrUpdateMateriaAuth(String dataJsonStr, String productId, String materiaListId) throws Exception {
        List<MateriaListAuth> oldData=this.materiaListAuthRepository.findByProductIdAndMateriaListId(productId, materiaListId);
        if(ObjectHelper.isEmpty(oldData)||oldData.size()==0){
            return this.saveMateriaAuth(dataJsonStr, productId, materiaListId);
        }else{
            return this.updateMateriaAuth(dataJsonStr, productId, materiaListId);
        }
    }
}
