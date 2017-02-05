<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file='../common/common_js.jsp'%>
</head>
<body>
		<!-- BEGIN CONTAINER -->
	<div class="frm-content">
		<div class="page-box">
			<div class="page-title">特批事项管理</div>
			<div class="p5">
				<form action="#" id="searchForm" class="zui-form form-search" method="post"
					enctype="multipart/form-data">
					<dl class="form-item">
						<dt class="title">编码</dt>
						<dd class="detail">
							<label>
								<input type="text" class="zui-input" name="code|LK|S"
									id="code" />
							</label>
						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">类型</dt>
						<dd class="detail">
							<label>
								<input  id="type" name="" class="zui-combobox zui-validatebox" type="hidden" data-toggle="combobox"
						 	data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=emt001000"		                   data-valuefield="id" data-textfield="text"
			                   data-valuefield="id" data-textfield="text"
			                   >
							</label>
						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">特批事项</dt>
						<dd class="detail">
							<label>
								<input type="text" class="zui-input" name="name|LK|S"
									id="name" />
							</label>
						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">状态</dt>
						<dd class="detail">
							<label>
								<input  id="status" name="status|E|S" class="zui-combobox zui-validatebox" type="hidden" data-toggle="combobox"
			                   data-data="[{'id':'1','text':'启用'},{'id':'0','text':'停用'}]"
			                   data-valuefield="id" data-textfield="text"
			                   >
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
						zdata-options="{'idField':'id','url':'<z:ukey key="com.zdsoft.finance.parameter.getExceptMatter"  context="admin"/>&jsoncallback=?','singleSelect':true,'rownumbers':false,'pagination':true,'tableCls': 'table-index','toolbar':'#btn-applylist'}">
					<table >
						<thead>
							<tr>
								<th data-options="field:code,width:10%">编码</th>
								<th data-options="field:type,width:15%">类型</th>
								<th data-options="field:name,width:15%">特批事项</th>
								<th data-options="field:empName,width:10%">操作人</th>
								<th data-options="field:updateTime,width:15%" formatter="changeDate">操作时间</th>
								<th data-options="field:remark,width:15%">备注</th>
								<th data-options="field:status,width:10%" formatter="changeStatus">状态</th>
								<th data-options="width:20%" formatter="operate">操作</th>

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
                <dt class="title">编码</dt>
                <dd class="detail">
                    <label>
                    	<input id="exceptMatterId" style="display:none;">
                        <input type="text" class="zui-input zui-validatebox" id="form_code" disabled="disabled"/>
                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>类型</dt>
                <dd class="detail">
                 <label>
                	<input  id="form_type" class="zui-combobox zui-validatebox" type="hidden" data-toggle="combobox"
						 	data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=emt001000"		                   data-valuefield="id" data-textfield="text"
		                   validate-type="Require" validate-false="请选择类型">
		          </label>
                </dd>
            </dl>
             <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>特批事项</dt>
                <dd class="detail">
                 <label>
                	<input type="text" class="zui-input zui-validatebox" id="form_name"  validate-type="Require,Length[1-25]" validate-false="特批事项不能为空"/>
                </label>
                </dd>
            </dl>
             <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>状态</dt>
                <dd class="detail">
                 <label>
                	<input class="zui-checkbox zui-validatebox" id="form_status" type="hidden" data-multiple="false"
                               data-data="[{'id':'1','text':'启用'},{'id':'0','text':'停用'}]"
                               data-valuefield="id" data-textfield="text" validate-type="Require" data-defaultvalue="1">
                  </label>
                </dd>
            </dl>
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
			                	var isValidate = $.ZUI.validateForm($('#testForm'));
								if(isValidate){
									if($("#form_name").val()=="null"){
										$.ZMessage.info("提示", "无效的特批事项名称", function () {
											
		                    			});
										return;
									}
									var param={};
									 param.type=$("#form_type").ZCombobox('getValue');
									 param.name=$("#form_name").val();
									 param.remark=$("#form_remark").val();
									 param.status=$("#form_status").ZCheckbox('getValue');
									 param.code=$("#form_code").val();
									 if($("#form_code").val()){
					                    	 param.code=$("#form_code").val();
					                 }
				                    if($("#exceptMatterId").val()){
				                    	param.id=$("#exceptMatterId").val();
				                    }
				                    $.ajax({
				                    	method : "post",
				                    	dataType : "json",
				                    	data : param,
				                    	url : '<z:ukey key="com.zdsoft.finance.parameter.updateExceptMatter"  context="admin"/>&jsoncallback=?',
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
				if(v){
					var date = new Date(v.time);
					return date.Format('yyyy-MM-dd hh:mm');
				}
				else{
					return '';
				}
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
			CALLBACK.operate=function(row){
				if(row.id=='0'){
					return ;
				}
				var  html = '<a href="javaScript:void(0)" onclick="doEdit"><button class="btn-blue">编辑</button></a>'+'&nbsp;&nbsp;'+
             	'<a href="javaScript:void(0)" onclick="doDelete"><button class="btn-blue">删除</button></a>'
				 return html;
			}
			//新增行
			CALLBACK.add=function(){
				$(".dialog-title").children()[0].innerHTML=" 新增";
				var param={};
				$.ajax({
                	method : "post",
                	dataType : "json",
                	data : param,
                	url : '<z:ukey key="com.zdsoft.finance.parameter.getBasicMessage"  context="admin"/>&jsoncallback=?',
                	success : function(result){
                		if(result.resultStatus==0){
                			var obj=result.optional;
                			 $("#dialogEdit").Zdialog("open");
            				 $("#form_status").ZCheckbox('setValue',"1");
            				 $("#form_code").val(obj.code);
                		}
                		else{
                			$.ZMessage.error("错误", result.msg, function () {});
                		}
                	}
                			
                });
				//var rows = $('#zd-table').ZTable("getSelections");
			}
			//编辑行
			CALLBACK.doEdit=function(num,row){
				$(".dialog-title").children()[0].innerHTML=" 编辑";
				 $("#dialogEdit").Zdialog("open");
				 if(row){
					 $("#form_name").val(row.name);
	                 $("#form_code").val(row.code);
	                 $("#form_remark").val(row.remark);
	                 $("#form_type").ZCombobox('setValue',row.type);
	                 $("#exceptMatterId").val(row.id);
	                 $("#form_status").ZCheckbox('setValue',row.status+"");
				 }
			}
			//删除行
			CALLBACK.doDelete=function(num,selRow){
				$.ZMessage.question("确认", "确认删除该特批事项吗？", function (r) {
	    				 if(selRow){
	    					 var param={};
	    					 param.id=selRow.id;
	    	                 $.ajax({
	    	                    	method : "post",
	    	                    	dataType : "json",
	    	                    	data : param,
	    	                    	url : '<z:ukey key="com.zdsoft.finance.parameter.deleteExceptMatter"  context="admin"/>&jsoncallback=?',
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
				$("#type").ZCombobox('setValue',"");
				$("#name").val("");
				$("#code").val("");
				$("#status").ZCombobox('setValue',"");
			});

	});
</script>
</body>
</html>