<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%> 
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>放款前还款计划查看</title>
<%@ include file="../../common/common_js.jsp" %>
</head>

<body id="body">
<!-- 查册入押 -->
<div class="page-box">
	<!-- 查看案件基本信息 -->
	<%@ include file="../handleMortgage/common/case_apply_view.jsp" %>
	<!-- 还款计划查看 -->
	<!-- datagrid列表区域 -->
    <div class="page-box">
		<div class="page-title">还款计划列表</div>
		<div class="p10">
			<div id="zd-table"></div>
			<table>
				<tr>
					<td style="width: 143px;" align="right"><font size="5">合计:</font></td>
					<td style="width: 215px;" ></td>
					<td style="width: 215px;" title="本金合计" align="center" ><font id="principalTotal" color="red" size="5">0.00</font></td>
					<td style="width: 215px;" title="利息合计" align="center" ><font id="interestTotal" color="red" size="5">0.00</font></td>
					<td style="width: 216px;" title="本息合计" align="center"><font id="amountTotal" color="#7ba023" size="5">0.00</font></td>
					<td style="width: 216px;" ></td>
				</tr>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	seajs.use(['jquery','zd/tools','zd/jquery.zds.table'], function($, ZTOOL) {
		var url_data_list = '<z:ukey key="com.zdsoft.finance.help.findReceivablePlanTempByCaseApplyId" context="admin"/>&caseApplyId=${caseApplyVo.id}';
		$('#zd-table').ZTable({
  		    url:url_data_list,
            singleSelect : true,
            isRowNum : false,
            pagination : false,
            currentPage : 1,
            rows:60,
            tableCls : 'table-index',//table的样式
            columns:[[ 
  			{
      			field : 'periods',
      			title : '期数',
      			align : 'center',width:80
  			}, 
  			{
      			field : 'planRepayDate',
      			title : '还款日期',width:120,
      			align : 'center',
                  formatter: function (row, value) {
                      return ZTOOL.strToDate(value+""); //时间转换
                  }
  			}, 
  			{
      			field : 'planPrincipalAmount',
      			title : '本金(元)',width:120,
      			align : 'center',
                  formatter: function (row, value) {
                      return ZTOOL.formatCurrency(value+""); 
                  }
  			}, 
  			{
      			field : 'planInterestAmount',
      			title : '利息(元)',
      			align : 'center',width:120,
                  formatter: function (row, value) {
                      return ZTOOL.formatCurrency(value+""); 
                  }
  			}, 
  			{
      			field : 'planTotalAmount',
      			title : '本息合计(元)',
      			align : 'center',width:120,
                  formatter: function (row, value) {
                      return ZTOOL.formatCurrency(row.planInterestAmount+row.planPrincipalAmount+""); 
                  }
  			}, 
  			{
      			field : 'remainPrincipal',
      			title : '剩余本金(元)',
      			align : 'center',width:120,
                  formatter: function (row, value) {
                      return ZTOOL.formatCurrency(value+""); 
                  }
  			}
  			]],
  			onLoadSuccess:function(data){
  				var allRows=$('#zd-table').ZTable("getRows");
  				var principalTotal=0;//本金
  				var interestTotal=0;//利息
  				for(var e in allRows){
  					principalTotal+=allRows[e].planPrincipalAmount;
  					interestTotal+=allRows[e].planInterestAmount;
  				}
					$("#principalTotal").html(ZTOOL.formatCurrency(principalTotal.toFixed(2)+""));
					$("#interestTotal").html(ZTOOL.formatCurrency(interestTotal.toFixed(2)+""));
					$("#amountTotal").html(ZTOOL.formatCurrency((principalTotal+interestTotal).toFixed(2)+""));
  			}
  		});
	});
</script>
</body>
</html>