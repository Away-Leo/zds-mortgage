<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<link rel="stylesheet" type="text/css"
	href="../../../web/designer4dev/js/jquery/jquery-ui-1.9.2.min.css" />
<link rel="stylesheet" type="text/css"
	href="../../../web/designer4dev/js/spectrum/spectrum.min.css" />
<link rel="stylesheet" type="text/css"
	href="../../../web/designer4dev/js/sh/sh.min.css" />
<link href="../../../web/designer4dev/js/css/commonCss.css"
	rel="stylesheet" type="text/css" />
<%@ include file='../common/common_js.jsp'%>



<style type="text/css">
.ui-selected1 {
	background: #E6E6F7;
}
</style>
<title>添加页</title>
</head>
<body>
	<div class="page-box" sytle="display:">
		<div class="p10">
			<form id="afterCheck_form" class="zui-form " method="post"
				enctype="multipart/form-data">
				<input type="hidden" id="id" name="id"
					value="${bussPrintTplPageVo.id }">
					<input type="hidden" id="printtemplateid" name="printtemplateid"
					value="${bussPrintTplPageVo.printtemplateid }" validate-type="Require"/>
				<div class="page-box">
					<div class="p5">
						<table class="table-detail">
							<tr>
								<td class="td-title pct10"><font class="c-red">*</font>页名</td>
								<td class="pct20"><label> <input
										class="zui-input zui-validatebox" validate-type="Require"
										name="pagename" id="pagename"
										value="${bussPrintTplPageVo.pagename }" validate-type="Require">
								</label></td>
								<td class="td-title pct10"><font class="c-red">*</font>页码</td>
								<td class="pct20"><input class="zui-input zui-validatebox toInt"
									validate-type="Require" name="pagenum"
									value="${bussPrintTplPageVo.pagenum }" validate-type="Require"/></td>

								<td class="td-title pct10"><font class="c-red">*</font>左边距</td>
								<td class="pct20"><input class="zui-input zui-validatebox toInt"
									validate-type="Require" name="leftmargin"
									value="${bussPrintTplPageVo.leftmargin}" validate-type="Require"/></td>
							</tr>

							<tr>
								<td class="td-title pct10"><font class="c-red">*</font>上边距</td>
								<td class="pct20"><input class="zui-input zui-validatebox toInt"
									validate-type="Require" name="topmargin"
									value="${bussPrintTplPageVo.topmargin}" validate-type="Require"/></td>
								<td class="td-title pct10"><font class="c-red">*</font>页地址</td>
								<td class="pct40" colspan="3">
								
								<%@ include file="upload_contract_file.jsp"%></td>

							</tr>
						</table>
					</div>
					<div class="form-btn">
						<button id="btnsave" type="button" class="btn-blue">保存</button>
					</div>

				</div>
			</form>

			<div>
				<div id="jp-error-dialog" title='出错了!' class='jp-dialog'>
					<p class='jp-error-text'></p>
				</div>
				<!-- 工具栏 -->
				<div class='jp-main-tools jp-toolbar jp-common-command'>
					<div class="jp-button-set">
						<a href="#" class='jp-new-text jp-view100' id='jp-new-text'
							title='新建文本字段'></a>
					</div>
					<div class="jp-button-set">
						<a href="#" class='jp-save jp-view100' id='jp-saveextend'
							title='保存'></a><a href="#" class='jp-print' id='jp-print'
							title='打印'></a><a href="#" class='jp-delete' id='jp-delete'
							title='删除(Delete)'></a>
					</div>
					<div class="jp-button-set">
						<a href="#" class='jp-undo' id='jp-undo' title='撤销(ctrl+z) '></a><a
							href="#" class='jp-redo' id='jp-redo' title='重做(ctrl+y)'></a>
					</div>
					<select id="SelectLabelType"
						style="width: 100px; height: 24px; margin-top: 2px; margin-right: 5px; margin-bottom: 2px; margin-left: 2px; float: left;"
						onchange="selectType(this)">
						<option value="0">请选择类型</option>
					</select> <select id="SelectLabel"
						style="width: 100px; height: 24px; margin-top: 2px; margin-right: 5px; margin-bottom: 2px; margin-left: 2px; float: left;"
						onchange="select(this)">
						<option value="0">请选择标签</option>
					</select> <select id="Select1"
						style="width: 100px; height: 24px; margin-top: 2px; margin-right: 5px; margin-bottom: 2px; margin-left: 2px; float: left;"
						onchange="selectFontSize(this)">
						<option value="0">字体大小</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
					</select>
				</div>
				<div class='jp-content'>
					<div class='jp-page jp-hidden' id="page1"
						style="position: relative; width: 210mm; height: 297mm; box-shadow: 0 0 13px rgba(0, 0, 0, 0.31); border: solid 1px gray;">
						<img class='jp-paper-background screen-only'
							src='../../designer4dev/images/blank.png'></img>
						<p class='jp-h-ruler jp-ruler-element'></p>
						<p class='jp-v-ruler jp-ruler-element'></p>
					</div>
					<p></p>
				</div>
				<iframe id='jp-ax' style='width: 0; height: 0'></iframe>
			</div>

		</div>
	</div>


	<script type="text/javascript"
		src="../../../web/designer4dev/js/jquery/jquery-ui-1.9.2.min.js"></script>

	<script type="text/javascript"
		src="../../../web/designer4dev/js/spectrum/spectrum.min.js"></script>

	<script type="text/javascript"
		src="../../../web/designer4dev/js/sh/sh.min.js"></script>

	<script type="text/javascript"
		src='../../../web/designer4dev/js/jatoolsPrinter-ui-min.js'></script>

	<script src="../../../web/designer4dev/js/json2.js"
		type="text/javascript"></script>



	<script type="text/javascript">
		seajs
				.use(
						[ 'jquery', 'zd/jquery.zds.page.callback',
								'zd/jquery.zds.form', 'zd/jquery.zds.message',
								'zd/jquery.zds.dialog',
								'zd/jquery.zds.combobox',
								'zd/jquery.zds.table', 'zd/jquery.zds.seleter' ],
						function($, CALLBACK, Zdialog,ZTOOL) {
							$.ZUI.init();
							

							$("#btnsave").click(function() {

												//检查信息
													var isValidateAfterCheck = $.ZUI.validateForm($('#afterCheck_form'));
												//校验
												if(!isValidateAfterCheck){
													$.ZMessage.info("提示", "请完善必填项信息！", function () {
												     });	 
													return false;
												}
												//获取检查信息
												var params = $('#afterCheck_form').serialize();
												//保存
												$.ajax({
															type : 'post',
															url : '<z:ukey key="com.zdsoft.finance.contract.contractPrintTplPage_Save" context="admin"/>',
															data : params,
															dataType : 'json',
															success : function(data) {
																if (data.resultStatus == 0) {
																	$.ZMessage.success(
																					"成功",
																					data.msg,
																					function() {
																				
												        	     		                	ZDS_MESSAGE_CLIENT.refreshOpenner();
												        	     		                	ZDS_MESSAGE_CLIENT.closeSelf();
												        	     		              
																					});
																} else {
																	$.ZMessage
																			.error(
																					"错误",
																					data.msg,
																					function() {
																					});
																}
															},
															error : function() {
																$.ZMessage
																		.error(
																				"错误",
																				"保存信息系统异常，请联系管理员",
																				function() {
																				});
															}
														});
											});

							$(function() {

								//添加图片
								if($("#attachmentid").val().length>0){
								loadFiletoContaner($("#attachmentid").val());
								}
								var typeurl="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0097";
								  //初始化加载类型
					            $.ajax({
					            	url:typeurl, 
					            	type : 'get',
					            	dataType : 'jsonp',
					            	success: function(result) {
					            

					                var labelList = result;
					                for (i = 0; i < result.length; i++) {
					                    var item = labelList[i];
					                    $("#SelectLabelType").append("<option value='" + item.id + "'>" + item.text + "</option>");
					                }


					            }});
								  
								  
								  
								$.ajaxSetup({
									async : false
								//取消异步
								});
								
								 var drag = "drag";
						            var operation = "edit";
						            if (operation === "view") {
						                drag = "";
						            }
						            
								var getLabelUrl = '<z:ukey key="com.zdsoft.finance.contract.printTplPageFieldList" context="admin"/>';
								
								//获取模版上标签的位置信息
								$.get(getLabelUrl,{
													PageID : $("#id").val(),
													actionname : operation,
													TradeObjectID:'',
													flagName:''
												},
												function(result) {

													var listPosition = jQuery.parseJSON(result);
													for (i = 0; i < listPosition.length; i++) {
														var item = listPosition[i];

														var element = '<div class="jp-text jp-component jp-ininted ' + drag + '"' +
'style="top: ' + item.top + 'px; left: ' + item.left + 'px; width: ' + item.width + 'px; height: ' + item.height + 'px; z-index: 101;">'
																+ '<span class="jp-text-content" id='
																+ item.labelid
																+ ' style="font-size:'
																+ item.fontsize
																+ ' !important">'
																+ item.labelname
																+ '</span>' +
																'</div>';

														$(".jp-page").append(element);
														$(".drag").data("draggable",{options : {disabled : false}});

													}

													
													 $(".drag").click(function(ee) {

											                $(this).toggleClass("ui-selected");
											                 if ($(this).is(".ui-selected")) {
											                      $(this).toggleClass("ui-selected1"); 
											                  }
											                 
											         
											            });
													
													 jQuery(".drag").resizable().draggable();
													 jQuery('.drag').draggable({ containment: 'parent' });  
													  var containment = jQuery('.drag').draggable('option', 'containment'); 
													  jQuery('.drag').draggable('option', 'containment', 'parent'); 
												
													 
													  //添加书签控制不能超出边界
													  jQuery(".jp-page").bind('DOMNodeInserted', function(e) {  
														  jQuery('.ui-draggable').draggable({ containment: 'parent' });  
														  var containment = jQuery('.ui-draggable').draggable('option', 'containment'); 
														  jQuery('.ui-draggable').draggable('option', 'containment', 'parent');  
														}); 
												});
							});

						});

		function loadFiletoContaner(attachmentid) {

			$(".jp-paper-background").attr("src",essdownUrl + '&attachmentId=' + attachmentid);
		}

		$("#jp-saveextend").click(
						function() {

							var Avr = new Array();

							$(".jp-page > .jp-component").each(
									function() {
										var widthE = $(this).width();
										var heightE = $(this).height();
										var leftE = $(this).position().left;
										var topE = $(this).position().top;
										var labelidE = $(this).find("span").attr("id")
										var fontsizeE = $(this).find("span").css("font-size");
										var labelName=$(this).find("span").text();
										Avr.push({
											width : widthE,
											height : heightE,
											left : leftE,
											top : topE,
											labelid : labelidE,
											PageID : $("#id").val(),
											fontsize : fontsizeE,
											labelName:labelName
										});

									});

							var filedSaveUrl = '<z:ukey key="com.zdsoft.finance.contract.printTplPageField_Save" context="admin"/>';

							//保存模版上标签的位置
							$.post(filedSaveUrl, {
								ElmentJsonString : JSON.stringify(Avr)
							}, function(result) {

								alert(result.msg);
							})
							
							
							
							

						});
		
		//获取所选择类型的标签
		 function selectType(o) {
	            //获取所选择类型的标签
	            var $sel = $(o).find(":selected");
	         
	           var urlsysLabel = '<z:ukey key="com.zdsoft.finance.contract.getSysLabelBylabelType" context="admin"/>';

	            $.get(urlsysLabel, { LabelType: $sel.val() }, function(result) {

	            var labelList = jQuery.parseJSON(result);
	            $("#SelectLabel").find("option").remove();
	                if (labelList.length == 0) {
	                    $("#SelectLabel").append('<option value="0">请选择标签</option>');
	                }
	                else {
	                	  $("#SelectLabel").append('<option value="0">请选择标签</option>');
	                    for (i = 0; i < labelList.length; i++) {
	                        var item = labelList[i];
	                        $("#SelectLabel").append("<option value='" + item.labelName + "' id='" + item.id + "'>" + item.labelName + "</option>");
	                    }
	                }

	            })
	        }			
		
		 //选择标签方法
	        function select(o) {
	            var $sel = $(o).find(":selected");
	            var v = $sel.val();
	            var id = $sel.attr("id");

	            if (id) {
	                var $content = $(".ui-selected").find(".jp-text-content");
	                $content.text(v);
	                $content.attr("id", id);
	            }
	        }
		 
	        //选择字体
	        function selectFontSize(o) {

	            var $sel = $(o).find(":selected");
	            var v = $sel.val();

	            if (v && v != "0") {
	                var $content = $(".ui-selected").find(".jp-text-content");
	                $content.css("font-size", v + "px");
	            }
	        }
	</script>
</body>
</html>