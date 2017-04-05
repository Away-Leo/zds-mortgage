package com.zdsoft.finance.businesssetting.vo;

import com.zdsoft.finance.businesssetting.entity.InComeBody;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title InComeBodyVo.java
 * @className InComeBodyVo
 * @description 收款主体VO
 * @author LiaoGuoWei
 * @create 2017/3/14 16:18
 * @version V1.0
 **/
public class InComeBodyVo extends BaseVo<InComeBody> {

	/**
	 * 描述
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 机构Id
	 */
	private String orgId;

	/**
	 * 父级机构ID
	 */
	private String orgPid;
	/**
	 * 收款主体
	 */
	private String inBody;

	/**
	 * 名称
	 */
	private String name;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 更新时间name
	 */
	private String createTimeName;
	/**
	 * 机构名称
	 */
	private String orgName;

	/**
	 * 多个机构ID
	 */
	private String orgIds;

	/**
	 * 更新时间
	 */
	private String updateTimeStr;

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public String getOrgPid() {
		return orgPid;
	}

	public void setOrgPid(String orgPid) {
		this.orgPid = orgPid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getInBody() {
		return inBody;
	}

	public void setInBody(String inBody) {
		this.inBody = inBody;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTimeName() {
		return createTimeName;
	}

	public void setCreateTimeName(String createTimeName) {
		this.createTimeName = createTimeName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public InComeBodyVo() {
		super();
	}

	public InComeBodyVo(InComeBody inComeBody) {
		super(inComeBody);
		if(ObjectHelper.isNotEmpty(inComeBody.getUpdateTime())){
			this.setUpdateTimeStr(DateHelper.dateToString(inComeBody.getUpdateTime(),DateHelper.DATE_SHORT_FORMAT));
		}
	}

	public InComeBody toPo() {
		InComeBody inComeBody = new InComeBody();
		return super.toPo(this, inComeBody);
	}

}
