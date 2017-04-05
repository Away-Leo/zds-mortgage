<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../common/common_upload.jsp" %>
<!-- 上传征信 -->		
<form id="credit_form" class="zui-form " method="post" enctype="multipart/form-data">
	<div class="page-box">
	<div class="page-title">上传征信</div>
	<div class="p5">
		<table class="table-detail">
			<tr>
				<td class="td-title pct10">上传征信</td>
				<td class="pct20">
					<dl class="form-item">
						<dd class="detail">
							<input type="hidden" id="creditAttachment" name="creditAttachment" value="${attachmentIds }"/>
							<div>
								<span id="creditUploadFile" class="f12">
									<c:if test="${empty fileStoreVos }">注：未上传附件</c:if>
									<c:if test="${not empty fileStoreVos }">
										<c:forEach var="c" items="${fileStoreVos }">
											<div id="fileDiv_${c.attachmentId }" >${c.fileName }
												<a class='ml5 c-blue'  name='deleteLink' data-id="${c.attachmentId }" data-filefiled='creditAttachment'>删除</a>
												<a class='ml5 c-blue'  name='downLink'  data-id="${c.attachmentId }" >下载</a>
											</div>
										</c:forEach>
									</c:if>
								</span>
							</div>
						</dd>
					</dl>
				</td>
				<td class="td-title pct10">
				<dl class="form-item">
					<dt class="title"></dt>
					<dd class="detail">
						<a href="javascript:void(0)" onclick="addFile()">
							<button class="btn-orange" type="button"><i class="icon-btn36"></i>上传</button>
						</a>
					</dd>
				</dl>
				
				</td>
				<td class="pct20">
				</td>
				<td class="td-title pct10"></td>
				<td class="pct30">
				</td>
			</tr>
		</table>
	</div>
	</div>	
</form>
<!-- 上传征信弹窗 -->
<div id="creditFileInfo" style="display: none">
	<form id="credit_file_form" class="zui-form" method="post" enctype="multipart/form-data">
		<input type="hidden" name="creditId" id="creditId" /> 
		<dl class="form-item block">
			<dt class="title">
				<b class="c-red mr5">*</b>上传：
			</dt>
			<dd class="detail mb10">
				<input id="file_upload" name="file_upload" type="file"  />
				<div style="width: 300px;">
					<span id="creditFileUploadStatus" class="f12">注：未上传附件</span>
				</div>
			</dd>
		</dl>
	</form>
</div>

<script type="text/javascript">
seajs.use(['jquery','zd/jquery.zds.page.callback','zd/jquery.zds.table'], function($, CALLBACK) {
	//初始化部署流程dialog
	$("#creditFileInfo").Zdialog({width: 500, height: 280, closed: true,title:'上传征信文件',
        buttons: [
            {
                id: 'message-btn',
                text: '确定',
                buttonCls: 'btn-blue',
                handler: function () {
                	var creditId = $('#creditId').val();
                	if(creditId ==null||creditId == ""){
                		$.ZMessage.warning("警告", "请上传附件!", function () {});
        				return;
                	}
                	var creditUploadFile = $("#creditUploadFile").html().trim();
                	if(creditUploadFile=="注：未上传附件"){
                		$("#creditUploadFile").html("");
                	}
                	$("#creditFileUploadStatus").find("a[name='deleteLink']").attr("data-filefiled","creditAttachment");
                	$("#creditUploadFile").append($("#creditFileUploadStatus").html());
                	var creditAttachments=$("#creditAttachment").val();
                	if(creditAttachments!=""){
                		$("#creditAttachment").val(creditAttachments+","+$("#creditId").val());
                	}else{
                		$("#creditAttachment").val($("#creditId").val());
                	}
                	$("#creditFileUploadStatus").html("");
                	$("#creditId").val("");
                	$("#creditFileInfo").Zdialog("close");
                }
            },
            {
                id: 'message-btn',
                text: '取消',
                buttonCls: 'btn-gray',
                handler: function () {
            		$("#creditId").val("");
                    $("#creditFileInfo").Zdialog("close");
                }
            }
        ]
    });
	//部署流程
	window.addFile = function() {
		$("#creditId").val("");
		$('#creditFileUploadStatus').html('注：未上传附件');  
	    $("#creditFileInfo").Zdialog("open");  
	};         
	
   	/**
   	 * 普通附件上传方法
   	 *@param multi 上传类型,true 多个上传
   	 *@param objId 回调函数名称拼接
   	 */
   	 var fileTypes="*.bmp;*.jpg;*.gif;*.wmf;*.png";
	 window.initCommonUpload(true,"",fileTypes);
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
	 */
	showFileContent(attachements,"creditFileUploadStatus",false,"creditId");
	var creditId = $("#creditId").val();
	if(creditId==""){
		creditId = attachementsId;
	}else{
		creditId+= "," + attachementsId;
	}
	$("#creditId").val(creditId);
}
</script>