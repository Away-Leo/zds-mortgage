<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div class="frm-content">
	<!-- 列表区域 -->
	<div class="page-box">
        <div class="page-title">还款计划编辑</div>
        <div class="p10">
            <div id="receivablePlanEdit"></div>
        </div>
	</div>
</div>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK,ZTOOL) {
	//初始化页面
	$.ZUI.init();
	$('#receivablePlanEdit').ZTable({
         toolbar: [
             {
                 id: 'btncut6',
                 text: '预生成还款计划',
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
                 id: 'btncut3',
                 text: '添加',
                 iconCls: 'icon-btn08',
                 buttonCls: 'btn-orange',
                 handler: function () {
                     $('#receivablePlanEdit').ZTable("addRow", {
                         "itemid": 0,
                         "productid": "445454",
                         "unitcost": "45454",
                         "attr1": "45656",
                         "listprice": "12323"
                     });
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
             {field: 'periodsNo', title: '期数', align: 'center', width: '300' },
             {field: 'repaymentDate', title: '应还款时间', align: 'center', width: '20%',formatter: function (r, v) {
                 var newValue = ZTOOL.strToDate(v);//字符串转换为日期字符串20150101==>2015-01-01
                 return newValue;}
             },
             {
                 field: 'repaymentAmount', title: '应还本金', align: 'center', width: '20%'
             },
             {
                 field: 'serviceChange', title: '服务费', align: 'center', width: '20%'
             },
             {
                 field: 'interestAmount', title: '利息', align: 'center', width: '20%'
             },
             {
                 field: 'surplusRepaymentAmount', title: '剩余本金', align: 'center', width: '20%'
             }
         ]],
         columnsType: [
             {
                 "repaymentDate": {
                     "inputType": "date"
                 },
                 "repaymentAmount": {
                     "inputType": "input",
                     "validateType": "Require,Amount"
                 },
                 "serviceChange": {
                     "inputType": "text",
                     "validateType": "Require,Amount"
                 },
                 "interestAmount": {
                	 "inputType": "text",
                     "validateType": "Require,Amount"
                 },
                 "surplusRepaymentAmount": {
                	 "inputType": "text",
                     "validateType": "Require,Amount"
                 },
             },
             {
                 "inputWidth": 140,
                 "inputHeight": 24,
                 "areaWidth": 200,
                 "areaHeight": 24
             }
         ],
         idField: 'id',//每行数据的，唯一标识符
         //queryParams: {param: 'xxooxxooxxoo000000000000000000'},//分页业务参数
         url: '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.receivablePlanGenerate" context="admin"/>&jsoncallback=?&caseApplyId=${caseApplyId }',
         // url: 'table.json',
         singleSelect: false,//表格行是单选还是多选
         isRowNum: false,//是否显示行号
         rows: 8,//分页情况下，显示的条数
         currentPage: 1,//分页情况下的，当前页
         pagination: false,//表格是否分页
         tableCls: 'table-index',//table的样式
         dbclickEditRow: true,//是否双击可编辑行
         batchSave: false,//默认为true，是否批量保存
         onSelect: function (rowIndex, rowData) {
             //alert("选中" + rowIndex+'='+JSON.stringify(rowData));
         },
         onUnselect: function (rowIndex, rowData) {
             //alert("选中" + rowIndex+'='+"取消" + rowIndex+'='+JSON.stringify(rowData));
         },
         onSave: function (rowIndex, rowData) {
             //保存数据到数据库
             console.log('保存的数据:' + JSON.stringify(rowData));
             return true;
         },
         onDelete: function (rowsIndex, rowsData) {
             //alert("删除IDs" + JSON.stringify(rowsIndex) + ',删除成功:' + JSON.stringify(rowsData));
             return true;
         }
    });
	
});
</script>
