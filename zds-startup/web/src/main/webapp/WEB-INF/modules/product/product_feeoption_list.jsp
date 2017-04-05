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
                      zdata-options={"callBack":"saveCallBack"}>

                    <dl class="form-item">
                        <dt class="title">收费类型：</dt>
                        <dd class="detail">
                            <input class="zui-combobox zui-validatebox" type="hidden" id="feeType"
                                   name="feeType|E|S"
                                   data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00146"
                                   data-callback=""
                                   data-height="300"
                                   data-defaultvalue="${feeType}"
                                   data-valuefield="fullcode" data-textfield="text">
                        </dd>
                    </dl>
                    <dl class="form-item">
                        <dt class="title">收费项目：</dt>
                        <dd class="detail">
                            <input class="zui-combobox" type="hidden" id="feeItem"
                                   name="feeItem|E|S"
                                   data-url="<z:ukey key="com.zdsoft.finance.parameter.findAllEffectiveItemSimpleCode" context="admin"/>&jsoncallback=?"
                                   data-callback=""
                                   data-height="300"
                                   data-defaultvalue="${feeItem}"
                                   data-valuefield="code" data-textfield="text" >
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
                     "url":"<z:ukey key="com.zdsoft.finance.feeOptionListData" context="admin"/>&jsoncallback=?&productId|E|S=${productId}",
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
                                <th data-options="field:feeTypeName,width:70">收费类型</th>
                                <th data-options="field:feeItemName,width:70">收费项目</th>
                                <th data-options="field:paymentTermsName,width:80">支付条件</th>
                                <th data-options="field:payConditionName,width:70">佣金支付条件</th>
                                <th data-options="field:remark">备注</th>
                                <th data-options="field:isEnable,width:10" formatter="formatTrue">是否停用</th>
                                <th data-options="field:id,width:170" formatter="feeoptionFunction">操作</th>
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


                CALLBACK.feeoptionFunction = function (index, rowData) {
                    var str = "<a title='查看' class='btn-blue' onclick='feeOptionViewData'>查看</a>" +
                    "&nbsp;&nbsp;<a title='修改' class='btn-blue' onclick='feeOptionEditData'>修改</a>" +
                	"&nbsp;&nbsp;<a title='删除' class='btn-blue' onclick='feeOptionDeleteData'>删除</a>";
            		return str;
                };
                
                CALLBACK.formatTrue = function (index, rowData) {
                	return rowData ? "是":"否";	
                }
                
                //费用项新增
                CALLBACK.feeOptionAdd = function (index, rowData) {
                    var url = '<z:ukey key="com.zdsoft.finance.feeOptionEdit" context="admin" />&productId=${productId}';
                    ZDS_MESSAGE_CLIENT.openMenuLink('feeOptionAdd', '机构产品费用项新增', url);
                }
                //费用项查看
                CALLBACK.feeOptionViewData = function (index, rowData) {
                    var id = rowData.id;
                    var url = '<z:ukey key="com.zdsoft.finance.feeOptionView" context="admin" />&id=' + id;
                    ZDS_MESSAGE_CLIENT.openMenuLink('feeOptionViewData', '机构产品费用项查看', url);
                };
                //费用项编辑
                CALLBACK.feeOptionEditData = function (index, rowData) {
                    var id = rowData.id;
                    var url = '<z:ukey key="com.zdsoft.finance.feeOptionEdit" context="admin" />&productId=${productId}&feeOptionId=' + id;
                    ZDS_MESSAGE_CLIENT.openMenuLink('feeOptionEditData', '机构产品费用项编辑', url);
                };
                //费用项删除
                CALLBACK.feeOptionDeleteData = function (index, rowData) {
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
                    $("#zds-feeOption-table").ZTable("reload",{});
                };

                //查询按钮
                $("#feeOptionSearchButton").click(function () {
                    var param = $("#feeOptionSearchFrom").serializeArray();
                    $("#zds-feeOption-table").ZTable("reload", param);
                });
                //重置按钮
                $("#feeOptionResetButton").click(function () {
                    $("#feeType").ZCombobox("setValue", "");
                    $("#feeItem").ZCombobox("setValue", "");
                });

                //下拉框初始化
                $("#feeType").ZCombobox();
                $("#feeItem").ZCombobox();

                $.ZUI.initGrid("#feeOptionAllContentDiv");
                $.ZUI.initForms("#feeOptionSearchFrom");

            });

</script>

</body>
</html>