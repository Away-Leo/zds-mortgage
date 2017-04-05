package com.zdsoft.finance.contract.vo;

import com.zdsoft.finance.contract.vo.ConCaseContractSupplementVo;
import com.zdsoft.finance.contract.vo.ConCaseContractSupplement2Vo;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConCaseContractSupplementAllVo.java 
 * @ClassName: ConCaseContractSupplementAllVo 
 * @Description: 合同信息补充
 * @author wangnengduo
 * @date 2017年3月2日 下午5:33:12 
 * @version V1.0
 */

public class ConCaseContractSupplementAllVo {

	/**
	 * 合同信息补充1
	 */
	private ConCaseContractSupplementVo conCaseContractSupplementVo;
	/**
	 * 合同信息补充2
	 */
	private ConCaseContractSupplement2Vo conCaseContractSupplement2Vo;
	
	public ConCaseContractSupplementVo getConCaseContractSupplementVo() {
		return conCaseContractSupplementVo;
	}
	public void setConCaseContractSupplementVo(ConCaseContractSupplementVo conCaseContractSupplementVo) {
		this.conCaseContractSupplementVo = conCaseContractSupplementVo;
	}
	public ConCaseContractSupplement2Vo getConCaseContractSupplement2Vo() {
		return conCaseContractSupplement2Vo;
	}
	public void setConCaseContractSupplement2Vo(ConCaseContractSupplement2Vo conCaseContractSupplement2Vo) {
		this.conCaseContractSupplement2Vo = conCaseContractSupplement2Vo;
	}
}
