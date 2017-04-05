package com.zdsoft.finance.casemanage.handleMortgage.vo;

import java.util.List;

import com.zdsoft.finance.marketing.vo.PledgeInfoVo;
import com.zdsoft.finance.marketing.vo.PropertyOwnerVo;
import com.zdsoft.finance.marketing.vo.SearchVo;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HandleMortgageVo.java 
 * @ClassName: HandleMortgageVo 
 * @Description: 办理抵押封装Vo
 * @author zhoushichao 
 * @date 2017年2月22日 下午3:55:33 
 * @version V1.0 
 */ 
public class HandleMortgageVo {

	/**
	 * 公证Vo
	 */
	private List<NotarizeVo> notarizeVoList;
	/**
	 * 抵押Vo
	 */
	private List<PledgeInfoVo> pledgeInfoVoList;
	/**
	 * 产权人Vo
	 */
	private List<PropertyOwnerVo> propertyOwnerVoList;
	/**
	 * 产权状态Vo
	 */
	private SearchVo searchVo;
	/**
	 * 查册入押Vo
	 */
	private DetainVo detainVo;
	/**
	 * 后补资料承诺Vo
	 */
	private List<MaterialPromiseVo> materialPromiseVoList;
	
	/**
	 * 
	 * @Title: setNotarizeLists 
	 * @Description: JSON字符串转换公证Vo
	 * @author zhoushichao 
	 * @param notarizeLists 公证JSON字符串
	 */
	@SuppressWarnings("unchecked")
	public void setNotarizeLists(String notarizeLists){
		if(ObjectHelper.isNotEmpty(notarizeLists)){
			JSONArray notarizeVoListArray = JSONArray.fromObject(notarizeLists);
			this.notarizeVoList = JSONArray.toList(notarizeVoListArray, new NotarizeVo(),new JsonConfig());
		}
	}
	/**
	 * 
	 * @Title: setPledgeInfoVoLists 
	 * @Description: JSON字符串转换抵押Vo
	 * @author zhoushichao 
	 * @param pledgeInfoVoLists  抵押JSON字符串
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
	 * @Description: JSON字符串转换产权人Vo
	 * @author zhoushichao 
	 * @param propertyOwnerVoLists 产权人JSON字符串
	 */
	@SuppressWarnings("unchecked")
	public void setPropertyOwnerVoLists(String propertyOwnerVoLists){
		if(ObjectHelper.isNotEmpty(propertyOwnerVoLists)){
			JSONArray propertyOwnerVoListArray = JSONArray.fromObject(propertyOwnerVoLists);
			this.propertyOwnerVoList = JSONArray.toList(propertyOwnerVoListArray, new PropertyOwnerVo(),new JsonConfig());
		}
	}
	/**
	 * 
	 * @Title: setMaterialPromises 
	 * @Description: JSON字符串后补资料承诺Vo
	 * @author zhoushichao 
	 * @param materialPromises  后补资料承诺JSON字符串
	 */
	@SuppressWarnings("unchecked")
	public void setMaterialPromises(String materialPromises){
		if(ObjectHelper.isNotEmpty(materialPromises)){
			JSONArray materialPromiseVoListArray = JSONArray.fromObject(materialPromises);
			this.materialPromiseVoList = JSONArray.toList(materialPromiseVoListArray, new MaterialPromiseVo(),new JsonConfig());
		}
	}
	
	public List<NotarizeVo> getNotarizeVoList() {
		return notarizeVoList;
	}
	public void setNotarizeVoList(List<NotarizeVo> notarizeVoList) {
		this.notarizeVoList = notarizeVoList;
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
	public DetainVo getDetainVo() {
		return detainVo;
	}
	public void setDetainVo(DetainVo detainVo) {
		this.detainVo = detainVo;
	}
	public SearchVo getSearchVo() {
		return searchVo;
	}
	public void setSearchVo(SearchVo searchVo) {
		this.searchVo = searchVo;
	}
	public List<MaterialPromiseVo> getMaterialPromiseVoList() {
		return materialPromiseVoList;
	}
	public void setMaterialPromiseVoList(List<MaterialPromiseVo> materialPromiseVoList) {
		this.materialPromiseVoList = materialPromiseVoList;
	}
}
