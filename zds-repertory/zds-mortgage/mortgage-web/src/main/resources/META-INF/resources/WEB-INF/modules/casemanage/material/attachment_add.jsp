<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 编辑附件对话框 -->
<div id="uploadAttachment_case_material" style="display: none">
	<div class="p10">
		<form id="case_material_AttaForm" class="zui-form " method="post" enctype="multipart/form-data">
			<input type="hidden" id="position" name="position" value=""/><!-- 是否定位 -->
			<input type="hidden" id="positionPath" name="positionPath" value=""/><!-- 定位 -->
			<input type="hidden" id="source" name="source"/><!-- 来源 -->
			<div class="page-box">
				<!-- <dl class="form-item span2">
					<dt class="title"><b class="c-red mr5">*</b>文件类型：</dt>
					<dd class="detail">
	                      <input class="zui-combotree zui-validatebox" 
	                      		id="attaType" name="attaType" type="hidden"
	                             data-data="[{'fullcode':'YWDM01','name':'征信授权书'},{'fullcode':'YWDM02','name':'征信报告'}]"
	                             data-valuefield="fullcode" data-textfield="name" 
	                             validate-type="Require" 
	                             data-parent-value="false" 
	                             data-toggle="combotree" 
	                             data-multiple="false">
					</dd>
				</dl> -->
				<dl class="form-item span2">
					<dt class="title"><b class="c-red mr5">*</b>选择文件：</dt>
					<dd class="detail">
						<label> 
							<input type="hidden" id="attachmentId" name="attachmentId"/>
							<input id="file_upload" name="file_upload" type="file" />
							<span id="attachmentUploadStatus" class="f12">注：未上传附件</span>
						</label>
					</dd>
				</dl>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog', 'zd/jquery.zds.seleter'], function($, CALLBACK) {
	// 附件上传对话框定义
	$("#uploadAttachment_case_material").Zdialog({
		width: 700, 
		height: 350, 
		closed: true, 
		title:'附件上传',
		buttons: [{
			id: 'message-btn',
			text: '保存', 
			buttonCls: 'btn-blue',
			handler: function () {
				var validate = $.ZUI.validateForm($('#case_material_AttaForm'));
				
		    	if (!validate) {
		    		$.ZMessage.error("错误", "数据验证失败!", function () {});
		            return false;
		        }
		    	
		    	var attachmentId = $("#attachmentId").val();
		    	if (attachmentId == "") {
		    		$.ZMessage.error("错误", "未上传文件!", function () {});
		            return false;
		    	}
		    	
               	var datas = $("#case_material_AttaForm").serialize();
               	$.ajax({
                   type: 'post',
                   url: '<z:ukey key="com.zdsoft.finance.caseMaterial.saveCaseMaterialListAtta" context="admin"/>&caseApplyId=${caseApplyId}',
                   data: datas,
                   dataType: 'json',
                   success: function (data) {
                       if (data.resultStatus == 0) {
	                       	$.ZMessage.success("成功", "添加附件成功！", function () {
	                       		//
	                       		doSearch();
	                       		//
	                       		$("#uploadAttachment_case_material").Zdialog('close');
	                        });	
                       }else{
                       		$.ZMessage.error("错误", data.msg);
                       }
                   },
                   error: function () {
                   		$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
                   			
						});
                   }
               });
			}
		},{
			id: 'cancel-btn',
			text: '取消',
			buttonCls: 'btn-gray',
			handler: function () {
				$('#uploadAttachment_case_material').Zdialog('close');
			}
		}],
		onOpen: function() {
			//打开前初始化
			$("#attachmentId").val("");
		},
		onClose: function() {
			//关闭时执行
		}
	});
	
	//普通上传附件初始化
    $(function(){
    	/**
    	 * 普通附件上传方法
    	 *@param multi 上传类型,true 多个上传
    	 *@param objId 回调函数名称拼接
    	 */
		 window.initCommonUpload(true);
	}); 
});

/**
 *普通上传回调函数
 */
window.uploadFileCallback=function (file, data, response){
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
	 * @param fileField 当个文件ID字段
	 */
	showFileContent(attachements,"attachmentUploadStatus","","attachmentId");
	var cooperationAttachment = $("#attachmentId").val() + "," + attachementsId;
	if(cooperationAttachment.indexOf(",") == 0){
		cooperationAttachment = cooperationAttachment.substring(1,cooperationAttachment.length);
	}
	$("#attachmentId").val(cooperationAttachment);
}
</script>