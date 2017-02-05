<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>机构产品费用项</title>
</head>
<body>
<div id="feeOptionAllContentDiv">
    <div class="frm-content frm-bottom">
        <div class="page-box">
            <div class="p10">

                <form class="zui-form form-search" action="javascript:void(0);" id="feeOptionSearchFrom"
                      zdata-options={"url":"http://192.168.33.137:8880/FindAllClints?jsoncallback=?","callBack":"saveCallBack"}>

                    <%--<dl class="form-item">
                        <dt class="title"><b class="c-red mr5">*</b>收费类型：</dt>
                        <dd class="detail">
                            <input class="zui-combobox zui-validatebox" type="hidden" id="chargeTypeCode"
                                   name="chargeTypeCode|E|S"
                                   data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=chargeClass"
                                   data-callback=""
                                   data-height="300"
                                   data-defaultvalue="${materiaTypeCode}"
                                   data-valuefield="id" data-textfield="text" validate-type="Require">
                        </dd>
                    </dl>--%>
                    <dl class="form-item">
                        <dt class="title">收费类型:</dt>
                        <dd class="detail">
                            <label>
                                <input class="zui-input" type="text" id="chargeTypeName" name="chargeTypeName|LK|S" />
                            </label>
                        </dd>
                    </dl>

                    <dl class="form-item">
                        <dt class="title">收费项目：</dt>
                        <dd class="detail">
                            <input class="zui-combobox" type="hidden" id="chargingItemCode"
                                   name="chargingItemCode|E|S"
                                   data-url="<z:ukey key="com.zdsoft.finance.findAllEffectiveItemSimpleCode" context="admin"/>&jsoncallback=?"
                                   <%--data-data="[{'id':'1',text:'收费项目1'},{'id':'2',text:'收费项目2'},{'id':'3',text:'收费项目3'}]"--%>
                                   data-callback=""
                                   data-height="300"
                                   data-defaultvalue="${chargingItemCode}"
                                   data-valuefield="id" data-textfield="text" >
                        </dd>
                    </dl>

                    <div class="form-btn">
                        <button class="btn-blue" id="feeOptionSearchButton">查询</button>
                        <button class="btn-gray" id="feeOptionResetButton">重置</button>
                    </div>
                </form>


                <div id="feeOpthintable">
                    <div id="zds-feeOption-table"
                         class="zui-datagrid table-scroll"
                         zdata-options='{
                     "url":"<z:ukey key="com.zdsoft.finance.feeOptionListData" context="admin"/>&jsoncallback=?&productCode|E|S=${productCode}",
                     "singleSelect":true,
                     "pagination":true,
                     "idField":"id",
                     "tableCls":"table-index",
                     "dbclickEditRow":false,
                     "toolbar":"#btn-applylistFeeOption"
                     }'>
                        <table>
                            <thead>
                            <tr>
                                <th data-options="field:chargeTypeName">收费类型</th>
                                <th data-options="field:chargingItemName">收费项目</th>
                                <th data-options="field:payConditionName">支付条件</th>
                                <th data-options="field:payCommiCondName">佣金支付条件</th>
                                <th data-options="field:mo,width:10%">备注</th>
                                <th data-options="field:isStopName">是否停用</th>
                                <th data-options="field:id" formatter="formatFuntion">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <!-- 与table中的一起使用才有效"toolbar":"#btn-applylist" ，写handler回调函数实现增删改功能-->
                    <div id="btn-applylistFeeOption" style="width: 100%">
                        <a class="zui-toolbar" id="btn-add" text="新增" iconCls="icon-btn08" buttonCls="btn-blue"
                           handler="feeOptionAdd"></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.checkbox', 'zd/jquery.zds.loading', 'zd/switch',
                'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form',
                'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter', 'zd/bothselecter', 'zd/jquery.zds.button'],
            function ($, CALLBACK, Loading, Switch, DropDown, Filter, Check, Zdialog, ZUI_MESSAGE_CLIENT) {


                CALLBACK.formatFuntion = function (index, rowData) {
                    var html = '<a title="查看" class="handler-icon icon-btn00 c-blue"  onclick="feeOptionViewData"></a>';
                    html += '<a title="编辑" class="handler-icon icon-btn22 c-blue"  onclick="feeOptionEditData"></a>';
                    html += '<a title="删除" class="handler-icon icon-btn12 c-blue"  onclick="feeOptionDeleteData" ></a>';
                    return html;
                };
                //费用项新增
                CALLBACK.feeOptionAdd = function (index, rowData, row, thisobj) {
                    var url = '<z:ukey key="com.zdsoft.finance.feeOptionEditPage" context="admin" />&productCode=${productCode}&productName=${productName}';
                    ZDS_MESSAGE_CLIENT.openMenuLink('feeOptionAdd', '机构产品费用项新增', url);
                }
                //费用项查看
                CALLBACK.feeOptionViewData = function (index, rowData, row, thisobj) {
                    var id = rowData.id;
                    var url = '<z:ukey key="com.zdsoft.finance.feeOptionViewPage" context="admin" />&id=' + id;
                    ZDS_MESSAGE_CLIENT.openMenuLink('feeOptionViewData', '机构产品费用项查看', url);
                };
                //费用项编辑
                CALLBACK.feeOptionEditData = function (index, rowData, row, thisobj) {
                    var id = rowData.id;
                    var url = '<z:ukey key="com.zdsoft.finance.feeOptionEditPage" context="admin" />&productCode=${productCode}&productName=${productName}&feeOptionId=' + id+"&type=edit";
                    ZDS_MESSAGE_CLIENT.openMenuLink('feeOptionEditData', '机构产品费用项编辑', url);
                };
                //费用项删除
                CALLBACK.feeOptionDeleteData = function (index, rowData, row, thisobj) {
                    var id = rowData.id;
                    var deleteUrl = "<z:ukey key='com.zdsoft.finance.deleteFeeOption' context='admin' />";
                    $.ZMessage.question("确认", "确认删除？", function () {
                        ;
                        $.post(deleteUrl, {id: id}, function (result) {
                            if (result.resultStatus == 0) {
                                $.ZMessage.info("成功", "数据删除成功", function () {
                                    var param = $("#feeOptionSearchFrom").serializeArray();
                                    $("#zds-feeOption-table").ZTable("reload", param);
                                })
                            } else {
                                $.ZMessage.error("错误", "删除数据错误" + result.msg, function () {
                                    return false;
                                });
                            }
                        });
                    })
                };

                ZDS_MESSAGE_CLIENT.refreshThis = function () {
                    var param = $("#feeOptionSearchFrom").serializeArray();
                    $("#zds-feeOption-table").ZTable("reload", param);
                };

                //查询按钮
                $("#feeOptionSearchButton").click(function () {
                    var param = $("#feeOptionSearchFrom").serializeArray();
                    $("#zds-feeOption-table").ZTable("reload", param);
                });
                //重置按钮
                $("#feeOptionResetButton").click(function () {
                    $("#chargeTypeName").val("");
                    $("#chargingItemCode").ZCombobox("setValue", "");
                });

                //下拉框初始化
                $("#chargeTypeCode").ZCombobox();
                $("#chargingItemCode").ZCombobox();

                $.ZUI.initGrid("#feeOptionAllContentDiv");
                $.ZUI.initForms("#feeOptionSearchFrom");

            });

</script>

</body>
</html>