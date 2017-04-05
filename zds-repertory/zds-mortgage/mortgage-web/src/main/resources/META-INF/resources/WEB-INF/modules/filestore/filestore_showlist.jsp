<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN CONTAINER -->
<div id="fileStoreListContent">
    <div class="page-box">
        <div class="page-title">附件信息</div>
        <div class="p5" id="fileStore-zd-tableDiv">
            <div class="mb10">
                <c:if test="${fileStoreVo.useType!=null&&(fileStoreVo.useType=='edit'||fileStoreVo.useType=='EDIT')}">
                    <a class="btn-blue mr5" id="fileStoreAdd">添加附件</a>
                </c:if>
                <a class="btn-blue mr5" id="fileStoreView">预览图片</a>
                <a class="btn-blue mr5" id="fileStoreAllDownload">批量下载</a>


                <span class="fr" style="margin-right: 152px">
                     <dl class="form-item form-auto">
                         <dd class="detail">
                             <input class="zui-combobox" id="materiaTypeCodeSearch" type="hidden"
                                    name="materiaTypeCode" data-height="200px"
                                    data-url="<z:ukey key="com.zdsoft.finance.fileStore.findParentMateria" context="admin"/>&jsoncallback=?"
                                    data-valuefield="id" data-textfield="name">
                         </dd>
                     </dl>
                    <a class="btn-blue" id="FileStoreDoSearch">筛选</a>
                    <a class="btn-blue mr5" id="fileStoreViewHis">查看下载历史记录</a>
                </span>
            </div>
            <!-- table演示 -->
            <div class="zui-datagrid" id="fileStore-zd-table"
                 zdata-options="{'idField':'id','url':'<z:ukey key="com.zdsoft.finance.fileStore.fileShowListDada"  context="admin"/>&jsoncallback=?&productId=${fileStoreVo.productId}&linkCode=${fileStoreVo.linkCode}&caseApplyId=${fileStoreVo.caseApplyId}&businessId=${fileStoreVo.businessId}',
                 'singleSelect':false,
                 'isRowNum':false,
                 'pagination':true,
                 'tableCls': 'table-index'}">
                <table>
                    <thead>
                    <tr>
                        <th data-options="field:materiaTypeCodeName,width:10%">所属分类</th>
                        <th data-options="field:materiaCodeName,width:10%">类别名称</th>
                        <th data-options="field:disOrder,width:5%">序号</th>
                        <th data-options="field:fileName,width:10%">文件名</th>
                        <th data-options="field:documentName,width:10%">文件名称</th>
                        <th data-options="field:updateEmpName,width:10%">上传人</th>
                        <th data-options="field:updateTimeStr,width:10%">上传时间</th>
                        <th data-options="field:filePath,width:5%" formatter="fileStoreLocationFormatter">定位</th>
                        <th data-options="field:linkName,width:10%">来源</th>
                        <th data-options="field:id,width:20%" formatter="fileStoreOperate">操作</th>

                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>


<div id="fileStoreEditContent" style="display: none">
    <div id="fileStoreEditContentDiv">
        <form id="fileStoreEditForm" class="zui-form" enctype="multipart/form-data">
            <input type="hidden" id="id" name="id"/>
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>所属类型</dt>
                <dd class="detail">
                    <input class="zui-combobox zui-validatebox" id="materiaTypeCode" type="hidden"
                           name="materiaTypeCode"
                           data-url="<z:ukey key="com.zdsoft.finance.fileStore.findProductMateriaWithLinkCode" context="admin"/>&jsoncallback=?&productId=${fileStoreVo.productId}&linkCode=${fileStoreVo.linkCode}"
                           data-callback="fileStoreMateriaTypeCodeChange"
                           data-height="270"
                           data-valuefield="id" data-textfield="text" validate-type="Require">
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>类别名称</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-validatebox" id="materiaCode" type="hidden" name="materiaCode"
                               data-height="200" validate-type="Require">
                    </label>
                </dd>
            </dl>
            <dl class="form-item block">
                <dt class="title"><b class="c-red mr5">*</b>文件名称:</dt>
                <dd class="detail">
                    <label>
                        <input type="text" class="zui-input zui-validatebox" id="fileNameEdit" readonly="true"
                               validate-type="Require"/>
                        <input type="hidden" id="fileName" name="fileName">
                    </label>
                </dd>
            </dl>
            <dl class="form-item block">
                <dt class="title"><b class="c-red mr5">*</b>文档名称:</dt>
                <dd class="detail">
                    <label>
                        <input type="text" class="zui-input zui-validatebox" id="documentNameEdit" name="documentName"
                               data-toggle="validate"
                               validate-type="Require,Length[1-64]" validate-false="最长只能输入64个字符"/>
                    </label>
                </dd>
            </dl>
        </form>
    </div>
</div>
<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.form', 'zd/jquery.zds.table', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'datepicker']
            , function ($, CALLBACK) {
                //操作栏的回调函数
                CALLBACK.fileStoreOperate = function (index, rowData) {
                    var useType='${fileStoreVo.useType}';
                    if (index.id != "" && index != null) {
                        if(useType!='null'&&useType!=''&&useType!=undefined&&(useType=='edit'||useType=='EDIT')){
                            var html = '<a href="javaScript:void(0)" class="btn-blue" onclick="fileStoreDoEdit">编辑</a>&nbsp;&nbsp;' +
                                    '<a href="javaScript:void(0)" class="btn-blue" onclick="fileStoreDoDelete">删除</a>&nbsp;&nbsp' +
                                    '<a href="javaScript:void(0)" class="btn-blue" onclick="fileStoreDoDownload">下载</a>'
                        }else{
                            var html = '<a href="javaScript:void(0)" class="btn-blue" onclick="fileStoreDoDownload">下载</a>'
                        }
                        return html;
                    } else {
                        return "";
                    }

                };
                //编辑行
                CALLBACK.fileStoreDoEdit = function (num, selRow) {
                    $("#id").val(selRow.id);
                    $("#materiaTypeCode").ZCombobox({
                        onLoadSuccess: function () {
                            $("#materiaCode").ZCombobox({
                                onLoadSuccess: function () {
                                    $("#materiaTypeCode").ZCombobox("setValue", selRow.materiaTypeCode);
                                    $("#materiaCode").ZCombobox("setValue", selRow.materiaCode);
                                }
                            });
                        }
                    });
                    $("#fileNameEdit").val(selRow.fileName);
                    $("#fileName").val(selRow.fileName);
                    $("#documentNameEdit").val(selRow.documentName);


                    $("#fileStoreEditContent").Zdialog("setTitle", "编辑");
                    $("#fileStoreEditContent").Zdialog("open");
                };
                //单个下载
                CALLBACK.fileStoreDoDownload = function (index, rowData) {

                    //得到附件ID
                    var id = rowData.id;
                    var attachmentId = rowData.attachmentId;
                    var downLoadUrl = '<z:ukey key="com.zdsoft.finance.fileStore.downloadFile" context="admin"/>';
                    $.ajax({
                        url: downLoadUrl,
                        data: {ids: id},
                        type: 'post',
                        success: function (data) {
                            if (data.resultStatus == 0) {
                                var sysDownLoadUrl = '<z:ukey key="public.upload.download" context="admin"/>&jsoncallback=?&attachmentId=' + attachmentId;
                                window.location.href = sysDownLoadUrl;
                            } else {
                                $.ZMessage.error("错误", "错误!" + data.msg, function () {
                                });
                            }
                        },
                        error: function (data) {
                            var errorMsg = JSON.parse(data.responseText);
                            $.ZMessage.error("错误", "错误!" + errorMsg.msg, function () {
                            });
                        }
                    })
                };
                //删除行
                CALLBACK.fileStoreDoDelete = function (num, selRow) {
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
                //资料类型下拉框
                CALLBACK.fileStoreMateriaTypeCodeChange = function (index, rowData, row, childData) {
                    loadMateriaCd(childData.child);
                };
                //资料名称下拉框
                CALLBACK.fileStoreMateriaCodeChange = function (index, rowData, row, thisobj) {
                    $("#materiaName").val(rowData);
                    //回写助记码
                    $("#rememberCode").val(thisobj.rememberCode);
                    $("#rememberNo").val(thisobj.rememberNo);
                };
                //定位 格式化
                CALLBACK.fileStoreLocationFormatter = function (index, v) {
                    //纬度
                    var latitude = index.latitude;
                    //经度
                    var longitude = index.longitude;
                    if (latitude != null && latitude != "" && latitude != undefined && longitude != null && longitude != "" && longitude != undefined) {
                        return '<a href="javaScript:void(0)" style="color: #4E99D9">是</a>';
                    } else if(index.id!="") {
                        return "否";
                    }else{
                        return "";
                    }
                };
                //添加附加
                $("#fileStoreAdd").click(function () {
                    var uploadUrl = '<z:ukey key="com.zdsoft.finance.fileStore.fileUploadPage" context="admin"/>&productId=${fileStoreVo.productId}&linkCode=${fileStoreVo.linkCode}&caseApplyId=${fileStoreVo.caseApplyId}&businessId=${fileStoreVo.businessId}';
                    ZDS_MESSAGE_CLIENT.openMenuLink('fileStoreUpload', '添加附件', uploadUrl);
                });
                //图片预览
                $("#fileStoreView").click(function () {

                    var selections = $("#fileStore-zd-table").ZTable("getSelections");
                    if (!selections || selections.length == 0) {
                        $.ZMessage.warning("提示", "请选择图片文件", function () {
                        });
                        return false;
                    }
                    var attachments = "";
                    for (var i = 0; i < selections.length; i++) {
                        var fileType = selections[i].fileType;
                        if (fileType != "0") {
                            $.ZMessage.warning("提示", "所选文件中含有非图片文件，请重新选择", function () {
                            });
                            return false;
                        }

                        attachments += selections[i].attachmentId + ",";
                    }
                    attachments = attachments.substr(0, attachments.length - 1);

                    var url = '<z:ukey key="com.zdsoft.finance.fileStore.imgViewPage" context="admin"/>&attachmentIds=' + attachments;
                    ZDS_MESSAGE_CLIENT.openMenuLink('fileStoreImgView', '预览图片', url);
                });
                //批量下载
                $("#fileStoreAllDownload").click(function () {

                    //获取选中行
                    var selections = $("#fileStore-zd-table").ZTable("getSelections");
                    if (!selections || selections.length == 0) {
                        $.ZMessage.warning("提示", "请选择文件", function () {
                        });
                        return false;
                    }
                    var ids = "";
                    var attachmentIds = "";
                    var downLoadUrl = '<z:ukey key="com.zdsoft.finance.fileStore.downloadFile" context="admin"/>';
                    //循环选中行 得到附件ID
                    for (var i = 0; i < selections.length; i++) {
                        var currentId=selections[i].id;
                        if(currentId==undefined||currentId==""||currentId=="null"){
                            $.ZMessage.warning("提示", "请确认文件已上传", function () {
                            });
                            return false;
                        }
                        ids += selections[i].id + ",";
                        attachmentIds += selections[i].attachmentId + ",";
                    }
                    ids = ids.substr(0, ids.length - 1);
                    attachmentIds = attachmentIds.substr(0, attachmentIds.length - 1);
                    $.ajax({
                        url: downLoadUrl,
                        data: {ids: ids},
                        type: 'post',
                        success: function (data) {
                            if (data.resultStatus == 0) {
                                var sysDownLoadUrl = '<z:ukey key="public.upload.downLoadZipFile" context="admin"/>&jsoncallback=?&zipName=files&attachmentIds=' + attachmentIds;
                                window.location.href = sysDownLoadUrl;
                            } else {
                                $.ZMessage.error("错误", "错误!" + data.msg, function () {
                                });
                            }
                        },
                        error: function (data) {
                            var errorMsg = JSON.parse(data.responseText);
                            $.ZMessage.error("错误", "错误!" + errorMsg.msg, function () {
                            });
                        }
                    })
                });
                //查看下载历史记录
                $("#fileStoreViewHis").click(function () {
                    var url = '<z:ukey key="com.zdsoft.finance.fileStore.downHisPage" context="admin"/>&productId=${fileStoreVo.productId}&linkCode=${fileStoreVo.linkCode}&caseApplyId=${fileStoreVo.caseApplyId}&businessId=${fileStoreVo.businessId}';
                    ZDS_MESSAGE_CLIENT.openMenuLink('fileStoreViewHis', '附件下载日志', url);
                });
                //筛选
                $("#FileStoreDoSearch").click(function(){
                    $("#fileStore-zd-table").ZTable("reload",{materiaTypeCode:$("#materiaTypeCodeSearch").val(),isSearch:"true"});
                });
                //刷新回调函数
                ZDS_MESSAGE_CLIENT.refreshThis = function () {
                    $("#fileStore-zd-table").ZTable("reload");
                };

                /**
                 * 弹出框初始化
                 * */
                $("#fileStoreEditContent").Zdialog({
                    title: "编辑",
                    width: 700,
                    height: 300,
                    closed: true,
                    isDrag: false,
                    buttons: [
                        {
                            id: 'message-btn', text: '确定', buttonCls: 'btn-blue',
                            handler: function () {
                                var isValidate = $.ZUI.validateForm($('#fileStoreEditForm'));
                                if (isValidate) {
                                    var param = $("#fileStoreEditForm").serializeArray();
                                    //
                                    var id = $("#id").val();
                                    var materiaTypeCode = $("#materiaTypeCode").val();
                                    var materiaCode = $("#materiaCode").val();
                                    var fileName = $("#fileName").val();
                                    var documentName = $("#documentNameEdit").val();

                                    $.ajax({
                                        method: "post",
                                        data: {
                                            id: id,
                                            materiaTypeCode: materiaTypeCode,
                                            materiaCode: materiaCode,
                                            fileName: fileName,
                                            documentName: documentName
                                        },
                                        url: '<z:ukey key="com.zdsoft.finance.fileStore.editFileStore"  context="admin"/>&jsoncallback=?',
                                        success: function (result) {
                                            if (result.resultStatus == 0) {
                                                $.ZMessage.success("提示", "操作成功", function () {
                                                    $('#fileStore-zd-table').ZTable("reload");
                                                });
                                            }
                                            else {
                                                $.ZMessage.error("错误", "操作失败：" + result.msg, function () {
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
                                    $("#fileStoreEditContent").Zdialog("close");
                                }
                            }
                        },
                        {
                            id: 'message-btn', text: '取消', buttonCls: 'btn-gray',
                            handler: function () {
                                $("#fileStoreEditContent").Zdialog("close");
                            }
                        }],
                });

                /**
                 * 加载资料名称下拉数据
                 * @param cataId
                 */
                function loadMateriaCd(cataId) {

                    var materiaCode = $("#materiaCode");
                    materiaCode.ZCombobox({
                        valueField: "id",
                        textField: "text",
                        data: cataId
                    });
                }


                //初始化框架
                $("#materiaTypeCodeSearch").ZCombobox();
                $.ZUI.initForms("#fileStoreEditContentDiv");
                $.ZUI.initGrid("#fileStore-zd-tableDiv");
            });
</script>