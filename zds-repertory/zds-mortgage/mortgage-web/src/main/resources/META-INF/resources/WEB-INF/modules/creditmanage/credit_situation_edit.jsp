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
<div class="save">
    <a class="btn-blue mr5" id="saveAfterCheck">录入完成</a>
    <a class="btn-blue mr5" id="back">返回</a>
</div>
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
                 "toolbar":"#btn-applylist1",
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
                    <!-- 与table中的一起使用才有效"toolbar":"#btn-applylist" ，写handler回调函数实现增删改功能-->
                    <div id="btn-applylist1">
                        <a class="zui-toolbar" id="btn-add1" text="增加" iconCls="icon-btn08" buttonCls="btn-orange"
                           handler="myCreditAdd"></a>
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
                     "toolbar":"#btn-applylist2",
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
                <!-- 与table中的一起使用才有效"toolbar":"#btn-applylist" ，写handler回调函数实现增删改功能-->
                <div id="btn-applylist2">
                    <a class="zui-toolbar" id="btn-add" text="增加" iconCls="icon-btn08" buttonCls="btn-orange"
                       handler="myCreditCardAdd"></a>
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
                                               onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'reportPrintingDate',maxDate:'%y-%M-%d'})"
                                               validate-type="Require" readonly/>
                                         <input type="hidden" id="reportPrintingDate" name="reportPrintingDate" value="${cs.reportPrintingDate}">
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
                                               validate-type="Require,Number"/>
                                    </label>
                                </dd>
                            </dl>
                        </td>
                    </tr>
                    <tr>
                        <th>审批意见</th>
                        <td colspan="3">
                            <textarea class="zui-area pct100" placeholder="最多可以输入200个字符" name="creditEvaluation">${cs.creditEvaluation }</textarea>
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
									data-valuefield="fullcode" data-textfield="name" data-callback="loanTypesCodeChange">
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
									data-valuefield="fullcode" data-textfield="name" data-callback="accountStatusCodeChange">
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
                                  <input class="zui-date strToDate zui-validatebox" validate-type="Require" type="text" id="loanDate1" onclick="WdatePicker({maxDate:'%y-%M-%d',realDateFmt:'yyyyMMdd',vel:'loanDate',dateFmt:'yyyy-MM-dd',onpicked:function(){WdateValidate(this);}})"
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
                               <input class="zui-input zui-validatebox" type="text" name="loanTerm" id="loanTerm" validate-type="Require,Number">
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
                                  <input class="zui-input zui-validatebox" type="text" validate-type="Require,IsOverNumber" validate-false="请输入正确的金额!" name="loanAmount" id="loanAmount">
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
                               <input class="zui-input zui-validatebox" type="text" name="loanBalance" id="loanBalance" validate-type="Require,IsOverNumber,CompareAmount[<=-loanAmount]" validate-false="请输入正确的金额!|请输入正确的金额!|贷款余额不能大于贷款金额！">
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
                                  <input class="zui-input zui-validatebox" value="0" type="text" validate-type="Require,Number" name="currentOverdue" id="currentOverdue">
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
                               <input class="zui-input zui-validatebox" type="text"  value="0" name="currentOverdueAmount" id="currentOverdueAmount" validate-type="Require,IsOverNumber" validate-false="请输入正确的金额!">
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
                                  <input class="zui-input zui-validatebox"  value="0" type="text" validate-type="Require,Number" name="cumulativeOverdue" id="cumulativeOverdue">
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
                               <input class="zui-input zui-validatebox"  value="0" type="text" name="maximumOverdue" id="maximumOverdue" validate-type="Require,Number" >
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
									    type="text" validate-type="Require,Number" validate-false="请输入正确的累计逾期次数!" name="cumulativeOverdue12" id="cumulativeOverdue12">
	                              <span class="word">次，最高逾期</span>
	                                  <input class="zui-validatebox tsInput" 
									    type="text" validate-type="Require,Number" validate-false="请输入正确的累计逾期次数!" name="maximumOverdue12" id="maximumOverdue12">
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
									    type="text" validate-type="Require,Number" validate-false="请输入正确的累计逾期次数!" name="monthsOverdue5" id="monthsOverdue5">
	                              <span class="word">个月处于逾期状态，</span>
	                                  <input class="zui-validatebox tsInput" 
									    type="text" validate-type="Require,Number" validate-false="请输入正确的累计逾期次数!" name="lastYear90Count" id="lastYear90Count">
	                              <span class="word">次90天以上逾期，已于</span>
	                              <input class="zui-date strToDate zui-validatebox"  type="text" id="closingDate1" onclick="WdatePicker({maxDate:'%y-%M-%d',realDateFmt:'yyyyMMdd',vel:'closingDate',dateFmt:'yyyy-MM-dd'})"
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
                                  <textarea class="zui-area zui-validatebox" validate-type="Length[0-200]" placeholder="最多可以输入200个字符" name="remark" id="remark"></textarea>
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
                                  <input class="zui-input zui-validatebox" type="text" validate-type="Require,IsOverNumber" validate-false="请输入正确的金额!" name="creditLimit" id="cardCreditLimit">
                              	  <span class="word">元</span>	
                              </label>
                          </dd>
                   </dl>
               </td>
               <td class="td-title pct10"><b class="c-red mr5">*</b>账户状态</td>
               <td class="pct20">
                   <dl class="form-item form-auto">
                       <dd class="detail">
                          <input class="zui-combobox zui-validatebox" validate-type="Require" type="hidden" name="accountStatusCode" id="cardAccountStatusCode"
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
                                  <input class="zui-input zui-validatebox"  value="0" type="text" validate-type="Require,IsOverNumber" validate-false="请输入正确的金额!" name="currentTotalOverdue" id="cardCurrentTotalOverdue">
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
                               <input class="zui-input zui-validatebox" type="text" name="currentOverdraft" id="cardCurrentOverdraft" validate-type="Require,IsOverNumber" validate-false="请输入正确的金额!">
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
									    type="text" validate-type="Number,Require" validate-false="请输入正确的累计逾期次数!" name="cumulativeOverdue12" id="cardCumulativeOverdue12">
	                              <span class="word">次，最高逾期</span>
	                                  <input class="zui-validatebox tsInput" 
									    type="text" validate-type="Number,Require" validate-false="请输入正确的累计逾期次数!" name="maximumOverdue12" id="cardMaximumOverdue12">
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
                                  <textarea class="zui-area zui-validatebox" validate-type="Length[0-200]" placeholder="最多可以输入200个字符" name="remark" id="cardRemark"></textarea>
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
        'zd/jquery.zds.form','viewer/superslide','viewer/iviewer'], function ($, CALLBACK,ZTOOL) {
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
         html += '<a class="btn-blue mr5" onclick="myCreditEditData">编辑</a>';
         html += '<a class="btn-blue mr5" onclick="myCreditDel">删除</a>';
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
                text: '确定',
                buttonCls: 'btn-blue',
                handler: function () {
                	var isValid = $.ZUI.validateForm($('#credit_form'));
     				if(isValid){
    					var param = $('#credit_form').serializeArray();
    					//保存
    					$.ajax({
                            type: 'post',
                            url: '<z:ukey key="com.zdsoft.finance.credit.myCredit.save" context="admin"/>',
                            data: param,
                            dataType: 'json',
                            success: function (data) {
                                if (data.resultStatus == 0) {
                                	$.ZMessage.success("提示", "保存成功", function () {
                                		$("#dialog1").Zdialog("close");
                                		$("#zds-table-myCredit").ZTable("reload");
                                 	 });
                                }else{
                                	$.ZMessage.error("错误", data.msg, function () {
    		                        });
                                }
                            },
                            error: function () {
                            	$.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
    	                        });
                            }
                        });
    				}
                }
            },
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
         html += '<a class="btn-blue mr5" onclick="myCreditCardEditData">编辑</a>';
         html += '<a class="btn-blue mr5" onclick="myCreditCardDel">删除</a>';
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
                text: '确定',
                buttonCls: 'btn-blue',
                handler: function () {
                	var isValid = $.ZUI.validateForm($('#credit_card_form'));
     				if(isValid){
    					var param = $('#credit_card_form').serializeArray();
    					//保存
    					$.ajax({
                            type: 'post',
                            url: '<z:ukey key="com.zdsoft.finance.credit.myCreditCard.save" context="admin"/>',
                            data: param,
                            dataType: 'json',
                            success: function (data) {
                                if (data.resultStatus == 0) {
                                	$.ZMessage.success("提示", "保存成功", function () {
                                		$("#dialog2").Zdialog("close");
                                		$("#zds-table-myCreditCard").ZTable("reload");
                                 	 });
                                }else{
                                	$.ZMessage.error("错误", data.msg, function () {
    		                        });
                                }
                            },
                            error: function () {
                            	$.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
    	                        });
                            }
                        });
    				}
                }
            },
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
  	//保存录入信息
	$('#saveAfterCheck').click(function(){
		//检查信息
		var isValidateAfterCheck = $.ZUI.validateForm($('#credit_situation_form'));
		var myCredit = $('#zds-table-myCredit').ZTable("getRows");
		var myCreditCard = $('#zds-table-myCreditCard').ZTable("getRows");
		//校验
		if(!isValidateAfterCheck){
			$.ZMessage.info("提示", "请完善必填项信息！", function () {
            });	 
			return false;
		}
		
		//获取检查信息
		var params = $('#credit_situation_form').serialize();
		//贷款信息
		if(myCredit!=""){
			params +="&credit="+JSON.stringify(myCredit) ;
		}
		//信用卡信息
		if(myCreditCard!=""){
			params +="&creditCard="+JSON.stringify(myCreditCard) ;
		}
		console.log(params);
		//保存录入
		$.ajax({
            type: 'post',
            url: '<z:ukey key="com.zdsoft.finance.credit.creditSituation.save" context="admin"/>',
            data: params,
            dataType: 'json',
            success: function (data) {
                if (data.resultStatus == 0) {
                	$.ZMessage.success("提示", "保存成功", function () {
                		setTimeout(function(){
                         	ZDS_MESSAGE_CLIENT.refreshOpenner();
                         	ZDS_MESSAGE_CLIENT.closeSelf();
                         },200);
                	});
                }else{
                	$.ZMessage.error("错误", data.msg, function () {
                    });
                }
            },
            error: function () {
            	$.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
                });
            }
        });
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
    
    
	CALLBACK.myCreditDel = function(index,data){
		$.ZMessage.question("提示", "是否删除", function (index) {
			$.ajax({
		        type: 'post',
		        url: '<z:ukey key="com.zdsoft.finance.credit.myCredit.del" context="admin"/>',
		        data: data,
		        dataType: 'json',
		        success: function (data) {
		            if (data.resultStatus == 0) {
		            	$.ZMessage.success("提示", "删除成功", function () {
		            		$("#zds-table-myCredit").ZTable("reload");
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
		});
	};
	
	CALLBACK.myCreditCardDel = function(index,data){
		$.ZMessage.question("提示", "是否删除", function (index) {
			$.ajax({
	            type: 'post',
	            url: '<z:ukey key="com.zdsoft.finance.credit.myCreditCard.del" context="admin"/>',
	            data: data,
	            dataType: 'json',
	            success: function (data) {
	                if (data.resultStatus == 0) {
	                	$.ZMessage.success("提示", "删除成功", function () {
	                		$("#zds-table-myCreditCard").ZTable("reload");
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
		});
	};
    
    
    $.ZUI.init();
});
</script>
</body>
</html>