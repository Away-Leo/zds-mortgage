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
			<div class="page-title">收款主体</div>
			<div class="p5">
				<form action="#" id="searchForm" class="zui-form form-search" method="post"
					enctype="multipart/form-data">
					<dl class="form-item">
						<dt class="title">机构名称</dt>
						<dd class="detail">
							<input class="zui-combotree zui-validatebox" type="hidden" name="orgId|E|S" id="orgId" data-multiple="false" data-defaultvalue=""
	                        	data-url="<z:res resource='enssential.org.findOrgToTree' isDefault='true'/>&jsoncallback=?&type=dept"
	                           data-valuefield="id" data-textfield="text" >
						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">收款主体</dt>
						<dd class="detail">
							<label>
								<input type="text" class="zui-input" name="inBody|LK|S" id="inBody" />
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
						zdata-options="{'idField':'id','url':'<z:ukey key="com.zdsoft.finance.parameter.getInComeBody"  context="admin"/>&jsoncallback=?&updateTime|OB|OB=1','singleSelect':true,'isRowNum':false,'pagination':true,'tableCls': 'table-index','toolbar':'#btn-applylist','onLoadSuccess':'megreColumn'}">
					<table >
						<thead>
							<tr>
								<th data-options="field:orgName,width:15%">机构名称</th>
								<th data-options="field:inBody,width:20%">收款主体</th>
								<th data-options="field:updateTime,width:20%" formatter="changeDate">操作时间</th>
								<th data-options="field:remark,width:25%">备注</th>
								<th data-options="field:projectName,width:20%" formatter="operate">操作</th>

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
                <dt class="title"><b class="c-red mr5">*</b>机构名称</dt>
                <dd class="detail">
                    <label>
                    	<input type="text" style="display:none;" id="inComeBodyId">
                        <input class="zui-combotree zui-validatebox" type="hidden" name="form_orgId" id="form_orgId" data-multiple="false" data-defaultvalue=""
	                        	data-url="<z:res resource='enssential.org.findOrgToTree' isDefault='true'/>&jsoncallback=?&type=dept"
	                           data-valuefield="id" data-textfield="text" validate-type="Require" validate-false="请选择机构!">

                    </label>
                </dd>
            </dl>
            <dl class="form-item" >
                <dt class="title"><b class="c-red mr5">*</b>收款主体</dt>
                <dd class="detail">
                    <label>
                        <input type="text" class="zui-input zui-validatebox" id="form_inBody"  validate-type="Require,Length[1-25]" validate-false="收款主体不能为空"/>
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
			                	var isValidate = $.ZUI.validateForm($('#dialogForm'));
								if(isValidate){
									if($("#form_inBody").val()=="null"){
										$.ZMessage.info("提示", "无效的收款主体名称", function () {
											
		                    			});
										return;
									}
									 //todo something
				                    var param={};
				                    param.inBody=$("#form_inBody").val();
				                    param.orgId=$("#form_orgId").ZComboTree('getValue');
				                    param.remark=$("#form_remark").val();
				                    if($("#inComeBodyId").val()){
				                    	param.id=$("#inComeBodyId").val();
				                    }
				                    $.ajax({
				                    	method : "post",
				                    	dataType : "json",
				                    	data : param,
				                    	url : '<z:ukey key="com.zdsoft.finance.parameter.updateInComeBody"  context="admin"/>&jsoncallback=?',
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
				var date = new Date(v.time);
				return date.Format('yyyy-MM-dd hh:mm');
			};
			//状态转换函数
			CALLBACK.changeStatus = function(index,v){
				switch(v){
				case 1:
					return "启用";
					break;
				case 0:
					return "停用";
					break;
				}
				
			};
			//操作栏的回调函数
			CALLBACK.operate=function(){
				var  html = '<a href="javaScript:void(0)" onclick="doEdit"><button class="btn-blue" >编辑</button></a>'+'&nbsp;&nbsp;'+
             	'<a href="javaScript:void(0)" onclick="doDelete"><button class="btn-blue" >删除</button></a>'
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
					 $("#form_inBody").val(selRow.inBody);
					 $("#form_remark").val(selRow.remark);
	                 $("#form_orgId").ZComboTree('setValue',selRow.orgId);
	                 $("#inComeBodyId").val(selRow.id);
				 }
			}
			//删除行
			CALLBACK.doDelete=function(num,selRow){ 
				$.ZMessage.question("确认", "确认删除该收款主体吗？", function (r) {

	    				 if(selRow){
	    					 var param={};
	    					 param.id=selRow.id;
	    	                 $.ajax({
	    	                    	method : "post",
	    	                    	dataType : "json",
	    	                    	data : param,
	    	                    	url : '<z:ukey key="com.zdsoft.finance.parameter.deleteInComeBody"  context="admin"/>&jsoncallback=?',
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
			CALLBACK.megreColumn=function(){
			}
			$.ZUI.init();
			//查询
			$("#searchButton").click(function() {
             	var formArray=$("#searchForm").serialize();
 				formArray = decodeURIComponent(formArray, true);
				$('#zd-table').ZTable("reload", formArray);
			});
			//重置
			$("#resetButton").click(function() {
				$("#inBody").val("");
				$("#orgId").ZComboTree('setValue',"");
			});

	});
</script>
</body>
</html>