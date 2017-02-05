<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<!-- 编辑项目附件对话框 -->
<div id="uploadAttachment" style="display: none">
	<div class="p10">
		<form id="projectAttaForm" class="zui-form " method="post" enctype="multipart/form-data">
			<div class="page-box">
				<dl class="form-item span2">
					<dt class="title"><b class="c-red mr5">*</b>附件名称：</dt>
					<dd class="detail">
						<label> 
							<input class="zui-input zui-validatebox" validate-type="Require" id="attaNm" name="attaNm">
						</label>
					</dd>
				</dl>
				<dl class="form-item span2">
					<dt class="title"><b class="c-red mr5">*</b>附件类型：</dt>
					<dd class="detail">
						 <input class="zui-combobox zui-validatebox" id="attaType" name="attaType" type="hidden"
	                             data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM0008"
	                             data-valuefield="fullcode" data-textfield="name" validate-type="Require">
					</dd>
				</dl>
				<dl class="form-item span2">
					<dt class="title"><b class="c-red mr5">*</b>选择文件：</dt>
					<dd class="detail">
						<label> 
							<input type="hidden" id="attachmentId" name="attachmentId"/>
							<input type="hidden" id="attachmentLabel" name="attachmentLabel"/>
							<input id="file_upload" name="file_upload" type="file" />
							<span id="attachmentUploadStatus" class="f12">
								<span id="uploadRemark">备注：未上传附件</span>
							</span>
						</label>
					</dd>
				</dl>
				<dl class="form-item span2">
	               	<dt class="title">备注：</dt>
	                <dd class="detail">
		                <label>
		                	<textarea class="zui-area zui-validatebox" id="remark" name="remark" validate-type="Length[0-200]" placeholder="最多可以输入200个字符"></textarea>
		                </label>
		                <div class="zd-area">
                            <span class="zd-curval">0</span>/<span class="zd-maxval">200</span>
                         </div>
	                </dd>
	               </dl>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.dialog', 'zd/jquery.zds.seleter'], function($, CALLBACK) {
	// 附件上传对话框定义
	$("#uploadAttachment").Zdialog({
		width: 700, 
		height: 350, 
		closed: true, 
		title:'附件上传',
		buttons: [{
			id: 'message-btn',
			text: '保存', 
			buttonCls: 'btn-blue',
			handler: function () {
				var validate = $.ZUI.validateForm($('#projectAttaForm'));
		    	if (!validate) {
		    		$.ZMessage.error("错误", "数据验证失败!", function () {});
		            return false;
		        }
		    	var attachmentId = $("#attachmentId").val();
		    	if (attachmentId == "") {
		    		$.ZMessage.error("错误", "未上传文件!", function () {});
		            return false;
		    	}
               	var datas = $("#projectAttaForm").serialize();
               	$.ajax({
                   type: 'post',
                   url: '<z:ukey key="com.zdsoft.finance.projectatta.saveOrUpdateProjectAtta" context="admin"/>&projectId=${projectId}',
                   data: datas,
                   dataType: 'json',
                   success: function (data) {
                       if (data.resultStatus == 0) {
	                       	$.ZMessage.success("成功", "添加附件成功！", function () {
	                       		doSearch();
	                       		$("#uploadAttachment").Zdialog('close');
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
				$('#uploadAttachment').Zdialog('close');
			}
		}],
		onOpen: function() {
			$("#attachmentUploadStatus").children("[id!='uploadRemark']").remove();
			$("#attachmentId").val("");
			$("#attachmentLabel").val("");
			$("#uploadRemark").show();
		},
		onClose: function() {
		}
	});
	$.ZUI.init();
	window.initUploadify();
});
// 文件上传方法
window.initUploadify = function() {
	// 附件上传
	$('#file_upload').uploadify({
			'multi': false,
			'swf': swf_url,
			'uploader' : upload_url,
			'buttonText':'上传资料',
			'width':'80',
			'debug':false,
			'uploadLimit':'3',
			'onUploadSuccess' : function(file, data, response) {
				var attachements = JSON.parse(data.toString());
				var result = attachements[0].success;
				attachements = attachements[0].attachements;
				var fileLabel = attachements.fileLabel;
				var attachementsId = attachements.id;
				$("#uploadRemark").hide();
				var html = "<span>"+fileLabel+"&nbsp;" + 
							"<a onclick=attrDel(this,'"+attachementsId+"') style='color:#4692f0;'>删除</a>&nbsp;" +
							"<a onclick=attrDownload('"+attachementsId+"') style='color:#4692f0;'>下载</a>" +
							"<br>" +
							"</span>";
				$("#attachmentUploadStatus").append(html);
				$("#attachmentId").val(attachementsId);
				$("#attachmentLabel").val(fileLabel);
			}
		});
}
// 附件删除
window.attrDel = function(obj,attachmentId) {
	$(obj).parent().remove();
	$("#attachmentId").val("");
	$("#attachmentLabel").val("");
	$("#uploadRemark").show();
}
// 附件下载
window.attrDownload = function(attachmentId) {
	window.location.href = ess_download_url + "&attachmentId="+attachmentId;
}
</script>