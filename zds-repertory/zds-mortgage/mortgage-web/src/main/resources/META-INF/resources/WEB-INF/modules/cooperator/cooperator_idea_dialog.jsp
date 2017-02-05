<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common_upload.jsp"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="ideaDialogDiv">
	    <div id="InfoDialog" >
	        <form id="contactForm" class="zui-form mt15" >
	        	<input type="hidden" style="display:none" name="id"  id="infoid" value="${infoVo.id}" >
	            <input type="hidden" style="display:none" id="capitalistId" name="capitalistId" value="${capitalistId}">
	            <dl class="form-item block">
	                <dt class="title"><b class="c-red mr5">*</b>协议名称:</dt>
	                <dd class="detail">
	                    <label>
	                    	<input class="zui-input" name="agreementName"  id="agreementName" value="${infoVo.agreementName}" >
	                    </label>
	                </dd>
	            </dl>
	            <dl class="form-item block">
	            <input type="" name="attachmentId"  id="attachmentId" value="${infoVo.attachmentId}" >
					<dt class="title">
						附件信息:
					</dt>
					<dd class="detail">
						<input id="file_upload" name="file_upload" type="file" />
						<div>
							<span id="importProcessUploadStatus" class="f12">
								<c:if test="${infoVo.attachmentId eq null || infoVo.attachmentId == ''}">
									<span id="attrRemark">备注：未上传附件</span>
								</c:if>
								<c:if test="${!(infoVo.attachmentId eq null || infoVo.attachmentId == '')}">
									<c:forEach items="${attrs}" var="attr">
										<span>${attr.fileLabel} &nbsp;
										<a onclick="attrDel(this,'${attr.id}')" style="color:#4692f0;">删除</a>&nbsp;
										<a onclick="attrDown('${attr.id}')" style="color:#4692f0;">下载</a>
										<br>
										</span>
									</c:forEach>
								</c:if>
							</span>
						</div>
					</dd>
				</dl>
	        </form>
	    </div>
	</div>
	<script type="text/javascript">
    seajs.use(['jquery','zd/jquery.zds.form','zd/jquery.zds.table'], function ($) {
    	$("#ideaDialogDiv").Zdialog({
            width: 700, height: 300, closed: false, title: "附件上传",isDestroy:true,
            buttons: 
            [
                {
                    id: 'message-btn',
                    text: '确定',
                    buttonCls: 'btn-blue',
                    handler: function () {
                    	var contactForm = $('#contactForm').serialize();
                            $.ajax({
                                type: 'post',
                                url: '<z:ukey key="com.zdsoft.finance.cooperator.idea.save" context="admin"/>',
                                data: contactForm,
                                dataType: 'json',
                                success: function (data) {
                                    if (data.resultStatus == 0) {
                                    	$.ZMessage.success("提示", "保存成功", function () {
                    	                    $(".zd-message").ZWindow("close");
                    	                });
                                    	$('#idea_datagrid_table').ZTable("reload", {});
                                    	$("#ideaDialogDiv").Zdialog("close");
                                    }
                                },
                                error: function () {
                                	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
                                        $(".zd-message").ZWindow("close");
                                    });
                                	$("#ideaDialogDiv").Zdialog("close");
                                }
                            });
                    }
                },
                {
                    id: 'message-btn',
                    text: '关闭',
                    buttonCls: 'btn-gray',
                    handler: function () {
                    	$("#ideaDialogDiv").Zdialog("close");
                    }
                }
            ]
        });
    	//删除附件
    	window.attrDel = function(_this,_id){
    		$(_this).parent().remove();
    		var att = $("#attachmentId").val();
    		att = att.replaceAll(_id,"");
    		att = att.replaceAll(",,",",");
    		if(att.indexOf(",") == 0){
    			att = att.substring(1,att.length);
    		}
    		if(att.lastIndexOf(",") + 1 == att.length){
    			att = att.substring(0,att.length - 1);
    		}
    		if(att==""){
    			$("#importProcessUploadStatus").val("<span id='attrRemark'>备注：未上传附件</span>");
    		}
    		
    		$("#attachmentId").val(att);
    	};
    	//下载
    	window.attrDown = function(_id){
    		var essdownUrl = '<z:ukey key="public.upload.download"  context="admin"/>';
    		window.location.href = essdownUrl+"&attachmentId="+_id;
    	};
    	//上传附件初始化
        $(function(){
    		 window.initUpload();
    	});
    	//初始化
        $.ZUI.init();
    });
  //附件上传方法
    function initUpload() {
    	// 上传相关js
    	var upload_url = '<z:ukey key="public.ess.upload"   context="admin"/>';
    	//模拟环境
    	var i = 1;
    	$.ajax({
    		type :'post',
    		url : upload_url+"&jsoncallback=?",
    		dataType : 'jsonp',
    		success : function(result) {

    		}
    	});

    	$('#file_upload').uploadify({
    		'multi': true,
    		'swf'      : '<%=resServerUpload %>/assets/js/zd/uploadify.swf',
    		'uploader' : upload_url,
    		'buttonText':'上传资料',
    		'width':'80',
    		'debug':false,
    		'uploadLimit':'3',
    		'onUploadSuccess' : function(file, data, response) {
    			var attachements = JSON.parse(data.toString());
    			result = attachements[0].success;
    			attachements = attachements[0].attachements;
    			var fileLabel = attachements.fileLabel;
    			var fileType = attachements.fileType;
    			var attachementsId = attachements.id;
    			$("#attrRemark").remove();
    			var html = "<span>"+fileLabel+"&nbsp;" + 
    						"<a onclick=attrDel(this,'"+attachementsId+"') style='color:#4692f0;'>删除</a>&nbsp;" +
    						"<a onclick=attrDown('"+attachementsId+"') style='color:#4692f0;'>下载</a>" +
    						"<br>" +
    						"</span>";
    			//$("#importProcessUploadStatus").append(html);
    			//var attachments = $("#attachmentId").val() + "," + attachementsId;
    			$("#importProcessUploadStatus").html("");
    			$("#importProcessUploadStatus").append(html);
    			var attachments = attachementsId;
    			if(attachments.indexOf(",") == 0){
    				attachments = attachments.substring(1,attachments.length);
    			}
    			$("#attachmentId").val(attachments);
    		}
    	});
    }
</script>