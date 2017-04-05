<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="page-title contract">
    <h1 class="page-title">资料盖章明细</h1>
</div>
<div class="page-box">
    <div class="p10 contract">
        <div id="contactSealDetailList"/>
    </div>
</div>
<div id="sealDetailFileNameDialog" style="display: none">
    <div id="sealDetailFileNameGrid" class="zui-datagrid"
         zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.contract.conCaseContractSeal.sealDataFileName" context="admin"/>","singleSelect":false,"pagination":false,"idField":"id","tableCls":"table-index"}'>
        <table>
            <tr>
                <th data-options="field:name">资料名称</th>
            </tr>
        </table>
    </div>
</div>

<div id="contractFileDialog" style="display: none">
    <div id="contractFileGrid" class="zui-datagrid"
         zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.contract.conCaseContractSeal.importContractFileList" context="admin"/>&contractId=${contract.id}","singleSelect":false,"pagination":false,"idField":"id","tableCls":"table-index"}'>
        <table>
            <tr>
                <th data-options="field:CONTRACTNAME">文件名</th>
            </tr>
        </table>
    </div>
</div>


<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.table', 'zd/jquery.zds.dialog', 'zd/jquery.zds.loading'], function ($, CALLBACK, ZTable, Zdialog, Loading) {
        var toolsBar = [];
        var tableColumns = null;
        var tableColumnsType = null;
        if (!viewType) {
            tableColumns = [[
                {field: 'materialCode', title: '资料编码', align: 'center', hidden: true},
                {field: 'materialName', title: '资料名称', align: 'center'},
                {field: 'originalNum', title: '原件(份数)', align: 'center'},
                {field: 'copyNum', title: '复印件(份数)', align: 'center'},
                {field: 'applySeal', title: '申请公章', align: 'center', width: "304"},
                {field: 'otherExplain', title: '其他说明', align: 'center'},
                {
                    field: "id", title: '操作', align: 'center', formatter: function (index, row) {
                    return '<button href="javaScript:void(0)" onclick="deleteContactHandle(this)" class="btn-blue">删除</button>';
                }
                }
            ]];
            toolsBar = [{
                id: "btn_add",
                text: "新增资料",
                iconCls: "icon-add",
                buttonCls: "btn-blue",
                handler: function () {
                    $("#sealDetailFileNameDialog").Zdialog("open");
                }
            }
                ,
                {
                    id: "btn_print",
                    text: "打印清单",
                    iconCls: "icon-add",
                    buttonCls: "btn-blue",
                    handler: function () {
                    }
                },
                {
                    id: "btn_import",
                    text: "导入合同",
                    iconCls: "icon-add",
                    buttonCls: "btn-blue",
                    handler: function () {
                        $("#contractFileDialog").Zdialog("open");
                    }
                }];
            tableColumnsType = [
                {
                    originalNum: {
                        "inputType": "input",
                        "validateType": "Require,Amount"
                    }, copyNum: {
                    "inputType": "input",
                    "validateType": "Require,Amount"
                }, applySeal: {
                    "inputType": "checkbox",
                    "data": {
                        "valueField": "id",
                        "textField": "name",
                        "multiple": false,
                        "data": "[{'id': '1', 'name': '公章'}, {'id': '2', 'name': '合同章'}, {'id': '3','name': '法人章'}, {'id': '4', 'name': '授权章'}]"
                    },
                    "validateType": "Require"
                },
                    otherExplain: {
                        "inputType": "textarea"
                    }
                },
                {
                    "inputWidth": 100,
                    "areaWidth": 220,
                    "areaHeight": 26
                }
            ];
        } else {
            tableColumns = [[
                {field: 'materialCode', title: '资料编码', align: 'center', hidden: true},
                {field: 'materialName', title: '资料名称', align: 'center'},
                {field: 'originalNum', title: '原件(份数)', align: 'center'},
                {field: 'copyNum', title: '复印件(份数)', align: 'center'},
                {field: 'applySeal', title: '申请公章', align: 'center', width: "304"},
                {field: 'otherExplain', title: '其他说明', align: 'center'}
            ]];
            tableColumnsType = [
                {
                    originalNum: {
                        "inputType": "readonly",
                        "validateType": "Require,Amount"
                    }, copyNum: {
                    "inputType": "readonly",
                    "validateType": "Require,Amount"
                }, applySeal: {
                    "inputType": "checkbox",
                    "data": {
                        "valueField": "id",
                        "textField": "name",
                        "multiple": false,
                        "choose": "disable",
                        "data": "[{'id': '1', 'name': '公章'}, {'id': '2', 'name': '合同章'}, {'id': '3','name': '法人章'}, {'id': '4', 'name': '授权章'}]"
                    },
                    "validateType": "Require"
                },
                    otherExplain: {
                        "inputType": "textarea",
                        "attributes":"disabled"
                    }
                },
                {
                    "inputWidth": 100,
                    "areaWidth": 280,
                    "areaHeight": 26
                }
            ];
        }
        $('#contactSealDetailList').ZTable({
            url: "<z:ukey key='com.zdsoft.finance.contract.conCaseContractSeal.contractSealDetailList' context='admin'/>&jsoncallBack=?&sealId=${contractSeal.id}",
            singleSelect: true,
            isRowNum: true,
            pagination: false,
            idField: "id",
            onEdit: true,
            tableCls: 'table-index',//table的样式
            columns: tableColumns,
            onDelete: function (index, rowData) {
                //  添加判断
                return true;
            },
            columnsType: tableColumnsType,
            toolbar: toolsBar
        });

        $("#sealDetailFileNameDialog").Zdialog({
            width: 500, height: 300, closed: true, title: "新增资料",
            buttons: [
                {
                    id: 'message-btn',
                    text: '确定',
                    buttonCls: 'btn-blue',
                    handler: function () {
                        //清除已有的记录
                        var j = 0;
                        var flag = true;
                        while (flag) {
                            if ($('#contactSealDetailList .datagrid-body').find("tr")[j]) {
                                $($('#contactSealDetailList .datagrid-body').find("tr")[j]).trigger("click");
                                //模拟点击选中事件
                                var selectedRowCol = $($('#contactSealDetailList .datagrid-body').find("tr")[j]).find(".selected");
                                //判断当前行是否未选中，如果未选中则需要选中后删除
                                if (selectedRowCol.length == 0) {
                                    $($('#contactSealDetailList .datagrid-body').find("tr")[j]).trigger("click");
                                }
                                $('#contactSealDetailList').ZTable("deleteRow");
                            } else {
                                flag = false;
                            }

                        }
                        //再重新增加新选择资料名称
                        var selRows = $("#sealDetailFileNameGrid").ZTable("getSelections");
                        for (var i = 0; i < selRows.length; i++) {
                            var sealDetailRow = {
                                materialCode: selRows[i].code,
                                materialName: selRows[i].name,
                                originalNum: "",
                                copyNum: "",
                                applySeal: "",
                                otherExplain: "",
                                id: ""
                            };
                            $("#contactSealDetailList").ZTable("addOneRow", sealDetailRow);
                        }
                        $("#sealDetailFileNameDialog").Zdialog("close");
                    }
                },
                {
                    id: 'message-btn',
                    text: '关闭',
                    buttonCls: 'btn-gray',
                    handler: function () {
                        $("#sealDetailFileNameDialog").Zdialog("close");
                    }
                }
            ]
        });

        $("#contractFileDialog").Zdialog({
            width: 500, height: 300, closed: true, title: "导入合同",
            buttons: [
                {
                    id: 'message-btn',
                    text: '打包导入',
                    buttonCls: 'btn-blue',
                    handler: function () {
                        var selRows = $("#contractFileGrid").ZTable("getSelections");
                        if (!selRows || selRows.length == 0) {
                            $.ZMessage.error("错误", "请选择文件名", function () {
                            });
                            return;
                        }
                        var selIds = "";
                        for (var i = 0; i < selRows.length; i++) {
                            selIds = selIds + selRows[i].ID + ",";
                        }
                        if (selIds.length != 0) {
                            selIds = selIds.substr(0, selIds.length - 1);
                        }
                        Loading.show();
                        $.post('<z:ukey key="com.zdsoft.finance.contract.conCaseContractSeal.saveImportContractFile" context="admin"/>&productId=${caseApply.productSubtypeId}&linkCode=${subStage}&caseApplyId=${caseApply.id}&businessId=${uuid}', {contractDetailIds: selIds}, function (data) {
                            Loading.hide();
                            data = eval("(" + data + ")");
                            if (data.resultStatus == 0) {
                                $.ZMessage.success("提示", data.msg, function () {
                                });
                                $("#contractFileDialog").Zdialog("close");
                                //刷新附件
                                $("#fileStore-zd-table").ZTable("reload");
                            } else {
                                $.ZMessage.error("错误", data.msg, function () {
                                });
                            }

                        });
                    }
                },
                {
                    id: 'message-btn',
                    text: '关闭',
                    buttonCls: 'btn-gray',
                    handler: function () {
                        $("#contractFileDialog").Zdialog("close");
                    }
                }
            ]
        });
        //盖章明细删除操作
        window.deleteContactHandle = function (el) {
            $.ZMessage.confirm("确认", "您确认要删除吗", function (r) {
                if (r == 'continue') {
                    //模拟点击选中事件
                    var selectedRowCol = $(el).parent().parent().find(".selected");
                    //判断当前行是否未选中，如果未选中则需要选中后删除
                    if (selectedRowCol.length == 0) {
                        $(el).parent().parent().trigger("click");
                    }
                    $('#contactSealDetailList').ZTable("deleteRow");
                }
            }, {
                'continue': {id: 'continue', text: '继续', buttonCls: 'btn-blue'},
                'cancel': {id: 'cancel', text: '取消', buttonCls: 'btn-gray'}
            });
        }
    });
</script>
