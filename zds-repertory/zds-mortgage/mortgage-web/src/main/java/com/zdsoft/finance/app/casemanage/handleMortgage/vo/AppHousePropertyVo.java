package com.zdsoft.finance.app.casemanage.handleMortgage.vo;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AppHousePropertyVo.java 
 * @ClassName: AppHousePropertyVo 
 * @Description: App房产Vo
 * @author zhoushichao 
 * @date 2017年3月2日 下午5:18:41 
 * @version V1.0
 */
public class AppHousePropertyVo {

    /**
     * 房产Id
     */
    private String id;
    /**
     * 小区名称
     */
    private String communityName;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
    
}
