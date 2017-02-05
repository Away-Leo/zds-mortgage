<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common_js.jsp" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>产品管理</title>
</head>
<body>
<div>
    <div class="frm-content">
		<div class="ztree-box">
		<div class="page-title">产品</div>
			<div id="ztree-sidebar" class="ztree-sidebar">
				<div class="tc p5">
                    <button id="addChild" type="button" class="btn-blue">添加分类</button>
                    <button id="deleteChild" type="button" class="btn-blue">删除</button>
                </div>
				<ul id="treeView" class="ztree"></ul>
			</div>                                                                                                                                                                                                                                        
			<div id="ztree-content" class="ztree-content">
				<div class="page-box">
					<div class="page-title">查询</div>
				    <div class="p10">
						<form id="queryProductForm" class="zui-form mt15" action="javascript:void(0);"
			              zdata-options="{}">
				            <dl class="form-item">
				                <dt class="title">产品分类:</dt>
				                <dd class="detail">
				                  <input class="zui-combobox zui-validatebox" type="hidden" validate-type="" id="productType"  name="categoryVo.id">
				                </dd> 
				            </dl>
				            <dl class="form-item">
				                <dt class="title">产品名称:</dt>
				                <dd class="detail">
				                   <label>
				                        <input type="text" id="name" class="zui-input zui-validatebox" validate-type="LegalChar,Length[0-60]" name="productName"/>
				                    </label>
				                </dd>
				            </dl>
				            <dl class="form-item">
				                <dt class="title">启用状态:</dt>
				                <dd class="detail">
				                   <input class="zui-combobox zui-validatebox" type="hidden" validate-type="" id="isValid"
				                          data-data="[{'id':'true','text':'是'},{'id':'false','text':'否'}]" value="true"
				                          data-valuefield="id" data-textfield="text" name="isValid">
				                </dd>
				            </dl>
				            <div class="form-btn">
					            <button class="btn-blue" type="button" id="find">查询</button>
					            <button class="btn-gray" type="button" id="reset">重置</button>
					        </div>
				        </form>
					</div>
				</div>
				<div class="page-box">
					<div class="page-title">列表</div>
				    <div class="p10">
						<div id="tb-product" class="zui-datagrid" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.product.getList" context="admin"/>","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#product_toolbar"}'>
						    <table>
						        <thead>
						        <tr>
						            <th data-options="field:productName,width:40%">产品名称</th>
						            <th data-options="field:isValid,width:20%" formatter="formatIsValid">启用状态</th>
						            <th data-options="field:id,width:40%" formatter="formatId">操作</th>
						        </tr>
						        </thead>
						    </table>
						</div>
						<div id="product_toolbar">
							<a class="zui-toolbar" iconCls="icon-btn08" text="新增" buttonCls="btn-blue" handler="addProduct"></a>
			        	</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="addCategoryDialog">
</div>
<div id="addProductDialog">
</div>
<div id="copyProductDialog">
</div>

<script type="text/javascript">
	seajs.use([
	           'jquery','zd/tools','zd/jquery.zds.page.callback','zd/jquery.zds.dialog', 'zd/jquery.zds.combotree',
	           'zd/jquery.zds.combobox','zd/jquery.zds.message','ztree', 'zd/jquery.zds.form','zd/jquery.zds.table','zd/jquery.zds.validate', 'zd/jquery.zds.seleter'
	           ], 
	 		function ($,ZTOOlS,CALLBACK,Zdialog) {
		/****************start************* ui公共部分***********************************/
		ZTOOlS.layoutResize('#ztree-sidebar',60);
	    ZTOOlS.layoutResize('#ztree-content',60);
	    $(window).resize(function(){
	           ZTOOlS.layoutResize('#ztree-sidebar',60);
	           ZTOOlS.layoutResize('#ztree-content',60);
	    }); 
	    /****************end************** ui公共部分***********************************/
	     
	    /****************end**************ztree 默认 加载*******************************/
		var setting = {
			view : {
			},
			data:{
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "parentId"
				}
			},
			callback : {
				onClick : onClick
			}
		};
	    
	    /**
	     * 查询
	     */
	    $('#find').on('click',function(){
	    	var flag=$.ZUI.validateForm($('#queryProductForm'));
        	if(flag){
            	var formArray=$("#queryProductForm").serialize();
            	formArray=decodeURIComponent(formArray, true);
            	$('#tb-product').ZTable("reload", formArray);
        	}
	    });
	    
	    /**
	     * 重置
	     */
	    $('#reset').on('click',function(){
	    	$('#name').val('');
	    	$('#isValid').ZCombobox('setValue',true);
	    	$('#productType').ZCombobox('setValue','');
	    	var treeObj = $.fn.zTree.getZTreeObj("treeView");
			var nodes = treeObj.getSelectedNodes();
			var id = nodes[0].id;
			$('#tb-product').ZTable("reload", {"categoryVo.id":id,isValid:true});
	    });
	    
	    //获取选择节点
		function getTreeNode(){
			var treeObj = $.fn.zTree.getZTreeObj("treeView");
			var nodes = treeObj.getSelectedNodes();
			var id = "";
			var name=""
			if(nodes.length > 0){
				id = nodes[0].id;
				name = nodes[0].name;
				return id;
			}else{
				$.ZMessage.warning("提示", "请选择产品父级", function () {
	                $(".zd-message").ZWindow("close");
	            });
				return;
			}
		}
	  
	    /**
	     * 获取树
	     */
	    function renderTreeNodes(selectId){
	    	$.ajax({
                type: 'post',
                url: '<z:ukey key="com.zdsoft.finance.product.findTree" context="admin"/>',
                data: {},
                dataType: 'json',
                success: function (data) {
                    if (data.resultStatus == 0) {
                    	$.fn.zTree.init($("#treeView"), setting,data.rows);
                    	//设置节点选中
                		var zTree = $.fn.zTree.getZTreeObj("treeView");
                    	if(selectId){
                    		var node = zTree.getNodeByParam("id",selectId);
                    	}else{
                			var node = zTree.getNodeByParam("id",'0');
                    	}
                		zTree.selectNode(node);
                		zTree.expandNode(node,true,false);
                    }
                },
                error: function () {
                	$.ZMessage.error("错误", "系统异常,请联系管理员", function () {
                        $(".zd-message").ZWindow("close");
                    });
                }
            });
	    }
	    
		//节点
		renderTreeNodes();
		
		//产品分类下拉框
		function renderCombobox(){
			$("#productType").ZCombobox({
	            valueField: "id",
	            textField: "name",
	            url:'<z:ukey key="com.zdsoft.finance.product.findCategorySimpleCode" context="admin"/>&jsoncallback=?',
	            onSelect:function(value,text,index){
	                $('#productType').val(value);
	            }
	        });
		}
		
		renderCombobox();
		
		//保存
		$('#addChild').on('click',function(){
			var id = getTreeNode();
			var treeObj = $.fn.zTree.getZTreeObj("treeView");
			var nodes = treeObj.getSelectedNodes();
			var level=nodes[0].level;
			
			if(level>=1){
				$.ZMessage.error("错误", "该层级不能添加类别", function () {
                    $(".zd-message").ZWindow("close");
                });
				return ;
			}
			if(!id){
				$.ZMessage.warning("警告", "请选择要添加的类别", function () {
                    $(".zd-message").ZWindow("close");
                });
				return ;
			}else{
		   		var	url = '<z:ukey key="com.zdsoft.finance.product.addCategoryDialog" context="admin"/>&id='+id;
				$('#addCategoryDialog').load(url,function(){
					$("#categoryDialog").Zdialog({
			             width: 700, height: 340, closed: false, title: "产品类别",isDestroy:true,
			             buttons: 
			             [
			                 {
			                     id: 'message-btn',
			                     text: '确定',
			                     buttonCls: 'btn-blue',
			                     handler: function () {
			                    	var flag=$.ZUI.validateForm($('#addCategoryForm'));
			                     	if(flag){
			                     		//验证产品名称
				                    	 $.ajax({
			                                 type: 'post',
			                                 url: '<z:ukey key="com.zdsoft.finance.product.findCatetoryByName" context="admin"/>',
			                                 data: {name:$('#categoryName').val()},
			                                 dataType: 'json',
			                                 success: function (data) {
			                                     if (data.resultStatus == 0) {
			                                    	if(data.optional.isExist){
			                                    		var addCategoryForm = $('#addCategoryForm').serialize();
				   			                             $.ajax({
				   			                                 type: 'post',
				   			                                 url: '<z:ukey key="com.zdsoft.finance.product.saveCategory" context="admin"/>',
				   			                                 data: addCategoryForm,
				   			                                 dataType: 'json',
				   			                                 success: function (data) {
				   			                                     if (data.resultStatus == 0) {
				   			                                    	renderCombobox();
				   			                                    	$("#categoryDialog").Zdialog("close");
				   			                                    	$.ZMessage.success("成功", "保存成功", function () {
					   			                                         $(".zd-message").ZWindow("close");
					   			                                     });
				   			                                    	var id= getTreeNode();
				   			                                    	renderTreeNodes(id);
				   			                                     }else{
				   			                                     	$.ZMessage.error("错误", data.msg, function () {
				   			             	                            $(".zd-message").ZWindow("close");
				   			             	                        });
				   			                                     }
				   			                                 },
				   			                                 error: function () {
				   			                                 	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
				   			                                         $(".zd-message").ZWindow("close");
				   			                                     });
				   			                                 }
				   			                             });
			                                    	}else{
			                                    		$.ZMessage.error("错误", "产品名称已存在", function () {
				             	                            $(".zd-message").ZWindow("close");
				             	                        });
			                                    	}
			                                     }else{
			                                     	$.ZMessage.error("错误", data.msg, function () {
			             	                            $(".zd-message").ZWindow("close");
			             	                        });
			                                     }
			                                 },
			                                 error: function () {
			                                 	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
			                                         $(".zd-message").ZWindow("close");
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
			                     	$("#categoryDialog").Zdialog("close");
			                     }
			                 }
			             ]
			         });
				});
			}
		});
		
		//删除
		$('#deleteChild').on('click',function(){
			var id = getTreeNode();
			if(!id || id=='0'){
				$.ZMessage.error("错误", "请先选中要删除的分类节点！", function () {
					$(".zd-message").ZWindow("close");
				});
				return ;
			}
			$.ZMessage.question("提示", "确认作废？", function () {
				$.ajax({
	                type: 'post',
	                url: '<z:ukey key="com.zdsoft.finance.product.deleteCategory" context="admin"/>',
	                data: {categoryId:id},
	                dataType: 'json',
	                success: function (data) {
	                    if (data.resultStatus == 0) {
		                   	renderTreeNodes();
		                   	renderCombobox();
		                   	$('#tb-product').ZTable("reload", {"categoryVo.id":0});
	                    	$.ZMessage.success("提示", "作废成功", function () {
	    	                    $(".zd-message").ZWindow("close");
	    	                });
	                    }else{
	                    	$.ZMessage.error("错误", data.msg, function () {
	                            $(".zd-message").ZWindow("close");
	                        });
	                    }
	                },
	                error: function () {
	                	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
	                        $(".zd-message").ZWindow("close");
	                    });
	                }
	            });
                $(".zd-message").ZWindow("close");
            });
		});
		
		CALLBACK.formatIsValid=function(rowData,index){
			if(rowData.isValid){
				return '是';
			}else{
				return '否';
			}
		}
		
		CALLBACK.formatId=function(rowData,index){
			var id=rowData.id;
			var isDeleted=rowData.isDeleted;
			if(!id){
				$.ZMessage.error("错误", "未获取到主键", function () {
                    $(".zd-message").ZWindow("close");
                });
				return ;
			}

			var data='<a href="javaScript:void(0)" onclick="edit"><button class="btn-blue">编辑</button></a>';
			/* if(isDeleted){
				data=data+'&nbsp;&nbsp;'+'<a href="javaScript:void(0)" onclick="restoreProduct"><button class="btn-blue">恢复</button></a>';
			}else{
				data=data+'&nbsp;&nbsp;'+'<a href="javaScript:void(0)" onclick="invalidProudct"><button class="btn-blue">作废</button></a>';
			} */
			data=data+'&nbsp;&nbsp;'+'<a href="javaScript:void(0)" onclick="copy"><button class="btn-blue">复制</button></a>';
			return data;
		}
		
		//作废产品
		CALLBACK.invalidProudct=function(index,rowData){
			var id=rowData.id;
			if(!id){
				$.ZMessage.error("错误", "未获取到主键", function () {
					$(".zd-message").ZWindow("close");
				});
			}else{
				$.ZMessage.warning("提示", "确认作废？", function () {
					$.ajax({
		                type: 'post',
		                url: '<z:ukey key="com.zdsoft.finance.product.invalidProudct" context="admin"/>',
		                data: {productId:id},
		                dataType: 'json',
		                success: function (data) {
		                    if (data.resultStatus == 0) {
			                   	$('#tb-product').ZTable("reload", {});
		                    	$.ZMessage.success("提示", "作废成功", function () {
		    	                    $(".zd-message").ZWindow("close");
		    	                });
		                    }else{
		                    	$.ZMessage.error("错误", data.msg, function () {
		                            $(".zd-message").ZWindow("close");
		                        });
		                    }
		                },
		                error: function () {
		                	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
		                        $(".zd-message").ZWindow("close");
		                    });
		                }
		            });
	                $(".zd-message").ZWindow("close");
	            });
			}
		}
		
		//恢复产品
		CALLBACK.restoreProduct=function(index,rowData){
			var id=rowData.id;
			if(!id){
				$.ZMessage.error("错误", "未获取到主键", function () {
					$(".zd-message").ZWindow("close");
				});
			}else{
				$.ZMessage.warning("提示", "确认恢复？", function () {
					$.ajax({
		                type: 'post',
		                url: '<z:ukey key="com.zdsoft.finance.product.restoreProduct" context="admin"/>',
		                data: {productId:id},
		                dataType: 'json',
		                success: function (data) {
		                    if (data.resultStatus == 0) {
			                   	$('#tb-product').ZTable("reload", {});
		                    	$.ZMessage.success("提示", "恢复成功", function () {
		    	                    $(".zd-message").ZWindow("close");
		    	                });
		                    }else{
		                    	$.ZMessage.error("错误", data.msg, function () {
		                            $(".zd-message").ZWindow("close");
		                        });
		                    }
		                },
		                error: function () {
		                	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
		                        $(".zd-message").ZWindow("close");
		                    });
		                }
		            });
	                $(".zd-message").ZWindow("close");
	            });
			}
		}
		
		//添加产品
		CALLBACK.addProduct=function(){
			var url='<z:ukey key="com.zdsoft.finance.product.addProductDialog" context="admin"/>';
			var id= getTreeNode();
			if(!id || id=='0'){
				$.ZMessage.warning("警告", "请选择添加产品的具体产品类别", function () {
                    $(".zd-message").ZWindow("close");
                });
			}else{
				$('#addProductDialog').load(url,function(){
					$("#productDialog").Zdialog({
			             width: 700, height: 340, closed: false, title: "添加产品",isDestroy:true,
			             buttons: 
			             [
			                 {
			                     id: 'message-btn',
			                     text: '确定',
			                     buttonCls: 'btn-blue',
			                     handler: function () {
			                    	var flag=$.ZUI.validateForm($('#addProductForm'));
			                     	if(flag){
			                     		//验证产品名称
				                    	 $.ajax({
			                                 type: 'post',
			                                 url: '<z:ukey key="com.zdsoft.finance.product.findProductByName" context="admin"/>',
			                                 data: {name:$('#productName').val()},
			                                 dataType: 'json',
			                                 success: function (data) {
			                                     if (data.resultStatus == 0) {
			                                    	if(data.optional.isExist){
			                                    		var addProductForm = $('#addProductForm').serialize();
				   			                             $.ajax({
				   			                                 type: 'post',
				   			                                 url: '<z:ukey key="com.zdsoft.finance.product.saveProduct" context="admin"/>',
				   			                                 data: addProductForm+"&categoryVo.id="+id,
				   			                                 dataType: 'json',
				   			                                 success: function (data) {
				   			                                     if (data.resultStatus == 0) {
				   			                                    	$('#tb-product').ZTable("reload", {"categoryVo.id":id,isValid:true});
				   			                                    	$("#productDialog").Zdialog("close");
				   			                                     	$.ZMessage.success("提示", "保存成功", function () {
				   			                     	                    $(".zd-message").ZWindow("close");
				   			                     	                });
				   			                                     }else{
				   			                                     	$.ZMessage.error("错误", data.msg, function () {
				   			             	                            $(".zd-message").ZWindow("close");
				   			             	                        });
				   			                                     }
				   			                                 },
				   			                                 error: function () {
				   			                                 	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
				   			                                         $(".zd-message").ZWindow("close");
				   			                                     });
				   			                                 }
				   			                             });
			                                    	}else{
			                                    		$.ZMessage.error("错误", "产品名称已存在", function () {
				             	                            $(".zd-message").ZWindow("close");
				             	                        });
			                                    	}
			                                     }else{
			                                     	$.ZMessage.error("错误", data.msg, function () {
			             	                            $(".zd-message").ZWindow("close");
			             	                        });
			                                     }
			                                 },
			                                 error: function () {
			                                 	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
			                                         $(".zd-message").ZWindow("close");
			                                     });
			                                 }
			                             });
				                    	 
			                     	}else{
			                     		$.ZMessage.error("错误", "请填入合法参数", function () {
			                                 $(".zd-message").ZWindow("close");
			                             });
			                     	}
			                     }
			                 },
			                 {
			                     id: 'message-btn',
			                     text: '取消',
			                     buttonCls: 'btn-gray',
			                     handler: function () {
			                     	$("#productDialog").Zdialog("close");
			                     }
			                 }
			             ]
			         });
				});
			}
		}
		
		CALLBACK.edit=function(index,rowData){
			var id=rowData.id;
			if(!id){
				$.ZMessage.error("错误", "未获取到主键", function () {
                    $(".zd-message").ZWindow("close");
                });
				return ;
			}
			
			ZDS_MESSAGE_CLIENT.openMenuLink('product_edit', '产品编辑', '<z:ukey key="com.zdsoft.finance.product.edit" context="admin"/>&id='+id);
		}
		
		CALLBACK.copy=function(index,rowData){
			var url='<z:ukey key="com.zdsoft.finance.product.copyDialog" context="admin"/>&productId='+rowData.id;
			$('#copyProductDialog').load(url,function(){
				$("#productCopyDialog").Zdialog({
		             width: 700, height: 340, closed: false, title: "复制产品",isDestroy:true,
		             buttons: 
		             [
		                 {
		                     id: 'message-btn',
		                     text: '确定',
		                     buttonCls: 'btn-blue',
		                     handler: function () {
		                    	var flag=$.ZUI.validateForm($('#copyProductForm'));
		                     	if(flag){
		                     		//验证产品名称
			                    	 $.ajax({
		                                 type: 'post',
		                                 url: '<z:ukey key="com.zdsoft.finance.product.findProductByName" context="admin"/>',
		                                 data: {name:$('#productName').val()},
		                                 dataType: 'json',
		                                 success: function (data) {
		                                     if (data.resultStatus == 0) {
		                                    	if(data.optional.isExist){
		                                    		var copyProductForm = $('#copyProductForm').serialize();
			   			                             $.ajax({
			   			                                 type: 'post',
			   			                                 url: '<z:ukey key="com.zdsoft.finance.product.copy" context="admin"/>',
			   			                                 data: copyProductForm,
			   			                                 dataType: 'json',
			   			                                 success: function (data) {
			   			                                     if (data.resultStatus == 0) {
			   			                                    	var id= getTreeNode();
			   			                                    	if(id=='0'){
				   			                         				$('#tb-product').ZTable("reload", {isValid:true});
				   			                         			}else{
				   			                         				$('#tb-product').ZTable("reload", {"categoryVo.id":id,isValid:true});
				   			                         			}
			   			                                    	$("#productCopyDialog").Zdialog("close");
			   			                                     	$.ZMessage.success("提示", "复制成功", function () {
			   			                     	                    $(".zd-message").ZWindow("close");
			   			                     	                });
			   			                                     }else{
			   			                                     	$.ZMessage.error("错误", data.msg, function () {
			   			             	                            $(".zd-message").ZWindow("close");
			   			             	                        });
			   			                                     }
			   			                                 },
			   			                                 error: function () {
			   			                                 	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
			   			                                         $(".zd-message").ZWindow("close");
			   			                                     });
			   			                                 }
			   			                             });
		                                    	}else{
		                                    		$.ZMessage.error("错误", "产品名称已存在", function () {
			             	                            $(".zd-message").ZWindow("close");
			             	                        });
		                                    	}
		                                     }else{
		                                     	$.ZMessage.error("错误", data.msg, function () {
		             	                            $(".zd-message").ZWindow("close");
		             	                        });
		                                     }
		                                 },
		                                 error: function () {
		                                 	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
		                                         $(".zd-message").ZWindow("close");
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
		                     	$("#productCopyDialog").Zdialog("close");
		                     }
		                 }
		             ]
		         });
			});
		}
		
		$.ZUI.init();
		
		function onClick(e, treeId, treeNode) {
			var treeObj = $.fn.zTree.getZTreeObj("treeView");
			var nodes = treeObj.getSelectedNodes();
			var id = nodes[0].id;
			
			if(!id){
				$.ZMessage.error("错误", "请联系管理员，获取选择节点错误！", function () {
                    $(".zd-message").ZWindow("close");
                });
				return ;
			}
			/* var node = treeObj.getNodeByParam("id",id);
			treeObj.expandNode(node,true,false); */
			if(id=='0'){
				$('#tb-product').ZTable("reload", {isValid:true});
			}else{
				$('#tb-product').ZTable("reload", {"categoryVo.id":id,isValid:true});
			}
	   	}
	});

</script>
</body>
</html>