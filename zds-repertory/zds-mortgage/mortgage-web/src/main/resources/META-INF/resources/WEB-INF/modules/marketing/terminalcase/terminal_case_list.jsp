<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../../common/common_js.jsp" %>
<%@ include file="../../common/common_upload.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>终端进件</title>
</head>
<body id="body">
<div class="page-box">
    <div class="page-title">查询信息</div>
    <div id="search" class="p5">
        <form id="searchTerminalCaseListForm" class="zui-form mt15">
            <dl class="form-item">
                <dt class="title">主借人:</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input" id="customerName" type="text" name="cus|customerName|LK|S">
                    </label>
                </dd>
            </dl>
            
            <dl class="form-item">
                <dt class="title">终端:</dt>
                <dd class="detail">
					<input class="zui-combobox zui-validatebox" type="hidden" id="terminalId" name="c|terminalId|E|S" value=""
						data-url="<z:ukey key='com.zdsoft.finance.cooperator.cooperatorSimpleCode' context='admin'/>&jsoncallback=?&createOrgCd=${empDto.orgCd}"
						data-valuefield="id" data-textfield="terminalFullName" 
						data-defaultvalue="" >
				</dd>
            </dl>
            
            <dl class="form-item">
                <dt class="title">案件状态:</dt>
                <dd class="detail">
                    <input class="zui-combobox zui-validatebox" id="stage" type="hidden" name="c|stage|E|S" value=""
			                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0092"
			                           data-valuefield="fullcode" data-textfield="name" >
                </dd>
            </dl>
            
            
        </form>
        <div class="form-btn">
            <button class="btn-blue" id="searchTerminalCaseList">查询</button>
            <button class="btn-gray" id="resetTerminalCaseList">重置</button>
        </div>
    </div>
</div>

   <div class="page-box">
   		<div class="page-title">终端进件列表</div>
        <div class="p10">
            <div id="tb-TerminalCaseList" class="zui-datagrid"
                 zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.marketing.getTerminalCaseList" context="admin"/>&jsoncallback=?","singleSelect":false,"pagination":true,"idField":"id","toolbar":"#btn-applylist","tableCls":"table-index"}'>
                <table>
                    <thead>
                    <tr>
                        <th data-options="field:CUSTOMERNAME">主借人</th>
			            <th data-options="field:APPLYAMOUNT" formatter="noDataShow">申请金额（元）</th>
			            <th data-options="field:PRODUCTTYPENAME" formatter="nullShow">产品父类</th>
			            <th data-options="field:PRODUCTSUBTYPENAME" formatter="nullShow">子产品</th>
			            <th data-options="field:PLACEFLOOR" formatter="nullShow">所在楼层</th>
			            <th data-options="field:HOUSEPROPERTYADDRESS">押品地址</th>
			            <th data-options="field:SYNTHESIZEPRICE" formatter="realPriceShow">评估价</th>
			            <th data-options="field:TERMINALFULLNAME" formatter="nullShow">终端</th>
			            <th data-options="field:MECHANISMNAME" formatter="nullShow">机构</th>
			            <th data-options="field:STAGENM">案件状态</th>
			            <th data-options="field:ID" formatter="formatId">操作</th>
                    </tr>
                    </thead>
                </table>
            </div>
			<div id="btn-applylist">
			    <a class="zui-toolbar" id="btn_import" text="导入" buttonCls="btn-blue" handler="importTerminalCase"></a>
			    <a class="zui-toolbar" id="btn_export" text="导出" buttonCls="btn-blue" handler="exportTerminalCase"></a>
			    <a class="zui-toolbar" id="btn_batchApproval" iconCls="icon-add" text="批量审批" buttonCls="btn-blue" handler="batchApprovalTerminalCase"></a>
	    	</div>
        </div>
    </div>
    <div style="display:none" >
    	<table id="tableTemp"></table>
    </div>
    
<!-- 终端进件导入 -->
<div id="terminalCaseListIntoDialog" style="display: none">
<input id="sessionId" type="hidden" value="${pageContext.session.id}"/> 
	<input type="file" name="fileUpload" id="fileUpload" validate-type="Require"  />
	 <div class="uploadify-queue" id="queue-item" style="margin-left: 105px">
        </div>

</div>
    

<!-- 批量审批弹窗 -->
<div id="terminalCaseApprovalEditDialog" style="display: none">
     <div id="terminalCaseApprovalEditDiv" class="p20">
	     <form id="terminalCaseApproval_form" class="zui-form " method="post" enctype="multipart/form-data">
			<input type="hidden" id="ids" name="ids" value=""/>
		       	<dl class="form-item">
		       		<dt class="title">操作</dt>
		            <dd class="detail">
		              	<input class="zui-combobox zui-validatebox" type="hidden" value=""
		                     data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0070"
		                   data-valuefield="fullcode" data-textfield="name"  name="approvalResult" id="approvalResult" data-callback="approvalResultChange">
		            </dd>
		       	</dl>
		       	<dl class="form-item cc">
		       	</dl>
		       	
		       	<dl class="form-item aa" style="display: none;">
		       		<dt class="title">拒绝原因</dt>
		            <dd class="detail">
		              	<input class="zui-combobox zui-validatebox" type="hidden"
		                     data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0055"
		                   data-valuefield="fullcode" data-textfield="name"  name="refuseReason" id="refuseReason">
		            </dd>
		       	</dl>
		       	
		        <dl class="form-item bb" style="display: none">
		       		<dt class="title"><b class="c-red mr5">*</b>分配机构</dt>
		            <dd class="detail" id="collectionOrgs">
		              <label>
		              	<input class="zui-input zui-validatebox" type="hidden" id="organizationCd" name="organizationCd" value="">
		              	<input class="zui-input zui-validatebox" type="text" id="organizationName" name="organizationName">
		              </label>
		            </dd>
		       	</dl>
		       	<dl class="form-item bb" style="display: none">
		       		<dt class="title"><b class="c-red mr5">*</b>渠道经理</dt>
		            <dd class="detail">
		            	<input class="zui-combobox zui-validatebox" type="hidden" id="channelManagerCd" name="channelManagerCd" value="" >
		                   
		            </dd>
		       	</dl>
		       	<dl class="form-item bb" style="display: none">
		       	</dl>
		       	
		       	<dl class="form-item block">
                	<dt class="title">备注：</dt>
	                <dd class="detail">
		                <label>
		                	<textarea class="zui-area zui-validatebox" id="mo" name="mo" validate-type="Length[0-200]" placeholder="最多可以输入200个字符"></textarea>
		                </label>
	                </dd>
                </dl>	
		       	
	   </form>
     </div>
</div>





<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter', 'zd/completer'
            ], function ($, CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
    	
    				
		    		// 批量审批对话框
		    		$("#terminalCaseApprovalEditDialog").Zdialog({
		    		   width: 700, height: 300, closed: true, title: "审批",buttons: 
		    	       [
		    	           {
		    	               id: 'message-btn',
		    	               text: '保存',
		    	               buttonCls: 'btn-blue',
		    	               handler: function () {
		    	            	   var params ="&terminalCaseApprovalOpinion="+ $("#terminalCaseApproval_form").serialize();
		    	            	   var ids = $('#ids').val();
		    	            	   params += '&ids='+ids;
		    		        		//保存审批信息
		    		        		$.ajax({
		    		                    type: 'post',
		    		                    url: '<z:ukey key="com.zdsoft.finance.marketing.terminalCaseApprovalOpinion.saveTerminalCaseApprovalOpinion" context="admin"/>',
		    		                    data: params,
		    		                    dataType: 'json',
		    		                    success: function (data) {
		    		                        if (data.resultStatus == 0) {
		    		                        	$.ZMessage.success("提示", "审批意见保存成功", function () {
		    		                         		setTimeout(function(){
		    		                         			$("#terminalCaseApprovalEditDialog").Zdialog("close");
		    		                         			$("#tb-TerminalCaseList").ZTable("reload",{});
		    		                                 },200);
		    	
		    		                          	 });
		    		                        }else{
		    		                        	$.ZMessage.error("错误", data.msg, function () {
		    		                            });
		    		                        }
		    		                    },
		    		        	            error: function () {
		    		        	            	$.ZMessage.error("错误", "审批意见保存异常，请联系管理员", function () {
		    		        	             });
		    		        	            }
		    		                }); 
		    	            	   
		    	               }
		    	           },
		    	           {
		    	               id: 'message-btn',
		    	               text: '提交',
		    	                  buttonCls: 'btn-blue',
		    	                  handler: function () {
		    	                	 var params = $("#terminalCaseApproval_form").serialize();
		    		        		//提交审批信息
		    		        		$.ajax({
		    		                    type: 'post',
		    		                    url: '<z:ukey key="com.zdsoft.finance.marketing .terminalCaseApprovalOpinion.submitTerminalCaseApprovalOpinion" context="admin"/>',
		    		                    data: params,
		    		                    dataType: 'json',
		    		                    success: function (data) {
		    		                        if (data.resultStatus == 0) {
		    		                        	$.ZMessage.success("提示", "审批意见提交成功", function () {
		    		                         		setTimeout(function(){
		    		                         			$("#terminalCaseApprovalEditDialog").Zdialog("close");
		    		                         			$("#tb-TerminalCaseList").ZTable("reload",{});
		    		                                 },200);
		    	
		    		                          	 });
		    		                        }else{
		    		                        	$.ZMessage.error("错误", data.msg, function () {
		    		                            });
		    		                        }
		    		                    },
		    		        	            error: function () {
		    		        	            	$.ZMessage.error("错误", "审批意见提交异常，请联系管理员", function () {
		    		        	             });
		    		        	            }
		    		                }); 
		    		        		
		    	                    
		    	                  }
		    	            }
		    	        ]
		    	   	});
		        	

    	
    	
    	
    	
    	
		    	//查询回调
		        $('#searchTerminalCaseList').on('click',function(){
		        	var flag=$.ZUI.validateForm($('#searchTerminalCaseListForm'));
		        	if(flag){
		            	var formArray=$("#searchTerminalCaseListForm").serializeArray();
		            	$('#tb-TerminalCaseList').ZTable("reload", formArray);
		        	}
		        });
		        
		        //重置回调
		        $('#resetTerminalCaseList').on('click',function(){
		        	$("#searchTerminalCaseListForm")[0].reset();
		        	$("#terminalId").ZCombobox("setValue","");
		        	$("#stage").ZCombobox("setValue","");
		        });
				
		        //当综合评估价没有时显示填写的评估价
		        CALLBACK.realPriceShow = function(rowData,index){
		        	if( rowData.SYNTHESIZEPRICE && '0'!=rowData.SYNTHESIZEPRICE ){
		        		return rowData.SYNTHESIZEPRICE;
		        	}else if(('0'==rowData.SYNTHESIZEPRICE || !rowData.SYNTHESIZEPRICE) && rowData.EVALUATINGPRICE){
		        		return rowData.EVALUATINGPRICE;
		        	}else{
		        		return "";
		        	}
		        	
		        }
    			
		    	//操作格式化
		        CALLBACK.formatId=function(rowData,index){
	        		if('YWDM009203'== rowData.STAGE){
		        		var data =  '<a href="javaScript:void(0)" onclick="editTerminalCase"><button class="btn-blue">编辑</button></a>&nbsp;&nbsp';
		        		data +=  '<a href="javaScript:void(0)" onclick="viewTerminalCase"><button class="btn-blue">详情</button></a>&nbsp;&nbsp';
	        		}else{
		        		var data = '<a href="javaScript:void(0)" onclick="viewTerminalCase"><button class="btn-blue">详情</button></a>&nbsp;&nbsp';
	        		}
	        	return data;
		        }
		 
		        //编辑
		        CALLBACK.editTerminalCase=function(index,row){
		        	var editTerminalCaseUrl = '<z:ukey key="com.zdsoft.finance.marketing.editTerminalCase" context="admin"/>&jsoncallback=?&caseApplyId='+row.ID;
		            ZDS_MESSAGE_CLIENT.openMenuLink('terminalCaseTab','终端进件编辑',editTerminalCaseUrl + "&openMethod=tabs"); 
		        	
			    }
		    	//详情
		        CALLBACK.viewTerminalCase=function(index,row){  
		        	var viewTerminalCaseUrl = '<z:ukey key="com.zdsoft.finance.marketing.viewTerminalCase" context="admin"/>&jsoncallback=?&caseApplyId='+row.ID;
		            ZDS_MESSAGE_CLIENT.openMenuLink('terminalCaseViewTab','终端进件详情',viewTerminalCaseUrl + "&openMethod=tabs"); 
		    		
                }
		    	
		    	//导入
		        CALLBACK.importTerminalCase=function(){
		        	 $("#terminalCaseListIntoDialog").Zdialog("open");
                }
		    	
		        //导入终端进件弹出框打开
		        $("#terminalCaseListIntoDialog").Zdialog({
					width : 475,
					height : 220,
					closed : true,
					title:"导入终端进件Excel",
					buttons : [{
						id : 'message-btn',
						text : '确定',
						buttonCls: 'btn-blue',
						handler : function() {
							$("#terminalCaseListIntoDialog").Zdialog("close");
							$("#tb-TerminalCaseList").ZTable("reload",{});

						}
					},{
						id : 'message-btn',
						text : '取消',
						buttonCls : 'btn-gray',
						handler : function() {
							$("#terminalCaseListIntoDialog").Zdialog("close");
						}
					}]
				}); 
		    	
		    	
		    	//导出
 		        CALLBACK.exportTerminalCase = function (index, data) {
/*  		        	var rows = $('#tb-TerminalCaseList').ZTable("getSelections");
					var params = {};
					params.content=JSON.stringify(rows);
 		        	$.ajax({
	                    type: 'post',
	                    url: '<z:ukey key="com.zdsoft.finance.marketing.terminalCaseApprovalOpinion.exportTerminalCase" context="admin"/>&jsoncallback=?&fileName=终端进件列表',
	                    data: params,
	                    dataType: 'json',
	                    success: function (data) {
	                        if (data.resultStatus == 0) {
	                        	$.ZMessage.success("提示", "终端进件导出成功", function () {
	                         		
	                          	 });
	                        }else{
	                        	$.ZMessage.error("错误", data.msg, function () {
	                            });
	                        }
	                    },
	        	            error: function () {
	        	            	$.ZMessage.error("错误", "终端进件导出异常，请联系管理员", function () {
	        	             });
	        	            }
	                });  */
	                
 		            var url = "<z:ukey key="com.zdsoft.finance.toExcel" context="admin"/>&jsoncallback=?&fileName=终端进件列表";
 		            $('#tableTemp').html($('table:eq(0)').html());
 		            $("body table:eq(1) td[field='datagrid-header-check']").remove();
 		            var params = $('body table:eq(1)').html();
 		            $("form").remove("#exportFrom");
 		            $("body").append("<form id='exportFrom' class='zui-form mt15' method='post' action='" + url + "' accept-charset='utf-8'><input type='hidden' id='htmlContent' name='htmlContent' value='" + params + "' /></form>");
 		            $("#exportFrom").submit();   
		            
		        };  
		    	
		    	//批量审批
		        CALLBACK.batchApprovalTerminalCase=function(index, data){
		        	var rows = $('#tb-TerminalCaseList').ZTable("getSelections");
		        	if (rows.length < 1) {
		                $.ZMessage.error("提示", "请勾选数据", function () {
		                	
		                });
		                return;
		            };
		            var params = '';
					 for (var i=0;i<rows.length;i++) {
						params += rows[i].ID + ',';
					 }
					$('#ids').val(params);
		            $("#terminalCaseApprovalEditDialog").Zdialog('open');
		        	
                }
		    	
		        CALLBACK.approvalResultChange = function(v,t){
					if("YWDM007002"==v){
						$(".aa").show();
						$(".bb").hide();
						$(".cc").hide();
						
					}else if("YWDM007001"==v){
						$(".aa").hide();
						$(".bb").show();
						$(".cc").hide();
					}else{
						$(".aa").hide();
						$(".bb").hide();
						$(".cc").show();
					}
		        }
		    	
		        //刷新
                function doSearch() {
    				$('#tb-TerminalCaseList').ZTable("reload",{});
    			};
                //页面回调
                ZDS_MESSAGE_CLIENT.refreshThis=function(){
            		doSearch();
                };
                //为空时显示空字符串
                CALLBACK.nullShow = function(row,value){
                	if(!value){
                		return "";
                	}else{
                		return value;
                	}
                }
                //金额没有数据时显示空字符串
                CALLBACK.noDataShow = function(row,value){
                	if('0'==value || !value){
                		return "";
                	}else{
                		return value;
                	}
                }
                
                //初始化
                $.ZUI.init();
                var channelManagerSelectUrl =  '<z:ukey key="com.zdsoft.finance.marketing.terminalCaseApprovalOpinion.channelManagerByOrgCd" context="admin"/>&jsoncallback=?';
                var data = '${data}';
                //分配机构的模糊搜索
                $("#organizationName").completer({
                    suggest: true,//默认false
                    idField: 'code',//默认id,唯一标识字段
                    nameField: 'name',//默认name,下拉列表展示数据的字段
                    valueField: 'py',//默认value,根据值查询数据的字段
                    source:data,
                    writable: false,//默认false，是否可自定义输入
                    placeObj:$("#collectionOrgs"),//悬浮框需要定位到的对象
                    complete: function (data) {
                        $('#organizationName').val(data.name);
                        $('#organizationCd').val(data.code);
                        
                        $('#channelManagerCd').ZCombobox({
                        	 valueField: "empCd",
                             textField: "empNm",
                             url:"<z:ukey key='com.zdsoft.finance.marketing.terminalCaseApprovalOpinion.channelManagerByOrgCd' context='admin'/>&jsoncallback=?&organizationCd="+data.code,
                             onSelect:function(value,text,index,data){
                            	 //channelManagerSelectByOrg(data.code);
							 }
                        });
                        
                        
                        //console.log($('#organizationCd').val(data.code));
/*                     	$.ajax({
                    		type:"post",
                    		url: channelManagerSelectUrl,
                    		data:"&organizationCd="+"001001",
                    		dataType:"json",
                    		success:function(){
                    		},
                    		error:function(){
                    		}
                    		
                    	}) */
                    },
                    filter: function (val) {
                        return val;//过滤输入的value值
                    }
                });
                
               
                //选择渠道经理下拉回调
                //var organizationCd = $('#organizationCd').val();
/*                CALLBACK.channelManagerSelect = function(v,t){
	               	if(!organizationCd){
		                $.ZMessage.warning("提示", "请先确定分配机构", function () {
		                	return false;
		                });
	               	}
            	   channelManagerSelectByOrg(organizationCd);
                }
                //初始化渠道经理选择
                 $(function(){
                	if(organizationCd){
                		channelManagerSelectByOrg("001001");
                	} 
                }); 
                if(organizationCd){
                	channelManagerSelectByOrg(organizationCd);
                }
                
                //选择机构渠道经理
                function channelManagerSelectByOrg(v){
                	console.log(v);
                	$.ajax({
                		type:"post",
                		url: channelManagerSelectUrl,
                		data:"&organizationCd="+v,
                		dataType:"json",
                		success:function(){
                		},
                		error:function(){
                		}
                		
                	});
                }
                 */
                
       			//下载
       			window.attrDown = function(_id){
       				var essdownUrl = '<z:ukey key="public.upload.download"  context="admin"/>';
       				window.downLoadFile(essdownUrl+"&attachmentId="+_id);
       			};
       			//上传附件初始化
                 $(function(){
            		 window.initUpload();
            	}); 
            	
            	window.uploadSuccess=function(){
            		$.ZMessage.success("提示", "保存成功", function () {
             		$("#terminalCaseListIntoDialog").Zdialog("close");
             		$("#tb-TerminalCaseList").ZTable("reload",{});
            		 });
            	};
            	
            	window.uploadFail=function(msg){
            		$.ZMessage.error("错误", msg, function () {
            			
                     });
           		};
           	
            });
    
    //附件上传方法
    function initUpload() {
       	// 上传相关js
       	var upload_url = '<z:ukey key="com.zdsoft.finance.marketing.terminalCaseApprovalOpinion.importTerminalCases" context="admin"/>;jsessionid=' + $("#sessionId").val();
       	//var upload_url = 'importTerminalCases;jsessionid='+ $("#sessionId").val();
    
       	$('#fileUpload').uploadify({
       		'multi': false,
               'swf': '<%=resServerUpload %>/assets/js/zd/uploadify.swf',
               'uploader': upload_url,
               'buttonText': '导入终端进件',
               'width': '80',
               'height': '22',
               'queueID': 'queue-item',
               'debug': false,
       		'onUploadSuccess' : function(file, data, response) {
       			var allData = JSON.parse(data);
       			if (allData[0].resultStatus == 0) {
                   	uploadSuccess();
                   }else{
                   	var msg = allData[0].msg
                   	uploadFail(msg);
                   }
       		}
       	});
       } 
    
</script>
</body>
</html>