<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../common/common_upload.jsp"%>

<!-- 模版上传 -->
<form id="credit_form" class="zui-form " method="post"
	enctype="multipart/form-data">
	<div class="page-box">
		<div class="p5">
			<table class="table-detail">
				<tr>
					<td class="pct80"><input class="zui-input zui-validatebox"
						id="creditAttachment" name="creditAttachment"
						style="width: 500px;" validate-type="Require"
						value="${bussPrintTplPageVo.creditAttachment}" />
							<input id="attachmentid" name="attachmentid" type="hidden" value="${bussPrintTplPageVo.attachmentid}"/>
						</td>
					<td class="td-title pct20"><a href="javascript:void(0)"
						onclick="addFile()">
							<button class="btn-orange" type="button">
								<i class="icon-btn36"></i>上传
							</button>
					</a></td>

				</tr>
			</table>
		</div>
	</div>
</form>
<!-- 上传征信弹窗 -->
<div id="creditFileInfo" style="display: none">
	<form id="credit_file_form" class="zui-form" method="post"
		enctype="multipart/form-data">

		<dl class="form-item block">
			<dt class="title">
				<b class="c-red mr5">*</b>上传：
			</dt>
			<dd class="detail mb10">
				<input id="file_upload" name="file_upload" type="file" />
				<div style="width: 300px;">
					<span id="creditFileUploadStatus" class="f12">注：未上传附件</span>
				</div>
			</dd>
		</dl>
	</form>
</div>

<script type="text/javascript">
	seajs.use([ 'jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.table',
			'zd/jquery.zds.dialog' ], function($, CALLBACK) {

		//初始化部署流程dialog
		$("#creditFileInfo").Zdialog({
			width : 500,
			height : 280,
			closed : true,
			title : '模版上传',
			buttons : [ {
				id : 'message-btn',
				text : '确定',
				buttonCls : 'btn-blue',
				handler : function() {
					var attachmentid = $('#attachmentid').val();
					if (attachmentid == null || attachmentid == "") {
						$.ZMessage.warning("警告", "请上传附件!", function() {
						});
						return;
					}

					$("#creditFileInfo").Zdialog("close");
				}
			}, {
				id : 'message-btn',
				text : '取消',
				buttonCls : 'btn-gray',
				handler : function() {
					$("#attachmentid").val("");
					$("#creditFileInfo").Zdialog("close");
				}
			} ]
		});

		//部署流程
		window.addFile = function() {
			$("#attachmentid").val("");
			$('#creditFileUploadStatus').html('注：未上传附件');
			$("#creditFileInfo").Zdialog("open");
		};

		/**
		 * 普通附件上传方法
		 *@param multi 上传类型,true 多个上传
		 *@param objId 回调函数名称拼接
		 */
		window.initCommonUpload(true);
	});

	/**
	 *普通上传回调函数
	 */
	window.uploadFileCallback = function(file, data, response) {

		var attachements = JSON.parse(data.toString());
		var result = attachements[0].success;
		attachements = attachements[0].attachements;
		var fileLabel = attachements.fileLabel;
		var fileType = attachements.fileType;
		var attachementsId = attachements.id;
		/**
		 * 组装显示值、删除、下载
		 * @param file 文件内容
		 * @param showId 传入需要显示的地方id
		 * @param single true 当个文件
		 */
		 //dengyy 2017.3.13 添加上传数据回显信息
		showFileContent(attachements,"creditFileUploadStatus","","attachmentid");
		 
		 $('#attachmentid').val(attachementsId);
		$("#creditAttachment").val(attachements.filePath);
		loadFiletoContaner(attachementsId);
	}
</script>