<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file='../common/common_js.jsp' %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>问题定义</title>
</head>
<body>
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
                 zdata-options='{"url":"<z:ukey key='com.zdsoft.finance.question.getList' context='admin'/>","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#question_toolbar"}'>
                <table>
                    <tr>
                        <th data-options="field:questionContent,width:40%">问题</th>
                        <th data-options="field:questionTypeCodeName,width:10%">字段类型</th>
                        <th data-options="field:paraValue,width:20%" formatter="paraValueFormatter">参数值</th>
                        <th data-options="field:judgeRule,width:20%" formatter="judgeRuleFormatter">命中规则</th>
                        <th data-options="field:id,width:10%" formatter="formatId">操作</th>
                    </tr>
                </table>
            </div>
            <div id="question_toolbar">
                <a class="zui-toolbar" iconCls="icon-btn08" text="新增" buttonCls="btn-blue" handler="addQuestion"></a>
            </div>
        </div>
    </div>
</div>

<div id="questionDialog" style="display: none">
    <div id="dialog">
        <form id="addQuestionForm" class="zui-form mt15" action="javascript:void(0);" zdata-options="{}">
            <input type="hidden" name="id" id="id" >
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>问题:</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input zui-validatebox" style="width: 504px" id="questionContent"
                               validate-type="Require,Length[1-128]" name="questionContent" >
                    </label>
                </dd>
            </dl>
            <br/>
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>字段类型:</dt>
                <dd class="detail">
                    <input class="zui-combobox zui-validatebox" type="hidden" validate-type="Require"
                           data-callback="showParam" id="questionTypeCode"
                           data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0098"
                           data-valuefield="fullcode" data-textfield="name" name="questionTypeCode">
                </dd>
            </dl>
            <dl class="form-item" id="showParam">
            </dl>
            <br>
            <dl class="form-item">
                <dt class="title">命中规则:</dt>
                <dd class="detail">
                    <label>
                            <textarea class="zui-area zui-validatebox" name="judgeRule" id="judgeRule"
                                      validate-type="Length[0-500]"
                                      placeholder="最多可以输入500个字符"></textarea>
                    </label>
                    <div class="zd-area">
                        <span class="zd-curval">0</span>/<span class="zd-maxval">500</span>
                    </div>
                </dd>
            </dl>
        </form>
    </div>
</div>

</body>
<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.form', 'zd/jquery.zds.message', 'zd/jquery.zds.dialog', 'zd/jquery.zds.combobox', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'], function ($, CALLBACK) {

        //添加
        CALLBACK.addQuestion = function () {
            $("#id").val("");
            $("#questionDialog").Zdialog("setTitle","新增");
            $("#questionDialog").Zdialog("open");
        };
        CALLBACK.judgeRuleFormatter = function(index,rowData){
            if(rowData==""||rowData=="null"||rowData==undefined){
                return "";
            }else{
                return rowData;
            }
        };
        //参数值回调
        CALLBACK.paraValueFormatter=function(index,rowData){
            if(rowData==""||rowData=="null"||rowData==undefined){
                return "";
            }else{
                return rowData;
            }
        };
        //操作
        CALLBACK.formatId = function (rowData, index) {
            return '<a href="javaScript:void(0)" onclick="edit"><button class="btn-blue">编辑</button></a>'/*
                    + '&nbsp;&nbsp;' + '<a href="javaScript:void(0)" onclick="delete"><button class="btn-blue">删除</button></a>'*/;
        };
        //单选框触发事件
        CALLBACK.showParam = function(rowData, index){
            if(rowData=="YWDM009802"||rowData=="YWDM009803"){
                $("#showParam").empty();
                $("#showParam").append("<dt class='title'><b class='c-red mr5'>*</b>参数值:</dt>"+
                                       "<dd class='detail'> "+
                                        "<label>"+
                                          "<input class='zui-input zui-validatebox' value='有:none,无:input,其他:file' id='paraValue'  validate-type='Require,Length[0-255]' name='paraValue' >"+
                                        "</label>"+
                                       "</dd>");
                /*$.ZUI.initGrid("#showParam");*/
            }else{
                $("#showParam").empty();
            }
        };
        //编辑
        CALLBACK.edit = function (index, rowData) {
            $("#id").val(rowData.id);
            //为各项赋值
            $("#questionContent").val(rowData.questionContent);
            $("#questionTypeCode").ZCombobox("setValue",rowData.questionTypeCode);
            $("#paraValue").val(rowData.paraValue);
            $("#judgeRule").val(rowData.judgeRule);

            $("#questionDialog").Zdialog("setTitle","编辑");
            $("#questionDialog").Zdialog("open");

        };
        //删除
        CALLBACK.delete = function (index, rowData) {
            $.ZMessage.question("警告", "确认删除？", function () {
                $(".zd-message").ZWindow("close");
                $.ajax({
                    type: 'post',
                    url: '<z:ukey key="com.zdsoft.finance.question.delete" context="admin"/>',
                    data: {questionId: rowData.id},
                    dataType: 'json',
                    success: function (data) {
                        if (data.resultStatus == 0) {
                            $.ZMessage.success("提示", "删除成功", function () {
                                var para = $('#addQuestionForm').serializeArray()
                                $('#tb-question').ZTable("reload",para);
                            });
                        } else {
                            $.ZMessage.error("错误", data.msg, function () {
                                var para = $('#addQuestionForm').serializeArray()
                                $('#tb-question').ZTable("reload",para);
                            });
                        }
                    },
                    error: function () {
                        $.ZMessage.error("错误", "系统异常,请联系管理员", function () {
                            var para = $('#addQuestionForm').serializeArray()
                            $('#tb-question').ZTable("reload",para);
                        });
                    }
                });
            });
        };
        //对话框初始化
        $("#questionDialog").Zdialog({
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
                        var flag = $.ZUI.validateForm($('#addQuestionForm'));
                        if (flag) {
                            var addQuestionForm = $('#addQuestionForm').serializeArray();
                            $.ajax({
                                type: 'post',
                                url: '<z:ukey key="com.zdsoft.finance.question.saveOrUpdate" context="admin"/>',
                                data: addQuestionForm,
                                dataType: 'json',
                                success: function (data) {
                                    if (data.resultStatus == 0) {
                                        $.ZMessage.success("提示", "操作成功", function () {
                                            var para = $('#addQuestionForm').resetSerialize(true);
                                            $('#tb-question').ZTable("reload",para);
                                        });
                                    }else{
                                        $.ZMessage.error("提示", "操作失败："+data.msg, function () {
                                            var para = $('#addQuestionForm').resetSerialize(true);
                                            $('#tb-question').ZTable("reload",para);
                                        });
                                    }
                                    $("#questionDialog").Zdialog("close");
                                },
                                error: function (data) {
                                    var errorMsg = JSON.parse(data.responseText);
                                    $.ZMessage.error("错误", "错误!" + errorMsg.msg, function () {
                                        var para = $('#addQuestionForm').resetSerialize(true);
                                        $('#tb-question').ZTable("reload",para);
                                    });
                                    $("#questionDialog").Zdialog("close");
                                }
                            });
                        } else {
                            $.ZMessage.error("错误", "参数验证失败", function () {
                            });
                        }
                    }
                },
                {
                    id: 'message-btn',
                    text: '取消',
                    buttonCls: 'btn-gray',
                    handler: function () {
                        $("#questionDialog").Zdialog("close");
                    }
                }
            ]
        });
        //查询
        $('#search').on('click', function () {
            var formArray = $("#searchForm").serializeArray();
            $('#tb-question').ZTable("reload", formArray);
        });
        //重置
        $('#reset').on('click', function () {
            $('#questionContentSearch').val('');
            $('#questionTypeCodeSearch').ZCombobox('setValue', '');
            $('#tb-question').ZTable("reload", {});
        });

        //初始化页面
        $.ZUI.init();
    });
</script>
</html>