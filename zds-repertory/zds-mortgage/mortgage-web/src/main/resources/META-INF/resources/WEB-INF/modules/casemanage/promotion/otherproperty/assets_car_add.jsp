<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>    
<!-------------其他资产->汽车情况-------------->
<div class="page-box">
	<div class="p5">
		<div class="page-title">汽车情况
		   <button type="button"  class="btn-blue" id="addAssetsCarInfo" style="float:right;margin-right: 40px">新增</button>
		</div>
		<div class="p10">
		   <div id="assetsCarInfoList">
		   </div>
		</div>
   </div>
</div>
	
<!-- 新增汽车弹窗 -->
<div id="assetsCarEditDialog" style="display: none">
     <div id="assetsCarEditDiv" class="p20">
	     <form id="assetsCarEdit_form" class="zui-form " method="post" enctype="multipart/form-data">
        		<input id="car_caseApplyId" name="caseApplyId" value="${caseApplyId }" type="hidden"/>
        		<input id="assetsCarId" name="id" value="" type="hidden"/>
        		
		        <dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>型号</dt>
			         	<dd class="detail">
		                    <label> 
		                    	<input class="zui-input zui-validatebox" type="text" name="carType" id="carType" value="" validate-type="Require">
							</label>
		               	</dd>
		        </dl>
		        <dl class="form-item">
		       		<dt class="title"><b class="c-red mr5">*</b>使用年限</dt>
		            <dd class="detail">
		              <label> 
		              	<input class="zui-input zui-validatebox" type="text" id="useYear" name="useYear" value="" validate-type="Require,IsInteger,MinSize[1]"><front style="font-size: 14px;">年</front>
		              </label> 
		            </dd>
		       	</dl>
		       	
		        <dl class="form-item">
		       		<dt class="title"><b class="c-red mr5">*</b>价值</dt>
		            <dd class="detail">
		              <label> 
		              	<input class="zui-input zui-validatebox" type="text" id="car_worth" name="worth" value="" validate-type="Require,Digital[10-2],MinSize[1]"><front style="font-size: 14px;">元</front>
		              </label> 
		            </dd>
		       	</dl>
		        <dl class="form-item">
		       		<dt class="title"><b class="c-red mr5">*</b>权属人</dt>
		            <dd class="detail">
		              <label> 
		              	<input class="zui-input zui-validatebox" type="text" id="car_owner" name="owner" value="" validate-type="Require">
		              </label> 
		            </dd>
		       	</dl>
		       	
		       	<dl class="form-item">
		       		<dt class="title"><b class="c-red mr5">*</b>是否在押</dt>
		            <dd class="detail">
		              	<input class="zui-combobox zui-validatebox" type="hidden" value="" validate-type="Require"
		                     data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0049"
		                   data-valuefield="fullcode" data-textfield="name"  name="isPledge" id="car_isPledge" data-callback="car_isPledgeChange">
		            </dd>
		       	</dl>
		        <dl class="form-item aa" style="display: none">
		       		<dt class="title">抵押权人</dt>
		            <dd class="detail">
		              <label> 
		              	<input class="zui-input zui-validatebox" type="text" id="car_pledgePerson" name="pledgePerson" value="">
		              </label> 
		            </dd>
		       	</dl>
        		
		        <dl class="form-item aa" style="display: none">
		       		<dt class="title">抵押金额</dt>
		            <dd class="detail">
		              <label> 
		              	<input class="zui-input zui-validatebox" type="text" id="car_pledgeAmount" name="pledgeAmount" value="" validate-type="Digital[10-2],MinSize[1]"><front style="font-size: 14px;">元</front>
		              </label> 
		            </dd>
		       	</dl>
		        <dl class="form-item aa" style="display: none">
		       		<dt class="title">抵押期限</dt>
		            <dd class="detail">
		              	<input class="zui-input nwidth2 zui-validatebox" type="text" id="car_pledgeDeadLine" name="pledgeDeadLine" value="" validate-type="IsInteger,MinSize[1]" >
		            </dd>
 		            <dd class="detail">
		              	<input class="zui-combobox zui-validatebox" type="hidden" value="" data-width="94"
		                     data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=0931" data-defaultvalue="0931001"
		                   data-valuefield="fullcode" data-textfield="name"  name="pledgeDeadLineUnit" id="car_pledgeDeadLineUnit" >
		            </dd>
		       	</dl>
	   </form>
     </div>
</div>
	
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.table','zd/jquery.zds.form'], 
		function($, CALLBACK, ZTOOLS) {
		
		// 添加汽车的对话框
		$("#assetsCarEditDialog").Zdialog({
		   width: 700, height: 300, closed: true, title: "汽车",buttons: 
	       [
	           {
	               id: 'message-btn',
	               text: '确定',
	               buttonCls: 'btn-blue',
	               handler: function () {
	            	   var isValid = $.ZUI.validateForm($('#assetsCarEditDiv'));
	            	   if (isValid) {
		           		var assetsCarVo = $("#assetsCarEdit_form").serialize();
		        		var params = assetsCarVo;
		        		//保存汽车信息
		        		$.ajax({
		                    type: 'post',
		                    url: '<z:ukey key="com.zdsoft.finance.casemanage.promotion.otherproperty.saveAssetsCar" context="admin"/>',
		                    data: params,
		                    dataType: 'json',
		                    success: function (data) {
		                        if (data.resultStatus == 0) {
		                        	$.ZMessage.success("提示", "汽车保存成功", function () {
		                         		setTimeout(function(){
		                         			$("#assetsCarEditDialog").Zdialog("close");
		                         			$("#assetsCarInfoList").ZTable("reload",{});
		                                 },200);
	
		                          	 });
		                        }else{
		                        	$.ZMessage.error("错误", data.msg, function () {
		                            });
		                        }
		                    },
		        	            error: function () {
		        	            	$.ZMessage.error("错误", "兴业贷资调其他资产之汽车保存异常，请联系管理员", function () {
		        	             });
		        	            }
		                }); 
	            	   
	               }
	               }
	           },
	           {
	               id: 'message-btn',
	               text: '取消',
	                  buttonCls: 'btn-gray',
	                  handler: function () {
	                    $("#assetsCarEditDialog").Zdialog("close");
	                  }
	            }
	        ]
	   	});
		//金额千分位
		var formatterAmount = function(r,value){
			if(value){
				return ZTOOLS.formatCurrency(value+"");
			}  
			return '';
		}
		// 汽车情况列表
		$('#assetsCarInfoList').ZTable({
	       url : "<z:ukey key='com.zdsoft.finance.casemanage.promotion.getAssetsCarList' context='admin'/>&jsoncallback=?&caseApplyId=${caseApplyId}",
	       singleSelect : true,
	       isRowNum : true,
	       rows : 5,
	       pagination : true,
	       currentPage : 1,
	       idField: "ID",
	       tableCls : 'table-index',//table的样式
	       columns:[[
	    	  	{field : 'CARTYPE',title : '型号',align : 'center'},
				{field : 'USEYEAR',title : '使用年限',align : 'center',hidden:true},//隐藏字段
				{field : 'WORTH',title : '价值(元)',align : 'center',formatter:formatterAmount},
				{field : 'OWNER',title : '权属人', align : 'center'},
				{field : 'ISPLEDGE',title : '是否在押', align : 'center',hidden:true},
				{field : 'ISPLEDGENM',title : '是否在押',align : 'center'},
				{field : 'PLEDGEPERSON',title : '抵押权人', align : 'center',formatter:function(rowData,v){if(!v){return ""} return v}},
				{field : 'PLEDGEAMOUNT',title : '抵押金额(元)', align : 'center',formatter:formatterAmount},
				{field : 'PLEDGEDEADLINE',title : '抵押期限', align : 'center',hidden:true},
				{field : 'PLEDGEDEADLINEUNIT',title : '抵押期限单位', align : 'center',hidden:true},
				{field : 'PLEDGEDEADLINEALL',title : '抵押期限', align : 'center'},
				{field : 'ID',title : '操作', align : 'center',width:'20%',formatter:function(r,v){
					return '<a href="javaScript:void(0)" onclick="editAssetsCarHandle" class="btn-blue" >编辑</a>&nbsp;&nbsp;<a href="javaScript:void(0)" onclick="deleteAssetsCarHandle" class="btn-blue">删除</a>';
				}}
		] ],
		onDelete:function(index, rowData) {
			//  添加判断
			return true;
		},
		onLoadSuccess:function() {
			$("#assetsCarInfoList").find("td").each(function(){  
				if($(this).text().trim()=='null'){
					$(this).text("");  
				}
			});    
		}
		});
		
 		//编辑行
 		CALLBACK.editAssetsCarHandle = function(index,rowData) {
  	        $("#assetsCarId").val(rowData.ID);
  	        $("#carType").val(rowData.CARTYPE);
	        $("#useYear").val(rowData.USEYEAR);
	        $("#car_worth").val(rowData.WORTH);
	        $("#car_owner").val(rowData.OWNER);
	        $("#car_isPledge").ZCombobox('setValue',rowData.ISPLEDGE);
	        $("#car_pledgePerson").val(rowData.PLEDGEPERSON);
	        $("#car_pledgeAmount").val(rowData.PLEDGEAMOUNT);
	        $("#car_pledgeDeadLine").val(rowData.PLEDGEDEADLINE);
	        $("#car_pledgeDeadLineUnit").ZCombobox('setValue',rowData.PLEDGEDEADLINEUNIT);

            $("#assetsCarEditDialog").Zdialog('open');
		};
		
		//删除此条汽车情况
		CALLBACK.deleteAssetsCarHandle=function(index, rowData){
    		var params = "&assetsCarId="+rowData.ID;
			
    		$.ajax({
                type: 'post',
                url: '<z:ukey key="com.zdsoft.finance.casemanage.promotion.otherproperty.deleteAssetsCar" context="admin"/>',
                data: params,
                dataType: 'json',
                success: function (data) {
                    if (data.resultStatus == 0) {
                    	$.ZMessage.success("提示", "删除汽车成功", function () {
                     		setTimeout(function(){
                     			$("#assetsCarInfoList").ZTable("reload",{});
                             },200);

                      	 });
                    }else{
                    	$.ZMessage.error("错误", data.msg, function () {
                        });
                    }
                },
    	            error: function () {
    	            	$.ZMessage.error("错误", "兴业贷资调其他资产之汽车删除异常，请联系管理员", function () {
    	             });
    	            }
            });
		};
		//新增汽车弹窗
        $('#addAssetsCarInfo').click(function () {
        	$("#assetsCarId").val("");
            $('#assetsCarEditDialog').Zdialog("open");
        });
		
		//下拉回调
		CALLBACK.car_isPledgeChange = function(v,t){
			carChangeValue(v);
		}
		//初始化值
		$(function(){
			var v = $("#car_isPledge").val();
			carChangeValue(v);
		});
		//隐藏或显示在押情况
		function carChangeValue(v){
			if("YWDM0049002"==v){
				$(".aa").show();
			}else{
				$(".aa").hide();
			}
		}
		
         $.ZUI.initForms("#assetsCarEditDiv");
});
</script>