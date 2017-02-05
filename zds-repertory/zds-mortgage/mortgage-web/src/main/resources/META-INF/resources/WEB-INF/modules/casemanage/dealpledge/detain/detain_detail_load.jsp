<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>   
<div class="opinionDiv">
	<!-- 单个押品信息列表 -->
	<%@ include file="detain_house_single_list.jsp" %>
	<!-- 引入抵押情况 -->
	<%@ include file="../../../marketing/beforehandapply/pledge_info_edit.jsp"%>
	
	 <!-- 产权状态 -->
	<%@ include file="detain_pledge_status_edit.jsp"%>
	<!--  产权人信息 -->
	<%@ include file="../../../marketing/beforehandapply/property_owner_edit.jsp"%>

	<!-- 查册入押 -->
	<%@ include file="detain_edit.jsp" %>
</div>
<div class="save">
		<button id="btn-save" class="btn-blue mr10">保存</button>
		<button id="btn-submit" class="btn-blue mr10">提交</button>
</div>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form', 'zd/jquery.zds.validate','zd/jquery.zds.table','zd/jquery.zds.message'], 
function($, CALLBACK) {
	
	$("#btn-save").bind("click", function(){
		//获取抵押情况列表对象
		var pledgeList = $("#pledgeList").ZTable("getRows");
		//console.log(JSON.stringify(pledgeList));
		
		var pledgeListToString = JSON.stringify(pledgeList);
		//获取产权状态对象
		var searchVo = $("#searchStatusForm").serialize();
		//获取产权人信息列表对象
		var propertyList = $('#propertyList').ZTable("getRows");
		var propertyListToString = JSON.stringify(propertyList);
		console.log(JSON.stringify(propertyList));
		//获取查册入押对象
		var detainVo = $("#detain").serialize();
		console.log(detainVo);
		//统一校验
		if(!validationAll(pledgeList,propertyList)){
			return false;
		}
		//保存数据
		saveData(pledgeListToString,propertyListToString,detainVo,searchVo);
	});
	$.ZUI.init();
	
	//统一校验
	function validationAll(pledgeList, propertyList){
		//抵押情况校验是否为空列表
// 		if(pledgeList.length == 0){
// 			$.ZMessage.error("错误", "请添加抵押情况信息", function () {
//             });
// 			return false;
// 		}
		//产权状态校验
		var validateSearchSatusInfoForm = $.ZUI.validateForm($('#searchStatusForm'));
		if(!validateSearchSatusInfoForm){
			return false;
		} 
		//产权人信息列表校验为是否为空列表
// 		if(propertyList.length == 0){
// 			$.ZMessage.error("错误", "请添加产权人信息", function () {
//             });
// 			return false;
// 		}
		//查册入押表校验
		var validateDetainInfoForm = $.ZUI.validateForm($('#detain'));
		if(!validateDetainInfoForm){
			return false;
		}
		//校验成功
		return true;
	}
	
	//保存数据
	function saveData(pledgeListToString,propertyListToString,detainVo, searchVo){
		var params = "pledgeList=" + pledgeListToString + "&propertyList="+ propertyListToString + "&" + detainVo + "&" + searchVo;
		$.ajax({
			type:'post',
			url:'<z:ukey key="com.zdsoft.finance.casemanage.dealpledge.detain.saveDetain" context="admin"/>',
			data:params,
			dataType:'json',
			success:function(data){
				if(data.resultStatus == 0){
					$.ZMessage.success("成功", data.msg, function(){
						
					});
					
				}else{
					$.ZMessage.error("失败", data.msg, function(){
						
					})
				}
				
			},
			error:function(){
				$.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function(){
					
				});
			}
		
		});
	}
});
</script>
	
	
	
</div>
<!--抵押信息结束-->