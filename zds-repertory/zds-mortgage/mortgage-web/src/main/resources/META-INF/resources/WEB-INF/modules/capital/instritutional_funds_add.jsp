<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<%@ include file='../common/common_js.jsp'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<title>信托计划分配</title>
</head>
<body>
<div class="page-box">
	<div class="p10">
		<form id="credit_entrust_form" class="zui-form " method="post"
			enctype="multipart/form-data">
			<input id="id" name="id" type="hidden" value="${fundsVo.id }"/>
	        <div class="page-box">
				<div class="page-title"></div>
				<dl class="form-item">
					<dt class="title">机构：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-disabled zui-validatebox" validate-type="Require"
							 id="orgName" value="${fundsVo.orgName }" name="orgName" >
						</label>
					</dd>
				</dl>
				<dl class="form-item block">
					<dt class="title">资方：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-validatebox" validate-type="Require"
							 id="capitalName" value="${fundsVo.capitalNames }" name="capitalNames">
							 <input type="hidden" id="capitalId" name="capitalIds" value="${fundsVo.capitalIds }"/>
						</label>
                        <a id="managementChooser" class="btn-blue" href="javascript:void(0);">选择</a>
					</dd>
				</dl>
				<dl class="form-item">
					<dt class="title">修改时间：</dt>
					<dd class="detail">
						<label> <input class="zui-input zui-disabled zui-validatebox" validate-type="Require"
							 id="updateTime" value="${updateTime }" name="updateTime" >
						</label>
					</dd>
				</dl>
				<dl class="form-item block">
                	<dt class="title">备注：</dt>
	                <dd class="detail">
		                <label>
		                	<textarea class="zui-area zui-validatebox" id="remark" name="remark" validate-type="Length[0-200]" placeholder="最多可以输入200个字符">${fundsVo.remark }</textarea>
		                </label>
	                </dd>
                </dl>	
			</div>
			<div class="form-btn">
               	<button id="saveBtn" type="button" class="btn-blue">保存</button>
            </div>
		</form>
	</div>
</div>
<div id="capital_dialog_add" style="display: none">
	<div id="Zbothselecter">
		<div class="power-own" style="height: 292px;">
			<ul class="power-select-list">
			</ul>
		</div>
		<div class="power-use" style="height: 292px;">
			<ul class="power-select-list">
			</ul>
		</div>
		<div class="power-btn" style="margin-top: 0px;padding-top: 70px;">
			<a href="javascript:void(0);" class="power-btn-blue" id="capitalAdd">添加<i class="icon-power-delete ml5"></i></a>
			<a href="javascript:void(0);" class="power-btn-gray" id="capitalDel"><i class="icon-power-add mr5"></i>移除</a>
			<a href="javascript:void(0);" class="power-btn-blue" id="capitalAddAll">全部添加<i class="icon-power-delete ml5"></i></a>
			<a href="javascript:void(0);" class="power-btn-gray" id="capitalDelAll"><i class="icon-power-add mr5"></i>全部移除</a>
		</div>
	</div>
</div>
<div id="zds_btn_selecter"></div>
<div id="chooseMember"></div>
		<script type="text/javascript">
		seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog','zd/jquery.zds.combobox','zd/jquery.zds.table','zd/jquery.zds.seleter'], function($, CALLBACK) {
			//编辑
			$("#capital_dialog_add").Zdialog({
		        width: 550, height: 410, closed: true, title: "添加合同",
		        buttons: [
		            {
		                id: 'message-btn',
		                text: '确定',
		                buttonCls: 'btn-blue',
		                handler: function () {
		                	var addRows = $("#Zbothselecter .power-use .power-select-list").find('li');
		                	var capitalIds = [];
		                	var capitalNames = [];
		            		$(addRows).each(function(i,v){
		            			capitalIds.push($(this).attr("data-id"));
		            			capitalNames.push($(this).text());
		            		});
		            		
		            		$('#capitalId').val(capitalIds);
		            		$('#capitalName').val(capitalNames);
		            		
		            		$('#capital_dialog_add').Zdialog('close');
		                }
		            },
		            {
		                id: 'message-btn',
		                text: '取消',
		                buttonCls: 'btn-gray',
		                handler: function () {
		                    $("#capital_dialog_add").Zdialog("close");
		                }
		            }
		        ]
		    });
			
			//选中或不选中
			window.capitalClick = function(_this){
				$(_this).toggleClass("selected");
			};
			
			//设置选择数据和事件
			function Zbothselecter(selectData){
				var selectData = eval(selectData);
				var list = $("#Zbothselecter .power-own .power-select-list");
				var noList = $("#Zbothselecter .power-use .power-select-list");
				list.children().remove();
				noList.children().remove();
				$(selectData).each(function(i,v){
					$("#Zbothselecter .power-own .power-select-list").append("<li data-id=" + this.id + " onclick='capitalClick(this)'>"+this.cooperatorName+"</li>")
				});
				
				$("#capitalAdd").unbind("click").click(function(){
					var selectRows = list.find('.selected');
		            if (selectRows.length === 0) {
		                $.ZMessage.info('提示', '请选择要添加的合同模版！')
		            }
		            $.each(selectRows, function (i, item) {
		                var selVal = $(selectRows[i]).attr("data-id");
		                var selText = $(selectRows[i]).text();
		                var selObj = {};
		                selObj[selVal] = selText;
		                selectData.splice(-1, 0, selObj);
		                noList.append("<li data-id=" + selVal + " onclick='capitalClick(this)'>"+selText+"</li>");
		            });
		            selectRows.remove();
				});
				$("#capitalDel").unbind("click").click(function(){
					var selectRows = noList.find('.selected');
		            if (selectRows.length === 0) {
		                $.ZMessage.info('提示', '请选择要移除的合同模版！')
		            }
		            $.each(selectRows, function (i, item) {
		                var selVal = $(selectRows[i]).attr("data-id");
		                var selText = $(selectRows[i]).text();
		                var selObj = {};
		                selObj[selVal] = selText;
		                selectData.splice(-1, 0, selObj);
		                list.append("<li data-id=" + selVal + " onclick='capitalClick(this)'>"+selText+"</li>");
		            });
		            selectRows.remove();
				});
				$("#capitalAddAll").unbind("click").click(function(){
					var selectRows = list.find('li');
		            if (selectRows.length === 0) {
		                $.ZMessage.info('提示', '没有合同模版数据')
		            }
		            $.each(selectRows, function (i, item) {
		                var selVal = $(selectRows[i]).attr("data-id");
		                var selText = $(selectRows[i]).text();
		                var selObj = {};
		                selObj[selVal] = selText;
		                selectData.splice(-1, 0, selObj);
		                noList.append("<li data-id=" + selVal + " onclick='capitalClick(this)'>"+selText+"</li>");
		            });
		            selectRows.remove();
				});
				$("#capitalDelAll").unbind("click").click(function(){
					var selectRows = noList.find('li');
		            if (selectRows.length === 0) {
		                $.ZMessage.info('提示', '没有合同模版数据')
		            }
		            $.each(selectRows, function (i, item) {
		                var selVal = $(selectRows[i]).attr("data-id");
		                var selText = $(selectRows[i]).text();
		                var selObj = {};
		                selObj[selVal] = selText;
		                selectData.splice(-1, 0, selObj);
		                list.append("<li data-id=" + selVal + " onclick='capitalClick(this)'>"+selText+"</li>");
		            });
		            selectRows.remove();
				});
			}
			
			$.ZUI.init();
			
			// 资方选择器
			$('#managementChooser').click(function(){
				$.ajax({
					url:'<z:ukey key="com.zdsoft.finance.capital.getManagements" context="admin"/>&jsoncallback=?',
					data:{},
					type:"post",
					dataType:"jsonp",
					success:function(rdata){
							Zbothselecter(rdata);
							$("#capital_dialog_add").Zdialog("open");
					}
				});
			});
			
			// 保存按钮点击
			$('#saveBtn').click(function(){
				var isValidate = $.ZUI.validateForm($('#credit_entrust_form'));
				if(isValidate){
					var param = $('#credit_entrust_form').serialize();
					$.ajax({
	                    type: 'post',
	                    url: '<z:ukey key="com.zdsoft.finance.capital.updateInstitutionFunds" context="admin"/>',
	                    data: param,
	                    dataType: 'json',
	                    success: function (data) {
	                        if (data.resultStatus == 0) {
	                        	$.ZMessage.success("成功", data.msg, function () {
	                        		setTimeout(function(){
	                                	ZDS_MESSAGE_CLIENT.refreshOpenner();
	                               	    ZDS_MESSAGE_CLIENT.closeSelf();
	                                },200);
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
				}
			});
			
		});
	</script>
</body>
</html>