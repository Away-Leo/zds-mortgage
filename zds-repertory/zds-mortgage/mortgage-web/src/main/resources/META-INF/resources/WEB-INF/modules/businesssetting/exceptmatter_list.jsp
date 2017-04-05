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
        <div class="page-title">特批事项管理</div>
        <div class="p5">
            <form action="#" id="searchForm" class="zui-form form-search" method="post"
                  enctype="multipart/form-data">
                <%--<dl class="form-item">
                    <dt class="title">编码</dt>
                    <dd class="detail">
                        <label>
                            <input type="text" class="zui-input" name="exceptMattercode|LK|S"
                                   id="exceptMattercode"/>
                        </label>
                    </dd>
                </dl>--%>
                <dl class="form-item">
                    <dt class="title">类型</dt>
                    <dd class="detail">
                        <label>
                            <input id="exceptMatterType" name="exceptMatterType"
                                   class="zui-combobox zui-validatebox" type="hidden"
                                   data-toggle="combobox"
                                   data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0077"
                                   data-valuefield="id" data-textfield="text"
                                    >
                        </label>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">特批事项</dt>
                    <dd class="detail">
                        <label>
                            <input type="text" class="zui-input" name="exceptMatterName"
                                   id="exceptMatterName"/>
                        </label>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">状态</dt>
                    <dd class="detail">
                        <label>
                            <input id="status" name="status" class="zui-combobox zui-validatebox" type="hidden"
                                   data-toggle="combobox"
                                   data-data="[{'id':'0','text':'启用'},{'id':'1','text':'停用'}]"
                                   data-valuefield="id" data-textfield="text"
                                    >
                        </label>
                    </dd>
                </dl>
                <div class="form-btn">
                    <button type="button" class="btn-blue" id="searchButton">查询</button>
                    <button type="button" class="btn-gray" id="resetButton">重置</button>
                </div>
            </form>
            <!-- table演示 -->
            <div class="zui-datagrid" id="zd-table"
                 zdata-options="{'idField':'id','url':'<z:ukey key="com.zdsoft.finance.parameter.getExceptMatter"  context="admin"/>&jsoncallback=?','singleSelect':true,'rownumbers':false,'pagination':true,'tableCls': 'table-index','toolbar':'#btn-applylist'}">
                <table>
                    <thead>
                    <tr>
                        <th data-options="field:exceptMattercode,width:10%">编码</th>
                        <th data-options="field:exceptMatterTypeName,width:15%">类型</th>
                        <th data-options="field:exceptMatterName,width:15%">特批事项</th>
                        <th data-options="field:handelrName,width:10%">操作人</th>
                        <th data-options="field:handelTime,width:15%">操作时间</th>
                        <th data-options="field:remark,width:15%">备注</th>
                        <th data-options="field:status,width:10%" formatter="changeStatus">状态</th>
                        <th data-options="field:id,width:20%" formatter="operate">操作</th>

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
                <dt class="title">编码</dt>
                <dd class="detail">
                    <label>
                        <input type="text" class="zui-input zui-validatebox" id="form_code" disabled="disabled"
                               data-toggle="validate"/>
                        <input type="hidden" id="exceptMattercodeAdd" name="exceptMattercode"/>
                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>类型</dt>
                <dd class="detail">
                    <input id="exceptMatterTypeAdd" name="exceptMatterType" class="zui-combobox zui-validatebox"
                           type="hidden" data-toggle="combobox"
                           data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0077"
                           data-toggle="validate"
                           data-valuefield="id" data-textfield="text"
                           validate-type="Require" validate-false="请选择类型">
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>特批事项</dt>
                <dd class="detail">
                    <label>
                        <input type="text" class="zui-input zui-validatebox" id="exceptMatterNameAdd"
                               name="exceptMatterName"
                               data-toggle="validate"
                               validate-type="Require,Length[1-25]" validate-false="特批事项不能为空"/>
                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>状态</dt>
                <dd class="detail">
                    <input class="zui-checkbox zui-validatebox" id="statusAdd" type="hidden" validate-false="请选择状态"
                           data-toggle="validate"
                           name="status"
                           data-data="[{'id':'0','text':'启用'},{'id':'1','text':'停用'}]"
                           data-valuefield="id" data-textfield="text" validate-type="Require">
                </dd>
            </dl>
            <div>
                <dl class="form-item">
                    <dt class="title">备注</dt>
                    <dd class="detail">
                        <label>
                            <textarea id="remarkAdd" name="remark" class="zui-area zui-validatebox" style="height: 100px;"
                                      validate-type="Length[0-200]" data-toggle="validate"
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

                //状态转换函数
                CALLBACK.changeStatus = function (index, v) {
                    switch (v) {
                        case "0":
                            return "启用";
                            break;
                        case "1":
                            return "停用";
                            break;
                    }

                };
                //操作栏的回调函数
                CALLBACK.operate = function (row) {
                    if (row.id == '0') {
                        return;
                    }
                    var html = '<a href="javaScript:void(0)" onclick="doEdit"><button class="btn-blue">编辑</button></a>'
                    return html;
                }
                //新增行
                CALLBACK.add = function () {
                    //生成唯一编码
                    $.ajax({
                        url:'<z:ukey key="com.zdsoft.finance.parameter.buildingCode" context="admin"/>&jsoncallback=?',
                        dataType:'json',
                        type:'post',
                        success:function(data){
                            if(data.resultStatus==0){
                                //赋值
                                $("#exceptMattercodeAdd").val(data.msg);
                                $("#form_code").val(data.msg);

                                $("#id").val("");
                                //给单选框赋初始值
                                $("#statusAdd").ZCheckbox("setValue","0");
                                $("#dialogEdit").Zdialog("setTitle", "新增");
                                $("#dialogEdit").Zdialog("open");
                            }else{
                                $.ZMessage.error("错误", "错误!" + data.msg, function () {
                                });
                                return false;
                            }
                        },
                        error:function(data){
                            var errorMsg = JSON.parse(data.responseText);
                            $.ZMessage.error("错误", "错误!" + errorMsg.msg, function () {
                                return false;
                            });
                        }
                    });
                };
                //编辑行
                CALLBACK.doEdit = function (num, row) {
                    //   exceptMatterNameAdd statusAdd remarkAdd
                    $("#id").val(row.id);
                    $("#form_code").val(row.exceptMattercode);
                    $("#exceptMattercodeAdd").val(row.exceptMattercode);
                    $("#exceptMatterTypeAdd").ZCombobox('setValue', row.exceptMatterType);
                    $("#exceptMatterNameAdd").val(row.exceptMatterName);
                    $("#statusAdd").ZCheckbox("setValue", row.status);
                    $("#remarkAdd").val(row.remark);

                    $("#dialogEdit").Zdialog("setTitle", "编辑");
                    $("#dialogEdit").Zdialog("open");
                }
                //删除行
                CALLBACK.doDelete = function (num, selRow) {
                    $.ZMessage.question("确认", "确认删除该特批事项吗？", function (r) {
                        if (selRow) {
                            var exceptMatterId = selRow.id;
                            $.ajax({
                                method: "post",
                                dataType: "json",
                                data: {exceptMatterId:exceptMatterId},
                                url: '<z:ukey key="com.zdsoft.finance.parameter.deleteExceptMatter"  context="admin"/>&jsoncallback=?',
                                success: function (result) {
                                    if (result.resultStatus == 0) {
                                        $.ZMessage.info("提示", "删除成功", function () {
                                            var formArray = $("#searchForm").serializeArray();
                                            $('#zd-table').ZTable("reload", formArray);
                                        });
                                    }
                                    else {
                                        $.ZMessage.error("错误", "删除失败" + result.msg, function () {
                                            var formArray = $("#searchForm").serializeArray();
                                            $('#zd-table').ZTable("reload", formArray);
                                        });
                                    }
                                },
                                error:function(data){
                                    var errorMsg = JSON.parse(data.responseText);
                                    $.ZMessage.error("错误", "错误!" + errorMsg.msg, function () {
                                        var formArray = $("#searchForm").serializeArray();
                                        $('#zd-table').ZTable("reload", formArray);
                                    });
                                }

                            });
                        }

                    });

                }


                $("#dialogEdit").Zdialog({
                    title: "新增",
                    width: 700,
                    height: 310,
                    closed: true,
                    isDrag: true,
                    buttons: [
                        {
                            id: 'message-btn', text: '确定', buttonCls: 'btn-blue',
                            handler: function () {
                                var isValidate = $.ZUI.validateForm($('#dialogForm'));
                                if (!isValidate) {
                                    return false;
                                }
                                var param = $("#dialogForm").serializeArray();
                                $.ajax({
                                    method: "post",
                                    dataType: "json",
                                    data: param,
                                    url: '<z:ukey key="com.zdsoft.finance.parameter.updateExceptMatter"  context="admin"/>&jsoncallback=?',
                                    success: function (result) {
                                        if (result.resultStatus == 0) {
                                            var title=$(".zd-window-title.dialog-title span")[0].innerText;
                                            $.ZMessage.success("提示", title+"成功" + result.msg, function () {
                                                var formArray = $("#searchForm").serializeArray();
                                                $('#zd-table').ZTable("reload", formArray);
                                                $("#dialogEdit").Zdialog("close");
                                            });
                                        }
                                        else if(result.resultStatus==-1) {
                                            $.ZMessage.warning("警告", "已经存在相同数据", function () {
                                                var formArray = $("#searchForm").serializeArray();
                                                $('#zd-table').ZTable("reload", formArray);
                                            });
                                        }else{
                                            $.ZMessage.error("错误", title+"出错" + result.msg, function () {
                                                var formArray = $("#searchForm").serializeArray();
                                                $('#zd-table').ZTable("reload", formArray);
                                            });
                                        }

                                    }

                                });
                            }
                        },
                        {
                            id: 'message-btn', text: '取消', buttonCls: 'btn-gray',
                            handler: function () {
                                $("#dialogEdit").Zdialog("close");
                                var formArray = $("#searchForm").serializeArray();
                                $('#zd-table').ZTable("reload", formArray);
                            }
                        }],
                });

                //初始化下拉控件
                $("#exceptMatterType").ZCombobox();
                $("#exceptMatterTypeAdd").ZCombobox();

                $.ZUI.init();
                //查询
                $("#searchButton").click(function () {
                    var formArray = $("#searchForm").serializeArray();
                    $('#zd-table').ZTable("reload", formArray);
                });
                //重置
                $("#resetButton").click(function () {
                    //exceptMattercode exceptMatterType exceptMatterName status
                    $("#exceptMattercode").val("");
                    $("#exceptMatterName").val("");
                    $("#exceptMatterType").ZCombobox('setValue', "");
                    $("#status").ZCombobox('setValue', "");
                });

            });
</script>
</body>
</html>