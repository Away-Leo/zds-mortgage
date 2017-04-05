<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common_js.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>标签设置</title>
</head>
<body>
<div id="labelSetAllContentDiv">
    <div class="frm-content frm-bottom">
        <div class="page-box">
            <h1 class="page-title">
                抵押权人管理
            </h1>

            <div class="p10">
                <form class="zui-form form-search" action="javascript:void(0);" id="labelSetSearchFrom">

                    <dl class="form-item">
                        <dt class="title">名称：</dt>
                        <dd class="detail">
                            <label>
                                <input class="zui-input" type="text" name="ownerName" id="ownerName"/>
                            </label>
                        </dd>
                    </dl>
                    <dl class="form-item">
                        <dt class="title">所属机构：</dt>
                        <dd class="detail">
                            <input class="zui-combobox zui-validatebox" type="hidden" id="beOrgCode"
                                   name="beOrgCode"
                                   data-url="<z:ukey key='com.zdsoft.finance.mortOwner.orgSimpleCode' context="admin"/>&jsoncallback=?"
                                   data-callback=""
                                   data-height="300"
                                   data-defaultvalue=""
                                   data-valuefield="id" data-textfield="text">
                        </dd>
                    </dl>
                    <%--<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=labelSetType--%>
                    <div class="form-btn">
                        <button class="btn-blue" id="labelSetSearchButton">查询</button>
                        <button class="btn-blue" id="labelSetResetButton">重置</button>
                    </div>
                </form>


                <div id="labelSettable">
                    <div id="zds-labelSet-table"
                         class="zui-datagrid table-scroll"
                         zdata-options='{
                     "url":"<z:ukey key="com.zdsoft.finance.labelSetListData" context="admin"/>&jsoncallback=?",
                     "singleSelect":true,
                     "pagination":true,
                     "idField":"id",
                     "tableCls":"table-index",
                     "dbclickEditRow":false,
                     "toolbar":"#btn-applylist"
                     }'>
                        <table>
                            <thead>
                            <tr>
                                <th data-options="field:beOrgName,width:10%">所属机构</th>
                                <th data-options="field:ownerTypeCodeName">类别</th>
                                <th data-options="field:ownerName">名称</th>
                                <th data-options="field:status" formatter="statusFormatter">状态</th>
                                <th data-options="field:createTimeStr">添加日期</th>
                                <th data-options="field:id" formatter="formatFuntion">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <!-- 与table中的一起使用才有效"toolbar":"#btn-applylist" ，写handler回调函数实现增删改功能-->
                    <div id="btn-applylist" style="width: 100%">
                        <a class="zui-toolbar" id="btn-add" text="新增" iconCls="icon-btn08" buttonCls="btn-blue"
                           handler="labelSetAdd"></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="dialogLabelSetAdd" style="display: none">
        <div id="dialogLabelSetAddFormDiv">
            <form id="labelSetAddForm" class="zui-form" zdata-options={"url":"www.zds.com"}>
                <input type="hidden" name="id" id="id"/>
                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>所属机构：</dt>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" type="hidden" id="beOrgCodeAdd"
                               name="beOrgCode"
                               data-url="<z:ukey key='com.zdsoft.finance.mortOwner.orgSimpleCode' context="admin"/>&jsoncallback=?"
                               data-callback=""
                               data-height="300"
                               data-toggle="validate"
                               data-defaultvalue=""
                               validate-type="Require"
                               data-valuefield="id" data-textfield="text">
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>类别：</dt>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" type="hidden" id="ownerTypeCodeAdd"
                               name="ownerTypeCode"
                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=labelSetType"
                               data-callback=""
                               data-height="300"
                               data-defaultvalue=""
                               data-toggle="validate"
                               validate-type="Require"
                               data-valuefield="id" data-textfield="text">
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>名称：</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input zui-validatebox" type="text" validate-type="Require"
                                   data-toggle="validate"
                                   id="ownerNameAdd" style="width: 503px"
                                   name="ownerName"/>
                        </label>
                    </dd>
                </dl>
                <br>
                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>状态：</dt>
                    <dd class="detail">
                        <input class="zui-checkbox zui-validatebox" id="statusAdd" name="status" type="hidden"
                               data-multiple="false"
                               data-data="[{'id':'0','text':'启用'},{'id':'1','text':'停用'}]"
                               data-toggle="validate"
                               data-valuefield="id" data-textfield="text" validate-type="Require">
                    </dd>
                </dl>
            </form>
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
                    var html = '';
                    html += '<a title="编辑" class="handler-icon icon-btn22 c-blue"  onclick="labelSetEditData"></a>';
                    html += '<a title="删除" class="handler-icon icon-btn12 c-blue"  onclick="labelSetDeleteData" ></a>';
                    return html;
                };
                //状态格式化
                CALLBACK.statusFormatter = function (index, rowdata) {
                    if (rowdata == "0") {
                        return "已启用";
                    } else {
                        return "已停用";
                    }
                }
                //新增
                CALLBACK.labelSetAdd = function (index, rowData, row, thisobj) {
                    $("#id").val("");
                    $("#dialogLabelSetAdd").Zdialog("setTitle", "新增");
                    $("#dialogLabelSetAdd").Zdialog("open");
                }
                //编辑
                CALLBACK.labelSetEditData = function (index, rowData, row, thisobj) {
                    $("#dialogLabelSetAdd").Zdialog("setTitle", "编辑");
                    $("#id").val(rowData.id);//
                    debugger;
                    $("#beOrgCodeAdd").ZCombobox("setValue", rowData.beOrgCode);
                    $("#ownerTypeCodeAdd").ZCombobox("setValue", rowData.ownerTypeCode);
                    $("#statusAdd").ZCheckbox("setValue",rowData.status);
                    $("#ownerNameAdd").val(rowData.ownerName);

                    $("#dialogLabelSetAdd").Zdialog("setTitle","编辑");
                    $("#dialogLabelSetAdd").Zdialog("open");
                };
                //删除
                CALLBACK.labelSetDeleteData = function (index, rowData, row, thisobj) {
                    var id = rowData.id;
                    var deleteUrl = "<z:ukey key='com.zdsoft.finance.deleteLabelSet' context='admin' />";
                    $.ZMessage.question("确认", "确认删除？", function () {
                        $.post(deleteUrl, {id: id}, function (result) {
                            if (result.resultStatus == 0) {
                                $.ZMessage.info("成功", "数据删除成功", function () {
                                    var param = $("#labelSetSearchFrom").serializeArray();
                                    $("#zds-labelSet-table").ZTable("reload", param);
                                })
                            } else {
                                $.ZMessage.error("错误", "删除数据错误" + result.msg, function () {
                                    return false;
                                });
                            }
                        });
                    })
                };
                //查询
                $("#labelSetSearchButton").click(function () {
                    var param = $("#labelSetSearchFrom").serializeArray();
                    $("#zds-labelSet-table").ZTable("reload", param);
                });
                //重置
                $("#labelSetResetButton").click(function () {
                    $("#ownerName").val("");
                    $("#beOrgCode").ZCombobox("setValue","");
                });

                //新增对话框
                $("#dialogLabelSetAdd").Zdialog({
                    width: 700, height: 400, closed: true, title: "新增",
                    buttons: [
                        {
                            id: 'message-btnLabelSetAdd',
                            text: '确定',
                            buttonCls: 'btn-blue',
                            handler: function () {
                                $.ZUI.initForms('#labelSetAddForm');
                                var validate = $.ZUI.validateForm($('#labelSetAddForm'));
                                if (!validate) {
                                    $.ZMessage.error("错误", "数据验证失败!", function () {
                                    });
                                    return false;
                                }
                                var params = $('#labelSetAddForm').serializeArray();
                                var url = '<z:ukey key="com.zdsoft.finance.saveOrUpdataLabelSet" context="admin" />&jsoncallback=? ';
                                $.ajax({
                                    url: url,
                                    data: params,
                                    type: "post",
                                    success: function (data) {
                                        if (data.resultStatus == 0) {
                                            $.ZMessage.success("提示", "数据操作成功!", function () {
                                                $("#dialogLabelSetAdd").Zdialog("close");
                                                var param = $("#labelSetSearchFrom").serializeArray();
                                                $("#zds-labelSet-table").ZTable("reload", param);
                                            });
                                        } else {
                                            $.ZMessage.error("错误", "数据操作出错!" + data.msg, function () {
                                            });
                                            return false;
                                        }
                                    },
                                    error: function (data) {
                                        $.ZMessage.error("错误", "错误!" + data.msg, function () {
                                        });
                                    }
                                });
                            }
                        },
                        {
                            id: 'message-btn',
                            text: '取消',
                            buttonCls: 'btn-blue',
                            handler: function () {
                                $("#dialogLabelSetAdd").Zdialog("close");
                            }
                        }
                    ]
                });

                //下拉框初始化
                $("#beOrgCode").ZCombobox();
                $("#beOrgCodeAdd").ZCombobox();
                $("#ownerTypeCodeAdd").ZCombobox();
                $("#statusAdd").ZCheckbox();

                $.ZUI.initGrid("#labelSetAllContentDiv");
                $.ZUI.initForms("#labelSetSearchFrom");

            });

</script>

</body>
</html>