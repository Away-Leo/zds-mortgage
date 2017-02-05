<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common_js.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>导出Excel</title>
</head>
<body id="body">
<div class="page-box">
    <div class="page-title">查询信息</div>
    <div id="search" class="p5">
        <form id="searchProductForm" class="zui-form mt15" method="post">
            <dl class="form-item">
                <dt class="title">测试类别:</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-combobox zui-validatebox" validate-type="Length[0-50]" id="typeCd"
                               type="hidden" name="typeCd" value=""
                        <%--data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0033"--%>
                               data-valuefield="fullcode" data-textfield="name">
                    </label>
                </dd>
            </dl>

            <dl class="form-item">
                <dt class="title">测试名称:</dt>
                <dd class="detail">
                    <label>
                        <input class="zui-input zui-validatebox" validate-type="Length[0-255]" type="text" id="name"
                               name="name" value="">
                    </label>
                </dd>
            </dl>

            <dl class="form-item">
                <dt class="title">启用状态:</dt>
                <dd class="detail">
                    <input class="zui-combobox zui-validatebox" validate-type="Length[0-50]" id="isEffect" type="hidden"
                           data-data="[{'id':'true','text':'是'},{'id':'false','text':'否'}]"
                           data-valuefield="id" data-textfield="text" data-defaultvalue="true" name="isEffect">
                </dd>
            </dl>
        </form>
        <div class="form-btn">
            <button class="btn-blue" id="searchProduct">查询</button>
            <button class="btn-gray" id="export">导出Excel</button>
        </div>
    </div>
</div>

<div class="page-box">
    <div class="page-title">测试列表</div>
    <div class="p10">
        <div id="tb-product" class="zui-datagrid"
             zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.export.exportTestData" context="admin"/>&jsoncallback=?","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#btn-function"}'>
            <table id="table_content" >
                <thead>
                <tr>
                    <!-- <th data-options="field:code">产品编号</th> -->
                    <th data-options="field:name">名字</th>
                    <th data-options="field:age">年龄</th>
                    <th data-options="field:address">地址</th>
                    <th data-options="field:phone">电话</th>
                    <th data-options="field:country">国家</th>
                    <th data-options="field:tall">身高</th>
                    <th data-options="field:weight">体重</th>
                    <th data-options="field:profession">职业</th>
                    <th data-options="field:company">公司</th>
                </tr>
                </thead>
            </table>
        </div>
        <%--<div id="btn-function">
            <a class="zui-toolbar" id="btn-add" text="新增" buttonCls="btn-blue" handler="addProduct"></a>
            <a class="zui-toolbar" id="btn-copy" text="复制" buttonCls="btn-blue" handler="copyProduct"></a>
        </div>--%>
    </div>
</div>

<script type="text/javascript">
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'
            ]
            , function ($, CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {

                //初始化
                $.ZUI.init();

                $("#export").click(function(){
                    var url="<z:ukey key="com.zdsoft.finance.toExcel" context="admin"/>&jsoncallback=?&fileName=测试导出Excel";
                    var param=$("table").html();
                    $("form").remove("#exportFrom");
                    $("body").append("<form id='exportFrom'  method='post' action='"+url+"' accept-charset='utf-8'><input type='hidden' id='htmlContent' name='htmlContent' value='"+param+"' /></form>");
                    $("#exportFrom").submit();
                });


            });
</script>
</body>
</html>