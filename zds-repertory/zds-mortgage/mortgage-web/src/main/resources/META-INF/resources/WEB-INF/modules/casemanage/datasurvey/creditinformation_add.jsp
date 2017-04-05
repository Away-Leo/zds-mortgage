<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<%@ include file="../../common/common_upload.jsp" %>
<c:if test="${not empty errorMsg }">${errorMsg }</c:if>
<c:if test="${empty errorMsg }">
	<!-- 上传弹窗 -->
	<div  id="uploadCreditDialog" >
		<div id="uploadCreditDiv">
		    <input class="zui-input zui-disabled" readonly value="${uploadTitleName }" style="width:100%; text-align:center;"/>
		    <div style="height: 10px;"></div>
		    
		    <div class="page-box" id="attachmentInfo">
		        <div class="page-title">附件信息</div>
		        <div class="p5">
		            <input id="credit_fileUpload" name="credit_fileUpload" type="file" value="添加附件" validate-type="Require" validate-false="文件名不能为空"/>
		            <div id="queue-item"></div>
		            <div style="height: 10px;"></div>
		            <form id="fileUploadListForm" class="zui-form">
		            	<div id="fileStore-credit-table"></div>
		            </form>
		        </div>
		    </div>
		</div>
	</div>
</c:if>	
<script>
	seajs.use([ 'jquery', 'zd/jquery.zds.page.callback','zd/jquery.zds.loading','zd/jquery.zds.combobox', 'zd/jquery.zds.dialog','zd/jquery.zds.button',
		'zd/jquery.zds.message','zd/jquery.zds.table', 'zd/jquery.zds.form','zd/jquery.zds.message' ], function($, CALLBACK, Loading) {
		
		//上传附件弹窗
        $("#uploadCreditDialog").Zdialog({
    		width: 1000, 
    		height: 500, 
    		closed: true, 
    		title:	'上传',
    		isDestroy:true,
    		buttons: [{
    			id: 'uploadCredit_save_btn',
    			text: '保存', 
    			buttonCls: 'btn-blue',
    			handler: function () {
    				var isValidate = $.ZUI.validateForm($('#fileUploadListForm'));
                    if(!isValidate){
                    	$.ZMessage.error("错误", "请完善文件类别信息！", function () { });
                        return false;
                    }
    				var params = "&productId=${productId}&sourceCode=${sourceCode}";
   		            var upUrl = '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.creditinfomation.saveCreditList" context="admin"/>&jsoncallback=?'+params;
   		         	var attachmentTable = $('#fileStore-credit-table').ZTable("getRows");
   		            var attachmentList = JSON.stringify(attachmentTable);
   		         	var load=Loading.show(null,true,88888);
   		            $.ajax({
   		            	url: upUrl,
   		                data: {'attachmentList':attachmentList,'creditVoJson':'${creditVoJson}'},
   		                type: 'post',
   		                success: function (data) {
   		                	Loading.hide(load);
   		                    if (data.resultStatus == 0) {
   		                        $.ZMessage.info("提示", "上传成功！", function () {
   		                        	$('#uploadCreditDialog').Zdialog('close');
   		                        	$('#creditInfoList').ZTable("reload");
   		                        });
   		                    } else {
   		                        $.ZMessage.error("错误", "错误!" + data.msg, function () {
   		                        });
   		                    }
   		                },
   		                error: function (data) {
   		                	Loading.hide(load);
   		                    var errorMsg = JSON.parse(data.responseText);
   		                    $.ZMessage.error("错误", "错误!" + errorMsg.msg, function () {
   		                    });
   		                }
   		            });
    		    	
    			}
    		},{
    			id: 'uploadCredit_cancel_btn',
    			text: '取消',
    			buttonCls: 'btn-gray',
    			handler: function () {
    				$('#uploadCreditDialog').Zdialog('close');
    			}
    		}],
    		onOpen: function() {
    			//打开前初始化
    		},
    		onClose: function() {
    			//关闭时执行
    		}
    	});
        $("#uploadCreditDialog").Zdialog("open");
        //征信附件上传列列表
        $('#fileStore-credit-table').ZTable({
  	       url : '<z:ukey key="com.zdsoft.finance.casemanage.datasurvey.creditinfomation.fileUploadTempData" context="admin"/>&jsoncallback=?',
  	       queryParams:{'businessId':'${businessId}', 'creditId':'${creditId}'},
  	       idField: "attachmentId",
  	       singleSelect : true,
  	       isRowNum : true,
  	       pagination : false,
  	       tableCls : 'table-index',//table的样式
  	       onEdit:true,
  	       columns:[[
  	    	   	{field : 'attachmentId',		title : '附件id',			align : 'center',hidden:true},
  	    	   	{field : 'fileName',			title : '文件名',			align : 'center'},
  				{field : 'materiaCodeName',		title : '类别名称',		align : 'center',required:true},
  				{field : 'id',					title : '操作', 			align : 'center',formatter:function(r,v){
                     var html = '<button type="button" onclick="doDelete(this)" class="btn-blue">删除</button>';
             		return html;
  				}}
 	 		] ],
 	 		columnsType: [
 	 			{ 
 	 				"materiaCodeName": {
	                    "inputType": "combobox",
	                    "data": {
	                        "valueField": "fullcode",
	                        "textField": "name",
	                        "data": "[{'fullcode': 'YWDM008503', 'name': '征信授权书'}, {'fullcode': 'YWDM008504', 'name': '征信报告'}, {'fullcode': 'YWDM008506','name': '征信身份证'}]"
	                    },
	                    "validateType": "Require"
                	}
 	 			},
                {
                    "inputWidth": 220,
                    "areaWidth": 220,
                    "areaHeight": 80
                }
 	 		],
 	 		onDelete:function(index, rowData) {
 	 			//  添加判断
				return true;
 	 		},
 	 		onLoadSuccess:function() {
 	 		}
  		});
        //点击删除附件
        window.doDelete = function (el) {
        	
	       	 $.ZMessage.question("确认", "确认删除？", function () {
	           	//模拟点击选中事件
	           	if($(el).parent().parent().find("td.selected").hasClass('selected')){
	           		$(el).parent().parent().find("[field=datagrid-header-check]").find('input').removeAttr('checked');
		           	$(el).parent().parent().find("td").removeClass('selected');
		           	$(el).parent().parent().click();
	           	}
				$(el).parent().parent().click();
				$('#fileStore-credit-table').ZTable("deleteRow");
	       	 });
	       	event.stopPropagation();
        }
        
        //附件上传成功操作
        window.fileStoreUpload = function (file, data, response) {
        	var allData = JSON.parse(data);
            var fileName = file.name;
            var attachmentId = allData[0].attachements.id;
          	//行数据
            var row = null;
            if(fileName.indexOf("+") == -1){//附件名称 没有规则 
	            row =  {
            		"attachmentId":attachmentId,
		   		 	"fileName": fileName
	            };
            }else {
            	var fileTypeName = fileName.substring(0, fileName.indexOf("+"));
            	var materiaCodeNameTemp = "";
            	if(fileTypeName == "借款人或买方征信"){
            		materiaCodeNameTemp = "YWDM008504";
            		
            	}else if(fileTypeName.indexOf("征信授权书") > 0 ){
           			materiaCodeNameTemp = "YWDM008503";
           			
               	}else if(fileTypeName.indexOf("征信身份证") > 0){
               		materiaCodeNameTemp = "YWDM008506";
           		}
            	
            	row = {
           			"attachmentId":attachmentId,	
           			"fileName": fileName,
           			"materiaCodeName":materiaCodeNameTemp
            	}
            }
       		$('#fileStore-credit-table').ZTable("addOneRow",row);
        }
     	//附件上传
		initUpload();
		
        $.ZUI.initForms("#uploadCreditDiv");
        
	});
  	//附件上传
	function initUpload(){
        var upload_url = '<z:ukey key="public.ess.upload" context="admin"/>';
        $('#credit_fileUpload').uploadify({
        	'fileTypeDesc':'只支持图片格式.',  
            'fileTypeExts':'*.bmp;*.jpg;*.gif;*.wmf;*.png',  
            'multi': true,
            'swf': '<%=resServerUpload %>/assets/js/zd/uploadify.swf',
            'uploader': upload_url,
            'buttonText': '上传',
            'width': '80',
            'height': '22',
            'queueID': 'queue-item',
            'debug': false,
            'uploadLimit': '10',
            'onUploadSuccess': function (file, data, response) {
            	fileStoreUpload(file, data, response);
            }
        });
	}
</script>