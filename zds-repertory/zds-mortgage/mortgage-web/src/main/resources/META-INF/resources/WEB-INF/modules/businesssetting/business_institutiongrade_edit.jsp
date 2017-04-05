<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common_js.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>机构评级编辑</title>
</head>
<body>
<div class="save">
    <c:if test="${type!='view'}">
        <button id="btn-save" class="btn-blue mr10">保存</button>
        <button id="btn-cancel" class="btn-gray mr10">取消</button>
    </c:if>
    <c:if test="${type=='view'}">
        <button id="btn-cancel" class="btn-gray mr10">返回</button>
    </c:if>
</div>
<div id="institutionAllContentDiv">
    <div class="frm-content frm-bottom">
        <div class="page-box">
            <h1 class="page-title">
                机构评级
            </h1>
            <div class="p10">
                <form action="javascript:void(0);" id="institutionSearchFrom"
                      enctype="multipart/form-data" method="post">
                    <input type="hidden" name="id" id="id" value="${orgAuthConn.id}" />
                    <dl class="form-item">
                        <dt class="title">机构：</dt>
                        <dd class="detail">
                            <label>
                                <input class="zui-input zui-disabled" type="text" value="${institutionName}"
                                       disabled="disabled"/>
                            </label>
                            <input type="hidden" id="institutionCode" name="orgIntermediateId" value="${institutionCode}" />
                            <input type="hidden" id="institutionName" name="orgIntermediateName" value="${institutionName}" />
                        </dd>
                    </dl>

                    <dl class="form-item">
                        <dt class="title">原评级：</dt>
                        <dd class="detail">
                            <label>
                                <input class="zui-input zui-disabled" type="text" <c:if test="${productParentId != 'null'}"> value="${orgAuthConn.originalGradeName}" </c:if>disabled="disabled"/>
                                <input type="hidden" id="originalGradeCode" name="originalGradeCode" <c:if test="${productParentId != 'null'}"> value="${orgAuthConn.originalGradeCode}"</c:if>/>
                                <input type="hidden" id="originalGradeName" name="originalGradeName" <c:if test="${productParentId != 'null'}"> value="${orgAuthConn.originalGradeName}"</c:if> />
                            </label>
                        </dd>
                    </dl>

                    <dl class="form-item">
                        <dt class="title"><b class="c-red mr5">*</b>终评：</dt>
                        <dd class="detail">
                            <input class="zui-combobox zui-validatebox" type="hidden" id="finalGradeCode" name="authGradeCode" <c:if test="${productParentId != 'null'}"> value="${orgAuthConn.authGradeCode}"</c:if>
                                   data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00111"
                                   data-callback="finalGradeCodeChange"
                                   data-height="300"
                                   data-defaultvalue=""
                                   <c:if test="${type == 'view'}">
                                   data-choose="disable"
                                   </c:if>
                                   data-valuefield="id" data-textfield="text" validate-type="Require">
                            <input type="hidden" id="finalGradeName" name="authGradeName" <c:if test="${productParentId != 'null'}"> value="${orgAuthConn.authGradeName}" </c:if>/>
                        </dd>
                    </dl>
                    <dl class="form-item">
                        <dt class="title">操作人：</dt>
                        <dd class="detail">
                            <label>
                                <input class="zui-input  zui-disabled" type="text" value="${empDto.empNm}"
                                       disabled="disabled"/>
                                <input type="hidden" id="handerName" name="handelerName" value="${empDto.empNm}" />
                                <input type="hidden" id="handerCode" name="handelerCode" value="${empDto.empCd}" />
                            </label>
                        </dd>
                    </dl>

                </form>



            </div>
        </div>
        <div class="page-box">
            <h1 class="page-title">
                机构评级历史记录
            </h1>
            <div class="p10">
                <div id="institutiontable">
                    <div id="zds-institution-table"
                         class="zui-datagrid table-scroll"
                         zdata-options='{
                     "url":"<z:ukey key="com.zdsoft.finance.institutionGrade.findHistoryData" context="admin"/>&jsoncallback=?&institutionCode=${institutionCode}",
                     "singleSelect":true,
                     "pagination":true,
                     "idField":"id",
                     "tableCls":"table-index",
                     "dbclickEditRow":false,
                     "isMergeCell":true,
                     "mergeColField": "originalGradeName",
                     "mergeCol": "originalGradeName"}'>
                        <table>
                            <thead>
                            <tr>
                                <th data-options="field:originalGradeName">原评级</th>
                                <th data-options="field:authGrade.productParentName" formatter="proParentFuntion">产品分类</th>
                                <th data-options="field:authGrade.productChildName" formatter="proChildFuntion" >子产品</th>
                                <th data-options="field:authGrade.authAmount" formatter="proAmountFuntion">额度(元)</th>
                                <th data-options="field:handerName">操作人</th>
                                <th data-options="field:handelTime">操作时间</th>
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
    seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/jquery.zds.validate', 'zd/jquery.zds.combobox', 'zd/jquery.zds.checkbox', 'zd/jquery.zds.loading', 'zd/switch',
                'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form',
                'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter', 'zd/bothselecter', 'zd/jquery.zds.button'],
            function ($, CALLBACK, Loading, Switch, DropDown, Filter, Check, Zdialog, ZUI_MESSAGE_CLIENT) {

                CALLBACK.formatFuntion = function (index, rowData) {
                    var html = '<a title="查看" class="handler-icon icon-btn00 c-blue"  onclick="institutionGradeViewData"></a>';
                    html += '<a title="编辑" class="handler-icon icon-btn22 c-blue"  onclick="institutionGradeEditData"></a>';
                    return html;
                };

                /**
                 * 终评下拉框值改变回调函数
                 * */
                CALLBACK.finalGradeCodeChange = function (index,rowData){
                    $("#finalGradeName").val(rowData);
                };
                CALLBACK.proParentFuntion = function (index,rowData){
                    if(index.authGrade.productParentName!=null){
                        return index.authGrade.productParentName;
                    }else{
                        return "";
                    }
                };
                CALLBACK.proChildFuntion = function (index,rowData){
                    if(index.authGrade.productChildName!=null){
                        return index.authGrade.productChildName;
                    }else{
                        return "";
                    }
                };
                CALLBACK.proAmountFuntion = function (index,rowData){
                    if(index.authGrade.authAmount!=null){
                        return index.authGrade.authAmount;
                    }else{
                        return "";
                    }
                };
                /**
                 *保存方法
                 */
                $("#btn-save").click(function(){
                    //验证表单
                    var validate = $.ZUI.validateForm($('#institutionSearchFrom'));
                    if(!validate){
                        return false;
                    }
                    var param=$("#institutionSearchFrom").serializeArray();

                    var url='<z:ukey key="com.zdsoft.finance.institutionGrade.saveOrUpdateInstitution" context="admin" />';
                    $.ajax({
                        url:url,
                        data:param,
                        type:"post",
                        success:function(data){
                            if(data.resultStatus==0){
                                $.ZMessage.info("成功", "数据操作成功", function () {
                                    ZDS_MESSAGE_CLIENT.refreshOpenner();
                                    setTimeout(function(){
                                        ZDS_MESSAGE_CLIENT.closeSelf();
                                    },200);
                                });
                            }else{
                                $.ZMessage.error("错误","数据操作出错"+data.msg,function(){
                                    return false;
                                })
                            }
                        },
                        error:function(data){
                            $.ZMessage.error("错误","数据操作出错"+data.msg,function(){
                                return false;
                            })
                        }
                    });
                });

                /**
                 * 返回按钮
                 */
                $("#btn-cancel").click(function(){
                    ZDS_MESSAGE_CLIENT.refreshOpenner();
                    setTimeout(function(){
                        ZDS_MESSAGE_CLIENT.closeSelf();
                    },200);
                });
                //下拉框初始化
                $("#finalGradeCode").ZCombobox();

                $.ZUI.initGrid("#institutionAllContentDiv");
                $.ZUI.initForms("#institutionSearchFrom");

            });

</script>

</body>
</html>