
package com.zdsoft.finance.credit.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.shoot.entity.PhotoTree;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PhotoTreeService.java
 * @ClassName: PhotoTreeService
 * @Description: 资调摄像照片管理 Service 
 * @author panshm
 * @date 2017年3月6日 上午9:31:04 
 * @version V1.0
 */
public interface PhotoTreeService extends BaseService<PhotoTree> {

	/**
	 * @Title: findShootPhotoInfo
	 * @Description: 通过案件号和关联环节取得资调照片数据
	 * @author panshm
	 * @param caseApplyCode 案件号
	 * @param linkCode 环节号
	 * @return List<PhotoTree>
	 */
	public List<PhotoTree> findShootPhotoInfo(String caseApplyCode, String linkCode);

	/**
	 * @Title: findShootPhotoDetail
	 * @Description: 通过案件号、关联环节和分类节点取得资调照片详细数据
	 * @author panshm
	 * @param caseApplyCode 案件号
	 * @param linkCode 环节号
	 * @param allNodeStr 完整分类节点
	 * @return List<PhotoTree>
	 */
	public List<PhotoTree> findShootPhotoDetail(String caseApplyCode, String linkCode, String allNodeStr);

	/**
	 * @Title: saveShootPhotoDatas
	 * @Description: 保存资调拍摄的照片，如果是最后一次上传，则同时生成pdf文件
	 * @author panshm
	 * @param addList 待添加的文件数据列表
	 * @param isOver 是否是最终上传
	 * @throws Exception
	 */
	public void saveShootPhotoDatas(List<PhotoTree> addList, String isOver) throws Exception;

	/**
	 * @Title: deleteShootPhotos
	 * @Description: 删除资调拍摄的照片，本操作要在保存之前
	 * @author panshm
	 * @param delList 待删除的文件数据列表
	 * @throws BusinessException
	 */
	public void deleteShootPhotos(List<PhotoTree> delList) throws BusinessException;

	/**
	 * @Title: saveShootPhotoFiles
	 * @Description: 保存资调拍摄的照片文件，可被多次调用
	 * @author panshm
	 * @param multipartfiles 文件流数组
	 * @throws BusinessException
	 * @throws IOException
	 */
	public void saveShootPhotoFiles(CommonsMultipartFile[] multipartfiles) throws BusinessException, IOException;

	/**
	 * @Title: saveShootPDF
	 * @Description: 保存资调拍摄照片对应的汇总PDF文件
	 * @author panshm
	 * @param caseApplyCode 案件号
	 * @param linkCode 环节编号
	 * @param preNodeStr allNodeStr前两个分类
	 * @param preNodeNameStr allNodeNameStr前两个分类
	 * @throws BusinessException
	 * @throws IOException
	 */
	public void saveShootPDF(String caseApplyCode, String linkCode,String preNodeStr,String preNodeNameStr) throws Exception;

	/**
	 *
	 * @Title: deleteByCaseApplyCodeAndAllNodeStrStartingWith
	 * @Description: 删除分类的记录（抵押物-抵押物1）
	 * @author denglw
	 * @param caseApplyCode 案件号
	 * @param allNodeStr 抵押物-抵押物1 前两个分类
	 * @return
	 */
	public int deleteByCaseApplyCodeAndAllNodeStrStartingWith(String caseApplyCode,String allNodeStr);

	/**
	 *
	 * @Title: deleteShootPdf
	 * @Description: 删除之前存的存的pdf(抵押物-抵押物1)
	 * @author denglw
	 * @param caseApplyCode 案件编号
	 * @param linkCode
	 * @return
	 * @throws Exception
	 */
	public void deleteShootPdf(String caseApplyCode,String linkCode,String nodeName)throws Exception;

	/**
	 *
	 * @Title: submitData
	 * @Description: 提交数据
	 * @author denglw
	 * @param addList 保存集合
	 * @param caseApplyCode 案件编号
	 * @param linkCode 阶段编码
	 * @param isOver 是否生成 pdf
	 * @throws Exception
	 */
	public void submitData(List<PhotoTree> addList,String caseApplyCode,String linkCode,String isOver) throws Exception;
}
