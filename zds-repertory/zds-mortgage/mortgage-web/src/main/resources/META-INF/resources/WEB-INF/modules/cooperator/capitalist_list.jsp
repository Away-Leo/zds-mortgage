<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <%@ include file='../common/common_js.jsp' %>
    <title>资方列表</title>
</head>
<body>
<div class="frm-content">
    <!-- 查询区域 -->
    <div class="page-box">
        <div class="page-title">资方管理</div>
        <div class="p10">
            <form id="search_from" class="zui-form form-search" method="post"
                  enctype="multipart/form-data">
                <dl class="form-item">
                    <dt class="title">资方名称：</dt>
                    <dd class="detail">
                        <label> <input class="zui-input" id="capitalName" name="capitalName|LK|S">
                        </label>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">资方类别：</dt>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" id="capitalistType" name="capitalistType|E|S"
                               type="hidden"
                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00112"
                               data-valuefield="fullcode" data-callback="reloadMeetingProject" data-textfield="name">
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
            <div id="capitalist_datagrid" class="zui-datagrid"
                 zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.cooperator.capitalist.getCapitalist" context="admin"/>&jsoncallback=?","singleSelect":false,"pagination":true,"idField":"id","toolbar":"#btn-applylist","tableCls":"table-index"}'>
                <table>
                    <thead>
                    <tr>
                        <th data-options="field:capitalName">资方名称</th>
                        <th data-options="field:capitalistTypeName">资方类型</th>
                        <th data-options="field:id,width:20%" formatter="operate">操作</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div id="btn-applylist">
                <a class="zui-toolbar" id="btn-add" text="新增" iconCls="icon-add" buttonCls="btn-blue"
                   handler="doAdd"></a>
                <a class="zui-toolbar" id="btn-export" text="导出" iconCls="icon-delete" buttonCls="btn-gray"
                   handler="doExport"></a>
            </div>
        </div>
    </div>
</div>
<div id="capitalistDialog" style="display: none">
</div>
<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.form', 'zd/jquery.zds.message', 'zd/jquery.zds.dialog', 'zd/jquery.zds.combobox', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'], function ($, CALLBACK) {
        //操作
        CALLBACK.operate = function (row, value) {
            var html = "<a title='编辑' class='btn-blue mr5' onclick='capitalistEdit'>编辑</a>";
            html += "<a title='查看' class='btn-blue mr5' onclick='capitalistView'>查看</a>";
            html += "<a title='删除' class='btn-blue' onclick='capitalistDel'>删除</a>";
            return html;
        };

        $.ZUI.init();
        CALLBACK.capitalistEdit = function (index, data) {
            ZDS_MESSAGE_CLIENT.openMenuLink('capitalist_edit', '资方编辑', '<z:ukey key="com.zdsoft.finance.cooperator.capitalist.tab" context="admin"/>&operationType=mod&capitalistId=' + data.id);
        };
        CALLBACK.doAdd = function () {
            var url = '<z:ukey key="com.zdsoft.finance.cooperator.capitalist.dialog" context="admin"/>';
            $('#capitalistDialog').load(url, function () {
            });
        }

        CALLBACK.capitalistView = function (index, data) {
            ZDS_MESSAGE_CLIENT.openMenuLink('capitalist_add', '资方查看', '<z:ukey key="com.zdsoft.finance.cooperator.capitalist.tab" context="admin"/>&operationType=view&capitalistId=' + data.id);
        };

        CALLBACK.capitalistDel = function (index, data) {
            $.ZMessage.question("提示", "是否删除", function (index) {
                $.ajax({
                    type: 'post',
                    url: '<z:ukey key="com.zdsoft.finance.cooperator.capitalist.del" context="admin"/>',
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
                        $("#capitalist_datagrid").ZTable("reload");
                    },
                    error: function () {
                        $.ZMessage.error("错误", "系统异常，请联系管理员", function () {
                            $(".zd-message").ZWindow("close");
                        });
                    }
                });
            });
        };
        CALLBACK.doExport = function (index, data) {
            var url = "<z:ukey key="com.zdsoft.finance.toExcel" context="admin"/>&jsoncallback=?&fileName=资方列表";
            var param = $("table").html();
            //去除多余的列,修改导出数据问题的bug
            var table=$(param);
            var head=$(table).children();
            for(var i=0;i<head.length;i++){
            	$($(head[i]).children()[0]).remove();
            	$($(head[i]).children()[3]).remove();
            }
            var p=table[0].outerHTML+table[1].outerHTML;
            $("form").remove("#exportFrom");
            $("body").append("<form id='exportFrom' class='zui-form mt15' method='post' action='" + url + "' accept-charset='utf-8'><input type='hidden' id='htmlContent' name='htmlContent' value='" + p + "' /></form>");
            $("#exportFrom").submit();
        };
        $('#btn-search').click(function () {
            doSearch();
        });
        $('#btn-reset').click(function () {
            $('#capitalName').val('');
            $("#capitalistType").ZCombobox('setValue', '');
        });

        function doSearch() {
            var formArray = $("#search_from").serialize();
            formArray = decodeURIComponent(formArray, true);
            $('#capitalist_datagrid').ZTable("reload", formArray);
        }

        ZDS_MESSAGE_CLIENT.refreshThis = function () {
            doSearch();
        };

    });
</script>
</body>
</html>