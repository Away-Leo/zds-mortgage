<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8" %>
    <%@ include file='../common/common_js.jsp' %>
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>机构合同报备详情</title>
</head>
<body>
<div class="page-box">
    <div class="p10">
        <form id="agency_contract_form" class="zui-form " method="post" enctype="multipart/form-data">
            <div class="page-title">
                <h1 class="page-title">基本信息</h1>
            </div>
            <div class="page-box">
                <div class="p5">
                    <table class="table-detail">
                        <tr>
                            <td class="td-title pct10">标题</td>
                            <td class="pct20" colspan="3">
                                <input style="width:100%" class="zui-input zui-validatebox" disabled="disabled"
                                       name="applyTitle" value="${vo.applyTitle}">
                            </td>
                            <td class="td-title pct10">编号</td>
                            <td class="pct20">
                                <input type="zui-input zui-validatebox" disabled="disabled" value="${vo.applyNo}"/>
                                <input type="hidden" name="applyNo" value="${vo.applyNo}"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="td-title pct10">申请人</td>
                            <td class="pct20">
                                <input class="zui-input zui-validatebox" disabled="disabled" value="${emp.empNm}">
                                <input type="hidden" name="applyEmpCode" value="${emp.empCd}"/>
                            </td>
                            <td class="td-title pct10">申请人部门</td>
                            <td class="pct20">
                                <input class="zui-input zui-validatebox" disabled="disabled"
                                       value="${emp.departmentName}">
                                <input type="hidden" name="applyDepartment" value="${emp.departmentCd}"/>
                            </td>
                            <td class="td-title pct10">申请时间</td>
                            <td class="pct20">
                                <label>
                                    <input type="zui-input zui-validatebox" disabled="disabled"
                                           value="${vo.applyDateStr}"/>
                                    <input type="hidden" name="applyDate" value="${vo.applyDate}"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td class="td-title pct10">机构</td>
                            <td class="pct20">
                                <input class="zui-input zui-validatebox" disabled="disabled" value="${emp.companyNm}">
                                <input type="hidden" name="applyOrgCode" value="${emp.companyCd}"/>
                            </td>
                            <td class="td-title pct10">申请类别</td>
                            <td class="pct20">
                                <dl class="form-item form-auto">
                                    <dd class="detail">
                                        <label>
                                            <input class="zui-combobox zui-validatebox" id="applyType" name="applyType"
                                                   type="hidden" value="${vo.applyType }"
                                                   data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM0066"
                                                   data-valuefield="fullcode"
                                                   data-textfield="name">
                                        </label>
                                    </dd>
                                </dl>
                            </td>
                            <td class="td-title pct10"></td>
                            <td class="pct20"></td>
                        </tr>
                    </table>
                </div>
            </div>

            <div class="page-title">
                <h1 class="page-title">合同信息</h1>
            </div>
            <div class="page-box">
                <div class="p10">

                    <div id="tb-plan" class="zui-datagrid"
                         zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.contract.agencyContractSubList" context="admin"/>&jsoncallback=?&tpl|orgCantractApplyId|E|S=${vo.id}","singleSelect":true,"pagination":true,"idField":"id","toolbar":"#btn-function","tableCls":"table-index"}'>
                        <table>
                            <tr>
                                <th data-options="field:capitalNm">资方</th>
                                <th data-options="field:capitalistTypeNm">资方类别</th>
                                <th data-options="field:contractTypeNm">合同类型</th>
                                <th data-options="field:contractName">合同名称</th>
                                <th data-options="field:attachName">附件</th>
                                <th data-options="field:id" formatter="contactFormat">操作</th>
                            </tr>
                        </table>
                    </div>
                    <div id="btn-function">
                        <a class="zui-toolbar" id="btn-add" text="全部下载" iconCls="icon-add" buttonCls="btn-blue"
                           handler="doAllDownload"></a>
                    </div>
                </div>
                <div class="p5">
                    <table class="table-detail">
                        <tr>
                            <td class="td-title">备注</td>
                            <td colspan="7">
                                <label>
                                    <textarea class="zui-area zui-validatebox row-width" disabled="disabled" placeholder="最多可以输入200个字符"
                                              validate-type="Require,Length[0-200]" id="applyReason" name="applyReason"
                                    >${vo.applyReason }</textarea>
                                </label>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <input type="hidden" name="id" id="agencyContractId" value="${vo.id }">
        </form>
    </div>
</div>
<div id="contactsDialog" style="display: none">
    <script type="text/javascript">
        seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox',
            'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message',
            'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'
        ], function ($, CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {

            //操作
            CALLBACK.contactFormat = function (index, row) {
                var html = '<a title="查看"  onclick="doView" class="btn-blue">查看</a>';
                html += '&nbsp;&nbsp;<a title="打印"  onclick="doPrint" class="btn-blue">打印</a>';
                return html;
            };

            //编辑弹出框打开
            CALLBACK.doView = function (index, row) {
                var agencyContractId = $("#agencyContractId").val();
                var id = row.id;
                var url = '<z:ukey key="com.zdsoft.finance.contract.editSubAgencyContract" context="admin"/>&id=' + id + '&agencyContractId=' + agencyContractId + '&operationType=view';
                $('#contactsDialog').load(url, function () {
                });
            };

            //禁用
            $("#applyType").data("choose", "disable");
            ;

            //全部下载
            CALLBACK.doAllDownload = function (index, rowData) {
                //获取选中行
                var selections = $("#tb-plan").ZTable("getRows");
                var attachmentIds = "";
                if (selections.length == 0) {
                    $.ZMessage.info("提示", "当前为合同数为0，不能下载！", function () {
                    });
                    return false;
                }
                //循环选中行 得到附件ID
                for (var i = 0; i < selections.length; i++) {
                    attachmentIds += selections[i].attachmentId + ",";
                }
                attachmentIds = attachmentIds.substr(0, attachmentIds.length - 1);
                console.log(attachmentIds);
                var sysDownLoadUrl = '<z:ukey key="public.upload.downLoadZipFile" context="admin"/>&jsoncallback=?&zipName=contract&attachmentIds=' + attachmentIds;
                window.location.href = sysDownLoadUrl;
            };

            $.ZUI.init();
        });
    </script>
</body>
</html>