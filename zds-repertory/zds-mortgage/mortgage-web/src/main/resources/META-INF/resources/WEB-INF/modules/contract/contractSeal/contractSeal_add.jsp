<!DOCTYPE html>
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8" %>
    <%@ include file='../../common/common_js.jsp' %>
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>合同盖章申请</title>
    <script type="application/javascript">
        var viewType = false;
    </script>
</head>
<body>

<!-- 基本信息 -->
<%@include file="common/case_apply_view.jsp" %>
<!-- 押品信息列表 -->
<%@ include file="common/house_list.jsp" %>
<!--盖章明细-->
<%@ include file="common/contractSealDetail_list.jsp" %>
<div class="page-box" id="upFile">
</div>
<div class="page-box">
    <div class="p10">
        <h1 class="page-title">申请信息</h1>
        <div class="p5">
            <form id="contractSealForm" class="zui-form " method="post">
                <table class="table-detail">
                    <tr>
                        <td class="td-title pct10">申请类型</td>
                        <td class="pct20">
                            <dl class="form-item">
                                <dd class="detail">
                                    <input class="zui-combobox zui-validatebox" id="applyType" type="hidden"
                                           name="applyType" validate-type="Require"
                                           data-data="[{'id':'0','text':'盖章申请'},{'id':'1','text':'二次盖章'}]"
                                           data-valuefield="id" data-textfield="text"/>
                                </dd>
                            </dl>
                        </td>
                        <td class="td-title pct10">申请人</td>
                        <td class="pct20">
                            <label>
                                <input type="hidden" id="fileUuid" name="fileUuid" value="${uuid}"/>
                                <input type="zui-input zui-validatebox" id="launchEmpName" name="launchEmpName"
                                       disabled="disabled"
                                       value="${emp.empNm}"/>
                                <input type="hidden" id="launchEmpCode" name="launchEmpCode" value="${emp.empCd}"/>
                            </label>
                        </td>
                        <td class="td-title pct10">申请时间</td>
                        <td class="pct20">
                            <input type="zui-input zui-validatebox" id="showApplyData" disabled="disabled"
                                   value="${applyDate}"/>
                            <input type="hidden" name="applyDate" value="${applyDate}"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="td-title pct10">备注</td>
                        <td colspan="5">
                            <label>
                                    <textarea class="zui-area row-width" id="remark" name="remark"
                                              validate-type="Require,Length[0-200]"
                                              placeholder="最多可以输入200个字符">${vo.remark }</textarea>
                            </label>
                        </td>
                    </tr>
                </table>
                <input type="hidden" name="caseContractId" id="caseContractId" value="${contract.id}">
            </form>
        </div>
        <div class="form-btn">
            <button id="btn-save" class="btn-blue mr10" type="button">保存</button>
            <button id="btn-submit" class="btn-blue mr10" type="button">提交</button>
        </div>
    </div>
</div>
<div id="contactsDialog" style="display: none">
    <script type="text/javascript">
        seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/tools','zd/jquery.zds.loading', 'zd/jquery.zds.table', 'zd/jquery.zds.combobox',
            'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message',
            'zd/jquery.zds.form', 'datepicker'
        ], function ($, CALLBACK, ZTOOlS,Loading) {
            $("#upFile").load('<z:ukey key="com.zdsoft.finance.fileStore.fileShowListPage" context="admin" />&productId=${caseApply.productSubtypeId}&linkCode=${subStage}&caseApplyId=${caseApply.id}&businessId=${uuid}');
            //修改日期显示格式
            $("#showApplyData").val(ZTOOlS.strToTime("${applyDate}"));
            $("#btn-save").click(function () {
                saveOrSubmit(false);
            });
            $("#btn-submit").click(function () {
                saveOrSubmit(true);
            });
            //保存 isSubmit是否提交
            function saveOrSubmit(submitStatus) {
                var sealDetailRows = $("#contactSealDetailList").ZTable("getRows");
                if (!sealDetailRows || sealDetailRows.length == 0) {
                    $.ZMessage.error("错误", "资料盖章明细不能为空！", function () {
                    });
                    return;
                }
                var sealFormValidate = $.ZUI.validateForm($('#contractSealForm'));
                if (sealFormValidate) {
                    var formArray = $("#contractSealForm").serializeArray();
                    formArray.push({name: "sealDetailStrs", value: JSON.stringify(sealDetailRows)});
                    formArray.push({name: "submitStatus", value: submitStatus});
                    Loading.show();
                    $.post('<z:ukey key="com.zdsoft.finance.contract.conCaseContractSeal.saveOrUpdateContractSeal" context="admin"/>',
                        formArray, function (data) {
                        data = eval("("+data+")");
                            if (data.resultStatus == 0) {
                                $.ZMessage.success("提示", data.msg, function () {
                                    ZDS_MESSAGE_CLIENT.closeSelf();
                                });
                            }else{
                                $.ZMessage.error("错误", data.msg, function () {
                                });
                            }
                            Loading.hide();
                        });
                }
            }

            $.ZUI.init();
            $.ZUI.initForms('#contractSealForm');
        });
    </script>
</body>
</html>