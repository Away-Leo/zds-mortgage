package com.zdsoft.finance.cooperator.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.cooperator.entity.CooperateIdea;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CooperateIdeaVo.java 
 * @ClassName: CooperateIdeaVo 
 * @Description: 合作协议Vo
 * @author liuwei
 * @date 2017年3月9日 上午11:27:00 
 * @version V1.0
 */
public class CooperateIdeaVo extends BaseVo<CooperateIdea> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 协议名称
	 */
	private String agreementName;
	/**
	 * 附件
	 */
	private String attachmentId;
	
	/**
	 * 附件名称
	 */
	private String attachName;
	/**
	 * 对应的资方
	 */
	private String capitalistId;

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getAttachName() {
		return attachName;
	}

	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	public String getAgreementName() {
		return agreementName;
	}

	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
	}

	public String getCapitalistId() {
		return capitalistId;
	}

	public void setCapitalistId(String capitalistId) {
		this.capitalistId = capitalistId;
	}

	public CooperateIdeaVo() {
		super();
	}

	public CooperateIdeaVo(CooperateIdea t) {
		super(t);
	}

	public CooperateIdeaVo(CooperateIdea t, String[] args, String[] simpleArgs) {
		super(t, args, simpleArgs);
	}

	public CooperateIdea toPO() {
		CooperateIdea po = new CooperateIdea();
		return super.toPo(this, po);
	}
}
