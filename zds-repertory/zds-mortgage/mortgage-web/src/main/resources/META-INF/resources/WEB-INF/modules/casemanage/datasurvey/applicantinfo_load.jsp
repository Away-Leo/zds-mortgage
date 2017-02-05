<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not empty errorMsg }">页面初始化出现异常，请联系管理员！异常信息为：${errorMsg }</c:if>
<c:if test="${empty errorMsg }">
<div class="frm-content">
	<div class="page-box">
		<div class="p10">
			<div class="info-tab" style="position: relative;">
				<ul class="tabs" id="page-tab">
					<c:forEach items="${vo}" var="customers" varStatus="status">
						<c:choose>
							<c:when test="${status.index==0}">
								<li class="tabs-on" id="cust_${customers.id}" ><a href="javascript:void(0);">${customers.joinTypeName}:${customers.customerName}</a></li>
							</c:when>
							<c:otherwise>
								<li id="cust_${customers.id}"  closeformatter="beforeClose"><a href="javascript:void(0);">${customers.joinTypeName}:${customers.customerName}<span class="dynamic-close"> <i class="icon-dclose"></i></span></a></a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>

				<button class="btn-orange mr10 mb5"
					style="position: absolute; right: 0; top: 0;" id="page-tab1">新增</button>
				<div class="tabcontents" id="dynamic-tabcontents"></div>
			</div>
		</div>

	</div>
</div>
</c:if>
<script>
	seajs.use([ 'jquery', 'zd/switch', 'zd/jquery.zds.page.callback','zd/jquery.zds.message' ],function($, tabSwitch,callback) {
		var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.edit" context="admin"/>';
		//初始化一个	dynamic-tabcontents
		$("#dynamic-tabcontents").empty();
		$("#dynamic-tabcontents").load(url + "&customerId=${vo[0].id}&caseApplyId=${caseApplyId}");
		$("#page-tab1").click(function() {
			//提示是否保存,
			//取tab是第几个
			var that = $(this).parent().find("ul").find(".tabs-on");
			var id = that.attr("id");
			var ids = id.split("_");
			var index_i = ids[1];
			if(index_i=="newId"){
				$.ZMessage.warning("警告", "请先保存后再新增!", function () {
              	 });
				return false;
			}
			var title = "申请人信息";
			$(this).parent().find("ul>li").removeClass("tabs-on");
			$(this).parent().find("ul").append('<li class="tabs-on" id="cust_newId" closeformatter="beforeClose" ><a href="javascript:void(0);">'+ title  + '<span class="dynamic-close"> <i class="icon-dclose"></i></span></a></li>');
			//删除以前的内容
			$("#dynamic-tabcontents").empty();
			$("#dynamic-tabcontents").load(url);
			tabSwitch.init(); //初始化页签
		});

		var hasDeleteFlag=false;
	 	//点击li,load新页面
		$("#page-tab li").live("click",function() {
			if(!hasDeleteFlag){
				loadContent(this);
			}else{
				//已点击删除事件
				hasDeleteFlag=false;
			}
		});
		
	 	callback.beforeClose=function(object){
	 		//已点击删除事件
       		hasDeleteFlag=true;
			var that = object.prev();
			var id = object.attr("id");
			var ids = id.split("_");
			var customerId = ids[1];//id
           	$.ZMessage.question("确认信息", "确认要删除?", function () {
           		if(customerId=="newId"){
           			object.remove();
            	  	loadContent(that);
           		}else{
           			$.ajax({
  		              type: 'post',
  		              url: '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.delete" context="admin"/>',
  		              data: 'customerId='+customerId+"&caseApplyId=${caseApplyId}",
  		              dataType: 'json',
  		               success: function (data) {
  		                  if (data.resultStatus == 0) {
  		                	    //删除数据
  		                	  	object.remove();
  		                	  	loadContent(that);
  		                        $.ZMessage.success("提示", "删除成功", function () { });
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

			});
			 
	 	}
	
		function loadContent(object){
			 	var that = $(object);
				//移出选中样式
				that.prevAll().removeClass("tabs-on");
				//添加选择样式
				that.addClass("tabs-on");
				var id = that.attr("id");
				var ids = id.split("_");
				var customerId = ids[1];//id
				//删除以前的内容
				$("#dynamic-tabcontents").empty();
				$("#contactEditDiv").closest(".zd-window").remove();
				$("#workUnitEditDiv").closest(".zd-window").remove();
				$("#dynamic-tabcontents").load(url + "&customerId=" + customerId+'&caseApplyId=${caseApplyId}');
				tabSwitch.init(); //初始化页签
				//默认没有点击删除事件
				hasDeleteFlag=false;
		}
		
		//ZDS_MESSAGE_CLIENT.closeBeforeTab
});
</script>