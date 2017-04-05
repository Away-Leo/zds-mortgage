<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="com.zdsoft.framework.core.common.configure.AppParameter" %>
<%
	String resServerUpload = request.getContextPath();
%>
<link  rel="stylesheet"    href="<%=resServerUpload%>/assets/js/vendor-modules/uploadify/uploadify.css"  >
<script type="text/javascript" src="<%=resServerUpload %>/assets/js/zd/jquery.min.js"></script>
<script type="text/javascript" src="<%=resServerUpload %>/assets/js/zd/uploadify.js"></script>

<script type="text/javascript">
// 上传相关js
var upload_url = '<z:ukey key="public.ess.upload"   context="admin"/>';
var essdownUrl = '<z:ukey key="public.upload.download"  context="admin"/>';
var delattachment = '<z:ukey key="public.upload.delattachment"  context="admin"/>';

/**
 * 普通附件上传方法
 *@param multi 上传类型
 */
function initCommonUpload(multi) {
	var multiFlag = false;
	if(multi){
		multiFlag=multi;
	}
	$("input[name='file_upload']").uploadify({
		'multi': multiFlag,
		'swf'      : '<%=resServerUpload %>/assets/js/zd/uploadify.swf',
		'uploader' : upload_url,
		'buttonText':'上传资料',
		'width':'80',
		'debug':false,
		'onUploadSuccess' : function(file, data, response,objFileId) {
			var objIds=objFileId.split("_");
			var objId=objIds[objIds.length-1];
			var fileCallback ="uploadFileCallback";
			try {
                var fileCallbackFun = eval(fileCallback);
                //根据属性拼接回调函授
                if (typeof fileCallbackFun === "function") {
                    // 回调函数以函数声明时的函数名传递 如 callback
                    fileCallbackFun(file, data, response,objId);
                }
            } catch (e) {}
			
		}
	});
}


seajs.use(['jquery', 'zd/jquery.zds.message'],function ($) {
	
	/**
	 * 组装显示值、删除、下载
	 * @param file 文件内容
	 * @param showId 传入需要显示的地方id
	 * @param singleFlag true 当个文件
	 * @param fileField 当个文件ID字段
	 */
	window.showFileContent=function(file,showId,singleFlag,fileField) {
		var fileLabel = file.fileLabel;
		var attachementsId = file.id;
		//获取html
		var html=$('#'+showId).html();
		if(singleFlag){
			$('#'+showId).html("");
			html = "<div id='fileDiv_"+attachementsId+"' >"+fileLabel+"<a class='ml5 c-blue' field='"+fileField+"'  name='deleteLink' data-id='"+attachementsId+"'>删除</a><a class='ml5 c-blue' field='"+fileField+"' name='downLink'  data-id='"+attachementsId+"'>下载</a></div>";
		}else{
			if(html==""||html=="备注：未上传附件"||html=="注：未上传附件"){
				html = "<div id='fileDiv_"+attachementsId+"' >"+fileLabel+"<a class='ml5 c-blue' field='"+fileField+"' name='deleteLink' data-id='"+attachementsId+"'>删除</a><a class='ml5 c-blue' field='"+fileField+"' name='downLink'  data-id='"+attachementsId+"'>下载</a></div>";
			}else{
				html=html+"<div id='fileDiv_"+attachementsId+"'>"+fileLabel+"<a class='ml5 c-blue' field='"+fileField+"' name='deleteLink' data-id='"+attachementsId+"'>删除</a><a class='ml5 c-blue' field='"+fileField+"' name='downLink' data-id='"+attachementsId+"'>下载</a></div>";
			}
		}
		
		$('#'+showId).html(html);
		
		//点击删除 文件
		$("div[id^='fileDiv_'] a").live("click", function(){
			var field = $(this).attr("field");
			var aName = $(this).attr("name");
			var fileId = $(this).data("id");
			if(aName=="downLink"){//下载
				var downUrl=essdownUrl+'&attachmentId='+fileId;
				window.downLoadFile(downUrl);
			}else{//删除
				var delete_url=delattachment+"&jsoncallback=?&attachmentId=" + fileId;
				
			    //调用删除
				/* $.ajax({
					type :'post',
					url : delete_url,
					dataType : 'jsonp',
					success : function(result) {
						if(result.success){
							$.ZMessage.success("成功", "删除成功!", function () { });
							$("#fileDiv_"+fileId).remove();
						}else{
							$.ZMessage.error("错误","删除失败!", function () {});
						}
					}
				}); */
				if(typeof $("#fileDiv_"+fileId)[0]!="undefined"){
					//删除上传ID对应在值
					var fileIds = $("#"+field).val();
					if(fileIds!=""){
						var fileArr=fileIds.split(",");
						if(fileArr.length==1){
							$("#"+field).val("");
						}else{
							var arr_index = $.inArray(fileId,fileArr);
							fileArr.splice(arr_index,1);
							$("#"+field).val(fileArr.toString());
						}
						
					}
					//删除行
					$.ZMessage.success("成功", "删除成功!", function () { });
					$("#fileDiv_"+fileId).remove();
				}
			}
		});
	}

	/*
	 * 需要下载请加方法downLoadFile
	 */
	window.downLoadFile=function(){
		window.location.href = essdownUrl;
	}
	
	/*
	 * 需要下载请加方法downLoadFile
	 */
	window.downLoadFile=function(essdownUrl){
		window.location.href = essdownUrl;
	}
	
	/*
	 * 下载
	 */
	window.downFile=function(fileId){
		var downUrl=essdownUrl+'&attachmentId='+fileId;
		window.location.href = downUrl;
	}
	
	/*
	 * 删除
	 */
	window.delFile=function(fileId){
		var delete_url=delattachment+"&jsoncallback=?&attachmentId=" + fileId;
		$.ZMessage.success("成功", "删除成功!", function () { });
		$("#fileDiv_"+fileId).remove();
	}
	
})
</script>
