<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%@ include file="../common/common_js.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>征信录入</title>
<style type="text/css">
.tsInput{
	width: 50px;
	border-left: 0px;
	border-right: 0px;
	border-top: 0px;
	border-bottom: 1px solid #CCCCCC;
	box-sizing: border-box;
	height: 24px;
	line-height: 24px;
	margin-right: 2px;
	font-size: 12px;
	vertical-align: top;
	text-overflow: ellipsis;
}
</style>
</head>
<body id="body">
<div class="frm-content frm-bottom">
    <div class="z-inp-left">
        <div  class="picScroll-current">
            <div class="crt-img" id="picShow">

            </div>
            <div class="crt-icon"><span class="crt-page">共${size}张</span></div>
        </div>

        <div class="picScroll-left">
            <div class="bd">
                <a class="prev"><i class="icon-power-add"></i></a>
                <a class="next"><i class="icon-power-delete"></i></a>
                <ul class="picList" id="picList">
                <c:forEach var="item" items="${atts}" varStatus="status">
                    <li <c:if test="${status.index == 0}">class="active"</c:if>><img src="../upload/download?attachmentId=${item.id}" /></li>
                </c:forEach>
                </ul>
            </div>
        </div>


    </div>
    <div class="z-inp-right">
        <div class="page-box">
            <h1 class="page-title">
                	本人贷款情况
            </h1>
            <div class="p10">
            <div id="zds-table-myCredit"
                 class="zui-datagrid"
                 zdata-options='{
                 "url":"<z:ukey key="com.zdsoft.finance.credit.myCredit.getMyCredit" context="admin"/>&creditSituationId|E|S=${cs.id}&jsoncallback=?",
                 "singleSelect":true,
                 "pagination":true,
                 "idField":"id",
                 "tableCls":"table-index",
                 "dbclickEditRow":false,
                 "onLoadSuccess":"operate",
                 "isScroll":true
                 }'>
                        <table>
                            <thead>
                            <tr>
                                <th data-options="field:loanTypesName">贷款种类</th>
                                <th data-options="field:accountStatusName">账户状态</th>
                                <th data-options="field:loanTerm">贷款年限(年)</th>
                                <th data-options="field:loanAmount">贷款金额(元)</th>
                                <th data-options="field:loanBalance">贷款余额(元)</th>
                                <th data-options="field:id" formatter="formatFuntion">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>

            </div>
        </div>
        <div class="page-box">
            <h1 class="page-title">
                本人信用卡情况
            </h1>
            <div class="p10">
                <div id="zds-table-myCreditCard"
                     class="zui-datagrid"
                     zdata-options='{
                     "url":"<z:ukey key="com.zdsoft.finance.credit.myCreditCard.getMyCreditCard" context="admin"/>&creditSituationId|E|S=${cs.id}&jsoncallback=?",
                     "singleSelect":true,
                     "pagination":true,
                     "idField":"id",
                     "tableCls":"table-index",
                     "dbclickEditRow":false,
                     "onLoadSuccess":"operate",
                     "isScroll":true
                     }'>
                    <table>
                        <thead>
                        <tr>
                            <th data-options="field:creditLimit">信用额度(元)</th>
                            <th data-options="field:accountStatusName">账户状态</th>
                            <th data-options="field:currentTotalOverdue">当前逾期总额(元)</th>
                            <th data-options="field:currentOverdraft">当前透支额(元)</th>
                            <th data-options="field:id" formatter="formatReapyStatusRecord">最近12个月还款状态记录</th>
                            <th data-options="field:id" formatter="formatFuntionCard">操作</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <div class="page-box">
        <h1 class="page-title">
            征信综合情况
        </h1>
        <div class="p10">
            <form id="credit_situation_form" class="zui-form" method="post" enctype="multipart/form-data">
                <table class="table-detail mb5">
                    <tr>
                        <th><b class="c-red mr5">*</b>人行征信报告打印时间</th>
                        <td class="tl">
                            <dl class="form-item">
                                <dd class="detail">
                                    <label>
                                        <input class="zui-date strToDate zui-validatebox" type="text" name="reportPrintingDate1" value="${cs.reportPrintingDate}"
                                               onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'reportPrintingDate'})"
                                               validate-type="Require" disabled="disabled" />
                                    </label>
                                </dd>
                            </dl>
                        </td>
                        <th><b class="c-red mr5">*</b>人行报告1个月内查询次数</th>
                        <td>
                            <dl class="form-item">
                                <dd class="detail">
                                    <label>
                                        <input class="zui-input zui-validatebox"  type="text" name="queryNumber" value="${cs.queryNumber}"
                                               validate-type="Require,Integer" disabled="disabled"/>
                                    </label>
                                </dd>
                            </dl>
                        </td>
                    </tr>
                    <tr>
                        <th>审批意见</th>
                        <td colspan="3">
                            <textarea class="zui-area pct100"  name="creditEvaluation" disabled="disabled">${cs.creditEvaluation }</textarea>
                        </td>
                    </tr>
                </table>
                <input type="hidden" name="id" value="${cs.id }">
                <input type="hidden" name="caseApplyId" value="${caseApplyId}">
                <input type="hidden" name="customerId" value="${customerId}">
                <input type="hidden" name="customerName" value="${customerName}">
                <input type="hidden" name="creditId" value="${creditId}">
            </form>
        </div>
    </div>
</div>
<!--弹出框1-->
<div id="dialog1" style="display: none">
<div id="pledgeEditDiv" class="p5">
<form id="credit_form" class="zui-form" method="post" enctype="multipart/form-data">
   <table class="table-detail">
           <tr>
               <td class="td-title pct10"><b class="c-red mr5">*</b>贷款种类</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                          <dd class="detail">
                             <input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require" name="loanTypesCode" id="loanTypesCode"
									 data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00164"
									data-valuefield="fullcode" data-textfield="name" data-callback="loanTypesCodeChange" disabled="disabled">
                          </dd>
                          <input type="hidden" id="loanTypesName" name="loanTypesName"/>
                   </dl>
               </td>
               <td class="td-title pct10"><b class="c-red mr5">*</b>账户状态</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                          <input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require" name="accountStatusCode" id="accountStatusCode"
									 data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00165"
									data-valuefield="fullcode" data-textfield="name" data-callback="accountStatusCodeChange" disabled="disabled">
                       </dd>
                       <input type="hidden" id="accountStatusName" name="accountStatusName"/>
                   </dl>
               </td>
           </tr>
           <tr>
               <td class="td-title pct10"><b class="c-red mr5">*</b>贷款发放日期</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                          <dd class="detail">
                              <label>
                                  <input class="zui-date strToDate zui-validatebox"  disabled="disabled" validate-type="Require" type="text" id="loanDate1" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'loanDate',dateFmt:'yyyy-MM-dd',onpicked:function(){WdateValidate(this);}})"
                                      readonly />
                                  <input type="hidden" id="loanDate" name="loanDate" >
                              </label>
                          </dd>
                   </dl>
               </td>
               <td class="td-title pct10"><b class="c-red mr5">*</b>贷款年限</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                           <label>
                               <input class="zui-input zui-validatebox" type="text" disabled="disabled" name="loanTerm" id="loanTerm" validate-type="Require,Number">
                           	   <span class="word">年</span>
                           </label>
                       </dd>
                   </dl>
               </td>
           </tr>
           <tr>
               <td class="td-title pct10"><b class="c-red mr5">*</b>贷款金额</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                          <dd class="detail">
                              <label>
                                  <input class="zui-input zui-validatebox" type="text"  disabled="disabled" validate-type="Require,IsOverNumber" validate-false="请输入正确的金额!" name="loanAmount" id="loanAmount">
                              	  <span class="word">元</span>	
                              </label>
                          </dd>
                   </dl>
               </td>
               <td class="td-title pct10"><b class="c-red mr5">*</b>贷款余额</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                           <label>
                               <input class="zui-input zui-validatebox" type="text" disabled="disabled" name="loanBalance" id="loanBalance" validate-type="Require,IsOverNumber" validate-false="请输入正确的金额!">
                           	   <span class="word">元</span>	
                           </label>
                       </dd>
                   </dl>
               </td>
           </tr>
            <tr>
               <td class="td-title pct10"><b class="c-red mr5">*</b>当前逾期期数</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                          <dd class="detail">
                              <label>
                                  <input class="zui-input zui-validatebox" type="text"  disabled="disabled" validate-type="Require,Number" name="currentOverdue" id="currentOverdue">
                              	  <span class="word">次</span>	
                              </label>
                          </dd>
                   </dl>
               </td>
               <td class="td-title pct10"><b class="c-red mr5">*</b>当前逾期金额</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                           <label>
                               <input class="zui-input zui-validatebox" type="text" disabled="disabled" name="currentOverdueAmount" id="currentOverdueAmount" validate-type="Require,IsOverNumber" validate-false="请输入正确的金额!">
                               <span class="word">元</span>
                           </label>
                       </dd>
                   </dl>
               </td>
           </tr>
           <tr>
               <td class="td-title pct10"><b class="c-red mr5">*</b>累计逾期期数</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                          <dd class="detail">
                              <label>
                                  <input class="zui-input zui-validatebox" type="text" disabled="disabled" validate-type="Require,Number" name="cumulativeOverdue" id="cumulativeOverdue">
                                  <span class="word">次</span>
                              </label>
                          </dd>
                   </dl>
               </td>
               <td class="td-title pct10"><b class="c-red mr5">*</b>最高逾期期数</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                           <label>
                               <input class="zui-input zui-validatebox" type="text" disabled="disabled" name="maximumOverdue" id="maximumOverdue" validate-type="Require,Number" >
                               <span class="word">次</span>
                           </label>
                       </dd>
                   </dl>
               </td>
           </tr>
           <tr>
               <td class="td-title pct10"><b class="c-red mr5">*</b>最近12个月还款状态记录</td>
               <td class="pct20" colspan="3">
                   <dl class="form-item form-auto">
                          <dd class="detail">
                              <label>
	                              <span class="word">累计逾期</span>
	                                  <input class="zui-validatebox tsInput" 
									    type="text" validate-type="Require,Number" disabled="disabled" validate-false="请输入正确的累计逾期次数!" name="cumulativeOverdue12" id="cumulativeOverdue12">
	                              <span class="word">次，最高逾期</span>
	                                  <input class="zui-validatebox tsInput" 
									    type="text" validate-type="Require,Number" disabled="disabled" validate-false="请输入正确的累计逾期次数!" name="maximumOverdue12" id="maximumOverdue12">
	                              <span class="word"> 次。</span>
                              </label>
                          </dd>
                   </dl>
               </td>
           </tr>
           <tr>
               <td class="td-title pct10"><b class="c-red mr5">*</b>最近5年内</td>
               <td class="pct20" colspan="3">
                   <dl class="form-item form-auto">
                          <dd class="detail">
                              <label>
	                              <input class="zui-validatebox tsInput" 
									    type="text" validate-type="Require,Number" disabled="disabled" validate-false="请输入正确的累计逾期次数!" name="monthsOverdue5" id="monthsOverdue5">
	                              <span class="word">个月处于逾期状态，</span>
	                                  <input class="zui-validatebox tsInput" 
									    type="text" validate-type="Require,Number" disabled="disabled" validate-false="请输入正确的累计逾期次数!" name="lastYear90Count" id="lastYear90Count">
	                              <span class="word">次90天以上逾期，已于</span>
	                              <input class="zui-date strToDate zui-validatebox" disabled="disabled" validate-type="Require" type="text" id="closingDate1" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'closingDate',dateFmt:'yyyy-MM-dd'})"
                                      readonly />
	                              <input type="hidden"  name="closingDate" id="closingDate">
	                              <span class="word">结清(日期)。</span>
                              </label>
                          </dd>
                   </dl>
               </td>
           </tr>
           <tr>
               <td class="td-title pct10">逾期情况及其他情况说明</td>
               <td class="pct20" colspan="3">
                   <dl class="form-item form-auto">
                          <dd class="detail">
                              <label>
                                  <textarea class="zui-area zui-validatebox" disabled="disabled" validate-type="Length[0-200]" placeholder="最多可以输入200个字符" name="remark" id="remark"></textarea>
                              </label>
                          </dd>
                   </dl>
               </td>
           </tr>
       </table>
       <input type="hidden" name="id" id="id">
       <input type="hidden" name="creditSituationId" value="${cs.id }">
    </form>
</div>
</div>

<!--弹出框2-->
<div id="dialog2" style="display: none">
<div id="pledgeEditDiv2" class="p5">
<form id="credit_card_form" class="zui-form" method="post" enctype="multipart/form-data">
   <table class="table-detail">
           <tr>
               <td class="td-title pct10"><b class="c-red mr5">*</b>信用额度</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                          <dd class="detail">
                              <label>
                                  <input class="zui-input zui-validatebox" disabled="disabled" type="text" validate-type="Require,IsOverNumber" validate-false="请输入正确的金额!" name="creditLimit" id="cardCreditLimit">
                              	  <span class="word">元</span>	
                              </label>
                          </dd>
                   </dl>
               </td>
               <td class="td-title pct10"><b class="c-red mr5">*</b>账户状态</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                          <input class="zui-combobox zui-validatebox" disabled="disabled" validate-type="Require" type="hidden" name="accountStatusCode" id="cardAccountStatusCode"
									 data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00165"
									data-valuefield="fullcode" data-textfield="name" data-callback="accountStatusCode1Change">
						<input type="hidden" id="accountStatusName1" name="accountStatusName"/>
                       </dd>
                   </dl>
               </td>
           </tr>
           <tr>
               <td class="td-title pct10"><b class="c-red mr5">*</b>当前逾期总额</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                          <dd class="detail">
                              <label>
                                  <input class="zui-input zui-validatebox" disabled="disabled" type="text" validate-type="Require,IsOverNumber" validate-false="请输入正确的金额!" name="currentTotalOverdue" id="cardCurrentTotalOverdue">
                              	  <span class="word">元</span>	
                              </label>
                          </dd>
                   </dl>
               </td>
               <td class="td-title pct10"><b class="c-red mr5">*</b>当前透支额</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                           <label>
                               <input class="zui-input zui-validatebox" disabled="disabled"type="text" name="currentOverdraft" id="cardCurrentOverdraft" validate-type="Require,IsOverNumber" validate-false="请输入正确的金额!">
                           	   <span class="word">元</span>	
                           </label>
                       </dd>
                   </dl>
               </td>
           </tr>
           <tr>
               <td class="td-title pct10"><b class="c-red mr5">*</b>最近12个月还款状态记录</td>
               <td class="pct20" colspan="3">
                   <dl class="form-item form-auto">
                          <dd class="detail">
                              <label>
	                              <span class="word">累计逾期</span>
	                                  <input class="zui-validatebox tsInput" 
									    type="text" validate-type="Number,Require"  disabled="disabled" validate-false="请输入正确的累计逾期次数!" name="cumulativeOverdue12" id="cardCumulativeOverdue12">
	                              <span class="word">次，最高逾期</span>
	                                  <input class="zui-validatebox tsInput" 
									    type="text" validate-type="Number,Require" disabled="disabled"  validate-false="请输入正确的累计逾期次数!" name="maximumOverdue12" id="cardMaximumOverdue12">
	                              <span class="word"> 次。</span>
                              </label>
                          </dd>
                   </dl>
               </td>
           </tr>
           <tr>
               <td class="td-title pct10">备注</td>
               <td class="pct20" colspan="3">
                   <dl class="form-item form-auto">
                          <dd class="detail">
                              <label>
                                  <textarea class="zui-area zui-validatebox" disabled="disabled" validate-type="Length[0-200]" placeholder="最多可以输入200个字符" name="remark" id="cardRemark"></textarea>
                              </label>
                          </dd>
                   </dl>
               </td>
           </tr>
       </table>
       <input type="hidden" name="id" id="cardId">
       <input type="hidden" name="creditSituationId" value="${cs.id }">
    </form>
</div>
</div>

<script>
seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/tools',
        'zd/jquery.zds.table','zd/jquery.zds.dialog',
        'zd/jquery.zds.form','zd/jquery.zds.combobox','viewer/superslide','viewer/iviewer'], function ($, CALLBACK,ZTOOL) {
    //初始化轮播图片
    $(".picScroll-left").slide({
        mainCell:".bd ul",
        autoPage:true,
        effect:"left",
        autoPlay:true,
        vis:3,
        scroll:3
    });

    //初始化当前图片
    ZTOOL.initView('#picList','#picShow');
    
  
    
    //信用卡最近12个月还款状态
    CALLBACK.formatReapyStatusRecord=function (rowData) {
    	 var html = '累计逾期'+rowData.cumulativeOverdue12+'次，最高逾期 '+rowData.maximumOverdue12+'次';
         return html;
    };
    //初始化表格
    CALLBACK.formatFuntion=function (index, rowData) {
    	 var html = '';
         html += '<a class="btn-blue mr5" onclick="myCreditEditData">查看</a>';
         return html;
    };
    
    //新增本人征信
    CALLBACK.myCreditAdd=function (r,v) {
    	$("#id").val("");
        $("#dialog1").Zdialog("open");
    };
    
    //编辑本人征信
    CALLBACK.myCreditEditData = function (index, rowData) {
        $("#id").val(rowData.id);//
       
        $("#loanTypesCode").ZCombobox("setValue", rowData.loanTypesCode);
        $("#loanTypesName").val(rowData.loanTypesName);
        $("#accountStatusCode").ZCombobox("setValue", rowData.accountStatusCode);
        $("#loanTypesCode").ZCombobox('disable');
        $("#accountStatusCode").ZCombobox('disable');
        $("#accountStatusName").val(rowData.accountStatusName);
        $("#loanDate1").val(ZTOOL.strToDate(rowData.loanDate+""));
        $("#loanDate").val(rowData.loanDate);
        $("#loanTerm").val(rowData.loanTerm);
        $("#loanAmount").val(rowData.loanAmount);
        $("#loanBalance").val(rowData.loanBalance);
        $("#currentOverdue").val(rowData.currentOverdue);
        $("#currentOverdueAmount").val(rowData.currentOverdueAmount);
        $("#cumulativeOverdue").val(rowData.cumulativeOverdue);
        $("#maximumOverdue").val(rowData.maximumOverdue);
        $("#cumulativeOverdue12").val(rowData.cumulativeOverdue12);
        $("#maximumOverdue12").val(rowData.maximumOverdue12);
        $("#monthsOverdue5").val(rowData.monthsOverdue5);
        $("#lastYear90Count").val(rowData.lastYear90Count);
        $("#closingDate1").val(ZTOOL.strToDate(rowData.closingDate+""));
        $("#closingDate").val(rowData.closingDate);
        $("#remark").val(rowData.remark);

        $("#dialog1").Zdialog("setTitle","编辑本人征信");
        $("#dialog1").Zdialog("open");
    };

    //初始化弹出框
    $("#dialog1").Zdialog({
        width: 730, height: 400, closed: true, title: "新增本人征信", hasLayer:false,left:517,top:50,
        buttons: [
            {
                id: 'message-btn',
                text: '取消',
                buttonCls: 'btn-gray',
                handler: function () {
                    $("#dialog1").Zdialog("close");
                }
            }
        ]
    });
    
    
    //初始化信用卡表格
    CALLBACK.formatFuntionCard=function (index, rowData) {
    	 var html = '';
         html += '<a class="btn-blue mr5" onclick="myCreditCardEditData">查看</a>';
         return html;
    };
    
    //新增本人信用卡
    CALLBACK.myCreditCardAdd=function (r,v) {
    	$("#id").val("");
        $("#dialog2").Zdialog("open");
    };
    
    //编辑本人信用卡
    CALLBACK.myCreditCardEditData = function (index, rowData) {
        $("#cardId").val(rowData.id);
        $("#cardCreditLimit").val(rowData.creditLimit);
        $("#cardAccountStatusCode").ZCombobox("setValue", rowData.accountStatusCode);
        $("#cardAccountStatusCode").ZCombobox('disable');
        $("#cardAccountStatusName").val(rowData.accountStatusName);
        $("#cardCurrentTotalOverdue").val(rowData.currentTotalOverdue);
        $("#cardCurrentOverdraft").val(rowData.currentOverdraft);
        $("#cardCumulativeOverdue12").val(rowData.cumulativeOverdue12);
        $("#cardMaximumOverdue12").val(rowData.maximumOverdue12);
        $("#cardRemark").val(rowData.remark);

        $("#dialog2").Zdialog("setTitle","编辑本人信用卡");
        $("#dialog2").Zdialog("open");
    };

    //初始化弹出框
    $("#dialog2").Zdialog({
        width: 730, height: 400, closed: true, title: "新增本人信用卡", hasLayer:false,left:517,top:50,
        buttons: [
            {
                id: 'message-btn',
                text: '取消',
                buttonCls: 'btn-gray',
                handler: function () {
                    $("#dialog2").Zdialog("close");
                }
            }
        ]
    });
    
    
	//返回页面
	$('#back').click(function(){
     	ZDS_MESSAGE_CLIENT.closeSelf();
	});
  	
    CALLBACK.loanTypesCodeChange = function (index,rowData){
        $("#loanTypesName").val(rowData);
    };
    CALLBACK.accountStatusCodeChange = function (index,rowData){
        $("#accountStatusName").val(rowData);
    };
    CALLBACK.accountStatusCode1Change = function (index,rowData){
        $("#accountStatusName1").val(rowData);
    };
    
    $.ZUI.init();
});
</script>
</body>
</html>