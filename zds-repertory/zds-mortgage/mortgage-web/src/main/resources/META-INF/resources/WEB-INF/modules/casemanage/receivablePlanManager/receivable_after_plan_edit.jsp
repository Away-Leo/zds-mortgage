<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div class="frm-content" id="receivablePlanEditDiv">
	<!-- 列表区域 -->
	<div class="page-box">
        <div class="page-title">还款计划编辑</div>
        <div class="p10">
            <div id="receivablePlanEdit"></div>
        <table>
				<tr>
					<td style="width: 143px;" align="right"><font size="5">合计:</font></td>
					<td style="width: 215px;" ></td>
					<td style="width: 215px;" title="本金合计" align="right" ><font id="principalTotal" color="red" size="5">0.00</font></td>
					<td style="width: 215px;" title="服务费合计" align="right" ><font id="serviceFeeTotal" color="red" size="5">0.00</font></td>
					<td style="width: 215px;" title="利息合计" align="center"><font id="interestTotal" color="red" size="5">0.00</font></td>
					<td style="width: 216px;" ></td>
				</tr>
			</table>
        </div>
	</div>
</div>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK,ZTOOL) {
	$('#receivablePlanEdit').ZTable({
         toolbar: [
             {
                 id: 'btncut6',
                 text: '重新生成还款计划',
                 iconCls: 'icon-btn00',
                 buttonCls: 'btn-blue',
                 handler: function () {
                	 //验证还款计划基本信息表单
                	 var validationRe = $.ZUI.validateForm($('#receivablePlanForm'));
                	 if(!validationRe){
                		 return false;
                	 }
                	 //获取还款计划基本信息表单
                	 var receivablePlan=$('#receivablePlanForm').serializeArray();
                	 receivablePlan.splice(0,1);
                	 receivablePlan.push({"name":"reqType","value":"0"});
                     $("#receivablePlanEdit").ZTable("reload",receivablePlan);
                 }
             },
             {
                 id: 'btncut09',
                 text: '添加',
                 iconCls: 'icon-btn08',
                 buttonCls: 'btn-orange',
                 handler: function () {
                     var row = {};
                     $('#receivablePlanEdit').ZTable("addRow");
                 }
             },
             {
                 id: 'btncut5',
                 text: '编辑',
                 iconCls: 'icon-btn22',
                 buttonCls: 'btn-white',
                 handler: function () {
                     $('#receivablePlanEdit').ZTable("editRow");
                 }
             },
             {
                 id: 'btncut4',
                 text: '删除',
                 iconCls: 'icon-btn12',
                 buttonCls: 'btn-gray',
                 handler: function () {
                     $('#receivablePlanEdit').ZTable("deleteRow");
                 }
             }
         ],
         columns: [[
             {field: 'periods', title: '期数', align: 'center', width: '15%' },
             {field: 'planRepayDate', title: '应还款时间', align: 'center', width: '15%',formatter: function (r, v) {
                 var newValue = ZTOOL.strToDate(v);//字符串转换为日期字符串20150101==>2015-01-01
                 return newValue;}
             },
             {field : 'startDate',title : 'startDate',align : 'center',hidden:true},
             {field : 'endDate',title : 'endDate',align : 'center',hidden:true},
             {field: 'planPrincipalAmount', title: '应还本金', align: 'center', width: '20%'},
             {field: 'planServiceFee', title: '服务费', align: 'center', width: '15%'},
             {field: 'planInterestAmount', title: '利息', align: 'center', width: '15%'},
             {field: 'remainPrincipal', title: '剩余本金', align: 'center', width: '20%'}
         ]],
         columnsType: [
             {
            	 "periods": {
                     "inputType": "input",
                     "validateType": "Require"
                 },
                 "planRepayDate": {
                     "inputType": "date"
                 },
                 "planPrincipalAmount": {
                     "inputType": "input",
                     "validateType": "Require,Amount"
                 },
                 "planServiceFee": {
                     "inputType": "input",
                     "validateType": "Require,Amount"
                 },
                 "planInterestAmount": {
                	 "inputType": "input",
                     "validateType": "Require,Amount"
                 },
             },
             {
                 "inputWidth": 100,
                 "inputHeight": 24,
                 "areaWidth": 100,
                 "areaHeight": 24
             }
         ],
         idField: 'id',//每行数据的，唯一标识符
         url: '<z:ukey key="com.zdsoft.finance.help.findReceivablePlanByCaseApplyIdOrReqType" context="admin"/>&caseApplyId=${caseApplyId }',
         singleSelect: false,//表格行是单选还是多选
         isRowNum: false,//是否显示行号
         rows: 8,//分页情况下，显示的条数
         currentPage: 1,//分页情况下的，当前页
         pagination: false,//表格是否分页
         tableCls: 'table-index',//table的样式
         dbclickEditRow: false,//是否双击可编辑行
         batchSave: false,//默认为true，是否批量保存
         onSelect: function (rowIndex, rowData) {
         },
         onUnselect: function (rowIndex, rowData) {
         },
         onSave: function (rowIndex, rowData) {
             //保存数据到数据库
             console.log('保存的数据:' + JSON.stringify(rowData));
             return true;
         },
         onDelete: function (rowsIndex, rowsData) {
             return true;
         },
         onLoadSuccess:function(data){
  				var allRows=$('#receivablePlanEdit').ZTable("getRows");
  				var principalTotal=0;//本金
  				var interestTotal=0;//利息
  				var serviceFeeTotal=0;//本息合计
  				for(var e in allRows){
  					principalTotal+=allRows[e].planPrincipalAmount;
  					interestTotal+=allRows[e].planInterestAmount;
  					serviceFeeTotal+=allRows[e].planServiceFee;
  				}
					$("#principalTotal").html(ZTOOL.formatCurrency(principalTotal.toFixed(2)+""));
					$("#interestTotal").html(ZTOOL.formatCurrency(interestTotal.toFixed(2)+""));
					$("#serviceFeeTotal").html(ZTOOL.formatCurrency(serviceFeeTotal.toFixed(2)+""));
  			}
    });
	
	//初始化页面
	$.ZUI.init("#receivablePlanEditDiv");
	
});
</script>
