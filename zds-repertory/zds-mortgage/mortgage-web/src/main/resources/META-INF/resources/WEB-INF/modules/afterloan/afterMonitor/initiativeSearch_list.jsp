<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<%@ include file='../../common/common_js.jsp' %>
<title>主动查询</title>
</head>
<body>
<div class="save">
    <button id="btn-save" class="btn-blue mr10">保存</button>
    <button id="btn-cancel" class="btn-gray mr10">返回</button>
</div>
<div class="page-box">
    <div class="p10">
        <div class="page-box">
            <!-- 查询区域 -->
            <div class="page-title">基本信息</div>
            <div class="p5">
                <table class="table-detail">
                    <tr>
                        <td class="td-title pct10">发送人</td>
                        <td class="pct20">
                            <dl class="form-item form-auto">
                                <dd class="detail f12">
                                    <label>
                                        ${emp.empNm}
                                    </label>
                                </dd>
                            </dl>
                        </td>
                        <td class="td-title pct10">发送时间</td>
                        <td class="pct20">
                            <dl class="form-item form-auto">
                                <dd class="detail f12">
                                    <label>
                                        ${searchDate}
                                    </label>
                                </dd>
                            </dl>
                        </td>
                        <td class="td-title pct10">数据数</td>
                        <td class="pct20">
                            <dl class="form-item form-auto">
                                <dd class="detail f12">
                                    <label id="number">
                                        ${overCount}
                                    </label>条
                                </dd>
                            </dl>
                        </td>
                    </tr>
                    <tr>
                        <td class="td-title pct10">逾期总金额</td>
                        <td class="pct20">
                            <dl class="form-item form-auto">
                                <dd class="detail f12">
                                    <label id="overdueAmount">
                                        ${overAmount}
                                    </label>元
                                </dd>
                            </dl>
                        </td>
                        <td class="td-title pct10"></td>
                        <td class="pct20">
                            <dl class="form-item form-auto">
                                <dd class="detail f12">
                                    <label>
                                    </label>
                                </dd>
                            </dl>
                        </td>
                        <td class="td-title pct10"></td>
                        <td class="pct20">
                            <dl class="form-item form-auto">
                                <dd class="detail f12">
                                    <label>
                                    </label>
                                </dd>
                            </dl>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <%--接口列表--%>
        <div class="page-box">
            <!-- 查询区域 -->
            <div class="page-title">接口选择</div>
            <div class="p5">
                <table class="table-detail">
                    <tr>
                        <td class="td-title pct10">选择接口</td>
                        <td class="pct20">
                            <input class="zui-checkbox zui-validatebox" id="activeSearchImpl" type="hidden"
                                   data-multiple="true"
                                   data-data="[{'id':'0','text':'汇法网'},{'id':'1','text':'房产估价'},{'id':'2','text':'工商'}]"
                                   data-valuefield="id" data-textfield="text" validate-type="Require">
                        </td>
                        <td class="td-title pct10"></td>
                        <td class="pct20"></td>
                        <td class="td-title pct10"></td>
                        <td class="pct20"></td>
                    </tr>
                </table>
            </div>
        </div>
        <!-- 案件列表 -->
        <!-- begin -->
        <div class="page-box">
            <div class="page-title">发送清单(<a style="color: FF6600;">请仔细确认以下清单</a>)
            </div>
            <div class="p10">
                <div id="caseList"></div>
            </div>
            <!-- end -->
        </div>
    </div>
</div>
<script type="text/javascript">
    var productUrl = '<z:ukey key="com.cnfh.rms.businessProduct.findByCategoryId" context="admin"/>&jsoncallback=?';
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/tools', 'zd/jquery.zds.form', 'zd/jquery.zds.message', 'zd/jquery.zds.dialog', 'zd/jquery.zds.combobox', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'], function ($, CALLBACK, ZTOOL) {
        //案件列表 begin
        $('#caseList').ZTable({
            url: "<z:ukey key='com.zdsoft.finance.afterMonitor.initiActiveList' context='admin'/>&jsoncallback=?&id=${ids}",
            isRowNum: false,
            currentPage: 1,
            rows: 10,
            singleSelect: true,
            pagination: false,
            idField: "id",
            tableCls: 'table-index',//table的样式
            columns: [[
                {field: 'caseApplyCode', title: '案件号', align: 'center'},
                {field: 'mechanismName', title: '机构名称', align: 'center'},
                {field: 'mainBorrower', title: '主借人', align: 'center'},
                {field: 'phoneNumber', title: '联系方式', align: 'center'},
                {field: 'productSubtypeName', title: '子产品', align: 'center'},
                {field: 'applyAmount', title: '申请金额(元)', align: 'center'},
                {field: 'overdueAmount', title: '逾期金额(元)', align: 'center'},
                {
                    field: 'overdueDate', title: '逾期日期', align: 'center', formatter: function (r, v) {
                    if (v) {
                        return ZTOOL.strToDate(v + "");
                    }
                    return '';
                }
                },
                {field: 'overdueDay', title: '逾期天数(天)', align: 'center'},

                {
                    field: 'id', title: '操作', align: 'center', formatter: function (r, v) {
                    return '<a href="javaScript:void(0)" class="btn-blue" onclick="deleteContactHandle">删除</a>';
                }
                }
            ]],
            onDelete: function (index, rowData) {
                //  添加判断
                return true;
            },
            onLoadSuccess: function () {
                $("td").each(function () {
                    if ($(this).text().trim() == 'null') {
                        $(this).text("");
                    }
                });
            }
        });
        //案件列表 end
        //删除行
        CALLBACK.deleteContactHandle = function (index, rowData) {
            $.ZMessage.confirm("确认删除", "请确认是否删除此记录", function (r) {
                $("#number").text($("#number").text() - 1);
                if (rowData.overdueAmount) {
                    $("#overdueAmount").text($("#overdueAmount").text() - rowData.overdueAmount);
                }
                //模拟点击选中事件
                $($('#caseList .datagrid-body').find("tr")[index]).trigger("click");
                $('#caseList').ZTable("deleteRow");
            });
        }
        //保存或者提交
        function saveOrSubmit() {
            var rows = $('#caseList').ZTable("getRows");
            if (rows.length == 0) {
                $.ZMessage.info("提示", "请至少添加一条记录！", function () {
                });
                return false;
            }
            var impls = $("#activeSearchImpl").val();
            if(impls==""){
                $.ZMessage.info("提示", "请至少选择一个接口！", function () {
                });
                return false;
            }
            var ids = "";
            for (var i = 0; i < rows.length; i++) {
                ids += rows[i].id + ",";
            }
            ids = ids.substr(0, ids.length - 1);
            $.ajax({
                type: 'post',
                url: '<z:ukey key="com.zdsoft.finance.afterMonitor.doInitiativeSearch" context="admin"/>',
                data: {ids: ids,impls:impls},
                success: function (data) {
                    if (data.resultStatus == 0) {
                        $.ZMessage.success("成功", "查询成功", function () {
                            setTimeout(function () {
                                ZDS_MESSAGE_CLIENT.refreshOpenner();
                                ZDS_MESSAGE_CLIENT.closeSelf();
                            }, 200);
                        });
                    } else {
                        $.ZMessage.error("错误", data.msg, function () {
                        });
                    }
                },
                error: function () {
                    $.ZMessage.error("错误", "保存信息系统异常，请联系管理员", function () {
                    });
                }
            });
        }

        //提交
        $("#btn-save").click(function () {
            saveOrSubmit();
        });
        //提交
        $("#btn-cancel").click(function () {
            ZDS_MESSAGE_CLIENT.refreshOpenner();
            ZDS_MESSAGE_CLIENT.closeSelf();
        });

        $("#activeSearchImpl").ZCheckbox({multiple: true});

        $.ZUI.init();
    });
</script>
</body>
</html>