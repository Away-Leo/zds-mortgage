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
	<div class="frm-content frm-bottom" id="opinionDiv">
	    <div class="page-box">
	        <div class="page-title">操作信息</div>
	        <div id="zd-table">
	            
	        </div>
	        <input type="hidden" id="data" value='${log}'>
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
			 //日期转换函数
	           function changeDate (v) {
	                var date = new Date(v);
	                return date.Format('yyyy-MM-dd hh:mm');
	            };
	            var rowData=JSON.parse($("#data").val());
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
		            {field: 'dealType', title: '操作类型', align: 'center',width: '15%',formatter:function(index,v) {
		            	switch(v){
						case "1":
							return "准放款";
							break;
						case "2":
							return "取消准放款";
							break;
			            case "3":
							return "放款";
							break;
						}
		            }},
		            {field: 'dealAmount', title: '处理金额(元)', align: 'center',width: '15%'},
		            {field: 'dealRealDate', title: '实际发生日期', align: 'center',width: '15%',formatter:function(index,v) {
		            	if(v==0){
		            		return "";
		            	}
		            	 var date = new Date(v);
			             return date.Format('yyyy-MM-dd');
		            }},
		            {field: 'dealDate', title: '操作时间', align: 'center',width: '15%',formatter:function(index,v) {
		            	 var date = new Date(v);
			             return date.Format('yyyy-MM-dd hh:mm');
		            }},
		            {field: 'handlerCode', title: '处理人', align: 'center',width: '15%'},
		            {field: 'remark', title: '备注', align: 'center',width: '15%'},
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

	});
</script>

</body>
</html>