package com.zdsoft.finance.specialapprove.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.VoUtil;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveThings;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: SpecialApproveThingsVo.java 
 * @ClassName: SpecialApproveThingsVo 
 * @Description: 风险特批事项 VO
 * @author wangrongwei
 * @date 2017年2月15日 下午3:55:01 
 * @version V1.0 
 */ 
public class SpecialApproveThingsVo extends BaseVo<SpecialApproveThings> {

    private static final long serialVersionUID = 1L;

    /**
     * 事项编码
     */
    private String itemCode;
    
    /**
     * 事项类别
     */
    private String itemType;
    /**   
     * 类别名称
     */ 
    private String itemTypeName;
    
    /**
     * 风险事项类别
     * 		addBy jingjiyan
     * 		addTime 2017年2月22日10:58:40
     */
    private String riskItemType;
    /**   
     * 风险事项类别名称  
     */ 
    private String riskItemTypeName;
    
    /**
     * 事项名称
     */
    private String itemName;
    
    /**   
     * @Fields specialApproveId : 特批id  
     */ 
    private String specialApproveId;
    
    
    /**   
     * 申请时间
     *    	addBy jingjiyan
     * 		addTime 2017年2月22日10:58:40
     */ 
    private String applyDate;  
    
    /** 
     * @Fields otherInfo : 其它（当选择风险措施中的‘其它’时所保存对应的内容）
     */
    private String otherInfo;
    
    /**   
     * 特批状态
     *    	addBy jingjiyan
     * 		addTime 2017年2月22日17:00:22
     */ 
    private String specialApproveStatus;  
    private String specialApproveStatusName;  
    
    public SpecialApproveThings toPo(SpecialApproveThings specialApproveThings){
    	return super.toPo(this, specialApproveThings);
    }
    
    public SpecialApproveThingsVo() {
	}
    
    public SpecialApproveThingsVo(SpecialApproveThings po,String[] args,String []simpleArgs) {
    	VoUtil.copyPoperties(po, this, false, args, simpleArgs);
    }

	public SpecialApproveThingsVo(String itemCode, String itemType, String itemName, String specialApproveId,
			String otherInfo) {
		super();
		this.itemCode = itemCode;
		this.itemType = itemType;
		this.itemName = itemName;
		this.specialApproveId = specialApproveId;
		this.otherInfo = otherInfo;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSpecialApproveId() {
		return specialApproveId;
	}

	public void setSpecialApproveId(String specialApproveId) {
		this.specialApproveId = specialApproveId;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getItemTypeName() {
		return itemTypeName;
	}

	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}

	public String getRiskItemType() {
		return riskItemType;
	}

	public void setRiskItemType(String riskItemType) {
		this.riskItemType = riskItemType;
	}

	public String getRiskItemTypeName() {
		return riskItemTypeName;
	}

	public void setRiskItemTypeName(String riskItemTypeName) {
		this.riskItemTypeName = riskItemTypeName;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getSpecialApproveStatus() {
		return specialApproveStatus;
	}

	public void setSpecialApproveStatus(String specialApproveStatus) {
		this.specialApproveStatus = specialApproveStatus;
	}

	public String getSpecialApproveStatusName() {
		return specialApproveStatusName;
	}

	public void setSpecialApproveStatusName(String specialApproveStatusName) {
		this.specialApproveStatusName = specialApproveStatusName;
	}
	
}
