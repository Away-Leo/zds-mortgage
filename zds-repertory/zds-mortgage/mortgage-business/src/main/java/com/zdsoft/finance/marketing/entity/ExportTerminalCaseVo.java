package com.zdsoft.finance.marketing.entity;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ExportTerminalCaseVo.java 
 * @ClassName: ExportTerminalCaseVo 
 * @Description: 终端进件导出数据Vo
 * @author xiongpan
 * @date 2017年3月13日 下午2:57:41 
 * @version V1.0 
 */ 
public class ExportTerminalCaseVo {
	
	/**
	 * 终端进件id
	 */
	private String ID;
	
	/**
	 * 申请金额
	 */
	private String APPLYAMOUNT;
	
	/**
	 * 抵押房产面积
	 */
	private String AREA;
	
	/**
	 * 案件状态
	 */
	private String CASEAPPLYSTATUS;

	
	/**
	 * 主借人姓名
	 */
	private String CUSTOMERNAME;
	
	/**
	 * 押品地址
	 */
	private String HOUSEPROPERTYADDRESS;
	
	/**
	 * 机构名称
	 */
	private String MECHANISMNAME;
	
	/**
	 * 押品所在楼层
	 */
	private Integer PLACEFLOOR;
	
	/**
	 * 父产品名称
	 */
	private String PRODUCTTYPENAME;
	
	/**
	 * 子产品名称
	 */
	private String PRODUCTSUBTYPENAME;
	
	/**
	 * 案件状态(阶段)
	 */
	private String STAGENM;
	
	/**
	 * 终端名称
	 */
	private String TERMINALFULLNAME;
	
	/**
	 * 综合评估价
	 */
	private String SYNTHESIZEPRICE;
	
	/**
	 * (手动输入的)评估价
	 */
	private String EVALUATINGPRICE;
	
	
	private String MAILINGADDRESS;
	private String STAGE;
	private String MECHANISMCODE;
	private String DISTRICT;
	private String TERMINALTYPE;
	private String STATUS;
	private String CITY;
	private String PROVINCE;
	
	

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getAPPLYAMOUNT() {
		return APPLYAMOUNT;
	}

	public void setAPPLYAMOUNT(String aPPLYAMOUNT) {
		APPLYAMOUNT = aPPLYAMOUNT;
	}

	public String getAREA() {
		return AREA;
	}

	public void setAREA(String aREA) {
		AREA = aREA;
	}

	public String getCASEAPPLYSTATUS() {
		return CASEAPPLYSTATUS;
	}

	public void setCASEAPPLYSTATUS(String cASEAPPLYSTATUS) {
		CASEAPPLYSTATUS = cASEAPPLYSTATUS;
	}

	public String getCUSTOMERNAME() {
		return CUSTOMERNAME;
	}

	public void setCUSTOMERNAME(String cUSTOMERNAME) {
		CUSTOMERNAME = cUSTOMERNAME;
	}

	public String getHOUSEPROPERTYADDRESS() {
		return HOUSEPROPERTYADDRESS;
	}

	public void setHOUSEPROPERTYADDRESS(String hOUSEPROPERTYADDRESS) {
		HOUSEPROPERTYADDRESS = hOUSEPROPERTYADDRESS;
	}

	public String getMECHANISMNAME() {
		return MECHANISMNAME;
	}

	public void setMECHANISMNAME(String mECHANISMNAME) {
		MECHANISMNAME = mECHANISMNAME;
	}

	public Integer getPLACEFLOOR() {
		return PLACEFLOOR;
	}

	public void setPLACEFLOOR(Integer pLACEFLOOR) {
		PLACEFLOOR = pLACEFLOOR;
	}

	public String getPRODUCTTYPENAME() {
		return PRODUCTTYPENAME;
	}

	public void setPRODUCTTYPENAME(String pRODUCTTYPENAME) {
		PRODUCTTYPENAME = pRODUCTTYPENAME;
	}

	public String getPRODUCTSUBTYPENAME() {
		return PRODUCTSUBTYPENAME;
	}

	public void setPRODUCTSUBTYPENAME(String pRODUCTSUBTYPENAME) {
		PRODUCTSUBTYPENAME = pRODUCTSUBTYPENAME;
	}

	public String getSTAGENM() {
		return STAGENM;
	}

	public void setSTAGENM(String sTAGENM) {
		STAGENM = sTAGENM;
	}

	public String getTERMINALFULLNAME() {
		return TERMINALFULLNAME;
	}

	public void setTERMINALFULLNAME(String tERMINALFULLNAME) {
		TERMINALFULLNAME = tERMINALFULLNAME;
	}

	public String getSYNTHESIZEPRICE() {
		return SYNTHESIZEPRICE;
	}

	public void setSYNTHESIZEPRICE(String sYNTHESIZEPRICE) {
		SYNTHESIZEPRICE = sYNTHESIZEPRICE;
	}

	public String getEVALUATINGPRICE() {
		return EVALUATINGPRICE;
	}

	public void setEVALUATINGPRICE(String eVALUATINGPRICE) {
		EVALUATINGPRICE = eVALUATINGPRICE;
	}

	public String getMAILINGADDRESS() {
		return MAILINGADDRESS;
	}

	public void setMAILINGADDRESS(String mAILINGADDRESS) {
		MAILINGADDRESS = mAILINGADDRESS;
	}

	public String getSTAGE() {
		return STAGE;
	}

	public void setSTAGE(String sTAGE) {
		STAGE = sTAGE;
	}

	public String getMECHANISMCODE() {
		return MECHANISMCODE;
	}

	public void setMECHANISMCODE(String mECHANISMCODE) {
		MECHANISMCODE = mECHANISMCODE;
	}

	public String getDISTRICT() {
		return DISTRICT;
	}

	public void setDISTRICT(String dISTRICT) {
		DISTRICT = dISTRICT;
	}

	public String getTERMINALTYPE() {
		return TERMINALTYPE;
	}

	public void setTERMINALTYPE(String tERMINALTYPE) {
		TERMINALTYPE = tERMINALTYPE;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public String getCITY() {
		return CITY;
	}

	public void setCITY(String cITY) {
		CITY = cITY;
	}

	public String getPROVINCE() {
		return PROVINCE;
	}

	public void setPROVINCE(String pROVINCE) {
		PROVINCE = pROVINCE;
	}


}
