<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z" %>
    <%@ include file='../../common/common_js.jsp' %>
</head>
<body>
<div id="capitalistDialogDiv">
    <div id="InfoDialog">
        <form id="contactForm" class="zui-form mt15">
            <input type="hidden" name="id" value="${vo.id }">
            <input type="hidden" name="formatContractApplyId" value="${formatContractApplyId}">
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>资方:</dt>
                <dd class="detail">
                    <input class="zui-combobox zui-validatebox" id="capitalistId" name="capitalistId" type="hidden"
                           value="${vo.capitalistId }"
                           data-url="<z:ukey key="com.zdsoft.finance.contract.findAllCapitalist" context="admin"/>"
                           data-valuefield="fullcode" data-textfield="name" validate-type="Require"
                           data-callback="capitalistSelect">
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>合同类型:</dt>
                <dd class="detail">
                    <input class="zui-combobox zui-validatebox" id="contractType" name="contractType" type="hidden"
                           value="${vo.contractType }"
                           data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM0066"
                           data-valuefield="fullcode" data-textfield="name" validate-type="Require"
                           data-callback="contractTypeSelect">
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>合同名称:</dt>
                <dd class="detail">
                    <input type="hidden" id="contractName" name="contractName">
                    <input class="zui-combobox zui-validatebox" id="contractTemplate" name="contractTemplateId"
                           type="hidden" value="${vo.contractTemplateId }"
                           data-url="<z:ukey key="com.zdsoft.finance.contract.getContractTemplate" context="admin"/>&capitalistId=${vo.capitalistId}&contractType=${vo.contractType}"
                           data-valuefield="fullcode" data-textfield="name" validate-type="Require"
                           data-callback="capitalistSelect">
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>份数:</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input zui-validatebox" id="contractCopies" name="contractCopies"
                               validate-type="Require,Number" value="${vo.contractCopies }">
                    </label>
                </dd>
            </dl>
        </form>
    </div>
</div>
</div>

<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading',
        'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form',
        'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter', 'zd/jquery.zds.combotree',
        'zd/jquery.zds.checkbox'], function ($, CALLBACK, COMBOBOX, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
        var type = "${operationType}";
        var xsTitle;
        if (type == 'add') {
            xsTitle = "合同新增";
        } else {
            xsTitle = "合同编辑";
        }

        $("#capitalistDialogDiv").Zdialog({
            width: 700, height: 300, closed: false, title: xsTitle, isDestroy: true,
            buttons: [
                {
                    id: 'message-btn',
                    text: '确定',
                    buttonCls: 'btn-blue',
                    handler: function () {
                        var isValidate = $.ZUI.validateForm($('#contactForm'));
                        if (isValidate) {
                            var contactForm = $('#contactForm').serialize();
                            $.ajax({
                                type: 'post',
                                url: '<z:ukey key="com.zdsoft.finance.contract.saveFormatContractDetail" context="admin"/>',
                                data: contactForm,
                                dataType: 'json',
                                success: function (data) {
                                    if (data.resultStatus == 0) {
                                        $.ZMessage.success("提示", "保存成功", function () {
                                            $(".zd-message").ZWindow("close");
                                        });
                                        $('#tb-plan').ZTable("reload",{formatContractApplyId:$("#formatContractApplyId").val()});
                                        $("#capitalistDialogDiv").Zdialog("close");
                                    }
                                },
                                error: function () {
                                    $.ZMessage.error("错误", "系统异常，请联系管理员", function () {
                                        $(".zd-message").ZWindow("close");
                                    });
                                    $("#capitalistDialogDiv").Zdialog("close");
                                }
                            });
                        }
                    }
                },
                {
                    id: 'message-btn',
                    text: '关闭',
                    buttonCls: 'btn-gray',
                    handler: function () {
                        $("#capitalistDialogDiv").Zdialog("close");
                    }
                }
            ]
        });

        CALLBACK.capitalistSelect = function (value, text, index) {
            var contractType = $("#contractType").ZCheckbox('getValue');
            if (contractType == '') {
                return;
            }
            $('#contractTemplate').ZCombobox({
                url: '<z:ukey key="com.zdsoft.finance.contract.getContractTemplate" context="admin"/>&capitalistId=' + value + '&contractType=' + contractType,
                valueField: 'fullcode',
                textField: 'name'
            });
        };
        CALLBACK.contractTypeSelect = function (value, text, index) {
            var capitalistId = $("#capitalistId").ZCheckbox('getValue');
            if (capitalistId == '') {
                return;
            }
            $('#contractTemplate').ZCombobox({
                url: '<z:ukey key="com.zdsoft.finance.contract.getContractTemplate" context="admin"/>&capitalistId=' + capitalistId + '&contractType=' + value,
                valueField: 'fullcode',
                textField: 'name'
            });
        };
        CALLBACK.capitalistSelect = function (value, text, index) {
            $('#contractName').val(text);
        };
        //初始化
        $.ZUI.init("#InfoDialog");

    });


</script>
</body>
</html>