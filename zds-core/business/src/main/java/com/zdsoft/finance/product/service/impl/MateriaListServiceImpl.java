package com.zdsoft.finance.product.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.ImageUtil;
import com.zdsoft.finance.common.utils.ObjectProperUtil;
import com.zdsoft.finance.common.utils.QRCodeUtil;
import com.zdsoft.finance.product.entity.MateriaListAuth;
import com.zdsoft.finance.product.entity.MaterialList;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.repository.MaterialListRepository;
import com.zdsoft.finance.product.service.MateriaListAuthService;
import com.zdsoft.finance.product.service.MateriaListService;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 资料清单service
 * @author LiaoGuoWei
 * @create 2016-12-26 11:29
 **/
@Service("materiaListService")
public class MateriaListServiceImpl extends BaseServiceImpl<MaterialList,MaterialListRepository> implements MateriaListService {

    @Autowired
    private MaterialListRepository materialListRepository;
    
    @Autowired
    private MateriaListAuthService materiaListAuthService;

    @Override
    public MaterialList findByMaterialListById(String materiaListId) throws BusinessException {
        if(ObjectHelper.isNotEmpty(materiaListId)){
            MaterialList sourceData=this.materialListRepository.findOne(materiaListId);
            if(ObjectHelper.isNotEmpty(sourceData)&&ObjectHelper.isNotEmpty(sourceData.getId())){
                return sourceData;
            }else{
                throw new BusinessException("10010002","根据参数未找到相应数据");
            }
        }else{
            throw new BusinessException("10010004","未传入相关参数");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MaterialList saveMateriaList(MaterialList materialList) throws BusinessException {
        if(ObjectHelper.isNotEmpty(materialList)&&ObjectHelper.isNotEmpty(materialList.getMateriaCode())){
            return this.materialListRepository.saveEntity(materialList);
        }else{
            throw new BusinessException("10010004","未传入相关参数");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MaterialList updateMateriaList(MaterialList materialList) throws BusinessException {
        if(ObjectHelper.isNotEmpty(materialList)){
            if(ObjectHelper.isNotEmpty(materialList.getId())){
                //获取老数据
                MaterialList old=this.findByMaterialListById(materialList.getId());
                //复制新数据
                old=(MaterialList) ObjectProperUtil.compareAndValue(materialList,old,false);
                //设置可空字段
                if(ObjectHelper.isEmpty(materialList.getRememberNo())){
                    old.setRememberNo(null);
                }
                old=this.materialListRepository.updateEntity(old);
                return old;
            }else{
                throw new BusinessException("10010003","传入参数有误！");
            }
        }else{
            throw new BusinessException("10010004","未传入相关参数");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MaterialList saveOrUpdateMateriaList(MaterialList materialList) throws BusinessException {
        if(ObjectHelper.isNotEmpty(materialList)){
            if(ObjectHelper.isNotEmpty(materialList.getId())){
                return this.updateEntity(materialList);
            }else{
                return this.saveEntity(materialList);
            }
        }else{
            throw new BusinessException("10010004","未传入相关参数");
        }
    }

    @Override
    public MaterialList findByMateriaTypeCdAndProductIdAndMateriaCd(String materiaTypeCode, String productId, String materiaCode) throws BusinessException {
        if(ObjectHelper.isNotEmpty(materiaTypeCode)&&ObjectHelper.isNotEmpty(productId)&&ObjectHelper.isNotEmpty(materiaCode)){
            MaterialList materialList=this.materialListRepository.findByMateriaTypeCodeAndProductCodeAndMateriaCode(materiaTypeCode,productId,materiaCode);
            if(ObjectHelper.isNotEmpty(materialList)&&ObjectHelper.isNotEmpty(materialList.getId())){
                return materialList;
            }else{
                throw new BusinessException("10010002","根据参数未找到相应数据");
            }
        }else{
            throw new BusinessException("10010004","未传入相关参数");
        }
    }

    @Override
    public List<MaterialList> findByTypeCdAndProductIdAndMateriaCd(String materiaTypeCode, String productId, String materiaCode) throws BusinessException {
        Map<String,Object> qryCondition=new HashMap<String,Object>();
        if(ObjectHelper.isNotEmpty(productId)){
            StringBuffer hql=new StringBuffer(" from MaterialList m where m.isDeleted = :isDeleted and m.productCode = :productCode ");
            qryCondition.put("isDeleted",!BaseEntity.DELETED);
            qryCondition.put("productCode",productId);
            //资料大类
            if(ObjectHelper.isNotEmpty(materiaTypeCode)){
                hql.append(" and m.materiaTypeCode = :materiaTypeCode ");
                qryCondition.put("materiaTypeCode",materiaTypeCode.trim());
            }
            //资料名称编号
            if(ObjectHelper.isNotEmpty(materiaCode)){
                hql.append(" and m.materiaCode = :materiaCode ");
                qryCondition.put("materiaCode",materiaCode);
            }
            hql.append(" order by m.createTime desc,m.materiaTypeCode desc ");
            List<MaterialList> sourceData=this.materialListRepository.findByHql(hql.toString(),qryCondition);
            if(ObjectHelper.isNotEmpty(sourceData)&&sourceData.size()>0){
                return sourceData;
            }else{
                throw new BusinessException("10010002","根据参数未找到相应数据");
            }
        }else{
            throw new BusinessException("10010004","未传入相关数据");
        }
    }

    @Override
    public Map<String,List<List<Map<String,Object>>>> getTwoCodeData(List<MaterialList> materialLists,String outPath) throws BusinessException {
        Map<String,List<List<Map<String,Object>>>> returnData=new HashMap<String,List<List<Map<String,Object>>>>();
        logger.info("--------------------------------获得的服务器物理路径为："+outPath);
        String QRtopPath=outPath+"QRtop\\";
        logger.info("--------------------------------二维码文件存放的地址为："+QRtopPath);
        Map<String,List<Map<String,Object>>> tempReturnData=new HashMap<String,List<Map<String,Object>>>();
        try{
            if(ObjectHelper.isNotEmpty(materialLists)&&materialLists.size()>0&&ObjectHelper.isNotEmpty(outPath)){
                //循环集合生成二维码并输出到服务器路径
                for(MaterialList temp:materialLists){
                    if(!tempReturnData.containsKey(temp.getMateriaTypeCode())){
                        //生成中间返回数据的集合
                        List<Map<String,Object>> typeList=new ArrayList<Map<String,Object>>();
                        tempReturnData.put(temp.getMateriaTypeCode(),typeList);
                        //生成返回数据的元素
                        List<List<Map<String,Object>>> returnDataNode=new ArrayList<List<Map<String,Object>>>();
                        returnData.put(temp.getMateriaTypeCode(),returnDataNode);
                    }
                    //二维码输出完整路径
                    String QRCodeFullPath=QRtopPath+temp.getMateriaTypeCode()+"\\";
                    //二维码文件名
                    String QRCodeName=temp.getMateriaCode();
                    //二维码中间LOGO完整路径
                    String logoFullPath=QRtopPath+temp.getMateriaTypeCode()+"\\"+temp.getMateriaCode()+".jpg";
                    //生成二维码中间LOGO
                    ImageUtil.createStrImage(temp.getMateriaName(),logoFullPath);
                    File file=new File(logoFullPath);
                    //如果logo文件存在则生成二维码
                    if(file.exists()){
                        QRCodeUtil.encode(temp.getMateriaTypeName()+":"+temp.getMateriaName(),logoFullPath,QRCodeFullPath,QRCodeName,true);
                    }
                    String QRCodePath=QRCodeFullPath+QRCodeName+".png";
                    //如果成功生成二维码则进行数据的组装
                    if(new File(QRCodePath).exists()){
                        Map<String,Object> dataMap=new HashMap<String,Object>();
                        dataMap.put("name",temp.getMateriaName());
                        dataMap.put("path","/QRtop/"+temp.getMateriaTypeCode()+"/"+temp.getMateriaCode()+".png");
                        tempReturnData.get(temp.getMateriaTypeCode()).add(dataMap);
                    }
                }

                for(String key:tempReturnData.keySet()){
                    List<Map<String,Object>> oneType=tempReturnData.get(key);
                    List<Map<String,Object>> perArray=new ArrayList<Map<String,Object>>();
                    int i=0;
                    for(Map<String,Object> temp:oneType){
                        if(perArray.size()>=8){
                            returnData.get(key).add(perArray);
                            perArray=new ArrayList<Map<String,Object>>();
                        }
                        perArray.add(temp);
                        i++;
                        if(i==oneType.size()){
                            returnData.get(key).add(perArray);
                        }
                    }
                }
            }
        }catch (Exception e){
            logger.error("生成二维码图片失败！",e);
            e.printStackTrace();
        }
        return returnData;
    }

    @Override
    public List<MaterialList> findByProductId(String productId) throws BusinessException {
        if(ObjectHelper.isEmpty(productId)){
            logger.error("参数为空");
            throw new BusinessException("参数为空");
        }
        return materialListRepository.findByProductId(productId);
    }

    @Override
    @Transactional
    public void copy(Product oldProduct,Product newProduct, EmpDto empDto) throws BusinessException {
        if(ObjectHelper.isEmpty(oldProduct) || ObjectHelper.isEmpty(oldProduct.getId()) || ObjectHelper.isEmpty(newProduct)){
            logger.error("参数不合法");
            throw new BusinessException("参数不合法");
        }

        List<MaterialList> list=this.findByProductId(oldProduct.getId());
        if(ObjectHelper.isNotEmpty(list)){
            for(MaterialList materialList:list){
                MaterialList newMaterialList=new MaterialList();
                BeanUtils.copyProperties(materialList, newMaterialList, new String[]{"id", "version", "isDeleted", "updateTime", "updateBy", "updateOrgCd", "productCode","materiaListAuth"});
                newMaterialList.setCreateBy(empDto.getEmpCd());
                newMaterialList.setCreateOrgCd(empDto.getOrgCd());
                newMaterialList.setCreateTime(new Date());
                newMaterialList.setProductCode(newProduct.getId());
                newMaterialList = this.saveEntity(newMaterialList);
                
                // 权限
                for(MateriaListAuth materiaListAuth:materialList.getMateriaListAuth()){
                	MateriaListAuth newMateriaListAuth=new MateriaListAuth();
                    BeanUtils.copyProperties(materiaListAuth, newMateriaListAuth, new String[]{"id", "version", "isDeleted", "updateTime", "updateBy", "updateOrgCd", "productCode","materialList"});
                    newMateriaListAuth.setProductCode(newProduct.getId());
                    newMateriaListAuth.setMaterialList(newMaterialList);
                    newMateriaListAuth.setCreateBy(empDto.getEmpCd());
                    newMateriaListAuth.setCreateOrgCd(empDto.getOrgCd());
                    newMateriaListAuth.setCreateTime(new Date());
                    materiaListAuthService.saveEntity(newMateriaListAuth);
                }
            }
        }

    }

    @Override
    public MaterialList findByProductAndClass(String productId, String classType) throws BusinessException {
        if(ObjectHelper.isNotEmpty(productId)&&ObjectHelper.isNotEmpty(classType)){
            //按照产品ID和资料名称查找
            List<MaterialList> sourceDataMateriaName=this.materialListRepository.findByProductCodeAndMateriaName(productId, classType);
            if(ObjectHelper.isNotEmpty(sourceDataMateriaName)&&sourceDataMateriaName.size()>0){
                return sourceDataMateriaName.get(0);
            }
            //按照产品ID和助记码查找
            List<MaterialList> sourceDataMateriaRememberCode=this.materialListRepository.findByProductCodeAndRememberCode(productId, classType);
            if(ObjectHelper.isNotEmpty(sourceDataMateriaRememberCode)&&sourceDataMateriaRememberCode.size()>0){
                return sourceDataMateriaRememberCode.get(0);
            }
            //先判断是否为数字
            if(StringUtils.isNumeric(classType)){
                List<MaterialList> sourceDataMateriaRememberNo=this.materialListRepository.findByProductCodeAndRememberNo(productId, Long.valueOf(classType));
                if(ObjectHelper.isNotEmpty(sourceDataMateriaRememberNo)&&sourceDataMateriaRememberNo.size()>0){
                    return sourceDataMateriaRememberNo.get(0);
                }
            }
            throw new BusinessException("10010002","根据参数未找到相应数据");
        }else{
            throw new BusinessException("10010004","未传入相关参数");
        }
    }
}
