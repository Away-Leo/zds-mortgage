package com.zdsoft.finance.capital.vo;

import com.zdsoft.finance.capital.entity.InstitutionFunds;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 资金机构配置信息Vo
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public class InstitutionFundsVo extends BaseVo<InstitutionFunds> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1111974703136878077L;

	/**
	 * 分公司编号
	 */
	private String orgCd;

	/**
	 * 分公司名称
	 */
	private String orgName;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 资方ids
	 */
	private String capitalIds;

	/**
	 * 资方names
	 */
	private String capitalNames;

	/**
	 * 更新时间字符串
	 */
	private String updateTimeStr;

	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCapitalIds() {
		return capitalIds;
	}

	public void setCapitalIds(String capitalIds) {
		this.capitalIds = capitalIds;
	}

	public String getCapitalNames() {
		return capitalNames;
	}

	public void setCapitalNames(String capitalNames) {
		this.capitalNames = capitalNames;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	public InstitutionFundsVo() {
		super();
	}

	public InstitutionFundsVo(InstitutionFunds institutionFunds) {
		super(institutionFunds, null, null, new String[] { "updateTime|yyyy/MM/dd" });
		if (ObjectHelper.isNotEmpty(institutionFunds.getCapitalists())) {
			StringBuffer cooperatorName = new StringBuffer();
			StringBuffer capitalistId = new StringBuffer();
			for (int i = 0; i < institutionFunds.getCapitalists().size(); i++) {
				if (i == (institutionFunds.getCapitalists().size() - 1)) {
					cooperatorName.append(institutionFunds.getCapitalists().get(i).getCooperatorName());
					capitalistId.append(institutionFunds.getCapitalists().get(i).getId());
				} else {
					cooperatorName.append(institutionFunds.getCapitalists().get(i).getCooperatorName() + ",");
					capitalistId.append(institutionFunds.getCapitalists().get(i).getId() + ",");
				}
			}

			this.setCapitalIds(capitalistId.toString());
			this.setCapitalNames(cooperatorName.toString());
		}
	}

	public InstitutionFunds toPo() {
		InstitutionFunds funds = new InstitutionFunds();
		return super.toPo(this, funds);
	}

}
