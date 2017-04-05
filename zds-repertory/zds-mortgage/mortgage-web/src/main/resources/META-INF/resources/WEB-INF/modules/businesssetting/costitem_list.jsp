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
        <div class="page-title">费用项管理</div>
        <div class="p5">
            <form action="#" id="searchForm" class="zui-form form-search" method="post"
                  enctype="multipart/form-data">
                <dl class="form-item">
                    <dt class="title">编码</dt>
                    <dd class="detail">
                        <label>
                            <input type="text" class="zui-input" name="costItemCode"
                                   id="code"/>
                        </label>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">费用名称</dt>
                    <dd class="detail">
                        <label>
                            <input type="text" class="zui-input" name="costItemName"
                                   id="name"/>
                        </label>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">操作人</dt>
                    <dd class="detail">
                        <label>
                            <input type="text" class="zui-input" name="empName"
                                   id="empName"/>
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
                 zdata-options="{'idField':'id','url':'<z:ukey key="com.zdsoft.finance.parameter.getCostItem"  context="admin"/>&jsoncallback=?','singleSelect':true,'isRowNum':false,'pagination':true,'tableCls': 'table-index','toolbar':'#btn-applylist'}">
                <table>
                    <thead>
                    <tr>
                        <th data-options="field:costItemCode,width:10%">编码</th>
                        <th data-options="field:costItemName,width:20%">费用名称</th>
                        <th data-options="field:empName,width:15%">操作人</th>
                        <th data-options="field:updateTime,width:15%" formatter="changeDate">操作时间</th>
                        <th data-options="field:remark,width:20%">备注</th>
                        <th data-options="field:status,width:10%" formatter="operate">操作</th>

                    </tr>
                    </thead>
                </table>
            </div>
            <div id="btn-applylist">
                <a class="zui-toolbar" id="btn-add" text="新增" iconCls="icon-add" buttonCls="btn-blue" handler="add"></a>
            </div>
        </div>
    </div>

   <div class="page-box" id="testFile">
    </div>

</div>


<div id="dialogEdit" style="display: none">
    <div id="testForm" class="mt20">
        <form id="dialogForm" class="zui-form">
            <dl class="form-item">
                <dt class="title">费用编码</dt>
                <dd class="detail">
                    <label>
                        <input id="costItemId" style="display:none;">
                        <input type="text" class="zui-input zui-validatebox" id="form_code" disabled="disabled"/>
                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>费用名称</dt>
                <dd class="detail">
                    <label>
                        <input type="text" class="zui-input zui-validatebox" id="form_name"
                               validate-type="Require,Length[1-25]" validate-false="费用名称不能为空"/>
                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title">操作人</dt>
                <dd class="detail">
                    <label>
                        <input type="text" class="zui-input zui-validatebox" id="form_empName" disabled="disabled"/>
                    </label>
                </dd>
            </dl>
            <dl class="form-item" id="operatorTime">
                <dt class="title">操作时间</dt>
                <dd class="detail">
                    <label>
                        <input type="text" class="zui-input zui-validatebox" id="form_updateTime" disabled="disabled"/>
                    </label>
                </dd>
            </dl>
            <div>
                <dl class="form-item">
                    <dt class="title">备注</dt>
                    <dd class="detail">
                        <label>
                            <textarea id="form_remark" class="zui-area zui-validatebox" validate-type="Length[0-200]"
                                      validate-false="备注不能超过200个字"></textarea>
                        </label>
                        <div class="zd-area">
                            <span class="zd-curval">0</span>/<span class="zd-maxval">200</span></div>
                    </dd>
                </dl>
            </div>
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
                    height: 300,
                    closed: true,
                    isDrag: false,
                    buttons: [
                        {
                            id: 'message-btn', text: '确定', buttonCls: 'btn-blue',
                            handler: function () {
                                var isValidate = $.ZUI.validateForm($('#dialogForm'));
                                if (isValidate) {
                                    if ($("#form_name").val() == "null") {
                                        $.ZMessage.info("提示", "无效的费用项名称", function () {

                                        });
                                        return;
                                    }
                                    var param = {};
                                    param.costItemName = $("#form_name").val();
                                    param.remark = $("#form_remark").val();
                                    param.costItemCode = $("#form_code").val();
                                    if ($("#form_code").val()) {
                                        param.costItemCode = $("#form_code").val();
                                    }
                                    if ($("#costItemId").val()) {
                                        param.id = $("#costItemId").val();
                                    }
                                    $.ajax({
                                        method: "post",
                                        dataType: "json",
                                        data: param,
                                        url: '<z:ukey key="com.zdsoft.finance.parameter.updateCostItem"  context="admin"/>&jsoncallback=?',
                                        success: function (result) {
                                            if (result.resultStatus == 0) {
                                                $.ZMessage.success("提示", result.msg, function () {
                                                    $("#dialogEdit").Zdialog("close");
                                                    $('#zd-table').ZTable("reload");

                                                });
                                            }
                                            else {
                                                $.ZMessage.error("错误", result.msg, function () {
                                                });
                                            }
                                        }

                                    });
                                }
                            }
                        },
                        {
                            id: 'message-btn', text: '取消', buttonCls: 'btn-gray',
                            handler: function () {
                                //todo something
                                $("#dialogEdit").Zdialog("close");
                            }
                        }],
                });
                //日期转换函数
                CALLBACK.changeDate = function (index, v) {
                    if (v != null && v != "") {
                        var date = new Date(v.time);
                        return date.Format('yyyy-MM-dd hh:mm');
                    } else {
                        return "";
                    }
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
                    var html = '<a href="javaScript:void(0)" onclick="doEdit"><button class="btn-blue">编辑</button></a>'
                    return html;

                }
                //新增行
                CALLBACK.add = function () {
                    $.ajax({
                        method: "post",
                        dataType: "json",
                        url: '<z:ukey key="com.zdsoft.finance.parameter.getCostitemBasicMessage"  context="admin"/>&jsoncallback=?',
                        success: function (result) {
                            if (result.resultStatus == 0) {
                                var obj = result.optional;
                                $("#operatorTime").css("display", "none");
                                $("#form_code").val(obj.code);
                                $("#form_empName").val(obj.empName);
                                $("#dialogEdit").Zdialog("setTitle", "新增");
                                $("#dialogEdit").Zdialog("open");
                            }
                            else {
                                $.ZMessage.error("错误", "自动生成费用项编号出错", function () {
                                });
                            }
                        }

                    });

                };
                //编辑行
                CALLBACK.doEdit = function (num, row) {
                    $("#form_name").val(row.costItemName);
                    $("#form_code").val(row.costItemCode);
                    $("#form_empName").val(row.empName);
                    if(row.updateTime!=null){
                        var date = new Date(row.updateTime.time);
                        var updateTime = date.Format('yyyy-MM-dd hh:mm');
                    }
                    $("#form_updateTime").val(updateTime);
                    $("#form_remark").val(row.remark);
                    $("#costItemId").val(row.id);
                    $("#dialogEdit").Zdialog("setTitle", "编辑");
                    $("#operatorTime").css("display", "");
                    $("#dialogEdit").Zdialog("open");
                }
                //删除行
                CALLBACK.doDelete = function (num, selRow) {
                    $.ZMessage.question("确认", "确认删除该费用项吗？", function (r) {

                        if (selRow) {
                            var param = {};
                            param.id = selRow.id;
                            $.ajax({
                                method: "post",
                                dataType: "json",
                                data: param,
                                url: '<z:ukey key="com.zdsoft.finance.parameter.deleteCostItem"  context="admin"/>&jsoncallback=?',
                                success: function (result) {
                                    if (result.resultStatus == 0) {
                                        $.ZMessage.success("提示", result.msg, function () {
                                        });
                                    }
                                    else {
                                        $.ZMessage.error("错误", result.msg, function () {
                                        });
                                    }
                                    $('#zd-table').ZTable("reload");
                                }

                            });
                        }

                    });

                }
                $.ZUI.init();
                //查询
                $("#searchButton").click(function () {
                    /*  var param={};
                     param=$("#name").val();
                     param.status=$("#status").ZCombobox('getValue');
                     param.type=$("#type").ZCombobox('getValue');
                     param.valueMethod=$("#form_valueMethod").ZCombobox('getValue'); */
                    var formArray = $("#searchForm").serializeArray();
                    $('#zd-table').ZTable("reload", formArray);
                });
                //重置
                $("#resetButton").click(function () {
                    $("#name").val("");
                    $("#code").val("");
                    $("#empName").val("");
                });

                $("#testFile").load('<z:ukey key="com.zdsoft.finance.fileStore.fileShowListPage" context="admin" />&productId=4028a1255a735bf4015a73df77c10004&linkCode=06&caseApplyId=4028a1095abb2d7e015abb3cad040014&businessId=&useType=edit');

            });
</script>
</body>
</html>