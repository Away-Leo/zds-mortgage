<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common_js.jsp" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>下载历史</title>
</head>
<body>
<div id="downHisAllContentDiv">
    <div class="frm-content frm-bottom">
        <div class="page-box">
            <div class="p10">
                <form class="zui-form form-search" action="javascript:void(0);" id="downHisSearchFrom"
                      zdata-options={"url":"http://192.168.33.137:8880/FindAllClints?jsoncallback=?","callBack":"saveCallBack"}>

                    <dl class="form-item">
                        <dt class="title">所属分类</dt>
                        <dd class="detail">
                            <input class="zui-combobox" id="materiaTypeCode" type="hidden"
                                   name="materiaTypeCode"
                                   data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00150"
                                   data-height="270"
                                   data-valuefield="id" data-textfield="text" >
                        </dd>
                    </dl>
                    <dl class="form-item">
                        <dt class="title">文件名</dt>
                        <dd class="detail">
                            <label>
                                <input class="zui-input" type="text" id="fileName" name="fileName" />
                            </label>
                        </dd>
                    </dl>
                    <dl class="form-item">
                        <dt class="title">下载人</dt>
                        <dd class="detail">
                            <label>
                                <input class="zui-input" type="text" id="downEmpName" name="downEmpName" />
                            </label>
                        </dd>
                    </dl>

                    <div class="form-btn">
                        <button class="btn-blue" id="downHisSearchButton">查询</button>
                        <button class="btn-gray" id="downHisResetButton">重置</button>
                    </div>
                </form>


                <div id="downHistable">
                    <div id="zds-downHis-table"
                         class="zui-datagrid table-scroll"
                         zdata-options='{
                     "url":"<z:ukey key="com.zdsoft.finance.fileStore.downHisListData" context="admin"/>&jsoncallback=?&fileStoreVo.productId=${fileStoreVo.productId}&fileStoreVo.linkCode=${fileStoreVo.linkCode}&fileStoreVo.caseApplyId=${fileStoreVo.caseApplyId}&fileStoreVo.businessId=${fileStoreVo.businessId}",
                     "singleSelect":true,
                     "pagination":true,
                     "idField":"id",
                     "tableCls":"table-index",
                     "dbclickEditRow":false
                     }'>
                        <table>
                            <thead>
                            <tr>
                                <th data-options="field:materiaTypeName">所属分类</th>
                                <th data-options="field:materiaName">类别名称</th>
                                <th data-options="field:fileName">文件名</th>
                                <th data-options="field:documentName">文档名称</th>
                                <th data-options="field:downEmpName,width:10%">下载人</th>
                                <th data-options="field:downFrequency">下载次数</th>
                                <th data-options="field:latestDownDate" formatter="timeFormatter">最近一次下载日期</th>
                                <th data-options="field:sourceName">来源</th>
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
                'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter', 'zd/bothselecter', 'zd/jquery.zds.button'],
            function ($, CALLBACK,ZTOOL, Loading, Switch, DropDown, Filter, Check, Zdialog, ZUI_MESSAGE_CLIENT) {
                CALLBACK.timeFormatter = function (index, rowData) {
                    return ZTOOL.strToDate(rowData);
                };

                //查询按钮
                $("#downHisSearchButton").click(function () {
                    var param = $("#downHisSearchFrom").serializeArray();
                    $("#zds-downHis-table").ZTable("reload", param);
                });
                //重置按钮
                $("#downHisResetButton").click(function () {
                    $("#fileName").val("");
                    $("#downEmpName").val("");
                    $("#materiaTypeCode").ZCombobox("setValue", "");
                });

                //下拉框初始化
                $("#materiaTypeCode").ZCombobox();

                $.ZUI.initGrid("#downHisAllContentDiv");
                $.ZUI.initForms("#downHisSearchFrom");

            });

</script>

</body>
</html>