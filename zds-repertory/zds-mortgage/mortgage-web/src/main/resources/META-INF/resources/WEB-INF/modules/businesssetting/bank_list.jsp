<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <%@ include file='../common/common_js.jsp' %>
</head>
<body>
<!-- BEGIN CONTAINER -->
<div class="frm-content">
    <div class="page-box">
        <div class="page-title">银行代码配置</div>
        <div class="p5">
            <form action="#" id="searchForm" class="zui-form form-search" method="post"
                  enctype="multipart/form-data">
                <dl class="form-item">
                    <dt class="title">银行名称</dt>
                    <dd class="detail">
                        <label>
                            <input type="text" class="zui-input" name="bankName|LK|S"
                                   id="name"/>
                        </label>
                    </dd>
                </dl>
                <div class="form-btn">
                    <button type="button" class="btn-blue" id="searchButton">查询</button>
                    <button type="button" class="btn-blue" id="resetButton">重置</button>
                </div>
            </form>
            <!-- table演示 -->
            <div class="zui-datagrid" id="zd-table"
                 zdata-options="{'idField':'id','url':'<z:ukey key="com.zdsoft.finance.parameter.getBank"  context="admin"/>&jsoncallback=?','singleSelect':true,'rownumbers':false,'pagination':true,'tableCls': 'table-index','toolbar':'#btn-applylist'}">
                <table>
                    <thead>
                    <tr>
                        <th data-options="field:bankName,width:40%">银行名称</th>
                        <th data-options="field:bankCode,width:40%">银行代码</th>
                        <th data-options="field:status,width:20%" formatter="operate">操作</th>

                    </tr>
                    </thead>
                </table>
            </div>
            <div id="btn-applylist">
                <a class="zui-toolbar" id="btn-add" text="新增" iconCls="icon-add" buttonCls="btn-blue" handler="add"></a>
            </div>
        </div>
    </div>
</div>


<div id="dialogEdit" style="display: none">
    <div id="testForm" class="mt20">
        <form id="dialogForm" class="zui-form">
            <input type="hidden" id="id" name="id"/>
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>银行名称</dt>
                <dd class="detail">
                    <label>
                        <input type="text" class="zui-input zui-validatebox" id="form_name" name="bankName"
                               validate-type="Require,Length[1-25]" validate-false="银行名称不能为空"/>
                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>银行代码</dt>
                <dd class="detail">
                    <label>
                        <input type="text" class="zui-input zui-validatebox" id="form_code" name="bankCode"
                               validate-type="Require,Length[1-25]" validate-false="银行代码不能为空"/>
                    </label>
                </dd>
            </dl>
        </form>
    </div>
</div>
<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter', 'zd/jquery.zds.combotree', 'zd/jquery.zds.checkbox']
            , function ($, CALLBACK, COMBOBOX, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
                Date.prototype.Format = function (fmt) { //author: meizz
                    var o = {
                        "M+": this.getMonth() + 1, //月份
                        "d+": this.getDate(), //日
                        "h+": this.getHours(), //小时
                        "m+": this.getMinutes(), //分
                        "s+": this.getSeconds(), //秒
                        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                        "S": this.getMilliseconds() //毫秒
                    };
                    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
                    for (var k in o)
                        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                    return fmt;
                }
                $("#dialogEdit").Zdialog({
                    title: "新增",
                    width: 650,
                    height: 170,
                    closed: true,
                    isDrag: false,
                    buttons: [
                        {
                            id: 'message-btn', text: '确定', buttonCls: 'btn-blue',
                            handler: function () {
                                var isValidate = $.ZUI.validateForm($('#dialogForm'));
                                if (isValidate) {
                                    if ($("#form_name").val() == "null") {
                                        $.ZMessage.info("提示", "无效的银行名称", function () {

                                        });
                                        return;
                                    }
                                    if ($("#form_code").val() == "null") {
                                        $.ZMessage.info("提示", "无效的银行代码", function () {

                                        });
                                        return;
                                    }
                                    var param = $("#dialogForm").serializeArray();
                                    $.ajax({
                                        method: "post",
                                        dataType: "json",
                                        data: param,
                                        url: '<z:ukey key="com.zdsoft.finance.parameter.updateBank"  context="admin"/>&jsoncallback=?',
                                        success: function (result) {
                                            if (result.resultStatus == 0) {
                                                $.ZMessage.success("提示", result.msg, function () {
                                                    $("#dialogEdit").Zdialog("close");
                                                });
                                            }
                                            else {
                                                $.ZMessage.error("错误", result.msg, function () {
                                                });
                                            }
                                            var formArray = $("#searchForm").serializeArray();
                                            $('#zd-table').ZTable("reload", formArray);
                                        }

                                    });
                                }
                            }
                        },
                        {
                            id: 'message-btn', text: '取消', buttonCls: 'btn-gray',
                            handler: function () {
                                $("#dialogEdit").Zdialog("close");
                            }
                        }],
                });
                //日期转换函数
                CALLBACK.changeDate = function (index, v) {
                    var date = new Date(v);
                    return date.Format('yyyy-MM-dd hh:mm');
                };
                //状态转换函数
                CALLBACK.changeStatus = function (index, v) {
                    switch (v) {
                        case 1:
                            return "启用";
                            break;
                        case  0:
                            return "停用";
                            break;
                    }

                };
                //操作栏的回调函数
                CALLBACK.operate = function () {
                    var html = '<a href="javaScript:void(0)" onclick="doEdit"><button class="btn-blue">编辑</button></a>' + '&nbsp;&nbsp;' +
                            '<a href="javaScript:void(0)" onclick="doDelete"><button class="btn-blue">删除</button></a>'
                    return html;
                }
                //新增行
                CALLBACK.add = function () {
                    $("#id").val("");
                    $("#dialogEdit").Zdialog("setTitle","新增");
                    $("#dialogEdit").Zdialog("open");
                }
                //编辑行
                CALLBACK.doEdit = function (num, row) {
                    $("#id").val(row.id);
                    $("#form_name").val(row.bankName);
                    $("#form_code").val(row.bankCode);

                    $("#dialogEdit").Zdialog("setTitle", "编辑");
                    $("#dialogEdit").Zdialog("open");
                }
                //删除行
                CALLBACK.doDelete = function (num, selRow) {
                    $.ZMessage.question("确认", "确认删除该银行代码吗？", function (r) {
                        if (selRow) {
                            var param = {};
                            param.id = selRow.id;
                            $.ajax({
                                method: "post",
                                dataType: "json",
                                data: param,
                                url: '<z:ukey key="com.zdsoft.finance.parameter.deleteBank"  context="admin"/>&jsoncallback=?',
                                success: function (result) {
                                    if (result.resultStatus == 0) {
                                        $.ZMessage.info("提示", result.msg, function () {
                                        });
                                    }
                                    else {
                                        $.ZMessage.error("错误", result.msg, function () {
                                        });
                                    }
                                    var formArray = $("#searchForm").serializeArray();
                                    $('#zd-table').ZTable("reload", formArray);
                                }

                            });
                        }

                    });

                }
                $.ZUI.init();
                //查询
                $("#searchButton").click(function () {
                    var formArray = $("#searchForm").serializeArray();
                    $('#zd-table').ZTable("reload", formArray);
                });
                //重置
                $("#resetButton").click(function () {
                    $("#name").val("");
                    $("#code").ZCombobox('code', "");
                });

            });
</script>
</body>
</html>