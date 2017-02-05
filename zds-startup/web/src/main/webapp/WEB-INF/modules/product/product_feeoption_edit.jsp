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
            <form id="feeOptionEditForm" class="zui-form form-search" zdata-options={"url":"www.zds.com"}>

                <input type="hidden" name="productCode" value="${productCode}"/>
                <input type="hidden" name="productName" value="${productName}"/>
                <input type="hidden" name="id" id="id" value="${id}"/>

                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>收费类型：</dt>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" id="chargeTypeCode" name="chargeTypeCode"
                               value="${chargeTypeCode}"
                               type="hidden"
                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=chargeClass"
                               data-callback="chargeTypeCodeChange"
                               data-height="300"
                               data-valuefield="id" data-textfield="text" validate-type="Require">
                        <input type="hidden" id="chargeTypeName" name="chargeTypeName" value="${chargeTypeName}"/>
                    </dd>
                </dl>

                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>收费项目：</dt>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" id="chargingItemCode" name="chargingItemCode"
                               type="hidden" value="${chargingItemCode}"
                        <%--data-url="<z:ukey key="com.zdsoft.finance.getMateriaSimpleCode" context="admin"/>&jsoncallback=?"--%>
                               data-url="<z:ukey key="com.zdsoft.finance.findAllEffectiveItemSimpleCode" context="admin"/>&jsoncallback=?"
                        <%--data-data="[{'id':'1',text:'收费项目1'},{'id':'2',text:'收费项目2'},{'id':'3',text:'收费项目3'}]"--%>
                               data-callback="chargingItemCodeChange"
                               data-height="300"
                               data-valuefield="id" data-textfield="text" validate-type="Require">
                        <input type="hidden" id="chargingItemName" name="chargingItemName" value="${chargingItemName}"/>
                    </dd>
                </dl>

                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>收款计算方式：</dt>
                    <dd class="detail">
                        <input class="zui-checkbox zui-validatebox" id="collectionMethodCode"
                               name="collectionMethodCode" value="${collectionMethodCode}"
                               type="hidden" data-multiple="false"
                               data-callback="collectionMethodCodeChange"
                               data-data="[{'id':'collectionMethodCode1','text':'固定'},{'id':'collectionMethodCode2','text':'比例'}]"
                               data-valuefield="id" data-textfield="text" validate-type="Require">
                        <input type="hidden" id="collectionMethodName" name="collectionMethodName"
                               value="${collectionMethodName}"/>
                    </dd>
                </dl>

                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>收款金额：</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input zui-validatebox" type="text" validate-type="Require,Digital[30-6]"
                                   data-toggle="validate"
                                   id="collectionAmount" value="${collectionAmount}"
                                   name="collectionAmount"/>
                        </label>
                        <span class="word" >元</span>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">收款比例(%)：</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input zui-validatebox" type="text" validate-type="Integer,Length[0-2]"
                                   id="collectionRatio" value="${collectionRatio}" data-toggle="validate"
                                   name="collectionRatio"/>
                        </label>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>付款计算方式：</dt>
                    <dd class="detail">
                        <input class="zui-checkbox zui-validatebox" id="paymentMethodCode" name="paymentMethodCode"
                               value="${paymentMethodCode}"
                               type="hidden" data-multiple="false"
                               data-callback="paymentMethodCodeChange"
                               data-data="[{'id':'paymentMethodCode1','text':'固定'},{'id':'paymentMethodCode2','text':'比例'}]"
                               data-valuefield="id" data-textfield="text" validate-type="Require">
                        <input type="hidden" id="paymentMethodName" name="paymentMethodName"
                               value="${paymentMethodName}"/>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">付款金额：</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input zui-validatebox" type="text" validate-type="Require,Digital[30-6]"
                                   data-toggle="validate" id="paymentAmount" value="${paymentAmount}"
                                   name="paymentAmount"/>
                        </label>
                        <span class="word" >元</span>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">付款比例(%)：</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input zui-validatebox" type="text" validate-type="Integer,Length[0-2]"
                                   id="paymentRatio"
                                   data-toggle="validate" value="${paymentRatio}"
                                   name="paymentRatio"/>
                        </label>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>营业收支：</dt>
                    <dd class="detail">
                        <input class="zui-checkbox zui-validatebox" id="isBusinessInAndOutCode"
                               name="isBusinessInAndOutCode" value="${isBusinessInAndOutCode}"
                               type="hidden" data-multiple="false"
                               data-callback="isBusinessInAndOutCodeChange"
                               data-data="[{'id':'isBusinessInAndOutCode1','text':'是'},{'id':'isBusinessInAndOutCode2','text':'否'}]"
                               data-valuefield="id" data-textfield="text" validate-type="Require">
                        <input type="hidden" id="isBusinessInAndOutName" name="isBusinessInAndOutName"
                               value="${isBusinessInAndOutName}"/>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>先请再付：</dt>
                    <dd class="detail">
                        <input class="zui-checkbox zui-validatebox" id="isRepayCode" name="isRepayCode"
                               value="${isRepayCode}"
                               type="hidden" data-multiple="false"
                               data-callback="isRepayCodeChange"
                               data-data="[{'id':'isRepayCode1','text':'是'},{'id':'isRepayCode2','text':'否'}]"
                               data-valuefield="id" data-textfield="text" validate-type="Require">
                        <input type="hidden" id="isRepayName" name="isRepayName" value="${isRepayName}"/>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>支付条件：</dt>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" id="payConditionCode" name="payConditionCode"
                               type="hidden" value="${payConditionCode}"
                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=payConditionClass"
                               data-callback="payConditionCodeChange"
                               data-height="300"
                               data-valuefield="id" data-textfield="text" validate-type="Require">
                        <input type="hidden" id="payConditionName" name="payConditionName" value="${payConditionName}"/>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">是否支佣：</dt>
                    <dd class="detail">
                        <input class="zui-checkbox zui-validatebox" id="isPayCommissionCode" name="isPayCommissionCode"
                               value="${isPayCommissionCode}"
                               type="hidden" data-multiple="false"
                               data-callback="isPayCommissionCodeChange"
                               data-data="[{'id':'isPayCommissionCode1','text':'是'},{'id':'isPayCommissionCode2','text':'否'}]"
                               data-valuefield="id" data-textfield="text" validate-type="">
                        <input type="hidden" id="isPayCommissionName" name="isPayCommissionName"
                               value="${isPayCommissionName}"/>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">佣金支付条件：</dt>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" id="payCommiCondCode" name="payCommiCondCode"
                               type="hidden" value="${payCommiCondCode}"
                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=payConditionClass"
                               data-callback="payCommiCondCodeChange"
                               data-height="300"
                               data-valuefield="id" data-textfield="text" validate-type="">
                        <input type="hidden" id="payCommiCondName" name="payCommiCondName" value="${payCommiCondName}"/>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">支佣节点：</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input zui-validatebox" type="text" validate-type="" id="payCommiNode"
                                   value="${payCommiNode}"
                                   name="payCommiNode"/>
                        </label>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">是否停用：</dt>
                    <dd class="detail">
                        <input class="zui-checkbox zui-validatebox" id="isStopCode" name="isStopCode"
                               value="${isStopCode}"
                               type="hidden" data-multiple="false"
                               data-callback="isStopCodeChange"
                               data-data="[{'id':'isStopCode1','text':'是'},{'id':'isStopCode2','text':'否'}]"
                               data-valuefield="id" data-textfield="text" validate-type="">
                        <input type="hidden" id="isStopName" name="isStopName" class="zui-validatebox"
                               value="${isStopName}"
                               validate-type=""/>
                    </dd>
                </dl>
                <dl class="form-item block">
                    <dt class="title">备注：</dt>
                    <dd class="detail">
                        <label>
                         <textarea class="zui-area zui-validatebox" data-toggle="validate" id="mo"
                                   name="mo"
                                   validate-type="Length[0-3000]"
                                   placeholder="最多可以输入3000个字符">${mo}</textarea>
                        </label>

                        <div class="zd-area">
                            <span class="zd-curval">0</span>/<span class="zd-maxval">3000</span></div>
                    </dd>
                </dl>
            </form>
        </div>
    </div>

</div>
<div class="save">
    <button id="feeoption-btn-save" class="btn-blue mr10">保存</button>
    <button id="feeoption-btn-cancel" class="btn-gray mr10">取消</button>
</div>
<script>
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.checkbox', 'zd/jquery.zds.loading', 'zd/switch',
                'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form',
                'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter', 'zd/bothselecter', 'zd/jquery.zds.button'],
            function ($, CALLBACK, Loading, Switch, DropDown, Filter, Check, Zdialog, ZUI_MESSAGE_CLIENT) {


                CALLBACK.formatFuntion = function (index, rowData) {
                    var html = '<a title="编辑" class="handler-icon icon-btn22 c-blue"  onclick="editData"></a>';
                    html += '<a title="删除" class="handler-icon icon-btn12 c-blue"  onclick="deleteData" ></a>';
                    return html;
                };
                //编辑按钮
                CALLBACK.editData = function (index, rowData) {
                    //赋值
                    $("#materiaTypeCode").ZCombobox("setValue", rowData.materiaTypeCode);
                    $("#materiaTypeName").val(rowData.materiaTypeName);
                    $("#materiaCode").ZCombobox("setValue", rowData.materiaCode);
                    $("#materiaName").val(rowData.materiaName);
                    $("#rememberCode").val(rowData.rememberCode);
                    $("#rememberNo").val(rowData.rememberNo);
                    $("#materiaIdentifyName").val(rowData.materiaIdentifyName);
                    $("#materiaIdentify").val(rowData.materiaIdentify);
                    //复选框赋值
                    var valueArray = (rowData.materiaIdentify).split(",");
                    var arraysize = valueArray.length;
                    for (var i = 0; i <= arraysize - 1; i++) {
                        $("#materiaIdentifyCkeck").ZCheckbox("setValue", valueArray[i]);
                    }
                    //赋值ID
                    $("#id").val(rowData.id);
                    $("#materiaIdentifyCkeck").ZCheckbox();
                    //alert($("#materiaIdentifyName").val()+"------"+$("#materiaIdentify").val());
                    $("#dialogAdd").Zdialog("open");
                };

                CALLBACK.chargeTypeCodeChange = function (index, rowData, row, thisobj) {
                    $("#chargeTypeName").val(rowData);
                };
                CALLBACK.chargingItemCodeChange = function (index, rowData, row, thisobj) {
                    $("#chargingItemName").val(rowData);
                };
                CALLBACK.collectionMethodCodeChange = function (index, rowData, row, thisobj) {
                    $("#collectionMethodName").val(rowData);
                };
                CALLBACK.paymentMethodCodeChange = function (index, rowData, row, thisobj) {
                    $("#paymentMethodName").val(rowData);
                };
                CALLBACK.isBusinessInAndOutCodeChange = function (index, rowData, row, thisobj) {
                    $("#isBusinessInAndOutName").val(rowData);
                };
                CALLBACK.isRepayCodeChange = function (index, rowData, row, thisobj) {
                    $("#isRepayName").val(rowData);
                };
                CALLBACK.payConditionCodeChange = function (index, rowData, row, thisobj) {
                    $("#payConditionName").val(rowData);
                };
                CALLBACK.isPayCommissionCodeChange = function (index, rowData, row, thisobj) {
                    $("#isPayCommissionName").val(rowData);
                };
                CALLBACK.payCommiCondCodeChange = function (index, rowData, row, thisobj) {
                    $("#payCommiCondName").val(rowData);
                };
                CALLBACK.isStopCodeChange = function (index, rowData, row, thisobj) {
                    $("#isStopName").val(rowData);
                };

                $("#feeoption-btn-save").click(function () {
//                    var flog = $('#feeOptionEditForm').ZDSValidatebox('validateAll', $('#feeOptionEditForm'));
                    var finalResult = $.ZUI.validateForm($('#feeOptionEditForm'));
                    if (!finalResult) {
                        return false;
                    }
                    var param = $("#feeOptionEditForm").serializeArray();
                    var url = '<z:ukey key="com.zdsoft.finance.saveOrUpdateFeeOption" context="admin" />';
                    $.ajax({
                        url: url,
                        data: param,
                        dataType: 'json',
                        type:"post",
                        success: function (data) {
                            if (data.resultStatus == 0) {
                                $.ZMessage.success("成功", "数据操作成功", function () {
                                    ZDS_MESSAGE_CLIENT.refreshOpenner();
                                    setTimeout(function () {
                                        ZDS_MESSAGE_CLIENT.closeSelf();
                                    }, 200);
                                });
                            } else {
                                $.ZMessage.error("错误", "数据操作失败" + data.msg, function () {
                                    return false;
                                });
                            }
                        },
                        error: function (data) {
                            $.ZMessage.error("错误", "数据操作失败" + data, function () {
                                return false;
                            });
                        }
                    });
                });
                $("#feeoption-btn-cancel").click(function () {
                    ZDS_MESSAGE_CLIENT.closeSelf();
                });


                //下拉菜单与单选菜单初始化
                $("#chargeTypeCode").ZCombobox();
                $("#chargingItemCode").ZCombobox();
                $("#payConditionCode").ZCombobox();
                $("#payCommiCondCode").ZCombobox();

                $("#collectionMethodCode").ZCheckbox();
                $("#paymentMethodCode").ZCheckbox();
                $("#isBusinessInAndOutCode").ZCheckbox();
                $("#isRepayCode").ZCheckbox();
                $("#isPayCommissionCode").ZCheckbox();
                $("#isStopCode").ZCheckbox();
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
                        $("#"+ele).val(eleValue.substring(0,eleValue.indexOf(".")+index+1));
                    }
                }
                //初始化比例数显示
                function formatScale(elements,index){
                    var elementArray=elements.split(",");
                    for(var i=0;i<elementArray.length;i++){
                        var ele=elementArray[i];
                        var eleValue=$("#"+ele).val();
                        $("#"+ele).val(eleValue.substring(0,eleValue.indexOf(".")+index));
                    }
                }

            });

</script>

</body>
</html>