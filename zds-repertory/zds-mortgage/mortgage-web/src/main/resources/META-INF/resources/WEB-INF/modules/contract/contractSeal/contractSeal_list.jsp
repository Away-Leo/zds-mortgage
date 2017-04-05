<!DOCTYPE html>
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">

    <%@ include file='../../common/common_js.jsp' %>
    <title>案件合同盖章</title>
</head>
<body>
<div class="frm-content">
    <!-- 查询区域 -->
    <div class="page-box">
        <div class="p10">
            <form id="search_from" class="zui-form form-search" method="post"
                  enctype="multipart/form-data">
                <dl class="form-item">
                    <dt class="title">合同编号：</dt>
                    <dd class="detail">
                        <input class="zui-input" id="contractNo" name="contract|contractNo|LK|S">
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">机构：</dt>
                    <dd class="detail">
                        <input class="zui-input" id="orgCd" name="caseApply|Mechanismname|LK|S">
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">产品分类：</dt>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" type="hidden"
                               id="productType" data-width="94" name="caseApply|productTypeId|E|S"
                               data-url="<z:ukey key='com.zdsoft.finance.authGrade.getParentProduct' context='admin'/>&jsoncallback=?"
                               data-callback="productParentIdChange" data-height="300"
                               data-defaultvalue="" data-valuefield="id" data-textfield="text">
                    </dd>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" type="hidden"
                               id="productSubtype" name="caseApply|productSubtypeId|E|S" data-width="94"
                        data-url="<z:ukey key='com.zdsoft.finance.authGrade.getProductByParentId' context='admin'/>&jsoncallback=?"
                        data-callback="" data-height="300" data-defaultvalue=""
                        data-valuefield="id" data-textfield="text">
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">案件状态：</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-combobox zui-validatebox" type="hidden"
                                   id="caseApplyStage" name="caseApply|stage|E|S"
                                   data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0092"
                                   data-callback="" data-height="300" data-defaultvalue=""
                                   data-valuefield="fullcode" data-textfield="name">
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
                 zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.contract.conCaseContractSeal.getContractSealList" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index"}'>
                <table>
                    <tr>
                        <th data-options="field:MECHANISMNAME">机构</th>
                        <th data-options="field:CASEAPPLYCODE">案件号</th>
                        <th data-options="field:PRODUCTTYPENAME">产品分类</th>
                        <th data-options="field:CONTRACTNO">合同编号</th>
                        <th data-options="field:CONTRACTAMOUNT">合同金额</th>
                        <th data-options="field:CONTRACTSTARTDATE" formatter="dateFormatter">合同开始日期</th>
                        <th data-options="field:STAGENAME">案件状态</th>
                        <th data-options="field:id,width:200" formatter="operate">操作</th>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/tools', 'zd/jquery.zds.form',
        'zd/jquery.zds.message', 'zd/jquery.zds.combobox',
        'zd/jquery.zds.table'], function ($, CALLBACK, ZTools) {
        //操作
        CALLBACK.operate = function(index, row) {
            var html = '<a title="申请盖章" onclick="preAdd" class="btn-blue">申请盖章</a>';
            return html;
        };
        CALLBACK.dateFormatter = function(index,value){
            return window.formatDate(index,value);
        }
        //新增
        CALLBACK.preAdd=function(index,row){
            ZDS_MESSAGE_CLIENT.openMenuLink('ContractSeal_add', '合同盖章申请', '<z:ukey key="com.zdsoft.finance.contract.conCaseContractSeal.preAddContractSeal" context="admin"/>&contractId='+row.CONTRACTID);
        }
        /**
         * 下拉框联动
         * */
        CALLBACK.productParentIdChange = function (index, rowData, row, thisobj) {
            var parentId = index;
            loadProductChildId(parentId);
        };
        /**
         * 下拉数据
         * @param cataId
         */
        function loadProductChildId(pId) {
            var productChildId = $("#productSubtype");
            productChildId.ZCombobox({queryParams: {"parentId": pId}});
        }
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
            $('#tb-seal').ZTable("reload", {});
        });
        $.ZUI.init();
    });
</script>
</body>
</html>