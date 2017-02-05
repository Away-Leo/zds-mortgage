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
			<div class="page-title">抵押权人列表</div>
			<div class="p5">
				<form action="#" id="searchForm" class="zui-form form-search" method="post"
					enctype="multipart/form-data">
					<dl class="form-item">
						<dt class="title">名称</dt>
						<dd class="detail">
							<label>
								<input type="text" class="zui-input" name="name|LK|S"
									id="name" />
							</label>
						</dd>
					</dl>
					<dl class="form-item">
						<dt class="title">所属机构</dt>
						<dd class="detail">
							<label>
							<input class="zui-combotree zui-validatebox" type="hidden" name="orgId|E|S" id="orgId" data-multiple="false" data-defaultvalue=""
	                        	data-url="<z:res resource='enssential.org.findOrgToTree' isDefault='true'/>&jsoncallback=?&type=dept"
	                           data-valuefield="id" data-textfield="text" >
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
						zdata-options="{'idField':'id','url':'<z:ukey key="com.zdsoft.finance.parameter.getMortgagePerson"  context="admin"/>&jsoncallback=?','singleSelect':true,'isRowNum':false,'pagination':true,'tableCls': 'table-index','toolbar':'#btn-applylist'}">
					<table >
						<thead>
							<tr>
								<th data-options="field:orgName,width:15%">所属机构</th>
								<th data-options="field:type,width:15%">类别</th>
								<th data-options="field:name,width:20%">名称</th>
								<th data-options="field:status,width:15%" formatter="changeStatus">状态</th>
								<th data-options="field:updateTime,width:20%" formatter="changeDate">添加日期</th>
								<th data-options="field:projectName,width:15%" formatter="operate">操作</th>

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
    <div id="testForm" class="mt20">
        <form id="dialogForm" class="zui-form">
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>所属机构</dt>
                <dd class="detail">
                    <label>
                    	<input type="text" id="mortgagePersonId" style="display:none;">
                         <input class="zui-combotree zui-validatebox" type="hidden" name="form_orgId" id="form_orgId" data-multiple="false" data-defaultvalue=""
	                        	data-url="<z:res resource='enssential.org.findOrgToTree' isDefault='true'/>&jsoncallback=?&type=dept"
	                           data-valuefield="id" data-textfield="text" validate-type="Require" validate-false="请选择机构!">

                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>类别</dt>
                <dd class="detail">
                	<label>
	                    <input id='form_type' class="zui-combobox zui-validatebox" type="hidden" data-toggle="combobox"
	                  data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=mt001000"
	                   data-valuefield="id" data-textfield="text"
	                   validate-type="Require" validate-false="请选择类别!">
                   </label>
                </dd>
            </dl>
            <dl class="form-item" >
                <dt class="title"><b class="c-red mr5">*</b>名称</dt>
                <dd class="detail">
                    <label>
                        <input type="text" class="zui-input zui-validatebox" id="form_name"  validate-type="Require,Length[1-25]" validate-false="名称不能为空"/>
                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>状态</dt>
                <dd class="detail">
                	<label>
		                <input class="zui-checkbox zui-validatebox" id="form_status" type="hidden" data-multiple="false"
		                               data-data="[{'id':'1','text':'启用'},{'id':'0','text':'停用'}]"
		                               data-valuefield="id" data-textfield="text" validate-type="Require" data-defaultvalue="1" data-toggle="checkbox">
                	</label>
                </dd>
            </dl>
           
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
										$.ZMessage.info("提示", "无效的抵押权人名称", function () {
											
		                    			});
										return;
									}
									 //todo something
				                    var param={};
				                    param.name=$("#form_name").val();
				                    param.status=$("#form_status").ZCheckbox('getValue');
				                    param.type=$("#form_type").ZCombobox('getValue');
				                    param.orgId=$("#form_orgId").ZComboTree('getValue');
				                    if($("#mortgagePersonId").val()){
				                    	param.id=$("#mortgagePersonId").val();
				                    }
				                    $.ajax({
				                    	method : "post",
				                    	dataType : "json",
				                    	data : param,
				                    	url : '<z:ukey key="com.zdsoft.finance.parameter.updateMortgagePerson"  context="admin"/>&jsoncallback=?',
				                    	success : function(result){
				                    		if(result.resultStatus==0){
				                    			$.ZMessage.info("提示", result.msg, function () {});
				                    			$('#zd-table').ZTable("reload");
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
				var  html = '<a href="javaScript:void(0)" onclick="doEdit"><button class="btn-blue">编辑</button></a>'+'&nbsp;&nbsp;'+
             	'<a href="javaScript:void(0)" onclick="doDelete"><button class="btn-blue">删除</button></a>'
				 return html;
		            
			}
			//新增行
			CALLBACK.add=function(){
				$(".dialog-title").children()[0].innerHTML=" 新增";
				//var rows = $('#zd-table').ZTable("getSelections");
				 $("#dialogEdit").Zdialog("open");
				 $("#form_status").ZCheckbox('setValue',"1");
			}
			//编辑行
			CALLBACK.doEdit=function(num,selRow){
				$(".dialog-title").children()[0].innerHTML=" 编辑";
				 $("#dialogEdit").Zdialog("open");
				 if(selRow){
					 $("#form_name").val(selRow.name);
	                 $("#form_status").ZCheckbox('setValue',selRow.status+"");
	                 $("#form_type").ZCombobox('setValue',selRow.type);
	                 $("#form_orgId").ZComboTree('setValue',selRow.orgId);
	                 $("#mortgagePersonId").val(selRow.id);
				 }
			}
			//删除行
			CALLBACK.doDelete=function(num,selRow){ 
				$.ZMessage.question("确认", "确认删除该抵押权人吗？", function (r) {

	    				 if(selRow){
	    					 var param={};
	    					 param.id=selRow.id;
	    	                 $.ajax({
	    	                    	method : "post",
	    	                    	dataType : "json",
	    	                    	data : param,
	    	                    	url : '<z:ukey key="com.zdsoft.finance.parameter.deleteMortgagePerson"  context="admin"/>&jsoncallback=?',
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
             	var formArray=$("#searchForm").serialize();
 				formArray = decodeURIComponent(formArray, true);
				$('#zd-table').ZTable("reload", formArray);
			});
			//重置
			$("#resetButton").click(function() {
				$("#name").val("");
				$("#orgId").ZComboTree('setValue',"");
			});

	});
</script>
</body>
</html>