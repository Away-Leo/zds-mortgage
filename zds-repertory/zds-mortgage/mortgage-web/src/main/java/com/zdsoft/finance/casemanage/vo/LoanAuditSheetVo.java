package com.zdsoft.finance.casemanage.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.VoUtil;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: LoanAuditSheetVo.java 	
* @Package com.zdsoft.finance.casemanage.vo 	
* @Description: 贷审审批单
* @author Liyb 	
* @date 2017年1月14日 下午5:09:31 	
* @version V1.0 	
*/
public class LoanAuditSheetVo <T extends BaseEntity> extends BaseVo<T> {

	/**
     * 
     */
    private static final long serialVersionUID = -4654362951356296295L;
    
	
	public LoanAuditSheetVo() {
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
	public LoanAuditSheetVo(T t, String[] args, String[] simpleArgs) {
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
	public LoanAuditSheetVo(T t, String[] args, String[] simpleArgs, String[] dateArgs) {
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
	public LoanAuditSheetVo(T t, String[] args) {
		VoUtil.copyPoperties(t, this, false, args);
	}

	/**
	 * 构造器
	 * 
	 * @param t
	 *            对象
	 */
	public LoanAuditSheetVo(T t) {
		VoUtil.copyPoperties(t, this, false);
	}
}
