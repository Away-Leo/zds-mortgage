<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>处理</title>
<%@ include file="../../common/common_js.jsp" %>
</head>  
<body>
  
	<div class="frm-content frm-bottom" id="opinionDiv">
		<div class="save">
		    <button id="launchAfterSupervise" class="btn-blue mr10">发起督办</button>
		    <button id="sub_back" class="btn-blue mr10">返回</button>
		</div>   
	    <div class="page-box">
	        <div class="page-title">案件基本信息</div>
	        <div class="p5">
	            <table class="table-detail">
	                <tr>
	                    <td class="td-title pct10">案件号</td>
	                    <td >${caseApply.caseApplyCode}</td>
	                    <td class="td-title pct10">机构</td>
	                    <td >${caseApply.orgName}</td>
	                    <td class="td-title pct10">主借人</td>
	                    <td >${caseApply.customerName}</td>
	                </tr>
	                <tr>
	                    <td class="td-title pct10">放款金额</td>
	                    <td >${caseApply.loanAmount}元</td>
	                    <td class="td-title pct10">贷款期限</td>
	                    <td >${caseApply.loanTerm}</td>
	                    <td class="td-title pct10">本金余额</td>
	                    <td >${caseApply.balance}元</td>
	                </tr>
	                <tr>
	                    <td class="td-title pct10">逾期天数</td>
	                    <td ></td>
	                    <td class="td-title pct10"></td>
	                    <td ></td>
	                    <td class="td-title pct10"></td>
	                    <td ><input type="hidden" value="${caseApply.caseApplyId}" id="caseApplyId"></td>
	                </tr>
	            </table>
	        </div>
	    </div>
	    <div class="page-box">
	        <div class="page-title">联系人信息
	        	<button class="btn-blue" style="float:right;margin-right:20px;" id="addEm">新增紧急联系人</button>
	        	<button class="btn-blue" style="float:right;margin-right:20px;" id="searchReceivablePlan">还款计划表入口</button>
	        </div>
	        
	        <div class="p5">
	        	<div class="zui-datagrid" id="zd-table"
						zdata-options="{'idField':'id','url':'<z:ukey key="com.zdsoft.finance.afterloan.contacts"  context="admin"/>&jsoncallback=?&caseApplyId=${caseApply.caseApplyId}','singleSelect':true,'rownumbers':false,'pagination':false,'tableCls': 'table-index','toolbar':'#btn-applylist', 'isMergeCell':true,'mergeColField':'joinTypeName,customerName','mergeCol':'joinTypeName,customerName,relationshipName,actualUsePerson,need'}">
					<table >
						<thead>
							<tr>
								<th data-options="field:joinTypeName,width:15%">参与类型</th>
								<th data-options="field:customerName,width:15%">姓名</th>
								<th data-options="field:relationshipName:15%">与主借人关系</th>
								<th data-options="field:actualUsePerson,width:15%" formatter="actualUsePerson">是否实际用款人</th>
								<th data-options="field:contactTypeName,width:15%">联系方式</th>
								<th data-options="field:phoneNumber,width:15%" formatter="handleNull">电话号码</th>
								<th data-options="field:customerId,width:15%,hidden:true">用户</th>
								<th data-options="field:need,width:10%" formatter="operate">操作</th>
							</tr>
						</thead>
					</table>
				</div>
				<div id="btn-applylist">
				</div>
	        </div>
	    </div>
	    
	    
	</div>
	
	<div id="dialogEdit" style="display: none">
    <div id="testForm" class="mt20">
        <form id="dialogForm" class="zui-form">
        	<table class="table-detail">
	                <tr>
	                 	<td class="td-title pct20"><b class="c-red mr5">*</b>姓名</td>
		                <td class="pct20">
			                    <dl class="form-item">
			                        <dd class="detail">
			                            <label>
			                            	<input type="hidden" id="customerId" value="">
			                            	<input type="hidden" id="isEmergency" value="">
			                             	<input type="text" class="zui-input zui-validatebox" validate-type="Require,Length[1-64]" validate-false="姓名不能为空" id="form_name" name="contactName"/>
			                            </label>
			                        </dd>
			                    </dl>
                        </td>
	                    <td class="td-title pct20"><b class="c-red mr5">*</b>参与类型</td>
		                <td class="pct20">
			                    <dl class="form-item">
			                        <dd class="detail">
			                            <label>
			                             	<input id="form_jointype" class="zui-combobox zui-validatebox" type="hidden" name="form_jointype"  validate-type="Require" validate-false="请选择参与类型"
															data-data="[{'id':'1','text':'紧急联系人'}]" data-valuefield="id" data-textfield="text">
															
			                            </label>
			                        </dd>
			                    </dl>
                        </td>
	                </tr>
	                <tr>
	                   <td class="td-title pct20"><b class="c-red mr5">*</b>与主借人关系</td>
		                <td class="pct20">
			                    <dl class="form-item">
			                        <dd class="detail" id="reEm">
				                             	<input id="form_relationEM" class="zui-combobox zui-validatebox" type="hidden" name="relationship" validate-type="Require" validate-false="请选择与主借人关系"
	                              				 data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=r0143"
												 data-valuefield="id" data-textfield="text">
			                        </dd>
			                        <dd class="detail" id="re">
											<label>
												 <input id="form_relation" class="zui-combobox" type="hidden" name="form_jointype"   validate-false="请选择参与类型"
												 data-data="[{'id':'1','text':'紧急联系人'}]" data-valuefield="id" data-textfield="text">
											 </label>
			                        </dd>
			                    </dl>
                        </td>
                        <td class="td-title pct20"></td>
                       	<td class="pct20"></td>
	                </tr>
	       </table>
	        <div id="table_contact">
            </div>
	       
        </form>
    </div>
</div>
	
<script>
seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter', 'zd/jquery.zds.combotree', 'zd/jquery.zds.checkbox']
, function ($, CALLBACK, COMBOBOX, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
	var contactTypeData;
	
	//查看还款计划  
	$("#searchReceivablePlan").click(function(){
		var receivablePlanUrl = '<z:ukey key="com.cnfh.rms.casemanage.receivablePlan.viewReceivablePlan" context="admin"/>&caseApplyId=${caseApply.caseApplyId}';
        ZDS_MESSAGE_CLIENT.openMenuLink('查看还款计划','查看还款计划',receivablePlanUrl + "&openMethod=tabs");
	});
	
	//处理null
	CALLBACK.handleNull=function(index,v){
		if(!index.phoneNumber){
			return "";
		}
		else{
			return index.phoneNumber;
		}
	}
	
		//操作栏的回调函数
		CALLBACK.operate=function(){
			var  html = '<a href="javaScript:void(0)" onclick="follow" class="btn-blue">跟催</a>&nbsp;';
			html+='<a href="javaScript:void(0)" onclick="edit" class="btn-blue">编辑</a>';
			 return html;
		}
		CALLBACK.actualUsePerson=function(r,v){
			if('YWDM0049002' == v){return '是';}
				return '否';  
		}
		
		//跟催
		CALLBACK.follow=function(index,row){
			var addAppointmentUrl = '<z:ukey key="com.zdsoft.finance.afterloan.initFollowInfoMessage" context="admin"/>&jsoncallback=?&customerId='+row.customerId+"&caseApplyId="+$("#caseApplyId").val();
	        ZDS_MESSAGE_CLIENT.openMenuLink('followinfo','跟催',addAppointmentUrl + "&openMethod=tabs"); 
		}
		//编辑
		CALLBACK.edit=function(index,row){
			//设置参与类型
			var joinTypeData=new Array();
		    var sel_jointype={};
		    sel_jointype.text=row.joinTypeName;
		    sel_jointype.id=1;
		    joinTypeData.push(sel_jointype);
			$("#form_jointype").ZCombobox({
				data : joinTypeData
			});
			//设置与主借人关系
			var relationshipData=new Array();
			var sel_relationshipName={};
			sel_relationshipName.text=row.relationshipName;
			sel_relationshipName.id=1;
			relationshipData.push(sel_relationshipName);
			$("#form_relation").ZCombobox({
				data : relationshipData
			});
			$("#reEm").css("display","none");
			$("#re").css("display","");
			//为了通过表单校验，设置一个初始值
			$("#form_relationEM").ZCombobox('setValue',"r01431");
			//设置表单初始值
			$("#form_name").val(row.customerName);
			$("#form_relation").ZCombobox('setValue',"1");
			$("#form_jointype").ZCombobox('setValue',"1");
			//禁用控件
			$("#form_jointype").ZCombobox('disable');
			$("#form_relation").ZCombobox('disable');
			$("#form_name").attr("disabled",true);
			//刷新联系方式表格
			var tableParam="customerId="+row.customerId;
			if(row.isEmergency){
				tableParam+="&isEmergency="+row.isEmergency;
			}
			$('#table_contact').ZTable("reload",tableParam);
			//打开dialog
			 $("#dialogEdit").Zdialog("open");
			 $("#customerId").val(row.customerId);
			 $("#isEmergency").val(row.isEmergency);
		}
		//新增紧急联系人
		$("#addEm").click(function(){
			$('#table_contact').ZTable("reload","customerId=001");
			//启用控件
			$("#form_jointype").ZCombobox('enabled');
			$("#form_relation").ZCombobox('enabled');
			$("#form_name").attr("disabled",false);
			//显示隐藏主借人关系下拉框
			$("#reEm").css("display","");
			$("#re").css("display","none");
			//设置参与类型为紧急联系人
			$("#form_jointype").ZCombobox({
				data : [{id:"1",text:"紧急联系人"}]
			})
			$("#form_jointype").ZCombobox('setValue',"1");
			$("#customerId").val("");
			//打开dialog
			 $("#dialogEdit").Zdialog("open");
		});
		 $("#dialogEdit").Zdialog({
             title: "新增/编辑联系方式",
             width: 850,
             height: 500,
             closed: true,
             isDrag: false,
             buttons: [
                 {
                     id: 'message-btn', text: '确定', buttonCls: 'btn-blue',
                     handler: function () {
                         var isValidate = $.ZUI.validateForm($('#dialogForm'));
                         if (isValidate) {
                        	var param={};
                        	if($("#customerId").val()){
                        		param.customerId=$("#customerId").val();
                        	}
                        	//是否紧急联系人
                        	if($("#isEmergency").val()){
                        		param.isEmergency=$("#isEmergency").val();
                        	}
                        	//案件id
                        	param.caseApplyId=$("#caseApplyId").val();
                        	//联系方式内容
                        	param.content=JSON.stringify($('#table_contact').ZTable("getRows"));
                        	//名称
                        	param.customerName=$("#form_name").val();
                        	//主借人关系
                        	param.relation=$("#form_relationEM").ZCombobox('getValue');
                        	 $.ajax({
                                 method: "post",
                                 dataType: "json",
                                 data: param,
                                 url: '<z:ukey key="com.zdsoft.finance.afterloan.addOrUpdateContact"  context="admin"/>&jsoncallback=?',
                                 success: function (result) {
                                     if (result.resultStatus == 0) {
                                         $.ZMessage.info("提示", result.msg, function () {
                                        	 $("#dialogEdit").Zdialog("close");
                                         });
                                     }
                                     else {
                                         $.ZMessage.error("错误", result.msg, function () {
                                         });
                                     }
                                    var reloanParam="caseApplyId="+$("#caseApplyId").val();
                                     $('#zd-table').ZTable("reload", reloanParam);
                                 }

                             });
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
		 //发起督办       
		 $("#launchAfterSupervise").click(function(){
			 ZDS_MESSAGE_CLIENT.openMenuLink('viewCaseApply', '督办申请', '<z:ukey key="com.zdsoft.finance.afterloan.afterSupervise.launchAfterSuperviseInit" context="admin"/>?&caseApplyId=${caseApply.caseApplyId}');
		 });
		 //返回
		 $("#sub_back").click(function(){
			 ZDS_MESSAGE_CLIENT.refreshOpenner();
          	 ZDS_MESSAGE_CLIENT.closeSelf();
		 });
    	$.ZUI.init();
    	$.ajax({
    		type : "post",
    		url : "<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00117",
    		dataType: "jsonp",
    		async: false,
    	    success : function(data){
    	    	contactTypeData="[";
    	    	for(var i=0;i<data.length;i++){
    	    		contactTypeData+="{'id':'"+data[i].id+"',"+"'text':'"+data[i].text+"'},";
    	    	}
    	    	contactTypeData=contactTypeData.substring(0,contactTypeData.length-1);
    	    	contactTypeData+="]";
    	    	$('#table_contact').ZTable({
    	            toolbar: [
    	                {
    	                    id: 'btncut3',
    	                    text: '新增联系方式',
    	                    iconCls: 'icon-btn08',
    	                    buttonCls: 'btn-blue',
    	                    handler: function () {
    	                        /* $('#zd-table-single').ZTable("addRow", {
    	                            "contactType": "",
    	                            "phoneNumber": "",
    	                            "isDelete": "0",
    	                        }); */
    	                        $('#table_contact').ZTable("addOneRow",{
    	                        	  "contactType": "",
    		                            "phoneNumber": "",
    		                            "isDeleted": "false",
    		                            "id" : ""
    	                        });
    	                    }
    	                }
    	            ],
    	            columns: [[
    	                {field: 'contactType', title: '联系方式', align: 'center', width: '33%'},
    	                {field: 'phoneNumber', title: '电话号码', align: 'center', width: '33%'},
    	                {
    	                    field: 'isDeleted', title: '状态', align: 'center', width: '33%'
    	                },
    	                {field: 'id', title: 'id', align: 'center', width: '0%',hidden:true}
    	            ]],
    	            columnsType: [
    	                {
    	                    "contactType": {
    	                        "inputType": "combobox",
    	                        "data": {
    	                            "valueField": "id",
    	                            "textField": "text",
                     				 "data":contactTypeData
    	                        },
    	                        "validateType": "Require"
    	                    },
    	                    "phoneNumber": {
    	                    	"inputType": "input",
    	                        "validateType": "Require,Mobile"
    	                    },
    	                    "isDeleted": {
    	                    	"inputType": "combobox",
    	                        "data": {
    	                            "valueField": "itemid",
    	                            "textField": "name",
    	                            "data": "[{'itemid': 'false', 'name': '生效'}, {'itemid': 'true', 'name': '失效'}]"
    	                        },
    	                        "validateType": "Require"

    	                    }
    	                },
    	                {
    	                    "inputWidth": 140,
    	                    "inputHeight": 124,
    	                    "areaWidth": 200,
    	                    "areaHeight": 124
    	                }
    	            ],
    	            idField: 'id',//每行数据的，唯一标识符
    	            url:'<z:ukey key="com.zdsoft.finance.afterloan.contactsImformation"  context="admin"/>&jsoncallback=?',
    	            singleSelect: true,//表格行是单选还是多选
    	            isRowNum: false,//是否显示行号
    	            rows: 8,//分页情况下，显示的条数
    	            currentPage: 1,//分页情况下的，当前页
    	            pagination: false,//表格是否分页
    	            tableCls: 'table-index',//table的样式
    	            dbclickEditRow: true,//是否双击可编辑行
    	            onEdit:true,
    	            batchSave: true//默认为true，是否批量保存
    	        });
    	    }
    	});

    });


</script>
	
</body>
</html>