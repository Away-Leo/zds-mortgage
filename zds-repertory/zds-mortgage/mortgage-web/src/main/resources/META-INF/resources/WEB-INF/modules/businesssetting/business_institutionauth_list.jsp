<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common_js.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="z" uri="http://www.zdsoft.cn/tags" %>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <title>机构授权查询</title>
</head>
<body>
<div id="authAllContentDiv">
    <div class="frm-content frm-bottom">
        <div class="page-box">
            <div class="p10">
                <form class="zui-form form-search" action="javascript:void(0);" id="authSearchFrom">

                    <dl class="form-item">
                        <dt class="title">机构：</dt>
                        <dd class="detail">
                            <input class="zui-combobox zui-validatebox" type="hidden" id="institutionCode"
                                   name="institutionCode"
                                   data-url="<z:ukey key="com.zdsoft.finance.institutionGrade.findAllOrgSimpleCode" context="admin"/>&jsoncallback=?"
                                   data-callback=""
                                   data-height="300"
                                   data-defaultvalue=""
                                   data-valuefield="id" data-textfield="text">
                        </dd>
                    </dl>

                    <dl class="form-item">
                        <dt class="title">终评：</dt>
                        <dd class="detail">
                            <input class="zui-combobox zui-validatebox" type="hidden" id="finalGradeCode"
                                   name="finalGradeCode"
                                   data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00111"
                                   data-callback="finalGradeCodeChange"
                                   data-height="300"
                                   data-defaultvalue=""
                                   data-valuefield="id" data-textfield="text">
                            <input type="hidden" id="finalGradeName" name="finalGradeName" />
                        </dd>
                    </dl>
                    <dl class="form-item">
                        <dt class="title">产品分类：</dt>
                        <dd class="detail">
                            <input class="zui-combobox zui-validatebox" type="hidden" id="productParentId"
                                   data-width="94"
                                   name="productParentId"
                                   data-url="<z:ukey key='com.zdsoft.finance.authGrade.getParentProduct' context='admin'/>&jsoncallback=?"
                                   data-callback="productParentIdChange"
                                   data-height="600"
                                   data-defaultvalue=""
                                   data-valuefield="id" data-textfield="text">
                        </dd>
                        <dd class="detail">
                            <input class="zui-combobox zui-validatebox" type="hidden" id="productChildId"
                                   name="productChildId" data-width="94"
                                   data-url="<z:ukey key='com.zdsoft.finance.authGrade.getProductByParentId' context='admin'/>&jsoncallback=?"
                                   data-callback=""
                                   data-height="600"
                                   data-defaultvalue=""
                                   data-valuefield="id" data-textfield="text">
                        </dd>

                    </dl>
                    <dl class="form-item">
                        <dt class="title">评级人：</dt>
                        <dd class="detail">
                            <input class="zui-input zui-validatebox" type="text" id="handerName" name="handerName" />
                                  
                        </dd>
                    </dl>

                    <div class="form-btn">
                        <button class="btn-blue" id="authSearchButton">查询</button>
                        <button class="btn-gray" id="authResetButton">重置</button>
                    </div>
                </form>


                <div id="authtable">
                    <div id="zds-auth-table"
                         class="zui-datagrid table-scroll"
                         zdata-options='{
                     "url":"<z:ukey key="com.zdsoft.finance.institutionGrade.institutionAuthList" context="admin"/>&jsoncallback=?",
                     "singleSelect":true,
                     "pagination":true,
                     "idField":"id",
                     "tableCls":"table-index",
                     "dbclickEditRow":false
                     }'>
                        <table>
                            <thead>
                            <tr>
                                <th data-options="field:institutionName">机构</th>
                                <th data-options="field:finalGradeName">终评</th>
                                <th data-options="field:originalGradeName">原评级</th>
                                <th data-options="field:productParentName">产品分类</th>
                                <th data-options="field:productChildName">子产品</th>
                                <th data-options="field:authAmount" formatter="authAmountFomatter">额度(元)</th>
                                <th data-options="field:handerName">评级人</th>
                                <th data-options="field:handelTime" >评级时间</th>
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
    seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/tools', 'zd/jquery.zds.combobox', 'zd/jquery.zds.checkbox', 'zd/jquery.zds.loading', 'zd/switch',
                'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form',
                'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter', 'zd/bothselecter', 'zd/jquery.zds.button', 'zd/completer'],
            function ($, CALLBACK,ZTOOL, Loading, Switch, DropDown, Filter, Check, Zdialog, ZUI_MESSAGE_CLIENT) {

                /**
                 * 对话框下拉框-产品大类
                 * */
                CALLBACK.productParentIdChange = function(index, rowData, row, thisobj){
                    var productChildId = $("#productChildId");
                    productChildId.ZCombobox({queryParams: {"parentId": index}});
                };
                //金额格式化
                CALLBACK.authAmountFomatter = function(index,rowData){
                    return ZTOOL.formatCurrency(rowData+"");
                };
                //查询
                $("#authSearchButton").click(function(){
                    var param = $("#authSearchFrom").serializeArray();
                    $("#zds-auth-table").ZTable("reload", param);
                });
                //重置
                $("#authResetButton").click(function(){
                    $("#institutionCode").ZCombobox("setValue","");
                    $("#finalGradeCode").ZCombobox("setValue","");
                    $("#productParentId").ZCombobox("setValue","");
                    setTimeout(function () {
                        $("#productChildId").ZCombobox("setValue","");
                    },100);
                    $("#handerCode").ZCombobox("setValue","");
					$('#handerName').val('');
                });

                //下拉框初始化
                $("#institutionCode").ZCombobox();
                $("#finalGradeCode").ZCombobox();
                $("#productParentId").ZCombobox();
                $("#productChildId").ZCombobox();
                $("#handerCode").ZCombobox();

                $.ZUI.initGrid("#authAllContentDiv");
                $.ZUI.initForms("#authSearchFrom");

                
            });

</script>

</body>
</html>