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
			<div class="page-title">放款请款</div>
			<div class="p5" id="outFormTest">
				<form action="#" id="searchForm" class="zui-form form-search" method="post"
					enctype="multipart/form-data">
					<dl class="form-item">
                <dt class="title">案件号:</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input" type="text" id="caseApplyCode" name="c|caseApplyCode|LK|S">
                    </label>
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
                                   data-width="94"
                                   name="c|productTypeId|LK|S"
                                   data-url="<z:ukey key='com.zdsoft.finance.authGrade.getParentProduct' context='admin'/>&jsoncallback=?"
                                   data-callback="productParentIdChange"
                                   data-height="300"
                                   data-defaultvalue=""
                                   data-valuefield="id" data-textfield="text">
                        </dd>
                        <dd class="detail">
                            <input class="zui-combobox zui-validatebox" type="hidden" id="productChildId"
                                   name="c|productSubtypeId|LK|S" data-width="94"
                                   data-url="<z:ukey key='com.zdsoft.finance.authGrade.getProductByParentId' context='admin'/>&jsoncallback=?"
                                   data-callback=""
                                   data-height="300"
                                   data-defaultvalue=""
                                   data-valuefield="id" data-textfield="text">
                        </dd>

                    </dl>
				<dl class="form-item">
					<dt class="title">案件状态：</dt>
					<dd class="detail">
						<label> 
							<input id="caseType" class="zui-combobox" type="hidden" name="c|caseApplyStatus|E|S"
													data-data="[{'id':'01','text':'正常'},{'id':'02','text':'退单'}]"
													data-valuefield="id" data-textfield="text">
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
						zdata-options="{'idField':'id','url':'<z:ukey key="com.zdsoft.finance.loan.getLoanApply"  context="admin"/>&jsoncallback=?','singleSelect':true,'rownumbers':false,'pagination':true,'tableCls': 'table-index','toolbar':'#btn-applylist'}">
					<table >
						<thead>
							<tr>
								<th data-options="field:CASEAPPLYCODE,width:15%">案件号</th>
								<th data-options="field:CUSTOMERNAME,width:15%">主借人</th>
								<th data-options="field:PRODUCTTYPENAME:15%">产品分类</th>
								<th data-options="field:PRODUCTSUBTYPENAME,width:15%">子产品</th>
								<th data-options="field:APPLYAMOUNT,width:15%" formatter="formatCurrency">贷款金额(元)</th>
								<th data-options="field:LOANAPPLYANOUNT,width:15%" formatter="formatCurrency">放款金额(元)</th>
								<th data-options="field:CASEAPPLYSTATUS,width:10%" formatter="changeStatus">案件状态</th>
								<th data-options="field:status,width:10%" formatter="operate">操作</th>

							</tr>
						</thead>
					</table>
				</div>
				<div id="btn-applylist">
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">


		seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/tools', 'zd/jquery.zds.loading', 'zd/switch','zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker','zd/jquery.zds.table', 'zd/jquery.zds.seleter','zd/jquery.zds.combotree','zd/jquery.zds.checkbox']
	            , function ($,CALLBACK,ZTOOL,Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
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
			CALLBACK.changeStatus = function(index,v){
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
			CALLBACK.operate=function(){
				var  html = '<a href="javaScript:void(0)" onclick="loanApply"><button class="btn-blue">请款</button></a>';
				 return html;
			}
			//请款
			CALLBACK.loanApply=function(num,row){
				if(row.loanApplyAmount<row.APPLYAMOUNT){
					var addAppointmentUrl = '<z:ukey key="com.zdsoft.finance.loan.getLoanApplyDetail" context="admin"/>&jsoncallback=?&param='+encodeURIComponent(JSON.stringify(row));
			        ZDS_MESSAGE_CLIENT.openMenuLink('loanApply','请款申请',addAppointmentUrl + "&openMethod=tabs"); 
				}
				else{
					$.ZMessage.info("提示", "请款额度已达到最大，无法再次请款。", function () {});
				}
			}
			 /**
             * 下拉框联动
             * */
            CALLBACK.productParentIdChange = function (index, rowData, row, thisobj) {
                var parentId = index;
                loadProductChildId(parentId);
            }
            //金额分隔符
            CALLBACK.formatCurrency=function(row, value) {
                return ZTOOL.formatCurrency(value+""); 
            }
            /**
             * 下拉数据
             * @param cataId
             */
            function loadProductChildId(pId) {
                var productChildId = $("#productChildId");
                productChildId.ZCombobox({queryParams: {"parentId": pId}});
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
				 $("#productChildId").ZCombobox("setValue","");
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