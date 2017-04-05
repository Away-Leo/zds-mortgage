<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common_js.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path=request.getContextPath();
%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>资料清单二维码</title>
</head>
<body>
<div class="save">
    <button id="btn-print" class="btn-blue mr10">打印</button>
    <button id="btn-return" class="btn-gray mr10">返回</button>
</div>
<div class="frm-content frm-bottom" id="twoCodeContentDiv">
    <div class="page-box">
        <div class="p10">
            <form class="zui-form form-search" id="twoCodeSearchForm" action='<z:ukey key="com.zdsoft.finance.twoCodePage"  context="admin"/>'
                  zdata-options={"url":"http://192.168.33.137:8880/FindAllClints?jsoncallback=?","callBack":"saveCallBack"}>
                <input type="hidden" id="productCode" name="productCode" value="${productCode}" />
                <dl class="form-item">
                    <dt class="title">资料类型：</dt>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" id="materiaTypeCode" type="hidden"
                               name="materiaTypeCode"
                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00150"
                               data-callback="materiaTypeCodeChange"
                               data-height="300"
                               data-defaultvalue="${materiaTypeCode}"
                               data-valuefield="id" data-textfield="text" >
                        <input type="hidden" id="materiaTypeName" name="materiaTypeName" />
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">资料名称：</dt>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" id="materiaCode" type="hidden" name="materiaCode"
                        <%--com.zdsoft.finance.getMateriaSimpleCode--%>
                               data-url="<z:ukey key="com.zdsoft.finance.getMateriaSimpleCode" context="admin"/>&jsoncallback=?"
                               data-callback="materiaCodeChange"
                               data-height="300"
                               data-defaultvalue="${materiaCode}"
                               data-valuefield="id" data-textfield="text">
                        <input type="hidden" id="materiaName" name="materiaName" />
                    </dd>
                </dl>

                <div class="form-btn">
                    <button  class="btn-blue" id="btn-query">查询</button>
                    <button type="button" class="btn-gray" id="btn-reset" <%--onclick="resetAll()"--%>>重置</button>
                </div>
            </form>

        </div>
    </div>
    <div class="page-box">
        <div class="p10">
            <div id="twoCodeContentContentDiv" class="table-scroll">
                <table class="table-index scroll">
                    <tr>
                        <th>资料类型</th>
                        <th colspan="8">资料名称</th>
                    </tr>
                    <c:forEach items="${data['YWDM0015001']}" var="d" varStatus="v">
                        <tr>
                            <c:if test="${v.index==0}"><td rowspan="${fn:length(data['YWDM0015001'])}" style="width: 11%">客户资料</td></c:if>
                            <c:forEach items="${d}" var="rl" varStatus="lv">
                                <td style="width: 9%;height: 70px">
                                    <span>${rl['name']}</span>
                                    <img width="100%" src="<%=path%>/${rl['path']}">
                                </td>
                            </c:forEach>
                            <c:if test="${fn:length(d)<8}">
                                <c:forEach begin="0" end="${7-fn:length(d)}">
                                    <td style="width: 9%;height: 70px"></td>
                                </c:forEach>
                            </c:if>
                        </tr>
                    </c:forEach>
                    <c:forEach items="${data['YWDM0015002']}" var="d" varStatus="v">
                        <tr>
                            <c:if test="${v.index==0}"><td rowspan="${fn:length(data['YWDM0015002'])}" style="width: 11%">合同类</td></c:if>
                            <c:forEach items="${d}" var="rl" varStatus="lv">
                                <td style="width: 9%;height: 70px">
                                    <span>${rl['name']}</span>
                                    <img width="100%" src="<%=path%>/${rl['path']}">
                                </td>
                            </c:forEach>
                            <c:if test="${fn:length(d)<8}">
                                <c:forEach begin="0" end="${7-fn:length(d)}">
                                    <td style="width: 9%;height: 70px"></td>
                                </c:forEach>
                            </c:if>
                        </tr>
                    </c:forEach>
                    <c:forEach items="${data['YWDM0015003']}" var="d" varStatus="v">
                        <tr>
                            <c:if test="${v.index==0}"><td rowspan="${fn:length(data['YWDM0015003'])}" style="width: 11%">内审文件夹</td></c:if>
                            <c:forEach items="${d}" var="rl" varStatus="lv">
                                <td style="width: 9%;height: 70px">
                                    <span>${rl['name']}</span>
                                    <img width="100%" src="<%=path%>/${rl['path']}">
                                </td>
                            </c:forEach>
                            <c:if test="${fn:length(d)<8}">
                                <c:forEach begin="0" end="${7-fn:length(d)}">
                                    <td style="width: 9%;height: 70px"></td>
                                </c:forEach>
                            </c:if>
                        </tr>
                    </c:forEach>
                    <c:forEach items="${data['YWDM0015004']}" var="d" varStatus="v">
                        <tr>
                            <c:if test="${v.index==0}"><td rowspan="${fn:length(data['YWDM0015004'])}" style="width: 11%">权证类</td></c:if>
                            <c:forEach items="${d}" var="rl" varStatus="lv">
                                <td style="width: 9%;height: 70px">
                                    <span>${rl['name']}</span>
                                    <img width="100%" src="<%=path%>/${rl['path']}">
                                </td>
                            </c:forEach>
                            <c:if test="${fn:length(d)<8}">
                                <c:forEach begin="0" end="${7-fn:length(d)}">
                                    <td style="width: 9%;height: 70px"></td>
                                </c:forEach>
                            </c:if>
                        </tr>
                    </c:forEach>
                    <c:forEach items="${data['YWDM0015005']}" var="d" varStatus="v">
                        <tr>
                            <c:if test="${v.index==0}"><td rowspan="${fn:length(data['YWDM0015005'])}" style="width: 11%">银行信息类</td></c:if>
                            <c:forEach items="${d}" var="rl" varStatus="lv">
                                <td style="width: 9%;height: 70px">
                                    <span>${rl['name']}</span>
                                    <img width="100%" src="<%=path%>/${rl['path']}">
                                </td>
                            </c:forEach>
                            <c:if test="${fn:length(d)<8}">
                                <c:forEach begin="0" end="${7-fn:length(d)}">
                                    <td style="width: 9%;height: 70px"></td>
                                </c:forEach>
                            </c:if>
                        </tr>
                    </c:forEach>
                    <c:forEach items="${data['YWDM0015006']}" var="d" varStatus="v">
                        <tr>
                            <c:if test="${v.index==0}"><td rowspan="${fn:length(data['YWDM0015006'])}" style="width: 11%">其他</td></c:if>
                            <c:forEach items="${d}" var="rl" varStatus="lv">
                                <td style="width: 9%;height: 70px">
                                    <span>${rl['name']}</span>
                                    <img width="100%" src="<%=path%>/${rl['path']}">
                                </td>
                            </c:forEach>
                            <c:if test="${fn:length(d)<8}">
                                <c:forEach begin="0" end="${7-fn:length(d)}">
                                    <td style="width: 9%;height: 70px"></td>
                                </c:forEach>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<script>
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/tools', 'zd/jquery.zds.combobox', 'zd/jquery.zds.checkbox', 'zd/jquery.zds.loading', 'zd/switch',
                'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form',
                'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter', 'zd/bothselecter', 'zd/jquery.zds.button'],
            function ($, CALLBACK, ZTOOL, Loading, Switch, DropDown, Filter, Check, Zdialog, ZUI_MESSAGE_CLIENT) {

                //资料类型下拉框
                CALLBACK.materiaTypeCodeChange = function (index, rowData) {
                    $("#materiaTypeName").val(rowData);
                    loadMateriaCd(index);
                };

                //资料名称下拉框
                CALLBACK.materiaCodeChange = function (index, rowData, row, thisobj) {
                    $("#materiaName").val(rowData);
                };

                CALLBACK.formatFuntion = function (index, rowData) {
                    var html = '<a title="编辑" class="handler-icon icon-btn01" buttonCls="btn-blue" onclick="editData"></a>';
                    html += '<a title="删除" class="handler-icon icon-btn02 disabled" buttonCls="btn-blue" onclick="deleteData" ></a>';
                    return html;
                };

                // materiaCode
                $("#materiaTypeCode").ZCombobox();
                $("#materiaCode").ZCombobox();

                //btn-query
                $("#btn-query").click(function(){
                    $("#twoCodeSearchForm").submit();
                });
                //materiaTypeCode materiaCode
                function resetAll(){
                    $("#materiaTypeCode").ZCombobox("setValue","");
                    setTimeout(function(){
                        $("#materiaCode").ZCombobox("setValue","");
                    },200);
                }

                $("#btn-reset").click(function () {
                    $("#materiaTypeCode").ZCombobox("setValue", "");
                    setTimeout(function () {
                        $("#materiaCode").ZCombobox("setValue", "");
                    }, 200);
                });
                /**
                 * 加载资料名称下拉数据
                 * @param cataId
                 */
                function loadMateriaCd(cataId) {
                    var materiaCode = $("#materiaCode");
                    materiaCode.ZCombobox({queryParams: {"codeCategoryId": cataId}});
                }

                $("#btn-print").click(function(){
                    /*var param=$("#twoCodeContentContentDiv").html();
                     /!*$("body").append("<form id='printFrom' class='zui-form mt15' method='post' action='<z:ukey key="com.zdsoft.finance.printPage"/>&htmlContent="+param+"'  /></form>");
                     $("#printFrom").submit();*!/
                     window.open('<z:ukey key="com.zdsoft.finance.printPage" context="admin"/>&htmlContent='+param)*/
                    window.print();
                });

                $.ZUI.initGrid("#twoCodeContentDiv");
                $.ZUI.initForms("#twoCodeSearchForm");
            });

</script>

</body>
</html>