<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <%@ include file='../common/common_js.jsp' %>
    <title>终端列表</title>
</head>
<body>
<div class="frm-content">
    <!-- 查询区域 -->
    <div class="page-box">
        <div class="page-title">终端管理</div>
        <div class="p10">
            <form id="search_from" class="zui-form form-search" method="post"
                  enctype="multipart/form-data">
                <dl class="form-item">
                    <dt class="title">终端名称：</dt>
                    <dd class="detail">
                        <label> <input class="zui-input" id="terminalFullName" name="terminalFullName|LK|S">
                        </label>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">状态：</dt>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" id="terminalStatus" name="terminalStatus|E|S" type="hidden"
                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00163"
                               data-valuefield="fullcode" data-textfield="name">
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">创建日期：</dt>
                    <dd class="detail">
                        <label>
                            <input type="text" id="startTimeLocal" class="zui-input width2-1"
                                   onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'startTime'})">
                            <input type="hidden" id="startTime"/>
                        </label>
                        <span class="word">至</span>
                        <label>
                            <input type="text" id="endTimeLocal" class="zui-input width2-1"
                                   onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'endTime'})">
                            <input type="hidden" id="endTime"/>
                        </label>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">终端类别：</dt>
                    <dd class="detail">
                        <input class="zui-combotree zui-validatebox" id="terminalType" name="terminalType|LK|S"
                               type="hidden"
                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00103"
                               data-valuefield="fullcode" data-callback="reloadMeetingProject" data-textfield="name">
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">终端归属：</dt>
                    <dd class="detail">
                        <label> <input class="zui-input zui-validatebox" validate-type="Length[0-128]" id="belongRelevanceName" name="belongRelevanceName|LK|S">
                        </label>
                    </dd>
                </dl>
                <dl class="form-btn">
                    <button type="button" class="btn-search-blue" id="btn-search">查询</button>
                    <button type="button" class="btn-search-gray" id="btn-reset">重置</button>
                </dl>
            </form>
        </div>
    </div>
    <div class="page-box">
        <div class="p10">
            <div id="terminal_datagrid" class="zui-datagrid"
                 zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.cooperator.getCooperator" context="admin"/>&jsoncallback=?&shareOrgCode|E|S=${shareOrgCode }","singleSelect":false,"pagination":true,"idField":"id","toolbar":"#btn-applylist","tableCls":"table-index"}'>
                <table>
                    <thead>
                    <tr>
                        <th data-options="field:terminalCode">终端编码</th>
                        <th data-options="field:terminalFullName">终端名称</th>
                        <th data-options="field:terminalTypeName">终端类别</th>
                        <th data-options="field:grade">终端等级</th>
                        <th data-options="field:linkman">主要联系人</th>
                        <th data-options="field:belongRelevanceName">终端归属</th>
                        <th data-options="field:createTimeName">创建日期</th>
                        <th data-options="field:maintenanceTimes">维护次数</th>
                        <th data-options="field:terminalStatusName">状态</th>
                        <th data-options="field:id" formatter="operate">操作</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div id="btn-applylist">
                <a class="zui-toolbar" id="btn-add" text="新增" iconCls="icon-add" buttonCls="btn-blue"
                   handler="doAdd"></a>
                <a class="zui-toolbar" id="btn-close" text="批量停用" iconCls="icon-edit" buttonCls="btn-blue"
                   handler="doClose"></a>
                <a class="zui-toolbar" id="btn-open" text="批量启用" iconCls="icon-edit" buttonCls="btn-blue"
                   handler="doOpen"></a>
                <a class="zui-toolbar" id="btn-export" text="导出" iconCls="icon-delete" buttonCls="btn-gray"
                   handler="doExport"></a>
            </div>
        </div>
    </div>
    <div style="display:none" >
    	<table id="tableTemp"></table>
    </div>
</div>
<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.form', 'zd/jquery.zds.message', 'zd/jquery.zds.dialog', 'zd/jquery.zds.combobox', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter', 'zd/completer'], function ($, CALLBACK) {
	    
//     	var data = '${data}';
//     	$("#belongRelevanceName").completer({
//             suggest: true,//默认false
//             idField: 'name',//默认id,唯一标识字段
//             nameField: 'name',//默认name,下拉列表展示数据的字段
//             valueField: 'py',//默认value,根据值查询数据的字段
//             source:data,
//             writable: true,//默认false，是否可自定义输入
//             complete: function (data) {
//                 $('#belongRelevanceName').val(data.name);
//             },
//             filter: function (val) {
//                 return val;//过滤输入的value值
//             }

//         });

    	
        $.ZUI.init();
        //操作
        CALLBACK.operate = function (row, value) {
            var html = "<a title='编辑' class='btn-blue mr5' onclick='termindEdit'>编辑</a>";
            html += "<a title='删除' class='btn-blue' onclick='terminalDel'>删除</a>";
            return html;
        };


        CALLBACK.termindEdit = function (index, data) {
            ZDS_MESSAGE_CLIENT.openMenuLink('terminal_edit', '编辑终端', '<z:ukey key="com.zdsoft.finance.cooperator.edit" context="admin"/>&id=' + data.id);
        };
        // 新增按钮事件
        var terminal_addUrl = '<z:ukey key="com.zdsoft.finance.cooperator.terminalAdd" context="admin"/>';
        CALLBACK.doAdd = function (index, data) {
            ZDS_MESSAGE_CLIENT.openMenuLink('terminal_add', '新增终端', terminal_addUrl);
        };
        CALLBACK.terminalDel = function (index, data) {
            $.ZMessage.question("提示", "是否删除", function (index) {
                $.ajax({
                    type: 'post',
                    url: '<z:ukey key="com.zdsoft.finance.cooperator.del" context="admin"/>',
                    data: data,
                    dataType: 'json',
                    success: function (data) {
                        if (data.resultStatus == 0) {
                            $.ZMessage.info("提示", "删除成功", function () {

                            });
                        } else {
                            $.ZMessage.error("错误", data.msg, function () {
                                $(".zd-message").ZWindow("close");
                            });
                        }
                        $("#terminal_datagrid").ZTable("reload");
                    },
                    error: function () {
                        $.ZMessage.error("错误", "系统异常，请联系管理员", function () {
                            $(".zd-message").ZWindow("close");
                        });
                    }
                });
            });
        };
        CALLBACK.doClose = function (index, data) {
            var rows = $('#terminal_datagrid').ZTable("getSelections");
            if (rows.length < 1) {
                $.ZMessage.error("提示", "请勾选数据", function () {
                });
                return;
            }
            var params = '';
            for (var index in rows) {
                params += rows[index]['id'] + ',';
            }
            $.ajax({
                type: 'post',
                url: '<z:ukey key="com.zdsoft.finance.cooperator.update" context="admin"/>&state=YWDM0016302',
                data: {'id': params},
                dataType: 'json',
                success: function (data) {
                    if (data.resultStatus == 0) {
                        $.ZMessage.success("提示", "停用成功", function () {
                            doSearch();
                        });
                    } else {
                        $.ZMessage.error("错误", data.msg, function () {
                            $(".zd-message").ZWindow("close");
                        });
                    }
                },
                error: function () {
                    $.ZMessage.error("错误", "系统异常，请联系管理员", function () {
                        $(".zd-message").ZWindow("close");
                    });
                }
            });
        };
        CALLBACK.doOpen = function (index, data) {
            var rows = $('#terminal_datagrid').ZTable("getSelections");
            if (rows.length < 1) {
                $.ZMessage.error("提示", "请勾选数据", function () {
                    doSearch();
                });
                return;
            }
            var params = '';
            for (var index in rows) {
                params += rows[index]['id'] + ',';
            }
            $.ajax({
                type: 'post',
                url: '<z:ukey key="com.zdsoft.finance.cooperator.update" context="admin"/>&state=YWDM0016301',
                data: {'id': params},
                dataType: 'json',
                success: function (data) {
                    if (data.resultStatus == 0) {
                        $.ZMessage.success("提示", "启用成功", function () {
                            doSearch();
                        });
                    } else {
                        $.ZMessage.error("错误", data.msg, function () {
                            $(".zd-message").ZWindow("close");
                        });
                    }
                },
                error: function () {
                    $.ZMessage.error("错误", "系统异常，请联系管理员", function () {
                        $(".zd-message").ZWindow("close");
                    });
                }
            });
        };
        CALLBACK.doExport = function (index, data) {
            var url = "<z:ukey key="com.zdsoft.finance.toExcel" context="admin"/>&jsoncallback=?&fileName=终端列表";
            $('#tableTemp').html($('table:eq(0)').html());
            $("body table:eq(1) td[field='datagrid-header-check']").remove();
            $("body table:eq(1) th[field='id']").remove();
            var params = $('body table:eq(1)').html();
            $("form").remove("#exportFrom");
            $("body").append("<form id='exportFrom' class='zui-form mt15' method='post' action='" + url + "' accept-charset='utf-8'><input type='hidden' id='htmlContent' name='htmlContent' value='" + params + "' /></form>");
            $("#exportFrom").submit();   
        };


        $('#btn-search').click(function () {
            doSearch();
        });
        $('#btn-reset').click(function () {
            $('#terminalFullName').val('');
            $('#belongTypeName').val('');
            $("#isStop").ZCombobox('setValue', '');
            $("#terminalType").ZCombobox('setValue', '');
            $("#terminalStatus").ZCombobox('setValue', '');
            $('#belongType').val('');
            $('#startTime').val('');
            $('#startTime2').val('');
            $('#endTime').val('');
            $('#endTime2').val('');
            $('#startTimeLocal').val('');
            $('#endTimeLocal').val('');
            $('#startTimeLocal1').val('');
            $('#endTimeLocal1').val('');
            $('#belongRelevanceName').val('');
        });
        function doSearch() {
            var formArray = $("#search_from").serialize();
            formArray = decodeURIComponent(formArray, true);
            var startTime = $('#startTime').val();
            var endTime = $('#endTime').val();
            formArray += '&createDateLong|BT|BT=' + startTime + ',' + endTime;
            $('#terminal_datagrid').ZTable("reload", formArray);
        };

        ZDS_MESSAGE_CLIENT.refreshThis = function () {
            doSearch();
        };

    });
</script>
</body>
</html>