package com.zdsoft.finance.common.base;

import java.io.Serializable;
import java.util.Date;

import com.zdsoft.finance.common.utils.VoUtil;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * Vo基础类
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public class BaseVo<T extends BaseEntity> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8366540686256259216L;

	/**
	 * id
	 */
	private String id;

	/**
	 * 版本号
	 */
	private String version;

	/**
	 * 是否删除
	 */
	private Boolean isDeleted;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 删除时间
	 */
	private Date deleteTime;

	/**
	 * 创建人
	 */
	private String createBy;

	/**
	 * 创建部门编号
	 */
	private String createOrgCd;

	/**
	 * 修改人
	 */
	private String updateBy;

	/**
	 * 修改部门编号
	 */
	private String updateOrgCd;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateOrgCd() {
		return createOrgCd;
	}

	public void setCreateOrgCd(String createOrgCd) {
		this.createOrgCd = createOrgCd;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateOrgCd() {
		return updateOrgCd;
	}

	public void setUpdateOrgCd(String updateOrgCd) {
		this.updateOrgCd = updateOrgCd;
	}

	/**
	 * 转换为PO,全复制
	 * 
	 * @param baseVo
	 *            Vo
	 * @param t
	 *            对象
	 * @return 对象
	 */
	public T toPo(BaseVo<?> baseVo, T t) {
		VoUtil.copyPoperties(baseVo, t, true);
		return t;
	}

	/**
	 * 转换Vo,选择性复制
	 * 
	 * @param baseVo
	 *            vo
	 * @param t
	 *            对象
	 * @param args
	 *            不复制的数组
	 * @return 对象
	 */
	public T toPo(BaseVo<?> baseVo, T t, String[] args) {
		VoUtil.copyPoperties(baseVo, t, true, args);
		return t;
	}

	/**
	 * 转换Vo,选择性复制,并自动转换simpleCode
	 * 
	 * @param baseVo
	 *            vo
	 * @param t
	 *            对象
	 * @param args
	 *            不复制的数组
	 * @param simpleArgs
	 *            转换simpleCode的属性,会将值赋给Nm的字段
	 * @return 对象
	 */
	public T toPo(BaseVo<?> baseVo, T t, String[] args, String[] simpleArgs) {
		VoUtil.copyPoperties(baseVo, t, true, args, simpleArgs);
		return t;
	}

	public BaseVo() {
		super();
	}

	/**
	 * 构造器
	 * 
	 * @param t
	 *            对象
	 * @param args
	 *            不复制的参数
	 * @param simpleArgs
	 *            需要转换simpleCode的属性
	 */
	public BaseVo(T t, String[] args, String[] simpleArgs) {
		VoUtil.copyPoperties(t, this, false, args, simpleArgs);
	}

	/**
	 * 构造器
	 * 
	 * @param t
	 *            对象
	 * @param args
	 *            不复制的参数
	 * @param simpleArgs
	 *            需要转换simpleCode的属性
	 */
	public BaseVo(T t, String[] args, String[] simpleArgs, String[] dateArgs) {
		VoUtil.copyPoperties(t, this, false, args, simpleArgs, dateArgs);
	}

	/**
	 * 构造器
	 * 
	 * @param t
	 *            对象
	 * @param args
	 *            不复制的参数
	 */
	public BaseVo(T t, String[] args) {
		VoUtil.copyPoperties(t, this, false, args);
	}

	/**
	 * 构造器
	 * 
	 * @param t
	 *            对象
	 */
	public BaseVo(T t) {
		VoUtil.copyPoperties(t, this, false);
	}
}
