<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>

<%@ include file='../common/common_js.jsp'%>
<%@ include file="../common/common_upload.jsp"%>
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
						<input class="zui-combobox zui-validatebox" id="capitalistType" name="capitalistType" type="hidden" value="${infoVo.capitalistType }"
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
	                            validate-type="Require">
	                           <i class="require icon-mrequire"></i>
	                           </label>
					</dd>
	            </dl>
	               <br> 
	            <dl class="form-item">
	                <dt class="title"><b class="c-red mr5">*</b>合同名称:</dt>
	                <dd class="detail">
	                    <label>
	                    	<input class="zui-input zui-validatebox" name="contractName" alidate-type="Require"   id="contractName" value="${infoVo.contractName }" >
	                    </label>
	                </dd>
	            </dl>
	            <dl class="form-item">
	                <dt class="title"><b class="c-red mr5">*</b>合同类型:</dt>
	                <dd class="detail">
	                <label>
	                <input class="zui-combobox zui-validatebox" id="contractType" name="contractType|E|S" type="hidden" value="${infoVo.contractType }"
                              data-url="<z:res resource="public.simplecode.selector" isDefault="true"/>&jsoncallback=?&target=true&categoryCd=YWDM0066"
                              data-valuefield="fullcode" 
                              data-textfield="name" validate-type="Require">
	                           </label>
					</dd>
	            </dl>
	            <br>
            
	            <dl class="form-item">
							<dt class="title"><b class="c-red mr5">*</b>是否停用:</dt>
							<dd class="detail">
									<input class="zui-checkbox zui-validatebox" name="contractTplState" id="contractTplState" data-multiple="false" 
									data-data="[{'id':'0','text':'是'},{'id':'1','text':'否'}]" data-valuefield="id" 
									data-textfield="text" validate-type="Require" style="display: none;" type="hidden">
							</dd>
						</dl>
						
						<dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>文件名</dt>
                <dd class="detail">
                	<label>
                		<input type="text" class="zui-input zui-validatebox" id="form_name"    validate-type="Require,Length[1-25]" validate-false="文件名不能为空"/>
					</label>
                </dd>
            </dl>
            <br>
            
	            <div>
	            <dl class="form-item" style="width:550px;">
	                <dt class="title"><b class="c-red mr5">*</b>文件上传</dt>
	                <dd class="detail">
	                    <label>
	                    	<input type="text" class="zui-input zui-validatebox" style="float:left;width:300px;" id="form_fileName"  readonly="true"   validate-type="Require" validate-false="请选择文件"/>
	                    	<input type="text" id="form_fileId" style="display:none;">
	                       	<input id="fileUpload" name="form_path" type="file" value="添加附件" validate-type="Require" validate-false="文件名不能为空"/>
	                    </label>
	                </dd>
						</dl>
						</div>
						
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
	  
	  
<script type="text/javascript">
seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/jquery.zds.combobox', 'zd/jquery.zds.loading', 'zd/switch','zd/jquery.zds.dialog', 'zd/jquery.zds.message', 'zd/jquery.zds.form', 'datepicker','zd/jquery.zds.table', 'zd/jquery.zds.seleter','zd/jquery.zds.combotree','zd/jquery.zds.checkbox']
, function ($,CALLBACK, COMBOBOX,  Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
	
    	var type = "${operationType}";
    	if(type == 'view'){
/*     		$('#capitalId').attr("disabled","disabled");
    		$('#contractTplType').attr("disabled","disabled");
    		$('#contractName').attr("disabled","disabled");
    		$('#contractType').attr("disabled","disabled");
    		$('#contractTplState').attr("disabled","disabled"); */
    		$('#remark').attr("disabled","disabled");
    	}
    	
		CALLBACK.onselect = function(value,text,index){
			 $('#capitalId').ZCombobox({
					    url:'<z:ukey key="com.zdsoft.finance.contract.getCapitalistCategory" context="admin"/>&capitalistType='+value,
					    valueField:'id',
					    textField:'value'
					}); 
			
		};
    	
    	
    	$("#capitalistDialogDiv").Zdialog({
            width: 700, height: 500, closed: false, title: "添加标准合同",isDestroy:true,
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
                                url: '<z:ukey key="com.zdsoft.finance.contract.standardContract.save" context="admin"/>',
                                data: contactForm,
                                dataType: 'json',
                                success: function (data) {
                                    if (data.resultStatus == 0) {
                                    	$.ZMessage.success("提示", "保存成功", function () {
                                    		$("#capitalistDialogDiv").Zdialog("close");
                                    		//刷新列表数据
                            				$('#tb-plan').ZTable("reload", {});
                    	                });
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
                },
                {
                    id: 'message-btn',
                    text: '取消',
                    buttonCls: 'btn-gray',
                    handler: function () {
                    	$("#capitalistDialogDiv").Zdialog("close");
                    }
                }
            ]
        });
    	
    	//初始化
        $.ZUI.init("#InfoDialog");
    	
        initUpload();
        
    });
function initUpload() {
 	// 上传相关js
	var upload_url = '<z:ukey key="public.ess.upload" context="admin"/>';
    $('#fileUpload').uploadify({
		'multi': false,
		'swf'      : '<%=resServerUpload %>/assets/js/zd/uploadify.swf',
		'uploader' : upload_url,
		'buttonText':'上传附件',
		'width':'80',
		'debug':false,
		'uploadLimit':'3',
		'onUploadSuccess' : function(file, data, response) {
			var fileName=file.name;
			fileName=fileName.substring(0,fileName.lastIndexOf("."));
			$("#form_name").val(fileName);
			$("#form_fileId").val(file.id);
			$("#form_fileName").val(file.name);
		}
	});
} 
    
</script>
</body>
</html>