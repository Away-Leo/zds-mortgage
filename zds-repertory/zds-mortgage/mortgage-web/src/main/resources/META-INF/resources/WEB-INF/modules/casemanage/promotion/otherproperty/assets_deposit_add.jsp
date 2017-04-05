<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>  
<!-------------其他资产->存款情况-------------->
<div class="page-box">
	<div class="p5">
		<div class="page-title">存款情况
		   <button type="button"  class="btn-blue" id="addAssetsDepositInfo" style="float:right;margin-right: 40px">新增</button>
		</div>
		<div class="p10">
		   <div id="assetsDepositInfoList">
		   </div>
		</div>
   </div>
</div>
	
<!-- 新增存款弹窗 -->
<div id="assetsDepositEditDialog" style="display: none">
     <div id="assetsDepositEditDiv" class="p20">
	     <form id="assetsDepositEdit_form" class="zui-form " method="post" enctype="multipart/form-data">
        		<input id="deposit_caseApplyId" name="caseApplyId" value="${caseApplyId }" type="hidden"/>
        		<input id="assetsDepositId" name="id" value="" type="hidden"/>
        		
		        <dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>存款类型</dt>
			         	<dd class="detail">
			              	<input class="zui-combobox zui-validatebox" type="hidden" value=""
			                     data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0082"
			                   data-valuefield="fullcode" data-textfield="name"  name="depositType" id="depositType" data-callback="deposit_isDepositTypeChange" validate-type="Require">
		               	</dd>
		        </dl>
		        <dl class="form-item bb" style="display: none;">
		       		<dt class="title">期限</dt>
		            <dd class="detail">
		              <label> 
		              	<input class="zui-input nwidth2 zui-validatebox" type="text" id="depositDeadLine" name="depositDeadLine" value="" validate-type="IsInteger,MinSize[1]">
		              </label> 
		            </dd>
		            <dd class="detail">
		              	<input class="zui-combobox zui-validatebox" type="hidden" value="" data-width="94"
		                     data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=0931" data-defaultvalue="0931001"
		                   data-valuefield="fullcode" data-textfield="name"  name="depositDeadLineUnit" id="depositDeadLineUnit" data-toggle="combobox">
		            </dd>
		       	</dl>
		       	
		        <dl class="form-item">
		       		<dt class="title"><b class="c-red mr5">*</b>存款金额</dt>
		            <dd class="detail">
		              <label> 
		              	<input class="zui-input zui-validatebox" type="text" id="depositAmount" name="depositAmount" value="" validate-type="Require,Digital[10-2],MinSize[1]"><front style="font-size: 14px;">元</front>
		              </label> 
		            </dd>
		       	</dl>
	   </form>
     </div>
</div>
	
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.table'], 
		function($, CALLBACK, ZTOOLS) {
		
		// 添加存款的对话框
		$("#assetsDepositEditDialog").Zdialog({
		   width: 700, height: 300, closed: true, title: "存款",buttons: 
	       [
	           {
	               id: 'message-btn',
	               text: '确定',
	               buttonCls: 'btn-blue',
	               handler: function () {
	            	   var isValid = $.ZUI.validateForm($('#assetsDepositEditDiv'));
	            	   if (isValid) {
		           		var assetsDeposit = $("#assetsDepositEdit_form").serialize();
		        		var params = assetsDeposit;
		        		//保存存款
		        		$.ajax({
		                    type: 'post',
		                    url: '<z:ukey key="com.zdsoft.finance.casemanage.promotion.otherproperty.saveAssetsDeposit" context="admin"/>',
		                    data: params,
		                    dataType: 'json',
		                    success: function (data) {
		                        if (data.resultStatus == 0) {
		                        	$.ZMessage.success("提示", "保存存款成功", function () {
		                         		setTimeout(function(){
		                         			$("#assetsDepositEditDialog").Zdialog("close");
		                         			$("#assetsDepositInfoList").ZTable("reload",{});
		                                 },200);
	
		                          	 });
		                        }else{
		                        	$.ZMessage.error("错误", data.msg, function () {
		                            });
		                        }
		                    },
		        	            error: function () {
		        	            	$.ZMessage.error("错误", "兴业贷资调其他资产之存款保存异常，请联系管理员", function () {
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
	                    $("#assetsDepositEditDialog").Zdialog("close");
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
		
		// 存款情况列表
		$('#assetsDepositInfoList').ZTable({
	       url : "<z:ukey key='com.zdsoft.finance.casemanage.promotion.getAssetsDepositList' context='admin'/>&jsoncallback=?&caseApplyId=${caseApplyId}",
	       singleSelect : true,
	       isRowNum : true,
	       rows : 5,
	       pagination : true,
	       currentPage : 1,
	       idField: "ID",
	       tableCls : 'table-index',//table的样式
	       columns:[[
	    	  	{field : 'DEPOSITTYPE',title : '存款类型',align : 'center',hidden:true},
	    	  	{field : 'DEPOSITTYPENM',title : '存款类型',align : 'center'},
				{field : 'DEPOSITDEADLINE',title : '期限',align : 'center',hidden:true},
				{field : 'DEPOSITDEADLINEUNIT',title : '期限单位',align : 'center',hidden:true},//隐藏字段
				{field : 'DEPOSITDEADLINEALL',title : '期限',align : 'center'},
				{field : 'DEPOSITAMOUNT',title : '存款金额(元)', align : 'center',formatter:formatterAmount},
				{field : 'ID',title : '操作', align : 'center',width:'20%',formatter:function(r,v){
					return '<a href="javaScript:void(0)" onclick="editAssetsDepositHandle" class="btn-blue" >编辑</a>&nbsp;&nbsp;<a href="javaScript:void(0)" onclick="deleteAssetsDepositHandle" class="btn-blue">删除</a>';
				}}
		] ],
		onDelete:function(index, rowData) {
			//  添加判断
			return true;
		},
		onLoadSuccess:function() {
		}
		});
		
 		//编辑行
 		CALLBACK.editAssetsDepositHandle = function(index,rowData) {
  	        $("#assetsDepositId").val(rowData.ID);
	        $("#depositType").ZCombobox('setValue',rowData.DEPOSITTYPE);
	        $("#depositDeadLine").val(rowData.DEPOSITDEADLINE);
	        $("#depositDeadLineUnit").val(rowData.DEPOSITDEADLINEUNIT);
	        $("#depositAmount").val(rowData.DEPOSITAMOUNT);

            $("#assetsDepositEditDialog").Zdialog('open');
		};
		
		//删除此条存款情况
		CALLBACK.deleteAssetsDepositHandle=function(index, rowData){
    		var params = "&assetsDepositId="+rowData.ID;
			
    		$.ajax({
                type: 'post',
                url: '<z:ukey key="com.zdsoft.finance.casemanage.promotion.otherproperty.deleteAssetsDeposit" context="admin"/>',
                data: params,
                dataType: 'json',
                success: function (data) {
                    if (data.resultStatus == 0) {
                    	$.ZMessage.success("提示", "删除存款成功", function () {
                     		setTimeout(function(){
                     			$("#assetsDepositInfoList").ZTable("reload",{});
                             },200);

                      	 });
                    }else{
                    	$.ZMessage.error("错误", data.msg, function () {
                        });
                    }
                },
    	            error: function () {
    	            	$.ZMessage.error("错误", "兴业贷资调其他资产之存款删除异常，请联系管理员", function () {
    	             });
    	            }
            });
		};
		
		//新增有价证券弹窗
        $('#addAssetsDepositInfo').click(function () {
        	$("#assetsDepositId").val("");
            $("#assetsDepositEditDialog").Zdialog("open");
        });
        
		// 下拉回调
 		CALLBACK.deposit_isDepositTypeChange = function(v,t){
 			depositChangeValue(v);
		};
		// 初始化值
		$(function(){
			var v = $('#depositType').val();
			depositChangeValue(v);
		});
		//隐藏或显示存款期限
		function depositChangeValue(v){
			if("YWDM008201"==v){
				$(".bb").show();
			}else{
				$(".bb").hide();
			}
		}
		
		$.ZUI.initForms("#assetsDepositEditDiv");
});
</script>