<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file='../common/common_js.jsp' %>
    <title>新增模版</title>
</head>
<body>
<div class="save">
    <button id="btn-save" class="btn-blue mr10">保存</button>
    <button id="btn-cancel" class="btn-gray mr10">取消</button>
</div>
<div class="page-box">
    <div class="p10">
        <form id="afterCheck_form" class="zui-form " method="post"
              enctype="multipart/form-data">
            <input type="hidden" id="id" name="id"
                   value="${bussPrintTplSetVo.id }">

            <div class="page-title">
                <h1 class="page-title">基本信息</h1>
            </div>
            <div class="page-box">
                <div class="p5">
                    <table class="table-detail">
                        <tr>
                            <td class="td-title pct10"><font class="c-red">*</font>模版名称</td>
                            <td class="pct20"><label> <input
                                    class="zui-input zui-validatebox" validate-type="Require,Length[1-32]"
                                    name="templatename" value="${bussPrintTplSetVo.templatename}">
                            </label></td>
                            <td class="td-title pct10"><font class="c-red">*</font>所属机构</td>
                            <td class="pct20">
                                <dl class="form-item">
                                    <dd class="detail">
                                        <input
                                                class="zui-combotree zui-validatebox" type="hidden"
                                                data-multiple="false" name="beloworgcode" id="beloworgcode"
                                                data-defaultvalue="" validate-type="Require"
                                                data-url="<z:res resource='enssential.org.findOrgToTree' isDefault='true'/>&jsoncallback=?&type=dept"
                                                data-valuefield="id" data-textfield="text"
                                                value="${bussPrintTplSetVo.beloworgcode}" />
                                                
                                                
                                    </dd>
                                </dl>
                            </td>

                            <td class="td-title pct15"><font class="c-red">*</font>适用产品分类</td>
                            <td class="pct30">
                                <dl class="form-item">
                                    <dd class="detail">
                                        <label>
                                            <input class="zui-input zui-validatebox" id="productName"
                                                   validate-type="Require" name="productName"
                                                   value="${bussPrintTplSetVo.productNames}">

                                            <input type="hidden" id="productids" name="productids"
                                                   value="${bussPrintTplSetVo.productids}" style="width:100px;">

                                            <button id="selectProduct" type="button" class="btn-blue">选择产品</button>
                                        </label>
                                    </dd>
                                </dl>
                            </td>

                        </tr>

                        <tr>
                            <td class="td-title pct10"><font class="c-red">*</font>资金来源</td>
                            <td class="pct20">
                                <dl class="form-item">
                                    <dd class="detail">
                                        <input class="zui-combobox" type="hidden"
                                               name="fundsource" id="fundsource" validate-type="Require"
                                               data-url="<z:ukey key='com.zdsoft.finance.cooperator.capitalist.capitalistSimpleCode' context='admin'/>&jsoncallback=?"
                                               data-defaultvalue="" data-valuefield="id"
                                               data-textfield="capitalName"
                                               value="${bussPrintTplSetVo.fundsource}">
                                    </dd>
                                </dl>
                            </td>
                            <td class="td-title pct10"><font class="c-red">*</font>可用机构</td>
                            <td class="pct20">
                                <dl class="form-item">
                                    <dd class="detail">
                                        <input
                                                class="zui-combotree zui-validatebox" type="hidden"
                                                data-parent-value="false" name="orglist" id="orglist"
                                                data-multiple="true" data-defaultvalue="" validate-type="Require"
                                                data-url="<z:res resource='enssential.org.findOrgToTree' isDefault='true'/>&jsoncallback=?&type=dept"
                                                data-valuefield="id" data-textfield="text"
                                                value="${bussPrintTplSetVo.orglist}">
                                    </dd>
                                </dl>
                            </td>
                            <td class="td-title pct10"><font class="c-red">*</font>是否停用</td>
                            <td class="pct20"><input class="zui-checkbox" id="isdisable" name="isdisable"
                                                     data-multiple="false" data-defaultvalue="1"
                                                     data-data="[{'id':'0','text':'是'},{'id':'1','text':'否'}]"
                                                     data-valuefield="id" data-textfield="text" type="hidden"
                                                     value="${bussPrintTplSetVo.isdisable}"/></td>
                        </tr>
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
<div id="pageform">
    <div class="page-title">
        <h1 class="page-title">新增页信息</h1>
    </div>
    <div class="page-box">
        <div class="p10">

            <div id="btn-function">

                <a class="zui-toolbar" id="btn-add" text="新增页" iconCls="icon-add" buttonCls="btn-blue"
                   handler="add"></a>
            </div>
            <div id="tb-plan" class="zui-datagrid"
                 zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.contract.contractPrintTplSet_PageList" context="admin"/>&jsoncallback=?&printTemplateId=${bussPrintTplSetVo.id}","singleSelect":true,"pagination":true,"idField":"id","toolbar":"#btn-function","tableCls":"table-index"}'>
                <table>
                    <tr>
                        <th data-options="field:pagename">页名</th>
                        <th data-options="field:pagenum">页码</th>
                        <th data-options="field:creditAttachment">页地址</th>
                        <th data-options="field:leftmargin">左边距</th>
                        <th data-options="field:topmargin">上边距</th>
                        <th data-options="field:id" formatter="operate">操作</th>
                    </tr>
                </table>
            </div>

        </div>


    </div>
</div>

<div id="dialogAddProject" style="display: none">

</div>

<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback',
            'zd/jquery.zds.form', 'zd/jquery.zds.message',
            'zd/jquery.zds.combobox',
            'zd/jquery.zds.table', 'zd/jquery.zds.seleter',
            'zd/jquery.zds.dialog','zd/jquery.zds.combotree'],
        function ($, CALLBACK) {
            var url = '<z:ukey key="com.zdsoft.finance.contract.ContractPrintTplPage_edit" context="admin"/>&jsoncallback=?';

            CALLBACK.add = function () {

                if ($("#id").val().length == 0) {
                    $.ZMessage.error("警告", "请先保存模版",
                        function () {
                            return false;
                        });

                    return;
                }

                ZDS_MESSAGE_CLIENT.openMenuLink('addPage', '添加页', url
                    + "&openMethod=tabs&printTemplateId=" + $("#id").val());

            }
            CALLBACK.operate = function (row, value) {
                var html = "<a title='编辑'  onclick='edit'><button class='btn-blue'><font><font>编辑</font></font></button></a>";
                html += "<a title='删除' class='ml10' onclick='deleteRow'><button class='btn-blue'><font><font>删除</font></font></button></a>";
                return html;
            };
            CALLBACK.edit = function (index, row) {
                ZDS_MESSAGE_CLIENT.openMenuLink('edit', '编辑页',
                    url + "&openMethod=tabs&id=" + row.id);
            }
            CALLBACK.deleteRow = function (index, row) {

                var id = row.id;

                var deleteUrl = "<z:ukey key='com.zdsoft.finance.contract.contractPrintTplPageDelete' context='admin' />";

                $.ZMessage.question("确认", "确认删除？", function () {
                    $.get(deleteUrl, {id: id}, function (result) {

                        if (result.resultStatus == 0) {
                            $.ZMessage.info("成功", "数据删除成功", function () {
                                setTimeout(function () {
                                    var param = $("#afterCheck_form").serializeArray();
                                    $("#tb-plan").ZTable("reload", param);
                                });
                            })
                        } else {
                            $.ZMessage.error("错误", "删除数据错误" + result.msg,
                                function () {
                                    return false;
                                });
                        }
                    });
                });

            }


            // 人员选择器
            $("#selectProduct").Zseleter({
                title: '选择器',
                btnId: "select",
                width: 700,
                height: 400,
                key: "id",
                value: "productName",
                singleSelect: false,
                defaultValue: function () {
                    return $("#productids").val();
                },
                defaultText: function () {
                    return $("#productName").val();
                },
                columns: {
                    test: [[
                        {field: 'productName', title: '产品名称', width: 100},
                        {
                            field: 'isValid', title: '启用状态', width: 60, formatter: function (r, v) {
                            if (r.isValid) {
                                return '是';
                            } else {
                                return '否';
                            }
                        }
                        }
                    ]]
                },
                url: "<z:ukey key='com.zdsoft.finance.product.getList' context='admin'/>",
                type: 'test',
                defSearchForm: {
                    test: [
                        {
                            label: "产品分类",
                            type: "combobox",
                            valueField: 'id',
                            textField: 'name',
                            url: '<z:ukey key="com.zdsoft.finance.product.findCategorySimpleCode" context="admin"/>&jsoncallback=?',
                            name: "categoryVo.id"
                        },
                        {
                            label: "产品名称",
                            type: "input",
                            name: "productName"
                        }
                    ]
                },
                onOk: function (data) {
                    var ids = new Array();
                    var names = new Array();
                    $.each(data, function (i, e) {
                        ids.push(e.id);
                        names.push(e.productName);
                    });

                    $("#productName").val(names.toString());
                    $("#productids").val(ids.toString());

                }

            });

            //查看对话框
            $("#dialogAddProject").Zdialog(
                {
                    width: 700,
                    height: 400,
                    closed: true,
                    title: "详情",
                    buttons: [{
                        id: 'message-btn',
                        text: '关闭',
                        buttonCls: 'btn-blue',
                        handler: function () {

                            $("#dialogAddProject").Zdialog("close");
                        }
                    }]
                });

            //保存检查信息
            $('#btn-save').click(
                function () {
                    //检查信息
                    var isValidateAfterCheck = $.ZUI.validateForm($('#afterCheck_form'));
                    //校验
                    if (!isValidateAfterCheck) {
                        $.ZMessage.info("提示", "请完善必填项信息！", function () {
                        });
                        return false;
                    }
                    //获取检查信息
                    var params = $('#afterCheck_form').serialize();
                    //保存
                    $.ajax({
                        type: 'post',
                        url: '<z:ukey key="com.zdsoft.finance.contract.contractPrintTplSetSave" context="admin"/>',
                        data: params,
                        dataType: 'json',
                        success: function (data) {

                            if (data.resultStatus == 0) {
                                $.ZMessage.success(
                                    "成功",
                                    data.msg,
                                    function () {

                                        setTimeout(function () {
                                            window.location.href = window.location.href + "&ContractPrintTplSet_id=" + data.optional.afterCheck.id;
                                        }, 200);
                                    });
                            } else {
                                $.ZMessage.error("错误",
                                    data.msg,
                                    function () {
                                    });
                            }
                        },
                        error: function () {
                            $.ZMessage
                                .error(
                                    "错误",
                                    "保存信息系统异常，请联系管理员",
                                    function () {
                                    });
                        }
                    });
                });


            ZDS_MESSAGE_CLIENT.refreshThis = function () {
                $("#tb-plan").ZTable("reload");
            };


            $.ZUI.init();

            if ($("#id").val().length == 0) {
                $("#pageform").hide();
            }
        });


</script>
</body>
</html>