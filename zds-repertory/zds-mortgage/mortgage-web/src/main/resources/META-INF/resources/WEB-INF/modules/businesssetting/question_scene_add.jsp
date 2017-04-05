<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8" %>
<%@ include file='../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>场景问题设置</title>
</head>
<body>
<div class="save">
    <button id="btn-save" class="btn-blue mr10">确定</button>
    <button id="btn-cancel" class="btn-gray mr10">取消</button>
</div>
<div id="questionForm">
    <!-- 查询区域 -->
    <div class="page-box">
        <div class="page-title">查询</div>
        <div class="p10">
            <form id="searchForm" class="zui-form form-search" method="post" zdata-options="{}">
                <dl class="form-item">
                    <dt class="title">问题:</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input" type="text" id="questionContentSearch" name="questionContent">
                        </label>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">字段类型:</dt>
                    <dd class="detail">
                        <input class="zui-combobox" type="hidden" id="questionTypeCodeSearch"
                               data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0098"
                               data-valuefield="fullcode" data-textfield="name" name="questionTypeCode">
                    </dd>
                </dl>
                <dl class="form-btn">
                    <input type="button" class="btn-blue" id="search" value="查询"/>
                    <input type="button" class="btn-gray" id="reset" value="重置"/>
                </dl>
            </form>
        </div>
    </div>
    <!-- 列表区域 -->
    <div class="page-box">
        <div class="page-title">问题库</div>
        <div class="p10">
            <div id="tb-question" class="zui-datagrid"
                 zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.question.getList' context='admin'/>","singleSelect":false,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#question_toolbar"}'>
                <table>
                    <tr>
                        <th data-options="field:questionContent,width:70%">问题</th>
                        <th data-options="field:questionTypeCodeName,width:30%">字段类型</th>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>

<div id="questionSceneAddDialog" style="display: none">
    <form id="addQuestionSceneForm" class="zui-form mt15" action="javascript:void(0);"
          zdata-options="{}">
        <dl class="form-item">
            <dt class="title"><b class="c-red mr5">*</b>类型:</dt>
            <dd class="detail">
                <input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require" id="sceneTypeCode"
                       data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00102"
                       data-valuefield="fullcode" data-toggle="validate" data-textfield="name" name="sceneTypeCode">
            </dd>
        </dl>
        <dl class="form-item">
            <dt class="title"><b class="c-red mr5">*</b>规则类型:</dt>
            <dd class="detail">
                <input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require" id="sceneQuestionType"
                       data-data="[{id:'0',text:'默认'},{id:'1',text:'命中'},{id:'2',text:'随机'}]"
                       data-valuefield="id" data-toggle="validate" data-textfield="text" name="sceneQuestionType">
            </dd>
        </dl>
    </form>
</div>

</body>
<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.form', 'zd/jquery.zds.message', 'zd/jquery.zds.dialog', 'zd/jquery.zds.combobox', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'], function ($, CALLBACK) {
        //查询
        $('#search').on('click', function () {
            var formArray = $("#searchForm").serializeArray();
            $('#tb-question').ZTable("reload", formArray);
        });
        //重置
        $('#reset').on('click', function () {
            $('#questionContentSearch').val('');
            $('#questionTypeCodeSearch').ZCombobox('setValue', '');
        });
        //保存
        $("#btn-save").on("click", function () {
            var selectRows = $("#tb-question").ZTable("getSelections");
            if (selectRows.length == 0) {
                $.ZMessage.warning("提示", "请选择数据", function () {
                    return false;
                });
            }else{
                $("#questionSceneAddDialog").Zdialog("open");
            }
        });
        //取消
        $("#btn-cancel").on("click",function(){
            ZDS_MESSAGE_CLIENT.refreshOpenner();
            ZDS_MESSAGE_CLIENT.closeSelf();
        });
        $("#questionSceneAddDialog").Zdialog({
            title: "编辑",
            width: 650,
            height: 300,
            closed: true,
            isDrag: false,
            buttons: [
                {
                    id: 'message-btn',
                    text: '确定',
                    buttonCls: 'btn-blue',
                    handler: function () {
                        var flag = $.ZUI.validateForm($('#addQuestionSceneForm'));
                        if (flag) {
                            //sceneTypeCode sceneQuestionType
                            var sceneTypeCode = $('#sceneTypeCode').val();
                            var sceneQuestionType = $("#sceneQuestionType").val();
                            var selectRows = $("#tb-question").ZTable("getSelections");
                            var ids = "";
                            if (selectRows.length == 0) {
                                $.ZMessage.warning("提示", "请选择数据", function () {
                                    return false;
                                });
                            }
                            //循环获得问题ID
                            for (var i = 0; i < selectRows.length; i++) {
                                ids += selectRows[i].id + ",";
                            }
                            //去掉最后一个逗号
                            ids = ids.substr(0, ids.length - 1);
                            $.ajax({
                                type: 'post',
                                url: '<z:ukey key="com.zdsoft.finance.questionScene.saveOrUpdate" context="admin"/>',
                                data: {
                                    'sceneTypeCode': sceneTypeCode,
                                    'sceneQuestionType': sceneQuestionType,
                                    'ids': ids
                                },
                                dataType: 'json',
                                success: function (data) {
                                    if (data.resultStatus == 0) {
                                        $.ZMessage.success("提示", "保存成功", function () {
                                            setTimeout(function () {
                                                ZDS_MESSAGE_CLIENT.refreshOpenner();
                                                ZDS_MESSAGE_CLIENT.closeSelf();
                                            }, 200);
                                            $("#questionSceneAddDialog").Zdialog("close");
                                        });
                                    }else if(data.resultStatus==-1){
                                        var msg="在当前场景下已经存在相同的问题设置:<br />";
                                        var exsitData=data.rows;
                                        for(var i=0;i<exsitData.length;i++){
                                            var questionContent=exsitData[i].questionContent;
                                            msg+=questionContent+"<br />";
                                        }
                                        $.ZMessage.warning("错误", msg, function () {
                                            return false;
                                        });
                                    }else {
                                        $.ZMessage.error("错误", data.msg, function () {
                                            $("#questionSceneAddDialog").Zdialog("close");
                                        });
                                    }
                                },
                                error: function (data) {
                                    var errorMsg = JSON.parse(data.responseText);
                                    $.ZMessage.error("错误", "错误!" + errorMsg.msg, function () {
                                    });
                                }
                            });
                        }
                    }
                },
                {
                    id: 'message-btn',
                    text: '取消',
                    buttonCls: 'btn-gray',
                    handler: function () {
                        $("#questionSceneAddDialog").Zdialog("close");
                    }
                }
            ]
        });

        //初始化页面
        $.ZUI.init();
    });
</script>
</html>