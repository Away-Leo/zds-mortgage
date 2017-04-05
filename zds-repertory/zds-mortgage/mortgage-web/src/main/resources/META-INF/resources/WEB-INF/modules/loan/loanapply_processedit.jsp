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
	<div id="outFormTest" class="p10">
           <form id="search" class="zui-form form-search" action="javascript:void(0);" 
            zdata-options={"url":'<z:ukey key="com.zdsoft.finance.loan.submitLoanApply"  context="admin"/>',"callBack":"saveCallBack"}>

                <div id="innerFormTest">
                   <table class="table-detail">
		                <tr>
		                    <td class="td-title pct10">申请单</td>
		                    <td class="pct20">
			                    <dl class="form-item">
			                        <dd class="detail">
			                            <label>
			                             	<input id="applyObj" name="applyObj" type="hidden" value='${applyObj}'>
			                                <input id="loanApplyCode" name="loanApplyCode" class="zui-input zui-disabled" type="text" validate-type="Require" value="${apply.loanApplyCode}"  disabled/>
			                            </label>
			                        </dd>
			                    </dl>
                            </td>
                            <td class="td-title">申请人</td>
		                    <td>
		                    	<dl class="form-item">
			                        <dd class="detail">
			                            <label>
			                                <input id="applyPerson" name="applyPerson" class="zui-input zui-disabled" type="text" validate-type="Require" value="${apply.applyPerson}" disabled/>
			                            </label>
			                        </dd>
			                    </dl>
		                    </td>
		                    <td class="td-title">申请人部门</td>
		                    <td>
		                    	<dl class="form-item">
			                        <dd class="detail">
			                            <label>
			                                <input id="applyPersonDep" name="applyPersonDep" class="zui-input zui-disabled" type="text" validate-type="Require" value="${apply.applyPersonDep}" disabled/>
			                                <input type="hidden" id="productId" value="${apply.productId}">
                                			<input type="hidden" id="caseApplyId" value="${apply.ID}">
			                            </label>
			                        </dd>
			                    </dl>
		                    </td>
		                </tr>
		                
		                <tr>
		                    <td class="td-title">申请时间</td>
		                    <td>
		                    	<dl class="form-item">
			                        <dd class="detail">
			                            <label>
			                                <input id="applyDate" name="applyDate" class="zui-input zui-disabled" type="text" validate-type="Require" value="${apply.applyDate}" disabled/>
			                            </label>
			                        </dd>
			                    </dl>
		                    </td>
		                    <td class="td-title">机构</td>
		                    <td>
		                    	<dl class="form-item">
			                        <dd class="detail">
			                            <label>
                                			<input id="applyOrg" name="applyOrg" class="zui-input zui-disabled" type="text" validate-type="Require" value="${apply.applyOrg}" disabled/>
			                            </label>
			                        </dd>
			                    </dl>
		                    </td>
		                    <td class="td-title"></td>
		                    <td>
		                    </td>
		                </tr>
		                 <tr>
		                    <td class="td-title">所在主体</td>
		                    <td colspan="3">
		                    	<dl class="form-item">
			                        <dd class="detail">
			                            <label>
	                                		<input id="company" name="company" class="zui-input zui-disabled" type="text" validate-type="Require" value="${apply.company}" disabled/>
			                            </label>
			                        </dd>
			                    </dl>
		                    </td>
		                    <td class="td-title">收款账户名称</td>
		                    <td>
		                    	<dl class="form-item">
			                        <dd class="detail">
			                            <label>
                                			<input id="payee" name="payee" class="zui-input zui-disabled" type="text" validate-type="Require" value="${apply.receiveBankAccountName}" disabled/>
			                            </label>
			                        </dd>
			                    </dl>
		                    </td>
		                </tr>
		                <tr>
		                    <td class="td-title">收款开户行</td>
		                    <td colspan="3">
		                    	<dl class="form-item">
			                        <dd class="detail">
			                            <label>
                                			<input id="bank" name="bank" class="zui-input zui-disabled" type="text" validate-type="Require" value="${apply.receiveBankName}" disabled/>
			                            </label>
			                        </dd>
			                    </dl>
		                    </td>
		                    <td class="td-title">收款帐号</td>
		                    <td>
		                    	<dl class="form-item">
			                        <dd class="detail">
			                            <label>
                                			<input id="account" name="account" class="zui-input zui-disabled" type="text" validate-type="Require" value="${apply.receiveBankAccount}" disabled/>
			                            </label>
			                        </dd>
			                    </dl>
		                    </td>
		                </tr>
		                 <tr>
		                    <td class="td-title"><b class="c-red mr5">*</b>请款金额(元)</td>
		                    <td colspan="3">
		                    	<dl class="form-item">
			                        <dd class="detail">
			                            <label>
                                			<input id="applyAmount" name="applyAmount" class="zui-input zui-disabled zui-validatebox" type="text" validate-type="Require,Digital[10-2]" validate-false="请款金额不能为空" value="${apply.applyAmount}" disabled/>
			                            </label>
			                        </dd>
			                    </dl>
		                    </td>
		                    <td class="td-title"></td>
		                    <td>
		                    </td>
		                </tr>
	            	</table>
                    <!-- table演示 -->
                
                <div id="zd-table"></div>
                
                 <div id="enclosure">
                 </div>
                </div>
            </form>
        </div>
        
        <script type="text/javascript">

        seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/tools', 'zd/jquery.zds.loading', 'zd/switch','zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker','zd/jquery.zds.table', 'zd/jquery.zds.seleter','zd/jquery.zds.combotree','zd/jquery.zds.checkbox']
        , function ($,CALLBACK, ZTOOL,  Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
			 //金额分隔符
		    function formatCurrency (row, value) {
		        return ZTOOL.formatCurrency(value+""); 
		    }
			//加载附件
			$("#enclosure").load('<z:ukey key="com.zdsoft.finance.fileStore.fileShowListPage" context="admin"/>'+"&productId="+$("#productId").val()+"&caseApplyId="+$("#caseApplyId").val()+
					"&businessId="+$("#loanApplyCode").val()+"&linkCode=21&useType=edit");
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
			 //日期转换函数
	           function changeDate (v) {
	                var date = new Date(v);
	                return date.Format('yyyy-MM-dd hh:mm');
	            };
				//状态转换函数
				 function changeStatus(v){
					switch(v){
					case "01":
						return "正常";
						break;
					case "02":
						return "退单";
						break;
					}
					
				};
				//操作栏的回调函数
				 function operate(){
					var  html = '<a href="javaScript:void(0)" onclick="see" class="btn-blue">查看</a>';
					 return html;
				}
				//查看
				CALLBACK.see=function(num,row){
					var addAppointmentUrl = '<z:ukey key="com.zdsoft.finance.casemanage.riskAuditView" context="admin"/>&jsoncallback=?&projectId='+row.caseApplyId;
			        ZDS_MESSAGE_CLIENT.openMenuLink('caseRiskMessage','风险信息',addAppointmentUrl + "&openMethod=tabs"); 
				}
			var rowData=new Array();
			rowData.push(JSON.parse($("#applyObj").val()));
			
			var tableData={
		            errored: false,
		            footer: [],
		            id: "",
		            msg: "",
		            optional: null,
		            resultStatus: 0,
		            rows: rowData,
		            total: 10,
		            type: [
		                {
		                    "itemid": "readOnly",
		                    "productid": "readOnly",
		                    "unitcost": "date",
		                    "attr1": "textarea",
		                    "listprice": "text"
		                },
		                {
		                    "inputWidth": 200,
		                    "areaWidth": 300,
		                    "inputHeight": 30,
		                    "areaHeight": 60
		                }]
		        };
			$('#zd-table').ZTable({
		        columns: [[
		            {field: 'customerName', title: '主借人', align: 'center',width: '15%'},
		            {field: 'productTypeName', title: '产品分类', align: 'center',width: '15%'},
		            {field: 'productSubtypeName', title: '子产品', align: 'center',width: '15%'},
		            {field: 'applyAmount', title: '贷款金额(元)', align: 'center',width: '15%',formatter:formatCurrency},
		            {field: 'dynamicRate', title: '收益率(%)', align: 'center',width: '15%'},
		            {field: 'contractStartDate', title: '合同起始日', align: 'center',width: '15%'},
		            {field: 'contractEndDate', title: '合同结束日', align: 'center',width: '15%'},
		            {field: 'days', title: '贷款天数', align: 'center',width: '15%'},
		            {field: 'field:status', title: '风险信息', align: 'center',width: '15%',formatter:operate}
		        ]],
		        data: tableData,
		        idField: 'id',
		        singleSelect: true,
		        rows: 1,
		        pagination: false,
		        tableCls: 'table-index',//table的样式
		        rownumbers:false
		    });
			$.ZUI.init();
			//保存
		    ZDS_WORKFLOW_CLIENT.saveFunction = function (datas) {
				 var WORKFLOW_FLAG=ZDS_WORKFLOW_PARAM._STATUS_VALIDATE_ERROR;//1、提交，需要，默认提交失败！
				//---------start------流程中有修改页面，需要提交业务数据操作------------------
				//流程参数
				var args = JSON.parse(datas);
				var params = "applyAmount="+$("#applyAmount").val();
		        params += '&processInstanceId=' + args.processInstanceId;
		        params += '&taskInstanceId=' + args.taskInstanceId;
		        params += '&taskId=' + args.taskId;
		        params += '&taskName=' + args.taskName;
		        params += '&jobAppCd=' + args.jobAppCd;
				$.ajax({
					url:'<z:ukey key="com.zdsoft.finance.prCostitemApply.editJobSave" context="admin"/>',
					data:params,
					type:"post",
					async: false,
					dataType:"json",
					traditional:true,
					success:function(rdata){
						if(rdata.resultStatus == 0){
							WORKFLOW_FLAG=ZDS_WORKFLOW_PARAM._STATUS_SUCCESS;
						}else{
							$.ZMessage.error("错误", rdata.msg, function () {
			                });
						}
					}
				});
		    	//---------end------流程中有修改页面，需要提交业务数据操作------------------
		    	
		    	//4、返回流程状态
		    	return WORKFLOW_FLAG;
		    };
		    
		    //提交方法
		    ZDS_WORKFLOW_CLIENT.submitFunction = ZDS_WORKFLOW_CLIENT.saveFunction;

	});
</script>

</body>
</html>