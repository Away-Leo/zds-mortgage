<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>还款计划工具</title>
     <%@ include file="../common/common_js.jsp" %>
</head>

<body id="body">

<!-- 查询搜索区域 -->
	<div class="page-box">
        <div class="page-title">贷款信息</div>
        <div id="searchForm" class="p5">

            <form id="form_search" class="zui-form" action="javascript:void(0);" zdata-options="{}">
                
                <dl class="form-item">
                    <dt class="title"><b class="c-red ">*</b>贷款金额(元)：</dt>
                    <dd class="detail">
                        <label>
                            <input type="text" class="zui-input zui-validatebox"   name="principalAmount"   validate-type="Require,IsDecimal"  value="10000"/>
                        </label>
                    </dd>
                </dl>
                
                <dl class="form-item">
					<dt class="title"><b class="c-red ">*</b> 业务类型：</dt>
					<dd class="detail">
							<input class="zui-combobox zui-validatebox" id="" type="hidden"
	                               data-data="[{'id':'1','text':'兴业贷'},{'id':'2','text':'过桥贷'}]"
	                               data-valuefield="id" data-textfield="text" name="businessType"  validate-type="Require" data-defaultvalue="1">
					</dd>
				</dl>
				
				<dl class="form-item">
					<dt class="title"><b class="c-red ">*</b> 贷款期限(月)：</dt>
					<dd class="detail">
							<input type="text" class="zui-input zui-validatebox"   name="term"   validate-type="Require,Number"  value="12"/>
					</dd>
				</dl>

				<dl class="form-item">
					<dt class="title"><b class="c-red ">*</b>还款方式：</dt>
					<dd class="detail">
					      <label>
							<input class="zui-combobox zui-validatebox" id="repayMethod" type="hidden"
	                               data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0051"
	                               data-valuefield="fullcode" data-textfield="name" name="repayMethod"  validate-type="Require" value="YWDM0051002"  data-defaultvalue="YWDM0051002" data-callback="showOrHideArea">
	                                  </label>
					</dd>
				</dl>
				
				 <dl class="form-item">
                    <dt class="title"><b class="c-red ">*</b>年利率(%)：</dt>
                    <dd class="detail">
                        <label>
                       		 <input type="text" class="zui-input  zui-validatebox"   name="rate"   validate-type="Require,IsDecimal"  value="15"/>
                        </label>
                    </dd>
                </dl>
                
                <dl class="form-item">
                    <dt class="title">每期还款日(号)：</dt>
                      <dd class="detail">
                        <label>
					 			<input type="text" class="zui-input zui-validatebox"   name="repaymentDate"   validate-type="Number,MinSize[1],MaxSize[31]"  validate-false="只能输入数字|每期还款日不能小于1号|每期还款日不能大于31号"/>
					  </label>
                    </dd>
                </dl>
                
                  <dl class="form-item">
                    <dt class="title"><b class="c-red ">*</b>放款日期：</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-date zui-validatebox" type="text"   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',realDateFmt:'yyyyMMdd',vel:'loanDate'})" validate-type="Require" value="2016-10-01">
                         	<input type="hidden" id="loanDate" name="loanDate" value="20161001">
                        </label>
                    </dd>
                </dl>
                
                 <dl class="form-item" id="dl_spTerm" style="display: none;">
                    <dt class="title"><b class="c-red ">*</b>还款期限(月)：</dt>
                    <dd class="detail">
                        <label>
                            	<input type="text" class="zui-input zui-validatebox"   name="spTerm"   validate-type="Number"   placeholder="请填写先息后本还款期限"/>
                        </label>
                    </dd>
                </dl>
                
                 <dl class="form-item" id="dl_spRate" style="display: none;">
                    <dt class="title"><b class="c-red ">*</b>月利率(%)：</dt>
                    <dd class="detail">
                        <label>
                            	<input type="text" class="zui-input zui-validatebox"   name="spRate"   validate-type="Number"  placeholder="请填写先息后本月利率"/>
                        </label>
                    </dd>
                </dl>
                
            </form>
        </div>
    </div>
    
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
	
	<script type="text/javascript">
	    seajs.use(
	    	['jquery', 'zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.combobox', 'zd/switch', 
	    	 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form',
	    	 'datepicker',  'zd/jquery.zds.table', 'zd/jquery.zds.seleter'],
	    	 function ($, CALLBACK,ZTOOL){
	    		
	    		CALLBACK.showOrHideArea=function(value){
	    			if(value==8){
	    				$("#dl_spTerm").show();
	    				$("#dl_spRate").show();
	    				$("input[name=spTerm]").attr("validate-type","Number,Require");
	    				$("input[name=spRate]").attr("validate-type","Number,Require");
	    			}else{
	    				$("#dl_spTerm").hide();
	    				$("#dl_spRate").hide();
	    				$("input[name=spTerm]").attr("validate-type","Number");
	    				$("input[name=spRate]").attr("validate-type","Number");
	    			}
	    			
	    		};
	    		
	    	$.ZUI.init();
	    	var url_data_list = '<z:ukey key="com.zdsoft.finance.help.repaymentPlan" context="admin"/>';
	    	var formS=$("#form_search").serialize();
	    	
	    	$('#zd-table').ZTable({
	    		  url:url_data_list,
	    		  queryParams:formS,
                  singleSelect : true,
                  isRowNum : false,
                  pagination : false,
                  currentPage : 1,
                  rows:60,
                  tableCls : 'table-index',//table的样式
                  toolbar : [
	                      { id : 'btnAdd',
	                    	text : '预览',
	                    	iconCls : 'icon-btn09',
	                        buttonCls : 'btn-blue',
	                        handler : function(){
	                        	var validFlg = $.ZUI.validateForm($('#form_search'));
	            				 if (validFlg) {
	                        		repaymentPlan();
	                        	}
	                        } 
	      				  }
                      ],
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
                            return ZTOOL.formatCurrency(value+""); 
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
        				var amountTotal=0;//本息合计
        				for(var e in allRows){
        					principalTotal+=allRows[e].planPrincipalAmount;
        					interestTotal+=allRows[e].planInterestAmount;
        					amountTotal+=allRows[e].planTotalAmount;
        				}
						$("#principalTotal").html(ZTOOL.formatCurrency(principalTotal.toFixed(2)+""));
						$("#interestTotal").html(ZTOOL.formatCurrency(interestTotal.toFixed(2)+""));
						$("#amountTotal").html(ZTOOL.formatCurrency(amountTotal.toFixed(2)+""));
        			}
        	});
	    	
	    	//初始化参数
	    	function repaymentPlan() {
	    		 var finalResult = $.ZUI.validateForm($('#form_search'));
	    	     if (finalResult) {
	 	    		var formArray=$("#form_search").serializeArray();
	 	    		$('#zd-table').ZTable("reload",formArray);
	    	     }
	    	}
	    });
	</script>
</body>
</html>