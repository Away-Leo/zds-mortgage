package com.zdsoft.finance.credit.dto;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditReceiveDto.java 
 * @ClassName: CreditReceiveDto 
 * @Description: 征信返回数据
 * @author gufeng 
 * @date 2017年2月21日 下午4:28:03 
 * @version V1.0
 */
public class CreditReceiveDto {

	private String funcName;  // 方法名
	private String jsonData; // 返回数据的JSon格式
	private String queryName; // 返回查询人姓名
	private String queryCertType; // 证件类型
	private String queryCredNum; // 证件号码
	private String objectId; // 业务主键  可以为空
	
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	public String getQueryName() {
		return queryName;
	}
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	public String getQueryCertType() {
		return queryCertType;
	}
	public void setQueryCertType(String queryCertType) {
		this.queryCertType = queryCertType;
	}
	public String getQueryCredNum() {
		return queryCredNum;
	}
	public void setQueryCredNum(String queryCredNum) {
		this.queryCredNum = queryCredNum;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	
}
