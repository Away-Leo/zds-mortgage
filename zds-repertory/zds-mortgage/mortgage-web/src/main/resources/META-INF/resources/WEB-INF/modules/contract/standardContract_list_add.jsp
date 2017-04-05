<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>

<%@ include file='../common/common_js.jsp'%>
<%@ include file="../common/common_upload.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
    .uploadify{ float:left;}
</style>
</head>
<body>
<div id="capitalistDialogDiv">
	<div id="InfoDialog" >
	        <form id="contactForm" class="zui-form mt15" >
	        	<input type="hidden" name="id"   value="${infoVo.id }" >
	        	
	        	<dl class="form-item">
	                <dt class="title"><b class="c-red mr5">*</b>资方类型:</dt>
	                <dd class="detail">
	                <label>
						<input class="zui-combobox zui-validatebox" id="capitalistType" name="capitalistType" type="hidden" value="${capitalists.capitalistType }"
                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM0065"
                              data-valuefield="fullcode" 
                              data-textfield="name" validate-type="Require"  data-callback="onselect">
                              </label>
					</dd>
	            </dl>
	            <dl class="form-item">
	                <dt class="title"><b class="c-red mr5">*</b>资方:</dt>
	                <dd class="detail">
	                <label>
					<input class="zui-combobox zui-validatebox" id="capitalId" name="capitalId" type="hidden" value="${infoVo.capitalId }"
							data-url="<z:ukey key="com.zdsoft.finance.contract.getCapitalistCategory" context="admin"/>&capitalistType=${capitalists.capitalistType}"
	                          data-valuefield="fullcode" 
                              data-textfield="name" validate-type="Require">
	                           </label>
					</dd>
	            </dl>
	               <br> 
	            <dl class="form-item">
	                <dt class="title"><b class="c-red mr5">*</b>合同名称:</dt>
	                <dd class="detail">
	                    <label>
	                    	<input class="zui-input zui-validatebox" id="recontractName" name="contractName" validate-type="Require" value="${infoVo.contractName }" >
	                    </label>
	                </dd>
	            </dl>
	            <dl class="form-item">
	                <dt class="title"><b class="c-red mr5">*</b>合同类型:</dt>
	                <dd class="detail">
	                <label>
	                <input class="zui-combobox zui-validatebox" id="recontractType" name="contractType" type="hidden" value="${infoVo.contractType }"
                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM0066"
                              data-valuefield="fullcode" 
                              data-textfield="name" validate-type="Require">
	                           </label>
					</dd>
	            </dl>
	            <br>
	            
            <dl class="form-item block">
	            <input type="" name="attachmentId"  id="attachmentId" value="${infoVo.attachmentId}" />
	            <dt class="title"><b class="c-red mr5">*</b>附件:</dt>
					<dd class="detail">
						<input id="file_upload" name="file_upload" type="file"/>
						<div>
							<span id="importProcessUploadStatus" class="f12">
								<c:if test="${infoVo.attachmentId eq null || infoVo.attachmentId == ''}">
									<span id="attrRemark">备注：未上传附件</span>
								</c:if>
								<c:if test="${!(infoVo.attachmentId eq null || infoVo.attachmentId == '')}">
									<c:forEach items="${attrs}" var="attr">
										<span>${attr.fileLabel} &nbsp;
										<a id="attrDel" onclick="attrDel(this,'${attr.id}')" style="color:#4692f0;">删除</a>&nbsp;
										<a id="attrDown" onclick="attrDown('${attr.id}')" style="color:#4692f0;">下载</a>
										<br>
										</span>
									</c:forEach>
								</c:if>
							</span>
						</div>
					</dd>
				</dl>
				
	            <dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>状态:</dt>
							<dd class="detail">
								
									<input class="zui-checkbox zui-validatebox" name="contractTplState" id="contractTplState" type="hidden" data-multiple="false"  value="${infoVo.contractTplState }"
									data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=ContractTplType"
		                               data-valuefield="id" data-textfield="text" validate-type="Require">
		                    	
							</dd>
						</dl>
							
	            <br>
	            <dl class="form-item">
							<dt class="title">备注:</dt>
							<dd class="detail">
								<textarea class="zui-area zui-validatebox" validate-type="Length[0-3000]" id="remark" name="remark" placeholder="最多可以输入3000个字符"  validate-type="Require" >${infoVo.remark}</textarea>
							</dd>
						</dl>
	            
	        </form>
	        </div>
	    </div>
	  </div>
	  
	  
<script type="text/javascript">
seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch','zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker','zd/jquery.zds.table', 'zd/jquery.zds.seleter','zd/jquery.zds.combotree','zd/jquery.zds.checkbox']
, function ($,CALLBACK, COMBOBOX,  Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
	var type = "${operationType}";
	var xsTitle;
	if(type == 'view')
	{
		xsTitle="标准合同查看";
	}
	else if(type == 'add')
	{
		xsTitle="标准合同新增";
	}
	else
	{
		xsTitle="标准合同编辑";
	}
	
	$("#capitalistDialogDiv").Zdialog({
        width: 700, height: 300, closed: false, title: xsTitle,isDestroy:true,
        buttons: 
        [
            {
                id: 'message-btn',
                text: '确定',
                buttonCls: 'btn-blue',
                handler: function () {
                	var creditId = $('#attachmentId').val();
                	if(creditId ==null||creditId == ""){
                		$.ZMessage.warning("警告", "请上传附件!", function () {});
        				return;
                	}
                	
                	var isValidate = $.ZUI.validateForm($('#contactForm'));
    				if(isValidate){
                	var contactForm = $('#contactForm').serialize();
                        $.ajax({
                            type: 'post',
                            url: '<z:ukey key="com.zdsoft.finance.contract.standardContract.save" context="admin"/>',
                            data: contactForm,
                            dataType: 'json',
                            success: function (data) {
                                if (data.resultStatus == 0) {
                                	$.ZMessage.success("提示", "保存成功", function () {
                	                    $(".zd-message").ZWindow("close");
                	                });
                                	$('#tb-plan').ZTable("reload", {});
                                	$("#capitalistDialogDiv").Zdialog("close");
                                }
                            },
                            error: function () {
                            	$.ZMessage.error("错误", "系统异常，请联系管理员", function () {
                                    $(".zd-message").ZWindow("close");
                                });
                            	$("#capitalistDialogDiv").Zdialog("close");
                            }
                        });
                }
                }
            },
            {
                id: 'message-btn',
                text: '关闭',
                buttonCls: 'btn-gray',
                handler: function () {
                	$("#capitalistDialogDiv").Zdialog("close");
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
	
    	//var type = "${operationType}";
    	if(type == 'view'){
    		$('#capitalistType').data("choose","disable");
    		$('#capitalId').data("choose","disable");
    		$('#recontractType').data("choose","disable");
    		$('#contractTplState').data("choose","disable");

    		document.getElementById("attrDel").onclick=function(){return false;};
    		document.getElementById("attrDown").onclick=function(){return false;};
    		//document.getElementById("file_upload").disable=false;
    		
    		document.getElementById("attrDel").innerHTML="";
    		document.getElementById("attrDown").innerHTML="";
    		
    		$("#SWFUpload_0").hide();
    		$("#SWFUpload_0").innerHTML="";
    		
    		$('#recontractName').attr("disabled","disabled");
    		$('#remark').attr("disabled","disabled");
    		$("#zds_btn_message-btn").hide();
    	}
    	
    	
		CALLBACK.onselect = function(value,text,index){
			 $('#capitalId').ZCombobox({
					    url:'<z:ukey key="com.zdsoft.finance.contract.getCapitalistCategory" context="admin"/>&capitalistType='+value,
					    valueField:'fullcode',
					    textField:'name'
					}); 
			
		};
    	
    	//初始化
        $.ZUI.init("#InfoDialog");
        
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
</body>
</html>