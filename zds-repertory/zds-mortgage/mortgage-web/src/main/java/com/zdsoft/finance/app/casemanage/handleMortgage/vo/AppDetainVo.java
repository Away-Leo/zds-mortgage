package com.zdsoft.finance.app.casemanage.handleMortgage.vo;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AppDetainVo.java 
 * @ClassName: AppDetainVo 
 * @Description: App查册入押Vo
 * @author zhoushichao 
 * @date 2017年3月2日 下午5:26:57 
 * @version V1.0
 */
public class AppDetainVo {
    
	/**
     * 查册入押Id
     */
    private String id;
    /**
     * 房产Id
     */
    private String housePropertyId;
    /**
     * 抵押模式确认code
     */
    private String pledgeType;
    /**
     * 抵押模式确认name
     */
    private String pledgeTypeName;
    /**
     * 现抵押权人
     */
    private String presentId;
    /**
     * 现抵押权人
     */
    private String presentName;
    /**
     * 现抵押时间
     */
    private Long presentPledgeDate;
    /**
     * 预计出他证时间
     */
    private Long predictCertifiedDate;
    
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHousePropertyId() {
        return housePropertyId;
    }
    public void setHousePropertyId(String housePropertyId) {
        this.housePropertyId = housePropertyId;
    }
    public String getPledgeType() {
        return pledgeType;
    }
    public void setPledgeType(String pledgeType) {
        this.pledgeType = pledgeType;
    }
    public String getPresentId() {
		return presentId;
	}
	public void setPresentId(String presentId) {
		this.presentId = presentId;
	}
	public String getPresentName() {
		return presentName;
	}
	public void setPresentName(String presentName) {
		this.presentName = presentName;
	}
	public Long getPresentPledgeDate() {
        return presentPledgeDate;
    }
    public void setPresentPledgeDate(Long presentPledgeDate) {
        this.presentPledgeDate = presentPledgeDate;
    }
    public Long getPredictCertifiedDate() {
        return predictCertifiedDate;
    }
    public void setPredictCertifiedDate(Long predictCertifiedDate) {
        this.predictCertifiedDate = predictCertifiedDate;
    }
    public String getPledgeTypeName() {
        return pledgeTypeName;
    }
    public void setPledgeTypeName(String pledgeTypeName) {
        this.pledgeTypeName = pledgeTypeName;
    }
}
