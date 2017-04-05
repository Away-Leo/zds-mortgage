package com.zdsoft.finance.app.casemanage.handleMortgage.vo;

import java.util.List;

import com.zdsoft.finance.casemanage.handleMortgage.vo.DetainVo;
import com.zdsoft.finance.marketing.vo.HousePropertyVo;
import com.zdsoft.finance.marketing.vo.PledgeInfoVo;
import com.zdsoft.finance.marketing.vo.PropertyOwnerVo;
import com.zdsoft.finance.marketing.vo.SearchVo;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AppHousePropertyVo.java 
 * @ClassName: AppHousePropertyVo 
 * @Description: App办理抵押封装Vo
 * @author zhoushichao 
 * @date 2017年3月2日 下午5:11:03 
 * @version V1.0
 */
public class AppHandleMortgageVo {

	/**
	 * 房产信息Vo
	 */
	private HousePropertyVo housePropertyVo;
	/**
	 * 抵押Vo
	 */
	private List<PledgeInfoVo> pledgeInfoVoList;
	/**
	 * 产权状态Vo
	 */
	private SearchVo searchVo;
	/**
	 * 产权人Vo
	 */
	private List<PropertyOwnerVo> propertyOwnerVoList;
	/**
	 * 查册入押Vo
	 */
	private DetainVo detainVo;
	
	/**
	 * 
	 * @Title: setPledgeInfoVoLists 
	 * @Description: 转换抵押Vo
	 * @author zhoushichao 
	 * @param pledgeInfoVoLists  抵押json字符串
	 */
	@SuppressWarnings("unchecked")
	public void setPledgeInfoVoLists(String pledgeInfoVoLists){
		if(ObjectHelper.isNotEmpty(pledgeInfoVoLists)){
			JSONArray pledgeInfoVoListArray = JSONArray.fromObject(pledgeInfoVoLists);
			this.pledgeInfoVoList = JSONArray.toList(pledgeInfoVoListArray, new PledgeInfoVo(),new JsonConfig());
		}
	}
	/**
	 * 
	 * @Title: setPropertyOwnerVoLists 
	 * @Description: 转换产权人Vo
	 * @author zhoushichao 
	 * @param propertyOwnerVoLists 产权人json字符串
	 */
	@SuppressWarnings("unchecked")
	public void setPropertyOwnerVoLists(String propertyOwnerVoLists){
		if(ObjectHelper.isNotEmpty(propertyOwnerVoLists)){
			JSONArray propertyOwnerVoListArray = JSONArray.fromObject(propertyOwnerVoLists);
			this.propertyOwnerVoList = JSONArray.toList(propertyOwnerVoListArray, new PropertyOwnerVo(),new JsonConfig());
		}
	}
	
	public List<PledgeInfoVo> getPledgeInfoVoList() {
		return pledgeInfoVoList;
	}
	public void setPledgeInfoVoList(List<PledgeInfoVo> pledgeInfoVoList) {
		this.pledgeInfoVoList = pledgeInfoVoList;
	}
	public List<PropertyOwnerVo> getPropertyOwnerVoList() {
		return propertyOwnerVoList;
	}
	public void setPropertyOwnerVoList(List<PropertyOwnerVo> propertyOwnerVoList) {
		this.propertyOwnerVoList = propertyOwnerVoList;
	}
	public SearchVo getSearchVo() {
		return searchVo;
	}
	public void setSearchVo(SearchVo searchVo) {
		this.searchVo = searchVo;
	}
	public HousePropertyVo getHousePropertyVo() {
		return housePropertyVo;
	}
	public void setHousePropertyVo(HousePropertyVo housePropertyVo) {
		this.housePropertyVo = housePropertyVo;
	}
	public DetainVo getDetainVo() {
		return detainVo;
	}
	public void setDetainVo(DetainVo detainVo) {
		this.detainVo = detainVo;
	}
}
