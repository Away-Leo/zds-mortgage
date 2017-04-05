<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common_js.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>费用项编辑</title>
</head>
<body>
<div class="frm-content frm-bottom" id="feeOptionEditContentDiv">
    <div id="feeOptionEdit" class="page-box">
        <h1 class="page-title">
            费用项编辑
        </h1>

        <div id="feeOptionEditDiv" class="p10">
            <form id="feeOptionEditForm" class="zui-form form-search" zdata-options={}>
                <input type="hidden" name="productId" value="${productId}"/>
                <input type="hidden" name="id" id="id" value="${feeOption.id}"/>
                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>收费类型：</dt>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" id="feeType" name="feeType"
                               value="${feeOption.feeType}"
                               type="hidden"
                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00146"
                               data-height="300"
                               data-valuefield="fullcode" data-textfield="text" validate-type="Require">
                    </dd>
                </dl>
				 <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>收费项目：</dt>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" id="feeItem" name="feeItem"
                               type="hidden" value="${feeOption.feeItem}"
                               data-url="<z:ukey key="com.zdsoft.finance.parameter.findAllEffectiveItemSimpleCode" context="admin"/>&jsoncallback=?"
                               data-height="300"
                               data-valuefield="code" data-textfield="text" validate-type="Require">
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>收款计算方式：</dt>
                    <dd class="detail">
                        <input class="zui-checkbox zui-validatebox" id="chargeCalculateWay"
                               name="chargeCalculateWay" value="${feeOption.chargeCalculateWay==null?'chargeCalculateWay1':feeOption.chargeCalculateWay}"
                               type="hidden" data-multiple="false"
                               data-data="[{'id':'chargeCalculateWay1','text':'固定'},{'id':'chargeCalculateWay2','text':'比例'}]"
                               data-valuefield="id" data-textfield="text" validate-type="Require" data-callback="checkValue">
                    </dd>
                </dl>
                <dl class="form-item" id="chargeAmountDl">
                    <dt class="title"><b class="c-red mr5">*</b>收款金额(元)：</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input zui-validatebox" type="text" validate-type="Require,Digital[18-6]" data-toggle="validate"
                                   id="chargeAmount" value="${feeOption.chargeAmount}" name="chargeAmount"/>
                        </label>
                    </dd>
                </dl>
                <dl class="form-item" id="chargeRatioDl" style="display: none;">
                    <dt class="title"><b class="c-red mr5">*</b>收款比例(%)：</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input zui-validatebox" type="text" validate-type="Digital[2-2]"
                                   id="chargeRatio" value="${feeOption.chargeRatio}" data-toggle="validate" name="chargeRatio"/>
                        </label>
                    </dd>
                </dl>
                <dl class="form-item" id="payAmountDl">
                    <dt class="title">付款金额(元)：</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input zui-validatebox" type="text" validate-type="Digital[18-6]"
                                   data-toggle="validate" id="payAmount" value="${feeOption.payAmount}" name="payAmount"/>
                        </label>
                    </dd>
                </dl>
                <dl class="form-item" id="payRatioDl" style="display: none;">
                    <dt class="title">付款比例(%)：</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input zui-validatebox" type="text" validate-type="Digital[2-2]"
                                   id="payRatio" data-toggle="validate" value="${feeOption.payRatio}" name="payRatio"/>
                        </label>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">付款计算方式：</dt>
                    <dd class="detail">
                        <input class="zui-checkbox zui-validatebox" id="payCalculateWay" name="payCalculateWay"
                               value="${feeOption.payCalculateWay==null?'payCalculateWay1':feeOption.payCalculateWay}"
                               type="hidden" data-multiple="false"
                               data-data="[{'id':'payCalculateWay1','text':'固定'},{'id':'payCalculateWay2','text':'比例'}]"
                               data-valuefield="id" data-textfield="text" data-callback="checkValue2">
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>营业收支：</dt>
                    <dd class="detail">
                        <input class="zui-checkbox zui-validatebox" id="isOperateIncome"
                               name="isOperateIncome" value="${feeOption.isOperateIncome == null?'false':feeOption.isOperateIncome}"
                               type="hidden" data-multiple="false"
                               data-data="[{'id':'true','text':'是'},{'id':'false','text':'否'}]"
                               data-valuefield="id" data-textfield="text" validate-type="Require">
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>先请再付：</dt>
                    <dd class="detail">
                        <input class="zui-checkbox zui-validatebox" id="isPayFirst" name="isPayFirst"
                               value="${feeOption.isPayFirst == null?'false':feeOption.isPayFirst}" type="hidden" data-multiple="false"
                               data-data="[{'id':'true','text':'是'},{'id':'false','text':'否'}]"
                               data-valuefield="id" data-textfield="text" validate-type="Require">
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>支付条件：</dt>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" id="paymentTerms" name="paymentTerms"
                               type="hidden" value="${feeOption.paymentTerms}"
                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00145"
                               data-height="300"
                               data-valuefield="fullcode" data-textfield="text" validate-type="Require">
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">是否支佣：</dt>
                    <dd class="detail">
                        <input class="zui-checkbox zui-validatebox" id="isPayCommission" name="isPayCommission"
                               value="${feeOption.isPayCommission == null?'false':feeOption.isPayCommission}"
                               type="hidden" data-multiple="false"
                               data-data="[{'id':'true','text':'是'},{'id':'false','text':'否'}]"
                               data-valuefield="id" data-textfield="text" validate-type="">
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">佣金支付条件：</dt>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" id="payCondition" name="payCondition"
                               type="hidden" value="${feeOption.payCondition}"
                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00145"
                               data-height="300" data-valuefield="fullcode" data-textfield="text" validate-type="">
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">支佣节点：</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input zui-validatebox" type="text" validate-type="" id="point"
                                   value="${feeOption.point}",validate-type="Length[0-30]" name="point"/>
                        </label>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">是否停用：</dt>
                    <dd class="detail">
                    	<input class="zui-checkbox zui-validatebox" id="isEnable" name="isEnable"
                               value="${feeOption.isEnable == null?'false':feeOption.isEnable}"
                               type="hidden" data-multiple="false"
                               data-data="[{'id':'true','text':'是'},{'id':'false','text':'否'}]"
                               data-valuefield="id" data-textfield="text" validate-type="">
                    </dd>
                </dl>
                <dl class="form-item block">
                    <dt class="title">备注：</dt>
                    <dd class="detail">
                        <label>
                         <textarea class="zui-area zui-validatebox" data-toggle="validate" id="remark"
                                   name="remark"
                                   validate-type="Length[0-500]"
                                   placeholder="最多可以输入500个字符">${feeOption.remark}</textarea>
                        </label>
                        <div class="zd-area">
                            <span class="zd-curval">0</span>/<span class="zd-maxval">500</span></div>
                    </dd>
                </dl>
               
            </form>
        </div>
    </div>

</div>
<div class="save">
    <button id="feeoption-btn-cancel" class="btn-gray mr10">取消</button>
    <button id="feeoption-btn-save" class="btn-blue mr10">保存</button>
</div>
<script>
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.checkbox', 'zd/jquery.zds.loading', 'zd/switch',
                'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form',
                'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter', 'zd/bothselecter', 'zd/jquery.zds.button'],
            function ($, CALLBACK, Loading, Switch, DropDown, Filter, Check, Zdialog, ZUI_MESSAGE_CLIENT) {

        $("#feeoption-btn-save").click(function () {
            var finalResult = $.ZUI.validateForm($('#feeOptionEditForm'));
            if (!finalResult) {
                return false;
            }
            var param = $("#feeOptionEditForm").serializeArray();
            var url = '<z:ukey key="com.zdsoft.finance.saveOrUpdateFeeOption" context="admin" />';
            $.ajax({
                url: url,
                data: param,
                dataType:"json",
                type: "post",
                success: function (data) {
                	if (data.resultStatus == 0) {
                        $.ZMessage.success("成功", "操作成功", function () {
                            ZDS_MESSAGE_CLIENT.refreshOpenner();
                            setTimeout(function () {
                                ZDS_MESSAGE_CLIENT.closeSelf();
                            }, 200);
                        });
                    } else {
                        $.ZMessage.error("错误", data.msg, function () {
                            return false;
                        });
                    }
                },
                error: function (data) {
                    $.ZMessage.error("错误", "操作失败" + data, function () {
                        return false;
                    });
                }
            });
            
        });
        $("#feeoption-btn-cancel").click(function () {
            ZDS_MESSAGE_CLIENT.closeSelf();
        });
                
        CALLBACK.checkValue = function(index,rowData){
			if('chargeCalculateWay1' == index){
				$('#chargeAmount').attr('validate-type','Require,Digital[18-6]');
				$('#chargeRatio').attr('validate-type','Digital[2-2]');
				$("#chargeRatio").val("");
				$("#chargeAmountDl").show();
				$("#chargeRatioDl").hide();
				$("#chargeAmount").val("${feeOption.chargeAmount}");
			}else if('chargeCalculateWay2' == index){
				$('#chargeAmount').attr('validate-type','Digital[18-6]');
				$("#chargeAmount").val("");
				$('#chargeRatio').attr('validate-type','Require,Digital[2-2]');
				$("#chargeAmountDl").hide();
				$("#chargeRatioDl").show();
				$("#chargeRatio").val("${feeOption.chargeRatio}");
			}
        };
                
        CALLBACK.checkValue2 = function(index,rowData){
			if('payCalculateWay1' == index){
				$('#payAmount').attr('validate-type','Require,Digital[18-6]');
				$('#payRatio').attr('validate-type','Digital[2-2]');
				$("#payAmountDl").show();
				$("#payRatioDl").hide();
				$("#payAmount").val("${feeOption.payAmount}");
			}else if('payCalculateWay2' == index){
				$('#payAmount').attr('validate-type','Digital[18-6]');
				$('#payRatio').attr('validate-type','Require,Digital[2-2]');
				$("#payAmountDl").hide();
				$("#payRatioDl").show();
				$("#payRatio").val("${feeOption.payRatio}");
			}
         };
                
         $(function(){
           	var chargeCalculateWay = $('#chargeCalculateWay').ZCombobox('getValue');
           	var payCalculateWay = $('#payCalculateWay').ZCombobox('getValue');
           	
           	if('chargeCalculateWay1' == chargeCalculateWay){
				$('#chargeAmount').attr('validate-type','Require,Digital[18-6]');
				$('#chargeRatio').attr('validate-type','Digital[2-2]');
				$("#chargeAmountDl").show();
				$("#chargeRatioDl").hide();
			}else if('chargeCalculateWay2' == chargeCalculateWay){
				$('#chargeAmount').attr('validate-type','Digital[18-6]');
				$('#chargeRatio').attr('validate-type','Require,Digital[2-2]');
				$("#chargeAmountDl").hide();
				$("#chargeRatioDl").show();
			}
           	
           	
           	if('payCalculateWay1' == payCalculateWay){
				$('#payAmount').attr('validate-type','Require,Digital[18-6]');
				$('#payRatio').attr('validate-type','Digital[2-2]');
				$("#payAmountDl").show();
				$("#payRatioDl").hide();
			}else if('payCalculateWay2' == payCalculateWay){
				$('#payAmount').attr('validate-type','Digital[18-6]');
				$('#payRatio').attr('validate-type','Require,Digital[2-2]');
				$("#payAmountDl").hide();
				$("#payRatioDl").show();
			}
       });


                //下拉菜单与单选菜单初始化
                $("#feeType").ZCombobox();
                $("#feeItem").ZCombobox();
                $("#chargeCalculateWay").ZCheckbox();
                $("#payCalculateWay").ZCheckbox();
                $("#isOperateIncome").ZCheckbox();
                $("#isPayFirst").ZCheckbox();
                $("#paymentTerms").ZCombobox();
                $("#isPayCommission").ZCheckbox();
                $("#payCondition").ZCombobox();
                $("#isEnable").ZCheckbox();
                
                $.ZUI.initGrid("#feeOptionEditContentDiv");
                $.ZUI.initForms("#feeOptionEditForm");

                formatAmount("collectionAmount,paymentAmount",6);
                formatScale("collectionRatio,paymentRatio",0);

                //初始化金额小数位数显示
                function formatAmount(elements,index){
                    var elementArray=elements.split(",");
                    for(var i=0;i<elementArray.length;i++){
                        var ele=elementArray[i];
                        var eleValue=$("#"+ele).val();
                        if(eleValue != null && eleValue != ''){
	                        $("#"+ele).val(eleValue.substring(0,eleValue.indexOf(".")+index+1));
                        }
                    }
                }
                //初始化比例数显示
                function formatScale(elements,index){
                    var elementArray=elements.split(",");
                    for(var i=0;i<elementArray.length;i++){
                        var ele=elementArray[i];
                        var eleValue=$("#"+ele).val();
                        if(eleValue != null && eleValue != ''){
	                        $("#"+ele).val(eleValue.substring(0,eleValue.indexOf(".")+index));
                        }
                    }
                }

            });

</script>

</body>
</html>