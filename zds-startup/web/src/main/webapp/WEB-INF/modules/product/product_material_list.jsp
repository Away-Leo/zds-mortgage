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
                     "url":"<z:ukey key="com.zdsoft.finance.materialListData" context="admin"/>&jsoncallback=?&productId|E|S=${productId}",
                     "singleSelect":true,
                     "pagination":true,
                     "idField":"id",
                     "tableCls":"table-index",
                     "dbclickEditRow":false,
                     "toolbar":"#btn-applylistMateria",
                     "isMergeCell":true,
                     "mergeColField": "materiaTypeName",
                     "mergeCol": "materiaTypeName,materiaListLimitsName"
                     }'>
                        <table>
                            <thead>
                            <tr>
                                <th data-options="field:materiaTypeName">资料类型</th>
                                <th data-options="field:materiaListLimitsName" formatter="authpack">默认查看权限</th>
                                <th data-options="field:materiaName">资料名称</th>
                                <th data-options="field:rememberCode">助记码</th>
                                <th data-options="field:rememberNo">数字助记码</th>
                                <th data-options="field:materiaIdentifyName">资料证明</th>
                                <th data-options="field:id" formatter="materialFunction">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <!-- 与table中的一起使用才有效"toolbar":"#btn-applylist" ，写handler回调函数实现增删改功能-->
                    <div id="btn-applylistMateria" style="width: 100%">
                        <a class="zui-toolbar" id="btn-add" text="新增" iconCls="icon-btn08" buttonCls="btn-blue"
                           handler="addDialogOpen"></a>
                        <!-- <a class="zui-toolbar" id="btn-moveUp" text="上移" iconCls="icon-btn30" buttonCls="btn-blue"
                           handler="tableRowMoveUp"></a>
                        <a class="zui-toolbar" id="btn-moveDown" text="下移" iconCls="icon-btn30" buttonCls="btn-blue"
                           handler="tableRowMoveDown"></a> 
                        <a class="zui-toolbar" id="btn-twoBarCodes" text="二维码打印" iconCls="icon-btn30"
                           buttonCls="btn-blue"
                           handler="twoCode"></a>-->
                        <a class="zui-toolbar" id="btn-auth" text="设置默认查看权限" iconCls="icon-btn30" buttonCls="btn-blue"
                           handler="limitDialogOpen"></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="dialogAdd" style="display: none">
        <div id="dialogAddFormDiv">
            <form id="materiaDialogAddForm" class="zui-form" zdata-options={}>
                <input type="hidden" name="productId" value="${productId}"/>
                <input type="hidden" name="id" id="id"/>
                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>资料类型：</dt>
                    <dd class="detail">
                    	<input class="zui-combobox zui-validatebox" id="dropdown1" validate-type="Require" name="materiaTypeCode" type="hidden" data-callback="select1" style="display: none;" data-height="250px">
                        <input type="hidden" id="materiaTypeName" name="materiaTypeName" />
                    </dd>
                </dl>

                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>资料名称：</dt>
                    <dd class="detail">
                    	<input class="zui-combobox zui-validatebox" id="dropdown2" validate-type="Require" name="materiaCode" type="hidden" data-callback="select2" style="display: none;" data-height="250px">
                        <input type="hidden" id="materiaName" name="materiaName" />
                    </dd>
                </dl>

                <dl class="form-item">
                    <dt class="title"><b class="c-red mr5">*</b>助记码：</dt>
                    <dd class="detail">
                        <label>
                            <input class="zui-input zui-validatebox" type="text" validate-type="Require,Length[1-30]" id="rememberCode"
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
                    	<input class="zui-checkbox zui-validatebox" id="materiaIdentifyCkeck" validate-type="Require" type="hidden"
							data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM00128"
							data-multiple="true"
							data-callback="materiaIdentifyChange"
						    data-valuefield="fullcode" data-textfield="name" >
                        <input type="hidden" id="materiaIdentifyName" name="materiaIdentifyName" />
                        <input type="hidden" id="materiaIdentify" name="materiaIdentify" />
                    </dd>
                </dl>
            </form>
        </div>
    </div>
    <div id="limitsDialog" style="display: none">
    	<form id='limitsForm' class='zui-form'>
    		<input type='hidden' name='productId' value="${productId}">
	    	<table id="limitsTable" class='table-detail'>
	    		<tr>
	    			<th style="text-align: center;font-weight: bold;">序号</th>
	    			<th style="text-align: center;font-weight: bold;">所属分类</th>
	    			<th style="text-align: center;font-weight: bold;">默认查看权限</th>
	    		</tr>
	    		<tbody id="tbody">
	    		</tbody>
	    	</table>
	    </form>
    </div>
</div>

<script>
seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/drop-down-linkage','zd/jquery.zds.combobox', 'zd/jquery.zds.checkbox', 'zd/jquery.zds.loading', 'zd/switch',
                'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form',
                'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter', 'zd/bothselecter', 'zd/jquery.zds.button'],
	function ($, CALLBACK,DropDownLinkage, Loading, Switch, DropDown, Filter, Check, Zdialog, ZUI_MESSAGE_CLIENT) {
		//下拉菜单数据
		var data = (${materiaClass} == null? "":${materiaClass});
		CALLBACK.materialFunction = function (index, rowData) {
             var str = "<a title='修改' class='btn-blue' onclick='editData'>修改</a>" +
         	"&nbsp;&nbsp;<a title='删除' class='btn-blue' onclick='deleteData'>删除</a>";
     		return str;
         };
         //编辑按钮
		CALLBACK.editData = function (index, rowData) {
            $("#dialogAdd").Zdialog('setTitle','编辑资料');
           	//赋值ID
            $("#materiaDialogAddForm #id").val(rowData.id);
            //赋值
            new DropDownLinkage({
            	elements: [
                    {elementId: '#dropdown1', callback: ['select1','onselect1']},
                    {elementId: '#dropdown2', callback: ['select2','onselect2']}
                ],//联动元素集,elementId:标签的id属性值；callback[0]:标签的data-callback属性值；callback[1]为自定义回调函数的函数名。
                parentId: "pId",//pId字段
                valueField: "id",//唯一标识(下拉框)
                textField: "name",//文本字段
                value: [{id:rowData.materiaTypeCode,pId:""},{id:rowData.materiaCode,pId:rowData.materiaTypeCode}],//初始化下拉值 不能删除
                data: data
			});
             
            $("#materiaTypeName").val(rowData.materiaTypeName);
            $("#materiaName").val(rowData.materiaName);
             
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
            $("#materiaIdentifyCkeck").ZCheckbox();
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
                                $('#zds-materia-table').ZTable("reload");
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
        //资料证明复选框
        CALLBACK.materiaIdentifyChange = function (index, rowData) {
            $("#materiaIdentifyName").val("");
            $("#materiaIdentify").val("");
            $("#materiaIdentifyName").val(rowData);
            $("#materiaIdentify").val(index);
        };
        //新增点击
        CALLBACK.addDialogOpen = function (index, rowData) {
            $("#materiaDialogAddForm #id").val("");
            $("#dialogAdd").Zdialog('setTitle','新增资料');
            $("#dialogAdd").Zdialog("open");
        };
       	//新增对话框
        $("#dialogAdd").Zdialog({
            width: 700, height: 400, closed: true, title: "",
            buttons: [{
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
	                if(!checkOnlyData()){
	                	return false;
	                }
                    var params = $('#materiaDialogAddForm').serializeArray();
                    var url = '<z:ukey key="com.zdsoft.finance.materiaSave" context="admin" />&jsoncallback=? ';
                    $.ajax({
                        url: url,
                        data: params,
                        type: "post",
                        success: function (data) {
                            if (data.resultStatus == 0) {
                                $.ZMessage.success("提示", "保存成功!", function () {
                                 	$("#dialogAdd").Zdialog("close");
                                	$('#dropdown2').ZCombobox();
                                 	$('#zds-materia-table').ZTable("reload",{});
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
				}
            },{
            	id: 'message-btn',
                text: '取消',
                buttonCls: 'btn-gray',
                handler: function () {
                    //赋值
                    $("#materiaCode").ZCombobox("setValue","");
                    $("#materiaName").val("");
                    $("#materiaTypeCode").ZCombobox("setValue","");
                    $("#materiaTypeName").val("");
                    $("#rememberCode").val("");
                    $("#rememberNo").val("");
                    $("#materiaIdentifyName").val("");
                    $("#materiaIdentify").val("");
                    $("#materiaDialogAddForm #id").val("");
                    $("#dialogAdd").Zdialog("close");
                    $('#dropdown2').ZCombobox();
                }
            }]
           
        });
                
        /**
         * 验证数据的唯一 相同产品和相同资料大类下不能存在相同资料
         * */
        function checkOnlyData(){
            var materiaTypeCode=$("#dropdown1").val();
            var materiaCode=$("#dropdown2").val();
            var productId='${productId}';
            var id = $("#materiaDialogAddForm #id").val();
            var rememberCode = $("#rememberCode").val();
            var rememberNo = $("#rememberNo").val();
            var flag = false;
            $.ajax({
                url:'<z:ukey key="com.zdsoft.finance.checkOnlyData" context="admin" />',
                data:{
                	id:id,
                	materiaTypeCode:materiaTypeCode,
                	productId:productId,
                	materiaCode:materiaCode,
                	rememberCode:rememberCode,
                	rememberNo:rememberNo,
                },
                type:'post',
                async:false,
                success:function(data){
                    if(data.resultStatus == 0){//不存在数据
                        flag = true;
                    }else{
                    	$.ZMessage.warning("错误", data.msg, function () {
	                    });
                    }
                },
                error:function(data){
                    $.ZMessage.error("错误", "验证唯一性出错!"+data, function () {
                    });
                }
            });
            return flag;
        }
				
        //复选框初始化
        $("#materiaIdentifyCkeck").ZCheckbox();
              	
      	//权限对话框
        $("#limitsDialog").Zdialog({
            width: 500, height: 400, closed: true, title: "设置默认查看权限",
            buttons: [{
            	id: 'message-btnAdd',
                text: '确定',
                buttonCls: 'btn-blue',
                handler: function () {
                	 var validate = $.ZUI.validateForm($('#limitsForm'));
                     if (!validate) {
                         $.ZMessage.error("错误", "数据验证失败!", function () {
                         });
                         return false;
                     }
                     var params = $('#limitsForm').serializeArray();
                     var url = '<z:ukey key="com.zdsoft.finance.limitsSave" context="admin" />&jsoncallback=? ';
                     $.ajax({
                         url: url,
                         data: params,
                         type: "post",
                         traditional: true,
                         success: function (data) {
                             if (data.resultStatus == 0) {
                                 $.ZMessage.success("提示", "保存成功!", function () {
                                     $("#limitsDialog").Zdialog("close");
                                     $('#zds-materia-table').ZTable("reload");
                                 });
                             } else {
                                 $.ZMessage.error("错误", "保存出错!" + data.msg, function () {
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
           	},{
               	id: 'message-btn',
               	text: '取消',
               	buttonCls: 'btn-gray',
               	handler: function () {
                   $("#limitsDialog").Zdialog("close");
               	}
            }]
        });
              	
      	//添加权限点击
        CALLBACK.limitDialogOpen = function (index, rowData) {
      		var simpleCode = "<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0092";
      		$.ajax({
                url:'<z:ukey key="com.zdsoft.finance.materialLimits" context="admin" />',
                data:{
                	productId:"${productId}",
                },
                dataType:'json',
                type:'post',
                success:function(data){
                	var optional = null;
                	if(data.resultStatus == 0){
                		if(data.optional == null){
                			$.ZMessage.warning("提示", "没有数据", function () {});
                			return;
                		}else{
                			//数据
                			optional = data.optional;
                		}
                	}else{
                		$.ZMessage.error("错误", data.msg, function () {});
                		return;
                	}
                	if(optional != null){
                		var i = 1;
                		var html = "";
                		for(var key in optional.limits){
                			var limits = optional.limits[key];
                			html += "<tr><td style='text-align: center;'>"+i+"</td>" +
        	    			"<td style='text-align: center;'>"+limits.MATERIATYPENAME+"<input type='hidden' name='materiaTypeCode' value='"+limits.MATERIATYPECODE+"'></td>" +
        	    			"<td style='text-align: center;'>" +
        	    				"<dl class='form-item'>" +
        		    				"<dd class='detail'>" +
        			    				"<input class='zui-combobox zui-validatebox' validate-type='Require' type='hidden' name='materiaLimit' data-multiple='true' " +
        									"data-url='"+simpleCode+"'" +
        								    "data-valuefield='fullcode' data-textfield='name' value='"+limits.LIMITS+"'>" +
        							"</dd>" +
        						"</dl>" +
        	    			"</td></tr>";
        	    			i ++;
                		}
                		$("#limitsForm #tbody").html(html);
                		$("#limitsForm input[name='materiaLimit']").ZCombobox();
                		$("#limitsDialog").Zdialog("open");
                	}
                },
                error:function(data){
                    $.ZMessage.error("错误", data, function () {});
                }
            });
        };
        //table数据
        CALLBACK.authpack = function (index, rowData) {
            if (rowData == "" || rowData == null) {
                return "无";
            }
            return rowData;
        };
                
        //二维码
        /* CALLBACK.twoCode = function(index,rowData){
            var twoUrl = '<z:ukey key="com.zdsoft.finance.twoCodePage" context="admin"/>&productCode=${productCode}';
            ZDS_MESSAGE_CLIENT.openMenuLink('资料清单二维码','资料清单二维码',twoUrl);
        }; */
                
        $.ZUI.initGrid("#materiaListContentDiv");
        $.ZUI.initForms("#materiaDialogAddForm");
        $.ZUI.initForms("#limitsForm");
       
        new DropDownLinkage({
            elements: [
                {elementId: '#dropdown1', callback: ['select1','onselect1']},
                {elementId: '#dropdown2', callback: ['select2','onselect2']}
            ],//联动元素集,elementId:标签的id属性值；callback[0]:标签的data-callback属性值；callback[1]为自定义回调函数的函数名。
            parentId: "pId",//pId字段
            valueField: "id",//唯一标识(下拉框)
            textField: "name",//文本字段
            value: [{id:"",pId:""},{id:"",pId:"gufeng"}],//初始化下拉值 不能删除
            data: data
        });
        
        CALLBACK.onselect1=function(data) {
        	if(data.id == ""){
	        	$("#dropdown2").ZCombobox();
        	}
        	$("#materiaTypeName").val(data.name);
        	$("#materiaName").val("");
            $("#rememberCode").val("");
            $("#rememberNo").val("");
	    };
	    CALLBACK.onselect2=function(data) {
	    	$("#materiaName").val(data.name);
            //回写助记码
            $("#rememberCode").val(data.rememberCode);
            $("#rememberNo").val(data.rememberNo);
	    };
});
</script>

</body>
</html>