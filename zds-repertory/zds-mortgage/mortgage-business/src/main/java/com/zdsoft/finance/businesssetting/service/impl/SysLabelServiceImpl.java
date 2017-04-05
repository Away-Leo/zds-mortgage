package com.zdsoft.finance.businesssetting.service.impl;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.businesssetting.entity.SysLabel;
import com.zdsoft.finance.businesssetting.repository.SysLabelRepository;
import com.zdsoft.finance.businesssetting.service.SysLabelService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.ObjectProperUtil;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @title SysLabelServiceImpl.java
 * @className SysLabelServiceImpl
 * @description 标签设置
 * @author LiaoGuoWei
 * @create 2017/2/27 21:00
 * @version V1.0
 **/
@Service("sysLabelService")
public class SysLabelServiceImpl extends BaseServiceImpl<SysLabel, CustomRepository<SysLabel, String>> implements SysLabelService {

    @Autowired
    private SysLabelRepository sysLabelRepository;

    @Autowired
    private CED CED;

    @Override
    public void deleteLabel(String id) throws BusinessException {
        SysLabel sysLabel = this.findSysLabelById(id);
        sysLabelRepository.logicDelete(sysLabel);
    }

    @Override
    public SysLabel findSysLabelById(String id) throws BusinessException {
        if (ObjectHelper.isNotEmpty(id)) {
            SysLabel sysLabel = this.sysLabelRepository.findOne(id);
            if (ObjectHelper.isNotEmpty(sysLabel)) {
                return sysLabel;
            } else {
                throw new BusinessException("10010002", "根据参数未找到相应数据，根据ID查找标签设置出错");
            }
        } else {
            throw new BusinessException("10010004", "未传入相关参数，根据ID查找标签设置出错");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysLabel saveSysLabel(SysLabel sysLabel) throws Exception {
        if (ObjectHelper.isNotEmpty(sysLabel)) {
            if (ObjectHelper.isEmpty(sysLabel.getId())) {
                sysLabel.setCreateBy(CED.getLoginUser().getEmpCd());
                sysLabel.setCreateOrgCd(CED.getLoginUser().getOrgCd());
                return this.sysLabelRepository.saveEntity(sysLabel);
            } else {
                throw new BusinessException("10010003", "传入参数有误，保存标签设置出错");
            }
        } else {
            throw new BusinessException("10010004", "未传入相关参数，保存标签设置出错");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysLabel updateSysLabel(SysLabel sysLabel) throws Exception {
        if (ObjectHelper.isNotEmpty(sysLabel)) {
            if (ObjectHelper.isNotEmpty(sysLabel.getId())) {
                SysLabel oldData = this.findSysLabelById(sysLabel.getId());
                oldData = (SysLabel) ObjectProperUtil.compareAndValue(sysLabel, oldData, false, null);
                oldData.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
                oldData.setUpdateBy(CED.getLoginUser().getEmpCd());
                return this.sysLabelRepository.updateEntity(oldData);
            } else {
                throw new BusinessException("1001003", "传入参数有误，更新标签设置出错");
            }
        } else {
            throw new BusinessException("10010004", "未传入相关参数，更新标签设置出错");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysLabel saveOrUpdateSysLabel(SysLabel sysLabel) throws Exception {
        if (ObjectHelper.isNotEmpty(sysLabel)) {
            if (ObjectHelper.isNotEmpty(sysLabel.getId())) {
                return this.updateSysLabel(sysLabel);
            } else {
                return this.saveSysLabel(sysLabel);
            }
        } else {
            throw new BusinessException("10010004", "未传入相关参数，更新或保存标签设置出错");
        }
    }

    @Override
    public List<SysLabel> findBylabelType(String labelType) throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();

        String hql = " from SysLabel where labelType = :labelType ";
        params.put("labelType", labelType);

        return sysLabelRepository.findByHql(hql, params);
    }
    /**
     * 通过标签解析标签脚本
     */

    @Override
    public Map<String, Object> getLabelValueSqlByKey(Map<String, Object> hsLabel) {
        Map<String, Object> resultsLabel= new HashMap<String, Object>();
        try {
            for (String k : hsLabel.keySet()) {
                Map<String, Object> params = new HashMap<String, Object>();
                //如：{年} 去掉{}方便数据查询，因为数据库的标签没有{} 
                params.put("labelName", k.replace("{", "").replace("}", ""));
                List<SysLabel> listSysLabel = sysLabelRepository.findByHql(" from SysLabel where labelName = :labelName and sceneType=0 ", params);
                if (listSysLabel.size() == 0) {//标签不存在
                    continue;
                }
                
                String sql = listSysLabel.get(0).getValueSQL();
                List<Map<String, Object>> tempList;
                //获取ValueSQL脚本
                tempList = sysLabelRepository.findListMapByCondition(sql, null);

                //标签返回的字段必须以“LABEL”为别名。
               // hsLabel.put(k, tempList.get(0).get("LABEL"));
                resultsLabel.put(k, tempList.get(0).get("LABEL"));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return resultsLabel;

    }
}
