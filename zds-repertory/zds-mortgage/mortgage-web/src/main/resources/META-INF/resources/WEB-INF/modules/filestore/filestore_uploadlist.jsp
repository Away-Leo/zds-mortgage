<%@ taglib prefix="z" uri="http://www.zdsoft.cn/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加附件</title>
    <%@ include file='../common/common_js.jsp' %>
    <%@ include file="../common/common_upload.jsp" %>
</head>
<body>
<div class="save">
    <button id="btn-save" class="btn-blue mr10">全部保存</button>
    <button id="btn-cancel" class="btn-gray mr10">返回</button>
</div>
<!-- BEGIN CONTAINER -->
<div class="frm-content">
    <div class="page-box">
        <div class="page-title">附件信息</div>
        <div class="p5">
            <input id="fileUpload" name="fileUpload" type="file" value="添加附件" validate-type="Require"
                   validate-false="文件名不能为空"/>

            <div id="queue-item"></div>
            <div style="height: 10px;"></div>
            <form id="fileUploadListForm" class="zui-form" enctype="multipart/form-data">
                <div class="zui-datagrid" id="fileStore-zd-table"
                     zdata-options="{'idField':'id','url':'<z:ukey key="com.zdsoft.finance.fileStore.fileUploadTempData" context="admin"/>&jsoncallback=?&productId=${fileStoreVo.productId}&linkCode=${fileStoreVo.linkCode}&caseApplyId=${fileStoreVo.caseApplyId}&status=2&businessId=${fileStoreVo.businessId}',
                 'singleSelect':true,'isRowNum':false,'pagination':true,'tableCls': 'table-index','toolbar':false,'onLoadSuccess':'fileStoreLoadSuccess'}">
                    <table>
                        <thead>
                        <tr>
                            <th data-options="field:fileName,width:10%">文件名</th>
                            <th data-options="field:materiaCodeName,width:10%" formatter="materiaFormatter">类别名称</th>
                            <th data-options="field:updateTimeStr,width:10%">上传时间</th>
                            <th data-options="field:updateEmpName,width:10%">上传人</th>
                            <th data-options="field:documentName,width:10%">文档名称</th>
                            <th data-options="width:20%" formatter="operate">操作</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </form>
            <%--<div id="btn-applylist">
                <a class="zui-toolbar" id="btn-add" text="上传" iconCls="icon-add" buttonCls="btn-blue" handler="add"></a>
            </div>--%>
        </div>
    </div>
    <input type="hidden" value="${fileStoreVo.caseApplyId}" id="caseApplyId" name="caseApplyId"/>
    <input type="hidden" value="${fileStoreVo.linkCode}" id="linkCode" name="linkCode"/>
    <input type="hidden" value="${fileStoreVo.businessId}" id="businessId" name="businessId"/>
    <input type="hidden" value="${fileStoreVo.productId}" id="productId" name="productId"/>
</div>
<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading',
                'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker',
                'zd/jquery.zds.table', 'zd/jquery.zds.seleter', 'zd/jquery.zds.combotree', 'zd/jquery.zds.checkbox', 'zd/completer']
            , function ($, CALLBACK, COMBOBOX, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
                //操作栏的回调函数
                CALLBACK.operate = function (index,rowData) {
                    //如果是图片才显示查看
                    if(index.fileType=="0"){
                        var html = '<a href="javaScript:void(0)" class="btn-blue" onclick="FileStoreDoView">查看</a>' + '&nbsp;&nbsp;' +
                                '<a href="javaScript:void(0)" class="btn-blue" onclick="FileStoreDoDelete">删除</a>'
                    }else{
                        var html = '<a href="javaScript:void(0)" class="btn-blue" onclick="FileStoreDoDelete">删除</a>'
                    }
                    return html;
                };
                //查看
                CALLBACK.FileStoreDoView = function (index, rowData) {
                    var url = '<z:ukey key="com.zdsoft.finance.fileStore.imgViewPage" context="admin"/>&attachmentIds=' + rowData.attachmentId;
                    ZDS_MESSAGE_CLIENT.openMenuLink('fileStoreImgView', '预览图片', url);
                };
                //列表加载成功回调函数
                CALLBACK.fileStoreLoadSuccess = function (v,t) {
                    var data = '${allSimple}';
                    //模糊查询
                    $('.zui-complete').completer({
                        suggest: true,//默认false
                        idField: 'id',//默认id,唯一标识字段
                        nameField: 'name',//默认name,下拉列表展示数据的字段
                        valueField: 'name',//默认value,根据值查询数据的字段
                        source: data,
                        placeObj:$("dd[id^='place_']"),
                        writable: true,//默认false，是否可自定义输入
                        complete: function (data) {
                        	$('.zui-complete').blur();
                            var fileStoreId = this.$element.data('id');
                            var materiaCode = data.id;
                            var productId = $("#productId").val();
                            var linkCode = $("#linkCode").val();
                            var updateUrl = '<z:ukey key="com.zdsoft.finance.fileStore.editFileStore" context="admin"/>&id=' + fileStoreId + "&productId=" + productId + "&materiaCode=" + materiaCode + "&linkCode=" + linkCode;
                            $.ajax({
                                url: updateUrl,
                                type: "post",
                                success: function (data) {
                                    if (data.resultStatus == 0) {
                                        $('#fileStore-zd-table').ZTable("reload");
                                    } else {
                                        $.ZMessage.error("错误", "错误!" + data.msg, function () {
                                            $('#fileStore-zd-table').ZTable("reload");
                                        });
                                    }
                                },
                                error: function (data) {
                                    var errorMsg = JSON.parse(data.responseText);
                                    $.ZMessage.error("错误", "错误!" + errorMsg.msg, function () {
                                        $('#fileStore-zd-table').ZTable("reload");
                                    });
                                }
                            });
                        },
                        filter: function (val) {
                            return val;//过滤输入的value值
                        }
                    });
                }
                CALLBACK.materiaFormatter = function (index, rowData) {
                    return '<dl class="form-item" > <dd class="detail" id="place_'+index.id+'"> <label> <input mid="'+index.materiaId+'" style="text-align:center" data-id="' + index.id + '" value="' + rowData + '" type="text" class="zui-input zui-validatebox zui-complete" ' +
                            'validate-type="Require"> </label> </dd> </dl>'
                };
                //删除行
                CALLBACK.FileStoreDoDelete = function (num, selRow) {
                    $.ZMessage.question("确认", "确认删除？", function (r) {
                        if (selRow) {
                            var id = selRow.id;
                            $.ajax({
                                method: "post",
                                data: {id: id},
                                url: '<z:ukey key="com.zdsoft.finance.fileStore.deleteFileStore"  context="admin"/>&jsoncallback=?',
                                success: function (result) {
                                    if (result.resultStatus == 0) {
                                        $.ZMessage.info("提示", "删除成功", function () {
                                            $('#fileStore-zd-table').ZTable("reload");
                                        });
                                    }
                                    else {
                                        $.ZMessage.error("错误", "删除出错：" + result.msg, function () {
                                            $('#fileStore-zd-table').ZTable("reload");
                                        });
                                    }
                                }

                            });
                        }

                    });

                };
                window.fileStoreUpload = function (file, data, response) {
                    var allData = JSON.parse(data);
                    var fileName = file.name;
                    var attachmentId = allData[0].attachements.id;
                    var upUrl = '<z:ukey key="com.zdsoft.finance.fileStore.uploadFileStore" context="admin"/>&jsoncallback=?';
                    $.ajax({
                        url: upUrl,
                        data: {fileStoreJson: '${fileStoreJson}', fileName: fileName, attachmentId: attachmentId},
                        type: 'post',
                        success: function (data) {

                            if (data.resultStatus == 0) {
                                $.ZMessage.info("提示", "上传成功！", function () {
                                    $('#fileStore-zd-table').ZTable("reload");
                                });
                            } else {
                                $.ZMessage.error("错误", "错误!" + data.msg, function () {
                                    $('#fileStore-zd-table').ZTable("reload");
                                });
                            }
                        },
                        error: function (data) {

                            var errorMsg = JSON.parse(data.responseText);
                            $.ZMessage.error("错误", "错误!" + errorMsg.msg, function () {
                                $('#fileStore-zd-table').ZTable("reload");
                            });
                        }
                    });
                }
                //保存
                $("#btn-save").click(function () {
                    var caseApplyId = $("#caseApplyId").val();
                    var linkCode = $("#linkCode").val();
                    var businessId = $("#businessId").val();
                    var materiaArras=$("table input[type=text]");
                    materiaArras.each(function(e){
                        if($(this).attr("mid")==""){
                            $(this).val("");
                        }
                    });
                    if(materiaArras.length==0){
                        $.ZMessage.info("提示", "保存前请先上传文件", function () {
                            return false;
                        });
                    }else{
                        //验证资料类型是否为手动输入（没有从联想框中选择）
                        var isValidate = $.ZUI.validateForm($('#fileUploadListForm'));
                        if(!isValidate){
                            $.ZMessage.warning("提示", "请从联想框中选择类型", function () {
                            });
                            return false;
                        }else{
                            $.ZMessage.question("确认", "保存", function () {
                                $.ajax({
                                    url: '<z:ukey key="com.zdsoft.finance.fileStore.saveAllDraft" context="admin" />',
                                    data: {caseApplyId: caseApplyId, linkCode: linkCode, businessId: businessId},
                                    type: 'post',
                                    success: function (data) {
                                        if (data.resultStatus == 0) {
                                            $.ZMessage.info("提示", "保存成功！", function () {
                                                ZDS_MESSAGE_CLIENT.refreshOpenner();
                                                setTimeout(function () {
                                                    ZDS_MESSAGE_CLIENT.closeSelf();
                                                }, 200);
                                            });
                                        } else {
                                            $.ZMessage.error("错误", "错误!" + data.msg, function () {
                                                $('#fileStore-zd-table').ZTable("reload");
                                            });
                                        }
                                    },
                                    error: function (data) {
                                        var errorMsg = JSON.parse(data.responseText);
                                        $.ZMessage.error("错误", "错误!" + errorMsg.msg, function () {
                                            $('#fileStore-zd-table').ZTable("reload");
                                        });
                                    }
                                });
                            });
                        }
                    }
                });
                //返回
                $("#btn-cancel").click(function () {
                    ZDS_MESSAGE_CLIENT.refreshOpenner();
                    setTimeout(function () {
                        ZDS_MESSAGE_CLIENT.closeSelf();
                    }, 200);
                });

                $.ZUI.init();
                $(function () {
                    window.initUpload();
                });
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
            'onUploadSuccess': function (file, data, response) {
                fileStoreUpload(file, data, response);
            }
        });
    }
</script>
</body>
</html>