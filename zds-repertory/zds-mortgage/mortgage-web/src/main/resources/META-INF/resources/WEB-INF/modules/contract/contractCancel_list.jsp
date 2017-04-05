<%@ taglib prefix="z" uri="http://www.zdsoft.cn/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file='../common/common_js.jsp'%>
    <title>案件合同作废</title>
</head>
<body>
<div class="frm-content">
    <!-- 查询区域 -->
    <div class="page-box">
        <div class="p10">
            <form id="search_from" class="zui-form form-search" method="post"
                  enctype="multipart/form-data">
                <dl class="form-item">
                    <dt class="title">机构名称：</dt>
                    <dd class="detail">
                        <input class="zui-combotree zui-validatebox" type="hidden" name="contractTemp|orgcd|E|S" id="orgId" data-multiple="false"
                               data-url="<z:res resource='enssential.org.findOrgToTree' isDefault='true'/>&jsoncallback=?&type=dept"
                               data-valuefield="id" data-textfield="text" value="${curOrgId}" checkStatus="true">
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">合同名称：</dt>
                    <dd class="detail">
                        <input class="zui-input" id="payFrom" name="contractTemp|contractName|LK|S">
                    </dd>
                </dl>

                <dl class="form-item">
                    <dt class="title">合同类型：</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-combobox zui-validatebox" id="contractType" name="contractTemp|contractType|E|S" type="hidden"
                                   data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM0066"
                                   data-valuefield="fullcode" data-textfield="name" >
                        </label>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">资方：</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-combobox zui-validatebox" id="capitalId" name="contractTemp|capitaId|E|S" type="hidden" value="${vo.capitalId }"
                                   data-url="<z:ukey key="com.zdsoft.finance.contract.findAllCapitalist" context="admin"/>&capitalistType=${vo.capitalistType}"
                                   data-valuefield="fullcode"
                                   data-textfield="name" validate-type="Require">
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

    <!-- 列表 -->
    <div class="page-box">
        <div class="p10">
            <div id="tb-seal" class="zui-datagrid"
                 zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.contract.contractCancel.getContractCancelList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index"}'>
                <table>
                    <tr>
                        <th data-options="field:CAPITANAME">资方</th>
                        <th data-options="field:CONTRACTTYPENAME">合同类型</th>
                        <th data-options="field:CONTRACTNAME">合同名称</th>
                        <th data-options="field:APPLYCOUNT">已申(领份)</th>
                        <th data-options="field:USECOUNT">已使用(份)</th>
                        <th data-options="field:CANCELCOUNT">已作废(份)</th>
                        <th data-options="field:id,width:200" formatter="operate">操作</th>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<div id="contactCancelDialog"  style="display: none">
    <div class="p10">
    <form id="contactCancelForm" class="zui-form" action="javascript:void(0);"
          zdata-options="{'idField':'id','url':'<z:ukey key="com.zdsoft.finance.contract.contractCancel.procContractCancel"  context="admin"/>'}">
        <dl class="form-item">
            <dt class="title"><label style="color: red;">*</label>文件编码：</dt>
            <dd class="detail">
                <label>
                    <input class="zui-input zui-validatebox" type="text" id="fileNo" name="fileNo"
                           validate-type="Require,Length[1-10]"/>
                    <input type="hidden" id="capitaIdC" name="capitaIdC"/>
                    <input type="hidden" id="contractTypeC" name="contractTypeC"/>
                    <input type="hidden" id="contractNameC" name="contractNameC"/>
                </label>
            </dd>
        </dl>
    </form>
    </div>
</div>
<script type="text/javascript">
    var curOrgId = "${curOrgId}";
    seajs
        .use(
            [ 'jquery', 'zd/jquery.zds.page.callback',
                'zd/jquery.zds.form', 'zd/jquery.zds.message',
                'zd/jquery.zds.dialog',
                'zd/jquery.zds.combobox',
                'zd/jquery.zds.table', 'zd/jquery.zds.seleter' ],
            function($, CALLBACK, ZUI_MESSAGE_CLIENT) {
                //操作
                CALLBACK.operate = function(row, value) {
                    var html = "<a title='补充'  onclick='contractCancel'><button class='btn-blue'><font><font>作废</font></font></button></a>";
                    return html;
                };
                //查询
                $('#btn-search').click(
                    function() {
                        var formArray = $("#search_from")
                            .serializeArray();
                        $('#tb-seal').ZTable("reload",
                            formArray);
                    });
                //重置
                $("#btn-reset").click(function() {
                    $.ZUI.resetForms('#search_from');
                    $("#orgId").ZComboTree("setValue",curOrgId);
                    $('#tb-seal').ZTable("reload", {});
                });
                $("#contactCancelDialog").Zdialog({
                    width: 380, height: 200, closed: true, title: "合同作废",
                    buttons: [
                        {
                            id: 'message-btn',
                            text: '确认',
                            buttonCls: 'btn-blue',
                            handler: function () {
                                var finalResult = $.ZUI.validateForm($('#contactCancelForm'));
                                if(finalResult){
                                    var formArray = $("#contactCancelForm").serializeArray();
                                    $.post('<z:ukey key="com.zdsoft.finance.contract.contractCancel.procContractCancel"  context="admin"/>',formArray,function(data){
                                        var retData = eval("("+data+")");
                                        if(retData){
                                            if(retData.errored){
                                                $.ZMessage.error("错误", retData.msg, function () {
                                                });
                                            }else{
                                                $.ZMessage.success("成功", retData.msg, function () {
                                                    $("#contactCancelDialog").Zdialog("close");
                                                    var formArray = $("#search_from")
                                                        .serializeArray();
                                                    $('#tb-seal').ZTable("reload",
                                                        formArray);
                                                });
                                            }
                                        }else{
                                            $.ZMessage.error("错误", "服务器连接异常，请重试!", function () {
                                            });
                                        }
                                    });
                                    $("#contactCancelForm").submit();
                                }
                            }
                        },
                        {
                            id: 'message-btn',
                            text: '取消',
                            buttonCls: 'btn-gray',
                            handler: function () {
                                $("#contactCancelDialog").Zdialog("close");
                            }
                        }
                    ]
                });

                CALLBACK.contractCancel =function(index,data){
                    $.ZUI.resetForms('#contactCancelForm');
                    $("#capitaIdC").val(data.CAPITAID);
                    $("#contractTypeC").val(data.CONTRACTTYPE);
                    $("#contractNameC").val(data.CONTRACTNAME);
                    $("#contactCancelDialog").Zdialog("open");
                };

                $.ZUI.init();
                //初始化表单
                $.ZUI.initForms('#contactCancelForm');
            });
</script>
</body>
</html>