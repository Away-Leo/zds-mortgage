package com.zdsoft.finance.credit.service.assist;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import net.sf.json.JSONObject;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditReceiveService.java 
 * @ClassName: CreditReceiveService 
 * @Description: 征信解析
 * @author gufeng 
 * @date 2017年2月22日 上午11:26:23 
 * @version V1.0
 */
@Service("creditReceiveService")
public class CreditReceiveService {

	@Autowired
	private CreditSaveService creditSaveService;
	
	@Log
	private Logger logger;
	
	@Autowired
	private CED CED;
	
	/**
	 * @Title: receiveJson 
	 * @Description: 解析json
	 * @author gufeng 
	 * @param queryId 查询id
	 * @param idCard 证件号
	 * @param jsonData 数据
	 * @throws BusinessException 解析异常
	 */
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor = Exception.class)
	public void receiveJson(String queryId, String idCard, String jsonData)throws BusinessException {
		//转map
		JSONObject  jasonObject = JSONObject.fromObject(jsonData);
		Map<String, Object> map = (Map<String, Object>)jasonObject;
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			Map<String,String> className = CreditCommon.getClassName(entry.getKey());
			String type = className.get("TYPE");
			String name = className.get("NAME");
			logger.info("=====>type:" + type + "====>name:" + name);
			Object obj = entry.getValue();
			logger.info("=====>data:" + obj);
			if(ObjectHelper.isEmpty(obj) || ObjectHelper.isEmpty(obj.toString())){
				 continue;
			}
			if(type.equals("BEAN")){
				Map<String,Object> data = (Map<String, Object>) obj;
				this.creditSaveService.saveBean(name,data,queryId,idCard);
			}else{
				List<Map<String,Object>> dataList = (List<Map<String, Object>>) obj;
				this.creditSaveService.saveListBean(name,dataList,queryId,idCard);
			}
		}
	}

	/**
	 * @Title: receivePDF 
	 * @Description: 解析pdf
	 * @author gufeng 
	 * @param queryId 查询id
	 * @param queryName 查询名字
	 * @param jsonData 数据
	 * @return 附件信息
	 * @throws BusinessException 转换异常
	 */
	@Transactional(rollbackFor = Exception.class)
	public AttachmentDto receivePDF(String queryId,String queryName,String jsonData) throws BusinessException{
		String fileName = "个人征信报告-" + queryName + ".pdf";
		AttachmentDto dto = null;
		try {
			dto = CED.uploadFileByBase64String("CreditSystem", fileName, jsonData);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("平台数据上传失败",e);
			throw new BusinessException("100000003","保存征信报告发生错误！");
		}
		// 保存附件
		// 不保存到业务附件中，该文件不允许下载  update by jingyh
		/*HmQuery query = hmQueryService.findOne(queryId);
		String caseApplyId = null;
		if(ObjectHelper.isEmpty(query)){
			throw new BusinessException("100000002","queryId未找到数据:" + queryId);
		}
		if(ObjectHelper.isNotEmpty(query.getObjectId())){
			caseApplyId = query.getObjectId();
			if(query.getObjectId().indexOf("_") != -1){
				caseApplyId = caseApplyId.split("_")[0];
			}
		}
		try {
			fileStoreService.saveForCredit(caseApplyId, "个人征信报告+" + queryName + ".pdf", queryId,dto.getId(),null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("100000003","保存业务附件出错caseApplyId:" + caseApplyId + "==>queryId:" + queryId);
		}*/
		return dto;
	}

}
