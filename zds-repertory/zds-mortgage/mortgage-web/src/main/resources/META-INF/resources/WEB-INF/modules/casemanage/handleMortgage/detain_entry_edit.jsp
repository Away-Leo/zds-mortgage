<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 查册入押 -->
<!-- 显示多个押品按钮 -->
<div class="page-box">
	<div class="p5">
		<div class="info-tab" style="position: relative;">
			<ul class="tabs" id="page-tab">
				<c:forEach items="${housePropertyIdList}" var="hz" varStatus="status">
						<c:if test="${status.index == 0}">
							<li class="tabs-on" id="${hz}" ><a href="javascript:void(0);">押品信息${status.index +1}</a></li>
						</c:if>
						<c:if test="${status.index != 0 }">
							<li id="${hz}" ><a href="javascript:void(0);" >押品信息${status.index +1}</a></li>
						</c:if>
				</c:forEach>
			</ul>
		</div>
		<!-- 查看案件基本信息 -->
		<%@ include file="common/case_apply_view.jsp" %>
		<!-- 单个押品信息-->
		<div class="tabcontents" id="showDetainAllInfo"></div>
	</div>
</div>
		
<script type="text/javascript">
	var tabNameId ="";
 	seajs.use([ 'jquery', 'zd/switch', 'zd/jquery.zds.page.callback'], function($,tabSwitch, CALLBACK) {
		//获取第一个tab的完整id
		var firstId = $("#page-tab>li:eq(0)").attr("id");
		//获取房产信息的controller的url
 		var url = '<z:ukey key="com.cnfh.rms.casemanage.handleMortgage.editDetain" context="admin"/>&caseApplyId=${caseApplyVo.id}&housePropertyId=';
 		//清除抵押情况的zdialog
		$("#pledgeEditDiv").closest(".zd-window").remove();
		//清除产权信息的zdialog
		$("#propertyEditDiv").closest(".zd-window").remove();
 		
 		//第一次初始化的URL
 		var firstUrl = url + firstId;
		$("#showDetainAllInfo").load(firstUrl);

		tabNameId = $("#page-tab>li:eq(0)").attr("id");
		//绑定页面所以li标签
 		$("#page-tab li").live("click", function(){
 			tabNameId = $(this).attr('id');
 			loadContent(this);
 		}); 
		
		//加载页面的公共方法
		function loadContent(obj){
			var thisObj = $(obj);
			//获取当前tab的id
			var id = thisObj.attr("id");
			//移除tab选中的样式
			thisObj.prevAll().removeClass("tabs-on");
			//添加选择样式
			thisObj.addClass("tabs-on");
			
			//清除当前tab的内容
			$("#showDetainAllInfo").empty();
			//清除抵押情况的zdialog
			$("#pledgeEditDiv").closest(".zd-window").remove();
			//清除产权信息的zdialog
			$("#propertyEditDiv").closest(".zd-window").remove();
			//加载选中的tab的内容
			$("#showDetainAllInfo").load(url+id);
			//初始化tab
			tabSwitch.init();
		}
		
		//设置保存过的页签信息
		var onValidate=JSON.parse('${validateJson}');
		var onValidateObj={};
		for(var i=0;i<onValidate.length;i++){
			onValidateObj[onValidate[i].tabName]=onValidate[i].executeTag;
		}
		var validateList={};
		$("#page-tab>li").each(function(){
			validateList[$(this).attr('id')]=0;
		})
		$.extend(validateList,onValidateObj);
		function onValidateShow(){
			for(var key in validateList){
				if(validateList[key]==1){
					$('li[id="'+key+'"]>a .tab-gou').remove();
					$('li[id="'+key+'"]>a').append('<span class="tab-gou"></span>');
				}
			}
			//判断页签是否全部保存完
	    	var allSaveStatus=true;
	    	for(var key in validateList){
	    		if(validateList[key]==0){
	    			allSaveStatus=false;
	    			break;
	    		}
	    	}
	    	if(allSaveStatus){
	    		//设置模块保存
				isValidate= true;
	    	}
		}
		$(function(){
			onValidateShow();
		});
		
		
		//保存事项模块验证
		window.saveValidateShow=function(){
			var validate = "businessKey=${caseApplyVo.id }";
			validate += "&matterName=bookEntry";
			validate += "&tabName=" + tabNameId;
			validate += "&executeTag=" + 1;
			$.ajax({
	              type: 'post',
	              url: '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.saveMatterModuleValidate" context="admin"/>',
	              data: validate,
	              async: false,
	              dataType: 'json',
	              success: function (data) {
	            	  validateList[tabNameId]=1;
	            	  onValidateShow();
	              },
		          error: function () {
		            $.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
		            });
		         }
	        });
		}
	   
 	}); 
</script>