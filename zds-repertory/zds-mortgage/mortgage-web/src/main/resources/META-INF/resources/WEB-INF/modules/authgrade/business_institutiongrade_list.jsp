<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common_js.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="z" uri="http://www.zdsoft.cn/tags" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>机构评级列表</title>
</head>
<body>
<div id="institutionGradeAllContentDiv">
    <div class="frm-content frm-bottom">
        <div class="page-box">
            <div class="p10">
                <form class="zui-form form-search" action="javascript:void(0);" id="institutionGradeSearchFrom"
                      zdata-options={"url":"http://192.168.33.137:8880/FindAllClints?jsoncallback=?","callBack":"saveCallBack"}>

                    <dl class="form-item">
                        <dt class="title">机构：</dt>
                        <dd class="detail">
                            <input class="zui-combobox zui-validatebox" type="hidden" id="institutionCode"
                                   name="institutionCode"
                                   data-url="<z:ukey key="com.zdsoft.finance.findAllOrgSimpleCode" context="admin"/>&jsoncallback=?"
                                   data-callback=""
                                   data-height="300"
                                   data-defaultvalue=""
                                   data-valuefield="id" data-textfield="text">
                        </dd>
                    </dl>

                    <div class="form-btn">
                        <button class="btn-blue" id="institutionGradeSearchButton">查询</button>
                        <button class="btn-blue" id="institutionGradeResetButton">重置</button>
                    </div>
                </form>


                <div id="institutionGradetable">
                    <div id="zds-institutionGrade-table"
                         class="zui-datagrid table-scroll"
                         zdata-options='{
                     "url":"<z:ukey key="com.zdsoft.finance.institutionGradeList" context="admin"/>&jsoncallback=?",
                     "singleSelect":true,
                     "pagination":true,
                     "idField":"id",
                     "tableCls":"table-index",
                     "dbclickEditRow":false}'>
                        <table>
                            <thead>
                            <tr>
                                <th data-options="field:institutionName" >机构</th>
                                <th data-options="field:finalGradeName" formatter="finalGradeNameFuntion">终评</th>
                                <th data-options="field:originalGradeName" formatter="originalGradeNameFuntion">原评级</th>
                                <th data-options="field:productParentName" formatter="productParentNameFuntion">产品分类</th>
                                <th data-options="field:productChildName" formatter="productChildNameFuntion">子产品</th>
                                <th data-options="field:authAmount" formatter="authAmountFuntion">额度(元)</th>
                                <th data-options="field:institutionCode" formatter="formatFuntion">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.checkbox', 'zd/jquery.zds.loading', 'zd/switch',
                'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form',
                'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter', 'zd/bothselecter', 'zd/jquery.zds.button'],
            function ($, CALLBACK, Loading, Switch, DropDown, Filter, Check, Zdialog, ZUI_MESSAGE_CLIENT) {

                CALLBACK.formatFuntion = function (index, rowData) {
                    var html = '<a title="查看" class="handler-icon icon-btn00 c-blue"  onclick="institutionGradeViewData"></a>';
                    html += '<a title="编辑" class="handler-icon icon-btn22 c-blue"  onclick="institutionGradeEditData"></a>';
                    return html;
                };
                CALLBACK.finalGradeNameFuntion = function(index,rowData){
                    if(rowData==null||index.productChildId==null){
                        return "";
                    }else{
                        return rowData;
                    }
                };
                CALLBACK.originalGradeNameFuntion = function(index,rowData){
                    if(rowData==null||index.productChildId==null){
                        return "";
                    }else{
                        return rowData;
                    }
                };
                CALLBACK.productParentNameFuntion = function(index,rowData){
                    if(rowData==null){
                        return "";
                    }else{
                        return rowData;
                    }
                };
                CALLBACK.productChildNameFuntion = function(index,rowData){
                    if(rowData==null){
                        return "";
                    }else{
                        return rowData;
                    }
                };
                CALLBACK.authAmountFuntion = function(index,rowData){
                    if(rowData==null){
                        return "";
                    }else{
                        return rowData;
                    }
                };
                //查看
                CALLBACK.institutionGradeViewData = function(index, rowData, row, thisobj){
                    //机构编号
                    var institutionCode=rowData.institutionCode;
                    //机构名称
                    var institutionName=rowData.institutionName;

                    var institutionEdit = '<z:ukey key="com.zdsoft.finance.jumpEditPage" context="admin"/>&type=view';
                    ZDS_MESSAGE_CLIENT.openMenuLink('机构评级编辑','机构评级编辑',institutionEdit + "&institutionCode="+institutionCode+"&institutionName="+institutionName);
                }
                //编辑
                CALLBACK.institutionGradeEditData = function (index, rowData, row, thisobj) {
                    //机构编号
                    var institutionCode=rowData.institutionCode;
                    //机构名称
                    var institutionName=rowData.institutionName;

                    var institutionEdit = '<z:ukey key="com.zdsoft.finance.jumpEditPage" context="admin"/>&type=edit';
                    ZDS_MESSAGE_CLIENT.openMenuLink('机构评级编辑','机构评级编辑',institutionEdit + "&institutionCode="+institutionCode+"&institutionName="+institutionName+"&id="+rowData.id);
                };

                //自刷新
                ZDS_MESSAGE_CLIENT.refreshThis = function () {
                    //var param = $("#institutionGradeSearchFrom").serializeArray();
                    $("#zds-institutionGrade-table").ZTable("reload");
                };

                //查询
                $("#institutionGradeSearchButton").click(function(){
                    var param = $("#institutionGradeSearchFrom").serializeArray();
                    $("#zds-institutionGrade-table").ZTable("reload", param);
                });
                //重置
                $("#institutionGradeResetButton").click(function(){
                    $("#institutionCode").ZCombobox("setValue","");
                });





                //下拉框初始化
                $("#institutionCode").ZCombobox();

                $.ZUI.initGrid("#institutionGradeAllContentDiv");
                $.ZUI.initForms("#institutionGradeSearchFrom");

            });

</script>

</body>
</html>