package com.zdsoft.finance.credit.service.assist;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.finance.credit.service.CreditManageService;
import com.zdsoft.finance.customer.dto.CaseRelationCustomerDto;
import com.zdsoft.finance.customer.service.BeforeCustomerService;
import com.zdsoft.finance.filestore.entity.FileStore;
import com.zdsoft.finance.filestore.service.FileStoreService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.HttpClientHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditRequestService.java
 * @ClassName: CreditRequestService
 * @Description: 征信请求
 * @author gufeng
 * @date 2017年2月21日 上午9:58:11
 * @version V1.0
 */
@SuppressWarnings("restriction")
@Service
public class CreditRequestService {

	@Log
	private Logger logger;
	
	@Autowired
	private CreditManageService creditManageService;
	@Autowired
	private BeforeCustomerService beforeCustomerService;
	@Autowired
	private FileStoreService fileStoreService;
	@Autowired
	private CED CED;
	
	/**
	 * @Title: getOutDataInfo 
	 * @Description: 接口请求发送
	 * @author gufeng 
	 * @param caseApplyId 案件id
	 * @return 提示信息
	 */
	@Transactional
	public String getOutDataInfo(String caseApplyId) {
		// 获取申请人，配偶，借款人
		List<CaseRelationCustomerDto> list = null;
		try {
			list = beforeCustomerService.findRelationCustomerByCaseApplyId(caseApplyId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("simpleCode转换失败:", e);
			return "查询失败";
		}
		String creditNo = null;
		String creditType = null;
		String creditName = null;
		String customerId = null;
		if(ObjectHelper.isNotEmpty(list) && list.size() > 0){
			creditNo = list.get(0).getCredentialNo();
			creditType = CreditCommon.getCertificateTypeInterface(list.get(0).getCredentialType());
			creditName = list.get(0).getCustomerName();
			customerId = list.get(0).getCustomerId();
		}
		if(ObjectHelper.isEmpty(creditNo) || ObjectHelper.isEmpty(creditName)){
			return "身份证或姓名不能为空";
		}
		
		//身份证
		List<FileStore> sfzList = null;
		AttachmentDto sfzDto = null;
		try {
			sfzList = fileStoreService.findByMateriaCode(caseApplyId, customerId, "maclass_client13");
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error("查询身份证出错");
		}
		if(ObjectHelper.isNotEmpty(sfzList) && sfzList.size() > 0){
			try {
				sfzDto = CED.findAttachmentById(sfzList.get(0).getAttachmentId());
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("查询身份证附件出错");
			}
		}
		
		//授权书
		List<FileStore> sqsList = null;
		AttachmentDto sqsDto = null;
		try {
			sqsList = fileStoreService.findByMateriaCode(caseApplyId, customerId, "maclass_client14");
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error("查询身份证出错");
		}
		if(ObjectHelper.isNotEmpty(sfzList) && sfzList.size() > 0){
			try {
				sqsDto = CED.findAttachmentById(sqsList.get(0).getAttachmentId());
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("查询身份证附件出错");
			}
		}
		if(ObjectHelper.isEmpty(sfzDto) || ObjectHelper.isEmpty(sqsDto)){
			return "请上传身份证或授权书附件";
		}
		String objectId = caseApplyId + "_" + DateHelper.dateToString(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT);
		String m_certFile = sfzDto.getFilePath();
		String m_authoFile = sqsDto.getFilePath();
		
		try{
			//如果已经查询直接获取PDF去解析
			//DAL.ControllerFactory.GetNewController(DAL.e_ConsType.Main2).GetByCondition(Risk_BaseServiceLog.EntityType, string.Format("ObjectCardNo='{0}'and RiskID={1} AND DATEDIFF(minute,RequestDatetime,GETDATE())<={2}", creditNo, riskID, limiDate));
			Boolean isEmpty = creditManageService.getCreditData(creditNo,caseApplyId);
			
            if (isEmpty){
                return "姓名：" + creditName + ",身份证：" + creditNo + "，已成功申请，2分钟后方可重查，请等待或联系深圳驻点专员回复后，刷新页面查看征信查询记录!";
            }
            Boolean success= this.RequestInfoURL(objectId, creditName, creditType, creditNo, imgToBase64String(m_certFile), imgToBase64String(m_authoFile));
            if (success == false){
            	return "查询失败";
            }
		}catch(Exception e){
			e.printStackTrace();
			logger.error("征信请求:", e);
			return "查询失败";
		}
		
		return "发送查询成功等待征信服务器回传数据";
	}
	
	/** 
	 * @Title: getCreditOutInfo 
	 * @Description: 调用征信接口
	 * @author liuhuan 
	 * @param caseApplyId 案件id
	 * @param creditNo 证件号(身份证号)
	 * @param creditName 客户姓名
	 * @param sfzAttaId  身份证附件id
	 * @param sqsAttaId  授权书附件id
	 * @param credentialType 证件类型
	 * @return  
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean getCreditOutInfo(String caseApplyId,String creditNo,String creditName,String sfzAttaId,String sqsAttaId,String credentialType) throws Exception{
		if(ObjectHelper.isEmpty(caseApplyId) || ObjectHelper.isEmpty(creditNo) || ObjectHelper.isEmpty(creditName) 
					|| ObjectHelper.isEmpty(sfzAttaId) || ObjectHelper.isEmpty(sqsAttaId) || ObjectHelper.isEmpty(credentialType)){
			return false;
		}
		String objectId = caseApplyId + "_" + DateHelper.dateToString(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT);
		AttachmentDto sfzDto = CED.findAttachmentById(sfzAttaId);
		AttachmentDto sqsDto = CED.findAttachmentById(sqsAttaId);
		String m_certFile = sfzDto.getFilePath();
		String m_authoFile = sqsDto.getFilePath();
		String creditType = CreditCommon.getCertificateTypeInterface(credentialType);
        Boolean success= this.RequestInfoURL(objectId, creditName, creditType, creditNo, imgToBase64String(m_certFile), imgToBase64String(m_authoFile));
        if (success == false){
        	return false;
        }
		return true;
	}
	
	
	/// <summary>
    /// 从接口获取信息
    /// </summary>
    /// <param name="objectName">姓名/全称</param>
    /// <param name="cardNo">身份证/组织机构代码</param>
    /// <returns></returns>
	/**
	 * @Title: RequestInfoURL 
	 * @Description: 请求
	 * @author gufeng 
	 * @param objectId 业务id
	 * @param queryName 查询名字
	 * @param queryCertType 证件类型
	 * @param queryCredNum 证件号
	 * @param p_certFile 身份证
	 * @param p_authoFile 授权书
	 * @return 是否发送成功
	 */
	@Transactional(rollbackFor = Exception.class)
    public Boolean RequestInfoURL(String objectId, String queryName, String queryCertType, String queryCredNum, String p_certFile, String p_authoFile) throws BusinessException{
    	Boolean success = false;
        try {
			p_certFile = URLEncoder.encode(p_certFile, "UTF-8");
			p_authoFile = URLEncoder.encode(p_authoFile, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error("编码转换错误",e);
		}
//        String url = "http://192.168.33.169:8080/huamn-manage/pub/hm";
        String url = CreditParameter.getCreditRequestUrl();
        String userName = CreditParameter.getCreditRequestUserName();
        String password = CreditParameter.getCreditRequestPassword();
        String secretKey = CreditParameter.getCreditRequestKey();
        Map<String,String> map=new HashMap<String,String>();
        map.put("objectId", objectId);
		map.put("userName", userName);
		// 密码：fk_passss   //key：fkkksskey
		String key = "";
		try {
			key = DesUtil.encrypt(password + "|" + DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT), secretKey);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new BusinessException("加密错误",e1.getMessage());
		} // 将密码和key 进行加密
		map.put("key", key);
		map.put("beanName", "hm001"); // 固定值
//		map.put("queryName","张三丰");
		map.put("queryName",queryName); // 查询人姓名
//		map.put("queryCertType","0"); // 证件类型   证件类型 0 身份证 1 户口簿 2 护照 3 军官证 4 士兵证 5 港澳居民来往内地通行证 6 台湾同胞来往内地通行证 7 临时身份证 8 外国人居留证 9 警官证 A 香港身份证 B 澳门身份证
		map.put("queryCertType",queryCertType); // 证件类型   证件类型 0 身份证 1 户口簿 2 护照 3 军官证 4 士兵证 5 港澳居民来往内地通行证 6 台湾同胞来往内地通行证 7 临时身份证 8 外国人居留证 9 警官证 A 香港身份证 B 澳门身份证
//		map.put("queryCredNum","432321199012129120");
		map.put("queryCredNum",queryCredNum);
		map.put("certFile",p_certFile); // 身份证复印件
		map.put("certFileFormat","jpg"); // 身份证复印件格式
		map.put("authoFile",p_authoFile);
		map.put("authoFileFormat","jpg");
		String code = "";
		String mes = "";
		try {
			logger.info("征信请求地址为：{}", url);
			String data = HttpClientHelper.receivePostData(url, map);
			if(ObjectHelper.isNotEmpty(data)){
				JSONObject json = JSONObject.fromObject(data);
				@SuppressWarnings("unchecked")
				Iterator<String> keys = json.keys();
				while (keys.hasNext()) {
					String keyValue = keys.next();
					if ("CODE".equalsIgnoreCase(keyValue)) {
						code = (String)json.get(keyValue);
					}
					if ("MES".equalsIgnoreCase(keyValue)) {
						mes = (String)json.get(keyValue);
					}
				}
//				 String return_suc_code="00";
//				 String return_unexit_user="01"; //用户不存在
//				 String return_err_user="02";  // 用户或密码错误
//				 String return_err_key="03";  // 用户或密码错误
//				 String return_err_Interface="04";  // 调用接口失败
//				 String return_err_outtime="05";  // 校验码超时
//				 String return_err_ip="06";  // ip限制
//				 String return_err_ip="07";  // 参数错误
//				 String return_err_ip="08";  // 数据保存错误
//				 String return_err_code="99"; //系统运行异常
				if(ObjectHelper.isNotEmpty(code) && code.equals("00")){
					success = true;
				} else {
					logger.error("征信信息返回数据异常！" + "异常代码：" + code + ";异常信息：" + mes);
				}
			} else {
				logger.error("征信信息返回结果为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("发送请求出错",e);
			throw new BusinessException("10010007","发起征信请求失败！请联系管理员检查系统配置！");
		}
    	return success;
    }
    
	/**
	 * @Title: imgToBase64String 
	 * @Description: 图片转base64
	 * @author gufeng 
	 * @param imagefilename 图片路径加名字
	 * @return base64字符串
	 */
	private String imgToBase64String(String imagefilename){
		byte[] data = null;
		try{
        	InputStream in = new FileInputStream(imagefilename);
        	data = new byte[in.available()];
        	in.read(data);
        	in.close();
			BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(data);
        }catch (Exception ex){
        	ex.printStackTrace();
        	logger.error("图片编码" ,ex);
        }
		return "";
    }
}
