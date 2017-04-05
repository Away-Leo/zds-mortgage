package com.zdsoft.finance.filestore.service.impl;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.filestore.entity.DownHistory;
import com.zdsoft.finance.filestore.entity.FileStore;
import com.zdsoft.finance.filestore.repository.DownHistoryRepository;
import com.zdsoft.finance.filestore.service.DownHistoryService;
import com.zdsoft.finance.filestore.service.FileStoreService;
import com.zdsoft.finance.product.entity.MaterialList;
import com.zdsoft.finance.product.service.MateriaListService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title DownHistoryServiceImpl.java
 * @className DownHistoryServiceImpl
 * @description 下载历史service实现类
 * @author LiaoGuoWei
 * @create 2017/2/27 11:08
 * @version V1.0
 **/
@Service("downHistoryService")
public class DownHistoryServiceImpl extends BaseServiceImpl<DownHistory,DownHistoryRepository> implements DownHistoryService {


    @Autowired
    private CED CED;

    @Autowired
    private DownHistoryRepository downHistoryRepository;

    @Autowired
    private FileStoreService fileStoreService;

    @Autowired
    private MateriaListService materiaListService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DownHistory saveDownHis(DownHistory downHistory) throws Exception {
        if(ObjectHelper.isNotEmpty(downHistory)){
            downHistory.setCreateBy(CED.getLoginUser().getEmpCd());
            downHistory.setCreateOrgCd(CED.getLoginUser().getOrgCd());
            return this.downHistoryRepository.saveEntity(downHistory);
        }else{
            throw new BusinessException("10010004","未传入相关参数");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DownHistory> saveDownHisByFileStoreIds(String ids) throws Exception {
        if(ObjectHelper.isNotEmpty(ids)){
            //申明批量保存数据
            List<DownHistory> batchSaveData=new ArrayList<DownHistory>(20);
            //对传入的参数进行拆分
            String[] idAray=ids.split(",");
            for (int i = 0; i <idAray.length ; i++) {
                //得到附件的单个ID
                String fileStoreId=idAray[i];
                //查找相应附件
                FileStore fileStore=this.fileStoreService.findById(fileStoreId);
                //查找对应 产品资料清单
                MaterialList materiaList=this.materiaListService.findMateriaListById(fileStore.getMateriaId());
                
                DownHistory downHis=new DownHistory();
                downHis.setFileStore(fileStore);
                downHis.setMateriaCode(materiaList.getMateriaCode());
                downHis.setMateriaTypeCode(materiaList.getMateriaTypeCode());
                downHis.setLatestDownDate(DateHelper.dateToLong(new Date(),DateHelper.DATE_LONG_SIMPLE_FORMAT));
                downHis.setDownEmpCode(CED.getLoginUser().getEmpCd());
                downHis.setDownEmpName(CED.getLoginUser().getEmpNm());
                batchSaveData.add(downHis);
            }
            return this.downHistoryRepository.batchSave(batchSaveData);
        }else{
            throw new BusinessException("10010004","未传入相关参数");
        }
    }

    @Override
    public Page<DownHistory> findPageByCondition(Pageable pageable, DownHistory downHistory) throws Exception {
        if(ObjectHelper.isNotEmpty(downHistory)){
            return this.downHistoryRepository.findPageByCondition(pageable, downHistory);
        }else{
            throw new BusinessException("10010004","未传入相关参数");
        }
    }
}
