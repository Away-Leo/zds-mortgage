<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
	String resServerUpload = request.getContextPath();
%>    
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--     <meta name="viewport" content="width=device-width, initial-scale=1"> -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
	<meta name="format-detection" content="telephone=no" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>智能问卷</title>
    
     <link rel="stylesheet"    type="text/css" href="<%=resServerUpload %>/assets/js/vendor-modules/bootstrap/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="<%=resServerUpload %>/assets/js/vendor-modules/huploadify/Huploadify.css">
	<script type="text/javascript" src="<%=resServerUpload %>/assets/js/zd/jquery.min.js"></script>
	<script type="text/javascript" src="<%=resServerUpload %>/assets/js/vendor-modules/huploadify/jquery.Huploadify.js"></script>
	  
	<style>
        body {
			background-color: #f3f3f3;
		}
		.container {
    width: 100% !important;
}
		.mr5 {
			margin-right: 5px;
		}

		.qs_title {
			border-bottom: 1px solid #ccc;
			padding: 5px 0;
			background-color: #fff;
		}
		.qs_user, .qs_item {
			padding-top: 10px;
			padding-bottom: 10px;
			border-bottom: 1px solid #eee;
			background-color: #fff;
		}
		.checkbox, .radio {
			position: relative;
			display: block;
			font-weight: 400;
			margin-bottom: 4px;
			padding-left: 25px;
			line-height: 25px;
			cursor: pointer;
			font-size: 14px;
		}
		.checkbox.checkbox-inline, .radio.radio-inline {
			display: inline-block;
			margin-top: 0;
			margin-left: 0;
			margin-right: 10px;
		}
		.checkbox input, .radio input {
			position: absolute;
			left: -9999px;
		}
		.checkbox i, .radio i {
			position: absolute;
			top: 6px;
			left: 0;
			display: block;
			width: 15px;
			height: 15px;
			outline: 0;
			color: #666;
		}
		.checkbox input:checked + i:before,
		.radio input:checked + i:before {
			opacity: 1;
			color: #f55152;
		}
		.upload input {
			opacity: 0;
			height: 32px;
		}
		.upload-info {
			color: #f55152;
			margin-top: 5px;
		}
		#ztree-sidebar li a{
	    	color: #333333;
	    }
		h4,.detail,label{color:#444;font-size:14px; font-weight:normal} 
    </style>
</head>

<body>
<div class="frm-content frm-bottom">
    <form class="zui-form" action="javascript:void(0);" id="questionnaireForm">
	   <div class="row qs_user">
	        <div class="col-xs-2">
	           		<c:if test="${empty main_picture_id}">
			            <img src="<%=resServerUpload %>/assets/images/user01@2x.png" alt="主借人" class="img-thumbnail">
		        	</c:if>
		        	<c:if test="${not empty main_picture_id}">
			            <img src="../upload/download?attachmentId=${main_picture_id}" alt="主借人" class="img-thumbnail">
		        	</c:if>
	        </div>
	        <div class="col-xs-2">
	            	<c:if test="${empty spouse_picture_id}">
			             <img src="<%=resServerUpload %>/assets/images/user02@2x.png" alt="主借人配偶" class="img-thumbnail">
		        	</c:if>
		        	<c:if test="${not empty spouse_picture_id}">
			            <img src="../upload/download?attachmentId=${spouse_picture_id}" alt="主借人配偶" class="img-thumbnail">
		        	</c:if>
	        </div>
	    </div>
	    <!-- 自动添加行 -->
	    <div class="row"  id="questionnaire"></div>
    </form>

     <!-- 按钮区域 -->
		<div class="form-btn"  id="questionnaire_btn" style="display: none;">
			<button id="questionnaire_btn_save" class="btn-blue mr10" >保存</button>
		</div>
</div>

<script type="text/javascript">
seajs.use(['jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.loading', 'zd/switch'],
		function ($, callback, Loading, Switch) {
	
	window.showPromptInfo=function(){
		$.ZMessage.success("成功", "操作成功",function(){});
	};
	window.showPromptError=function(){
		$.ZMessage.error("失败", "操作失败",function(){});
	};
	window.showPrompt=function(str){
		$.ZMessage.warning("提示", str,function(){});
	};
	$("#questionnaire_btn_save").on("click",function(){
		var questionObj=window.saveQuestionnaire();
		var saveUrl ='<z:ukey key="com.zdsoft.finance.question.saveQuestionnaire"  context="admin"/>';
		var params= "caseApplyId="+questionObj.caseApplyId;
			 params +="&sceneTypeCode="+questionObj.sceneTypeCode;
			 params +="&questionList="+JSON.stringify(questionObj.questionList);
			 if(questionObj.validFlg){
					$.ajax({
			            type: 'post',
			            url: saveUrl,
			            async: false,
			            data:params,     
			             dataType: 'json',
			             success: function (data) {
			                if (data.resultStatus == 0) {
			              	  	$.ZMessage.success("成功", data.msg, function () {});
			                }else{
			              	  	$.ZMessage.error("失败", data.msg, function () {}); 
			                }
			              }
			       });
			 }
	});
});

$(function(){
	//成功之后
	var phoneType = "${phoneType}";
	var data=JSON.parse('${question}');
	var Obj=$("#questionnaire");
		 Obj.attr("caseapplyid",data.caseApplyId);
		 Obj.attr("linktypecode",data.linkTypeCode);
		 
	for(var i=0;i<data.questionList.length;i++){
		 var type=data.questionList[i];
		 var id=type.id;//案件环节问卷记录表ID
		 var questionId=type.questionId;//题目的ID
		 var title=type.questionContent;//题目内容
		 var inputType=type.questionTypeCode;//题目类型
		 var questionItem=type.questionItem;//题目选项处理规则
		 var value=type.questionValue;//默认值
		
		switch (inputType) {
			case 'YWDM009802'://单选
				   var htmlInput='<div class="col-xs-12 qs_item" id="'+id+'" questionTypeCode="'+inputType+'">';
		 		   htmlInput+=' <h4>'+(i+1)+'、'+title+'</h4>';
		 		   htmlInput+=' <span class="detail" id="'+questionId+'">';
		 		   htmlInput+=' <div class="clickLable">';
		 		   var tempValues=value.split(",");
		 		   //循环复选值
		 		   if(questionItem&&questionItem!=''){
		 				questionItem=questionItem.split(',');
		 				for(var j=0;j<questionItem.length;j++){
							 var tempValue= questionItem[j].split(':')[0];//获取对应的值
							 var tempCode= questionItem[j].split(':')[1];//获取对应的值
		 					 htmlInput+=' <label class="radio radio-inline">';
		 					 htmlInput+='  <input type="radio" name="radio_'+questionId+'"';
		 					 //通过查找该值是否在数组中,存在表示选中
		 					 if($.inArray(tempValue,tempValues)>-1){
		 					 	htmlInput+=' checked="checked"';
		 					 }
		 					 //判断是否有二级联动
		 					 htmlInput+=' typeCode="'+tempCode+'"';
		 					 htmlInput+=' value="'+tempValue+'" >';
		 					 htmlInput+=' <i class="glyphicon glyphicon-record" aria-hidden="true"></i>'+tempValue+'</label>';
		 				}
		 			}
		 		   htmlInput+='</span> </div> </div>';
		 		   Obj.append(htmlInput);
		           break;
	        case 'YWDM009803'://复选
		       	   var htmlInput='<div class="col-xs-12 qs_item" id="'+id+'" questionTypeCode="'+inputType+'">';
		   		   htmlInput+=' <h4>'+(i+1)+'、'+title+'</h4>';
		   		   htmlInput+=' <span class="detail" id="'+questionId+'">';
		   			htmlInput+=' <div class="clickLable">';
		   		   var tempValues=value.split(",");
		   		   //循环复选值
		   		   if(questionItem&&questionItem!=''){
		   				questionItem=questionItem.split(',');
		   				for(var j=0;j<questionItem.length;j++){
							 var tempValue= questionItem[j].split(':')[0];//获取对应的值
		   					 htmlInput+=' <label class="checkbox checkbox-inline">';
		   					 htmlInput+=' <input type="checkbox" name="checkbox_'+questionId+'"';
		   					 //通过查找该值是否在数组中,存在表示选中
		   					 if($.inArray(tempValue,tempValues)>-1){
		   					 	htmlInput+=' checked="checked"';
		   					 }
		   					 htmlInput+=' value="'+tempValue+'" >';
		   					 htmlInput+=' <i  class="glyphicon glyphicon-check" aria-hidden="true"></i>'+tempValue+'</label>';
		   				}
		   			}
		   		   htmlInput+='</span> </div> </div>';
		   		   Obj.append(htmlInput);
		           break;
	        case 'YWDM009801'://输入框
		           var htmlInput='<div class="col-xs-12 qs_item" id="'+id+'" questionTypeCode="'+inputType+'">';
		   		   htmlInput+=' <h4>'+(i+1)+'、'+title+'</h4>';
		   		   htmlInput+=' <span class="detail" id="'+questionId+'">';
		   		   htmlInput+='<input type="text" class="form-control" placeholder="Text input" name="text2" value="'+value+'">';
		   		   htmlInput+='</span> </div>';
		   		   Obj.append(htmlInput);
		           break;
	        case 'YWDM009804':
		           var htmlInput='<div class="col-xs-12 qs_item" id="'+id+'" questionTypeCode="'+inputType+'">';
		   		   htmlInput+=' <h4>'+(i+1)+'、'+title+'</h4>';
		   		   htmlInput+=' <span class="detail" id="'+questionId+'" >';
		   		   htmlInput+=' <div class="group-file">';
		   		   htmlInput+=' <div id="upload_'+questionId+'"  name="file_upload"></div>';
		   		   htmlInput+=' <span class="upload-info" id="upload-info_'+questionId+'">注：请上传附件</span>';
		   		   htmlInput+=' <input type="hidden" id="attachmentId_'+questionId+'" name="attachmentId"/>';
		   		   htmlInput+=' </div></div> </div>';
		   		   Obj.append(htmlInput);
		           break;
    	}
		//判断是否有二级级联数据
		if(type.secondLevelTypeCode!=""){
			var lastObj=Obj.find(".clickLable:eq("+i+")");//获取点击对象大元素
			secondLevel(lastObj,type.secondLevelTypeCode,type.secondLevelValue);
		}
	}

	/**
	*点击单选按钮事件
	*/
	$(":radio").on("click",function(){
		//判断是否有二级级联
		var hasSecObj=$(this).attr('typeCode');
		if(typeof hasSecObj!="undefined"){
			var lastObj=$(this).parents(".clickLable");//获取最后一个子元素
			secondLevel(lastObj,hasSecObj);
		}
	});
	
	/**
	 *处理二级级联
	 */
	function secondLevel(Obj,inputType,value){
		value=value?value:"";
		switch (inputType) {
			case 'none':
			   Obj.next().remove();
               break;
            case 'input':
               Obj.next().remove();
               Obj.parents(".detail").append('<input secondLevelTypeCode="'+inputType+'" type="text" class="form-control" placeholder="Text input" name="text2" value="'+value+'">');
               break;
            case 'file':
               Obj.next().remove();
               var _id= Obj.parent().attr("id");
               var fileHtml="";
               fileHtml+=' <div class="group-file">';
               fileHtml+=' <div id="upload_'+_id+'"  name="file_upload_dy"></div>';
               fileHtml+=' <span class="upload-info" id="upload-info_'+_id+'">注：请上传附件</span>';
               fileHtml+=' <input secondLevelTypeCode="'+inputType+'" type="hidden" id="attachmentId_'+_id+'" name="attachmentId"/>';
               fileHtml+=' </div>';
               
               Obj.parents(".detail").append(fileHtml);
               initDynamicUpload('upload_'+_id);
               break;
        }
	}
	
	/**
	 * 普通附件上传方法
	 *@param multi 上传类型,true 多个上传
	 */
	 window.initCommonUpload();
	
	/**
	 *保存
	 */
	window.saveQuestionnaire=function(){
		var validFlg =true;
		var Obj=$("#questionnaire");
		var data={};
			data.caseApplyId=Obj.attr("caseapplyid");
			data.linktypecode=Obj.attr("linktypecode");
			data.questionList=[];
			Obj.children().each(function(){
				var listObj={};
				listObj.id=$(this).attr('id');
				var questionTypeCode=$(this).attr('questionTypeCode');
				listObj.questionTypeCode=questionTypeCode;
				listObj.questionId=$(this).find('.detail').attr('id');
				var title=$(this).find('h4').text();
				listObj.questionContent=title;
				//获取value值
				if(questionTypeCode=='YWDM009802'||questionTypeCode=='YWDM009803'){//单选、复选
					var tempObjs = $(this).find(".detail>.clickLable>label>input:checked");
					var str="";	
					$.each(tempObjs,function() {
						if(str!="") { 
							str += ","+$(this).val(); 
						} else{
							str = $(this).val();
						}
					}); 
					if(str==""){
						window.showPrompt("请选择"+title+"信息！");
						validFlg=false;
						return false;
					}	
					listObj.questionValue=str;
					//获取二级联动数据
					var secondLevelTypeCode = $(this).find("input:last").attr("secondLevelTypeCode");
					if(typeof secondLevelTypeCode!="undefined"){
						var typeCodeTemp=secondLevelTypeCode;
						listObj.secondLevelTypeCode=typeCodeTemp;
						var lastValue =$(this).find("input:last").val();
						if(typeCodeTemp=="file"&&lastValue==""){
							window.showPrompt("请上传"+title+"信息！");
							validFlg=false;
							return false;
						}else if(typeCodeTemp=="input"&&lastValue==""){
							window.showPrompt("请填写"+title+"信息！");
							validFlg=false;
							return false;
						}
						listObj.secondLevelValue=lastValue;
					}
				}else if(questionTypeCode=='YWDM009801'){//输入框
					var lastValue=$(this).find('.detail>input').val();
					if(lastValue==""){
						window.showPrompt("请填写"+title+"信息！");
						validFlg=false;
						return false;
					}
					listObj.questionValue=lastValue;
					listObj.secondLevelTypeCode="none";
					listObj.secondLevelValue="";
				}else if(questionTypeCode=='YWDM009804'){//上传
					var lastValue=$(this).find('.detail>.group-file>input').val();
					if(lastValue==""){
						window.showPrompt("请上传"+title+"信息！");
						validFlg=false;
						return false;
					}
					listObj.questionValue=lastValue;
					listObj.secondLevelTypeCode="none";
					listObj.secondLevelValue="";
				}
				validFlg=true;
				data.questionList.push(listObj);
			});
			return {"validFlg":validFlg,"caseApplyId":"${caseApplyId}","sceneTypeCode":"${sceneTypeCode}","questionList":data.questionList,"isView":true};
		}
});

/**
 * 普通附件上传方法
 *@param multi 上传类型
 */
 var upload_url = '<z:ukey key="public.ess.upload"   context="admin"/>';
function initDynamicUpload(id) {
	$("#"+id).Huploadify({
		auto:true,
		fileTypeExts:'*.jpg;*.png;',
		multi:true,
		fileSizeLimit:999999,
		removeTimeout:500,
		uploader:upload_url,
		onUploadSuccess:function(originalFile,responseText,objFileId){
			var objFile=JSON.parse(responseText)[0];
			if(typeof objFile!="undefined"){
				uploadFileCallback(objFile,objFileId);
			}
		},
		onCancel:function(file,objFileId){
			deleteFile(file,objFileId);
		}
	});
}

/**
 * 普通附件上传方法
 *@param multi 上传类型
 */
function initCommonUpload() {
	
	$('div[name="file_upload"]').Huploadify({
		auto:true,
		fileTypeExts:'*.jpg;*.png;',
		multi:true,
		fileSizeLimit:999999,
		removeTimeout:500,
		uploader:upload_url,
		onUploadSuccess:function(originalFile,responseText,objFileId){
			var objFile=JSON.parse(responseText)[0];
			if(typeof objFile!="undefined"){
				uploadFileCallback(objFile,objFileId);
			}
		},
		onCancel:function(file,objFileId){
			deleteFile(file,objFileId);
		}
	});
}
 /**
 *删除附件公共方法
 *@param file, 文件对象
 *@param objFileId 对象id
 */
function deleteFile(file,objFileId){
	var objIds=objFileId.split("_");
	var objId=objIds[objIds.length-1];
	var fileIds = $("#attachmentId_"+objId).val();
	if(fileIds!=""){
		var fileArr=fileIds.split(",");
		if(fileArr.length==1){//删除所有数据
			$("#attachmentId_"+objId).val("");
			$("#upload-info_"+objId).show();
		}else{
			//查找对应的id，移除id
			var fileId=$("#"+file.id).find(".delfilebtn").attr("fileid");
			var arr_index = $.inArray(fileId,fileArr);
			fileArr.splice(arr_index,1);
			$("#attachmentId_"+objId).val(fileArr.toString());
		}
		
	}
}
/**
 *普通上传回调函数回写id
 */
function uploadFileCallback(objFile,objFileId){
	var objIds=objFileId.split("_");
	var objId=objIds[objIds.length-1];
	if(objFile.success){
		var attachements = objFile.attachements;
		var attachementsId = attachements.id;
		var cooperationAttachment = $("#attachmentId_"+objId).val() + "," + attachementsId;
		if(cooperationAttachment.indexOf(",") == 0){
			cooperationAttachment = cooperationAttachment.substring(1,cooperationAttachment.length);
		}
		$("#attachmentId_"+objId).val(cooperationAttachment);
		//隐藏
		if(cooperationAttachment!=""){
			$("#upload-info_"+objId).hide();
		}
	}
}
</script>
</body>

</html>