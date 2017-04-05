<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z" %>
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
                                <input style="width:100%" class="zui-input zui-validatebox" validate-type="Require"
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
                                           value="${vo.applyDate}"/>
                                    <input type="hidden" name="applyDate" value="${vo.applyDate}"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td class="td-title pct10">机构</td>
                            <td class="pct20">
                                <input class="zui-input zui-validatebox" disabled="disabled" value="${emp.orgNm}">
                                <input type="hidden" name="applyOrgCode" value="${emp.orgCd}"/>
                            </td>
                            <td class="td-title pct10">申请类别</td>
                            <td class="pct20">
                                <dl class="form-item form-auto">
                                    <dd class="detail">
                                        <label>
                                            <input class="zui-combobox zui-validatebox" id="applyType" name="applyType"
                                                   validate-type="Require" type="hidden" value="${vo.applyType }"
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
                                <th data-options="field:attachFile">文件类型</th>
                                <th data-options="field:attachName">附件</th>
                                <th data-options="field:id" formatter="contactFormat">操作</th>
                            </tr>
                        </table>
                    </div>
                    <div id="btn-function">
                        <a class="zui-toolbar" id="btn-add" text="新增" iconCls="icon-add" buttonCls="btn-blue"
                           handler="doAdd"></a>
                    </div>
                </div>
                <div class="p5">
                    <table class="table-detail">
                        <tr>
                            <td class="td-title">备注</td>
                            <td colspan="7">
                                <label>
                                    <textarea class="zui-area zui-validatebox row-width"
                                              placeholder="最多可以输入200个字符"
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
        <div class="form-btn">
            <button id="btn-save" class="btn-blue mr10" type="button">保存</button>
            <button id="btn-sub" class="btn-blue mr10" type="button">提交</button>
        </div>
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
                var html = '<a title="编辑" onclick="doEdit" class="btn-blue">编辑</a>';
                html += '&nbsp;&nbsp;<a title="删除" onclick="doDel" class="btn-blue">删除</a>';
                return html;
            };

            //查询
            $('#btn-search').click(function () {
                var formArray = $("#search_from").serializeArray();
                $('#projectTable').ZTable("reload", formArray);
            });
            //重置
            $("#btn-reset").click(function () {
                $("#search_from")[0].reset();
            });

            //新增弹出框打开
            CALLBACK.doAdd = function (index, row) {
                var agencyContractId = $("#agencyContractId").val();
                if (agencyContractId == null || agencyContractId == "") {
                    $.ZMessage.error("错误", "请先保存机构报备申请信息", function () {
                    });
                } else {
                    var url = '<z:ukey key="com.zdsoft.finance.contract.editSubAgencyContract" context="admin"/>&agencyContractId=' + agencyContractId;
                    $('#contactsDialog').load(url, function () {
                    });
                }
            };
            //编辑弹出框打开
            CALLBACK.doEdit = function (index, row) {
                var agencyContractId = $("#agencyContractId").val();
                var id = row.id;
                var url = '<z:ukey key="com.zdsoft.finance.contract.editSubAgencyContract" context="admin"/>&id=' + id + '&agencyContractId=' + agencyContractId;
                $('#contactsDialog').load(url, function () {
                });
            };

            //删除
            CALLBACK.doDel = function (index, row) {
                $.ZMessage.question("提示", "是否删除", function (index) {
                    $.ajax({
                        type: 'post',
                        data: {id: row.id},
                        url: '<z:ukey key="com.zdsoft.finance.contract.delSubAgencyContract" context="admin"/>',
                        dataType: 'json',
                        success: function (data) {
                            if (data.resultStatus == 0) {
                                $.ZMessage.info("提示", "删除成功", function () {
                                    $("#tb-plan").ZTable("reload");
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
                });
            };

            //保存
            $("#btn-save").click(function () {
                save(0);
            });

            //提交
            $("#btn-sub").click(function () {
                save(1);
            });

            function save(isSubmit) {
                var flag = $('#agency_contract_form').ZDSValidatebox('validateAll', $('#agency_contract_form'));
                if (flag) {
                    var params = $("#agency_contract_form").serialize();
                    $.ajax({
                        type: 'post',
                        url: '<z:ukey key="com.zdsoft.finance.contract.saveAgencyContract" context="admin"/>&isSubmit=' + isSubmit,
                        data: params,
                        dataType: 'json',
                        success: function (data) {
                            if (data.resultStatus == 0) {
                                $.ZMessage.success("提示", data.msg, function () {
                                    //刷新父页面
                                    ZDS_MESSAGE_CLIENT.refreshOpenner();
                                    //关闭本页面
                                    ZDS_MESSAGE_CLIENT.closeSelf();
                                });
                            } else {
                                $.ZMessage.error("错误", data.msg, function () {
                                });
                            }
                        },
                        error: function () {
                            $.ZMessage.error("错误", "机构合同报备保存系统异常，请联系管理员", function () {
                            });
                        }
                    });
                }
            }

            $.ZUI.init();

            //保存
            ZDS_WORKFLOW_CLIENT.saveFunction = function (datas) {
                //校验
                var flag = $('#agency_contract_form').ZDSValidatebox('validateAll', $('#agency_contract_form'));
                if (flag) {
                    var params = $("#agency_contract_form").serialize();
                    var args = JSON.parse(datas.args);
                    params += '&processInstanceId=' + args.processInstanceId;
                    params += '&taskInstanceId=' + args.taskInstanceId;
                    params += '&taskId=' + args.taskId;
                    params += '&taskName=' + args.taskName;
                    params += '&jobAppCd=' + args.jobAppCd;
                    $.ajax({
                        url: '<z:ukey key="com.zdsoft.finance.afterloan.afterSupervise.saveOrUpdateOrgTask" context="admin"/>&jsoncallback=?',
                        data: params,
                        type: "post",
                        dataType: "json",
                        success: function (rdata) {
                            var msg = "";
                            if (rdata.resultStatus == 0) {
                                //执行回调函数
                                ZDS_WORKFLOW_CLIENT.callBackFuntion(datas, ZDS_WORKFLOW_PARAM._STATUS_SUCCESS, rdata.msg);
                            } else {
                                //执行回调函数
                                ZDS_WORKFLOW_CLIENT.callBackFuntion(datas, ZDS_WORKFLOW_PARAM._STATUS_ERROR, rdata.msg);
                            }
                        }
                    });
                }
                //---------end------流程中有修改页面，需要提交业务数据操作------------------
            };
            //提交方法
            ZDS_WORKFLOW_CLIENT.submitFunction = ZDS_WORKFLOW_CLIENT.saveFunction;

        });
    </script>