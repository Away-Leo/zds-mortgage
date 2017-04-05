<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<%@ include file='../../common/common_js.jsp' %>
<title>贷后监控</title>
</head>
<body>
<div class="frm-content">
    <!-- 查询区域 -->
    <div class="page-box">
        <div class="p10">
            <form id="search_from" class="zui-form form-search" method="post"
                  enctype="multipart/form-data">
                <dl class="form-item">
                    <dt class="title">案件号：</dt>
                    <dd class="detail">
                        <input class="zui-input" id="caseApplyCode" name="caseApplyCode">
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">主借人：</dt>
                    <dd class="detail">
                        <input class="zui-input" id="mainBorrower" name="mainBorrower">
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">产品分类：</dt>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" type="hidden" id="productTypeId"
                               data-width="94"
                               name="productTypeId"
                               data-url="<z:ukey key='com.zdsoft.finance.authGrade.getParentProduct' context='admin'/>&jsoncallback=?"
                               data-callback="productParentIdChange"
                               data-height="300"
                               data-defaultvalue=""
                               data-valuefield="id" data-textfield="text">
                    </dd>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" type="hidden" id="productSubtypeId"
                               name="productSubtypeId" data-width="94"
                               data-url="<z:ukey key='com.zdsoft.finance.authGrade.getProductByParentId' context='admin'/>&jsoncallback=?"
                               data-callback=""
                               data-height="300"
                               data-defaultvalue=""
                               data-valuefield="id" data-textfield="text">
                    </dd>

                </dl>
                <dl class="form-item">
                    <dt class="title">机构：</dt>
                    <dd class="detail">
                        <input class="zui-input" id="mechanismName" name="mechanismName">
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">案件状态：</dt>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" type="hidden" id="stage"
                               name="stage"
                               data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0092"
                               data-height="300"
                               data-defaultvalue=""
                               data-valuefield="id" data-textfield="text">
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">是否出险：</dt>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" type="hidden" id="isLoss"
                               name="isLoss"
                               data-data="[{'id':'0','text':'否'},{'id':'1','text':'是'}]"
                               data-defaultvalue=""
                               data-valuefield="id" data-textfield="text">
                    </dd>
                </dl>
                <dl class="form-btn">
                    <button type="button" class="btn-search-blue" id="btn-search">查询</button>
                    <button type="button" class="btn-search-gray" id="btn-reset">重置</button>
                </dl>
            </form>
        </div>
    </div>
    <!-- 案件列表 -->
    <!-- begin -->
    <div class="page-box">
        <div class="p10">
            <div id="afterMonitorTable">
                <div id="zds-afterMonitor-table"
                     class="zui-datagrid table-scroll"
                     zdata-options='{
                     "url":"<z:ukey key="com.zdsoft.finance.afterLoan.afterMonitor.afterMonitorListData" context="admin"/>&jsoncallback=?",
                     "singleSelect":false,
                     "pagination":true,
                     "idField":"id",
                     "tableCls":"table-index",
                     "dbclickEditRow":false,
                     "toolbar":"#btn-applylist"
                     }'>
                    <table>
                        <thead>
                        <tr>
                            <th data-options="field:mechanismName">机构</th>
                            <th data-options="field:caseApplyCode">案件号</th>
                            <th data-options="field:mainBorrower">主借人</th>
                            <th data-options="field:phoneNumber">联系方式</th>
                            <th data-options="field:productTypeName">产品分类</th>
                            <th data-options="field:productSubtypeName">子产品</th>
                            <th data-options="field:floorAge">楼龄(年)</th>
                            <th data-options="field:contractAmount" formatter="formatCurrencyFunction">贷款金额(元)</th>
                            <th data-options="field:overdueAmount" formatter="formatCurrencyFunction">逾期金额(元)</th>
                            <th data-options="field:overdueDate" formatter="formatterCheckedDate">逾期日期</th>
                            <th data-options="field:overdueDay">逾期天数(天)</th>
                            <th data-options="field:lastMonitorDate" formatter="formatterCheckedDate">上次重估时间</th>
                            <th data-options="field:isLoss">是否出险</th>
                            <th data-options="field:stageName">案件状态</th>
                            <th data-options="field:id" formatter="formatFuntion">操作</th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <!-- 与table中的一起使用才有效"toolbar":"#btn-applylist" ，写handler回调函数实现增删改功能-->
                <div id="btn-applylist" style="width: 100%">
                    <a class="zui-toolbar" id="btn-add" text="主动查询" iconCls="icon-btn02" buttonCls="btn-blue"
                       handler="activeSearch"></a>
                </div>
            </div>
        </div>
        <!-- end -->
    </div>
    <div id="contactsDialog" style="display: none">
        <script type="text/javascript">
            seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/tools', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter', 'zd/jquery.zds.combotree', 'zd/jquery.zds.checkbox'], function ($, CALLBACK, ZTOOL) {

                //金额转换
                CALLBACK.formatCurrencyFunction = function(row,value){
                    return ZTOOL.formatCurrency(value+"");
                };
                CALLBACK.productTypeChange = function (v, t) {
                    $("#productSubtype").ZCombobox({
                        valueField: "id",
                        textField: "value",
                        url: productUrl + "&categoryId=" + v
                    });
                };
                //主动查询
                CALLBACK.activeSearch = function (index, rowData) {
                    var rows = $('#zds-afterMonitor-table').ZTable("getSelections");
                    if (rows.length == 0) {
                        $.ZMessage.info("提示", "请至少选择一条记录！", function () {
                        });
                        return false;
                    }
                    debugger;
                    //逾期金额
                    var overAmount = 0;
                    //所选ID
                    var ids = "";
                    for(var i=0;i<rows.length;i++){
                        var overdueAmount = rows[i].overdueAmount;
                        overAmount += parseFloat(overdueAmount);
                        ids += rows[i].id + ",";
                    }
                    ids = ids.substr(0, ids.length - 1);
                    ZDS_MESSAGE_CLIENT.openMenuLink('initiativeSearch', '主动查询',
                            '<z:ukey key="com.zdsoft.finance.afterMonitor.initiativeSearchPage" context="admin"/>&ids=' + ids + '&overAmount=' + overAmount + '&overCount=' + rows.length);
                };
                CALLBACK.formatFuntion = function () {
                    var html = '<a href="javaScript:void(0)" onclick="detailView"><button class="btn-blue">详情</button></a>';
                    return html;
                };
                CALLBACK.detailView = function (index, rowData) {
                    var pathParam = "&caseApplyId=" + rowData.id + "&controlType=after";
                    ZDS_MESSAGE_CLIENT.openMenuLink('viewCaseApply', '案件详情', '<z:ukey key="com.zdsoft.finance.afterloan.base.initCaseApplyDetail" context="admin"/>' + pathParam);
                };
                //时间转换
                CALLBACK.formatterCheckedDate = function (row, value) {
                    if (value) {
                        return ZTOOL.strToDate(value);

                    } else {
                        return "";
                    }
                };
                /**
                 * 下拉框联动
                 * */
                CALLBACK.productParentIdChange = function (index, rowData, row, thisobj) {
                    var parentId = index;
                    loadProductChildId(parentId);
                };
                ZDS_MESSAGE_CLIENT.refreshThis = function () {
                    $('#caseList').ZTable("reload", {});
                };
                /**
                 * 下拉数据
                 * @param cataId
                 */
                function loadProductChildId(pId) {
                    var productChildId = $("#productSubtypeId");
                    productChildId.ZCombobox({queryParams: {"parentId": pId}});
                }

                $.ZUI.init();
                //点击查询
                $("#btn-search").click(function () {
                    var formArray = $("#search_from").serializeArray();
                    $("#zds-afterMonitor-table").ZTable("reload", formArray);
                });
                //点击重置
                $("#btn-reset").click(function () {
                    //caseApplyCode mainBorrower productTypeId productSubtypeId mechanismName stage isLoss
                    $("#caseApplyCode").val("");
                    $("#mainBorrower").val("");
                    $("#mechanismName").val("");
                    $("#productSubtypeId").ZCombobox("setValue", "");
                    $("#productTypeId").ZCombobox("setValue", "");
                    $("#stage").ZCombobox("setValue", "");
                    $("#isLoss").ZCombobox("setValue", "");
                });
            });
        </script>
</body>
</html>