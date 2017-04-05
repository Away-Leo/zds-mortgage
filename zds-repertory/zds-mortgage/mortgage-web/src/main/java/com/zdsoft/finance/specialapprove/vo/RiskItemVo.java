package com.zdsoft.finance.specialapprove.vo;

import com.zdsoft.finance.businesssetting.entity.ExceptMatter;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveThings;

import java.util.List;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: SpecialApproveThingsVo.java 
 * @ClassName: SpecialApproveThingsVo 
 * @Description: 风险特批事项 VO
 * @author wangrongwei
 * @date 2017年2月15日 下午3:55:01 
 * @version V1.0 
 */ 
public class RiskItemVo {

    /**
     * 事项类别
     */
    private String itemType;
    
    /**   
     * @Fields typeName : 事项类别名称
     */ 
    private String typeName;
    
    /**   
     * @Fields list : 特批事项（业务设置）
     */ 
    private List<ExceptMatter> list;
    
    /**   
     * @Fields listThing : 风险特批事项   （特批管理）
     */ 
    private List<SpecialApproveThings> listThing;
    
    public RiskItemVo() {
	}

	public RiskItemVo(String itemType, String typeName, List<ExceptMatter> list, List<SpecialApproveThings> listThing) {
		super();
		this.itemType = itemType;
		this.typeName = typeName;
		this.list = list;
		this.listThing = listThing;
	}

	public List<SpecialApproveThings> getListThing() {
		return listThing;
	}

	public void setListThing(List<SpecialApproveThings> listThing) {
		this.listThing = listThing;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<ExceptMatter> getList() {
		return list;
	}

	public void setList(List<ExceptMatter> list) {
		this.list = list;
	}
	
}
