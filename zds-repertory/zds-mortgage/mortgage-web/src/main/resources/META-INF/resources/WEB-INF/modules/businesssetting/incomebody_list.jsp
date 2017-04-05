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
        <div class="page-title">收款主体</div>
        <div class="p5">
            <form action="#" id="searchForm" class="zui-form form-search" method="post"
                  enctype="multipart/form-data">
                <dl class="form-item">
                    <dt class="title">机构名称</dt>
                    <dd class="detail">
                        <input class="zui-combotree zui-validatebox" type="hidden" id="orgId"
                               data-multiple="false" data-defaultvalue=""
                        <%--data-url="<z:res resource='enssential.org.findOrgToTree' isDefault='true'/>&jsoncallback=?&type=dept"--%>
                               data-callback="orgCallBack"
                               data-valuefield="id" data-textfield="text" data-height="550">
                        <input type="hidden" id="orgIdSearch" name="orgPid"/>
                        <input type="hidden" id="orgIds" name="orgIds"/>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">收款主体</dt>
                    <dd class="detail">
                        <label>
                            <input type="text" class="zui-input" name="inBody" id="inBody"/>
                        </label>
                    </dd>
                </dl>
                <div class="form-btn">
                    <button type="button" class="btn-blue" id="searchButton">查询</button>
                    <button type="button" class="btn-gray" id="resetButton">重置</button>
                </div>
            </form>
            <!-- table演示 -->
            <div class="zui-datagrid" id="zd-table">
            </div>
        </div>
    </div>
    <%--当前登录人所属机构--%>
    <input type="hidden" id="currentOrdCode" value="${empOrgCd}"/>

    <div id="dialogEdit" style="display: none">
        <div id="testForm" class="mt20">
            <form id="dialogForm" class="zui-form">
                <input type="hidden" id="id" name="id" />
                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>机构名称</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-combotree zui-validatebox" type="hidden" name="orgId" id="form_orgId"
                                   data-multiple="false" data-defaultvalue="" data-callback="orgEditCallBack"
                            <%--data-url="<z:res resource='enssential.org.findOrgToTree' isDefault='true'/>&jsoncallback=?&type=dept"--%>
                                   data-valuefield="id" data-textfield="text" validate-type="Require"
                                   validate-false="请选择机构!">
                            <input type="hidden" id="orgPid" name="orgPid"/>
                        </label>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>收款主体</dt>
                    <dd class="detail">
                        <label>
                            <input type="text" class="zui-input zui-validatebox" id="form_inBody" name="inBody"
                                   validate-type="Require,Length[1-25]" validate-false="收款主体不能为空"/>
                        </label>
                    </dd>
                </dl>
                <div>
                    <dl class="form-item">
                        <dt class="title">备注</dt>
                        <dd class="detail">
                            <label>
                                <textarea id="form_remark" class="zui-area zui-validatebox" validate-type="Length[0-200]" name="remark"
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
                            case 1:
                                return "启用";
                                break;
                            case 0:
                                return "停用";
                                break;
                        }

                    };
                    //操作栏的回调函数
                    CALLBACK.operate = function () {
                        var html = '<a href="javaScript:void(0)" onclick="doEdit"><button class="btn-blue" >编辑</button></a>' + '&nbsp;&nbsp;' +
                                '<a href="javaScript:void(0)" onclick="doDelete"><button class="btn-blue" >删除</button></a>'
                        return html;

                    };
                    //编辑行
                    CALLBACK.doEdit = function (num, selRow) {
                        $("#id").val(selRow.id);
                        $("#form_inBody").val(selRow.inBody);
                        $("#form_remark").val(selRow.remark);
                        $("#form_orgId").ZComboTree('setValue', selRow.orgId);
                        $("#orgPid").val(selRow.orgPId);
                        $("#dialogEdit").Zdialog("setTitle", "编辑");
                        $("#dialogEdit").Zdialog("open");
                    };
                    //删除行
                    CALLBACK.doDelete = function (num, selRow) {
                        $.ZMessage.question("确认", "确认删除该收款主体吗？", function (r) {

                            if (selRow) {
                                var param = {};
                                param.id = selRow.id;
                                $.ajax({
                                    method: "post",
                                    dataType: "json",
                                    data: param,
                                    url: '<z:ukey key="com.zdsoft.finance.parameter.deleteInComeBody"  context="admin"/>&jsoncallback=?',
                                    success: function (result) {
                                        if (result.resultStatus == 0) {
                                            $.ZMessage.info("提示", result.msg, function () {
                                                var formArray = $("#searchForm").serializeArray();
                                                $('#zd-table').ZTable("reload", formArray);
                                            });
                                        }
                                        else {
                                            $.ZMessage.error("错误", result.msg, function () {
                                                var formArray = $("#searchForm").serializeArray();
                                                $('#zd-table').ZTable("reload", formArray);
                                            });
                                        }
                                    }

                                });
                            }

                        });
                    };
                    //弹出框机构下拉框回调函数
                    CALLBACK.orgEditCallBack = function (index, rowData) {
                        var orgPid = rowData.pId;
                        if (orgPid != "null") {
                            $("#orgPid").val(orgPid);
                        } else {
                            $("#orgPid").val("");
                        }
                    };
                    CALLBACK.orgCallBack = function (index, rowData) {
                        //清空上一次搜索数据
                        $("#orgIds").val("");
                        //循环取出所有的子机构
                        loopOrgIds(rowData);
                    };
                    CALLBACK.megreColumn = function () {
                        //表格所有行对象
                        var rows = $(".datagrid-body").children();
                        //表体对象
                        var tBody = $(".datagrid-body");
                        //清空表体
                        tBody.html("");
                        //将表体中的行按机构分组重新添加到表体
                        while (rows.length > 0) {
                            //一组的行数
                            var samNum = 0;
                            //当前分组的机构名称
                            var curName = $(rows[0]).children()[1].innerHTML;
                            //分组的第一行数据
                            var firstGroupRow = $(rows[0]);
                            tBody.append($(rows[0]));
                            //删除行的第一条数据
                            rows.splice(0, 1);
                            for (var j = 0; j < rows.length; j++) {
                                //查找机构单元格
                                var cell = $($(rows[j]).children()[1]);
                                if (cell.html() == curName) {
                                    tBody.append(rows[j]);
                                    cell.remove();
                                    rows.splice(j, 1);
                                    j--;
                                    samNum++;
                                }
                            }
                            $(firstGroupRow.children()[1]).attr("rowspan", samNum + 1);

                        }
                    };
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
                                        if ($("#form_inBody").val() == "null") {
                                            $.ZMessage.info("提示", "无效的收款主体名称", function () {

                                            });
                                            return;
                                        }
                                        var param = $('#dialogForm').serializeArray();
                                        $.ajax({
                                            method: "post",
                                            dataType: "json",
                                            data: param,
                                            url: '<z:ukey key="com.zdsoft.finance.parameter.updateInComeBody"  context="admin"/>&jsoncallback=?',
                                            success: function (result) {
                                                if (result.resultStatus == 0) {
                                                    $.ZMessage.success("提示", result.msg, function () {
                                                        var param = $("#searchForm").serializeArray();
                                                        $('#zd-table').ZTable("reload", param);
                                                        $("#dialogEdit").Zdialog("close");
                                                    });
                                                }
                                                else {
                                                    $.ZMessage.error("错误", result.msg, function () {
                                                        var param = $("#searchForm").serializeArray();
                                                        $('#zd-table').ZTable("reload", param);
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
                                    $("#dialogEdit").Zdialog("close");
                                }
                            }],
                    });
                    //执行机构下拉框初始化
                    currentOrgs();
                    //得到当前登录人权限内的机构数据
                    function currentOrgs() {
                        var allOrgJsonUrl = '<z:res resource='enssential.org.findOrgToTree' isDefault='true'/>&jsoncallback=?&type=dept';
                        $.ajax({
                            url: allOrgJsonUrl,
                            type: 'post',
                            dataType: 'jsonp',
                            success: function (data) {
                                loopGetOrgs(data);
                            }
                        })
                    };
                    //循环取出所选机构的id和子ID
                    function loopOrgIds(orgs) {
                        if (orgs) {
                            var childs = orgs.children;
                            $("#orgIds").val($("#orgIds").val() + orgs.id + ",");
                            if (childs.length > 0) {
                                for (var i = 0; i < childs.length; i++) {
                                    loopOrgIds(childs[i]);
                                }
                            }
                        }

                    };
                    //循环迭代取出有权限的机构数据
                    function loopGetOrgs(orgs) {
                        for (var j = 0; j < orgs.length; j++) {
                            if (orgs) {
                                var childs = orgs[j].children;
                                var orgCd = orgs[j].attributes.orgCd;
                                var currentOrdCode = $("#currentOrdCode").val();
                                if (orgCd == currentOrdCode) {
                                    $("#orgId").ZComboTree({
                                        data: orgs[j]
                                    });
                                    $("#form_orgId").ZComboTree({
                                        data: orgs[j]
                                    });
                                    //清空上一次搜索数据
                                    $("#orgIds").val("");
                                    loopOrgIds(orgs[j]);
                                    tableFormat();
                                    return;
                                } else {
                                    if (childs.length > 0) {
                                        loopGetOrgs(childs);
                                    }
                                }
                            }
                        }
                    };
                    function tableFormat() {
                        $('#zd-table').ZTable({
                            toolbar: [
                                {
                                    id: 'btn-add', text: '新增', iconCls: 'icon-add', buttonCls: 'btn-blue',
                                    handler: function (jq) {
                                        $("#id").val("");
                                        $("#orgPid").val("");
                                        $("#dialogEdit").Zdialog("setTitle", "新增");
                                        $("#dialogEdit").Zdialog("open");
                                    }
                                }
                            ],
                            columns: [[
                                {field: 'orgName', title: '机构名称',width:'20%'},
                                {field: 'inBody', title: '收款主体',width:'20%'},
                                {field: 'updateTimeStr', title: '操作时间',width:'20%'},
                                {field: 'remark', title: '备注',width:'30%'},
                                {
                                    field: 'id', title: '操作', align: 'center',width:'10%',
                                    formatter: function (r, v) {
                                        var html = '<a href="javaScript:void(0)" onclick="doEdit"><button class="btn-blue" >编辑</button></a>' + '&nbsp;&nbsp;' +
                                                '<a href="javaScript:void(0)" onclick="doDelete"><button class="btn-blue" >删除</button></a>'
                                        return html;
                                    }
                                }
                            ]],
                            idField: 'id',
                            url: '<z:ukey key="com.zdsoft.finance.parameter.getInComeBody"  context="admin"/>&jsoncallback=?',
                            singleSelect: false,
                            isRowNum: true,
                            rows: 8,
                            queryParams:{orgIds:$("#orgIds").val()},
                            currentPage: 1,
                            pagination: true,
                            tableCls: 'table-index',
                            isMergeCell: true,
                            mergeColField: 'orgName',
                            mergeCol: 'orgName'
                        });
                    };
                    //查询
                    $("#searchButton").click(function () {
                        var formArray = $("#searchForm").serializeArray();
                        $('#zd-table').ZTable("reload", formArray);
                    });
                    //重置
                    $("#resetButton").click(function () {
                        $("#inBody").val("");
                        $("#orgId").ZComboTree('setValue', "");
                        $("#orgIdSearch").val("");
                        //执行机构下拉框初始化 并加载列表
                        currentOrgs();
                    });

                    $.ZUI.init();
                });
    </script>
</body>
</html>