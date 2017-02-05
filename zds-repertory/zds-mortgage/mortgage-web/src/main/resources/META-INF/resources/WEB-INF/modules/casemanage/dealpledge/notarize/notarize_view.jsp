<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公证</title>

<%@ include file="../../../common/common_js.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <link rel="stylesheet" type="text/css" href="http://192.168.18.253:8080/dev/static/assets/css/style.css"> -->
</head>
<body>
<!-- 查看案件基本信息 -->
<div class ="frm-content">
	
	<div class="page-box">
        <h1 class="page-title">基本信息</h1>
        <div class="p5">
            <table class="table-detail">
                <tr>
                    <td class="td-title pct15">案件号</td>
                    <td class="pct15">${basicInfoVo.caseApplyCode}</td>
                    <td class="td-title pct15">接单日期</td>
                    <td class="pct15" id="singleDate"></td>
                    <td class="td-title pct15">子产品</td>
                    <td class="pct15">${basicInfoVo.productSubtypeName}</td>
                </tr>
                <tr>
                    <td class="td-title pct15">拓展经理</td>
                    <td class="pct15">${basicInfoVo.developmentManagerName}</td>
                    <td class="td-title pct15">拓展部门</td>
                    <td class="pct15">${basicInfoVo.developmentDepartmentName}</td>
                    <td class="td-title pct15">机构</td>
                    <td class="pct15">${basicInfoVo.mechanismName}</td>
                </tr>
                <tr>
                    <td class="td-title pct15">申请金额(元)</td>
                    <td class="pct15">${basicInfoVo.applyAmount}</td>
                    <td class="td-title pct15">贷款期限</td>
                    <td class="pct15">${basicInfoVo.applyDeadline}</td>
                    <td class="td-title pct15">还款方式</td>
                    <td class="pct15">${basicInfoVo.repayMethod}</td>
                </tr>
                <tr>
                    <td class="td-title pct15">贷款利率</td>
                    <td class="pct15">${basicInfoVo.applyRate}</td>
                    <td class="td-title pct15">逾期利率</td>
                    <td class="pct15">${basicInfoVo.overdueRate}</td>
                    <td class="td-title pct15">终端</td>
                    <td class="pct15">${cooperatorTerminalVo.terminalFullName }</td>
                </tr>
                <tr>
                    <td class="td-title pct15">资金来源</td>
                    <td class="pct15">${basicInfoVo.capitalSource}</td>
                    <td class="td-title pct15"></td>
                    <td class="pct15"></td>
                    <td class="td-title pct15"></td>
                    <td class="pct15"></td>
                </tr>
            </table>
        </div>
	</div>
<!-- 押品信息 -->
	<div class="page-box">
        <h1 class="page-title">押品信息</h1>
		<div class="p10">
               <div id="zds-table-house" class="zui-datagrid table-scroll" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.dealpledge.notarize.listHouse" context="admin"/>&caseApplyId=402892c459aa80600159aa8bb1d20019","singleSelect":true,"pagination":false,"idField":"id","tableCls":"table-index"}'>
	                <table>
	                 	<thead>
	                        <tr>
	                            <th data-options="field:communityName,align: center,width:200">小区名称</th>
	                            <th data-options="field:mailingAddress">押品地址</th>
	                        	<th data-options="field:floorAge">楼龄</th>
	                            <th data-options="field:area">面积</th>
	                            <th data-options="field:estateProperties">房产性质</th>
	                            <th data-options="field:estateOwnership">房产权属</th>
	                            <th data-options="field:isRenovation">是否有装修</th>
	                            <th data-options="field:synthesizePrice">综合评估价</th>
	                        </tr>
	                    </thead>
	               	</table>
           		</div>
		</div>
	</div>
	    <!-- 公证 -->	
	<div class="page-box">
        <h1 class="page-title">公证</h1>
		<div class="p10">
			<div id="zds-table-notarize" class="zui-datagrid table-scroll" zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.casemanage.dealpledge.notarize.listNotarize" context="admin"/>","singleSelect":true,"pagination":true,"idField":"id","tableCls":"table-index","toolbar":"#notarizeToolbar"}'>
				<table>
					<thead>
						<tr>
							<th data-options="field:notarizeTypeName,width:10%">公证类型</th>
							<th data-options="field:notarizeOffice,width:10%">公证机关</th>
							<th data-options="field:notarizeDate,width:15%" formatter="formatterDate">办理公证时间</th>
							<th data-options="field:notarizeProvideDate,width:15%" formatter="formatterDate" >预计公证书出具时间</th>
							<th data-options="field:mo,width:40%">备注</th>
							<th data-options="field:id,width:10%" formatter="operate">操作</th>
						</tr>
					</thead>
				</table>
			</div>
			<div id="notarizeToolbar">
			    <a class="zui-toolbar" id="btn-add" text="新增" iconCls="icon-add" buttonCls="btn-blue" handler="addNotarize"></a>
			</div>
		</div>
	</div>
</div>

<div id="addNotarizeDialog" class="page-box" style="display: none">
    <div class="p10">
        <form id="notarizeForm" class="zui-form" action="javascript:void(0);"}>
        	<input type="hidden" id="notarizeId" name="id"> <!-- 公证id -->
        	<input type="hidden" id="noCaseApplyId" name="caseApplyId" value=""> <!-- 案件号id -->
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>公证类型:</dt>
                <dd class="detail">
                    <label>
<!--                			<input class="zui-combobox zui-validatebox" type="hidden" id="notarizeType" name="notarizeType" value="" -->
<!-- 								data-toggle="combobox" -->
<%-- 								data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=notarizetype" --%>
<!-- 								data-valuefield="id" data-textfield="text" -->
<!-- 								validate-type="Require">  -->
                        <input class="zui-combobox zui-validatebox" validate-type="Require" id="notarizeType" type="hidden" name="notarizeType" value=""
				                          data-url="<z:res resource='public.simplecode.selector' isDefault='true'/>&jsoncallback=?&target=true&categoryCd=YWDM0048"
				                           data-valuefield="fullcode" data-textfield="name" >
                    </label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>公证机关:</dt>
                <dd class="detail">
                   	<label> 
                   		<input class="zui-input zui-orange zui-validatebox" type="text" id="notarizeOffice" name="notarizeOffice"  value="${notarizeVo.notarizeOffice}"
                   		validate-type="Require" />
					</label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>办理公证时间:</dt>
                <dd class="detail">
                    <label> 
						<input class="zui-date zui-validatebox strToDate" type="text" id="notarizeDate" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changeNotarizeDate',dateFmt:'yyyy-MM-dd', maxDate:'#F{$dp.$D(\'notarizeProvideDate\')}'})" validate-type="Require" readonly />
						<input type="hidden" id="changeNotarizeDate" name="notarizeDate" />
					</label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>预计公证书出具时间:</dt>
                <dd class="detail">
					<label> 
						<input class="zui-date zui-validatebox strToDate" type="text" id="notarizeProvideDate" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'changeNotarizeProvideDate',dateFmt:'yyyy-MM-dd', minDate:'#F{$dp.$D(\'notarizeDate\')}'})" validate-type="Require" readonly />
						<input type="hidden" id="changeNotarizeProvideDate" name="notarizeProvideDate" />
					</label>
                </dd>
            </dl>
            <dl class="form-item">
                <dt class="title"><b class="c-red mr5">*</b>备注：</dt>
                <dd class="detail">
					<label> 
						<textarea class="zui-area zui-validatebox" placeholder="最多可以输入500个字符" id="mo" name="mo" validate-type="Require,Length[0-500]"></textarea>
					</label>
                </dd>
            </dl>
        </form>
    </div>
</div>
<!-- <div class="save">
		<button id="btn-save" class="btn-blue mr10">保存</button>
		<button id="btn-submit" class="btn-blue mr10">提交</button>
</div> -->
<script type="text/javascript">
 	seajs.use([ 'jquery', 'zd/jquery.zds.page.callback', 'zd/jquery.zds.combobox', 'zd/jquery.zds.dialog','zd/jquery.zds.button','zd/jquery.zds.message', 'zd/jquery.zds.table',
 		'zd/jquery.zds.form' ,'zd/jquery.zds.message','zd/jquery.zds.validate'], function($, CALLBACK) {
		//格式化时间
		CALLBACK.formatterDate = function(row,value){
			return window.formatDate(row,value);
		};
		
		$('#btn-save').click(function() {
			$.ZMessage.success("成功", "这是成功提示", function() {
				alert('这里是回调函数');
			});
		});
		
		$('#btn-submit').click(function() {
			$.ZMessage.success("成功", "这是成功提示", function() {
				alert('这里是回调函数');
			});
		});
		
 		//公证弹窗 
		$("#addNotarizeDialog").Zdialog({
            width: 700, height: 340, closed: true, title: "新增/编辑",
            buttons: [
                {
                    id: 'message-btn',
                    text: '确定',
                    buttonCls: 'btn-blue',
                    handler: function () {
                    	saveNotarize();
                    }
                },
                {
                    id: 'message-btn',
                    text: '取消',
                    buttonCls: 'btn-gray',
                    handler: function () {
                        $("#addNotarizeDialog").Zdialog("close");
                    }
                }
               ]
                    	
        }); 
		 
		 //新增公证按钮
 		CALLBACK.addNotarize = function(){
			 $.ZUI.resetForms("#addNotarizeDialog");
			 $('#notarizeId').val('');
			 $('#noCaseApplyId').val('cxk');
			 
		  	 $('#addNotarizeDialog').Zdialog("open");
		};
		
		 //操作格式化
 		 CALLBACK.operate=function(rowData,index){
        	var data='<a href="javaScript:void(0)" onclick="editNotarize"><button class="btn-blue">编辑</button></a>&nbsp;&nbsp;'+
	        	'<a href="javaScript:void(0)" onclick="deleteNotarize"><button class="btn-blue">删除</button></a>';
        	return data;
        }; 
		
      //编辑公证格式化
        CALLBACK.editNotarize=function(index,row){	
    	  	$('#noCaseApplyId').val(row.caseApplyId);
        	$("#notarizeId").val(row.id);
			$("#notarizeType").ZCombobox('setValue',row.notarizeType);
			$("#notarizeOffice").val(row.notarizeOffice);
			$("#notarizeDate").val(row.notarizeDate);
			$("#notarizeProvideDate").val(row.notarizeProvideDate);
			$("#changeNotarizeDate").val(row.notarizeDate);
			$("#changeNotarizeProvideDate").val(row.notarizeProvideDate);
			$("#mo").val(row.mo);
			$.ZUI.strToDate();
    	  	$('#addNotarizeDialog').Zdialog("open");
    	  
         };
        
    	//删除公证格式化
         CALLBACK.deleteNotarize=function(index,row){
             $.ZMessage.confirm("确认删除", "请确认是否删除此公证记录", function (r) {
                
            	 if (r == 'confirm') {
                     $.ajax({
                         type: 'post',
                         url: '<z:ukey key="com.zdsoft.finance.casemanage.dealpledge.notarize.deleteNotarize" context="admin"/>',
                         data: {id : row.id},
                         dataType: 'json',
                         success: function (data) {
                         	if (data.resultStatus == 0) {
                         		
                         		$.ZMessage.info("提示", data.msg, function () {});
                         	}
                              
                       		else{
                   				$.ZMessage.error("错误", data.msg, function () {});
                   			}
	                    		$('#zds-table-notarize').ZTable("reload");
                         	}
                         });
                 }
                 if (r == 'cancel') {
                 }
             }, {
                'confirm': {id: 'confirm', text: '确定', buttonCls: 'btn-blue'},
            	'cancel':  {id: 'cancel', text: '取消', buttonCls: 'btn-gray'}
             });
    	};
 	
		 
        $.ZUI.init(); 
        //保存公证
		var saveNotarize = function(){
			var validation = $.ZUI.validateForm($('#addNotarizeDialog'));
// 			$("#notarizeForm").ZDSValidatebox('validateAll',$("#notarizeForm"));
//			var validation2 = $('#remark').ZDSValidatebox();
//			$.ZUI.initForms("#notarizeForm");
			if(!validation){
				return false;
			}
			var param = $('#notarizeForm').serializeArray();
			
			$.ajax({
                type: 'post',
                url: '<z:ukey key="com.zdsoft.finance.casemanage.dealpledge.notarize.saveOrUpdateNotarize" context="admin"/>',
                data: param,
                dataType: 'json',
                success: function (data) {
                	if (data.resultStatus == 0) {
                		$.ZMessage.success("提示", "保存成功", function () {
                			$("#addNotarizeDialog").Zdialog("close");
                     		$("#zds-table-notarize").ZTable("reload",{});
                       	 });
                	}
                	else{
                       	$.ZMessage.error("错误", data.msg, function () {})
                	};
		                   
               	},
                error: function () {
                  	$.ZMessage.error("错误", "保存公证系统异常，请联系管理员", function () {
                    });
                }
                
                
            });
		
 	};
//   	ZDS_WORKFLOW_CLIENT.saveFunction = function (datas) {
// 		var WORKFLOW_FLAG=ZDS_WORKFLOW_PARAM._STATUS_VALIDATE_ERROR;//1、提交，需要，默认提交失败！
// 		//---------start------流程中有修改页面，需要提交业务数据操作------------------
// 		//流程参数
// 		var args = JSON.parse(datas);
// 		var params = $('#addForm').serialize();
//         params += '&processInstanceId=' + args.processInstanceId;
//         params += '&taskInstanceId=' + args.taskInstanceId;
//         params += '&taskId=' + args.taskId;
//         params += '&taskName=' + args.taskName;
//         params += '&jobAppCd=' + args.jobAppCd;
// 		$.ajax({
// 			url:'<z:ukey key="com.zdsoft.finance.prCostitemApply.editJobSave" context="admin"/>',
// 			data:params,
// 			type:"post",
// 			async: false,
// 			dataType:"json",
// 			traditional:true,
// 			success:function(rdata){
// 				if(rdata.resultStatus == 0){
// 					WORKFLOW_FLAG=ZDS_WORKFLOW_PARAM._STATUS_SUCCESS;
// 				}else{
// 					$.ZMessage.error("错误", rdata.msg, function () {
// 	                });
// 				}
// 			}
// 		});
//    	//---------end------流程中有修改页面，需要提交业务数据操作------------------
   	
//    	//4、返回流程状态
//    	return WORKFLOW_FLAG;
//   	 };
   
//    //提交方法
//     ZDS_WORKFLOW_CLIENT.submitFunction = ZDS_WORKFLOW_CLIENT.saveFunction;
		
 }); 
</script>
</body>

</html>