<%@ taglib prefix="z" uri="http://www.zdsoft.cn/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <%@ include file='../common/common_js.jsp' %>
    <%@ include file="../common/common_upload.jsp" %>
    <style type="text/css">
        .uploadify {
            float: left;
        }
    </style>
</head>
<body>
<!-- BEGIN CONTAINER -->
<div class="frm-content">
    <div class="page-box">
        <div class="page-title">诉讼资料配置</div>
        <div class="p5">
            <form action="#" id="searchForm" class="zui-form form-search" method="post"
                  enctype="multipart/form-data">
                <dl class="form-item">
                    <dt class="title">文件名</dt>
                    <dd class="detail">
                        <label>
                            <input type="text" class="zui-input" name="fileName"  id="fileName"/>
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
                 zdata-options="{'idField':'id','url':'<z:ukey key="com.zdsoft.finance.parameter.getLitigationData"  context="admin"/>&jsoncallback=?','singleSelect':true,'isRowNum':false,'pagination':true,'tableCls': 'table-index','toolbar':'#btn-applylist'}">
                <table>
                    <thead>
                    <tr>
                        <th data-options="field:fileTypeCodeName,width:10%">文件类型</th>
                        <th data-options="field:fileName,width:20%">文件名</th>
                        <th data-options="field:filePath,width:30%">文件路径</th>
                        <th data-options="field:remark,width:30%">备注</th>
                        <th data-options="width:15%" formatter="operate">操作</th>

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
    <form id="dialogForm" class="zui-form" enctype="multipart/form-data" style="margin-top: 20px;">
        <input type="hidden" id="id" name="id"/>
        <dl class="form-item">
            <dt class="title"><b class="c-red mr5">*</b>文件类型</dt>
            <dd class="detail">
                   <input id='fileTypeCodeAdd' class="zui-combobox zui-validatebox" type="hidden"
                          data-toggle="combobox"
                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00121"
                          data-valuefield="id" data-textfield="text"
                          name="fileTypeCode"
                          validate-type="Require" validate-false="请选择文件类型">
            </dd>
        </dl>
        <dl class="form-item">
            <dt class="title"><b class="c-red mr5">*</b>文件名</dt>
            <dd class="detail">
                <label>
                    <input type="text" class="zui-input zui-validatebox" id="fileNameAdd" name="fileName"
                           validate-type="Require,Length[1-25]" validate-false="文件名不能为空"/>
                </label>
            </dd>
        </dl>
        <dl class="form-item block">
            <dt class="title"><b class="c-red mr5">*</b>文件路径:</dt>
            <dd class="detail">
                <label>
                    <input type="text" class="zui-input zui-validatebox" id="filePath" readonly="true" style="width: 420px"
                           validate-type="Require" validate-false="请选择文件"/>
                    <input type="hidden" id="attachmentId" name="attachmentId">
                </label>
            </dd>
            <dd class="detail" style="width: 80px;">
                <label>
                    <input id="fileUpload" name="fileUpload" type="file" value="添加附件" validate-type="Require" validate-false="文件名不能为空" />
                </label>
            </dd>
        </dl>
        <div class="uploadify-queue" id="queue-item" style="margin-left: 105px">
        </div>
        <div>
            <dl class="form-item">
                <dt class="title">备注</dt>
                <dd class="detail">
                    <label>
                            <textarea id="remarkAdd" class="zui-area zui-validatebox" validate-type="Length[0-250]"
                                      name="remark"
                                      validate-false="备注不能超过250个字"></textarea>
                    </label>
                </dd>
            </dl>
        </div>
    </form>
</div>

<script type="text/javascript">


    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter', 'zd/jquery.zds.combotree', 'zd/jquery.zds.checkbox']
            , function ($, CALLBACK, COMBOBOX, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
                Date.prototype.Format = function (fmt) { //author: meizz
                    var o = {
                        "M+": this.getMonth() + 1, //月份
                        "d+": this.getDate(), //日
                        "h+": this.getHours(), //小时
                        "m+": this.getMinutes(), //分
                        "s+": this.getSeconds(), //秒
                        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                        "S": this.getMilliseconds() //毫秒
                    };
                    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
                    for (var k in o)
                        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                    return fmt;
                }
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
                                    if ($("#form_name").val() == "null") {
                                        $.ZMessage.info("提示", "无效的文件名称", function () {

                                        });
                                        return;
                                    }
                                    var param = $("#dialogForm").serializeArray();
                                    $.ajax({
                                        method: "post",
                                        dataType: "json",
                                        data: param,
                                        url: '<z:ukey key="com.zdsoft.finance.parameter.updateLitigationData"  context="admin"/>&jsoncallback=?',
                                        success: function (result) {
                                            if (result.resultStatus == 0) {
                                                $.ZMessage.info("提示", "操作成功", function () {
                                                    var param = $("#searchForm").serializeArray();
                                                    $('#zd-table').ZTable("reload", param);
                                                });
                                            }
                                            else {
                                                $.ZMessage.error("错误", "操作失败："+result.msg, function () {
                                                });
                                            }
                                        }

                                    });
                                    $("#dialogEdit").Zdialog("close");
                                }
                            }
                        },
                        {
                            id: 'message-btn', text: '取消', buttonCls: 'btn-gray',
                            handler: function () {
                                //todo something
                                $("#dialogEdit").Zdialog("close");
                            }
                        }],
                });
                //日期转换函数
                CALLBACK.changeDate = function (index, v) {
                    var date = new Date(v);
                    return date.Format('yyyy-MM-dd hh:mm');
                };
                //状态转换函数
                CALLBACK.changeStatus = function (index, v) {
                    switch (v) {
                        case 1:
                            return "启用";
                            break;
                        case  0:
                            return "停用";
                            break;
                    }

                };
                //操作栏的回调函数
                CALLBACK.operate = function () {
                    var html = '<a href="javaScript:void(0)" onclick="doEdit"><button class="btn-blue">编辑</button></a>' + '&nbsp;&nbsp;' +
                            '<a href="javaScript:void(0)" onclick="doDelete"><button class="btn-blue">删除</button></a>'
                    return html;

                }
                //新增行
                CALLBACK.add = function () {
                    $("#id").val("");
                    $("#dialogEdit").Zdialog("setTitle", "新增");
                    $("#dialogEdit").Zdialog("open");

                }
                //编辑行
                CALLBACK.doEdit = function (num, selRow) {
                    $("#id").val(selRow.id);
                    $("#fileTypeCodeAdd").ZCombobox("setValue", selRow.fileTypeCode);
                    $("#fileNameAdd").val(selRow.fileName);
                    $("#filePath").val(selRow.filePath);
                    $("#remarkAdd").val(selRow.remark);

                    $("#dialogEdit").Zdialog("setTitle", "编辑");
                    $("#dialogEdit").Zdialog("open");
                }
                //删除行
                CALLBACK.doDelete = function (num, selRow) {
                    $.ZMessage.question("确认", "确认删除该资料？", function (r) {
                        if (selRow) {
                            var param = {};
                            param.id = selRow.id;
                            $.ajax({
                                method: "post",
                                dataType: "json",
                                data: param,
                                url: '<z:ukey key="com.zdsoft.finance.parameter.deleteLitigationData"  context="admin"/>&jsoncallback=?',
                                success: function (result) {
                                    if (result.resultStatus == 0) {
                                        $.ZMessage.info("提示", result.msg, function () {
                                        });
                                    }
                                    else {
                                        $.ZMessage.error("错误", result.msg, function () {
                                        });
                                    }
                                    var param = $("#searchForm").serializeArray();
                                    $('#zd-table').ZTable("reload", param);
                                }

                            });
                        }

                    });
                }

                //下拉框初始化
                $("#fileTypeCodeAdd").ZCombobox();

                $.ZUI.init();

                $(function () {
                    window.initUpload();
                });
                //查询
                $("#searchButton").click(function () {
                    var formArray = $("#searchForm").serializeArray();
                    $('#zd-table').ZTable("reload", formArray);
                });
                //重置
                $("#resetButton").click(function () {
                    $("#fileName").val("");
                });

                //$("#testDiv").load('<z:ukey key="com.zdsoft.finance.fileStore.fileShowListPage" context="admin"/>&productId=4028a11b5a1cd677015a1ce4ce84000c&linkCode=P002&caseApplyCode=c123456&businessId=c456789');
            });

    function initUpload() {
        // 上传相关js
        var upload_url = '<z:ukey key="public.ess.upload" context="admin"/>';
        $('#fileUpload').uploadify({
            'multi': false,
            'swf': '<%=resServerUpload %>/assets/js/zd/uploadify.swf',
            'uploader': upload_url,
            'buttonText': '上传附件',
            'width': '80',
            'height': '22',
            'queueID': 'queue-item',
            'debug': false,
            'uploadLimit': '3',
            'onUploadSuccess': function (file, data, response) {
                var allData = JSON.parse(data);
                var fileName = file.name;
                fileName = fileName.substring(0, fileName.lastIndexOf("."));
                //$("#fileNameAdd").val(fileName);
                $("#attachmentId").val(allData[0].attachements.id);
                $("#filePath").val(allData[0].attachements.filePath);
            }
        });
    }
</script>
</body>
</html>