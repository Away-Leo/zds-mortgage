<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>

<%@ include file="../common/common_upload.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
    .uploadify{ float:left;}
</style>
<div id="capitalistDialogDiv">
	<div id="InfoDialog" >
	    <form id="contactForm" class="zui-form mt15" >
	    	<input type="hidden" name="id" value="${vo.id }" >
	    	<dl class="form-item">
	            <dt class="title"><b class="c-red mr5">*</b>资方类型:</dt>
	            <dd class="detail">
					<input class="zui-combobox zui-validatebox" id="capitalistType" name="capitalistType" type="hidden" value="${vo.capitalistType }"
	               		data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM00112"
	               		data-valuefield="fullcode" 
	               		data-textfield="name" validate-type="Require"  data-callback="onselect">
				</dd>
	        </dl>
	        <dl class="form-item">
	            <dt class="title"><b class="c-red mr5">*</b>资方:</dt>
	            <dd class="detail">
					<input class="zui-combobox zui-validatebox" id="capitalId" name="capitalId" type="hidden" value="${vo.capitalId }"
						data-url="<z:ukey key="com.zdsoft.finance.contract.getCapitalistCategory" context="admin"/>&capitalistType=${vo.capitalistType}"
	                	data-valuefield="fullcode" 
	                   	data-textfield="name" validate-type="Require">
				</dd>
	        </dl>
	        <dl class="form-item">
	            <dt class="title"><b class="c-red mr5">*</b>合同名称:</dt>
	            <dd class="detail">
	                <label>
	                	<input class="zui-input zui-validatebox" id="recontractName" name="contractName" validate-type="Require,Length[1-64]" value="${vo.contractName }" >
	                </label>
	            </dd>
	        </dl>
	        <dl class="form-item">
	            <dt class="title"><b class="c-red mr5">*</b>合同类型:</dt>
	            <dd class="detail">
	            	<input class="zui-combobox zui-validatebox" id="recontractType" name="contractType" type="hidden" value="${vo.contractType }"
	               		data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM0066"
	                       data-valuefield="fullcode" 
	                       data-textfield="name" validate-type="Require">
				</dd>
	        </dl>
	       	<dl class="form-item block">
	        	<input type="hidden" name="attachmentId"  id="attachmentId" value="${vo.attachmentId}" />
	        	<dt class="title"><b class="c-red mr5">*</b>附件:</dt>
				<dd class="detail">
					<input id="contract_file_upload" name="contract_file_upload" type="file"/>
					<div>
						<span id="importProcessUploadStatus" class="f12">
						<c:if test="${vo.attachmentId eq null || infoVo.attachmentId == ''}">
							<span id="attrRemark">&nbsp;&nbsp;备注：未上传附件</span>
						</c:if>
						<c:if test="${!(vo.attachmentId eq null || vo.attachmentId == '')}">
							<c:forEach items="${attrs}" var="attr">
								<span>&nbsp;&nbsp;asdf${attr.fileLabel} &nbsp;
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
					<input class="zui-checkbox zui-validatebox " id="contractTplState" name="contractTplState" data-multiple="false" value="${vo.contractTplState }"
					data-data="[{'id':'Enable','text':'启用'},{'id':'Disable','text':'禁用'}]"
					type="hidden" data-textfield="text" validate-type="Require" data-valuefield="id"/>
				</dd>
			</dl>
	        <br>
	        <dl class="form-item">
				<dt class="title">备注:</dt>
				<dd class="detail">
					<textarea class="zui-area zui-validatebox" validate-type="Length[0-250]" id="remark" name="remark" placeholder="最多可以输入250个字符"  validate-type="Require" >${vo.remark}</textarea>
				</dd>
			</dl>
	     </form>
		</div>
	</div>
</div>
	  
<script type="text/javascript">
seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 
           'zd/switch','zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 
           'datepicker','zd/jquery.zds.table', 'zd/jquery.zds.seleter','zd/jquery.zds.combotree',
           'zd/jquery.zds.checkbox']
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
        width: 700, height: 300,  title: xsTitle,isDestroy:true,isDrag: true,
        buttons: 
        [
            {
                id: 'capitalistDialogDiv-message-btn',
                text: '确定',
                buttonCls: 'btn-blue',
                handler: function () {
                	var creditId = $('#attachmentId').val();

                	var isValidate = $.ZUI.validateForm($('#contactForm'));
    				if(isValidate){
    					if(creditId ==null||creditId == ""){
                    		$.ZMessage.warning("警告", "请上传附件!", function () {});
            				return;
                    	}
                		var contactForm = $('#contactForm').serialize();
                        $.ajax({
                            type: 'post',
                            url: '<z:ukey key="com.zdsoft.finance.contract.saveStandardContract" context="admin"/>',
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
	$("#capitalistDialogDiv").Zdialog("open");
	//删除附件
	window.attrDel = function(_this,_id){
        $.ZMessage.confirm("确认", "您确认要删除吗", function (r) {
            if (r == 'continue') {
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
            }
        }, {
            'continue': {id: 'continue', text: '继续', buttonCls: 'btn-blue'},
            'cancel': {id: 'cancel', text: '取消', buttonCls: 'btn-gray'}
        });
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
	
    	if(type == 'view'){
    		$("#contract_file_upload").css("display","none");
    		$('#capitalistType').data("choose","disable");
    		$('#capitalId').data("choose","disable");
    		$('#recontractType').data("choose","disable");
    		$('#contractTplState').data("choose","disable");

    		document.getElementById("attrDel").onclick=function(){return false;};
    		
    		document.getElementById("attrDel").innerHTML="";
    		
    		$("#SWFUpload_0").hide();
    		$("#SWFUpload_0").innerHTML="";
    		
    		$('#recontractName').attr("disabled","disabled");
    		$('#remark').attr("disabled","disabled");
    		$("#zds_btn_capitalistDialogDiv-message-btn").hide();
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
	/* $.ajax({
		type :'post',
		url : upload_url+"&jsoncallback=?",
		dataType : 'jsonp',
		success : function(result) {

		}
	}); */

	$('#contract_file_upload').uploadify({
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
			var html = "<span>&nbsp;&nbsp;"+fileLabel+"&nbsp;" +
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
