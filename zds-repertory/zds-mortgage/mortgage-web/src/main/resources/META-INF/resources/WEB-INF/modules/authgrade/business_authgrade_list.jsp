<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common_js.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>授权等级设定</title>
</head>
<body>
<div id="authGradeAllContentDiv">
    <div class="frm-content frm-bottom">
        <div class="page-box">
            <div class="p10">
                <form class="zui-form form-search" action="javascript:void(0);" id="authGradeSearchFrom"
                      zdata-options={"url":"http://192.168.33.137:8880/FindAllClints?jsoncallback=?","callBack":"saveCallBack"}>

                    <dl class="form-item">
                        <dt class="title">产品分类：</dt>
                        <dd class="detail">
                            <input class="zui-combobox zui-validatebox" type="hidden" id="productParentId"
                                   data-width="94"
                                   name="productParentId"
                                   data-url="<z:ukey key='com.zdsoft.finance.getParentProduct' context='admin'/>&jsoncallback=?"
                                   data-callback="productParentIdChange"
                                   data-height="300"
                                   data-defaultvalue=""
                                   data-valuefield="id" data-textfield="text">
                        </dd>
                        <dd class="detail">
                            <input class="zui-combobox zui-validatebox" type="hidden" id="productChildId"
                                   name="productChildId" data-width="94"
                                   data-url="<z:ukey key='com.zdsoft.finance.getProductByParentId' context='admin'/>&jsoncallback=?"
                                   data-callback=""
                                   data-height="300"
                                   data-defaultvalue=""
                                   data-valuefield="id" data-textfield="text">
                        </dd>

                    </dl>
                    <dl class="form-item">
                        <dt class="title">授权等级：</dt>
                        <dd class="detail">
                            <input class="zui-combobox zui-validatebox" type="hidden" id="gradeCode"
                                   name="gradeCode"
                                   data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=gradeClass"
                                   data-callback=""
                                   data-height="300"
                                   data-defaultvalue=""
                                   data-valuefield="id" data-textfield="text">
                        </dd>
                    </dl>

                    <div class="form-btn">
                        <button class="btn-blue" id="authGradeSearchButton">查询</button>
                        <button class="btn-blue" id="authGradeResetButton">重置</button>
                    </div>
                </form>


                <div id="authGradetable">
                    <div id="zds-authGrade-table"
                         class="zui-datagrid table-scroll"
                         zdata-options='{
                     "url":"<z:ukey key="com.zdsoft.finance.authGradeListData" context="admin"/>&jsoncallback=?",
                     "singleSelect":true,
                     "pagination":true,
                     "idField":"id",
                     "tableCls":"table-index",
                     "dbclickEditRow":false,
                     "toolbar":"#btn-applylist"
                     }'>
                        <table>
                            <thead>
                            <tr>
                                <th data-options="field:gradeName">授权等级</th>
                                <th data-options="field:productParentName">产品分类</th>
                                <th data-options="field:productChildName">子产品</th>
                                <th data-options="field:authAmount">额度(元)</th>
                                <th data-options="field:updateByName">操作人</th>
                                <th data-options="field:updateTimeStr">操作时间</th>
                                <th data-options="field:id" formatter="formatFuntion">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <!-- 与table中的一起使用才有效"toolbar":"#btn-applylist" ，写handler回调函数实现增删改功能-->
                    <div id="btn-applylist" style="width: 100%">
                        <a class="zui-toolbar" id="btn-add" text="新增" iconCls="icon-btn08" buttonCls="btn-blue"
                           handler="authGradeAdd"></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="dialogAuthGradeAdd" style="display: none">
        <div id="dialogAuthGradeAddFormDiv">
            <form id="AuthGradeAddForm" class="zui-form" zdata-options={"url":"www.zds.com"}>
                <input type="hidden" name="id" id="id"/>
                <input type="hidden" id="flag"/>
                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>授权等级：</dt>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" type="hidden" id="gradeCodeAdd"
                               name="gradeCode"
                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=gradeClass"
                               data-callback="gradeCodeAddChange"
                               data-height="300"
                               data-defaultvalue=""
                               validate-type="Require"
                               data-valuefield="id" data-textfield="text">
                        <input type="hidden" id="gradeNameAdd" name="gradeName" />
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>产品分类：</dt>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" type="hidden" id="productParentIdAdd"
                               data-width="94"
                               name="productParentId"
                               data-url="<z:ukey key='com.zdsoft.finance.getParentProduct' context='admin'/>&jsoncallback=?"
                               data-callback="productParentIdAddChange"
                               data-height="300"
                               data-defaultvalue=""
                               data-valuefield="id" data-textfield="text" validate-type="Require">
                        <input type="hidden" name="productParentName" id="productParentNameAdd" />
                    </dd>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" type="hidden" id="productChildIdAdd"
                               name="productChildId" data-width="94"
                               data-url="<z:ukey key='com.zdsoft.finance.getProductByParentId' context='admin'/>&jsoncallback=?"
                               data-callback="productChildIdAddChange"
                               data-height="300"
                               data-defaultvalue=""
                               data-valuefield="id" data-textfield="text" validate-type="Require">
                        <input type="hidden" name="productChildName" id="productChildNameAdd" />
                    </dd>

                </dl>
                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>额度</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input zui-validatebox" type="text" validate-type="Require,Amount"
                                   id="authAmountAdd"
                                   name="authAmount"/>
                        </label>
                        <span class="word">元</span>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">操作人</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input zui-validatebox zui-disabled" type="text" validate-type="Require"
                                   id="updateByNameAdd"
                                   disabled="disabled"
                                   name="updateByName" value="${empDto.empNm}"/>
                            <input type="hidden" value="${empDto.empCd}" id="updateByAdd" name="updateBy"
                                   class="zui-validatebox" validate-type="Require"/>
                            <input type="hidden" value="${empDto.empNm}" name="updateByName"
                                   class="zui-validatebox" validate-type="Require"/>
                        </label>
                    </dd>
                </dl>

            </form>
        </div>
    </div>

    <%--查看对话框--%>
    <div id="dialogAuthGradeView" style="display: none">
        <div id="dialogAuthGradeViewFormDiv">
            <form id="AuthGradeViewForm" class="zui-form" zdata-options={"url":"www.zds.com"}>
                <dl class="form-item">
                    <dt class="title">授权等级：</dt>
                    <dd class="detail">
                        <input class="zui-combobox" type="hidden" id="gradeCodeView"
                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=gradeClass"
                               data-height="300"
                               data-defaultvalue=""
                               data-valuefield="id" data-textfield="text">
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">产品分类：</dt>
                    <dd class="detail">
                        <input class="zui-combobox" type="hidden" id="productParentIdView"
                               data-width="94"
                               data-url="<z:ukey key='com.zdsoft.finance.getParentProduct' context='admin'/>&jsoncallback=?"
                               data-height="300"
                               data-defaultvalue=""
                               data-valuefield="id" data-textfield="text" validate-type="Require">
                    </dd>
                    <dd class="detail">
                        <input class="zui-combobox" type="hidden" id="productChildIdView"
                               data-width="94"
                               data-url="<z:ukey key='com.zdsoft.finance.getProductByParentId' context='admin'/>&jsoncallback=?"
                               data-height="300"
                               data-defaultvalue=""
                               data-valuefield="id" data-textfield="text" validate-type="Require">
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">额度</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input zui-disabled" type="text" id="authAmountView" disabled="disabled"/>
                        </label>
                        <span class="word">元</span>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">操作人</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input zui-disabled" type="text"
                                   id="updateByNameView"
                                   disabled="disabled" />
                        </label>
                    </dd>
                </dl>
                <dl class="form-item" id="handleTimeDiv">
                    <dt class="title">操作时间：</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input zui-disabled" type="text"
                                   id="handleTimeView"
                                   disabled="disabled"/>
                        </label>
                    </dd>
                </dl>

            </form>
        </div>
    </div>


</div>

<script>
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.checkbox', 'zd/jquery.zds.loading', 'zd/switch',
                'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form',
                'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter', 'zd/bothselecter', 'zd/jquery.zds.button'],
            function ($, CALLBACK, Loading, Switch, DropDown, Filter, Check, Zdialog, ZUI_MESSAGE_CLIENT) {

                CALLBACK.formatFuntion = function (index, rowData) {
                    var html = '';
                    html += '<a title="编辑" class="handler-icon icon-btn22 c-blue"  onclick="authGradeEditData"></a>';
                    html += '<a title="删除" class="handler-icon icon-btn12 c-blue"  onclick="authGradeDeleteData" ></a>';
                    return html;
                };
                //新增
                CALLBACK.authGradeAdd = function (index, rowData, row, thisobj) {
                    $("#flag").val("add");
                    $("#flag").attr("name","新增");
                    //设置为可读
                    $("#productParentIdAdd").data("choose","");

                    $("#productChildIdAdd").data("choose","");
                    $("#gradeCodeAdd").data("choose","");
                    $("#authAmountAdd").removeAttr("disabled");

                    $("#dialogAuthGradeAdd").Zdialog("open");
                }
                //查看
                CALLBACK.authGradeViewData = function (index, rowData, row, thisobj) {
                    debugger;
                    $("#productParentIdView").ZCombobox("setValue",rowData.productParentId);
                    $("#productChildIdView").ZCombobox("setValue",rowData.productChildId);
                    $("#gradeCodeView").ZCombobox("setValue",rowData.gradeCode);
                    $("#authAmountView").val(rowData.authAmount);
                    $("#updateByNameView").val(rowData.updateByName);
                    $("#handleTimeView").val(rowData.updateTimeStr);
                    //设置为只读
                    $("#productParentIdView").data("choose","disable");
                    $("#productChildIdView").data("choose","disable");
                    $("#gradeCodeView").data("choose","disable");
                    $("#authAmountView").attr("disabled","disabled");

                    $("#dialogAuthGradeView").Zdialog("open");
                };
                //编辑
                CALLBACK.authGradeEditData = function (index, rowData, row, thisobj) {
                    $("#flag").val("edit");
                    $("#flag").attr("name","编辑");
                    $("#id").val(rowData.id);
                    $("#productParentIdAdd").ZCombobox("setValue",rowData.productParentId);
                    setTimeout(function(){
                        $("#productChildIdAdd").ZCombobox("setValue",rowData.productChildId);
                    },200);
                    $("#gradeCodeAdd").ZCombobox("setValue",rowData.gradeCode);
                    $("#productParentNameAdd").val(rowData.productParentName);
                    $("#productChildNameAdd").val(rowData.productChildName);
                    $("#gradeNameAdd").val(rowData.gradeName);
                    $("#authAmountAdd").val(rowData.authAmount);
                    $("#productParentIdAdd").data("choose","");
                    //设置为可读
                    $("#productParentIdAdd").data("choose","");
                    $("#productChildIdAdd").data("choose","");
                    $("#gradeCodeAdd").data("choose","");
                    $("#authAmountAdd").removeAttr("disabled");

                    $("#dialogAuthGradeAdd").Zdialog("open");
                };
                //删除
                CALLBACK.authGradeDeleteData = function (index, rowData, row, thisobj) {
                    var id = rowData.id;
                    var deleteUrl = "<z:ukey key='com.zdsoft.finance.deleteAuthGrade' context='admin' />";
                    $.ZMessage.question("确认", "确认删除？", function () {
                        ;
                        $.post(deleteUrl, {id: id}, function (result) {
                            if (result.resultStatus == 0) {
                                $.ZMessage.info("成功", "数据删除成功", function () {
                                    var param = $("#AuthGradeAddForm").serializeArray();
                                    $("#zds-authGrade-table").ZTable("reload", param);
                                })
                            } else {
                                $.ZMessage.error("错误", "删除数据错误" + result.msg, function () {
                                    return false;
                                });
                            }
                        });
                    })
                };
                /**
                 * 下拉框联动
                 * */
                CALLBACK.productParentIdChange = function (index, rowData, row, thisobj) {
                    var parentId = index;
                    loadProductChildId(parentId);
                };
                /**
                 * 对话框下拉框-产品大类
                 * */
                CALLBACK.productParentIdAddChange = function(index, rowData, row, thisobj){
                    $("#productParentNameAdd").val(rowData);
                    var productChildIdAdd = $("#productChildIdAdd");
                    productChildIdAdd.ZCombobox({queryParams: {"parentId": index}});
                };
                /**
                 * 对话框下拉框-产品子类
                 * */
                CALLBACK.productChildIdAddChange = function(index, rowData, row, thisobj){
                    $("#productChildNameAdd").val(rowData);
                };
                /**
                 * 对话框下拉框-授权等级
                 * */
                CALLBACK.gradeCodeAddChange = function(index, rowData, row, thisobj){
                    $("#gradeNameAdd").val(rowData);
                };

                /*ZDS_MESSAGE_CLIENT.refreshThis = function () {
                 var param = $("#feeOptionSearchFrom").serializeArray();
                 $("#zds-feeOption-table").ZTable("reload", param);
                 };*/

                //查询
                $("#authGradeSearchButton").click(function(){
                    var param = $("#authGradeSearchFrom").serializeArray();
                    $("#zds-authGrade-table").ZTable("reload", param);
                });
                //重置
                $("#authGradeResetButton").click(function(){
                    $("#productParentId").ZCombobox("setValue","");
                    setTimeout(function(){
                        $("#gradeCode").ZCombobox("setValue","");
                        $("#productChildId").ZCombobox("setValue","");
                    },200);
                });

                //新增对话框
                $("#dialogAuthGradeAdd").Zdialog({
                    width: 700, height: 400, closed: true, title: "新增",
                    buttons: [
                        {
                            id: 'message-btnAuthGradeAdd',
                            text: '确定',
                            buttonCls: 'btn-blue',
                            handler: function () {
                                //区分是否为查看
                                if($("#flag").val()!="view"){
                                    $.ZUI.initForms('#AuthGradeAddForm');
                                    var validate = $.ZUI.validateForm($('#AuthGradeAddForm'));
                                    if (!validate) {
                                        $.ZMessage.error("错误", "数据验证失败!", function () {
                                        });
                                        return false;
                                    }
                                    var params = $('#AuthGradeAddForm').serializeArray();
                                    var url = '<z:ukey key="com.zdsoft.finance.saveOrUpdateAuthGrade" context="admin" />&jsoncallback=? ';
                                    $.ajax({
                                        url: url,
                                        data: params,
                                        type: "post",
                                        success: function (data) {
                                            if (data.resultStatus == 0) {
                                                $.ZMessage.success("提示", "数据操作成功!", function () {
                                                    $("#productChildIdAdd").ZCombobox("setValue","");
                                                    $("#dialogAuthGradeAdd").Zdialog("close");
                                                    $('#zds-authGrade-table').ZTable("reload", {});
                                                });
                                            } else {
                                                $.ZMessage.error("错误", "数据操作出错!" + data.msg, function () {
                                                });
                                                return false;
                                            }
                                        },
                                        error: function (data) {
                                            $.ZMessage.error("错误", "错误!" + data.msg, function () {
                                            });
                                        }
                                    });
                                }else{
                                    $("#dialogAuthGradeAdd").Zdialog("close");
                                }
                            }
                        },
                        {
                            id: 'message-btn',
                            text: '取消',
                            buttonCls: 'btn-blue',
                            handler: function () {
                                $("#dialogAuthGradeAdd").Zdialog("close");
                            }
                        }
                    ]
                });

                //查看对话框
                $("#dialogAuthGradeView").Zdialog({
                    width: 700, height: 400, closed: true, title: "详情",
                    buttons: [
                        {
                            id: 'message-btn',
                            text: '关闭',
                            buttonCls: 'btn-blue',
                            handler: function () {
                                $("#dialogAuthGradeView").Zdialog("close");
                            }
                        }
                    ]
                });


                /**
                 * 下拉数据
                 * @param cataId
                 */
                function loadProductChildId(pId) {
                    var productChildId = $("#productChildId");
                    productChildId.ZCombobox({queryParams: {"parentId": pId}});
                }


                //下拉框初始化
                $("#productParentId").ZCombobox();
                $("#productChildId").ZCombobox();
                $("#gradeCode").ZCombobox();
                $("#productParentIdAdd").ZCombobox();
                $("#productChildIdAdd").ZCombobox();
                $("#gradeCodeAdd").ZCombobox();
                $("#productParentIdView").ZCombobox();
                $("#productChildIdView").ZCombobox();
                $("#gradeCodeView").ZCombobox();



                $.ZUI.initGrid("#authGradeAllContentDiv");
                $.ZUI.initForms("#authGradeSearchFrom");
                $.ZUI.initForms("#AuthGradeViewForm");

            });

</script>

</body>
</html>