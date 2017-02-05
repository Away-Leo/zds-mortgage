<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>资料清单</title>
</head>
<body>
<div id="materiaListContentDiv">
    <div class="frm-content frm-bottom">
        <div class="page-box">
            <div class="p10">
                <div id="tableDemo">
                    <div id="zds-materia-table"
                         class="zui-datagrid table-scroll"
                         zdata-options='{
                     "url":"<z:ukey key="com.zdsoft.finance.materialListData" context="admin"/>&jsoncallback=?&productCode|E|S=${productCode}&materiaTypeCode,showOrder|OB|S=1",
                     "singleSelect":true,
                     "pagination":true,
                     "idField":"id",
                     "tableCls":"table-index",
                     "dbclickEditRow":false,
                     "toolbar":"#btn-applylistMateria"
                     }'>
                        <table>
                            <thead>
                            <tr>
                                <th data-options="field:materiaTypeName">资料类型</th>
                                <th data-options="field:materiaListAuth" formatter="authpack">默认查看权限</th>
                                <th data-options="field:materiaName">资料名称</th>
                                <th data-options="field:rememberCode">助记码</th>
                                <th data-options="field:rememberNo">数字助记码</th>
                                <th data-options="field:materiaIdentifyName" <%--formatter="idenPack"--%>>资料证明</th>
                                <th data-options="field:id" formatter="formatFuntion">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <!-- 与table中的一起使用才有效"toolbar":"#btn-applylist" ，写handler回调函数实现增删改功能-->
                    <div id="btn-applylistMateria" style="width: 100%">
                        <a class="zui-toolbar" id="btn-add" text="新增" iconCls="icon-btn08" buttonCls="btn-blue"
                           handler="addDialogOpen"></a>
                        <a class="zui-toolbar" id="btn-moveUp" text="上移" iconCls="icon-btn30" buttonCls="btn-blue"
                           handler="tableRowMoveUp"></a>
                        <a class="zui-toolbar" id="btn-moveDown" text="下移" iconCls="icon-btn30" buttonCls="btn-blue"
                           handler="tableRowMoveDown"></a>
                        <a class="zui-toolbar" id="btn-twoBarCodes" text="二维码打印" iconCls="icon-btn30"
                           buttonCls="btn-blue"
                           handler="twoCode"></a>
                        <a class="zui-toolbar" id="btn-auth" text="设置默认查看权限" iconCls="icon-btn30" buttonCls="btn-blue"
                           handler="authDialogOpen"></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="dialogAdd" style="display: none">
        <div id="dialogAddFormDiv">
            <form id="materiaDialogAddForm" class="zui-form" zdata-options={"url":"www.zds.com"}>
                <input type="hidden" name="productCode" value="${productCode}"/>
                <input type="hidden" name="productName" value="${productName}"/>
                <input type="hidden" name="id" id="id"/>
                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>资料类型：</dt>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" id="materiaTypeCode" type="hidden"
                               name="materiaTypeCode"
                               data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=materiaClass"
                               data-callback="materiaTypeCodeChange"
                               data-height="270"
                               data-valuefield="id" data-textfield="text" validate-type="Require">
                        <input type="hidden" id="materiaTypeName" name="materiaTypeName" />
                    </dd>
                </dl>

                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>资料名称：</dt>
                    <dd class="detail">
                        <input class="zui-combobox zui-validatebox" id="materiaCode" type="hidden" name="materiaCode"
                               data-url="<z:ukey key="com.zdsoft.finance.getMateriaSimpleCode" context="admin"/>&jsoncallback=?"
                               data-callback="materiaCodeChange"
                               data-height="270"
                               data-valuefield="id" data-textfield="text" validate-type="Require">
                        <input type="hidden" id="materiaName" name="materiaName" />
                    </dd>
                </dl>

                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>助记码：</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input zui-validatebox" type="text" validate-type="Require" id="rememberCode"
                                   name="rememberCode"/>
                        </label>
                    </dd>
                </dl>
                <dl class="form-item">
                    <dt class="title">数字助记码：</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input zui-validatebox" type="text" validate-type="Number" id="rememberNo"
                                   name="rememberNo"/>
                        </label>
                    </dd>
                </dl>


                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>资料证明：</dt>
                    <dd class="detail">
                        <input class="zui-checkbox zui-validatebox" id="materiaIdentifyCkeck" type="hidden"
                               data-defaultvalue=""
                               data-multiple="true"
                               data-callback="materiaIdentifyChange"
                               data-data="[{'id':'0','text':'原件'},{'id':'1','text':'复印件'},{'id':'2','text':'照片件'}]"
                               data-valuefield="id" data-textfield="text" validate-type="Require">
                        <input type="hidden" id="materiaIdentifyName" name="materiaIdentifyName" />
                        <input type="hidden" id="materiaIdentify" name="materiaIdentify" />
                    </dd>
                </dl>
            </form>
        </div>
    </div>

    <div id="dialogauth" style="display: none">
        <input type="hidden" id="productCode" name="productCode" value="${productCode}" />
        <input type="hidden" id="materiaListId" name="productCode"/>
        <div class="page-box">
            <div class="p10">
                <div class="power-box">
                    <div class="power-side">
                        <h1 class="power-side-title">选择流程</h1>
                        <ul id="processSelectBox" class="power-side-list">
                            <%--<li class="selected" data-data='[{selectData: [],unSelectData: [{field:"node4",name:"nodeName4"}]'>房贷流程1</li>--%>
                            <%--<li class="selected" data-data="[{selectData: [],unSelectData: [{field: 'us001', name: '项目变更'}]}]">法务经理</li>--%>
                        </ul>
                    </div>

                    <div id="selectContent" class="power-content">
                        <div class="power-buttom">
                            <a id="saveAll" class="btn-blue mr10" href="javascript:void(0);">保存</a>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<input type="hidden" id="handelType" value="55555" />
<script>
    seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.checkbox', 'zd/jquery.zds.loading', 'zd/switch',
                'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form',
                'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter', 'zd/bothselecter', 'zd/jquery.zds.button'],
            function ($, CALLBACK, Loading, Switch, DropDown, Filter, Check, Zdialog, ZUI_MESSAGE_CLIENT) {


                CALLBACK.formatFuntion = function (index, rowData) {
                    var html = '<a title="编辑" class="handler-icon icon-btn22 c-blue"  onclick="editData"></a>';
                    html += '<a title="删除" class="handler-icon icon-btn12 c-blue"  onclick="deleteData" ></a>';
                    return html;
                };
                //编辑按钮
                CALLBACK.editData = function (index, rowData) {
                    $("#dialogAdd").Zdialog('setTitle','编辑资料');
                    //赋值
                    $("#materiaTypeCode").ZCombobox("setValue", rowData.materiaTypeCode);
                    $("#materiaTypeName").val(rowData.materiaTypeName);
                    //解决联动
                    setTimeout(function(){
                        $("#materiaCode").ZCombobox("setValue", rowData.materiaCode);
                        $("#materiaName").val(rowData.materiaName);
                    },200);
                    $("#rememberCode").val(rowData.rememberCode);
                    $("#rememberNo").val(rowData.rememberNo);
                    $("#materiaIdentifyName").val(rowData.materiaIdentifyName);
                    $("#materiaIdentify").val(rowData.materiaIdentify);
                    //复选框赋值
                    var valueArray = (rowData.materiaIdentify).split(",");
                    var arraysize = valueArray.length;
                    for (var i = 0; i <= arraysize - 1; i++) {
                        $("#materiaIdentifyCkeck").ZCheckbox("setValue", valueArray[i]);
                    }
                    //赋值ID
                    $("#id").val(rowData.id);
                    $("#materiaIdentifyCkeck").ZCheckbox();
                    //alert($("#materiaIdentifyName").val()+"------"+$("#materiaIdentify").val());
                    $("#dialogAdd").Zdialog("open");
                };
                //删除按钮
                CALLBACK.deleteData = function (index, rowData) {
                    $.ZMessage.question("核对","确认删除？",function(){
                        var id = rowData.id;
                        $.ajax({
                            url: '<z:ukey key="com.zdsoft.finance.materiaDeleteById" context="admin"/>&jsoncallBack=?',
                            data: {id: id},
                            type: "post",
                            success: function (data) {
                                if (data.resultStatus == 0) {
                                    $.ZMessage.info("成功", "删除成功", function () {
                                        $('#zds-materia-table').ZTable("reload", {});
                                    });
                                } else {
                                    $.ZMessage.error("错误", "删除失败" + data.msg, function () {
                                        return false;
                                    });
                                }
                            },
                            error: function (data) {
                                $.ZMessage.error("错误", "删除出错" + data, function () {
                                    return false;
                                });
                            }
                        })
                    });
                };
                //资料类型下拉框
                CALLBACK.materiaTypeCodeChange = function (index, rowData) {
                    $("#materiaTypeName").val(rowData);
                    loadMateriaCd(index);
                };
                //资料名称下拉框
                CALLBACK.materiaCodeChange = function (index, rowData,row,thisobj) {
                    $("#materiaName").val(rowData);
                    //回写助记码
                    $("#rememberCode").val(thisobj.rememberCode);
                    $("#rememberNo").val(thisobj.rememberNo);
                };
                //资料证明复选框
                CALLBACK.materiaIdentifyChange = function (index, rowData) {
                    //alert("index="+index+"----rowdada="+rowData);
                    $("#materiaIdentifyName").val("");
                    $("#materiaIdentify").val("");
                    $("#materiaIdentifyName").val(rowData);
                    $("#materiaIdentify").val(index);
                    //alert($("#materiaIdentifyName").val()+"--------"+$("#materiaIdentify").val());
                };
                //新增点击
                CALLBACK.addDialogOpen = function (index, rowData) {
                    $("#id").val("");
                    $("#dialogAdd").Zdialog('setTitle','新增资料');
                    $("#dialogAdd").Zdialog("open");
                };
                //添加权限点击
                CALLBACK.authDialogOpen = function (index, rowData) {
                    var selections = $('#zds-materia-table').ZTable("getSelections");
                    //将资料清单ID回写
                    $("#materiaListId").val(selections[0].id);
                    if (selections == "") {
                        $.ZMessage.warning("提示", "请选中数据", function () {

                        })
                        return false;
                    }
                    $("#dialogauth").Zdialog("open");
                };
                //
                CALLBACK.authpack = function (index, rowData) {
                    if (rowData == "" || rowData == "undefined" || rowData == null) {
                        return "无";
                    }
                };

                //表格下移
                CALLBACK.tableRowMoveDown = function (index, rowData) {
                    $('#zds-materia-table').ZTable("moveDown");
                };
                //表格上移
                CALLBACK.tableRowMoveUp = function (index, rowData) {
                    $('#zds-materia-table').ZTable("moveUp");
                };
                //二维码
                CALLBACK.twoCode = function(index,rowData){
                    var twoUrl = '<z:ukey key="com.zdsoft.finance.twoCodePage" context="admin"/>&productCode=${productCode}';
                    ZDS_MESSAGE_CLIENT.openMenuLink('资料清单二维码','资料清单二维码',twoUrl);
                };

                /**
                 * 加载资料名称下拉数据
                 * @param cataId
                 */
                function loadMateriaCd(cataId) {
                    var materiaCode=$("#materiaCode");
                    materiaCode.ZCombobox({queryParams:{"codeCategoryId":cataId}});
                }

                /**
                 * 验证数据的唯一 相同产品和相同资料大类下不能存在相同资料
                 * */
                function checkOnlyData(){
                    debugger;
                    var materiaCode=$("#materiaCode").val();
                    var materiaTypeCode=$("#materiaTypeCode").val();
                    var productCode='${productCode}';
                    $.ajax({
                        url:'<z:ukey key="com.zdsoft.finance.checkOnlyData" context="admin" />',
                        data:{materiaTypeCode:materiaTypeCode,productCode:productCode,materiaCode:materiaCode},
                        type:'post',
                        success:function(data){
                            if(data.resultStatus==0){//已经存在数据
                                return false;
                            }else{
                                return true;
                            }
                        },
                        error:function(data){
                            $.ZMessage.error("错误", "验证唯一性出错!"+data, function () {
                            });
                            return false;
                        }
                    });
                }
                //新增对话框
                $("#dialogAdd").Zdialog({
                    width: 700, height: 400, closed: true, title: "",
                    buttons: [
                        {
                            id: 'message-btnAdd',
                            text: '确定',
                            buttonCls: 'btn-blue',
                            handler: function () {
                                $.ZUI.initForms('#materiaDialogAddForm');
                                var validate = $.ZUI.validateForm($('#materiaDialogAddForm'));
                                if (!validate) {
                                    $.ZMessage.error("错误", "数据验证失败!", function () {
                                    });
                                    return false;
                                }
                                var params = $('#materiaDialogAddForm').serializeArray();
                                var url = '<z:ukey key="com.zdsoft.finance.materiaAdd" context="admin" />&jsoncallback=? ';
                                debugger;
                                //alert($("#materiaIdentifyName").val()+"--------"+$("#materiaIdentify").val());
                                if(true){
                                    $.ajax({
                                        url: url,
                                        data: params,
                                        type: "post",
                                        success: function (data) {
                                            if (data.resultStatus == 0) {
                                                $.ZMessage.success("提示", "数据操作成功!", function () {
                                                });
                                                $("#dialogAdd").Zdialog("close");
                                                $('#zds-materia-table').ZTable("reload", {});
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
                                }
                            }
                        },
                        {
                            id: 'message-btn',
                            text: '取消',
                            buttonCls: 'btn-gray',
                            handler: function () {
                                $("#materiaIdentifyCkeck").ZCheckbox();
                                //赋值
                                $("#materiaTypeCode").ZCombobox("setValue","");
                                $("#materiaTypeName").val("");
                                $("#rememberCode").val("");
                                $("#rememberNo").val("");
                                $("#materiaIdentifyName").val("");
                                $("#materiaIdentify").val("");

                                //解决联动
                                setTimeout(function(){
                                    $("#materiaCode").ZCombobox("setValue","");
                                    $("#materiaName").val("");
                                    $("#dialogAdd").Zdialog("close");
                                },200);
                            }
                        }
                    ]
                });
                //权限对话框
                $("#dialogauth").Zdialog({
                    width: 800, height: 660, closed: true, title: "设置默认查看权限",
                    buttons: [
                        {
                            id: 'message-btn',
                            text: '确定',
                            buttonCls: 'btn-blue',
                            handler: function () {
                                /*alert('验证表单');
                                 $.ZUI.initForms('#testForm');
                                 $('#dialogForm').submit();
                                 $("#dialogEdit1").Zdialog("close");*/
                            }
                        },
                        {
                            id: 'message-btn',
                            text: '取消',
                            buttonCls: 'btn-gray',
                            handler: function () {
                                $("#dialogauth").Zdialog("close");
                            }
                        }
                    ],
                    onOpen: function () {
                        //todo something
                    }
                });


                //下拉框初始化
                $("#materiaTypeCode").ZCombobox();
                $("#materiaCode").ZCombobox();

                //复选框初始化
                $("#materiaIdentifyCkeck").ZCheckbox();



                /*************************************权限控制模块开始*************************************/
                /**
                 * 权限初始化数据加载
                 **/
                var processs=$("#processSelectBox");
                $.ajax({
                    url:"<z:ukey key='com.zdsoft.finance.findProcessNode' context='admin' />",
                    type:"post",
                    dataType:"json",
                    async:false,
                    success:function(data){

                        processs.empty();
                        for(key in data){
//                                alert(data[key].nodes);
//                                alert(data[key].process.name);
                            debugger;
                            if(key == 0){
                                processs.append("<li class='selected' data-data='[{selectData: [],unSelectData:"+data[key].nodes+"}]'>"+data[key].process.name+"</li> ");
                            }else{
                                processs.append("<li  data-data='[{selectData: [],unSelectData:"+data[key].nodes+"}]'>"+data[key].process.name+"</li> ");
                            }
                        }

                        /*$.ZUI.initGrid("#materiaListContentDiv");
                         $.ZUI.initForms("#materiaDialogAddForm");*/
                    },
                    error:function(data){
                        $.ZMessage.error("错误","请求流程数据出错",function(){
                            return false;
                        });
                    }
                });

                /**
                 * 事件绑定
                 * */
                $('#processSelectBox').on("click","li",function () {
                    $(this).Zbothselecter({
                        valueField: "field",//值字段
                        textField: "name",//文本字段
                        divTarget: $("#selectContent"),
                        /*data: [{
                         selectData: [
                         {field: 's001', name: '项目申请'},
                         {field: 's002', name: '客户资料录入'},
                         {field: 's003', name: '申请审查'},
                         {field: 's004', name: '尽职调查'},
                         {field: 's005', name: '调查资料补充'},
                         {field: 's006', name: '项目审查'},
                         {field: 's007', name: '审查会议'},
                         {field: 's008', name: '项目表决'}],
                         unSelectData: [
                         {field: 'us001', name: '项目变更'},
                         {field: 'us002', name: '法务审核'},
                         {field: 'us003', name: '风险审核'},
                         {field: 'us004', name: '财务部门审核'},
                         {field: 'us005', name: '草拟合同'},
                         {field: 'us006', name: '合同评审'}],
                         selectTitle:'我的拥有权限',
                         unSelectTitle:'我的可用权限'
                         }],*/
                        title: '流程节点',
                        url: 'http://192.168.33.131:8080/zds-base-data-web/admin/employee/findEmployees?jsoncallback=?',
                        queryParams: {param: 'xxx'}
                    });
                    $(this).addClass('selected').siblings().removeClass('selected');
                });
                $('#processSelectBox').find('.selected').trigger('click');
                $('#saveAll').ZButton({
                    id: 'saveAll',
                    text: "保存",
                    buttonCls: 'btn-blue mr10',
                    handler: function () {
                        var opts = $('#processSelectBox').Zbothselecter('getOptions', '#selectContent');
                        debugger;
                        var selectedData=opts.selectData;
                        if(selectedData.length>0){
                            ////productCode materiaListId
                            var param="&dataJsonStr="+JSON.stringify(selectedData);
                            debugger;
                            param+="&productId="+$("#productCode").val();
                            param+="&materiaListId="+$("#materiaListId").val();

                            $.ajax({
                                url:'<z:ukey key="com.zdsoft.finance.saveOrUpdateMateriaListAuth" context="admin" />&jsoncallback=?',
                                data:param,
                                type:'post',
                                success:function(data){
                                    if(data.resultStatus==0){
                                        $.ZMessage.success("成功","编辑节点权限成功",function(){
                                            $("#dialogauth").Zdialog("close");
                                            $('#zds-materia-table').ZTable("reload", {});
                                        })
                                    }else{
                                        $.ZMessage.error("错误","编辑节点权限出错"+data.msg,function(){
                                            $("#dialogauth").Zdialog("close");
                                            $('#zds-materia-table').ZTable("reload", {});
                                        });
                                    }
                                },
                                error:function(data){
                                    $.ZMessage.error("错误","编辑节点权限出错",function(){
                                        $("#dialogauth").Zdialog("close");
                                        $('#zds-materia-table').ZTable("reload", {});
                                    });
                                }
                            });
                        }else{
                            $.ZMessage.info("提示","请选择数据",function(){
                                return false;
                            });
                        }
                    }
                })

                /*************************************权限控制模块结束***************************************/

                $.ZUI.initGrid("#materiaListContentDiv");
                $.ZUI.initForms("#materiaDialogAddForm");

            });

</script>

</body>
</html>