<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<div class="page-box">
	<div class="p10">
		<form id="edit_form" class="zui-form " method="post" enctype="multipart/form-data">
			<div class="page-title">
				<h1 class="page-title">基本信息</h1>
			</div>
			<div class="page-box">
				<div class="p5">
		            <table class="table-detail">
						<tr>
		                    <td class="td-title pct10">申请单</td>
		                    <td class="pct20">
		                    	${vo.applyNo }
		                    </td>
		                    <td class="td-title pct10">机构名称</td>
		                    <td class="pct20">
		                    	${emp.orgNm}
		                    </td>
		                    <td class="td-title pct10">申请人状态</td>
		                    <td class="pct20">
		                    	${vo.applyStatusNm }
		                    </td>
						</tr>
						<tr>
		                    <td class="td-title pct10">驻点寄出快递单号</td>
		                    <td class="pct20">
		                    	${vo.trackingNoSend }
		                    </td>
		                    <td class="td-title pct10">合同退回信托快递单号</td>
		                    <td class="pct20">
		                    	${vo.trackingNoReceive}
		                    </td>
		                    <td class="td-title pct10"></td>
		                    <td class="pct20"></td>
						</tr>
					</table>
				</div>
			</div>
			
			<div class="page-title">
				<h1 class="page-title">打印资料明细清单</h1>
			</div>
			<div class="page-box">
				<div class="p10">
					<div id="tb-plan" class="zui-datagrid"
						zdata-options='{"url":"<z:ukey key="com.zdsoft.finance.contract.formatContractDetailList" context="admin"/>&jsoncallback=?&formatContractApplyId|E|S=${vo.id}","singleSelect":true,"pagination":true,"idField":"id","toolbar":"#btn-function","tableCls":"table-index"}'>
						<table>
							<tr>
								<th data-options="field:capitalistName">资方</th>
					            <th data-options="field:contractTypeName">合同类型</th>
					            <th data-options="field:contractName">合同名称</th>
					            <th data-options="field:contractCopies">份数</th>
					            <th data-options="field:id" formatter="operateFormat">操作</th>
							</tr>
						</table>
					</div>
				</div>
			</div>
			
			<div class="page-title">
				<h1 class="page-title">其他信息</h1>
			</div>
			<div class="page-box">
				<div class="p5">
		            <table class="table-detail">
						<tr>
		                    <td class="td-title pct10">申请人</td>
		                    <td class="pct20">
		                    	<input class="zui-input zui-validatebox" disabled="disabled" value="${emp.empNm}">
		                    	<input type="hidden" name="applyEmpCode" value="${emp.empCd}"/>
		                    </td>
		                    <td class="td-title pct10">申请时间</td>
		                    <td class="pct20">
		                    	<label>
		                            <input type="zui-input zui-validatebox" disabled="disabled" value="${vo.applyDate}"/>
		                            <input type="hidden" name="applyDate" value="${vo.applyDate}"/>
			                    </label>
							</td>
						</tr>
						<tr>
			         		<td class="td-title pct10">备注</td>
			                <td colspan="3">
			                    <label>
			                    	<textarea class="zui-area row-width" id="remark" name="remark" disabled="disabled" validate-type="Require,Length[0-200]" placeholder="最多可以输入200个字符">${vo.remark }</textarea>
			                    </label>
		                   	</td>
						</tr>
					</table>
				</div>
			</div>
			
			<div class="page-title">
				<h1 class="page-title">快递单号</h1>
			</div>
			<div class="page-box">
				<div class="p5">
		            <table class="table-detail">
						<tr>
		                    <td class="td-title pct10"><b class="c-red mr5">*</b>驻点寄出快递单号</td>
		                    <td class="pct20">
		                    	<input class="zui-input zui-validatebox" id="trackingNoSend" name="trackingNoSend" validate-type="Require,Length[1-10]" >
		                    </td>
		                    <td class="td-title pct10"></td>
		                    <td class="pct20"></td>
		                    <td class="td-title pct10"></td>
		                    <td class="pct20"></td>
						</tr>
					</table>
				</div>
			</div>
			<input type="hidden" name="id" id="formatContractApplyId" value="${vo.id}">
		</form>
	</div>
</div>
<script type="text/javascript">
	seajs.use(['jquery', 'zd/jquery.zds.page.callback','zd/jquery.zds.combobox', 
	           'zd/jquery.zds.loading', 'zd/switch', 'zd/jquery.zds.dialog', 'zd/jquery.zds.message', 
	           'zd/jquery.zds.form', 'datepicker', 'zd/jquery.zds.table', 'zd/jquery.zds.seleter'
	           ],function($, CALLBACK, Loading, Switch, Zdialog, ZUI_MESSAGE_CLIENT) {
		
		//操作
		CALLBACK.operateFormat = function(index, row) {
			var html = '<a title="查看" onclick="doView" class="btn-blue">查看</a>';
				html += '&nbsp;&nbsp;<a title="下载" onclick="doDownload" class="btn-blue">下载</a>';
            return html;
		};
		
		//查看合同实物列表
		CALLBACK.doView=function(index, rowDate){
			var id = rowDate.id;
			ZDS_MESSAGE_CLIENT.openMenuLink('contract_view', '格式化合同实物列表', '<z:ukey key="com.zdsoft.finance.contract.viewFormatContractApply" context="admin"/>&id='+id);
		}
		
		//下载
		CALLBACK.doDownload=function(index,data){
			var downLoadUrl = '<z:ukey key="public.upload.download"  context="admin"/>';
			window.location.href = downLoadUrl+"&attachmentId="+data.attachmentId;
		}
		
		//保存
        ZDS_WORKFLOW_CLIENT.saveFunction = function (datas) {
            //---------start------流程中有修改页面，需要提交业务数据操作------------------
            //校验
            var validate = $.ZUI.validateForm($('#edit_form'));
            if (!validate) {
                $.ZMessage.error("错误", "数据验证失败!", function () {
                });
                return false;
            }
            var id = $('#formatContractApplyId').val();
            var trackingNoSend = $('#trackingNoSend').val();
            var params;
            var args = JSON.parse(datas.args);
            params += '&processInstanceId=' + args.processInstanceId;
            params += '&taskInstanceId=' + args.taskInstanceId;
            params += '&taskId=' + args.taskId;
            params += '&taskName=' + args.taskName;
            params += '&jobAppCd=' + args.jobAppCd;
            params += '&id=' + id;
            params += '&trackingNoSend=' + trackingNoSend;
            $.ajax({
                url:'<z:ukey key="com.zdsoft.finance.contract.processSaveStationSend" context="admin"/>',
                data:params,
                type:"post",
                dataType:"json",
                success:function(rdata){
                    var msg="";
                    if(rdata.resultStatus == 0){
                        //执行回调函数
                        ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_SUCCESS,rdata.msg);
                    }else{
                        //执行回调函数
                        ZDS_WORKFLOW_CLIENT.callBackFuntion(datas,ZDS_WORKFLOW_PARAM._STATUS_ERROR,rdata.msg);
                    }
                }
            });
            //---------end------流程中有修改页面，需要提交业务数据操作------------------
        };

        //提交方法
        ZDS_WORKFLOW_CLIENT.submitFunction = ZDS_WORKFLOW_CLIENT.saveFunction;
		
	 	$.ZUI.init();
	 });
</script>
