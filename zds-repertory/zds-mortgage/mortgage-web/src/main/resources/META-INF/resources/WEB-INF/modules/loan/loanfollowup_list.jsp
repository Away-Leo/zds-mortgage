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
			<div class="page-title">放款跟进</div>
			<div class="p5" id="outFormTest">
				<form action="#" id="searchForm" class="zui-form form-search" method="post"
					enctype="multipart/form-data">
					<dl class="form-item">
                <dt class="title">机构:</dt>
                <dd class="detail">
                        <input class="zui-combotree zui-validatebox" type="hidden"  name="c|mechanismCode|E|S" id="mechanismCode" data-multiple="false" data-defaultvalue=""
								data-url="<z:res resource='enssential.org.findOrgToTree' isDefault='true'/>&jsoncallback=?&type=dept"
	                           data-valuefield="id" data-textfield="text" >
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">案件号:</dt>
                <dd class="detail">
                    <input class="zui-input" type="text" id="caseApplyCode" name="c|caseApplyCode|LK|S">
                </dd>
            </dl>
             <dl class="form-item">
                <dt class="title">主借人:</dt>
                <dd class="detail">
                    <input class="zui-input" id="customerName" type="text" name="cust|customerName|LK|S">
                </dd>
            </dl>
            <dl class="form-item">
                        <dt class="title">产品分类：</dt>
                        <dd class="detail">
                            <input class="zui-combobox zui-validatebox" type="hidden" id="productParentId"
                                   name="c|productTypeId|LK|S"
                                   data-url="<z:ukey key='com.zdsoft.finance.authGrade.getParentProduct' context='admin'/>&jsoncallback=?"
                                   data-callback="productParentIdChange"
                                   data-height="300"
                                   data-defaultvalue=""
                                   data-valuefield="id" data-textfield="text">
                        </dd>

                    </dl>
				<dl class="form-item">
					<dt class="title">放款状态:</dt>
					<dd class="detail">
						<label> 
							<input id="caseType" class="zui-combobox" type="hidden" name="apply|status|E|S"
													data-data="[{'id':'1','text':'待放款'},{'id':'2','text':'准放款'},{'id':'3','text':'已放款'}]"
													data-valuefield="id" data-textfield="text">
						</label>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">放款批次号:</dt>
					<dd class="detail">
						<label> 
							<input id="batchCode" class="zui-combobox" type="hidden" name="apply|batchNumber|E|S"
                              				 data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00101"
											data-valuefield="id" data-textfield="text" data-callback="changeBatchCode">
						</label>
					</dd>
				</dl>
					<div class="form-btn">
	                    <button type="button" class="btn-blue" id="searchButton">查询</button>
	                    <button type="button" class="btn-gray" id="resetButton">重置</button>
	                </div>
				</form>
				<!-- table演示 -->
				<div class="zui-datagrid" id="zd-table"
						zdata-options="{'idField':'id','url':'<z:ukey key="com.zdsoft.finance.loan.getLoanFollowUp"  context="admin"/>&jsoncallback=?','singleSelect':true,'rownumbers':false,'pagination':true,'tableCls': 'table-index','toolbar':'#btn-applylist'}">
					<table >
						<thead>
							<tr>
								<th data-options="field:MECHANISMNAME">机构</th>
								<th data-options="field:CASEAPPLYCODE">案件号</th>
								<th data-options="field:CUSTOMERNAME">主借人</th>
								<th data-options="field:PRODUCTTYPENAME">产品分类</th>
								<th data-options="field:APPLYAMOUNT" formatter="formatCurrency">申请金额(元)</th>
								<th data-options="field:pendingLoan" formatter="formatCurrency">待放款金额(元)</th>
								<th data-options="field:days">计划天数(天)</th>
								<th data-options="field:applyTerm">贷款期限</th>
								<th data-options="field:applyDate">放款日期</th>
								<th data-options="field:CAPITALNAME">资金来源</th>
								<th data-options="field:FUNDPLANNAME">所属资金计划</th>
								<th data-options="field:actualLimitStatus">备付资金分配</th>
								<th data-options="field:STATUS" formatter="changeStatus">集团放款状态</th>
								<th data-options="field:batchNumber">批次号</th>
								<th data-options="field:status,width:18%" formatter="operate">操作</th>
							</tr>
						</thead>
					</table>
				</div>
				
				<div id="btn-applylist">
					 <a class="zui-toolbar" id="btn-export" text="准放款导出" iconCls="icon-add" buttonCls="btn-blue" handler="exportPend"></a>
				</div>
			</div>
		</div>
	</div>
	
	 <form id="subForm" action='<z:ukey key="com.zdsoft.finance.loan.exportLoanApply"  context="admin"/>'>
        	<input id="param" name="param" type="hidden" style="display:none">
      </form>
	
	<script type="text/javascript">


		seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/tools', 'zd/jquery.zds.loading', 'zd/switch','zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker','zd/jquery.zds.table', 'zd/jquery.zds.seleter','zd/jquery.zds.combotree','zd/jquery.zds.checkbox']
	            , function ($,CALLBACK,ZTOOL, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
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
			//状态转换函数
			CALLBACK.changeStatus = function(row){
				switch(row.STATUS){
				case "1":
					return "待放款";
					break;
				case "2":
					return "准放款";
					break;
				case "3":
					return "已放款";
					break;
				}
				
			};
			 //金额分隔符
            CALLBACK.formatCurrency=function(row, value) {
                return ZTOOL.formatCurrency(value+""); 
            }
			//准放款导出
            CALLBACK.exportPend = function () {
				if($("#batchCode").ZCombobox("getValue")){
					var rows=$('#zd-table').ZTable("getRows");
	            	var paraStr="";
	            	for(var i=0;i<rows.length;i++){
	            		paraStr+=rows[i].ID+".";
	            	}
	            	paraStr+=$("#batchCode").ZCombobox("getValue");
	            	$("#param").val(paraStr);
	            	$("#subForm").submit();
				}
				else{
					 $.ZMessage.info("提示", "请选择放款批次", function () {
                     });
				}
            }
			//操作栏的回调函数
			CALLBACK.operate=function(row){
				var  html = '';
				if(row.STATUS=='1'){
					html+='<a href="javaScript:void(0)" onclick="allowLoan"><button class="btn-blue">准放款</button></a>&nbsp;';
					html+='<a href="javaScript:void(0)" onclick="history"><button class="btn-blue">跟进历史</button></a>';
				}
				else if(row.STATUS=='2'){
					html+='<a href="javaScript:void(0)" onclick="loan"><button class="btn-blue">放款</button></a>&nbsp;';
					html+='<a href="javaScript:void(0)" onclick="cancelAllowLoan"><button class="btn-blue">取消准放款</button></a>&nbsp;';
					html+='<a href="javaScript:void(0)" onclick="history"><button class="btn-blue">跟进历史</button></a>';
				}
				else{
					html+='<a href="javaScript:void(0)" onclick="history"><button class="btn-blue">跟进历史</button></a>';
				}
				 return html;
			}
			//准放款
			CALLBACK.allowLoan=function(index,row){
				row.operate="sure";
				var addAppointmentUrl = '<z:ukey key="com.zdsoft.finance.loan.allowLoanPage" context="admin"/>&jsoncallback=?&param='+encodeURIComponent(JSON.stringify(row));
		        ZDS_MESSAGE_CLIENT.openMenuLink('allowLoan','确认准放款',addAppointmentUrl + "&openMethod=tabs"); 
			}
			//取消准放款
			CALLBACK.cancelAllowLoan=function(index,row){
				row.operate="cancel";
				var addAppointmentUrl = '<z:ukey key="com.zdsoft.finance.loan.allowLoanPage" context="admin"/>&jsoncallback=?&param='+encodeURIComponent(JSON.stringify(row));
		        ZDS_MESSAGE_CLIENT.openMenuLink('allowLoan','取消准放款',addAppointmentUrl + "&openMethod=tabs"); 
			}
			//跟进历史
			CALLBACK.history=function(index,row){
				var addAppointmentUrl = '<z:ukey key="com.zdsoft.finance.loan.loanHistoryList" context="admin"/>&jsoncallback=?&id='+row.ID;
		        ZDS_MESSAGE_CLIENT.openMenuLink('applyHistory','跟进历史',addAppointmentUrl + "&openMethod=tabs"); 
			}
			//放款
			CALLBACK.loan=function(index,row){
				var addAppointmentUrl = '<z:ukey key="com.zdsoft.finance.loan.loanPage" context="admin"/>&jsoncallback=?&param='+encodeURIComponent(JSON.stringify(row));
		        ZDS_MESSAGE_CLIENT.openMenuLink('loan','放款',addAppointmentUrl + "&openMethod=tabs"); 
			}
			//修改放款批次回调
			CALLBACK.changeBatchCode=function(index,row){
				setTimeout(function(){
					$("#searchButton").click();
				},200);
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
				  $.ZUI.resetForms('#outFormTest');//重置

			});
			 function doSearch() {
					$('#zd-table').ZTable("reload",{});
				}; 
		    ZDS_MESSAGE_CLIENT.refreshThis=function(){
				doSearch();
		    }; 

	});
</script>
</body>
</html>