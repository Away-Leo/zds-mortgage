<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>还款计划辅助工具</title>
    <%@ include file="../common/common_js.jsp"%>
</head>

<body id="body">

<!-- 查询搜索区域 -->
	<div class="page-box">
        <div class="page-title">贷款信息</div>
        <div id="searchForm" class="p5">

            <form id="form_search" class="zui-form" action="javascript:void(0);" zdata-options="{}">
                
                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>贷款金额(元):</dt>
                    <dd class="detail">
                        <label>
                            <input type="text" id="aMT_Page" class="zui-input zui-validatebox"   name="aMT_Page"   validate-type="Require,IsDecimal"  value="10000"/>
                        </label>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">大写:</dt>
                    <dd class="detail">
                        <span id="capital" class="f12"></span>
                    </dd>
                </dl>
				<br>
				<dl class="form-item">
					<dt class="title"><b class="c-red mr5">*</b>还款方式:</dt>
					<dd class="detail">
							<input class="zui-combobox zui-validatebox" id="" type="hidden"
	                               data-data="[{'id':'1','text':'按月付息到期还本'},{'id':'2','text':'按季付息到期还本'},{'id':'3','text':'按年付息到期还本'},{'id':'4','text':'利随本清'},{'id':'5','text':'等额本金'},{'id':'6','text':'等额本息'},{'id':'7','text':'等本等息'}]"
	                               data-valuefield="id" data-textfield="text" name="repayMethod"  validate-type="Require" data-defaultvalue="1">
					</dd>
				</dl>
				
				 <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>利率(%):</dt>
                    <dd class="detail">
                        <label>
                       		 <input type="text" class="zui-input nwidth3 zui-validatebox"   name="interestRate"   validate-type="Require,IsDecimal"  value="15"/>
                        </label>
                    </dd>
                    <dd class="detail">
							<input class="zui-combobox zui-validatebox" id="" type="hidden" data-width="125"
	                               data-data="[{'id':'09310001','text':'年'},{'id':'09310002','text':'月'},{'id':'09310003','text':'日'}]"
	                               data-valuefield="id" data-textfield="text" name="e_RateUnit"  validate-type="Require" data-defaultvalue="09310001">
					</dd>
                </dl>
                
                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>放款日期:</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-date zui-validatebox" type="text"   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',realDateFmt:'yyyyMMdd',vel:'applyLoanDt'})" validate-type="Require" value="2016-10-01">
                         	<input type="hidden" id="applyLoanDt" name="applyLoanDt" value="20161001">
                        </label>
                    </dd>
                </dl>
                
                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>到期日期:</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-date zui-validatebox" type="text"   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',realDateFmt:'yyyyMMdd',vel:'applyRepayDt'})" validate-type="Require" value="2017-10-01">
                         	<input type="hidden" id="applyRepayDt" name="applyRepayDt" value="20171001">
                        </label>
                    </dd>
                </dl>
                
                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>每期还款日:</dt>
                    <dd class="detail">
							<input class="zui-combobox zui-validatebox" id="" type="hidden"
	                               data-data="[{'id':'1','text':'按放款日'},{'id':'2','text':'指定日期'},{'id':'3','text':'月末'}]"
	                               data-valuefield="id" data-textfield="text" name="selectFixRepaymentDt"  validate-type="Require" data-defaultvalue="1">
					</dd>
					
					 <input type="hidden"    name="repaymentDt"    value="20"/>
                </dl>
                
                <dl class="form-item">
                    <dt class="title">项目算尾:</dt>
                    <dd class="detail">
							<input class="zui-combobox zui-validatebox" id="" type="hidden"
	                               data-data="[{'id':'0','text':'不算尾'},{'id':'1','text':'算尾'}]"
	                               data-valuefield="id" data-textfield="text" name="h_InterestType"  validate-type="Require" data-defaultvalue="0">
					</dd>
                </dl>
                
                 <dl class="form-item">
                    <dt class="title">期限内算尾:</dt>
                    <dd class="detail">
							<input class="zui-combobox zui-validatebox" id="" type="hidden"
	                               data-data="[{'id':'0','text':'不算尾'},{'id':'1','text':'算尾'}]"
	                               data-valuefield="id" data-textfield="text" name="h_IsTailType"  validate-type="Require" data-defaultvalue="0">
					</dd>
                </dl>
            </form>
        </div>
    </div>
	<!-- datagrid列表区域 -->
    <div class="page-box">
		<div class="page-title">还款计划列表</div>
		<button type="button" class="btn-blue" id="previewRepayPlan"><i class="icon-btn09"></i>预览还款计划</button>
		<button type="button" class="btn-blue" id="editRepayPlan"><i class="icon-btn22"></i>编辑还款计划</button>
		<div class="p10">
			<div id="zd-table">
			</div>
			<div class="form-btn">
				<span class="c-orange" id="warnMessage"></span>
		    </div>
		</div>
	</div>
	<!-- 编辑弹出层 -->
	<div id="editPlan"></div>
	<script type="text/javascript">
	    seajs.use(
	    	['jquery', 'zd/jquery.zds.page.callback','zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 
	    	 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form',
	    	 'datepicker',  'zd/jquery.zds.table', 'zd/jquery.zds.seleter'],
	    	 function ($, CALLBACK, Loading, Switch, DropDown, Filter, Check, Zdialog, ZUI_MESSAGE_CLIENT){
	    		 
	    	$.ZUI.init();
	    	
	    	var url_data_list = '<z:ukey key="com.zdsoft.finance.repayplantool.tool.generateRepayPlan" context="admin"/>';
	    	var formS=$("#form_search").serialize();
	    	function showTable(formArray){
	    		var dataRows;
	    		$.ajax({
                    type: 'post',
                    url: url_data_list,
                    data: formArray,
                    dataType: 'json',
                    success: function (data) {
                        if (data.resultStatus == 0) {
                        	var amount=0;
                        	var interstAmount=0;
                        	for(var i=0;i<data.rows.length;i++){
                        		amount=amount+data.rows[i].planPrincipalAmount;
                        		interstAmount=interstAmount+data.rows[i].planInterestAmount;
                        	}
                        	$('#warnMessage').text('合计:'+((amount+interstAmount)/2).toFixed(2)+'元(其中本金:'+(amount/2).toFixed(2)+'元,利息:'+(interstAmount/2).toFixed(2)+'元)');

                        	$('#zd-table').ZTable({
          		    		  data:data,
          		    		  queryParams:formArray,
          	                  singleSelect : true,
          	                  isMergeCell: true, 
          	                  mergeColField: 'planDueDt',
          	                  pagination : false,
          	                  isRowNum:false,
          	                  currentPage : 1,
          	                  rows:60,
          	                  tableCls : 'table-index',//table的样式
          	                  /* toolbar : [
          		                      { id : 'btnAdd',
          		                    	text : '预览还款计划',
          		                    	iconCls : 'icon-btn09',
          		                        buttonCls : 'btn-blue',
          		                        handler : function(){
          		                        	repaymentPlan();
          		                        } 
          		      				  },{ 
          		      					id : 'btnEdit',
          		                    	text : '编辑还款计划',
          		                    	iconCls : 'icon-btn22',
          		                        buttonCls : 'btn-blue',
          		                        handler : function(){
          		                           //load事件
          		                           var url_plan = '<z:ukey key="com.zdsoft.finance.repayplantool.tool.repayPlanToolEdit" context="admin"/>';
          		                           //选中行
          		                           var selectRow = $('#zd-table').ZTable("getSelections");
          		                           var selectDate=selectRow[0];
          		                           if(!selectDate){
          		                        	   $.ZMessage.warning("提示", "请选中一行", function () {
          		           	                    $(".zd-message").ZWindow("close");
          		           	                   });
          		                        	   return ;
          		                           }
          		                           $("#editPlan").load(url_plan, function () {});
          		                      	} 
          		      				 }
          	                      ], */
          	                  columns:[[ 
          	                	{
          		          			field : 'startDt',
          		        			title : '开始日期',
          		        			align : 'center'
          	        			}, 
          	        			{
          		        			field : 'endDt',
          		        			title : '结束日期',
          		        			align : 'center'
          	        			}, 
          	        			{
          		        			field : 'days',
          		        			title : '天数',
          		        			align : 'center'
          	        			}, 
          	        			{
          		        			field : 'planDueDt',
          		        			title : '还款日期',
          		        			align : 'center'
          	        			}, 
          	        			{
          		        			field : 'periodsNo',
          		        			title : '期数',
          		        			align : 'center'
          	        			}, 
          	        			{
          		        			field : 'fundType',
          		        			title : '资金类型',
          		        			align : 'center'
          	        			}, 
          	        			{
          		        			field : 'planAmount',
          		        			title : '计划金额',
          		        			align : 'center'
          	        			}, 
          	        			{
          		        			field : 'currentAccounts',
          		        			title : '当前应收',
          		        			align : 'center'
          	        			}
          	        			]]
          	        		});
                        	
                        }else{
                        	$.ZMessage.error("错误", data.msg, function () {
	                            $(".zd-message").ZWindow("close");
	                        });
                        }
                    },
                    error: function () {
                    	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
                            $(".zd-message").ZWindow("close");
                        });
                    }
                });
		    	
	    	}
	    	
	    	//初始化参数
	    	$('#previewRepayPlan').on('click',function(){
	    		 var finalResult = $.ZUI.validateForm($('#form_search'));
	    	     if (finalResult) {
	 	    		var formArray=$("#form_search").serializeArray();
	 	    		//$('#zd-table').ZTable("reload",formArray);
	 	    		showTable(formArray)
	 	    	}
	    	});
	    	
	    	$('#editRepayPlan').on('click', function(){
	    		 //load事件
                var url_plan = '<z:ukey key="com.zdsoft.finance.repayplantool.tool.repayPlanToolEdit" context="admin"/>';
                //选中行
                var selectRow = $('#zd-table').ZTable("getSelections");
                var selectDate=selectRow[0];
                if(!selectDate){
             	   $.ZMessage.warning("提示", "请选中一行", function () {
	                    $(".zd-message").ZWindow("close");
	                   });
             	   return ;
                }
                $("#editPlan").load(url_plan, function () {});
	    	});

	    	$('#aMT_Page').on('keyup',function(){
	    		var n=$('#aMT_Page').val();
	    		var dx=DX(n);
	    		$('#capital').text(dx);
	    	});
	    	
	    	function DX(n) {
    	        if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(n))
    	            return "数据非法";
    	        var unit = "千百拾亿千百拾万千百拾元角分", str = "";
    	            n += "00";
    	        var p = n.indexOf('.');
    	        if (p >= 0)
    	            n = n.substring(0, p) + n.substr(p+1, 2);
    	            unit = unit.substr(unit.length - n.length);
    	        for (var i=0; i < n.length; i++)
    	            str += '零壹贰叁肆伍陆柒捌玖'.charAt(n.charAt(i)) + unit.charAt(i);
    	        return str.replace(/零(千|百|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元整");
	    	}
	    	
	    	$('#capital').text(DX($('#aMT_Page').val()));
	    });
	</script>
</body>
</html>