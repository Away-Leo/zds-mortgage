package com.zdsoft.finance.contract.vo;

import com.zdsoft.finance.common.utils.VoUtil;
import com.zdsoft.finance.contract.entity.BussPrintTplSet;

public class BussPrintTplSetVo extends BussPrintTplSet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//机构IDS
	private String orglist = null;
	//产品IDS
    private String productids=null;
    //产品名
    private String productNames;
	// 可用机构
	public String getOrglist() {
		return orglist;
	}

	public void setOrglist(String orglist) {
		this.orglist = orglist;
	}

	public BussPrintTplSetVo(BussPrintTplSet entity) throws Exception {
		VoUtil.copyPoperties(entity, this, false);
	}

	public BussPrintTplSet toPO() {
		BussPrintTplSet bussPrintTplSet = new BussPrintTplSet();

		VoUtil.copyPoperties(this, bussPrintTplSet, true);
		return bussPrintTplSet;
	}

	public BussPrintTplSetVo() {
		super();

	}

    public String getProductids() {
        return productids;
    }

    public void setProductids(String productids) {
        this.productids = productids;
    }

    public String getProductNames() {
        return productNames;
    }

    public void setProductNames(String productNames) {
        this.productNames = productNames;
    }
}
