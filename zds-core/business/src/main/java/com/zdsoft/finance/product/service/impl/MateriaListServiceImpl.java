package com.zdsoft.finance.product.service.impl;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.MateriaListLimits;
import com.zdsoft.finance.product.entity.MaterialList;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.repository.MaterialListRepository;
import com.zdsoft.finance.product.service.MateriaListLimitsService;
import com.zdsoft.finance.product.service.MateriaListService;
import com.zdsoft.finance.product.service.ProductService;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: MateriaListServiceImpl.java
 * @ClassName: MateriaListServiceImpl
 * @Description: 资料清单
 * @author gufeng
 * @date 2017年3月2日 下午8:10:01
 * @version V1.0
 */
@Service("materiaListService")
public class MateriaListServiceImpl extends BaseServiceImpl<MaterialList, MaterialListRepository>
		implements MateriaListService {

	@Autowired
	private MaterialListRepository materialListRepository;
	@Autowired
	private ProductService productService;
	@Autowired
	MateriaListLimitsService materiaListLimitsService;

	@Override
	public MaterialList findMateriaListById(String id) throws BusinessException {
		if (ObjectHelper.isNotEmpty(id)) {
			MaterialList materialList = this.materialListRepository.findOne(id);
			if (ObjectHelper.isNotEmpty(materialList)) {
				return materialList;
			} else {
				throw new BusinessException("10010002", "根据参数未找到相应数据，通过ID查找资料清单数据出错");
			}
		} else {
			throw new BusinessException("10010004", "未传入相关参数，通过ID查找资料清单出错");
		}
	}

	@Override
	public void onlyOne(MaterialList bean)throws BusinessException {
		if (ObjectHelper.isEmpty(bean.getMateriaTypeCode()) || ObjectHelper.isEmpty(bean.getProductId())
				|| ObjectHelper.isEmpty(bean.getMateriaCode())) {
			throw new BusinessException("100000001", "数据错误materiaTypeCode:" + bean.getMateriaTypeCode() + ",productId:"
					+ bean.getProductId() + ",materiaCode:" + bean.getMateriaCode());
		}
		MaterialList po = null;
		if (ObjectHelper.isNotEmpty(bean.getId())) {
			po = materialListRepository.findOne(bean.getId());
		}
		if (ObjectHelper.isEmpty(po) || !bean.getMateriaTypeCode().equals(po.getMateriaTypeCode())
				|| !bean.getMateriaCode().equals(po.getMateriaCode()) || !bean.getProductId().equals(po.getProductId())) {
			List<MaterialList> onlys = materialListRepository.findByProductIdAndMateriaTypeCodeAndMateriaCodeAndIsDeleted(
					bean.getProductId(), bean.getMateriaTypeCode(), bean.getMateriaCode(), false);
			if (ObjectHelper.isNotEmpty(onlys) && onlys.size() != 0) {
				throw new BusinessException("10000000001","存在重复资料类型");
			}
		}
		if(ObjectHelper.isEmpty(po) || !bean.getRememberCode().equals(po.getRememberCode())){
			List<MaterialList> onlys = materialListRepository.findByProductIdAndRememberCode(bean.getProductId(), bean.getRememberCode());
			if(ObjectHelper.isNotEmpty(onlys) && onlys.size() != 0){
				throw new BusinessException("10000000002","助记码重复");
			}
		}
		if(ObjectHelper.isEmpty(po) || !bean.getRememberNo().equals(po.getRememberNo())){
			List<MaterialList> onlys = materialListRepository.findByProductIdAndRememberNo(bean.getProductId(), bean.getRememberNo());
			if(ObjectHelper.isNotEmpty(onlys) && onlys.size() != 0){
				throw new BusinessException("1000000003","数字助记码重复");
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public MaterialList saveOrUpdateMateriaList(MaterialList materialList) throws BusinessException {
		if (ObjectHelper.isEmpty(materialList) || ObjectHelper.isEmpty(materialList.getProductId())) {
			throw new BusinessException("10010004", "保存数据异常");
		}
		if (ObjectHelper.isNotEmpty(materialList.getId())) {// 更新
			MaterialList bean = materialListRepository.findOne(materialList.getId());
			BeanUtils.copyProperties(materialList, bean,
					new String[] { "productName", "createTime", "createBy", "createOrgCd" });
			return materialListRepository.updateEntity(materialList);
		} else {// 添加
			Product p = productService.findOne(materialList.getProductId());
			if (ObjectHelper.isNotEmpty(p)) {
				materialList.setProductName(p.getProductName());
			}
			if (ObjectHelper.isEmpty(materialList.getShowOrder())) {
				materialList.setShowOrder(0);
			}
			return materialListRepository.saveEntity(materialList);
		}
	}

	@Override
	public List<MaterialList> findByProductId(String productId) throws BusinessException {
		if (ObjectHelper.isEmpty(productId)) {
			logger.error("参数为空");
			throw new BusinessException("1000000001", "参数为空");
		}
		return materialListRepository.findByProductId(productId);
	}

	@Override
	public List<MaterialList> findByProductIdAndMaterial(String productId, String material) throws BusinessException {
		if (ObjectHelper.isEmpty(productId) || ObjectHelper.isEmpty(material)) {
			throw new BusinessException("10000000001", "传入参数错误,productId=" + productId + ",material=" + material);
		}
		List<MaterialList> list = null;
		list = materialListRepository.findByProductIdAndMateriaNm(productId, material);
		if (ObjectHelper.isNotEmpty(list) && list.size() > 0) {
			return list;
		}
		list = materialListRepository.findByProductIdAndMateriaCd(productId, material);
		if (ObjectHelper.isNotEmpty(list) && list.size() > 0) {
			return list;
		}
		list = materialListRepository.findByProductIdAndRememberCode(productId, material);
		if (ObjectHelper.isNotEmpty(list) && list.size() > 0) {
			return list;
		}
		try {
			Long l = Long.valueOf(material);
			list = materialListRepository.findByProductIdAndRememberNo(productId, l);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("1000000002", "不能转换为LONG");
		}
		if (ObjectHelper.isNotEmpty(list) && list.size() > 0) {
			return list;
		}
		return list;
	}

	@Override
	public MaterialList findByMateriaTypeCdAndProductIdAndMateriaCd(String materiaTypeCode, String productId,
			String materiaCode) throws BusinessException {
		if (ObjectHelper.isNotEmpty(materiaTypeCode) && ObjectHelper.isNotEmpty(productId)
				&& ObjectHelper.isNotEmpty(materiaCode)) {
			List<MaterialList> sourceData = this.materialListRepository
					.findByProductIdAndMateriaTypeCodeAndMateriaCodeAndIsDeleted(productId, materiaTypeCode,
							materiaCode, false);
			if (ObjectHelper.isNotEmpty(sourceData) && sourceData.size() > 0) {
				return sourceData.get(0);
			} else {
				throw new BusinessException("10010002", "根据参数未找到相应数据，根据产品、大类、小类未找到资料清单数据");
			}
		} else {
			throw new BusinessException("10010004", "未传入相关参数，通过产品、大类、小类查找资料清单出错");
		}
	}

	@Override
	public MaterialList findByProductIdAndMateriaCode(String productId, String materiaCode) throws Exception {
		if (ObjectHelper.isNotEmpty(productId) && ObjectHelper.isNotEmpty(materiaCode)) {
			MaterialList materialList = this.materialListRepository.findByProductIdAndMateriaCode(productId,
					materiaCode);
			if (ObjectHelper.isNotEmpty(materialList)) {
				return materialList;
			} else {
				throw new BusinessException("10010002", "根据参数未找到相应数据，根据产品ID和资料编号未找到资料清单数据");
			}
		} else {
			throw new BusinessException("10010004", "未传入相关参数，按照产品ID和资料编号查找资料清单出错");
		}
	}

	@Override
	public MaterialList findByProductIdAndMateriaName(String productId, String materiaName) throws BusinessException {
		if (ObjectHelper.isNotEmpty(productId) && ObjectHelper.isNotEmpty(materiaName)) {
			List<MaterialList> materialList = this.materialListRepository.findByProductIdAndMateriaName(productId,materiaName);
			return materialList.get(0);
		} else {
			throw new BusinessException("10010004", "未传入相关参数，按照产品ID和资料名称查找资料清单出错");
		}
	}

	// @Override
	// public Map<String,List<List<Map<String,Object>>>>
	// getTwoCodeData(List<MaterialList> materialLists,String outPath) throws
	// BusinessException {
	// Map<String,List<List<Map<String,Object>>>> returnData=new
	// HashMap<String,List<List<Map<String,Object>>>>();
	// logger.info("--------------------------------获得的服务器物理路径为："+outPath);
	// String QRtopPath=outPath+"QRtop\\";
	// logger.info("--------------------------------二维码文件存放的地址为："+QRtopPath);
	// Map<String,List<Map<String,Object>>> tempReturnData=new
	// HashMap<String,List<Map<String,Object>>>();
	// try{
	// if(ObjectHelper.isNotEmpty(materialLists)&&materialLists.size()>0&&ObjectHelper.isNotEmpty(outPath)){
	// //循环集合生成二维码并输出到服务器路径
	// for(MaterialList temp:materialLists){
	// if(!tempReturnData.containsKey(temp.getMateriaTypeCode())){
	// //生成中间返回数据的集合
	// List<Map<String,Object>> typeList=new ArrayList<Map<String,Object>>();
	// tempReturnData.put(temp.getMateriaTypeCode(),typeList);
	// //生成返回数据的元素
	// List<List<Map<String,Object>>> returnDataNode=new
	// ArrayList<List<Map<String,Object>>>();
	// returnData.put(temp.getMateriaTypeCode(),returnDataNode);
	// }
	// //二维码输出完整路径
	// String QRCodeFullPath=QRtopPath+temp.getMateriaTypeCode()+"\\";
	// //二维码文件名
	// String QRCodeName=temp.getMateriaCode();
	// //二维码中间LOGO完整路径
	// String
	// logoFullPath=QRtopPath+temp.getMateriaTypeCode()+"\\"+temp.getMateriaCode()+".jpg";
	// //生成二维码中间LOGO
	// ImageUtil.createStrImage(temp.getMateriaName(),logoFullPath);
	// File file=new File(logoFullPath);
	// //如果logo文件存在则生成二维码
	// if(file.exists()){
	// QRCodeUtil.encode(temp.getMateriaTypeName()+":"+temp.getMateriaName(),logoFullPath,QRCodeFullPath,QRCodeName,true);
	// }
	// String QRCodePath=QRCodeFullPath+QRCodeName+".png";
	// //如果成功生成二维码则进行数据的组装
	// if(new File(QRCodePath).exists()){
	// Map<String,Object> dataMap=new HashMap<String,Object>();
	// dataMap.put("name",temp.getMateriaName());
	// dataMap.put("path","/QRtop/"+temp.getMateriaTypeCode()+"/"+temp.getMateriaCode()+".png");
	// tempReturnData.get(temp.getMateriaTypeCode()).add(dataMap);
	// }
	// }
	//
	// for(String key:tempReturnData.keySet()){
	// List<Map<String,Object>> oneType=tempReturnData.get(key);
	// List<Map<String,Object>> perArray=new ArrayList<Map<String,Object>>();
	// int i=0;
	// for(Map<String,Object> temp:oneType){
	// if(perArray.size()>=8){
	// returnData.get(key).add(perArray);
	// perArray=new ArrayList<Map<String,Object>>();
	// }
	// perArray.add(temp);
	// i++;
	// if(i==oneType.size()){
	// returnData.get(key).add(perArray);
	// }
	// }
	// }
	// }
	// }catch (Exception e){
	// logger.error("生成二维码图片失败！",e);
	// e.printStackTrace();
	// }
	// return returnData;
	// }

	@Override
	@Transactional
	public void copy(Product oldProduct, Product newProduct, EmpDto empDto) throws BusinessException {
		if (ObjectHelper.isEmpty(oldProduct) || ObjectHelper.isEmpty(oldProduct.getId())
				|| ObjectHelper.isEmpty(newProduct)) {
			logger.error("参数不合法");
			throw new BusinessException("参数不合法");
		}

		List<MaterialList> list = this.findByProductId(oldProduct.getId());
		if (ObjectHelper.isNotEmpty(list)) {
			for (MaterialList materialList : list) {
				MaterialList newMaterialList = new MaterialList();
				BeanUtils.copyProperties(materialList, newMaterialList, new String[] { "id", "version", "isDeleted",
						"updateTime", "updateBy", "updateOrgCd", "productCode", "materiaListAuth" });
				newMaterialList.setCreateBy(empDto.getEmpCd());
				newMaterialList.setCreateOrgCd(empDto.getOrgCd());
				newMaterialList.setCreateTime(new Date());
				newMaterialList.setProductId(newProduct.getId());
				newMaterialList = this.saveEntity(newMaterialList);

			}
		}

	}

	@Override
	@Transactional
	public void logicDeleteMaterialAndMaterialLimit(String id) throws BusinessException {
		MaterialList materialList = this.findOne(id);
		String productId = materialList.getProductId();
		String materiaTypeCode = materialList.getMateriaTypeCode();
		this.logicDelete(materialList);
		List<MaterialList> list = materialListRepository.findByProductIdAndMateriaTypeCodeAndIsDeleted(productId,materiaTypeCode,false);
		if(ObjectHelper.isEmpty(list) || list.size() == 0){
			List<MateriaListLimits> materiaListLimits = materiaListLimitsService.getLimits(materialList.getProductId(),
					materialList.getMateriaTypeCode());
			for (int i = 0; i < materiaListLimits.size(); i++) {
				materiaListLimitsService.logicDelete(materiaListLimits.get(i));
			}
		}
	}

	@Override
	public MaterialList findByProductAndClass(String productId, String classType) throws BusinessException {
		if(ObjectHelper.isNotEmpty(productId)&&ObjectHelper.isNotEmpty(classType)){
			//按照产品ID和资料名称查找
			List<MaterialList> sourceDataMateriaName=this.materialListRepository.findByProductIdAndMateriaName(productId, classType);
			if(ObjectHelper.isNotEmpty(sourceDataMateriaName)&&sourceDataMateriaName.size()>0){
				return sourceDataMateriaName.get(0);
			}
			//按照产品ID和助记码查找
			List<MaterialList> sourceDataMateriaRememberCode=this.materialListRepository.findByProductIdAndRememberCode(productId, classType);
			if(ObjectHelper.isNotEmpty(sourceDataMateriaRememberCode)&&sourceDataMateriaRememberCode.size()>0){
				return sourceDataMateriaRememberCode.get(0);
			}
			//先判断是否为数字
			if(StringUtils.isNumeric(classType)){
				List<MaterialList> sourceDataMateriaRememberNo=this.materialListRepository.findByProductIdAndRememberNo(productId, Long.valueOf(classType));
				if(ObjectHelper.isNotEmpty(sourceDataMateriaRememberNo)&&sourceDataMateriaRememberNo.size()>0){
					return sourceDataMateriaRememberNo.get(0);
				}
			}
			return null;
		}else{
			throw new BusinessException("10010004","未传入相关参数");
		}
	}

}
