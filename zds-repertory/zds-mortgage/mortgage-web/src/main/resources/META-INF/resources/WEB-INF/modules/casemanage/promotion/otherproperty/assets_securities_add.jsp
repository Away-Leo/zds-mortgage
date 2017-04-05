<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>  
<!-------------其他资产->有价证券-------------->
<div class="page-box">
	<div class="p5">
		<div class="page-title">有价证券情况
		   <button type="button"  class="btn-blue" id="addAssetsSecuritiesInfo" style="float:right;margin-right: 40px">新增</button>
		</div>
		<div class="p10">
		   <div id="assetsSecuritiesInfoList">
		   </div>
		</div>
   </div>
</div>
	
<!-- 新增有价证券弹窗 -->
<div id="assetsSecuritiesEditDialog" style="display: none">
     <div id="assetsSecuritiesEditDiv" class="p20">
	     <form id="assetsSecuritiesEdit_form" class="zui-form " method="post" enctype="multipart/form-data">
        		<input id="securities_caseApplyId" name="caseApplyId" value="${caseApplyId }" type="hidden"/>
        		<input id="assetsSecuritiesId" name="id" value="" type="hidden"/>
        		
		        <dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>权属人(受益人)</dt>
			         	<dd class="detail">
		                    <label> 
		                    	<input class="zui-input zui-validatebox" type="text" name="owner" id="securities_owner" value="" validate-type="Require">
							</label>
		               	</dd>
		        </dl>
		        <dl class="form-item">
		       		<dt class="title"><b class="c-red mr5">*</b>价值</dt>
		            <dd class="detail">
		              <label> 
		              	<input class="zui-input zui-validatebox" type="text" id="securities_worth" name="worth" value="" validate-type="Require,Digital[10-2],MinSize[1]"><front style="font-size: 14px;">元</front>
		              </label> 
		            </dd>
		       	</dl>
		       	
		        <dl class="form-item">
		       		<dt class="title"><b class="c-red mr5">*</b>证券名称</dt>
		            <dd class="detail">
		              <label> 
		              	<input class="zui-input zui-validatebox" type="text" id="securitiesName" name="securitiesName" value="" validate-type="Require">
		              </label> 
		            </dd>
		       	</dl>
        		
	   </form>
     </div>
     
</div>
	
	
<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.table'], 
		function($, CALLBACK, ZTOOLS) {
		
		// 添加证券的对话框
		$("#assetsSecuritiesEditDialog").Zdialog({
		   width: 700, height: 300, closed: true, title: "有价证券",buttons: 
	       [
	           {
	               id: 'message-btn',
	               text: '确定',
	               buttonCls: 'btn-blue',
	               handler: function () {
	            	   var isValid = $.ZUI.validateForm($('#assetsSecuritiesEditDiv'));
	            	   if (isValid) {
			           		var assetsSecuritiesVo = $("#assetsSecuritiesEdit_form").serialize();
			        		var params = assetsSecuritiesVo;
			        		//保存案件预约
			        		$.ajax({
			                    type: 'post',
			                    url: '<z:ukey key="com.zdsoft.finance.casemanage.promotion.otherproperty.saveAssetsSecurities" context="admin"/>',
			                    data: params,
			                    dataType: 'json',
			                    success: function (data) {
			                        if (data.resultStatus == 0) {
			                        	$.ZMessage.success("提示", "保存有价证券成功", function () {
			                         		setTimeout(function(){
			                         			$("#assetsSecuritiesEditDialog").Zdialog("close");
			                         			$("#assetsSecuritiesInfoList").ZTable("reload",{});
			                                 },200);
		
			                          	 });
			                        }else{
			                        	$.ZMessage.error("错误", data.msg, function () {
			                            });
			                        }
			                    },
			        	            error: function () {
			        	            	$.ZMessage.error("错误", "兴业贷资调其他资产之有价证券保存异常，请联系管理员", function () {
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
	                    $("#assetsSecuritiesEditDialog").Zdialog("close");
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
		
		// 有价证券情况列表
		$('#assetsSecuritiesInfoList').ZTable({
	       url : "<z:ukey key='com.zdsoft.finance.casemanage.promotion.getAssetsSecuritiesList' context='admin'/>&jsoncallback=?&caseApplyId=${caseApplyId}",
	       singleSelect : true,
	       isRowNum : true,
	       rows : 5,
	       pagination : true,
	       currentPage : 1,
	       idField: "ID",
	       tableCls : 'table-index',//table的样式
	       columns:[[
	    	  	{field : 'SECURITIESNAME',title : '证劵名称',align : 'center'},
				{field : 'WORTH',title : '价值(元)',align : 'center',formatter:formatterAmount},
				{field : 'OWNER',title : '权属人(受益人)', align : 'center'},
				{field : 'ID',title : '操作', align : 'center',width:'20%',formatter:function(r,v){
					return '<a href="javaScript:void(0)" onclick="editAssetsSecuritiesHandle" class="btn-blue" >编辑</a>&nbsp;&nbsp;<a href="javaScript:void(0)" onclick="deleteAssetsSecuritiesHandle" class="btn-blue">删除</a>';
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
 		CALLBACK.editAssetsSecuritiesHandle = function(index,rowData) {
  	        $("#assetsSecuritiesId").val(rowData.ID);
	        $("#securities_owner").val(rowData.OWNER);
	        $("#securities_worth").val(rowData.WORTH);
	        $("#securitiesName").val(rowData.SECURITIESNAME);

            $("#assetsSecuritiesEditDialog").Zdialog('open');
		};
		
		//删除此条证券情况
		CALLBACK.deleteAssetsSecuritiesHandle=function(index, rowData){
    		var params = "&assetsSecuritiesId="+rowData.ID;
			
    		$.ajax({
                type: 'post',
                url: '<z:ukey key="com.zdsoft.finance.casemanage.promotion.otherproperty.deleteAssetsSecurities" context="admin"/>',
                data: params,
                dataType: 'json',
                success: function (data) {
                    if (data.resultStatus == 0) {
                    	$.ZMessage.success("提示", "删除有价证券成功", function () {
                     		setTimeout(function(){
                     			$("#assetsSecuritiesInfoList").ZTable("reload",{});
                             },200);

                      	 });
                    }else{
                    	$.ZMessage.error("错误", data.msg, function () {
                        });
                    }
                },
    	            error: function () {
    	            	$.ZMessage.error("错误", "兴业贷资调其他资产之有价证券删除异常，请联系管理员", function () {
    	             });
    	            }
            });
		};
		
		//新增有价证券弹窗
        $('#addAssetsSecuritiesInfo').click(function () {
        	$("#assetsSecuritiesId").val("");
            $("#assetsSecuritiesEditDialog").Zdialog("open");
        });
		
        $.ZUI.initForms("#assetsSecuritiesEditDiv");
});
</script>