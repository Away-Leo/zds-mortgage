package com.zdsoft.finance.app.casemanage.handleMortgage.vo;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AppSearchVo.java 
 * @ClassName: AppSearchVo 
 * @Description:  App产权状态Vo
 * @author zhoushichao 
 * @date 2017年3月2日 下午5:22:50 
 * @version V1.0
 */
public class AppSearchVo {


    /**
     * 产权状态Id
     */
    private String id;
    
    /**
     * 查册状态
     */
    private String searchStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSearchStatus() {
		return searchStatus;
	}

	public void setSearchStatus(String searchStatus) {
		this.searchStatus = searchStatus;
	}
}
