<%@ taglib prefix="z" uri="http://www.zdsoft.cn/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../common/common_js.jsp" %>
<%@ include file="../common/common_upload.jsp"%> 


<title>客户黑名单信息管理</title>
</head>
<body id="body">
<div class="page-box">
    <div class="page-title">查询信息</div>
    <div id="search" class="p5">
        <form id="searchProductForm" class="zui-form mt15">
        	<input type="hidden" name="createBy|E|S" value="${EmpCd }">
            <dl class="form-item">
                <dt class="title">姓名:</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input zui-validatebox" validate-type="Length[0-64]" type="text" id="blackName" name="blackName|LK|S" value="">
                    </label>
                </dd>
            </dl>
        </form>
        <div class="form-btn">
            <button class="btn-blue" id="searchProduct">查询</button>
            <button class="btn-gray" id="resetProduct">重置</button>
        </div>
    </div>
</div>

<div class="page-box">
    <div class="page-title">黑名单列表</div>
    <div class="p10">
        <div id="tb-blanckList" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.blanckList.getBlanckList" context="admin"/>&jsoncallback=?&createBy|E|S=${EmpCd }","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#btn-function"}'>
		    <table>
		        <thead>
		        <tr>
		            <th data-options="field:blackName,width:10%">姓名</th>
		            <th data-options="field:credentiaType,width:15%">证件类型</th>
		            <th data-options="field:credentialNo,width:25%">证件号码</th>
		            <th data-options="field:reasonType,width:10%">原因</th>
		            <th data-options="field:source,width:10%">来源</th>
		            <th data-options="field:startDate,width:10%" formatter="enableFormatter">开始时间</th>
		            <th data-options="field:endDate,width:10%" formatter="enableFormatter">结束时间</th>
		            <th data-options="field:id,width:20%" formatter="formatId">操作</th>
		        </tr>
		        </thead>
		    </table>
		</div>
		<div id="btn-function">
			<a class="zui-toolbar" id="btn-into" text="导入" buttonCls="btn-blue" handler="intoCustomer"></a> 
		    <a class="zui-toolbar" id="btn-add" text="新增" buttonCls="btn-blue" handler="addCustomer"></a>
		    <a class="zui-toolbar" id="exports" text="导出" buttonCls="btn-blue" handler="exports"></a>
	    </div>
	</div>
</div>

<!-- 新增黑名单信息 -->
<div id="blanckListDialog" style="display: none">
	
</div>

<!-- 黑名单导入 -->
<div id="blanckListIntoDialog" style="display: none">
<input id="sessionId" type="hidden" value="${pageContext.session.id}"/> 
	<input type="file" name="fileUpload" id="fileUpload" validate-type="Require"  />
	 <div class="uploadify-queue" id="queue-item" style="margin-left: 105px">
        </div>

</div>

<script type="text/javascript">
    seajs.use(['jquery', 'zd/tools', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'
            ]
            , function ($, ZTOOL, CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
    	
		    	/* CALLBACK.setattr = function(index,value){
					if(value == '身份证'){
						$("#credentialNo").attr('validate-type',"Require,Length[1-64],IDCard");
					}else{
						$("#credentialNo").attr('validate-type',"Require,Length[1-64]");
					}
				} */
    			
		    	//查询回调
		        $('#searchProduct').on('click',function(){
		        	var flag=$.ZUI.validateForm($('#searchProductForm'));
		        	if(flag){
		            	var formArray=$("#searchProductForm").serialize();
		            	formArray=decodeURIComponent(formArray, true);
		            	$('#tb-blanckList').ZTable("reload", formArray);
		        	}
		        });
		        
		        //重置回调
		        $('#resetProduct').on('click',function(){
		        	$('#blackName').val('');
		        	var flag=$.ZUI.validateForm($('#searchProductForm'));
		        	if(flag){
		            	var formArray=$("#searchProductForm").serialize();
		            	formArray=decodeURIComponent(formArray, true);
		            	$('#tb-blanckList').ZTable("reload", formArray);
		        	}
		        });
    			
		        //新增黑名单
		        CALLBACK.addCustomer=function(){
		        	/* $("#changeStartDate").val('');
		        	$("#changeEndDate").val('');
		        	$("#blanckListDialog").Zdialog("open"); */
		        	var	url = '<z:ukey key="com.zdsoft.finance.blanckList.dialog" context="admin"/>';
		    		$('#blanckListDialog').load(url,function(){
		    			//$("#blanckListDialogs").Zdialog("open");
		    		});
                }
		        //新增黑名单弹出框打开
		        /* $("#blanckListDialog").Zdialog({
					width : 430,
					height : 350,
					closed : true,
					title:"新增黑名单",
					buttons : [{
						id : 'message-btn',
						text : '确定',
						buttonCls: 'btn-blue',
						handler : function() {
							//校验黑名单输入数据
	                        var Validate = $.ZUI.validateForm($('#blanckList_form'));
	             			if(Validate){
	             				var param = $('#blanckList_form').serialize();
	             				//黑名单保存
	             				$.ajax({
	                                 type: 'post',
	                                 url: '<z:ukey key="com.zdsoft.finance.blanckList.saveBlanckList" context="admin"/>',
	                                 data: param,
	                                 dataType: 'json',
	                                 success: function (data) {
	                                     if (data.resultStatus == 0) {
	                                     	 $.ZMessage.success("提示", "保存成功", function () {
	                                     		$("#blanckListDialog").Zdialog("close");
	                                     		$("#tb-blanckList").ZTable("reload",{});
	                                       	 });
	                                     }else{
	                                       	$.ZMessage.error("错误", data.msg, function () {
	             		                    });
	                                     }
	                                 },
	                                 error: function () {
	                                   	$.ZMessage.error("错误", "保存联系方式系统异常，请联系管理员", function () {
	             	                    });
	                                 }
	                             });
	             			}
						}
					},{
						id : 'message-btn',
						text : '取消',
						buttonCls : 'btn-gray',
						handler : function() {
							$("#blanckListDialog").Zdialog("close");
						}
					} ]
				}); */
		        
		        //黑名单导入
		         CALLBACK.intoCustomer=function(){
		        	
		        	/*var	url = '<z:ukey key="com.zdsoft.finance.blanckList.intoDialog" context="admin"/>';
		    		$('#blanckListIntoDialog').load(url,function(){
		    			
		    		}); */
		    	
		        	 $("#blanckListIntoDialog").Zdialog("open");
                } 
		        //导入黑名单弹出框打开
		        $("#blanckListIntoDialog").Zdialog({
					width : 475,
					height : 220,
					closed : true,
					title:"导入黑名单",
					buttons : [{
						id : 'message-btn',
						text : '确定',
						buttonCls: 'btn-blue',
						handler : function() {
							$("#blanckListIntoDialog").Zdialog("close");
							$("#tb-blanckList").ZTable("reload",{});

						}
					},{
						id : 'message-btn',
						text : '取消',
						buttonCls : 'btn-gray',
						handler : function() {
							$("#blanckListIntoDialog").Zdialog("close");
						}
					}]
				}); 
				
		        
		    	//操作格式化
		        CALLBACK.formatId=function(rowData,index){
		        	var data='<a onclick="delBlanck"><button class="btn-blue">删除</button></a>';
		        	return data;
		        }
		    	//删除黑名单弹出框打开
		        CALLBACK.delBlanck=function(index,row){
		        	$.ZMessage.question("确认", "确认删除", function (r) {
		                	$.ajax({
			                    type: 'post',
			                    url: '<z:ukey key="com.zdsoft.finance.blanckList.delBlanckList" context="admin"/>',
			                    data: {id : row.id},
			                    dataType: 'json',
			                    success: function (data) {
			                        if (data.resultStatus == 0) {
			                        	 $.ZMessage.success("提示", "删除成功", function () {
			                        		$("#tb-blanckList").ZTable("reload",{});
			                          	 });
			                        }else{
			                          	$.ZMessage.error("错误", data.msg, function () {
					                    });
			                        }
			                    },
			                    error: function () {
			                      	$.ZMessage.error("错误", "删除黑名单系统异常，请联系管理员", function () {
				                    });
			                    }
			                });
		            });
		        	/* $.ZMessage.warning("提示", "确定删除", function () {
			        	$.ajax({
		                    type: 'post',
		                    url: '<z:ukey key="com.zdsoft.finance.blanckList.delBlanckList" context="admin"/>',
		                    data: {id : row.id},
		                    dataType: 'json',
		                    success: function (data) {
		                        if (data.resultStatus == 0) {
		                        	 $.ZMessage.info("提示", "删除成功", function () {
		                        		$("#tb-blanckList").ZTable("reload",{});
		                          	 });
		                        }else{
		                          	$.ZMessage.error("错误", data.msg, function () {
				                    });
		                        }
		                    },
		                    error: function () {
		                      	$.ZMessage.error("错误", "保存联系方式系统异常，请联系管理员", function () {
			                    });
		                    }
		                });
                  	 }); */
                }
		        
		    	//黑名单列表导出
		        CALLBACK.exports=function(){
		        	var rows=$('#tb-blanckList').ZTable("getRows");
		    		if(rows.length==0){
		    			$.ZMessage.warning("警告", "查询列表为空，无法导出", function () {
	                    });
		    			return ;
		    		}
		        	var url="<z:ukey key="com.zdsoft.finance.toExcel" context="admin"/>&jsoncallback=?&fileName=黑名单列表导出文档";
                    var param=$("table").html();
					$("form").remove("#exportFrom");
                    $("body").append("<form id='exportFrom' class='zui-form mt15' method='post' action='"+url+"' accept-charset='utf-8'><input type='hidden' id='htmlContent' name='htmlContent' value='"+param+"' /></form>");
                    $("#exportFrom").submit();
                }
		    	
                //初始化
                $.ZUI.init();
                
                CALLBACK.enableFormatter=function(index, value){
                	if(value == '0'){
                		return '';
                	}else{
	                	//时间转换
	                    value = ZTOOL.strToDate(value);
	                    return value;
                	}
           		};
                
   			//删除附件
   			window.attrDel = function(_this,_id){
   				$(_this).parent().remove();
   				var att = $("#attachments").val();
   				att = att.replaceAll(_id,"");
   				att = att.replaceAll(",,",",");
   				if(att.indexOf(",") == 0){
   					att = att.substring(1,att.length);
   				}
   				if(att.lastIndexOf(",") + 1 == att.length){
   					att = att.substring(0,att.length - 1);
   				}
   				if(att==""){
   					$("#importProcessUploadStatus").append("<span id='attrRemark'>备注：未上传附件</span>");
   				}
   				$("#attachments").val(att);
   			};
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
         		$("#blanckListIntoDialog").Zdialog("close");
         		$("#tb-blanckList").ZTable("reload",{});
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
    	//var upload_url = '<z:ukey key="com.zdsoft.finance.blanckList.impBlanckList" context="admin"/>;jsessionid=' + $("#sessionId").val();
    	var upload_url = 'impBlanckList;jsessionid='+ $("#sessionId").val();
 
    	$('#fileUpload').uploadify({
    		'multi': false,
            'swf': '<%=resServerUpload %>/assets/js/zd/uploadify.swf',
            'uploader': upload_url,
            'buttonText': '导入黑名单',
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