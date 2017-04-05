<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="info-tab" style="position: relative;">
	<ul class="tabs" id="page-family">
		<c:if test="${empty vo }">
			<li class="tabs-on" id="family_newId" closeformatter="deleteFamily"><a href="javascript:void(0);" style="">户主名<span class="dynamic-close"> <i class="icon-dclose"></i></span></a></a></li>
		</c:if>
		<c:if test="${not empty vo }">
			<c:forEach items="${vo}" var="familyIncome" varStatus="status">
				<c:choose>
					<c:when test="${status.index==0}">
						<li class="tabs-on" id="family_${familyIncome.id}" closeformatter="deleteFamily"><a href="javascript:void(0);">${familyIncome.houseHolder}<span class="dynamic-close"> <i class="icon-dclose"></i></span></a></a></li>
					</c:when>
					<c:otherwise>
						<li id="family_${familyIncome.id}"  closeformatter="deleteFamily"><a href="javascript:void(0);">${familyIncome.houseHolder}<span class="dynamic-close"> <i class="icon-dclose"></i></span></a></a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</c:if>
	</ul>

	<button class="btn-orange mr10 mb5"
		style="position: absolute; right: 0; top: 0;" id="page-familyIncome">新增</button>
	<div class="tabcontents" id="familyIncome-tab"></div>
</div>
<script>
	var caseApplyId = "${caseApplyId}";
	seajs.use([ 'jquery', 'zd/switch', 'zd/jquery.zds.page.callback','zd/jquery.zds.message' ],function($, tabSwitch,callback) {
		var url = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.familyincomeinfo.edit" context="admin"/>';
		//初始化一个	dynamic-tabcontents
		$("#familyIncome-tab").empty();
		var familyIncomeId ="${vo[0].id}";
		$("#familyIncome-tab").load(url + "&familyIncomeId="+familyIncomeId+"&caseApplyId=${caseApplyId}",function(){
			tabSwitch.init(); //初始化页签
		});
		//新增
		$("#page-familyIncome").click(function() {
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
			var title = "户主名";
			$(this).parent().find("ul>li").removeClass("tabs-on").addClass("tabs-disabled");
			$(this).parent().find("ul").append('<li class="tabs-on" id="family_newId" closeformatter="deleteFamily" ><a href="javascript:void(0);">'+ title  + '<span class="dynamic-close"> <i class="icon-dclose"></i></span></a></li>');
			//删除以前的内容
			$("#familyIncome-tab").empty();
			$("#familyIncome-tab").load(url);
			tabSwitch.init(); //初始化页签
		});

		var hasDeleteFlag=false;
	 	//点击li,load新页面
		$("#page-family li").live("click",function() {
			if(!hasDeleteFlag){
				var that = $(this).parent().find(".tabs-on");
				var id = that.attr("id");
				var ids = id.split("_");
				var index_i = ids[1];
				if(index_i=="newId"){
					$.ZMessage.info("提示", "请先保存当前家庭收入!", function () {
	              	 });
					return false;
				}
				loadContent(this);
			}else{
				//已点击删除事件
				hasDeleteFlag=false;
			}
		});
		
	 	callback.deleteFamily=function(object){
	 		//已点击删除事件
       		hasDeleteFlag=true;
			var that = object.prev();
			var id = object.attr("id");
			var ids = id.split("_");
			var familyIncomeId = ids[1];//id
           	$.ZMessage.question("确认信息", "确认要删除?", function () {
           		//删除样式数据
				$("#page-family li").removeClass("tabs-disabled");
           		if(familyIncomeId=="newId"){
           			object.remove();
           			if(that.length!=0){
           				loadContent(that);
           			}else{
           				$("#familyIncome-tab").empty();
           				var title = "户主名";
           				$('#familyInComeInfo').find("ul").append('<li class="tabs-on" id="family_newId" closeformatter="deleteFamily" ><a href="javascript:void(0);">'+ title  + '<span class="dynamic-close"> <i class="icon-dclose"></i></span></a></li>');
           				//删除以前的内容
           				$("#familyIncome-tab").empty();
           				$("#familyIncome-tab").load(url);
           				tabSwitch.init(); //初始化页签
           			}
           		}else{
           			$.ajax({
  		              type: 'post',
  		              url: '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.familyincomeinfo.deleteFamilyIncomeById" context="admin"/>',
  		              data: 'familyIncomeId='+familyIncomeId,
  		              dataType: 'json',
  		               success: function (data) {
  		                  if (data.resultStatus == 0) {
  		                	    //删除数据
  		                	  	object.remove();
  		                	  if(that.length!=0){
	  		           				loadContent(that);
	  		           			}else{
	  		           				$("#familyIncome-tab").empty();
	  		           				var title = "户主名";
	  		           				$('#familyInComeInfo').find("ul").append('<li class="tabs-on" id="family_newId" closeformatter="deleteFamily" ><a href="javascript:void(0);">'+ title  + '<span class="dynamic-close"> <i class="icon-dclose"></i></span></a></li>');
	  		           				//删除以前的内容
	  		           				$("#familyIncome-tab").empty();
	  		           				$("#familyIncome-tab").load(url,function(){
	  		           				tabSwitch.init(); //初始化页签
	  		           				});
	  		           			}
  		                        $.ZMessage.success("提示", data.msg);
  	                        }else{
  	                        	$.ZMessage.error("错误", data.msg);
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
			var familyIncomeId = ids[1];//id
			//删除以前的内容
			$("#familyIncome-tab").empty();
			$("#contactEditDiv").closest(".zd-window").remove();
			$("#workUnitEditDiv").closest(".zd-window").remove();
			$("#familyIncome-tab").load(url + "&familyIncomeId=" + familyIncomeId+'&caseApplyId='+caseApplyId);
			tabSwitch.init(); //初始化页签
			//默认没有点击删除事件
			hasDeleteFlag=false;
		}
	});
</script>