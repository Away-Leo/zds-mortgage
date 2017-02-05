<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file='../../../common/common_js.jsp'%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<div class="frm-content">
	<div class="page-box">
	
		<div class="p10">
			<div class="info-tab" style="position: relative;">
				<ul class="tabs houseBox">
					<c:if test="${empty index }">
						<li class="tabs-on" id="pledge_0"><a href="javascript:void(0);">押品信息1</a></li>
					</c:if>
					<c:forEach var="i" items="${index }">
						<c:if test="${i==0 }">
							<li class="tabs-on" id="pledge_${i }"><a href="javascript:void(0);">押品信息${i+1 }</a></li>
						</c:if>
						<c:if test="${i!=0 }">
							<li id="pledge_${i }" closeformatter="beforeClose"><a href="javascript:void(0);">押品信息${i+1 }<span class="dynamic-close"> <i class="icon-dclose"></i></span></a></li>
						</c:if>
					</c:forEach>
				</ul>
				
				<button class="btn-orange mr10 mb5" style="position: absolute; right: 0; top: 0;" id="page-pledge">新增</button>
				<div class="tabcontents" id="dynamic-pledge"></div>
				
			</div>
			
		</div>

	</div>
</div>

<script type="text/javascript">
seajs.use([ 'jquery', 'zd/switch', 'zd/jquery.zds.page.callback' ],function($, tabSwitch,callback) {
	//查看还款计划(初始化)
	var url = '<z:ukey key="com.zdsoft.finance.caseManager.queryHousePropertyById" context="admin"/>&caseApplyId=${caseApplyId }';
	
	//当前操作的押品ID
	var t_houseId = '',newTable = false;
	var houseLength = ${fn:length(houseIds) };
	var houseIds=new Array();
	<c:forEach items="${houseIds }" var="t">  
		houseIds.push('${t }');
	</c:forEach>  
	t_houseId = houseIds.length == 0 ? '':houseIds[0];
	
	//初始化一个	dynamic-pledge
	$("#dynamic-pledge").empty();
	//初始化
	$("#dynamic-pledge").load(url + "&housePropertyId=" + t_houseId);
	
	//新增
	$("#page-pledge").click(function() {
		if(!t_houseId || newTable){
			$.ZMessage.warning("警告", "请先保存后再新增!", function () {
	     	});
			return false;
		}
		t_houseId = '';
		
		//提示是否保存,
		//取tab是第几个
		var that = $(this);
		var id = that.attr("id");
		var ids = id.split("_");
		var index_i = ids[1];
		var title = "押品信息"+(Number(houseLength) + 1);
		var pageIndex = "1";
		var index=parseInt(pageIndex)+1;
		$(this).find("a").data("index",index);
		var Index = $(this).index();
		$(this).parent().find("ul>li").removeClass("tabs-on");
		$(this).parent().find("ul").append('<li class="tabs-on" id="pledge_'+houseLength+'" closeformatter="beforeClose"><a href="javascript:void(0);">'+ title  + '<span class="dynamic-close"> <i class="icon-dclose"></i></span></a></li>');
		houseLength ++;
		//删除以前的内容
		$("#dynamic-pledge").empty();
		$("#pledgeEditDiv").closest(".zd-window").remove();
		$("#propertyEditDiv").closest(".zd-window").remove();
		$("#dynamic-pledge").load(url);
		tabSwitch.init(); //初始化页签
		t_houseId = '';
		newTable = true;
	});

	var hasDeleteFlag=false;
	//点击li,load新页面
	$(".houseBox li").live("click",function() {
		if(!hasDeleteFlag){
			loadContent(this);
		}else{
			//已点击删除事件
			hasDeleteFlag=false;
		}
	});
		
	callback.beforeClose=function(object){
		hasDeleteFlag = true;
		var that = object.prev();
		$.ZMessage.question("确认信息", "确认要删除?", function () {
			houseLength --;
			deletePledgeInfo(object);
			object.remove();
		});
	};

	function loadContent(object){
	 	var that = $(object);
		//移出选中样式
		that.prevAll().removeClass("tabs-on");
		//添加选择样式
		that.addClass("tabs-on");
		var ids = that.attr("id");
		var id = ids.split("_");
		var idIndex = id[1];
		t_houseId=houseIds[idIndex]==undefined?'':houseIds[idIndex];
		//删除以前的内容
		$("#dynamic-pledge").empty();
		$("#pledgeEditDiv").closest(".zd-window").remove();
		$("#propertyEditDiv").closest(".zd-window").remove();
		$("#dynamic-pledge").load(url + "&housePropertyId=" + t_houseId);
		tabSwitch.init(); //初始化页签
	}
		
	function deletePledgeInfo(object){
		//取当前点击标签所对应的ID
		var ids = $(object).attr("id");
		var id = ids.split("_");
		var idIndex = id[1];
		t_houseId=houseIds[idIndex]==undefined?'':houseIds[idIndex];
		if(newTable){
			newTable = false;
		}
		var array = [];
		if(t_houseId){
			array = {'housePropertyId':t_houseId+''};
			//删除押品信息
			$.ajax({
	            type: 'post',
	            url: '<z:ukey key="com.zdsoft.finance.caseManager.deleteHousePropertyById" context="admin"/>&jsoncallback=?',
	            data : array,
	            dataType: 'json',
	            async:true,
	            success: function (data) {
	            	console.log(data);
	                if (data.resultStatus == 0) {
	                	$.ZMessage.success("成功", data.msg, function () {
	                		//重新加载页面
	                		$('#showHouseInfo').load('<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.pledgeInfoMain" context="admin"/>&caseApplyId=${caseApplyId }');
	                  	});
	                }else{
	                	$.ZMessage.error("错误", data.msg, function () {
	                    });
	                }
	            },
		        error: function () {
		           	$.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
		            });
	            }
	        });
		}
		//删除完成后load上一个页签
		loadContent(object.prev());
	}
	//保存方法
	window.saveData = function(){
		var status = false;
		//验证
		var validationRe = $.ZUI.validateForm($('#houseProperty_form'));
		var validationSearchStatus = $.ZUI.validateForm($('#searchStatusForm'));
		if(!validationRe||!validationSearchStatus){
			return false;
		}
		//form取值 押品信息
		var houseInfoForm=$('#houseProperty_form').serialize();
		//form取值 产权状态
		var searchStatusForm=$('#searchStatusForm').serialize();
		//获取产权人信息
		var propertyList = $('#propertyList').ZTable("getRows");
		//抵押情况
		var pledgeList = $('#pledgeList').ZTable("getRows");
		
		//拼接参数
		var param = houseInfoForm + "&" + searchStatusForm + "&propertyList=" + JSON.stringify(propertyList) + "&pledgeList=" + JSON.stringify(pledgeList);
		console.log(param + "****************");
		$.ajax({
            type: 'post',
            url: '<z:ukey key="com.zdsoft.finance.caseManager.savePledgeInfo" context="admin"/>&jsoncallback=?',
            data : param ,
            dataType: 'json',
            success: function (data) {
            	console.log(data);
                if (data.resultStatus == 0) {
                	$.ZMessage.success("成功", data.msg, function () {
                		t_houseId = data.sourceKey;
                		if(!newTable){
	                		//将保存后的ID添加到houseIds
	                		houseIds.push(t_houseId);
	                		status = true;
                		}
                		//保存以后不再是新增页面
                		newTable = false;
                  	 });
                }else{
                	status = false;
                }
            },
	        error: function () {
	           	status = false;
            }
        });
		return status;
	}
	
});

</script>