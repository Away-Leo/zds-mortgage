<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file='../common/common_js.jsp'%>
<%@ include file="../common/common_upload.jsp"%>
<style type="text/css">
    .uploadify{ float:left;}
</style>
</head>
<body>
		<!-- BEGIN CONTAINER -->
	<div class="frm-content">
		<div class="page-box">
			<div class="page-title">诉讼资料配置</div>
			<div class="p5">
				<form action="#" id="searchForm" class="zui-form form-search" method="post"
					enctype="multipart/form-data">
					<dl class="form-item">
						<dt class="title">文件名</dt>
						<dd class="detail">
							<label>
								<input type="text" class="zui-input" name="name|LK|S"
									id="name" />
							</label>
						</dd>
					</dl>
				
					
					<div class="form-btn">
	                    <button type="button" class="btn-blue" id="searchButton">查询</button>
	                    <button type="button" class="btn-blue" id="resetButton">重置</button>
	                </div>
				</form>
				<!-- table演示 -->
				<div class="zui-datagrid" id="zd-table"
						zdata-options="{'idField':'id','url':'<z:ukey key="com.zdsoft.finance.parameter.getLitigationData"  context="admin"/>&jsoncallback=?','singleSelect':true,'isRowNum':false,'pagination':true,'tableCls': 'table-index','toolbar':'#btn-applylist'}">
					<table >
						<thead>
							<tr>
								<th data-options="field:type,width:25%">文件类型</th>
								<th data-options="field:name,width:30%">文件名</th>
								<th data-options="field:remark,width:30%">备注</th>
								<th data-options="width:15%" formatter="operate">操作</th>

							</tr>
						</thead>
					</table>
				</div>
				<div id="btn-applylist">
				    <a class="zui-toolbar" id="btn-add" text="新增" iconCls="icon-add" buttonCls="btn-blue" handler="add"></a>
				</div>
			</div>
		</div>
	</div>
	
	
	<div id="dialogEdit" style="display: none">
    <div id="testForm"  class="mt20">
        <form id="dialogForm" class="zui-form">
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>文件类型</dt>
                <dd class="detail">
                    <label>
                    	<input id="litigationDataId" style="display:none;">
                        <input id='form_type' class="zui-combobox zui-validatebox" type="hidden" data-toggle="combobox"
						 	data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=lft001000"		                   data-valuefield="id" data-textfield="text"
		                   data-valuefield="id" data-textfield="text"
		                   validate-type="Require" validate-false="请选择文件类型">
                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>文件名</dt>
                <dd class="detail">
                	<label>
                		<input type="text" class="zui-input zui-validatebox" id="form_name"    validate-type="Require,Length[1-25]" validate-false="文件名不能为空"/>
					</label>
                </dd>
            </dl>
            <div>
	            <dl class="form-item" style="width:550px;">
	                <dt class="title"><b class="c-red mr5">*</b>文件上传</dt>
	                <dd class="detail">
	                    <label>
	                    	<input type="text" class="zui-input zui-validatebox" style="float:left;width:350px;" id="form_fileName"  readonly="true"   validate-type="Require" validate-false="请选择文件"/>
	                    	<input type="text" id="form_fileId" style="display:none;">
	                       	<input id="fileUpload" name="form_path" type="file" value="添加附件" validate-type="Require" validate-false="文件名不能为空"/>
	                    </label>
	                </dd>
	            </dl>
            </div>
            <div>
	           <dl class="form-item">
	                <dt class="title">备注</dt>
	                <dd class="detail">
	                	<label>
	                    	<textarea id="form_remark" class="zui-area zui-validatebox" validate-type="Length[0-250]" validate-false="备注不能超过250个字"></textarea>
						</label>
	                </dd>
	            </dl>
            </div>
        </form>
    </div>
</div>
	
	<script type="text/javascript">


		seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch','zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker','zd/jquery.zds.table', 'zd/jquery.zds.seleter','zd/jquery.zds.combotree','zd/jquery.zds.checkbox']
	            , function ($,CALLBACK, COMBOBOX,  Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
			Date.prototype.Format = function (fmt) { //author: meizz 
			    var o = {
			        "M+": this.getMonth() + 1, //月份 
			        "d+": this.getDate(), //日 
			        "h+": this.getHours(), //小时 
			        "m+": this.getMinutes(), //分 
			        "s+": this.getSeconds(), //秒 
			        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
			        "S": this.getMilliseconds() //毫秒 
			    };
			    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
			    for (var k in o)
			    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
			    return fmt;
			}
			 $("#dialogEdit").Zdialog({
			        title: "新增",
			        width: 650,
			        height: 300,
			        closed: true,
			        isDrag: false,
			        buttons: [
			            {
			                id: 'message-btn', text: '确定',buttonCls: 'btn-blue',
			                handler: function () {
			                	var isValidate = $.ZUI.validateForm($('#dialogForm'));
								if(isValidate){
									if($("#form_name").val()=="null"){
										$.ZMessage.info("提示", "无效的文件名称", function () {
											
		                    			});
										return;
									}
									var param={};
				                    param.name=$("#form_name").val();
				                    param.fileId=$("#form_fileId").val();
				                    param.status=$("#form_status").ZCheckbox('getValue');
				                    param.type=$("#form_type").ZCombobox('getValue');
				                    param.remark=$("#form_remark").val();
				                    param.fileName=$("#form_fileName").val();
				                    if($("#litigationDataId").val()){
				                    	param.id=$("#litigationDataId").val();
				                    }
				                    $.ajax({
				                    	method : "post",
				                    	dataType : "json",
				                    	data : param,
				                    	url : '<z:ukey key="com.zdsoft.finance.parameter.updateLitigationData"  context="admin"/>&jsoncallback=?',
				                    	success : function(result){
				                    		if(result.resultStatus==0){
				                    			$.ZMessage.info("提示", result.msg, function () {
					                    				$('#zd-table').ZTable("reload");
					                    			});
				                    		}
				                    		else{
				                    			$.ZMessage.error("错误", result.msg, function () {});
				                    		}
				                    	}
				                    			
				                    });
				                    $("#dialogEdit").Zdialog("close");
								}
			                }
			            },
			            {
			                id: 'message-btn', text: '取消', buttonCls: 'btn-gray',
			                handler: function () {
			                    //todo something
			                    $("#dialogEdit").Zdialog("close");
			                }
			            }],
			    });
			//日期转换函数
			CALLBACK.changeDate = function(index, v) {
				var date = new Date(v);
				return date.Format('yyyy-MM-dd hh:mm');
			};
			//状态转换函数
			CALLBACK.changeStatus = function(index,v){
				switch(v){
				case 1:
					return "启用";
					break;
				case  0:
					return "停用";
					break;
				}
				
			};
			//操作栏的回调函数
			CALLBACK.operate=function(){
				var  html = '<a href="javaScript:void(0)" onclick="doEdit"><button class="btn-blue">编辑</button></a>'+'&nbsp;&nbsp;'+
             	'<a href="javaScript:void(0)" onclick="doDelete"><button class="btn-blue">删除</button></a>'
				 return html;
		            
			}
			//新增行
			CALLBACK.add=function(){
				$(".dialog-title").children()[0].innerHTML=" 新增";
				//var rows = $('#zd-table').ZTable("getSelections");
				 $("#dialogEdit").Zdialog("open");
				 
			}
			//编辑行
			CALLBACK.doEdit=function(num,selRow){
				$(".dialog-title").children()[0].innerHTML=" 编辑";
				 $("#dialogEdit").Zdialog("open");
				 if(selRow){
					 $("#form_name").val(selRow.name);
	                 $("#form_type").ZCombobox('setValue',selRow.type);
	                 $("#form_fileId").val(selRow.fileId);
	                 $("#form_remark").val(selRow.remark);
	                 $("#litigationDataId").val(selRow.id);
	                 $("#form_fileName").val(selRow.fileName);
				 }
			}
			//删除行
			CALLBACK.doDelete=function(num,selRow){
				$.ZMessage.question("确认", "确认删除该费用项吗？", function (r) {
	                	 if(selRow){
	    					 var param={};
	    					 param.id=selRow.id;
	    	                 $.ajax({
	    	                    	method : "post",
	    	                    	dataType : "json",
	    	                    	data : param,
	    	                    	url : '<z:ukey key="com.zdsoft.finance.parameter.deleteLitigationData"  context="admin"/>&jsoncallback=?',
	    	                    	success : function(result){
	    	                    		if(result.resultStatus==0){
	    	                    			$.ZMessage.info("提示", result.msg, function () {});
	    	                    		}
	    	                    		else{
	    	                    			$.ZMessage.error("错误", result.msg, function () {});
	    	                    		}
	    	                    		$('#zd-table').ZTable("reload");
	    	                    	}
	    	                    			
	    	                    });
	    				 }

	            });
			}
			$.ZUI.init();
			
			$(function(){
        		window.initUpload();
        	});
			//查询
			$("#searchButton").click(function() {
				/*  var param={};
                 param=$("#name").val();
                 param.status=$("#status").ZCombobox('getValue');
                 param.type=$("#type").ZCombobox('getValue');
                 param.valueMethod=$("#form_valueMethod").ZCombobox('getValue'); */
             	var formArray=$("#searchForm").serialize();
 				formArray = decodeURIComponent(formArray, true);
				$('#zd-table').ZTable("reload", formArray);
			});
			//重置
			$("#resetButton").click(function() {
				$("#name").val("");
			});
	});
		
	function initUpload() {
	 	// 上传相关js
		var upload_url = '<z:ukey key="public.ess.upload" context="admin"/>';
	    $('#fileUpload').uploadify({
			'multi': false,
			'swf'      : '<%=resServerUpload %>/assets/js/zd/uploadify.swf',
			'uploader' : upload_url,
			'buttonText':'上传附件',
			'width':'80',
			'debug':false,
			'uploadLimit':'3',
			'onUploadSuccess' : function(file, data, response) {
				var fileName=file.name;
				fileName=fileName.substring(0,fileName.lastIndexOf("."));
				$("#form_name").val(fileName);
				$("#form_fileId").val(file.id);
				$("#form_fileName").val(file.name);
			}
		});
	} 
</script>
</body>
</html>