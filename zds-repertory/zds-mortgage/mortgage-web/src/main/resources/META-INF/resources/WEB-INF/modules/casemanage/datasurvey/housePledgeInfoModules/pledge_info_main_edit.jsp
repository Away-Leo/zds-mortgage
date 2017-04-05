<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<style>
.info-tab .tabs>li>a{
	height:26px;
	line-height:16px;
}
</style>
<!-- 押品信息编辑tab -->
<div class="page-box">
	<div class="p5">
		<div class="info-tab" style="position: relative;">
			<ul class="tabs houseBox">
				<c:forEach items="${voList}" var="housePropertyVo" varStatus="status">
					<c:choose>
						<c:when test="${status.index==0}">
							<li class="tabs-on" id="house_${housePropertyVo.id}" ><a href="javascript:void(0);">${housePropertyVo.communityName}</a></li>
						</c:when>
						<c:otherwise>
							<li id="house_${housePropertyVo.id}"  closeformatter="houseClose"><a href="javascript:void(0);">${housePropertyVo.communityName}<span class="dynamic-close"> <i class="icon-dclose"></i></span></a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
			
			<button class="btn-orange mr10 mb5" style="position: absolute; right: 0; top: 0;" id="page-pledge">新增</button>
			<div class="tabcontents" id="dynamic-pledge"></div>
		</div>
	</div>
</div>

<script type="text/javascript">
	seajs.use([ 'jquery', 'zd/switch', 'zd/jquery.zds.page.callback' ],function($, tabSwitch,callback) {
		//押品信息(初始化)
		var url = '<z:ukey key="com.cnfh.rms.marketing.houseProperty.editHousePropertyById" context="admin"/>';
		
		//初始化一个	dynamic-pledge
		$("#dynamic-pledge").empty();
		//清除抵押情况的zdialog
		$("#pledgeEditDiv").closest(".zd-window").remove();
		//清除产权信息的zdialog
		$("#propertyEditDiv").closest(".zd-window").remove();
		//初始化
		$("#dynamic-pledge").load(url + "&housePropertyId=${voList[0].id}");
		
		//新增
		$("#page-pledge").click(function() {
			//提示是否保存,
			//取tab是第几个
			var that = $(this).parent().find(".tabs-on");
			var id = that.attr("id");
			var ids = id.split("_");
			var index_i = ids[1];
			if(index_i=="newId"){
				$.ZMessage.warning("警告", "请先保存后再新增!", function () {
              	 });
				return false;
			}
			
			var title = "押品信息";
			$(this).parent().find("ul>li").removeClass("tabs-on").addClass("tabs-disabled");
			$(this).parent().find("ul").append('<li class="tabs-on" id="house_newId" closeformatter="houseClose"><a href="javascript:void(0);">'+ title  + '<span class="dynamic-close"> <i class="icon-dclose"></i></span></a></li>');
			//删除以前的内容
			$("#dynamic-pledge").empty();
			$("#pledgeEditDiv").closest(".zd-window").remove();
			$("#propertyEditDiv").closest(".zd-window").remove();
			$("#dynamic-pledge").load(url);
			tabSwitch.init(); //初始化页签
		});
	
		var hasDeleteFlag=false;
		//点击li,load新页面
		$(".houseBox li").live("click",function() {
			if($(this).attr("id")=="house_newId"){
				return false;
			}
			if(!hasDeleteFlag){
				var that = $(this).parent().find(".tabs-on");
				var id = that.attr("id");
				var ids = id.split("_");
				var index_i = ids[1];
				if(index_i=="newId"){
					$.ZMessage.info("提示", "请先保存当前押品信息!", function () {
	              	 });
					return false;
				}
				loadHouse(this);
			}else{
				//已点击删除事件
				hasDeleteFlag=false;
			}
		});
			
		callback.houseClose=function(object){
			hasDeleteFlag = true;
			var that = object.prev();
			var id = object.attr("id");
			var ids = id.split("_");
			var houseId = ids[1];//id
			$.ZMessage.question("确认信息", "确认要删除?", function () {
				//删除样式数据
				$(".houseBox li").removeClass("tabs-disabled");
       			object.remove();
       			loadHouse(that);
       			
				if(houseId=="newId"){
           		}else{
           			$.ajax({
  		              type: 'post',
  		              url: '<z:ukey key="com.cnfh.rms.marketing.houseProperty.deleteHousePropertyById" context="admin"/>',
  		              data: 'housePropertyId='+houseId,
  		              dataType: 'json',
  		               success: function (data) {
  		                  if (data.resultStatus == 0) {
  		                        $.ZMessage.success("提示", data.msg, function () { });
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
		};
	
		function loadHouse(object){
		 	var that = $(object);
			//移出选中样式
			that.prevAll().removeClass("tabs-on");
			//添加选择样式
			that.addClass("tabs-on");
			var ids = that.attr("id");
			var id = ids.split("_");
			var idIndex = id[1];
			//删除以前的内容
			$("#dynamic-pledge").empty();
			$("#pledgeEditDiv").closest(".zd-window").remove();
			$("#propertyEditDiv").closest(".zd-window").remove();
			$("#dynamic-pledge").load(url + "&housePropertyId=" + idIndex);
			tabSwitch.init(); //初始化页签
		}
	});
</script>